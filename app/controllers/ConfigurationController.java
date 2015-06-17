package controllers;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import models.Configuration;
import models.Event;
import models.data.FormData;
import models.template.Template;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import formulars.ConfigurationForm.AddEventForm;
import formulars.ConfigurationForm.ContactForm;
import formulars.ConfigurationForm.CourseForm;
import formulars.ConfigurationForm.EmailForm;
import formulars.ConfigurationForm.EventForm;
import formulars.ConfigurationForm.GlobalForm;
import formulars.ConfigurationForm.ImpressumForm;
import formulars.ConfigurationForm.LoginTemplateForm;
import formulars.ConfigurationForm.ParticipantForm;
import formulars.ConfigurationForm.PrivacyForm;
import formulars.ConfigurationForm.TicketForm;

public class ConfigurationController extends Controller {

	static Form<GlobalForm> globalForm = Form.form(GlobalForm.class);
	static Form<EventForm> eventForm = Form.form(EventForm.class);
	static Form<AddEventForm> addEventForm = Form.form(AddEventForm.class);
	static Form<TicketForm> ticketForm = Form.form(TicketForm.class);
	static Form<CourseForm> courseForm = Form.form(CourseForm.class);
	static Form<EmailForm> emailForm = Form.form(EmailForm.class);

	public static Result showConfiguration(String shortcode) {
		EventController.event = Event.findByShortcode(shortcode);
		return ok(views.html.configuration.render(globalForm, eventForm,
				addEventForm, ticketForm, courseForm, emailForm));
	}

	public static Result saveGlobal(String shortcode) {
		Form<GlobalForm> globalData = Form.form(GlobalForm.class)
				.bindFromRequest();
		if (globalData.hasErrors()) {
			flash("danger", "Fehler in der Allgemeinen Einstellung.");
			return badRequest(views.html.configuration.render(globalData,
					eventForm, addEventForm, ticketForm, courseForm, emailForm));
		} else {
			EventController.event.configuration.edit(
					globalData.get().pageTitle, globalData.get().pageHeader,
					globalData.get().pageWidth, globalData.get().pageBorder,
					globalData.get().pageHeadlineBackgroundcolor,
					globalData.get().pageNavigationBackgroundcolor,
					globalData.get().pageContentBackgroundcolor,
					globalData.get().pageFooterBackgroundcolor,
					globalData.get().pageBackgroundimage,
					globalData.get().pageFooterLeft,
					globalData.get().pageFooterRight,
					globalData.get().multipleLanguages, globalData.get().theme);
			flash("success", "Konfiguration geändert.");
			return redirect("/configuration?shortcode=" + shortcode
					+ "#tabGlobal");
		}
	}

	public static Result saveEvent(String shortcode) throws ParseException {
		Form<EventForm> eventData = Form.form(EventForm.class)
				.bindFromRequest();
		if (eventData.hasErrors()) {
			flash("danger", "Fehler im Event.");
			return badRequest(views.html.configuration.render(globalForm,
					eventData, addEventForm, ticketForm, courseForm, emailForm));
		} else {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = sdf.parse(eventData.get().eventStartDate);
			java.sql.Date sqlStartDate = new Date(date.getTime());

			java.util.Date date2 = sdf.parse(eventData.get().eventEndDate);
			java.sql.Date sqlEndDate = new Date(date2.getTime());

			EventController.event.edit(eventData.get().eventName,
					eventData.get().eventDescription,
					eventData.get().eventDescriptionImage, sqlStartDate,
					sqlEndDate, eventData.get().eventSupportEmail,
					eventData.get().eventFacebook,
					eventData.get().eventTwitter,
					eventData.get().eventGoogleplus);

			flash("success", "Event geändert.");
			return redirect("/configuration?shortcode=" + shortcode
					+ "#tabEvent");
		}
	}

	public static Result addEvent(String shortcode) throws ParseException {
		Form<AddEventForm> addEventData = Form.form(AddEventForm.class)
				.bindFromRequest();

		if (addEventData.hasErrors()) {
			flash("danger", "Fehler beim Erstellen eines Events.");
			return badRequest(views.html.configuration.render(globalForm,
					eventForm, addEventData, ticketForm, courseForm, emailForm));
		} else {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = sdf
					.parse(addEventData.get().addEventStartDate);
			java.sql.Date sqlStartDate = new Date(date.getTime());

			java.util.Date date2 = sdf
					.parse(addEventData.get().addEventEndDate);
			java.sql.Date sqlEndDate = new Date(date2.getTime());

			Configuration configuration = new Configuration(
					addEventData.get().configurationTitle,
					addEventData.get().configurationLogo);
			Event event = new Event(addEventData.get().addEventName,
					addEventData.get().addEventShortcode,
					addEventData.get().addEventDescription, sqlStartDate,
					sqlEndDate, configuration);
			flash("success", "Event hinzugefügt.");
			return redirect("/configuration?shortcode=" + event.shortcode
					+ "#tabGlobal");
		}
	}

	public static Result saveLoginTemplate(String shortcode) {
		Form<LoginTemplateForm> loginTemplateData = Form.form(
				LoginTemplateForm.class).bindFromRequest();
		if (loginTemplateData.hasErrors()) {
			flash("danger", "Fehler beim Ändern der Login Template Daten.");
			return badRequest(views.html.configuration.render(globalForm,
					eventForm, addEventForm, ticketForm, courseForm, emailForm));
		} else {
			Template template = EventController.event.configuration
					.findTemplateByName("loginTemplate");
			template.login = loginTemplateData.data().get("login") == null ? false
					: true;
			template.registration = loginTemplateData.data()
					.get("registration") == null ? false : true;
			template.passwordLogin = loginTemplateData.data().get(
					"passwordLogin") == null ? false : true;
			template.passwords = loginTemplateData.data().get("passwords");
			template.update();
			flash("success", "Persönliche Daten geändert.");
			return redirect("/configuration?shortcode=" + shortcode
					+ "#tabLoginTemplate");
		}
	}

	public static Result saveParticipantForm(String shortcode) {
		Form<ParticipantForm> participantData = Form
				.form(ParticipantForm.class).bindFromRequest();
		if (participantData.hasErrors()) {
			flash("danger", "Fehler beim Ändern des Persönlichen Daten.");
			return badRequest(views.html.configuration.render(globalForm,
					eventForm, addEventForm, ticketForm, courseForm, emailForm));
		} else {

			for (FormData form : EventController.event.configuration
					.findTemplateByName("participantTemplate").formData) {
				form.icon = participantData.data().get("select" + form.name);
				form.display = participantData.data()
						.get("display" + form.name) == null ? false : true;
				form.required = participantData.data().get(
						"required" + form.name) == null ? false : true;
				form.update();
			}
			for (FormData form : EventController.event.configuration
					.findTemplateByName("billingTemplate").formData) {
				form.icon = participantData.data().get(
						"selectbilling" + form.name);
				form.display = participantData.data().get(
						"displaybilling" + form.name) == null ? false : true;
				form.required = participantData.data().get(
						"requiredbilling" + form.name) == null ? false : true;
				form.update();
			}
			flash("success", "Persönliche Daten geändert.");
			return redirect("/configuration?shortcode=" + shortcode
					+ "#tabParticipant");
		}
	}

	public static Result saveEmail(String shortcode) {
		Form<EmailForm> emailData = Form.form(EmailForm.class)
				.bindFromRequest();
		if (emailData.hasErrors()) {
			flash("danger", "Fehler beim Ändern der E-Mail-Einstellungen.");
			return badRequest(views.html.configuration.render(globalForm,
					eventForm, addEventForm, ticketForm, courseForm, emailData));
		} else {
			EventController.event.configuration.emailConfig.edit(
					emailData.get().emailDisplay == null ? false : true,
					emailData.get().emailSubject,
					emailData.get().emailFromName,
					emailData.get().emailFromEmail,
					emailData.get().emailAddTNB == null ? false : true,
					emailData.get().emailAddAttachement == null ? false : true,
					emailData.get().emailBodyText);
			flash("success", "E-Mail-Einstellungen geändert.");
			return redirect("/configuration?shortcode=" + shortcode
					+ "#tabEmails");
		}
	}

	public static Result saveImpressum(String shortcode) {
		Form<ImpressumForm> impressumData = Form.form(ImpressumForm.class)
				.bindFromRequest();
		if (impressumData.hasErrors()) {
			flash("danger", "Fehler beim Ändern des Impressums.");
			return badRequest(views.html.configuration.render(globalForm,
					eventForm, addEventForm, ticketForm, courseForm, emailForm));
		} else {
			EventController.event.configuration.impressum = impressumData.get().impressum;
			EventController.event.configuration.update();
			flash("success", "Impressum geändert.");
			return redirect("/configuration?shortcode=" + shortcode
					+ "#tabImpressum");
		}
	}

	public static Result savePrivacy(String shortcode) {
		Form<PrivacyForm> privacyData = Form.form(PrivacyForm.class)
				.bindFromRequest();
		if (privacyData.hasErrors()) {
			flash("danger", "Fehler beim Ändern der Datenschutzbestimmung.");
			return badRequest(views.html.configuration.render(globalForm,
					eventForm, addEventForm, ticketForm, courseForm, emailForm));
		} else {
			EventController.event.configuration.privacy = privacyData.get().privacy;
			EventController.event.configuration.update();
			flash("success", "Datenschutzbestimmungen geändert.");
			return redirect("/configuration?shortcode=" + shortcode
					+ "#tabPrivacy");
		}
	}

	public static Result saveContact(String shortcode) {
		Form<ContactForm> contactData = Form.form(ContactForm.class)
				.bindFromRequest();
		if (contactData.hasErrors()) {
			flash("danger", "Fehler beim Ändern des Kontaktes.");
			return badRequest(views.html.configuration.render(globalForm,
					eventForm, addEventForm, ticketForm, courseForm, emailForm));
		} else {
			EventController.event.configuration.contact = contactData.get().contact;
			EventController.event.configuration.update();
			flash("success", "Kontakt geändert.");
			return redirect("/configuration?shortcode=" + shortcode
					+ "#tabContact");
		}
	}
}

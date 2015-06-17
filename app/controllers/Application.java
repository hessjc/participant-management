package controllers;

import static play.data.Form.form;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;

import models.Event;
import models.Participant;
import play.Routes;
import play.data.Form;
import play.libs.Json;
import play.libs.mailer.Email;
import play.libs.mailer.MailerPlugin;
import play.mvc.Controller;
import play.mvc.Result;
import controllers.account.Reset.AskForm;
import formulars.AuthenticationForm.LoginParticipantForm;

public class Application extends Controller {

	public static Result showStart(String shortcode) {
		EventController.event = Event.findByShortcode(shortcode);
		return ok(views.html.start.render());
	}

	public static Result showLogin(String shortcode) {
		EventController.event = Event.findByShortcode(shortcode);
		session().clear();
		Form<LoginParticipantForm> loginParticipantForm = Form
				.form(LoginParticipantForm.class);
		Form<AskForm> askForm = form(AskForm.class);
		return ok(views.html.login.render(loginParticipantForm, askForm));
	}

	public static Result sendMail(Long pid) throws EmailException,
			MalformedURLException {

		Participant participant = Participant.find.byId(pid);

		Email email = new Email();
		email.setSubject(EventController.event.configuration.emailConfig.subject);
		email.setFrom(EventController.event.configuration.emailConfig.fromName
				+ " <"
				+ EventController.event.configuration.emailConfig.fromEmail
				+ ">");
		email.addTo(participant.firstname + " " + participant.lastname + " <"
				+ participant.email + ">");
		// adds attachment
		// email.addAttachment("attachment.pdf", new
		// File("/some/path/attachment.pdf"));
		// adds inline attachment from byte array
		email.addAttachment("data.txt", "data".getBytes(), "text/plain",
				"Simple data", EmailAttachment.INLINE);

		// sends text, HTML or both...
		email.setBodyText("A text message");
		email.setBodyHtml(EventController.event.configuration.emailConfig.bodyText);
		MailerPlugin.send(email);

		flash("success", "Teilnehmer angelegt.");
		flash("success", "E-Mail an " + participant.email + " versendet.");

		Form<LoginParticipantForm> loginParticipantForm = Form
				.form(LoginParticipantForm.class);
		Form<AskForm> askForm = form(AskForm.class);
		return ok(views.html.login.render(loginParticipantForm, askForm));
	}

	public void writePDF() {
		// Create a document and add a page to it
		// PDDocument document = new PDDocument();
		// PDPage page = new PDPage();
		// document.addPage(page);
		//
		// // Create a new font object selecting one of the PDF base fonts
		// PDFont font = PDType1Font.HELVETICA_BOLD;
		//
		// // Start a new content stream which will "hold" the to be created
		// // content
		// PDPageContentStream contentStream = new PDPageContentStream(document,
		// page);
		//
		// // Define a text content stream using the selected font, moving the
		// // cursor and drawing the text "Hello World"
		// contentStream.beginText();
		// contentStream.setFont(font, 12);
		// contentStream.moveTextPositionByAmount(100, 700);
		// contentStream.drawString("Hello World");
		// contentStream.endText();
		//
		// // Make sure that the content stream is closed:
		// contentStream.close();
		//
		// // Save the results and ensure that the document is properly closed:
		// document.save("Hello World.pdf");
		// document.close();
	}

	public static Result showCalendar(String shortcode) {
		EventController.event = Event.findByShortcode(shortcode);
		return ok(views.html.calendar.render());
	}

	public static Result showNews(String shortcode) {
		EventController.event = Event.findByShortcode(shortcode);
		return ok(views.html.news.render());
	}

	public static Result showImpressum(String shortcode) {
		EventController.event = Event.findByShortcode(shortcode);
		return ok(views.html.impressum.render());
	}

	public static Result showPrivacy(String shortcode) {
		EventController.event = Event.findByShortcode(shortcode);
		return ok(views.html.privacy.render());
	}

	public static Result showContact(String shortcode) {
		EventController.event = Event.findByShortcode(shortcode);
		return ok(views.html.contact.render());
	}

	public static Result upload(String fileName, String filePath)
			throws IOException {

		return ok("File uploaded");
	}

	public static Result changeLanguage(String lang) {
		changeLang(lang);
		return ok(Json.newObject().put("status", "OK"));
	}

	public static Result changeEvent(String shortcode) {
		return ok(Json.newObject().put("shortcode", shortcode));
	}

	public static Result refreshGlobalMessage() {
		return ok(views.html.inc.alert.render());
	}

	public static Result javascriptRoutes() {
		response().setContentType("text/javascript");
		return ok(Routes.javascriptRouter("myJsRoutes",
				routes.javascript.TicketController.refreshTicketPrice(),
				routes.javascript.TicketController.addTicket(),
				routes.javascript.TicketController.saveTicket(),
				routes.javascript.CourseController.saveCourse(),
				routes.javascript.Application.refreshGlobalMessage(),
				routes.javascript.Application.upload(),
				routes.javascript.Application.changeLanguage(),
				routes.javascript.Application.changeEvent()));
	}
}

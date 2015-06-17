package controllers;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import models.Event;
import models.Participant;
import models.Ticket;
import models.service.ParticipantTicketService;
import models.utils.AppException;

import org.apache.commons.mail.EmailException;

import play.cache.Cache;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import formulars.ParticipantForm;
import formulars.RegistrationForm.TicketForm;

public class RegistrationController extends Controller {

	public static Result showRegistration(String shortcode, Integer stepIndex) {
		EventController.event = Event.findByShortcode(shortcode);
		if (utilities.Configuration.registerTemplates.get(stepIndex).equals(
				"participant"))
			return redirect(routes.RegistrationController.showParticipant(
					shortcode, stepIndex));
		else if (utilities.Configuration.registerTemplates.get(stepIndex)
				.equals("ticket"))
			return redirect(routes.RegistrationController.showTicket(shortcode,
					stepIndex));
		else if (utilities.Configuration.registerTemplates.get(stepIndex)
				.equals("overview"))
			return redirect(routes.RegistrationController.showOverview(
					shortcode, stepIndex));
		else
			return ok(views.html.start.render());
	}

	public static Result showParticipant(String shortcode, Integer stepIndex) {
		EventController.event = Event.findByShortcode(shortcode);
		Form<ParticipantForm> participantForm = Form
				.form(ParticipantForm.class);
		return ok(views.html.register.participant.render(participantForm,
				stepIndex));
	}

	public static Result showTicket(String shortcode, Integer stepIndex) {
		EventController.event = Event.findByShortcode(shortcode);
		Form<TicketForm> ticketForm = Form.form(TicketForm.class);
		return ok(views.html.register.ticket.render(ticketForm, stepIndex));
	}

	public static Result showOverview(String shortcode, Integer stepIndex) {
		EventController.event = Event.findByShortcode(shortcode);
		return ok(views.html.register.overview.render(
				(Participant) Cache.get("participant"),
				(List<String>) Cache.get("tickets"), stepIndex));
	}

	public static Result addParticipant(String shortcode, Integer stepIndex)
			throws NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException {

		Form<ParticipantForm> participantData = Form
				.form(ParticipantForm.class).bindFromRequest();

		if (participantData.hasErrors()) {
			flash("danger", "Please correct errors above.");
			return badRequest(views.html.register.participant.render(
					participantData, stepIndex));
		} else {

			Participant participant = new Participant();
			for (Field field : ParticipantForm.class.getDeclaredFields()) {
				if (participantData.data().get(field.getName()) != null) {
					Field editField = Participant.class.getField(field
							.getName());
					editField.set(participant,
							participantData.data().get(field.getName()));
				}
			}
			Cache.set("participant", participant);
			return redirect(routes.RegistrationController.showRegistration(
					shortcode, stepIndex + 1));
		}
	}

	public static Result addTicket(String shortcode, Integer stepIndex) {

		Form<TicketForm> ticketData = Form.form(TicketForm.class)
				.bindFromRequest();

		if (ticketData.hasErrors()) {
			flash("danger", "Please correct errors above. ");
			return badRequest(views.html.register.ticket.render(ticketData,
					stepIndex));
		} else {
			List<String> tickets = new ArrayList<String>();
			for (Ticket ticket : Ticket.find.all()) {
				tickets.add(ticketData.data().get("ticket." + ticket.id));
			}

			Cache.set("tickets", tickets);
			return redirect(routes.RegistrationController.showRegistration(
					shortcode, stepIndex + 1));
		}
	}

	public static Result submitRegistration(String shortcode, Integer stepIndex)
			throws MalformedURLException, EmailException, AppException {

		Participant participant = (Participant) Cache.get("participant");
		participant.save();
		participant.changePassword(participant.password);
		Cache.remove("participant");

		Long index = (long) 1;
		for (String ticket : (List<String>) Cache.get("tickets")) {
			for (Integer count = 0; count < Integer.parseInt(ticket); count++) {
				participant.ptServices.add(new ParticipantTicketService(
						participant, Ticket.find.byId(index)));
			}
			index++;
		}
		participant.update();

		Application.sendMail(participant.id);

		return redirect(routes.Application.showLogin(shortcode));
	}
}

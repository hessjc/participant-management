package controllers;

import java.io.File;

import models.Ticket;
import play.data.Form;
import play.i18n.Messages;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import formulars.ConfigurationForm.TicketForm;

public class TicketController extends Controller {

	public static File file;

	public Result refreshTicketPrice(Long id, Integer count) {
		Double price = Ticket.find.byId(id).price * count;
		return ok(Json.newObject().put(
				"price",
				utilities.NumberHelper.decimalFormat.format(price) + " "
						+ Messages.get("main.currency.shortcode")));
	}

	public Result addTicket(String shortcode) {

		Form<TicketForm> ticketData = Form.form(TicketForm.class)
				.bindFromRequest();

		if (ticketData.hasErrors()) {
			flash("danger", "Fehler.");
			return badRequest(views.html.configuration.render(
					ConfigurationController.globalForm,
					ConfigurationController.eventForm,
					ConfigurationController.addEventForm, ticketData,
					ConfigurationController.courseForm,
					ConfigurationController.emailForm));
		} else {

			Ticket ticket = new Ticket(ticketData.get().ticketTitle,
					ticketData.get().ticketDescription,
					ticketData.get().ticketPrice, ticketData.get().ticketMinpp,
					ticketData.get().ticketMaxpp,
					ticketData.get().ticketContingent,
					ticketData.get().ticketDuration, ticketData.get().ticketImg);
			ticket.save();

			flash("success", "Ticket hinzugefügt.");
			return redirect("/configuration?shortcode=" + shortcode
					+ "#tabTickets");
		}
	}

	public Result saveTicket(Long id, String title, String description,
			Double price, Integer minpp, Integer maxpp, Integer contingent,
			Integer duration, String img) {
		Ticket ticket = Ticket.find.byId(id).edit(title, description, price,
				minpp, maxpp, contingent, duration, img);
		ticket.save();
		flash("success", "Ticket (ID: " + id + ") erfolgreich gespeichert.");
		return ok(Json.newObject().put("status", "OK"));
	}

	public Result editTicket(Long id) {
		String shortcode = EventController.event.shortcode;
		flash("info", "Ticket (ID: " + id + ") editiert.");
		return redirect("/configuration?shortcode=" + shortcode + "#tabTickets");
	}

	public Result removeTicket(Long id) {
		String shortcode = EventController.event.shortcode;
		Ticket.find.byId(id).delete();
		flash("danger", "Ticket (ID: " + id + ") gelöscht.");
		return redirect("/configuration?shortcode=" + shortcode + "#tabTickets");
	}
}

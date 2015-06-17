package controllers;

import static play.data.Form.form;

import java.util.Arrays;

import models.Event;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import controllers.account.Reset.AskForm;
import formulars.AuthenticationForm.LoginParticipantForm;
import formulars.AuthenticationForm.PasswordParticipantForm;

public class AuthenticationController extends Controller {
	
	public static Result authenticateParticipant(String shortcode) {
		EventController.event = Event.findByShortcode(shortcode);
		Form<LoginParticipantForm> loginParticipantForm = Form.form(
				LoginParticipantForm.class).bindFromRequest();
		Form<AskForm> askForm = form(AskForm.class);
		if (loginParticipantForm.hasErrors()) {
			flash("danger", "falsche daten");
			return badRequest(views.html.login.render(loginParticipantForm,
					askForm));
		} else {
			session("username", loginParticipantForm.get().username);
			flash("success", "login erfolgreich.");
			return ok(views.html.dashboard.render());
		}
	}

	public static Result passwordLogin(String shortcode) {
		EventController.event = Event.findByShortcode(shortcode);
		Form<PasswordParticipantForm> passwordParticipantForm = Form.form(PasswordParticipantForm.class)
				.bindFromRequest();
		if (!passwordParticipantForm.hasErrors()
				&& passwordConfirmed(passwordParticipantForm.get().password)) {
			flash("success", "korrekt.");
			return redirect(routes.RegistrationController.showRegistration(
					EventController.event.shortcode, 0));
		} else {
			flash("danger", "Falsches Passwort.");
			return badRequest(views.html.login.render(
					Form.form(LoginParticipantForm.class), Form.form(AskForm.class)));
		}
	}

	public static boolean passwordConfirmed(String password) {
		for (String loginPassword : Arrays
				.asList(EventController.event.configuration
						.findTemplateByName("loginTemplate").passwords
						.split(","))) {
			if (loginPassword.equals(password))
				return true;
		}

		return false;
	}
}

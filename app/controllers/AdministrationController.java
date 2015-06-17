package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import formulars.AuthenticationForm.LoginUserForm;

public class AdministrationController extends Controller {

	public static Result administration() {
		session().clear();
		Form<LoginUserForm> loginUserForm = Form.form(LoginUserForm.class);
		return ok(views.html.administration.render(loginUserForm));
	}

	/**
	 * Handle login form submission.
	 *
	 * @return Dashboard if auth OK or login form if auth KO
	 */
	public static Result authenticate() {
		Form<LoginUserForm> loginUserForm = Form.form(LoginUserForm.class)
				.bindFromRequest();

		if (loginUserForm.hasErrors()) {
			flash("danger", "login fehlgeschlagen.");
			return badRequest(views.html.administration.render(loginUserForm));
		} else {
			String shortcode = loginUserForm.get().shortcode;
			session("username", loginUserForm.get().username);
			flash("success", "login erfolgreich.");
			return redirect("/configuration?shortcode=" + shortcode
					+ "#tabGlobal");
		}
	}
}

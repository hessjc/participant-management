package formulars;

import java.util.ArrayList;
import java.util.List;

import models.Participant;
import models.User;
import models.utils.AppException;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;

public class AuthenticationForm {

	public static class LoginUserForm extends AuthenticationForm {

		@Constraints.Required
		public String username;
		@Constraints.Required
		public String password;
		public String shortcode;

		/** Required for form instantiation. */
		public LoginUserForm() {
		}

		public List<ValidationError> validate() {

			List<ValidationError> errors = new ArrayList<>();

			User user = null;
			try {
				user = User.authenticate(username, password);
				if (user == null)
					errors.add(new ValidationError("danger",
							"fehler falsche datern."));
			} catch (AppException e) {
				errors.add(new ValidationError("error", "fehler im login."));
			}
			if (username == null || username.length() == 0) {
				errors.add(new ValidationError("username",
						"No username was given."));
			}

			if (password == null || password.length() == 0) {
				errors.add(new ValidationError("password",
						"No password was given."));
			}

			return errors.isEmpty() ? null : errors;
		}
	}

	public static class PasswordUserForm {

		@Constraints.Required
		public String password;

		/** Required for form instantiation. */
		public PasswordUserForm() {
		}

		public List<ValidationError> validate() {

			List<ValidationError> errors = new ArrayList<>();

			if (password == null || password.length() == 0) {
				errors.add(new ValidationError("password",
						"No password was given."));
			}

			return errors.isEmpty() ? null : errors;
		}
	}

	public static class LoginParticipantForm extends AuthenticationForm {

		@Constraints.Required
		public String username;
		@Constraints.Required
		public String password;
		public String shortcode;

		/** Required for form instantiation. */
		public LoginParticipantForm() {
		}

		public List<ValidationError> validate() {

			List<ValidationError> errors = new ArrayList<>();

			Participant participant = null;
			try {
				participant = Participant.authenticate(username, password);
				if (participant == null)
					errors.add(new ValidationError("danger",
							"fehler falsche datern."));
			} catch (AppException e) {
				errors.add(new ValidationError("error", "fehler im login."));
			}
			if (username == null || username.length() == 0) {
				errors.add(new ValidationError("username",
						"No username was given."));
			}

			if (password == null || password.length() == 0) {
				errors.add(new ValidationError("password",
						"No password was given."));
			}

			return errors.isEmpty() ? null : errors;
		}
	}

	public static class PasswordParticipantForm {

		@Constraints.Required
		public String password;

		/** Required for form instantiation. */
		public PasswordParticipantForm() {
		}

		public List<ValidationError> validate() {

			List<ValidationError> errors = new ArrayList<>();

			if (password == null || password.length() == 0) {
				errors.add(new ValidationError("password",
						"No password was given."));
			}

			return errors.isEmpty() ? null : errors;
		}
	}
}

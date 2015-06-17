package formulars;

import java.util.ArrayList;
import java.util.List;

import play.data.validation.ValidationError;

public class RegistrationForm {

	public static class TicketForm extends RegistrationForm {

		public Integer checkError;

		/** Required for form instantiation. */
		public TicketForm() {
		}

		public List<ValidationError> validate() {

			List<ValidationError> errors = new ArrayList<>();

			if (checkError == null || checkError == 0) {
				errors.add(new ValidationError("validation",
						"No Ticket was selected."));
			}

			return errors.isEmpty() ? null : errors;
		}
	}
}

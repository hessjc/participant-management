package formulars;

import java.util.ArrayList;
import java.util.List;

import play.data.validation.ValidationError;

public class ConfigurationForm {

	public static class GlobalForm extends ConfigurationForm {

		public String pageTitle;
		public String pageHeader;
		public Integer pageWidth = 100;
		public Boolean pageBorder = false;
		public String pageHeadlineBackgroundcolor = "ffffff";
		public String pageNavigationBackgroundcolor = "ffffff";
		public String pageContentBackgroundcolor = "ffffff";
		public String pageFooterBackgroundcolor = "ffffff";
		public String pageBackgroundimage;
		public String pageFooterLeft;
		public String pageFooterRight;
		public Boolean multipleLanguages = false;
		public String theme;

		/** Required for form instantiation. */
		public GlobalForm() {
		}

		public List<ValidationError> validate() {

			List<ValidationError> errors = new ArrayList<>();

			if (pageTitle == null || pageTitle.length() == 0) {
				errors.add(new ValidationError("pageTitle",
						"No pageTitle was given."));
			}
			if (pageHeader == null || pageHeader.length() == 0) {
				errors.add(new ValidationError("pageHeader",
						"No pageHeader was given."));
			}
			if (pageWidth == null || pageWidth < 0) {
				errors.add(new ValidationError("pageWidth",
						"No pageWidth was given."));
			}

			return errors.isEmpty() ? null : errors;
		}
	}

	public static class EventForm extends ConfigurationForm {

		public String eventName;
		public String eventDescription;
		public String eventDescriptionImage;
		public String eventStartDate;
		public String eventEndDate;
		public String eventSupportEmail;
		public String eventFacebook;
		public String eventTwitter;
		public String eventGoogleplus;

		public EventForm() {
		}

		public List<ValidationError> validate() {

			List<ValidationError> errors = new ArrayList<ValidationError>();

			if (eventName == null || eventName.length() == 0) {
				errors.add(new ValidationError("name", "No name was given."));
			}
			if (eventDescription == null || eventDescription.length() == 0) {
				errors.add(new ValidationError("description",
						"No description was given."));
			}
			if (eventStartDate == null || eventStartDate.length() == 0) {
				errors.add(new ValidationError("startdate",
						"No startdate was given."));
			}
			if (eventEndDate == null || eventEndDate.length() == 0) {
				errors.add(new ValidationError("enddate",
						"No enddate was given."));
			}

			return errors.isEmpty() ? null : errors;
		}
	}

	public static class AddEventForm extends ConfigurationForm {

		public String configurationTitle;
		public String configurationLogo;
		public Integer configurationWidth = 100;
		public String addEventName;
		public String addEventShortcode;
		public String addEventDescription;
		public String addEventStartDate;
		public String addEventEndDate;

		public AddEventForm() {
		}

		public List<ValidationError> validate() {

			List<ValidationError> errors = new ArrayList<ValidationError>();

			if (configurationTitle == null || configurationTitle.length() == 0) {
				errors.add(new ValidationError("titel", "No title was given."));
			}
			if (configurationLogo == null || configurationLogo.length() == 0) {
				errors.add(new ValidationError("logo", "No logo was given."));
			}
			if (addEventName == null || addEventName.length() == 0) {
				errors.add(new ValidationError("name", "No name was given."));
			}
			if (addEventDescription == null
					|| addEventDescription.length() == 0) {
				errors.add(new ValidationError("description",
						"No description was given."));
			}
			if (addEventStartDate == null || addEventStartDate.length() == 0) {
				errors.add(new ValidationError("startdate",
						"No startdate was given."));
			}
			if (addEventEndDate == null || addEventEndDate.length() == 0) {
				errors.add(new ValidationError("enddate",
						"No enddate was given."));
			}

			return errors.isEmpty() ? null : errors;
		}
	}

	public static class LoginTemplateForm extends ConfigurationForm {

		public Boolean registration = false;
		public Boolean login = false;
		public Boolean passwordLogin = false;
		public String passwords;

		public LoginTemplateForm() {
		}

		public List<ValidationError> validate() {

			List<ValidationError> errors = new ArrayList<ValidationError>();

			return errors.isEmpty() ? null : errors;
		}
	}

	public static class TicketForm extends ConfigurationForm {

		public String ticketTitle;
		public String ticketDescription;
		public Double ticketPrice;
		public Integer ticketMinpp;
		public Integer ticketMaxpp;
		public Integer ticketContingent;
		public Integer ticketDuration;
		public String ticketImg;

		/** Required for form instantiation. */
		public TicketForm() {
		}

		public List<ValidationError> validate() {

			List<ValidationError> errors = new ArrayList<>();

			if (ticketTitle == null || ticketTitle.length() == 0) {
				errors.add(new ValidationError("pageTitle",
						"No pageTitle was given."));
			}
			if (ticketDescription == null || ticketDescription.length() == 0) {
				errors.add(new ValidationError("description",
						"No description was given."));
			}

			return errors.isEmpty() ? null : errors;
		}
	}

	public static class CourseForm extends ConfigurationForm {

		public String title;
		public String description;
		public int slots = 0;

		/** Required for form instantiation. */
		public CourseForm() {
		}

		public CourseForm(String title, String description, int slots) {
			this.title = title;
			this.description = description;
			this.slots = slots;
		}

		public List<ValidationError> validate() {

			List<ValidationError> errors = new ArrayList<>();

			if (title == null || title.length() == 0) {
				errors.add(new ValidationError("pageTitle",
						"No pageTitle was given."));
			}
			if (description == null || description.length() == 0) {
				errors.add(new ValidationError("description",
						"No description was given."));
			}

			return errors.isEmpty() ? null : errors;
		}
	}

	public static class ParticipantForm extends ConfigurationForm {

		/** Required for form instantiation. */
		public ParticipantForm() {
		}

		public List<ValidationError> validate() {
			List<ValidationError> errors = new ArrayList<>();
			return errors.isEmpty() ? null : errors;
		}
	}

	public static class EmailForm extends ConfigurationForm {

		public Boolean emailDisplay;
		public String emailSubject;
		public String emailFromName;
		public String emailFromEmail;
		public Boolean emailAddTNB;
		public Boolean emailAddAttachement;
		public String emailBodyText;

		/** Required for form instantiation. */
		public EmailForm() {
		}

		public List<ValidationError> validate() {

			List<ValidationError> errors = new ArrayList<>();
			
			if (emailSubject == null || emailSubject.length() == 0) {
				errors.add(new ValidationError("fromName",
						"No fromName was given."));
			}
			if (emailFromName == null || emailFromName.length() == 0) {
				errors.add(new ValidationError("fromName",
						"No fromName was given."));
			}
			if (emailFromEmail == null || emailFromEmail.length() == 0) {
				errors.add(new ValidationError("fromName",
						"No fromName was given."));
			}

			return errors.isEmpty() ? null : errors;
		}
	}

	public static class ImpressumForm extends ConfigurationForm {

		public String impressum;

		/** Required for form instantiation. */
		public ImpressumForm() {
		}

		public ImpressumForm(String impressum) {
			this.impressum = impressum;
		}

		public List<ValidationError> validate() {

			List<ValidationError> errors = new ArrayList<>();

			if (impressum == null || impressum.length() == 0) {
				errors.add(new ValidationError("impressum",
						"No impressum was given."));
			}

			return errors.isEmpty() ? null : errors;
		}
	}

	public static class PrivacyForm extends ConfigurationForm {

		public String privacy;

		/** Required for form instantiation. */
		public PrivacyForm() {
		}

		public PrivacyForm(String privacy) {
			this.privacy = privacy;
		}

		public List<ValidationError> validate() {

			List<ValidationError> errors = new ArrayList<>();

			if (privacy == null || privacy.length() == 0) {
				errors.add(new ValidationError("privacy",
						"No privacy was given."));
			}

			return errors.isEmpty() ? null : errors;
		}
	}

	public static class ContactForm extends ConfigurationForm {

		public String contact;

		/** Required for form instantiation. */
		public ContactForm() {
		}

		public ContactForm(String contact) {
			this.contact = contact;
		}

		public List<ValidationError> validate() {

			List<ValidationError> errors = new ArrayList<>();

			if (contact == null || contact.length() == 0) {
				errors.add(new ValidationError("contact",
						"No contact was given."));
			}

			return errors.isEmpty() ? null : errors;
		}
	}
}

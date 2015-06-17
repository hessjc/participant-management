package formulars;

import java.util.ArrayList;
import java.util.List;

import models.utils.AppException;
import models.utils.Hash;
import play.data.validation.ValidationError;
import play.i18n.Messages;
import controllers.EventController;

public class ParticipantForm {

	public String salutation = "";
	public String lastname = "";
	public String firstname = "";
	public String birthday = "";
	public String badgeFirstname;
	public String badgeLastname;
	public String company;
	public String companyAdditional;
	public String department;
	public String serviceCostCenter;
	public String position;
	public String vatin;
	public String street;
	public String postofficebox;
	public Integer postcode;
	public String location;
	public String country;
	public String phone;
	public String mobilephone;
	public String telefax;
	public String email;
	public String url;
	public String username;
	public String password;
	public String passwordConfirmation;

	public Boolean billing;

	public String billingsalutation;
	public String billinglastname;
	public String billingfirstname;
	public String billingcompany;
	public String billingcompanyAdditional;
	public String billingdepartment;
	public String billingserviceCostCenter;
	public String billingvatin;
	public String billingstreet;
	public Integer billingpostcode;
	public String billinglocation;
	public String billingcountry;
	public String billingemail;

	/** Required for form instantiation. */
	public ParticipantForm() {
	}

	public List<ValidationError> validate() throws AppException {

		List<ValidationError> errors = new ArrayList<>();

		if ((salutation == null || salutation.length() == 0)
				&& EventController.event.configuration.findTemplateByName(
						"participantTemplate").findFormDataByName("salutation").required) {
			errors.add(new ValidationError("salutation", Messages
					.get("participant.salutation.error")));
		}
		if ((firstname == null || firstname.length() == 0)
				&& EventController.event.configuration.findTemplateByName(
						"participantTemplate").findFormDataByName("firstname").required) {
			errors.add(new ValidationError("firstname", Messages
					.get("participant.firstname.error")));
		}
		if ((lastname == null || lastname.length() == 0)
				&& EventController.event.configuration.findTemplateByName(
						"participantTemplate").findFormDataByName("lastname").required) {
			errors.add(new ValidationError("lastname", Messages
					.get("participant.lastname.error")));
		}
		if ((birthday == null || birthday.length() == 0)
				&& EventController.event.configuration.findTemplateByName(
						"participantTemplate").findFormDataByName("birthday").required) {
			errors.add(new ValidationError("birthday", Messages
					.get("participant.birthday.error")));
		}
		if ((badgeFirstname == null || badgeFirstname.length() == 0)
				&& EventController.event.configuration.findTemplateByName(
						"participantTemplate").findFormDataByName("badgeFirstname").required) {
			errors.add(new ValidationError("badgeFirstname", Messages
					.get("participant.badgeFirstname.error")));
		}
		if ((badgeLastname == null || badgeLastname.length() == 0)
				&& EventController.event.configuration.findTemplateByName(
						"participantTemplate").findFormDataByName("badgeLastname").required) {
			errors.add(new ValidationError("badgeLastname", Messages
					.get("participant.badgeLastname.error")));
		}
		if ((company == null || company.length() == 0)
				&& EventController.event.configuration.findTemplateByName(
						"participantTemplate").findFormDataByName("company").required) {
			errors.add(new ValidationError("company", Messages
					.get("participant.company.error")));
		}
		if ((companyAdditional == null || companyAdditional.length() == 0)
				&& EventController.event.configuration.findTemplateByName(
						"participantTemplate").findFormDataByName(
						"companyAdditional").required) {
			errors.add(new ValidationError("companyAdditional", Messages
					.get("participant.companyAdditional.error")));
		}
		if ((department == null || department.length() == 0)
				&& EventController.event.configuration.findTemplateByName(
						"participantTemplate").findFormDataByName("department").required) {
			errors.add(new ValidationError("department", Messages
					.get("participant.department.error")));
		}
		if ((serviceCostCenter == null || serviceCostCenter.length() == 0)
				&& EventController.event.configuration.findTemplateByName(
						"participantTemplate").findFormDataByName(
						"serviceCostCenter").required) {
			errors.add(new ValidationError("serviceCostCenter", Messages
					.get("participant.serviceCostCenter.error")));
		}
		if ((position == null || position.length() == 0)
				&& EventController.event.configuration.findTemplateByName(
						"participantTemplate").findFormDataByName("position").required) {
			errors.add(new ValidationError("position", Messages
					.get("participant.position.error")));
		}
		if ((vatin == null || vatin.length() == 0)
				&& EventController.event.configuration.findTemplateByName(
						"participantTemplate").findFormDataByName("vatin").required) {
			errors.add(new ValidationError("vatin", Messages
					.get("participant.vatin.error")));
		}
		if ((street == null || street.length() == 0)
				&& EventController.event.configuration.findTemplateByName(
						"participantTemplate").findFormDataByName("street").required) {
			errors.add(new ValidationError("street", Messages
					.get("participant.street.error")));
		}
		if ((postofficebox == null || postofficebox.length() == 0)
				&& EventController.event.configuration.findTemplateByName(
						"participantTemplate").findFormDataByName("postofficebox").required) {
			errors.add(new ValidationError("postofficebox", Messages
					.get("participant.postofficebox.error")));
		}
		if ((postcode == null)
				&& EventController.event.configuration.findTemplateByName(
						"participantTemplate").findFormDataByName("postcode").required) {
			errors.add(new ValidationError("postcode", Messages
					.get("participant.postcode.error")));
		}
		if ((location == null || location.length() == 0)
				&& EventController.event.configuration.findTemplateByName(
						"participantTemplate").findFormDataByName("location").required) {
			errors.add(new ValidationError("location", Messages
					.get("participant.location.error")));
		}
		if ((country == null || country.length() == 0)
				&& EventController.event.configuration.findTemplateByName(
						"participantTemplate").findFormDataByName("country").required) {
			errors.add(new ValidationError("country", Messages
					.get("participant.country.error")));
		}
		if ((phone == null || phone.length() == 0)
				&& EventController.event.configuration.findTemplateByName(
						"participantTemplate").findFormDataByName("phone").required) {
			errors.add(new ValidationError("phone", Messages
					.get("participant.phone.error")));
		}
		if ((mobilephone == null || mobilephone.length() == 0)
				&& EventController.event.configuration.findTemplateByName(
						"participantTemplate").findFormDataByName("mobilephone").required) {
			errors.add(new ValidationError("mobilephone", Messages
					.get("participant.mobilephone.error")));
		}
		if ((telefax == null || telefax.length() == 0)
				&& EventController.event.configuration.findTemplateByName(
						"participantTemplate").findFormDataByName("telefax").required) {
			errors.add(new ValidationError("telefax", Messages
					.get("participant.telefax.error")));
		}
		if ((email == null || email.length() == 0)
				&& EventController.event.configuration.findTemplateByName(
						"participantTemplate").findFormDataByName("email").required) {
			errors.add(new ValidationError("email", Messages
					.get("participant.email.error")));
		}
		if ((url == null || url.length() == 0)
				&& EventController.event.configuration.findTemplateByName(
						"participantTemplate").findFormDataByName("url").required) {
			errors.add(new ValidationError("url", Messages
					.get("participant.url.error")));
		}
		if ((username == null || username.length() == 0)
				&& EventController.event.configuration.findTemplateByName(
						"participantTemplate").findFormDataByName("username").required) {
			errors.add(new ValidationError("username", Messages
					.get("participant.username.error")));
		}
		if ((password == null || password.length() == 0)
				&& EventController.event.configuration.findTemplateByName(
						"participantTemplate").findFormDataByName("password").required) {
			errors.add(new ValidationError("password", Messages
					.get("participant.password.error")));
		}
		if ((passwordConfirmation == null || passwordConfirmation.length() == 0)
				&& EventController.event.configuration.findTemplateByName(
						"participantTemplate").findFormDataByName(
						"passwordConfirmation").required) {
			errors.add(new ValidationError("passwordConfirmation", Messages
					.get("participant.passwordConfirmation.error")));
		}

		if ((billingsalutation == null || billingsalutation.length() == 0)
				&& EventController.event.configuration.findTemplateByName(
						"billingTemplate").findFormDataByName("salutation").required) {
			errors.add(new ValidationError("billingsalutation", Messages
					.get("participant.billingaddress.salutation.error")));
		}

		return errors.isEmpty() ? null : errors;
	}
}

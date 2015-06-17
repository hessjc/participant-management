package models;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import models.service.ParticipantCourseService;
import models.service.ParticipantTicketService;
import models.utils.AppException;
import models.utils.Hash;
import play.data.format.Formats;
import play.db.ebean.Model;

@Entity
@Table(name = "participants")
public class Participant extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5922370531468988025L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "participant_id")
	public Long id;

	public String salutation;
	public String lastname;
	public String firstname;
	public Date birthday;
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
	@Formats.NonEmpty
	@Column(unique = true)
	public String email;
	public String url;
	@Formats.NonEmpty
	@Column(unique = true)
	public String username;
	@Formats.NonEmpty
	public String password;
	@Formats.NonEmpty
	public String passwordConfirmation;

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

	public String shippingsalutation;
	public String shippinglastname;
	public String shippingfirstname;
	public String shippingcompany;
	public String shippingcompanyAdditional;
	public String shippingdepartment;
	public String shippingstreet;
	public Integer shippingpostcode;
	public String shippinglocation;
	public String shippingcountry;

	@ManyToOne
	@JoinColumn(name = "event_id")
	public Event event;

	@OneToMany(cascade=CascadeType.ALL, mappedBy="participant")
	public List<ParticipantTicketService> ptServices;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "participant")
	public List<ParticipantCourseService> pcServices;

	public static Model.Finder<Long, Participant> find = new Model.Finder<Long, Participant>(
			Long.class, Participant.class);
	
	public static Participant authenticate(String username, String clearPassword)
			throws AppException {
		// get the participant with email only to keep the salt password
		Participant participant = find.where().eq("email", username).findUnique();
		if (participant == null)
			participant = find.where().eq("username", username).findUnique();
		if (participant != null) {
			// get the hash password from the salt + clear password
			if (Hash.checkPassword(clearPassword, participant.password)) {
				return participant;
			}
		}
		return null;
	}

	public void changePassword(String password) throws AppException {
		this.password = Hash.createPassword(password);
		this.save();
	}

}

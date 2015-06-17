package models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import models.Configuration;

@Entity
@Table(name = "events")
public class Event extends Model {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "event_id")
	public Long id;

	@Required
	public String name;
	@Required
	public String shortcode;

	@Column(columnDefinition = "TEXT")
	public String description;
	public String descriptionImage;

	@Required
	public Date startDate;

	@Required
	public Date endDate;

	public String supportEmail;

	public String facebook;
	public String twitter;
	public String googleplus;

	@OneToOne
	public Configuration configuration;

	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	public List<Course> courses;

	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	public List<Participant> participants;
	
	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	public List<Participant> tickets;

	public Event(String name, String shortcode, String description,
			Date startDate, Date endDate, Configuration configuration) {
		this.name = name;
		this.shortcode = shortcode;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.configuration = configuration;
		this.save();
	}

	public Event edit(String name, String description, String descriptionImage,
			Date startDate, Date endDate, String supportEmail, String facebook,
			String twitter, String googleplus) {
		this.name = name;
		this.description = description;
		this.descriptionImage = descriptionImage;
		this.startDate = startDate;
		this.endDate = endDate;
		this.supportEmail = supportEmail;
		this.facebook = facebook;
		this.twitter = twitter;
		this.googleplus = googleplus;
		this.update();
		return this;
	}

	public static Model.Finder<Long, Event> find = new Model.Finder<Long, Event>(
			Long.class, Event.class);

	public static List<String> findAllShortcodes() {
		ArrayList<String> shortnames = new ArrayList<String>();
		for (Event event : find.all()) {
			shortnames.add(event.shortcode);
		}
		return shortnames;
	}

	public static Event findByShortcode(String shortcode) {
		return find.where().like("shortcode", shortcode).findUnique();
	}
}

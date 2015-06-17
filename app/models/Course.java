package models;

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
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
@Table(name = "courses")
public class Course extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "course_id")
	public Long id;

	@Required
	public String title;

	@Required
	@Column(columnDefinition = "TEXT")
	public String description;

	@Required
	public int slots;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
	public List<ParticipantCourseService> pcServices;

	@ManyToOne
	@JoinColumn(name = "event_id")
	public Event event;

	public Course(String title, String description, int slots) {
		this.title = title;
		this.description = description;
		this.slots = slots;
		this.save();
	}

	public Course edit(String title, String description, int slots) {
		this.title = title;
		this.description = description;
		this.slots = slots;
		return this;
	}

	public static Model.Finder<Long, Course> find = new Model.Finder<Long, Course>(
			Long.class, Course.class);
}

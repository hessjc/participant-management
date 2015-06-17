package models.service;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import models.Course;
import models.Participant;
import play.db.ebean.Model;

@Entity
public class ParticipantCourseService extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1822657873301192046L;

	@ManyToOne
	public Participant participant;

	@ManyToOne
	public Course course;

	public ParticipantCourseService(Participant participant, Course course) {
		this.participant = participant;
		this.course = course;
		this.save();
	}

	public static Model.Finder<Long, ParticipantCourseService> find = new Model.Finder<Long, ParticipantCourseService>(
			Long.class, ParticipantCourseService.class);

	public List<ParticipantCourseService> findCoursesByParticipant(
			Participant participant) {
		return find.where()
				.like("participant_participant_id", participant.id.toString())
				.findList();
	}

	public List<ParticipantCourseService> findParticipantsByCourse(Course course) {
		return find.where().like("course_course_id", course.id.toString())
				.findList();
	}
}

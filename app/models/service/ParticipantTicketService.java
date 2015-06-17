package models.service;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import models.Participant;
import models.Ticket;
import play.db.ebean.Model;

@Entity
public class ParticipantTicketService extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3126782394461303067L;

	@ManyToOne
	public Participant participant;

	@ManyToOne
	public Ticket ticket;

	public ParticipantTicketService(Participant participant, Ticket ticket) {
		this.participant = participant;
		this.ticket = ticket;
		this.save();
	}

	public static Model.Finder<Long, ParticipantTicketService> find = new Model.Finder<Long, ParticipantTicketService>(
			Long.class, ParticipantTicketService.class);

	public List<ParticipantTicketService> findTicketsByParticipant(
			Participant participant) {
		return find.where()
				.like("participant_participant_id", participant.id.toString())
				.findList();
	}

	public List<ParticipantTicketService> findParticipantsByTicket(Ticket ticket) {
		return find.where().like("ticket_ticket_id", ticket.id.toString())
				.findList();
	}
}

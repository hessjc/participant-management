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

import models.service.ParticipantTicketService;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
@Table(name = "tickets")
public class Ticket extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6512860367259176555L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ticket_id")
	public Long id;
	@Required
	public String title;
	@Required
	@Column(columnDefinition = "TEXT")
	public String description;
	@Required
	public Double price;
	public Integer minpp;
	public Integer maxpp;
	@Required
	public Integer contingent;
	@Required
	public Integer duration;
	public String img;

	@ManyToOne
	@JoinColumn(name = "event_id")
	public Event event;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ticket")
	public List<ParticipantTicketService> ptServices;

	public Ticket(String title, String description, Double price,
			Integer minpp, Integer maxpp, Integer contingent, Integer duration,
			String img) {
		this.title = title;
		this.description = description;
		this.price = price;
		this.minpp = minpp;
		this.maxpp = maxpp;
		this.contingent = contingent;
		this.duration = duration;
		this.img = img;
		this.save();
	}

	public Ticket edit(String title, String description, Double price,
			Integer minpp, Integer maxpp, Integer contingent, Integer duration,
			String img) {
		this.title = title;
		this.description = description;
		this.price = price;
		this.minpp = minpp;
		this.maxpp = maxpp;
		this.contingent = contingent;
		this.duration = duration;
		this.img = img;
		this.update();
		return this;
	}

	public static Model.Finder<Long, Ticket> find = new Model.Finder<Long, Ticket>(
			Long.class, Ticket.class);

}

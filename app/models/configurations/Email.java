package models.configurations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import models.Configuration;
import play.db.ebean.Model;

@Entity
@Table(name = "emails")
public class Email extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "email_id")
	public Long id;

	public Boolean display;
	public String subject;
	public String fromName;
	public String fromEmail;

	public Boolean addTNB;
	public Boolean addAttachement;

	@Column(columnDefinition = "TEXT")
	public String bodyText;

	@OneToOne
	public Configuration configuration;

	public Email(Configuration configuration) {
		this.display = true;
		this.addTNB = false;
		this.addAttachement = false;
		this.configuration = configuration;
		this.save();
	}

	public void edit(Boolean display, String subject, String fromName,
			String fromEmail, Boolean addTNB, Boolean addAttachement,
			String bodyText) {
		this.display = display;
		this.subject = subject;
		this.fromName = fromName;
		this.fromEmail = fromEmail;
		this.addTNB = addTNB;
		this.addAttachement = addAttachement;
		this.bodyText = bodyText;
		this.update();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static Model.Finder<Long, Email> find = new Model.Finder<Long, Email>(
			Long.class, Email.class);
}

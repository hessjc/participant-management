package models.template;

import java.util.ArrayList;
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

import models.Configuration;
import models.data.FormData;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
@Table(name = "templates")
public class Template extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "template_id")
	public Long id;

	@Required
	public String name;
	@Required
	public Boolean display;

	public Boolean login;
	public Boolean registration;
	public Boolean passwordLogin;

	public String passwords;

	public Template(String name, Configuration configuration) {
		this.name = name;
		this.display = true;
		this.configuration = configuration;
		this.save();
		if (name.equals("loginTemplate")) {
			this.login = true;
			this.registration = true;
			this.passwordLogin = true;
			this.update();
		}
		if (name.equals("participantTemplate")) {
			for (int count = 0; count < utilities.Configuration.participantFieldsName
					.size(); count++) {
				formData.add(new FormData(
						utilities.Configuration.participantFieldsName
								.get(count),
						utilities.Configuration.participantFieldsTyp.get(count),
						this));
			}
		} else if (name.equals("billingTemplate")) {
			for (int count = 0; count < utilities.Configuration.billingFieldsName
					.size(); count++) {
				formData.add(new FormData(
						utilities.Configuration.billingFieldsName.get(count),
						utilities.Configuration.billingFieldsTyp.get(count),
						this));
			}
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FormData findFormDataByName(String name) {
		for (FormData formData : formData) {
			if (formData.name.equals(name))
				return formData;
		}
		return null;
	}

	@ManyToOne
	@JoinColumn(name = "configuration_id")
	public Configuration configuration;

	@OneToMany(mappedBy = "template", cascade = CascadeType.ALL)
	public List<FormData> formData = new ArrayList<FormData>();

	public static Model.Finder<Long, Template> find = new Model.Finder<Long, Template>(
			Long.class, Template.class);

	public Template findByName(String name) {
		return find.where().like("name", name).findUnique();
	}
}

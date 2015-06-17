package models.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import models.template.Template;
import play.db.ebean.Model;

@Entity
@Table(name = "formdata")
public class FormData extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "data_id")
	public Long id;

	public String name;
	public String typ;
	public String icon;

	public Boolean display;
	public Boolean required;

	@ManyToOne
	@JoinColumn(name = "template_id")
	public Template template;

	public FormData(String name, String typ, Template template) {
		this.name = name;
		this.typ = typ;
		this.icon = "glyphicon glyphicon-ok";
		this.display = false;
		this.required = false;
		this.template = template;
		this.save();
	}

	public static Model.Finder<Long, FormData> find = new Model.Finder<Long, FormData>(
			Long.class, FormData.class);
}

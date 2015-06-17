package models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import models.configurations.Email;
import models.template.Template;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
@Table(name = "configurations")
public class Configuration extends Model {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "configuration_id")
	public Long id;

	@Required
	public String pageTitle;
	@Required
	public String pageHeader;
	@Required
	public Integer pageWidth;
	public Boolean pageBorder;
	public String pageBackgroundImage;
	public String pageHeadlineBackgroundcolor;
	public String pageNavigationBackgroundcolor;
	public String pageContentBackgroundcolor;
	public String pageFooterBackgroundcolor;
	public String pageBackgroundimage;

	@Column(columnDefinition = "TEXT")
	public String pageFooterLeft;
	@Column(columnDefinition = "TEXT")
	public String pageFooterRight;
	
	public String theme;

	public String metaCharset;
	public String metaAuthor;
	public String metaDescription;
	public String metaKeywords;
	public Date metaDate;

	@OneToOne
	public Email emailConfig;

	public Boolean multipleLanguages;

	@Column(columnDefinition = "TEXT")
	public String impressum;
	@Column(columnDefinition = "TEXT")
	public String privacy;
	@Column(columnDefinition = "TEXT")
	public String contact;

	@OneToMany(mappedBy = "configuration", cascade = CascadeType.ALL)
	public List<Template> templates;

	public Configuration(String title, String header) {
		this.pageTitle = title;
		this.pageHeader = header;
		this.pageWidth = 100;
		this.pageBorder = true;
		this.pageHeadlineBackgroundcolor = "ffffff";
		this.pageNavigationBackgroundcolor = "ffffff";
		this.pageContentBackgroundcolor = "ffffff";
		this.pageFooterBackgroundcolor = "ffffff";
		this.multipleLanguages = true;
		this.save();
		this.templates = new ArrayList<Template>();
		this.emailConfig = new Email(this);
		for (String template : utilities.Configuration.templates) {
			this.templates.add(new Template(template, this));
		}
		this.update();
	}

	public Configuration edit(String pageTitle, String pageHeader,
			Integer pageWidth, Boolean pageBorder,
			String pageHeadlineBackgroundcolor,
			String pageNavigationBackgroundcolor,
			String pageContentBackgroundcolor,
			String pageFooterBackgroundcolor, String pageBackgroundimage,
			String pageFooterLeft, String pageFooterRight,
			Boolean multipleLanguages, String theme) {
		this.pageTitle = pageTitle;
		this.pageHeader = pageHeader;
		this.pageWidth = pageWidth;
		this.pageBorder = pageBorder;
		this.pageHeadlineBackgroundcolor = pageHeadlineBackgroundcolor;
		this.pageNavigationBackgroundcolor = pageNavigationBackgroundcolor;
		this.pageContentBackgroundcolor = pageContentBackgroundcolor;
		this.pageFooterBackgroundcolor = pageFooterBackgroundcolor;
		this.pageBackgroundimage = pageBackgroundimage;
		this.pageFooterLeft = pageFooterLeft;
		this.pageFooterRight = pageFooterRight;
		this.multipleLanguages = multipleLanguages;
		this.theme = theme;
		this.update();
		return this;
	}

	public Template findTemplateByName(String name) {
		for (Template template : templates) {
			if (template.name.equals(name))
				return template;
		}
		return null;
	}

	public static Model.Finder<Long, Configuration> find = new Model.Finder<Long, Configuration>(
			Long.class, Configuration.class);

	public static Configuration findById(Long id) {
		return find.where().like("configuration_id", id.toString())
				.findUnique();
	}
}

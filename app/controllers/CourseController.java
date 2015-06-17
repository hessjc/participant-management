package controllers;

import models.Course;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import formulars.ConfigurationForm.CourseForm;

public class CourseController extends Controller {

	public Result addCourse(String shortcode) {
		Form<CourseForm> courseData = Form.form(CourseForm.class)
				.bindFromRequest();

		if (courseData.hasErrors()) {
			flash("danger", "Fehler beim Erstellen eines Course.");
			return badRequest(views.html.configuration.render(
					ConfigurationController.globalForm,
					ConfigurationController.eventForm,
					ConfigurationController.addEventForm,
					ConfigurationController.ticketForm, courseData, ConfigurationController.emailForm));
		} else {
			Course course = new Course(courseData.get().title,
					courseData.get().description, courseData.get().slots);
			course.save();
			flash("success", "Course erfolgreich hinzugefügt.");
			return redirect("/configuration?shortcode=" + shortcode
					+ "#tabCourses");
		}
	}

	public Result saveCourse(Long id, String title, String description,
			int slots) {
		Course course = Course.find.byId(id).edit(title, description, slots);
		course.save();
		flash("success", "Course (ID: " + id + ") erfolgreich geändert.");
		return ok(Json.newObject().put("status", "OK"));
	}

	public Result removeCourse(Long id) {
		Course.find.byId(id).delete();
		flash("danger", "Course (ID: " + id + ") erfolgreich gelöscht.");
		return redirect("/configuration#tabCourses");
	}
}

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import models.Configuration;
import models.Event;
import models.SecurityRole;
import models.User;
import models.UserPermission;
import models.utils.AppException;
import models.utils.Hash;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.api.mvc.EssentialFilter;
import play.filters.gzip.GzipFilter;

import com.avaje.ebean.Ebean;

import controllers.EventController;

public class Global extends GlobalSettings {

	@SuppressWarnings("unchecked")
	public <T extends EssentialFilter> Class<T>[] filters() {
		return new Class[] { GzipFilter.class };
	}

	@Override
	public void onStart(Application app) {
		Logger.info("Application has started");

		if (SecurityRole.find.findRowCount() == 0) {
			for (String name : utilities.Permissions.administrators) {
				SecurityRole role = new SecurityRole(name);
				role.save();
			}
		}

		if (UserPermission.find.findRowCount() == 0) {
			UserPermission permission = new UserPermission();
			permission.value = "printers.edit";
			permission.save();
		}

		if (User.find.findRowCount() == 0) {
			for (String name : utilities.Permissions.administrators) {
				User user = new User();
				user.username = name;
				try {
					user.passwordHash = Hash.createPassword("123");
				} catch (AppException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				user.roles = new ArrayList<SecurityRole>();
				user.roles.add(SecurityRole.findByName("Administrator"));
				user.permissions = new ArrayList<UserPermission>();
				user.permissions.add(UserPermission
						.findByValue("printers.edit"));

				user.save();
				Ebean.saveManyToManyAssociations(user, "roles");
				Ebean.saveManyToManyAssociations(user, "permissions");
			}

		}

		if (Configuration.find.findRowCount() == 0) {

			Configuration configuration = new Configuration(
					"Titel der Microsite",
					"http://finance.tabigo.net/wp-content/uploads/computer-and-office-business-header.jpg");
			EventController.event = new Event("Event", "emendo15",
					"Beschreibung der Veranstaltung", new Date(Calendar
							.getInstance().getTimeInMillis()), new Date(
							Calendar.getInstance().getTimeInMillis()),
					configuration);

			EventController.event.configuration.impressum = "<h1>Impressum</h1>";
			EventController.event.configuration.contact = "<h1>Kontakt</h1>";
			EventController.event.configuration.privacy = "<h1>Datenschutz</h1>";
			EventController.event.configuration.update();
		}
	}

	public void onStop(Application app) {
		Logger.info("Application shutdown...");
	}

}

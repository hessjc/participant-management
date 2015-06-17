/*
 * Copyright 2012 Steve Chaloner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import models.utils.AppException;
import models.utils.Hash;
import play.data.format.Formats;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import be.objectify.deadbolt.core.models.Permission;
import be.objectify.deadbolt.core.models.Role;
import be.objectify.deadbolt.core.models.Subject;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
@Entity
public class User extends Model implements Subject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6513505301485081470L;

	@Id
	public Long id;

	@Required
	@Formats.NonEmpty
	@Column(unique = true)
	public String username;

	@Required
	@Formats.NonEmpty
	@Column(unique = true)
	public String email;

	@Required
	@Formats.NonEmpty
	public String passwordHash;

	@Formats.DateTime(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date dateCreation;

	@Formats.NonEmpty
	public Boolean validated = false;

	@ManyToMany
	public List<SecurityRole> roles;

	@ManyToMany
	public List<UserPermission> permissions;

	public static final Finder<Long, User> find = new Finder<Long, User>(
			Long.class, User.class);

	@Override
	public List<? extends Role> getRoles() {
		return roles;
	}

	@Override
	public List<? extends Permission> getPermissions() {
		return permissions;
	}

	@Override
	public String getIdentifier() {
		return username;
	}

	public static User findByUserName(String userName) {
		return find.where().eq("userName", userName).findUnique();
	}

	public static User findByEmail(String email) {
		return find.where().eq("email", email).findUnique();
	}

	/**
	 * Authenticate a User, from a email and clear password.
	 *
	 * @param email
	 *            email
	 * @param clearPassword
	 *            clear password
	 * @return User if authenticated, null otherwise
	 * @throws AppException
	 *             App Exception
	 */
	public static User authenticate(String username, String clearPassword)
			throws AppException {
		// get the user with email only to keep the salt password
		User user = find.where().eq("email", username).findUnique();
		if (user == null)
			user = find.where().eq("username", username).findUnique();
		if (user != null) {
			// get the hash password from the salt + clear password
			if (Hash.checkPassword(clearPassword, user.passwordHash)) {
				return user;
			}
		}
		return null;
	}

	public void changePassword(String password) throws AppException {
		this.passwordHash = Hash.createPassword(password);
		this.save();
	}
}

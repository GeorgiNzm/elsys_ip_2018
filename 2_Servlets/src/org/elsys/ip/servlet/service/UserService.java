package org.elsys.ip.servlet.service;

import java.util.ArrayList;
import java.util.List;

import org.elsys.ip.servlet.model.User;

public class UserService {
	private static List<User> users = new ArrayList<>();
	private static int idCounter = 0;

	public UserService() {
		if (getUsers().isEmpty()) {
			users.add(new User((++idCounter), "admin", "admin@admin.bg"));
			users.add(new User((++idCounter), "user", "user@user.bg"));
		}
	}

	public List<User> getUsers() {
		return users;
	}

	public User getByName(String name) {
		System.out.println(name);
		if (name != null) {
			return users.stream().filter(u -> name.equals(u.getName())).findFirst().orElse(null);
		} else {
			return null;
		}
	}

	public void addUser(User u) {
		if (!getUsers().contains(u)) getUsers().add(u);
	}

	public void deleteUser(String name) {
		getUsers().remove(this.getByName(name));
	}

	public User updateUser(String newName, String newEmail, String oldName) {
		return getByName(oldName).setName(newName).setEmail(newEmail);
	}
}

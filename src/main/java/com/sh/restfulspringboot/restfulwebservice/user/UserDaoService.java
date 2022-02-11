package com.sh.restfulspringboot.restfulwebservice.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	private static int usersCount = 3;

	static {
		try {
			users.add(new User(1, "Laxmi Sudhakar", new SimpleDateFormat("dd-MM-yyyy").parse("27-03-2001")));
			users.add(new User(2, "Laxmi Naurange", new SimpleDateFormat("dd-MM-yyyy").parse("25-01-2001")));
			users.add(new User(3, "Shubham Kumar", new SimpleDateFormat("dd-MM-yyyy").parse("05-02-1998")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public List<User> findAll() {
		return users;
	}

	public User findOne(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}

	public User deleteById(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				users.remove(user);
				return user;
			}
		}
		return null;
	}
}

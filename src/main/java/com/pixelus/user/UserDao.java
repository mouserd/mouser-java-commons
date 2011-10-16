package com.pixelus.user;

import java.util.List;

public interface UserDao {

	void save(User user);

	List<User> findAll();
}

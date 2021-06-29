package com.rentalbooking.service;

import com.rentalbooking.model.User;

public interface UserService {
	public User getUser(int id);
	public void updateUser(User user);
}

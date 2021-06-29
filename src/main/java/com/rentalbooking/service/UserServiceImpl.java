package com.rentalbooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentalbooking.model.User;
import com.rentalbooking.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
	UserRepo userRepo;
	
	@Autowired
	UserServiceImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public User getUser(int id) {
		return userRepo.getOne(id);
	}

	@Override
	public void updateUser(User user) {
		userRepo.saveAndFlush(user);

	}

}

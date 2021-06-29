package com.rentalbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalbooking.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}

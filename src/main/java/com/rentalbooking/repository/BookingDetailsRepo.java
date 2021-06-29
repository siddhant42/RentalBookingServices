package com.rentalbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalbooking.model.BookingDetails;

public interface BookingDetailsRepo extends JpaRepository<BookingDetails, Integer> {

}

package com.rentalbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalbooking.model.BookingDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDetailsRepo extends JpaRepository<BookingDetails, Integer> {

}

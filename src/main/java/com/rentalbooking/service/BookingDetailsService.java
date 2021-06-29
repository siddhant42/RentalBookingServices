package com.rentalbooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentalbooking.model.BookingDetails;
import com.rentalbooking.repository.BookingDetailsRepo;

@Service
public class BookingDetailsService {
	
	private BookingDetailsRepo bookingDetailsRepo;
	
	@Autowired
	public BookingDetailsService(BookingDetailsRepo bookingDetailsRepo) {
		this.bookingDetailsRepo = bookingDetailsRepo;
	}
	public BookingDetails updateBookingDetails(BookingDetails bookingDetails) {
//		BookingDetails bookingDetails1 = bookingDetailsRepo.getOne(bookingDetails.getId());
		BookingDetails bookingDetails1 = bookingDetailsRepo.saveAndFlush(bookingDetails);
		return bookingDetails;
	}
}

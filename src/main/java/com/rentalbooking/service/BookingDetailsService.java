package com.rentalbooking.service;

import com.rentalbooking.model.Booking;
import com.rentalbooking.repository.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentalbooking.model.BookingDetails;
import com.rentalbooking.repository.BookingDetailsRepo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingDetailsService {

	@Autowired
	private BookingDetailsRepo bookingDetailsRepo;

	@Autowired
	private BookingRepo bookingRepo;

	@Autowired
	UserService userService;

	public BookingDetails updateBookingDetails(BookingDetails bookingDetails) {
//		BookingDetails bookingDetails1 = bookingDetailsRepo.getOne(bookingDetails.getId());
		BookingDetails bookingDetails1 = bookingDetailsRepo.saveAndFlush(bookingDetails);
		return bookingDetails;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
	public  BookingDetails createBooking(List<Booking> bookingList, LocalDate from, LocalDate to) {
		BookingDetails bookingDetails = new BookingDetails();
		bookingDetails.setBike(bookingList.get(0).getBike());
		bookingDetails.setFromdate(Timestamp.valueOf(from.atStartOfDay()));
		bookingDetails.setTodate(Timestamp.valueOf(to.atStartOfDay()));
		bookingDetails.setBookingList(bookingList);
		//TODO for currently taking default user with id=1
		bookingDetails.setUser(userService.getUser(1));

		String joining = bookingList.stream().map(i->String.valueOf(i.getId()))
				.collect(Collectors.joining(","));
		final int count = bookingRepo.updateBookings(joining);
		if(count<bookingList.size()) throw new RuntimeException("Required bookings not available");

		bookingDetails = bookingDetailsRepo.saveAndFlush(bookingDetails);

		return bookingDetails;
	}
}

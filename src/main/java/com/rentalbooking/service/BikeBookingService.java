package com.rentalbooking.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import com.rentalbooking.model.*;
import com.rentalbooking.repository.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentalbooking.repository.BikeRepository;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class BikeBookingService implements BookingService {
	
	@Autowired
	private BookingDetailsService bookingDetailsService;

	@Autowired
	private BikeRepository bikeRepo;
	
	@Autowired
	private BookingRepo bookingRepo;

	@Override
	public List<Bike> showBikes(LocalDate from, LocalDate to, String city) {
		List<Bike> bikes = bikeRepo.getBikes(from, to, city);
		return bikes;
	}

	@Override
	public List<Booking> bookBike(LocalDate from, LocalDate to, Bike bike) {
		final List<Booking> availBookings = bookingRepo.getAvailBookings(from,to,bike.getId());
		final int requiredCount = (int)DAYS.between(from, to);
		if(availBookings.size()<requiredCount) throw new RuntimeException("Bike not available");
		return availBookings;
	}


	@Override
	public boolean cancelBooking(BookingDetails bookingDetails) {
		// TODO Auto-generated method stub
		return false;
	}

}

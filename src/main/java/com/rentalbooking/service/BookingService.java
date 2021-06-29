package com.rentalbooking.service;

import java.time.LocalDate;
import java.util.List;

import com.rentalbooking.model.Bike;
import com.rentalbooking.model.BookingDetails;

public interface BookingService {
	public List<Bike> showBikes(LocalDate from, LocalDate to, String city);
	public BookingDetails bookBike(LocalDate from,LocalDate to,Bike bike);
	public boolean cancelBooking(BookingDetails bookingDetails);
}

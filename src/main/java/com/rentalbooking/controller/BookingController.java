package com.rentalbooking.controller;

import java.sql.Timestamp;
import java.util.List;

import com.rentalbooking.model.Booking;
import org.springframework.web.servlet.ModelAndView;

import com.rentalbooking.model.Bike;
import com.rentalbooking.model.BookingDetails;

public interface BookingController {
	public ModelAndView showAvailBikes(String from,String to,String city);
	public ModelAndView bookBike(BookingDetails bookingDetails);
	public ModelAndView cancelBooking(BookingDetails bookingDetails);
	public ModelAndView confirmBooking(BookingDetails bookingDetails);
}

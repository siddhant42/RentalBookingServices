package com.rentalbooking.controller;

import java.sql.Timestamp;

import org.springframework.web.servlet.ModelAndView;

import com.rentalbooking.model.Bike;
import com.rentalbooking.model.BookingDetails;

public interface BookingController {
	public ModelAndView showBikes(String from,String to,String city);
	public ModelAndView bookBike(Timestamp from,Timestamp to,Bike bike);
	public ModelAndView cancelBooking(BookingDetails bookingDetails);
}

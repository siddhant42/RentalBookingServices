package com.rentalbooking.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.rentalbooking.model.Bike;
import com.rentalbooking.model.Booking;
import com.rentalbooking.model.BookingDetails;
import org.springframework.web.servlet.ModelAndView;

public interface BookingService {
	public List<BookingDetails> showAvailBikes(LocalDateTime from, LocalDateTime to, String city);
	public void bookBike(BookingDetails bookingDetails);
	public BookingDetails createBooking(BookingDetails bookingDetails);
	public boolean cancelBooking(BookingDetails bookingDetails);
}

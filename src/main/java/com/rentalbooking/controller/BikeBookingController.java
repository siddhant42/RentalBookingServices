package com.rentalbooking.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.rentalbooking.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rentalbooking.model.Bike;
import com.rentalbooking.model.BookingDetails;
import com.rentalbooking.service.BookingService;

@Controller
@RequestMapping(value="/bookingservice")
public class BikeBookingController implements BookingController {
	@Autowired
	BookingService bookingService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("home");
        return modelAndView;
	}

	@Override
	@RequestMapping(value="/bikes", method=RequestMethod.POST)
	public ModelAndView showAvailBikes(String fromdate, String todate,String city) {
		LocalDateTime from = LocalDateTime.parse(fromdate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDateTime to = LocalDateTime.parse(todate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		List<BookingDetails> bookingDetailsList = bookingService.showAvailBikes(from, to, city);
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("bookingDetailsList", bookingDetailsList);
	    modelAndView.setViewName("bikesview");
        return modelAndView;
	}

	@Override
	@RequestMapping(value="book", method=RequestMethod.POST)
	public ModelAndView bookBike(BookingDetails bookingDetails) {
		bookingService.bookBike(bookingDetails);
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("bookingDetails", bookingDetails);
	    modelAndView.setViewName("payment");
        return modelAndView;
	}
	@Override
	@RequestMapping(value="confirmbooking", method=RequestMethod.POST)
	public ModelAndView confirmBooking(BookingDetails bookingDetails) {
		ModelAndView modelAndView = new ModelAndView();
		bookingService.createBooking(bookingDetails);
		modelAndView.addObject("bookingDetails",bookingDetails);
		modelAndView.setViewName("booked");
		return modelAndView;
	}


	@Override
	@RequestMapping(value="cancel", method=RequestMethod.PUT)
	public ModelAndView cancelBooking(BookingDetails bookingDetails) {
		boolean isCancelled =  bookingService.cancelBooking(bookingDetails);
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("iscancelled", isCancelled);
	    modelAndView.setViewName("runCrawler");
        return modelAndView;
	}

}

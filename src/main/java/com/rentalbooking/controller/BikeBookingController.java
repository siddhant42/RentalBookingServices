package com.rentalbooking.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
public class BikeBookingController {
	@Autowired
	BookingService bookingService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("home");
        return modelAndView;
	}

//	@Override
	@RequestMapping(value="/bikes", method=RequestMethod.POST)
	public ModelAndView showBikes(String fromdate, String todate,String city) {
		LocalDate from = LocalDate.parse(fromdate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate to = LocalDate.parse(todate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		List<Bike> bikes = bookingService.showBikes(from, to, city);
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("bikes", bikes);
	    modelAndView.addObject("fromdate", fromdate);
	    modelAndView.addObject("todate", todate);
	    Period period = Period.between(from, to);
	    modelAndView.addObject("numOfDays", period.getDays());
	    modelAndView.setViewName("bikesview");
        return modelAndView;
	}

//	@Override
	@RequestMapping(value="book", method=RequestMethod.POST)
	public ModelAndView bookBike(@RequestParam(value = "fromdate", required = false) String fromdate,
			@RequestParam(value = "todate", required = false)String todate, Bike bike) {
		LocalDate from = LocalDate.parse(fromdate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate to = LocalDate.parse(todate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		BookingDetails bookingDetails =  bookingService.bookBike(from, to, bike);
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("bookingDetails", bookingDetails);
	    modelAndView.setViewName("book");
        return modelAndView;
	}

//	@Override
	@RequestMapping(value="cancel", method=RequestMethod.PUT)
	public ModelAndView cancelBooking(BookingDetails bookingDetails) {
		boolean isCancelled =  bookingService.cancelBooking(bookingDetails);
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("iscancelled", isCancelled);
	    modelAndView.setViewName("runCrawler");
        return modelAndView;
	}

}

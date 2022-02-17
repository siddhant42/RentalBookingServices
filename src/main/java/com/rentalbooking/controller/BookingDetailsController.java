package com.rentalbooking.controller;

import com.rentalbooking.model.Bike;
import com.rentalbooking.model.Booking;
import com.rentalbooking.model.BookingDetails;
import com.rentalbooking.service.BookingDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping(value="/bookingservice")
public class BookingDetailsController {

    @Autowired
    BookingDetailsService bookingDetailsService;

    @RequestMapping(value="/confirmbooking", method= RequestMethod.POST)
    public BookingDetails createBooking(@RequestParam(value = "fromdate", required = false) String fromdate,
                                        @RequestParam(value = "todate", required = false)String todate,
                                        List<Booking> bookings) {
        LocalDate from = LocalDate.parse(fromdate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate to = LocalDate.parse(todate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return bookingDetailsService.createBooking(bookings, from, to);
    }
}

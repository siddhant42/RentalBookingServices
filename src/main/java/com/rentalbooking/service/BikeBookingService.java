package com.rentalbooking.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.rentalbooking.model.*;
import com.rentalbooking.repository.BookingDetailsRepo;
import com.rentalbooking.repository.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentalbooking.repository.BikeRepository;
import org.springframework.transaction.annotation.Transactional;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class BikeBookingService implements BookingService {

	@Autowired
	private BikeRepository bikeRepo;
	
	@Autowired
	private BookingRepo bookingRepo;

	@Autowired
	private BookingDetailsRepo bookingDetailsRepo;

	@Autowired
	private UserService userService;

	@Override
	public List<BookingDetails> showAvailBikes(LocalDateTime from, LocalDateTime to, String city) {
		List<Booking> bookingList = bookingRepo.getAvailBookings(from, to, city);
		Map<Integer,List<Booking>> map = new HashMap<>();

		for(Booking booking: bookingList) {
			if(map.get(booking.getBike().getId())==null) {
				List<Booking> bookingList1 = new ArrayList<>();
				bookingList1.add(booking);
				map.put(booking.getBike().getId(),bookingList1);
			} else {
				map.get(booking.getBike().getId()).add(booking);
			}
		}
		final int requiredCount = (int)DAYS.between(from, to);
		List<BookingDetails> bookingDetailsList = new ArrayList<>();
		if(bookingList==null || bookingList.size()==0)
		for(int bikeId : map.keySet()) {
			final BookingDetails bookingDetails = new BookingDetails();
			final Bike bike = bikeRepo.getOne(bikeId);
			bookingDetails.setBike(bike);
			bookingDetails.setPrice(requiredCount*bike.getPrice());
			bookingDetails.setFromdate(Timestamp.valueOf(from));
			bookingDetails.setTodate(Timestamp.valueOf(to));
			bookingDetails.setBookingList(map.get(bikeId));

			bookingDetailsList.add(bookingDetails);
		}
		return bookingDetailsList;
	}

	@Override
	public void bookBike(BookingDetails bookingDetails) {
		String joining = bookingDetails.getBookingList().stream().map(i->String.valueOf(i.getId()))
				.collect(Collectors.joining(","));
		final int availBookings = bookingRepo.checkBookings(joining);
		int requiredCount = (int)DAYS.between(bookingDetails.getFromdate().toLocalDateTime(),
				bookingDetails.getTodate().toLocalDateTime());
		if(availBookings<requiredCount) throw new RuntimeException("Bike not available");
	}


	@Override
	@Transactional
	public BookingDetails createBooking(BookingDetails bookingDetails) {
		String joining = bookingDetails.getBookingList().stream().map(i->String.valueOf(i.getId()))
				.collect(Collectors.joining(","));
		//TODO for currently taking default user with id=1
		bookingDetails.setUser(userService.getUser(1));
		final int count = bookingRepo.updateBookings(joining);
		if(count<bookingDetails.getBookingList().size()) throw new RuntimeException("Required bookings not available");

		bookingDetailsRepo.saveAndFlush(bookingDetails);

		return bookingDetails;
	}


	@Override
	public boolean cancelBooking(BookingDetails bookingDetails) {
		// TODO Auto-generated method stub
		return false;
	}

}

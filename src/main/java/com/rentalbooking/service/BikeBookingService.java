package com.rentalbooking.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentalbooking.model.Bike;
import com.rentalbooking.model.BookingDetails;
import com.rentalbooking.model.User;
import com.rentalbooking.repository.BikeRepository;
@Service
public class BikeBookingService implements BookingService {
	
	@Autowired 
	UserService userService;
	
	@Autowired
	private BookingDetailsService bookingDetailsService;
	
	private BikeRepository bikeRepo;
	
	@Autowired
	public BikeBookingService(BikeRepository bikeRepository) {
		this.bikeRepo = bikeRepository;
	}

	@Override
	public List<Bike> showBikes(LocalDate from, LocalDate to, String city) {
		List<Bike> bikes = bikeRepo.getBikes(from, to, city);
		return bikes;
	}

	@Override
	public BookingDetails bookBike(LocalDate from, LocalDate to, Bike bike) {
//		Bike bike = bikeRepo.getOne(bike.getId());
		int vendorid = bike.getVendor().getId();
		String model = bike.getModel();
		int count = bikeRepo.updateBikes(from,to,model,vendorid);
		BookingDetails bookingDetails = new BookingDetails();
		if(count>0) {
			bookingDetails.setBike(bike);
			bookingDetails.setFromdate(Timestamp.valueOf(from.atStartOfDay()));
			bookingDetails.setTodate(Timestamp.valueOf(to.atStartOfDay()));
			//TODO for currently taking default user with id=1
			bookingDetails.setUser(userService.getUser(1));
			bookingDetails = bookingDetailsService.updateBookingDetails(bookingDetails);
		}
		
		return bookingDetails;
	}

	@Override
	public boolean cancelBooking(BookingDetails bookingDetails) {
		// TODO Auto-generated method stub
		return false;
	}

}

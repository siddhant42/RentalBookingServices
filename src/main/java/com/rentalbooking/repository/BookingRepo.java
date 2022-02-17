package com.rentalbooking.repository;


import com.rentalbooking.model.Bike;
import com.rentalbooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {
    @Query(value="select * from Booking where status='AVAILABLE' and date between ?1 and ?2 and bike_id=?3", nativeQuery=true)
    List<Booking> getAvailBookings(LocalDate from, LocalDate to, int bikeId);

    @Query(value="update Booking set status='BOOKED' and bookings in ('?1')", nativeQuery=true)
    int updateBookings(String bookings);
}

package com.rentalbooking.repository;


import com.rentalbooking.model.Bike;
import com.rentalbooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {
    @Query(value="select * from Booking where status='AVAILABLE' and date between ?1 and ?2 and city=?3", nativeQuery=true)
    List<Booking> getAvailBookings(LocalDateTime from, LocalDateTime to, String city);

    @Query(value="select count(*) from Booking where status='AVAILABLE' id in('?1')", nativeQuery=true)
    int checkBookings(String id);

    @Query(value="update Booking set status='BOOKED' where status!='BOOKED' and id in('?1')", nativeQuery=true)
    int updateBookings(String bookings);
}

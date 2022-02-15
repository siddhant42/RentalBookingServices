package com.rentalbooking.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name="Booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('AVAILABLE', 'BOOKED', 'CANCELLED')")
    private Status status;
    @Column(name="date")
    private Timestamp date;
    @Column(name="start_time")
    private String starttime;
    @Column(name="end_time")
    private String endtime;
    @OneToOne
    @JoinColumn(name ="bike_id", nullable = false)
    private Bike bike;
    @ManyToOne
    @JoinColumn(name ="booking_details_id", nullable = false)
    private BookingDetails bookingDetails;

}

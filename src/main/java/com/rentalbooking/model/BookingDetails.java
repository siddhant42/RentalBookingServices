package com.rentalbooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

@Data
@Entity
@Table(name="BookingDetails")
public class BookingDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="fromdate",nullable=false)
	private Timestamp fromdate;
	@Column(name="todate",nullable=false)
	private Timestamp todate;
	@Column(name="totalprice")
	private int price;
	@OneToOne
	@JoinColumn(name ="bike_id", nullable = false)
	private Bike bike;
	@OneToOne
	@JoinColumn(name ="user_id", nullable = false)
	private User user;

	@OneToMany(mappedBy="bookingDetails")
	@JsonIgnore
	private List<Booking> bookingList;
	
}

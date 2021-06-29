package com.rentalbooking.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	@OneToOne
	@JoinColumn(name ="bike_id", nullable = false)
	private Bike bike;
	@OneToOne
	@JoinColumn(name ="user_id", nullable = false)
	private User user;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getFromdate() {
		return fromdate;
	}
	public void setFromdate(Timestamp fromdate) {
		this.fromdate = fromdate;
	}
	public Timestamp getTodate() {
		return todate;
	}
	public void setTodate(Timestamp todate) {
		this.todate = todate;
	}
	public Bike getBike() {
		return bike;
	}
	public void setBike(Bike bike) {
		this.bike = bike;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}

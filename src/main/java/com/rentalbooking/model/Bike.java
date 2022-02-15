package com.rentalbooking.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name="Bike")
public class Bike {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="model", nullable=false)
	private String model;
	@Column(name="city", nullable=false)
	private String city;
	@Column(name="imageurl")
	private String imageurl;
	@Column()
	private int price;
	@OneToOne
	@JoinColumn(name ="vendor_id", nullable = false)
	private Vendor vendor;
	
}

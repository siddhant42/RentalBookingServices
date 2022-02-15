package com.rentalbooking.service;

import com.rentalbooking.model.Booking;
import com.rentalbooking.model.PaymentStatus;

import java.util.List;

public interface PaymentService {
    public PaymentStatus pay(final int amount);
}

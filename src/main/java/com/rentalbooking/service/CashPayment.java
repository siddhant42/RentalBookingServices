package com.rentalbooking.service;

import com.rentalbooking.model.Booking;
import com.rentalbooking.model.PaymentStatus;

import java.util.List;

public class CashPayment implements PaymentService {
    @Override
    public PaymentStatus pay(final int amount) {
        System.out.println("paying amount:"+amount+" through cash");
        return PaymentStatus.SUCCESS;
    }
}

package com.rentalbooking.service;

import com.rentalbooking.model.PaymentStatus;

public class CardPayment implements PaymentService {
    @Override
    public PaymentStatus pay(final int amount) {
        System.out.println("paying amount:"+amount+" through card");
        return PaymentStatus.SUCCESS;
    }
}

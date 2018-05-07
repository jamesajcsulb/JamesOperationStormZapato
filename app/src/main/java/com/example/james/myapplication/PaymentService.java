package com.example.james.myapplication;

import com.example.james.myapplication.Model.User;
import com.stripe.model.Order;

public interface PaymentService {
    public String createCustomer(User user);
    public void chargeCreditCard(Order order);
}
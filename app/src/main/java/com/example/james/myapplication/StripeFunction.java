package com.example.james.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.net.RequestOptions;

public class StripeFunction extends Exception {

    public void StripeFunction()
    {
        //
    }

    public String createAccount(String desc, String descval)
    {
        Stripe.apiKey = "sk_test_1uDfdDN5u2hgBegUb5kbD6qr";

        Map<String, Object> customerParams = new HashMap<String, Object>();
        customerParams.put("description", desc);//"description", "Customer for joshua.robinson@example.com");
        //customerParams.put("source", "tok_mastercard");

        String string = new String();
        try {
            Customer s = Customer.create(customerParams);


            string = s.getId();//getCreated().toString();// = Customer.retrieve();
            //System.out.printf("zzz",s);


        } catch (StripeException e) {
            e.printStackTrace();
        }

        return string;
    }

    public void hello()
    {
        Stripe.apiKey = "sk_test_1uDfdDN5u2hgBegUb5kbD6qr";

        Map<String, Object> customerParams = new HashMap<String, Object>();
        customerParams.put("description", "Customer for joshua.robinson@example.com");
        customerParams.put("source", "tok_mastercard");

        try {
            Customer.create(customerParams);
            System.out.println("hello stripe");
        } catch (StripeException e) {
            e.printStackTrace();
        }
    }
}
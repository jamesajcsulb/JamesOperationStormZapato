package com.example.james.myapplication;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

//import org.springframework.stereotype.Service;

import com.example.james.myapplication.Model.User;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.RateLimitException;
import com.stripe.exception.StripeException;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Order;
import com.stripe.model.Token;


public class StripeCool implements PaymentService {

    private static final String TEST_STRIPE_SECRET_KEY = "sk_test_1uDfdDN5u2hgBegUb5kbD6qr";

    public StripeCool() {
        Stripe.apiKey = TEST_STRIPE_SECRET_KEY;
    }

    public String createCustomer(User user) {

        Map<String, Object> customerParams = new HashMap<String, Object>();
        customerParams.put("description", "jam"+" "+"g");
                //user.getFirstName() + " " + user.getLastName());
        customerParams.put("email", user.getEmail());

        String id = null;

        try {
            // Create customer
            Customer stripeCustomer = Customer.create(customerParams);
            id = stripeCustomer.getId();
            //System.out.println(stripeCustomer);
        } catch (CardException e) {
            // Doesn't make much sense here
        } catch (RateLimitException e) {
            // Too many requests made to the API too quickly
        } catch (InvalidRequestException e) {
            // Invalid parameters were supplied to Stripe's API
        } catch (AuthenticationException e) {
            // Authentication with Stripe's API failed (wrong API key?)
        } catch (APIConnectionException e) {
            // Network communication with Stripe failed
        } catch (StripeException e) {
            // Display a very generic error to the user, and maybe send
            // yourself an email
        } catch (Exception e) {
            // Something else happened unrelated to Stripe
        }

        return id;
    }

    @Override
    public void chargeCreditCard(Order order) {
    }
    //@Override
    public void chargeCreditCard(Order order, String token) {

        Map<String, Object> chargeParams = new HashMap<String, Object>();

        // Stripe requires the charge amount to be in cents
        int chargeAmountCents = 1000;//(int) order.getChargeAmountDollars() * 100;
        //User user = order.getUser();

        chargeParams.put("amount", chargeAmountCents);
        chargeParams.put("currency", "usd");
        chargeParams.put("description", "cus_CosxmqGw8AyvPK");
        //chargeParams.put("customer", "cus_CosxmqGw8AyvPK");//user.getStripeCustomerId());
        chargeParams.put("source", token);

        try {
            // Submit charge to credit card
            Charge charge = Charge.create(chargeParams);
            System.out.println("WOW");//
        } catch (CardException e) {
            // Transaction was declined
            //System.out.println("Status is: " + e.getCode());
            //System.out.println("Message is: " + e.getMessage());
        } catch (RateLimitException e) {
            // Too many requests made to the API too quickly
            Log.d("aasaaaaaaaaaaaaaaaaa","bbbbbbbbbbbbbbbbbbbb");
        } catch (InvalidRequestException e) {
            // Invalid parameters were supplied to Stripe's API
            Log.d("aadaaaaaaaaaaaaaaaa","bbbbbbbbbbbbbbbbbbbb");
        } catch (AuthenticationException e) {
            // Authentication with Stripe's API failed (wrong API key?)
            Log.d("aaafaaaaaaaaaaaaaaa","bbbbbbbbbbbbbbbbbbbb");
        } catch (APIConnectionException e) {
            // Network communication with Stripe failed
            Log.d("aaabaaaaaaaaaaaaaaa","bbbbbbbbbbbbbbbbbbbb");
        } catch (StripeException e) {
            // Display a very generic error to the user, and maybe send
            // yourself an email
            Log.d("aaanaaaaaaaaaaaaaaa","bbbbbbbbbbbbbbbbbbbb");
        } catch (Exception e) {
            // Something else happened unrelated to Stripe
            Log.d("aaaaaaaaaaaaaaaaaa","bbbbbbbbbbbbbbbbbbbb" + e.toString() + e.getStackTrace());
        }

    }
}
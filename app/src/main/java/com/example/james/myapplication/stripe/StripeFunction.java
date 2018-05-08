package com.example.james.myapplication.stripe;

import android.os.AsyncTask;

import java.util.HashMap;
import java.util.Map;

import com.example.james.myapplication.Model.User;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.RateLimitException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Order;
//import com.stripe.android.model.Token;


//import com.stripe.android.Stripe;
//import com.stripe.android.TokenCallback;
//import com.stripe.android.model.Customer;
//import com.stripe.android.model.Token;

/*
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import com.example.james.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;
import com.stripe.android.model.Card;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Customer;
import com.stripe.android.model.Token;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
*/

public class StripeFunction extends Exception implements PaymentService {

    String tok;
    public StripeFunction(String toktok)
    {
        this.tok = toktok;
    }

    @Override
    public void chargeCreditCard(Order o)
    {
        //
//        Stripe.apiKey = "sk_test_1uDfdDN5u2hgBegUb5kbD6qr";
//
//        Map<String, Object> chargeParams = new HashMap<String, Object>();
//        chargeParams.put("amount", 500);
//        chargeParams.put("currency", "usd");
//        chargeParams.put("description", "cus_CosxmqGw8AyvPK");//customerId);
//        chargeParams.put("source", tok);//"tok_visa");
//
//        try {
//            Charge.create(chargeParams);
//            //Customer.create(customerParams);
//        } catch (StripeException e) {
//            e.printStackTrace();
//        }

        ///////
        Map<String, Object> chargeParams = new HashMap<String, Object>();

        // Stripe requires the charge amount to be in cents
        //int chargeAmountCents = (int) order.getChargeAmountDollars() * 100;
        int chargeAmountCents = 500;
        //
        //User user = order.getUser();
        //User user =

        chargeParams.put("amount", chargeAmountCents);
        chargeParams.put("currency", "usd");
        chargeParams.put("description", "Test Charge");
        chargeParams.put("customer", "cus_CosxmqGw8AyvPK");//user.getStripeCustomerId());


        try {
            // Submit charge to credit card
            Charge charge = Charge.create(chargeParams);
            System.out.println(charge);
        } catch (CardException e) {
            // Transaction was declined
            System.out.println("Status is: " + e.getCode());
            System.out.println("Message is: " + e.getMessage());
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


    }
    @Override
    public String createCustomer(User u)
    {
        //
        return "";
    }
    public String createAccount(String desc, String descval)
    {
        Stripe.apiKey = "sk_test_1uDfdDN5u2hgBegUb5kbD6qr";

        Map<String, Object> customerParams = new HashMap<String, Object>();
        customerParams.put("description", desc);//"description", "Customer for joshua.robinson@example.com");
        customerParams.put("source", "tok_visa");

        String string = new String();
        try {
            Customer s = Customer.create(customerParams);


            string = s.getId();//getCreated().toString();// = Customer.retrieve();
            //System.out.printf("zzz",s);


        } catch (StripeException e) {
            e.printStackTrace();
        }

/*
https://www.linkedin.com/pulse/developers-guide-accepting-online-payments-stripe-amir-boroumand
        com.stripe.Stripe.apiKey = "sk_test_1uDfdDN5u2hgBegUb5kbD6qr";

        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", 500);
        chargeParams.put("currency", "usd");
        chargeParams.put("description", "cus_CotuLfi5J882Wx");//customerId);
        chargeParams.put("source", token);//"tok_visa");

        try {
            Charge.create(chargeParams);
        } catch (StripeException e) {
            e.printStackTrace();
            */


        return string;
    }

    public void charge()
    {
        //tokk = tok;




        // Stripe. getContext(), "pk_test_yFM3zfqa8WXhKLP8hfP8P5cW");
        /*stripe.createToken(
                card,
                new TokenCallback() {
                    public void onSuccess(Token token) {
                        // Send token to your server
                    }
                    public void onError(Exception error) {
                        // Show localized error message
                        Toast.makeText(getContext(),
                                error.getLocalizedString(getContext()),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        );*/

/*
        Card card = new Card();
        card.//("4242424242424242", 1, 2020, "111");
        Stripe stripe = Stripe(getApplicationContext(), "");
        stripe.createToken(
                card,
                object :TokenCallback {
        override fun onSuccess(token:Token) {
            tokenIdExtract = token.id.toString()
            bulkToken = token.toString()
        }

        override fun onError(error: Exception) {
            // Error message may be added here
        }
    })*/

        AsyncTask ast = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                Stripe.apiKey = "sk_test_1uDfdDN5u2hgBegUb5kbD6qr";

                Map<String, Object> chargeParams = new HashMap<String, Object>();
                chargeParams.put("amount", 500);
                chargeParams.put("currency", "usd");
                chargeParams.put("description", "cus_CosxmqGw8AyvPK");//customerId);
                chargeParams.put("source", tok);//"tok_visa");

                try {
                    Charge.create(chargeParams);
                    //Customer.create(customerParams);
                } catch (StripeException e) {
                    e.printStackTrace();
                }

                return null;
            }
        };

        try{
            ast.wait(50000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }

        //ast.execute();
/*
        Stripe.apiKey = "sk_test_1uDfdDN5u2hgBegUb5kbD6qr";

        Map<String, Object> chargeMap = new HashMap<String, Object>();
        chargeMap.put("amount", 100);
        chargeMap.put("currency", "usd");
        chargeMap.put("source", tok); // obtained via Stripe.js

        try {
            Charge charge = Charge.create(chargeMap);
            System.out.println(charge);
            Log.d("AAAAAAAAAAAAAAAAAA","YYYYYYYYYYYYYYYYYYYY");
        } catch (StripeException e) {
            e.printStackTrace();
        }
*/

    }
    /*
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
    }*/
}
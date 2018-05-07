package com.example.james.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.james.myapplication.activities.AccountTestMain;
import com.example.james.myapplication.activities.Login;
import com.stripe.Stripe;
import com.stripe.android.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.Customer;

import java.util.HashMap;
import java.util.Map;

public class InitActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}

package com.example.james.myapplication.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.james.myapplication.R;
import com.example.james.myapplication.stripe.StripeCool;
import com.example.james.myapplication.unused.StripeFunction;
import com.example.james.myapplication.models.MyRecyclerViewAdapterShoes;

//import kotlinx.android.synthetic.main.activity_tab.*;
//import kotlinx.android.synthetic.main.create_account.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.exception.StripeException;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardInputWidget;
import com.stripe.android.view.CardMultilineWidget;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Order;


import org.xml.sax.ErrorHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PurchaseStepOneFragment extends Fragment
{
    private MyRecyclerViewAdapterShoes adapter;
    private String desired_string;
    private String desired_string2;
    private String desired_string3;
    private String desired_string4;
    private String desired_string5;
    private String desired_string6;
    private Button button;
    private int purchaseConfirmation;
    private StripeFunction stripeFunction;
    private StripeCool sc;
    private String tokenn;
    private CardInputWidget mCardInputWidget;
    private CardMultilineWidget mCardMultilineWidget;
    //com.stripe.exception.StripeException;
    private StripeException mStripeExeption;
    private Card cardToSave;
    //private Card mCardMultilineWidget;
    ErrorHandler eh;

    private String itemName;
    private String itemSellerId;
    private String itemShoeId;
    private String itemBrand;
    private String itemGender;
    private String itemShoeType;
    private String itemImageUrl;
    private String itemShoeCondition;
    private String itemSize;
    private String itemPrice;

    public static PurchaseStepOneFragment newInstance(String param1, String param2) {
        PurchaseStepOneFragment fragment = new PurchaseStepOneFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public PurchaseStepOneFragment(){}

    public static PurchaseStepOneFragment newInstance()
    {
        return new PurchaseStepOneFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_details_purchase_step_one, container, false);

        //CardInputWidget mCardInputWidget = (CardInputWidget) getActivity().findViewById(R.id.card_input_widget);
        //chargeStripeAccount();

        mCardInputWidget = (CardInputWidget) v.findViewById(R.id.card_input_widget);



        //mCardMultilineWidget = (CardMultilineWidget) v.findViewById(R.id.card_multiline_widget);


/*
        Card cardToSave = mCardMultilineWidget.getCard();
        if (cardToSave == null) {
            mErrorDialogHandler.showError("Invalid Card Data");
            return;
        }

        Card cardToSave = mCardMultilineWidget.getCard();

        stripe.createToken(
                card,
                new TokenCallback() {
                    public void onSuccess(Token token) {
                        // Send token to your own web service
                        MyServer.chargeToken(token);
                    }
                    public void onError(Exception error) {
                        Toast.makeText(getContext(),
                                error.getLocalizedMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }
        );

        if (cardToSave == null) {
            mErrorDialogHandler.showError("Invalid Card Data");
            return;
        }

        Stripe stripe
        new Stripe(v.getContext()).setDefaultPublishableKey("YOUR_PUBLISHABLE_KEY");
        stripe.createToken(
                new Card("4242424242424242", 12, 2013, "123"),
                tokenCallback
        );
*/

        // Get data for transaction
        Bundle arguments = getArguments();
        //desired_string = arguments.getString("itemId");
        desired_string2 = arguments.getString("itemId1");
        desired_string3 = arguments.getString("itemPicture2");
        desired_string4 = arguments.getString("itemDescription3");
        desired_string5 = arguments.getString("itemDescription3");
        desired_string6 = arguments.getString("itemSeller4");

        itemName = arguments.getString("itemName");
        itemSellerId = arguments.getString("itemSellerId");
        itemShoeId = arguments.getString("itemShoeId");
        itemBrand = arguments.getString("itemBrand");
        itemGender = arguments.getString("itemGender");
        itemShoeType = arguments.getString("itemShoeType");
        itemImageUrl = arguments.getString("itemImageUrl");
        itemShoeCondition = arguments.getString("itemShoeCondition");
        itemSize = arguments.getString("itemSize");
        itemPrice = arguments.getString("itemPrice");

        //Toast.makeText(getContext(), "" + desired_string4, Toast.LENGTH_LONG).show();

        // Record purchase to firebase and show user confirmation fragment
        button = (Button) v.findViewById(R.id.confirm_purchase);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                AsyncTask asyncTask = new AsyncTask() {
                    @Override
                    protected Object doInBackground(Object[] objects) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        final DatabaseReference myRef = database.getReference("users");

                        myRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                FirebaseUser user2 = FirebaseAuth.getInstance().getCurrentUser();
                                String string = dataSnapshot.child(user2.getUid()).child("stripe_customer_id").getValue().toString();

                                myRef.removeEventListener(this);
                                chargeStripeAccount();


                                //FirebaseAppHelper fah = new FirebaseAppHelper();
                                Random rand = new Random();
                                purchaseConfirmation = rand.nextInt(10000000) + 1;
                                desired_string5 = "" + purchaseConfirmation;

                                // Insert purchase confirmation to buyer
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                FirebaseDatabase fba = FirebaseDatabase.getInstance();
                                DatabaseReference db = fba.getReference("users").child(user.getUid()).child("6buy_history");
                                db.child("" + purchaseConfirmation).child("itemId").setValue("" + desired_string2);
                                db.child("" + purchaseConfirmation).child("itemImage").setValue("" + desired_string3);
                                db.child("" + purchaseConfirmation).child("Brand").setValue("" + desired_string4);

                                // Insert purchase confirmation to seller
                                FirebaseUser userseller = FirebaseAuth.getInstance().getCurrentUser();
                                FirebaseDatabase fbaseller = FirebaseDatabase.getInstance();
                                DatabaseReference dbseller = fbaseller.getReference("users").child(desired_string6).child("7sell_history");
                                dbseller.child("" + purchaseConfirmation).child("itemId").setValue("" + desired_string2);
                                dbseller.child("" + purchaseConfirmation).child("itemImage").setValue("" + desired_string3);
                                dbseller.child("" + purchaseConfirmation).child("Brand").setValue("" + desired_string4);
                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                            }
                        });

                        performTransition();
                        return null;
                    }
                };
                asyncTask.execute();
            }
        });



        return v;
    }

    //@Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    private void performTransition()
    {
        Fragment fragment = new PurchaseStepTwoFragment();

        // Bundle data to pass
        Bundle arguments = new Bundle();
        arguments.putString( "itemId" , "" + desired_string2);
        arguments.putString( "itemImage" , "" + desired_string3);
        arguments.putString( "Brand" , "" + desired_string4);
        arguments.putString( "confId" , "" + desired_string5);
        arguments.putString( "seller" , "" + desired_string6);

        arguments.putString( "itemName", itemName);
        arguments.putString( "itemSellerId", itemSellerId);
        arguments.putString( "itemShoeId", itemShoeId);
        arguments.putString( "itemBrand", itemBrand);
        arguments.putString( "itemGender", itemGender);
        arguments.putString( "itemShoeType", itemShoeType);
        arguments.putString( "itemImageUrl", itemImageUrl);
        arguments.putString( "itemShoeCondition" , itemShoeCondition);
        arguments.putString( "itemSize", itemSize);
        arguments.putString( "itemPrice", itemPrice);

        fragment.setArguments(arguments);

        // Take off to next fragment
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void chargeStripeAccount()
    {
        Card cardToSave = mCardInputWidget.getCard();
        if (cardToSave == null) {
            //mErrorDialogHandler.showError("Invalid Card Data");
            return;
        }



        //try
        //{
            //Card
            cardToSave = mCardInputWidget.getCard();
            if (cardToSave == null) {
                //mErrorDialogHandler.showError("Invalid Card Data");
            }
                cardToSave.setName("Customer Name");
                cardToSave.setAddressZip("12345");

                sc = new StripeCool();
                //Card card = new Card("4242424242424242", 01, 2019, "123");
                Stripe stripe = new Stripe(getContext(), "pk_test_yFM3zfqa8WXhKLP8hfP8P5cW");
                stripe.createToken(cardToSave, new TokenCallback() {
                    public void onSuccess(Token token) {
                        tokenn = token.getId();
                        AsyncTask ast = new AsyncTask() {
                            @Override
                            protected Object doInBackground(Object[] objects) {
                                StripeCool sc = new StripeCool();
                                sc.chargeCreditCard(new Order(), tokenn);
                                return null;
                            }
                        }.execute();
                    }
                    public void onError(Exception error) {
                    }
                });

            if (!cardToSave.validateCard()) {
                // Do not continue token creation.
            }
        //}
        //catch(Exception e)
        //{
          //  Log.d("card numberincorrectfix", e.getStackTrace().getClass().toString());
        //}
/*
        Card cardToSaveMulti = mCardMultilineWidget.getCard();
        if (cardToSaveMulti == null) {
            //mErrorDialogHandler.showError("Invalid Card Data");
            //return;
        }
        Stripe stripeMulti = new Stripe(getContext(), "pk_test_yFM3zfqa8WXhKLP8hfP8P5cW");
        stripeMulti.createToken(
                cardToSaveMulti,
                new TokenCallback() {
                    public void onSuccess(Token token) {
                        // Send token to your server
                        tokenn = token.getId();
                        AsyncTask ast = new AsyncTask() {
                            @Override
                            protected Object doInBackground(Object[] objects) {
                                StripeCool sc = new StripeCool();
                                sc.chargeCreditCard(new Order(), tokenn);
                                return null;
                            }
                        }.execute();
                    }
                    public void onError(Exception error) {
                        // Show localized error message
                        //Toast.makeText(getContext(),
                        //error.getLocalizedString(getContext()),
                        //Toast.LENGTH_LONG
                        //).show();
                    }
                }
        );*/
    }
}
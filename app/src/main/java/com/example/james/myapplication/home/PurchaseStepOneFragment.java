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
import com.example.james.myapplication.R;
import com.example.james.myapplication.stripe.StripeCool;
import com.example.james.myapplication.unused.StripeFunction;
import com.example.james.myapplication.models.MyRecyclerViewAdapterShoes;
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
import com.stripe.model.Order;
import org.xml.sax.ErrorHandler;
import java.util.Random;

public class PurchaseStepOneFragment extends Fragment
{
    private MyRecyclerViewAdapterShoes adapter;
    private Button button;
    private String purchaseConfirmation;
    private StripeFunction stripeFunction;
    private StripeCool sc;
    private String tokenn;
    private CardInputWidget mCardInputWidget;
    private CardMultilineWidget mCardMultilineWidget;
    private StripeException mStripeExeption;
    private Card cardToSave;
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
    private ErrorHandler eh;

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
        mCardInputWidget = (CardInputWidget) v.findViewById(R.id.card_input_widget);

        // Get data for transaction
        Bundle arguments = getArguments();
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

                                chargeStripeAccount(user2.toString());

                                Random rand = new Random();
                                purchaseConfirmation = "" + rand.nextInt(10000000) + 1;

                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                FirebaseDatabase fba = FirebaseDatabase.getInstance();
                                DatabaseReference db = fba.getReference("users").child(user.getUid()).child("6buy_history");
                                db.child("" + purchaseConfirmation).child("itemName").setValue("" + itemName);
                                db.child("" + purchaseConfirmation).child("itemSellerId").setValue("" + itemSellerId);
                                db.child("" + purchaseConfirmation).child("itemShoeId").setValue("" + itemShoeId);
                                db.child("" + purchaseConfirmation).child("itemBrand").setValue("" + itemBrand);
                                db.child("" + purchaseConfirmation).child("itemGender").setValue("" + itemGender);
                                db.child("" + purchaseConfirmation).child("itemShoeType").setValue("" + itemShoeType);
                                db.child("" + purchaseConfirmation).child("itemImageUrl").setValue("" + itemImageUrl);
                                db.child("" + purchaseConfirmation).child("itemShoeCondition").setValue("" + itemShoeCondition);
                                db.child("" + purchaseConfirmation).child("itemSize").setValue("" + itemSize);
                                db.child("" + purchaseConfirmation).child("itemPrice").setValue("" + itemPrice);

                                FirebaseUser userseller = FirebaseAuth.getInstance().getCurrentUser();
                                FirebaseDatabase fbaseller = FirebaseDatabase.getInstance();
                                DatabaseReference dbseller = fbaseller.getReference("users").child(itemSellerId).child("7sell_history");
                                dbseller.child("" + purchaseConfirmation).child("itemName").setValue("" + itemName);
                                dbseller.child("" + purchaseConfirmation).child("itemSellerId").setValue("" + itemSellerId);
                                dbseller.child("" + purchaseConfirmation).child("itemShoeId").setValue("" + itemShoeId);
                                dbseller.child("" + purchaseConfirmation).child("itemBrand").setValue("" + itemBrand);
                                dbseller.child("" + purchaseConfirmation).child("itemGender").setValue("" + itemGender);
                                dbseller.child("" + purchaseConfirmation).child("itemShoeType").setValue("" + itemShoeType);
                                dbseller.child("" + purchaseConfirmation).child("itemImageUrl").setValue("" + itemImageUrl);
                                dbseller.child("" + purchaseConfirmation).child("itemShoeCondition").setValue("" + itemShoeCondition);
                                dbseller.child("" + purchaseConfirmation).child("itemSize").setValue("" + itemSize);
                                dbseller.child("" + purchaseConfirmation).child("itemPrice").setValue("" + itemPrice);

                                performTransition();//IMPREF//
                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                            }
                        });

                        //IMPREF//performTransition();
                        return null;
                    }
                };
                asyncTask.execute();



                //StripeCool sc = new StripeCool();
                //sc.chargeCreditCard(new Order(), tokenn);
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
        Bundle arguments = new Bundle();
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
        arguments.putString( "confirmationCode", "" + purchaseConfirmation);

        Fragment fragment = new PurchaseStepTwoFragment();
        fragment.setArguments(arguments);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void chargeStripeAccount(String sss)
    {
        //Card cardToSave = mCardInputWidget.getCard();
        //if (cardToSave == null) {
            //mErrorDialogHandler.showError("Invalid Card Data");
        //    return;
        //}


        cardToSave = mCardInputWidget.getCard();
        if (cardToSave == null) {
            //IMPOREF//mErrorDialogHandler.showError("Invalid Card Data");
        }

        Log.d("cussss",sss);
        cardToSave.setName(sss);//"Customer Name");
        cardToSave.setAddressZip("12345");

        //IMPOREF//Card card = new Card("4242424242424242", 01, 2019, "123");
        sc = new StripeCool();
        Stripe stripe = new Stripe(getContext(), "pk_test_yFM3zfqa8WXhKLP8hfP8P5cW");
        stripe.createToken(cardToSave, new TokenCallback() {
            public void onSuccess(Token token) {
                tokenn = token.getId();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReference("users");

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        FirebaseUser user2 = FirebaseAuth.getInstance().getCurrentUser();
                        String string = dataSnapshot.child(user2.getUid()).child("stripe_customer_id").getValue().toString();

                        myRef.removeEventListener(this);

                        AsyncTask ast = new AsyncTask() {
                            @Override
                            protected Object doInBackground(Object[] objects) {
                                StripeCool sc = new StripeCool();
                                sc.chargeCreditCard(new Order(), tokenn,objects[0].toString());
                                Log.d("tokkkkkk", objects[0].toString());//tokenn);
                                return null;
                            }
                        }.execute(string);
                    }
                    @Override
                    public void onCancelled(DatabaseError error) {
                    }
                });


            }
            public void onError(Exception error) {
            }
        });

        if (!cardToSave.validateCard()) {
            // Do not continue token creation.
        }
    }
}

package com.example.james.myapplication.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.james.myapplication.R;
import com.example.james.myapplication.StripeFunction;
import com.example.james.myapplication.models.MyRecyclerViewAdapterShoes;

//import kotlinx.android.synthetic.main.activity_tab.*;
//import kotlinx.android.synthetic.main.create_account.*;
import com.example.james.myapplication.models.Shoe;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class PurchaseStepOneFragment extends Fragment
{
    private MyRecyclerViewAdapterShoes adapter;
    private String desired_string;
    private String desired_string2;
    private String desired_string3;
    private String desired_string4;
    private String desired_string5;
    private Button button;
    private int purchaseConfirmation;
    private StripeFunction stripeFunction;

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


        // Get data for transaction
        Bundle arguments = getArguments();
        //desired_string = arguments.getString("itemId");
        desired_string2 = arguments.getString("itemId1");
        desired_string3 = arguments.getString("itemPicture2");
        desired_string4 = arguments.getString("itemDescription3");
        desired_string5 = arguments.getString("itemDescription3");

        //Toast.makeText(getContext(), "" + desired_string4, Toast.LENGTH_LONG).show();

        // Record purchase to firebase and show user confirmation fragment
        button = (Button) v.findViewById(R.id.confirm_purchase);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //FirebaseAppHelper fah = new FirebaseAppHelper();
                Random rand = new Random();
                purchaseConfirmation = rand.nextInt(10000000) + 1;
                desired_string5 = "" + purchaseConfirmation;






                AsyncTask asyncTask = new AsyncTask() {
                    @Override
                    protected Object doInBackground(Object[] objects) {
/*
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        //Toast.makeText(getContext(), "" + user.getUid(), Toast.LENGTH_LONG).show();

                        FirebaseDatabase fba = FirebaseDatabase.getInstance();
                        //DatabaseReference db = fba.getReference("users").child("DrmYuz6ApObAgBKGjlQM2JvER8t1").child("6buy_history");
                        //DatabaseReference db = fba.getReference("users").child("OS8hpHlgYRgTB73CAC4EC7badD82").child("6buy_history");
                        DatabaseReference db = fba.getReference("users").child(user.getUid()).child("6buy_history");
                        db.child("" + purchaseConfirmation).child("itemId").setValue("" + desired_string2);
                        db.child("" + purchaseConfirmation).child("itemImage").setValue("" + desired_string3);
                        db.child("" + purchaseConfirmation).child("Brand").setValue("" + desired_string4);

                        //String cusIdString = "";
                        //DatabaseReference db2 = fba.getReference("users").child(user.getUid()).child("strip_customer_id");
*/
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        final DatabaseReference myRef = database.getReference("users");

                        myRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                String string = dataSnapshot.child(user.getUid()).child("stripe_customer_id").getValue().toString();

                                //stripeFunction = new StripeFunction();//user2.getEmail(),"");
                                //Toast.makeText(getContext(),"hello " + string,Toast.LENGTH_LONG).show();
                                //stripeFunction.charge(string);

                                myRef.removeEventListener(this);
                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                            }
                        });
                        //FirebaseUser user2 = FirebaseAuth.getInstance().getCurrentUser();

                        //FirebaseDatabase database = FirebaseDatabase.getInstance();

                        //DatabaseReference myRef = fba.getReference("users").child(user.toString()).child("strip_customer_id");
                        //myRef .getValue().toString();
                        //db = fba.getReference("users").child(user.toString()).child("strip_customer_id");
                        //cusIdString = db.toString();

                        //StripeFunction stripeFunction = new StripeFunction();//user2.getEmail(),"");
                        //stripeFunction.charge(cusIdString);
                        //string = stripeFunction.createAccount(user2.getEmail(),"");

                        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        //FirebaseUser fba = FirebaseAuth.getInstance().getCurrentUser();
                        //DatabaseReference db = FirebaseDatabase.getInstance().getReference();

                        //db.child("users").child(user.getUid()).child("stripe_customer_id").setValue(string);


                        //performTransition();
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
        fragment.setArguments(arguments);

        // Take off to next fragment
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
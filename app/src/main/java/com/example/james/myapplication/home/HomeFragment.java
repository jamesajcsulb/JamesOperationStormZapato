package com.example.james.myapplication.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.james.myapplication.StripeCool;
import com.example.james.myapplication.StripeFunction;
import com.example.james.myapplication.models.ImageAdapter;
import com.example.james.myapplication.R;
import com.example.james.myapplication.models.MyRecyclerViewAdapterShoes;
import com.example.james.myapplication.home.HomeFragmentRecyclerViewAdapter;
import com.example.james.myapplication.models.Shoe;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
//import com.stripe.android.model.Token;
import com.stripe.android.model.Token;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.security.AccessController.getContext;

public class HomeFragment extends Fragment
{
    private GridView gridView = null;
    private ArrayList<Shoe> mShoeList = null;
    private ImageAdapter mImageAdapter = null;
    private ArrayList<String> shoearraypass = null;
    private ArrayList<Shoe> classshoe = null;
    private ArrayAdapter<Shoe> adap = null;
    private ImageAdapter imageViewing = null;
    private RecyclerView recycled = null;
    private HomeFragmentRecyclerViewAdapter adaptershoe;
    private Button button;
    private ImageView ivvv;
    private LinearLayout rvvvv;
    private RecyclerView recview;
    private RecyclerView rectest;
    private ListView listTest;
    private StripeCool sc;
    private String tokenn;

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public static HomeFragment newInstance()
    {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_home, container, false);


         sc = new StripeCool();





        Card card = new Card("4242424242424242", 01, 2019, "123");
        Stripe stripe = new Stripe(getContext(), "pk_test_yFM3zfqa8WXhKLP8hfP8P5cW");
        stripe.createToken(
                card,
                new TokenCallback() {
                    public void onSuccess(Token token) {
                        // Send token to your server
                        Toast.makeText(getContext(), token.getId(), Toast.LENGTH_LONG).show();
                        //StripeFunction sf = new StripeFunction();
                        //sf.charge(token);

                        //StripeFunction sf = new StripeFunction(token.getId());
                        //sf.charge();
                        //Order o = new Order();
                        //sf.chargeCreditCard(o);
                        tokenn = token.getId();
                        AsyncTask ast = new AsyncTask() {
                            @Override
                            protected Object doInBackground(Object[] objects) {
                                StripeCool sc = new StripeCool();
                                sc.chargeCreditCard(new Order(), tokenn);
                                return null;
                            }
                        }.execute();




/*
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
                        }*/
                    }
                    public void onError(Exception error) {
                        // Show localized error message
                        //Toast.makeText(getContext(),
                        //error.getLocalizedString(getContext()),
                        //       Toast.LENGTH_LONG
                        //).show();
                    }
                }
        );




        //ivvv = (ImageView) v.findViewById(R.id.image_view_test);
        //ivvv.setClickable(true);
        //ivvv.setFocusable(true);
        //rectest = (RecyclerView) v.findViewById(R.id.rec_test);
        //ivvv.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
              //  Toast.makeText(getContext(), "this", Toast.LENGTH_LONG).show();
            //}
        //});
        if (v != null)
        {


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("users");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                shoearraypass = new ArrayList<String>();
                classshoe = new ArrayList<Shoe>();
                for (DataSnapshot snaparray : dataSnapshot.getChildren()) {
                    //if(snaparray.getValue() != null)
                    //{
                        for(DataSnapshot snaparray2 : snaparray.child("9sellinventory").child("shoes").child("b22mhFiqHuVR9vwowQyQjD4720Q2").getChildren()) {
                            shoearraypass.add("" + snaparray2.child("shoeImageUrl").getValue());
                            classshoe.add(new Shoe(snaparray2));
                        }
                   // }
                }
                myRef.removeEventListener(this);

                RecyclerView recyclerView2 = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
                int numberOfColumns2 = 2;
                recyclerView2.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns2));
                adaptershoe = new HomeFragmentRecyclerViewAdapter(getContext(), classshoe, getContext(), getActivity(), new HomeFragment() );
                recyclerView2.setAdapter(adaptershoe);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        }

/*
        recview = (RecyclerView) v.findViewById(R.id.linerec);
        recview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_MOVE){
                    Toast.makeText(getContext(), "moveA", Toast.LENGTH_LONG).show();
                }
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Toast.makeText(getContext(), "downA", Toast.LENGTH_LONG).show();
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    Toast.makeText(getContext(), "upA", Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });
*//*
        AsyncTask ask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                stripetok2 ();
                return null;
            }
        }.execute();
*/

        return v;
    }

    public void stripetok2 () {
        Card card = new Card("4242424242424242", 01, 2019, "123");
// Remember to validate the card object before you use it to save time.
        if (!card.validateCard()) {
            // Do not continue token creation.
        }

        Stripe stripe = new Stripe(getContext(), "sk_test_1uDfdDN5u2hgBegUb5kbD6qr");
        stripe.createToken(
                card,
                new TokenCallback() {
                    public void onSuccess(Token token) {
                        // Send token to your server
                        Toast.makeText(getContext(), token.getId(), Toast.LENGTH_LONG).show();
                        //StripeFunction sf = new StripeFunction();
                        //sf.charge(token);

                        Map<String, Object> chargeParams = new HashMap<String, Object>();
                        chargeParams.put("amount", 500);
                        chargeParams.put("currency", "usd");
                        chargeParams.put("description", "cus_CotuLfi5J882Wx");//customerId);
                        chargeParams.put("source", token);//"tok_visa");

                        try {
                            Charge.create(chargeParams);
                        } catch (StripeException e) {
                            e.printStackTrace();
                        }
                    }
                    public void onError(Exception error) {
                        // Show localized error message
                        //Toast.makeText(getContext(),
                                //error.getLocalizedString(getContext()),
                         //       Toast.LENGTH_LONG
                        //).show();
                    }
                }
        );

    }
}

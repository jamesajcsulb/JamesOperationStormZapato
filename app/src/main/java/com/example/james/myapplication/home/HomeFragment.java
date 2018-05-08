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
import com.example.james.myapplication.stripe.StripeCool;
import com.example.james.myapplication.models.ImageAdapter;
import com.example.james.myapplication.R;
import com.example.james.myapplication.models.Shoe;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.model.Order;
import java.util.ArrayList;

public class HomeFragment extends Fragment {
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

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("users");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                shoearraypass = new ArrayList<String>();
                classshoe = new ArrayList<Shoe>();
                for (DataSnapshot snaparray : dataSnapshot.getChildren()) {
                    for (DataSnapshot snaparray2 : snaparray.child("9sellinventory").child("shoes").child("b22mhFiqHuVR9vwowQyQjD4720Q2").getChildren()) {
                        shoearraypass.add("" + snaparray2.child("shoeImageUrl").getValue());
                        classshoe.add(new Shoe(snaparray2));
                    }
                }
                myRef.removeEventListener(this);

                RecyclerView recyclerView2 = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
                int numberOfColumns2 = 2;
                recyclerView2.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns2));
                adaptershoe = new HomeFragmentRecyclerViewAdapter(getContext(), classshoe, getContext(), getActivity(), new HomeFragment());
                recyclerView2.setAdapter(adaptershoe);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        //chargeStripeAccount();

        return v;
    }
/*
    public void chargeStripeAccount()
    {
        sc = new StripeCool();

        Card card = new Card("4242424242424242", 01, 2019, "123");
        Stripe stripe = new Stripe(getContext(), "pk_test_yFM3zfqa8WXhKLP8hfP8P5cW");
        stripe.createToken(card, new TokenCallback() {
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
    }*/
}

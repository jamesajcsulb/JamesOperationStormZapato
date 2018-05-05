package com.example.james.myapplication.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.example.james.myapplication.R;
import com.example.james.myapplication.home.HomeFragment;
import com.example.james.myapplication.models.ImageAdapter;
import com.example.james.myapplication.models.Shoe;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileFragmentDetails extends Fragment
{
    private GridView gridView = null;
    private ArrayList<Shoe> mShoeList = null;
    private ImageAdapter mImageAdapter = null;
    private ArrayList<String> shoearraypass = null;
    private ArrayList<Shoe> classshoe = null;
    private ArrayAdapter<Shoe> adap = null;
    private ImageAdapter imageViewing = null;
    private RecyclerView recycled = null;
    private ProfileRecyclerDetailsViewAdapter adaptershoe;

    public static ProfileFragmentDetails newInstance(String param1, String param2) {
        ProfileFragmentDetails fragment = new ProfileFragmentDetails();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public static ProfileFragmentDetails newInstance()
    {
        return new ProfileFragmentDetails();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        //container.removeAllViews();
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("shoes");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                shoearraypass = new ArrayList<String>();
                classshoe = new ArrayList<Shoe>();
                String ds = dataSnapshot.child("b22mhFiqHuVR9vwowQyQjD4720Q2").getKey();
                for (DataSnapshot snaparray : dataSnapshot.child("b22mhFiqHuVR9vwowQyQjD4720Q2").getChildren()) {
                    //for() {
                    shoearraypass.add("" + snaparray.child("shoeImageUrl").getValue());
                    classshoe.add(new Shoe(snaparray));
                    //Toast.makeText(getContext(),"Zeta: " + snaparray,Toast.LENGTH_SHORT).show();
                    //}
                }

                RecyclerView recyclerView2 = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
                int numberOfColumns2 = 1;
                recyclerView2.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns2));
                adaptershoe = new ProfileRecyclerDetailsViewAdapter(getContext(), classshoe, getContext(), getActivity(), new HomeFragment() );
                recyclerView2.setAdapter(adaptershoe);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        //ListView mListView = (ListView) getActivity().findViewById(R.id.staticListView);
        //mListView.setAdapter(null);

        return v;
    }
}

package com.example.james.myapplication.home;

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
import java.util.ArrayList;

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
*/
        return v;
    }
}

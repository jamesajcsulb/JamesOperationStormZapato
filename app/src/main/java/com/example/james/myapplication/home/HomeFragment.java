package com.example.james.myapplication.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.example.james.myapplication.Network.FirebaseManager;
import com.example.james.myapplication.models.ImageAdapter;
import com.example.james.myapplication.models.MyAdapter;
import com.example.james.myapplication.models.MyRecyclerViewAdapter;
import com.example.james.myapplication.R;
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
    private ArrayAdapter<Shoe> adap = null;
    private ImageAdapter imageViewing = null;
    private RecyclerView recycled = null;
    private MyRecyclerViewAdapter adapter;

    public HomeFragment()
    {
    }

    public static HomeFragment newInstance()
    {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState)
    {

        View v = inflater.inflate(R.layout.fragment_home2, container, false);
        if (v != null)
        {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return v;
    }

}

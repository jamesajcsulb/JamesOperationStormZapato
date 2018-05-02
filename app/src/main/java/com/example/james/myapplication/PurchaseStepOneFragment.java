package com.example.james.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;


public class PurchaseStepOneFragment extends Fragment
{


    private MyRecyclerViewAdapter adapter;

    public PurchaseStepOneFragment()
    {
    }

    public static PurchaseStepOneFragment newInstance()
    {
        return new PurchaseStepOneFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_details_purchase_step_one, container, false);
        if (v != null)
        {
        }
//
        //Bundle arguments = getArguments();
        //String desired_string = arguments.getString("welcome");
        //Toast.makeText(getContext(),"bundlereceived: " + desired_string,Toast.LENGTH_SHORT).show();

        //ListView mListView = (ListView) getActivity().findViewById(R.id.staticListView);
        //mListView.setAdapter(null);
/*
        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.rvNumbers);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));
        adapter = new MyRecyclerViewAdapter(getContext(), null, getContext(), getActivity(), new HomeFragment() );
        //adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
*/
        return v;
    }
}

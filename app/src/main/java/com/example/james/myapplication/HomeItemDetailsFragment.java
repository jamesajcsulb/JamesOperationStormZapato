package com.example.james.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class HomeItemDetailsFragment extends Fragment
{
    public HomeItemDetailsFragment()
    {
    }

    public static HomeItemDetailsFragment newInstance()
    {
        return new HomeItemDetailsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_details_item_home, container, false);
        if (v != null)
        {
        }

        Bundle arguments = getArguments();
        String desired_string = arguments.getString("welcome");
        Toast.makeText(getContext(),"bundlereceived: " + desired_string,Toast.LENGTH_SHORT).show();

        ListView mListView = (ListView) getActivity().findViewById(R.id.staticListView);
        mListView.setAdapter(null);

        return v;
    }
}

package com.example.james.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ProfileFragment extends Fragment
{
    public ProfileFragment()
    {
    }

    public static ProfileFragment newInstance()
    {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        //container.removeAllViews();
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        if (v != null)
        {
        }

        ListView mListView = (ListView) getActivity().findViewById(R.id.staticListView);
        mListView.setAdapter(null);

        return v;
    }
}

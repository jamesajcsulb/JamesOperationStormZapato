package com.example.james.myapplication.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.support.v4.app.Fragment;

import com.example.james.myapplication.R;

public class PostFragment extends Fragment {
    public PostFragment()
    {
    }

    public static PostFragment newInstance()
    {
        return new PostFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        //container.removeAllViews();
        View v = inflater.inflate(R.layout.fragment_post, container, false);
        if (v != null)
        {
        }

        ListView mListView = (ListView) getActivity().findViewById(R.id.staticListView);
        mListView.setAdapter(null);

        return v;
    }
}

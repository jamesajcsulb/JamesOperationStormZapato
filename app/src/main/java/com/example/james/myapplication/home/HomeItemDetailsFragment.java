package com.example.james.myapplication.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.james.myapplication.models.MyRecyclerViewAdapter;
import com.example.james.myapplication.PurchaseStepOneFragment;
import com.example.james.myapplication.R;


public class HomeItemDetailsFragment extends Fragment
{
    private MyRecyclerViewAdapter adapter;
    private Button btn;

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


        final Button button = (Button) v.findViewById(R.id.purchase_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                performTransition();

            }
        });

        return v;
    }

    private void performTransition()
    {
        Fragment fragment = new PurchaseStepOneFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.james.myapplication.models.MyRecyclerViewAdapterShoes;
import com.example.james.myapplication.PurchaseStepOneFragment;
import com.example.james.myapplication.R;

public class HomeItemDetailsFragment extends Fragment
{
    private MyRecyclerViewAdapterShoes adapter;
    private Button btn;
    private ImageView myImageView;
    private TextView mTextView;

    public HomeItemDetailsFragment(){}

    public static HomeItemDetailsFragment newInstance()
    {
        return new HomeItemDetailsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_details_item_home, container, false);

        Bundle arguments = getArguments();
        String desired_string = arguments.getString("welcome");
        String desired_string2 = arguments.getString("itemId");
        String desired_string3 = arguments.getString("itemPicture");
        String desired_string4 = arguments.getString("itemDescription");
        //Toast.makeText(getContext(),"bundlereceived: \n\n"
        //        + desired_string + " \n\n" + desired_string2
        //                + " \n\n" + desired_string3 + " \n\n" + desired_string4
        //        ,Toast.LENGTH_SHORT).show();

        myImageView=(ImageView)v.findViewById(R.id.imageView);
        Glide.with(myImageView.getContext())
                .load(""+arguments.getString("itemPicture"))
                .into(myImageView);

        //ListView mListView = (ListView) getActivity().findViewById(R.id.staticListView);
        //mListView.setAdapter(null);

        // Set completDeatilsText

        //mTextView = (TextView) getActivity().findViewById(R.id.completeDetailsText);
        //mTextView.setText("" + desired_string3 + desired_string4);

        final Button button = (Button) v.findViewById(R.id.purchase_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                performTransition();

            }
        });

        /*
        LinearLayoutManager layoutManager
            = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        RecyclerView myList = (RecyclerView) findViewById(R.id.my_recycler_view);
        myList.setLayoutManager(layoutManager);
         */
        return v;
    }

    //@Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        Bundle arguments = getArguments();
        String desired_string = arguments.getString("welcome");
        String desired_string2 = arguments.getString("itemId");
        String desired_string3 = arguments.getString("itemPicture");
        String desired_string4 = arguments.getString("itemDescription");

        mTextView = (TextView) getActivity().findViewById(R.id.completeDetailsText);
        mTextView.setText("Item ID:" + desired_string2 + " \nDescription: " + desired_string4);
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

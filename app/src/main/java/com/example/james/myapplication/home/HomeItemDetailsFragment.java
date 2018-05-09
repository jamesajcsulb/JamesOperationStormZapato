package com.example.james.myapplication.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.james.myapplication.MainActivity;
import com.example.james.myapplication.models.MyRecyclerViewAdapterShoes;
import com.example.james.myapplication.R;

public class HomeItemDetailsFragment extends Fragment
{
    private MyRecyclerViewAdapterShoes adapter;
    private Button btn;
    private ImageView myImageView;
    private TextView mTextView;
    private TextView mTextViewSeller;
    private String desired_string;
    private String desired_string2;
    private String desired_string3;
    private String desired_string4;
    private String desired_string5;
    private Button button;
    //private TextView mTextView;

    private String itemName;
    private String itemSellerId;
    private String itemShoeId;
    private String itemBrand;
    private String itemGender;
    private String itemShoeType;
    private String itemImageUrl;
    private String itemShoeCondition;
    private String itemSize;
    private String itemPrice;

    public static HomeItemDetailsFragment newInstance(String param1, String param2) {
        HomeItemDetailsFragment fragment = new HomeItemDetailsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

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
        desired_string = arguments.getString("welcome");
        desired_string2 = arguments.getString("itemId");
        desired_string3 = arguments.getString("itemPicture");
        desired_string4 = arguments.getString("itemDescription");
        desired_string5 = arguments.getString("itemDescription");

        itemName = arguments.getString("itemName");
        itemSellerId = arguments.getString("itemSellerId");
        itemShoeId = arguments.getString("itemShoeId");
        itemBrand = arguments.getString("itemBrand");
        itemGender = arguments.getString("itemGender");
        itemShoeType = arguments.getString("itemShoeType");
        itemImageUrl = arguments.getString("itemImageUrl");
        itemShoeCondition = arguments.getString("itemShoeCondition");
        itemSize = arguments.getString("itemSize");
        itemPrice = arguments.getString("itemPrice");

        myImageView=(ImageView)v.findViewById(R.id.imageView);
        Glide.with(myImageView.getContext())
                .load(""+arguments.getString("itemImageUrl"))
                .into(myImageView);

        button = (Button) v.findViewById(R.id.purchase_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                performTransition();
            }
        });
        ImageView imageView = (ImageView) v.findViewById(R.id.purchase_button);

        mTextView = (TextView) v.findViewById(R.id.completeDetailsText);
        mTextView.setText(itemName+" "+itemSellerId+" "+itemShoeId+" "+itemBrand+" "+itemGender+" "+itemShoeType+" "+itemShoeCondition+" "+itemSize+" "+itemPrice);

        mTextViewSeller = (TextView) v.findViewById(R.id.sellerInformation);
        mTextViewSeller.setText("Seller Id: " + itemSellerId);
        return v;
    }

    //@Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
/*
        Bundle arguments = getArguments();
        desired_string = arguments.getString("welcome");
        desired_string2 = arguments.getString("itemId");
        desired_string3 = arguments.getString("itemPicture");
        desired_string4 = arguments.getString("itemDescription");

        mTextView = (TextView) getActivity().findViewById(R.id.completeDetailsText);
        mTextView.setText("Item ID:" + desired_string2 + " \nDescription: " + desired_string4);
    */
    }

    private void performTransition()
    {
        Fragment fragment = new PurchaseStepOneFragment();

        // Bundle data to pass
        Bundle arguments = new Bundle();
        arguments.putString( "itemId1" , "" + desired_string2);
        arguments.putString( "itemPicture2" , "" + desired_string3);
        arguments.putString( "itemDescription3" , "" + desired_string4);
        arguments.putString( "itemSeller4" , "" + desired_string5);

        arguments.putString( "itemName", itemName);
        arguments.putString( "itemSellerId", itemSellerId);
        arguments.putString( "itemShoeId", itemShoeId);
        arguments.putString( "itemBrand", itemBrand);
        arguments.putString( "itemGender", itemGender);
        arguments.putString( "itemShoeType", itemShoeType);
        arguments.putString( "itemImageUrl", itemImageUrl);
        arguments.putString( "itemShoeCondition" , itemShoeCondition);
        arguments.putString( "itemSize", itemSize);
        arguments.putString( "itemPrice", itemPrice);

        fragment.setArguments(arguments);

        // Take off to next fragment
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

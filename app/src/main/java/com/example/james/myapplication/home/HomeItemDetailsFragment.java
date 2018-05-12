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
import com.example.james.myapplication.models.MyRecyclerViewAdapterShoes;
import com.example.james.myapplication.R;

public class HomeItemDetailsFragment extends Fragment
{
    private MyRecyclerViewAdapterShoes adapter;
    private Button btn;
    private ImageView myImageView;
    private TextView mTextView;
    private TextView mTextViewSeller;
    private Button button;
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

        mTextView = (TextView) v.findViewById(R.id.completeDetailsText);
        mTextView.setText(itemName+" "+itemSellerId+" "+itemShoeId+" "+itemBrand+" "+itemGender+" "+itemShoeType+" "+itemShoeCondition+" "+itemSize+" "+itemPrice);

        mTextViewSeller = (TextView) v.findViewById(R.id.sellerInformation);
        mTextViewSeller.setText("Seller Id: " + itemSellerId);
        return v;
    }

    //@Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    private void performTransition()
    {
        Bundle arguments = new Bundle();
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

        Fragment fragment = new PurchaseStepOneFragment();
        fragment.setArguments(arguments);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

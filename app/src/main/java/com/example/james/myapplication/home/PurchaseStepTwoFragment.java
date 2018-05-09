package com.example.james.myapplication.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.myapplication.R;
import com.example.james.myapplication.models.MyRecyclerViewAdapterShoes;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

//import kotlinx.android.synthetic.main.activity_tab.*;
//import kotlinx.android.synthetic.main.create_account.*;

public class PurchaseStepTwoFragment extends Fragment
{
    private MyRecyclerViewAdapterShoes adapter;
    private String desired_string;
    private String desired_string2;
    private String desired_string3;
    private String desired_string4;
    private String desired_string5;
    private String desired_string6;
    private Button button;
    private TextView textView;

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

    private String mConfirmationCode;

    public static PurchaseStepTwoFragment newInstance(String param1, String param2) {
        PurchaseStepTwoFragment fragment = new PurchaseStepTwoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public PurchaseStepTwoFragment(){}

    public static PurchaseStepTwoFragment newInstance()
    {
        return new PurchaseStepTwoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_details_purchase_step_two, container, false);

        // Get data for transaction
        Bundle arguments = getArguments();
        //desired_string = arguments.getString("itemId");
        //desired_string2 = arguments.getString("itemId");
        //desired_string3 = arguments.getString("itemImage");
        //desired_string4 = arguments.getString("Brand");
        //desired_string5 = arguments.getString("confId");
        //desired_string6 = arguments.getString("seller");

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

        mConfirmationCode = arguments.getString("confirmationCode");

        //Toast.makeText(getContext(), "" + desired_string5, Toast.LENGTH_LONG).show();

        textView = (TextView) v.findViewById(R.id.confirmationCodeHold);
        textView.setText(mConfirmationCode);

        // Transaction
        //button = (Button) v.findViewById(R.id.confirm_purchase);
        //button.setOnClickListener(new View.OnClickListener() {
            //public void onClick(View v) {

                //FirebaseAppHelper fah = new FirebaseAppHelper();
                //Random rand = new Random();
                //int  purchaseConfirmation = rand.nextInt(10000000) + 1;
                //FirebaseDatabase fba = FirebaseDatabase.getInstance();
                //DatabaseReference db = fba.getReference("users").child("DrmYuz6ApObAgBKGjlQM2JvER8t1").child("6buy_history");
                //db.child("" + purchaseConfirmation).child("itemId").setValue("" + desired_string3);
                //db.child("" + purchaseConfirmation).child("itemImage").setValue("" + desired_string4);
                //db.child("" + purchaseConfirmation).child("Brand").setValue("" + desired_string5);
        //    }
        //});

        return v;
    }

    //@Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }
}

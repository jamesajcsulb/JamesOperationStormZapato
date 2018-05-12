package com.example.james.myapplication.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.james.myapplication.R;
import com.example.james.myapplication.models.MyRecyclerViewAdapterShoes;

public class PurchaseStepTwoFragment extends Fragment
{
    private MyRecyclerViewAdapterShoes adapter;
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

        textView = (TextView) v.findViewById(R.id.confirmationCodeHold);
        textView.setText(mConfirmationCode);

        return v;
    }

    //@Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }
}

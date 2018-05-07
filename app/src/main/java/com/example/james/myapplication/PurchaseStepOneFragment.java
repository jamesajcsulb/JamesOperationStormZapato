package com.example.james.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.james.myapplication.models.MyRecyclerViewAdapterShoes;

public class PurchaseStepOneFragment extends Fragment
{
    private MyRecyclerViewAdapterShoes adapter;

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
        final Button button = (Button) v.findViewById(R.id.confirm_purchase);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreateAccountActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}
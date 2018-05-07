package com.example.james.myapplication.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.james.myapplication.R;
import com.example.james.myapplication.models.MyRecyclerViewAdapterShoes;

//import kotlinx.android.synthetic.main.activity_tab.*;
//import kotlinx.android.synthetic.main.create_account.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Random;

public class PurchaseStepOneFragment extends Fragment
{
    private MyRecyclerViewAdapterShoes adapter;
    private String desired_string;
    private String desired_string2;
    private String desired_string3;
    private String desired_string4;
    private String desired_string5;
    private Button button;
    private int purchaseConfirmation;
    private FirebaseDatabase fba;
    private DatabaseReference db;

    private DatabaseReference mDatabase;

    public static PurchaseStepOneFragment newInstance(String param1, String param2) {
        PurchaseStepOneFragment fragment = new PurchaseStepOneFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public PurchaseStepOneFragment(){}

    public static PurchaseStepOneFragment newInstance()
    {
        return new PurchaseStepOneFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_details_purchase_step_one, container, false);

        button = (Button) v.findViewById(R.id.confirm_purchase);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //FirebaseAppHelper fah = new FirebaseAppHelper();
                Random rand = new Random();
                //purchaseConfirmation = rand.nextInt(10000000) + 1;
                desired_string5 = "" + purchaseConfirmation;

                //DatabaseReference db = fba.getReference("users").child("DrmYuz6ApObAgBKGjlQM2JvER8t1").child("6buy_history");
                //db = fba.getReference("users").child("OS8hpHlgYRgTB73CAC4EC7badD82").child("6buy_history");
                //db.child("" + purchaseConfirmation).child("itemId").setValue("" + desired_string2);
                mDatabase.child("users").child("DrmYuz6ApObAgBKGjlQM2JvER8t1").child("6buy_history").
                        child("" + rand).child("qq").setValue("" + "qq");
                //db.child("" + purchaseConfirmation).child("itemImage").setValue("" + desired_string3);
                //db.child("" + purchaseConfirmation).child("Brand").setValue("" + desired_string4);

                //performTransition();
            }
        });

        return v;
    }

}

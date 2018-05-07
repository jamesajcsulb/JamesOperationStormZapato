package com.example.james.myapplication.add;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.james.myapplication.R;

public class ShareFragment3 extends Fragment {

   String TAG = "ShareFragment3";
   CheckBox checkbox;
   Button nextButton;
   TextInputEditText priceInput;
   Bundle extras;
   boolean firmPrice=false;
   int timesChecked=0;
   int price=0;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      extras = getArguments();
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      Log.d(TAG, "onCreateView: Starting.");
      View view = inflater.inflate(R.layout.fragment_share3, container, false);
      checkbox = view.findViewById(R.id.checkbox);
      nextButton = view.findViewById(R.id.next_button3);
      priceInput = view.findViewById(R.id.price_input);

      checkbox.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            timesChecked++;
            if (timesChecked%2==1) firmPrice=true; // if timesChecked is ODD number. 1, 3, etc.
            else firmPrice=false;
         }
      });

      nextButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            price=Integer.parseInt(priceInput.getText().toString());
            if (price==0) {
               Toast.makeText(getContext(),"Set your price!!",Toast.LENGTH_SHORT).show();
            }
            else{
               extras.putString("price",Integer.toString(price));
               extras.putBoolean("firm",firmPrice);
               performTransition();
            }
         }
      });
      return view;
   }

   /**
    * performs transition to next fragment
    * adds arguments to successive fragments through Bundle "extras"
    */
   private void performTransition()
   {
      Fragment fragment = new ReviewFragment();
      fragment.setArguments(extras); // pass Bundle with imageBitmaps and title as args to next fragment
      FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
      fragmentTransaction.replace(R.id.fragment_container, fragment);
      fragmentTransaction.addToBackStack(null);
      fragmentTransaction.commit();
   }

}

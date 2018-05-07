
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
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.james.myapplication.R;


public class ShareFragment2 extends Fragment {


   String TAG = "ShareFragment2";
   SeekBar seek_bar;
   TextInputEditText describeInput;
   Button nextButton;
   Bundle extras;
   int shoeCondition;
   String description; // Shoe description

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      extras = getArguments();
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      Log.d(TAG, "onCreateView: Starting.");
      View view = inflater.inflate(R.layout.fragment_share2, container, false);
      //ButterKnife.bind(this, view);
      nextButton = view.findViewById(R.id.next_button2);
      seek_bar = view.findViewById(R.id.newpost_seekbar);
      describeInput = view.findViewById(R.id.edit_description);
      seekbar();

//       view.findViewById(R.id.cancel_button1).setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View view) {
//             // TODO : Return to new_post step 1
//             //new ShareFragment();
////              mainRef.getRootFragment(2);
//          }
//       });

      setButtonListeners(view, inflater, container);

      return view;
   }

   private void setButtonListeners(View view, LayoutInflater inflater, ViewGroup container) {

      nextButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            description=describeInput.getText().toString();
            if (description.length()==0) {
               Toast.makeText(getContext(),"Description can't be empty!!",Toast.LENGTH_SHORT).show();
            }
            else{
               extras.putString("description",description);
               extras.putString("condition", Integer.toString(shoeCondition));
               performTransition();
            }
         }
      });

   }

   /**
    * performs transition to next fragment
    * adds arguments to successive fragments through Bundle "extras"
    */
   private void performTransition()
   {
      Fragment fragment = new ShareFragment3();
      fragment.setArguments(extras); // pass Bundle with imageBitmaps and title as args to next fragment
      FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
      fragmentTransaction.replace(R.id.fragment_container, fragment);
      fragmentTransaction.addToBackStack(null);
      fragmentTransaction.commit();
   }

   /**
    * This function handles seekbar values
    */

   public void seekbar() {
      //initialize condition values
      int poor = 0;
      int good = 1;
      int veryGood = 2;
      int likeNew = 3;
      int brandNew = 4;

      Log.d("seekbar()", "inside");
      seek_bar.setMax(4);
      seek_bar.setOnSeekBarChangeListener(
              new SeekBar.OnSeekBarChangeListener() {

                 int progress_value;

                 @Override
                 public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    progress_value = progress;
//                        Toast.makeText(getContext(),"SeekBar in progress",Toast.LENGTH_SHORT).show();
                 }

                 @Override
                 public void onStartTrackingTouch(SeekBar seekBar) {
//                        Toast.makeText(getContext(),"SeekBar in StartTracking",Toast.LENGTH_SHORT).show();
                 }


                 @Override
                 public void onStopTrackingTouch(SeekBar seekBar) {
//                   text_view.setText("Covered : " + progress_value + " / " +seek_bar.getMax());
//                   Toast.makeText(getContext(),"SeekBar in StopTracking",Toast.LENGTH_SHORT).show();
                     updateCondition(progress_value);
                 }
              }
      );

   }

   public void updateCondition(int cond) {
      shoeCondition = cond;
   }

}

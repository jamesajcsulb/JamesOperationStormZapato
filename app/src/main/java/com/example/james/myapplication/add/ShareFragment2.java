
package com.example.james.myapplication.add;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.myapplication.R;


public class ShareFragment2 extends Fragment {

   String TAG = "ShareFragment2";
   SeekBar seek_bar;
   TextView text_view;
   Button nextButton;
   int shoeCondition;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      Log.d(TAG, "onCreateView: Starting.");
      View view = inflater.inflate(R.layout.fragment_share2, container, false);
      //ButterKnife.bind(this, view);
      nextButton = view.findViewById(R.id.next_button2);
      seek_bar = view.findViewById(R.id.newpost_seekbar);
      text_view = view.findViewById(R.id.text_seekprogress);
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
            Toast.makeText(getContext(), "shoe condition: "+shoeCondition, Toast.LENGTH_SHORT).show();
         }
      });

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
      text_view.setText("Covered : " + seek_bar.getProgress() + " / " + seek_bar.getMax());
      seek_bar.setMax(4);
      seek_bar.setOnSeekBarChangeListener(
              new SeekBar.OnSeekBarChangeListener() {

                 int progress_value;

                 @Override
                 public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    progress_value = progress;
                    text_view.setText("Covered : " + progress + " / " + seek_bar.getMax());
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

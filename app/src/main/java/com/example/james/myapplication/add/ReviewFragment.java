package com.example.james.myapplication.add;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.james.myapplication.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends Fragment {

   Bundle extras;
   TextView title, description, condition, price;
   private ImageView photo1,photo2,photo3,photo4;
   ArrayList<Bitmap> bitmaps;
   Button updateButton;


   public ReviewFragment() {
      // Required empty public constructor
   }

   @Override
   public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      extras = getArguments();
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
      // Inflate the layout for this fragment
      View view = inflater.inflate(R.layout.fragment_review, container, false);

      photo1 = view.findViewById(R.id.revImage1);
      photo2 = view.findViewById(R.id.revImage2);
      photo3 = view.findViewById(R.id.revImage3);
      photo4 = view.findViewById(R.id.revImage4);

      updateButton = view.findViewById(R.id.updateButton);

      title = view.findViewById(R.id.review_title);

      description = view.findViewById(R.id.review_description);

      price = view.findViewById(R.id.review_price);

      condition = view.findViewById(R.id.review_condition);

      updateButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            update();
         }
      });

      return view;
   }

   public void updateBitmap(){
      for (int i = 1; i < 5; i++) {
         Bitmap bmp = extras.getParcelable("image "+i);
         switch (i){
            case 1: photo1.setImageBitmap(bmp); break;
            case 2: photo2.setImageBitmap(bmp); break;
            case 3: photo3.setImageBitmap(bmp); break;
            case 4: photo4.setImageBitmap(bmp); break;
            default: break;
         }
      }
   }

   public void update(){
      title.setText(extras.getString("title"));
      description.setText(extras.getString("description"));
      price.setText(extras.getString("price"));
      condition.setText(extras.getString("condition"));
      updateBitmap();
   }

}

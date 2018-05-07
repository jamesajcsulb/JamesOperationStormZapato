package com.example.james.myapplication.add;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.james.myapplication.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends Fragment {

   Bundle extras;
   TextView title;
   TextView description;


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

      title = view.findViewById(R.id.review_title);
      title.setText(extras.getString("title"));

      description = view.findViewById(R.id.review_description);
      description.setText(extras.getString("description"));


      return view;
   }

}

package com.example.james.myapplication.add;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.myapplication.Model.SquareImageView;
import com.example.james.myapplication.R;
import com.example.james.myapplication.models.Shoe;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends Fragment {

   FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
   DatabaseReference db_shoeRef = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).child("9sellinventory").child("shoes");
   FirebaseStorage storage = FirebaseStorage.getInstance();
   StorageReference userPhotosRef = storage.getReference().child("ShoeImages/").child(user.getUid()); //TODO: MIGHT CAUSE EXCEPTION BC OF '/'
   String imageURL;
   String staticURL="https://firebasestorage.googleapis.com/v0/b/fragmenttransitionlink.appspot.com/o/ShoeImages%2F21fGOBPcK8ZffFD1NV3OgV8ofLk1%2Fimage%201?alt=media&token=bfc7d0fb-96d7-4bd5-b329-11ac7c148950";

   ArrayList<Bitmap> bitmap_array;
   Bundle extras;
   TextView title, description, condition_textview, price, username, email;
   private SquareImageView photo1,photo2,photo3,photo4;
   ArrayList<Bitmap> bitmaps;
   Button postButton;
   String[] conditions = {"Poor","Good","Very Good","Like New","Brand New"};

   String shoe_name;
   String shoe_price;
   String shoe_condition;
   String shoe_description;


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

      bitmap_array = new ArrayList<>();
      photo1 = view.findViewById(R.id.revImage1);
      photo2 = view.findViewById(R.id.revImage2);
      photo3 = view.findViewById(R.id.revImage3);
      photo4 = view.findViewById(R.id.revImage4);

      username = view.findViewById(R.id.username_textview);
      email = view.findViewById(R.id.email_textview);
      postButton = view.findViewById(R.id.postButton);
      title = view.findViewById(R.id.review_title);
      description = view.findViewById(R.id.review_description);
      price = view.findViewById(R.id.review_price);
      condition_textview = view.findViewById(R.id.review_condition);

      // TODO: Add $ sign to price.
      // TODO: Reformat switch logic for pictures taken with camera vs from gallery
      // TODO: Add final POST button and push to Firebase using correct user reference
      // TODO: Get User name and key for access to Firebase (ask Adrian, James)

      // Extract saved previous Fragment values from Bundle
      String condition_temp = extras.getString("condition");
      int index = Integer.parseInt(condition_temp);
      shoe_condition = conditions[index];
      condition_textview.append(shoe_condition);

      shoe_name = extras.getString("title");
      title.setText(shoe_name);

      shoe_price = extras.getString("price");
      price.append(shoe_price);

      shoe_description = extras.getString("description");
      description.setText(shoe_description);
      updateBitmap();

      username.setText("Agustin Barajas");
      email.setText("agustincards14@gmail.com");

      postButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            post();
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
      Toast.makeText(getContext(), "Your shoe has been posted!", Toast.LENGTH_LONG).show();
      Fragment fragment = new ShareFragment();
      fragment.setArguments(extras); // pass Bundle with imageBitmaps and title as args to next fragment
      FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
      fragmentTransaction.replace(R.id.fragment_container, fragment);
      fragmentTransaction.addToBackStack(null);
      fragmentTransaction.commit();
   }


   /**
    * Assign saved images to imageViews in ReviewFragment
    * If taken with camera, need to rotate it 90degrees
    * TODO: TRY CROPPING !!
    */
   public void updateBitmap(){
      for (int i = 1; i < 5; i++) {
         Bitmap bmp = extras.getParcelable("image "+i);
         bitmap_array.add(bmp);
         switch (i){
            case 1: photo1.setImageBitmap(bmp); break;
            case 2: photo2.setImageBitmap(bmp); break;
            case 3: photo3.setImageBitmap(bmp); break;
            case 4: photo4.setImageBitmap(bmp); break;
            default: break;
         }
      }
   }
   /**
    * method will be changed to carry out pushing into Firebase.
    * Will be called from "POST" button instead of "Update"
    */
   public void post(){
      double price = Double.parseDouble(shoe_price);
      Shoe newShoe = new Shoe(shoe_name, 10.5, price);
      newShoe.description = shoe_description;
      newShoe.shoeCondition = shoe_condition;

      int shoeCount=0;
      StorageReference imageRef;
      for (Bitmap bmp: bitmap_array)
      {
         shoeCount++;
         imageRef = userPhotosRef.child("image "+shoeCount);
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
         byte[] data = baos.toByteArray();
         imageRef.putBytes(data); //upload image to Storage reference

         if (shoeCount==1) { // assign FIRST shoe image URL to the default imageURL for entire Shoe object I am about to upload to DB
            imageURL = imageRef.getDownloadUrl().toString(); // get URL of that image storage Reference
            newShoe.shoeImageUrl = staticURL; // set shoeImageURL
         }
      }
      db_shoeRef.child(user.getUid()).child(db_shoeRef.push().getKey()).setValue(newShoe);
      performTransition();
   }

}


//package com.example.james.myapplication.add;
//
//
//import android.graphics.Bitmap;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.james.myapplication.Model.SquareImageView;
//import com.example.james.myapplication.R;
//import com.example.james.myapplication.models.Shoe;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//
//import java.io.ByteArrayOutputStream;
//import java.util.ArrayList;
//
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class ReviewFragment extends Fragment {
//
//   FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//   DatabaseReference db_shoeRef = FirebaseDatabase.getInstance().getReference().child("shoes");
//   FirebaseStorage storage = FirebaseStorage.getInstance();
//   StorageReference userPhotosRef = storage.getReference().child("ShoeImages/").child(user.getUid()); //TODO: MIGHT CAUSE EXCEPTION BC OF '/'
//   String imageURL;
//   String staticURL="https://firebasestorage.googleapis.com/v0/b/fragmenttransitionlink.appspot.com/o/ShoeImages%2F21fGOBPcK8ZffFD1NV3OgV8ofLk1%2Fimage%201?alt=media&token=bfc7d0fb-96d7-4bd5-b329-11ac7c148950";
//
//   ArrayList<Bitmap> bitmap_array;
//   Bundle extras;
//   TextView title, description, condition_textview, price, username, email;
//   private SquareImageView photo1,photo2,photo3,photo4;
//   ArrayList<Bitmap> bitmaps;
//   Button postButton;
//   String[] conditions = {"Poor","Good","Very Good","Like New","Brand New"};
//
//   String shoe_name;
//   String shoe_price;
//   String shoe_condition;
//   String shoe_description;
//
//
//   public ReviewFragment() {
//      // Required empty public constructor
//   }
//
//   @Override
//   public void onCreate(@Nullable Bundle savedInstanceState) {
//      super.onCreate(savedInstanceState);
//      extras = getArguments();
//   }
//
//   @Override
//   public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                            Bundle savedInstanceState) {
//      // Inflate the layout for this fragment
//      View view = inflater.inflate(R.layout.fragment_review, container, false);
//
//      bitmap_array = new ArrayList<>();
//      photo1 = view.findViewById(R.id.revImage1);
//      photo2 = view.findViewById(R.id.revImage2);
//      photo3 = view.findViewById(R.id.revImage3);
//      photo4 = view.findViewById(R.id.revImage4);
//
//      username = view.findViewById(R.id.username_textview);
//      email = view.findViewById(R.id.email_textview);
//      postButton = view.findViewById(R.id.postButton);
//      title = view.findViewById(R.id.review_title);
//      description = view.findViewById(R.id.review_description);
//      price = view.findViewById(R.id.review_price);
//      condition_textview = view.findViewById(R.id.review_condition);
//
//      // TODO: Add $ sign to price.
//      // TODO: Reformat switch logic for pictures taken with camera vs from gallery
//      // TODO: Add final POST button and push to Firebase using correct user reference
//      // TODO: Get User name and key for access to Firebase (ask Adrian, James)
//
//      // Extract saved previous Fragment values from Bundle
//      String condition_temp = extras.getString("condition");
//      int index = Integer.parseInt(condition_temp);
//      shoe_condition = conditions[index];
//      condition_textview.append(shoe_condition);
//
//      shoe_name = extras.getString("title");
//      title.setText(shoe_name);
//
//      shoe_price = extras.getString("price");
//      price.append(shoe_price);
//
//      shoe_description = extras.getString("description");
//      description.setText(shoe_description);
//      updateBitmap();
//
//      username.setText("Agustin Barajas");
//      email.setText("agustincards14@gmail.com");
//
//      postButton.setOnClickListener(new View.OnClickListener() {
//         @Override
//         public void onClick(View view) {
//            post();
//         }
//      });
//
//      return view;
//   }
//   /**
//    * performs transition to next fragment
//    * adds arguments to successive fragments through Bundle "extras"
//    */
//   private void performTransition()
//   {
//      Toast.makeText(getContext(), "Your shoe has been posted!", Toast.LENGTH_LONG).show();
//      Fragment fragment = new ShareFragment();
//      fragment.setArguments(extras); // pass Bundle with imageBitmaps and title as args to next fragment
//      FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//      fragmentTransaction.replace(R.id.fragment_container, fragment);
//      fragmentTransaction.addToBackStack(null);
//      fragmentTransaction.commit();
//   }
//
//
//   /**
//    * Assign saved images to imageViews in ReviewFragment
//    * If taken with camera, need to rotate it 90degrees
//    * TODO: TRY CROPPING !!
//    */
//   public void updateBitmap(){
//      for (int i = 1; i < 5; i++) {
//         Bitmap bmp = extras.getParcelable("image "+i);
//         bitmap_array.add(bmp);
//         switch (i){
//            case 1: photo1.setImageBitmap(bmp); break;
//            case 2: photo2.setImageBitmap(bmp); break;
//            case 3: photo3.setImageBitmap(bmp); break;
//            case 4: photo4.setImageBitmap(bmp); break;
//            default: break;
//         }
//      }
//   }
//   /**
//    * method will be changed to carry out pushing into Firebase.
//    * Will be called from "POST" button instead of "Update"
//    */
//   public void post(){
//      double price = Double.parseDouble(shoe_price);
//      Shoe newShoe = new Shoe(shoe_name, 10.5, price);
//      newShoe.description = shoe_description;
//      newShoe.shoeCondition = shoe_condition;
//
//      int shoeCount=0;
//      StorageReference imageRef;
//      for (Bitmap bmp: bitmap_array)
//      {
//         shoeCount++;
//         imageRef = userPhotosRef.child("image "+shoeCount);
//         ByteArrayOutputStream baos = new ByteArrayOutputStream();
//         bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//         byte[] data = baos.toByteArray();
//         imageRef.putBytes(data); //upload image to Storage reference
//
//         if (shoeCount==1) { // assign FIRST shoe image URL to the default imageURL for entire Shoe object I am about to upload to DB
//            imageURL = imageRef.getDownloadUrl().toString(); // get URL of that image storage Reference
//            newShoe.shoeImageUrl = staticURL; // set shoeImageURL
//         }
//      }
//      db_shoeRef.child(user.getUid()).child(db_shoeRef.push().getKey()).setValue(newShoe);
//      performTransition();
//   }
//
//}

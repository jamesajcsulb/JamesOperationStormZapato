package com.example.james.myapplication.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.james.myapplication.R;
import com.example.james.myapplication.models.Shoe;

import java.util.ArrayList;

/**
 * Created by agustincards on 3/6/18.
 */

//TODO uncomment all of drawableId

public class ImageAdapter extends BaseAdapter {
   private static final String TAG = "ImageAdapter";

   private ArrayList<Shoe> shoeList;
   private LayoutInflater mInflater;
   private Context thisContext; // IMPORTANT

   public ImageAdapter(Context context, ArrayList<Shoe> list) {
      //Log.d(TAG, "constructor: Starting.");
      shoeList = list;
      mInflater = LayoutInflater.from(context);
      this.thisContext = context;
   }

   @Override
   public int getCount() {
      //System.out.println("shoeList size in getCount(): "+shoeList.size());
      return shoeList.size();
   }

   @Override
   public Shoe getItem(int i) {
      //System.out.println("getting Item: "+i);
      return shoeList.get(i);
   }

   @Override
   public long getItemId(int i) {
      //System.out.println("getting drawable: "+shoeList.get(i).drawableId);
      //return shoeList.get(i).drawableId;
       //TODO remove return 1 once FirebaseManager finish fetching from database
      //TODO I do not know what is getItemId used for. - Adrian
       return 1;
   }


   // create a new ImageView for each item referenced by the Adapter
   @Override
   public View getView(int i, View view, ViewGroup viewGroup) {
      //Log.d(TAG,"inside getView()");
      View v = view;
      ImageView picture;
      TextView name;
      TextView price;

      if (v == null) {
         //v = mInflater.inflate(R.layout.grid_item, viewGroup, false);
         //v.setTag(R.id.picture, v.findViewById(R.id.picture));
         //v.setTag(R.id.text, v.findViewById(R.id.text));
         //v.setTag(R.id.price, v.findViewById(R.id.price));
      }

      //picture = (ImageView) v.getTag(R.id.picture);
      name = (TextView) v.getTag(R.id.text);
      //price = (TextView) v.getTag(R.id.price);

      Shoe shoe = getItem(i);

      //Log.d(TAG,shoe.shoeImageUrl);
      // download image using url with Glide media framework
      //Glide.with(thisContext).load(shoe.shoeImageUrl).into(picture);
      name.setText(shoe.name);
      // convert double to int to strings
      //price.setText("$"+new Integer((int) shoe.price).toString());

      return v;
   }

}

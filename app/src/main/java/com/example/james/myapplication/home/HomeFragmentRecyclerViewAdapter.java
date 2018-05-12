package com.example.james.myapplication.home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.james.myapplication.MainActivity;
import com.example.james.myapplication.R;
import com.example.james.myapplication.models.Shoe;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class HomeFragmentRecyclerViewAdapter extends RecyclerView.Adapter<HomeFragmentRecyclerViewAdapter.ViewHolder> {

    private String[] mData = new String[0];
    private ArrayList<Shoe> mShoe = new ArrayList<Shoe>();
    private LayoutInflater mInflater;
    private Context context;
    private ImageView myImageView;
    private TextView myTextView;
    private TextView myBrandTextView;
    public ImageView recyclerimageView;
    //IMPREF//private Activity ac;
    //IMPREF//private Fragment fra;
    private FragmentManager frasss;
    private RecyclerView myRecView;
    private LinearLayout myLinLay;
    private ImageView mImageView;
    GestureDetector gestureDetector;

    // data is passed into the constructor
    public HomeFragmentRecyclerViewAdapter(Context context, ArrayList<Shoe> shoeshoe, Context con, Activity a, Fragment fragm) {
        this.mInflater = LayoutInflater.from(context);
        this.context = con;
        //IMPREF//this.ac = a;
        //IMPREF//this.fra = fragm;
        this.mShoe = shoeshoe;
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);

        myRecView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mImageView = (ImageView) view.findViewById(R.id.imageButton);
        myTextView = (TextView) view.findViewById(R.id.price);
        myBrandTextView = (TextView) view.findViewById(R.id.title);

        return new ViewHolder(mImageView.getContext(), view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ArrayList<Shoe> arrayList = mShoe;
        Glide.with(mImageView.getContext())
                .load("" + arrayList.get(position).getShoeImageUrl())
                .into(mImageView);

        Double toBeTruncated = new Double(arrayList.get(position).getPrice());
        Double truncatedDouble = BigDecimal.valueOf(toBeTruncated)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();
        String formato = String.format("%.2f",arrayList.get(position).getPrice());

        myTextView.setText("$" + formato);
        myBrandTextView.setText("" + arrayList.get(position).getBrand());
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mShoe.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        public ViewHolder(Context contextq, View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageButton);
            mImageView.setOnClickListener(this);
        }

        // Convenience method for getting data at click position
        String getItem(int id) {
            return mShoe.get(id).toString();
        }

        @Override
        public void onClick(View view) {
            Bundle arguments = new Bundle();
            arguments.putString( "itemName" , "" + mShoe.get(getAdapterPosition()).getName());
            arguments.putString( "itemSellerId" , "" + mShoe.get(getAdapterPosition()).getSellerId());
            arguments.putString( "itemShoeId" , "" + mShoe.get(getAdapterPosition()).getShoeId());
            arguments.putString( "itemBrand" , "" + mShoe.get(getAdapterPosition()).getBrand());
            arguments.putString( "itemGender" , "" + mShoe.get(getAdapterPosition()).getGender());
            arguments.putString( "itemShoeType" , "" + mShoe.get(getAdapterPosition()).getShoeType());
            arguments.putString( "itemImageUrl" , "" + mShoe.get(getAdapterPosition()).getShoeImageUrl());
            arguments.putString( "itemShoeCondition" , "" + mShoe.get(getAdapterPosition()).getShoeCondition());
            arguments.putString( "itemSize" , "" + mShoe.get(getAdapterPosition()).getSize());
            arguments.putString( "itemPrice" , "" + mShoe.get(getAdapterPosition()).getPrice());

            FragmentManager fragmentManagerssss = ((MainActivity)context).getSupportFragmentManager();
            Fragment fragmentt = new HomeItemDetailsFragment();
            fragmentt.setArguments(arguments);
            FragmentTransaction ft = fragmentManagerssss.beginTransaction();
            ft.addToBackStack(null);
            ft.replace(R.id.fragment_container, fragmentt);
            ft.commit();
        }
    }
}

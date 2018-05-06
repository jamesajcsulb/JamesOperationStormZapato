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
import java.util.ArrayList;

public class HomeFragmentRecyclerViewAdapter extends RecyclerView.Adapter<HomeFragmentRecyclerViewAdapter.ViewHolder> {

    private String[] mData = new String[0];
    private ArrayList<Shoe> mShoe = new ArrayList<Shoe>();
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;
    private ImageView myImageView;
    private TextView myTextView;
    private TextView myBrandTextView;
    public ImageView recyclerimageView;
    private Activity ac;
    private Fragment fra;
    private FragmentManager frasss;
    private RecyclerView myRecView;
    private LinearLayout myLinLay;
    private ImageView mImageView;
    GestureDetector gestureDetector;

    // data is passed into the constructor
    public HomeFragmentRecyclerViewAdapter(Context context, ArrayList<Shoe> shoeshoe, Context con, Activity a, Fragment fragm) {
        this.mInflater = LayoutInflater.from(context);
        this.context = con;
        this.ac = a;
        this.fra = fragm;
        this.mShoe = shoeshoe;
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);

        myRecView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mImageView = (ImageView) view.findViewById(R.id.imageButton);

        return new ViewHolder(mImageView.getContext(), view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ArrayList<Shoe> arrayList = mShoe;
        Glide.with(mImageView.getContext())
                .load("" + arrayList.get(position).getShoeImageUrl())
                .into(mImageView);
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

        @Override
        public void onClick(View view) {
            FragmentManager fragmentManagerssss = ((MainActivity)context).getSupportFragmentManager();

            Fragment fragment = new HomeItemDetailsFragment();
            Bundle arguments = new Bundle();
            arguments.putString( "itemId" , "" + getAdapterPosition());
            arguments.putString( "itemPicture" , "" + mShoe.get(getAdapterPosition()).getShoeImageUrl());
            arguments.putString( "itemDescription" , "" + mShoe.get(getAdapterPosition()).getShoePrice()
                    + mShoe.get(getAdapterPosition()).getShoeBrand());
            fragment.setArguments(arguments);
            final FragmentTransaction ft = fragmentManagerssss.beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.commit();
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
            return mShoe.get(id).toString(); //mData[id];
        }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    //parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        //void onItemClick(View view, int position);
    }
}

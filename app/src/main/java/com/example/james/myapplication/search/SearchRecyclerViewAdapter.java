package com.example.james.myapplication.search;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.james.myapplication.MainActivity;
import com.example.james.myapplication.Orders.OrdersFragment;
import com.example.james.myapplication.PurchaseHistory.PurchaseHistoryFragment;
import com.example.james.myapplication.R;
import com.example.james.myapplication.home.HomeItemDetailsFragment;
import com.example.james.myapplication.models.Shoe;

import java.util.ArrayList;

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.ViewHolder> {

    private String[] mData = new String[0];
    private ArrayList<String> mShoe = new ArrayList<String>();
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

    // data is passed into the constructor
    public SearchRecyclerViewAdapter(Context context, ArrayList<String> shoeshoe, Context con, Activity a, Fragment fragm) {
        this.mInflater = LayoutInflater.from(context);
        //this.mData = data;
        this.context = con;
        this.ac = a;
        this.fra = fragm;
        this.mShoe = shoeshoe;
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.search_recyclerview_item, parent, false);

        //recyclerimageView=(ImageView)view.findViewById(R.id.round_price);
        //myImageView=(ImageView)view.findViewById(R.id.recyclerlistitemimageview);
        //myTextView=(TextView)view.findViewById(R.id.textView2);
        myBrandTextView=(TextView)view.findViewById(R.id.brand);

        return new ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ArrayList<String> arrayList = mShoe;
        //Glide.with(myImageView.getContext())
        //        .load(""+arrayList.get(position).getShoeImageUrl())
        //        .into(myImageView);
        //myTextView.setText("" + arrayList.get(position).getShoePrice());
        myBrandTextView.setText("" + arrayList.get(position).toString());
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

        @Override
        public void onClick(View view) {

            if(getAdapterPosition() == 0)
            {
                transitionToSelectedFragment(new OrdersFragment());
            }
            else if(getAdapterPosition() == 1)
            {
                transitionToSelectedFragment(new PurchaseHistoryFragment());
            }

            if (mClickListener != null) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        }

        public void transitionToSelectedFragment(Fragment frg)
        {
            FragmentManager fragmentManagerssss = ((MainActivity)context).getSupportFragmentManager();

            Fragment fragment = frg;//new ProfileFragmentDetails();
            Bundle arguments = new Bundle();
            arguments.putString( "itemId" , "" + getAdapterPosition());
            //arguments.putString( "itemPicture" , "" + mShoe.get(getAdapterPosition()).getShoeImageUrl());
            //arguments.putString( "itemDescription" , "" + mShoe.get(getAdapterPosition()).getShoePrice()
            //        + " " + mShoe.get(getAdapterPosition()).getShoeBrand());
            fragment.setArguments(arguments);
            final FragmentTransaction ft = fragmentManagerssss.beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.commit();
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData[id];
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    private void fragmentJump(int i) {
        fra = new HomeItemDetailsFragment();
        switchContent(R.id.fragment_container, fra);
    }

    public void switchContent(int id, Fragment fragment) {
        if (context == null)
            return;
        if (context instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) context;
            Fragment frag = fragment;
            mainActivity.switchContent(id, frag);
        }
    }
}

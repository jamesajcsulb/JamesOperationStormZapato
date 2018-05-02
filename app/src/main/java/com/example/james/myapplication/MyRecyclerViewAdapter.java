package com.example.james.myapplication;

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
import android.widget.GridView;
import android.widget.ImageView;
//import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;


import java.util.ArrayList;
import java.util.Arrays;

import static java.security.AccessController.getContext;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private String[] mData = new String[0];
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;
    private ImageView myImageView;
    public ImageView recyclerimageView;
    private Activity ac;
    private Fragment fra;
    private FragmentManager frasss;

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, String[] data, Context con, Activity a, Fragment fragm) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = con;
        this.ac = a;
        this.fra = fragm;

    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);


        recyclerimageView=(ImageView)view.findViewById(R.id.recyclerlistitemimageview);
        myImageView=(ImageView)view.findViewById(R.id.recyclerlistitemimageview);

        return new ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //String animal = mData[position];
        //holder.myTextView.setText(animal);
        //HomeFragment hfrag = HomeFragment.newInstance();
        //int currentContainerId = holder.iDetailsContainer.getId();
       // //View iView;
        ////super(itemView);
        ////iView = itemView;
        ////iContainer = (LinearLayout) iView.findViewById(R.id.operation_container);
       //// (LinearLayout) iView.findViewById(R.id.details_container);
        //fragmentManager.beginTransaction().replace(unique_id_hereR.id.container, fragment).commit();
      ////  FragmentManager fragmentManager = ((MainActivity)context).getSupportFragmentManager();
      ////  Fragment hfrag = fragmentManager.findFragmentById(currentContainerId)
        //Fragment fragmentssss = ((MainActivity)context);
        //FragmentManager fragmentManagerssss = ((MainActivity)context).getSupportFragmentManager();

        //frasss = fragmentManagerssss;
        //ImageView
        //myImageView=new ImageView(context);//context);
        //myImageView.setLayoutParams(new RecyclerView.LayoutParams();// .LayoutParams("match_parent", "match_parent"));

        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(mData));
        //Toast.makeText(context,"Hello" + position,Toast.LENGTH_LONG).show();
        //arrayList.add
        Glide.with(myImageView.getContext())
                .load(""+arrayList.get(position))
                .into(myImageView);
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.length;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //TextView myTextView;
        //ImageView myImageView;//
        //ImageView myImageView;//=new ImageView(context);

        ViewHolder(View itemView) {
            super(itemView);
            //myTextView = (TextView) itemView.findViewById(R.id.info_text);
            //myImageView = (ImageView) itemView.findViewById(R.id.info_image);//
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //final Feed item = mFeeds.get(position);

            FragmentManager fragmentManagerssss = ((MainActivity)context).getSupportFragmentManager();


            ///////////////////////////////////
            Fragment fragment = new HomeItemDetailsFragment();
            Bundle arguments = new Bundle();
            arguments.putString( "welcome" , "parent: " + getAdapterPosition());// + "\n view: " + view.toString() + "\n position: " + position + "\n id: " + id);
            fragment.setArguments(arguments);
            final FragmentTransaction ft = fragmentManagerssss.beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.commit();
            /////////////////////////////////////
            /*////////////////////////////////n                 gggggg
            FragmentManager fragmentManagerssss = ((MainActivity)context).getSupportFragmentManager();

            Fragment fragment = new HomeItemDetailsFragment();
            Fragment fragment2 = new HomeFragment();
            Bundle arguments = new Bundle();
            arguments.putString( "welcome" , "parent: " + getAdapterPosition() );//+ parent.toString() + "\n view: " + view.toString() + "\n position: " + position + "\n id: " + id);
            fragment.setArguments(arguments);
            final FragmentTransaction ft = fragmentManagerssss.beginTransaction();//fra.getFragmentManager().beginTransaction();


            ft.replace(R.id.fragment_container, fragment);
            //ft.addToBackStack(null);
            //ft.remove(fragment);
            ft.commit();///////////////////////////////////                        ggggg
   */
//            fragmentJump(getAdapterPosition());
/*
            Fragment fragment = new HomeItemDetailsFragment();
            Fragment fragment2 = new HomeFragment();
            Bundle arguments = new Bundle();
            arguments.putString( "welcome" , "parent: " );//+ parent.toString() + "\n view: " + view.toString() + "\n position: " + position + "\n id: " + id);
            fragment.setArguments(arguments);
            final FragmentTransaction ft = fra.getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.commit();
*/
            // Transition to details fragment
            //getAdapterPosition()
            //Toast.makeText(context,"Hello" + getAdapterPosition(),Toast.LENGTH_LONG).show();

            if (mClickListener != null) {
                mClickListener.onItemClick(view, getAdapterPosition());

                //Toast.makeText(context,"Hello" + getAdapterPosition(),Toast.LENGTH_LONG).show();
            }
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
        //mBundle = new Bundle();
        //mBundle.putParcelable("item_selected_key", mItemSelected);
        //mFragment.setArguments(mBundle);
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

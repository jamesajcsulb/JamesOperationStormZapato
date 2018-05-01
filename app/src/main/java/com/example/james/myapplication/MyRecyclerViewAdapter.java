package com.example.james.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
//import android.widget.TextView;
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

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, String[] data, Context con) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = con;

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
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
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
}

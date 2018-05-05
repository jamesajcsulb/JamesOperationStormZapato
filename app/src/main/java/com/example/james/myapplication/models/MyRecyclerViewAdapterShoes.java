package com.example.james.myapplication.models;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.james.myapplication.MainActivity;
import com.example.james.myapplication.R;
import com.example.james.myapplication.home.HomeItemDetailsFragment;
import java.util.ArrayList;
import android.widget.Toast;

public class MyRecyclerViewAdapterShoes extends RecyclerView.Adapter<MyRecyclerViewAdapterShoes.ViewHolder> {

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
    private ImageView mImageButton;
    GestureDetector gestureDetector;


    // data is passed into the constructor
    public MyRecyclerViewAdapterShoes(Context context, ArrayList<Shoe> shoeshoe, Context con, Activity a, Fragment fragm) {
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
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);

        myRecView = (RecyclerView) view.findViewById(R.id.recycler_view);

        //myLinLay = (LinearLayout) view.findViewById(R.id.recycler_linear_layout);

        //myImageView = (ImageView) view.findViewById(R.id.recyclerlistitemimageview);

        mImageButton = (ImageView) view.findViewById(R.id.imageButton);

        //view.setClickable(true);
        //view.setFocusable(true);





        return new ViewHolder(mImageButton.getContext(), view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ArrayList<Shoe> arrayList = mShoe;
        Glide.with(mImageButton.getContext())
                .load("" + arrayList.get(position).getShoeImageUrl())
                .into(mImageButton);




        //myLinLay.setClickable(true);
        //myLinLay.setEnabled(true);

        //mImageButton.setClickable(true);
        //myImageView.setEnabled(true);

        //myRecView.setClickable(true);
        //myRecView.setEnabled(true);
        //myImageView.setFocusableInTouchMode(true);
        //myImageView.setClickable(true);



        //Toast.makeText(context, "imagebefore", Toast.LENGTH_LONG).show();
        /*myImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "image", Toast.LENGTH_LONG).show();
            }
        });*/
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

            //itemView.setClickable(true);
            //itemView.setFocusable(true);
            //Toast.makeText(itemView.getContext(), "click", Toast.LENGTH_LONG).show();
            //itemView.setOnTouchListener(this);
            itemView.setOnClickListener(this);
        }

        public ViewHolder(Context contextq, View itemView) {
            super(itemView);
            mImageButton = (ImageView) itemView.findViewById(R.id.imageButton);
            mImageButton.setOnClickListener(this);
            //ListView lv = (ListView) itemView.findViewById(R.id.imageButton);
            //lv.setOnItemClickedListener
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(itemView.getContext(), "click" + getAdapterPosition(), Toast.LENGTH_LONG).show();
        }
    }

        //@Override
        //public void onClick(View view) {
            //Toast.makeText(myImageView.getContext(), "hello", Toast.LENGTH_LONG).show();
            //Toast.makeText(context, "hello", Toast.LENGTH_LONG).show();
            //Toast.makeText(view.getContext(), "hello", Toast.LENGTH_LONG).show();
            /*boolean isMoving = false;
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    Toast.makeText(myImageView.getContext(), "move", Toast.LENGTH_LONG).show();
                    break;
                case MotionEvent.ACTION_UP:
                    Toast.makeText(view.getContext(), "up", Toast.LENGTH_LONG).show();
                    break;
                case MotionEvent.ACTION_DOWN:
                    Toast.makeText(view.getContext(), "down", Toast.LENGTH_LONG).show();
                default:
                    Toast.makeText(view.getContext(), "default", Toast.LENGTH_LONG).show();
                    break;
            }
            return true;*/
        //}

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
package com.example.james.myapplication.models;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList arrayList;

    public MyAdapter(Context context, ArrayList arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView=new ImageView(context);

        imageView.setLayoutParams(new GridView.LayoutParams(250, 250));

        Glide.with(imageView.getContext())
                .load(""+arrayList.get(i))
                .into(imageView);

        return imageView;
    }
}
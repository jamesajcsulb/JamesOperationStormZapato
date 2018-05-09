package com.example.james.myapplication.Model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by agustincards on 3/12/18.
 */

@SuppressLint("AppCompatCustomView")
public class SquareImageView extends ImageView {

   int picNum;

   public SquareImageView(Context context) {
      super(context);
   }

   public SquareImageView(Context context, AttributeSet attrs) {
      super(context, attrs);
   }

   public SquareImageView(Context context, AttributeSet attrs, int defStyle) {
      super(context, attrs, defStyle);
   }

   public void setPicNum(int x){picNum=x;}
   public int getPicNum(){return picNum;}

   @Override
   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth()); //Snap to width
   }

}

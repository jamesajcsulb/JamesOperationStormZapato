//package com.example.james.myapplication;
//
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.RecyclerView;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.Toast;
//
//import com.example.james.myapplication.home.HomeItemDetailsFragment;
//
//// stores and recycles views as they are scrolled off screen
//public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnTouchListener {
//
//    MyViewHolder(View itemView) {
//        super(itemView);
//
//
//    }
//
//
//        @Override
//        public boolean onTouch(View view, MotionEvent motionEvent) {
//            Toast.makeText(myImageView.getContext(), "hello", Toast.LENGTH_LONG).show();
//            boolean isMoving = false;
//            switch (motionEvent.getAction()) {
//                case MotionEvent.ACTION_MOVE:
//                    Toast.makeText(myImageView.getContext(), "move", Toast.LENGTH_LONG).show();
//                    break;
//                case MotionEvent.ACTION_UP:
//                    Toast.makeText(view.getContext(), "up", Toast.LENGTH_LONG).show();
//                    break;
//                case MotionEvent.ACTION_DOWN:
//                    Toast.makeText(view.getContext(), "down", Toast.LENGTH_LONG).show();
//                default:
//                    Toast.makeText(view.getContext(), "default", Toast.LENGTH_LONG).show();
//                    break;
//            }
//            return true;
//        }
//
//        @Override
//        public void onClick(View view) {
//
//            switch (view.getId()) {
//                case R.id.recyclerlistitemimageview: {
//                    Toast.makeText(myImageView.getContext(), "rec", Toast.LENGTH_LONG).show();
//                    break;
//                }
//                case R.id.button5: {
//                    Toast.makeText(myImageView.getContext(), "but", Toast.LENGTH_LONG).show();
//                    break;
//                }
//                default:
//                    Toast.makeText(myImageView.getContext(), "def", Toast.LENGTH_LONG).show();
//                    break;
//            }
//    }
//}
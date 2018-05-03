package com.example.james.myapplication.add;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.james.myapplication.MainActivity;
import com.example.james.myapplication.R;

import java.util.ArrayList;

public class ShareFragment extends BaseFragment {

    public static ShareFragment newInstance()
    {
        return new ShareFragment();
    }

    String TAG = "ShareFragment";

    private GridView thumbnail_grid;
    private ImageView photo1;
    private ImageView photo2;
    private ImageView photo3;
    private ImageView photo4;
    int pictureCount = 0;
    int stepCount = 0;
    MainActivity mainRef = new MainActivity();

    private ArrayList<ImageView> shoe_photos;
//    private ImageAdapter mImageAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Starting.");

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_share, container, false);
        view.setBackgroundColor(Color.WHITE);
//        ButterKnife.bind(this, view);

        photo1= view.findViewById(R.id.imageView1);
        photo2= view.findViewById(R.id.imageView2);
        photo3= view.findViewById(R.id.imageView3);
        photo4= view.findViewById(R.id.imageView4);
        // TODO : Make ImageViews of taken pictures deletable when clicked
        // TODO : Save full-size taken pictures into Zapato DB, then access them from Product Detail page
        setButtonListeners(view, inflater, container);

        return view;
    }

    public void setButtonListeners(View view, LayoutInflater inflater, ViewGroup container){

        view.findViewById(R.id.next_button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"go to next",Toast.LENGTH_SHORT).show();
//                changeView(view);
//              ButterKnife.bind(this, view);
            }
        });

        view.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : Return to new_post step 1
                //new ShareFragment();
//              mainRef.getRootFragment(2);
            }
        });

        view.findViewById(R.id.takephoto_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
    }

//    public void changeView(View view){
//        ViewGroup parent = (ViewGroup)view.getParent();
//        int index = parent.indexOfChild(view);
//        parent.removeView(view);
//        view = getLayoutInflater().inflate(R.layout.fragment_share_2, parent, false);
//        parent.addView(view, index);
//
//    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        pictureCount++;
        if (pictureCount>4) {
            getView().findViewById(R.id.takephoto_button).setClickable(false);
            return;
        }

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            switch (pictureCount) {
                case 1: photo1.setImageBitmap(imageBitmap); photo1.setRotation(90);
                    break;
                case 2: photo2.setImageBitmap(imageBitmap); photo2.setRotation(90);
                    break;
                case 3: photo3.setImageBitmap(imageBitmap); photo3.setRotation(90);
                    break;
                case 4: photo4.setImageBitmap(imageBitmap); photo4.setRotation(90);
                    break;
                default:
                    break;
            }
        }
    }
}

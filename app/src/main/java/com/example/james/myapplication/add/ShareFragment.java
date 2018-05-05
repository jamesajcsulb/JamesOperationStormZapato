
package com.example.james.myapplication.add;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.james.myapplication.R;



public class ShareFragment extends Fragment {

    public ShareFragment()
    {
    }
    public static ShareFragment newInstance()
    {
        return new ShareFragment();
    }

    String TAG = "ShareFragment";

    EditText editTitle;
    private ImageView photo1;
    private ImageView photo2;
    private ImageView photo3;
    private ImageView photo4;
    Button takePicButton;
    int pictureCount = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Starting.");

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_share1, container, false);
        //ButterKnife.bind(this, view);

        editTitle = view.findViewById(R.id.textInputLayout2);
        takePicButton = view.findViewById(R.id.takephoto_button);
        photo1= view.findViewById(R.id.imageView);
        photo2= view.findViewById(R.id.imageView2);
        photo3= view.findViewById(R.id.imageView3);
        photo4= view.findViewById(R.id.imageView4);
        // TODO : Make ImageViews of taken pictures deletable when clicked
        // TODO : Save full-size taken pictures into Zapato DB, then access them from Product Detail page
        setButtonListeners(view, inflater, container);

        return view;
    }

    private void performTransition()
    {
        Fragment fragment = new ShareFragment2();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void setButtonListeners(View view, LayoutInflater inflater, ViewGroup container){

        view.findViewById(R.id.next_button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"go to next",Toast.LENGTH_SHORT).show();
//                nextFragment();
                //changeView(view);
//              ButterKnife.bind(this, view);

                performTransition();
            }
        });

        editTitle.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    Toast.makeText(getContext(), editTitle.getText(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });


        takePicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
    }



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


package com.example.james.myapplication.add;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


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
    Button uploadPicButton;
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
        uploadPicButton = view. findViewById(R.id.uploadphoto_button);
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

        uploadPicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchUploadPicIntent();
            }
        });
    }

    // TODO: Crop picture from gallery
    public static final int GET_FROM_GALLERY = 3;
    /**
     * this function runs when User clicks "Upload picture" button
     */
    private void dispatchUploadPicIntent() {
        pictureCount++;
        if (pictureCount>4) {
            getView().findViewById(R.id.takephoto_button).setClickable(false);
            Toast.makeText(getContext(), "Maximum picture upload reached!", Toast.LENGTH_SHORT).show();
            return;
        }

        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File cameraPhotoFile = new File(path, System.currentTimeMillis()+".jpg"); //timestamp makes this file name unique for immediate retrieval
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);

    }


    static final int REQUEST_IMAGE_CAPTURE = 1;
    /**
     * this function runs when User clicks "Take picture" button
     */
    private void dispatchTakePictureIntent() {
        pictureCount++;
        if (pictureCount>4) {
            getView().findViewById(R.id.takephoto_button).setClickable(false);
            Toast.makeText(getContext(), "Maximum picture upload reached!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    /**
     * this function runs when Camera access is granted
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("onActivityResult", "requestCode: "+requestCode);

        if (resultCode!=Activity.RESULT_OK) return;

        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            Log.d("REQUEST_IMAGE_CAPTURE", "requestCode: "+requestCode);
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            setThumbnails(REQUEST_IMAGE_CAPTURE, imageBitmap);
        }
        else if (requestCode == GET_FROM_GALLERY) {
            Log.d("GET_FROM_GALLERY", "requestCode: "+requestCode);
            Uri selectedImage = data.getData();
            Bitmap imageBitmap = null;
            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            setThumbnails(GET_FROM_GALLERY, imageBitmap);
        }
    }

    public void setThumbnails(int code, Bitmap imageBitmap) {
        boolean rotate=true;
        if (code==GET_FROM_GALLERY) {rotate=false;}
        else if (code==REQUEST_IMAGE_CAPTURE) {rotate=true;}

        switch (pictureCount) {
            case 1: photo1.setImageBitmap(imageBitmap);
                    if (rotate) photo1.setRotation(90);
                break;
            case 2: photo2.setImageBitmap(imageBitmap);
                    if (rotate) photo2.setRotation(90);
                break;
            case 3: photo3.setImageBitmap(imageBitmap);
                    if (rotate) photo3.setRotation(90);
                break;
            case 4: photo4.setImageBitmap(imageBitmap);
                    if (rotate) photo4.setRotation(90);
                break;
            default:
                break;
        }

    }

    /**
     * this function does the crop operation.
     */
//    private void performCrop() {
////        Log.d("performCrop", "picUriPATH: "+picUri.getPath());
//        // take care of exceptions
//        try {
//            // call the standard crop action intent (the user device may not
//            // support it)
//            Intent cropIntent = new Intent("com.android.camera.action.CROP");
//            // indicate image type and Uri
//            cropIntent.setDataAndType(picUri, "image/*");
//            // set crop properties
//            cropIntent.putExtra("crop", "true");
//            // indicate aspect of desired crop
//            cropIntent.putExtra("aspectX", 2);
//            cropIntent.putExtra("aspectY", 1);
//            // indicate output X and Y
//            cropIntent.putExtra("outputX", 256);
//            cropIntent.putExtra("outputY", 256);
//            // retrieve data on return
//            cropIntent.putExtra("return-data", true);
//            // start the activity - we handle returning in onActivityResult
//            startActivityForResult(cropIntent, CROP_PIC);
//        }
//        // respond to users whose devices do not support the crop action
//        catch (ActivityNotFoundException e) {
//            Toast toast = Toast.makeText(getContext(), "This device doesn't support the crop action!", Toast.LENGTH_SHORT);
//            toast.show();
//        }
//    }



}

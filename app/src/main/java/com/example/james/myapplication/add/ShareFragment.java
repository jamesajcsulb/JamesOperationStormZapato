
package com.example.james.myapplication.add;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
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
import android.widget.Toast;

import com.example.james.myapplication.Model.SquareImageView;
import com.example.james.myapplication.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


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
    private SquareImageView photo1;
    private SquareImageView photo2;
    private SquareImageView photo3;
    private SquareImageView photo4;
    ArrayList<Bitmap> bitmaps;
    Button takePicButton;
    Button uploadPicButton;
    int pictureCount = 0;
    DialogInterface.OnClickListener dialogClickListener;
    String title; // Shoe title
    Bundle extras = new Bundle();

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

        title="";
        editTitle = view.findViewById(R.id.edit_title);
        uploadPicButton = view. findViewById(R.id.uploadphoto_button);
        takePicButton = view.findViewById(R.id.takephoto_button);
        photo1= view.findViewById(R.id.imageView);
        photo2= view.findViewById(R.id.imageView2);
        photo3= view.findViewById(R.id.imageView3);
        photo4= view.findViewById(R.id.imageView4);
        bitmaps = new ArrayList<>();
        // TODO : Make ImageViews of taken pictures deletable when clicked
        // TODO : Save full-size taken pictures into Zapato DB, then access them from Product Detail page
        setButtonListeners(view);

        return view;
    }

    private void performTransition()
    {
        Fragment fragment = new ShareFragment2();
        fragment.setArguments(extras); // pass Bundle with imageBitmaps and title as args to next fragment
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void setButtonListeners(View view){

        view.findViewById(R.id.next_button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title=editTitle.getText().toString();
                if (title.length()==0) {
                    Toast.makeText(getContext(),"Title can't be empty!!",Toast.LENGTH_SHORT).show();
                }
                else if (pictureCount<4){
                    Toast.makeText(getContext(),"4 images required!!",Toast.LENGTH_SHORT).show();
                }
                else { // pass Bitmap array to next fragment
                    extras.putString("title",title);
                    performTransition();
                }
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

        photo1.setOnClickListener(new View.OnClickListener() {
//            AlertDialog.Builder builder;
            @Override
            public void onClick(View view) {
                deleteImage(photo1);
//                builder = new AlertDialog.Builder(getContext());
//                builder.setMessage("Delete your picture? ").setPositiveButton("Yes", dialogClickListener)
//                        .setNegativeButton("No", dialogClickListener).show();
            }
        });
        photo2.setOnClickListener(new View.OnClickListener() {
//            AlertDialog.Builder builder;
            @Override
            public void onClick(View view) {
                deleteImage(photo2);
//                builder = new AlertDialog.Builder(getContext());
//                builder.setMessage("Delete your picture? ").setPositiveButton("Yes", dialogClickListener)
//                        .setNegativeButton("No", dialogClickListener).show();
            }
        });
        photo3.setOnClickListener(new View.OnClickListener() {
//            AlertDialog.Builder builder;
            @Override
            public void onClick(View view) {
                deleteImage(photo3);
//                builder = new AlertDialog.Builder(getContext());
//                builder.setMessage("Delete your picture? ").setPositiveButton("Yes", dialogClickListener)
//                        .setNegativeButton("No", dialogClickListener).show();
            }
        });
        photo4.setOnClickListener(new View.OnClickListener() {
//            AlertDialog.Builder builder;
            @Override
            public void onClick(View view) {
                deleteImage(photo4);
//                builder = new AlertDialog.Builder(getContext());
//                builder.setMessage("Delete your picture? ").setPositiveButton("Yes", dialogClickListener)
//                        .setNegativeButton("No", dialogClickListener).show();
            }
        });

    }

    public void deleteImage(final SquareImageView image){
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Delete your picture? ").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

        dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        pictureCount--;
                        image.setImageResource(R.drawable.empty_image_icon);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };


    }

    // TODO: Crop picture from gallery
    public static final int GET_FROM_GALLERY = 3;
    /**
     * this function runs when User clicks "Upload picture" button
     */
    private void dispatchUploadPicIntent() {
        if (pictureCount==4) {
            getView().findViewById(R.id.takephoto_button).setClickable(false);
            Toast.makeText(getContext(), "Maximum picture upload reached!", Toast.LENGTH_SHORT).show();
            return;
        }

//      File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//      File cameraPhotoFile = new File(path, System.currentTimeMillis()+".jpg"); //timestamp makes this file name unique for immediate retrieval
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);

    }


    static final int REQUEST_IMAGE_CAPTURE = 1;
    /**
     * this function runs when User clicks "Take picture" button
     */
    private void dispatchTakePictureIntent() {
        if (pictureCount==4) {
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
            case 0: photo1.setImageBitmap(imageBitmap);
                    if (rotate) photo1.setRotation(90);
                break;

            case 1: photo2.setImageBitmap(imageBitmap);
                    if (rotate) photo2.setRotation(90);
                break;

            case 2: photo3.setImageBitmap(imageBitmap);
                    if (rotate) photo3.setRotation(90);
                break;

            case 3: photo4.setImageBitmap(imageBitmap);
                    if (rotate) photo4.setRotation(90);
                break;

            default:
                break;
        }
        pictureCount++;
        extras.putParcelable("image "+pictureCount,imageBitmap);

    }

}

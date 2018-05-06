package com.example.james.myapplication.search;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.myapplication.R;
import com.example.james.myapplication.home.HomeFragment;
import com.example.james.myapplication.models.ImageAdapter;
import com.example.james.myapplication.models.Shoe;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class SearchFragment extends Fragment {

    private TextView voiceInput;
    private TextView speakButton;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    private GridView gridView = null;
    private ArrayList<Shoe> mShoeList = null;
    private ImageAdapter mImageAdapter = null;
    private ArrayList<String> shoearraypass = null;
    private ArrayList<Shoe> classshoe = null;
    private ArrayAdapter<Shoe> adap = null;
    private ImageAdapter imageViewing = null;
    private RecyclerView recycled = null;
    private SearchRecyclerViewAdapter adaptershoe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        voiceInput = (TextView) v.findViewById(R.id.voiceInput);
        speakButton = (Button) v.findViewById(R.id.btnSpeak2);

        //Toast.makeText(getContext(), "whatsup" , Toast.LENGTH_SHORT).show();

        speakButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                askSpeechInput();
            }
        });



        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("search");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                shoearraypass = new ArrayList<String>();
                //classshoe = new ArrayList<Shoe>();
                //String ds = dataSnapshot.child("DrmYuz6ApObAgBKGjlQM2JvER8t1").getKey();
                for (DataSnapshot snaparray : dataSnapshot.getChildren()) {
                    //for(DataSnapshot s2 : snaparray.child("6buy_history").getChildren()) {
                    //shoearraypass.add("" + s2.getKey());
                    //classshoe.add("");
                    shoearraypass.add(snaparray.getValue().toString());
                    //Toast.makeText(getContext(),"Peta: " + snaparray.getValue(), Toast.LENGTH_SHORT).show();
                    //}
                }

                RecyclerView recyclerView2 = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
                int numberOfColumns2 = 1;
                recyclerView2.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns2));
                adaptershoe = new SearchRecyclerViewAdapter(getContext(), shoearraypass, getContext(), getActivity(), new HomeFragment() );
                recyclerView2.setAdapter(adaptershoe);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        return v;
    }

    private void askSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "speak something");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //voiceInput.setText(result.get(0));
                    Toast.makeText(getContext(), "" + result.toString(), Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
}
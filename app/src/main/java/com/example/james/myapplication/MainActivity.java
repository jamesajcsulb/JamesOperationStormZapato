package com.example.james.myapplication;

import android.content.ActivityNotFoundException;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.james.myapplication.add.ShareFragment;
import com.example.james.myapplication.add.ShareFragment;
import com.example.james.myapplication.favorite.FavoriteFragment;
import com.example.james.myapplication.home.HomeFragment;
import com.example.james.myapplication.profile.ProfileFragment;
import com.example.james.myapplication.search.SearchFragment;
import com.example.james.myapplication.search.fragment_speech;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextView mTextMessage;
    private FragmentManager mFragmentManager;
//
    private TextToSpeech tts;
    private Button btnSpeak;
    private EditText txtText;
//
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Fragment initialFragment = loadInitialFragment();
                    return true;
                case R.id.navigation_dashboard:
                    //initialFragment = SearchFragment.newInstance();
                    Fragment initialFragment0 = new fragment_speech();
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, initialFragment0);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_notifications:
                    Fragment initialFragment2 = new ShareFragment();
                    fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, initialFragment2);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_notifications2:
                    Fragment initialFragment3 = FavoriteFragment.newInstance();
                    fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, initialFragment3);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_notifications3:
                    Fragment initialFragment4 = ProfileFragment.newInstance();
                    fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, initialFragment4);
                    fragmentTransaction.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mFragmentManager = getSupportFragmentManager();

        loadInitialFragment();

        ///////////////////////////////
        //tts = new TextToSpeech(this, this);
        ////btnSpeak = (Button) findViewById(R.id.btnSpeak);
       /// txtText = (EditText) findViewById(R.id.txtText);
        // button on click event
        //btnSpeak.setOnClickListener(new View.OnClickListener() {

            //@Override
            //public void onClick(View arg0) {
            //    speakOut();
            //}

        //});
    //}
    ///////////////////
    }

    private Fragment loadInitialFragment()
    {
        Fragment initialFragment = HomeFragment.newInstance();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, initialFragment);
        fragmentTransaction.commit();
        return initialFragment;
    }

    public void switchContent(int id, Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(id, fragment, fragment.toString());
        ft.addToBackStack(null);
        ft.commit();
    }





//////////////////////
    @Override
    public void onDestroy() {
        // Don't forget to shutdown!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        // TODO Auto-generated method stub

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            // tts.setPitch(5); // set pitch level

            // tts.setSpeechRate(2); // set speech speed rate

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Language is not supported");
            } else {
                btnSpeak.setEnabled(true);
                speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed");
        }

    }

    private void speakOut() {

        String text = txtText.getText().toString();

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}

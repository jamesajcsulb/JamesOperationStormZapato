package com.example.james.myapplication;

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

import com.example.james.myapplication.add.ShareFragment;
//import com.example.james.myapplication.stripe.ShareFragment;
import com.example.james.myapplication.favorite.FavoriteFragment;
import com.example.james.myapplication.home.HomeFragment;
import com.example.james.myapplication.profile.ProfileFragment;
import com.example.james.myapplication.search.SearchFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.os.AsyncTask;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private TextView mTextMessage;
    private FragmentManager mFragmentManager;
    private TextToSpeech tts;
    private Button btnSpeak;
    private EditText txtText;
    private String string = "";
    private Boolean isloading = false;
    private String tokenIdExtract = "";
    private String bulkToken = "";
    private FirebaseUser fba = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    private String stripetestvar = "";
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setTitle("Zapato");
                    Fragment initialFragment = loadInitialFragment();
                    return true;
                case R.id.navigation_search:
                    setTitle("Discover");
                    Fragment initialFragment0 = new SearchFragment();
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, initialFragment0);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_share:
                    setTitle("Post your shoe");
                    Fragment initialFragment2 = new ShareFragment();
                    fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, initialFragment2);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_heart:
                    setTitle("My Favorites");
                    Fragment initialFragment3 = FavoriteFragment.newInstance();
                    fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, initialFragment3);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_profile:
                    setTitle("My Profile");
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

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_bar);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mFragmentManager = getSupportFragmentManager();

        loadInitialFragment();
        restoreAccountDatabaseStructure();
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

    private void restoreAccountDatabaseStructure()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        // Stripe register account
        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                FirebaseUser user2 = FirebaseAuth.getInstance().getCurrentUser();
                StripeFunction stripeFunction = new StripeFunction();//user2.getEmail(),"");

                string = stripeFunction.createAccount(user2.getEmail(),"");

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                FirebaseUser fba = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference db = FirebaseDatabase.getInstance().getReference();

                db.child("users").child(user.getUid()).child("stripe_customer_id").setValue(string);


                return null;
            }
        };
        asyncTask.execute();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("users").child(user.getUid()).child("1_0name").setValue("myvalue");
        db.child("users").child(user.getUid()).child("1_1birthday").setValue("myvalue");
        db.child("users").child(user.getUid()).child("1_2gender").setValue("myvalue");
        db.child("users").child(user.getUid()).child("2email_address").setValue("myvalue");
        db.child("users").child(user.getUid()).child("3_0phone_number").setValue("myvalue");
        db.child("users").child(user.getUid()).child("3_1paymenttype(s)").child("Paypal").setValue("default");
        db.child("users").child(user.getUid()).child("3_1paymenttype(s)").child("Zelle").setValue("myvalue");
        db.child("users").child(user.getUid()).child("3_1paymenttype(s)").child("Venmo").setValue("myvalue");
        db.child("users").child(user.getUid()).child("4shipping_address").child("location1").child("addrline1").setValue("Student Gang, James");
        db.child("users").child(user.getUid()).child("4shipping_address").child("location1").child("addrline2").setValue("CECS491B: Seat Random");
        db.child("users").child(user.getUid()).child("4shipping_address").child("location1").child("addrline3").setValue("1250 Bellflower Blvd");
        db.child("users").child(user.getUid()).child("4shipping_address").child("location1").child("state").setValue("CA");
        db.child("users").child(user.getUid()).child("4shipping_address").child("location1").child("city").setValue("Long Beach");
        db.child("users").child(user.getUid()).child("4shipping_address").child("location1").child("postalcode").setValue("90840");
        db.child("users").child(user.getUid()).child("4shipping_address").child("location2").child("addrline1").setValue("1250 Bellflower Blvd");
        db.child("users").child(user.getUid()).child("4shipping_address").child("location2").child("addrline2").setValue("null");
        db.child("users").child(user.getUid()).child("4shipping_address").child("location2").child("addrline3").setValue("null");
        db.child("users").child(user.getUid()).child("4shipping_address").child("location2").child("state").setValue("CA");
        db.child("users").child(user.getUid()).child("4shipping_address").child("location2").child("city").setValue("Long Beach");
        db.child("users").child(user.getUid()).child("4shipping_address").child("location2").child("postalcode").setValue("90840");
        db.child("users").child(user.getUid()).child("4shipping_address").child("location3").child("addrline1").setValue("Gang, James A210");
        db.child("users").child(user.getUid()).child("4shipping_address").child("location3").child("addrline2").setValue("CSULB Dorm");
        db.child("users").child(user.getUid()).child("4shipping_address").child("location3").child("addrline3").setValue("1250 Bellflower Blvd");
        db.child("users").child(user.getUid()).child("4shipping_address").child("location3").child("state").setValue("CA");
        db.child("users").child(user.getUid()).child("4shipping_address").child("location3").child("city").setValue("Long Beach");
        db.child("users").child(user.getUid()).child("4shipping_address").child("location3").child("postalcode").setValue("90840");
        db.child("users").child(user.getUid()).child("5billing_address").child("location1").child("addrline1").setValue("Student Gang, James");
        db.child("users").child(user.getUid()).child("5billing_address").child("location1").child("addrline2").setValue("CECS491B: Seat Random");
        db.child("users").child(user.getUid()).child("5billing_address").child("location1").child("addrline3").setValue("1250 Bellflower Blvd");
        db.child("users").child(user.getUid()).child("5billing_address").child("location1").child("state").setValue("CA");
        db.child("users").child(user.getUid()).child("5billing_address").child("location1").child("city").setValue("Long Beach");
        db.child("users").child(user.getUid()).child("5billing_address").child("location1").child("postalcode").setValue("90840");
        db.child("users").child(user.getUid()).child("5billing_address").child("location2").child("addrline1").setValue("1250 Bellflower Blvd");
        db.child("users").child(user.getUid()).child("5billing_address").child("location2").child("addrline2").setValue("null");
        db.child("users").child(user.getUid()).child("5billing_address").child("location2").child("addrline3").setValue("null");
        db.child("users").child(user.getUid()).child("5billing_address").child("location2").child("state").setValue("CA");
        db.child("users").child(user.getUid()).child("5billing_address").child("location2").child("city").setValue("Long Beach");
        db.child("users").child(user.getUid()).child("5billing_address").child("location2").child("postalcode").setValue("90840");
        db.child("users").child(user.getUid()).child("5billing_address").child("location3").child("addrline1").setValue("Gang, James A210");
        db.child("users").child(user.getUid()).child("5billing_address").child("location3").child("addrline2").setValue("CSULB Dorm");
        db.child("users").child(user.getUid()).child("5billing_address").child("location3").child("addrline3").setValue("1250 Bellflower Blvd");
        db.child("users").child(user.getUid()).child("5billing_address").child("location3").child("state").setValue("CA");
        db.child("users").child(user.getUid()).child("5billing_address").child("location3").child("city").setValue("Long Beach");
        db.child("users").child(user.getUid()).child("5billing_address").child("location3").child("postalcode").setValue("90840");
        db.child("users").child(user.getUid()).child("6buy_history").child("buy1").setValue("item_bought");
        db.child("users").child(user.getUid()).child("6buy_history").child("buy2").setValue("item_bought");
        db.child("users").child(user.getUid()).child("6buy_history").child("buy3").setValue("item_bought");
        db.child("users").child(user.getUid()).child("6buy_history").child("buy4").setValue("item_bought");
        db.child("users").child(user.getUid()).child("7sell_history").child("sell1").setValue("item_sold");
        db.child("users").child(user.getUid()).child("7sell_history").child("sell2").setValue("item_sold");
        db.child("users").child(user.getUid()).child("7sell_history").child("sell3").setValue("item_sold");
        db.child("users").child(user.getUid()).child("7sell_history").child("sell4").setValue("item_sold");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival1").child("From").setValue("message transmitter");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival1").child("To").setValue("message receipient(s)");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival1").child("Subject").setValue("message to transmit title");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival1").child("Body").setValue("message to transmit body");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival1").child("stored").setValue("inbox");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival1").child("starred").setValue("f");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival1").child("important").setValue("f");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival1").child("ismarkedjunk").setValue("f");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival2").child("From").setValue("message transmitter");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival2").child("To").setValue("message receipient(s)");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival2").child("Subject").setValue("message to transmit title");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival2").child("Body").setValue("message to transmit body");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival2").child("stored").setValue("inbox");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival2").child("starred").setValue("f");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival2").child("important").setValue("f");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival2").child("ismarkedjunk").setValue("f");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival3").child("From").setValue("message transmitter");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival3").child("To").setValue("message receipient(s)");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival3").child("Subject").setValue("message to transmit title");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival3").child("Body").setValue("message to transmit body");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival3").child("stored").setValue("inbox");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival3").child("starred").setValue("f");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival3").child("important").setValue("f");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival3").child("ismarkedjunk").setValue("f");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival4").child("From").setValue("message transmitter");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival4").child("To").setValue("message receipient(s)");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival4").child("Subject").setValue("message to transmit title");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival4").child("Body").setValue("message to transmit body");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival4").child("stored").setValue("inbox");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival4").child("starred").setValue("f");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival4").child("important").setValue("f");
        db.child("users").child(user.getUid()).child("8conversation").child("1emailtype").child("timeofarrival4").child("ismarkedjunk").setValue("f");
        db.child("users").child(user.getUid()).child("8conversation").child("2chattype-probablyFirebaseCloudMessagingbutforconceptputtingplaceholderhere").child("toreceipient1").child("received1").setValue("r");
        db.child("users").child(user.getUid()).child("8conversation").child("2chattype-probablyFirebaseCloudMessagingbutforconceptputtingplaceholderhere").child("toreceipient1").child("sent1").setValue("s");
        db.child("users").child(user.getUid()).child("8conversation").child("2chattype-probablyFirebaseCloudMessagingbutforconceptputtingplaceholderhere").child("toreceipient2").child("received2").setValue("r");
        db.child("users").child(user.getUid()).child("8conversation").child("2chattype-probablyFirebaseCloudMessagingbutforconceptputtingplaceholderhere").child("toreceipient2").child("sent2").setValue("s");
        db.child("users").child(user.getUid()).child("8conversation").child("2chattype-probablyFirebaseCloudMessagingbutforconceptputtingplaceholderhere").child("toreceipient3").child("received3").setValue("r");
        db.child("users").child(user.getUid()).child("8conversation").child("2chattype-probablyFirebaseCloudMessagingbutforconceptputtingplaceholderhere").child("toreceipient3").child("sent3").setValue("s");
        //Shoe myShoe = new Shoe("Nike Tiempo", 11.5, 130.0);
        //db.child("shoes").child(user.getUid()).child(db.push().getKey()).setValue(myShoe);
        //db.child("shoes") = database reference -> mkdir child "shoes" ->
        //user.getUid() = "unique user id" from "FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser()"
        //db.push().getKey() = create ref to auto-generated child location, then getKey() in String format
        //setValue(myShoe) = upload Shoe object onto DB at that location. All Shoe values will be separate attributes
    }

    private void speechTest()
    {
        tts = new TextToSpeech(this, this);
        btnSpeak = (Button) findViewById(R.id.btnSpeak);
        txtText = (EditText) findViewById(R.id.txtText);
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                speakOut();
            }
        });
    }

    private void speakOut() {
        String text = txtText.getText().toString();
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

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
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);
            tts.setSpeechRate(2); // set speech speed rate

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
}

package com.example.james.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.james.myapplication.home.HomeFragment;
import com.example.james.myapplication.profile.ProfileFragment;
import com.example.james.myapplication.search.SearchFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private FragmentManager mFragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    loadInitialFragment();
                    //mTextMessage.setText(R.string.title_home);

                    //Intent sellIntent = new Intent(getApplicationContext(), SellActivity.class);
                    //sellIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    //startActivity(sellIntent);
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);

                    Fragment initialFragment = SearchFragment.newInstance();
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, initialFragment);
                    fragmentTransaction.commit();

                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);

                    initialFragment = ProfileFragment.newInstance();
                    fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, initialFragment);
                    fragmentTransaction.commit();

                    return true;
                case R.id.navigation_notifications2:
                    //mTextMessage.setText(R.string.title_notifications);

                    initialFragment = ProfileFragment.newInstance();
                    fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, initialFragment);
                    fragmentTransaction.commit();

                    return true;
                case R.id.navigation_notifications3:
                    //mTextMessage.setText(R.string.title_notifications);

                    initialFragment = ProfileFragment.newInstance();
                    fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, initialFragment);
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

        //getIntent().setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);// Intent.FLAG_ACTIVITY_CLEAR_STACK);
    }

    private void loadInitialFragment()
    {
        Fragment initialFragment = HomeFragment.newInstance();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, initialFragment);
        fragmentTransaction.commit();
    }


    public void switchContent(int id, Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(id, fragment, fragment.toString());
        ft.addToBackStack(null);
        ft.commit();
    }

}

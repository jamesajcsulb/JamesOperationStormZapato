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
import com.example.james.myapplication.add.ShareFragment;
import com.example.james.myapplication.favorite.FavoriteFragment;
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
                    Fragment initialFragment = loadInitialFragment();
                    return true;
                case R.id.navigation_dashboard:
                    initialFragment = SearchFragment.newInstance();
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, initialFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_notifications:
                    initialFragment = ShareFragment.newInstance();
                    fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, initialFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_notifications2:
                    initialFragment = FavoriteFragment.newInstance();
                    fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, initialFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_notifications3:
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
}

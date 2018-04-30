package com.example.james.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeFragment extends Fragment
{
    public HomeFragment()
    {
    }

    public static HomeFragment newInstance()
    {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState)
    {
/*        ////////////////////////////////////////////////////
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        //////////////////////////////////////////////////////////////////
        */
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        if (v != null)
        {
        }

        ArrayList<String> alist = new ArrayList<String>();
        alist.add("jamesOS");
        alist.add("AnJinOS");
        alist.add("GangOS");
        alist.add("Android");
        alist.add("iPhone");
        alist.add("WindowsMobile");
        alist.add("Blackberry");
        alist.add("WebOS");
        alist.add("Ubuntu");
        alist.add("Windows7");
        alist.add("Max OS X");
        alist.add("Linux");
        alist.add("OS/2");

        ArrayAdapter<String> adapterr = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, alist);

        ListView mListView = (ListView) getActivity().findViewById(R.id.staticListView);
        mListView.setAdapter(adapterr);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //////////////////////////////////////////////////////////////////////////
                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("message");

                myRef.setValue("Detail for item " + id + " triggered");
                //////////////////////////////////////////////////////////////////////////

                Fragment fragment = new HomeItemDetailsFragment();
                Bundle arguments = new Bundle();
                arguments.putString( "welcome" , "parent: " + parent.toString() + "\n view: " + view.toString() + "\n position: " + position + "\n id: " + id);
                fragment.setArguments(arguments);
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.commit();
            }
        });

        return v;
    }

    private void performTransition()
    {
        Fragment fragment = new HomeItemDetailsFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

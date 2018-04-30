package com.example.james.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment
{
    private GridView gridView = null;
    private ArrayList<Shoe> mShoeList = null;
    private ImageAdapter mImageAdapter = null;
    private ArrayList<String> shoearraypass = null;
    private ArrayAdapter<Shoe> adap = null;

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

        //////////////////////////////////////////////////////////////////////////
        //var thisView = inflater.inflate(R.layout.product_browse, container, false);

        //fetch Buy Now function
        ////FirebaseManager().fetch(FirebaseManager().buynow_ref, {
                ////mShoeList = it;
                //parse mShoeList data into ImageAdapter
                ////mImageAdapter = ImageAdapter(getContext(), mShoeList);

        ////gridView = v.findViewById(R.id.product_browse);// .findViewById<View>(R.id.product_browse) as GridView
        //gridView!!.adapter = mImageAdapter
        //optional
        //gridView!!.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id -> Toast.makeText(activity, "" + position, Toast.LENGTH_SHORT).show() }
        //gridView!!.onItemClickListener = AdapterView.OnItemClickListener {
        //parent, view, position, id ->
                //Toast.makeText(activity, "" + mShoeList!![position].name.toString(), Toast.LENGTH_SHORT).show()
        //SegueManager().passDatatoProductDetail(this@HomeFragment.context!!, mShoeList!![position])
    ////}
       //// });
        //////////////////////////////////////////////////////////////////////////
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("shoes");
        //child("b22mhFiqHuVR9vwowQyQjD4720Q2").child("L9FNpPzi2FouktspF3G")
        //child("b22mhFiqHuVR9vwowQyQjD4720Q2").child("L9FNpPzi2FouktspF3G")

        //myRef.setValue("Detail for item triggered");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                shoearraypass = new ArrayList<String>();
                String ds = dataSnapshot.child("b22mhFiqHuVR9vwowQyQjD4720Q2").getKey();
                for (DataSnapshot snaparray : dataSnapshot.child("b22mhFiqHuVR9vwowQyQjD4720Q2").getChildren())
                    //for (DataSnapshot snaparray : dataSnapshot.child("b22mhFiqHuVR9vwowQyQjD4720Q2").child("-L9FNpPzi2FouktspF3G").getChildren())
                {
                    shoearraypass.add("" + snaparray.getValue());
                    //Toast.makeText(getContext(),"Zeta: " + snaparray,Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(getContext(),"Zeta: " + shoearraypass.toString(),Toast.LENGTH_SHORT).show();

                ArrayAdapter<Shoe> adap = new ArrayAdapter(getActivity(),
                        android.R.layout.simple_list_item_1, shoearraypass);

                ListView mListView = (ListView) getActivity().findViewById(R.id.staticListView);
                mListView.setAdapter(adap);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
                //Toast.makeText(getContext(),"error commander: " + error.toException(),Toast.LENGTH_SHORT).show();

            }
        });
        //////////////////////////////////////////////////////////////////////////

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

        //ArrayAdapter<String> adapterr = new ArrayAdapter(getActivity(),
        //        android.R.layout.simple_list_item_1, alist);

        ///////shoearraypass
        //ArrayList<Shoe> shoelist = new ArrayList<Shoe>();
        //shoelist.addAll(shoearraypass);
        ArrayAdapter<Shoe> adapterr = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, alist);


        //ListView mListView = (ListView) getActivity().findViewById(R.id.staticListView);
        //mListView.setAdapter(adapterr);
        //mListView.setAdapter(adap);
        /*mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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
        });*/

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

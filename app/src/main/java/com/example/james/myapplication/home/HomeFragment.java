package com.example.james.myapplication.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.example.james.myapplication.models.ImageAdapter;
import com.example.james.myapplication.models.MyAdapter;
import com.example.james.myapplication.models.MyRecyclerViewAdapter;
import com.example.james.myapplication.R;
import com.example.james.myapplication.models.MyRecyclerViewAdapterShoes;
import com.example.james.myapplication.models.Shoe;
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
    private ArrayList<Shoe> classshoe = null;
    private ArrayAdapter<Shoe> adap = null;
    private ImageAdapter imageViewing = null;
    private RecyclerView recycled = null;
    //private MyRecyclerViewAdapter adapter;
    private MyRecyclerViewAdapter adapter;
    private MyRecyclerViewAdapterShoes adaptershoe;

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
        View v = inflater.inflate(R.layout.fragment_home2, container, false);
        if (v != null)
        {
        }
        //////////////////////////////////////////////////////////////////////////
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("shoes");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                shoearraypass = new ArrayList<String>();
                classshoe = new ArrayList<Shoe>();
                String ds = dataSnapshot.child("b22mhFiqHuVR9vwowQyQjD4720Q2").getKey();
                for (DataSnapshot snaparray : dataSnapshot.child("b22mhFiqHuVR9vwowQyQjD4720Q2").getChildren()) {
                    //for() {
                        shoearraypass.add("" + snaparray.child("shoeImageUrl").getValue());
                        classshoe.add(new Shoe(snaparray));
                        //Toast.makeText(getContext(),"Zeta: " + snaparray,Toast.LENGTH_SHORT).show();
                    //}
                }
                    //for (DataSnapshot snaparray : dataSnapshot.child("b22mhFiqHuVR9vwowQyQjD4720Q2").getChildren())
                //{
                //    shoearraypass.add("" + snaparray.child("shoeImageUrl").getValue());
                //    classshoe.add(new Shoe(snaparray));
                    //Toast.makeText(getContext(),"Zeta: " + snaparray,Toast.LENGTH_SHORT).show();
                //}

                //MyAdapter myadapter=new MyAdapter(getActivity(),shoearraypass);
                String[] arrayshoeconv = shoearraypass.toArray(new String[0]);

                //RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.rvNumbers);
                //int numberOfColumns = 2;
                //recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));
                //adapter = new MyRecyclerViewAdapter(getContext(), arrayshoeconv, getContext(), getActivity(), new HomeFragment() );
                //recyclerView.setAdapter(adapter);

                RecyclerView recyclerView2 = (RecyclerView) getActivity().findViewById(R.id.rvNumbers);
                int numberOfColumns2 = 2;
                recyclerView2.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns2));
                adaptershoe = new MyRecyclerViewAdapterShoes(getContext(), classshoe, getContext(), getActivity(), new HomeFragment() );
                recyclerView2.setAdapter(adaptershoe);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                //
            }
        });
        //////////////////////////////////////////////////////////////////////////

        /*
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

        ArrayAdapter<Shoe> adapterr = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, alist);
*/
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
//package com.example.james.myapplication.home;
//
//import android.app.ProgressDialog;
//import android.os.Bundle;
//import android.support.design.widget.Snackbar;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v4.widget.NestedScrollView;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.GridView;
//import android.widget.Toast;
//
//import com.example.james.myapplication.Network.FirebaseManager;
//import com.example.james.myapplication.models.ImageAdapter;
//import com.example.james.myapplication.models.MyAdapter;
//import com.example.james.myapplication.models.MyRecyclerViewAdapter;
//import com.example.james.myapplication.R;
//import com.example.james.myapplication.models.Shoe;
////import com.example.james.myapplication.Model.Shoe;
//import com.example.james.myapplication.models.MyRecyclerViewAdapterShoes;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class HomeFragment extends Fragment
//{
//    //private GridView gridView = null;
//    //private ArrayList<Shoe> mShoeList = null;
//    //private ImageAdapter mImageAdapter = null;
//    private ArrayList<String> shoearraypass = null;
//    private ArrayAdapter<Shoe> adap = null;
//    private ImageAdapter imageViewing = null;
//    private RecyclerView recycled = null;
//    //private MyRecyclerViewAdapter adapter;
//    private MyRecyclerViewAdapterShoes adapter;
//    private MyRecyclerViewAdapterShoes adaptershoe;
//
//    private GridView gridView = null;
//    private ArrayList<Shoe> mShoeList;
//    private ImageAdapter mImageAdapter;//  = null
//
//    private Shoe shoe;
//
//    private ArrayList<Shoe> mShoeListt;
//
//    public HomeFragment()
//    {
//    }
//
//    public static HomeFragment newInstance()
//    {
//        return new HomeFragment();
//    }
///*
//    interface DataReceivedListener{
//        void onDataReceived(List<Shoe> data);
//    }
//
//    void feed_data(DataReceivedListener listener) {
//        final List<Shoe> feededProducts = new ArrayList<>();
//
//        //progressDialog = new ProgressDialog(this);
//        //progressDialog.setMessage("Please wait...");
//        //progressDialog.show();
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("shoes");
//
//        //mDatabase = FirebaseDatabase.getInstance().getReference("/products");
//
//        //adding an event listener to fetch values
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                //dismissing the progress dialog
//                //progressDialog.dismiss();
//
//                //iterating through all the values in database
//                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
//                    Shoe upload =
//                            postSnapshot.getValue(Shoe.class);
//                    Log.d("error", "onDataChange: " + upload);//.about);
//                    feededProducts.add(upload);
//                }
//
//                listener.onDataReceived(feededProducts);
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                //Log.d("Error", "onCancelled: " + databaseError.getMessage());
//
//                //NestedScrollView root_layout =
//                 //       findViewById(R.id.category_root_layout) ;
//               // Snackbar.make(root_layout, "Internal Error!!",
//                //        Snackbar.LENGTH_SHORT).show();
//            }
//        });
//    }
//*/
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState)
//    {
//        View v = inflater.inflate(R.layout.fragment_home2, container, false);
//        if (v != null)
//        {
//        }
//
//        //RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.rvNumbers);
//        //int numberOfColumns = 2;
//
//        //mShoeListt = new ArrayList<Shoe>();
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("shoes");
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                shoearraypass = new ArrayList<String>();
//                String ds = dataSnapshot.child("b22mhFiqHuVR9vwowQyQjD4720Q2").getKey();
//                //String ds = dataSnapshot.child("b22mhFiqHuVR9vwowQyQjD4720Q2").getChildren();
//                for (DataSnapshot snaparray : dataSnapshot.child("b22mhFiqHuVR9vwowQyQjD4720Q2").getChildren())
//                //for (DataSnapshot snaparray : dataSnapshot.child("b22mhFiqHuVR9vwowQyQjD4720Q2").getChildren())
//                //for (DataSnapshot snaparray : dataSnapshot.child("b22mhFiqHuVR9vwowQyQjD4720Q2").child("-L9FNpPzi2FouktspF3G").getChildren())
//                {
//                    shoearraypass.add("" + snaparray.child("shoeImageUrl").getValue());
//                    //Toast.makeText(getContext(),"Zeta: " + snaparray,Toast.LENGTH_SHORT).show();
//                }
//
//                //RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.rvNumbers);
//                //int numberOfColumns = 2;
//                //recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));
//                //adaptershoe = new MyRecyclerViewAdapterShoes(getContext(), mShoeListt,null, getContext(), getActivity(),new HomeFragment());
//                //recyclerView.setAdapter(adaptershoe);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                //Log.w(TAG, "Failed to read value.", error.toException());
//                //Toast.makeText(getContext(),"error commander: " + error.toException(),Toast.LENGTH_SHORT).show();
//
//            }
//
//        });
//
//        String[] arrayshoeconv = shoearraypass.toArray(new String[0]);
//
//        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.rvNumbers);
//        int numberOfColumns = 2;
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));
//        adaptershoe = new MyRecyclerViewAdapterShoes(getContext(), arrayshoeconv, getContext(), getActivity(),new HomeFragment());
//        recyclerView.setAdapter(adaptershoe);
//
//        //Toast.makeText(getContext(),"fetched FIRST " + mShoeListt.get(0).getShoeImageUrl().toString(), Toast.LENGTH_LONG).show();
//
//        //MyAdapter myadapter=new MyAdapter(getActivity(),shoearraypass);
//
//        //String[] arrayshoeconv = mShoeListt.toArray(new String[0]);
//
//        ////Toast.makeText(getContext(), "yo",Toast.LENGTH_LONG).show();
//        //for(Shoe sh : mShoeListt){
//        //    Toast.makeText(getContext(), "fuckyou",Toast.LENGTH_LONG).show();
//        //}
//
//        //String[] arrayshoeconv = shoearraypass.toArray(new String[0]);
//        /////////////////Toast.makeText(getContext(), "" + arrayshoeconv.toString(),Toast.LENGTH_LONG).show();
//
//        //Toast.makeText(getContext(), "fuckyou" + shoearraypass.toString(),Toast.LENGTH_LONG).show();
//
//        //RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.rvNumbers);
//        //int numberOfColumns = 2;
//        //recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));
//       // adaptershoe = new MyRecyclerViewAdapterShoes(getContext(),arrayshoeconv, getContext(), getActivity(), new HomeFragment() );
//        //Toast.makeText(getContext(),"beforeadapter",Toast.LENGTH_LONG).show();
//        //recyclerView.setAdapter(adaptershoe);
//
//        /////
//          /*  fun onDataChange(dataSnapshot: DataSnapshot) {
//            for (snapshot in dataSnapshot.children) {
//                for (snapshot2 in snapshot.children) {
//                    val fetchedShoe = Shoe(snapshot2);
//                    mShoeList.add(fetchedShoe);
//                }
//            }*/
//            //completion handler, return the mShoeList to whoever called it
//            //callback(mShoeList!!)
//        //}
//
//        /////////////////////////////
////bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb
//        // Read from the database
//        /*
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                shoearraypass = new ArrayList<String>();
//                String ds = dataSnapshot.child("b22mhFiqHuVR9vwowQyQjD4720Q2").getKey();
//                //String ds = dataSnapshot.child("b22mhFiqHuVR9vwowQyQjD4720Q2").getChildren();
//                for (DataSnapshot snaparray : dataSnapshot.child("b22mhFiqHuVR9vwowQyQjD4720Q2").getChildren())
//                //for (DataSnapshot snaparray : dataSnapshot.child("b22mhFiqHuVR9vwowQyQjD4720Q2").getChildren())
//                //for (DataSnapshot snaparray : dataSnapshot.child("b22mhFiqHuVR9vwowQyQjD4720Q2").child("-L9FNpPzi2FouktspF3G").getChildren())
//                {
//                    shoearraypass.add("" + snaparray.child("shoeImageUrl").getValue());
//                    //Toast.makeText(getContext(),"Zeta: " + snaparray,Toast.LENGTH_SHORT).show();
//                }
//
//                //Toast.makeText(getContext(),"Zeta: " + shoearraypass.toString(),Toast.LENGTH_SHORT).show();
//
//                //ArrayAdapter<String> adap = new ArrayAdapter(getActivity(),
//                //        android.R.layout.simple_list_item_1, shoearraypass);
//
//                //ListView mListView = (ListView) getActivity().findViewById(R.id.staticListView);
//                //GridView mListView = (GridView) getActivity().findViewById(R.id.gridglide);
//
//
//
//                //Initialize ImageView
//
//                MyAdapter myadapter=new MyAdapter(getActivity(),shoearraypass);
//                //mListView.setAdapter(myadapter);
//
//
//
//
//                /////
//                //List<String> list = ..;
//                String[] arrayshoeconv = shoearraypass.toArray(new String[0]);
//                /////
//
//                //setContentView(R.layout.activity_main);
//
//                // data to populate the RecyclerView with
//                ////String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48"};
//
//                // set up the RecyclerView
//                //RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.rvNumbers);
//                //int numberOfColumns = 6;
//                //recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));
//                //adapter = new MyRecyclerViewAdapter(getContext(), data);
//
//                RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.rvNumbers);
//                int numberOfColumns = 2;
//                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));
//                adapter = new MyRecyclerViewAdapterShoes(getContext(), arrayshoeconv, getContext(), getActivity(), new HomeFragment() );
//                //adapter.setClickListener(this);
//                recyclerView.setAdapter(adapter);
//
//
//                //recyclerView.addOnItemTouchListener();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                //Log.w(TAG, "Failed to read value.", error.toException());
//                //Toast.makeText(getContext(),"error commander: " + error.toException(),Toast.LENGTH_SHORT).show();
//
//            }
//        });
//*/
/////////bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb
//        //////////////////////////////////////////////////////////////////////////
//
//        ArrayList<String> alist = new ArrayList<String>();
//        alist.add("jamesOS");
//        alist.add("AnJinOS");
//        alist.add("GangOS");
//        alist.add("Android");
//        alist.add("iPhone");
//        alist.add("WindowsMobile");
//        alist.add("Blackberry");
//        alist.add("WebOS");
//        alist.add("Ubuntu");
//        alist.add("Windows7");
//        alist.add("Max OS X");
//        alist.add("Linux");
//        alist.add("OS/2");
//
//        //ArrayAdapter<String> adapterr = new ArrayAdapter(getActivity(),
//        //        android.R.layout.simple_list_item_1, alist);
//
//        ///////shoearraypass
//        //ArrayList<Shoe> shoelist = new ArrayList<Shoe>();
//        //shoelist.addAll(shoearraypass);
//        ArrayAdapter<Shoe> adapterr = new ArrayAdapter(getActivity(),
//                android.R.layout.simple_list_item_1, alist);
//
//
//        //ListView mListView = (ListView) getActivity().findViewById(R.id.staticListView);
//        //mListView.setAdapter(adapterr);
//        //mListView.setAdapter(adap);
//        /*mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                //////////////////////////////////////////////////////////////////////////
//                // Write a message to the database
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference myRef = database.getReference("message");
//
//                myRef.setValue("Detail for item " + id + " triggered");
//                //////////////////////////////////////////////////////////////////////////
//
//                Fragment fragment = new HomeItemDetailsFragment();
//                Bundle arguments = new Bundle();
//                arguments.putString( "welcome" , "parent: " + parent.toString() + "\n view: " + view.toString() + "\n position: " + position + "\n id: " + id);
//                fragment.setArguments(arguments);
//                final FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.replace(R.id.fragment_container, fragment);
//                ft.commit();
//            }
//        });*/
//
//
//
//        return v;
//    }
//
//    private void performTransition()
//    {
//        Fragment fragment = new HomeItemDetailsFragment();
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_container, fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//    }
//}
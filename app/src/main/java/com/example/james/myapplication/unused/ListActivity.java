//package com.example.james.myapplication;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.View;
//
//import java.util.ArrayList;
//
//public class HomeActivity extends AppCompatActivity {
//    RecyclerView itemsList;
//    ItemsListAdapter adapter;
//    ArrayList<ItemsListSingleItem> data = new ArrayList<>();
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list);
//
//        itemsList = (RecyclerView) getView().findViewById(R.id.items_list);
//        itemsList.setHasFixedSize(true);
//        mLinearLayoutManager = new LinearLayoutManager(this);
//        itemsList.setLayoutManager(mLinearLayoutManager);
//
//        //let us add some items into the list
//        data.add(
//                new ItemsListSingleItem(
//                        1,
//                        "First Item",
//                        "www.someUrlToMyThumbnailImage1"
//                ));
//
//        data.add(
//                new ItemsListSingleItem(
//                        2,
//                        "Second Item",
//                        "www.someUrlToMyThumbnailImage2"
//                ));
//
//        adapter = new ItemsListAdapter(getActivity(), data, new CustomItemClickListener() {
//            @Override
//            public void onItemClick(View v, int position) {
//                Log.d(TAG, "clicked position:" + position);
//                long postId = data.get(position).getID();
//                // do what ever you want to do with it
//            }
//        });
//        itemsList.setAdapter(adapter);
//    }
//}
package com.example.james.myapplication.search;

import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.james.myapplication.R;
import com.example.james.myapplication.models.Shoe;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class firebaseSearch extends AppCompatActivity
{
    private AutoCompleteTextView mSearchField;
    private Button mSearchBtn;

    private RecyclerView mResultList;

    private DatabaseReference mUserDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search);

        mUserDatabase = FirebaseDatabase.getInstance().getReference("shoes");

        mSearchField = (AutoCompleteTextView) findViewById(R.id.searchBox);
        mSearchBtn = (Button) findViewById(R.id.searchButton);

        mResultList = (RecyclerView) findViewById(R.id.recycler_view);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchText = mSearchField.getText().toString();

                firebaseShoeSearch(searchText);

            }
        });
    }

    private void firebaseShoeSearch(String searchText)
    {
        Toast.makeText(this, "Started Search", Toast.LENGTH_LONG).show();

        Query firebaseSearchQuery = mUserDatabase.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");
        
        FirebaseRecyclerAdapter<Shoe, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Shoe, UsersViewHolder>(

                Shoe.class,
                R.layout.list_layout,
                UsersViewHolder.class,
                firebaseSearchQuery

        ) {
            //@Override
            protected void populateViewHolder(UsersViewHolder viewHolder, Shoe model, int position)
            {
                viewHolder.setDetails(getApplicationContext(), model.getName());
            }
        };

        mResultList.setAdapter(firebaseRecyclerAdapter);
    }

    // View Holder Class
    public static class UsersViewHolder extends RecyclerView.ViewHolder
    {
        View mView;

        public UsersViewHolder(View itemView)
        {
            super(itemView);
            mView = itemView;
        }

        public void setDetails(Context ctx, String shoeName){

            TextView shoe_name = (TextView) mView.findViewById(R.id.text);
            //ImageView user_image = (ImageView) mView.findViewById(R.id.profile_image);

            shoe_name.setText(shoeName);

            //Glide.with(ctx).load(userImage).into(user_image);
        }
    }
}
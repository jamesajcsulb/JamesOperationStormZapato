//package com.example.james.myapplication;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.text.Html;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//public class ItemsListAdapter extends RecyclerView.Adapter<ItemsListAdapter.ViewHolder> {
//    ArrayList<ItemListSingleItem> data;
//
//    Context mContext;
//    CustomItemClickListener listener;
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_list_single_item, parent, false);
//        final ViewHolder mViewHolder = new ViewHolder(mView);
//        mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onItemClick(v, mViewHolder.getPosition());
//            }
//        });
//        return mViewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.itemTitle.setText(Html.fromHtml(data.get(position).getTitle()));
//        if (!TextUtils.isEmpty(data.get(position).getThumbnailURL())) {
//            // I Love picasso library :) http://square.github.io/picasso/
//            Picasso.with(mContext).load(data.get(position).getThumbnailURL()).error(R.drawable.ic_no_image).
//                    placeholder(R.drawable.ic_no_image).
//                    transform(new RoundedCornersTransformation(5, 0)).
//                    into(holder.thumbnailImage);
//        } else {
//            holder.thumbnailImage.setImageResource(R.drawable.ic_no_image);
//        }
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return data.size();
//    }
//
//    public ItemsListAdapter(Context mContext, ArrayList<ItemsListSingleItem> data, CustomItemClickListener listener) {
//        this.data = data;
//        this.mContext = mContext;
//        this.listener = listener;
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        public TextView itemTitle;
//        public ImageView thumbnailImage;
//
//        ViewHolder(View v) {
//            super(v);
//            itemTitle = (TextView) v
//                    .findViewById(R.id.post_title);
//            thumbnailImage = (ImageView) v.findViewById(R.id.post_thumb_image);
//        }
//    }
//}
///*
//activity_list.xml
//
//<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
//    android:layout_width="match_parent"
//    android:layout_height="match_parent"
//    android:orientation="vertical">
//
//    <android.support.v7.widget.RecyclerView
//        android:id="@+id/items_list"
//        android:layout_width="match_parent"
//        android:layout_height="match_parent"/>
//
//</LinearLayout>*/
//
///*
////ItemsListSingleItem.java
//// */
////public class ItemsListSingleItem {
////    private String title,thumbnailURL;
////    /**
////     * Just for the sake of internal reference so that we can identify the item.
////     */
////long id;
////
////    /**
////     *
////     * @param id
////     * @param title
////     * @param thumbnailURL
////     */
////    public ItemsListSingleItem(long id, String title, String thumbnailURL) {
////        this.id = id;
////        this.title = title;
////        this.thumbnailURL = thumbnailURL;
////    }
////
////    public String getTitle() {
////        return title;
////    }
////
////    public long getID() {
////        return id;
////    }
////
////    public String getThumbnailURL() {
////        return thumbnailURL;
////    }
////}
//
///*
//ListActivity.java
//public class HomeActivity extends AppCompatActivity{
//    RecyclerView itemsList;
//    ItemsListAdapter adapter;
//    ArrayList<ItemsListSingleItem> data = new ArrayList<>();
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//      super.onCreate(savedInstanceState);
//      setContentView(R.layout.activity_list);
//
//      itemsList = (RecyclerView) getView().findViewById(R.id.items_list);
//      itemsList.setHasFixedSize(true);
//      mLinearLayoutManager = new LinearLayoutManager(this);
//      itemsList.setLayoutManager(mLinearLayoutManager);
//
//      //let us add some items into the list
//      data.add(
//        new ItemsListSingleItem(
//        1,
//        "First Item",
//        "www.someUrlToMyThumbnailImage1"
//      ));
//
//      data.add(
//        new ItemsListSingleItem(
//        2,
//        "Second Item",
//        "www.someUrlToMyThumbnailImage2"
//      ));
//
//      adapter = new ItemsListAdapter(getActivity(), data, new CustomItemClickListener() {
//        @Override
//        public void onItemClick(View v, int position) {
//            Log.d(TAG, "clicked position:" + position);
//            long postId = data.get(position).getID();
//            // do what ever you want to do with it
//        }
//      });
//      itemsList.setAdapter(adapter);
//    }
//}
// */
//package com.example.james.myapplication;
//
//import android.content.Context;
//import android.media.VolumeShaper;
//import android.os.SystemClock;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import java.util.HashMap;
//import java.util.List;
//
//public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewLedgerAdapter.ViewHolder>{
//    private final String TAG = RecyclerViewAdapter.class.getSimpleName();
//
//    private final float FINAL_OPACITY = 0.3f;
//    private final float START_OPACITY = 1f;
//
//    private final int ANIMATION_TIME = 500;
//    private final int TYPE_ITEM = 0;
//    private final int TYPE_DATE = 1;
//    private final int TYPE_TRANSACTION = 2;
//    private final int TYPE_PENDING = 3;
//
//    private HashMap<Integer, Integer> mElementTypes;
//    private List<VolumeShaper.Operation> mObjects;
//    private Context mContext;
//    //private Utils.CURRENCIES mCurrencySelected; // Which currency is already selected
//    private boolean mCurrencyFilter; // Defines if a currency is already selected to apply filter
//    private Animation mAnimationUp;
//    private Animation mAnimationDown;
//
//    public RecyclerViewLedgerAdapter(List<VolumeShaper.Operation> objects, Context context) {
//        mElementTypes = new HashMap<Integer, Integer>();
//        mObjects = objects;
//        mContext = context;
//        mCurrencyFilter = false;
//        //mCurrencySelected = null;
//        mAnimationUp = AnimationUtils.loadAnimation(context, R.anim.slide_up);
//        mAnimationDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);
//    }
//
//   //     ...
//   //             ...
//  //  Not needed methods
//   //     ...
//    //            ...
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_element_ledger, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        VolumeShaper.Operation operation = mObjects.get(position);
//        holder.setAppUserActivity(mainActivity);//userActivityOperation);
//
//        // Remember that RecyclerView does not have onClickListener, you should implement it
//        holder.getView().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Hide details
//                // iDetailsContainer object could be checked on inner class ViewHolder
//                if(holder.iDetailsContainer.isShown()){
//                    holder.iDetailsContainer.setVisibility(View.GONE);
//                }else{
//                    // Show details
//                    // Get fragment manager inside our fragment
//                    FragmentManager fragmentManager = ((MainActivity)mContext).getSupportFragmentManager();
//
//                    // Delete previous added fragment
//                    int currentContainerId = holder.iDetailsContainer.getId();
//                    // Get the current fragment
//                    Fragment oldFragment = fragmentManager.findFragmentById(currentContainerId);
//                    if(oldFragment != null) {
//                        // Delete fragmet from ui, do not forget commit() otherwise no action
//                        // is going to be observed
//                        fragmentManager.beginTransaction().remove(oldFragment).commit();
//                    }
//
//                    // In order to be able of replacing a fragment on a recycler view
//                    // the target container should always have a different id ALWAYS
//                    int newContainerId = getUniqueId();
//                    // Set the new Id to our know fragment container
//                    holder.iDetailsContainer.setId(newContainerId);
//
//                    // Just for Testing we are going to create a new fragment according
//                    // if the view position is pair one fragment type is created, if not
//                    // a different one is used
//                    Fragment f;
//                    if(position%2 == 0) {
//                        //f = new FragmentCard();
//                    }else{
//                        //f=new FragmentChat();
//                    }
//
//                    // Then just replace the recycler view fragment as usually
//                    manager.beginTransaction().replace(newContainerId, f).commit();
//
//                    // Once all fragment replacement is done we can show the hidden container
//                    holder.iDetailsContainer.setVisibility(View.VISIBLE);
//                }
//            }
//
//            // Method that could us an unique id
//            public int getUniqueId(){
//                return (int) SystemClock.currentThreadTimeMillis();
//            }
//        });
//    }
//
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//        private View iView;
//        private LinearLayout iContainer;
//        public LinearLayout iDetailsContainer;
//        private ImageView iOperationIcon;
//        private ImageView iOperationActionImage;
//        private TextView iOperation;
//        private TextView iAmount;
//        private TextView iTimestamp;
//        private TextView iStatus;
//
//        private MainActivity mainActivity;//UserActivityOperation mUserActivityOperation;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            iView = itemView;
//            iContainer = (LinearLayout) iView.findViewById(R.id.operation_container);
//            iDetailsContainer = (LinearLayout) iView.findViewById(R.id.details_container);
//            iOperationIcon = (ImageView) iView.findViewById(R.id.ledgerOperationIcon);
//            iOperationActionImage = (ImageView) iView.findViewById(R.id.ledgerAction);
//            iOperation = (TextView) iView.findViewById(R.id.ledgerOperationDescription);
//            iAmount = (TextView) iView.findViewById(R.id.ledgerOperationCurrencyAmount);
//            iTimestamp = (TextView) iView.findViewById(R.id.ledgerOperationTimestamp);
//            iStatus = (TextView) iView.findViewById(R.id.ledgerOperationStatus);
//
//            // This linear layout status is GONE in order to avoid the view to use space
//            // even when it is not seen, when any element selected the Adapter will manage the
//            // behavior for showing the layout - container
//            iDetailsContainer.setVisibility(View.GONE);
//        }
//
//         //   ...
//         //           ...
//        //Not needed methods
//        //    ...
//         //           ...
//    }
//}
//
///*
//package com.example.james.myapplication;
//
//import android.content.Context;
//import android.media.VolumeShaper;
//import android.os.SystemClock;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import java.util.HashMap;
//import java.util.List;
//
//public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewLedgerAdapter.ViewHolder>{
//    private final String TAG = RecyclerViewAdapter.class.getSimpleName();
//
//    private final float FINAL_OPACITY = 0.3f;
//    private final float START_OPACITY = 1f;
//
//    private final int ANIMATION_TIME = 500;
//    private final int TYPE_ITEM = 0;
//    private final int TYPE_DATE = 1;
//    private final int TYPE_TRANSACTION = 2;
//    private final int TYPE_PENDING = 3;
//
//    private HashMap<Integer, Integer> mElementTypes;
//    private List<VolumeShaper.Operation> mObjects;
//    private Context mContext;
//    //private Utils.CURRENCIES mCurrencySelected; // Which currency is already selected
//    private boolean mCurrencyFilter; // Defines if a currency is already selected to apply filter
//    private Animation mAnimationUp;
//    private Animation mAnimationDown;
//
//    public RecyclerViewLedgerAdapter(List<VolumeShaper.Operation> objects, Context context) {
//        mElementTypes = new HashMap<Integer, Integer>();
//        mObjects = objects;
//        mContext = context;
//        mCurrencyFilter = false;
//        //mCurrencySelected = null;
//        mAnimationUp = AnimationUtils.loadAnimation(context, R.anim.slide_up);
//        mAnimationDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);
//    }
//
//   //     ...
//   //             ...
//  //  Not needed methods
//   //     ...
//    //            ...
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_element_ledger, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        VolumeShaper.Operation operation = mObjects.get(position);
//        holder.setAppUserActivity(userActivityOperation);
//
//        // Remember that RecyclerView does not have onClickListener, you should implement it
//        holder.getView().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Hide details
//                // iDetailsContainer object could be checked on inner class ViewHolder
//                if(holder.iDetailsContainer.isShown()){
//                    holder.iDetailsContainer.setVisibility(View.GONE);
//                }else{
//                    // Show details
//                    // Get fragment manager inside our fragment
//                    FragmentManager fragmentManager = ((UserActivity)mContext).getSupportFragmentManager();
//
//                    // Delete previous added fragment
//                    int currentContainerId = holder.iDetailsContainer.getId();
//                    // Get the current fragment
//                    Fragment oldFragment = fragmentManager.findFragmentById(currentContainerId);
//                    if(oldFragment != null) {
//                        // Delete fragmet from ui, do not forget commit() otherwise no action
//                        // is going to be observed
//                        ragmentManager.beginTransaction().remove(oldFragment).commit();
//                    }
//
//                    // In order to be able of replacing a fragment on a recycler view
//                    // the target container should always have a different id ALWAYS
//                    int newContainerId = getUniqueId();
//                    // Set the new Id to our know fragment container
//                    holder.iDetailsContainer.setId(newContainerId);
//
//                    // Just for Testing we are going to create a new fragment according
//                    // if the view position is pair one fragment type is created, if not
//                    // a different one is used
//                    Fragment f;
//                    if(position%2 == 0) {
//                        f = new FragmentCard();
//                    }else{
//                        f=new FragmentChat();
//                    }
//
//                    // Then just replace the recycler view fragment as usually
//                    manager.beginTransaction().replace(newContainerId, f).commit();
//
//                    // Once all fragment replacement is done we can show the hidden container
//                    holder.iDetailsContainer.setVisibility(View.VISIBLE);
//                }
//            }
//
//            // Method that could us an unique id
//            public int getUniqueId(){
//                return (int) SystemClock.currentThreadTimeMillis();
//            }
//        });
//    }
//
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//        private View iView;
//        private LinearLayout iContainer;
//        public LinearLayout iDetailsContainer;
//        private ImageView iOperationIcon;
//        private ImageView iOperationActionImage;
//        private TextView iOperation;
//        private TextView iAmount;
//        private TextView iTimestamp;
//        private TextView iStatus;
//
//        private UserActivityOperation mUserActivityOperation;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            iView = itemView;
//            iContainer = (LinearLayout) iView.findViewById(R.id.operation_container);
//            iDetailsContainer = (LinearLayout) iView.findViewById(R.id.details_container);
//            iOperationIcon = (ImageView) iView.findViewById(R.id.ledgerOperationIcon);
//            iOperationActionImage = (ImageView) iView.findViewById(R.id.ledgerAction);
//            iOperation = (TextView) iView.findViewById(R.id.ledgerOperationDescription);
//            iAmount = (TextView) iView.findViewById(R.id.ledgerOperationCurrencyAmount);
//            iTimestamp = (TextView) iView.findViewById(R.id.ledgerOperationTimestamp);
//            iStatus = (TextView) iView.findViewById(R.id.ledgerOperationStatus);
//
//            // This linear layout status is GONE in order to avoid the view to use space
//            // even when it is not seen, when any element selected the Adapter will manage the
//            // behavior for showing the layout - container
//            iDetailsContainer.setVisibility(View.GONE);
//        }
//
//         //   ...
//         //           ...
//        //Not needed methods
//        //    ...
//         //           ...
//    }
//}
// */
//package com.example.james.myapplication;
//
//// This is one of the fragments used in the RecyclerViewAdapterCode, and also makes a HTTPRequest to fill the
//// view dynamically, you could laso use any of your fragments.
//public class FragmentCard extends Fragment {
//
//    TextView mTextView;
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_card, container, false);
//
//        mTextView = (TextView) view.findViewById(R.id.tv_fragment_two);
//        new UserActivityAsyncTask().execute();
//        return view;
//    }
//
//    private UserActivityOperation[] asyncMethodGetPendingWithdrawals(){
//        BitsoWithdrawal[] userWithdrawals = HttpHandler.getUserWithdrawals(getActivity());
//        int totalWithDrawals = userWithdrawals.length;
//        UserActivityOperation[] appUserActivities = new UserActivityOperation[totalWithDrawals];
//        for(int i=0; i<totalWithDrawals; i++){
//            appUserActivities[i] = new UserActivityOperation(userWithdrawals[i], getActivity());
//        }
//        return appUserActivities;
//    }
//
//    private class UserActivityAsyncTask extends AsyncTask<String, Void, Integer> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected Integer doInBackground(String... strings) {
//            // Precess Compound balance
//            UserActivityOperation[] compoundBalanceProcessed = asyncMethodGetPendingWithdrawals();
//            return compoundBalanceProcessed.length;
//        }
//
//        @Override
//        protected void onPostExecute(Integer result) {
//            super.onPostExecute(result);
//            mTextView.setText(result.toString());
//        }
//    }
//}
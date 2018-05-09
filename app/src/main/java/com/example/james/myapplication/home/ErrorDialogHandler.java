//package com.example.james.myapplication.home;
//
//import android.support.v4.app.DialogFragment;
//import android.support.v4.app.FragmentManager;
//
//import com.google.android.gms.common.ErrorDialogFragment;
//
//
///**
// * A convenience class to handle displaying error dialogs.
// */
//public class ErrorDialogHandler {
//
//    FragmentManager mFragmentManager;
//
//    public ErrorDialogHandler(FragmentManager fragmentManager) {
//        mFragmentManager = fragmentManager;
//    }
//
//    public void showError(String errorMessage) {
//        DialogFragment fragment = ErrorDialogFragment.newInstance(
//                R.string.validationErrors, errorMessage);
//        fragment.show(mFragmentManager, "error");
//    }
//}

package net.simplifiedcoding.speechtotext.utils;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by bipulkhan on 4/13/17.
 */

public class SnackbarCustom {

   public static void showInternetStatus(Activity activity, int id,String message){
        View parentLayout = activity.findViewById(id);
        Snackbar snackbar = Snackbar.make(parentLayout,message,5000);
        View snackbarView = snackbar.getView();
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
      //  layout.setGravity(Gravity.CENTER);
        TextView mTextView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        mTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        mTextView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        snackbar.show();
    }
     public static void showInternetMessage(Activity activity,String message){
          View parentLayout = activity.getWindow().getDecorView().findViewById(android.R.id.content);
          Snackbar snackbar = Snackbar.make(parentLayout,message,5000);
          View snackbarView = snackbar.getView();
          Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        //  layout.setGravity(Gravity.CENTER);
          TextView mTextView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
          mTextView.setGravity(Gravity.CENTER_HORIZONTAL);
          mTextView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
          snackbar.show();
     }
}

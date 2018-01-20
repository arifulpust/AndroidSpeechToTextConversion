package net.simplifiedcoding.speechtotext.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;


/**
 * Created by Dream&1 on 12-Mar-17.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String status = NetworkConnection.getConnectivityStatusString(context);
        Log.e("status",""+status+"  "+NetworkConnection.NETWORK_STATE);
        Intent intent2 = new Intent("internet_result");
        if(NetworkConnection.NETWORK_STATE==0){
            intent2.putExtra("status", "No Internet Connection");
        }else{
            intent2.putExtra("status", "Internet Connected");
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent2);

    }
}

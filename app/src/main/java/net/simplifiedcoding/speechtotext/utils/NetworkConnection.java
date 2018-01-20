package net.simplifiedcoding.speechtotext.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by Dream&1 on 12-Mar-17.
 */

public class NetworkConnection {
    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;
    public static int NETWORK_STATE = 0;
Context context;


    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

    public static String getConnectivityStatusString(Context context) {
        int conn = NetworkConnection.getConnectivityStatus(context);
        String status = null;
        if (conn == NetworkConnection.TYPE_WIFI) {
            NETWORK_STATE=1;
            status = "Wifi enabled";
        } else if (conn == NetworkConnection.TYPE_MOBILE) {
            NETWORK_STATE=1;
            status = "Mobile data enabled";
        } else if (conn == NetworkConnection.TYPE_NOT_CONNECTED) {

            NETWORK_STATE=0;
            status = "Not connected to Internet";

        }
        return status;
    }
    public static boolean getConnectivitychecking(Context context) {

        int conn = NetworkConnection.getConnectivityStatus(context);
         boolean flag= false;
        if (conn == NetworkConnection.TYPE_WIFI) {
            flag=true;

        } else if (conn == NetworkConnection.TYPE_MOBILE) {
            flag=true;
        } else if (conn == NetworkConnection.TYPE_NOT_CONNECTED) {
            flag=false;
            Intent intent=new Intent("inernet_connection_fail");
            intent.putExtra("message","Not connected to Internet");
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

        }
        return flag;
    }
}
//NetworkConnection.getConnectivityStatusString()
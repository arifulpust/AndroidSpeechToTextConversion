package net.simplifiedcoding.speechtotext.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by bipulkhan on 12/27/16.
 */


public class AppData {

    //keys
    public static String employeeInfo = "person";
    public static String service = "service";
    public static String userName = "arif";
    public static String password = "1234";
    public static String Acess_Toten = "Acess_Toten";

//AppData.saveData
    public static final String MyPREFERENCES = "voicetotext" ;



    public static void setString(String key, String value, Context context){
        if(context!=null) {
            SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, 0);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(key, value);
            editor.commit();
          //  Log.e("saveData"," "+ key+"  "+getData(key,context));
        }
        else
        {
            Log.e("saveData"," null");
        }
    }
    public static void setLong(String key, long value, Context context){
        if(context!=null) {
            SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, 0);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putLong(key, value);
            editor.commit();
            //  Log.e("saveData"," "+ key+"  "+getData(key,context));
        }
        else
        {
            Log.e("saveData"," null");
        }
    }
    public static void setInt(String key, int value, Context context){
        if(context!=null) {
            SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, 0);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(key, value);
            editor.commit();
            //  Log.e("saveData"," "+ key+"  "+getData(key,context));
        }
        else
        {
            Log.e("saveData"," null");
        }
    }
    public static void setBool(String key, boolean value, Context context){
        if(context!=null) {
            SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, 0);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(key, value);
            editor.commit();
            //  Log.e("saveData"," "+ key+"  "+getData(key,context));
        }
        else
        {
            Log.e("saveData"," null");
        }
    }
    public static void setGloat(String key, float value, Context context){
        if(context!=null) {
            SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, 0);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putFloat(key, value);
            editor.commit();
            //  Log.e("saveData"," "+ key+"  "+getData(key,context));
        }
        else
        {
            Log.e("saveData"," null");
        }
    }

    public static String getString(String key, Context context){
        String string="";
        if(context!=null) {
            //Log.e("contex", "not null");
            SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, 0);
             string = prefs.getString(key, "");
        }
        else
        {
            //Log.e("contex"," null");
        }
        return string;
    }
    public static long getLong(String key, Context context){
        long string=0;
        if(context!=null) {
            //Log.e("contex", "not null");
            SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, 0);
            string = prefs.getLong(key,0);
        }
        else
        {
            //Log.e("contex"," null");
        }
        return string;
    }
    public static int getInt(String key, Context context){
        int string=0;
        if(context!=null) {
            //Log.e("contex", "not null");
            SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, 0);
            string = prefs.getInt(key,0);
        }
        else
        {
            //Log.e("contex"," null");
        }
        return string;
    }
    public static boolean getBool(String key, Context context){
        boolean string=false;
        if(context!=null) {
            //Log.e("contex", "not null");
            SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, 0);
            string = prefs.getBoolean(key,false);
        }
        else
        {
            //Log.e("contex"," null");
        }
        return string;
    }
    public static float getFloat(String key, Context context){
        float string=0;
        if(context!=null) {
            //Log.e("contex", "not null");
            SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, 0);
            string = prefs.getFloat(key,0);
        }
        else
        {
            //Log.e("contex"," null");
        }
        return string;
    }
    public static String Clear(Context context){
        String string="";
        if(context!=null) {
            //Log.e("contex", "not null");
            SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, 0);
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            editor.commit();
        }
        else
        {
            //Log.e("contex"," null");
        }
        return string;
    }


}

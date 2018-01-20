package net.simplifiedcoding.speechtotext.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



import org.chalup.microorm.MicroOrm;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


/**
 * Created by Dream71 on 02/07/2017.
 */

public class DatabaseHelper  extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "biman.DB";

    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("database Helper","On create");
        // db.execSQL(DBTables.Create_Table_Rules);

        db.execSQL(DBTablesInfo.Create_Table_Message);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // db.execSQL("DROP TABLE IF EXISTS " + DBTables.Create_Table_Rules);
        db.execSQL("DROP TABLE IF EXISTS " + DBTablesInfo.Create_Table_Message);

        onCreate(db);
    }
    public boolean addItems(String tableName,ArrayList<ContentValues> list){

        SQLiteDatabase db = this.getWritableDatabase();
        if(list == null || list.size()==0){
            return false;
        }
        for (int i = 0;i<list.size();i++){
            ContentValues values = list.get(i);
            db.insert(tableName, null, values);
        }
        db.close();
        return true;
    }

    public boolean deleteAllItems(String tableName){

        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "DELETE FROM " + tableName;
        db.execSQL(Query);
        db.close();
        return true;
    }
    public boolean deleteAllItems(String tableName,int status){

        SQLiteDatabase db = this.getWritableDatabase();
//        String Query = "DELETE FROM " + tableName+" where status = "+status;
//        db.execSQL(Query);


        int delete=  db.delete(tableName,  "status  = ?", new String[] { status+""});
        Log.e("delete",""+delete);
        db.close();
        return true;
    }
    public int deleteItems(String tableName,String columnName,String id){

        SQLiteDatabase db = this.getWritableDatabase();
        // String Query = "DELETE FROM " + tableName;
        int deleted = db.delete(tableName, columnName + "=?", new String[]{"" + id});
        //db.execSQL(Query);
        db.close();
        return deleted;
    }
    public Cursor getAllItemsUnSyne(String tableName) {

        String selectQuery = "SELECT  * FROM " + tableName+" where status=0";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.e("Count",""+cursor.getCount());
        db.close();
        return cursor;
    }
    public int UpdateValue(int _id, String KeyId,String TableName) {
        int update=-1;
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("status", 1);

        try {
            update = database.update(TableName, cv, KeyId+" = " + _id, null);
            Log.e("UPDATE", cv.toString()+"  "+update);


        } catch (SQLiteException exception) {
            exception.printStackTrace();
        }

        database.close();
        return update;

    }

    //
    // Getting All places
    public Cursor getAllResItems(String tableName) {

        String selectQuery = "SELECT  * FROM " + tableName;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.e("Count",""+cursor.getCount());
        db.close();
        return cursor;
    }
    public Cursor getAllResItems(String tableName,String ordrCollumn) {

        String selectQuery = "SELECT  * FROM " + tableName+" order by "+ordrCollumn+" ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.e("Count",""+cursor.getCount());
        db.close();
        return cursor;
    }
    public Cursor getAllDateItems(String tableName,String DateCullmn,String collumnValue,String ordrCollumn) {


        String quat="\"";
        String date=quat+collumnValue+quat;
        Log.e("collumnValue",""+quat+collumnValue+quat);
        String selectQuery = "SELECT  * FROM " + tableName+" where "+DateCullmn+" ="+date+" order by "+ordrCollumn+" DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.e("Count",""+cursor.getCount()+"    "+selectQuery);
        db.close();
        return cursor;
    }
    public Cursor getAllResItems(String tableName,String ordrCollumn,String CollumnName,String CollumnValue) {

        String selectQuery = "SELECT  * FROM " + tableName+" where "+CollumnName+" = "+CollumnValue+" order by "+ordrCollumn+" ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.e("Count",""+cursor.getCount());
        return cursor;
    }
    public Cursor getAllResItemsOrderByDate(String tableName,String ordrCollumn) {
        String selectQuery = "SELECT  * FROM " + tableName+" where status =  0 or status = 1 order  by "+ ordrCollumn +" DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.e("Count",""+cursor.getCount());
        return cursor;
    }
    public Cursor getAllDISDate(String tableName,String Date,String ordrCollumn) {
        // String selectQuery = "SELECT  DISTINCT "+Date+" FROM " + tableName+" order  by "+ ordrCollumn +" DESC";
        String selectQuery = "SELECT  DISTINCT "+Date+" FROM " + tableName+" order  by "+ ordrCollumn +" DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.e("Count",""+cursor.getCount());
        return cursor;
    }
    public Cursor getAllResItemsOrderByDate(String tableName,String ordrCollumn,int status) {
        String selectQuery = "SELECT  * FROM " + tableName+" where status = "+status+" order  by "+ ordrCollumn +" DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.e("Count",""+cursor.getCount());
        return cursor;
    }
    public void open() {
        db = this.getWritableDatabase();
    }

    /**
     * method to close database connection
     *
     * @param: takes no params
     */
    public void close() {
        if(db!=null)
            db.close();
    }
}

package net.simplifiedcoding.speechtotext.database;

/**
 * Created by Dream71 on 13/11/2017.
 */

public class DBTablesInfo {
    // org table

    public static final String ID_KEY = "id";
    public static final String TEXT_KEY = "text";
    public static final String NAME_KEY = "name";
    public static final String PHONE_KEY = "phone";
    public static final String TIME_KEY = "time";
    public static final String DATETIME_KEY = "datetime";
    public static final String CALLTYPE_KEY = "callType";
    public static final String AUDIOFILE_KEY = "audio_file";



    public static final String MESSAGE_TABLE_NAME = "message";

    //
    public static final String Create_Table_Message = "CREATE TABLE IF NOT EXISTS "
            + MESSAGE_TABLE_NAME
            + " ("
            + ID_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DATETIME_KEY + " TEXT,"
            + PHONE_KEY + " TEXT,"
            + TEXT_KEY + " TEXT,"
            + TIME_KEY + " TEXT,"
            + CALLTYPE_KEY + " TEXT,"
            + AUDIOFILE_KEY + " TEXT,"
            + NAME_KEY + " TEXT"

            + ");";

}

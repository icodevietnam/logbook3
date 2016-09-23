package logbook3.entry.logbook.com.logbook3.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import logbook3.entry.logbook.com.logbook3.model.Kiddy;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "logbook3.db";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    //Bird
    public static final String KIDDY_TABLE = "kiddy";
    public static final String KIDDY_ID = "id";
    public static final String KIDDY_NAME = "kiddy_name";
    public static final String KIDDY_LOCATION = "kiddy_location";
    public static final String KIDDY_DATE = "kiddy_date";
    public static final String KIDDY_TIME = "kiddy_time";
    public static final String KIDDY_REPORTER_NAME = "kiddy_reporter_name";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create table bird
        db.execSQL("create table " + KIDDY_TABLE + " ( " + KIDDY_ID + " integer primary key autoincrement not null ," + KIDDY_NAME + " text," + KIDDY_LOCATION + " text," + KIDDY_DATE + " text," + KIDDY_TIME + " text," + KIDDY_REPORTER_NAME + " text)");
        Log.d("Init database:", "Init kiddy successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " +KIDDY_TABLE);
        onCreate(db);
    }

    public boolean insertKiddy(Kiddy kiddy){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(KIDDY_NAME,kiddy.getActivityName());
            contentValues.put(KIDDY_LOCATION,kiddy.getLocation());
            contentValues.put(KIDDY_DATE,kiddy.getDate());
            contentValues.put(KIDDY_TIME,kiddy.getTime());
            contentValues.put(KIDDY_REPORTER_NAME,kiddy.getReporterName());
            db.insert(KIDDY_TABLE,null,contentValues);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Kiddy> getAllKiddys(){
        List<Kiddy> listKiddys = new ArrayList<Kiddy>();
        // Select all bird query
        String query = "SELECT * FROM " + KIDDY_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Kiddy kiddy = new Kiddy();
                kiddy.setId(cursor.getInt(cursor.getColumnIndex(KIDDY_ID)));
                kiddy.setActivityName(cursor.getString(cursor.getColumnIndex(KIDDY_NAME)));
                kiddy.setLocation(cursor.getString(cursor.getColumnIndex(KIDDY_LOCATION)));
                kiddy.setDate(cursor.getString(cursor.getColumnIndex(KIDDY_DATE)));
                kiddy.setTime(cursor.getString(cursor.getColumnIndex(KIDDY_TIME)));
                kiddy.setReporterName(cursor.getString(cursor.getColumnIndex(KIDDY_REPORTER_NAME)));
                // Adding report to list
                listKiddys.add(kiddy);
            } while (cursor.moveToNext());
        }
        return listKiddys;
    }
}

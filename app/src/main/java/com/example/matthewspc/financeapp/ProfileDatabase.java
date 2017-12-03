package com.example.matthewspc.financeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.util.Date;

public class ProfileDatabase extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "myDatabase.db";//creates fields
    public static final String DATABASE_TABLE = "ExpenseLog";
    private static final int DATABASE_VERSION = 1;
    public static final String ID = "_id";
    public static final String GOAL = "spendGoal";
    public static final String DATE = "spendDate";
    public static final String SPENT = "spendSpent";
    public static final String DATABASE_CREATE = "CREATE TABLE "+DATABASE_TABLE+" ("+ID+" INTEGER PRIMARY KEY, "+GOAL+" TEXT,"+DATE+" TEXT," +SPENT+ "TEXT )";

    public ProfileDatabase(Context context)
    {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);//constructs database
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(DATABASE_CREATE);//Creates database
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //Log version upgrade
        Log.w("TaskDBAdapter","Upgrading from version "+oldVersion+" to "+newVersion+", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
        onCreate(db);//recreates database
    }

    public void updateProfile(String goal,String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();//gets the database
        removeAll(db);
        ContentValues expenseValues = new ContentValues();
        expenseValues.put(GOAL,goal);//sets values for new log
        expenseValues.put(DATE,date);
        db.insert(ProfileDatabase.DATABASE_TABLE,null,expenseValues);//inserts log to database
        db.close();
    }



    public void removeAll(SQLiteDatabase db)
    {
        db.delete(DATABASE_TABLE, null, null);
    }

    public Cursor getLog()
    {
        SQLiteDatabase db = this.getReadableDatabase();//retrieves database
        Cursor allData = db.query(DATABASE_TABLE, new String[] {ID,GOAL,DATE},null,null,null,null,null);//gets cursor
        if (allData != null)
        {//if not empty
            allData.moveToFirst();
            return allData;
        }
        else
        {
            return null;
        }
    }
}
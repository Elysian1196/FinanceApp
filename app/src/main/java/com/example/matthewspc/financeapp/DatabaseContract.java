package com.example.matthewspc.financeapp;

import android.provider.BaseColumns;

/**
 * Created by Beast_07 on 11/24/2017.
 */

public final class DatabaseContract {
    private DatabaseContract(){
    }
    public static class Expenses implements BaseColumns {
        //This is the tables name
        public static final String TABLE_NAME = "Expenses";
        //These are it's columns. The _ID one is pretty important I think, not sure if it's actually supposed to be String type
        public static final String _ID = "_id";
        public static final String COLUMN_PURCHASE_NAME = "purchaseName";
        public static final String COLUMN_PURCHASE_COST = "purchaseCost";
        public static final String COLUMN_PURCHASE_TIMEDATE = "purchaseTimeDate";
        public static final String COLUMN_PURCHASE_TAG = "purchaseTag";

        //This is the creator. Call it when you make the table in the main app.
        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PURCHASE_NAME + " TEXT, " +
                COLUMN_PURCHASE_COST + " TEXT, " + //I have this as text for now but it should probably be a double. Maybe we should just set it as int, and the first two places are cents.
                COLUMN_PURCHASE_TIMEDATE + " TEXT," + //Not sure if there is a date setting for sqlite.
                COLUMN_PURCHASE_TAG + " TEXT" + ")"; //This one I think is fine.
    }
}

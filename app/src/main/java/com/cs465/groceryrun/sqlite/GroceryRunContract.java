package com.cs465.groceryrun.sqlite;

import android.provider.BaseColumns;
import android.widget.Toast;

/**
 * Created by tdw6193 on 11/29/2015.
 */
public class GroceryRunContract {

    public static final  int    DATABASE_VERSION = 3;
    public static final  String DATABASE_NAME = "groceryrun.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";

    public GroceryRunContract () {}

    /** Represents a transaction table */
    public static abstract class TransactionTable implements BaseColumns {

        public static final String TABLE_NAME = "expense"; //?????
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_PERSON = "person";
        public static final String COLUMN_NAME_ROLE = "role";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_DUEDATE = "duedate";
        public static final String COLUMN_NAME_STATUS = "status";
        public static final String COLUMN_NAME_RATING = "rating";
        public static final String COLUMN_NAME_AMOUNT = "amount";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_TITLE    + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_PERSON  + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_ROLE    + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_DATE    + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_DUEDATE + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_STATUS  + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_RATING  + REAL_TYPE + COMMA_SEP +
                COLUMN_NAME_AMOUNT  + REAL_TYPE + ");";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

}

package com.cs465.groceryrun.sqlite;

import android.provider.BaseColumns;

/**
 * Created by tdw6193 on 11/29/2015.
 */
public class GroceryRunContract {

    public static final  int    DATABASE_VERSION = 1;
    public static final  String DATABASE_NAME = "groceryrun.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";

    public GroceryRunContract () {}

    /** Represents a transaction table */
    public static abstract class TransactionTable implements BaseColumns {

        public static final String TABLE_NAME = "transactions";
        public static final String COLUMN_NAME_TIMESTAMP = "timestamp";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_PERSON = "person";
        public static final String COLUMN_NAME_ROLE = "role";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_DUE_DATE = "due_date";
        public static final String COLUMN_NAME_DUE_TIME = "due_time";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_NOTE = "note";
        public static final String COLUMN_NAME_STATUS = "status";
        public static final String COLUMN_NAME_RATING = "rating";
        public static final String COLUMN_NAME_GROCERY_PRICE = "grocery_price";
        public static final String COLUMN_NAME_GRATUITY = "gratuity";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_TIMESTAMP       + TEXT_TYPE     + COMMA_SEP +
                COLUMN_NAME_TITLE           + TEXT_TYPE     + COMMA_SEP +
                COLUMN_NAME_PERSON          + TEXT_TYPE     + COMMA_SEP +
                COLUMN_NAME_ROLE            + TEXT_TYPE     + COMMA_SEP +
                COLUMN_NAME_DATE            + TEXT_TYPE     + COMMA_SEP +
                COLUMN_NAME_DUE_DATE        + TEXT_TYPE     + COMMA_SEP +
                COLUMN_NAME_DUE_TIME        + INTEGER_TYPE  + COMMA_SEP +
                COLUMN_NAME_ADDRESS         + TEXT_TYPE     + COMMA_SEP +
                COLUMN_NAME_NOTE            + TEXT_TYPE     + COMMA_SEP +
                COLUMN_NAME_STATUS          + TEXT_TYPE     + COMMA_SEP +
                COLUMN_NAME_RATING          + REAL_TYPE     + COMMA_SEP +
                COLUMN_NAME_GROCERY_PRICE   + REAL_TYPE     + COMMA_SEP +
                COLUMN_NAME_GRATUITY        + REAL_TYPE     + ");";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static abstract class GroceryListItemTable implements BaseColumns {

        public static final String TABLE_NAME = "grocery_list_items";
        public static final String COLUMN_NAME_ASSOCIATED_TRANSACTION_ID= "associated_transaction_id";
        public static final String COLUMN_NAME_ITEM = "item";
        public static final String COLUMN_NAME_ITEM_QUANTITY = "quantity";
        public static final String COLUMN_NAME_ITEM_BOUGHT = "bought";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_ASSOCIATED_TRANSACTION_ID   + TEXT_TYPE     + COMMA_SEP +
                COLUMN_NAME_ITEM                        + TEXT_TYPE     + COMMA_SEP +
                COLUMN_NAME_ITEM_QUANTITY               + INTEGER_TYPE  + COMMA_SEP +
                COLUMN_NAME_ITEM_BOUGHT                 + INTEGER_TYPE  +");";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

}

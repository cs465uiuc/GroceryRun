package com.cs465.groceryrun.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tdw6193 on 11/29/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, GroceryRunContract.DATABASE_NAME, null, GroceryRunContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(GroceryRunContract.TransactionTable.CREATE_TABLE);
        db.execSQL(GroceryRunContract.GroceryListItemTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(GroceryRunContract.TransactionTable.DELETE_TABLE);
        db.execSQL(GroceryRunContract.GroceryListItemTable.DELETE_TABLE);
        onCreate(db);
    }
}

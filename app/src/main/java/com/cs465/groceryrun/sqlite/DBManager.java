package com.cs465.groceryrun.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cs465.groceryrun.Utils.CalendarConverter;
import com.cs465.groceryrun.enums.GroceryListItem;
import com.cs465.groceryrun.sqlite.GroceryRunContract.TransactionTable;
import com.cs465.groceryrun.sqlite.GroceryRunContract.GroceryListItemTable;
import com.cs465.groceryrun.enums.Transaction;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by tdw6193 on 11/29/2015.
 */
public class DBManager {

    private DBHelper dbHelper = null;
    private SQLiteDatabase db = null;

    /**
     * Default Constructor
     */
    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
    }


    public ArrayList<Transaction> getAllTransactions(String limit) {
        db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        String[] columns = {TransactionTable._ID,
                            TransactionTable.COLUMN_NAME_TIMESTAMP,
                            TransactionTable.COLUMN_NAME_TITLE,
                            TransactionTable.COLUMN_NAME_PERSON,
                            TransactionTable.COLUMN_NAME_ROLE,
                            TransactionTable.COLUMN_NAME_DATE,
                            TransactionTable.COLUMN_NAME_DUE_DATE,
                            TransactionTable.COLUMN_NAME_DUE_TIME,
                            TransactionTable.COLUMN_NAME_ADDRESS,
                            TransactionTable.COLUMN_NAME_NOTE,
                            TransactionTable.COLUMN_NAME_STATUS,
                            TransactionTable.COLUMN_NAME_RATING,
                            TransactionTable.COLUMN_NAME_GROCERY_PRICE,
                            TransactionTable.COLUMN_NAME_GRATUITY, };

        String sortBy = TransactionTable.COLUMN_NAME_DATE + " DESC " + limit;

        Cursor c = queryDatabase(TransactionTable.TABLE_NAME, columns, null, null, null, null, sortBy);

        return loadTransactions(c);
    }

    private ArrayList<Transaction> loadTransactions(Cursor c) {
        ArrayList<GroceryListItem> allGroceryListItems = getAllGroceryListItems ("");

        ArrayList<Transaction> transactions = new ArrayList<Transaction>();

        c.moveToFirst();

        while (!c.isAfterLast()) {
            Transaction t = new Transaction();
            t.setId(c.getInt(c.getColumnIndexOrThrow(TransactionTable._ID)));
            t.setTimestamp(c.getString(c.getColumnIndexOrThrow(TransactionTable.COLUMN_NAME_TIMESTAMP)));
            t.setTitle(c.getString(c.getColumnIndexOrThrow(TransactionTable.COLUMN_NAME_TITLE)));
            t.setPerson(c.getString(c.getColumnIndexOrThrow(TransactionTable.COLUMN_NAME_PERSON)));
            t.setRole(c.getString(c.getColumnIndexOrThrow(TransactionTable.COLUMN_NAME_ROLE)));
            t.setDate(c.getString(c.getColumnIndexOrThrow(TransactionTable.COLUMN_NAME_DATE)));
            t.setDueDate(c.getString(c.getColumnIndexOrThrow(TransactionTable.COLUMN_NAME_DUE_DATE)));
            t.setDueTime(c.getInt(c.getColumnIndexOrThrow(TransactionTable.COLUMN_NAME_DUE_TIME)));
            t.setAddress(c.getString(c.getColumnIndexOrThrow(TransactionTable.COLUMN_NAME_ADDRESS)));
            t.setNote(c.getString(c.getColumnIndexOrThrow(TransactionTable.COLUMN_NAME_NOTE)));
            t.setStatus(c.getString(c.getColumnIndexOrThrow(TransactionTable.COLUMN_NAME_STATUS)));
            t.setRating(c.getDouble(c.getColumnIndexOrThrow(TransactionTable.COLUMN_NAME_RATING)));
            t.setGroceryPrice(c.getDouble(c.getColumnIndexOrThrow(TransactionTable.COLUMN_NAME_GROCERY_PRICE)));
            t.setGratuity(c.getDouble(c.getColumnIndexOrThrow(TransactionTable.COLUMN_NAME_GRATUITY)));

            ArrayList<GroceryListItem> tGList = new ArrayList<GroceryListItem>();
            for(int i=0; i<allGroceryListItems.size(); i++) {
                if(allGroceryListItems.get(i).getAssociatedTransactionID().equals(t.getTimestamp()))
                    tGList.add(allGroceryListItems.get(i));
            }
            t.setGroceryList(tGList);

            transactions.add(t);
            c.moveToNext();
        }

        return transactions;
    }


    public void addTransaction(String title, String person, String role, ArrayList<GroceryListItem> groceryList, String dueDate, String address, String note, double gratuity, int dueTime, double groceryPrice, String specificDate) {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TransactionTable.COLUMN_NAME_TITLE, title);
        values.put(TransactionTable.COLUMN_NAME_PERSON, person);
        values.put(TransactionTable.COLUMN_NAME_ROLE, role);
        if(specificDate != null)
            values.put(TransactionTable.COLUMN_NAME_DATE, specificDate);
        else
            values.put(TransactionTable.COLUMN_NAME_DATE, CalendarConverter.convertCalendarToString(Calendar.getInstance(), true));
        values.put(TransactionTable.COLUMN_NAME_DUE_DATE, dueDate);
        values.put(TransactionTable.COLUMN_NAME_DUE_TIME, dueTime);
        values.put(TransactionTable.COLUMN_NAME_ADDRESS, address);
        values.put(TransactionTable.COLUMN_NAME_NOTE, note);
        values.put(TransactionTable.COLUMN_NAME_STATUS, "Due");
        values.put(TransactionTable.COLUMN_NAME_RATING, 0.0);
        values.put(TransactionTable.COLUMN_NAME_GROCERY_PRICE, groceryPrice);
        values.put(TransactionTable.COLUMN_NAME_GRATUITY, gratuity);

        Long curTimestamp = System.currentTimeMillis()/1000;
        String timestampTxt = curTimestamp.toString();
        values.put(TransactionTable.COLUMN_NAME_TIMESTAMP, timestampTxt);

        for(int i=0; i<groceryList.size(); i++) {
            addGroceryListItem(timestampTxt, groceryList.get(i).getItem(), groceryList.get(i).getItemQuantity());
        }

        insert(TransactionTable.TABLE_NAME, null, values);
    }

    public void editTransaction(int id, String status, double rating) {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        if(status != null)
            values.put(TransactionTable.COLUMN_NAME_STATUS, status);
        if(rating >= 0f && rating <= 5f)
            values.put(TransactionTable.COLUMN_NAME_RATING, rating);

        String[] whereArgs = {Integer.toString(id), };

        db.update(TransactionTable.TABLE_NAME, values, "_id=?", whereArgs);
    }

    public void addGroceryListItem(String transactionID, String item, int quantity) {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(GroceryListItemTable.COLUMN_NAME_ASSOCIATED_TRANSACTION_ID, transactionID);
        values.put(GroceryListItemTable.COLUMN_NAME_ITEM, item);
        values.put(GroceryListItemTable.COLUMN_NAME_ITEM_QUANTITY, quantity);

        insert(GroceryListItemTable.TABLE_NAME, null, values);
    }

    public ArrayList<GroceryListItem> getAllGroceryListItems(String limit) {
        db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        String[] columns = {GroceryListItemTable._ID,
                GroceryListItemTable.COLUMN_NAME_ASSOCIATED_TRANSACTION_ID,
                GroceryListItemTable.COLUMN_NAME_ITEM,
                GroceryListItemTable.COLUMN_NAME_ITEM_QUANTITY, };

        String sortBy = GroceryListItemTable.COLUMN_NAME_ASSOCIATED_TRANSACTION_ID + " ASC;";

        Cursor c = queryDatabase(GroceryListItemTable.TABLE_NAME, columns, null, null, null, null, sortBy);

        return loadGroceryListItems(c);
    }

    private ArrayList<GroceryListItem> loadGroceryListItems (Cursor c) {
        ArrayList<GroceryListItem> groceryListItems = new ArrayList<GroceryListItem> ();

        c.moveToFirst();

        while (!c.isAfterLast()) {
            GroceryListItem gList = new GroceryListItem();
            gList.setId(c.getInt(c.getColumnIndexOrThrow(GroceryListItemTable._ID)));
            gList.setAssociatedTransactionID(c.getString(c.getColumnIndexOrThrow(GroceryListItemTable.COLUMN_NAME_ASSOCIATED_TRANSACTION_ID)));
            gList.setItem(c.getString(c.getColumnIndexOrThrow(GroceryListItemTable.COLUMN_NAME_ITEM)));
            gList.setItemQuantity(c.getInt(c.getColumnIndexOrThrow(GroceryListItemTable.COLUMN_NAME_ITEM_QUANTITY)));

            groceryListItems.add(gList);
            c.moveToNext();
        }

        return groceryListItems;
    }

    private Cursor queryDatabase(String tableName, String[] columns,
                                 String selection, String[] selectionArgs, String groupBy,
                                 String having, String orderBy) {

        Cursor c = db.query(tableName, // The table to query
                            columns, // The columns to return
                            selection, // The columns for the WHERE clause
                            selectionArgs, // The values for the WHERE clause
                            groupBy, // don't group the rows
                            having, // don't filter by row groups
                            orderBy // The sort order
        );

        return c;
    }

    private void insert(String tableName, String nullColumnHack, ContentValues values) {
        db.insert(tableName, nullColumnHack, values);
    }

}

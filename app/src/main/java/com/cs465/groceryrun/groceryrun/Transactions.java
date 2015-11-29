package com.cs465.groceryrun.groceryrun;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ExpandableListView;

import com.cs465.groceryrun.customexpandablelistview.ExpandableListViewAdapter;
import com.cs465.groceryrun.enums.Transaction;

public class Transactions extends AppCompatActivity {

    private ExpandableListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        ArrayList<Transaction> transactions = generateDummyData();

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.transaction_listview);


        if(!transactions.isEmpty()) {
            adapter = new ExpandableListViewAdapter(this, transactions);
            listView.setAdapter(adapter);
            listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    Intent intent = new Intent(Transactions.this, ViewTransaction.class);
                    TextView nameTxt = (TextView) v.findViewById(R.id.transaction_title);
                    String name = nameTxt.getText().toString();
                    TextView personTxt = (TextView) v.findViewById(R.id.transaction_person);
                    String person = personTxt.getText().toString();
                    TextView dateTxt = (TextView) v.findViewById(R.id.transaction_duedate);
                    String date = dateTxt.getText().toString();
                    intent.putExtra("TRANSACTION_NAME", name);
                    intent.putExtra("TRANSACTION_PERSON", person);
                    intent.putExtra("TRANSACTION_DATE", date);
                    startActivity(intent);
                    return false;
                }
            });

        }

    }

    private ArrayList<Transaction> generateDummyData () {
        ArrayList<Transaction> transactions = new ArrayList<>();

        Transaction transaction;

        transaction = new Transaction();
        transaction.setName("Others");
        transaction.setPerson("Julie");
        transaction.setIsShopper(false);
        transaction.setDate(new GregorianCalendar(2015, 10, 1));
        transaction.setDueDate(new GregorianCalendar(2015, 11, 3));
        transaction.setStatus("Arriving");
        transactions.add(transaction);

        transaction = new Transaction();
        transaction.setName("Audrie");
        transaction.setPerson("Shelby");
        transaction.setIsShopper(true);
        transaction.setDate(new GregorianCalendar(2015, 10, 1));
        transaction.setDueDate(new GregorianCalendar(2015, 10, 30));
        transaction.setStatus("Due");
        transactions.add(transaction);

        transaction = new Transaction();
        transaction.setName("Groceries");
        transaction.setPerson("Audrie");
        transaction.setIsShopper(true);
        transaction.setDate(new GregorianCalendar(2006, 2, 11));
        transaction.setDueDate(new GregorianCalendar(2006, 3, 18));
        transaction.setStatus("Confirmed");
        transactions.add(transaction);

        return transactions;
    }

    public void filterTrans(View v){
        Intent intent = new Intent(this,FilterTransaction.class);
        startActivity(intent);
    }

}

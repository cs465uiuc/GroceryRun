package com.cs465.groceryrun.groceryrun;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;

public class Transactions extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        Transaction transaction;
        GregorianCalendar gc;

        ArrayList<Transaction> transactions = new ArrayList<>();

        transaction = new Transaction();
        transaction.setName("dunno");
        transaction.setPerson("no one");
        gc = new GregorianCalendar(2006, 2, 11);
        transaction.setDate(gc);
        transaction.setStatus("Confirmed");
        transactions.add(transaction);

        transaction = new Transaction();
        transaction.setName("me");
        transaction.setPerson("someone else");
        gc = new GregorianCalendar(2015, 10, 1);
        transaction.setDate(gc);
        transaction.setStatus("Needs confirmation");
        transactions.add(transaction);

        final ListView listView = (ListView) findViewById(R.id.listView);
        TransactionAdapter adapter = new TransactionAdapter(this, transactions);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int p, long l) {
                Intent intent = new Intent(Transactions.this,ViewTransaction.class);
                TextView nameTxt = (TextView) v.findViewById(R.id.list_entry_name);
                String name = nameTxt.getText().toString();
                TextView personTxt = (TextView) v.findViewById(R.id.list_entry_person);
                String person = personTxt.getText().toString();
                TextView dateTxt = (TextView) v.findViewById(R.id.list_entry_date);
                String date = dateTxt.getText().toString();
                intent.putExtra("TRANSACTION_NAME", name);
                intent.putExtra("TRANSACTION_PERSON", person);
                intent.putExtra("TRANSACTION_DATE", date);
                startActivity(intent);
            }
        });
    }

    public void filterTrans(View v){
        Intent intent = new Intent(this,FilterTransaction.class);
        startActivity(intent);
    }
}

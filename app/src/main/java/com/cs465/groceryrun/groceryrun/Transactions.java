package com.cs465.groceryrun.groceryrun;

import java.util.ArrayList;
import java.util.Calendar;
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

    private String filterType, filterStatus, filterTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        filterType = "All";
        filterStatus = "All";
        filterTime = "All";

        Intent intent = getIntent();
        if(intent.getStringExtra("FILTER_TYPE") != null)
            filterType = intent.getStringExtra("FILTER_TYPE");
        if(intent.getStringExtra("FILTER_STATUS") != null)
            filterStatus = intent.getStringExtra("FILTER_STATUS");
        if(intent.getStringExtra("FILTER_TIME") != null)
            filterTime = intent.getStringExtra("FILTER_TIME");

        ArrayList<Transaction> transactions = generateDummyData();
        transactions = filterTransactions(transactions);

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

    private ArrayList<Transaction> filterTransactions (ArrayList<Transaction> allTransactions) {
        ArrayList<Transaction>  filteredTransactions = filterTransactionType(allTransactions);
        filteredTransactions = filterTransactionStatus(filteredTransactions);
        filteredTransactions = filterTransactionTime(filteredTransactions);
        return filteredTransactions;
    }

    private ArrayList<Transaction> filterTransactionType (ArrayList<Transaction> Transactions) {
        ArrayList<Transaction>  filteredTransactions = new ArrayList<>();
        for(Transaction transaction : Transactions) {
            if(filterType.equals("Requests") && !transaction.getIsShopper()) { //not a shopper or is a buyer
                filteredTransactions.add(transaction);
            } else if(filterType.equals("Jobs") && transaction.getIsShopper()) { //is a buyer
                filteredTransactions.add(transaction);
            } else if(filterType.equals("All")) {
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    private ArrayList<Transaction> filterTransactionStatus (ArrayList<Transaction> Transactions) {
        ArrayList<Transaction>  filteredTransactions = new ArrayList<>();
        for(Transaction transaction : Transactions) {
            if(filterStatus.equals("Completed") && (transaction.getStatus() == "Delivered" || transaction.getStatus() == "Confirmed")) {
                filteredTransactions.add(transaction);
            } else if(filterStatus.equals("In Progress") && !(transaction.getStatus() == "Delivered" || transaction.getStatus() == "Confirmed")) {
                filteredTransactions.add(transaction);
            } else if(filterStatus.equals("All")) {
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    private ArrayList<Transaction> filterTransactionTime (ArrayList<Transaction> Transactions) {
        ArrayList<Transaction>  filteredTransactions = new ArrayList<>();
        Calendar curDate = Calendar.getInstance();
        for(Transaction transaction : Transactions) {
            if(filterTime.equals("This Month") && transaction.getDate().get(Calendar.MONTH) == curDate.get(Calendar.MONTH)) {
                filteredTransactions.add(transaction);
            } else if(filterTime.equals("Last 6 Months")) {
                if(curDate.get(Calendar.MONTH) <= 6) {
                    if((transaction.getDate().get(Calendar.YEAR) == curDate.get(Calendar.YEAR) && transaction.getDate().get(Calendar.MONTH) <= curDate.get(Calendar.MONTH))
                    || (transaction.getDate().get(Calendar.YEAR) == (curDate.get(Calendar.YEAR) - 1) && transaction.getDate().get(Calendar.MONTH) >= (12 - (6 - curDate.get(Calendar.MONTH)))))
                        filteredTransactions.add(transaction);
                } else {
                    if(transaction.getDate().get(Calendar.MONTH) >= (curDate.get(Calendar.MONTH) - 6) && transaction.getDate().get(Calendar.MONTH) <= curDate.get(Calendar.MONTH))
                        filteredTransactions.add(transaction);
                }
            } else if (filterTime.equals("This Year") && transaction.getDate().get(Calendar.YEAR) == curDate.get(Calendar.YEAR)) {
                filteredTransactions.add(transaction);
            } else if(filterTime.equals("All")) {
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
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

        intent.putExtra("FILTER_TYPE", filterType);
        intent.putExtra("FILTER_STATUS", filterStatus);
        intent.putExtra("FILTER_TIME", filterTime);

        startActivity(intent);
    }

    public static String convertCalendarToString(GregorianCalendar gc) {
        int year = gc.get(Calendar.YEAR);
        int month = gc.get(Calendar.MONTH);
        int day = gc.get(Calendar.DAY_OF_MONTH);

        return Integer.toString(month) + '/' + Integer.toString(day) + '/' + Integer.toString(year);
    }

}

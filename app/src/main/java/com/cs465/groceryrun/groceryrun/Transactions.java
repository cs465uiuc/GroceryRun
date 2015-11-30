package com.cs465.groceryrun.groceryrun;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.cs465.groceryrun.Utils.CalendarConverter;
import com.cs465.groceryrun.customexpandablelistview.ExpandableListViewAdapter;
import com.cs465.groceryrun.enums.Transaction;
import com.cs465.groceryrun.sqlite.DBManager;

public class Transactions extends AppCompatActivity {

    private ExpandableListView listView;
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

        generateDummyData();
        ArrayList<Transaction> transactions = new DBManager(this).getAllTransactions("");
        transactions = filterTransactions(transactions);

        listView = (ExpandableListView) findViewById(R.id.transaction_listview);

        if(!transactions.isEmpty()) {
            adapter = new ExpandableListViewAdapter(this, transactions);
            listView.setAdapter(adapter);
            for(int i=0; i<adapter.getGroupCount(); i++)
                listView.expandGroup(i);

            listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    Transaction clickedTransaction = (Transaction) adapter.getChild(groupPosition, childPosition);
                    Intent intent = new Intent(Transactions.this, ViewTransaction.class);
                    intent.putExtra("TRANSACTION_ID", clickedTransaction.getId());
                    startActivity(intent);
                    /*
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
                    */
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
            if(filterType.equals("Requests") && transaction.getRole().equals("Buyer")) {
                filteredTransactions.add(transaction);
            } else if(filterType.equals("Jobs") && transaction.getRole().equals("Shopper")) {
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
            String transactionDate = transaction.getDate();
            if(filterTime.equals("This Month")
            && CalendarConverter.convertCalendarStrToInt(transactionDate, CalendarConverter.MONTH) == CalendarConverter.convertCalendarToInt(curDate, CalendarConverter.MONTH, true)) {
                filteredTransactions.add(transaction);
            } else if(filterTime.equals("Last 6 Months")) {
                if(CalendarConverter.convertCalendarToInt(curDate, CalendarConverter.MONTH, true) <= 6) {
                    if((CalendarConverter.convertCalendarStrToInt(transactionDate, CalendarConverter.YEAR) == CalendarConverter.convertCalendarToInt(curDate, CalendarConverter.YEAR, true)
                    && CalendarConverter.convertCalendarStrToInt(transactionDate, CalendarConverter.MONTH) <= CalendarConverter.convertCalendarToInt(curDate, CalendarConverter.MONTH, true))
                    || (CalendarConverter.convertCalendarStrToInt(transactionDate, CalendarConverter.YEAR) == CalendarConverter.convertCalendarToInt(curDate, CalendarConverter.YEAR, true) - 1
                    && CalendarConverter.convertCalendarStrToInt(transactionDate, CalendarConverter.MONTH) >= (12 - (6 - CalendarConverter.convertCalendarToInt(curDate, CalendarConverter.MONTH, true)))))
                        filteredTransactions.add(transaction);
                } else {
                    if(CalendarConverter.convertCalendarStrToInt(transactionDate, CalendarConverter.MONTH) >= CalendarConverter.convertCalendarToInt(curDate, CalendarConverter.MONTH, true) - 6
                    && CalendarConverter.convertCalendarStrToInt(transactionDate, CalendarConverter.MONTH) <= CalendarConverter.convertCalendarToInt(curDate, CalendarConverter.MONTH, true))
                        filteredTransactions.add(transaction);
                }
            } else if (filterTime.equals("This Year") && CalendarConverter.convertCalendarStrToInt(transactionDate, CalendarConverter.YEAR) == CalendarConverter.convertCalendarToInt(curDate, CalendarConverter.YEAR, true)) {
                filteredTransactions.add(transaction);
            } else if(filterTime.equals("All")) {
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    private void generateDummyData () {
        DBManager db = new DBManager(this);

        db.addTransaction(  "Grocery",
                            "Julie",
                            "Shopper",
                            CalendarConverter.convertCalendarIntToString(2015, 12, 1),
                            100.00,
                            null);
    }

    public void filterTrans(View v){
        Intent intent = new Intent(this,FilterTransaction.class);

        intent.putExtra("FILTER_TYPE", filterType);
        intent.putExtra("FILTER_STATUS", filterStatus);
        intent.putExtra("FILTER_TIME", filterTime);

        startActivity(intent);
    }
}

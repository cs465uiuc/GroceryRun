package com.cs465.groceryrun.groceryrun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cs465.groceryrun.enums.Transaction;
import com.cs465.groceryrun.sqlite.DBManager;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by YourName on 11/27/2015.
 */
public class ViewTransaction extends AppCompatActivity {
    /*
    String name = null;
    String buyer = null;
    String date = null;
    String status = null;
    double rating = -1.;
    */

    private String title;
    private String person;
    private String date;
    private String due_date;
    private String role;
    private String status;
    private double rating;
    private double amount;

    private int transactionID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_transaction);

        Intent intent = getIntent();
        transactionID = intent.getIntExtra("TRANSACTION_ID", -1);

        DBManager db = new DBManager(this);
        ArrayList<Transaction> allTransactions = db.getAllTransactions("");
        Transaction transaction = allTransactions.get(transactionID-1);

        if(transaction != null) {
            title = transaction.getTitle();
            person = transaction.getPerson();
            date = transaction.getDate();
            due_date = transaction.getDueDate();
            role = transaction.getRole();
            status = transaction.getStatus();
            rating = transaction.getRating();
            amount = transaction.getAmount();
        }

        TextView transactionTitle = (TextView) findViewById(R.id.transactionTitle);
        TextView transactionPerson = (TextView) findViewById(R.id.transactionPerson);
        TextView transactionAmount = (TextView) findViewById(R.id.transactionAmount);

        if(title != null)
            transactionTitle.setText(title);

        if(role != null && person != null) {
            if(role.equals("Shopper")) //2nd party is a buyer
                transactionPerson.setText("Requested by: " + person);
            else
                transactionPerson.setText("Shopper: " + person);
        }

        if(amount >= 0)
            transactionAmount.setText("Agreed fees: " + String.format("%.2f", amount));


        /*
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("TRANSACTION_NAME");
            buyer = extras.getString("TRANSACTION_PERSON");
            date = extras.getString("TRANSACTION_DATE");
            if(name != null && name.equals("dunno")) {
                status = "Confirmed";
                rating = 5.0;
            }
            else {
                status = "Needs confirmation";
            }
        }

        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar4);

        TextView nameText = (TextView) findViewById(R.id.textViewName);
        TextView personText = (TextView) findViewById(R.id.textViewPerson);
        TextView dateText = (TextView) findViewById(R.id.textViewDate);
        TextView statusText = (TextView) findViewById(R.id.textViewStatus);

        if(name != null) {
            nameText.setText(name);
        }
        else {
            nameText.setText("error");
        }

        if(buyer != null) {
            personText.setText(buyer);
        }
        else {
            personText.setText("error");
        }

        if(date != null) {
            dateText.setText(date);
        }
        else {
            dateText.setText("error");
        }

        if(status != null) {
            statusText.setText(status);
            if(status.equals("Confirmed")) {
                confirmButton.setVisibility(View.GONE);
                ratingBar.setRating((float) rating);
            }
            else {
                ratingBar.setVisibility(View.GONE);
                confirmButton.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmTrans(v);
                    }
                });
            }
        }
        else {
            statusText.setText("error");
        }
        */
    }

    public void backToOverview(View v){
        Intent intent = new Intent(this, Transactions.class);
        startActivity(intent);
    }

    public void confirmTrans(View v){
        Intent intent = new Intent(this, ConfirmTransaction.class);
        intent.putExtra("TRANSACTION_ID", transactionID);
        intent.putExtra("FROM", "detailview");
        startActivity(intent);
    }
}

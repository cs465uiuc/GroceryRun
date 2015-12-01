package com.cs465.groceryrun.groceryrun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cs465.groceryrun.enums.Transaction;
import com.cs465.groceryrun.sqlite.DBManager;


import java.util.ArrayList;

/**
 * Created by tdw6193 on 11/27/2015.
 */
public class ViewTransaction extends AppCompatActivity {

    private String title;
    private String person;
    private String date;
    private String due_date;
    private String role;
    private String status;
    private double rating;
    private double amount;

    private DBManager db;
    private int transactionID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_transaction);

        Intent intent = getIntent();
        transactionID = intent.getIntExtra("TRANSACTION_ID", -1);

        db = new DBManager(this);
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

        TextView reqestDateText = (TextView) findViewById(R.id.requestDateText);
        TextView dueDateText = (TextView) findViewById(R.id.dueDateText);
        RadioGroup progressButtonGroup = (RadioGroup) findViewById(R.id.progreeButtonGroup);
        RadioButton requestReceivedBtn = (RadioButton) findViewById(R.id.progressRequestReceived);
        RadioButton shoppingBtn = (RadioButton) findViewById(R.id.progressShopping);
        RadioButton deliveringBtn = (RadioButton) findViewById(R.id.progressDelivering);
        RadioButton deliveredBtn = (RadioButton) findViewById(R.id.progressDelivered);
        ProgressBar transactionProgress = (ProgressBar) findViewById(R.id.transactionProgress);
        Button confirmBtn = (Button) findViewById(R.id.dtransaction_confirmBtn);
        RatingBar rateBar = (RatingBar) findViewById(R.id.transactionRate);

        TextView transactionTitle = (TextView) findViewById(R.id.transactionTitle);
        TextView transactionPerson = (TextView) findViewById(R.id.transactionPerson);
        TextView transactionAmount = (TextView) findViewById(R.id.transactionAmount);

        if(date != null)
            reqestDateText.setText("Requested\n" + date);
        if(due_date != null)
            dueDateText.setText("Due\n" + due_date);

        if(rating >= 0)
            rateBar.setRating((float) rating);

        confirmBtn.setVisibility(View.GONE);
        rateBar.setVisibility(View.GONE);
        if(status != null) {
            if (status.equals("Request Received"))
                transactionProgress.setProgress(Transaction.PROGRESS_REQUEST_RECEIVED);
            else if (status.equals("Shopping"))
                transactionProgress.setProgress(Transaction.PROGRESS_SHOPPING);
            else if (status.equals("Delivering"))
                transactionProgress.setProgress(Transaction.PROGRESS_DELIVERING);
            else if (status.equals("Delivered")) {
                transactionProgress.setProgress(Transaction.PROGRESS_DELIVERED);
                progressButtonGroup.setVisibility(View.GONE);
                confirmBtn.setVisibility(View.VISIBLE);
            }
            else if (status.equals("Confirmed")) {
                transactionProgress.setProgress(Transaction.PROGRESS_CONFIRMED);
                progressButtonGroup.setVisibility(View.GONE);
                rateBar.setVisibility(View.VISIBLE);
            }
            else
                transactionProgress.setProgress(0);
        }


        if(role.equals("Buyer")) {
            progressButtonGroup.setVisibility(View.GONE);
        } else if(!status.equals("Delivered") && !status.equals("Confirmed")) {
            progressButtonGroup.setVisibility(View.VISIBLE);
            requestReceivedBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.editTransaction(transactionID, "Request Received", -1);
                    refreshActivity();
                }
            });
            shoppingBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.editTransaction(transactionID, "Shopping", -1);
                    refreshActivity();
                }
            });
            deliveringBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.editTransaction(transactionID, "Delivering", -1);
                    refreshActivity();
                }
            });
            deliveredBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.editTransaction(transactionID, "Delivered", -1);
                    refreshActivity();
                }
            });
        }

        if(title != null)
            transactionTitle.setText(title);

        if(role != null && person != null) {
            if(role.equals("Shopper")) //2nd party is a buyer
                transactionPerson.setText("Requested by: " + person);
            else
                transactionPerson.setText("Shopper: " + person);
        }

        if(amount >= 0)
            transactionAmount.setText("Agreed fees: $" + String.format("%.2f", amount));

    }

    private void refreshActivity () {
        Intent intent = getIntent();
        intent.putExtra("TRANSACTION_ID", transactionID);
        finish();
        startActivity(intent);
    }

    public void confirmTrans(View v){
        Intent intent = new Intent(this, ConfirmTransaction.class);
        intent.putExtra("TRANSACTION_ID", transactionID);
        intent.putExtra("FROM", "detailview");
        startActivity(intent);
    }

    public void backToOverview(View v){
        Intent intent = new Intent(this, Transactions.class);
        startActivity(intent);
    }
}

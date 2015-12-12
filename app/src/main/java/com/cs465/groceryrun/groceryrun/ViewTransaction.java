package com.cs465.groceryrun.groceryrun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cs465.groceryrun.customexpandablelistview.TransactionGroceryListviewAdapter;
import com.cs465.groceryrun.enums.GroceryListItem;
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
    private int due_time;
    private String role;
    private ArrayList<GroceryListItem> groceryList;
    private String note;
    private String address;
    private String status;
    private double rating;
    private double grocery_price;
    private double gratuity;

    private DBManager db;
    private int transactionID;

    private TransactionGroceryListviewAdapter adapter;

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
            due_time = transaction.getDueTime();
            role = transaction.getRole();
            groceryList = transaction.getGroceryList();
            note = transaction.getNote();
            address = transaction.getAddress();
            status = transaction.getStatus();
            rating = transaction.getRating();
            grocery_price = transaction.getGroceryPrice();
            gratuity = transaction.getGratuity();
        }

        ImageView roleImage = (ImageView) findViewById(R.id.roleImage);
        TextView reqestDateText = (TextView) findViewById(R.id.requestDateText);
        TextView dueDateText = (TextView) findViewById(R.id.dueDateText);
        TextView progressText = (TextView) findViewById(R.id.progressText);
        RadioGroup progressButtonGroup = (RadioGroup) findViewById(R.id.progreeButtonGroup);
        RadioButton requestReceivedBtn = (RadioButton) findViewById(R.id.progressRequestReceived);
        RadioButton shoppingBtn = (RadioButton) findViewById(R.id.progressShopping);
        RadioButton deliveringBtn = (RadioButton) findViewById(R.id.progressDelivering);
        RadioButton deliveredBtn = (RadioButton) findViewById(R.id.progressDelivered);
        ProgressBar transactionProgress = (ProgressBar) findViewById(R.id.transactionProgress);
        ImageButton confirmBtn = (ImageButton) findViewById(R.id.dtransaction_confirmBtn);
        RatingBar rateBar = (RatingBar) findViewById(R.id.transactionRate);

        TextView transactionTitle = (TextView) findViewById(R.id.transactionTitle);
        TextView transactionGroceryPrice = (TextView) findViewById(R.id.transactionGroceryPrice);
        TextView transactionNote = (TextView) findViewById(R.id.transactionNote);
        ListView transactionGroceryList = (ListView) findViewById(R.id.transactionGroceryList);
        TextView transactionPerson = (TextView) findViewById(R.id.transactionPerson);
        TextView transactionGratuity = (TextView) findViewById(R.id.transactionGratuity);
        TextView transactionAddress = (TextView) findViewById(R.id.transactionAddress);

        if(date != null)
            reqestDateText.setText("Requested\n" + date);
        if(due_date != null && due_time >= 0) {
            String dueTimeText;
            if(due_time > 12)
                dueTimeText = Integer.toString(due_time-12) + " pm";
            else if(due_time == 12)
                dueTimeText = Integer.toString(due_time) + " pm";
            else
                dueTimeText = Integer.toString(due_time) + " am";
            dueDateText.setText("Due\n" + due_date + "\n" + dueTimeText);
        }

        if(rating >= 0)
            rateBar.setRating((float) rating);

        confirmBtn.setVisibility(View.GONE);
        rateBar.setVisibility(View.GONE);
        if(status != null) {

            if(status.equals("Due"))
                progressText.setText("Request Received");
            else
                progressText.setText(status);

            if (status.equals("Request Received") || status.equals("Due")) {
                transactionProgress.setProgress(Transaction.PROGRESS_REQUEST_RECEIVED);
                progressText.setTextColor(getResources().getColor(R.color.Black));
                requestReceivedBtn.setChecked(true);
            } else if (status.equals("Shopping")) {
                transactionProgress.setProgress(Transaction.PROGRESS_SHOPPING);
                progressText.setTextColor(getResources().getColor(R.color.Black));
                shoppingBtn.setChecked(true);
            } else if (status.equals("Delivering")) {
                transactionProgress.setProgress(Transaction.PROGRESS_DELIVERING);
                progressText.setTextColor(getResources().getColor(R.color.Black));
                deliveringBtn.setChecked(true);
            } else if (status.equals("Delivered")) {
                transactionProgress.setProgress(Transaction.PROGRESS_DELIVERED);
                progressText.setTextColor(getResources().getColor(R.color.Gray));
                progressButtonGroup.setVisibility(View.GONE);
                confirmBtn.setVisibility(View.VISIBLE);
            } else if (status.equals("Confirmed")) {
                transactionProgress.setProgress(Transaction.PROGRESS_CONFIRMED);
                progressText.setTextColor(getResources().getColor(R.color.FlatGreen));
                progressButtonGroup.setVisibility(View.GONE);
                rateBar.setVisibility(View.VISIBLE);
            } else
                transactionProgress.setProgress(0);
        }

        if(role.equals("Buyer")) {
            roleImage.setImageResource(R.mipmap.icon_request);

            progressButtonGroup.setVisibility(View.GONE);
        } else {
            roleImage.setImageResource(R.mipmap.icon_delivery);

            if(!status.equals("Delivered") && !status.equals("Confirmed")) {
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
        }

        if(title != null)
            transactionTitle.setText(title);

        if(groceryList != null && groceryList.size() > 0) {
            adapter = new TransactionGroceryListviewAdapter(this, role, groceryList);
            transactionGroceryList.setAdapter(adapter);
            transactionGroceryList.setDivider(null);
        }

        if(grocery_price >= 0) {
            transactionGroceryPrice.setText("Approximate Price: $" + String.format("%.2f", grocery_price));
        } else {
            transactionGroceryPrice.setText("Approximate Price: N/A");
        }

        if(note != null) {
            if(note.equals(""))
                transactionNote.setText("Note: none");
            else
                transactionNote.setText("Note: " + note);
        }

        if(role != null && person != null) {
            if(role.equals("Shopper")) //2nd party is a buyer
                transactionPerson.setText("Requested by: " + person);
            else
                transactionPerson.setText("Shopper: " + person);
        }

        if(gratuity >= 0)
            transactionGratuity.setText("Gratuity: $" + String.format("%.2f", gratuity));

        if(address != null)
            transactionAddress.setText("Shipping to:\n\n   "+address);

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

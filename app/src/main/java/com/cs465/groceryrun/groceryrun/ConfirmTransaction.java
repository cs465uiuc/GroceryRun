package com.cs465.groceryrun.groceryrun;

import android.content.Intent;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cs465.groceryrun.sqlite.DBManager;

public class ConfirmTransaction extends AppCompatActivity {

    private int transactionID;
    private String from;

    private TextView confirmTxt;
    private RatingBar rBar;
    private Button applyButton;

    private boolean tConfirmed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_transaction);

        confirmTxt = (TextView) findViewById(R.id.confirmText);
        rBar = (RatingBar) findViewById(R.id.transactionRating);
        applyButton = (Button) findViewById(R.id.applyConfirmOrRating);

        rBar.setVisibility(View.GONE);
        tConfirmed = false;

        Intent intent = getIntent();
        transactionID = intent.getIntExtra("TRANSACTION_ID", -1);
        from = intent.getStringExtra("FROM");
    }

    public void ConfirmOrRate(View v){
        DBManager db = new DBManager(this);
        if(!tConfirmed) {
            db.editTransaction(transactionID, "Confirmed", -1);

            confirmTxt.setVisibility(View.GONE);
            rBar.setVisibility(View.VISIBLE);
            applyButton.setText("Rate");
            tConfirmed = true;
        } else{
            db.editTransaction(transactionID, null, (double) rBar.getRating());

            Intent intent;
            if(from.equals("detailview")) {
                intent = new Intent(this, ViewTransaction.class);
                intent.putExtra("TRANSACTION_ID", transactionID);
            } else {
                intent = new Intent(this, Transactions.class);
            }

            startActivity(intent);
        }
    }
}

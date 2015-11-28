package com.cs465.groceryrun.groceryrun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by YourName on 11/27/2015.
 */
public class ViewTransaction extends AppCompatActivity {
    String name = null;
    String buyer = null;
    String date = null;
    String status = null;
    double rating = -1.;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_transaction);
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
    }

    public void confirmTrans(View v){
        Intent intent = new Intent(this,ConfirmTransaction.class);
        startActivity(intent);
    }
}

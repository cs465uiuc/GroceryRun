package com.cs465.groceryrun.groceryrun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class AvailabilityInfo extends Activity {
    private String person, price, date_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability_info);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            person = extras.getString("AVAILABILITY_PERSON");
            price = extras.getString("AVAILABILITY_PRICE");
            date_time = extras.getString("AVAILABILITY_DATE");
        }

        TextView personText = (TextView) findViewById(R.id.textView24);
        TextView mostRecentReviewText = (TextView) findViewById(R.id.textView29);
        RatingBar ratingBar1 = (RatingBar) findViewById(R.id.ratingBar5);

        if(person.equals("a person")) {
            personText.setText("a person");
            ratingBar1.setRating((float) 0.5);
            mostRecentReviewText.setText("this man murdered my dog :(");
        }
        else if(person.equals("some guy")) {
            personText.setText("some guy");
            ratingBar1.setRating((float) 4.5);
            mostRecentReviewText.setText("this man bought me a new dog :)");
        }
    }


    public void back(View v){
        Intent intent = new Intent(this,SearchAvailabilities.class);
        intent.putExtra("AVAILABILITY_PERSON", person);
        startActivity(intent);
    }

    public void createRequest(View v){
        Intent intent = new Intent(this,CreateRequest.class);
        intent.putExtra("AVAILABILITY_PERSON", person);
        intent.putExtra("AVAILABILITY_PRICE", price);
        startActivity(intent);
    }

    public void chat(View v){
        Intent intent = new Intent(this,SearchAvailabilities.class);
        startActivity(intent);
    }
}

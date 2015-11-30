package com.cs465.groceryrun.groceryrun;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class Ratings extends Activity {
    String person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            person = extras.getString("AVAILABILITY_PERSON");
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
}

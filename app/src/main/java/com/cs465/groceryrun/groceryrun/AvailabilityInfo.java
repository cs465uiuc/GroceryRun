package com.cs465.groceryrun.groceryrun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
    }

    public void ratings(View v){
        Intent intent = new Intent(this,Ratings.class);
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
        Intent intent = new Intent(this,Chat.class);
        startActivity(intent);
    }
}

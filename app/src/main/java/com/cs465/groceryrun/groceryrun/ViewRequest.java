package com.cs465.groceryrun.groceryrun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cs465.groceryrun.customexpandablelistview.TransactionGroceryListviewAdapter;

public class ViewRequest extends Activity {
    String person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            person = extras.getString("REQUEST_PERSON");
        }
        TextView personText = (TextView) findViewById(R.id.userNameRequest);
        RatingBar requestRatingBar = (RatingBar) findViewById(R.id.userRating);
        ListView groceryList = (ListView) findViewById(R.id.requestGroceryListView);
        TextView approxText = (TextView) findViewById(R.id.approxPrice);
        TextView gratuityText = (TextView) findViewById(R.id.gratuityRequestId);
        TextView remarksText = (TextView) findViewById(R.id.remarksRequestId);
        TextView dayText = (TextView) findViewById(R.id.dayDateRequest);
        TextView monthText = (TextView) findViewById(R.id.monthDateRequest);
        TextView yearText = (TextView) findViewById(R.id.yearDateRequest);
        TextView startTimeText = (TextView) findViewById(R.id.startTimeRequest);
        TextView endTimeText = (TextView) findViewById(R.id.endTimeRequest);
        TextView locationText = (TextView) findViewById(R.id.locationRequest);

        if(person.equals("some guy")) {
            personText.setText("User: some guy");
            approxText.setText("0");
            gratuityText.setText("0");
            remarksText.setText("Tell me I'm beautiful");
            requestRatingBar.setRating((float) 4.7);
            locationText.setText("not exactly sure");
            startTimeText.setText("10");
            endTimeText.setText("11");
            dayText.setText("21");
            monthText.setText("9");
            yearText.setText("2999");

            String[] data = {"egg whites", "egg whites", "egg whites", "egg whites"};

            groceryList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data));
        }
        else {
            personText.setText("User: a person");
            approxText.setText("100000");
            gratuityText.setText("300");
            remarksText.setText("");
            requestRatingBar.setRating((float) 2.9);
            locationText.setText("place");
            startTimeText.setText("5");
            endTimeText.setText("7");
            dayText.setText("21");
            monthText.setText("9");
            yearText.setText("2000");

            String[] data = {"Vespene gas", "minerals", "more minerals"};

            groceryList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data));
        }
    }

    public void backToRequests(View v){
        Intent intent = new Intent(this, SearchRequests.class);
        startActivity(intent);
    }
}

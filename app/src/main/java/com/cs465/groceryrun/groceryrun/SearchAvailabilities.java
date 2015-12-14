package com.cs465.groceryrun.groceryrun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cs465.groceryrun.customexpandablelistview.AvailabilityExpandableListViewAdapter2;
import com.cs465.groceryrun.customexpandablelistview.TransactionExpandableListViewAdapter;
import com.cs465.groceryrun.enums.Availability;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SearchAvailabilities extends Activity {
    private ExpandableListView listView;
    private AvailabilityExpandableListViewAdapter2 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_availabilities);
        Availability availability;
        GregorianCalendar gc;

        ArrayList<Availability> availabilities = new ArrayList<>();

        availability = new Availability();
        availability.setPerson("a person");
        gc = new GregorianCalendar(2000, 9, 21);
        availability.setDate(gc);
        availability.setPrice(100000);
        availability.setStartTime(5);
        availability.setEndTime(7);
        availability.setLocation("25,000 CH");
        availabilities.add(availability);

        availability = new Availability();
        availability.setPerson("some guy");
        gc = new GregorianCalendar(2999, 9, 21);
        availability.setDate(gc);
        availability.setPrice(0);
        availability.setStartTime(10);
        availability.setEndTime(11);
        availability.setLocation("there");
        availabilities.add(availability);

        listView = (ExpandableListView) findViewById(R.id.availability_listview);

        if(!availabilities.isEmpty()) {
            adapter = new AvailabilityExpandableListViewAdapter2(this, availabilities);
            listView.setAdapter(adapter);
            for(int i=0; i<adapter.getGroupCount(); i++)
                listView.expandGroup(i);

            listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    Availability clickedAvailability = (Availability) adapter.getChild(groupPosition, childPosition);
                    Intent intent = new Intent(SearchAvailabilities.this, AvailabilityInfo.class);
                    intent.putExtra("AVAILABILITY_PRICE", Double.toString(clickedAvailability.getPrice()));
                    intent.putExtra("AVAILABILITY_PERSON", clickedAvailability.getPerson());
                    startActivity(intent);
                    return false;
                }
            });
        }
    }

    public void filterAvailabilities(View v){
        Intent intent = new Intent(this,FilterAvailability.class);
        startActivity(intent);
    }

    public void back(View v){
        Intent intent = new Intent(this,Requester.class);
        startActivity(intent);
    }
}

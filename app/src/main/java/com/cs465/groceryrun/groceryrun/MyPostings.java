package com.cs465.groceryrun.groceryrun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.cs465.groceryrun.customexpandablelistview.AvailabilityExpandableListViewAdapter;
import com.cs465.groceryrun.enums.Availability;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class MyPostings extends Activity {
    private ExpandableListView listView;
    private AvailabilityExpandableListViewAdapter adapter;
    String price = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_postings);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            price = extras.getString("AVAILABILITY_PRICE_DELETE");
        }
        System.out.println(price);

        Availability availability;
        GregorianCalendar gc;

        ArrayList<Availability> availabilities = new ArrayList<>();

        if(price == null) {
            availability = new Availability();
            availability.setPerson("Hello World");
            gc = new GregorianCalendar(2005, 4, 22);
            availability.setDate(gc);
            availability.setLocation("Phobos");
            availability.setPrice(1);
            availability.setStartTime(8);
            availability.setEndTime(9);
            availabilities.add(availability);

            availability = new Availability();
            availability.setPerson("Hello World");
            gc = new GregorianCalendar(2010, 6, 6);
            availability.setDate(gc);
            availability.setLocation("Phobos");
            availability.setPrice(2);
            availability.setStartTime(2);
            availability.setEndTime(3);
            availabilities.add(availability);
        }
        else {
            if(price.equals('1')) {
                availability = new Availability();
                availability.setPerson("Hello World");
                gc = new GregorianCalendar(2010, 6, 6);
                availability.setDate(gc);
                availability.setLocation("Phobos");
                availability.setPrice(2);
                availability.setStartTime(2);
                availability.setEndTime(3);
                availabilities.add(availability);
            }
            else {
                availability = new Availability();
                availability.setPerson("Hello World");
                gc = new GregorianCalendar(2005, 4, 22);
                availability.setDate(gc);
                availability.setLocation("Phobos");
                availability.setPrice(1);
                availability.setStartTime(8);
                availability.setEndTime(9);
                availabilities.add(availability);
            }
        }

        listView = (ExpandableListView) findViewById(R.id.availability_listview);

        if(!availabilities.isEmpty()) {
            adapter = new AvailabilityExpandableListViewAdapter(this, availabilities);
            listView.setAdapter(adapter);
            for(int i=0; i<adapter.getGroupCount(); i++)
                listView.expandGroup(i);

            listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    Availability clickedAvailability = (Availability) adapter.getChild(groupPosition, childPosition);
                    Intent intent = new Intent(MyPostings.this, ViewMyPosting.class);
                    intent.putExtra("AVAILABILITY_PRICE", Double.toString(clickedAvailability.getPrice()));
                    startActivity(intent);
                    return false;
                }
            });
        }
    }

    public void backToDeliverer(View v){
        Intent intent = new Intent(this, Deliverer.class);
        startActivity(intent);
    }

    public void filterAvailabilities(View v){

    }
}

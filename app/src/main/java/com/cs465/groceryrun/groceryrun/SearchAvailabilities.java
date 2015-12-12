package com.cs465.groceryrun.groceryrun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.cs465.groceryrun.customexpandablelistview.TransactionExpandableListViewAdapter;
import com.cs465.groceryrun.enums.Availability;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SearchAvailabilities extends Activity {

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
        availabilities.add(availability);

        availability = new Availability();
        availability.setPerson("some guy");
        gc = new GregorianCalendar(2999, 9, 21);
        availability.setDate(gc);
        availability.setPrice(0);
        availability.setStartTime(10);
        availability.setEndTime(11);
        availabilities.add(availability);

        final ListView listView = (ListView) findViewById(R.id.availability_listview);
        SimpleAvailabilityAdapter adapter = new SimpleAvailabilityAdapter(this, availabilities);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int p, long id) {
                Intent intent = new Intent(SearchAvailabilities.this, AvailabilityInfo.class);
                TextView personTxt = (TextView) v.findViewById(R.id.list_entry_person_and_price);
                String personAndPrice = personTxt.getText().toString();
                TextView dateTxt = (TextView) v.findViewById(R.id.list_entry_date);
                String date = dateTxt.getText().toString();
                int colonPos = personAndPrice.indexOf(':');
                String person = personAndPrice.substring(0, colonPos);
                String price = personAndPrice.substring(colonPos + 3);

                intent.putExtra("AVAILABILITY_PERSON", person);
                intent.putExtra("AVAILABILITY_PRICE", price);
                intent.putExtra("AVAILABILITY_DATE", date);
                startActivity(intent);
            }
        });
    }

    public void filterAvailabilities(View v){
        Intent intent = new Intent(this,FilterAvailability.class);
        startActivity(intent);
    }
}

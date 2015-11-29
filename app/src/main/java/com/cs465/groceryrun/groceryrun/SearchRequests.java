package com.cs465.groceryrun.groceryrun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cs465.groceryrun.enums.Request;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SearchRequests extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_requests);
        Request request;
        GregorianCalendar gc;

        ArrayList<Request> requests = new ArrayList<>();

        request = new Request();
        request.setPerson("a person");
        gc = new GregorianCalendar(2000, 9, 21);
        request.setDate(gc);
        request.setLocation("place");
        request.setPrice(100000);
        request.setStartTime(5);
        request.setEndTime(7);
        requests.add(request);

        request = new Request();
        request.setPerson("some guy");
        gc = new GregorianCalendar(2999, 9, 21);
        request.setDate(gc);
        request.setLocation("not exactly sure");
        request.setPrice(0);
        request.setStartTime(10);
        request.setEndTime(11);
        requests.add(request);

        final ListView listView = (ListView) findViewById(R.id.request_listview);
        SimpleRequestAdapter adapter = new SimpleRequestAdapter(this, requests);
        listView.setAdapter(adapter);
    }

    public void filterRequests(View v){

    }

    public void startTransaction(View v) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}

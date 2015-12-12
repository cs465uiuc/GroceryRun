package com.cs465.groceryrun.groceryrun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import com.cs465.groceryrun.customexpandablelistview.RequestExpandableListViewAdapter;
import com.cs465.groceryrun.enums.Request;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SearchRequests extends AppCompatActivity {
    private ExpandableListView listView;
    private RequestExpandableListViewAdapter adapter;

    protected static String filterType, filterStatus, filterTime;

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

        listView = (ExpandableListView) findViewById(R.id.request_listview);

        if(!requests.isEmpty()) {
            adapter = new RequestExpandableListViewAdapter(this, requests);
            listView.setAdapter(adapter);
            for(int i=0; i<adapter.getGroupCount(); i++)
                listView.expandGroup(i);

            listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    Request clickedRequest = (Request) adapter.getChild(groupPosition, childPosition);
                    Intent intent = new Intent(SearchRequests.this, ViewRequest.class);
                    intent.putExtra("REQUEST_PERSON", clickedRequest.getPerson());
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

    public void filterRequests(View v){

    }
}

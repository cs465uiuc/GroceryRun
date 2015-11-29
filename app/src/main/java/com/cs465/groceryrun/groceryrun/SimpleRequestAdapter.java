package com.cs465.groceryrun.groceryrun;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cs465.groceryrun.enums.Request;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by YourName on 11/29/2015.
 */
public class SimpleRequestAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Request> requests;

    public SimpleRequestAdapter(Context context, ArrayList<Request> requests) {
        this.context = context;
        this.requests = requests;
    }

    @Override
    public int getCount() {
        return requests.size();
    }

    @Override
    public Object getItem(int position) {
        return requests.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = (View) inflater.inflate(
                    R.layout.activity_request_layout, null);
        }

        TextView date = (TextView)convertView.findViewById(R.id.list_entry_date);
        TextView person_and_price = (TextView)convertView.findViewById(R.id.list_entry_person_and_price);
        TextView location = (TextView)convertView.findViewById(R.id.list_entry_location);

        date.setText(convertCalendarToString(requests.get(position).getDate())
                   + ", "
                   + Integer.toString(requests.get(position).getStartTime())
                   + "-"
                   + Integer.toString(requests.get(position).getEndTime()));
        person_and_price.setText(requests.get(position).getPerson() + ": $" + Double.toString(requests.get(position).getPrice()));
        location.setText(requests.get(position).getLocation());

        return convertView;
    }

    private String convertCalendarToString(GregorianCalendar gc) {
        int year = gc.get(Calendar.YEAR);
        int month = gc.get(Calendar.MONTH);
        int day = gc.get(Calendar.DAY_OF_MONTH);

        return Integer.toString(month) + '/' + Integer.toString(day) + '/' + Integer.toString(year);
    }
}

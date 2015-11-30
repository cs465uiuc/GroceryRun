package com.cs465.groceryrun.groceryrun;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cs465.groceryrun.enums.Availability;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by YourName on 11/29/2015.
 */
public class SimpleAvailabilityAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Availability> availabilities;

    public SimpleAvailabilityAdapter(Context context, ArrayList<Availability> availabilities) {
        this.context = context;
        this.availabilities = availabilities;
    }

    @Override
    public int getCount() {
        return availabilities.size();
    }

    @Override
    public Object getItem(int position) {
        return availabilities.get(position);
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
                    R.layout.activity_availability_layout, null);
        }

        TextView date = (TextView)convertView.findViewById(R.id.list_entry_date);
        TextView person_and_price = (TextView)convertView.findViewById(R.id.list_entry_person_and_price);

        date.setText(convertCalendarToString(availabilities.get(position).getDate())
                   + ", "
                   + Integer.toString(availabilities.get(position).getStartTime())
                   + "-"
                   + Integer.toString(availabilities.get(position).getEndTime()));
        person_and_price.setText(availabilities.get(position).getPerson() + ": $" + Double.toString(availabilities.get(position).getPrice()));

        return convertView;
    }

    private String convertCalendarToString(GregorianCalendar gc) {
        int year = gc.get(Calendar.YEAR);
        int month = gc.get(Calendar.MONTH);
        int day = gc.get(Calendar.DAY_OF_MONTH);

        return Integer.toString(month) + '/' + Integer.toString(day) + '/' + Integer.toString(year);
    }
}

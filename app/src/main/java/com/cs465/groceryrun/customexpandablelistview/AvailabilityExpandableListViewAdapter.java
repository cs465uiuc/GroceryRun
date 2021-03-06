package com.cs465.groceryrun.customexpandablelistview;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs465.groceryrun.enums.Availability;
import com.cs465.groceryrun.groceryrun.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public class AvailabilityExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<String> headerList; //For dates
    private HashMap<String, ArrayList<Availability>> itemList;

    public AvailabilityExpandableListViewAdapter(Context context, ArrayList<Availability> items) {
        this.context = context;
        this.headerList = new ArrayList<String>();
        this.itemList = new HashMap<String, ArrayList<Availability>>();

        extractData(items);
    }

    private void extractData(List<Availability> availabilities) {

        for(Availability availability : availabilities) {
            String groupText = convertCalendarToString(availability.getDate());
            if(!itemList.containsKey(groupText)) {
                headerList.add(groupText);
                itemList.put(groupText, new ArrayList<Availability>());
            }
            itemList.get(groupText).add(availability);
        }
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.itemList.get(this.headerList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View view, ViewGroup parent) {

        final Availability child = (Availability) getChild(groupPosition, childPosition);
        final int childStartTime = child.getStartTime();
        final int childEndTime = child.getEndTime();
        final String childTime = Integer.toString(childStartTime)
                                  + "-"
                                  + Integer.toString(childEndTime)
                                  + "pm";
        final String childLocation = child.getLocation();

        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.expandable_listview_availability, null);
        }

        ImageView childImage = (ImageView) view.findViewById(R.id.availability_icon);
        childImage.setImageResource(R.mipmap.icon_delivery);

        TextView childText1 = (TextView) view.findViewById(R.id.availability_time);
        childText1.setText(childTime);

        TextView childText3 = (TextView) view.findViewById(R.id.availability_location);
        childText3.setText(childLocation);

        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.itemList.get(this.headerList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.headerList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.headerList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view,
                             ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);

        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.expandable_listview_group_availability, null);
        }

        TextView lblListHeader = (TextView) view.findViewById(R.id.availability_date_textview);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return view;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private String convertCalendarToString(GregorianCalendar gc) {
        int year = gc.get(Calendar.YEAR);
        int month = gc.get(Calendar.MONTH);
        int day = gc.get(Calendar.DAY_OF_MONTH);

        return Integer.toString(month) + '/' + Integer.toString(day) + '/' + Integer.toString(year);
    }
}

package com.cs465.groceryrun.customexpandablelistview;

import android.content.Context;

import android.graphics.Typeface;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs465.groceryrun.groceryrun.R;
import com.cs465.groceryrun.enums.Transaction;

import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<String> headerList; //For dates
    private HashMap<String, ArrayList<Transaction>> itemList;

    public ExpandableListViewAdapter(Context context, ArrayList<Transaction> items) {
        this.context = context;
        this.headerList = new ArrayList<String>();
        this.itemList = new HashMap<String, ArrayList<Transaction>>();

        extractData(items);
    }

    private void extractData(List<Transaction> transactions) {

        for(Transaction transaction : transactions) {
            String groupText = convertCalendarToString(transaction.getDate());
            if(!itemList.containsKey(groupText)) {
                headerList.add(groupText);
                itemList.put(groupText, new ArrayList<Transaction>());
            }
            itemList.get(groupText).add(transaction);
        }
    }

    private String convertCalendarToString(GregorianCalendar gc) {
        int year = gc.get(Calendar.YEAR);
        int month = gc.get(Calendar.MONTH);
        int day = gc.get(Calendar.DAY_OF_MONTH);

        return Integer.toString(month) + '/' + Integer.toString(day) + '/' + Integer.toString(year);
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

        Transaction child = (Transaction) getChild(groupPosition, childPosition);
        final String childTitle = child.getName();
        final String childPerson = child.getPerson();
        final String childStatus = child.getStatus();
        final String childDate = convertCalendarToString(child.getDueDate());

        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.expandable_listview_item, null);
        }

        ImageView childImage = (ImageView) view.findViewById(R.id.transaction_icon);
        if(child.getIsShopper())
            childImage.setImageResource(R.mipmap.icon_shopper);
        else
            childImage.setImageResource(R.mipmap.icon_buyer);

        TextView childText1 = (TextView) view.findViewById(R.id.transaction_title);
        childText1.setText(childTitle);

        TextView childText2 = (TextView) view.findViewById(R.id.transaction_person);
        childText2.setText(childPerson);

        TextView childText3 = (TextView) view.findViewById(R.id.transaction_status);
        childText3.setText(childStatus);

        TextView childText4 = (TextView) view.findViewById(R.id.transaction_duedate);
        childText4.setText(childDate);

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
            view = infalInflater.inflate(R.layout.expandable_listview_group, null);
        }

        TextView lblListHeader = (TextView) view.findViewById(R.id.transaction_date_textview);
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
}

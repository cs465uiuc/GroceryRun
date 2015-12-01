package com.cs465.groceryrun.customexpandablelistview;

import android.content.Context;

import android.content.Intent;
import android.graphics.Typeface;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs465.groceryrun.groceryrun.ConfirmTransaction;
import com.cs465.groceryrun.groceryrun.R;
import com.cs465.groceryrun.enums.Transaction;

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
            String groupText = transaction.getDate();
            if(!itemList.containsKey(groupText)) {
                headerList.add(groupText);
                itemList.put(groupText, new ArrayList<Transaction>());
            }
            itemList.get(groupText).add(transaction);
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

        final Transaction child = (Transaction) getChild(groupPosition, childPosition);
        final String childTitle = child.getTitle();
        final String childPerson = child.getPerson();
        final String childAmount = String.format("%.2f", child.getAmount());
        final String childStatus = child.getStatus();
        final String childDueDate = child.getDueDate();
        final String childRating = Double.toString(child.getRating());

        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.expandable_listview_item, null);
        }

        ImageView childImage = (ImageView) view.findViewById(R.id.transaction_icon);
        if(child.getRole().equals("Shopper"))
            childImage.setImageResource(R.mipmap.icon_shopper);
        else
            childImage.setImageResource(R.mipmap.icon_buyer);

        TextView childText1 = (TextView) view.findViewById(R.id.transaction_title);
        childText1.setText(childTitle);

        TextView childText2 = (TextView) view.findViewById(R.id.transaction_person);
        childText2.setText(childPerson + ", $" + childAmount);

        TextView childText3 = (TextView) view.findViewById(R.id.transaction_status);
        if(!childStatus.equals("Delivered") && !childStatus.equals("Confirmed"))
            childText3.setText("Due");
        else
            childText3.setText(childStatus);

        if(childStatus.equals("Delivered")) {
            TextView childText4 = (TextView) view.findViewById(R.id.transaction_duedate);
            childText4.setVisibility(View.GONE);
            ImageButton childBtn = (ImageButton) view.findViewById(R.id.otransaction_confirmBtn);
            childBtn.setVisibility(View.VISIBLE);
            childBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ConfirmTransaction.class);
                    intent.putExtra("TRANSACTION_ID", child.getId());
                    intent.putExtra("FROM", "overview");
                    context.startActivity(intent);
                }
            });
        } else if (childStatus.equals("Confirmed"))  {
            ImageButton childBtn = (ImageButton) view.findViewById(R.id.otransaction_confirmBtn);
            childBtn.setVisibility(View.GONE);
            TextView childText4 = (TextView) view.findViewById(R.id.transaction_duedate);
            childText4.setVisibility(View.VISIBLE);
            childText4.setText("Rating: " + childRating);
        } else {
            ImageButton childBtn = (ImageButton) view.findViewById(R.id.otransaction_confirmBtn);
            childBtn.setVisibility(View.GONE);
            TextView childText4 = (TextView) view.findViewById(R.id.transaction_duedate);
            childText4.setVisibility(View.VISIBLE);
            childText4.setText(childDueDate);
        }
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

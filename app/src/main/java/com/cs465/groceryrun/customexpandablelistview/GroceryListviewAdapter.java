package com.cs465.groceryrun.customexpandablelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.cs465.groceryrun.enums.GroceryListItem;
import com.cs465.groceryrun.groceryrun.R;

import java.util.ArrayList;

/**
 * Created by tdw6193 on 12/2/2015.
 */
public class GroceryListviewAdapter extends BaseAdapter {

    private Context context;
    private String role;
    private ArrayList<GroceryListItem> groceryItems;

    public GroceryListviewAdapter(Context context, String role, ArrayList<GroceryListItem> groceryItems) {
        this.context = context;
        this.role = role;
        this.groceryItems = groceryItems;
    }

    @Override
    public int getCount() {return groceryItems.size(); }

    @Override
    public Object getItem(int position) {
        return groceryItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {


        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = (View) inflater.inflate(R.layout.grocery_listview_item, null);
        }

        CheckBox groceryItemCheckbox = (CheckBox) view.findViewById(R.id.groceryItemCheckbox);
        if(role.equals("Shopper"))
            groceryItemCheckbox.setVisibility(View.VISIBLE);
        else
            groceryItemCheckbox.setVisibility(View.GONE);

        GroceryListItem gItem = groceryItems.get(position);
        TextView groceryItemText = (TextView) view.findViewById(R.id.groceryItemText);
        groceryItemText.setText(gItem.getItem() + " x " + Integer.toString(gItem.getItemQuantity()));

        return view;
    }
}

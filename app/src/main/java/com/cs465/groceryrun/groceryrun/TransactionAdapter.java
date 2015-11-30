package com.cs465.groceryrun.groceryrun;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cs465.groceryrun.enums.Transaction;

/**
 * Created by Oleksiy Kamenyev on 11/27/2015.
 */
public class TransactionAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Transaction> transactions;

    public TransactionAdapter(Context context, ArrayList<Transaction> transactions) {
        this.context = context;
        this.transactions = transactions;
    }

    @Override
    public int getCount() {
        return transactions.size();
    }

    @Override
    public Object getItem(int position) {
        return transactions.get(position);
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
                    R.layout.activity_transaction_layout, null);
        }

        TextView name = (TextView)convertView.findViewById(R.id.list_entry_name);
        TextView summary=(TextView)convertView.findViewById(R.id.list_entry_person);
        TextView date=(TextView)convertView.findViewById(R.id.list_entry_date);

        name.setText(transactions.get(position).getName());
        summary.setText(transactions.get(position).getPerson());
        date.setText(transactions.get(position).getDate());

        return convertView;
    }

    private String convertCalendarToString(GregorianCalendar gc) {
        int year = gc.get(Calendar.YEAR);
        int month = gc.get(Calendar.MONTH);
        int day = gc.get(Calendar.DAY_OF_MONTH);

        return Integer.toString(month) + '/' + Integer.toString(day) + '/' + Integer.toString(year);
    }
}

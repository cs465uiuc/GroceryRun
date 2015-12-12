package com.cs465.groceryrun.groceryrun;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class EditMyPosting extends Activity {
    private String date;
    private String price;
    private String startTime;
    private String endTime;
    private String location;
    private TextView dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_posting);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            date = extras.getString("AVAILABILITY_DATE");
            price = extras.getString("AVAILABILITY_PRICE");
            startTime = extras.getString("AVAILABILITY_START_TIME");
            endTime = extras.getString("AVAILABILITY_END_TIME");
            location = extras.getString("AVAILABILITY_LOCATION");
        }
        EditText startTimeText = (EditText) findViewById(R.id.startTimeMyPosting);
        EditText endTimeText = (EditText) findViewById(R.id.endTimeMyPosting);
        dateText = (TextView) findViewById(R.id.editDateMyPosting);
        EditText priceText = (EditText) findViewById(R.id.approxPriceEditMyPosting);
        EditText locationText = (EditText) findViewById(R.id.locationEditMyPosting);

        startTimeText.setText(startTime);
        endTimeText.setText(endTime);
        priceText.setText(price);
        locationText.setText(location);
        dateText.setText(date);
    }

    public void editDate(View v) {
        Calendar currentDate = Calendar.getInstance();
        int mYear = currentDate.get(Calendar.YEAR);
        int mMonth = currentDate.get(Calendar.MONTH);
        int mDay = currentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog availabilityDatePicker;
        availabilityDatePicker = new DatePickerDialog(EditMyPosting.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                selectedMonth += 1;
                dateText.setText(selectedDay + "/" + selectedMonth + "/" + selectedYear);
            }
        }, mYear, mMonth, mDay);
        availabilityDatePicker.setTitle("Select Date");
        availabilityDatePicker.show();
    }

    public void saveMyPosting(View v) {
        Intent intent = new Intent(this, ViewMyPosting.class);
        intent.putExtra("AVAILABILITY_PRICE", price);
        startActivity(intent);
    }

    public void cancelMyPosting(View v) {
        Intent intent = new Intent(this, ViewMyPosting.class);
        intent.putExtra("AVAILABILITY_PRICE", price);
        startActivity(intent);
    }
}

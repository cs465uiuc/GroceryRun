package com.cs465.groceryrun.groceryrun;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class PostAvailability extends Activity {
    private TextView dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_availability);
        dateText = (TextView) findViewById(R.id.editDateMyPosting);
    }

    public void changeDate(View v) {
        Calendar currentDate = Calendar.getInstance();
        int mYear = currentDate.get(Calendar.YEAR);
        int mMonth = currentDate.get(Calendar.MONTH);
        int mDay = currentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog availabilityDatePicker;
        availabilityDatePicker = new DatePickerDialog(PostAvailability.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                selectedMonth += 1;
                dateText.setText(selectedDay + "/" + selectedMonth + "/" + selectedYear);
            }
        }, mYear, mMonth, mDay);
        availabilityDatePicker.setTitle("Select Date");
        availabilityDatePicker.show();
    }

    public void postAvailability(View v){
        Intent intent = new Intent(this,Deliverer.class);
        startActivity(intent);
    }

    public void backToDeliverer(View v){
        Intent intent = new Intent(this,Deliverer.class);
        startActivity(intent);
    }
}

package com.cs465.groceryrun.groceryrun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class FilterTransaction extends AppCompatActivity {

    RadioGroup filter_type;
    RadioGroup filter_status;
    RadioGroup filter_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_transaction);

        filter_type = (RadioGroup) findViewById(R.id.filterTypeGroup);
        filter_status = (RadioGroup) findViewById(R.id.filterStatusGroup);
        filter_time = (RadioGroup) findViewById(R.id.filterTimeGroup);

        RadioButton preCheckedType, preCheckedStatus, preCheckedTime;
        Intent intent = getIntent();
        if(intent.getStringExtra("FILTER_TYPE").equals("Requests"))
            preCheckedType = (RadioButton) findViewById(R.id.filterType_requests);
        else if (intent.getStringExtra("FILTER_TYPE").equals("Jobs"))
            preCheckedType = (RadioButton) findViewById(R.id.filterType_jobs);
        else
            preCheckedType = (RadioButton) findViewById(R.id.filterType_all);

        if(intent.getStringExtra("FILTER_STATUS").equals("Completed"))
            preCheckedStatus = (RadioButton) findViewById(R.id.filterStatus_completed);
        else if (intent.getStringExtra("FILTER_STATUS").equals("In Progress"))
            preCheckedStatus = (RadioButton) findViewById(R.id.filterStatus_inprogress);
        else
            preCheckedStatus = (RadioButton) findViewById(R.id.filterStatus_all);

        if(intent.getStringExtra("FILTER_TIME").equals("This Month"))
            preCheckedTime = (RadioButton) findViewById(R.id.filterTime_onemonth);
        else if (intent.getStringExtra("FILTER_TIME").equals("Last 6 Months"))
            preCheckedTime = (RadioButton) findViewById(R.id.filterTime_halfyear);
        else if (intent.getStringExtra("FILTER_TIME").equals("This Year"))
            preCheckedTime = (RadioButton) findViewById(R.id.filterTime_oneyear);
        else
            preCheckedTime = (RadioButton) findViewById(R.id.filterTime_all);

        preCheckedType.setChecked(true);
        preCheckedStatus.setChecked(true);
        preCheckedTime.setChecked(true);
    }

    public void apply(View v){

        String selectedFilterType = ((RadioButton) findViewById(filter_type.getCheckedRadioButtonId())).getText().toString();
        String selectedFilterStatus = ((RadioButton) findViewById(filter_status.getCheckedRadioButtonId())).getText().toString();
        String selectedFilterTime = ((RadioButton) findViewById(filter_time.getCheckedRadioButtonId())).getText().toString();;

        Intent intent = new Intent(this, Transactions.class);
        intent.putExtra("FILTER_TYPE", selectedFilterType);
        intent.putExtra("FILTER_STATUS", selectedFilterStatus);
        intent.putExtra("FILTER_TIME", selectedFilterTime);

        startActivity(intent);
    }
}

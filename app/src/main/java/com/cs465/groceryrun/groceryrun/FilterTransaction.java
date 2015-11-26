package com.cs465.groceryrun.groceryrun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FilterTransaction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_transaction);
    }

    public void back(View v){
        Intent intent = new Intent(this,Transactions.class);
        startActivity(intent);
    }


}

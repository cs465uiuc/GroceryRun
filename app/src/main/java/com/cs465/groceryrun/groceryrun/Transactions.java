package com.cs465.groceryrun.groceryrun;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class Transactions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
    }


    public void confirmTrans(View v){
        Intent intent = new Intent(this,ConfirmTransaction.class);
        startActivity(intent);
    }

    public void filterTrans(View v){
        Intent intent = new Intent(this,FilterTransaction.class);
        startActivity(intent);
    }
}

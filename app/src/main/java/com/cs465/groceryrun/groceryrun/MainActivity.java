package com.cs465.groceryrun.groceryrun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void settings(View v){
        Intent intent = new Intent(this,Settings.class);
        startActivity(intent);
    }
    public void contact(View v){
        Intent intent = new Intent(this,ContactUs.class);
        startActivity(intent);
    }

    public void profile(View v){
        Intent intent = new Intent(this,Profile.class);
        startActivity(intent);
    }

    public void forum(View v){
        Intent intent = new Intent(this,Forum.class);
        startActivity(intent);
    }

    public void transactions(View v){
        Intent intent = new Intent(this,Transactions.class);
        startActivity(intent);
    }

}

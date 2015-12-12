package com.cs465.groceryrun.groceryrun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by YourName on 11/28/2015.
 */
public class Deliverer extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliverer);
    }

    public void myPostings(View v){
        Intent intent = new Intent(this,MyPostings.class);
        startActivity(intent);
    }

    public void postAvailability(View v){
        Intent intent = new Intent(this,PostAvailability.class);
        startActivity(intent);
    }

    public void searchRequests(View v){
        Intent intent = new Intent(this,SearchRequests.class);
        startActivity(intent);
    }

    public void profilePage(View v) {
        Intent intent = new Intent(this,Profile.class);
        startActivity(intent);
    }

    public void homePage(View v) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}

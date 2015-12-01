package com.cs465.groceryrun.groceryrun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Requester extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requester);
    }

    public void searchAvailabilities(View v){
        Intent intent = new Intent(this,SearchAvailabilities.class);
        startActivity(intent);
    }

    public void postRequest(View v){
        Intent intent = new Intent(this,PostRequest.class);
        startActivity(intent);
    }
}

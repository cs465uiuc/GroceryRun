package com.cs465.groceryrun.groceryrun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Sign_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }


    public void confirm(View v){
        Intent intent = new Intent(this,Confirm_Email.class);
        startActivity(intent);
    }


}

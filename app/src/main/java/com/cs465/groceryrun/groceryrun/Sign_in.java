package com.cs465.groceryrun.groceryrun;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class Sign_in extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }


    public void signup(View v){
        Intent intent = new Intent(this,Sign_up.class);
        startActivity(intent);
    }

    public void login(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void info(View v){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("An application that helps those in need of groceries by providing a medium to communicate with those who can help deliver.");
        builder1.setCancelable(true);
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


}

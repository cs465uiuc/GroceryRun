package com.cs465.groceryrun.groceryrun;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.View;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    public void signup(View v){
        Intent intent = new Intent(this,SignUp.class);
        startActivity(intent);
    }

    public void login(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void info(View v){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
        builder1.setMessage("An application that helps those in need of groceries by providing a medium to communicate with those who can help deliver.");
        builder1.setCancelable(true);
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


}


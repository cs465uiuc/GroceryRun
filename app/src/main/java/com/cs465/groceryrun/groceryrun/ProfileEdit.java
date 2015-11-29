package com.cs465.groceryrun.groceryrun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class ProfileEdit extends AppCompatActivity {
    String firstName = null;
    String lastName = null;
    String location = null;
    String description = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            firstName = extras.getString("USER_FIRSTNAME");
            lastName = extras.getString("USER_LASTNAME");
            location = extras.getString("USER_LOCATION");
            description = extras.getString("USER_DESCRIPTION");
        }

        EditText firstNameText = (EditText) findViewById(R.id.firstNameEdit);
        EditText lastNameText = (EditText) findViewById(R.id.lastNameEdit);
        EditText locationText = (EditText) findViewById(R.id.locationEdit);
        EditText descriptionText = (EditText) findViewById(R.id.descriptionEdit);

        if(firstName != null) {
            firstNameText.setText(firstName);
        }
        else {
            firstNameText.setText("error");
        }

        if(lastName != null) {
            lastNameText.setText(lastName);
        }
        else {
            lastNameText.setText("error");
        }

        if(location != null) {
            locationText.setText(location);
        }
        else {
            locationText.setText("error");
        }

        if(description != null) {
            descriptionText.setText(description);
        }
        else {
            descriptionText.setText("error");
        }
    }

    public void saveData(View v){
        Intent intent = new Intent(this,Profile.class);
        EditText firstNameText = (EditText) findViewById(R.id.firstNameEdit);
        firstName = firstNameText.getText().toString();
        EditText lastNameText = (EditText) findViewById(R.id.lastNameEdit);
        lastName = lastNameText.getText().toString();
        EditText locationText = (EditText) findViewById(R.id.locationEdit);
        location = locationText.getText().toString();
        EditText descriptionText = (EditText) findViewById(R.id.descriptionEdit);
        description = descriptionText.getText().toString();
        intent.putExtra("USER_FIRSTNAME", firstName);
        intent.putExtra("USER_LASTNAME", lastName);
        intent.putExtra("USER_LOCATION", location);
        intent.putExtra("USER_DESCRIPTION", description);
        startActivity(intent);
    }

    public void cancelData(View v){
        Intent intent = new Intent(this,Profile.class);
        startActivity(intent);
    }
}

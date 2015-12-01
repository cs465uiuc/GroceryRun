package com.cs465.groceryrun.groceryrun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CreateRequest extends Activity {
    String person = null;
    String price = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            person = extras.getString("AVAILABILITY_PERSON");
            price = extras.getString("AVAILABILITY_PRICE");
        }

        TextView userNameText = (TextView) findViewById(R.id.userName);
        EditText priceEdit = (EditText) findViewById(R.id.approximatePrice);

        if(person != null) {
            userNameText.setText(person);
        }
        else {
            userNameText.setText("error");
        }

        if(price != null) {
            priceEdit.setText(price);
        }
        else {
            priceEdit.setText("error");
        }
    }

    public void submitRequest(View v){
        Intent intent = new Intent(this,AvailabilityInfo.class);
        startActivity(intent);
    }
}

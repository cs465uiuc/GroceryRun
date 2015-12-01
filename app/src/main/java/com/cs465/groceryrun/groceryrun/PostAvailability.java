package com.cs465.groceryrun.groceryrun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PostAvailability extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_availability);
    }

    public void postAvailability(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}

package com.cs465.groceryrun.groceryrun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PostRequest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_request);
    }

    public void postRequest(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void back(View v){
        Intent intent = new Intent(this,Requester.class);
        startActivity(intent);
    }
}

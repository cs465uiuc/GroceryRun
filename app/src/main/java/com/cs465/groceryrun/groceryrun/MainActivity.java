package com.cs465.groceryrun.groceryrun;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface font = Typeface.createFromAsset( getAssets(), "fontawesome-webfont.ttf" );
        Button profileButton = (Button)findViewById( R.id.imageButton );
        profileButton.setTypeface(font);
        Button forumButton = (Button)findViewById( R.id.imageButton2 );
        forumButton.setTypeface(font);
        Button transactionButton = (Button)findViewById( R.id.imageButton3 );
        transactionButton.setTypeface(font);
        Button settingsButton = (Button)findViewById( R.id.imageButton4 );
        settingsButton.setTypeface(font);
        Button contactButton = (Button)findViewById( R.id.imageButton5 );
        contactButton.setTypeface(font);
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

/*



 */

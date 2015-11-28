package com.cs465.groceryrun.groceryrun;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // profileImage = (ImageView) findViewById(R.id.imageView);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        RatingBar ratingBar2 = (RatingBar) findViewById(R.id.ratingBar2);

        /*Bitmap bm = null;
        try {
            URL url = new URL("not working");
            try {
                bm = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            }
            catch(IOException e) {System.out.println("IO exception");}
        }
        catch(MalformedURLException e) {System.out.println(":(");}
        profileImage.setImageBitmap(bm);*/

        ratingBar.setRating((float) 3.5);
        ratingBar2.setRating((float) 4.2);
    }
}

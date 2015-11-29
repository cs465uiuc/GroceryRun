package com.cs465.groceryrun.groceryrun;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import android.os.AsyncTask;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    String firstName = null;
    String lastName = null;
    String location = null;
    String description = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            firstName = extras.getString("USER_FIRSTNAME");
            lastName = extras.getString("USER_LASTNAME");
            location = extras.getString("USER_LOCATION");
            description = extras.getString("USER_DESCRIPTION");
        }

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        RatingBar ratingBar2 = (RatingBar) findViewById(R.id.ratingBar2);

        new DownloadImageTask((ImageView) findViewById(R.id.imageView))
                .execute("https://yt3.ggpht.com/-WcRboVIb5gA/AAAAAAAAAAI/AAAAAAAAAAA/JenN9yg2JwU/s100-c-k-no/photo.jpg");

        ratingBar.setRating((float) 3.5);
        ratingBar2.setRating((float) 4.2);

        TextView firstNameText = (TextView) findViewById(R.id.firstNameTxt);
        TextView lastNameText = (TextView) findViewById(R.id.lastNameTxt);
        TextView locationText = (TextView) findViewById(R.id.locationTxt);
        TextView descriptionText = (TextView) findViewById(R.id.descriptionTxt);

        if(firstName != null) {
            firstNameText.setText(firstName);
        }

        if(lastName != null) {
            lastNameText.setText(lastName);
        }

        if(location != null) {
            locationText.setText(location);
        }

        if(description != null) {
            descriptionText.setText(description);
        }
    }

    public void editProfile(View v){
        Intent intent = new Intent(this,ProfileEdit.class);
        TextView firstNameText = (TextView) findViewById(R.id.firstNameTxt);
        firstName = firstNameText.getText().toString();
        TextView lastNameText = (TextView) findViewById(R.id.lastNameTxt);
        lastName = lastNameText.getText().toString();
        TextView locationText = (TextView) findViewById(R.id.locationTxt);
        location = locationText.getText().toString();
        TextView descriptionText = (TextView) findViewById(R.id.descriptionTxt);
        description = descriptionText.getText().toString();
        intent.putExtra("USER_FIRSTNAME", firstName);
        intent.putExtra("USER_LASTNAME", lastName);
        intent.putExtra("USER_LOCATION", location);
        intent.putExtra("USER_DESCRIPTION", description);
        System.out.println("heck3");
        startActivity(intent);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                System.out.println("failed");
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}

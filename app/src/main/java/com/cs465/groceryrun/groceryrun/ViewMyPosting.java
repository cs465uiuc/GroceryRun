package com.cs465.groceryrun.groceryrun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ViewMyPosting extends Activity {
    boolean edited = false;
    String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_posting);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            price = extras.getString("AVAILABILITY_PRICE");
        }
        TextView timeText = (TextView) findViewById(R.id.timeRangeMyPosting);
        TextView dateText = (TextView) findViewById(R.id.dateMyPosting);
        TextView priceText = (TextView) findViewById(R.id.approxPriceMyPosting);
        TextView locationText = (TextView) findViewById(R.id.locationMyPosting);

        if(edited == false) {
            if (price.equals("1")) {
                timeText.setText("8 - 9");
                dateText.setText("4/22/2005");
                priceText.setText("Approximate Price: $1");
                locationText.setText("Location: Phobos");
            }
            else {
                timeText.setText("2 - 3");
                dateText.setText("6/6/2010");
                priceText.setText("Approximate Price: $2");
                locationText.setText("Location: Phobos");
            }
        }
    }

    public void backToMyPostings(View v){
        Intent intent = new Intent(this, MyPostings.class);
        startActivity(intent);
    }

    public void editPosting(View v) {
        Intent intent = new Intent(this, EditMyPosting.class);
        TextView timeText = (TextView) findViewById(R.id.timeRangeMyPosting);
        TextView dateText = (TextView) findViewById(R.id.dateMyPosting);
        TextView priceText = (TextView) findViewById(R.id.approxPriceMyPosting);
        TextView locationText = (TextView) findViewById(R.id.locationMyPosting);
        String time = timeText.getText().toString();
        String startTime = time.substring(0, time.indexOf('-') - 1);
        String endTime = time.substring(time.indexOf('-') + 2);
        String date = dateText.getText().toString();
        String priceParam = priceText.getText().toString();
        priceParam = priceParam.substring(priceParam.indexOf('$') + 1);
        String location = locationText.getText().toString();
        location = location.substring(location.indexOf(':') + 2);
        intent.putExtra("AVAILABILITY_START_TIME", startTime);
        intent.putExtra("AVAILABILITY_END_TIME", endTime);
        intent.putExtra("AVAILABILITY_DATE", date);
        intent.putExtra("AVAILABILITY_PRICE", priceParam);
        intent.putExtra("AVAILABILITY_LOCATION", location);
        startActivity(intent);
    }

    public void deletePosting(View v) {
        Intent intent = new Intent(this, MyPostings.class);
        TextView priceText = (TextView) findViewById(R.id.approxPriceMyPosting);
        String priceParam = priceText.getText().toString();
        priceParam = priceParam.substring(priceParam.indexOf('$') + 1);
        intent.putExtra("AVAILABILITY_PRICE_DELETE", priceParam);
        startActivity(intent);
    }
}

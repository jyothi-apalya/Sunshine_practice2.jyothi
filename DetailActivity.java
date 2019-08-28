package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";
    private TextView wethear_for_day,Pressure,Humidity;
    private String mForecast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        wethear_for_day=(TextView)findViewById(R.id.wethear_for_day);
        Pressure=(TextView)findViewById(R.id.pressure);
        Humidity=(TextView)findViewById(R.id.humidity);
        Intent startDetailActivity=getIntent();
        Log.d("TAG",startDetailActivity.toString());
        if(startDetailActivity!=null){
         if(startDetailActivity.hasExtra(Intent.EXTRA_TEXT)){
             Log.d("TAG",startDetailActivity.getStringExtra(Intent.EXTRA_TEXT));
             mForecast=startDetailActivity.getStringExtra(Intent.EXTRA_TEXT);
             Log.d("tag",mForecast);
             wethear_for_day.setText(mForecast);

         }
       }

    }
    private Intent createShareForecastIntent() {
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText(mForecast + FORECAST_SHARE_HASHTAG)
                .getIntent();
        return shareIntent;
    }

 /* public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.deatal_activity_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.share_wethear_data) {
            Toast.makeText(getApplicationContext(),"share clicked",Toast.LENGTH_LONG).show();
            item.setIntent(createShareForecastIntent());

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.deatal_activity_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.share_wethear_data);
        menuItem.setIntent(createShareForecastIntent());
        return true;
    }

}

package com.example.menu;

import androidx.appcompat.app.ActionBar;
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

import static com.example.menu.R.id.highTempHeader;
import static com.example.menu.R.id.lowTempHeader;
import static com.example.menu.R.id.today_date1;

public class DetailActivity extends AppCompatActivity {
    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";
    private TextView wethear_for_day,Pressure,Humidity,header_current_temp_max,heaaer_temp_min,wind,discryption,row_date,header_temp_max,header_temp_min;
    private String mForecast,Row_date;
    private double humidity_current,maximum_temp_current,minimum_temp_current,wind_current;
    private double pressure_current;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        wethear_for_day=(TextView)findViewById(R.id.wethear_for_day);
        Pressure=(TextView)findViewById(R.id.pressure);
        Humidity=(TextView)findViewById(R.id.humidity);
        header_current_temp_max=(TextView)findViewById(R.id.highTempHeader1);
        heaaer_temp_min=(TextView)findViewById(R.id.lowTempHeader1);
        discryption=(TextView)findViewById(R.id.discryption);
        row_date=(TextView)findViewById(R.id.today_date1);





        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent startDetailActivity=getIntent();
        Log.d("TAG",startDetailActivity.toString());
        if(startDetailActivity!=null){
         if(startDetailActivity.hasExtra(Intent.EXTRA_TEXT)){
             Log.d("TAG",startDetailActivity.getStringExtra(Intent.EXTRA_TEXT));
             mForecast=startDetailActivity.getStringExtra(Intent.EXTRA_TEXT);
             pressure_current=startDetailActivity.getDoubleExtra("pressure",0.0);
             humidity_current=startDetailActivity.getDoubleExtra("humidity",0.0);
             maximum_temp_current=startDetailActivity.getDoubleExtra("maximum_temp",0.0);
             minimum_temp_current=startDetailActivity.getDoubleExtra("minimum_temp",0.0);
             wind_current=startDetailActivity.getDoubleExtra("Wind",0.0);
             Row_date=startDetailActivity.getStringExtra("row_date");
             Log.d("tag",mForecast);
             discryption.setText(mForecast);
             row_date.setText(Row_date);
             Pressure.append(pressure_current+"hPa");
             Humidity.append(humidity_current+"%");
             header_current_temp_max.setText(maximum_temp_current+"°");
             heaaer_temp_min.setText(minimum_temp_current+"°");

             wethear_for_day.append(wind_current+"km/h SW");
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

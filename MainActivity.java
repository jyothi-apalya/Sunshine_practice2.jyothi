package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.menu.Data.RecyclerViewItems;
import com.example.menu.Data.WethearDetailsmodel;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static com.example.menu.R.id.highTempHeader;
import static com.example.menu.R.id.lowTempHeader;

public class MainActivity extends AppCompatActivity implements WetherRecyclerAdapter.ForecastAdapterOnClickListener {

    TextView mWeatherTextView, today_date, header_temp_max, header_temp_min;
    private RecyclerView recyclerView;
    private wethearAdaptere wethearAdaptere;
    private int NUM_OF_LIST_ITEMS = 100;
    private List<WethearDetailsmodel> wethear_list;
    private List<RecyclerViewItems> recyclerViewItemsList = new ArrayList<>();
    private WetherRecyclerAdapter wetherRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWeatherTextView = (TextView) findViewById(R.id.mWeatherTextView);
        today_date = (TextView) findViewById(R.id.today_date);
        header_temp_max = (TextView) findViewById(highTempHeader);
        header_temp_min = (TextView) findViewById(lowTempHeader);


        recyclerView = (RecyclerView) findViewById(R.id.recyler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        wethear_list = new ArrayList<>();


        recyclerView.setHasFixedSize(true);

//        wethearAdaptere = new wethearAdaptere(wethear_list, this);



        Date myDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.setTime(myDate);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy");
        String today_Date = sdf.format(cal.getTime());
//         today_date.setText(today_Date);
        Log.d("list","size"+recyclerViewItemsList.size());
        wetherRecyclerAdapter = new WetherRecyclerAdapter(recyclerViewItemsList,this);
        recyclerView.setAdapter(wetherRecyclerAdapter);

        loadWetheardata();

//          recyclerViewItemsList=


    }

    public void loadWetheardata() {
        Log.d("TAG","LOAD_DATA");
        String DEFAULT_WEATHER_LOCATION = "94043,USA";
        WethearFetchinformation wethearFetchinformation = new WethearFetchinformation();
        wethearFetchinformation.execute(DEFAULT_WEATHER_LOCATION);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.sample) {
            Toast.makeText(getApplicationContext(), "REFRESH", Toast.LENGTH_LONG).show();
//            mWeatherTextView.setText(null);
//            loadWetheardata();
            return true;
        } else {
            if (id == R.id.sample1) {
                LoadMapInLocation();
                return true;
            } else {
                if (id == R.id.settings) {

                    Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(settingsIntent);
                    return true;
                }
            }
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClickListener(int onClickPostion, String weatherForDay, double pressure, double humidity, double wind, double maximum_temp, double minimum_temp, String Row_date) {
        Context context = this;
        String wethearforday = weatherForDay;
        String toastmessage = "Item" + onClickPostion + "Clicked";
        Intent Start_Detail_Activity = new Intent(context, DetailActivity.class);
        Start_Detail_Activity.putExtra(Intent.EXTRA_TEXT, wethearforday);
        Start_Detail_Activity.putExtra("pressure", pressure);
        Start_Detail_Activity.putExtra("humidity", humidity);
        Start_Detail_Activity.putExtra("Wind", wind);
        Start_Detail_Activity.putExtra("maximum_temp", maximum_temp);
        Start_Detail_Activity.putExtra("minimum_temp", minimum_temp);
        Start_Detail_Activity.putExtra("row_date", Row_date);
        startActivity(Start_Detail_Activity);
        Toast.makeText(context, toastmessage, Toast.LENGTH_SHORT).show();
        //  finish();
    }


    public class WethearFetchinformation extends AsyncTask<String, Void, List<RecyclerViewItems>> {

        @Override
        public List<RecyclerViewItems> doInBackground(String... strings) {

            if (strings.length == 0)
                return null;

            String location = strings[0];
            URL wethearreporturl = NetworkUtils.BuildUrl(location);

            try {

                String WetherResponse = NetworkUtils.getResponsesFromHttpUrl(wethearreporturl);
                Log.d("tag", WetherResponse);


                List<RecyclerViewItems> simpleJsonWeatherData = OpenWetherJsonUtils
                        .getSimpleWeatherStringsFromJson(MainActivity.this, WetherResponse, recyclerViewItemsList);


                Log.d("Tag0", "Size" + simpleJsonWeatherData.size());

                return simpleJsonWeatherData;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }


        }

        protected void onPostExecute(List<RecyclerViewItems> weatherData) {

            Log.d("TAG", "post" + weatherData.size());

            wetherRecyclerAdapter.setWeatherData(weatherData);

                recyclerViewItemsList = weatherData;
                Log.d("tag","recycler"+recyclerViewItemsList.size());
//                for (String weatherString : weatherData) {
//                    mWeatherTextView.append((weatherString) + "\n\n\n");
            }


        }
        public void LoadMapInLocation() {
        String address_string = "1600 Ampitheatre Parkway, CA";
        Uri geoLocation = Uri.parse("geo:0,0?q=" + address_string);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("TAG", "Couldn't call " + geoLocation.toString()
                    + ", no receiving apps installed!");
        }
    }

}





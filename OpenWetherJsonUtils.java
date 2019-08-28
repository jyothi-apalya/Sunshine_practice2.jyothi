package com.example.menu;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;

import com.example.menu.Data.Header_data_model;
import com.example.menu.Data.RecyclerViewItems;
import com.example.menu.Data.WethearDetailsmodel;
import com.example.menu.Utils.SunshineDateUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OpenWetherJsonUtils {


    public static List<RecyclerViewItems> getSimpleWeatherStringsFromJson(Context context, String forecastJsonStr,List<RecyclerViewItems> wethearDetailsmodelList)
            throws JSONException {

        final String OWM_LIST = "list";
        final String temp = "temp";
        final String max = "max";
        final String min = "min";
        final String OWM_WEATHER = "weather";
        final String OWM_DESCRIPTION = "main";
        final String PRESSURE = "pressure";
        final String HUMIDITY = "humidity";
        final String WIND_SPEED = "speed";
        final String DEGREES = "deg";

        final String OWM_MESSAGE_CODE = "cod";


        String[] parseddsata = null;
        JSONObject wethearinfromation = new JSONObject(forecastJsonStr);
        JSONArray weatherArray = wethearinfromation.getJSONArray(OWM_LIST);

        parseddsata = new String[(weatherArray.length())];

        long normalizedUtcStartDay = SunshineDateUtils.getNormalizedUtcDateForToday();


        for (int i = 0; i < weatherArray.length(); i++) {

            long dateTimeUtils;
            double min1;
            double max1;
            String description;
            double pressure;
            int humidity;
            double wind_speed;
            double degrees;


            JSONObject dayForecast = weatherArray.getJSONObject(i);
            // description=dayForecast.getString(OWM_DESCRIPTION);
            JSONObject weatherObject =
                    dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            description = weatherObject.getString(OWM_DESCRIPTION);
            dateTimeUtils = normalizedUtcStartDay + SunshineDateUtils.DAY_IN_MILLIS * i;
//            String dateText = (String) SunshineDateUtils.getFriendlyDateString(this, dateTimeUtils, true);
            pressure = dayForecast.getDouble(PRESSURE);
            humidity = dayForecast.getInt(HUMIDITY);
            wind_speed = dayForecast.getDouble(WIND_SPEED);
            JSONObject temparature = dayForecast.getJSONObject(temp);
            max1 = temparature.getDouble(max);
            min1 = temparature.getDouble(min);

            if (i == 0) {
                Header_data_model header_data_model = new Header_data_model();
                header_data_model.setHeader_temp_maximum(max1);
                header_data_model.setHeader_temp_minimum(min1);
                header_data_model.setDiscryption(description);
                String dateformat = convertdate(dateTimeUtils, "EEEE d  MMMM  yyyy");
                header_data_model.setHeader_date(dateformat);
                wethearDetailsmodelList .add(header_data_model);
            } else {
                WethearDetailsmodel wethearDetailsmodelInstance = new WethearDetailsmodel();
                wethearDetailsmodelInstance.setRowPressure(pressure);
                wethearDetailsmodelInstance.setRowHumidity(humidity);
                wethearDetailsmodelInstance.setRowWindSpeed(wind_speed);
                wethearDetailsmodelInstance.setDICRYPTION(description);
                wethearDetailsmodelInstance.setRow_temp_max(max1);
                wethearDetailsmodelInstance.setRow_temp_min(min1);
                wethearDetailsmodelList.add(wethearDetailsmodelInstance);


                //

//    WethearDetailsmodel.setRowWind(degrees);
                String dateformat = convertdate(dateTimeUtils, "dd/MM/yyyy");
                wethearDetailsmodelInstance.setROW_DATE(dateformat);
//                Log.d("time", "currrent" + dateTimeUtils);
                parseddsata[i] = description;

            }
        }


        Log.d("Tag","header"+wethearDetailsmodelList.size());
        return wethearDetailsmodelList;

    }
    public static String convertdate(long datetimeutils,String dateformat){
        SimpleDateFormat formatter = new SimpleDateFormat(dateformat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(datetimeutils);
        return formatter.format(calendar.getTime());
    }
}







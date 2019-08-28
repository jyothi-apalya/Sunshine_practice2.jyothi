package com.example.menu;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    public static  String QUERY_PARAM="q";
    final static String LON_PARAM = "lon";
    final static String FORMAT_PARAM = "mode";
    final static String UNITS_PARAM = "units";
    final static String DAYS_PARAM = "cnt";
    private static final String format = "json";
    private static final String units = "metric";
    private static final int numDays = 14;



    public static URL BuildUrl(String location) {

        Uri builtUri = Uri.parse("https://andfun-weather.udacity.com/staticweather").buildUpon()
                .appendQueryParameter(QUERY_PARAM,location)
                .appendQueryParameter(FORMAT_PARAM,format)
                .appendQueryParameter(UNITS_PARAM,units)
                .appendQueryParameter(DAYS_PARAM,Integer.toString(numDays))
                .build();

        URL uri = null;
        try {

            uri = new URL(builtUri.toString());
            return uri;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return uri;
    }


    public static String getResponsesFromHttpUrl(URL uri) throws IOException {
        HttpURLConnection httpURLConnection=(HttpURLConnection)uri.openConnection();
        try{
            InputStream in=httpURLConnection.getInputStream();
            Scanner scanner=new Scanner(in);
                   scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            httpURLConnection.disconnect();

        }








    }
}

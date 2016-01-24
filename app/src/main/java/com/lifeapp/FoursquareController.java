package com.lifeapp;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class FoursquareController extends ListActivity {
    ArrayList<FoursquareVenue> venuesList;

    // the foursquare client_id and the client_secret
    final String CLIENT_ID = "IR2KV15BYBD4HT4P5A53GLUQAAFMD3CD0K2FG35Y2MC0MW0Z";
    final String CLIENT_SECRET = "DKHNFMARUMXCWTZWTVDDX3KAXIZW1DGDRPK2JES2LICG23FA";

    // we will need to take the latitude and the logntitude from a certain point
    // this is the center of New York
    final String latitude = "40.7463956";
    final String longtitude = "-73.9852992";

    ArrayAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // start the AsyncTask that makes the call for the venus search.
        new Foursquare().execute();
    }

    private class Foursquare extends AsyncTask<String,Void,String> {

        String temp;

        @Override
        protected String doInBackground(String... params) {
            temp = makeCall("https://api.foursquare.com/v2/venues/search?client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&v=20130815&ll=40.7463956,-73.9852992");
            return "";
        }

        @Override
        protected void onPreExecute() {
            // we can start a progress bar here
        }

        @Override
        protected void onPostExecute(String result) {
            if (temp == null) {
                // we have an error to the call
                // we can also stop the progress bar
            } else {
                // all things went right

                // parseFoursquare venues search result
                try {
                    venuesList =  parseFoursquare(temp);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                ArrayList listTitle = new ArrayList();

                for (int i = 0; i < venuesList.size(); i++) {
                    // make a list of the venus that are loaded in the list.
                    // show the name, the category and the city
                    listTitle.add(i, venuesList.get(i).getName() + ", " + venuesList.get(i).getCategory() + "" + venuesList.get(i).getCity());
                }

                // set the results to the list
                // and show them in the xml
                myAdapter = new ArrayAdapter(FoursquareController.this, R.layout.row_layout, R.id.listText, listTitle);
                setListAdapter(myAdapter);
            }
        }
    }

    public static String makeCall(String url) {

        // string buffer for the the URL
        StringBuffer buffer_string = new StringBuffer(url);
        String replyString = "";

        // instantiate an HttpClient
        HttpClient httpclient = new DefaultHttpClient();
        // instantiate an HttpGet
        HttpGet httpget = new HttpGet(buffer_string.toString());

        try {
            // get the responce of the httpclient execution of the url
            HttpResponse response = httpclient.execute(httpget);
            InputStream is = response.getEntity().getContent();

            // buffer input stream the result
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayBuffer baf = new ByteArrayBuffer(20);
            int current = 0;
            while ((current = bis.read()) != -1) {
                baf.append((byte) current);
            }
            // the result as a string is ready for parsing
            replyString = new String(baf.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // trim the whitespaces
        return replyString.trim();
    }

    private static ArrayList parseFoursquare(final String response) throws JSONException {

        ArrayList<FoursquareVenue> temp = new ArrayList();
        try {

            // make an jsonObject in order to parse the response
            JSONObject jsonObject = new JSONObject(response);

            // make an jsonObject in order to parse the response
            if (jsonObject.has("response")) {
                FoursquareVenue poi = new FoursquareVenue();
                if (jsonObject.getJSONObject("response").has("venues")) {
                    JSONArray jsonArray = jsonObject.getJSONObject("response").getJSONArray("venues");

                    for (int i = 0; i<jsonArray.length();  i++) {
                        if (jsonArray.getJSONObject(i).getJSONArray("categories").getJSONObject(0).has("icon")) {
                            poi.setCategory(jsonArray.getJSONObject(i).getJSONArray("categories").getJSONObject(0).getString("name"));
                        }
                    }
                }
                temp.add(poi);
            }
        }
        catch(Exception e){

        }
        return  temp;
    }
}

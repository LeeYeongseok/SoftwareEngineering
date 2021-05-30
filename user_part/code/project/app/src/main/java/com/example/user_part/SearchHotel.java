package com.example.user_part;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;


public class SearchHotel extends AppCompatActivity {

    public static Context context_SearchHotel;
    private RsvCond rsvCond = new RsvCond();
    private ArrayList<MeetCond> meetCond = new ArrayList<>();
    private SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        context_SearchHotel = this;

        //체크인-체크아웃 날짜
        Spinner dateCheckinSpinner = (Spinner) findViewById(R.id.dateCheckinSpinner);
        Spinner dateCheckoutSpinner = (Spinner) findViewById(R.id.dateCheckoutSpinner);

        Calendar today = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("yy-MM-dd");
        SimpleDateFormat saveFormat = new SimpleDateFormat("yyyy-MM-dd");

        ArrayList<String> date_checkinlist = new ArrayList<String>(Collections.singleton(dateFormat.format(new Date(today.getTimeInMillis()))));

        for(int i=1; i<10; i++){
            Calendar new_date = Calendar.getInstance();
            new_date.add(Calendar.DATE, i);
            date_checkinlist.add(dateFormat.format(new Date(new_date.getTimeInMillis())));
        }

        Adapter dateCheckinAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, date_checkinlist);

        dateCheckinSpinner.setAdapter((SpinnerAdapter) dateCheckinAdapter);
        dateCheckinSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //rsvCond.setCheckin_date(date_checkinlist.get(position));
                try {
                    Date checkin_date = (Date) dateFormat.parse(date_checkinlist.get(position));
                    rsvCond.setCheckin_date(saveFormat.format(checkin_date)+" 00:00:00");
                    ArrayList<String> date_checkoutlist = new ArrayList<String>();
                    for(int i=1; i<10; i++){
                        Calendar new_date = Calendar.getInstance();
                        new_date.setTime(checkin_date);
                        new_date.add(Calendar.DATE, i);
                        date_checkoutlist.add(dateFormat.format(new Date(new_date.getTimeInMillis())));
                    }

                    Adapter dateCheckoutAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, date_checkoutlist);
                    dateCheckoutSpinner.setAdapter((SpinnerAdapter) dateCheckoutAdapter);
                    dateCheckoutSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //rsvCond.setCheckout_date(date_checkoutlist.get(position));
                            try {
                                Date checkout_date = (Date) dateFormat.parse(date_checkoutlist.get(position));
                                rsvCond.setCheckout_date(saveFormat.format(checkout_date)+" 00:00:00");
                                int diff = (int) Math.abs((checkout_date.getTime()-checkin_date.getTime())/(24*60*60*1000));
                                rsvCond.setStayNight(diff);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (NullPointerException e){
                    e.printStackTrace();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //인원 수
        Spinner numSpinner = (Spinner) findViewById(R.id.numSpinner);
        Adapter numAdapter = (ArrayAdapter) ArrayAdapter.createFromResource(this, R.array.num, android.R.layout.simple_spinner_dropdown_item);
        numSpinner.setAdapter((SpinnerAdapter) numAdapter);
        numSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rsvCond.setNum(Integer.parseInt(numSpinner.getSelectedItem().toString())); //인원 수 저장
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //위치
        Spinner locationSpinner = (Spinner) findViewById(R.id.locationSpinner);
        Adapter locationAdapter = (ArrayAdapter) ArrayAdapter.createFromResource(this, R.array.location, android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter((SpinnerAdapter) locationAdapter);

        //세부위치
        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            Adapter dLocationAdapter;
            Spinner dLocationSpinner = (Spinner) findViewById(R.id.detailed_locationSpinner);

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rsvCond.setLocation(locationSpinner.getSelectedItem().toString());

                switch(position){
                    case 0:
                        dLocationAdapter = (ArrayAdapter) ArrayAdapter.createFromResource(getApplicationContext(), R.array.dLocation_Seoul, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case 1:
                        dLocationAdapter = (ArrayAdapter) ArrayAdapter.createFromResource(getApplicationContext(), R.array.dLocation_Busan, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case 2:
                        dLocationAdapter = (ArrayAdapter) ArrayAdapter.createFromResource(getApplicationContext(), R.array.dLocation_Jeju, android.R.layout.simple_spinner_dropdown_item);
                        break;
                }
                dLocationSpinner.setAdapter((SpinnerAdapter) dLocationAdapter);
                dLocationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        rsvCond.setDlocation(dLocationSpinner.getSelectedItem().toString()); //세부 위치 저장
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //검색 버튼
        final Button searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meetCond.clear();
                DBConnection dbConnection = new DBConnection();
                dbConnection.execute(rsvCond.getCheckin_date(), rsvCond.getCheckout_date(), rsvCond.getDlocation(),
                        rsvCond.getNum()+"");
                /*dbConnection.execute(rsvCond.getCheckin_date(), rsvCond.getCheckout_date(), rsvCond.getLocation()+" "+rsvCond.getDlocation(),
                        rsvCond.getNum()+"");*/
            }
        });

    }



    private class DBConnection extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result == null) {
                System.out.println("*****************result is null!********************");
            } else {
                SaveResult(result);
            }
        }

        @Override
        protected String doInBackground(String... params) {

            //서버에서 조건에 맞는 방을 가져옴
            String iDate = params[0];
            String oDate = params[1];
            String loc = params[2];
            String num = params[3];

            System.out.println(iDate + oDate + loc + num);
            String server_url = "http://qmdlrhdfyd.synology.me:8080/searchRoom.php";
            /*String postParameters = "date1=" + "2021-05-28 00:00:00" + "&date2=" + "2021-05-29 00:00:00" +
                    "&location=" + "동작구" + "&maxGuest=" + "1";*/
            String postParameters = "date1=" + iDate + "&date2=" + oDate +
                    "&location=" + loc + "&maxGuest=" + num;

            try {
                URL url = new URL(server_url);
                HttpURLConnection URLConnection = (HttpURLConnection) url.openConnection();

                URLConnection.setReadTimeout(5000);
                URLConnection.setConnectTimeout(5000);
                URLConnection.setRequestMethod("POST");
                URLConnection.setDoInput(true);
                URLConnection.connect();

                OutputStream outputStream = URLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = URLConnection.getResponseCode();

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = URLConnection.getInputStream();
                } else {
                    inputStream = URLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                bufferedReader.close();
                return stringBuilder.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }
    }

    private void SaveResult(String response) {

        try {
            response = response.replaceAll("</pre></pre>", "");

            JSONObject JsonObject = new JSONObject(response);
            JSONArray JsonArray = JsonObject.getJSONArray("webnautes");

            for (int i = 0; i < JsonArray.length(); i++) {
                JSONObject r = JsonArray.getJSONObject(i);

                String hotelID = r.getString("hotelID");
                int costPerDay = r.getInt("costPerDay");
                String roomID = r.getString("roomID");
                MeetCond mc = new MeetCond(hotelID, costPerDay, (float) -1.0, R.drawable.room2, "", "",
                        "", "", roomID, true, -1, -1);
                meetCond.add(mc);
            }

            if (!meetCond.isEmpty()){
                Intent intent = new Intent(SearchHotel.this, ShowRoom.class);
                SearchHotel.this.startActivity(intent);}
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<MeetCond> getMeetCond() {
        return meetCond;
    }

    public RsvCond getRsvCond() {
        return rsvCond;
    }

}
package com.example.user_part;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class SearchHotel extends AppCompatActivity {

    public static Context context_SearchHotel;
    private RsvCond rsvCond = new RsvCond();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        context_SearchHotel = this;

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //체크인-체크아웃 날짜
        Spinner dateCheckinSpinner = (Spinner) findViewById(R.id.dateCheckinSpinner);
        Spinner dateCheckoutSpinner = (Spinner) findViewById(R.id.dateCheckoutSpinner);

        Calendar today = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");

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
                rsvCond.setCheckin_date(date_checkinlist.get(position));
                try {
                    Date checkin_date = (Date) dateFormat.parse(date_checkinlist.get(position));
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
                            rsvCond.setCheckout_date(date_checkoutlist.get(position));
                            try {
                                Date checkout_date = (Date) dateFormat.parse(date_checkoutlist.get(position));
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
                    System.out.println(rsvCond.getCheckin_date());
                    System.out.println("왜 때문에 null?");
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
        searchButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchHotel.this, ShowRoom.class);
                SearchHotel.this.startActivity(intent);
                //finish();
                /*Response.Listener<String> responseListen = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                Intent intent = new Intent(SearchHotel.this, ShowRoom.class);
                                SearchHotel.this.startActivity(intent);
                                finish();
                            }
                        } catch(Exception e){

                        }
                    }
                };
                DBConnection dbConnection = new DBConnection(rsvCond, responseListen);
                RequestQueue queue = Volley.newRequestQueue(SearchHotel.this);
                queue.add(dbConnection);*/
            }
        });

    }

    public RsvCond getRsvCond() {
        return rsvCond;
    }

}
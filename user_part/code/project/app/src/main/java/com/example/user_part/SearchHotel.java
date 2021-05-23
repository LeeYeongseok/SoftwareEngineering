package com.example.user_part;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class SearchHotel extends AppCompatActivity {

        RsvCond rsvCond = new RsvCond();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //날짜
        Spinner dateSpinner = (Spinner) findViewById(R.id.dateSpinner);
        Calendar today = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
        ArrayList<String> date_list = new ArrayList<String>(Collections.singleton(dateFormat.format(new Date(today.getTimeInMillis()))));

        for(int i=1; i<10; i++){
            Calendar new_date = Calendar.getInstance();
            new_date.add(Calendar.DATE, i);
            date_list.add(dateFormat.format(new Date(new_date.getTimeInMillis())));
        }

        Adapter dateAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, date_list);
        dateSpinner.setAdapter((SpinnerAdapter) dateAdapter);
        dateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rsvCond.setDate(date_list.get(position));
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
                rsvCond.setLoc(locationSpinner.getSelectedItem().toString());

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
                        rsvCond.setdLoc(dLocationSpinner.getSelectedItem().toString()); //세부 위치 저장
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
                finish();
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

}
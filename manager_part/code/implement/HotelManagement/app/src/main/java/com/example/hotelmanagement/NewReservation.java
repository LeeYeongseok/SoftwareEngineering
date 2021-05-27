package com.example.hotelmanagement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NewReservation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_reservation);

        Intent intent = getIntent();

        ArrayList<String> rlist = new ArrayList<String>();
        for (int i = 0; i<15; i++)
            rlist.add("null");

        ArrayAdapter adpater = new ArrayAdapter(this, android.R.layout.simple_list_item_1, rlist);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adpater);

    }
}
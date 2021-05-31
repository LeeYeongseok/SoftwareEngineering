package com.example.hotelmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        Intent intent = getIntent();
        String hotelName = intent.getStringExtra("HotelName");

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setText("Hotel Name : " + hotelName); //+ controller.getID())

        Button new_reservation_btn = findViewById(R.id.button1);
        new_reservation_btn.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewReservation.class);
                intent.putExtra("HotelName", hotelName);
                startActivity(intent);
            }
        }));

        Button manage_room_btn = findViewById(R.id.button2);
        manage_room_btn.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ManageRoom.class);
                intent.putExtra("HotelName", hotelName);
                startActivity(intent);
            }
        }));

        Button manage_reservation_btn = findViewById(R.id.button3);
        manage_reservation_btn.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CheckReservation.class);
                intent.putExtra("HotelName", hotelName);
                startActivity(intent);
            }
        }));
    }
}

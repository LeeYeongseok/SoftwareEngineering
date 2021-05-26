package com.example.hotelmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val new_reservation_btn = findViewById<View>(R.id.button1) as Button
        new_reservation_btn.setOnClickListener {
            val intent = Intent(this, NewReservation::class.java)
            startActivity(intent)
        }

        val manage_room_btn = findViewById<View>(R.id.button2) as Button
        manage_room_btn.setOnClickListener {
            val intent = Intent(this, ManageRoom::class.java)
            startActivity(intent)
        }

        val manage_reservation_btn = findViewById<View>(R.id.button3) as Button
        manage_reservation_btn.setOnClickListener {
            val intent = Intent(this, ManageReservation::class.java)
            startActivity(intent)
        }
    }
}
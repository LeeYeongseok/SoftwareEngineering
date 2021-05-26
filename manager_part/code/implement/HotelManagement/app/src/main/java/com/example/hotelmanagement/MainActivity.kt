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

        val imageButton = findViewById<View>(R.id.button1) as Button
        imageButton.setOnClickListener {
            val intent = Intent(this, NewReservation::class.java)
            startActivity(intent)
        }
    }
}
package com.example.hotelmanagement

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    //var controller = Controller(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //var a = hotelIDlist();
        //a.execute()
        //for(i in 0..a.IDList.size-1)
         //   println(a.IDList.get(i))

        //UI 객체생성
        val autoCompleteTextView = findViewById<View>(R.id.autoCompleteTextView) as AutoCompleteTextView
        autoCompleteTextView.setText("Hotel ID : (print ID)") //+ controller.getID())

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
            val intent = Intent(this, CheckReservation::class.java)
            startActivity(intent)
        }

    }
}
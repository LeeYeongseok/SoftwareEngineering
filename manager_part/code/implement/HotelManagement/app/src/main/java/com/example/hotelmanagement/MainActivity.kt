package com.example.hotelmanagement

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
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

        val hotelName = findViewById<View>(R.id.hotelName) as EditText

        val new_reservation_btn = findViewById<View>(R.id.login_btn) as Button
        new_reservation_btn.setOnClickListener {
            val intent = Intent(this, MainMenu::class.java)
            intent.putExtra("HotelName", hotelName?.getText()?.toString());
            startActivity(intent)
        }

    }
}
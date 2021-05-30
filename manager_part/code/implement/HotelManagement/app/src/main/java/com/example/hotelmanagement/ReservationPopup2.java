package com.example.hotelmanagement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class ReservationPopup2 extends Activity {

    TextView txtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.reservation_popup2);

        //UI 객체생성
        txtText = (TextView) findViewById(R.id.txtText);

        //데이터 가져오기
        Intent intent = getIntent();

        int position = intent.getIntExtra("Index", 0);
        String RsvNum = intent.getStringExtra("RsvNum");
        String RoomNum = intent.getStringExtra("RoomNum");
        String checkIn = intent.getStringExtra("checkIn");
        String checkOut = intent.getStringExtra("checkOut");
        String NumOfPeople = intent.getStringExtra("NumOfPeople");
        String Meal = intent.getStringExtra("Meal");

        String text = "No." + RsvNum + "\n\nRoom " + RoomNum
                + "\nCheck In: " + checkIn + "\nCheck Out: " + checkOut
                + "\nNumber of people: " + NumOfPeople + "\nMeal: " + Meal;
        txtText.setText(text);
    }

    //확인 버튼 클릭
    public void mOnClose(View v){
        //액티비티(팝업) 닫기
        finish();
    }
}
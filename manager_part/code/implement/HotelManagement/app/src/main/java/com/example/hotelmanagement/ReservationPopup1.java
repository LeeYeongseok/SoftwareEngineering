package com.example.hotelmanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class ReservationPopup1 extends Activity {

    TextView txtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.reservation_popup1);

        //UI 객체생성
        txtText = (TextView)findViewById(R.id.txtText);

        //데이터 가져오기
        Intent intent = getIntent();

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
        //데이터 전달하기
        Intent intent = new Intent();
        intent.putExtra("result", "Close Popup");
        setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기
        finish();
    }

}

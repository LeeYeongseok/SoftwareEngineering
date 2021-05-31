package com.example.user_part;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;

public class PaymentCheck extends AppCompatActivity {

  RsvCond rsvCond = ((SearchHotel)SearchHotel.context_SearchHotel).getRsvCond();
  MeetCond selRoom = ((SelectRoom)SelectRoom. context_SelectRoom).selroom;
  RoomOpt roomOpt = ((SelectRoom)SelectRoom. context_SelectRoom).roomopt;
   TextView tv_hotelName;
   TextView tv_roomNum;
   TextView tv_headContent;
   TextView tv_inDate;
   TextView tv_inTime;
   TextView tv_outDate;
   TextView tv_outTime;
   TextView tv_OP1;
   TextView tv_OP2;
   TextView tv_OP3;
   TextView tv_priceData;
   Button btn_withoutBankbook;
   Button btn_card;
   Button btn_phone;
   Button btn_etc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_check);

        Intent intent = getIntent();
        int totalPrice = intent.getIntExtra("totalPrice",0);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

     tv_hotelName = findViewById(R.id.tv_hotelName);
     tv_hotelName.setText(selRoom.getHotelName());

     tv_roomNum = findViewById(R.id.tv_roomNum);
     tv_roomNum.setText(selRoom.getRoomType());

     tv_headContent = findViewById(R.id.tv_headContent);
     String num = String.valueOf(rsvCond.getNum());
     tv_headContent.setText(num);

     tv_inDate = findViewById(R.id.tv_inDate);
     tv_inDate.setText(rsvCond.getCheckin_date().replaceAll("00:00:00", ""));

     tv_inTime = findViewById(R.id.tv_inTime);
     String iTime = selRoom.getITime();
     tv_inTime.setText(iTime.substring(0,iTime.length()-3));


     tv_outDate = findViewById(R.id.tv_outDate);
     tv_outDate.setText(rsvCond.getCheckout_date().replaceAll("00:00:00", ""));

     tv_outTime = findViewById(R.id.tv_outTime);
        String oTime = selRoom.getITime();
        tv_outTime.setText(oTime.substring(0,oTime.length()-3));



     tv_OP1 = findViewById(R.id.tv_OP1);
     if(roomOpt.isMeal()){
         String mealCnt = String.valueOf(roomOpt.getMealCnt());
         tv_OP1.setText("식사를 " + mealCnt + "끼 주문했습니다.");
     }else {
         tv_OP1.setText("식사를 주문하지 않았습니다.");
     }



     tv_priceData = findViewById(R.id.tv_priceData);
     tv_priceData.setText(String.valueOf(totalPrice) +"원");

     btn_withoutBankbook = findViewById(R.id.btn_withoutBankbook);
     btn_withoutBankbook.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
         }
     });
     btn_card = findViewById(R.id.btn_card);
     btn_card.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
         }
     });

     btn_phone = findViewById(R.id.btn_phone);
     btn_phone.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
         }
     });

     btn_etc = findViewById(R.id.btn_etc);
     btn_etc.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
         }
     });



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.home){
            finish();
            //NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        else return super.onOptionsItemSelected(item);
    }
}
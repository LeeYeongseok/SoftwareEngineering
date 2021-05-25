package com.example.user_part;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class PaymentCheck extends AppCompatActivity {

  //RsvCond rsvCond = ((SearchHotel)SearchHotel. context_SearchHotel).rsvCond;
  SelRoom selRoom = ((SelectRoom)SelectRoom. context_SelectRoom).selroom;
  RoomOpt roomOpt = ((SelectRoom)SelectRoom. context_SelectRoom).roomopt;
   TextView tv_hotelName;
   TextView tv_roomNum;
   TextView tv_headContent;
   TextView tv_inDate;
   TextView tv_inTime;
   TextView tv_outDate;
   TextView tv_outTime;
   TextView tv_notiInfo;
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

     tv_hotelName = findViewById(R.id.tv_hotelName);
     tv_hotelName.setText(selRoom.getHotelName());

     tv_roomNum = findViewById(R.id.tv_roomNum);
     tv_roomNum.setText(selRoom.getRoomType());

     tv_headContent = findViewById(R.id.tv_headContent);
   //  String num = String.valueOf(rsvCond.getNum());
    // tv_headContent.setText(num);

     tv_inDate = findViewById(R.id.tv_inDate);
    // tv_inDate.setText(rsvCond.getDate());

     tv_inTime = findViewById(R.id.tv_inTime);
     tv_inTime.setText(selRoom.getITime());

     tv_outDate = findViewById(R.id.tv_outDate);
     //rsvCond수정후 setText해야함

     tv_outTime = findViewById(R.id.tv_outTime);
     tv_outTime.setText(selRoom.getoTime());



     tv_OP1 = findViewById(R.id.tv_OP1);
     if(roomOpt.isMeal()){
         String mealCnt = String.valueOf(roomOpt.getMealCnt());
         tv_OP1.setText("식사를 " + mealCnt + "끼 주문했습니다.");
     }else {
         tv_OP1.setText("식사를 주문하지 않았습니다.");
     }


     tv_OP2 = findViewById(R.id.tv_OP2);

     tv_OP3 = findViewById(R.id.tv_OP3);

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
}
package com.example.user_part;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SelectRoom extends AppCompatActivity {

    MeetCond selroom = ((ShowRoom)ShowRoom.context_ShowRoom).getMeetCond();
    RsvCond rsvCond = ((SearchHotel)SearchHotel.context_SearchHotel).getRsvCond();
    RoomOpt roomopt = new RoomOpt(rsvCond.getStayNight(), rsvCond.getNum());

    public static Context context_SelectRoom;
    ImageView hotelImage;
    TextView tv_hotelNameContents;
    TextView tv_RoomContent;
    TextView tv_locationContent;
    TextView tv_checkInTimeContent;
    TextView tv_checkOutTimeContent;
    TextView tv_PriceContent;
    TextView tv_FacilityContent;
    TextView tv_grade;
    TextView tv_reviewContent;
    RadioGroup rg_mealSel;
    RadioButton radioButton_op1;
    RadioButton radioButton_op2;
    TextView tv_totalPriceContent;
    Button button_reserve;
    Spinner sp_mealCnt;
    TextView tv_mealInfo;
    int intTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_room);
        context_SelectRoom = this;

        Calculator calculator = new Calculator(roomopt, selroom);

        tv_totalPriceContent= findViewById(R.id.tv_totalPriceContent);
        intTotalPrice = calculator.calculate();
        String totalPrice =  String.valueOf(intTotalPrice);
        tv_totalPriceContent.setText(totalPrice + "원");

        hotelImage = findViewById((R.id.hotelImage));
        try{
            System.out.println(selroom.getPicture());
            hotelImage.setImageBitmap(selroom.getPicture());
        } catch(Exception e){
            e.printStackTrace();
        }

        tv_hotelNameContents = findViewById(R.id.tv_hotelNameContents);
        tv_hotelNameContents.setText(selroom.getHotelName());

        tv_RoomContent= findViewById(R.id.tv_RoomContent);
        tv_RoomContent.setText(selroom.getRoomType());

        tv_locationContent= findViewById(R.id.tv_locationContent);
        tv_locationContent.setText(selroom.getLocation());

        tv_checkInTimeContent= findViewById(R.id.tv_checkInTimeContent);
        tv_checkInTimeContent.setText(selroom.getITime());

        tv_checkOutTimeContent= findViewById(R.id.tv_checkOutTimeContent);
        tv_checkOutTimeContent.setText(selroom.getoTime());

        tv_PriceContent= findViewById(R.id.tv_PriceContent);
        String Price = String.valueOf(selroom.getPriceOfDay());
        intTotalPrice = selroom.getPriceOfDay();
        tv_PriceContent.setText(Price + "원");

        tv_FacilityContent= findViewById(R.id.tv_FacilityContent);
        tv_FacilityContent.setText(selroom.getExFacility());

        tv_grade= findViewById(R.id.tv_grade);
        String grade = Float.toString(selroom.getGrade());
        tv_grade.setText("(" +grade + "/5.0)");

        tv_reviewContent= findViewById(R.id.tv_reviewContent);
        try{
            tv_reviewContent.setText(selroom.getReview().get(0));
        } catch(Exception e){
            e.printStackTrace();
        }

        rg_mealSel = findViewById(R.id.rg_mealSel);
        radioButton_op1= findViewById(R.id.radioButton_op1);
        radioButton_op2= findViewById(R.id.radioButton_op2);

        sp_mealCnt =findViewById(R.id.sp_mealCnt);
        tv_mealInfo = findViewById(R.id.tv_mealInfo);
        sp_mealCnt.setVisibility(View.GONE);
        tv_mealInfo.setVisibility(View.GONE);

        if(selroom.isMeal()) { //식사 옵션이 존재하면 스피너 활성화시키기 & 가격 표시하기
            String mealPrice = String.valueOf(selroom.getMealPrice());
            radioButton_op2.setText("식사 추가: " + mealPrice + "원/끼"); //가격 표시하기

        }else{
            radioButton_op2.setEnabled(false);
            radioButton_op2.setText("식사 추가(식사가 제공되지 않는 호텔입니다)");
        }

        rg_mealSel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioButton_op1) {//식사 추가 X
                    roomopt.setMeal(false);
                    sp_mealCnt.setVisibility(View.GONE);
                    tv_mealInfo.setVisibility(View.GONE);
                    intTotalPrice = calculator.calculate();
                    tv_totalPriceContent.setText(intTotalPrice + "원");

                }else if(checkedId == R.id.radioButton_op2) { //식사 추가 시
                    sp_mealCnt.setVisibility(View.VISIBLE);
                    tv_mealInfo.setVisibility(View.VISIBLE);
                    roomopt.setMeal(true);
                    intTotalPrice = calculator.calculate();
                    tv_totalPriceContent.setText(intTotalPrice + "원");
                    sp_mealCnt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String mealResult = parent.getItemAtPosition(position).toString();
                            roomopt.setMealCnt(Integer.parseInt(mealResult));
                            intTotalPrice = calculator.calculate();
                            tv_totalPriceContent.setText(intTotalPrice + "원");
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {


                        }
                    });
                }
            }

        });


        button_reserve= findViewById(R.id.button_reserve);
       button_reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioButton_op1.isChecked() == false &&radioButton_op2.isChecked()== false) {
                    Toast.makeText(getApplicationContext(), "식사 옵션을 선택하세요", Toast.LENGTH_SHORT).show();
                }else{
                        Intent intent = new Intent(SelectRoom.this, PaymentCheck.class);
                        intent.putExtra("totalPrice", intTotalPrice);
                        startActivity(intent);
                    }
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
package com.example.hotelmanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RoomPopup1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.room_popup1);

        //UI 객체생성
        TextView roomNumText = (TextView)findViewById(R.id.txtText1);
        EditText priceText = (EditText)findViewById(R.id.priceText);
        EditText roomTypeText = (EditText)findViewById(R.id.roomTypeText);
        EditText capacityText = (EditText)findViewById(R.id.capacityText);

        //데이터 가져오기
        Intent intent = getIntent();

        int position = intent.getIntExtra("Index", 0);
        String RoomNum = intent.getStringExtra("RoomNum");
        String Price = intent.getStringExtra("Price");
        //String checkIn = intent.getStringExtra("checkIn");
        //String checkOut = intent.getStringExtra("checkOut");
        String RoomType = intent.getStringExtra("RoomType");
        String Capacity = intent.getStringExtra("Capacity");

        roomNumText.setText("Room " + RoomNum);
        priceText.setText(Price);
        roomTypeText.setText(RoomType);
        capacityText.setText(Capacity);

        Button modify_btn = (Button) findViewById(R.id.modify);
        modify_btn.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View v) {
                if (String.valueOf(priceText.getText()).length() != 0 && String.valueOf(roomTypeText.getText()).length() != 0
                        && String.valueOf(capacityText.getText()).length() != 0) {
                    Intent intent = new Intent();
                    intent.putExtra("Decision", "modify");
                    intent.putExtra("Price", Integer.parseInt(String.valueOf(priceText.getText())));
                    intent.putExtra("RoomType", String.valueOf(roomTypeText.getText()));
                    intent.putExtra("Capacity", Integer.parseInt(String.valueOf(capacityText.getText())));
                    intent.putExtra("Index", position);
                    setResult(RESULT_OK, intent);

                    //액티비티(팝업) 닫기
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please enter everything", Toast.LENGTH_SHORT).show();
                }
            }
        }));

        Button delete_btn = (Button) findViewById(R.id.delete);
        delete_btn.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("Decision", "delete");
                intent.putExtra("Index", position);
                setResult(RESULT_OK, intent);

                //액티비티(팝업) 닫기
                finish();
            }
        }));
    }
}
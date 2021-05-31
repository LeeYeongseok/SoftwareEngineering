package com.example.hotelmanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RoomPopup2 extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.room_popup2);

        //UI 객체생성
        EditText roomNumText = (EditText)findViewById(R.id.roomNumText);
        EditText priceText = (EditText)findViewById(R.id.priceText);
        EditText roomTypeText = (EditText)findViewById(R.id.roomTypeText);
        EditText capacityText = (EditText)findViewById(R.id.capacityText);

        //데이터 가져오기
        Intent intent = getIntent();

        Button add_btn = (Button) findViewById(R.id.add);
        add_btn.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View v) {
                if (String.valueOf(roomNumText.getText()).length() != 0 && String.valueOf(priceText.getText()).length() != 0
                        && String.valueOf(roomTypeText.getText()).length() != 0 && String.valueOf(capacityText.getText()).length() != 0) {
                    Intent intent = new Intent();
                    intent.putExtra("Decision", "add");
                    intent.putExtra("RoomNum", Integer.parseInt(String.valueOf(roomNumText.getText())));
                    intent.putExtra("Price", Integer.parseInt(String.valueOf(priceText.getText())));
                    intent.putExtra("RoomType", String.valueOf(roomTypeText.getText()));
                    intent.putExtra("Capacity", Integer.parseInt(String.valueOf(capacityText.getText())));
                    setResult(RESULT_OK, intent);

                    //액티비티(팝업) 닫기
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please enter everything", Toast.LENGTH_SHORT).show();
                }
            }
        }));
    }
}

package com.example.hotelmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CheckReservation extends AppCompatActivity {
    ListView listView;
    private ArrayList<RsvInfo> list;
    ReservationAdapter adapter;
    Controller controller = new Controller();

    String hotelID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_reservation);

        Intent intent = getIntent();
        list = controller.checkReservation(); // 여기는 승인 받은 예약만 띄워야 하는데 지금 decision 값을 저장하는 부분이 구현이 안돼서 일단 다 받아옴

        adapter = new ReservationAdapter(this, R.layout.reservation_list, list);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ReservationPopup2.class);
                /* putExtra의 첫 값은 식별 태그, 뒤에는 다음 화면에 넘길 값 */
                intent.putExtra("RsvNum", Integer.toString(list.get(position).getRsv_Num()));
                intent.putExtra("RoomNum", Integer.toString(list.get(position).getRoom_Num()));
                intent.putExtra("checkIn", list.get(position).getCheckIn_date() + " " + list.get(position).getiTime());
                intent.putExtra("checkOut", list.get(position).getCheckOut_date() + " " + list.get(position).getoTime());
                intent.putExtra("NumOfPeople", Integer.toString(list.get(position).getNumOfPeople()));
                intent.putExtra("Meal", list.get(position).getMeal() ? "O":"X");
                startActivity(intent);
            }
        });


    }
}
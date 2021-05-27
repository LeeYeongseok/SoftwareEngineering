package com.example.hotelmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NewReservation extends AppCompatActivity {
    ListView listView;
    private ArrayList<RsvInfo> list;
    ReservationAdapter adapter;
    Controller controller = new Controller();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_reservation);

        Intent intent = getIntent();
        list = controller.checkReservation();

        adapter = new ReservationAdapter(this, R.layout.reservation_list, list);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ReservationPopup1.class);
                /* putExtra의 첫 값은 식별 태그, 뒤에는 다음 화면에 넘길 값 */
                intent.putExtra("Index", position);
                intent.putExtra("RsvNum", Integer.toString(list.get(position).getRsv_Num()));
                intent.putExtra("RoomNum", Integer.toString(list.get(position).getRoom_Num()));
                intent.putExtra("checkIn", list.get(position).getCheckIn_date() + " " + list.get(position).getiTime());
                intent.putExtra("checkOut", list.get(position).getCheckOut_date() + " " + list.get(position).getoTime());
                intent.putExtra("NumOfPeople", Integer.toString(list.get(position).getNumOfPeople()));
                intent.putExtra("Meal", list.get(position).getMeal() ? "O":"X");
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //데이터 받기
                String result = data.getStringExtra("Decision");
                if (result.equals("true")) {
                    // RsvInfo의 d 값 true로 변경
                }
                else if (result.equals("false")) {
                    // RsvInfo의 d 값 false로 변경
                }

                int pos = data.getIntExtra("Index", 0);
                list.remove(pos);
                adapter.notifyDataSetChanged();
            }
        }
    }

}
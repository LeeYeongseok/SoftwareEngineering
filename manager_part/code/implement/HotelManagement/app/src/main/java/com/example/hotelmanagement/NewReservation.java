package com.example.hotelmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class NewReservation extends AppCompatActivity {
    ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
    private ArrayList<RsvInfo> data;
    ReservationAdapter adpater;
    Controller controller = new Controller();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_reservation);

        Intent intent = getIntent();
        data = controller.checkReservation();

        adpater = new ReservationAdapter(this, R.layout.reservation_list, data);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adpater);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ReservationPopup1.class);
                /* putExtra의 첫 값은 식별 태그, 뒤에는 다음 화면에 넘길 값 */
                intent.putExtra("RsvNum", Integer.toString(data.get(position).getRsv_Num()));
                intent.putExtra("RoomNum", Integer.toString(data.get(position).getRoom_Num()));
                intent.putExtra("checkIn", data.get(position).getCheckIn_date() + " " + data.get(position).getiTime());
                intent.putExtra("checkOut", data.get(position).getCheckOut_date() + " " + data.get(position).getoTime());
                intent.putExtra("NumOfPeople", Integer.toString(data.get(position).getNumOfPeople()));
                intent.putExtra("Meal", data.get(position).getMeal() ? "O":"X");
                //intent.putExtra("data", "Test");
                startActivityForResult(intent, 1);
            }
        });
    }

}
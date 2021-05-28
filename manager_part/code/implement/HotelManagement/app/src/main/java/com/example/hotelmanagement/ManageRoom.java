package com.example.hotelmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ManageRoom extends AppCompatActivity {
    ListView listView;
    private ArrayList<RoomInfo> list = null;
    RoomAdapter adapter;
    Controller controller = new Controller();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_room);

        Intent intent = getIntent();
        list = controller.getRoomList();

        adapter = new RoomAdapter(this, R.layout.room_list, list);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);

        Button add_btn = (Button) findViewById(R.id.addButton);
        add_btn.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RoomPopup2.class);
                startActivityForResult(intent, 2);
            }
        }));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), RoomPopup1.class);
                /* putExtra의 첫 값은 식별 태그, 뒤에는 다음 화면에 넘길 값 */
                intent.putExtra("Index", position);
                intent.putExtra("RoomNum", Integer.toString(list.get(position).getRoom_Num()));
                intent.putExtra("Price", Integer.toString(list.get(position).getPrice()));
                //intent.putExtra("checkIn", list.get(position).getCheckIn_date() + " " + list.get(position).getiTime());
                //intent.putExtra("checkOut", list.get(position).getCheckOut_date() + " " + list.get(position).getoTime());
                intent.putExtra("RoomType", list.get(position).getRoomType());
                intent.putExtra("Capacity", Integer.toString(list.get(position).getCapacity()));
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
                int pos = data.getIntExtra("Index", 0);

                if (result.equals("modify")) {
                    list.get(pos).setPrice(data.getIntExtra("Price", 0));
                    list.get(pos).setRoomType(data.getStringExtra("RoomType"));
                    list.get(pos).setCapacity(data.getIntExtra("Capacity", 0));
                }
                else if (result.equals("delete")) {
                    list.remove(pos);
                }

                adapter.notifyDataSetChanged();
            }
        }
        else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                String result = data.getStringExtra("Decision");

                if (result.equals("add")) {
                    int roomNum = data.getIntExtra("RoomNum", 0);
                    int priceOfDay = data.getIntExtra("Price", 0);
                    String roomType = data.getStringExtra("RoomType");
                    int capacity = data.getIntExtra("Capacity", 0);

                    list.add(new RoomInfo(roomNum, priceOfDay, roomType, capacity));
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }
}

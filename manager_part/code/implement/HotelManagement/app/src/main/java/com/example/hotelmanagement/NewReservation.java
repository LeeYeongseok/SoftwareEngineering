package com.example.hotelmanagement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NewReservation extends AppCompatActivity {

    ArrayList<String> rlist = new ArrayList<String>();
    ArrayList<RsvInfo> rsvlist;
    Controller controller = new Controller();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_reservation);

        Intent intent = getIntent();
        rsvlist = controller.checkReservation();

        for(RsvInfo rsv : rsvlist)
            rlist.add("Reservation " + Integer.toString(rsv.getRsv_Num()));
        //for (int i = 0; i<15; i++)
          //  rlist.add("Reservation " + Integer.toString(i+1));

        ArrayAdapter adpater = new ArrayAdapter(this, android.R.layout.simple_list_item_1, rlist);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adpater);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ReservationPopup.class);
                /* putExtra의 첫 값은 식별 태그, 뒤에는 다음 화면에 넘길 값 */
                intent.putExtra("data", rlist.get(position));
                startActivityForResult(intent, 1);
            }
        });
    }

}
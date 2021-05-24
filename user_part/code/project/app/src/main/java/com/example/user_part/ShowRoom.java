package com.example.user_part;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShowRoom extends AppCompatActivity {

    private ListView meetCondView;
    private MeetCondAdapter MCAdapter;
    private ArrayList<MeetCond> meetCondList;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        meetCondView = (ListView) findViewById(R.id.roomListView);
        meetCondList = new ArrayList<MeetCond>();
        meetCondList.add(new MeetCond("a호텔", 50000, (float) 4.0, R.drawable.room1, "15:00", "11:00", "수영장", "", "", true, 7000,1));
        meetCondList.add(new MeetCond("b호텔", 70000, (float) 4.2, R.drawable.room2, "15:00", "11:00" , "수영장", "", "", true, 7000,2));
        meetCondList.add(new MeetCond("c호텔", 60000, (float) 4.5, R.drawable.room1, "15:00", "11:00", "수영장", "", "", true, 8000,3));
        meetCondList.add(new MeetCond("d호텔", 50000, (float) 4.5, R.drawable.room2, "15:00", "11:00", "수영장", "", "", true,9000, 4));
        meetCondList.add(new MeetCond("e호텔", 40000, (float) 3.0, R.drawable.room1, "15:00", "11:00", "수영장", "", "", true, 10000,3));
        meetCondList.add(new MeetCond("f호텔", 30000, (float) 5.0, R.drawable.room2, "15:00", "11:00","수영장", "", "", false,9000, 1));
        meetCondList.add(new MeetCond("g호텔", 20000, (float) 2.0, R.drawable.room1, "15:00", "11:00", "수영장", "", "", true, 11000,2));
        meetCondList.add(new MeetCond("h호텔", 10000, (float) 3.0, R.drawable.room2, "15:00", "11:00", "수영장", "", "", true,10000, 4));
        meetCondList.add(new MeetCond("i호텔", 20000, (float) 3.0, R.drawable.room1, "15:00", "11:00", "수영장", "", "", true, 7000,4));
        meetCondList.add(new MeetCond("j호텔", 30000, (float) 6.0, R.drawable.room2, "15:00", "11:00","수영장", "", "", true,11000, 6));
        meetCondList.add(new MeetCond("k호텔", 40000, (float) 7.0, R.drawable.room1, "15:00", "11:00", "수영장", "", "", true, 8000,2));
        meetCondList.add(new MeetCond("l호텔", 50000, (float) 4.0, R.drawable.room2, "15:00", "11:00", "수영장", "", "", true,8000, 3));
        MCAdapter = new MeetCondAdapter(getApplicationContext(), meetCondList);
        meetCondView.setAdapter(MCAdapter);

    }

}
package com.example.user_part;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import java.util.ArrayList;

public class ShowRoom extends AppCompatActivity {

    public static Context context_ShowRoom;
    private ListView meetCondView;
    private MeetCondAdapter MCAdapter;
    private ArrayList<MeetCond> meetCondList;
    private MeetCond meetCond;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        context_ShowRoom = this;

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        meetCondView = (ListView) findViewById(R.id.roomListView);
        meetCondList = new ArrayList<MeetCond>();
        meetCondList.add(new MeetCond("a호텔", 50000, (float) 4.0, R.drawable.room1, "15:00", "11:00", "수영장", "상도", "?", true, 7000,1));
        meetCondList.add(new MeetCond("b호텔", 70000, (float) 4.2, R.drawable.room2, "15:00", "11:00" , "수영장", "장승배기", "!", true, 7000,2));
        meetCondList.add(new MeetCond("c호텔", 60000, (float) 4.5, R.drawable.room1, "15:00", "11:00", "수영장", "보라매", "ㅇ", true, 8000,3));
        meetCondList.add(new MeetCond("d호텔", 50000, (float) 4.5, R.drawable.room2, "15:00", "11:00", "수영장", "강남", "ㄹ", true,9000, 4));
        meetCondList.add(new MeetCond("e호텔", 40000, (float) 3.0, R.drawable.room1, "15:00", "11:00", "수영장", "흑석", "ㅎ", true, 10000,3));
        meetCondList.add(new MeetCond("f호텔", 30000, (float) 5.0, R.drawable.room2, "15:00", "11:00","수영장", "수서", "ㄴ", false,9000, 1));
        meetCondList.add(new MeetCond("g호텔", 20000, (float) 2.0, R.drawable.room1, "15:00", "11:00", "수영장", "용산", "ㄹ", true, 11000,2));
        meetCondList.add(new MeetCond("h호텔", 10000, (float) 3.0, R.drawable.room2, "15:00", "11:00", "수영장", "노량진", "ㅊ", true,10000, 4));
        meetCondList.add(new MeetCond("i호텔", 20000, (float) 3.0, R.drawable.room1, "15:00", "11:00", "수영장", "성수", "ㅁ", true, 7000,4));
        meetCondList.add(new MeetCond("j호텔", 30000, (float) 6.0, R.drawable.room2, "15:00", "11:00","수영장", "상수", "ㅈ", true,11000, 6));
        meetCondList.add(new MeetCond("k호텔", 40000, (float) 7.0, R.drawable.room1, "15:00", "11:00", "수영장", "홍대", "ㄹ", true, 8000,2));
        meetCondList.add(new MeetCond("l호텔", 50000, (float) 4.0, R.drawable.room2, "15:00", "11:00", "수영장", "신촌", "ㅎ", true,8000, 3));
        MCAdapter = new MeetCondAdapter(getApplicationContext(), meetCondList);
        meetCondView.setAdapter(MCAdapter);

        meetCondView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                meetCond = (MeetCond) MCAdapter.getItem(position);

                Intent intent = new Intent(ShowRoom.this, SelectRoom.class);
                ShowRoom.this.startActivity(intent);
            }

        });


    }

    public MeetCond getMeetCond() {
        return meetCond;
    }

    public void setMeetCond(MeetCond meetCond) {
        this.meetCond = meetCond;
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
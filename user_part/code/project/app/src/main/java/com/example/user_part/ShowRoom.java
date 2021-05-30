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
        meetCondList = ((SearchHotel)SearchHotel.context_SearchHotel).getMeetCond();
        //meetCondList.add(new MeetCond("a호텔", 50000, (float) 4.0, R.drawable.room1, "15:00", "11:00", "수영장", "상도", "?", true, 7000,1));
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
package com.example.user_part;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ShowRoom extends AppCompatActivity {

    public static Context context_ShowRoom;
    private ListView meetCondView;
    private MeetCondAdapter MCAdapter;
    private ArrayList<MeetCond> meetCondList;
    private MeetCond meetCond;
    private static boolean mAsyncTaskExecute = false;
    private static boolean mAsyncEnd = false;
    private WN end = new WN();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        context_ShowRoom = this;

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        meetCondView = (ListView) findViewById(R.id.roomListView);
        meetCondList = ((SearchHotel)SearchHotel.context_SearchHotel).getMeetCond();

        if(meetCondList.size()>0){
            WN wn = new WN();
            int k = 0;
            mAsyncEnd = true;
            for( ; k<meetCondList.size(); k++){
                if(mAsyncTaskExecute){
                    wn.mWait();
                } mAsyncTaskExecute = true;
                String url = meetCondList.get(k).getPicLink().replaceAll(" ", "");
                if(url.length()!=0){
                    DownloadImage downloadImage = new DownloadImage(wn, meetCondList.get(k));
                    downloadImage.execute(url);
                } else{
                    wn.mNotify();
                    mAsyncTaskExecute = false;
                }
            }
            if(mAsyncEnd){
                end.mWait();}

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


    }

    private class WN{

        synchronized public void mWait(){
            try {
                wait();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        synchronized public void mNotify(){
            try{
                notify();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class DownloadImage extends AsyncTask<String,Void, Bitmap>{

        private WN wn;
        private MeetCond meetCond;

        public DownloadImage(WN wn, MeetCond meetCond) {
            this.meetCond = meetCond;
            this.wn = wn;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bmp = null;
            String picurl = strings[0];
            System.out.println(picurl);
            try {
                URL url = new URL("http://" + picurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(8000);
                httpURLConnection.setReadTimeout(8000);
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                int responseStatusCode = httpURLConnection.getResponseCode();
                InputStream is;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    is = httpURLConnection.getInputStream();
                } else {
                    System.out.println("연결 실패");
                    is = httpURLConnection.getErrorStream();
                }

                bmp = BitmapFactory.decodeStream(is);
                System.out.println("background");
                System.out.println(bmp);
                is.close();

                meetCond.setPicture(bmp);
                mAsyncTaskExecute = false;
                wn.mNotify();

                if (meetCondList.get(meetCondList.size() - 1) == meetCond) {
                    mAsyncEnd = false;
                    end.mNotify();
                }
                System.out.println("background 끝");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmp;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            System.out.println("on post execute");
        }
    }

    public MeetCond getMeetCond() {
        return meetCond;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.home){
            finish();
            return true;
        }
        else return super.onOptionsItemSelected(item);
    }
}
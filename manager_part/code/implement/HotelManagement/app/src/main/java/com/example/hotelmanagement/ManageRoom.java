package com.example.hotelmanagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ManageRoom extends AppCompatActivity {
    ListView listView;
    private ArrayList<RoomInfo> list = new ArrayList<RoomInfo>();
    RoomAdapter adapter;
    String mJsonString;
    Intent intent;
    String hotelName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        intent = getIntent();
        hotelName = intent.getStringExtra("HotelName");

        super.onCreate(savedInstanceState);
        System.out.println("결과? "+hotelName);
        setContentView(R.layout.manage_room);

        GetData task = new GetData();
        task.execute();

    }

    private class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;
        private static final String TAG = "MyTag";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(ManageRoom.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response - " + result);

            if (result == null){

                System.out.println(errorString);
            }
            else if (result.contains("Undefined variable")) {
                list.clear();
                adapter.notifyDataSetChanged();
            }
            else {

                mJsonString = result;
                showResult();
            }
        }

        @Override
        protected String doInBackground(String... params) {

            String serverURL = "http://qmdlrhdfyd.synology.me:8080/getRoom.php";
            String postParameters = "hotelname=" + hotelName;


            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }

                bufferedReader.close();
                return sb.toString().trim();

            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();

                return null;
            }

        }
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

                    InsertData task = new InsertData();
                    task.execute("http://qmdlrhdfyd.synology.me:8080/updateInfo.php", hotelName, Integer.toString(list.get(pos).getRoom_Num()),
                                                Integer.toString(data.getIntExtra("Price", 0)), data.getStringExtra("RoomType"),
                                                Integer.toString(data.getIntExtra("Capacity", 0)));

                    GetData task2 = new GetData();
                    task2.execute();

                } else if (result.equals("delete")) {
                    InsertData task = new InsertData();
                    task.execute("http://qmdlrhdfyd.synology.me:8080/deleteInfo.php", hotelName, Integer.toString(list.get(pos).getRoom_Num()), " ", " ", " ");

                    GetData task2 = new GetData();
                    task2.execute();
                }

            }
        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                String result = data.getStringExtra("Decision");

                if (result.equals("add")) {
                    int roomNum = data.getIntExtra("RoomNum", 0);
                    int priceOfDay = data.getIntExtra("Price", 0);
                    String roomType = data.getStringExtra("RoomType");
                    int capacity = data.getIntExtra("Capacity", 0);

                    InsertData task = new InsertData();
                    task.execute("http://qmdlrhdfyd.synology.me:8080/insertInfo.php", hotelName,
                            Integer.toString(roomNum), Integer.toString(priceOfDay), roomType, Integer.toString(capacity));

                    GetData task2 = new GetData();
                    task2.execute();
                }
            }
        }
    }

    private void showResult(){
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("webnautes");

            list.clear();
            if (!mJsonString.contains("Undefined variable")) {
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject item = jsonArray.getJSONObject(i);
                    int id = item.getInt("roomID");
                    int price = item.getInt("costPerDay");
                    String roomType = item.getString("roomType");
                    int capacity = item.getInt("maxGuests");
                    String picture = " ";

                    RoomInfo roomInfo = new RoomInfo(id, price, roomType, capacity);
                    list.add(roomInfo);
                }
            }

            adapter = new RoomAdapter(this, R.layout.room_list, list);
            listView = (ListView) findViewById(R.id.listview);
            listView.setAdapter(adapter);

            Button add_btn = (Button) findViewById(R.id.addButton);
            add_btn.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
                public final void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), RoomPopup2.class);
                    intent.putExtra("HotelName", hotelName);
                    startActivityForResult(intent, 2);
                }
            }));

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View v, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(), RoomPopup1.class);

                    /* putExtra의 첫 값은 식별 태그, 뒤에는 다음 화면에 넘길 값 */
                    intent.putExtra("Index", position);
                    intent.putExtra("HotelName", hotelName);
                    intent.putExtra("RoomNum", Integer.toString(list.get(position).getRoom_Num()));
                    intent.putExtra("Price", Integer.toString(list.get(position).getPrice()));
                    intent.putExtra("RoomType", list.get(position).getRoomType());
                    intent.putExtra("Capacity", Integer.toString(list.get(position).getCapacity()));

                    startActivityForResult(intent, 1);
                }
            });

            for(RoomInfo r : list){
                System.out.println(r.getRoom_Num()+" "+r.getPrice()+" "+r.getCapacity());
            }

        } catch (JSONException e) {

            Log.d("TAG", "showResult : ", e);
        }

    }

    class InsertData extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog;
        private static final String TAG = "MyTag";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(ManageRoom.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {

            String serverURL = (String)params[0];
            String postParameters;
            String hotelName = (String)params[1];
            int roomNum = Integer.valueOf(params[2]);

            if(params[3].equals(" ")){
                //delete
                postParameters = "hotelname=" + hotelName + "&roomID=" + roomNum;
                System.out.println("삭제: "+hotelName+" "+roomNum);
            }
            else{
                //modify
                int costPerDay = Integer.valueOf(params[3]);
                String roomType = (String)params[4];
                int maxGuests = Integer.valueOf(params[5]);

                postParameters = "hotelname=" + hotelName + "&roomID=" + roomNum +"&price=" + costPerDay
                        + "&maxGuest=" + maxGuests + "&picture=" + " " + "&roomtype=" + roomType;
            }

            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }

                bufferedReader.close();
                return sb.toString();

            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }
        }
    }
}



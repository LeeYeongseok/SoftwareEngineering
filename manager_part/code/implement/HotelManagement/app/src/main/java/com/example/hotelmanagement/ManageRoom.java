package com.example.hotelmanagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetManager;
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
    Controller controller = new Controller();
    String mJsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("결과?");
        setContentView(R.layout.manage_room);

        GetData task = new GetData();
        task.execute();

        Intent intent = getIntent();

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
            //mTextViewResult.setText(result);
            Log.d(TAG, "response - " + result);

            if (result == null){

                System.out.println(errorString);
            }
            else {

                mJsonString = result;
                showResult();
            }
        }

        @Override
        protected String doInBackground(String... params) {

            //String searchKeyword1 = params[0]; //호텔 이름 받을 수 있으면 사용

            String serverURL = "http://qmdlrhdfyd.synology.me:8080/getRoom.php";
            //String postParameters = "hotelname=" + searchKeyword1;
            String postParameters = "hotelname=" + "a호텔";


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
                    list.get(pos).setPrice(data.getIntExtra("Price", 0));
                    list.get(pos).setRoomType(data.getStringExtra("RoomType"));
                    list.get(pos).setCapacity(data.getIntExtra("Capacity", 0));
                } else if (result.equals("delete")) {
                    list.remove(pos);
                }

                adapter.notifyDataSetChanged();
            }
        } else if (requestCode == 2) {
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
    private void showResult(){
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("webnautes");

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);
                int id = item.getInt("roomID");
                int price = item.getInt("costPerDay");
                String roomType = item.getString("roomType");
                int capacity = item.getInt("maxGuests");
                //String picture = item.getString("사진 링크");

                RoomInfo roomInfo = new RoomInfo(id, price, roomType, capacity);
                System.out.println("roomID: "+id+" costPerDay: "+price+" roomType: "+roomType+" capacity: "+capacity);
                list.add(roomInfo);

            }

            adapter = new RoomAdapter(this, R.layout.room_list, list);
            listView = (ListView) findViewById(R.id.listview);
            listView.setAdapter(adapter);

            Button add_btn = (Button) findViewById(R.id.addButton);
            add_btn.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
                public final void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), RoomPopup2.class);
                    startActivityForResult(intent, 2);
                    //GetData tast = new GetData();
                    //tast.execute();
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

            for(RoomInfo r : list){
                System.out.println(r.getRoom_Num()+" "+r.getPrice()+" "+r.getCapacity());
            }
            //mListViewList.setAdapter(adapter);

        } catch (JSONException e) {

            Log.d("TAG", "showResult : ", e);
        }

    }

}



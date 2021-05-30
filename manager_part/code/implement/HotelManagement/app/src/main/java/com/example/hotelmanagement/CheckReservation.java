package com.example.hotelmanagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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

public class CheckReservation extends AppCompatActivity {
    ListView listView;
    private ArrayList<RsvInfo> list;
    ReservationAdapter adapter;
    Controller controller = new Controller();
    String mJsonString;

    String hotelID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_reservation);

        Intent intent = getIntent();
        //list = controller.checkReservation(); // 여기는 승인 받은 예약만 띄워야 하는데 지금 decision 값을 저장하는 부분이 구현이 안돼서 일단 다 받아옴

        GetData task = new GetData();
        task.execute();

        /*if(list != null){
            adapter = new ReservationAdapter(this, R.layout.reservation_list, list);
            listView = (ListView) findViewById(R.id.listview);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View v, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(), ReservationPopup2.class);
                    // putExtra의 첫 값은 식별 태그, 뒤에는 다음 화면에 넘길 값 //
                    intent.putExtra("RsvNum", Integer.toString(list.get(position).getRsv_Num()));
                    intent.putExtra("RoomNum", Integer.toString(list.get(position).getRoom_Num()));
                    intent.putExtra("checkIn", list.get(position).getCheckIn_date() + " " + list.get(position).getiTime());
                    intent.putExtra("checkOut", list.get(position).getCheckOut_date() + " " + list.get(position).getoTime());
                    intent.putExtra("NumOfPeople", Integer.toString(list.get(position).getNumOfPeople()));
                    intent.putExtra("Meal", list.get(position).getMeal() ? "O":"X");
                    startActivity(intent);
                }
            });
        }*/

    }
    private class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;
        private static final String TAG = "MyTag";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(CheckReservation.this,
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

            String serverURL = "http://qmdlrhdfyd.synology.me:8080/getFinishedReservation.php";
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
   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //데이터 받기
                int d = 0;
                String result = data.getStringExtra("Decision");
                if (result.equals("true")) { //0이 초기화, 1이 true, 2가 false
                    // RsvInfo의 d 값 true로 변경
                    d=1;
                }
                else if (result.equals("false")) {
                    // RsvInfo의 d 값 false로 변경
                    d=2;
                }

                int pos = data.getIntExtra("Index", 0);
                list.remove(pos);
                // 저장하도록 바꾸기

                controller.acceptOrReject(pos, d);
                adapter.notifyDataSetChanged();
            }
        }
    }*/
    private void showResult(){
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("webnautes");

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);
                int reserNum = item.getInt("reserNum");
                int roomID = item.getInt("roomID");
                String checkin = item.getString("checkin");
                String checkout = item.getString("checkout");
                //boolean meal = item.getInt("meal");
                boolean meal;
                if(item.getInt("meal")==1) meal = true;
                else meal = false;
                int NumofPeople = item.getInt("NumofPeople");
                //String picture = item.getString("사진 링크");

                RsvInfo rsvInfo = new RsvInfo(reserNum, roomID, checkin, checkout, meal, "", "", NumofPeople);
                System.out.println(reserNum+" "+roomID+" "+checkin+" "+checkout+" "+meal+" "+NumofPeople);
                list.add(rsvInfo);

            }

            for(RsvInfo r : list){
                System.out.println(r.getRoom_Num()+" "+r.getRsv_Num()+" "+r.getNumOfPeople());
            }
            //mListViewList.setAdapter(adapter);


            /*adapter = new ReservationAdapter(this, R.layout.reservation_list, list);
            listView = (ListView) findViewById(R.id.listview);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View v, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(), ReservationPopup1.class);
                    // putExtra의 첫 값은 식별 태그, 뒤에는 다음 화면에 넘길 값 //
                    intent.putExtra("Index", position);
                    intent.putExtra("RsvNum", Integer.toString(list.get(position).getRsv_Num()));
                    intent.putExtra("RoomNum", Integer.toString(list.get(position).getRoom_Num()));
                    intent.putExtra("checkIn", list.get(position).getCheckIn_date() + " " + list.get(position).getiTime());
                    intent.putExtra("checkOut", list.get(position).getCheckOut_date() + " " + list.get(position).getoTime());
                    intent.putExtra("NumOfPeople", Integer.toString(list.get(position).getNumOfPeople()));
                    intent.putExtra("Meal", list.get(position).getMeal() ? "O":"X");
                    startActivityForResult(intent, 1);
                }
            });*/

            if(list != null) {
                adapter = new ReservationAdapter(this, R.layout.reservation_list, list);
                listView = (ListView) findViewById(R.id.listview);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView parent, View v, int position, long id) {
                        Intent intent = new Intent(getApplicationContext(), ReservationPopup2.class);
                        // putExtra의 첫 값은 식별 태그, 뒤에는 다음 화면에 넘길 값 //
                        intent.putExtra("RsvNum", Integer.toString(list.get(position).getRsv_Num()));
                        intent.putExtra("RoomNum", Integer.toString(list.get(position).getRoom_Num()));
                        intent.putExtra("checkIn", list.get(position).getCheckIn_date() + " " + list.get(position).getiTime());
                        intent.putExtra("checkOut", list.get(position).getCheckOut_date() + " " + list.get(position).getoTime());
                        intent.putExtra("NumOfPeople", Integer.toString(list.get(position).getNumOfPeople()));
                        intent.putExtra("Meal", list.get(position).getMeal() ? "O" : "X");
                        startActivity(intent);
                    }
                });
            }

        } catch (JSONException e) {

            Log.d("TAG", "showResult : ", e);
        }
    }
}
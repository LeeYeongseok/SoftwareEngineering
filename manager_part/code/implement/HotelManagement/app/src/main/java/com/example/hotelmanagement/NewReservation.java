package com.example.hotelmanagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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

public class NewReservation extends AppCompatActivity {
    ListView listView;
    private ArrayList<RsvInfo> list = new ArrayList<RsvInfo>();
    ReservationAdapter adapter;
    String mJsonString;
    Intent intent;
    String hotelName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_reservation);
        intent = getIntent();
        hotelName = intent.getStringExtra("HotelName");

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

            progressDialog = ProgressDialog.show(NewReservation.this,
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

            String serverURL = "http://qmdlrhdfyd.synology.me:8080/getReservation.php";
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
                int pos = data.getIntExtra("Index", 0);

                //데이터 받기
                //d = 0 아무 결정 안한 상태
                String result = data.getStringExtra("Decision");
                if (result.equals("true")) { //0이 초기화, 1이 true, 2가 false
                    // RsvInfo의 d 값 true로 변경
                    //d=1 수락

                    InsertData task = new InsertData();
                    task.execute("http://qmdlrhdfyd.synology.me:8080/answerReservation.php"
                            ,hotelName,Integer.toString(list.get(pos).getRsv_Num()),"1");
                }
                else if (result.equals("false")) {
                    // RsvInfo의 d 값 false로 변경
                    //d=2 거절

                    InsertData task = new InsertData();
                    task.execute("http://qmdlrhdfyd.synology.me:8080/answerReservation.php"
                            ,hotelName,Integer.toString(list.get(pos).getRsv_Num()),"2");
                }
                list.remove(pos);

                adapter.notifyDataSetChanged();
            }
        }
    }
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

            adapter = new ReservationAdapter(this, R.layout.reservation_list, list);
            listView = (ListView) findViewById(R.id.listview);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View v, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(), ReservationPopup1.class);
                    /* putExtra의 첫 값은 식별 태그, 뒤에는 다음 화면에 넘길 값 */
                    intent.putExtra("Index", position);
                    intent.putExtra("RsvNum", Integer.toString(list.get(position).getRsv_Num()));
                    intent.putExtra("RoomNum", Integer.toString(list.get(position).getRoom_Num()));
                    intent.putExtra("checkIn", list.get(position).getCheckIn_date() + " " + list.get(position).getiTime());
                    intent.putExtra("checkOut", list.get(position).getCheckOut_date() + " " + list.get(position).getoTime());
                    intent.putExtra("NumOfPeople", Integer.toString(list.get(position).getNumOfPeople()));
                    intent.putExtra("Meal", list.get(position).getMeal() ? "O":"X");
                    startActivityForResult(intent, 1);
                }
            });

        } catch (JSONException e) {

            Log.d("TAG", "showResult : ", e);
        }
    }


    class InsertData extends AsyncTask<String, Void, String>{
        private static final String TAG = "myTag";
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(NewReservation.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            //mTextViewResult.setText(result);
            Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {

            String hotelName = (String)params[1];
            int rsvNum = Integer.valueOf(params[2]);
            int decision = Integer.valueOf(params[3]);

            System.out.println("예약 결정 "+hotelName+" 예약번호 No."+rsvNum+" 예약 결과:"+decision);

            String serverURL = (String)params[0];
            String postParameters = "hotelname="+hotelName+"&reservationNum="+rsvNum+"&status=" + decision;


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
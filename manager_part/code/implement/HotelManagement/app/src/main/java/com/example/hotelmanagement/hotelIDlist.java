package com.example.hotelmanagement;

import android.app.ProgressDialog;
import android.hardware.input.InputManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

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


public class hotelIDlist extends AsyncTask<String, Void, String>{
    public ArrayList<String > IDList = new ArrayList<String>();
    ProgressDialog progressDialog;
    String errorString = null;
    private static final String TAG = "MyTag";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected void onPostExecute(String result) {
        try {
            // PHP에서 받아온 JSON 데이터를 JSON오브젝트로 변환
            JSONObject jObject = new JSONObject(result);
            // results라는 key는 JSON배열로 되어있다.
            JSONArray results = jObject.getJSONArray("");
            String zz = "";
            zz += "hotelName : " + jObject.get("호텔이름");

            for ( int i = 0; i < results.length(); ++i ) {
                JSONObject temp = results.getJSONObject(i);
                IDList.add(temp.getString("호텔이름"));
            }
            for(String str : IDList)
                System.out.println("호텔이름: "+str);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    @Override
    protected String doInBackground(String... params) {

        String searchKeyword1 = params[0];
        //String searchKeyword2 = params[1];

        String serverURL = "http://qmdlrhdfyd.synology.me:8080/isHotel.php";
        String postParameters = "호텔이름=" + searchKeyword1;


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
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
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

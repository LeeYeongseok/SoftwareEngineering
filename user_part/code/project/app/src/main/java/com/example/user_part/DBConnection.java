package com.example.user_part;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DBConnection extends StringRequest {

    final static private String URL = ""; //서버 주소
    private RsvCond r;
    private MeetCond m;

    //params: 선택한 방을 다른 class로부터 받아옴
    public DBConnection(RsvCond rsvCond, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        r = rsvCond;
    }

    //방 정보 전달 받기
    public MeetCond getRoominfo(){
        return m;
    }

}

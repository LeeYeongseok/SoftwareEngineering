package com.example.hotelmanagement;

import android.content.Context;
import android.content.res.AssetManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DBConnection {

    private RoomInfo room;
    private ArrayList<String> IDList = new ArrayList<String>();
    private ArrayList<RsvInfo> RsvList = new ArrayList<RsvInfo>();
    private ArrayList<RoomInfo> RoomList = new ArrayList<RoomInfo>();
    private ArrayList<RsvInfo> AcceptRsvList = new ArrayList<RsvInfo>();
    private Context context=null;
    ArrayList<Integer> reject_RsvNum = new ArrayList<Integer>();
    ArrayList<Integer> accept_RsvNum = new ArrayList<Integer>();

    private t_data Data;

    public DBConnection() {
        //this.context=context;
        Data = new t_data();
        IDList = Data.getIDList();
        RsvList = Data.getRsvList();
        //RoomList = Data.getRoomList();
        //String roomJson = getJsonString();
        //jsonParsing(roomJson);

        // 지우기
        System.out.println("방 목록: ");
        for(RoomInfo r : RoomList) {
            System.out.println("방 번호: " + r.getRoom_Num());
            System.out.println("하루 가격: " + r.getPrice());
            System.out.println("방 옵션: " + r.getRoomType());
            System.out.println("최대 수용 인원: " + r.getCapacity() + "\n");
        }

        System.out.println("예약 목록: ");
        for(RsvInfo r : RsvList) {
            System.out.println("예약 번호: " + r.getRsv_Num());
            System.out.println("방 번호: " + r.getRoom_Num());
            System.out.println("체크인 날짜: " + r.getCheckIn_date());
            System.out.println("체크인 시간: " + r.getiTime());
            System.out.println("체크아웃 날짜: "+r.getCheckOut_date());
            System.out.println("체크아웃 시간: "+r.getoTime());
            System.out.println("식사 여부: "+ r.getMeal());
            System.out.println("예약 인원: "+r.getNumOfPeople()+"\n");
        }

        System.out.println("hotel ID: ");
        for(String id : IDList)
            System.out.println(id);
        //

    }
    private String getJsonString(){
        String json = "";

        try{
            AssetManager am = this.context.getResources().getAssets();
            InputStream is=am.open("test.json");
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json=new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
    private void jsonParsing(String json){
        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONArray roomList = jsonObject.getJSONArray("Room");
            for(int i=0;i<roomList.length();i++){
                JSONObject roomObject = roomList.getJSONObject(i);
                RoomInfo room = new RoomInfo();
                room.setRoom_Num(roomObject.getInt("Room_Num"));
                room.setPrice(roomObject.getInt("priceOfDay"));
                room.setRoomType(roomObject.getString("roomType"));
                room.setCapacity(roomObject.getInt("capacity"));
                this.RoomList.add(room);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<String> getIDList(){
        IDList=Data.getIDList();
        return IDList;
    }
    public ArrayList<RsvInfo> getRsvList(){
        RsvList=Data.getRsvList();
        return RsvList;
    }
    public ArrayList<RsvInfo> getAcceptRsvList(){
        AcceptRsvList=Data.getAcceptRsvList();
        return AcceptRsvList;
    }
    public ArrayList<RoomInfo> getRoomList(){
        RoomList=Data.getRoomList();
        return RoomList;
    }

    public void setRsvList(ArrayList<RsvInfo> rsvList){
        for(RsvInfo rsv : rsvList){
            //
        }
        this.RsvList = rsvList;
        Data.update_RsvList(this.RsvList);
    }

    public void setRoomList(ArrayList<RoomInfo> roomList){
        this.RoomList = roomList;
        Data.update_RoomList(this.RoomList);
    }

    public void Rsv_Record(ArrayList<RsvInfo> rsvInfo) { //아마 안쓸지도..
        for (RsvInfo one_rsv : rsvInfo) {
            if (one_rsv.getDecision() == 2) //0이 초기화, 1이 true, 2가 false
                reject_RsvNum.add(one_rsv.getRsv_Num()); // 거절된 예약에 대한 리스트 작성
            else if (one_rsv.getDecision() == 1) //0이 초기화, 1이 true, 2가 false
                accept_RsvNum.add(one_rsv.getRsv_Num()); // 승인된 예약에 대한 리스트 작성
        }
        // 예약에 대한 수락or거절에 대해 DB에 전달 ~~??
        System.out.println("Reject Reservation: ");
        for (Integer one_rsvNum : reject_RsvNum) System.out.println(one_rsvNum);
        System.out.println("\nAccept Reservation: ");
        for (Integer one_rsvNum : accept_RsvNum) System.out.println(one_rsvNum);

        Data.update_RsvList(this.RsvList);
    }

    public void Rsv_Record_v2(int index, int d){
        this.RsvList.get(index).setDecision(d);
        System.out.println("예약 결정 된 예약 정보: "+this.RsvList.get(index).getRsv_Num());

        Data.update_RsvList(this.RsvList);
    }
    public void Room_add(RoomInfo roomInfo){
        this.RoomList.add(roomInfo);
        Data.update_RoomList(this.RoomList);
    }
    public void Room_modify(ArrayList<RoomInfo> roomlist) {
        // 입력 혹은 수정된 방 정보에 대한 정보 전달

        /*System.out.println("Room Number: "+roomInfo.getRoom_Num());
        System.out.println("Room Type: "+roomInfo.getRoomType());
        System.out.println("Day of Price: "+roomInfo.getPrice());
        System.out.println("Room Capacity: "+roomInfo.getCapacity());

        room = roomInfo;

        for(RoomInfo room : this.RoomList)
            if(room.getRoom_Num()==roomInfo.getRoom_Num())*/

        //this.RoomList=roomInfo;

        this.RoomList=roomlist;
        Data.update_RoomList(this.RoomList);
    }
    public void Room_delete(int roomNum) {
        int index=-1;
        for(RoomInfo room : this.RoomList)
        {
            if(room.getRoom_Num()==roomNum)
                index=this.RoomList.indexOf(room);
        }
        if(index==-1)
            return;

        this.RoomList.remove(index);
        Data.update_RoomList(this.RoomList);
    }
}
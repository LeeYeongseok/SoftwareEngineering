package com.example.hotelmanagement;

import java.util.ArrayList;

public class DBConnection {

    private RoomInfo room;
    private RsvInfo rsv;
    private ArrayList<String> IDList = new ArrayList<String>();
    private ArrayList<RsvInfo> RsvList = new ArrayList<RsvInfo>();

    public DBConnection() {

    }
    public ArrayList<String> getIDList(){
        return IDList;
    }
    public ArrayList<RsvInfo> getRsv(){
        return RsvList;
    }
    public void Rsv_Record(boolean d) {
        // 예약에 대한 수락or거절에 대해 DB에 전달
    }
    public void Room_Record(RoomInfo roomInfo) {
        // 입력 혹은 수정된 방 정보에 대한 정보 전달
        room = roomInfo;
    }
    public void Room_delete(int roomNum) {

    }

}
package com.example.hotelmanagement;

public class RoomMenu {

    RoomInfo room;
    int menu=0;

    // 사진은 object 형식??
    public void add(RoomInfo roomInfo) {
        room=roomInfo;
        // 함수 pageMaker에 넘겨 줄 페이지 메뉴가 리턴값 -> 메소드 리턴형 int?
    }
    public void delete(int roomNum) {

    }
    public void modify(RoomInfo roomInfo) {
        room=roomInfo;
    }


}

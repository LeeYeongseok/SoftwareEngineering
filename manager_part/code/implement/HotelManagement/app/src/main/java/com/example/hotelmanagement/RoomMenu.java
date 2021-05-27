package com.example.hotelmanagement;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RoomMenu {

    RoomInfo room;
    DBConnection db = new DBConnection();
    ArrayList<RoomInfo> roomlist;

    public RoomMenu(int menuNum, RoomInfo roomInfo){
        switch (menuNum){
            case 1:
                add(roomInfo);
                break;
            case 2:
                delete(roomInfo.getRoom_Num());
                break;
            case 3:
                modify(roomInfo);
                break;
        }

    }
    // 사진은 object 형식??
    public void add(RoomInfo roomInfo) {
        //room=roomInfo;
        // 함수 pageMaker에 넘겨 줄 페이지 메뉴가 리턴값 -> 메소드 리턴형 int?
        db.Room_add(roomInfo);

    }
    public void delete(int roomNum) {
        db.Room_delete(roomNum);
    }
    public void modify(RoomInfo roomInfo) {
        //room=roomInfo;
        int index=-1;
        roomlist = db.getRoomList();
        for(RoomInfo room : roomlist)
        {
            if(room.getRoom_Num()==roomInfo.getRoom_Num())
                index = roomlist.indexOf(room);
        }
        if(index==-1)
            return;

        roomlist.get(index).setRoomList(roomInfo.getRoom_Num(), roomInfo.getPrice(), roomInfo.getRoomType(), roomInfo.getCapacity());

        //db로 넘기기
        db.Room_modify(roomlist);
    }
}

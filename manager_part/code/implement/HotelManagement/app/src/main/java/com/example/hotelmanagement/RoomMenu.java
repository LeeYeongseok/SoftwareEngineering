package com.example.hotelmanagement;

public class RoomMenu {

    RoomInfo room;
    //int menu=0;
    DBConnection db = new DBConnection();

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
        db.Room_Record(roomInfo);
    }
    public void delete(int roomNum) {
        db.Room_delete(roomNum);
    }
    public void modify(RoomInfo roomInfo) {
        //room=roomInfo;
        db.Room_Record(roomInfo);
    }

}

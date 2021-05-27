package com.example.hotelmanagement;

import java.util.ArrayList;

public class t_data {
    private ArrayList<RoomInfo> roomList = new ArrayList<RoomInfo>();
    private ArrayList<RsvInfo> rsvList = new ArrayList<RsvInfo>();
    private ArrayList<String> IDList = new ArrayList<String>();
    private RoomInfo room;
    private RsvInfo rsv;

    public t_data(){
        roomList = new ArrayList<RoomInfo>();
        rsvList = new ArrayList<RsvInfo>();
        IDList = new ArrayList<String>();

        // 방 정보 입력 (6개)
        room=new RoomInfo();
        setRoomList(101, 95, "Single Room", 1);
        roomList.add(room);

        room=new RoomInfo();
        setRoomList(102, 100, "Single Room", 2);
        roomList.add(room);

        room=new RoomInfo();
        setRoomList(103, 115, "Double Room", 3);
        roomList.add(room);

        room=new RoomInfo();
        setRoomList(104, 120, "Triple Room", 4);
        roomList.add(room);

        room=new RoomInfo();
        setRoomList(201, 300, "Sweet Room", 5);
        roomList.add(room);

        room=new RoomInfo();
        setRoomList(202, 205,"Sweet Room", 5);
        roomList.add(room);

        /*for(RoomInfo r : roomList){
            System.out.println("방 번호: "+r.getRoom_Num());
            System.out.println("하루 가격: "+r.getPrice());
            System.out.println("방 옵션: "+r.getRoomType());
            System.out.println("최대 수용 인원: "+r.getCapacity()+"\n");
        }*/


        // 예약 정보 입력 (5개)
        rsv = new RsvInfo();
        setRsvList(1, 101,"pm 3", "pm 12", true, "June 1", "June 3", 2);
        rsvList.add(rsv);

        rsv = new RsvInfo();
        setRsvList(2, 102,"pm 3", "pm 12", false, "June 1", "June 3", 2);
        rsvList.add(rsv);

        rsv = new RsvInfo();
        setRsvList(3, 103,"pm 3", "pm 12", true, "June 1", "June 3", 3);
        rsvList.add(rsv);

        rsv = new RsvInfo();
        setRsvList(4, 201,"pm 3", "pm 12", false, "June 1", "June 3", 5);
        rsvList.add(rsv);

        rsv = new RsvInfo();
        setRsvList(5, 202,"pm 3", "pm 12", true, "June 1", "June 3", 3);
        rsvList.add(rsv);

        // 호텔 id 입력 (5개)
        IDList.add("30283829"); IDList.add("200"); IDList.add("300"); IDList.add("400"); IDList.add("500");
    }

    public ArrayList<RoomInfo> getRoomList(){
        return roomList;
    }
    public ArrayList<RsvInfo> getRsvList(){
        return rsvList;
    }
    public ArrayList<String> getIDList(){
        return IDList;
    }

    private void setRoomList(int Room_Num, int priceOfDay, String roomType, int capacity){
        room.setRoom_Num(Room_Num);
        room.setPrice(priceOfDay);
        room.setRoomType(roomType);
        room.setCapacity(capacity);
    }
    private void setRsvList(int Rsv_Num, int Room_Num, String iTime, String oTime, boolean meal, String checkIn_date, String checkOut_date, int NumOfPeople){
        rsv.setRsv_Num(Rsv_Num);
        rsv.setRoom_Num(Room_Num);
        rsv.setiTime(iTime);
        rsv.setoTime(oTime);
        rsv.setMeal(meal);
        rsv.setCheckIn_date(checkIn_date);
        rsv.setCheckOut_date(checkOut_date);
        rsv.setNumOfPeople(NumOfPeople);
    }

    public void update_RoomList(ArrayList<RoomInfo> roomList){
        this.roomList=roomList;
    }
    public void update_RsvList(ArrayList<RsvInfo> rsvList){
        this.rsvList=rsvList;
    }
}

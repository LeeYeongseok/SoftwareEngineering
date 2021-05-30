package com.example.hotelmanagement;

import java.util.ArrayList;

public class t_data {
    private ArrayList<RoomInfo> roomList = new ArrayList<RoomInfo>();
    private ArrayList<RsvInfo> rsvList = new ArrayList<RsvInfo>();
    private ArrayList<String> IDList = new ArrayList<String>();
    private ArrayList<RsvInfo> AcceptRsvList = new ArrayList<RsvInfo>();
    private RoomInfo room;
    private RsvInfo rsv;

    public t_data(){
        roomList = new ArrayList<RoomInfo>();
        rsvList = new ArrayList<RsvInfo>();
        IDList = new ArrayList<String>();

        // 방 정보 입력 (6개)
        roomList.add(new RoomInfo(101, 95, "Single Room", 1));
        roomList.add(new RoomInfo(102, 100, "Single Room", 2));
        roomList.add(new RoomInfo(103, 115, "Double Room", 3));
        roomList.add(new RoomInfo(104, 120, "Triple Room", 4));
        roomList.add(new RoomInfo(201, 300, "Sweet Room", 5));
        roomList.add(new RoomInfo(202, 205,"Sweet Room", 5));

        /*for(RoomInfo r : roomList){
            System.out.println("방 번호: "+r.getRoom_Num());
            System.out.println("하루 가격: "+r.getPrice());
            System.out.println("방 옵션: "+r.getRoomType());
            System.out.println("최대 수용 인원: "+r.getCapacity()+"\n");
        }*/

        // 예약 정보 입력 (5개)
        rsvList.add(new RsvInfo(202101, 201, "10:00", "12:00", true, "5/28", "5/31", 3));
        rsvList.add(new RsvInfo(202102, 102,"15:00", "13:00", false, "5/28", "5/29", 2));
        rsvList.add(new RsvInfo(202103, 103,"12:00", "12:00", true, "6/3", "6/5", 3));
        rsvList.add(new RsvInfo(202104, 304,"9:00", "10:00", false, "5/31", "6/3", 5));
        rsvList.add(new RsvInfo(202105, 202,"10:00", "17:00", true, "6/1", "6/3", 3));

        // 호텔 id 입력 (5개)
        IDList.add("30283829");
        IDList.add("200");
        IDList.add("300");
        IDList.add("400");
        IDList.add("500");
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
    public ArrayList<RsvInfo> getAcceptRsvList() {return AcceptRsvList;}
//<<<<<<< HEAD
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
        for(int i=0;i<rsvList.size();i++)
        {
            if(rsvList.get(i).getDecision()==2) //0이 초기화, 1이 true, 2가 false
                this.rsvList.remove(i);
        }

        for(RsvInfo rsv : this.rsvList)
            rsv.printRsvList();
    }
}
//=======
//}
//>>>>>>> 38e0d413a07e46a0c1735d1a7a439354fe4ebe66

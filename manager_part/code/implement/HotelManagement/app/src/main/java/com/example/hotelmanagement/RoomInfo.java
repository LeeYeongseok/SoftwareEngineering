package com.example.hotelmanagement;

public class RoomInfo {
    private int Room_Num;
    private int priceOfDay; //1박 가격
    //private String iTime; //체크인 시간??
    //private String oTime; //체크아웃 시간??
    private String roomType; //방 타입
    private int capacity; //방 수용인원(최대)

    public RoomInfo(){

    }
    public RoomInfo(int Room_Num, int priceOfDay, String roomType, int capacity) {
        this.Room_Num=Room_Num;
        this.priceOfDay=priceOfDay;
        this.roomType=roomType;
        this.capacity=capacity;
    }

    public int getRoom_Num(){return Room_Num;}
    public void setRoom_Num(int Room_Num){this.Room_Num=Room_Num;}
    public int getPrice() {
        return priceOfDay;
    }
    public void setPrice(int priceOfDay) {
        this.priceOfDay=priceOfDay;
    }
    //public String getiTime() { return iTime; }
    //public void setiTime(String iTime) { this.iTime=iTime; }
    //public String getoTime() { oTime; }
    //public void setoTime(String oTime) { this.oTime=oTime; }
    public String getRoomType() {
        return roomType;
    }
    public void setRoomType(String roomType) {
        this.roomType=roomType;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity=capacity;
    }

    public void setRoomList(int Room_Num, int priceOfDay, String roomType, int capacity){
        this.Room_Num=Room_Num;
        this.priceOfDay=priceOfDay;
        this.roomType=roomType;
        this.capacity=capacity;
    }

    public void printRoomList(){
        System.out.println(Room_Num+" "+priceOfDay+" "+roomType+" "+capacity);
    }
}

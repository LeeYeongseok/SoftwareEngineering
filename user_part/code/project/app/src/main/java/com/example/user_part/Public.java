public class SelRoom {
    public String hotelName; //호텔 이름
    public int priceOfDay; //1박 당 가격
    public float grade;    //평점
    public String review;  //리뷰
    public int picture;   //사진
    public String iTime;  //체크인 시간
    public String oTime;  //체크아웃 시간
    public String exFacility; //부대시설
    public String location; //위치
    public String roomType; //방 타입
    public boolean meal;   //식사
    public int mealPrice;   //식사가격
    public int maxHeadCnt; //최대 인수
}

public class RoomOpt { //사용자가 선택하는 옵션
    public int stayNight; //머무는 일 수
    public int headCnt; //인원
    public boolean meal; //식사
}


public class Calculator {
    public RoomOpt op;  // 방 옵션
    public SelRoom sel; // 선택한 방

    private int calculate(RoomOpt op, SelRoom sel){
        public int prc = sel.priceOfDay*op.stayNight;
        if(sel.meal){
            prc += sel.mealPrice* op.stayNight;
        }
        return prc;
    }

}

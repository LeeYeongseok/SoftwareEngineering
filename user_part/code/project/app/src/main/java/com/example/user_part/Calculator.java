package com.example.user_part;

import java.util.concurrent.CopyOnWriteArrayList;

public class Calculator {

    RoomOpt op;  // 방 옵션
    SelRoom sel; // 선택한 방

    public Calculator(RoomOpt op, SelRoom sel){
        this.op = op;
        this.sel = sel;
    }

    void setOp(RoomOpt op){
        this.op = op;
    }
    RoomOpt getop(){
        return op;
    }
    void setSel(SelRoom sel){
        this.sel = sel;
    }
    SelRoom getSel(){
        return sel;
    }


    public int calculate(){
       int prc;
       int priceOfDay = sel.getPriceOfDay();
       int stayNight =  op.getStayNight();
       int mealCnt = op.getMealCnt();
        int mealPrice = sel.getMealPrice();
       boolean isMeal = op.isMeal();

        prc = priceOfDay * stayNight;
        if(isMeal){
            prc += mealPrice * mealCnt ;
        }
        return prc;
    }

}

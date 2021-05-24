package com.example.user_part;

public class RoomOpt { //사용자가 선택하는 옵션
    private int stayNight; //머무는 일 수
    private int headCnt; //인원
    private boolean meal; //식사
    private int mealCnt; //끼니

    public RoomOpt(int stayNight,
                   int headCnt,
                   boolean meal,
                   int mealCnt) {

        this.stayNight = stayNight;
        this.headCnt = headCnt;
        this.meal = meal;
        this.mealCnt = mealCnt;
    }
    public int getStayNight() {
        return stayNight;
    }

    public void setStayNight(int stayNight) {
        this.stayNight = stayNight;
    }

    public int getHeadCnt() {
        return headCnt;
    }

    public void setHeadCnt(int headCnt) {
        this.headCnt = headCnt;
    }

    public boolean isMeal() {
        return meal;
    }

    public void setMeal(boolean meal) {
        this.meal = meal;
    }

    public int getMealCnt() {
        return mealCnt;
    }

    public void setMealCnt(int mealCnt) {
        this.mealCnt = mealCnt;
    }
}

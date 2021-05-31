package com.example.hotelmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ReservationAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<RsvInfo> data; //Item 목록을 담을 배열
    private int layout;

    public ReservationAdapter(Context context, int layout, ArrayList<RsvInfo> data) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.layout = layout;
    }

    @Override
    public int getCount() { //리스트 안 Item의 개수를 센다.
        return data.size();
    }

    @Override
    public String getItem(int position) {
        return "";
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }
        RsvInfo rvsInfo = data.get(position);

        //이름 등 정보 연동
        TextView roomNum = (TextView) convertView.findViewById(R.id.roomNum);
        roomNum.setText("Room " + Integer.toString(rvsInfo.getRoom_Num()));

        //전화번호 연동
        TextView time = (TextView) convertView.findViewById(R.id.time);
        time.setText(rvsInfo.getCheckIn_date() + " " + rvsInfo.getiTime()
                + " ~ " + rvsInfo.getCheckOut_date() + " " + rvsInfo.getoTime());


        return convertView;
    }
}

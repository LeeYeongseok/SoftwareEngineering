package com.example.user_part;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MeetCondAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MeetCond> meetCondList;

    public MeetCondAdapter(Context context, ArrayList<MeetCond> meetCondList) {
        this.context = context;
        this.meetCondList = meetCondList;
    }

    @Override
    public int getCount() {
        return meetCondList.size();
    }

    @Override
    public Object getItem(int position) {
        return meetCondList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.room_list, null);
        ImageView prev = (ImageView) v.findViewById(R.id.room_preview);
        TextView hotelName = (TextView) v.findViewById(R.id.hotel_name);
        TextView roomSpec = (TextView) v.findViewById(R.id.spec);
        TextView price = (TextView) v.findViewById(R.id.price);

        prev.setImageResource(meetCondList.get(i).getPicture());
        hotelName.setText(meetCondList.get(i).getHotelName());
        String meal = (meetCondList.get(i).isMeal())? "O": "X";
        roomSpec.setText("평점: " + meetCondList.get(i).getGrade()+" 체크인: "+ meetCondList.get(i).getITime() +
                        " 최대인수: " + meetCondList.get(i).getMaxHeadCnt()
                + "\n식사: " + meal + " 부대시설: " + meetCondList.get(i).getExFacility());
        price.setText(Integer.toString(meetCondList.get(i).getPriceOfDay()));

        //v.setTag(meetCondList.get(i).);
        return v;
    }
}

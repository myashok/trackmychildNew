package com.example.kanchicoder.trackmychildparent;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by FamilyAngel on 11/8/2016.
 */

public class ScheduleListAdapter extends ArrayAdapter<String> {
    private String[] busIds;
    private String[] busStopNames;
    private String[] arrivalTimes;
    private String[] departureTimes;
    Context context;

    public ScheduleListAdapter(Activity context, String[] busIds, String[] busStopNames, String[] arrivalTimes, String[] departureTimes) {
        super(context, R.layout.second_frag);
        this.context = context;
        this.busIds = busIds;
        this.busStopNames = busStopNames;
        this.arrivalTimes = arrivalTimes;
        this.departureTimes = departureTimes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //LayoutInflater inflater = context.getLayoutInflater();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listViewItem = inflater.inflate(R.layout.schedule_list_layout, null);
        TextView busIdTv = (TextView) listViewItem.findViewById(R.id.busId);
        TextView busStopNameTv = (TextView) listViewItem.findViewById(R.id.busStopName);
        TextView arrivalTimeTv = (TextView) listViewItem.findViewById(R.id.arrivalTime);
        TextView departureTimeTv = (TextView) listViewItem.findViewById(R.id.departureTime);

        busIdTv.setText(busIds[position]);
        busStopNameTv.setText(busStopNames[position]);
        arrivalTimeTv.setText(arrivalTimes[position]);
        departureTimeTv.setText(departureTimes[position]);

        return listViewItem;
    }
}

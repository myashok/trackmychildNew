package com.example.kanchicoder.trackmychildparent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by FamilyAngel on 11/20/2016.
 */

public class ExpectedScheduleCustomAdapter extends ArrayAdapter<ExpectedSchedule>{

    private ArrayList<ExpectedSchedule> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView busIdTv;
        TextView busStopIdTv;
        TextView arrivalTimeTv;
        TextView departureTimeTv;
    }

    public ExpectedScheduleCustomAdapter(ArrayList<ExpectedSchedule> data, Context context) {
        super(context, R.layout.schedule_list_layout, data);
        this.dataSet = data;
        this.mContext=context;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ExpectedSchedule expectedSchedule = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.schedule_list_layout, parent, false);
            viewHolder.busIdTv = (TextView) convertView.findViewById(R.id.busId);
            viewHolder.busStopIdTv = (TextView) convertView.findViewById(R.id.busStopName);
            viewHolder.arrivalTimeTv = (TextView) convertView.findViewById(R.id.arrivalTime);
            viewHolder.departureTimeTv = (TextView) convertView.findViewById(R.id.departureTime);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.busIdTv.setText(expectedSchedule.getBusId());
        viewHolder.busStopIdTv.setText(expectedSchedule.getBusStopId());
        viewHolder.arrivalTimeTv.setText(expectedSchedule.getArrivalTime());
        viewHolder.departureTimeTv.setText(expectedSchedule.getDepartureTime());


        return convertView;
    }
}

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

public class PreviousLogCustomAdapter extends ArrayAdapter<PreviousLog> {

    private ArrayList<PreviousLog> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView busIdTv;
        TextView startBusStopIdTv;
        TextView endBusStopIdTv;
        TextView startTimeTv;
        TextView endTimeTv;
    }

    public PreviousLogCustomAdapter(ArrayList<PreviousLog> data, Context context) {
        super(context, R.layout.previous_list_layout, data);
        this.dataSet = data;
        this.mContext=context;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        PreviousLog previousLog = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        PreviousLogCustomAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new PreviousLogCustomAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.previous_list_layout, parent, false);
            viewHolder.busIdTv = (TextView) convertView.findViewById(R.id.busId);
            viewHolder.startBusStopIdTv = (TextView) convertView.findViewById(R.id.startBusStopId);
            viewHolder.endBusStopIdTv = (TextView) convertView.findViewById(R.id.endBusStopId);
            viewHolder.startTimeTv = (TextView) convertView.findViewById(R.id.startTime);
            viewHolder.endTimeTv = (TextView) convertView.findViewById(R.id.endTime);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (PreviousLogCustomAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;


        viewHolder.busIdTv.setText(previousLog.getBusId());
        viewHolder.startBusStopIdTv.setText(previousLog.getStartBusStopName());
        viewHolder.endBusStopIdTv.setText(previousLog.getEndBusStopName());
        viewHolder.startTimeTv.setText(previousLog.getStartTime());
        viewHolder.endTimeTv.setText(previousLog.getEndTime());


        return convertView;
    }
}
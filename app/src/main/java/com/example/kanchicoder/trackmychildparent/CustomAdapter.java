package com.example.kanchicoder.trackmychildparent;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by FamilyAngel on 11/8/2016.
 */

public class CustomAdapter extends ArrayAdapter<Student> implements View.OnClickListener{

    private ArrayList<Student> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView studentNameTv;
        TextView schoolNameTv;
        ImageView studentPhotoIv;
    }

    public CustomAdapter(ArrayList<Student> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Student student=(Student)object;

        switch (v.getId())
        {
            case R.id.student_name:
                Snackbar.make(v, "Name: " +student.getStudentName(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Student student = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.studentNameTv = (TextView) convertView.findViewById(R.id.student_name);
            viewHolder.studentPhotoIv = (ImageView) convertView.findViewById(R.id.student_photo);
            viewHolder.schoolNameTv = (TextView) convertView.findViewById(R.id.school_name);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.studentNameTv.setText(student.getStudentName());
        viewHolder.schoolNameTv.setText(student.getSchoolName());
        //Log.i("id", student.getStudentPhoto());
        Log.i("id", "https://trackmychild.000webhostapp.com/Images/studentImages/"+student.getStudentPhoto()+".jpg");
        Glide.with(mContext).load("https://trackmychild.000webhostapp.com/Images/studentImages/"+student.getStudentPhoto()+".jpg").placeholder(R.drawable.logo).into(viewHolder.studentPhotoIv);
        return convertView;
    }
}
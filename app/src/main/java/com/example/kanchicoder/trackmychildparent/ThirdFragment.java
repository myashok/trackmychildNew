package com.example.kanchicoder.trackmychildparent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by FamilyAngel on 11/8/2016.
 */

public class ThirdFragment extends Fragment {
    private ListView listView;



    private String[] busId = new String[100];
    private String[] startBusStopName = new String[100];
    private String[] endBusStopName = new String[100];
    private String[] startTime = new String[100];
    private String[] endTime = new String[100];
    private int size = 0;
    private static final String URL = "https://trackmychild.000webhostapp.com/trackmychild/previous_log.php";
    ArrayList<PreviousLog> dataModels;
    // private static String uid;
    //ListView listView;
    private static PreviousLogCustomAdapter adapter;
    MultipleFragmentsActivity activity;
    private String stuId;

    private static final String TAG = "MainActivity";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.third_frag, container, false);

        listView = (ListView) v.findViewById(R.id.previousLogListView);

        //sendRequest();
        activity=(MultipleFragmentsActivity)getActivity();
        stuId=activity.student.getStudentPhoto();


        //listView=(ListView)findViewById(R.id.list);
        dataModels = new ArrayList<>();
        adapter = new PreviousLogCustomAdapter(dataModels, getContext());
        //sendRequest();
        addDatafromInternet();
        listView.setAdapter(adapter);

        return v;
    }

    private void addDatafromInternet() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL+"?student="+stuId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d(TAG, "outcome = " + response);
                    JSONArray array = new JSONArray(response);
                    size = 0;
                    for (int i = 0; i < array.length(); ++i) {
                        JSONObject nsonobject = array.getJSONObject(i);
                        busId[i] = nsonobject.getString("bus_id");
                        startBusStopName[i] = nsonobject.getString("start_bus_stop_name");
                        endBusStopName[i] = nsonobject.getString("end_bus_stop_name");
                        startTime[i] = nsonobject.getString("start_time");
                        endTime[i] = nsonobject.getString("end_time");
                        size = size + 1;
                    }
                    dataModels.clear();
                    for (int i = 0; i < size; ++i) {
                        dataModels.add(new PreviousLog(busId[i], startBusStopName[i], endBusStopName[i], startTime[i], endTime[i]));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}
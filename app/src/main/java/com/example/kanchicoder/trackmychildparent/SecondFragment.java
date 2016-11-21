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

public class SecondFragment extends Fragment {
    //public static final String JSON_URL = "http://172.19.18.22/login_register/expected_time.php";

    private ListView listView;
    private String orgNo;


    private String[] busId = new String[100];
    private String[] busStopName = new String[100];
    private String[] arrivalTime = new String[100];
    private String[] departureTime = new String[100];
    MultipleFragmentsActivity activity;
    private int size = 0;
    private static final String URL = "https://trackmychild.000webhostapp.com/trackmychild/expected_time.php";
    ArrayList<ExpectedSchedule> dataModels;
    // private static String uid;
    //ListView listView;
    private static ExpectedScheduleCustomAdapter adapter;

    private static final String TAG = "MainActivity";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.second_frag, container, false);

        listView = (ListView) v.findViewById(R.id.scheduleListView);

        //sendRequest();
        activity=(MultipleFragmentsActivity) getActivity();
        orgNo=activity.student.getOrgNo();

        //listView=(ListView)findViewById(R.id.list);
        dataModels = new ArrayList<>();
        adapter = new ExpectedScheduleCustomAdapter(dataModels, getContext());
        //sendRequest();
        addDatafromInternet();
        listView.setAdapter(adapter);

        return v;
    }

    private void addDatafromInternet() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL+"?org="+orgNo, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d(TAG, "outcome = " + response);
                    JSONArray array = new JSONArray(response);
                    size = 0;
                    for (int i = 0; i < array.length(); ++i) {
                        JSONObject nsonobject = array.getJSONObject(i);
                        busId[i] = nsonobject.getString("bus_id");
                        busStopName[i] = nsonobject.getString("common_name");
                        arrivalTime[i] = nsonobject.getString("arrival_time");
                        departureTime[i] = nsonobject.getString("departure_time");
                        size = size + 1;
                    }
                    dataModels.clear();
                    for (int i = 0; i < size; ++i) {
                        dataModels.add(new ExpectedSchedule(busId[i], busStopName[i], arrivalTime[i], departureTime[i]));
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
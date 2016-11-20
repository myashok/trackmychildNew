package com.example.kanchicoder.trackmychildparent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by FamilyAngel on 11/8/2016.
 */

public class ThirdFragment extends Fragment {
    private RequestQueue requestQueue;
    private String [] name = new String[100];
    private String [] school_name = new String[100];
    private String [] photo_url = new String[100];
    private int size = 0;
    //  private static final String URL = "https://trackmychild.000webhostapp.com/trackmychild/parent_login.php";
    private static final String URL = "https://trackmychild.000webhostapp.com/trackmychild/fetch_parent_child_detail.php";
    private StringRequest request;
    ArrayList<Student> dataModels;
    // private static String uid;
    ListView listView;
    private static CustomAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);

        ListView listview =(ListView)view.findViewById(R.id.list);
        dataModels= new ArrayList<>();
        SessionManager sessionManager = new SessionManager(getContext());
        final String uid = sessionManager.getUserDetails();
        addDatafromInternet(uid);
        adapter= new CustomAdapter(dataModels, getContext());
        listview.setAdapter(adapter);
        return listview;
    }
    private void addDatafromInternet(final String uid) {
        requestQueue = Volley.newRequestQueue(getActivity());
        // String name[]
        request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonobject = new JSONObject(response);
                    size = 0;
                    for(int i = 0; i < jsonobject.length(); ++i) {
                        JSONObject nsonobject = jsonobject.getJSONObject(""+i);
                        name[i] = nsonobject.getString("name");
                        school_name[i] = nsonobject.getString("school_name");
                        photo_url[i] = nsonobject.getString("photo_url");
                        size = size+1;
                    }
                    dataModels.clear();
                    for (int i = 0; i < size; ++i) {
                        dataModels.add(new Student(name[i],school_name[i],photo_url[i]));
                    };
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("uid",uid);
                return hashMap;
            }
        };
        requestQueue.add(request);
    }
}
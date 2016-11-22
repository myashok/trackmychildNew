package com.example.kanchicoder.trackmychildparent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kanchicoder on 11/6/2016.
 */

public class MainActivity extends AppCompatActivity{
    private RequestQueue requestQueue;
    private String [] name = new String[100];
    private String [] school_name = new String[100];
    private String [] org_no = new String[100];
    private String [] photo_url = new String[100];
    private int size = 0;
    private static final String URL = "https://trackmychild.000webhostapp.com/trackmychild/parent_child_detail.php";
    private StringRequest request;
    ArrayList<Student> dataModels;
   // private static String uid;
    ListView listView;
    private static CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.list);
        dataModels= new ArrayList<>();
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        final String uid = sessionManager.getUserDetails();
        adapter = new CustomAdapter(dataModels,getApplicationContext());
        addDatafromInternet(uid);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Student student = (Student) adapter.getItem(position);
                Intent intent = new Intent(getApplicationContext(), MultipleFragmentsActivity.class);
                intent.putExtra("student", student);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        if(!sessionManager.isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    private void addDatafromInternet(final String uid) {
        requestQueue = Volley.newRequestQueue(this);
        // String name[]
        request = new StringRequest(Request.Method.POST, URL+"?parent="+uid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    size = 0;
                    for(int i = 0; i < array.length(); ++i) {
                        JSONObject nsonobject = array.getJSONObject(i);
                        name[i] = nsonobject.getString("name");
                        school_name[i] = nsonobject.getString("school_name");
                        org_no[i] = nsonobject.getString("organization_no");
                        photo_url[i] = nsonobject.getString("id");
                        size = size+1;
                    }
                    dataModels.clear();
                    for (int i = 0; i < size; ++i) {
                        dataModels.add(new Student(name[i],school_name[i],photo_url[i],org_no[i]));
                    }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            SessionManager sessionManager = new SessionManager(getApplicationContext());
            sessionManager.logoutUser();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

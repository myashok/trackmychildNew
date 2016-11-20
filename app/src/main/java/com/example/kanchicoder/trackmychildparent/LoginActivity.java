package com.example.kanchicoder.trackmychildparent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by kanchicoder on 11/6/2016.
 */

public class LoginActivity extends AppCompatActivity {
    private Button logIn;
    private RequestQueue requestQueue;
    private EditText password, uid;
    private static final String URL = "https://trackmychild.000webhostapp.com/trackmychild/parent_login.php";
    private StringRequest request;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.inject(this);
        initListeners();
    }

    private void initListeners() {
        logIn        = (Button) findViewById(R.id.login_button);
        requestQueue = Volley.newRequestQueue(this);
        password     = (EditText) findViewById(R.id.login_password);
        uid          = (EditText) findViewById(R.id.login_userid);
        logIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(uid.getText().toString().length() != 12  || uid.getText().toString().matches("[0-9]+") == false) {
                    Snackbar mySnackbar  = Snackbar.make(findViewById(R.id.loginCoordinatorLayout),
                            "Please enter valid 12 Digit User ID", Snackbar.LENGTH_SHORT);
                    mySnackbar.show();
                    return;
                }
                if(password.getText().toString().length() < 1) {
                    Snackbar mySnackbar  = Snackbar.make(findViewById(R.id.loginCoordinatorLayout),
                            "Password cannot be blank", Snackbar.LENGTH_SHORT);
                    mySnackbar.show();
                    return;
                }
            //    logIn.setEnabled(false);
                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Authenticating...");
                progressDialog.show();
                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.names().get(0).equals("success")){
                                progressDialog.dismiss();
                           //     logIn.setEnabled(true);
                                SessionManager sessionManager = new SessionManager(getApplicationContext());
                                sessionManager.createLoginSession(uid.getText().toString(),password.getText().toString());
                                finish();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }else {
                                progressDialog.dismiss();
                            //    logIn.setEnabled(true);
                                Snackbar mySnackbar  = Snackbar.make(findViewById(R.id.loginCoordinatorLayout),
                                        "Error" + jsonObject.getString("error"), Snackbar.LENGTH_SHORT);
                                mySnackbar.show();                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Log.i(uid.getText().toString(),password.getText().toString());
                        HashMap<String,String> hashMap = new HashMap<>();
                        hashMap.put("uid",uid.getText().toString());
                        hashMap.put("password",password.getText().toString());
                        return hashMap;
                    }
                };
                requestQueue.add(request);
            }

        });

    }
}

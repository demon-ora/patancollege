package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
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
import java.util.Arrays;

public class Course extends AppCompatActivity {
    GridView grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        grid=findViewById(R.id.customgrid);


// ...

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.bimash.com.np/patan/api/v1/get?token=cGF0YW5fYmNhZ3V5cw==&course=true";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray dataArray = jsonObject.getJSONArray("data");
                            String[] descriptions = new String[dataArray.length()];
                            String[] names = new String[dataArray.length()];
                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject dataObject = dataArray.getJSONObject(i);
                                String name = dataObject.getString("name");
                                Log.d("nameof","thisname"+name);
                                String description = dataObject.getString("description");
                                descriptions[i]=description;
                                names[i]=name;
                            }
                            MyGridAdapter adapter= new MyGridAdapter(Course.this,names,descriptions);
                            grid.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Course.this, "error"+error, Toast.LENGTH_SHORT).show();
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
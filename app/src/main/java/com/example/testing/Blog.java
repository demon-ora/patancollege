package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import java.util.List;

public class Blog extends AppCompatActivity {
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        list = findViewById(R.id.mylist);
        extractBlog();
    }

    private void extractBlog() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.bimash.com.np/patan/api/v1/get?token=cGF0YW5fYmNhZ3V5cw==&blog=true";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("mainresponse", "this is main" + response);
                        // Display the first 500 characters of the response string.
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray dataArray = jsonObject.getJSONArray("data");

                            String[] images = new String[dataArray.length()];
                            String[] descriptions = new String[dataArray.length()];
                            String[] codes = new String[dataArray.length()];

                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject dataObject = dataArray.getJSONObject(i);

                                String image = dataObject.getString("image");
                                String code = dataObject.getString("code");
                                String description = dataObject.getString("description");
                                images[i] = image;
                                descriptions[i] = description;
                                codes[i] = code;
                            }
                            MyAdapter adapter = new MyAdapter(Blog.this, codes, descriptions, images);
                            list.setAdapter(adapter);
                            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    switch (position){
                                        case 0:
                                            Intent intent = new Intent(Blog.this, Singlepage.class);
                                            intent.putExtra("code", codes[position]);
                                            startActivity(intent);
                                            break;
                                            case 1:
                                                Intent intent1 = new Intent(Blog.this, Singlepage.class);
                                                intent1.putExtra("code", codes[position]);
                                                startActivity(intent1);
                                                case 2:
                                                    Toast.makeText(Blog.this,"Third List Item is clicked",Toast.LENGTH_SHORT).show();
                                                    break;
                                        case 3:
                                            Toast.makeText(Blog.this,"Fourth List Item is clicked",Toast.LENGTH_SHORT).show();
                                            break;
                                            case 4:
                                                Toast.makeText(Blog.this,"Fifth List Item is clicked",Toast.LENGTH_SHORT).show();
                                                break;
                                    }
                                }

                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("apierror", "this is error " + error.toString());

            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}

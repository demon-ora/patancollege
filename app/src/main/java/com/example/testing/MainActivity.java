package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button contact,course,about,home,blog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView1 = (TextView) findViewById(R.id.text1);
        final TextView textView3 = (TextView) findViewById(R.id.text3);
        final TextView textView4 = (TextView) findViewById(R.id.text4);
        final TextView textView5 = (TextView) findViewById(R.id.text5);
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        final ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        contact=findViewById(R.id.button);
        course=findViewById(R.id.button2);
        about=findViewById(R.id.button4);
        home=findViewById(R.id.button5);
        blog=findViewById(R.id.button6);
// ...

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.bimash.com.np/patan/api/v1/get?token=cGF0YW5fYmNhZ3V5cw==&course=true&limit=2&offset=0";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray dataArray = jsonObject.getJSONArray("data");

                            // Extract data for first text view
                            JSONObject dataObject1 = dataArray.getJSONObject(0);
                            String id1 = dataObject1.getString("name");
                            String title1 = dataObject1.getString("description");
                            String dataString1 = "ID: " + id1 + "\n" +
                                    "Title: " + title1 + "\n";
                            textView1.setText(dataString1);

                            // Extract data for second text view
                            JSONObject dataObject2 = dataArray.getJSONObject(1);
                            String id2 = dataObject2.getString("name");
                            String title2 = dataObject2.getString("description");
                            String dataString2 = "ID: " + id2 + "\n" +
                                    "Title: " + title2 + "\n";
                            textView3.setText(dataString2);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView1.setText("That didn't work!");
            }
        });

        queue.add(stringRequest);


        RequestQueue queue1 = Volley.newRequestQueue(this);
        String url1 = "https://api.bimash.com.np/patan/api/v1/get?token=cGF0YW5fYmNhZ3V5cw==&blog=true&limit=2&offset=0";

// Request a string response from the provided URL.
        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response1) {
                        try {
                            JSONObject jsonObject1 = new JSONObject(response1);
                            JSONArray dataArray1 = jsonObject1.getJSONArray("data");

                            // Extract data for first text view
                            JSONObject dataObject1 = dataArray1.getJSONObject(0);
                            String image = dataObject1.getString("image");
                            String title1 = dataObject1.getString("description");
                            String code = dataObject1.getString("code");
                            String dataString1 = "ID: " + code + "\n" +
                                    "Title: " + title1 + "\n";
                            Picasso.get().load(image).into(imageView);
                            textView4.setText(dataString1);

                            // Extract data for second text view
                            JSONObject dataObject2 = dataArray1.getJSONObject(1);
                            String image1 = dataObject2.getString("image");
                            String title2 = dataObject2.getString("description");
                            Log.d("titleof","this is" +title2);
                            String code1 = dataObject2.getString("code");
                            String dataString2 = "ID: " + code1 + "\n" +
                                    "Title: " + title2 + "\n";
                            Picasso.get().load(image1).into(imageView2);
                            textView5.setText(dataString2);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView4.setText("That didn't work!");
            }
        });

        queue1.add(stringRequest1);

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, About.class);
                startActivity(intent);
            }
        });

        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Course.class);
                startActivity(intent);
            }
        });

        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Blog.class);
                startActivity(intent);
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Contact.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
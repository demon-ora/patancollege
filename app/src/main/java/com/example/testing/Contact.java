package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.json.JSONException;


public class Contact extends AppCompatActivity {
    EditText name, contact, address, email, message;
    Button b3,b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        name = findViewById(R.id.emailed1);
        contact = findViewById(R.id.contacted1);
        address = findViewById(R.id.addressed);
        email = findViewById(R.id.emailed);
        message = findViewById(R.id.messageed);
        b3 = findViewById(R.id.button3);
        b4=findViewById(R.id.inquire);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameval = name.getText().toString();
                String contactval = contact.getText().toString();
                String addressval = address.getText().toString();
                String emailval = email.getText().toString();
                String messageval = message.getText().toString();
                Log.d("namevalue", "namve" + nameval);
                Toast.makeText(Contact.this, "message" + nameval + contactval + addressval + emailval + messageval, Toast.LENGTH_SHORT).show();

                try {
                    String url = "https://api.bimash.com.np/patan/api/v1/post?token=cGF0YW5fYmNhZ3V5cw==&contact_us"
                            + "&name=" + URLEncoder.encode(nameval, "UTF-8")
                            + "&contact=" + URLEncoder.encode(contactval, "UTF-8")
                            + "&email=" + URLEncoder.encode(emailval, "UTF-8")
                            + "&address=" + URLEncoder.encode(addressval, "UTF-8")
                            + "&message=" + URLEncoder.encode(messageval, "UTF-8");

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        String message = jsonObject.getJSONObject("data").getString("message");
                                        Toast.makeText(Contact.this, message, Toast.LENGTH_SHORT).show();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Contact.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    RequestQueue queue = Volley.newRequestQueue(Contact.this);
                    queue.add(stringRequest);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Contact.this,Inquire.class);
                startActivity(intent);
            }
        });
    }
}

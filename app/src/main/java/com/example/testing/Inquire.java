package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Inquire extends AppCompatActivity {
    EditText  contact, email, message;
    Button inqu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquire);
        email = findViewById(R.id.emailed1);
        contact = findViewById(R.id.contacted1);
        message = findViewById(R.id.messageed);
        inqu = findViewById(R.id.inquired);

        inqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contactval = contact.getText().toString();
                String emailval = email.getText().toString();
                String messageval = message.getText().toString();
                String inputValue = "bca";
                Toast.makeText(Inquire.this, "message"  + contactval +  emailval + messageval, Toast.LENGTH_SHORT).show();

                try {
                    String url = "https://api.bimash.com.np/patan/api/v1/post?inquiry&token=cGF0YW5fYmNhZ3V5cw=="
                            + "&contact_no=" + URLEncoder.encode(contactval, "UTF-8")
                            + "&email=" + URLEncoder.encode(emailval, "UTF-8")
                            + "&code=" + URLEncoder.encode(inputValue, "UTF-8")
                            + "&message=" + URLEncoder.encode(messageval, "UTF-8");

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        String message = jsonObject.getJSONObject("data").getString("message");
                                        Toast.makeText(Inquire.this, message, Toast.LENGTH_SHORT).show();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Inquire.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    RequestQueue queue = Volley.newRequestQueue(Inquire.this);
                    queue.add(stringRequest);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
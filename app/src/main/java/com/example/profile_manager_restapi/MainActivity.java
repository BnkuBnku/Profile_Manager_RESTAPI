package com.example.profile_manager_restapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    Button addProfile, refreshButton;
    RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addProfile = findViewById(R.id.addButton);
        recyclerview = findViewById(R.id.recyclerView);
        refreshButton = findViewById(R.id.refreshButo);

        Display();

        addProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("TypeModify", addProfile.getText().toString());
                startActivity(intent);
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Display();
                Toast.makeText(MainActivity.this, "Refreshed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Display(){
        String url = "http://192.168.56.1/db_exer12/DisplayProfile.php";

        RequestQueue q = Volley.newRequestQueue(MainActivity.this);

        StringRequest r = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject oh = new JSONObject(response);

                            JSONArray profilearray = oh.getJSONArray("Profile");

                            int size = profilearray.length();

                            String[] A1 = new String[size];
                            String[] A2 = new String[size];
                            String[] A3 = new String[size];
                            String[] A4 = new String[size];


                            for(int i=0; i<size; i++) {

                                JSONObject ob = profilearray.getJSONObject(i);

                                A1[i] = ob.getString("id");
                                A2[i] = ob.getString("name");
                                A3[i] = ob.getString("age");
                                A4[i] = ob.getString("gender");


                                LinearLayoutManager layout = new LinearLayoutManager(MainActivity.this);
                                recyclerview.setLayoutManager(layout);
                                recyclerview.setAdapter(new MainAdapter(MainActivity.this,A1,A2,A3,A4));
                            }
                        }
                        catch(Exception e){

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Volley Error Response! \n\n" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        q.add(r);
    }

}
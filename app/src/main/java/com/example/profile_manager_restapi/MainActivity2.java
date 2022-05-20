package com.example.profile_manager_restapi;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity2 extends AppCompatActivity {

    TextView modify;
    EditText editname, editage,editgender;
    Button backbtn, savebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editname = findViewById(R.id.EditName);
        editage = findViewById(R.id.EditAge);
        editgender = findViewById(R.id.EditGender);
        backbtn = findViewById(R.id.ButtonBack);
        savebtn = findViewById(R.id.ButtonSave);
        modify = findViewById(R.id.TypeModify);


        //Get intent value
        //Intent from MainActivity
        Intent get = getIntent();

        //Intent from Adapter
        //Set String
        modify.setText(get.getStringExtra("TypeModify"));

        //Back Button
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //Save Button
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertProfile(
                        editname.getText().toString(),
                        editage.getText().toString(),
                        editgender.getText().toString());
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
                makeText(MainActivity2.this, "Added Profile Successfully", LENGTH_SHORT).show();
            }
        });
    }

    private void InsertProfile(String name, String age, String gender){
        // post our data.
        String url = "http://192.168.56.1/db_exer12/InsertProfile.php";

        // creating a new variable for our request queue.
        RequestQueue q = Volley.newRequestQueue(MainActivity2.this);

        StringRequest r = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        makeText(getBaseContext(), "Failed to Search. \n\n" + error.getMessage(), LENGTH_SHORT).show();
                    }
                }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("name", name);
                param.put("age", age);
                param.put("gender", gender);
                return param;
            }
        };
        q.add(r);
    }
}
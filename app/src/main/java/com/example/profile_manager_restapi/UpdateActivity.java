package com.example.profile_manager_restapi;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.HashMap;
import java.util.Map;

public class UpdateActivity extends AppCompatActivity {

    TextView textviewid;
    EditText editname, editage,editgender;
    Button backbtn, savebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        editname = findViewById(R.id.EditName);
        editage = findViewById(R.id.EditAge);
        editgender = findViewById(R.id.EditGender);
        backbtn = findViewById(R.id.ButtonBack);
        savebtn = findViewById(R.id.ButtonSave);
        textviewid = findViewById(R.id.TextViewID);

        Intent intent = getIntent();
        textviewid.setText(intent.getStringExtra("id"));
        editname.setText(intent.getStringExtra("name"));
        editage.setText(intent.getStringExtra("age"));
        editgender.setText(intent.getStringExtra("gender"));

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateProfile(
                        textviewid.getText().toString(),
                        editname.getText().toString(),
                        editage.getText().toString(),
                        editgender.getText().toString());
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(UpdateActivity.this, "Updated Successfully", LENGTH_SHORT).show();
            }
        });

    }

    private void UpdateProfile(String id, String name, String age, String gender){
        // post our data.
        String url = "http://192.168.56.1/db_exer12/EditProfile.php";

        // creating a new variable for our request queue.
        RequestQueue q = Volley.newRequestQueue(UpdateActivity.this);

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
                param.put("id", id);
                param.put("name", name);
                param.put("age", age);
                param.put("gender", gender);
                return param;
            }
        };
        q.add(r);
    }
}
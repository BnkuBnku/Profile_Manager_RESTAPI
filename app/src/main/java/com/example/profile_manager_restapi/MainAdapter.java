package com.example.profile_manager_restapi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    Context context;
    String[] arrayid;
    String[] arrayname;
    String[] arrayage;
    String[] arraygender;

    public MainAdapter(Context context, String[] arrayid, String[] arrayname, String[] arrayage, String[] arraygender) {
        this.context = context;
        this.arrayid = arrayid;
        this.arrayname = arrayname;
        this.arrayage = arrayage;
        this.arraygender = arraygender;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView profileid, profilename, profileage,profilegender;
        Button editbtn, deletebtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileid = itemView.findViewById(R.id.RowOne_2);
            profilename = itemView.findViewById(R.id.RowTwo_2);
            profileage = itemView.findViewById(R.id.RowThree_2);
            profilegender = itemView.findViewById(R.id.RowFour_2);
            editbtn = itemView.findViewById(R.id.ButtonEdit);
            deletebtn = itemView.findViewById(R.id.ButtonDelete);

            editbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UpdateActivity.class);
                    intent.putExtra("id", profileid.getText().toString());
                    intent.putExtra("name", profilename.getText().toString());
                    intent.putExtra("age", profileage.getText().toString());
                    intent.putExtra("gender", profilegender.getText().toString());
                    context.startActivity(intent);
                }
            });

            deletebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = "http://192.168.56.1/db_exer12/DeleteProfile.php";

                    RequestQueue q = Volley.newRequestQueue(context);

                    StringRequest r = new StringRequest(
                            Request.Method.POST,
                            url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context, "Volley Error Response! \n\n" + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> param = new HashMap<>();
                            param.put("id", profileid.getText().toString());
                            return param;
                        }
                    };
                    q.add(r);

                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.profile_rowlayout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        holder.profileid.setText(arrayid[position]);
        holder.profilename.setText(arrayname[position]);
        holder.profileage.setText(arrayage[position]);
        holder.profilegender.setText(arraygender[position]);
    }

    @Override
    public int getItemCount() {
        return arrayid.length;
    }


}

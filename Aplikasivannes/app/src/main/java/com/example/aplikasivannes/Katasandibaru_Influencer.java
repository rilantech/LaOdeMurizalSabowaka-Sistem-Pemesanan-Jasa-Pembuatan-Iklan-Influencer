package com.example.aplikasivannes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Katasandibaru_Influencer extends AppCompatActivity {
    TextView tvLogin;
    EditText etPassword;
    Toolbar toolbar;
    Button btnReset;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katasandibaru_influencer);
        tvLogin = findViewById(R.id.tvLogin);
        toolbar = findViewById(R.id.toolbar);
        etPassword = findViewById(R.id.etPassword);
        btnReset = findViewById(R.id.btnReset);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPassword.getText().toString().length() == 0) {
                    etPassword.setError("Tidak Boleh Kosong");
                }

                else {
                    String url = new Configurasi().baseUrl()+"influencer/api/katasandibaru.php";
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String status =jsonObject.getString("status");
                                if(status.equals("berhasil_tersimpan"))
                                {
                                    // Toast.makeText(HalamanRegistrasi.this, "Berhasil Registrasi", Toast.LENGTH_SHORT).show();
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Katasandibaru_Influencer.this);
                                    builder.setTitle("Sukses");
                                    builder.setMessage("Password Anda Berhasil DiReset!");
                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Intent intent = new Intent(Katasandibaru_Influencer.this, Login_Influencer.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });

                                    AlertDialog alertDialog = builder.create();
                                    alertDialog.show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Katasandibaru_Influencer.this, "Anda Sedang Offline!", Toast.LENGTH_LONG).show();
                        }
                    }){

                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> myParams = new HashMap<>();
                            myParams.put("password", etPassword.getText().toString());
                            myParams.put("email", Lupakatasandi_Influencer.email);
                            return myParams;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(Katasandibaru_Influencer.this);
                    requestQueue.add(stringRequest);
                }

            }

        });





        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login_Influencer.class));
                finish();
            }
        });

        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Lupakatasandi_Influencer.class));
                finish();
            }
        });
    }
}
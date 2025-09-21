package com.example.aplikasivannes;

import static com.example.aplikasivannes.Login_Brand.preferenceHelper;

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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Lupakatasandi extends AppCompatActivity {
    TextView tvLogin;
    EditText etEmail;
    Toolbar toolbar;
    Button btnLanjut;
    public static String email;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupakatasandi);
        tvLogin = findViewById(R.id.tvLogin);
        toolbar = findViewById(R.id.toolbar);
        etEmail = findViewById(R.id.etEmail);
        btnLanjut = findViewById(R.id.btnLanjut);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(etEmail.getText().toString().length() ==0){
                    etEmail.setError("Tidak Boleh Kosong");
                    etEmail.requestFocus();
                }
                else{
                    String url = new Configurasi().baseUrl()+"brand/api/lupakatasandi.php";

                    StringRequest stringRequest = new StringRequest(
                            1, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        String status =jsonObject.getString("status");
                                        if(status.equals("berhasil"))
                                        {
                                            saveInfo(jsonObject);
                                                    Intent intent = new Intent(Lupakatasandi.this, Katasandibaru_Brand.class);
                                                    startActivity(intent);
                                                    finish();
                                        }
                                        if(status.equals(("gagal"))){
                                            AlertDialog.Builder builder = new AlertDialog.Builder(Lupakatasandi.this);
                                            builder.setTitle("Peringatan");
                                            builder.setMessage("Email Tidak Terdaftar!");
                                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    onRestart();
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
                            Toast.makeText(Lupakatasandi.this, "Anda Sedang Offline!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    ){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> form= new HashMap<String, String>();
                            form.put("email", etEmail.getText().toString());
                            return form;
                        }
                    };
                    RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);

                }
            }

            private void saveInfo(JSONObject response) {
//                Toast.makeText(Login_Brand.this, response.toString(), Toast.LENGTH_SHORT).show();
                preferenceHelper.putIsLogin(true);
                try {

                    email = response.getString("email");


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });



        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login_Brand.class));
                finish();
            }
        });

        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login_Brand.class));
                finish();
            }
        });
    }
}
package com.example.aplikasivannes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
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


public class Login_Brand extends AppCompatActivity {
    TextView tvRegister, tvLupakatasandi;
    EditText etUsername, etPassword;
    Button btnLogin;
    boolean passwordVisible;
    public static String id, nama_brand, username, password, foto_profil, email, nomor_telepon;
    public static PreferenceHelper preferenceHelper;
    public static SharedPreferences getData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_brand);
        getData = getSharedPreferences("data", MODE_PRIVATE);
        preferenceHelper = new PreferenceHelper(this);
        tvRegister = findViewById(R.id.tvRegister);
        tvLupakatasandi = findViewById(R.id.tvLupakatasandi);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Registrasi_Brand.class));
                finish();
            }
        });

        tvLupakatasandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Lupakatasandi.class));
                finish();
            }
        });


        this.etPassword.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() != 1 || event.getRawX() < ((float) (etPassword.getRight() - etPassword.getCompoundDrawables()[2].getBounds().width()))) {
                    return false;
                }
                int selection = etPassword.getSelectionEnd();
                if (passwordVisible) {
                    etPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.showpassword, 0);
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordVisible = false;
                } else {
                    etPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.hidepassword, 0);
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passwordVisible = true;
                }
                etPassword.setSelection(selection);
                return true;
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(etUsername.getText().toString().length() ==0){
                    etUsername.setError("Tidak Boleh Kosong");
                    etUsername.requestFocus();
                }
                if(etPassword.getText().toString().length() ==0){
                    etPassword.setError("Tidak Boleh Kosong");
                    etPassword.requestFocus();
                }
                else{
                    String url = new Configurasi().baseUrl()+"brand/api/login.php";

                    StringRequest stringRequest = new StringRequest(
                            1, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        String status =jsonObject.getString("status");
                                        if(status.equals("berhasil_login"))
                                        {
                                            saveInfo(jsonObject);
                                            AlertDialog.Builder builder = new AlertDialog.Builder(Login_Brand.this);
                                            builder.setTitle("Sukses");
                                            builder.setMessage("Berhasil Login");
                                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    Intent intent = new Intent(Login_Brand.this, Halaman_Utama_Brand.class);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            });

                                            AlertDialog alertDialog = builder.create();
                                            alertDialog.show();
                                        }
                                        if(status.equals(("gagal_login"))){
                                            AlertDialog.Builder builder = new AlertDialog.Builder(Login_Brand.this);
                                            builder.setTitle("Peringatan");
                                            builder.setMessage("Username / Password Salah!");
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
                            Toast.makeText(Login_Brand.this, "Anda Sedang Offline!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    ){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> form= new HashMap<String, String>();
                            form.put("username", etUsername.getText().toString());
                            form.put("password", etPassword.getText().toString());
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
                    id = response.getString("id");
                    nama_brand = response.getString("nama_brand");
                    username = response.getString("username");
                    password = response.getString("password");
                    foto_profil = response.getString("foto_profil");
                    email = response.getString("email");
                    nomor_telepon = response.getString("nomor_telepon");

                    preferenceHelper.putNama(nama_brand);
                    preferenceHelper.putUsername(username);
                    preferenceHelper.putPassword(password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), Halaman_Awal.class));
        finish();
    }
}
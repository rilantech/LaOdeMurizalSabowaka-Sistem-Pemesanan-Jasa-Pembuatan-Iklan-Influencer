package com.example.aplikasivannes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Registrasi_Influencer extends AppCompatActivity {
    TextView tvLogin;
    Toolbar toolbar;
    EditText etNama_Influencer, etUsername, etPassword, etEmail, etNomor_Telepon, etSosmed_Tiktok, etPengikut_Sosmed_Tiktok, etSosmed_Instagram, etPengikut_Sosmed_Instagram, etSosmed_Facebook, etPengikut_Sosmed_Facebook ;
    ImageView imageView;
    Button buttonPilihGambar, btnRegister;
    Bitmap bitmap;
    String encodeImage;
    int MY_REQUEST_CODE= 1;
    boolean passwordVisible;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi_influencer);
        tvLogin = findViewById(R.id.tvLogin);
        toolbar = findViewById(R.id.toolbar);
        etNama_Influencer = findViewById(R.id.etNama_Influencer);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);
        etNomor_Telepon = findViewById(R.id.etNomor_Telepon);
        etSosmed_Tiktok = findViewById(R.id.etSosmed_Tiktok);
        etPengikut_Sosmed_Tiktok= findViewById(R.id.etPengikut_Sosmed_Tiktok);
        etSosmed_Instagram = findViewById(R.id.etSosmed_Instagram);
        etPengikut_Sosmed_Instagram= findViewById(R.id.etPengikut_Sosmed_Instagram);
        etSosmed_Facebook = findViewById(R.id.etSosmed_Facebook);
        etPengikut_Sosmed_Facebook= findViewById(R.id.etPengikut_Sosmed_Facebook);
        imageView = findViewById(R.id.imageView);
        buttonPilihGambar = findViewById(R.id.buttonPilihGambar);
        btnRegister = findViewById(R.id.btnRegister);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //pilih gambar
        buttonPilihGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity(Registrasi_Influencer.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                Intent intent= new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "Select Image"), MY_REQUEST_CODE);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etNama_Influencer.getText().toString().length() == 0) {
                    etNama_Influencer.setError("Tidak Boleh Kosong");
                }
                if (etUsername.getText().toString().length() == 0) {
                    etUsername.setError("Tidak Boleh Kosong");
                }
                if (etPassword.getText().toString().length() == 0) {
                    etPassword.setError("Tidak Boleh Kosong");
                }
                if (etEmail.getText().toString().length() == 0) {
                    etEmail.setError("Tidak Boleh Kosong");
                }
                if (etNomor_Telepon.getText().toString().length() == 0) {
                    etNomor_Telepon.setError("Tidak Boleh Kosong");
                }
                if (etSosmed_Tiktok.getText().toString().length() == 0) {
                    etSosmed_Tiktok.setError("Tidak Boleh Kosong");
                }
                if (etPengikut_Sosmed_Tiktok.getText().toString().length() == 0) {
                    etPengikut_Sosmed_Tiktok.setError("Tidak Boleh Kosong");
                }
                if (etSosmed_Instagram.getText().toString().length() == 0) {
                    etSosmed_Instagram.setError("Tidak Boleh Kosong");
                }
                if (etPengikut_Sosmed_Instagram.getText().toString().length() == 0) {
                    etPengikut_Sosmed_Instagram.setError("Tidak Boleh Kosong");
                }
                if (etSosmed_Facebook.getText().toString().length() == 0) {
                    etSosmed_Facebook.setError("Tidak Boleh Kosong");
                }
                if (etPengikut_Sosmed_Facebook.getText().toString().length() == 0) {
                    etPengikut_Sosmed_Facebook.setError("Tidak Boleh Kosong");
                }
                else {
                    String url = new Configurasi().baseUrl()+"influencer/api/registrasi.php";
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String status =jsonObject.getString("status");
                                if(status.equals("berhasil_registrasi"))
                                {
                                    // Toast.makeText(HalamanRegistrasi.this, "Berhasil Registrasi", Toast.LENGTH_SHORT).show();
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Registrasi_Influencer.this);
                                    builder.setTitle("Sukses");
                                    builder.setMessage("Berhasil Registrasi");
                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Intent intent = new Intent(Registrasi_Influencer.this, Login_Influencer.class);
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
                            Toast.makeText(Registrasi_Influencer.this, "Anda Sedang Offline!", Toast.LENGTH_LONG).show();
                        }
                    }){

                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> myParams = new HashMap<>();
                            myParams.put("nama_influencer", etNama_Influencer.getText().toString());
                            myParams.put("username", etUsername.getText().toString());
                            myParams.put("password", etPassword.getText().toString());
                            myParams.put("foto_profil", encodeImage);
                            myParams.put("email", etEmail.getText().toString());
                            myParams.put("nomor_telepon", etNomor_Telepon.getText().toString());
                            myParams.put("sosmed_tiktok", etSosmed_Tiktok.getText().toString());
                            myParams.put("pengikut_sosmed_tiktok", etPengikut_Sosmed_Tiktok.getText().toString());
                            myParams.put("sosmed_instagram", etSosmed_Instagram.getText().toString());
                            myParams.put("pengikut_sosmed_instagram", etPengikut_Sosmed_Instagram.getText().toString());
                            myParams.put("sosmed_facebook", etSosmed_Facebook.getText().toString());
                            myParams.put("pengikut_sosmed_facebook", etPengikut_Sosmed_Facebook.getText().toString());
                            return myParams;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(Registrasi_Influencer.this);
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
                startActivity(new Intent(getApplicationContext(), Login_Brand.class));
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == MY_REQUEST_CODE && resultCode == RESULT_OK && data!=null){
            Uri filePath = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
                imageStore(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void imageStore(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] imageByte = stream.toByteArray();
        encodeImage = Base64.encodeToString(imageByte, Base64.DEFAULT);
    }

}
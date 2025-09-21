package com.example.aplikasivannes;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.aplikasivannes.AdapterProjectBrand.AdaptorProjectBrand;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Fragment_Edit_Profil_Brand extends Fragment {
    ImageView tombol_kembali, ivFoto_ProfilLama, ivFoto_ProfilBaru;
    EditText etNama_Brand, etUsername, etPassword, etEmail, etNomor_Telepon;
    Button btnbatal, btnsimpan;
    Bitmap bitmap;
    String encodeImage="";
    int MY_REQUEST_CODE= 1;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profil_brand, container, false);
       tombol_kembali = view.findViewById(R.id.tombol_kembali);
       ivFoto_ProfilLama = view.findViewById(R.id.ivFoto_ProfilLama);
       ivFoto_ProfilBaru = view.findViewById(R.id.ivFoto_ProfilBaru);
       etNama_Brand = view.findViewById(R.id.etNama_Brand);
       etUsername = view.findViewById(R.id.etUsername);
       etPassword = view.findViewById(R.id.etPassword);
       etEmail = view.findViewById(R.id.etEmail);
       etNomor_Telepon = view.findViewById(R.id.etNomor_Telepon);
       btnbatal = view.findViewById(R.id.btnbatal);
       btnsimpan = view.findViewById(R.id.btnsimpan);

        String urlgambar = new Configurasi().baseUrl()+"brand/gambar/"+ Login_Brand.foto_profil;

        Glide.with(getContext())
                .load(urlgambar)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(ivFoto_ProfilLama);
        etNama_Brand.setText(Login_Brand.nama_brand);
        etUsername.setText(Login_Brand.username);
        etPassword.setText(Login_Brand.password);
        etEmail.setText(Login_Brand.email);
        etNomor_Telepon.setText(Login_Brand.nomor_telepon);
        tombol_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.konten, new Fragment_Profil_Brand());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        ivFoto_ProfilBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity((Activity) getContext())
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

        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login_Brand.username = etUsername.getText().toString();
                Login_Brand.password = etPassword.getText().toString();

                    String url = new Configurasi().baseUrl()+"brand/api/edit_profil.php";
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String status =jsonObject.getString("status");
                                if(status.equals("berhasil_tersimpan"))
                                {
                                    // Toast.makeText(HalamanRegistrasi.this, "Berhasil Registrasi", Toast.LENGTH_SHORT).show();
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                    builder.setTitle("Sukses");
                                    builder.setMessage("Profil Terupdate!");
                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                            transaction.replace(R.id.konten, new Fragment_Profil_Brand());
                                            transaction.addToBackStack(null);
                                            transaction.commit();
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
                            Toast.makeText(getContext(), "Anda Sedang Offline!", Toast.LENGTH_LONG).show();
                        }
                    }){

                        @androidx.annotation.Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> myParams = new HashMap<>();
                            myParams.put("id", Login_Brand.id);
                            myParams.put("foto_profil", encodeImage);
                            myParams.put("nama_brand", etNama_Brand.getText().toString());
                            myParams.put("username", etUsername.getText().toString());
                            myParams.put("password", etPassword.getText().toString());
                            myParams.put("email", etEmail.getText().toString());
                            myParams.put("nomor_telepon", etNomor_Telepon.getText().toString());
                            return myParams;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                    requestQueue.add(stringRequest);
                }


        });

        btnbatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.konten, new Fragment_Profil_Brand());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        if(requestCode == MY_REQUEST_CODE && resultCode == RESULT_OK && data!=null){
            Uri filePath = data.getData();

            try {
                InputStream inputStream = requireContext().getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                ivFoto_ProfilBaru.setImageBitmap(bitmap);
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


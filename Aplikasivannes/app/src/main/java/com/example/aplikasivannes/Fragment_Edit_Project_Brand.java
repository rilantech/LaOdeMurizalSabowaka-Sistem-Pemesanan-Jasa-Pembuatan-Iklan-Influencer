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

public class Fragment_Edit_Project_Brand extends Fragment {
    ImageView tombol_kembali, ivLogo_BrandLama, ivLogo_BrandBaru;
    EditText etNama_Project, etKriteria_Project, etGaji_Influencer;
    Spinner spinner_Kategori, spinner_lama_kontrak;
    Button btnbatal, btnsimpan;
    Bitmap bitmap;
    String encodeImage="";
    int MY_REQUEST_CODE= 1;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_project_brand, container, false);
       tombol_kembali = view.findViewById(R.id.tombol_kembali);
       ivLogo_BrandLama = view.findViewById(R.id.ivLogo_BrandLama);
       ivLogo_BrandBaru = view.findViewById(R.id.ivLogo_BrandBaru);
       etNama_Project = view.findViewById(R.id.etNama_Project);
       etKriteria_Project = view.findViewById(R.id.etKriteria_Project);
       etGaji_Influencer = view.findViewById(R.id.etGaji_Influencer);
       spinner_Kategori = view.findViewById(R.id.spinner_Kategori);
       spinner_lama_kontrak = view.findViewById(R.id.spinner_lama_kontrak);
       btnbatal = view.findViewById(R.id.btnbatal);
       btnsimpan = view.findViewById(R.id.btnsimpan);

       if(AdaptorProjectBrand.id_project != ""){
           tampil_project();
       }
        tombol_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.konten, new Fragment_Project_Brand());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        ivLogo_BrandBaru.setOnClickListener(new View.OnClickListener() {
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
                if (etNama_Project.getText().toString().length() == 0) {
                    etNama_Project.setError("Tidak Boleh Kosong");
                }
                if (etKriteria_Project.getText().toString().length() == 0) {
                    etKriteria_Project.setError("Tidak Boleh Kosong");
                }
                if (etGaji_Influencer.getText().toString().length() == 0) {
                    etGaji_Influencer.setError("Tidak Boleh Kosong");
                }

                else {
                    String url = new Configurasi().baseUrl()+"brand/api/edit_project.php";
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
                                    builder.setMessage("Project Terupdate!");
                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                            transaction.replace(R.id.konten, new Fragment_Project_Brand());
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
                            myParams.put("id", AdaptorProjectBrand.id_project);
                            myParams.put("logo_brand", encodeImage);
                            myParams.put("nama_project", etNama_Project.getText().toString());
                            myParams.put("kategori", spinner_Kategori.getSelectedItem().toString());
                            myParams.put("lama_kontrak", spinner_lama_kontrak.getSelectedItem().toString());
                            myParams.put("kriteria_project", etKriteria_Project.getText().toString());
                            myParams.put("gaji_influencer", etGaji_Influencer.getText().toString());
                            return myParams;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                    requestQueue.add(stringRequest);
                }

            }

        });

        btnbatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivLogo_BrandBaru.setImageResource(R.drawable.ic_baseline_file_upload_24);
                etNama_Project.setText("");
                spinner_Kategori.setSelection(0);
                spinner_lama_kontrak.setSelection(0);
                etKriteria_Project.setText("");
                etGaji_Influencer.setText("");
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
                ivLogo_BrandBaru.setImageBitmap(bitmap);
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

    void tampil_project(){
        String url = new Configurasi().baseUrl()+"brand/api/tampil_project.php";

        StringRequest stringRequest = new StringRequest(
                1, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response).getJSONObject("data");
                            String gwaktu_posting = jsonObject.getString("waktu_posting");
                            String glogo_brand = jsonObject.getString("logo_brand");
                            String gnama_brand =jsonObject.getString("nama_brand");
                            String gnama_project =jsonObject.getString("nama_project");
                            String gkategori =jsonObject.getString("kategori");
                            String glama_kontrak =jsonObject.getString("lama_kontrak");
                            String gkriteria_project =jsonObject.getString("kriteria_project");
                            String ggaji_influencer =jsonObject.getString("gaji_influencer");

                            String urlgambar = new Configurasi().baseUrl()+"brand/gambar/"+ glogo_brand;

                            Glide.with(getContext())
                                    .load(urlgambar)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true)
                                    .into(ivLogo_BrandLama);

                            etNama_Project.setText(gnama_project);
                            if(gkategori.equals("Fashion & Accessories")) {
                                spinner_Kategori.setSelection(0);
                            }
                            if(gkategori.equals("Food & Drink")) {
                                spinner_Kategori.setSelection(1);
                            }
                            if(gkategori.equals("Electronic")) {
                                spinner_Kategori.setSelection(2);
                            }
                            if(gkategori.equals("Beauty")) {
                                spinner_Kategori.setSelection(3);
                            }
                            if(gkategori.equals("Tech")) {
                                spinner_Kategori.setSelection(4);
                            }

                            if(glama_kontrak.equals("1 Minggu")) {
                                spinner_lama_kontrak.setSelection(0);
                            }
                            if(glama_kontrak.equals("2 Minggu")) {
                                spinner_lama_kontrak.setSelection(1);
                            }
                            if(glama_kontrak.equals("3 Minggu")) {
                                spinner_lama_kontrak.setSelection(2);
                            }
                            if(glama_kontrak.equals("1 Bulan")) {
                                spinner_lama_kontrak.setSelection(3);
                            }
                            if(glama_kontrak.equals("2 Bulan")) {
                                spinner_lama_kontrak.setSelection(4);
                            }
                            if(glama_kontrak.equals("3 Bulan")) {
                                spinner_lama_kontrak.setSelection(5);
                            }
                            if(glama_kontrak.equals("4 Bulan")) {
                                spinner_lama_kontrak.setSelection(6);
                            }
                            if(glama_kontrak.equals("5 Bulan")) {
                                spinner_lama_kontrak.setSelection(7);
                            }
                            if(glama_kontrak.equals("6 Bulan")) {
                                spinner_lama_kontrak.setSelection(8);
                            }
                            if(glama_kontrak.equals("7 Bulan")) {
                                spinner_lama_kontrak.setSelection(9);
                            }
                            if(glama_kontrak.equals("8 Bulan")) {
                                spinner_lama_kontrak.setSelection(10);
                            }
                            if(glama_kontrak.equals("9 Bulan")) {
                                spinner_lama_kontrak.setSelection(11);
                            }
                            if(glama_kontrak.equals("10 Bulan")) {
                                spinner_lama_kontrak.setSelection(12);
                            }
                            if(glama_kontrak.equals("11 Bulan")) {
                                spinner_lama_kontrak.setSelection(13);
                            }
                            if(glama_kontrak.equals("12 Bulan")) {
                                spinner_lama_kontrak.setSelection(14);
                            }

                            etKriteria_Project.setText(gkriteria_project);
                            etGaji_Influencer.setText(ggaji_influencer);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Anda Sedang Offline!", Toast.LENGTH_SHORT).show();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> form= new HashMap<String, String>();
                form.put("nama_brand", Login_Brand.nama_brand);
                form.put("id", AdaptorProjectBrand.id_project);
                return form;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }
    }


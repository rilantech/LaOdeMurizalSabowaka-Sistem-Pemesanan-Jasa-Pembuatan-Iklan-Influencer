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
import android.widget.TextView;
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
import com.example.aplikasivannes.AdaptorNotifikasi.AdaptorNotifikasiBrand;
import com.example.aplikasivannes.AdaptorProgressBrand.AdaptorProgressBrand;
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

public class Fragment_Detail_Progress_Brand extends Fragment {
    ImageView tombol_kembali, ivLogo_Brand, ivBukti_Pembayaran;
    EditText etLink_Draft, etLink_Bukti_Post, etRevisi, etNomor_Rekening;
    TextView tvJudul_Progress;
    Spinner spinner_status_project;
    Button btnbatal, btnsimpan;
    Bitmap bitmap;
    String encodeImage="", id_progress;
    int MY_REQUEST_CODE= 1;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_progress_brand, container, false);
       tombol_kembali = view.findViewById(R.id.tombol_kembali);
       ivLogo_Brand = view.findViewById(R.id.ivLogo_Brand);
       ivBukti_Pembayaran = view.findViewById(R.id.ivBukti_Pembayaran);
       tvJudul_Progress = view.findViewById(R.id.tvJudul_Progress);
       etLink_Draft = view.findViewById(R.id.etLink_Draft);
       etLink_Bukti_Post = view.findViewById(R.id.etLink_Bukti_Post);
       etRevisi = view.findViewById(R.id.etRevisi);
       etNomor_Rekening = view.findViewById(R.id.etNomor_Rekening);
       spinner_status_project = view.findViewById(R.id.spinner_status_project);
       btnbatal = view.findViewById(R.id.btnbatal);
       btnsimpan = view.findViewById(R.id.btnsimpan);

       if(AdaptorProgressBrand.id_progress != ""){
           id_progress = AdaptorProgressBrand.id_progress;
           tampil_progress();
       }


//        if(NotificationService.id_progress != ""){
//            id_progress = NotificationService.id_progress;
//            tampil_progress2();
//        }



        tombol_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.konten, new Fragment_Progress_Brand());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        ivBukti_Pembayaran.setOnClickListener(new View.OnClickListener() {
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
                if (etRevisi.getText().toString().length() == 0) {
                    etRevisi.setError("Tidak Boleh Kosong");
                }
                else {
                    String url = new Configurasi().baseUrl()+"brand/api/edit_detail_progress.php";
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
                                    builder.setMessage("Progress Terupdate!");
                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                            transaction.replace(R.id.konten, new Fragment_Progress_Brand());
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
                            myParams.put("id", id_progress);
                            myParams.put("status_project", spinner_status_project.getSelectedItem().toString());
                            myParams.put("revisi", etRevisi.getText().toString());
                            myParams.put("struk_bukti_pembayaran", encodeImage);
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
               etRevisi.setText("");
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
                ivBukti_Pembayaran.setImageBitmap(bitmap);
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

    void tampil_progress(){
        String url = new Configurasi().baseUrl()+"brand/api/tampil_progress.php";

        StringRequest stringRequest = new StringRequest(
                1, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response).getJSONObject("data");
                            String glogo_brand = jsonObject.getString("logo_brand");
                            String gnama_influencer =jsonObject.getString("nama_influencer");
                            String gnama_project =jsonObject.getString("nama_project");
                            String glink_draft =jsonObject.getString("link_draft");
                            String glink_bukti_post = jsonObject.getString("link_bukti_post");
                            String gstatus_project =jsonObject.getString("status_project");
                            String grevisi =jsonObject.getString("revisi");
                            String gnomor_rekening = jsonObject.getString("nomor_rekening");
                            String gstruk_bukti_pembayaran = jsonObject.getString("struk_bukti_pembayaran");

                            if(glogo_brand != ""){
                                String urlgambar = new Configurasi().baseUrl()+"brand/gambar/"+ glogo_brand;

                                Glide.with(getContext())
                                        .load(urlgambar)
                                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                                        .skipMemoryCache(true)
                                        .into(ivLogo_Brand);

                            }

                            tvJudul_Progress.setText(gnama_project + " | " + gnama_influencer);

                            if(glink_draft != ""){
                                etLink_Draft.setText(glink_draft);
                            }

                            if(glink_draft != ""){
                                etLink_Bukti_Post.setText(glink_bukti_post);
                            }

                            if(gstatus_project.equals("Sedang Dikerjakan")) {
                                spinner_status_project.setSelection(0);
                            }

                            if(gstatus_project.equals("ACC")) {
                                spinner_status_project.setSelection(1);
                            }

                            if(gstatus_project.equals("Revisi")) {
                                spinner_status_project.setSelection(2);
                            }

                            if(gstatus_project.equals("Ditolak")) {
                                spinner_status_project.setSelection(3);
                            }


                            if(grevisi != ""){
                                etRevisi.setText(grevisi);
                            }

                            if(gnomor_rekening != ""){
                                etNomor_Rekening.setText(gnomor_rekening);
                            }

                            if(gstruk_bukti_pembayaran != ""){
                                String urlgambar1 = new Configurasi().baseUrl()+"brand/gambar/"+ gstruk_bukti_pembayaran;

                                Glide.with(getContext())
                                        .load(urlgambar1)
                                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                                        .skipMemoryCache(true)
                                        .into(ivBukti_Pembayaran);
                            }

                            if(gstruk_bukti_pembayaran == ""){
                                ivBukti_Pembayaran.setImageResource(R.drawable.icon_upload_logo_brand);
                            }



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
                form.put("id", AdaptorProgressBrand.id_progress);
                return form;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }

    void tampil_progress1(){
        String url = new Configurasi().baseUrl()+"brand/api/tampil_progress.php";

        StringRequest stringRequest = new StringRequest(
                1, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response).getJSONObject("data");
                            String glogo_brand = jsonObject.getString("logo_brand");
                            String gnama_influencer =jsonObject.getString("nama_influencer");
                            String gnama_project =jsonObject.getString("nama_project");
                            String glink_draft =jsonObject.getString("link_draft");
                            String glink_bukti_post = jsonObject.getString("link_bukti_post");
                            String gstatus_project =jsonObject.getString("status_project");
                            String grevisi =jsonObject.getString("revisi");
                            String gnomor_rekening = jsonObject.getString("nomor_rekening");
                            String gstruk_bukti_pembayaran = jsonObject.getString("struk_bukti_pembayaran");

                            String urlgambar = new Configurasi().baseUrl()+"brand/gambar/"+ glogo_brand;

                            Glide.with(getContext())
                                    .load(urlgambar)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true)
                                    .into(ivLogo_Brand);

                            tvJudul_Progress.setText(gnama_project + " | " + gnama_influencer);

                            if(glink_draft != ""){
                                etLink_Draft.setText(glink_draft);
                            }

                            if(glink_draft != ""){
                                etLink_Bukti_Post.setText(glink_bukti_post);
                            }

                            if(gstatus_project.equals("Sedang Dikerjakan")) {
                                spinner_status_project.setSelection(0);
                            }

                            if(gstatus_project.equals("ACC")) {
                                spinner_status_project.setSelection(1);
                            }

                            if(gstatus_project.equals("Revisi")) {
                                spinner_status_project.setSelection(2);
                            }

                            if(gstatus_project.equals("Ditolak")) {
                                spinner_status_project.setSelection(3);
                            }


                            if(grevisi != ""){
                                etRevisi.setText(grevisi);
                            }

                            if(gnomor_rekening != ""){
                                etNomor_Rekening.setText(gnomor_rekening);
                            }

                            if(gstruk_bukti_pembayaran != ""){
                                String urlgambar1 = new Configurasi().baseUrl()+"brand/gambar/"+ gstruk_bukti_pembayaran;

                                Glide.with(getContext())
                                        .load(urlgambar1)
                                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                                        .skipMemoryCache(true)
                                        .into(ivBukti_Pembayaran);
                            }

                            if(gstruk_bukti_pembayaran == ""){
                                ivBukti_Pembayaran.setImageResource(R.drawable.icon_upload_logo_brand);
                            }



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
                form.put("id", AdaptorNotifikasiBrand.id_progress1);
                return form;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }


    void tampil_progress2(){
        String url = new Configurasi().baseUrl()+"brand/api/tampil_progress.php";

        StringRequest stringRequest = new StringRequest(
                1, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response).getJSONObject("data");
                            String glogo_brand = jsonObject.getString("logo_brand");
                            String gnama_influencer =jsonObject.getString("nama_influencer");
                            String gnama_project =jsonObject.getString("nama_project");
                            String glink_draft =jsonObject.getString("link_draft");
                            String glink_bukti_post = jsonObject.getString("link_bukti_post");
                            String gstatus_project =jsonObject.getString("status_project");
                            String grevisi =jsonObject.getString("revisi");
                            String gnomor_rekening = jsonObject.getString("nomor_rekening");
                            String gstruk_bukti_pembayaran = jsonObject.getString("struk_bukti_pembayaran");

                            String urlgambar = new Configurasi().baseUrl()+"brand/gambar/"+ glogo_brand;

                            Glide.with(getContext())
                                    .load(urlgambar)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true)
                                    .into(ivLogo_Brand);

                            tvJudul_Progress.setText(gnama_project + " | " + gnama_influencer);

                            if(glink_draft != ""){
                                etLink_Draft.setText(glink_draft);
                            }

                            if(glink_draft != ""){
                                etLink_Bukti_Post.setText(glink_bukti_post);
                            }

                            if(gstatus_project.equals("Sedang Dikerjakan")) {
                                spinner_status_project.setSelection(0);
                            }

                            if(gstatus_project.equals("ACC")) {
                                spinner_status_project.setSelection(1);
                            }

                            if(gstatus_project.equals("Revisi")) {
                                spinner_status_project.setSelection(2);
                            }

                            if(gstatus_project.equals("Ditolak")) {
                                spinner_status_project.setSelection(3);
                            }

                            if(gstatus_project.equals("Dibayar")) {
                                spinner_status_project.setSelection(1);
                            }


                            if(grevisi != ""){
                                etRevisi.setText(grevisi);
                            }

                            if(gnomor_rekening != ""){
                                etNomor_Rekening.setText(gnomor_rekening);
                            }

                            if(gstruk_bukti_pembayaran != ""){
                                String urlgambar1 = new Configurasi().baseUrl()+"brand/gambar/"+ gstruk_bukti_pembayaran;

                                Glide.with(getContext())
                                        .load(urlgambar1)
                                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                                        .skipMemoryCache(true)
                                        .into(ivBukti_Pembayaran);
                            }

                            if(gstruk_bukti_pembayaran == ""){
                                ivBukti_Pembayaran.setImageResource(R.drawable.icon_upload_logo_brand);
                            }



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
                form.put("id", NotificationService.id_progress);
                return form;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }
}


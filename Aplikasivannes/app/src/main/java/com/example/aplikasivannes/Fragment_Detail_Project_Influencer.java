package com.example.aplikasivannes;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import com.example.aplikasivannes.AdapterProjectBrand.AdaptorProjectInfluencer;
import com.example.aplikasivannes.AdaptorProgressBrand.AdaptorProgressInfluencer;

import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Fragment_Detail_Project_Influencer extends Fragment {
    ImageView ivLogo_Brand, tombol_kembali;
    TextView tvNama_Project, tvNama_Brand, tvGaji_Influencer, tvKriteria_Project, tvlama_kontrak;
    Button btnKerjakan;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_project_influencer, container, false);
            tombol_kembali = view.findViewById(R.id.tombol_kembali);
            tvNama_Project = view.findViewById(R.id.tvNama_Project);
            tvNama_Brand = view.findViewById(R.id.tvNama_Brand);
            tvGaji_Influencer = view.findViewById(R.id.tvGaji_Influencer);
            tvKriteria_Project = view.findViewById(R.id.tvKriteria_Project);
            ivLogo_Brand = view.findViewById(R.id.ivLogo_Brand);
            btnKerjakan = view.findViewById(R.id.btnKerjakan);
            tvlama_kontrak = view.findViewById(R.id.tvlama_kontrak);


            String urlgambar = new Configurasi().baseUrl()+"brand/gambar/"+ AdaptorProjectInfluencer.logo_brand;

            Glide.with(getContext())
                    .load(urlgambar)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(ivLogo_Brand);
            tvNama_Project.setText(AdaptorProjectInfluencer.nama_project);
            tvNama_Brand.setText(AdaptorProjectInfluencer.nama_brand+" | "+ AdaptorProjectInfluencer.kategori);
            tvGaji_Influencer.setText(AdaptorProjectInfluencer.gaji_influencer);
            tvlama_kontrak.setText("Lama Kontrak : "+AdaptorProjectInfluencer.lama_kontrak);
            tvKriteria_Project.setText(AdaptorProjectInfluencer.kriteria_project);

        tombol_kembali.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.konten, new Fragment_Project_Influencer());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnKerjakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String url = new Configurasi().baseUrl()+"influencer/api/kerjakanproject.php";
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
                                    builder.setMessage("Project Berhasil Diajukan!");
                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                            transaction.replace(R.id.konten, new Fragment_Progress_Influencer());
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
                            myParams.put("id", AdaptorProjectInfluencer.id_project);
                            myParams.put("nama_influencer", Login_Influencer.nama_influencer);
                            return myParams;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                    requestQueue.add(stringRequest);
                }
        });
        return view;
    }
    }

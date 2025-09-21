package com.example.aplikasivannes;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
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
import com.example.aplikasivannes.AdapterProjectBrand.AdaptorProjectBrand;
import com.example.aplikasivannes.AdapterProjectBrand.GetProjectBrand;

import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fragment_Project_Brand extends Fragment {
    public static String kosong, id_hapus="";
    AdaptorProjectBrand adaptor;
    EditText cari;
    ListView listView;
    ArrayList<GetProjectBrand> model;
    TextView txttambahdata, tvDaftarinfluencer;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project_brand, container, false);
        listView = view.findViewById(R.id.list);
        cari = view.findViewById(R.id.cari);
        txttambahdata = view.findViewById(R.id.txttambahdata);
        tvDaftarinfluencer = view.findViewById(R.id.tvDaftarInflucer);


        tampil_data_project();

        tvDaftarinfluencer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.konten, new Fragment_Daftar_Influencer());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

       if(AdaptorProjectBrand.id_hapus_project != ""){
            hapus_project();
        }

        this.cari.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               cari_data_project(charSequence.toString());
            }

            public void afterTextChanged(Editable editable) {
            }
        });

        cari_data_project(kosong);

        txttambahdata.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.konten, new Fragment_Tambah_Project_Brand());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }


    void tampil_data_project(){
        String url = new Configurasi().baseUrl()+"brand/api/project.php";
        StringRequest request = new StringRequest(
                Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray= jsonObject.getJSONArray("data");
                            model = new ArrayList<>();
                            for(int i=0; i<=jsonArray.length(); i++){
                                JSONObject getData = jsonArray.getJSONObject(i);
                                model.add(new GetProjectBrand(
                                        getData.getInt("id"),
                                        getData.getString("waktu_posting"),
                                        getData.getString("logo_brand"),
                                        getData.getString("nama_brand"),
                                        getData.getString("nama_project"),
                                        getData.getString("kategori"),
                                        getData.getString("lama_kontrak"),
                                        getData.getString("kriteria_project"),
                                        getData.getString("gaji_influencer")
                                ));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        AdaptorProjectBrand adaptor = new AdaptorProjectBrand(getContext(), model);
                        listView.setAdapter(adaptor);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> form = new HashMap<String, String>();
                form.put("nama_brand", Login_Brand.nama_brand);
                return form;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }

    void cari_data_project(String str){
        String url = new Configurasi().baseUrl()+"brand/api/cari_project.php";
        StringRequest request = new StringRequest(
                Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray= jsonObject.getJSONArray("data");
                            model = new ArrayList<>();
                            for(int i=0; i<=jsonArray.length(); i++){
                                JSONObject getData = jsonArray.getJSONObject(i);
                                model.add(new GetProjectBrand(
                                        getData.getInt("id"),
                                        getData.getString("waktu_posting"),
                                        getData.getString("logo_brand"),
                                        getData.getString("nama_brand"),
                                        getData.getString("nama_project"),
                                        getData.getString("kategori"),
                                        getData.getString("lama_kontrak"),
                                        getData.getString("kriteria_project"),
                                        getData.getString("gaji_influencer")
                                ));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        AdaptorProjectBrand adaptor = new AdaptorProjectBrand(getContext(), model);
                        listView.setAdapter(adaptor);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> form = new HashMap<String, String>();
                form.put("nama_brand", Login_Brand.nama_brand);
                form.put("cari_project", str);
                return form;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }

    void hapus_project(){
        String url = new Configurasi().baseUrl()+"brand/api/hapus_project.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =jsonObject.getString("status");
                    if(status.equals("berhasil_terhapus"))
                    {
                        // Toast.makeText(HalamanRegistrasi.this, "Berhasil Registrasi", Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Sukses");
                        builder.setMessage("Project Terhapus!");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tampil_data_project();
                                AdaptorProjectBrand.id_hapus_project = "";
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
                //Toast.makeText(getContext(), "Anda Sedang Offline!", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }){

            @androidx.annotation.Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> myParams = new HashMap<>();
                myParams.put("id", AdaptorProjectBrand.id_hapus_project);
                return myParams;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }
}

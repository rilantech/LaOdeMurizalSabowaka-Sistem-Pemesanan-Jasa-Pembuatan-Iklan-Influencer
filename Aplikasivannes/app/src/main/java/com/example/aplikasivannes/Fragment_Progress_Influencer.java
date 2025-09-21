package com.example.aplikasivannes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.aplikasivannes.AdaptorProgressBrand.AdaptorProgressBrand;
import com.example.aplikasivannes.AdaptorProgressBrand.AdaptorProgressInfluencer;
import com.example.aplikasivannes.AdaptorProgressBrand.GetProgressBrand;

import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fragment_Progress_Influencer extends Fragment {
    public static String kosong="", id_hapus="";
    AdaptorProgressInfluencer adaptor;
    EditText cari;
    ListView listView;
    ArrayList<GetProgressBrand> model;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress_influencer, container, false);

        if(view != null){
            listView = view.findViewById(R.id.list);
            cari = view.findViewById(R.id.cari);

            tampil_data_progress();

            this.cari.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    cari_data_progress(charSequence.toString());
                }

                public void afterTextChanged(Editable editable) {
                }
            });

            cari_data_progress(kosong);

        }

        return view;
    }

    void tampil_data_progress(){
        String url = new Configurasi().baseUrl()+"influencer/api/progress.php";
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
                                model.add(new GetProgressBrand(
                                        getData.getInt("id"),
                                        getData.getString("waktu_pengajuan"),
                                        getData.getString("waktu_posting"),
                                        getData.getString("waktu_pembayaran"),
                                        getData.getString("logo_brand"),
                                        getData.getString("nama_brand"),
                                        getData.getString("nama_project"),
                                        getData.getString("kategori"),
                                        getData.getString("kriteria_project"),
                                        getData.getString("gaji_influencer"),
                                        getData.getString("lama_kontrak"),
                                        getData.getString("nama_influencer"),
                                        getData.getString("link_draft"),
                                        getData.getString("link_bukti_post"),
                                        getData.getString("revisi"),
                                        getData.getString("status_project"),
                                        getData.getString("nomor_rekening"),
                                        getData.getString("struk_bukti_pembayaran")
                                ));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        AdaptorProgressInfluencer adaptor = new AdaptorProgressInfluencer(getContext(), model);
                        listView.setAdapter(adaptor);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> form = new HashMap<String, String>();
                form.put("nama_influencer", Login_Influencer.nama_influencer);
                return form;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }

    void cari_data_progress(String str){
        String url = new Configurasi().baseUrl()+"influencer/api/cari_progress.php";
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
                                model.add(new GetProgressBrand(
                                        getData.getInt("id"),
                                        getData.getString("waktu_pengajuan"),
                                        getData.getString("waktu_posting"),
                                        getData.getString("waktu_pembayaran"),
                                        getData.getString("logo_brand"),
                                        getData.getString("nama_brand"),
                                        getData.getString("nama_project"),
                                        getData.getString("kategori"),
                                        getData.getString("kriteria_project"),
                                        getData.getString("gaji_influencer"),
                                        getData.getString("lama_kontrak"),
                                        getData.getString("nama_influencer"),
                                        getData.getString("link_draft"),
                                        getData.getString("link_bukti_post"),
                                        getData.getString("revisi"),
                                        getData.getString("status_project"),
                                        getData.getString("nomor_rekening"),
                                        getData.getString("struk_bukti_pembayaran")
                                ));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        AdaptorProgressInfluencer adaptor = new AdaptorProgressInfluencer(getContext(), model);
                        listView.setAdapter(adaptor);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> form = new HashMap<String, String>();
                form.put("nama_influencer", Login_Influencer.nama_influencer);
                form.put("cari_progress", str);
                return form;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }
    }

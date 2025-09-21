package com.example.aplikasivannes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.aplikasivannes.AdapterProjectBrand.AdaptorProjectInfluencer;
import com.example.aplikasivannes.AdapterProjectBrand.GetProjectBrand;

import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_Kategori_Project_Influencer extends Fragment {
    public static String kosong, id_hapus="";
    AdaptorProjectInfluencer adaptor;
    EditText cari;
    List<GetProjectBrand> model;
    RecyclerView recyclerView;
    ImageView tombol_kembali;
    TextView tvJudul_Kategori;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kategori_project_influencer, container, false);
        cari = view.findViewById(R.id.cari);
        recyclerView = view.findViewById(R.id.recycle_view);
        tombol_kembali = view.findViewById(R.id.tombol_kembali);
        tvJudul_Kategori = view.findViewById(R.id.tvJudul_Kategori);

        GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        model= new ArrayList<>();

        tampil_data_project();

        tvJudul_Kategori.setText("Kategori "+Fragment_Project_Influencer.kategori);

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

        tombol_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.konten, new Fragment_Project_Influencer());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        return view;
    }
    
    void tampil_data_project(){
        String url = new Configurasi().baseUrl()+"influencer/api/kategori_project.php";
        StringRequest request = new StringRequest(
                Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                      //  Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
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
                            adaptor.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        AdaptorProjectInfluencer adaptor = new AdaptorProjectInfluencer(getContext(), model);
                        recyclerView.setAdapter(adaptor);


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
                form.put("kategori", Fragment_Project_Influencer.kategori);
                return form;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }

    void cari_data_project(String str){
        String url = new Configurasi().baseUrl()+"influencer/api/cari_kategori_project.php";
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
                            adaptor.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        AdaptorProjectInfluencer adaptor = new AdaptorProjectInfluencer(getContext(), model);
                        recyclerView.setAdapter(adaptor);
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
                form.put("cari_project", str);
                form.put("kategori", Fragment_Project_Influencer.kategori);
                return form;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }

    @Override
    public void onStart() {
        tampil_data_project();
        super.onStart();
    }
}

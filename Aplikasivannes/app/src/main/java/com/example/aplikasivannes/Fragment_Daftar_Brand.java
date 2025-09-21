package com.example.aplikasivannes;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.example.aplikasivannes.AdapterDaftarBrand.AdaptorDaftarBrand;
import com.example.aplikasivannes.AdapterDaftarBrand.GetDaftarBrand;
import com.example.aplikasivannes.AdapterProjectBrand.AdaptorProjectBrand;
import com.example.aplikasivannes.AdapterProjectBrand.GetProjectBrand;

import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fragment_Daftar_Brand extends Fragment {
    public static String kosong, nama_brand, foto_profil, email, nomor_telepon;
    AdaptorDaftarBrand adaptor;
    EditText cari;
    ListView listView;
    ArrayList<GetDaftarBrand> model;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daftar_brand, container, false);
        listView = view.findViewById(R.id.list);
        cari = view.findViewById(R.id.cari);
        tampil_daftar_brand();

        this.cari.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               cari_daftar_brand(charSequence.toString());
            }

            public void afterTextChanged(Editable editable) {
            }
        });

        cari_daftar_brand(kosong);

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                nama_brand= model.get(i).getNama_brand();
                foto_profil = model.get(i).getFoto_profil();
                email = model.get(i).getEmail();
                nomor_telepon = model.get(i).getNomor_telepon();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.konten, new Fragment_Detail_Daftar_Brand());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }


    void tampil_daftar_brand(){
        String url = new Configurasi().baseUrl()+"influencer/api/daftarbrand.php";
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
                                model.add(new GetDaftarBrand(
                                        getData.getInt("id"),
                                        getData.getString("nama_brand"),
                                        getData.getString("foto_profil"),
                                        getData.getString("email"),
                                        getData.getString("nomor_telepon")

                                ));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        AdaptorDaftarBrand adaptor = new AdaptorDaftarBrand(getContext(), model);
                        listView.setAdapter(adaptor);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }

    void cari_daftar_brand(String str){
        String url = new Configurasi().baseUrl()+"influencer/api/cari_daftar_brand.php";
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
                                model.add(new GetDaftarBrand(
                                        getData.getInt("id"),
                                        getData.getString("nama_brand"),
                                        getData.getString("foto_profil"),
                                        getData.getString("email"),
                                        getData.getString("nomor_telepon")
                                ));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        AdaptorDaftarBrand adaptor = new AdaptorDaftarBrand(getContext(), model);
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
                form.put("cari_brand", str);
                return form;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }

}

package com.example.aplikasivannes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
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
import com.example.aplikasivannes.AdapterDaftarInfluencer.AdaptorDaftarInfluencer;
import com.example.aplikasivannes.AdapterDaftarInfluencer.GetDaftarInfluencer;

import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fragment_Daftar_Influencer extends Fragment {
    public static String kosong, nama_influencer, foto_profil, email, nomor_telepon, sosmed_tiktok, pengikut_sosmed_tiktok,sosmed_instagram, pengikut_sosmed_instagram, sosmed_facebook, pengikut_sosmed_facebook;
    AdaptorDaftarInfluencer adaptor;
    EditText cari;
    ListView listView;
    ArrayList<GetDaftarInfluencer> model;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daftar_influencer, container, false);
        listView = view.findViewById(R.id.list);
        cari = view.findViewById(R.id.cari);
        tampil_daftar_influencer();

        this.cari.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               cari_daftar_influencer(charSequence.toString());
            }

            public void afterTextChanged(Editable editable) {
            }
        });

        cari_daftar_influencer(kosong);

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                nama_influencer= model.get(i).getNama_influencer();
                foto_profil = model.get(i).getFoto_profil();
                email = model.get(i).getEmail();
                nomor_telepon = model.get(i).getNomor_telepon();
                sosmed_tiktok = model.get(i).getSosmed_tiktok();
                pengikut_sosmed_tiktok = model.get(i).getPengikut_sosmed_tiktok();
                sosmed_instagram = model.get(i).getSosmed_instagram();
                pengikut_sosmed_instagram = model.get(i).getPengikut_sosmed_instagram();
                sosmed_facebook = model.get(i).getSosmed_facebook();
                pengikut_sosmed_facebook = model.get(i).getPengikut_sosmed_facebook();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.konten, new Fragment_Detail_Daftar_Influencer());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }


    void tampil_daftar_influencer(){
        String url = new Configurasi().baseUrl()+"brand/api/daftarinfluencer.php";
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
                                model.add(new GetDaftarInfluencer(
                                        getData.getInt("id"),
                                        getData.getString("nama_influencer"),
                                        getData.getString("foto_profil"),
                                        getData.getString("email"),
                                        getData.getString("nomor_telepon"),
                                        getData.getString("sosmed_tiktok"),
                                        getData.getString("pengikut_sosmed_tiktok"),
                                        getData.getString("sosmed_instagram"),
                                        getData.getString("pengikut_sosmed_instagram"),
                                        getData.getString("sosmed_facebook"),
                                        getData.getString("pengikut_sosmed_facebook")

                                ));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        AdaptorDaftarInfluencer adaptor = new AdaptorDaftarInfluencer(getContext(), model);
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

    void cari_daftar_influencer(String str){
        String url = new Configurasi().baseUrl()+"brand/api/cari_daftar_influencer.php";
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
                                model.add(new GetDaftarInfluencer(
                                        getData.getInt("id"),
                                        getData.getString("nama_influencer"),
                                        getData.getString("foto_profil"),
                                        getData.getString("email"),
                                        getData.getString("nomor_telepon"),
                                        getData.getString("sosmed_tiktok"),
                                        getData.getString("pengikut_sosmed_tiktok"),
                                        getData.getString("sosmed_instagram"),
                                        getData.getString("pengikut_sosmed_instagram"),
                                        getData.getString("sosmed_facebook"),
                                        getData.getString("pengikut_sosmed_facebook")
                                ));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        AdaptorDaftarInfluencer adaptor = new AdaptorDaftarInfluencer(getContext(), model);
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
                form.put("cari_influencer", str);
                return form;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }

}

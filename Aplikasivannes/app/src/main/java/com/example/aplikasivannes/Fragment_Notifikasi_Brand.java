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
import android.widget.ImageView;
import android.widget.ListView;

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
import com.example.aplikasivannes.AdapterRiwayatTransaksiBrand.AdaptorRiwayatTransaksiBrand;
import com.example.aplikasivannes.AdapterRiwayatTransaksiBrand.GetRiwayatTransaksiBrand;
import com.example.aplikasivannes.AdaptorNotifikasi.AdaptorNotifikasiBrand;
import com.example.aplikasivannes.AdaptorNotifikasi.GetNotifikasiBrand;

import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fragment_Notifikasi_Brand extends Fragment {
    public static String kosong, struk_pembayaran, tanggal_transaksi, nama_project, nama_influencer, jumlah_transaksi;
    AdaptorNotifikasiBrand adaptor;
    EditText cari;
    ListView listView;
    ArrayList<GetNotifikasiBrand> model;
    ImageView tombol_kembali;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifikasi_brand, container, false);
        listView = view.findViewById(R.id.list);
        cari = view.findViewById(R.id.cari);
        tombol_kembali = view.findViewById(R.id.tombol_kembali);

        tombol_kembali.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.konten, new Fragment_Profil_Brand());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        tampil_data_riwayat_transaksi();

       if(AdaptorProjectBrand.id_hapus_project != ""){
            hapus_project();
        }

        this.cari.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               cari_data_riwayat_transaksi(charSequence.toString());
            }

            public void afterTextChanged(Editable editable) {
            }
        });

        cari_data_riwayat_transaksi(kosong);

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                struk_pembayaran = model.get(i).getStruk_bukti_pembayaran();
//                tanggal_transaksi = model.get(i).getWaktu_pembayaran();
//                nama_project = model.get(i).getNama_project();
//                nama_influencer = model.get(i).getNama_influencer();
//                jumlah_transaksi = model.get(i).getGaji_influencer();

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.konten, new Fragment_Detail_Riwayat_Transaksi_Brand());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

    void tampil_data_riwayat_transaksi(){
        String url = new Configurasi().baseUrl()+"brand/api/notifikasi.php";
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
                                model.add(new GetNotifikasiBrand(
                                        getData.getInt("id"),
                                        getData.getString("id_project"),
                                        getData.getString("id_progress"),
                                        getData.getString("jenis_notif"),
                                        getData.getString("waktu_notif"),
                                        getData.getString("nama_brand"),
                                        getData.getString("nama_influencer"),
                                        getData.getString("isi_notif")
                                ));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        AdaptorNotifikasiBrand adaptor = new AdaptorNotifikasiBrand(getContext(), model);
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

    void cari_data_riwayat_transaksi(String str){
        String url = new Configurasi().baseUrl()+"brand/api/notifasi.php";
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
                                model.add(new GetNotifikasiBrand(
                                        getData.getInt("id"),
                                        getData.getString("id_project"),
                                        getData.getString("id_progress"),
                                        getData.getString("jenis_notif"),
                                        getData.getString("waktu_notif"),
                                        getData.getString("nama_brand"),
                                        getData.getString("nama_influencer"),
                                        getData.getString("isi_notif")
                                ));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        AdaptorNotifikasiBrand adaptor = new AdaptorNotifikasiBrand(getContext(), model);
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
                form.put("cari_transaksi", str);
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
                                tampil_data_riwayat_transaksi();
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

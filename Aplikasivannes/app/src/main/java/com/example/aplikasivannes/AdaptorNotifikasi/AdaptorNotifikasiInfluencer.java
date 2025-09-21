package com.example.aplikasivannes.AdaptorNotifikasi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.aplikasivannes.AdaptorProgressBrand.AdaptorProgressBrand;
import com.example.aplikasivannes.AdaptorProgressBrand.AdaptorProgressInfluencer;
import com.example.aplikasivannes.Fragment_Detail_Progress_Brand;
import com.example.aplikasivannes.Fragment_Detail_Progress_Influencer;
import com.example.aplikasivannes.R;

import java.util.ArrayList;

public class AdaptorNotifikasiInfluencer extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<GetNotifikasiBrand> model;
    public static String id_progress1, id_hapus_progress;
    public AdaptorNotifikasiInfluencer(Context context2, ArrayList<GetNotifikasiBrand> model2) {
        this.inflater = LayoutInflater.from(context2);
        this.context = context2;
        this.model = model2;
    }

    public int getCount() {
        return this.model.size();
    }

    public Object getItem(int position) {
        return this.model.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    @SuppressLint("MissingInflatedId")
    public View getView(int position, View view, ViewGroup viewGroup) {
        TextView tvTanggal_Notifikasi, tvNama_Pengirim, tvIsi_Notif;
        Button btnLihat;
        View view1 = this.inflater.inflate(R.layout.list_notifikasi_influencer, (ViewGroup) null);
        if (view1 != null) {
            tvTanggal_Notifikasi = view1.findViewById(R.id.tvTanggal_Notifikasi);
            tvNama_Pengirim =view1.findViewById(R.id.tvNama_Pengirim);
            tvIsi_Notif =view1.findViewById(R.id.tvIsi_Notif);
            btnLihat = view1.findViewById(R.id.btnLihat);

               tvTanggal_Notifikasi.setText(model.get(position).getWaktu_notif());
               tvNama_Pengirim.setText(model.get(position).getNama_brand());
               tvIsi_Notif.setText(model.get(position).getIsi_notif());

            btnLihat.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    id_progress1 = String.valueOf(model.get(position).getId_progress());
                    AdaptorProgressInfluencer.id_progress ="";
                    FragmentTransaction fragmentTransaction =
                            ((AppCompatActivity)view.getContext()).getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.konten, new Fragment_Detail_Progress_Influencer());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }
        return view1;
    }
}

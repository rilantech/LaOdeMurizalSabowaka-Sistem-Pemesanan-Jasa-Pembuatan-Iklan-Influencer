package com.example.aplikasivannes.AdaptorProgressBrand;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.aplikasivannes.AdaptorNotifikasi.AdaptorNotifikasiBrand;
import com.example.aplikasivannes.AdaptorNotifikasi.AdaptorNotifikasiInfluencer;
import com.example.aplikasivannes.Fragment_Detail_Progress_Brand;
import com.example.aplikasivannes.Fragment_Detail_Progress_Influencer;
import com.example.aplikasivannes.R;

import java.util.ArrayList;

public class AdaptorProgressInfluencer extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<GetProgressBrand> model;
    public static String id_progress, id_hapus_progress;
    public AdaptorProgressInfluencer(Context context2, ArrayList<GetProgressBrand> model2) {
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
        TextView tvTanggal_Pengajuan, tvNama_Project, tvNama_Brand, tvGaji_Influencer, tvLama_Kontrak, tvStatus_Project;
        ImageView tomboledit;
        View view1 = this.inflater.inflate(R.layout.list_progress_influencer, (ViewGroup) null);
        if (view1 != null) {
            tvTanggal_Pengajuan = view1.findViewById(R.id.tvTanggal_Pengajuan);
            tvNama_Project =view1.findViewById(R.id.tvNama_Project);
            tvNama_Brand =view1.findViewById(R.id.tvNama_Brand);
            tvGaji_Influencer =view1.findViewById(R.id.tvGaji_Influencer);
            tvLama_Kontrak =view1.findViewById(R.id.tvLama_Kontrak);
            tvStatus_Project =view1.findViewById(R.id.tvStatus_Project);
            tomboledit = view1.findViewById(R.id.tomboledit);

               tvTanggal_Pengajuan.setText(model.get(position).getWaktu_pengajuan());
               tvNama_Project.setText(model.get(position).getNama_project());
               tvNama_Brand.setText(model.get(position).getNama_brand());
               tvGaji_Influencer.setText(model.get(position).getGaji_influencer());
               tvLama_Kontrak.setText(model.get(position).getLama_kontrak());
               tvStatus_Project.setText(model.get(position).getStatus_project());

            tomboledit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    id_progress = String.valueOf(model.get(position).getId());
                    AdaptorNotifikasiInfluencer.id_progress1 ="";
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

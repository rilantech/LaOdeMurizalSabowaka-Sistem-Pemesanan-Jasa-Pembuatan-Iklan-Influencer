package com.example.aplikasivannes.AdapterProjectBrand;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.aplikasivannes.Configurasi;
import com.example.aplikasivannes.Fragment_Edit_Project_Brand;
import com.example.aplikasivannes.Fragment_Project_Brand;
import com.example.aplikasivannes.Fragment_Tambah_Project_Brand;
import com.example.aplikasivannes.Halaman_Utama_Brand;
import com.example.aplikasivannes.Login_Brand;
import com.example.aplikasivannes.R;

import java.util.ArrayList;

public class AdaptorProjectBrand extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<GetProjectBrand> model;
    public static String id_project, id_hapus_project;
    public AdaptorProjectBrand(Context context2, ArrayList<GetProjectBrand> model2) {
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
        TextView tvTanggal_Posting, tvNama_Project, tvKategori, tvGaji_Influencer, tvlama_kontrak;
        ImageView ivLogo_Brand, tomboledit, tombolhapus;
        View view1 = this.inflater.inflate(R.layout.list_project_brand, (ViewGroup) null);
        if (view1 != null) {
            tvTanggal_Posting = view1.findViewById(R.id.tvTanggal_Posting);
            tvNama_Project =view1.findViewById(R.id.tvNama_Project);
            tvKategori =view1.findViewById(R.id.tvKategori);
            tvGaji_Influencer =view1.findViewById(R.id.tvGaji_Influencer);
            tvlama_kontrak =view1.findViewById(R.id.tvlama_kontrak);
            ivLogo_Brand =view1.findViewById(R.id.ivLogo_Brand);
            tomboledit = view1.findViewById(R.id.tomboledit);
            tombolhapus = view1.findViewById(R.id.tombolhapus);

               tvTanggal_Posting.setText(model.get(position).getWaktu_posting());
               tvNama_Project.setText(model.get(position).getNama_project());
               tvKategori.setText(model.get(position).getKategori());
               tvlama_kontrak.setText(model.get(position).getLama_kontrak());
//               tvGaji_Influencer.setText(model.get(position).getGaji_influencer());

                String urlgambar = new Configurasi().baseUrl()+"brand/gambar/"+ model.get(position).getLogo_brand();

                Glide.with(context)
                        .load(urlgambar)
                        .circleCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(ivLogo_Brand);

            tomboledit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    id_project = String.valueOf(model.get(position).getId());
                    FragmentTransaction fragmentTransaction =
                            ((AppCompatActivity)view.getContext()).getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.konten, new Fragment_Edit_Project_Brand());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });

            tombolhapus.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    id_hapus_project = String.valueOf(model.get(position).getId());
                    FragmentTransaction fragmentTransaction =
                            ((AppCompatActivity)view.getContext()).getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.konten, new Fragment_Project_Brand());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }
        return view1;
    }
}

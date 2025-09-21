package com.example.aplikasivannes.AdapterDaftarInfluencer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.aplikasivannes.Configurasi;
import com.example.aplikasivannes.R;

import java.util.ArrayList;

public class AdaptorDaftarInfluencer extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<GetDaftarInfluencer> model;
    public static String id_project, id_hapus_project;
    public AdaptorDaftarInfluencer(Context context2, ArrayList<GetDaftarInfluencer> model2) {
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
        TextView tvNama_Influencer, tvEmail, tvNomor_Telepon, tvFollower_IG;
        ImageView ivFoto_Profil;
        View view1 = this.inflater.inflate(R.layout.list_daftar_influencer, (ViewGroup) null);
        if (view1 != null) {
            tvNama_Influencer = view1.findViewById(R.id.tvNama_Influencer);
            tvEmail =view1.findViewById(R.id.tvEmail);
            tvNomor_Telepon =view1.findViewById(R.id.tvNomor_Telepon);
            ivFoto_Profil =view1.findViewById(R.id.ivFoto_Profil);
            tvFollower_IG = view1.findViewById(R.id.tvFollower_IG);

               tvNama_Influencer.setText(model.get(position).getNama_influencer());
               tvEmail.setText(model.get(position).getEmail());
               tvNomor_Telepon.setText(model.get(position).getNomor_telepon());
               tvFollower_IG.setText(model.get(position).getPengikut_sosmed_instagram());

                String urlgambar = new Configurasi().baseUrl()+"influencer/gambar/"+ model.get(position).getFoto_profil();

                Glide.with(context)
                        .load(urlgambar)
                        .circleCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(ivFoto_Profil);

        }
        return view1;
    }
}

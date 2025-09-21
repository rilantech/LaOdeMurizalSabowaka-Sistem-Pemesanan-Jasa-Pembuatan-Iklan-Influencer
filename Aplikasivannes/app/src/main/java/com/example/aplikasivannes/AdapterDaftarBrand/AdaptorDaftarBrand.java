package com.example.aplikasivannes.AdapterDaftarBrand;

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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.aplikasivannes.Configurasi;
import com.example.aplikasivannes.Fragment_Edit_Project_Brand;
import com.example.aplikasivannes.Fragment_Project_Brand;
import com.example.aplikasivannes.R;

import java.util.ArrayList;

public class AdaptorDaftarBrand extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<GetDaftarBrand> model;
    public static String id_project, id_hapus_project;
    public AdaptorDaftarBrand(Context context2, ArrayList<GetDaftarBrand> model2) {
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
        TextView tvNama_Brand, tvEmail, tvNomor_Telepon;
        ImageView ivFoto_Profil;
        View view1 = this.inflater.inflate(R.layout.list_daftar_brand, (ViewGroup) null);
        if (view1 != null) {
            tvNama_Brand = view1.findViewById(R.id.tvNama_Brand);
            tvEmail =view1.findViewById(R.id.tvEmail);
            tvNomor_Telepon =view1.findViewById(R.id.tvNomor_Telepon);
            ivFoto_Profil =view1.findViewById(R.id.ivFoto_Profil);

               tvNama_Brand.setText(model.get(position).getNama_brand());
               tvEmail.setText(model.get(position).getEmail());
               tvNomor_Telepon.setText(model.get(position).getNomor_telepon());

                String urlgambar = new Configurasi().baseUrl()+"brand/gambar/"+ model.get(position).getFoto_profil();

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

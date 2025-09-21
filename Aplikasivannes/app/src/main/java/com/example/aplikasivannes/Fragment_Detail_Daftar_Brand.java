package com.example.aplikasivannes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.jetbrains.annotations.Nullable;

public class Fragment_Detail_Daftar_Brand extends Fragment {
    ImageView ivFoto_Profil, tombol_kembali;
    TextView tvNama_Brand, tvEmail, tvNomor_Telepon;
    Button btnEdit, btnRiwayat, btnAbout;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_daftar_brand, container, false);
            ivFoto_Profil = view.findViewById(R.id.ivFoto_Profil);
            tombol_kembali = view.findViewById(R.id.tombol_kembali);
            tvNama_Brand = view.findViewById(R.id.tvNama_Brand);
            tvEmail = view.findViewById(R.id.tvEmail);
            tvNomor_Telepon = view.findViewById(R.id.tvNomor_Telepon);

            String urlgambar = new Configurasi().baseUrl()+"brand/gambar/"+ Fragment_Daftar_Brand.foto_profil;

            Glide.with(getContext())
                    .load(urlgambar)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(ivFoto_Profil);

            tvNama_Brand.setText(Fragment_Daftar_Brand.nama_brand);
            tvEmail.setText(Fragment_Daftar_Brand.email);
            tvNomor_Telepon.setText(Fragment_Daftar_Brand.nomor_telepon);
        tombol_kembali.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.konten, new Fragment_Daftar_Brand());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        return view;
    }
    }

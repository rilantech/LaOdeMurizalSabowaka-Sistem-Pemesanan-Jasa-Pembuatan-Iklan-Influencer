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

public class Fragment_Detail_Daftar_Influencer extends Fragment {
    ImageView ivFoto_Profil, tombol_kembali;
    TextView tvNama_Influencer, tvEmail, tvNomor_Telepon, tvSosmed_Tiktok, tvPengikut_Sosmed_Tiktok, tvSosmed_Instagram, tvPengikut_Sosmed_Instagram, tvSosmed_Facebook, tvPengikut_Sosmed_Facebook;
    Button btnEdit, btnRiwayat, btnAbout;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_daftar_influencer, container, false);
            ivFoto_Profil = view.findViewById(R.id.ivFoto_Profil);
            tombol_kembali = view.findViewById(R.id.tombol_kembali);
            tvNama_Influencer = view.findViewById(R.id.tvNama_Influencer);
            tvEmail = view.findViewById(R.id.tvEmail);
            tvNomor_Telepon = view.findViewById(R.id.tvNomor_Telepon);
            tvSosmed_Tiktok = view.findViewById(R.id.tvSosmed_Tiktok);
            tvPengikut_Sosmed_Tiktok = view.findViewById(R.id.tvPengikut_Sosmed_Tiktok);
            tvSosmed_Instagram = view.findViewById(R.id.tvSosmed_Instagram);
            tvPengikut_Sosmed_Instagram = view.findViewById(R.id.tvPengikut_Sosmed_Instagram);
            tvSosmed_Facebook = view.findViewById(R.id.tvSosmed_Facebook);
            tvPengikut_Sosmed_Facebook = view.findViewById(R.id.tvPengikut_Sosmed_Facebook);

            String urlgambar = new Configurasi().baseUrl()+"influencer/gambar/"+ Fragment_Daftar_Influencer.foto_profil;

            Glide.with(getContext())
                    .load(urlgambar)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(ivFoto_Profil);

            tvNama_Influencer.setText(Fragment_Daftar_Influencer.nama_influencer);
            tvEmail.setText(Fragment_Daftar_Influencer.email);
            tvNomor_Telepon.setText(Fragment_Daftar_Influencer.nomor_telepon);
            tvSosmed_Tiktok.setText(Fragment_Daftar_Influencer.sosmed_tiktok);
            tvPengikut_Sosmed_Tiktok.setText("Follower : "+Fragment_Daftar_Influencer.pengikut_sosmed_tiktok);
            tvSosmed_Instagram.setText(Fragment_Daftar_Influencer.sosmed_instagram);
            tvPengikut_Sosmed_Instagram.setText("Follower : "+Fragment_Daftar_Influencer.pengikut_sosmed_instagram);
            tvSosmed_Facebook.setText(Fragment_Daftar_Influencer.sosmed_facebook);
            tvPengikut_Sosmed_Facebook.setText("Follower : "+Fragment_Daftar_Influencer.pengikut_sosmed_facebook);


        tombol_kembali.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.konten, new Fragment_Daftar_Influencer());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        return view;
    }
    }

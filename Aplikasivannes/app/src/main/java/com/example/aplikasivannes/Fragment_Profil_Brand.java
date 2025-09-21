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

public class Fragment_Profil_Brand extends Fragment {
    ImageView ivLogo_Brand;
    TextView tvNama_Brand, tvUsername, tvPassword, tvEmail, tvNomor_Telepon;
    Button btnEdit, btnRiwayat, btnAbout;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil_brand, container, false);
            ivLogo_Brand = view.findViewById(R.id.ivLogo_Brand);
            tvNama_Brand = view.findViewById(R.id.tvNama_Brand);
            tvUsername = view.findViewById(R.id.tvUsername);
            tvPassword = view.findViewById(R.id.tvPassword);
            tvEmail = view.findViewById(R.id.tvEmail);
            tvNomor_Telepon = view.findViewById(R.id.tvNomor_Telepon);
            btnEdit = view.findViewById(R.id.btnEdit);
            btnRiwayat = view.findViewById(R.id.btnRiwayat);
            btnAbout = view.findViewById(R.id.btnAbout);

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View view) {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.konten, new Fragment_Edit_Profil_Brand());
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });

            btnRiwayat.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View view) {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.konten, new Fragment_Riwayat_Transaksi_Brand());
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });

            btnAbout.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View view) {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.konten, new Fragment_About_Brand());
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });

            String urlgambar = new Configurasi().baseUrl()+"brand/gambar/"+ Login_Brand.foto_profil;

            Glide.with(getContext())
                    .load(urlgambar)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(ivLogo_Brand);
            tvNama_Brand.setText(Login_Brand.nama_brand);
            tvUsername.setText(Login_Brand.username);
            tvPassword.setText(Login_Brand.password);
            tvEmail.setText(Login_Brand.email);
            tvNomor_Telepon.setText(Login_Brand.nomor_telepon);

        return view;
    }
    }

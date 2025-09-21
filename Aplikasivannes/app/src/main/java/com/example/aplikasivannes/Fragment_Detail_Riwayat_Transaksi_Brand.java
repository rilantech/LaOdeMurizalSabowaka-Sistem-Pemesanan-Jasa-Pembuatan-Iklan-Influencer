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

public class Fragment_Detail_Riwayat_Transaksi_Brand extends Fragment {
    ImageView ivStruk_Pembayaran, tombol_kembali;
    TextView tvTanggal_Transaksi, tvNama_Project, tvNama_Influencer, tvJumlah_Transaksi;
    Button btnEdit, btnRiwayat, btnAbout;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_riwayat_transaksi_brand, container, false);
            ivStruk_Pembayaran = view.findViewById(R.id.ivStruk_Pembayaran);
            tombol_kembali = view.findViewById(R.id.tombol_kembali);
            tvTanggal_Transaksi = view.findViewById(R.id.tvTanggal_Transaksi);
            tvNama_Project = view.findViewById(R.id.tvNama_Project);
            tvNama_Influencer = view.findViewById(R.id.tvNama_Influencer);
            tvJumlah_Transaksi = view.findViewById(R.id.tvJumlah_Transaksi);


            String urlgambar = new Configurasi().baseUrl()+"brand/gambar/"+ Fragment_Riwayat_Transaksi_Brand.struk_pembayaran;

            Glide.with(getContext())
                    .load(urlgambar)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(ivStruk_Pembayaran);
            tvTanggal_Transaksi.setText(Fragment_Riwayat_Transaksi_Brand.tanggal_transaksi);
            tvNama_Project.setText(Fragment_Riwayat_Transaksi_Brand.nama_project);
            tvNama_Influencer.setText(Fragment_Riwayat_Transaksi_Brand.nama_influencer);
            tvJumlah_Transaksi.setText(Fragment_Riwayat_Transaksi_Brand.jumlah_transaksi);

        tombol_kembali.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.konten, new Fragment_Riwayat_Transaksi_Brand());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        return view;
    }
    }

package com.example.aplikasivannes.AdapterProjectBrand;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.aplikasivannes.Configurasi;
import com.example.aplikasivannes.Fragment_Detail_Project_Influencer;
import com.example.aplikasivannes.Fragment_Edit_Project_Brand;
import com.example.aplikasivannes.Fragment_Project_Influencer;
import com.example.aplikasivannes.R;

import java.util.ArrayList;
import java.util.List;

public class AdaptorProjectInfluencer extends RecyclerView.Adapter<AdaptorProjectInfluencer.MyViewHolder> {
    Context context;
    public List<GetProjectBrand> datalist;
    public static String id_project, logo_brand, nama_project, nama_brand, gaji_influencer, lama_kontrak, kriteria_project, kategori;
    public AdaptorProjectInfluencer(Context context, List<GetProjectBrand> datalist)
    {
        this.context = context;
        this.datalist = datalist;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_project_influencer, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        GetProjectBrand data = datalist.get(position);
        holder.tvTanggal_Posting.setText(data.getWaktu_posting());
        holder.tvNama_Project.setText(data.getNama_project());
        holder.tvNama_Brand.setText(data.getNama_brand());
        holder.tvlama_kontrak.setText(data.getLama_kontrak());
        holder.tvGaji_Influencer.setText(data.getGaji_influencer());


        String urlgambar = new Configurasi().baseUrl()+"brand/gambar/"+ data.getLogo_brand();

        Glide.with(context)
                .load(urlgambar)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.ivLogo_Brand);

        holder.cardviewproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id_project = String.valueOf(data.getId());
                logo_brand = data.getLogo_brand();
                nama_project = data.getNama_project();
                nama_brand = data.getNama_brand();
                kategori = data.getKategori();
                lama_kontrak = data.getLama_kontrak();
                gaji_influencer =data.getGaji_influencer();
                kriteria_project = data.getKriteria_project();


                FragmentTransaction fragmentTransaction =
                        ((AppCompatActivity)view.getContext()).getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.konten, new Fragment_Detail_Project_Influencer());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTanggal_Posting, tvNama_Project, tvNama_Brand,tvGaji_Influencer, tvlama_kontrak;
        public ImageView ivLogo_Brand;
        public CardView cardviewproject;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivLogo_Brand = itemView.findViewById(R.id.ivLogo_Brand);
            tvTanggal_Posting = itemView.findViewById(R.id.tvTanggal_Posting);
            tvNama_Project = itemView.findViewById(R.id.tvNama_Project);
            tvNama_Brand = itemView.findViewById(R.id.tvNama_Brand);
            tvGaji_Influencer = itemView.findViewById(R.id.tvGaji_Influencer);
            tvlama_kontrak = itemView.findViewById(R.id.tvlama_kontrak);
            cardviewproject = itemView.findViewById(R.id.cardviewproject);
        }
    }
}


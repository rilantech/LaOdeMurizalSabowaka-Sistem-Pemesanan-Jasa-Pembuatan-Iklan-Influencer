package com.example.aplikasivannes.AdapterRiwayatTransaksiBrand;

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
import com.example.aplikasivannes.Fragment_Detail_Progress_Brand;
import com.example.aplikasivannes.R;

import java.util.ArrayList;

public class AdaptorRiwayatTransaksiBrand extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<GetRiwayatTransaksiBrand> model;
    public static String id_progress, id_hapus_progress;
    public AdaptorRiwayatTransaksiBrand(Context context2, ArrayList<GetRiwayatTransaksiBrand> model2) {
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
        TextView tvTanggal_Transaksi, tvNama_Project, tvNama_Influencer, tvJumlah_Transaksi;
        ImageView tomboledit, ivStruk_Pembayaran;
        View view1 = this.inflater.inflate(R.layout.list_riwayat_transaksi_brand, (ViewGroup) null);
        if (view1 != null) {
            tvTanggal_Transaksi = view1.findViewById(R.id.tvTanggal_Transaksi);
            tvNama_Project =view1.findViewById(R.id.tvNama_Project);
            tvNama_Influencer =view1.findViewById(R.id.tvNama_Influencer);
            tvJumlah_Transaksi =view1.findViewById(R.id.tvJumlah_Transaksi);
            ivStruk_Pembayaran = view1.findViewById(R.id.ivStruk_Pembayaran);
            tomboledit = view1.findViewById(R.id.tomboledit);


               tvTanggal_Transaksi.setText(model.get(position).getWaktu_pembayaran());
               tvNama_Project.setText(model.get(position).getNama_project());
               tvNama_Influencer.setText(model.get(position).getNama_influencer());
               tvJumlah_Transaksi.setText(model.get(position).getGaji_influencer());

            String urlgambar = new Configurasi().baseUrl()+"brand/gambar/"+ model.get(position).getStruk_bukti_pembayaran();

            Glide.with(context)
                    .load(urlgambar)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(ivStruk_Pembayaran);

            tomboledit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    id_progress = String.valueOf(model.get(position).getId());
                    FragmentTransaction fragmentTransaction =
                            ((AppCompatActivity)view.getContext()).getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.konten, new Fragment_Detail_Progress_Brand());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });

        }
        return view1;
    }
}

package com.example.aplikasivannes.AdapterJadwalPosyandu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.aplikasivannes.R;

import java.util.ArrayList;

public class AdaptorJadwalPosyandu extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<GetJadwalPosyandu> model;

    public AdaptorJadwalPosyandu(Context context2, ArrayList<GetJadwalPosyandu> model2) {
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

    public View getView(int position, View view, ViewGroup viewGroup) {
        TextView waktu_post, agenda, tanggal, jam;
        @SuppressLint("ViewHolder") View view1 = this.inflater.inflate(R.layout.list_jadwal_posyandu, (ViewGroup) null);
        if (view1 != null) {
            waktu_post = view1.findViewById(R.id.waktu_post);
            agenda =view1.findViewById(R.id.agenda);
            tanggal =view1.findViewById(R.id.tanggal);
            jam =view1.findViewById(R.id.jam);
           waktu_post.setText(this.model.get(position).getWaktu_post());
           agenda.setText(this.model.get(position).getAgenda());
           tanggal.setText(this.model.get(position).getTanggal());
           jam.setText(this.model.get(position).getJam());
        }
        return view1;
    }
}

package com.example.aplikasivannes.AdaptorNotifikasi;

public class GetNotifikasiBrand {
    int id = 0;
    String id_project = "";
    String id_progress = "";
    String jenis_notif= "";
    String waktu_notif = "";
    String nama_brand = "";
    String nama_influencer = "";
    String isi_notif = "";

    public GetNotifikasiBrand(int id, String id_project, String id_progress, String jenis_notif, String waktu_notif,
                              String nama_brand, String nama_influencer, String isi_notif) {
        this.id = id;
        this.id_project = id_project;
        this.id_progress = id_progress;
        this.jenis_notif = jenis_notif ;
        this.waktu_notif = waktu_notif;
        this.nama_brand = nama_brand;
        this.nama_influencer = nama_influencer;
        this.isi_notif = isi_notif;

    }

    public int getId() {
        return id;
    }

    public String getId_project() {
        return id_project;
    }

    public String getId_progress() {
        return id_progress;
    }

    public String getJenis_notif() {
        return jenis_notif;
    }

    public String getWaktu_notif() {
        return waktu_notif;
    }

    public String getNama_brand() {
        return nama_brand;
    }

    public String getNama_influencer() {
        return nama_influencer;
    }

    public String getIsi_notif() {
        return isi_notif;
    }
}

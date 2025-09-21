package com.example.aplikasivannes.AdapterRiwayatTransaksiBrand;

public class GetRiwayatTransaksiBrand {
    int id = 0;
    String waktu_pengajuan = "";
    String waktu_posting = "";
    String waktu_pembayaran = "";
    String logo_brand = "";
    String nama_brand = "";
    String nama_project = "";
    String kategori = "";
    String kriteria_project = "";
    String gaji_influencer = "";
    String nama_influencer = "";
    String link_draft = "";
    String link_bukti_post = "";
    String revisi = "";
    String status_project = "";
    String nomor_rekening = "";
    String struk_bukti_pembayaran = "";


    public GetRiwayatTransaksiBrand(int id, String waktu_pengajuan, String waktu_posting, String waktu_pembayaran, String logo_brand,
                                    String nama_brand, String nama_project, String kategori, String kriteria_project, String gaji_influencer, String nama_influencer,
                                    String link_draft, String link_bukti_post, String revisi, String status_project, String nomor_rekening, String struk_bukti_pembayaran) {
        this.waktu_pengajuan = waktu_pengajuan;
        this.waktu_posting = waktu_posting;
        this.waktu_pembayaran = waktu_pembayaran;
        this.logo_brand= logo_brand;
        this.nama_brand = nama_brand;
        this.nama_project = nama_project;
        this.kategori = kategori;
        this.kriteria_project = kriteria_project;
        this.gaji_influencer = gaji_influencer;
        this.nama_influencer = nama_influencer;
        this.link_draft = link_draft;
        this.link_bukti_post = link_bukti_post;
        this.revisi = revisi;
        this.status_project = status_project;
        this.nomor_rekening = nomor_rekening;
        this.struk_bukti_pembayaran = struk_bukti_pembayaran;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getWaktu_pengajuan() {
        return waktu_pengajuan;
    }

    public String getWaktu_posting() {
        return waktu_posting;
    }

    public String getWaktu_pembayaran() {
        return waktu_pembayaran;
    }

    public String getLogo_brand() {
        return logo_brand;
    }

    public String getNama_brand() {
        return nama_brand;
    }

    public String getNama_project() {
        return nama_project;
    }

    public String getKategori() {
        return kategori;
    }

    public String getKriteria_project() {
        return kriteria_project;
    }

    public String getGaji_influencer() {
        return gaji_influencer;
    }

    public String getNama_influencer() {
        return nama_influencer;
    }

    public String getLink_draft() {
        return link_draft;
    }

    public String getLink_bukti_post() {
        return link_bukti_post;
    }

    public String getRevisi() {
        return revisi;
    }

    public String getStatus_project() {
        return status_project;
    }

    public String getNomor_rekening() {
        return nomor_rekening;
    }

    public String getStruk_bukti_pembayaran() {
        return struk_bukti_pembayaran;
    }
}

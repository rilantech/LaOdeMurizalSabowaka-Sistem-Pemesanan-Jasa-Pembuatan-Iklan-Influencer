package com.example.aplikasivannes.AdapterProjectBrand;

public class GetProjectBrand {
    int id = 0;
    String waktu_posting = "";
    String logo_brand = "";
    String nama_brand = "";
    String nama_project = "";
    String kategori = "";
    String lama_kontrak = "";
    String kriteria_project = "";
    String gaji_influencer = "";

    public GetProjectBrand(int id, String waktu_posting, String logo_brand,
                           String nama_brand, String nama_project, String kategori, String lama_kontrak, String kriteria_project, String gaji_influencer) {
        this.waktu_posting = waktu_posting;
        this.logo_brand= logo_brand;
        this.nama_brand = nama_brand;
        this.nama_project = nama_project;
        this.kategori = kategori;
        this.lama_kontrak = lama_kontrak;
        this.kriteria_project = kriteria_project;
        this.gaji_influencer = gaji_influencer;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getWaktu_posting() {
        return waktu_posting;
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

    public String getLama_kontrak() {
        return lama_kontrak;
    }

    public String getKriteria_project() {
        return kriteria_project;
    }

    public String getGaji_influencer() {
        return gaji_influencer;
    }
}

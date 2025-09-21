package com.example.aplikasivannes.AdapterDaftarBrand;

public class GetDaftarBrand {
    int id = 0;
    String nama_brand = "";
    String foto_profil = "";
    String email = "";
    String nomor_telepon = "";

    public GetDaftarBrand(int id, String nama_brand, String foto_profil, String email, String nomor_telepon) {
        this.nama_brand = nama_brand;
        this.foto_profil = foto_profil;
        this.email = email;
        this.nomor_telepon = nomor_telepon;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNama_brand() {
        return nama_brand;
    }

    public String getFoto_profil() {
        return foto_profil;
    }

    public String getEmail() {
        return email;
    }

    public String getNomor_telepon() {
        return nomor_telepon;
    }
}

package com.example.aplikasivannes.AdapterDaftarInfluencer;

public class GetDaftarInfluencer {
    int id = 0;
    String nama_influencer = "";
    String foto_profil = "";
    String email = "";
    String nomor_telepon = "";
    String sosmed_tiktok ="";
    String pengikut_sosmed_tiktok="";
    String sosmed_instagram ="";
    String pengikut_sosmed_instagram="";
    String sosmed_facebook ="";
    String pengikut_sosmed_facebook="";

    public GetDaftarInfluencer(int id, String nama_influencer, String foto_profil, String email, String nomor_telepon,
    String sosmed_tiktok, String pengikut_sosmed_tiktok, String sosmed_instagram, String pengikut_sosmed_instagram,
                               String sosmed_facebook, String pengikut_sosmed_facebook) {
        this.nama_influencer = nama_influencer;
        this.foto_profil = foto_profil;
        this.email = email;
        this.nomor_telepon = nomor_telepon;
        this.sosmed_tiktok = sosmed_tiktok;
        this.pengikut_sosmed_tiktok = pengikut_sosmed_tiktok;
        this.sosmed_instagram = sosmed_instagram;
        this.pengikut_sosmed_instagram = pengikut_sosmed_instagram;
        this.sosmed_facebook = sosmed_facebook;
        this.pengikut_sosmed_facebook = pengikut_sosmed_facebook;
        this.id = id;
    }

    public int getId() {
        return id;
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

    public String getNama_influencer() {
        return nama_influencer;
    }

    public String getSosmed_tiktok() {
        return sosmed_tiktok;
    }

    public String getPengikut_sosmed_tiktok() {
        return pengikut_sosmed_tiktok;
    }

    public String getSosmed_instagram() {
        return sosmed_instagram;
    }

    public String getPengikut_sosmed_instagram() {
        return pengikut_sosmed_instagram;
    }

    public String getSosmed_facebook() {
        return sosmed_facebook;
    }

    public String getPengikut_sosmed_facebook() {
        return pengikut_sosmed_facebook;
    }
}

package com.example.aplikasivannes.AdapterJadwalPosyandu;

public class GetJadwalPosyandu {
    String agenda = "";
    int id = 0;
    String jam = "";
    String tanggal = "";
    String waktu_post = "";

    public GetJadwalPosyandu(int id2, String waktu_post2, String agenda2, String tanggal2, String jam2) {
        this.waktu_post = waktu_post2;
        this.agenda = agenda2;
        this.tanggal = tanggal2;
        this.jam = jam2;
        this.id = id2;
    }

    public String getWaktu_post() {
        return this.waktu_post;
    }

    public String getAgenda() {
        return this.agenda;
    }

    public String getTanggal() {
        return this.tanggal;
    }

    public String getJam() {
        return this.jam;
    }

    public Integer getId() {
        return Integer.valueOf(this.id);
    }
}

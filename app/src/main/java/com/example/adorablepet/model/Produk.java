package com.example.adorablepet.model;

public class Produk {
    private int id;
    public String linkFoto;
    public String nama;
    public String usageFor;
    public String description;

    public Produk() {
        //NOT IMPLEMENTED
    }

    public Produk(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }
}

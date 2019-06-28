package com.fa.ecommerce.Model;

public class Users {
    private String nama, telepon, password,username,alamat,email,confpas;

    public Users(){

    }

    public Users(String nama, String telepon, String password, String username, String alamat, String email, String confpas) {
        this.nama = nama;
        this.telepon = telepon;
        this.password = password;
        this.username = username;
        this.alamat = alamat;
        this.email = email;
        this.confpas = confpas;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfpas() {
        return confpas;
    }

    public void setConfpas(String confpas) {
        this.confpas = confpas;
    }
}

package com.example;

/**
 * Created by Alen on 20.4.2016.
 */
public class Izvajalci {
    int gostujejo;
    String naziv;
    int idIzv;
    public Izvajalci(){
        gostujejo = 0;naziv = "";
        idIzv =0;
    }
    public Izvajalci(int gosti,String naziv,int komadi) {
        this.gostujejo = gosti; this.naziv = naziv; this.idIzv =komadi;
    }

    public int getIdIzv() {
        return idIzv;
    }

    public void setIdIzv(int idIzv) {
        this.idIzv = idIzv;
    }

    public int getGostujejo(){return gostujejo;}

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return "Izvajalci{" +
                "naziv='" + naziv + '\'' +
                '}';
    }



}

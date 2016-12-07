package com.example.aplikacija.organization;

/**
 * Created by Alen on 15.5.2016.
 */
public class Udelezbe {
    private String ime;
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Udelezbe(String ime,int i) {
        this.ime = ime;
        this.ID=i;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

}

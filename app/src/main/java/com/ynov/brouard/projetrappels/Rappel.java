package com.ynov.brouard.projetrappels;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

@IgnoreExtraProperties
public class Rappel {
    public String titre;
    public String contenu;
    public String date;
    public String heure;
    //public Boolean notification;

    public Rappel() {
    }

    public Rappel(String titre, String contenu, String date, String heure) {
        this.titre = titre;
        this.contenu = contenu;
        this.date = date;
        this.heure = heure;
    }

    public String toString() {
        return this.titre + "\n" + date + " à " + heure+"\n"+"\n"+contenu;
    }
}

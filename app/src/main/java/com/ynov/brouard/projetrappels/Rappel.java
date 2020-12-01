package com.ynov.brouard.projetrappels;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

@IgnoreExtraProperties
public class Rappel {
    public String titre;
    public String contenu;

    public Rappel() {}

    public Rappel(String titre, String contenu) {
        this.titre = titre;
        this.contenu = contenu;
    }
}

package com.ynov.brouard.projetrappels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class creerRappel extends AppCompatActivity {

    private DatabaseReference mDatabase;
    String Stitre;
    String Scontenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_rappel);

        EditText Etitre = findViewById(R.id.editTitle);
        EditText Econtenu = findViewById(R.id.editContent);
        FloatingActionButton Bvalidation = findViewById(R.id.validerButton);


        mDatabase = FirebaseDatabase.getInstance().getReference();

        Bvalidation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Stitre = Etitre.getText().toString();
                Scontenu = Econtenu.getText().toString();
                ecrireRappel(Stitre,Scontenu);
                Bvalidation.setVisibility(View.INVISIBLE);
            }
        });
    }
    private void ecrireRappel(String titre, String contenu) {

        Rappel rappel = new Rappel(titre,contenu);
        //ECRIRE

        mDatabase.child("rappels").child(titre).setValue(rappel);
    }
    /*private void modifierRappel(String Id) {
        mDatabase.child("rappels").child(Id).child("contenu").setValue("valeur à modifier");
    }*/

}
package com.ynov.brouard.projetrappels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class creerRappel extends AppCompatActivity {

    DatabaseReference mDatabase;
    String Stitre;
    String Scontenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_creer_rappel);

        EditText Etitre = findViewById(R.id.editTitle);
        EditText Econtenu = findViewById(R.id.editContent);
        FloatingActionButton Bvalidation = findViewById(R.id.validerButton);
        TextView Tvalide = findViewById(R.id.txtEnvoye);
        TextView TverifChamps = findViewById(R.id.verifChamps);

        Tvalide.setVisibility(View.INVISIBLE);
        TverifChamps.setVisibility(View.INVISIBLE);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Bvalidation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Stitre = Etitre.getText().toString();
                Scontenu = Econtenu.getText().toString();

                if (Stitre.equals("") || Scontenu.equals("")) {
                    TverifChamps.setVisibility(View.VISIBLE);
                }
                else {
                    ecrireRappel(Stitre,Scontenu);
                    TverifChamps.setVisibility(View.INVISIBLE);
                    Bvalidation.setVisibility(View.INVISIBLE);
                    Tvalide.setVisibility(View.VISIBLE);
                }
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


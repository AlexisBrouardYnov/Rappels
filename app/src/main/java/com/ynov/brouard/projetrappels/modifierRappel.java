package com.ynov.brouard.projetrappels;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class modifierRappel extends AppCompatActivity {

    DatabaseReference mDatabase;

    String SmodifierTitre;
    String SmodifierContenu;
    String SmodifierDate;
    String SmodifierHeure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_rappel);

        EditText Id = findViewById(R.id.EmodifierTitre);
        EditText contenu = findViewById(R.id.EmodifContenu);
        EditText modifDate = findViewById(R.id.EmodifDate);
        EditText modifHeure = findViewById(R.id.EmodifHeure);
        TextView TverifChampsmodifier = findViewById(R.id.verifChampsmodifier);
        TextView TtxtModifier = findViewById(R.id.txtmodifier);

        TverifChampsmodifier.setVisibility(View.INVISIBLE);

        Button validerTitre = findViewById(R.id.validerTitre);
        FloatingActionButton validerModif = findViewById(R.id.validerModif);


        validerModif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmodifierContenu = contenu.getText().toString();
                SmodifierDate = modifDate.getText().toString();
                SmodifierHeure = modifHeure.getText().toString();
                if (SmodifierTitre.equals("") || SmodifierContenu.equals("") || SmodifierDate.equals("") || SmodifierHeure.equals("")) {
                    TverifChampsmodifier.setVisibility(View.VISIBLE);
                }
                else {
                    TverifChampsmodifier.setVisibility(View.INVISIBLE);
                    validerModif.setVisibility(View.INVISIBLE);
                    TtxtModifier.setVisibility(View.VISIBLE);
                    modifierRappel();
                }
            }
        });

        validerTitre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmodifierTitre = Id.getText().toString();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("rappels/"+SmodifierTitre);
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Rappel rappel = snapshot.getValue(Rappel.class);
                        SmodifierContenu = rappel.contenu;
                        SmodifierDate = rappel.date;
                        SmodifierHeure = rappel.heure;

                        contenu.setText(SmodifierContenu);
                        modifDate.setText(SmodifierDate);
                        modifHeure.setText(SmodifierHeure);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

            }
        });


    }
    private void modifierRappel() {
        mDatabase = FirebaseDatabase.getInstance().getReference("rappels");
        mDatabase.child(SmodifierTitre).child("contenu").setValue(SmodifierContenu);
        mDatabase.child(SmodifierTitre).child("date").setValue(SmodifierDate);
        mDatabase.child(SmodifierTitre).child("heure").setValue(SmodifierHeure);
    }
}
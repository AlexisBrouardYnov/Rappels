package com.ynov.brouard.projetrappels;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class modifierRappel extends AppCompatActivity {

    DatabaseReference mDatabase;

    String SmodifierTitre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_rappel);

        EditText Id = findViewById(R.id.EmodifierTitre);

        SmodifierTitre = Id.getText().toString();

        modifierRappel(SmodifierTitre);


    }
    private void modifierRappel(String Id) {
        mDatabase = FirebaseDatabase.getInstance().getReference("rappels");
        mDatabase.child("rappels").child(Id).child("contenu").setValue("valeur à modifier");
    }
}
package com.ynov.brouard.projetrappels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Date;

public class creerRappel extends AppCompatActivity {

    DatabaseReference mDatabase;
    String Stitre;
    String Scontenu;
    String Sdate;
    String Sheure;
    Boolean notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_creer_rappel);

        EditText Etitre = findViewById(R.id.editTitle);
        EditText Econtenu = findViewById(R.id.editContent);
        EditText Edate = findViewById(R.id.editDate);
        EditText Eheure = findViewById(R.id.editHeure);
        //Switch Snotification = findViewById(R.id.notification);
        FloatingActionButton Bvalidation = findViewById(R.id.validerButton);
        TextView Tvalide = findViewById(R.id.txtEnvoye);
        TextView TverifChamps = findViewById(R.id.verifChamps);

        Tvalide.setVisibility(View.INVISIBLE);
        TverifChamps.setVisibility(View.INVISIBLE);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Validation de l'écriture
        Bvalidation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Stitre = Etitre.getText().toString();
                Scontenu = Econtenu.getText().toString();
                Sdate = Edate.getText().toString();
                Sheure = Eheure.getText().toString();
                //notification = Snotification.isChecked();

                //Vérification de champs vides
                if (Stitre.equals("") || Scontenu.equals("") || Sdate.equals("") || Sheure.equals("")) {
                    TverifChamps.setVisibility(View.VISIBLE);
                }
                else {
                    //Fonction ecriture des rappels
                    ecrireRappel(Stitre,Scontenu,Sdate,Sheure);
                    TverifChamps.setVisibility(View.INVISIBLE);
                    Bvalidation.setVisibility(View.INVISIBLE);
                    Tvalide.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    private void ecrireRappel(String titre, String contenu,String date,String heure) {
        Rappel rappel = new Rappel(titre,contenu,date,heure);

        //Ecriture du rappel

        mDatabase.child("rappels").child(titre).setValue(rappel);

        //Ajout de notifications si le switch est activé

        /*if(notification==true){

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"rappels")
                    .setSmallIcon(R.drawable.ic_baseline_article_24)
                    .setContentTitle(titre)
                    .setContentText(contenu)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        }*/
    }
}


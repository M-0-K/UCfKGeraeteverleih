package com.example.ucgeraeteverleih;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class MainActivity extends AppCompatActivity {

    private Button btVerbinden;
    private TextView tvStatus;
    private EditText ptUSR;
    private EditText ptIpadress;
    private EditText pPassword;
    private DB db;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Prüfung ob zugriff auf die Kamera erlaubt ist
            //wenn nicht wird Zugriffsanfrage gestellt
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.CAMERA }, 100);
        }

        btVerbinden = (Button) findViewById(R.id.btVerbinden);
        tvStatus = (TextView) findViewById(R.id.tvStatus);
        ptUSR = (EditText) findViewById(R.id.ptUSR);
        ptIpadress = (EditText) findViewById(R.id.ptIpadress);
        pPassword = (EditText) findViewById(R.id.pPassword);

        //Lädt schon einmal eingegebene Logindaten aus dem Appspeicher
        pref = getSharedPreferences("dblogin", 0);
        ptIpadress.setText(pref.getString("ip","IP-Adresse"));
        ptUSR.setText(pref.getString("usr","User"));
        pPassword.setText(pref.getString("password",""));

        btVerbinden.setOnClickListener(new View.OnClickListener(){
            //Wird ausgeführt wenn der Button verbinden betätigt wird
            public void onClick(View v){

                //Speichert neue Logindaten in den Appspeicher
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.putString("ip", ptIpadress.getText().toString().trim());
                editor.putString("usr", ptUSR.getText().toString().trim());
                editor.putString("password", pPassword.getText().toString().trim());
                editor.commit();

                System.out.println(pref.getString("ip","Error"));
                System.out.println(pref.getString("usr","Error"));
                System.out.println(pref.getString("password","Error"));

                //Erstellt DB Objekt anhand der Logindaten im Appspeicher
                db = new DB(pref.getString("ip","Error"),pref.getString("usr","Error"),pref.getString("password","Error"));

                //Prüft verbindung, wenn verbunden, weiterleitung auf Activity Kundewaehlen
                if(db.verbinden()){
                    tvStatus.setText("Verbunden");
                    Intent gotoKW = new Intent(MainActivity.this, Kundewaehlen.class);
                    startActivity(gotoKW);
                }else {
                    tvStatus.setText("Fehler");
                }

            }

        });

    }
}
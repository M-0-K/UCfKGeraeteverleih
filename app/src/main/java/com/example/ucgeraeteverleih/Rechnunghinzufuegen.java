package com.example.ucgeraeteverleih;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class Rechnunghinzufuegen extends AppCompatActivity {

    private Button btLoeschen;
    private Button btSpeichern;
    private TextView tvKunde;
    private TextView tvPreis;
    private EditText etdAbgabe;
    private EditText etdRueckgabe;
    private ListView lvGeraete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechnunghinzufuegen);

        btLoeschen = (Button) findViewById(R.id.btLoeschen);
        btSpeichern = (Button) findViewById(R.id.btSpeichern);
        tvKunde = (TextView) findViewById(R.id.tvKunde);
        tvPreis = (TextView) findViewById(R.id.tvPreis);
        etdAbgabe = (EditText) findViewById(R.id.etdAbgabe);
        etdRueckgabe = (EditText) findViewById(R.id.etdRueckgabe);
        lvGeraete = (ListView) findViewById(R.id.lvGeraet);

        //Kunde k = Kundewaehlen.getKunde();

    }
}
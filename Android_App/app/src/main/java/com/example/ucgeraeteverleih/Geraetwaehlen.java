package com.example.ucgeraeteverleih;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Geraetwaehlen extends AppCompatActivity {

    private final ArrayList<Geraet> g = new ArrayList<Geraet>();
    private CodeScanner mCodeScanner;
    private ListView lvGeraet;
    private Button btWeiter;
    private Kunde k;
    private DB db;
    private SharedPreferences pref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geraetwaehlen);

        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);
        lvGeraet = (ListView) findViewById(R.id.lvGeraet);
        btWeiter = (Button) findViewById(R.id.btWeiter);

        //Erstellt DB Objekt anhand der Logindaten im Appspeicher
        pref = getSharedPreferences("dblogin", 0);
        db = new DB(pref.getString("ip","Error"),pref.getString("usr","Error"),pref.getString("password","Error"));
        //Lädt Kunden anhand der Übergebenen KundenID
        db.ladeKunde(Integer.parseInt(getIntent().getStringExtra("kunde")));


        scannerView.setOnClickListener(new View.OnClickListener() {
            //Wird ausgeführt, wenn man auf den CodeScanner klickt
            @Override
            public void onClick(View v) {
                mCodeScanner.startPreview(); //Startet das Scannen mit dem Codescanner
            }
        });


        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            //Wird ausgeführt, wenn der CodeScanner einen Code scant
            @Override
            public void onDecoded(@NonNull @NotNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        boolean flag = false;
                        //Gescannter Code wird in Variable gespeichert und mögliche Nullen am anfang der Codes werden entfernt
                        String code = result.getText();
                        while (code.charAt(0) == '0'){
                            code = code.substring(1);
                        }
                        //Gerät wird anhand er Gescannten GeraeteID aus der DB geladen
                        Geraet gneu = db.ladeGeraet(Integer.parseInt(code));

                        //Es Wird geprüft ob Geraet schon gescannt wurde
                        for(int i = 0;i < g.size(); i++){
                            if(g.get(i).getG_id() == gneu.getG_id()){
                                flag = true;
                            }
                        }
                        if(flag){
                            Toast.makeText(Geraetwaehlen.this, "Dieses Gerät wurde schon gescannt!", Toast.LENGTH_LONG).show();
                        }else if(gneu.getZustand().equalsIgnoreCase("defekt")){
                            Toast.makeText(Geraetwaehlen.this, "Dieses Gerät ist defekt!", Toast.LENGTH_LONG).show();
                        }else{
                            //Gereat wird der Geraete Liste hinzugefügt und die Listview wird aktualisiert
                            g.add(gneu);
                            lvGeraet.setAdapter(new GeraetListAdapter());
                            }

                    }
                });
            }
        });

        btWeiter.setOnClickListener(new View.OnClickListener() {
            //Wird ausgeführt wenn Button Weiter geklickt wird
            @Override
            public void onClick(View view) {
                if(g.size() != 0){// Wenn mehr als null Geraete gescannt sind
                    //Weiterleitung auf die Activity Rechnunghinzufuegen, mit überegabe der KundenID und GeraeteIDs
                    Intent gotoRH = new Intent(Geraetwaehlen.this, Rechnunghinzufuegen.class);
                    gotoRH.putExtra("kunde", getIntent().getStringExtra("kunde"));
                    String sg = "";
                    for(int i = 0; i < g.size(); i++){
                        sg = g.get(i).getG_id() + ";" + sg;
                    }
                    gotoRH.putExtra("Geraete", sg);
                    startActivity(gotoRH);
                }else{
                    Toast.makeText(Geraetwaehlen.this, "Scannen sie ein Gerät!", Toast.LENGTH_LONG).show();
                }
            }
        });

        lvGeraet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //Wird ausgeführt ein Eintrag in der Listview geklickt wird
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //entfernt geklicktes Geraet von der Liste und aktualisiert ListView
                g.remove(position);
                lvGeraet.setAdapter(new GeraetListAdapter());
            }
        });
    }
    public class GeraetListAdapter extends ArrayAdapter<Geraet> { //ist zuständig, das die ListView mit Daten gefüllt wird und dem Design der listview.xml entspricht

        public GeraetListAdapter() {
            super(Geraetwaehlen.this, R.layout.listview, g);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent ) {
            // Make sure we have a view to work with (may have been given null)
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.listview, parent, false);
            }
            Geraet currentGeraet = g.get(position);

            TextView tvName = (TextView) itemView.findViewById(R.id.tvMain);
            TextView tvAdresse = (TextView) itemView.findViewById(R.id.tvFusszeile);

            tvName.setText(currentGeraet.getG_id() +". " + currentGeraet.getBezeichnung());
            tvAdresse.setText(currentGeraet.getZustand());

            return itemView;
        }
    }

}
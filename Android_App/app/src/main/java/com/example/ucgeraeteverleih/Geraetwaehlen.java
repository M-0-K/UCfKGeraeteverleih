package com.example.ucgeraeteverleih;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Geraetwaehlen extends AppCompatActivity {

    private ArrayList<Geraet> g = new ArrayList<Geraet>();
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

        pref = getSharedPreferences("dblogin", 0);
        db = new DB(pref.getString("ip","Error"),pref.getString("usr","Error"),pref.getString("password","Error"));

        db.ladeKunde(Integer.parseInt(getIntent().getStringExtra("kunde")));

        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCodeScanner.startPreview();
            }
        });

        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull @NotNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        boolean flag = false;
                        Geraet gneu = db.ladeGeraet(Integer.parseInt(result.getText()));
                        for(int i = 0;i < g.size(); i++){
                            if(g.get(i).getG_id() == gneu.getG_id()){
                                flag = true;
                            }
                        }
                        if(flag){
                            Toast.makeText(Geraetwaehlen.this, "Dieses Gerät wurde schon gescannt!", Toast.LENGTH_LONG).show();
                        }else{
                            g.add(gneu);
                            lvGeraet.setAdapter(new GeraetListAdapter());
                        }

                    }
                });
            }
        });

        btWeiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(g.size() != 0){
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

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                g.remove(position);
                lvGeraet.setAdapter(new GeraetListAdapter());
            }
        });
    }
    public class GeraetListAdapter extends ArrayAdapter<Geraet> {

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
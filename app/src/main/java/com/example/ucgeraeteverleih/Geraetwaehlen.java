package com.example.ucgeraeteverleih;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geraetwaehlen);

        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);
        lvGeraet = (ListView) findViewById(R.id.lvGeraet);
        btWeiter = (Button) findViewById(R.id.btWeiter);

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
                        DB db = new DB();
                        g.add(db.ladeGeraet(Integer.parseInt(result.getText())));
                        lvGeraet.setAdapter(new GeraetListAdapter());
                    }
                });
            }
        });

        btWeiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(g.size() != 0){
                    Intent gotoRH = new Intent(Geraetwaehlen.this, Rechnunghinzufuegen.class);
                    startActivity(gotoRH);
                }else{
                    Toast.makeText(Geraetwaehlen.this, "Scannen sie ein Gerät!", Toast.LENGTH_LONG).show();
                }
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
            Geraet currentKunde = g.get(position);

            TextView tvName = (TextView) itemView.findViewById(R.id.tvName);
            TextView tvAdresse = (TextView) itemView.findViewById(R.id.tvAdresse);

            tvName.setText(currentKunde.getG_id() +"." + currentKunde.getBezeichnung());
            tvAdresse.setText(currentKunde.getProduktgruppe() +" " + currentKunde.getZustand());

            return itemView;
        }
    }

    public ArrayList<Geraet> getGeraete(){
        return g;
    }

}
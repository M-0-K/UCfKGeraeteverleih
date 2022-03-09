package com.example.ucgeraeteverleih;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Kundewaehlen extends AppCompatActivity {


    protected  ArrayList<Kunde> k = new ArrayList<Kunde>();
    private Kunde kunde = null;
    private EditText ptSuche;
    private ListView lvKunden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kundewaehlen);

        ptSuche = (EditText) findViewById(R.id.ptSuche);
        lvKunden = (ListView) findViewById(R.id.lvKunden);

        DB db = new DB();
        k = db.ladeKunden("WHERE Name != '' and Vorname != ''");
        lvKunden.setAdapter(new KundenListAdapter());

        lvKunden.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                kunde = k.get(position);
                Intent gotoGW = new Intent(Kundewaehlen.this, Geraetwaehlen.class);
                System.out.println(kunde.getK_id());
                gotoGW.putExtra("kunde", kunde.getK_id()+"");
                startActivity(gotoGW);
            }
        });

    }

    public class KundenListAdapter extends ArrayAdapter<Kunde> {

        public KundenListAdapter() {
            super(Kundewaehlen.this, R.layout.listview, k);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent ) {
            // Make sure we have a view to work with (may have been given null)
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.listview, parent, false);
            }
            Kunde currentKunde = k.get(position);

            TextView tvName = (TextView) itemView.findViewById(R.id.tvMain);
            TextView tvAdresse = (TextView) itemView.findViewById(R.id.tvFusszeile);

            tvName.setText(currentKunde.getK_id() +". " + currentKunde.getVorname()+ " " + currentKunde.getName());
            tvAdresse.setText(currentKunde.getStrasse()+" "+ currentKunde.getHausnummer()+" "  + currentKunde.getPlz()+ " "+ currentKunde.getOrt());

            return itemView;
        }
    }

}




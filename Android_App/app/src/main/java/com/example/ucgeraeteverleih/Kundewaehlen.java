package com.example.ucgeraeteverleih;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Kundewaehlen extends AppCompatActivity {


    protected  ArrayList<Kunde> k = new ArrayList<Kunde>();
    private Kunde kunde = null;
    private ListView lvKunden;
    private DB db;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kundewaehlen);

        lvKunden = (ListView) findViewById(R.id.lvKunden);

        //Erstellt DB Objekt anhand der Logindaten im Appspeicher
        pref = getSharedPreferences("dblogin", 0);
        db = new DB(pref.getString("ip","Error"),pref.getString("usr","Error"),pref.getString("password","Error"));

        k = db.ladeKunden("WHERE Name != '' and Vorname != ''");
        lvKunden.setAdapter(new KundenListAdapter());


        lvKunden.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // Wird ausgeführt, wenn ein Eintrag in der Listview geklickt wird
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                kunde = k.get(position);
                //Weiterleitung auf die Activity Geraetwaehlen, mit überegabe der KundenID
                Intent gotoGW = new Intent(Kundewaehlen.this, Geraetwaehlen.class);
                System.out.println(kunde.getK_id());
                gotoGW.putExtra("kunde", kunde.getK_id()+"");
                startActivity(gotoGW);
            }
        });


    }

    public class KundenListAdapter extends ArrayAdapter<Kunde> { //ist zuständig, das die ListView mit Daten gefüllt wird und dem Design der listview.xml entspricht

        public KundenListAdapter() {
            super(Kundewaehlen.this, R.layout.listview, k);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent ) {

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




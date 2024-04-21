package com.example.ucgeraeteverleih;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class Rechnunghinzufuegen extends AppCompatActivity {

    private Button btLoeschen, btSpeichern, btAbgabe,btRueckgabe;
    private TextView tvKunde, tvAbgabe, tvRueckgabe;
    private ListView lvGeraete;

    private final ArrayList<Geraet> g = new ArrayList<Geraet>();
    private Kunde k;
    private DB db;
    private SharedPreferences pref;
    private LocalDate abgabe = null;
    private LocalDate rueckgabe = null;
    private Rechnung r =null;
    private final Calendar calander = Calendar.getInstance();
    private DatePickerDialog datePickerDialog;
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("d.M.yyyy");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechnunghinzufuegen);

        btLoeschen = (Button) findViewById(R.id.btLoeschen);
        btSpeichern = (Button) findViewById(R.id.btSpeichern);
        btAbgabe = (Button) findViewById(R.id.btAbgabe);
        btRueckgabe = (Button) findViewById(R.id.btRueckgabe);
        tvKunde = (TextView) findViewById(R.id.tvKunde);
        lvGeraete = (ListView) findViewById(R.id.lvGeraete);
        tvAbgabe = (TextView) findViewById(R.id.tvAbgabe);
        tvRueckgabe = (TextView) findViewById(R.id.tvRueckgabe);

        //Erstellt DB Objekt anhand der Logindaten im Appspeicher
        pref = getSharedPreferences("dblogin", 0);
        db = new DB(pref.getString("ip","Error"),pref.getString("usr","Error"),pref.getString("password","Error"));

        //Lädt Kunden anhand der Übergebenen KundenID und trägt Kundendaten ins Textfeld ein
        k = db.ladeKunde(Integer.parseInt(getIntent().getStringExtra("kunde")));
        tvKunde.setText(k.getK_id()+ ". " + k.getVorname() + " " + k.getName() +"\n"
                + k.getStrasse() + " "+ k.getHausnummer() + "\n"
                + k.getPlz() + " " + k.getOrt()
         );

        //Lädt Geraete Liste anhand der Übergebenen GeraeteIDs
        String[] gs = (getIntent().getStringExtra("Geraete")).split(";");
        for(int i = 0; i < gs.length; i++){
            g.add(db.ladeGeraet(Integer.parseInt(gs[i])));
        }
        //Lädt Gerate in ListView
        lvGeraete.setAdapter(new RechnungListAdapter());

        btAbgabe.setOnClickListener(new View.OnClickListener() {
            //Wird ausgefuehrt wenn Button für die Eingabe des Abgabedatums geklickt wird
            @Override
            public void onClick(View view) {
                //Oeffnet datePickerDialog und nach eingabe des Datums wird dies in TextView geladen und Variable gespeichert
                datePickerDialog = new DatePickerDialog(Rechnunghinzufuegen.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int i, int i1, int i2) {
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("d.M.yyyy");
                        abgabe = LocalDate.parse(i2+"."+i1+"."+i, format);
                        tvAbgabe.setText(abgabe.format(format));
                    }
                }, calander.get(Calendar.YEAR), calander.get(Calendar.MONTH), calander.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        btRueckgabe.setOnClickListener(new View.OnClickListener() {
            //Wird ausgeführt wenn Button für die Eingabe des Rueckgabedatums geklickt wird
            @Override
            public void onClick(View view) {
                //Oeffnet datePickerDialog und nach eingabe des Datums wird dies in TextView geladen und Variable gespeichert
                datePickerDialog = new DatePickerDialog(Rechnunghinzufuegen.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int i, int i1, int i2) {
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("d.M.yyyy");
                        rueckgabe = LocalDate.parse(i2+"."+i1+"."+i, format);
                        tvRueckgabe.setText(rueckgabe.format(format));
                    }
                }, calander.get(Calendar.YEAR), calander.get(Calendar.MONTH), calander.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        btSpeichern.setOnClickListener(new View.OnClickListener() {
            //wird ausgeführt wenn der button speichern betaetigt wird
            @Override
            public void onClick(View view) {
                if(abgabe != null && rueckgabe != null){ //prueft ob Daten eingetragen wurden
                    //erstelle Liste mit Mietvertraegen um Kunden zu erstellen um Kunden speichern zu können
                    ArrayList<Mietvertrag> m = new ArrayList<Mietvertrag>();
                    for(int i = 0; i < g.size(); i++){
                        m.add(new Mietvertrag(g.get(i), k, abgabe, rueckgabe, false));
                    }
                    r = new Rechnung(m, LocalDate.now(), false, m.get(0).getKunde().getName(),
                            m.get(0).getKunde().getVorname(), m.get(0).getKunde().getStrasse(),
                            m.get(0).getKunde().getHausnummer(), m.get(0).getKunde().getPlz(),
                            m.get(0).getKunde().getOrt(), m.get(0).getKunde().getMitglied());
                    db.speicherRechnung(r);
                }else {
                    Toast.makeText(Rechnunghinzufuegen.this, "Tragen Sie ein Datum ein!", Toast.LENGTH_LONG).show();
                }

                //leitet weiter auf Activity Kundewaehlen
                Intent gotoKW = new Intent(Rechnunghinzufuegen.this, Kundewaehlen.class);
                startActivity(gotoKW);

            }
        });

        btLoeschen.setOnClickListener(new View.OnClickListener() {
            //Wird ausgeführt wenn der button Abbrechen/Loschen betaetigt wird
            @Override
            public void onClick(View view) {
                // Leitet weiter auf Activity Kundewaehlen
               Intent gotoKW = new Intent(Rechnunghinzufuegen.this, Kundewaehlen.class);
               startActivity(gotoKW);
            }
        });

    }


    public class RechnungListAdapter extends ArrayAdapter<Geraet> { //ist zuständig, das die ListView mit Daten gefüllt wird und dem Design der listview.xml entspricht

        public RechnungListAdapter() {
            super(Rechnunghinzufuegen.this, R.layout.listview, g);
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
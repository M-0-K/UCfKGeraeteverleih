package com.example.ucgeraeteverleih;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class Rechnunghinzufuegen extends AppCompatActivity {

    private Button btLoeschen;
    private Button btSpeichern;
    private TextView tvKunde;
    private TextView tvPreis;
    private TextView tvAbgabe;
    private TextView tvRueckgabe;
    private ListView lvGeraete;
    private Button btAbgabe;
    private Button btRueckgabe;

    private ArrayList<Geraet> g = new ArrayList<Geraet>();
    private Kunde k;
    private DB db = new DB();
    private LocalDate abgabe = null;
    private LocalDate rueckgabe = null;
    private Rechnung r =null;
    private Calendar calander = Calendar.getInstance();
    private DatePickerDialog datePickerDialog;
    private DateTimeFormatter format = DateTimeFormatter.ofPattern("d.M.yyyy");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechnunghinzufuegen);

        btLoeschen = (Button) findViewById(R.id.btLoeschen);
        btSpeichern = (Button) findViewById(R.id.btSpeichern);
        btAbgabe = (Button) findViewById(R.id.btAbgabe);
        btRueckgabe = (Button) findViewById(R.id.btRueckgabe);
        tvKunde = (TextView) findViewById(R.id.tvKunde);
        tvPreis = (TextView) findViewById(R.id.tvPreis);
        lvGeraete = (ListView) findViewById(R.id.lvGeraete);
        tvAbgabe = (TextView) findViewById(R.id.tvAbgabe);
        tvRueckgabe = (TextView) findViewById(R.id.tvRueckgabe);


        k = db.ladeKunde(Integer.parseInt(getIntent().getStringExtra("kunde")));
        tvKunde.setText(k.getK_id()+ ". " + k.getVorname() + " " + k.getName() +"\n"
                + k.getStrasse() + " "+ k.getHausnummer() + "\n"
                + k.getPlz() + " " + k.getOrt()
         );
        String[] gs = (getIntent().getStringExtra("Geraete")).split(";");
        for(int i = 0; i < gs.length; i++){
            g.add(db.ladeGeraet(Integer.parseInt(gs[i])));
        }

        lvGeraete.setAdapter(new RechnungListAdapter());

        btAbgabe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            @Override
            public void onClick(View view) {
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
            @Override
            public void onClick(View view) {
                //Mietvertrag(Geraet geraet, Kunde kunde, LocalDate abgabe, LocalDate rueckgabe, boolean status)#

                if(abgabe != null && rueckgabe != null){
                    ArrayList<Mietvertrag> m = new ArrayList<Mietvertrag>();
                    for(int i = 0; i < g.size(); i++){
                        m.add(new Mietvertrag(g.get(i), k, abgabe, rueckgabe, false));
                    }
                    //Rechnung(ArrayList<Mietvertrag> mietvertraege, LocalDate rechnungsdatum,
                    // boolean status, String kundenname, String kundenvorname, String strasse, String hausnummer,
                    // String plz, String ort, double preis)
                    r = new Rechnung(m, LocalDate.now(), false, m.get(0).getKunde().getName(),
                            m.get(0).getKunde().getVorname(), m.get(0).getKunde().getStrasse(),
                            m.get(0).getKunde().getHausnummer(), m.get(0).getKunde().getPlz(),
                            m.get(0).getKunde().getOrt());
                    r.aktuellisierePreis();
                    db.speicherRechnung(r);
                }else {
                    Toast.makeText(Rechnunghinzufuegen.this, "Tragen Sie ein Datum ein!", Toast.LENGTH_LONG).show();
                }

                Intent gotoKW = new Intent(Rechnunghinzufuegen.this, Kundewaehlen.class);
                startActivity(gotoKW);

            }
        });

        btLoeschen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent gotoKW = new Intent(Rechnunghinzufuegen.this, Kundewaehlen.class);
               startActivity(gotoKW);
            }
        });

    }




    public class RechnungListAdapter extends ArrayAdapter<Geraet> {

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

    public ArrayList<Geraet> getGeraete(){
        return g;
    }
}
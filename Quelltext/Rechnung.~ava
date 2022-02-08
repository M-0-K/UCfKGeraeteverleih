import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 05.01.2022
 * @author 
 */

public class Rechnung {
  
  // Anfang Attribute
  private int r_id;
  private ArrayList<Mietvertrag> mietvertraege;
  private LocalDate rechnungsdatum;
  private boolean status;
  private String kundenname;
  private String kundenvorname;
  private String strasse;
  private String hausnummer;
  private String ort;
  private double preis;
  private String plz;
  // Ende Attribute

  public Rechnung(int r_id, ArrayList<Mietvertrag> mietvertraege, LocalDate rechnungsdatum, boolean status, String kundenname, String kundenvorname, String strasse, String hausnummer, String plz, String ort, double preis) {
    this.r_id = r_id;
    this.mietvertraege = mietvertraege;
    this.rechnungsdatum = rechnungsdatum;
    this.status = status;
    this.kundenname = kundenname;
    this.kundenvorname = kundenvorname;
    this.strasse = strasse;
    this.hausnummer = hausnummer;
    this.ort = ort;
    this.preis = preis;
    this.plz = plz;
  }
  
  public Rechnung(ArrayList<Mietvertrag> mietvertraege, LocalDate rechnungsdatum, boolean status, String kundenname, String kundenvorname, String strasse, String hausnummer, String plz, String ort) {
    
    this.r_id = 0;
    this.mietvertraege = mietvertraege;
    this.rechnungsdatum = rechnungsdatum;
    this.status = status;
    this.kundenname = kundenname;
    this.kundenvorname = kundenvorname;
    this.strasse = strasse;
    this.hausnummer = hausnummer;
    this.ort = ort;
    this.plz = plz;
    aktuellisierePreis();
  }

  public Rechnung(int r_id) {
    DB db = new DB();
    Rechnung r = db.ladeRechnung(r_id);
    this.mietvertraege = r.getMietvertraege();
    this.rechnungsdatum = r.getRechnungsdatum();
    this.status = r.getStatus();
    this.kundenname = r.getKundenname();
    this.kundenvorname = r.getKundenvorname();
    this.strasse = r.getStrasse();
    this.hausnummer = r.getHausnummer();
    this.ort = r.getOrt();
    this.preis = r.getPreis(); 
  }

  // Anfang Methoden
 

  public int getR_id() {
    return r_id;
  }

  public String getPlz() {
    return plz;
  }

  public void setR_id(int r_idNeu) {
    r_id = r_idNeu;
  }

  public ArrayList<Mietvertrag> getMietvertraege() {
    return mietvertraege;
  }

  public LocalDate getRechnungsdatum() {
    return rechnungsdatum;
  }

  public boolean getStatus() {
    return status;
  }

  public String getKundenname() {
    return kundenname;
  }

  public String getKundenvorname() {
    return kundenvorname;
  }

  public String getStrasse() {
    return strasse;
  }

  public String getHausnummer() {
    return hausnummer;
  }

  public String getOrt() {
    return ort;
  }

  public double getPreis() {
    return preis;
  }
  
  public void aktuellisierePreis(){
    this.preis = 0;
    for (int i = 0; i < this.mietvertraege.size(); i++) {
      preis = preis + this.mietvertraege.get(i).getGeraet().getMietpreisklasse()[this.mietvertraege.get(0).getKunde().getMitgliedid()-1];
    }
    
    }

  // Ende Methoden
} // end of Rechnung


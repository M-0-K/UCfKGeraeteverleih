import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
  private String plz;
  private String mitglied;
  // Ende Attribute

 

  public Rechnung(int r_id, ArrayList<Mietvertrag> mietvertraege, LocalDate rechnungsdatum, boolean status, String kundenname, String kundenvorname, String strasse, String hausnummer, String ort, String plz, String mitglied) {
    this.r_id = r_id;
    this.mietvertraege = mietvertraege;
    this.rechnungsdatum = rechnungsdatum;
    this.status = status;
    this.kundenname = kundenname;
    this.kundenvorname = kundenvorname;
    this.strasse = strasse;
    this.hausnummer = hausnummer;
    this.ort = ort;
    this.plz = plz;
    this.mitglied = mitglied;
  }
  
  public Rechnung(ArrayList<Mietvertrag> mietvertraege, LocalDate rechnungsdatum, boolean status, String kundenname, String kundenvorname, String strasse, String hausnummer, String ort, String plz, String mitglied) {
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
    this.mitglied = mitglied;
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
  public double getPreis(){
    double p = 0;
    for (int i = 0; i < this.mietvertraege.size(); i++) {
       p += this.mietvertraege.get(i).getGeraet().getMietpreisklasse()[this.getMitgliedid()-1];
    }
    return Math.round(100.0*p)/100.0;
  }

  public String getMitglied() {
    return mitglied;
  }

  public int getMitgliedid() {
    switch(this.mitglied.toLowerCase()){
      case "mitglied": return 1;
      case "bekannt": return 2;
      case "unbekannt": return 3;
      }
    return 3;
  }

  // Ende Methoden
} // end of Rechnung


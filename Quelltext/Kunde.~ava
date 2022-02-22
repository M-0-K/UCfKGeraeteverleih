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

public class Kunde {
  
  // Anfang Attribute
  private int k_id;
  private String name;
  private String vorname;
  private String strasse;
  private String hausnummer;
  private String mitglied;
  private String plz;
  private String ort;
  // Ende Attribute
  
  public Kunde(int k_id, String name, String vorname, String strasse, String hausnummer, String plz, String ort, String mitglied) {
    this.k_id = k_id;
    this.name = name;
    this.vorname = vorname;
    this.strasse = strasse;
    this.hausnummer = hausnummer;
    this.mitglied = mitglied;
    this.plz = plz;
    this.ort = ort;
  }
  
   public Kunde(String name, String vorname, String strasse, String hausnummer, String plz, String ort, String mitglied) {
    this.name = name;
    this.vorname = vorname;
    this.strasse = strasse;
    this.hausnummer = hausnummer;
    this.mitglied = mitglied;
    this.plz = plz;
    this.ort = ort;
  }

  /**Konstruktor mit k_id aus DB erstellen, example: 1
   *@param k_id, Kundenid
   **/
    public Kunde(int k_id) {
    DB db = new DB();
    Kunde k = db.ladeKunde(k_id);
    this.k_id = k.getK_id();
    this.name = k.getName();
    this.vorname = k.getVorname();
    this.strasse = k.getStrasse();
    this.hausnummer = k.getHausnummer();
    this.mitglied = k.getMitglied();
    this.plz = k.getPlz();
    this.ort = k.getOrt(); 
  }
  // Anfang Methoden
  public int getK_id() {
    return this.k_id;
  }

  public void setK_id(int k_idNeu) {
    k_id = k_idNeu;
  }

  public String getName() {
    return name;
  }

  public void setName(String nameNeu) {
    name = nameNeu;
  }

  public String getVorname() {
    return vorname;
  }

  public void setVorname(String vornameNeu) {
    vorname = vornameNeu;
  }
  public String getStrasse() {
    return strasse;
  }

  public void setStrasse(String strasseNeu) {
    strasse = strasseNeu;
  }

  public String getHausnummer() {
    return hausnummer;
  }

  public void setHausnummer(String hausnummerNeu) {
    hausnummer = hausnummerNeu;
  }

  public String getMitglied() {
    return mitglied;
  }
  
  public int getMitgliedid() {
    switch(this.mitglied){
      case "mitglied": return 1;
      case "bekannt": return 2;
      case "unbekannt": return 3;
      }
    return 3;
  }

  public void setMitglied(String mitgliedNeu) {
    mitglied = mitgliedNeu;
  }
  public String getPlz() {
    return plz;
  }

  public void setPlz(String plzNeu) {
    plz = plzNeu;
  }

  public String getOrt() {
    return ort;
  }

  public void setOrt(String ortNeu) {
    ort = ortNeu;
  }
  
  public void speichern(){
    DB db = new DB();
    db.speicherKunde(this);
    }  
  
  public void update(){
    DB db = new DB();
    db.updateKunde(this);
    }      

  // Ende Methoden
} // end of Kunde


package com.example.ucgeraeteverleih;


import java.time.LocalDate;
import java.util.ArrayList;
/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 05.01.2022
 * @Moritz Kockert
 */

public class Rechnung {

  // Anfang Attribute
  private int r_id;
  private final ArrayList<Mietvertrag> mietvertraege;
  private final LocalDate rechnungsdatum;
  private final boolean status;
  private final String kundenname;
  private final String kundenvorname;
  private final String strasse;
  private final String hausnummer;
  private final String ort;
  private final String plz;
  private final String mitglied;
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

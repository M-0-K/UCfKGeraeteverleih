import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 05.01.2022
 * @author 
 */

public class Geraet {
  
  // Anfang Attribute
  private int g_id;
  private String bezeichnung;
  private double anschaffungspreis;
  private LocalDate anschaffungsdatum;
  private double[] mietpreisklasse;
  private String zustand;
  private String produktgruppe;
  // Ende Attribute
  

  public Geraet(int g_id, String bezeichnung, double anschaffungspreis, LocalDate anschaffungsdatum, double[] mietpreisklasse, String zustand, String produktgruppe) {
    this.g_id = g_id;
    this.bezeichnung = bezeichnung;
    this.anschaffungspreis = anschaffungspreis;
    this.anschaffungsdatum = anschaffungsdatum;
    this.mietpreisklasse = mietpreisklasse;
    this.zustand = zustand;
    this.produktgruppe = produktgruppe;
  }
  
  public Geraet(String bezeichnung, double anschaffungspreis, LocalDate anschaffungsdatum, double[] mietpreisklasse, String zustand, String produktgruppe) {
    this.g_id = 0;
    this.bezeichnung = bezeichnung;
    this.anschaffungspreis = anschaffungspreis;
    this.anschaffungsdatum = anschaffungsdatum;
    this.mietpreisklasse = mietpreisklasse;
    this.zustand = zustand;
    this.produktgruppe = produktgruppe;
  }

  // Anfang Methoden
  public int getG_id() {
    return g_id;
  }

  public void setG_id(int g_idNeu) {
    g_id = g_idNeu;
  }

  public String getBezeichnung() {
    return bezeichnung;
  }

  public void setBezeichnung(String bezeichnungNeu) {
    bezeichnung = bezeichnungNeu;
  }

  public double getAnschaffungspreis() {
    return anschaffungspreis;
  }

  public void setAnschaffungspreis(double anschaffungspreisNeu) {
    anschaffungspreis = anschaffungspreisNeu;
  }

  public LocalDate getAnschaffungsdatum() {
    return anschaffungsdatum;
  }

  public void setAnschaffungsdatum(LocalDate anschaffungsdatumNeu) {
    anschaffungsdatum = anschaffungsdatumNeu;
  }

  public double[] getMietpreisklasse() {
    return mietpreisklasse;
  }

  public void setMietpreisklasse(double[] mietpreisklasseNeu) {
    mietpreisklasse = mietpreisklasseNeu;
  }

  public String getZustand() {
    return zustand;
  }

  public void setZustand(String zustandNeu) {
    zustand = zustandNeu;
  }

  public String getProduktgruppe() {
    return produktgruppe;
  }
  
  public int getProduktgruppeid() {
    switch (this.produktgruppe) {
      case"Licht":return 1;
      case"Ton":return 2;
      case"Video":return 3;  
      case"Kabel":return 4;
      case"Sonstiges":return 5;
    } // end of switch
    return 5;
  }

  public void setProduktgruppe(String produktgruppeNeu) {
    produktgruppe = produktgruppeNeu;
  }
  
  // Ende Methoden
} // end of Geraet


package com.example.ucgeraeteverleih;

import java.time.LocalDate;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 05.01.2022
 * @author 
 */

public class Mietvertrag {
  
  // Anfang Attribute
  private int m_id;
  private Geraet geraet;
  private Kunde kunde;
  private LocalDate abgabe;
  private LocalDate rueckgabe;
  private boolean status;
  // Ende Attribute
  

  
  public Mietvertrag(int m_id, Geraet geraet, Kunde kunde, LocalDate abgabe, LocalDate rueckgabe, boolean status) {
    this.m_id = m_id;
    this.geraet = geraet;
    this.kunde = kunde;
    this.abgabe = abgabe;
    this.rueckgabe = rueckgabe;
    this.status = status;
  }
  
   public Mietvertrag(Geraet geraet, Kunde kunde, LocalDate abgabe, LocalDate rueckgabe, boolean status) {
    this.m_id = 0;
    this.geraet = geraet;
    this.kunde = kunde;
    this.abgabe = abgabe;
    this.rueckgabe = rueckgabe;
    this.status = status;
  }

  // Anfang Methoden
  public void setM_id(int m_idNeu) {
    m_id = m_idNeu;
  }

  public Geraet getGeraet() {
    return geraet;
  }

  public void setGeraet(Geraet geraetNeu) {
    geraet = geraetNeu;
  }

  public Kunde getKunde() {
    return kunde;
  }

  public void setKunde(Kunde kundeNeu) {
    kunde = kundeNeu;
  }
  
  public void setStatus(boolean statusNeu) {
    status = statusNeu;
  }

  public LocalDate getAbgabe() {
    return abgabe;
  }

  public void setAbgabe(LocalDate abgabeNeu) {
    abgabe = abgabeNeu;
  }

  public LocalDate getRueckgabe() {
    return rueckgabe;
  }

  public void setRueckgabe(LocalDate rueckgabeNeu) {
    rueckgabe = rueckgabeNeu;
  }

  public int getM_id() {
    return m_id;
  }
  
  public boolean getStatus() {
    return status;
  }

  /*
  public void speichern(){
    DB db = new DB();
    db.speicherMietvertrag(this);
    }
  */
  // Ende Methoden
} // end of Mietet


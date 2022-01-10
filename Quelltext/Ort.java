/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 05.01.2022
 * @author 
 */

public class Ort {
  
  // Anfang Attribute
  private int o_id;
  private String plz;
  private String ortname;
  // Ende Attribute
  
  public Ort() {
    this.o_id = 0;
    this.plz = "";
    this.ortname = "";
  }

  public Ort(int o_id, String plz, String ortname) {
    this.o_id = o_id;
    this.plz = plz;
    this.ortname = ortname;
  }

  // Anfang Methoden
  public void setO_id(int o_idNeu) {
    o_id = o_idNeu;
  }

  public String getPlz() {
    return plz;
  }

  public void setPlz(String plzNeu) {
    plz = plzNeu;
  }

  public String getOrtname() {
    return ortname;
  }

  public void ortname(String ortnameNeu) {
    ortname = ortnameNeu;
  }

  // Ende Methoden
} // end of ort


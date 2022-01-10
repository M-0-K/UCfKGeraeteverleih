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
  private int R_id;
  private ArrayList<Mietvertrag> mietvertraege;
  private LocalDate rechnungsdatum;
  private boolean status;
  // Ende Attribute
  
  public Rechnung() {
    this.R_id = 0;
    this.mietvertraege = null;
    this.rechnungsdatum = null;
    this.status = false;
  }

  public Rechnung(int R_id, ArrayList<Mietvertrag> mietvertraege, LocalDate rechnungsdatum, boolean status) {
    this.R_id = R_id;
    this.mietvertraege = mietvertraege;
    this.rechnungsdatum = rechnungsdatum;
    this.status = status;
  }

  // Anfang Methoden
  public int getR_id() {
    return R_id;
  }

  public void setR_id(int R_idNeu) {
    R_id = R_idNeu;
  }

  public ArrayList<Mietvertrag> getMietvertraege() {
    return mietvertraege;
  }

  public void setMietvertraege(ArrayList<Mietvertrag> mietvertraegeNeu) {
    mietvertraege = mietvertraegeNeu;
  }

  public LocalDate getRechnungsdatum() {
    return rechnungsdatum;
  }

  public void setRechnungsdatum(LocalDate rechnungsdatumNeu) {
    rechnungsdatum = rechnungsdatumNeu;
  }

  public boolean getStatus() {
    return status;
  }

  public void setStatus(boolean statusNeu) {
    status = statusNeu;
  }

  // Ende Methoden
} // end of Rechnung



import java.util.ArrayList;
/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 11.01.2022
 * @author 
 */

public class test {
  
  // Anfang Attribute
  private String stringTest;
  ArrayList<Rechnung> r;
  // Ende Attribute
  
  public test() {
    DB db = new DB();
    this.stringTest = "";
    this.r = db.ladeRechnungen();
  }

  // Anfang Methoden
  public String getStringTest() {
    return stringTest;
  }

  public void setStringTest(String stringTestNeu) {
    stringTest = stringTestNeu;
  }
  

  // Ende Methoden
} // end of test

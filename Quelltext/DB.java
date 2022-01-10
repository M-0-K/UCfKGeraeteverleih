import com.mysql.cj.result.LocalDateValueFactory;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;
/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 05.01.2022
 * @author 
 */

public class DB {
  
  // Anfang Attribute
  private Connection con =     null;  //Verbindungsstring
  private Statement stmt =     null;  //Anweisung an Managementsystem
  private ResultSet rs =       null;  //Ergebnisstruktur
  private String query =       null;  //SQL-Anweisung
  private String ret =         null;  //Ergebnisvariable
  private String driverClass = null;  //aktive Treiberklasse
  private String conURL =      null;  //aktiver Verbindungsstring
  private DateTimeFormatter sqlformat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  // Ende Attribute
  
    public DB() {
    String server = "127.0.0.1";
    String dbName = "belegarbeit";
    String usr = "Admin";
    String password = "47114711";
    //Constructor für MySQL ab V8
    this.driverClass = "com.mysql.cj.jdbc.Driver";     
    this.conURL = "jdbc:mysql://"+server+":3306/"+dbName+"?user="+usr+"&password="+password; 
  }  
  
  // Anfang Methoden
    public boolean verbinden() {
    boolean s = false;
    //setzt die Verbindung zur Datenbank und gibt Erfolg (true) zurück
  
    try {
      Class.forName(driverClass);
      System.out.println("Treiber geladen");
    }
    catch(ClassNotFoundException e)
    {System.out.println("MsTreiberladefehler");
    }
    try {con  = DriverManager.getConnection(conURL);
      System.out.println("Verbindung ok");
      s = true;              
    }      
    catch(SQLException e)
    {System.out.println("URLTreiberladefehler");
    }
    return s;
  }
  
  public Kunde ladeKunde(int k_id){
    String name = ""; 
    String vname = "";
    int oid = 0;
    String ort = "";
    String plz = "";
    String strasse = "";
    String hausnummer = "";
    String mitglied = "";
    verbinden();
    query = "SELECT K_id, name, vorname, ort, plz, strasse, hausnummer, mitglied from Kunde Inner Join Ort on Ort.o_id = Kunde.O_id WHERE K_id ="+k_id;
          try{
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);         
            while (rs.next()) { 
              name = rs.getString(2);   
              vname = rs.getString(3);
              ort = rs.getString(4);  
              plz = rs.getString(5);
              strasse = rs.getString(6);
              hausnummer = rs.getString(7);
              mitglied = rs.getString(8);
            }           
            rs.close();
            stmt.close(); 
          }catch (SQLException e){
      System.out.println("Abfragefehler: Kunde");
      System.out.println(e.getMessage());
      System.out.println(e.getSQLState());
      System.out.println(e.getErrorCode());
      System.exit(0);
    } 
      finally{if (con != null){
        try{con.close();
        System.out.println("Verbindung geschlossen");}
        catch (SQLException e){e.printStackTrace();}}   
    } 
    Kunde k = new Kunde(k_id, name, vname, strasse, hausnummer, plz, ort, mitglied);
    return k;
    }
  
  public void speicherKunde(Kunde k){
    query = "INSERT INTO Kunde (Name, Vorname, O_id, Strasse, Hausnummer ,Mitglied) VALUES ('" +
    k.getName()+ "', '" +
    k.getVorname() + "', "+
    "(SELECT O_id FROM ort WHERE plz = '" + k.getPlz() + "' && ort = '"+ k.getOrt() + "'),' " +
    k.getStrasse() + "','"+ 
    k.getHausnummer() + "',"+ 
    k.getMitgliedid() + ");";  
    verbinden();
    try{
      stmt = con.createStatement();
      int status = stmt.executeUpdate(query);   
      stmt.close(); }      
    catch (SQLException e){
      System.out.println("Einfuegefehler: Kunde");
      System.out.println(e.getMessage());
      System.out.println(e.getSQLState());
      System.out.println(e.getErrorCode());
      System.exit(0);
    } 
      finally{if (con != null){
        try{con.close();
        System.out.println("Verbindung geschlossen");}
        catch (SQLException e){e.printStackTrace();}}   
    }      
    }
  
  public Geraet ladeGeraet(int g_id){
    String bezeichnung = "";
    double anschaffungspreis =0;
    LocalDate anschaffungsdatum = LocalDate.of(2000, 1, 1);
    double[] mietpreise = {0, 0, 0};
    String zustand = "";
    String produktgruppe = "";
    verbinden();
    query = "SELECT * FROM Geraet WHERE G_id ="+g_id;
    try{
      stmt = con.createStatement();
      rs = stmt.executeQuery(query);         
      while (rs.next()) { 
        bezeichnung = rs.getString(2);   
        anschaffungspreis = rs.getDouble(3);
        anschaffungsdatum = Date.valueOf(rs.getString(4)).toLocalDate();
        mietpreise[0] = rs.getDouble(5);
        mietpreise[1] = rs.getDouble(6);
        mietpreise[2] = rs.getDouble(7);
        zustand = rs.getString(8); 
        produktgruppe = rs.getString(9);
      }           
      rs.close();
      stmt.close(); 
    }catch (SQLException e){
      System.out.println("Abfragefehler: Geraet");
      System.out.println(e.getMessage());
      System.out.println(e.getSQLState());
      System.out.println(e.getErrorCode());
      System.exit(0);
    } 
      finally{if (con != null){
        try{con.close();System.out.println("Verbindung geschlossen");}
        catch (SQLException e){e.printStackTrace();}}   
    } 
    
    Geraet g = new Geraet(g_id, bezeichnung, anschaffungspreis, anschaffungsdatum, mietpreise, zustand, produktgruppe);
    return g ;  
    }
  
  public void speicherGeraet(Geraet g){
    query = "INSERT INTO geraet (Bezeichnung, Anschaffungspreis, Anschaffungsdatum, Mietpreisklasse1, Mietpreisklasse2, Mietpreisklasse3,  Zustand, Produktgruppe) VALUES ('"+
    g.getBezeichnung() + "'," + 
    g.getAnschaffungspreis() + ",'" + 
    g.getAnschaffungsdatum().format(sqlformat) + "'," + 
    g.getMietpreisklasse()[0]+ "," + 
    g.getMietpreisklasse()[1]+ "," + 
    g.getMietpreisklasse()[2]+ ",'" + 
    g.getZustand() + "'," +  
    g.getProduktgruppeid() + ");";  
    verbinden();
    try{
      stmt = con.createStatement();
      int status = stmt.executeUpdate(query);  //nur ausführen    
    stmt.close(); }      
    catch (SQLException e){
      System.out.println("Geraet: Einfuegefehler");
      System.out.println(e.getMessage());
      System.out.println(e.getSQLState());
      System.out.println(e.getErrorCode());
      System.exit(0);
    } 
      finally{if (con != null){
        try{con.close();
        System.out.println("Verbindung geschlossen");}
        catch (SQLException e){e.printStackTrace();}}   
    }      
  }
  
  public Mietvertrag ladeMietvertrag(int m_id) {
    int g_id = 0;
    int k_id = 0;
    LocalDate abgabe = LocalDate.of(2000, 1, 1);
    LocalDate rueckgabe = LocalDate.of(2000, 1, 1);
    verbinden();
    query = "SELECT * FROM Mietvertrag WHERE M_id ="+ m_id;
    try{
      stmt = con.createStatement();
      rs = stmt.executeQuery(query);         
      while (rs.next()) { 
        g_id = rs.getInt(2);   
        k_id = rs.getInt(3);
        abgabe  = Date.valueOf(rs.getString(5)).toLocalDate();
        rueckgabe  = Date.valueOf(rs.getString(6)).toLocalDate();
      }           
      rs.close();
      stmt.close(); 
    }catch (SQLException e){
      System.out.println("Abfragefehler: Mietvertrag");
      System.out.println(e.getMessage());
      System.out.println(e.getSQLState());
      System.out.println(e.getErrorCode());
      System.exit(0);
    }finally{
      if (con != null){
        try{con.close();System.out.println("Verbindung geschlossen");}
        catch (SQLException e){e.printStackTrace();}
        }   
    } 
    
    Mietvertrag m = new Mietvertrag(m_id, ladeGeraet(g_id), ladeKunde(k_id), abgabe, rueckgabe);
    return m;
  }
  
  public void speicherMietvertrag(Mietvertrag m) {
    query = "INSERT INTO Mietvertrag (G_id, K_id, Abgabe, Rueckgabe) VALUES ("+
    m.getGeraet().getG_id() + "," + 
    m.getKunde().getK_id() + ",'" +  
    m.getAbgabe().format(sqlformat) +"','" + 
    m.getRueckgabe().format(sqlformat)+ "');"; 
    System.out.println(query);    
    verbinden();
    try{
      stmt = con.createStatement();
      int status = stmt.executeUpdate(query);  //nur ausführen    
      stmt.close(); 
    } catch (SQLException e){
      System.out.println("Einfuegefehler: Mietvertrag");
      System.out.println(e.getMessage());
      System.out.println(e.getSQLState());
      System.out.println(e.getErrorCode());
      System.exit(0);
    } finally{
      if (con != null){
        try{con.close(); System.out.println("Verbindung geschlossen");}
        catch (SQLException e){e.printStackTrace();}
      }   
    }
  }   
  // Ende Methoden
} // end of DB


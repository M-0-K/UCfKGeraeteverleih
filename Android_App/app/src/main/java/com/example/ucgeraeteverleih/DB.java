package com.example.ucgeraeteverleih;


import android.os.StrictMode;

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
  
  
  


  public DB(String ip, String usr, String psw) {
    String dbName = "belegarbeit";
    //Constructor f�r MySQL ab V8
    this.driverClass = "com.mysql.cj.jdbc.Driver";     
    this.conURL = "jdbc:mysql://"+ip+":3306/"+dbName+"?user="+usr+"&password="+psw;
  }

  public DB() {
    String server = "192.168.180.112";
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
    //setzt die Verbindung zur Datenbank und gibt Erfolg (true) zur�ck
  
    try {
      Class.forName(driverClass);
      System.out.println("Treiber geladen");
    }
    catch(ClassNotFoundException e)
    {System.out.println("MsTreiberladefehler");
    }
      try {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        con  = DriverManager.getConnection(conURL);
        //con  = DriverManager.getConnection("jdbc:mysql://192.168.180.112:3306/belegarbeit", "Admin", "47114711");
        System.out.println("Verbindung ok");
      s = true;              
    }      
    catch(SQLException e)
    {System.out.println("URLTreiberladefehler");
    }
    return s;
  }
  
  public Vector<String> getDQLV(String dql, boolean metadata) {
    //f�hrt eine SQL-Anweisung aus und gibt einen Vector mit Daten zur�ck
    //bei metadata true werden die Metadaten in die erste Zeile geschrieben
    ResultSetMetaData sqlMetaDaten;   //f�r Metadaten, z.B.Spalten�berschriften
    Vector<String> attribute = null;          //Vector f�r Spalten�berschriften
    Vector<String> datensaetze = null;        //Vector f�r Datens�tze
    if (dql.isEmpty()) {                //keine SQL-Anweisung, dann ..
      return null;
    } else {      
      verbinden();        
      try {  
        // Abfrage Spalten ausf�hren      
        stmt = con.createStatement();      
        rs = stmt.executeQuery(dql);               
        // Attribute der Tabelle lesen
        sqlMetaDaten = rs.getMetaData();            
        int spalten = sqlMetaDaten.getColumnCount();
        //Spalten�berschriften lesen 
        attribute = new Vector<String>();                 
        for(int i = 0; i < spalten; i++)             
          attribute.addElement(sqlMetaDaten.getColumnLabel(i+1)); 
           
        // Datens�tze lesen
        datensaetze = new Vector<String>();              //Zeilenvector
        if (metadata) {                          //Spalten�berschriften
          datensaetze.addAll(attribute);              
        } // end of if
        while (rs.next()) {       //Datenbereich satzeise auslesen und an Vector
          Vector<String> neuerDatensatz = new Vector<String>();  //Spaltenvector
          for (int i = 1; i <= spalten; i++)
            neuerDatensatz.addElement(rs.getObject(i).toString());
          datensaetze.addAll(neuerDatensatz);  //Satz anf�gen            
        }
        rs.close();          
      }
      catch (SQLException e) {
        while (e != null) {
          System.out.println("SQL-Abfragefehler");
        }
      } 
      finally{if (con != null){
          try{con.close();
          //System.out.println("Verbindung geschlossen");
          }
          catch (SQLException e){e.printStackTrace();}}
      } 
      return datensaetze;   
      } 
  }

  public String[][] getDQLA(String dql, boolean metadata) {
    //f�hrt eine SQL-Anweisung aus und gibt eine Tabelle mit Daten zur�ck
    //bei metadata true werden die Metadaten in die erste Zeile geschrieben
    ResultSetMetaData sqlMetaDaten;   //f�r Metadaten, z.B.Spalten�berschriften
    String[] attribute = null;
    String[][] datensaetze = null; 
    if (dql.isEmpty()) {
      return null;
    } else {      
      verbinden();        
      try {
        //Abfrage Metadaten ausf�hren      
        stmt = con.createStatement();
        rs = stmt.executeQuery(dql);
        // Attribute lesen
        sqlMetaDaten = rs.getMetaData();
        int spalten = sqlMetaDaten.getColumnCount();     
        //Spalten�berschriften ermitteln
        attribute = new String[spalten];
        for(int i = 0; i < spalten; i++)
          attribute[i] = sqlMetaDaten.getColumnLabel(i+1);
        
        //ben�tigte Arrayl�nge ermitteln 
        int rowcount = 0;
        while (rs.next()) {rowcount++;}          
        rs.close();
        
        //abfragen und Ergebnisarray bauen
        int y = 0; //Startwert Daten im Array
        rs = stmt.executeQuery(dql);  
        //Spalten�berschriften  ja/nein
        if (metadata) {y = 1; //Startwert Daten im Array
          datensaetze = new String[rowcount+1][spalten];
          for (int i = 0; i < spalten; i++) {
            datensaetze[0][i] = attribute[i];
          }
        } else{      
          datensaetze = new String[rowcount][spalten];
        }
                 
       // Datens�tze lesen und in Ergebnisarray eintragen                 
        while (rs.next()) { 
          for (int i = 1; i <= spalten; i++) {                             
            datensaetze[y][i-1] = rs.getString(i);} 
            y++; //Zeilenz�hler f�r Ergebnisarrayadressierung           
        }
        rs.close();
      }
      catch (SQLException e) {
        while (e != null) {
          System.out.println("SQL-Abfragefehler");
        }
      } 
      finally{if (con != null){
          try{con.close();
          //System.out.println("Verbindung geschlossen");
          }
          catch (SQLException e){e.printStackTrace();}}
      } 
      return datensaetze;   
      } 
  }

  public boolean executeNonDQL(String nonDQL) {
    //f�hrt eine SQL-Anweisung aus und gibt Erfolg (true) zur�ck
    boolean flag = false;    
    if (nonDQL.isEmpty()) {                //keine SQL-Anweisung, dann ..
      flag = false;
    } else {      
      verbinden();        
      try {  
        // Abfrage Spalten ausf�hren      
        stmt = con.createStatement();      
        int r = stmt.executeUpdate(nonDQL);               
        if (r==0) {flag = true;}                                         
      }
      catch (SQLException e) {
        while (e != null) {
          System.out.println("SQL-Abfragefehler");
        }
      } 
      finally{if (con != null){
          try{con.close();
          //System.out.println("Verbindung geschlossen");
          }
          catch (SQLException e){e.printStackTrace();}}      
      }      
    } 
    return flag;      
  }
  
  public String[] getMetadata(String dql){
    ResultSetMetaData sqlMetaDaten;   //f�r Metadaten, z.B.Spalten�berschriften
    String[] attribute = null;          //Vector f�r Spalten�berschriften
    if (dql.isEmpty()) {                //keine SQL-Anweisung, dann ..
      return null;
    } else {      
      verbinden();        
      try {  
        // Abfrage Spalten ausf�hren      
        stmt = con.createStatement();      
        rs = stmt.executeQuery(dql);               
        // Attribute der Tabelle lesen
        sqlMetaDaten = rs.getMetaData();            
        int spalten = sqlMetaDaten.getColumnCount();
        //Spalten�berschriften lesen 
        attribute = new String[spalten];               
        for(int i = 0; i < spalten; i++)             
          attribute[i] = sqlMetaDaten.getColumnLabel(i+1);
      }
      catch (SQLException e) {
        while (e != null) {
          System.out.println("SQL-Abfragefehler");
        }
      } 
      finally{if (con != null){
          try{con.close();
          //System.out.println("Verbindung geschlossen");
          }
          catch (SQLException e){e.printStackTrace();}}
      } 
      return attribute;   
      }       
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
    query = "SELECT Kunde.K_id, Kunde.name, Kunde.vorname, Ort.ort, Ort.plz, Kunde.strasse, Kunde.hausnummer, Kunde.mitglied FROM Kunde INNER JOIN Ort ON Ort.o_id = Kunde.O_id WHERE K_id ="+k_id;
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
  
  public ArrayList<Kunde> ladeKunden(String where){
    ArrayList<Kunde> kunden = new ArrayList<Kunde>(); 
    verbinden();
    query = "SELECT Kunde.K_id, Kunde.name, Kunde.vorname, Kunde.strasse, Kunde.hausnummer, Ort.plz, Ort.ort,  Kunde.mitglied from Kunde Inner Join Ort on Ort.o_id = Kunde.O_id " + where;
          try{
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);         
            while (rs.next()) { 
              Kunde k = new Kunde(rs.getInt(1) ,rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
              kunden.add(k);
            }           
            rs.close();
            stmt.close(); 
          }catch (SQLException e){
      System.out.println("Abfragefehler: ladeKunden");
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
    return kunden;
    }
  
  public void speicherKunde(Kunde k){
    query = "INSERT INTO Kunde (name, vorname, o_id, strasse, hausnummer , mitglied) VALUES ('" +
    k.getName()+ "', '" +
    k.getVorname() + "', "+
    "(SELECT O_id FROM ort WHERE plz = '" + k.getPlz() + "' && ort = '"+ k.getOrt() + "'),' " +
    k.getStrasse() + "','"+ 
    k.getHausnummer() + "',"+ 
    k.getMitgliedid() + ");";  
    System.out.println(query);
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
  
  public void updateKunde(Kunde k){
    executeNonDQL("UPDATE `kunde` SET"
    +" `Name` = '"+k.getName()+"',"
    +" `Vorname` = '"+k.getVorname()+"',"
    +" `O_id` = (SELECT O_id FROM ort WHERE plz = '" + k.getPlz() + "' && ort = '"+ k.getOrt() + "'),"
    +" `Strasse` = '"+k.getStrasse()+"',"
    +" `Hausnummer` = '"+k.getHausnummer()+"',"
    +" `Mitglied` = '"+k.getMitglied()+"'"
    +" WHERE `kunde`.`K_id` = "+k.getK_id());
    }
  
  public void loescheKunde(Kunde k){
    System.out.println("UPDATE `kunde` SET `Name` = '', `Vorname` = '', `O_id` = '0', `Strasse` = '', `Hausnummer` = '', `Mitglied` = '0' WHERE `kunde`.`K_id` = "+k.getK_id());
    executeNonDQL("UPDATE `kunde` SET `Name` = '', `Vorname` = '', `O_id` = '0', `Strasse` = '', `Hausnummer` = '', `Mitglied` = '0' WHERE `kunde`.`K_id` = "+k.getK_id());
    
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
        anschaffungsdatum = LocalDate.parse(rs.getString(4));
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
  
  public ArrayList<Geraet> ladeGeraete(String where){
    ArrayList<Geraet> geraete = new ArrayList<Geraet>();
    
    verbinden();
    query = "SELECT Geraet.G_id, Geraet.Bezeichnung, Geraet.Anschaffungspreis, Geraet.Anschaffungsdatum, Geraet.Mietpreisklasse1, Geraet.Mietpreisklasse2, Geraet.Mietpreisklasse3, Geraet.Zustand, Geraet.Produktgruppe FROM Geraet " + where;
    try{
      stmt = con.createStatement();
      rs = stmt.executeQuery(query);         
      while (rs.next()) { 
        double[] mietpreise = {rs.getDouble(5), rs.getDouble(6), rs.getDouble(7)};
        Geraet g = new Geraet(rs.getInt(1) ,rs.getString(2), rs.getDouble(3), LocalDate.parse(rs.getString(4)), mietpreise, rs.getString(8), rs.getString(9) );
        geraete.add(g);
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
    return geraete;  
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
      int status = stmt.executeUpdate(query);  //nur ausf�hren    
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
  
  public void updateGeraet(Geraet g) {
    executeNonDQL("UPDATE `geraet` SET " 
    +" `Bezeichnung` = '"+g.getBezeichnung() +"',"
    +" `Anschaffungspreis` = '"+ g.getAnschaffungspreis() +"',"
    +" `Anschaffungsdatum` = '"+ g.getAnschaffungsdatum().format(sqlformat) +"',"
    +" `Mietpreisklasse1` = '"+ g.getMietpreisklasse()[0] +"',"
    +" `Mietpreisklasse2` = '"+ g.getMietpreisklasse()[1] +"',"
    +" `Mietpreisklasse3` = '"+ g.getMietpreisklasse()[2] +"',"
    +" `Zustand` = '"+ g.getZustand() +"',"
    +" `Produktgruppe` = '"+ g.getProduktgruppeid() +"'"
    +"WHERE `geraet`.`G_id` = "+ g.getG_id());
  }
  
  public void loescheGeraet(Geraet g){
    executeNonDQL("DELETE FROM `geraet` WHERE `geraet`.`G_id` = "+ g.getG_id());
  }
  
  public void defektGeraet(Geraet g){
    executeNonDQL("UPDATE `geraet` SET `Zustand` = 'defekt' WHERE `geraet`.`G_id` = "+ g.getG_id());
  }
  
  // Diese Funktion pr�ft, ob ein Ger�t schon einemal vermietet wurde 
  public boolean vorLoeschenGeraet(Geraet g){
    String[][] s = getDQLA("SELECT * FROM `mietvertrag` WHERE G_id = "+ g.getG_id(), false);
    if(s.length == 0){
      return true;
    }
    return false;
    }
  
  public Mietvertrag ladeMietvertrag(int m_id) {
    int g_id = 0;
    int k_id = 0;
    LocalDate abgabe = LocalDate.of(2000, 1, 1);
    LocalDate rueckgabe = LocalDate.of(2000, 1, 1);
    boolean status = false;
    verbinden();
    query = "SELECT * FROM Mietvertrag WHERE M_id ="+ m_id;
    try{
      stmt = con.createStatement();
      rs = stmt.executeQuery(query);         
      while (rs.next()) { 
        g_id = rs.getInt(2);   
        k_id = rs.getInt(3);
        abgabe  = LocalDate.parse(rs.getString(5));
        rueckgabe  = LocalDate.parse(rs.getString(6));
        status = rs.getBoolean(7);
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
    
    Mietvertrag m = new Mietvertrag(m_id, ladeGeraet(g_id), ladeKunde(k_id), abgabe, rueckgabe, status);
    return m;
  }
  
  
  public ArrayList<Mietvertrag> ladeMietvertraege(String where) {
    ArrayList<Integer> id = new ArrayList<Integer>();
    ArrayList<Integer> k_id = new ArrayList<Integer>();
    ArrayList<Integer> g_id = new ArrayList<Integer>();
    ArrayList<Integer> r_id = new ArrayList<Integer>();
    ArrayList<LocalDate> abgabe = new ArrayList<LocalDate>();
    ArrayList<LocalDate> rueckgabe = new ArrayList<LocalDate>();
    ArrayList<Boolean> status = new ArrayList<Boolean>();
    verbinden();
    query = "SELECT mietvertrag.M_id, mietvertrag.G_id, mietvertrag.K_id, mietvertrag.R_id, mietvertrag.Abgabe, mietvertrag.Rueckgabe, mietvertrag.Status FROM Mietvertrag "+ where;
    try{
      stmt = con.createStatement();
      rs = stmt.executeQuery(query);         
      while (rs.next()) { 
        id.add(rs.getInt(1));
        g_id.add(rs.getInt(2));   
        k_id.add(rs.getInt(3));
        r_id.add(rs.getInt(4));
        abgabe.add(LocalDate.parse(rs.getString(5)));
        rueckgabe.add(LocalDate.parse(rs.getString(6)));
        status.add(rs.getBoolean(7));
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
    
    ArrayList<Mietvertrag> mv = new ArrayList<Mietvertrag>();
    for (int i = 0; i < g_id.size(); i++) {
        Mietvertrag m = new Mietvertrag(id.get(i), ladeGeraet(g_id.get(i)), ladeKunde(k_id.get(i)), abgabe.get(i), rueckgabe.get(i), status.get(i));
        mv.add(m);
    }
    return mv;
  }
  
   public ArrayList<Mietvertrag> ladeMietvertraege(int r_id) {
    ArrayList<Integer> id = new ArrayList<Integer>();
    ArrayList<Integer> k_id = new ArrayList<Integer>();
    ArrayList<Integer> g_id = new ArrayList<Integer>();
    ArrayList<LocalDate> abgabe = new ArrayList<LocalDate>();
    ArrayList<LocalDate> rueckgabe = new ArrayList<LocalDate>();
    ArrayList<Boolean> status = new ArrayList<Boolean>();
    verbinden();
    query = "SELECT * FROM Mietvertrag WHERE R_id ="+ r_id;
    try{
      stmt = con.createStatement();
      rs = stmt.executeQuery(query);         
      while (rs.next()) { 
        id.add(rs.getInt(1));
        g_id.add(rs.getInt(2));   
        k_id.add(rs.getInt(3));
        abgabe.add(LocalDate.parse(rs.getString(5)));
        rueckgabe.add(LocalDate.parse(rs.getString(6)));
        status.add(rs.getBoolean(7));
      }           
      rs.close();
      stmt.close(); 
    }catch (SQLException e){
      System.out.println("Abfragefehler: Mietvertraege");
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
    ArrayList<Mietvertrag> mv = new ArrayList<Mietvertrag>();
    for (int i = 0; i < g_id.size(); i++) {
        Mietvertrag m = new Mietvertrag(id.get(i), ladeGeraet(g_id.get(i)), ladeKunde(k_id.get(i)), abgabe.get(i), rueckgabe.get(i), status.get(i));
        mv.add(m);
    }
    return mv;
  }
  
  public void speicherMietvertrag(Mietvertrag m, Rechnung r) {
    String ab = "'null'";
    String rue = "'null'";
    try {ab = "'"+m.getAbgabe().format(sqlformat) +"'";}catch (Exception e) {}
    try {rue = "'"+m.getRueckgabe().format(sqlformat) +"'";}catch (Exception e) {}
    
    query = "INSERT INTO Mietvertrag (R_id, G_id, K_id, Abgabe, Rueckgabe, Status) VALUES ("+
    r.getR_id() +"," + 
    m.getGeraet().getG_id() + "," + 
    m.getKunde().getK_id() + "," +  
    ab +"," + 
    rue+ ","+
    m.getStatus() + ");"; 
    System.out.println(query);    
    verbinden();
    try{
      stmt = con.createStatement();
      int status = stmt.executeUpdate(query);  //nur ausf�hren    
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
  
  public void setMietvertragstatus(int mid, boolean zurueck){
    int z = 0;
    if (zurueck) {
      z = 1;
    } // end of if
    executeNonDQL("UPDATE `mietvertrag` SET `Status` = '"+z+"' WHERE `mietvertrag`.`M_id` =" + mid);
    }
  
  public void loescheMietvertrag(Mietvertrag m){
    // 155
    System.out.println("DELETE FROM `mietvertrag` WHERE `mietvertrag`.`M_id` = " + m.getM_id());
    executeNonDQL("DELETE FROM `mietvertrag` WHERE `mietvertrag`.`M_id` = " + m.getM_id());
    }
  
   public Rechnung ladeRechnung(int r_id) {
    String kname = "";
    String kvorname = "";
    String strasse = "";
    String hausnummer = "";
    String plz = "";
    String ort = "";
    LocalDate rechnungsdatum = null;
    double preis = 0;
    boolean status = false;  
    query = "SELECT * FROM Rechnung WHERE R_id =" + r_id;
    verbinden();
    try{
      stmt = con.createStatement();
      rs = stmt.executeQuery(query);         
      while (rs.next()) { 
        kname  = rs.getString(2);
        kvorname = rs.getString(3);
        strasse = rs.getString(4);
        hausnummer = rs.getString(5);
        plz = rs.getString(6);
        ort = rs.getString(7);
        rechnungsdatum = LocalDate.parse(rs.getString(8));
        preis = rs.getDouble(9);
        status = rs.getBoolean(10);
      }           
      rs.close();
      stmt.close(); 
    }catch (SQLException e){
      System.out.println("Rechnung:Abfragefehler");
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
    
    Rechnung r = new Rechnung(r_id, ladeMietvertraege(r_id), rechnungsdatum, status, kname, kvorname, strasse, hausnummer, plz, ort, preis);
    return r;
    }
  
  public ArrayList<Rechnung> ladeRechnungen(String where) {
    ArrayList<Integer> r_id = new ArrayList<Integer>();
    ArrayList<String> kname = new ArrayList<String>();
    ArrayList<String> kvorname = new ArrayList<String>();
    ArrayList<String> strasse = new ArrayList<String>();
    ArrayList<String> hausnummer = new ArrayList<String>();
    ArrayList<String> plz = new ArrayList<String>();
    ArrayList<String> ort = new ArrayList<String>();
    ArrayList<LocalDate>  rechnungsdatum = new ArrayList<LocalDate>();
    ArrayList<Double> preis = new ArrayList<Double>();
    ArrayList<Boolean> status = new ArrayList<Boolean>();  
    query = "SELECT * FROM Rechnung "+ where;
    verbinden();
    try{
      stmt = con.createStatement();
      rs = stmt.executeQuery(query);         
      while (rs.next()) { 
        r_id.add(rs.getInt(1));
        kname.add(rs.getString(2));
        kvorname.add(rs.getString(3));
        strasse.add(rs.getString(4));
        hausnummer.add(rs.getString(5));
        plz.add(rs.getString(6));
        ort.add(rs.getString(7));
        rechnungsdatum.add(LocalDate.parse(rs.getString(8)));
        preis.add(rs.getDouble(9));
        status.add(rs.getBoolean(10));
      }           
      rs.close();
      stmt.close(); 
    }catch (SQLException e){
      System.out.println("Rechnung:Abfragefehler");
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
    ArrayList<Rechnung> rechnungen = new ArrayList<Rechnung>(); 
    for (int i = 0; i < r_id.size(); i++) {
      Rechnung r = new Rechnung(r_id.get(i), ladeMietvertraege(r_id.get(i)), rechnungsdatum.get(i), status.get(i), kname.get(i), kvorname.get(i), strasse.get(i), hausnummer.get(i), plz.get(i), ort.get(i), preis.get(i));
      rechnungen.add(r);
    }
    return rechnungen;
    }     
  
  public void speicherRechnung(Rechnung r) {
    query = "INSERT INTO Rechnung (Kundenname, Kundenvorname, Strasse, Hausnummer, PLZ, Ort, Rechnungsdatum, Preis, Status) VALUES ('r"+
    r.getMietvertraege().get(0).getKunde().getName() + "','" + 
    r.getMietvertraege().get(0).getKunde().getVorname() + "','" +  
    r.getMietvertraege().get(0).getKunde().getStrasse() +"','" + 
    r.getMietvertraege().get(0).getKunde().getHausnummer() +"','" + 
    r.getMietvertraege().get(0).getKunde().getPlz() +"','" + 
    r.getMietvertraege().get(0).getKunde().getOrt() +"','" + 
    r.getRechnungsdatum().format(sqlformat) +"'," +
    r.getPreis()+","+
    r.getStatus()+ ");"; 
    System.out.println(query);    
    verbinden();
    try{
      stmt = con.createStatement();
      int status = stmt.executeUpdate(query);  //nur ausf�hren    
      stmt.close(); 
    } catch (SQLException e){
      System.out.println("Einfuegefehler: Rechnung");
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
    
    for (int i = 0; i < r.getMietvertraege().size(); i++) {
      speicherMietvertrag(r.getMietvertraege().get(i), ladeRechnungen("ORDER BY R_id DESC LIMIT 1").get(0));
    }
  }  
  
  public void setRechnungstatus(int rid, boolean bezahlt){
    int b = 0;
    if (bezahlt) {
      b = 1;
    } // end of if
    executeNonDQL("UPDATE `rechnung` SET `Status` = '"+b+"' WHERE `rechnung`.`R_id` ="+ rid);
  }  
  
  public void loescheRechnung(Rechnung r){
    executeNonDQL("DELETE FROM `mietvertrag` WHERE `mietvertrag`.`R_id` = " + r.getR_id());
    executeNonDQL("DELETE FROM `rechnung` WHERE `rechnung`.`R_id` = "+ r.getR_id());
    } 
  // Ende Methoden
} // end of DB

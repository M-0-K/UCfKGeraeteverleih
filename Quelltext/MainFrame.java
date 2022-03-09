
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JDialog;
import javax.swing.table.*;
import java.io.*;

/**
 *                                                 
 * Beschreibung
 *
 * @version 1.0 vom 03.01.2022
 * @author 
 */

public class MainFrame extends JFrame {
  // Anfang Attribute
    private DB db = new DB();
    private int status;
    private Kundewaehlen kw; 
    private Geraetewaehlen gw;
    private Rechnunghinzufuegen rh;
  

  private JButton bHinzufuegen = new JButton();
  
  private JButton bBearbeiten = new JButton();
  private JNumberField jNumberField1 = new JNumberField();
  private JTable mainTable = new JTable(5, 5);
    private DefaultTableModel mainTableModel = (DefaultTableModel) mainTable.getModel();
    private JScrollPane mainTableScrollPane = new JScrollPane(mainTable);
  private JButton bKunden = new JButton();
  private JButton bGeraete = new JButton();
  private JButton bRechnungen = new JButton();
  private JButton bDrucken = new JButton();
  private JButton bLoeschen1 = new JButton();
  private JButton bDiagramme = new JButton();
  // Ende Attribute
  
  public MainFrame() {        // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 1103; 
    int frameHeight = 653;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("UCfKGeraeteverleih");
    setResizable(true);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    bHinzufuegen.setBounds(8, 568, 147, 25);
    bHinzufuegen.setText("hinzufuegen");
    bHinzufuegen.setMargin(new Insets(2, 2, 2, 2));
    bHinzufuegen.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bHinzufuegen_ActionPerformed(evt);
      }
    });
    cp.add(bHinzufuegen);

    bBearbeiten.setBounds(168, 568, 147, 25);
    bBearbeiten.setText("bearbeiten");
    bBearbeiten.setMargin(new Insets(2, 2, 2, 2));
    bBearbeiten.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bBearbeiten_ActionPerformed(evt);
      }
    });
    cp.add(bBearbeiten);
    jNumberField1.setBounds(88, 544, 1, 17);
    jNumberField1.setText("");
    cp.add(jNumberField1);
    mainTableScrollPane.setBounds(8, 48, 1060, 486);
    mainTable.getColumnModel().getColumn(0).setHeaderValue("Title 1");
    mainTable.getColumnModel().getColumn(1).setHeaderValue("Title 2");
    mainTable.getColumnModel().getColumn(2).setHeaderValue("Title 3");
    mainTable.getColumnModel().getColumn(3).setHeaderValue("Title 4");
    mainTable.getColumnModel().getColumn(4).setHeaderValue("Title 5");
    cp.add(mainTableScrollPane);
    bKunden.setBounds(16, 8, 99, 25);
    bKunden.setText("Kunden");
    bKunden.setMargin(new Insets(2, 2, 2, 2));
    bKunden.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bKunden_ActionPerformed(evt);
      }
    });
    cp.add(bKunden);
    bGeraete.setBounds(136, 8, 99, 25);
    bGeraete.setText("Geräte");
    bGeraete.setMargin(new Insets(2, 2, 2, 2));
    bGeraete.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bGeraete_ActionPerformed(evt);
      }
    });
    cp.add(bGeraete);
    bRechnungen.setBounds(256, 8, 99, 25);
    bRechnungen.setText("Rechnungen");
    bRechnungen.setMargin(new Insets(2, 2, 2, 2));
    bRechnungen.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bRechnungen_ActionPerformed(evt);
      }
    });
    cp.add(bRechnungen);
    bDrucken.setBounds(488, 568, 147, 25);
    bDrucken.setText("drucken");
    bDrucken.setMargin(new Insets(2, 2, 2, 2));
    bDrucken.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bDrucken_ActionPerformed(evt);
      }
    });
    cp.add(bDrucken);
    bLoeschen1.setBounds(328, 568, 147, 25);
    bLoeschen1.setText("löschen");
    bLoeschen1.setMargin(new Insets(2, 2, 2, 2));
    bLoeschen1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bLoeschen1_ActionPerformed(evt);
      }
    });
    cp.add(bLoeschen1);
    bDiagramme.setBounds(376, 8, 99, 25);
    bDiagramme.setText("Diagramme");
    bDiagramme.setMargin(new Insets(2, 2, 2, 2));
    bDiagramme.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bDiagramme_ActionPerformed(evt);
      }
    });
    cp.add(bDiagramme);
    // Ende Komponenten
    
       
    status = 2;
    aktualisieren();
    setVisible(true);
  } // end of public MainFrame
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new MainFrame();
  } // end of main
  
  
  public void bHinzufuegen_ActionPerformed(ActionEvent evt) {
     switch (status) {
      case 0: 
        Kundehinzufuegen kh = new Kundehinzufuegen(this, true, null);
        aktualisieren();
        break;
      case 1: 
        Geraethinzufuegen gh = new Geraethinzufuegen(this, true, null);
        aktualisieren();
        break;
      case 2:
        kw = new Kundewaehlen(this, true);
        if (kw.getKunde() != null) {
           gw = new Geraetewaehlen(this, true);
        } // end of if
        if (gw.getGereat().size() != 0){
           rh = new Rechnunghinzufuegen(this, true, kw.getKunde(), gw.getGereat());
          if (rh.getRechnung() != null) {
            Rechnungdrucken rd = new Rechnungdrucken(this, true, rh.getRechnung(), true);
          } // end of if
        }
        aktualisieren();
        break;
    } // end of switch 
  } // end of bHinzufuegen_ActionPerformed

  public void loadTableKunde(ArrayList<Kunde> k){
    mainTableModel.setNumRows(0);
    String[] colname = {"ID", "Name", "Vorname",  "Strasse", "Hausnummer",  "Postleitzahl", "Wohnort",  "Mitglied"};
    mainTableModel.setColumnIdentifiers(colname);
    for (int i = 0; i < k.size(); i++) {
      String[] row = {k.get(i).getK_id()+"", k.get(i).getName(), k.get(i).getVorname(),k.get(i).getStrasse(), k.get(i).getHausnummer(), k.get(i).getPlz(), k.get(i).getOrt(),  k.get(i).getMitglied()};
      mainTableModel.addRow(row);
    }
    }
  
  public void loadTabelleGeraet(ArrayList<Geraet> g) {
    mainTableModel.setNumRows(0);
    String[] colname = {"ID", "Bezeichnung", "Anschaffungspreis", "Anschaffungsdatum", "Mietpreisklasse1", "Mietpreisklasse2", "Mietpreisklasse3", "Zustand"};
    mainTableModel.setColumnIdentifiers(colname);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    for (int i = 0; i < g.size(); i++) {   
      String[] row = {g.get(i).getG_id()+"", g.get(i).getBezeichnung(), g.get(i).getAnschaffungspreis()+"€", g.get(i).getAnschaffungsdatum().format(formatter), g.get(i).getMietpreisklasse()[0]+"€", g.get(i).getMietpreisklasse()[1]+"€", g.get(i).getMietpreisklasse()[2]+"€", g.get(i).getZustand()};
      mainTableModel.addRow(row);
    }
  }
  
  public void loadTabelleMietverhaeltnisse(ArrayList<Rechnung> r) {
    mainTableModel.setNumRows(0);
    String[] colname = {"R_ID","M_ID","Kunde", "Geraet", "Abholdatum", "Ruegabedatum", "Zurueckgeben", "Bezahlt"};
    mainTableModel.setColumnIdentifiers(colname);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    
    for (int i = r.size()-1; i > -1; i--) {   
      String[] rowkunde = {r.get(i).getR_id()+"","",r.get(i).getKundenname()+", "+ r.get(i).getKundenvorname(),"", "", "", "", r.get(i).getStatus()+" "};
      mainTableModel.addRow(rowkunde);
      for (int j = 0; j < r.get(i).getMietvertraege().size(); j++) {
        String mstatus = "NEIN";
        if (r.get(i).getMietvertraege().get(j).getStatus()){mstatus = "JA";}
        String rueckgabe = r.get(i).getMietvertraege().get(j).getRueckgabe().format(formatter);
        String[] rowgeraet = {"",""+ r.get(i).getMietvertraege().get(j).getM_id(),"",r.get(i).getMietvertraege().get(j).getGeraet().getBezeichnung(), r.get(i).getMietvertraege().get(j).getAbgabe().format(formatter), rueckgabe, mstatus, ""};
        mainTableModel.addRow(rowgeraet); 
      }
    }
  } 
  
  public void bBearbeiten_ActionPerformed(ActionEvent evt) {
    switch (status) {
      case  0: 
        Kundehinzufuegen kh = new Kundehinzufuegen(this, true, db.ladeKunde(Integer.parseInt(mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString())));
        aktualisieren();
        break;
      case  1: 
        Geraethinzufuegen gh = new Geraethinzufuegen(this, true, db.ladeGeraet(Integer.parseInt(mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString())));
        aktualisieren();
        break;        
      case 2:
        //tMietverhaeltnisse.getSelectedRowCount();
        if (mainTable.getValueAt(mainTable.getSelectedRow(), 2).toString().equals("")) {
          for (int i = 0; i < mainTable.getSelectedRowCount(); i++) {
            db.setMietvertragstatus((Integer.parseInt(mainTable.getValueAt(mainTable.getSelectedRow()+i, 1).toString())) , true);
          }
        } else {
          db.setRechnungstatus((Integer.parseInt(mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString())) , true);  
          } // end of if-else
        aktualisieren();
    }
  } // end of bBearbeiten_ActionPerformed

  public void bKunden_ActionPerformed(ActionEvent evt) {
    status = 0; 
    aktualisieren();
  } // end of bKunden_ActionPerformed

  public void bGeraete_ActionPerformed(ActionEvent evt) {
    status = 1;
    aktualisieren();
  } // end of bGeraete_ActionPerformed

  public void bRechnungen_ActionPerformed(ActionEvent evt) {
    status = 2;
    aktualisieren();
  } // end of bRechnungen_ActionPerformed
  
  public void bDiagramme_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    
  } // end of bDiagramme_ActionPerformed
  
  public void aktualisieren(){
    switch (status) {
      case  0: 
        loadTableKunde(db.ladeKunden("WHERE Name != '' and Vorname != ''"));
        bDrucken.setVisible(false);
        break;
      case  1: 
        loadTabelleGeraet(db.ladeGeraete(""));
        bDrucken.setVisible(true);
        bDrucken.setText("aus Datei hinzufuegen");
        break;        
      case 2:
        loadTabelleMietverhaeltnisse(db.ladeRechnungen(""));
        bDrucken.setVisible(true);
        bDrucken.setText("drucken");
        break;
    }
  }
  
  public void bDrucken_ActionPerformed(ActionEvent evt) {
     switch (status) {
      case  0: 
        break;
      case  1: 
        Geraeteinlesen ge = new Geraeteinlesen(this, true);
        break;        
      case 2:
        int i = 0; 
        while (mainTable.getValueAt(mainTable.getSelectedRow()-i, 2).toString().equals("")) { 
          i++;  
        } // end of while( {
        Rechnungdrucken rd = new Rechnungdrucken(this, true, db.ladeRechnung(Integer.parseInt(mainTable.getValueAt(mainTable.getSelectedRow()-i, 0).toString())), true);
        aktualisieren();
    }
  } // end of bDrucken_ActionPerformed

  public void bLoeschen1_ActionPerformed(ActionEvent evt) {
    switch (status) {
      case  0: 
        db.ladeKunde(Integer.parseInt(mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString())).loeschen();
        aktualisieren();
        break;
      case  1: 
        db.ladeGeraet(Integer.parseInt(mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString())).loeschen();
        aktualisieren();
        break;        
      case 2:
        //tMietverhaeltnisse.getSelectedRowCount();
        if (mainTable.getValueAt(mainTable.getSelectedRow(), 2).toString().equals("")) {
          for (int i = 0; i < mainTable.getSelectedRowCount(); i++) {
            db.ladeMietvertrag((Integer.parseInt(mainTable.getValueAt(mainTable.getSelectedRow()+i, 1).toString()))).loeschen();
          }
        } else {
          db.ladeRechnung((Integer.parseInt(mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString()))).loeschen();  
          } // end of if-else
        aktualisieren();
    }
  } // end of bLoeschen1_ActionPerformed
  // Ende Methoden
} // end of class MainFrame


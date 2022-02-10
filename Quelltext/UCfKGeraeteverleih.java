
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JDialog;
import javax.swing.table.*;

/**
 *                                                 
 * Beschreibung
 *
 * @version 1.0 vom 03.01.2022
 * @author 
 */

public class UCfKGeraeteverleih extends JFrame {
  // Anfang Attribute
    private DB db = new DB();
    private int status = 2;
    private Kundewaehlen kw; 
    private Geraetewaehlen gw;
    private Mietvertraegehinzufuegen mh;
  

  private JButton bHinzufuegen = new JButton();
  
  private JButton bAendern = new JButton();
  private JNumberField jNumberField1 = new JNumberField();
  private JLabel lSelected = new JLabel();
  private JTable mainTable = new JTable(5, 5);
    private DefaultTableModel mainTableModel = (DefaultTableModel) mainTable.getModel();
    private JScrollPane mainTableScrollPane = new JScrollPane(mainTable);
  private JButton bKunden = new JButton();
  private JButton bGeraete = new JButton();
  private JButton bRechnungen = new JButton();
  // Ende Attribute
  
  public UCfKGeraeteverleih() {        // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 1105; 
    int frameHeight = 642;
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

    bAendern.setBounds(168, 568, 147, 25);
    bAendern.setText("ändern");
    bAendern.setMargin(new Insets(2, 2, 2, 2));
    bAendern.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bAendern_ActionPerformed(evt);
      }
    });
    cp.add(bAendern);
    jNumberField1.setBounds(88, 544, 1, 17);
    jNumberField1.setText("");
    cp.add(jNumberField1);
    lSelected.setBounds(8, 536, 934, 20);
    lSelected.setText("");
    cp.add(lSelected);
    mainTableScrollPane.setBounds(8, 48, 1060, 486);
    mainTable.getColumnModel().getColumn(0).setHeaderValue("Title 1");
    mainTable.getColumnModel().getColumn(1).setHeaderValue("Title 2");
    mainTable.getColumnModel().getColumn(2).setHeaderValue("Title 3");
    mainTable.getColumnModel().getColumn(3).setHeaderValue("Title 4");
    mainTable.getColumnModel().getColumn(4).setHeaderValue("Title 5");
    cp.add(mainTableScrollPane);
    bKunden.setBounds(8, 8, 99, 25);
    bKunden.setText("Kunden");
    bKunden.setMargin(new Insets(2, 2, 2, 2));
    bKunden.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bKunden_ActionPerformed(evt);
      }
    });
    cp.add(bKunden);
    bGeraete.setBounds(128, 8, 99, 25);
    bGeraete.setText("Geräte");
    bGeraete.setMargin(new Insets(2, 2, 2, 2));
    bGeraete.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bGeraete_ActionPerformed(evt);
      }
    });
    cp.add(bGeraete);
    bRechnungen.setBounds(248, 8, 99, 25);
    bRechnungen.setText("Rechnungen");
    bRechnungen.setMargin(new Insets(2, 2, 2, 2));
    bRechnungen.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bRechnungen_ActionPerformed(evt);
      }
    });
    cp.add(bRechnungen);
    // Ende Komponenten
    setVisible(true);
    // Kunden Laden
  } // end of public UCfKGeraeteverleih
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new UCfKGeraeteverleih();

  } // end of main
  
  /*public void kundenTable(){
    tKundeModel.setNumRows(0);
    String[] colname = {"ID", "Name", "Vorname", "Wohnort", "Postleitzahl", "Mitglied"};
    tKundeModel.setColumnIdentifiers(colname);
    for (int i = 1; i < db.kundenzahlLaden() +1; i++) {   
      Kunde k = db.kundeLaden(i);   
      String[] row = {k.getId()+"", k.getName(), k.getVorname(), k.getOrt(), k.getPlz(), k.getMitglied()};
      tKundeModel.addRow(row);
    }
  }
  
  /*/ 
  
  
  public void bHinzufuegen_ActionPerformed(ActionEvent evt) {
     switch (status) {
      case 0: 
        Kundehinzufuegen kh = new Kundehinzufuegen();
        break;
      case 1: 
        Geraethinzufuegen gh = new Geraethinzufuegen(this, true);
        break;
      case 2:
        kw = new Kundewaehlen(this, true);
        if (kw.getKunde() != null) {
           gw = new Geraetewaehlen(this, true);
        } // end of if
        if (gw.getGereat().size() != 0){
           mh = new Mietvertraegehinzufuegen(this, true, kw.getKunde(), gw.getGereat());
          }
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
    String[] colname = {"Kunde", "Geraet", "Abholdatum", "Ruegabedatum", "Zurueckgeben", "Bezahlt"};
    mainTableModel.setColumnIdentifiers(colname);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    
    for (int i = r.size()-1; i > -1; i--) {   
      String[] rowkunde = {r.get(i).getKundenname()+", "+ r.get(i).getKundenvorname(),"", "", "", "", r.get(i).getStatus()+" "};
      mainTableModel.addRow(rowkunde);
      for (int j = 0; j < r.get(i).getMietvertraege().size(); j++) {
        String mstatus = "NEIN";
        if (r.get(i).getMietvertraege().get(j).getStatus()){mstatus = "Ja";}
        String rueckgabe = r.get(i).getMietvertraege().get(j).getRueckgabe().format(formatter);
        String[] rowgeraet = {"",r.get(i).getMietvertraege().get(j).getGeraet().getBezeichnung(), r.get(i).getMietvertraege().get(j).getAbgabe().format(formatter), rueckgabe, mstatus, ""};
        mainTableModel.addRow(rowgeraet);
        
      }
      
    }
  } 
  public void bAendern_ActionPerformed(ActionEvent evt) {
    switch (status) {
      case  0: 
        //tKunde.getSelectedRowCount();
        break;
      case  1: 
        //tGeraet.getSelectedRowCount();
        break;        
      case 2:
        //tMietverhaeltnisse.getSelectedRowCount();
        
    }
    
  } // end of bAendern_ActionPerformed

  public void bKunden_ActionPerformed(ActionEvent evt) {
    status = 0; 
    loadTableKunde(db.ladeKunden());
  } // end of bKunden_ActionPerformed

  public void bGeraete_ActionPerformed(ActionEvent evt) {
    status = 1;
    loadTabelleGeraet(db.ladeGeraete(""));
  } // end of bGeraete_ActionPerformed

  public void bRechnungen_ActionPerformed(ActionEvent evt) {
    status = 2;
    loadTabelleMietverhaeltnisse(db.ladeRechnungen());
    
  } // end of bRechnungen_ActionPerformed

  // Ende Methoden
} // end of class UCfKGeraeteverleih


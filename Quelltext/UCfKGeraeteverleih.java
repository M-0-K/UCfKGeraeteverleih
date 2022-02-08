
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
    private String status = "Kunde";
    private Kundewaehlen kw; 
    private Geraetewaehlen gw;
    private Mietvertraegehinzufuegen mh;

  private JButton bHinzufuegen = new JButton();
  
  private JTabbedPane jTabbedPane1 = new JTabbedPane();
    private JPanel jTabbedPane1TabPanelKunde = new JPanel(null, true);
    private JPanel jTabbedPane1TabPanelGeraet = new JPanel(null, true);
  private JTable tKunde = new JTable(5, 5);
    private DefaultTableModel tKundeModel = (DefaultTableModel) tKunde.getModel();
    private JScrollPane tKundeScrollPane = new JScrollPane(tKunde);
  private JTable tGeraet = new JTable(5, 5);
    private DefaultTableModel tGeraetModel = (DefaultTableModel) tGeraet.getModel();
    private JScrollPane tGeraetScrollPane = new JScrollPane(tGeraet);
    private JPanel jTabbedPane1TabPanelMietverhältnisse = new JPanel(null, true);
  private JTable tMietverhaeltnisse = new JTable(5, 5);
    private DefaultTableModel tMietverhaeltnisseModel = (DefaultTableModel) tMietverhaeltnisse.getModel();
    private JScrollPane tMietverhaeltnisseScrollPane = new JScrollPane(tMietverhaeltnisse);
    private JPanel jTabbedPane1TabPanelRechnung = new JPanel(null, true);
      private JButton bDrucken = new JButton();
  // Ende Attribute
  
  public UCfKGeraeteverleih() {        // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 961; 
    int frameHeight = 627;
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
    
    bHinzufuegen.setBounds(8, 528, 147, 25);
    bHinzufuegen.setText("hinzufuegen");
    bHinzufuegen.setMargin(new Insets(2, 2, 2, 2));
    bHinzufuegen.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bHinzufuegen_ActionPerformed(evt);
      }
    });
    cp.add(bHinzufuegen);

    jTabbedPane1.setBounds(8, 8, 936, 518);
    jTabbedPane1.addTab("Kunde", jTabbedPane1TabPanelKunde);
    jTabbedPane1.addTab("Geraet", jTabbedPane1TabPanelGeraet);
    jTabbedPane1.addTab("Mietverhältnisse", jTabbedPane1TabPanelMietverhältnisse);
    jTabbedPane1.addTab("Rechnung", jTabbedPane1TabPanelRechnung);
    jTabbedPane1.addChangeListener(new ChangeListener() { 
      public void stateChanged(ChangeEvent evt) { 
        jTabbedPane1_StateChanged(evt);
      }
    });
    jTabbedPane1.setSelectedIndex(3);
    cp.add(jTabbedPane1);
    tKundeScrollPane.setBounds(6, 8, 924, 478);
    tKunde.getColumnModel().getColumn(0).setHeaderValue("Title 1");
    tKunde.getColumnModel().getColumn(1).setHeaderValue("Title 2");
    tKunde.getColumnModel().getColumn(2).setHeaderValue("Title 3");
    tKunde.getColumnModel().getColumn(3).setHeaderValue("Title 4");
    tKunde.getColumnModel().getColumn(4).setHeaderValue("Title 5");
    jTabbedPane1TabPanelKunde.add(tKundeScrollPane);
    tGeraetScrollPane.setBounds(6, 8, 924, 478);
    tGeraet.getColumnModel().getColumn(0).setHeaderValue("Title 1");
    tGeraet.getColumnModel().getColumn(1).setHeaderValue("Title 2");
    tGeraet.getColumnModel().getColumn(2).setHeaderValue("Title 3");
    tGeraet.getColumnModel().getColumn(3).setHeaderValue("Title 4");
    tGeraet.getColumnModel().getColumn(4).setHeaderValue("Title 5");
    jTabbedPane1TabPanelGeraet.add(tGeraetScrollPane);
    tMietverhaeltnisseScrollPane.setBounds(-2, 0, 932, 486);
    tMietverhaeltnisse.getColumnModel().getColumn(0).setHeaderValue("Title 1");
    tMietverhaeltnisse.getColumnModel().getColumn(1).setHeaderValue("Title 2");
    tMietverhaeltnisse.getColumnModel().getColumn(2).setHeaderValue("Title 3");
    tMietverhaeltnisse.getColumnModel().getColumn(3).setHeaderValue("Title 4");
    tMietverhaeltnisse.getColumnModel().getColumn(4).setHeaderValue("Title 5");
    jTabbedPane1TabPanelMietverhältnisse.add(tMietverhaeltnisseScrollPane);
    bDrucken.setBounds(374, 456, 147, 25);
    bDrucken.setText("Drucken");
    bDrucken.setMargin(new Insets(2, 2, 2, 2));
    bDrucken.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bDrucken_ActionPerformed(evt);
      }
    });
    jTabbedPane1TabPanelRechnung.add(bDrucken);
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
     switch (jTabbedPane1.getSelectedIndex()) {
      case  0: 
        Kundehinzufuegen kh = new Kundehinzufuegen();
        break;
      case  1: 
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
    tKundeModel.setNumRows(0);
    String[] colname = {"ID", "Name", "Vorname",  "Strasse", "Hausnummer",  "Postleitzahl", "Wohnort",  "Mitglied"};
    tKundeModel.setColumnIdentifiers(colname);
    for (int i = 0; i < k.size(); i++) {
      String[] row = {k.get(i).getK_id()+"", k.get(i).getName(), k.get(i).getVorname(),k.get(i).getStrasse(), k.get(i).getHausnummer(), k.get(i).getPlz(), k.get(i).getOrt(),  k.get(i).getMitglied()};
      tKundeModel.addRow(row);
    }
    }
  
  public void loadTabelleGeraet(ArrayList<Geraet> g) {
    tGeraetModel.setNumRows(0);
    String[] colname = {"ID", "Bezeichnung", "Anschaffungspreis", "Anschaffungsdatum", "Mietpreisklasse1", "Mietpreisklasse2", "Mietpreisklasse3", "Zustand"};
    tGeraetModel.setColumnIdentifiers(colname);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    for (int i = 0; i < g.size(); i++) {   
      String[] row = {g.get(i).getG_id()+"", g.get(i).getBezeichnung(), g.get(i).getAnschaffungspreis()+"€", g.get(i).getAnschaffungsdatum().format(formatter), g.get(i).getMietpreisklasse()[0]+"€", g.get(i).getMietpreisklasse()[1]+"€", g.get(i).getMietpreisklasse()[2]+"€", g.get(i).getZustand()};
      tGeraetModel.addRow(row);
    }
  }
  
  public void loadTabelleMietverhaeltnisse(ArrayList<Rechnung> r) {
    tMietverhaeltnisseModel.setNumRows(0);
    String[] colname = {"Kunde", "Geraet", "Abholdatum", "Ruegabedatum", "Zurueckgeben", "Bezahlt"};
    tMietverhaeltnisseModel.setColumnIdentifiers(colname);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    
    for (int i = r.size()-1; i > -1; i--) {   
      String[] rowkunde = {r.get(i).getKundenname()+", "+ r.get(i).getKundenvorname(),"", "", "", "", r.get(i).getStatus()+" "};
      tMietverhaeltnisseModel.addRow(rowkunde);
      for (int j = 0; j < r.get(i).getMietvertraege().size(); j++) {
        String status = "NEIN";
        if (r.get(i).getMietvertraege().get(j).getStatus()){status = "Ja";}
        String rueckgabe = r.get(i).getMietvertraege().get(j).getRueckgabe().format(formatter);
        String[] rowgeraet = {"",r.get(i).getMietvertraege().get(j).getGeraet().getBezeichnung(), r.get(i).getMietvertraege().get(j).getAbgabe().format(formatter), rueckgabe, status, ""};
        tMietverhaeltnisseModel.addRow(rowgeraet);
        
      }
      
    }
  } 
  public void jTabbedPane1_StateChanged(ChangeEvent evt) {
    switch (jTabbedPane1.getSelectedIndex()) {
      case  0: 
        loadTableKunde(db.ladeKunden());
        break;
      case  1: 
        loadTabelleGeraet(db.ladeGeraete(""));
        break;        
      case 2:
        loadTabelleMietverhaeltnisse(db.ladeRechnungen());
        
    } // end of switch
    
  } // end of jTabbedPane1_StateChanged

  public void bDrucken_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    
  } // end of bDrucken_ActionPerformed

  // Ende Methoden
} // end of class UCfKGeraeteverleih


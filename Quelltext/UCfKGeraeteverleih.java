
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
  private JButton bKundehinzufuegen = new JButton();
  private JButton bGeraethinzufuegen = new JButton();
  private JRadioButton rbInMietverhaeltnis = new JRadioButton();
  
  private JTabbedPane jTabbedPane1 = new JTabbedPane();
    private JPanel jTabbedPane1TabPanelKunde = new JPanel(null, true);
    private JPanel jTabbedPane1TabPanelGeraet = new JPanel(null, true);
  private JTable tKunde = new JTable(5, 5);
    private DefaultTableModel tKundeModel = (DefaultTableModel) tKunde.getModel();
    private JScrollPane tKundeScrollPane = new JScrollPane(tKunde);
  private JTable tGeraet = new JTable(5, 5);
    private DefaultTableModel tGeraetModel = (DefaultTableModel) tGeraet.getModel();
    private JScrollPane tGeraetScrollPane = new JScrollPane(tGeraet);
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
    
    bKundehinzufuegen.setBounds(288, 512, 147, 25);
    bKundehinzufuegen.setText("Kunde hinzufuegen");
    bKundehinzufuegen.setMargin(new Insets(2, 2, 2, 2));
    bKundehinzufuegen.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bKundehinzufuegen_ActionPerformed(evt);
      }
    });
    cp.add(bKundehinzufuegen);
    bGeraethinzufuegen.setBounds(136, 512, 147, 25);
    bGeraethinzufuegen.setText("Geraet hinzufuegen");
    bGeraethinzufuegen.setMargin(new Insets(2, 2, 2, 2));
    bGeraethinzufuegen.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bGeraethinzufuegen_ActionPerformed(evt);
      }
    });
    cp.add(bGeraethinzufuegen);
    rbInMietverhaeltnis.setBounds(10, 510, 124, 20);
    rbInMietverhaeltnis.setOpaque(false);
    rbInMietverhaeltnis.setText("in Mietverhältnis");
    rbInMietverhaeltnis.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        rbInMietverhaeltnis_ActionPerformed(evt);
      }
    });
    cp.add(rbInMietverhaeltnis);

    jTabbedPane1.setBounds(16, 16, 888, 486);
    jTabbedPane1.addTab("Kunde", jTabbedPane1TabPanelKunde);
    jTabbedPane1.addTab("Geraet", jTabbedPane1TabPanelGeraet);
    jTabbedPane1.addChangeListener(new ChangeListener() { 
      public void stateChanged(ChangeEvent evt) { 
        jTabbedPane1_StateChanged(evt);
      }
    });
    jTabbedPane1.setSelectedIndex(0);
    cp.add(jTabbedPane1);
    tKundeScrollPane.setBounds(6, 8, 716, 406);
    tKunde.getColumnModel().getColumn(0).setHeaderValue("Title 1");
    tKunde.getColumnModel().getColumn(1).setHeaderValue("Title 2");
    tKunde.getColumnModel().getColumn(2).setHeaderValue("Title 3");
    tKunde.getColumnModel().getColumn(3).setHeaderValue("Title 4");
    tKunde.getColumnModel().getColumn(4).setHeaderValue("Title 5");
    jTabbedPane1TabPanelKunde.add(tKundeScrollPane);
    tGeraetScrollPane.setBounds(6, 0, 868, 454);
    tGeraet.getColumnModel().getColumn(0).setHeaderValue("Title 1");
    tGeraet.getColumnModel().getColumn(1).setHeaderValue("Title 2");
    tGeraet.getColumnModel().getColumn(2).setHeaderValue("Title 3");
    tGeraet.getColumnModel().getColumn(3).setHeaderValue("Title 4");
    tGeraet.getColumnModel().getColumn(4).setHeaderValue("Title 5");
    jTabbedPane1TabPanelGeraet.add(tGeraetScrollPane);
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
  
  
  public void bKundehinzufuegen_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    Kundehinzufuegen kh = new Kundehinzufuegen(); 
  } // end of bKundehinzufuegen_ActionPerformed

  public void bGeraethinzufuegen_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    Geraethinzufuegen gh = new Geraethinzufuegen();
  } // end of bGeraethinzufuegen_ActionPerformed

  public void rbInMietverhaeltnis_ActionPerformed(ActionEvent evt) {
    /*if (rbInMietverhaeltnis.isSelected()) {
      if ("Geraet".equals(status)) {
        geraeteTable(db.rechnungLaden(1).getGereate());
      } else {
        
      } // end of if-else
    } else {
      
    } // end of if-else    */
    
  } // end of rbInMietverhaeltnis_ActionPerformed

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
  public void jTabbedPane1_StateChanged(ChangeEvent evt) {
    switch (jTabbedPane1.getSelectedIndex()) {
      case  0: 
        loadTableKunde(db.ladeKunden());
        break;
      case  1: 
        loadTabelleGeraet(db.ladeGeraete());
        break;
        
    } // end of switch
    
  } // end of jTabbedPane1_StateChanged

  // Ende Methoden
} // end of class UCfKGeraeteverleih


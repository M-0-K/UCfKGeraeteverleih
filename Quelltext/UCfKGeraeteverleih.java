import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.JDialog;
import java.awt.event.MouseEvent;

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
    private JTable jTable1 = new JTable(5, 5);
    private DefaultTableModel jTable1Model = (DefaultTableModel) jTable1.getModel();
    private JScrollPane jTable1ScrollPane = new JScrollPane(jTable1);
  private JButton bKunden = new JButton();
  private JButton bGereate = new JButton();
  private JButton bKundehinzufuegen = new JButton();
  private JButton bGeraethinzufuegen = new JButton();
  private JButton bLoeschen = new JButton();
  private JRadioButton rbInMietverhaeltnis = new JRadioButton();
  private ButtonGroup buttonGroup1 = new ButtonGroup();
  
  private ButtonGroup buttonGroup2 = new ButtonGroup();
  // Ende Attribute
  
  public UCfKGeraeteverleih() { 
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 967; 
    int frameHeight = 595;
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
    
    jTable1ScrollPane.setBounds(17, 29, 772, 366);
    jTable1.getColumnModel().getColumn(0).setHeaderValue("Title 1");
    jTable1.getColumnModel().getColumn(1).setHeaderValue("Title 2");
    jTable1.getColumnModel().getColumn(2).setHeaderValue("Title 3");
    jTable1.getColumnModel().getColumn(3).setHeaderValue("Title 4");
    jTable1.getColumnModel().getColumn(4).setHeaderValue("Title 5");
    cp.add(jTable1ScrollPane);
    bKunden.setBounds(17, 3, 75, 25);
    bKunden.setText("Kunden");
    bKunden.setMargin(new Insets(2, 2, 2, 2));
    bKunden.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bKunden_ActionPerformed(evt);
      }
    });
    cp.add(bKunden);
    bGereate.setBounds(96, 3, 75, 25);
    bGereate.setText("Gereate");
    bGereate.setMargin(new Insets(2, 2, 2, 2));
    bGereate.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bGereate_ActionPerformed(evt);
      }
    });
    cp.add(bGereate);
    bKundehinzufuegen.setBounds(792, 32, 147, 25);
    bKundehinzufuegen.setText("Kunde hinzufuegen");
    bKundehinzufuegen.setMargin(new Insets(2, 2, 2, 2));
    bKundehinzufuegen.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bKundehinzufuegen_ActionPerformed(evt);
      }
    });
    cp.add(bKundehinzufuegen);
    bGeraethinzufuegen.setBounds(792, 64, 147, 25);
    bGeraethinzufuegen.setText("Geraet hinzufuegen");
    bGeraethinzufuegen.setMargin(new Insets(2, 2, 2, 2));
    bGeraethinzufuegen.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bGeraethinzufuegen_ActionPerformed(evt);
      }
    });
    cp.add(bGeraethinzufuegen);
    bLoeschen.setBounds(1736, 96, 147, 25);
    bLoeschen.setText("Loeschen");
    bLoeschen.setMargin(new Insets(2, 2, 2, 2));
    bLoeschen.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bLoeschen_ActionPerformed(evt);
      }
    });
    cp.add(bLoeschen);
    rbInMietverhaeltnis.setBounds(18, 398, 124, 20);
    rbInMietverhaeltnis.setOpaque(false);
    rbInMietverhaeltnis.setText("in Mietverhältnis");
    rbInMietverhaeltnis.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        rbInMietverhaeltnis_ActionPerformed(evt);
      }
    });
    cp.add(rbInMietverhaeltnis);
    // Ende Komponenten
    setVisible(true);
   
    
    
    // Kunden Laden
  } // end of public UCfKGeraeteverleih
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new UCfKGeraeteverleih();

  } // end of main
  
  public void kundenTable(){
    jTable1Model.setNumRows(0);
    String[] colname = {"ID", "Name", "Vorname", "Wohnort", "Postleitzahl", "Mitglied"};
    jTable1Model.setColumnIdentifiers(colname);
    for (int i = 1; i < db.kundenzahlLaden() +1; i++) {   
      Kunde k = db.kundeLaden(i);   
      String[] row = {k.getId()+"", k.getName(), k.getVorname(), k.getOrt(), k.getPlz(), k.getMitglied()};
      jTable1Model.addRow(row);
    }
  }
  
  public void geraeteTable(ArrayList<Geraet> g) {
    jTable1Model.setNumRows(0);
    String[] colname = {"ID", "Bezeichnung", "Anschaffungspreis", "Anschaffungsdatum", "Mietpreisklasse1", "Mietpreisklasse2", "Mietpreisklasse3", "Zustand"};
    jTable1Model.setColumnIdentifiers(colname);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    for (int i = 0; i < g.size(); i++) {   
      String[] row = {g.get(i).getId()+"", g.get(i).getBezeichnung(), g.get(i).getAnschaffungspreis()+"€", g.get(i).getAnschaffungsdatum().format(formatter), g.get(i).getMietpreise()[0]+"€", g.get(i).getMietpreise()[1]+"€", g.get(i).getMietpreise()[2]+"€", g.get(i).getZustand()};
      jTable1Model.addRow(row);
    }
  }  
  
  
  public void bKunden_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    status = "Kunde";
    kundenTable();
  } // end of bKunden_ActionPerformed

  public void bGereate_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    status = "Geraet";
    ArrayList<Geraet> g = new ArrayList<Geraet>();
    for (int i = 1; i < db.geraetzahlLaden()+1; i++) {
      g.add(db.geraetLaden(i));
    }
    geraeteTable(g);
  } // end of bGereate_ActionPerformed

  public void bKundehinzufuegen_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    Kundehinzufuegen kh = new Kundehinzufuegen(); 
  } // end of bKundehinzufuegen_ActionPerformed

  public void bGeraethinzufuegen_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    Geraethinzufuegen gh = new Geraethinzufuegen();
  } // end of bGeraethinzufuegen_ActionPerformed

  public void bLoeschen_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    //db.loeschen("geraet","G_id",jTable1.getSelectedRow()+1);
    System.out.println(jTable1.getSelectedRow()+1);
  } // end of bLoeschen_ActionPerformed

  public String buttonGroup1_getSelectedRadioButtonLabel() {
    for (java.util.Enumeration<AbstractButton> e = buttonGroup1.getElements(); e.hasMoreElements();) {
      AbstractButton b = e.nextElement();
      if (b.isSelected()) return b.getText();
    }
    return "";
  }

  public void rbInMietverhaeltnis_ActionPerformed(ActionEvent evt) {
    if (rbInMietverhaeltnis.isSelected()) {
      if ("Geraet".equals(status)) {
        geraeteTable(db.rechnungLaden(1).getGereate());
      } else {
        
      } // end of if-else
    } else {
      
    } // end of if-else
    
  } // end of rbInMietverhaeltnis_ActionPerformed

  public String buttonGroup2_getSelectedRadioButtonLabel() {
    for (java.util.Enumeration<AbstractButton> e = buttonGroup2.getElements(); e.hasMoreElements();) {
      AbstractButton b = e.nextElement();
      if (b.isSelected()) return b.getText();
    }
    return "";
  }

  // Ende Methoden
} // end of class UCfKGeraeteverleih


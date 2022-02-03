import org.jdatepicker.impl.*;
import org.jdatepicker.util.*;
import org.jdatepicker.*;
import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;


/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 11.01.2022
 * @author 
 */

public class Mietvertraegehinzufuegen extends JDialog {
  // Anfang Attribute
  private JTable tGeraet = new JTable(5, 5);
    private DefaultTableModel tGeraetModel = (DefaultTableModel) tGeraet.getModel();
    private JScrollPane tGeraetScrollPane = new JScrollPane(tGeraet);
  private JLabel lGeraete = new JLabel();
  private JLabel lKunde1 = new JLabel();
  private JLabel lSumme = new JLabel();
  private JNumberField nfAbTag = new JNumberField();
  private JNumberField nfAbMonat = new JNumberField();
  private JNumberField nfAbJahr = new JNumberField();
  private JLabel l = new JLabel();
  private JLabel l1 = new JLabel();
  private JLabel lAbgabedatum = new JLabel();
  private JButton bSpeichern = new JButton();
  private JButton bAbbrechen = new JButton();
  private JLabel lRueckgabedatum1 = new JLabel();
  private JNumberField nfRueTag = new JNumberField();
  private JLabel l2 = new JLabel();
  private JNumberField nfRueMonat = new JNumberField();
  private JLabel l3 = new JLabel();
  private JNumberField nfRueJahr = new JNumberField();
   

  private Kunde k;
  private ArrayList<Geraet> g;
  private ArrayList<Mietvertrag> mietvertraege = new ArrayList<Mietvertrag>();
  private JTextArea taKunde = new JTextArea("");
  private JScrollPane taKundeScrollPane = new JScrollPane(taKunde);
  private Rechnung rechnung;
  // Ende Attribute
  
  public Mietvertraegehinzufuegen(JFrame owner, boolean modal, Kunde k, ArrayList<Geraet> g) { 
    // Dialog-Initialisierung
    super(owner, modal);
    this.g = g;
    this.k = k;
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 717; 
    int frameHeight = 714;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Mietvertraegehinzufuegen");
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    tGeraetScrollPane.setBounds(16, 224, 676, 390);
    tGeraet.getColumnModel().getColumn(0).setHeaderValue("Title 1");
    tGeraet.getColumnModel().getColumn(1).setHeaderValue("Title 2");
    tGeraet.getColumnModel().getColumn(2).setHeaderValue("Title 3");
    tGeraet.getColumnModel().getColumn(3).setHeaderValue("Title 4");
    tGeraet.getColumnModel().getColumn(4).setHeaderValue("Title 5");
    cp.add(tGeraetScrollPane);
    lGeraete.setBounds(16, 200, 110, 20);
    lGeraete.setText("Geraete");
    cp.add(lGeraete);
    lKunde1.setBounds(16, 8, 110, 20);
    lKunde1.setText("Kunde:");
    cp.add(lKunde1);
    lSumme.setBounds(256, 624, 150, 20);
    lSumme.setText("Summe");
    cp.add(lSumme);
    nfAbTag.setBounds(16, 160, 35, 20);
    nfAbTag.setText("");
    cp.add(nfAbTag);
    nfAbMonat.setBounds(72, 160, 35, 20);
    nfAbMonat.setText("");
    cp.add(nfAbMonat);
    nfAbJahr.setBounds(128, 160, 59, 20);
    nfAbJahr.setText("");
    cp.add(nfAbJahr);
    l.setBounds(56, 160, 14, 20);
    l.setText(".");
    cp.add(l);
    l1.setBounds(112, 160, 14, 20);
    l1.setText(".");
    cp.add(l1);
    lAbgabedatum.setBounds(16, 136, 110, 20);
    lAbgabedatum.setText("Abgabedatum:");
    cp.add(lAbgabedatum);
    bSpeichern.setBounds(616, 632, 75, 25);
    bSpeichern.setText("Speichern");
    bSpeichern.setMargin(new Insets(2, 2, 2, 2));
    bSpeichern.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bSpeichern_ActionPerformed(evt);
      }
    });
    cp.add(bSpeichern);
    bAbbrechen.setBounds(16, 632, 75, 25);
    bAbbrechen.setText("Abbrechen");
    bAbbrechen.setMargin(new Insets(2, 2, 2, 2));
    bAbbrechen.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bAbbrechen_ActionPerformed(evt);
      }
    });
    cp.add(bAbbrechen);
    lRueckgabedatum1.setBounds(224, 136, 110, 20);
    lRueckgabedatum1.setText("Rueckgabedatum:");
    cp.add(lRueckgabedatum1);
    nfRueTag.setBounds(224, 160, 35, 20);
    nfRueTag.setText("");
    cp.add(nfRueTag);
    l2.setBounds(264, 160, 14, 20);
    l2.setText(".");
    cp.add(l2);
    nfRueMonat.setBounds(280, 160, 35, 20);
    nfRueMonat.setText("");
    cp.add(nfRueMonat);
    l3.setBounds(320, 160, 14, 20);
    l3.setText(".");
    cp.add(l3);
    nfRueJahr.setBounds(336, 160, 59, 20);
    nfRueJahr.setText("");
    cp.add(nfRueJahr);
    taKundeScrollPane.setBounds(16, 32, 208, 92);
    cp.add(taKundeScrollPane);
    // Ende Komponenten
    
    
    
    
   
    double summe = 0;
    for (int i = 0; i < g.size(); i++) {
      summe = summe + g.get(i).getMietpreisklasse()[k.getMitgliedid()-1];
    }
    
    //ArrayList<Mietvertrag> mietvertraege, LocalDate rechnungsdatum, boolean status, String kundenname, String kundenvorname, String strasse, String hausnummer, String plz, String ort, double preis
    
    
    taKunde.setText(
      k.getVorname() + " "+ k.getName() +
    "\n" + k.getStrasse() + " "+ k.getHausnummer()+
    "\n" + k.getPlz() + " " + k.getOrt()
    );
      
    LocalDate jetzt = LocalDate.now();
    DateTimeFormatter jahr = DateTimeFormatter.ofPattern("yyyy");
    DateTimeFormatter monat = DateTimeFormatter.ofPattern("MM");
    DateTimeFormatter tag = DateTimeFormatter.ofPattern("dd");
    
    nfAbJahr.setInt(Integer.parseInt(jetzt.format(jahr)));
    nfAbMonat.setInt(Integer.parseInt(jetzt.format(monat)));
    nfAbTag.setInt(Integer.parseInt(jetzt.format(tag)));
    
    nfRueTag.setInt(20);
    nfRueMonat.setInt(3);
    nfRueJahr.setInt(2022);
    
    loadTabelleGeraet(g, k.getMitgliedid());
    
   
    lSumme.setText("Gesamt Preis: "+summe+"�");
    
    setResizable(false);
    setVisible(true);
  } // end of public Mietvertraegehinzufuegen
  
  // Anfang Methoden
  public void bSpeichern_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einf�gen
    DateTimeFormatter klassisch = DateTimeFormatter.ofPattern("d.M.yyyy");
    String ab = nfAbTag.getInt() + "." + nfAbMonat.getInt() + "."+ nfAbJahr.getInt();
    LocalDate abDatum = LocalDate.parse(ab, klassisch); 
    LocalDate zueDatum = null;
    if (nfRueTag.getInt() > 0 && nfRueMonat.getInt() > 0 && nfRueJahr.getInt() > 0) {
      String zue = nfRueTag.getInt() +"."+nfRueMonat.getInt()+"."+nfRueJahr.getInt();
      zueDatum = LocalDate.parse(zue, klassisch);
    } 
    
    for (int i = 0; i < g.size(); i++) {
//      (int m_id, Geraet geraet, Kunde kunde, LocalDate abgabe, LocalDate rueckgabe)
    Mietvertrag m = new Mietvertrag(g.get(i), k, abDatum, zueDatum, false);
    mietvertraege.add(m);
    }
    
    //ArrayList<Mietvertrag> mietvertraege, LocalDate rechnungsdatum, boolean status, String kundenname, String kundenvorname, String strasse, String hausnummer, String plz, String ort
    rechnung = new Rechnung(mietvertraege, LocalDate.now() , false, mietvertraege.get(0).getKunde().getName(), mietvertraege.get(0).getKunde().getVorname(), mietvertraege.get(0).getKunde().getStrasse(), mietvertraege.get(0).getKunde().getHausnummer(), mietvertraege.get(0).getKunde().getPlz(), mietvertraege.get(0).getKunde().getOrt());
    
    DB db = new DB();
    db.speicherRechnung(rechnung);
    dispose();
  } // end of bSpeichern_ActionPerformed

  public void bAbbrechen_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einf�gen
    dispose();
  } // end of bAbbrechen_ActionPerformed
  
  
  public void loadTabelleGeraet(ArrayList<Geraet> g, int preis) {
    tGeraetModel.setNumRows(0);
    String[] colname = {"", "ID", "Bezeichnung",  "Preis","Zustand"};
    tGeraetModel.setColumnIdentifiers(colname);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    System.out.println(g.size());
    for (int i = 0; i < g.size(); i++) {   
      String[] row = {i+1+".",g.get(i).getG_id()+"", g.get(i).getBezeichnung(),  g.get(i).getMietpreisklasse()[preis-1]+"�", g.get(i).getZustand()};
      tGeraetModel.addRow(row);
    }
  }
  // Ende Methoden
  
} // end of class Mietvertraegehinzufuegen

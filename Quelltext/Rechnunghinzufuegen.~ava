
import org.jdatepicker.*;
import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.JDialog;
import java.awt.BorderLayout;




/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 11.01.2022
 * @author 
 */

public class Rechnunghinzufuegen extends JDialog {
  // Anfang Attribute
  private JTable tGeraet = new JTable(5, 5);
    private DefaultTableModel tGeraetModel = (DefaultTableModel) tGeraet.getModel();
    private JScrollPane tGeraetScrollPane = new JScrollPane(tGeraet);
  private JLabel lGeraete = new JLabel();
  private JLabel lKunde1 = new JLabel();
  private JLabel lAbgabedatum = new JLabel();
  private JButton bSpeichern = new JButton();
  private JButton bAbbrechen = new JButton();
  private JLabel lRueckgabedatum1 = new JLabel();
   

  private Kunde k;
  private ArrayList<Geraet> g;
  private ArrayList<Mietvertrag> mietvertraege = new ArrayList<Mietvertrag>();
  private JTextArea taKunde = new JTextArea("");
  private JScrollPane taKundeScrollPane = new JScrollPane(taKunde);
  private Rechnung rechnung;
  private JCheckBox cpBezahlt = new JCheckBox();
  private JLabel lBezahlt = new JLabel();
  private JDatePicker jdpabgabe = new JDatePicker();
  private JDatePicker jdpzurueckgabe = new JDatePicker();

  private JPanel jPAbgabe = new JPanel();
  private JPanel jPzurueckgabe = new JPanel();
  private JTable tPreis = new JTable(5, 5);
    private DefaultTableModel tPreisModel = (DefaultTableModel) tPreis.getModel();
    private JScrollPane tPreisScrollPane = new JScrollPane(tPreis);
  private JButton bSpeichernundDrucken = new JButton();
  // Ende Attribute
  
  public Rechnunghinzufuegen(JFrame owner, boolean modal, Kunde k, ArrayList<Geraet> g) { 
    // Dialog-Initialisierung
    super(owner, modal);
    this.g = g;
    this.k = k;
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 715; 
    int frameHeight = 772;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Mietvertraegehinzufuegen");
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    tGeraetScrollPane.setBounds(16, 224, 676, 374);
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
    lAbgabedatum.setBounds(16, 136, 110, 20);
    lAbgabedatum.setText("Abgabedatum:");
    cp.add(lAbgabedatum);
    bSpeichern.setBounds(608, 696, 75, 25);
    bSpeichern.setText("Speichern");
    bSpeichern.setMargin(new Insets(2, 2, 2, 2));
    bSpeichern.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bSpeichern_ActionPerformed(evt);
      }
    });
    cp.add(bSpeichern);
    bAbbrechen.setBounds(16, 696, 75, 25);
    bAbbrechen.setText("Abbrechen");
    bAbbrechen.setMargin(new Insets(2, 2, 2, 2));
    bAbbrechen.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bAbbrechen_ActionPerformed(evt);
      }
    });
    cp.add(bAbbrechen);
    lRueckgabedatum1.setBounds(256, 136, 110, 20);
    lRueckgabedatum1.setText("Rueckgabedatum:");
    cp.add(lRueckgabedatum1);
    taKundeScrollPane.setBounds(16, 32, 208, 92);
    cp.add(taKundeScrollPane);
    cpBezahlt.setBounds(496, 160, 20, 20);
    cpBezahlt.setOpaque(false);
    cp.add(cpBezahlt);
    lBezahlt.setBounds(496, 136, 110, 20);
    lBezahlt.setText("Bezahlt:");
    cp.add(lBezahlt);
    
    
    // DatePicker
    jdpabgabe.setTextEditable(true);
    jdpabgabe.setShowYearButtons(true);
    jPAbgabe.add((JComponent) jdpabgabe);
    
    jdpzurueckgabe.setTextEditable(true);
    jdpzurueckgabe.setShowYearButtons(true);
    jPzurueckgabe.add((JComponent) jdpzurueckgabe);
    
    
    jPAbgabe.setBounds(16, 160, 210, 30);
    jPAbgabe.setOpaque(false);
    cp.add(jPAbgabe);
    
    jPzurueckgabe.setBounds(256, 160, 210, 30);
    jPzurueckgabe.setOpaque(false);
    cp.add(jPzurueckgabe);
    tPreisScrollPane.setBounds(463, 603, 228, 78);
    tPreis.getColumnModel().getColumn(0).setHeaderValue("Title 1");
    tPreis.getColumnModel().getColumn(1).setHeaderValue("Title 2");
    tPreis.getColumnModel().getColumn(2).setHeaderValue("Title 3");
    tPreis.getColumnModel().getColumn(3).setHeaderValue("Title 4");
    tPreis.getColumnModel().getColumn(4).setHeaderValue("Title 5");
    cp.add(tPreisScrollPane);
    bSpeichernundDrucken.setBounds(448, 696, 155, 25);
    bSpeichernundDrucken.setText("Speichern und Drucken");
    bSpeichernundDrucken.setMargin(new Insets(2, 2, 2, 2));
    bSpeichernundDrucken.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bSpeichernundDrucken_ActionPerformed(evt);
      }
    });
    cp.add(bSpeichernundDrucken);
    // Ende Komponenten
    
    
    
    
   
    double summe = 0;
    for (int i = 0; i < g.size(); i++) {
      summe = ((summe*100) + (g.get(i).getMietpreisklasse()[k.getMitgliedid()-1]*100))/100;
    }
    
    //ArrayList<Mietvertrag> mietvertraege, LocalDate rechnungsdatum, boolean status, String kundenname, String kundenvorname, String strasse, String hausnummer, String plz, String ort, double preis
    
    
    taKunde.setText(
    k.getVorname() + " "+ k.getName() +
    "\n" + k.getStrasse() + " "+ k.getHausnummer()+
    "\n" + k.getPlz() + " " + k.getOrt()
    );
      

    loadTabelleGeraet(g, k.getMitgliedid());
    
    loadTabellePreis(summe, 19);
    
    setResizable(false);
    setVisible(true);
  } // end of public Rechnunghinzufuegen
  
  // Anfang Methoden
  public void bSpeichern_ActionPerformed(ActionEvent evt) {
    LocalDate abDatum = jdpgetLocalDate(jdpabgabe);
    LocalDate zueDatum = jdpgetLocalDate(jdpzurueckgabe);
    
    for (int i = 0; i < g.size(); i++) {
    Mietvertrag m = new Mietvertrag(g.get(i), k, abDatum, zueDatum, false);
    mietvertraege.add(m);
    }
    
    //ArrayList<Mietvertrag> mietvertraege, LocalDate rechnungsdatum, boolean status, String kundenname, String kundenvorname, String strasse, String hausnummer, String plz, String ort
    rechnung = new Rechnung(mietvertraege, LocalDate.now() , cpBezahlt.isSelected(), mietvertraege.get(0).getKunde().getName(), mietvertraege.get(0).getKunde().getVorname(), mietvertraege.get(0).getKunde().getStrasse(), mietvertraege.get(0).getKunde().getHausnummer(), mietvertraege.get(0).getKunde().getPlz(), mietvertraege.get(0).getKunde().getOrt());
    
    DB db = new DB();
    db.speicherRechnung(rechnung);
    rechnung = null;
    dispose();
  } // end of bSpeichern_ActionPerformed

  public void bAbbrechen_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    dispose();
  } // end of bAbbrechen_ActionPerformed
  
  
  public void loadTabelleGeraet(ArrayList<Geraet> g, int preis) {
    tGeraetModel.setNumRows(0);
    String[] colname = {"", "ID", "Bezeichnung",  "Preis","Zustand"};
    tGeraetModel.setColumnIdentifiers(colname);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    System.out.println(g.size());
    for (int i = 0; i < g.size(); i++) {   
      String[] row = {i+1+".",g.get(i).getG_id()+"", g.get(i).getBezeichnung(),  g.get(i).getMietpreisklasse()[preis-1]+"€", g.get(i).getZustand()};
      tGeraetModel.addRow(row);
    }
  }
  
  
  public LocalDate jdpgetLocalDate(JDatePicker p){
    if (p != null) {
      LocalDate ld = LocalDate.parse(
      p.getModel().getDay()+"."+
      (p.getModel().getMonth()+1)+"."+
      p.getModel().getYear()
      , DateTimeFormatter.ofPattern("d.M.yyyy"));
      return ld;
    } // end of i
  return null;
  }
  
  
   public void loadTabellePreis(double summe, int ust){
    tPreisModel.setNumRows(0);
    String[] colname = {"",  ""};
    tPreisModel.setColumnIdentifiers(colname);
    String[] row = new String[2];
    row[0] = "Summe Netto"; row[1] = summe + "€";
    tPreisModel.addRow(row);
    row[0] = ust + "% Ust. auf "+ summe + "€"; row[1] = rundenkm(summe/100*ust) +"€";
    tPreisModel.addRow(row);
    row[0] = "Endpreis"; row[1] = rundenkm(summe + (summe/100*ust)) + "€";
    System.out.println(summe/100*ust);
    tPreisModel.addRow(row);
    }
  
  public double rundenkm(double r){
    return Math.round(100.0*r)/100.0;
    }
  
  public void bSpeichernundDrucken_ActionPerformed(ActionEvent evt) {
    LocalDate abDatum = jdpgetLocalDate(jdpabgabe);
    LocalDate zueDatum = jdpgetLocalDate(jdpzurueckgabe);
    
    for (int i = 0; i < g.size(); i++) {
//      (int m_id, Geraet geraet, Kunde kunde, LocalDate abgabe, LocalDate rueckgabe)
    Mietvertrag m = new Mietvertrag(g.get(i), k, abDatum, zueDatum, false);
    mietvertraege.add(m);
    }
    
    //ArrayList<Mietvertrag> mietvertraege, LocalDate rechnungsdatum, boolean status, String kundenname, String kundenvorname, String strasse, String hausnummer, String plz, String ort
    rechnung = new Rechnung(mietvertraege, LocalDate.now() , cpBezahlt.isSelected(), mietvertraege.get(0).getKunde().getName(), mietvertraege.get(0).getKunde().getVorname(), mietvertraege.get(0).getKunde().getStrasse(), mietvertraege.get(0).getKunde().getHausnummer(), mietvertraege.get(0).getKunde().getPlz(), mietvertraege.get(0).getKunde().getOrt());
    
    DB db = new DB();
    db.speicherRechnung(rechnung);
    rechnung = db.ladeRechnungen("ORDER BY R_id DESC LIMIT 1").get(0);
    dispose();
  } // end of bSpeichernundDrucken_ActionPerformed
  
  public Rechnung getRechnung(){
    return rechnung; 
    }

  // Ende Methoden
  
} // end of class Rechnunghinzufuegen

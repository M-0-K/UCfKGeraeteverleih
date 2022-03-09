import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.ArrayList;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 21.02.2022
 * @author Moritz Kockert
 */

public class Rechnungdrucken extends JDialog {
  // Anfang Attribute
  private JPanel pRechnung = new JPanel(null, true);
    private JLabel lUnitedClubsforKuloweV = new JLabel();
    private JLabel lRechnung = new JLabel();
    private JTextArea taMieter = new JTextArea("");
      private JScrollPane taMieterScrollPane = new JScrollPane(taMieter);
    private JTextArea taAbsender = new JTextArea("");
      private JScrollPane taAbsenderScrollPane = new JScrollPane(taAbsender);
    private JTextArea taRechnungsdetails = new JTextArea("");
      private JScrollPane taRechnungsdetailsScrollPane = new JScrollPane(taRechnungsdetails);
    private JTextArea taFusszeile = new JTextArea("");
      private JScrollPane taFusszeileScrollPane = new JScrollPane(taFusszeile);
  private JButton bDrucken = new JButton();
  private JTable tGeraete = new JTable(5, 5);
    private DefaultTableModel tGeraeteModel = (DefaultTableModel) tGeraete.getModel();
    private JScrollPane tGeraeteScrollPane = new JScrollPane(tGeraete);
  private DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
  private JTable tPreis = new JTable(4, 2);
    private DefaultTableModel tPreisModel = (DefaultTableModel) tPreis.getModel();
    private JScrollPane tPreisScrollPane = new JScrollPane(tPreis);
  // Ende Attribute
  
  public Rechnungdrucken(JFrame owner, boolean modal, Rechnung r, boolean show) { 
    // Dialog-Initialisierung
    super(owner, modal);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 628; 
    int frameHeight = 866;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Rechnungdrucken");
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    pRechnung.setBounds(11, 6, 590, 755);
    pRechnung.setOpaque(true);
    pRechnung.setBackground(Color.WHITE);
    cp.add(pRechnung);
    bDrucken.setBounds(256, 792, 75, 25);
    bDrucken.setText("Drucken");
    bDrucken.setMargin(new Insets(2, 2, 2, 2));
    bDrucken.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bDrucken_ActionPerformed(evt);
      }
    });
    cp.add(bDrucken);
    lUnitedClubsforKuloweV.setBounds(21, 18, 330, 34);
    lUnitedClubsforKuloweV.setText("United Clubs for Kulow e.V.");
    lUnitedClubsforKuloweV.setFont(new Font("Dialog", Font.BOLD, 25));
    pRechnung.add(lUnitedClubsforKuloweV);
    lRechnung.setBounds(21, 194, 110, 20);
    lRechnung.setText("Rechnung");
    pRechnung.add(lRechnung);
    taMieterScrollPane.setBounds(24, 82, 240, 100);
    pRechnung.add(taMieterScrollPane);
    taAbsenderScrollPane.setBounds(384, 58, 192, 92);
    taAbsender.setText("United Clubs for Kulow e.V.\nDubring 3\n02997 Wittichenau\n\ninfo@united-clubs.de");
    taAbsender.setFont(new Font("Dialog", Font.BOLD, 12));
    taAbsender.setEditable(false);
    pRechnung.add(taAbsenderScrollPane);
    tGeraeteScrollPane.setBounds(21, 314, 564, 342);
    tGeraete.getColumnModel().getColumn(0).setHeaderValue("Title 1");
    tGeraete.getColumnModel().getColumn(1).setHeaderValue("Title 2");
    tGeraete.getColumnModel().getColumn(2).setHeaderValue("Title 3");
    tGeraete.getColumnModel().getColumn(3).setHeaderValue("Title 4");
    tGeraete.getColumnModel().getColumn(4).setHeaderValue("Title 5");
    tGeraete.setFont(new Font("Dialog", Font.PLAIN, 10));
    tGeraeteScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    tGeraete.setRowSelectionAllowed(false);
    tGeraete.setShowGrid(true);
    tGeraeteScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
    tGeraete.setFillsViewportHeight(true);
    pRechnung.add(tGeraeteScrollPane);
    taRechnungsdetailsScrollPane.setBounds(21, 218, 560, 84);
    taRechnungsdetails.setText("Rechnungsnummer:\nKundennummer:\nRechnungsdatum:\nAbgabedatum:\nRückgabedatum:");
    taRechnungsdetails.setFont(new Font("Dialog", Font.BOLD, 10));
    taRechnungsdetails.setWrapStyleWord(true);
    taRechnungsdetailsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
    pRechnung.add(taRechnungsdetailsScrollPane);
    tPreisScrollPane.setBounds(303, 666, 284, 54);
    tPreis.setRowSelectionAllowed(false);
    tPreis.setShowGrid(true);
    tPreis.setShowHorizontalLines(true);
    tPreis.setShowVerticalLines(false);
    tPreis.setFont(new Font("Dialog", Font.BOLD, 12));
    tPreisScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
    pRechnung.add(tPreisScrollPane);
    taFusszeileScrollPane.setBounds(21, 730, 568, 20);
    taFusszeile.setText("Sitz des Vereins: Wittichenau, Deutschland · Vorstandsvorsitzender: Max Mustermann · USt-IdNr. DE216398573");
    taFusszeile.setFont(new Font("Dialog", Font.ITALIC, 10));
    taFusszeile.setForeground(Color.GRAY);
    taFusszeile.setWrapStyleWord(true);
    taFusszeile.setLineWrap(true);
    pRechnung.add(taFusszeileScrollPane);
    // Ende Komponenten
    
    taMieter.setText(
    r.getKundenname() + ", "+ r.getKundenvorname() +"\n" 
    + r.getStrasse() + " "+ r.getHausnummer() +"\n" 
    + r.getPlz() + " " + r.getOrt());
    
    taRechnungsdetails.setText(
    "Rechnungsnummer: " + r.getR_id()+"\n" 
    +"Kundennummer: " + r.getMietvertraege().get(0).getKunde().getK_id() +"\n"
    +"Rechnungsdatum: " + r.getRechnungsdatum().format(dateformat) +"\n"
    +"Abgabedatum: " + r.getMietvertraege().get(0).getAbgabe().format(dateformat) +"\n"
    +"Rückgabedatum: " + r.getMietvertraege().get(0).getRueckgabe().format(dateformat)
    );
    
    ArrayList<Geraet> gl= new ArrayList<Geraet>();
    for (int i = 0; i < r.getMietvertraege().size(); i++) {
      gl.add(r.getMietvertraege().get(i).getGeraet());
    }
    loadTabelleGeraet(gl, r.getMietvertraege().get(0).getKunde().getMitgliedid());
    
    loadTabellePreis(r.getPreis(), 19);
    
    taAbsender.setEditable(false);
    taMieter.setEditable(false);
    taRechnungsdetails.setEditable(false);
    taFusszeile.setEditable(false);

    setResizable(false);
    if (show) {
      setVisible(true);
    } else {
      PrintSuit ps = new PrintSuit(pRechnung);
      ps.print();
      dispose();  
      } // end of if-else
    
  } // end of public Rechnungdrucken
  
  // Anfang Methoden
  public void bDrucken_ActionPerformed(ActionEvent evt) {
    PrintSuit ps = new PrintSuit(pRechnung);
    ps.print();
  } // end of bDrucken_ActionPerformed
  
  public void loadTabelleGeraet(ArrayList<Geraet> g, int preis) {
    tGeraeteModel.setNumRows(0);
    String[] colname = {"Pos.", "Geräte ID", "Bezeichnung",  "Preis"};
    tGeraeteModel.setColumnIdentifiers(colname);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    System.out.println(g.size());
    for (int i = 0; i < g.size(); i++) {   
      String[] row = {i+1+".",g.get(i).getG_id()+"", g.get(i).getBezeichnung(),  g.get(i).getMietpreisklasse()[preis-1]+"€"};
      tGeraeteModel.addRow(row);
    }
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
    tPreisModel.addRow(row);
    }
  
  public double rundenkm(double r){
    return Math.round(100.0*r)/100.0;
    }
  // Ende Methoden
  
} // end of class Rechnungdrucken


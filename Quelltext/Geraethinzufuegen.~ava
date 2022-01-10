import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.event.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import javax.swing.JDialog;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 01.01.2022
 * @author 
 */

public class Geraethinzufuegen extends JDialog {
  // Anfang Attribute
  private JButton bSpeichern = new JButton();
  private JLabel lBezeichnung = new JLabel();
  private JTextField tfBezeichnung = new JTextField();
  private JLabel lGeraethinzufuegen = new JLabel();
  private JNumberField nfAnPreis = new JNumberField();
  private JLabel lAnschaffungspreis = new JLabel();
  private JNumberField nfAnTag = new JNumberField();
  private JNumberField nfAnMonat = new JNumberField();
  private JNumberField nfAnJahr = new JNumberField();
  private JLabel lAnschaffungsdatum = new JLabel();
  private JTextField tfZustand = new JTextField();
  private JNumberField nfMietkl1 = new JNumberField();
  private JNumberField nfMietkl2 = new JNumberField();
  private JNumberField nfMietkl3 = new JNumberField();
  private JLabel lMietpreise = new JLabel();
  private JLabel lKlasse2 = new JLabel();
  private JLabel lKlasse3 = new JLabel();
  private JLabel lZustand = new JLabel();
  private JLabel lProdukgruppe = new JLabel();
  private JComboBox<String> cbProduktgruppe = new JComboBox<String>();
    private DefaultComboBoxModel<String> cbProduktgruppeModel = new DefaultComboBoxModel<String>();
  private JLabel lKlasse1 = new JLabel();
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JLabel jLabel3 = new JLabel();
  private JLabel l = new JLabel();
  private JLabel l1 = new JLabel();
  private JLabel jLabel4 = new JLabel();
  private JButton bAbbrechen = new JButton();
  private JLabel lStatus = new JLabel();
  // Ende Attribute
  
  public Geraethinzufuegen() { 
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 283; 
    int frameHeight = 502;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Geraethinzufuegen");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    bSpeichern.setBounds(8, 392, 75, 25);
    bSpeichern.setText("Speichern");
    bSpeichern.setMargin(new Insets(2, 2, 2, 2));
    bSpeichern.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bSpeichern_ActionPerformed(evt);
      }
    });
    cp.add(bSpeichern);
    lBezeichnung.setBounds(8, 40, 110, 20);
    lBezeichnung.setText("Bezeichnung");
    cp.add(lBezeichnung);
    tfBezeichnung.setBounds(8, 64, 150, 20);
    cp.add(tfBezeichnung);
    lGeraethinzufuegen.setBounds(8, 8, 270, 23);
    lGeraethinzufuegen.setText("Gerät hinzufügen");
    lGeraethinzufuegen.setFont(new Font("Dialog", Font.BOLD, 16));
    cp.add(lGeraethinzufuegen);
    nfAnPreis.setBounds(8, 116, 75, 20);
    nfAnPreis.setText("");
    cp.add(nfAnPreis);
    lAnschaffungspreis.setBounds(8, 95, 112, 20);
    lAnschaffungspreis.setText("Anschaffungspreis");
    cp.add(lAnschaffungspreis);
    nfAnTag.setBounds(8, 168, 35, 20);
    nfAnTag.setText("");
    cp.add(nfAnTag);
    nfAnMonat.setBounds(56, 168, 35, 20);
    nfAnMonat.setText("");
    cp.add(nfAnMonat);
    nfAnJahr.setBounds(104, 168, 59, 20);
    nfAnJahr.setText("");
    cp.add(nfAnJahr);
    lAnschaffungsdatum.setBounds(8, 141, 119, 20);
    lAnschaffungsdatum.setText("Anschaffungsdatum");
    cp.add(lAnschaffungsdatum);
    tfZustand.setBounds(6, 306, 150, 20);
    cp.add(tfZustand);
    nfMietkl1.setBounds(8, 248, 64, 20);
    nfMietkl1.setText("");
    cp.add(nfMietkl1);
    nfMietkl2.setBounds(96, 248, 64, 20);
    nfMietkl2.setText("");
    cp.add(nfMietkl2);
    nfMietkl3.setBounds(184, 248, 64, 20);
    nfMietkl3.setText("");
    cp.add(nfMietkl3);
    lMietpreise.setBounds(8, 200, 80, 20);
    lMietpreise.setText("Mietpreise");
    cp.add(lMietpreise);
    lKlasse2.setBounds(96, 224, 80, 20);
    lKlasse2.setText("Klasse2");
    cp.add(lKlasse2);
    lKlasse3.setBounds(184, 224, 80, 20);
    lKlasse3.setText("Klasse3");
    cp.add(lKlasse3);
    lZustand.setBounds(8, 280, 110, 20);
    lZustand.setText("Zustand");
    cp.add(lZustand);
    lProdukgruppe.setBounds(8, 336, 110, 20);
    lProdukgruppe.setText("Produkgruppe");
    cp.add(lProdukgruppe);
    cbProduktgruppe.setModel(cbProduktgruppeModel);
    cbProduktgruppe.setBounds(5, 358, 150, 20);
    cbProduktgruppeModel.addElement("Licht");
    cbProduktgruppeModel.addElement("Ton");
    cbProduktgruppeModel.addElement("Video");
    cbProduktgruppeModel.addElement("Sonstiges");
    cp.add(cbProduktgruppe);
    lKlasse1.setBounds(8, 224, 78, 20);
    lKlasse1.setText("Klasse1");
    cp.add(lKlasse1);
    jLabel1.setBounds(72, 248, 14, 20);
    jLabel1.setText("€");
    cp.add(jLabel1);
    jLabel2.setBounds(160, 248, 14, 20);
    jLabel2.setText("€");
    cp.add(jLabel2);
    jLabel3.setBounds(248, 248, 14, 20);
    jLabel3.setText("€");
    cp.add(jLabel3);
    l.setBounds(48, 168, 6, 20);
    l.setText(".");
    cp.add(l);
    l1.setBounds(96, 168, 6, 20);
    l1.setText(".");
    cp.add(l1);
    jLabel4.setBounds(88, 116, 14, 20);
    jLabel4.setText("€");
    cp.add(jLabel4);
    bAbbrechen.setBounds(96, 392, 75, 25);
    bAbbrechen.setText("Abbrechen");
    bAbbrechen.setMargin(new Insets(2, 2, 2, 2));
    bAbbrechen.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bAbbrechen_ActionPerformed(evt);
      }
    });
    cp.add(bAbbrechen);
    lStatus.setBounds(8, 432, 246, 20);
    lStatus.setText("Status:");
    cp.add(lStatus);
    // Ende Komponenten
    LocalDate jetzt = LocalDate.now();
    DateTimeFormatter jahr = DateTimeFormatter.ofPattern("yyyy");
    DateTimeFormatter monat = DateTimeFormatter.ofPattern("MM");
    DateTimeFormatter tag = DateTimeFormatter.ofPattern("dd");
    
    nfAnJahr.setInt(Integer.parseInt(jetzt.format(jahr)));
    nfAnMonat.setInt(Integer.parseInt(jetzt.format(monat)));
    nfAnTag.setInt(Integer.parseInt(jetzt.format(tag)));
    setVisible(true);
  } // end of public Geraethinzufuegen
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new Geraethinzufuegen();
  } // end of main

  public void bSpeichern_ActionPerformed(ActionEvent evt) {
    //Geraet(String bezeichnung, double anschaffungspreis, LocalDate anschaffungsdatum, double[] mietpreise, String zustand, String produktgruppe){
    DateTimeFormatter klassisch = DateTimeFormatter.ofPattern("d.M.yyyy");
    if (eingabe()) {
      String datum = nfAnTag.getInt() + "." +nfAnMonat.getInt() + "."+ nfAnJahr.getInt();
      LocalDate neuDatum = LocalDate.parse(datum, klassisch);
      double[] mietpreise = {nfMietkl1.getDouble(), nfMietkl2.getDouble(), nfMietkl3.getDouble()};
      Geraet neuGeraet = new Geraet(tfBezeichnung.getText(), nfAnPreis.getDouble(), neuDatum, mietpreise, tfZustand.getText(), cbProduktgruppeModel.getSelectedItem().toString());
      neuGeraet.speichern();
      lStatus.setText("Erfolgreich gespeichert!");
      dispose();
    } // end of if
    
    
  } // end of bSpeichern_ActionPerformed

  public void bAbbrechen_ActionPerformed(ActionEvent evt) {
    dispose();
  } // end of bAbbrechen_ActionPerformed
  
  public boolean eingabe() {
    if (tfBezeichnung.getText().equals("")) {
      lStatus.setText("Bezeichnung fehlt!");
      return false;
    } // end of if
    if (nfAnPreis.getText().equals("")) {
      lStatus.setText("Anschaffungspreis fehlt!");
      return false;
    } // end of if
    if (nfAnTag.getText().equals("")) {
      lStatus.setText("Tag felht!");
      return false;
    } // end of if
    if (nfAnMonat.getText().equals("")) {
      lStatus.setText("Monat fehlt!");
      return false;
    } // end of if
     if (nfAnJahr.getText().equals("")) {
      lStatus.setText("Monat fehlt!");
      return false;
    } // end of if
     if (nfMietkl1.getText().equals("")) {
      lStatus.setText("Mietklasse 1 fehlt!");
      return false;
    } // end of if
     if (nfMietkl2.getText().equals("")) {
      lStatus.setText("Mietklasse 2 fehlt!");
      return false;
    } // end of if
     if (nfMietkl3.getText().equals("")) {
      lStatus.setText("Mietklasse 3 fehlt!");
      return false;
    } // end of if
     if (tfZustand.getText().equals("")) {
      lStatus.setText("Zustand fehlt!");
      return false;
    } // end of if
     if (cbProduktgruppeModel.getSelectedItem().toString().equals("")) {
      lStatus.setText("Produktgruppe fehlt!");
      return false;
    } // end of if
    return true;
  }
  // Ende Methoden
} // end of class Geraethinzufuegen


import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JDialog;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 01.01.2022
 * @author 
 */

public class Kundehinzufuegen extends JDialog {
  // Anfang Attribute
  private JTextField tfName = new JTextField();
  private JLabel lName = new JLabel();
  private JLabel lVorname = new JLabel();
  private JTextField tfVorname = new JTextField();
  private JLabel lWohnort = new JLabel();
  private JLabel lPostleitzahl = new JLabel();
  private JTextField tfWohnort = new JTextField();
  private JComboBox<String> cbMitglied = new JComboBox<String>();
  private DefaultComboBoxModel<String> cbMitgliedModel = new DefaultComboBoxModel<String>();
  private JButton bSpeichern = new JButton();
  private JLabel lKundehinzufuegen = new JLabel();
  private JTextField tfPLZ = new JTextField();
  private JLabel lStatus = new JLabel();
  private JButton bAbbrechen = new JButton();
  private JLabel lStrasse = new JLabel();
  private JLabel lHausnummer = new JLabel();
  private JTextField tfStrasse = new JTextField();
  private JTextField tfHausnummer = new JTextField();
  // Ende Attribute
  
  public Kundehinzufuegen() { 
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 309; 
    int frameHeight = 412;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Kundehinzufuegen");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    tfName.setBounds(8, 72, 150, 20);
    cp.add(tfName);
    lName.setBounds(8, 48, 110, 20);
    lName.setText("Name");
    cp.add(lName);
    lVorname.setBounds(8, 104, 110, 20);
    lVorname.setText("Vorname");
    cp.add(lVorname);
    tfVorname.setBounds(8, 128, 150, 20);
    cp.add(tfVorname);
    lWohnort.setBounds(8, 216, 110, 20);
    lWohnort.setText("Wohnort");
    cp.add(lWohnort);
    lPostleitzahl.setBounds(160, 216, 110, 20);
    lPostleitzahl.setText("Postleitzahl");
    cp.add(lPostleitzahl);
    tfWohnort.setBounds(8, 240, 150, 20);
    cp.add(tfWohnort);
    cbMitglied.setModel(cbMitgliedModel);
    cbMitglied.setBounds(8, 272, 150, 20);
    cbMitgliedModel.addElement("Mitglied ");
    cbMitgliedModel.addElement("Bekannt");
    cbMitgliedModel.addElement("Unbekannt");
    cp.add(cbMitglied);
    bSpeichern.setBounds(8, 304, 75, 25);
    bSpeichern.setText("Speichern");
    bSpeichern.setMargin(new Insets(2, 2, 2, 2));
    bSpeichern.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bSpeichern_ActionPerformed(evt);
      }
    });
    cp.add(bSpeichern);
    lKundehinzufuegen.setBounds(8, 8, 270, 28);
    lKundehinzufuegen.setText("Kundehinzuf�gen");
    lKundehinzufuegen.setFont(new Font("Dialog", Font.BOLD, 16));
    cp.add(lKundehinzufuegen);
    tfPLZ.setBounds(159, 239, 110, 20);
    cp.add(tfPLZ);
    lStatus.setBounds(8, 344, 270, 20);
    lStatus.setText("Status:");
    cp.add(lStatus);
    bAbbrechen.setBounds(88, 304, 75, 25);
    bAbbrechen.setText("Abbrechen");
    bAbbrechen.setMargin(new Insets(2, 2, 2, 2));
    bAbbrechen.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bAbbrechen_ActionPerformed(evt);
      }
    });
    cp.add(bAbbrechen);
    lStrasse.setBounds(8, 160, 110, 20);
    lStrasse.setText("Stra�e");
    cp.add(lStrasse);
    lHausnummer.setBounds(160, 160, 110, 20);
    lHausnummer.setText("Hausnummer");
    cp.add(lHausnummer);
    tfStrasse.setBounds(8, 184, 150, 20);
    cp.add(tfStrasse);
    tfHausnummer.setBounds(160, 184, 110, 20);
    cp.add(tfHausnummer);
    // Ende Komponenten
    
    setVisible(true);
  } // end of public Kundehinzufuegen
  
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new Kundehinzufuegen();
  } // end of main
  
  public void bSpeichern_ActionPerformed(ActionEvent evt) {
    if (eingabe()) {
       Kunde neuKunde = new Kunde(tfName.getText(), tfVorname.getText(),tfStrasse.getText(), tfHausnummer.getText(), tfWohnort.getText(), tfPLZ.getText(), cbMitgliedModel.getSelectedItem().toString());
      neuKunde.speichern();
      lStatus.setText("Erfolgreich gespeichert!");
      dispose();
    } // end of if
  } // end of bSpeichern_ActionPerformed

  public void bAbbrechen_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einf�gen
    dispose();
  } // end of bAbbrechen_ActionPerformed
  
  public boolean eingabe() {
    if (tfName.getText().equals("")) {
      lStatus.setText("Name fehlt!");
      return false;
    } // end of if
    if (tfVorname.getText().equals("")) {
      lStatus.setText("Vorname fehlt!");
      return false;
    } // end of if
    if (tfStrasse.equals("")) {
      lStatus.setText("Strasse felht!");
      return false;
    } // end of if
    if (tfHausnummer.getText().equals("")) {
      lStatus.setText("Hausnummer felht!");
      return false;
    } // end of if
    if (tfWohnort.getText().equals("")) {
      lStatus.setText("Wohnort felht!");
      return false;
    } // end of if
    if (tfPLZ.getText().equals("")) {
      lStatus.setText("Postleitzahl fehlt!");
      return false;
    } // end of if
     if (cbMitgliedModel.getSelectedItem().toString().equals("")) {
      lStatus.setText("Mitglied fehlt!");
      return false;
    } // end of if
    return true;
  }
  // Ende Methoden
} // end of class Kundehinzufuegen

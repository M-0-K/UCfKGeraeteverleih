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
  // Ende Attribute
  
  public Kundehinzufuegen() { 
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 304; 
    int frameHeight = 357;
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
    lWohnort.setBounds(8, 160, 110, 20);
    lWohnort.setText("Wohnort");
    cp.add(lWohnort);
    lPostleitzahl.setBounds(168, 160, 110, 20);
    lPostleitzahl.setText("Postleitzahl");
    cp.add(lPostleitzahl);
    tfWohnort.setBounds(8, 184, 150, 20);
    cp.add(tfWohnort);
    cbMitglied.setModel(cbMitgliedModel);
    cbMitglied.setBounds(8, 216, 150, 20);
    cbMitgliedModel.addElement("Mitglied ");
    cbMitgliedModel.addElement("Bekannt");
    cbMitgliedModel.addElement("Unbekannt");
    cp.add(cbMitglied);
    bSpeichern.setBounds(8, 248, 75, 25);
    bSpeichern.setText("Speichern");
    bSpeichern.setMargin(new Insets(2, 2, 2, 2));
    bSpeichern.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bSpeichern_ActionPerformed(evt);
      }
    });
    cp.add(bSpeichern);
    lKundehinzufuegen.setBounds(8, 8, 270, 28);
    lKundehinzufuegen.setText("Kundehinzufügen");
    lKundehinzufuegen.setFont(new Font("Dialog", Font.BOLD, 16));
    cp.add(lKundehinzufuegen);
    tfPLZ.setBounds(167, 183, 110, 20);
    cp.add(tfPLZ);
    lStatus.setBounds(8, 288, 270, 20);
    lStatus.setText("Status:");
    cp.add(lStatus);
    bAbbrechen.setBounds(96, 248, 75, 25);
    bAbbrechen.setText("Abbrechen");
    bAbbrechen.setMargin(new Insets(2, 2, 2, 2));
    bAbbrechen.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bAbbrechen_ActionPerformed(evt);
      }
    });
    cp.add(bAbbrechen);
    // Ende Komponenten
    
    setVisible(true);
  } // end of public Kundehinzufuegen
  
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new Kundehinzufuegen();
  } // end of main
  
  public void bSpeichern_ActionPerformed(ActionEvent evt) {
    if (eingabe()) {
       Kunde neuKunde = new Kunde(tfName.getText(), tfVorname.getText(), tfWohnort.getText(), tfPLZ.getText(), cbMitgliedModel.getSelectedItem().toString());
      neuKunde.speichern();
      lStatus.setText("Erfolgreich gespeichert!");
      dispose();
    } // end of if
  } // end of bSpeichern_ActionPerformed

  public void bAbbrechen_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
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


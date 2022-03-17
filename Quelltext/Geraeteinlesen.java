import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.util.converter.LocalDateStringConverter;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 09.03.2022
 * @author Moritz Kockert
 */

public class Geraeteinlesen extends JDialog {
  // Anfang Attribute
  private JTable tGeraete = new JTable(5, 5);
    private DefaultTableModel tGeraeteModel = (DefaultTableModel) tGeraete.getModel();
    private JScrollPane tGeraeteScrollPane = new JScrollPane(tGeraete);
  private JButton bSpeichern1 = new JButton();
  private JButton bAbbrechen = new JButton();
  private JFileChooser jFileChooser1 = new JFileChooser();
  private DB db = new DB();
  //private ArrayList<Geraet> g = new ArrayList<Geraet>();
  private JButton bLoeschen = new JButton();
  // Ende Attribute
  
  public Geraeteinlesen(JFrame owner, boolean modal) { 
    // Dialog-Initialisierung
    super(owner, modal);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 705; 
    int frameHeight = 687;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Geraeteinlesen");
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    tGeraeteScrollPane.setBounds(14, 5, 652, 558);
    tGeraete.getColumnModel().getColumn(0).setHeaderValue("Title 1");
    tGeraete.getColumnModel().getColumn(1).setHeaderValue("Title 2");
    tGeraete.getColumnModel().getColumn(2).setHeaderValue("Title 3");
    tGeraete.getColumnModel().getColumn(3).setHeaderValue("Title 4");
    tGeraete.getColumnModel().getColumn(4).setHeaderValue("Title 5");
    cp.add(tGeraeteScrollPane);
    bSpeichern1.setBounds(591, 576, 75, 25);
    bSpeichern1.setText("speichern");
    bSpeichern1.setMargin(new Insets(2, 2, 2, 2));
    bSpeichern1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bSpeichern1_ActionPerformed(evt);
      }
    });
    cp.add(bSpeichern1);
    bAbbrechen.setBounds(17, 576, 75, 25);
    bAbbrechen.setText("abbrechen");
    bAbbrechen.setMargin(new Insets(2, 2, 2, 2));
    bAbbrechen.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bAbbrechen_ActionPerformed(evt);
      }
    });
    cp.add(bAbbrechen);
    bLoeschen.setBounds(288, 576, 75, 25);
    bLoeschen.setText("l�schen");
    bLoeschen.setMargin(new Insets(2, 2, 2, 2));
    bLoeschen.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bLoeschen_ActionPerformed(evt);
      }
    });
    cp.add(bLoeschen);
    // Ende Komponenten
    
    tGeraeteModel.setNumRows(0);
    String[] colname = {"Bezeichnung", "Anschaffungspreis", "Anschaffungsdatum", "Mietpreisklasse1", "Mietpreisklasse2", "Mietpreisklasse3", "Zustand", "Gruppe"};
    tGeraeteModel.setColumnIdentifiers(colname); 
    FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Datei", "csv");
    jFileChooser1.setFileFilter(filter);   
    try{
      BufferedReader reader = new BufferedReader(new FileReader(jFileChooser1_openFile().getPath()));
      String zeile;
    while ( (zeile = reader.readLine()) != null ){
        String[] items = zeile.split(";");
        tGeraeteModel.addRow(items);
      }
    }catch (Exception e) {
      System.out.println("Einlesefehler"); 
     }
      
    setResizable(false);
    setVisible(true);
  } // end of public Geraeteinlesen
  
  // Anfang Methoden
  public void bSpeichern1_ActionPerformed(ActionEvent evt) {
    try {
      DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
      for (int i = 0; i < tGeraeteModel.getRowCount(); i++) {
        //Geraet(String bezeichnung, double anschaffungspreis, LocalDate anschaffungsdatum, double[] mietpreisklasse, String zustand, String produktgruppe)
        double[] mietpreis = {Double.parseDouble(tGeraeteModel.getValueAt(i, 3).toString()), Double.parseDouble(tGeraeteModel.getValueAt(i, 4).toString()), Double.parseDouble(tGeraeteModel.getValueAt(i, 5).toString())};
        Geraet g = new Geraet(
        tGeraeteModel.getValueAt(i, 0).toString(), 
        Double.parseDouble(tGeraeteModel.getValueAt(i, 1).toString()),
        LocalDate.parse(tGeraeteModel.getValueAt(i, 2).toString(), format),
        mietpreis,
        tGeraeteModel.getValueAt(i, 6).toString(),
        tGeraeteModel.getValueAt(i, 7).toString()
        );
        db.speicherGeraet(g);
      }
    } catch(Exception e) {
      System.out.println(e);
    } finally {
      dispose();
    } // end of try
    
  } // end of bSpeichern1_ActionPerformed

  public void bAbbrechen_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einf�gen
    
  } // end of bAbbrechen_ActionPerformed

  public File jFileChooser1_openFile() {
    if (jFileChooser1.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
      return jFileChooser1.getSelectedFile();
    } else {
      return null;
    }
  }

  public void bLoeschen_ActionPerformed(ActionEvent evt) {
    tGeraeteModel.removeRow(tGeraete.getSelectedRow());
  } // end of bLoeschen_ActionPerformed

  // Ende Methoden
  
} // end of class Geraeteinlesen


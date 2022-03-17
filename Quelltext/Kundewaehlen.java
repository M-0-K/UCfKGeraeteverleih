
import java.awt.*;
import java.awt.event.*;
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

public class Kundewaehlen extends JDialog {
  // Anfang Attribute
  
  private JTable tKunde = new JTable(5, 5);
    private DefaultTableModel tKundeModel = (DefaultTableModel) tKunde.getModel();
    private JScrollPane tKundeScrollPane = new JScrollPane(tKunde);
  private JTextField tfSuchen = new JTextField();
  private JButton bSuchen = new JButton();
  private JButton bWeiter1 = new JButton();
  private JLabel lStatus = new JLabel();
  
  private DB db = new DB();
  private ArrayList<Kunde> kunden;
  private Kunde k = null; 
  private boolean gewaehlt = false;
  
  private JButton bHinzufuegen = new JButton();
  private JFrame owner;
  // Ende Attribute
  
  public Kundewaehlen(JFrame owner, boolean modal) { 
    // Dialog-Initialisierung
    super(owner, modal);
    owner = owner;
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 676; 
    int frameHeight = 563;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Kundewaehlen");
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    tKundeScrollPane.setBounds(8, 40, 628, 430);
    tKunde.getColumnModel().getColumn(0).setHeaderValue("Title 1");
    tKunde.getColumnModel().getColumn(1).setHeaderValue("Title 2");
    tKunde.getColumnModel().getColumn(2).setHeaderValue("Title 3");
    tKunde.getColumnModel().getColumn(3).setHeaderValue("Title 4");
    tKunde.getColumnModel().getColumn(4).setHeaderValue("Title 5");
    tKunde.addMouseListener(new MouseAdapter() { 
      public void mouseClicked(MouseEvent evt) { 
        tKunde_MouseClicked(evt);
      }
    });

    cp.add(tKundeScrollPane);
    tfSuchen.setBounds(8, 8, 353, 25);
    tfSuchen.setText("");
    cp.add(tfSuchen);
    bSuchen.setBounds(368, 8, 75, 25);
    bSuchen.setText("suchen");
    bSuchen.setMargin(new Insets(2, 2, 2, 2));
    bSuchen.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bSuchen_ActionPerformed(evt);
      }
    });
    cp.add(bSuchen);
    bWeiter1.setBounds(544, 480, 91, 25);
    bWeiter1.setText("weiter");
    bWeiter1.setMargin(new Insets(2, 2, 2, 2));
    bWeiter1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bWeiter1_ActionPerformed(evt);
      }
    });
    
    
    cp.add(bWeiter1);
    lStatus.setBounds(105, 480, 430, 25);
    lStatus.setText("");
    cp.add(lStatus);
    addWindowListener(new WindowAdapter() { 
      public void windowActivated(WindowEvent evt) { 
        Kundewaehlen_WindowActivated(evt);
      }
    });
    
    addWindowListener(new WindowAdapter() { 
      public void windowClosing(WindowEvent evt) { 
        Kundewaehlen_WindowClosing(evt);
      }
    });
    
       
    bHinzufuegen.setBounds(8, 480, 91, 25);
    bHinzufuegen.setText("hinzufuegen");
    bHinzufuegen.setMargin(new Insets(2, 2, 2, 2));
    bHinzufuegen.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bHinzufuegen_ActionPerformed(evt);
      }
    });
    cp.add(bHinzufuegen);
    
//    sorter.setRowFilter(RowFilter.regexFilter(tfSuchen.getText()));
//    tKunde.setRowSorter(sorter);
//    sorter.setModel(tKunde.getModel());
    // Ende Komponenten
    
    kunden = db.ladeKunden("WHERE Name != '' and Vorname != ''");
    setResizable(false);
    setVisible(true);

    
  } // end of public Kundewaehlen
  
  // Anfang Methoden
  public void bSuchen_ActionPerformed(ActionEvent evt) {
    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) tKunde.getModel())); 
    sorter.setRowFilter(RowFilter.regexFilter(tfSuchen.getText()));
    tKunde.setRowSorter(sorter);
    sorter.setModel(tKunde.getModel());  
    tKunde.convertRowIndexToModel(tKunde.getRowCount());
  } // end of bSuchen_ActionPerformed
  
  public Kunde getKunde() {
    return k;
  }

  public void bWeiter1_ActionPerformed(ActionEvent evt) {
    if (gewaehlt) {
      k = db.ladeKunde(Integer.parseInt(tKunde.getValueAt(tKunde.getSelectedRow(), 0).toString()));
      dispose();
    } else {
      lStatus.setText("Wählen Sie einen Kunden aus!");
    } // end of if-else            )
    
  } // end of bWeiter1_ActionPerformed

  public void loadTableKunde(ArrayList<Kunde> k){ 
    tKundeModel.setNumRows(0);
    String[] colname = {"ID", "Name", "Vorname",  "Strasse", "Hausnummer",  "Postleitzahl", "Wohnort",  "Mitglied"};
    tKundeModel.setColumnIdentifiers(colname);
    for (int i = 0; i < k.size(); i++) {
      String[] row = {k.get(i).getK_id()+"", k.get(i).getName(), k.get(i).getVorname(),k.get(i).getStrasse(), k.get(i).getHausnummer(), k.get(i).getPlz(), k.get(i).getOrt(),  k.get(i).getMitglied()};
      tKundeModel.addRow(row);
    }
  }
  
  public void tKunde_MouseClicked(MouseEvent evt) {
    k = db.ladeKunde(Integer.parseInt(tKunde.getValueAt(tKunde.getSelectedRow(), 0).toString()));
    lStatus.setText(k.getK_id()+". "+k.getName()+ ", " +k.getVorname());  
    gewaehlt = true;
  } // end of tKunde_MouseClicked

  public void Kundewaehlen_WindowActivated(WindowEvent evt) {
    loadTableKunde(kunden);
  } // end of Kundewaehlen_WindowActivate
  
  public void Kundewaehlen_WindowClosing(WindowEvent evt) {
    k = null;
  } // end of Geraetewaehlen_WindowClosing
  
  public void bHinzufuegen_ActionPerformed(ActionEvent evt) {
    Kundehinzufuegen kh = new Kundehinzufuegen(owner, true, null);
    kunden = db.ladeKunden("WHERE Name != '' and Vorname != ''");
    loadTableKunde(kunden);
  } // end of bHinzufuegen_ActionPerformed

  // Ende Methoden
  
} // end of class Kundewaehlen

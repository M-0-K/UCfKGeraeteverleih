
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
  private DB db = new DB();
  private JTable tKunde = new JTable(5, 5);
    private DefaultTableModel tKundeModel = (DefaultTableModel) tKunde.getModel();
    private JScrollPane tKundeScrollPane = new JScrollPane(tKunde);
  private JTextField tfName = new JTextField();
  private JButton bSuchen1 = new JButton();
  private JButton bAuswaehlen = new JButton();
  private JLabel lStatus = new JLabel();
  private ArrayList<Kunde> kunden;
  private Kunde k; 
  // Ende Attribute
  
  public Kundewaehlen(JFrame owner, boolean modal) { 
    // Dialog-Initialisierung
    super(owner, modal);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 659; 
    int frameHeight = 554;
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
    tfName.setBounds(8, 8, 353, 25);
    tfName.setText("");
    cp.add(tfName);
    bSuchen1.setBounds(368, 8, 75, 25);
    bSuchen1.setText("Suchen");
    bSuchen1.setMargin(new Insets(2, 2, 2, 2));
    bSuchen1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bSuchen1_ActionPerformed(evt);
      }
    });
    cp.add(bSuchen1);
    bAuswaehlen.setBounds(328, 480, 310, 25);
    bAuswaehlen.setText("Auswaehlen");
    bAuswaehlen.setMargin(new Insets(2, 2, 2, 2));
    bAuswaehlen.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bAuswaehlen_ActionPerformed(evt);
      }
    });
    
    
    cp.add(bAuswaehlen);
    lStatus.setBounds(9, 480, 310, 25);
    lStatus.setText("");
    cp.add(lStatus);
    addWindowListener(new WindowAdapter() { 
      public void windowActivated(WindowEvent evt) { 
        Kundewaehlen_WindowActivated(evt);
      }
    });
    
    kunden = db.ladeKunden();
    // Ende Komponenten
    
    setResizable(false);
    setVisible(true);
    
    
  } // end of public Kundewaehlen
  
  // Anfang Methoden
  public void bSuchen1_ActionPerformed(ActionEvent evt) {
    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) tKunde.getModel())); 
    sorter.setRowFilter(RowFilter.regexFilter(tfName.getText()));
    tKunde.setRowSorter(sorter);
  } // end of bSuchen1_ActionPerformed
  
  public Kunde getKunde() {
    return k;
  }

  public void bAuswaehlen_ActionPerformed(ActionEvent evt) {
    k = kunden.get(Integer.parseInt(tKundeModel.getValueAt(tKunde.getSelectedRow() ,0).toString())-1);
    dispose();
  } // end of bAuswaehlen_ActionPerformed

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
    k = kunden.get(Integer.parseInt(tKundeModel.getValueAt(tKunde.getSelectedRow() ,0).toString())-1);
    lStatus.setText(k.getK_id()+". "+k.getName()+ ", " +k.getVorname());  
    
    ListSelectionModel i = tKunde.getSelectionModel();
    System.out.println(i.getMinSelectionIndex());
  } // end of tKunde_MouseClicked

  public void Kundewaehlen_WindowActivated(WindowEvent evt) {
    loadTableKunde(kunden);
  } // end of Kundewaehlen_WindowActivated

  // Ende Methoden
  
} // end of class Kundewaehlen

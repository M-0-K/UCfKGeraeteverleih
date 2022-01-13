
import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;
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

public class Geraetewaehlen extends JDialog {
  // Anfang Attribute
  private JTable tGeraeteauswahl = new JTable(5, 5);
    private DefaultTableModel tGeraeteauswahlModel = (DefaultTableModel) tGeraeteauswahl.getModel();
    private JScrollPane tGeraeteauswahlScrollPane = new JScrollPane(tGeraeteauswahl);
  private JTable tGeraetewahl = new JTable(5, 5);
    private DefaultTableModel tGeraetewahlModel = (DefaultTableModel) tGeraetewahl.getModel();
    private JScrollPane tGeraetewahlScrollPane = new JScrollPane(tGeraetewahl);
  private JTextField tfSuchen = new JTextField();
  private JButton bSuchen = new JButton();
  private JButton bWeiter = new JButton();
  private DB db = new DB();
  private ArrayList<Geraet> gauswahl;
  private ArrayList<Geraet> gwahl = new ArrayList<Geraet>();
  // Ende Attribute
  
  public Geraetewaehlen(JFrame owner, boolean modal) { 
    // Dialog-Initialisierung
    super(owner, modal);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 1427; 
    int frameHeight = 741;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Geraetewaehlen");
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    tGeraeteauswahlScrollPane.setBounds(16, 48, 692, 606);
    tGeraeteauswahl.getColumnModel().getColumn(0).setHeaderValue("Title 1");
    tGeraeteauswahl.getColumnModel().getColumn(1).setHeaderValue("Title 2");
    tGeraeteauswahl.getColumnModel().getColumn(2).setHeaderValue("Title 3");
    tGeraeteauswahl.getColumnModel().getColumn(3).setHeaderValue("Title 4");
    tGeraeteauswahl.getColumnModel().getColumn(4).setHeaderValue("Title 5");
    tGeraeteauswahl.addMouseListener(new MouseAdapter() { 
      public void mouseClicked(MouseEvent evt) { 
        tGeraeteauswahl_MouseClicked(evt);
      }
    });
    cp.add(tGeraeteauswahlScrollPane);
    tGeraetewahlScrollPane.setBounds(728, 48, 668, 606);
    tGeraetewahl.getColumnModel().getColumn(0).setHeaderValue("Title 1");
    tGeraetewahl.getColumnModel().getColumn(1).setHeaderValue("Title 2");
    tGeraetewahl.getColumnModel().getColumn(2).setHeaderValue("Title 3");
    tGeraetewahl.getColumnModel().getColumn(3).setHeaderValue("Title 4");
    tGeraetewahl.getColumnModel().getColumn(4).setHeaderValue("Title 5");
    tGeraetewahl.addMouseListener(new MouseAdapter() { 
      public void mouseClicked(MouseEvent evt) { 
        tGeraetewahl_MouseClicked(evt);
      }
    });
    cp.add(tGeraetewahlScrollPane);
    tfSuchen.setBounds(16, 16, 609, 25);
    cp.add(tfSuchen);
    bSuchen.setBounds(632, 16, 75, 25);
    bSuchen.setText("Suchen");
    bSuchen.setMargin(new Insets(2, 2, 2, 2));
    bSuchen.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bSuchen_ActionPerformed(evt);
      }
    });
    cp.add(bSuchen);
    bWeiter.setBounds(1320, 664, 75, 25);
    bWeiter.setText("Weiter");
    bWeiter.setMargin(new Insets(2, 2, 2, 2));
    bWeiter.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bWeiter_ActionPerformed(evt);
      }
    });
    cp.add(bWeiter);
    
    addWindowListener(new WindowAdapter() { 
      public void windowActivated(WindowEvent evt) { 
        Geraetewaehlen_WindowActivated(evt);
      }
    });
    
    tGeraetewahlModel.setNumRows(0);
    String[] colname = {"ID", "Bezeichnung", "Anschaffungspreis", "Anschaffungsdatum", "Mietpreisklasse1", "Mietpreisklasse2", "Mietpreisklasse3", "Zustand"};
    tGeraetewahlModel.setColumnIdentifiers(colname);
    
    gauswahl = db.ladeGeraete();
    // Ende Komponenten
     
    setResizable(false);
    setVisible(true);
  } // end of public Geraetewaehlen
  
  // Anfang Methoden
  public void bSuchen_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einf�gen
    
  } // end of bSuchen_ActionPerformed

  public void bWeiter_ActionPerformed(ActionEvent evt) {
    dispose();
    
  } // end of bWeiter_ActionPerformed

  public ArrayList<Geraet> getGereat(){
    return gwahl;
    }
  
  public void Geraetewaehlen_WindowActivated(WindowEvent evt) {
    // TODO hier Quelltext einf�gen
    //Hier muss noch Ger�te Where zur�ckgegeben eingef�gt werden --- Es k�nnen ja keine Ger�te vermietet werden, die nicht da sind!
    loadTabelleGeraetauswahl(gauswahl);
  } // end of Geraetewaehlen_WindowActivated

  public void tGeraetewahl_MouseClicked(MouseEvent evt) {
    gauswahl.add(gauswahl.get(tGeraetewahl.getSelectedRow()));
    gwahl.remove(tGeraetewahl.getSelectedRow());
    loadTabelleGeraetauswahl(gauswahl);
    loadTabelleGeraetwahl(gwahl);
  } // end of tGeraetewahl_MouseClicked

  public void tGeraeteauswahl_MouseClicked(MouseEvent evt) {
    System.out.println(tGeraeteauswahl.getSelectedRow());
    gwahl.add(gauswahl.get(tGeraeteauswahl.getSelectedRow()));
    gauswahl.remove(tGeraeteauswahl.getSelectedRow());
    loadTabelleGeraetauswahl(gauswahl);
    loadTabelleGeraetwahl(gwahl);
  } // end of tGeraeteauswahl_MouseClicked
  
  
  public void loadTabelleGeraetauswahl(ArrayList<Geraet> g) {
    tGeraeteauswahlModel.setNumRows(0);
    String[] colname = {"ID", "Bezeichnung","Mietpreisklasse1", "Mietpreisklasse2", "Mietpreisklasse3", "Zustand"};
    tGeraeteauswahlModel.setColumnIdentifiers(colname);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    for (int i = 0; i < g.size(); i++) {   
      String[] row = {g.get(i).getG_id()+"", g.get(i).getBezeichnung(), g.get(i).getMietpreisklasse()[0]+"�", g.get(i).getMietpreisklasse()[1]+"�", g.get(i).getMietpreisklasse()[2]+"�", g.get(i).getZustand()};
      tGeraeteauswahlModel.addRow(row);
    }
  }
  
  public void loadTabelleGeraetwahl(ArrayList<Geraet> g) {
    tGeraetewahlModel.setNumRows(0);
    String[] colname = {"ID", "Bezeichnung","Mietpreisklasse1", "Mietpreisklasse2", "Mietpreisklasse3", "Zustand"};
    tGeraetewahlModel.setColumnIdentifiers(colname);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    for (int i = 0; i < g.size(); i++) {   
      String[] row = {g.get(i).getG_id()+"", g.get(i).getBezeichnung(), g.get(i).getMietpreisklasse()[0]+"�", g.get(i).getMietpreisklasse()[1]+"�", g.get(i).getMietpreisklasse()[2]+"�", g.get(i).getZustand()};
      tGeraetewahlModel.addRow(row);
    }
  }
  // Ende Methoden
  
} // end of class Geraetewaehlen
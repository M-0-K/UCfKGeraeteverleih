
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyListener;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.event.KeyEvent;

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
  private JButton bSuchen1 = new JButton();
  private JButton bWeiter1 = new JButton();
  private DB db = new DB();
  private ArrayList<Geraet> gauswahl;
  private ArrayList<Geraet> gwahl = new ArrayList<Geraet>();
  private JLabel lStatus = new JLabel();
  TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) tGeraeteauswahl.getModel())); 
  private JNumberField jNumberField1 = new JNumberField();
  // Ende Attribute
  
  public Geraetewaehlen(JFrame owner, boolean modal) { 
    // Dialog-Initialisierung
    super(owner, modal);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 1418; 
    int frameHeight = 740;
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
    //cp.add(tfSuchen);
    bSuchen1.setBounds(632, 16, 75, 25);
    bSuchen1.setText("suchen");
    bSuchen1.setMargin(new Insets(2, 2, 2, 2));
    bSuchen1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bSuchen1_ActionPerformed(evt);
      }
    });
    cp.add(bSuchen1);
    bWeiter1.setBounds(1320, 664, 75, 25);
    bWeiter1.setText("weiter");
    bWeiter1.setMargin(new Insets(2, 2, 2, 2));
    bWeiter1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bWeiter1_ActionPerformed(evt);
      }
    });
    cp.add(bWeiter1);
    
    addWindowListener(new WindowAdapter() { 
      public void windowActivated(WindowEvent evt) { 
        Geraetewaehlen_WindowActivated(evt);
      }
    });
    
    tGeraetewahlModel.setNumRows(0);
    String[] colname = {"ID", "Bezeichnung", "Anschaffungspreis", "Anschaffungsdatum", "Mietpreisklasse1", "Mietpreisklasse2", "Mietpreisklasse3", "Zustand"};
    tGeraetewahlModel.setColumnIdentifiers(colname);
    
    gauswahl = db.ladeGeraete("");
    ArrayList<Geraet> gnicht = db.ladeGeraete("left JOIN mietvertrag ON geraet.G_id = mietvertrag.G_id Where mietvertrag.Status = false or geraet.Zustand = 'defekt'  GROUP by geraet.G_id asc order by geraet.G_id asc");
    
    for (int i = 0; i < gnicht.size(); i++) {
      for (int j = 0; j < gauswahl.size(); j++) {
        if (gnicht.get(i).getG_id() == gauswahl.get(j).getG_id()) {
          gauswahl.remove(j);
        } // end of if
      }
    }
    
    lStatus.setBounds(16, 664, 1294, 25);
    lStatus.setText("");
    cp.add(lStatus);
    addWindowListener(new WindowAdapter() { 
      public void windowClosing(WindowEvent evt) { 
        Geraetewaehlen_WindowClosing(evt);
      }
    });
    
    jNumberField1.setBounds(0, 1048, 80, 24);
    cp.add(jNumberField1);
    
    
    tfSuchen.addKeyListener(new CustomKeyListener());
    cp.add(tfSuchen);
    // Ende Komponenten
     
    setResizable(false);
    setVisible(true);
    

  } // end of public Geraetewaehlen
  
  // Anfang Methoden
  public void bSuchen1_ActionPerformed(ActionEvent evt) {
    sorter.setRowFilter(RowFilter.regexFilter(tfSuchen.getText()));
    tGeraeteauswahl.setRowSorter(sorter);
    sorter.setModel(tGeraeteauswahl.getModel());  
    tGeraeteauswahl.convertRowIndexToModel(tGeraeteauswahl.getRowCount());
    
  } // end of bSuchen1_ActionPerformed

  public void bWeiter1_ActionPerformed(ActionEvent evt) {
    if(gwahl.size() == 0){
      lStatus.setText("Wählen sie Geraete aus!");
      }else {
      dispose();   
       } // end of if-else
    
  } // end of bWeiter1_ActionPerformed

  public ArrayList<Geraet> getGereat(){
    return gwahl;
    }
  
  public void Geraetewaehlen_WindowActivated(WindowEvent evt) {
    // TODO hier Quelltext einfügen
    //Hier muss noch Geräte Where zurückgegeben eingefügt werden --- Es können ja keine Geräte vermietet werden, die nicht da sind!
    loadTabelleGeraetauswahl(gauswahl);
  } // end of Geraetewaehlen_WindowActivated

  public void tGeraetewahl_MouseClicked(MouseEvent evt) { 
    Geraet tempg = db.ladeGeraet(Integer.parseInt(tGeraetewahl.getValueAt(tGeraetewahl.getSelectedRow(), 0).toString()));
    boolean flag = true;
    for(Geraet g : gauswahl){
          if(g.getG_id() == tempg.getG_id()){
          flag = false;  
    }
         }
         if(flag){ 
         gauswahl.add(tempg);
         for (int i = 0; i < gwahl.size(); i++) {
         if (tempg.getG_id() == gwahl.get(i).getG_id()) {
        gwahl.remove(i);
        } // end of if
    }
          }
    loadTabelleGeraetauswahl(gauswahl);
    loadTabelleGeraetwahl(gwahl);
  } // end of tGeraetewahl_MouseClicked

  public void tGeraeteauswahl_MouseClicked(MouseEvent evt) {
    Geraet tempg = db.ladeGeraet(Integer.parseInt(tGeraeteauswahl.getValueAt(tGeraeteauswahl.getSelectedRow(), 0).toString()));
    //gwahl.add(tempg);
    boolean flag = true;
    for(Geraet g : gwahl){
          if(g.getG_id() == tempg.getG_id()){
          flag = false;  
            }
         }
         if(flag){ 
         gwahl.add(tempg);
         for (int i = 0; i < gauswahl.size(); i++) {
         if (tempg.getG_id() == gauswahl.get(i).getG_id()) {
            gauswahl.remove(i);
        } // end of if
    }
          }
    loadTabelleGeraetauswahl(gauswahl);
    loadTabelleGeraetwahl(gwahl);
  } // end of tGeraeteauswahl_MouseClicked
  
  
  public void loadTabelleGeraetauswahl(ArrayList<Geraet> g) {
    tGeraeteauswahlModel.setNumRows(0);
    String[] colname = {"ID", "Bezeichnung","Mietpreisklasse1", "Mietpreisklasse2", "Mietpreisklasse3", "Zustand"};
    tGeraeteauswahlModel.setColumnIdentifiers(colname);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    for (int i = 0; i < g.size(); i++) {   
      String[] row = {g.get(i).getG_id()+"", g.get(i).getBezeichnung(), g.get(i).getMietpreisklasse()[0]+"€", g.get(i).getMietpreisklasse()[1]+"€", g.get(i).getMietpreisklasse()[2]+"€", g.get(i).getZustand()};
      tGeraeteauswahlModel.addRow(row);
    }
  }
  
  public void loadTabelleGeraetwahl(ArrayList<Geraet> g) {
    tGeraetewahlModel.setNumRows(0);
    String[] colname = {"ID", "Bezeichnung","Mietpreisklasse1", "Mietpreisklasse2", "Mietpreisklasse3", "Zustand"};
    tGeraetewahlModel.setColumnIdentifiers(colname);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    for (int i = 0; i < g.size(); i++) {   
      String[] row = {g.get(i).getG_id()+"", g.get(i).getBezeichnung(), g.get(i).getMietpreisklasse()[0]+"€", g.get(i).getMietpreisklasse()[1]+"€", g.get(i).getMietpreisklasse()[2]+"€", g.get(i).getZustand()};
      tGeraetewahlModel.addRow(row);
    }          
  }
  
  public void Geraetewaehlen_WindowClosing(WindowEvent evt) {
    gwahl.removeAll(gwahl);
  } // end of Geraetewaehlen_WindowClosing   
  // Ende Methoden
  class CustomKeyListener implements KeyListener{
    public void keyTyped(KeyEvent e){}
    
    public void keyPressed(KeyEvent e) {
         //System.out.println("Pressed!:");
      if(e.getKeyCode() == KeyEvent.VK_ENTER){
         Geraet tempg = db.ladeGeraet(Integer.parseInt(tfSuchen.getText()));
         System.out.println(tempg.getG_id());
         boolean flag = true;
         for(Geraet g : gwahl){
          if(g.getG_id() == tempg.getG_id()){
          flag = false;  
            }
         }
         if(flag){ 
         gwahl.add(tempg);
         for (int i = 0; i < gauswahl.size(); i++) {
         if (tempg.getG_id() == gauswahl.get(i).getG_id()) {
            gauswahl.remove(i);
        } // end of if
          }
           loadTabelleGeraetauswahl(gauswahl);
           loadTabelleGeraetwahl(gwahl);
           tfSuchen.setText("");
        } else {
           sorter.setRowFilter(RowFilter.regexFilter(tfSuchen.getText()));
           tGeraeteauswahl.setRowSorter(sorter);
           sorter.setModel(tGeraeteauswahl.getModel());  
           tGeraeteauswahl.convertRowIndexToModel(tGeraeteauswahl.getRowCount());
        } // end of if-else
    }
      }
    
    public void keyReleased(KeyEvent e){}
  }

  
  
} // end of class Geraetewaehlen



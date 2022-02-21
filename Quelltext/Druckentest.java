import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.PrintJob;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 21.02.2022
 * @author Moritz Kockert
 */

public class Druckentest extends JFrame {
  // Anfang Attribute
  private JButton bDrucken = new JButton();
  private JPanel jPanel1 = new JPanel(null, true);
    private JLabel jLabel1 = new JLabel();
    private JTextArea jTextArea1 = new JTextArea("");
      private JScrollPane jTextArea1ScrollPane = new JScrollPane(jTextArea1);
    private JLabel jLabel2 = new JLabel();
  private JTable jTable1 = new JTable(5, 5);
    private DefaultTableModel jTable1Model = (DefaultTableModel) jTable1.getModel();
    private JScrollPane jTable1ScrollPane = new JScrollPane(jTable1);
  // Ende Attribute
  
  public Druckentest() { 
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 887; 
    int frameHeight = 707;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Druckentest");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    bDrucken.setBounds(368, 640, 75, 25);
    bDrucken.setText("Drucken");
    bDrucken.setMargin(new Insets(2, 2, 2, 2));
    bDrucken.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bDrucken_ActionPerformed(evt);
      }
    });
    cp.add(bDrucken);
    jPanel1.setBounds(189, 16, 420, 594);
    jPanel1.setOpaque(false);
    cp.add(jPanel1);
    jLabel1.setBounds(11, 0, 110, 20);
    jLabel1.setText("text");
    jPanel1.add(jLabel1);
    jTextArea1ScrollPane.setBounds(11, 24, 264, 68);
    jTextArea1.setText("Hallo dieses Feld ist ein Testfeld\nIch Teste auf dieser seite das Drucken");
    jPanel1.add(jTextArea1ScrollPane);
    jTable1ScrollPane.setBounds(66, 211, 300, 150);
    jTable1.getColumnModel().getColumn(0).setHeaderValue("Title 1");
    jTable1.getColumnModel().getColumn(1).setHeaderValue("Title 2");
    jTable1.getColumnModel().getColumn(2).setHeaderValue("Title 3");
    jTable1.getColumnModel().getColumn(3).setHeaderValue("Title 4");
    jTable1.getColumnModel().getColumn(4).setHeaderValue("Title 5");
    jPanel1.add(jTable1ScrollPane);
    jLabel2.setBounds(150, 571, 110, 20);
    jLabel2.setText("text");
    jPanel1.add(jLabel2);
    // Ende Komponenten
    
    setVisible(true);
  } // end of public Druckentest
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new Druckentest();
  } // end of main
  
  public void bDrucken_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    PrintSuit ps = new PrintSuit(jPanel1);
    ps.print();
  } // end of bDrucken_ActionPerformed

  // Ende Methoden
} // end of class Druckentest


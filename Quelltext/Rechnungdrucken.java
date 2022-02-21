import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

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
    private JLabel lUnitedClubsforKuloweV1 = new JLabel();
    private JLabel lRechnung = new JLabel();
    private JTextArea jTextArea1 = new JTextArea("");
      private JScrollPane jTextArea1ScrollPane = new JScrollPane(jTextArea1);
    private JTextArea jTextArea2 = new JTextArea("");
      private JScrollPane jTextArea2ScrollPane = new JScrollPane(jTextArea2);
  private JButton bDrucken = new JButton();
  // Ende Attribute
  
  public Rechnungdrucken(JFrame owner, boolean modal) { 
    // Dialog-Initialisierung
    super(owner, modal);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 457; 
    int frameHeight = 684;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Rechnungdrucken");
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    pRechnung.setBounds(11, 6, 420, 594);
    pRechnung.setOpaque(false);
    cp.add(pRechnung);
    bDrucken.setBounds(176, 616, 75, 25);
    bDrucken.setText("Drucken");
    bDrucken.setMargin(new Insets(2, 2, 2, 2));
    bDrucken.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bDrucken_ActionPerformed(evt);
      }
    });
    cp.add(bDrucken);
    lUnitedClubsforKuloweV1.setBounds(13, 10, 156, 20);
    lUnitedClubsforKuloweV1.setText("United Clubs for Kulow e.V,");
    pRechnung.add(lUnitedClubsforKuloweV1);
    lRechnung.setBounds(13, 194, 110, 20);
    lRechnung.setText("Rechnung");
    pRechnung.add(lRechnung);
    jTextArea1ScrollPane.setBounds(13, 90, 136, 100);
    pRechnung.add(jTextArea1ScrollPane);
    jTextArea2ScrollPane.setBounds(205, 34, 200, 100);
    pRechnung.add(jTextArea2ScrollPane);
    // Ende Komponenten
    
    setResizable(false);
    setVisible(true);
  } // end of public Rechnungdrucken
  
  // Anfang Methoden
  public void bDrucken_ActionPerformed(ActionEvent evt) {
    PrintSuit ps = new PrintSuit(pRechnung);
    ps.print();
  } // end of bDrucken_ActionPerformed

  // Ende Methoden
  
} // end of class Rechnungdrucken


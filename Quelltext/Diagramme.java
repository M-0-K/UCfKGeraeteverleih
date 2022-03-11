import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.ArrayList;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 11.03.2022
 * @author Moritz Kockert
 */

public class Diagramme extends JDialog {
  // Anfang Attribute
  private Canvas canvas = new Canvas();
  private JButton bAbbrechen1 = new JButton();
  private JButton bDrucken = new JButton();
  // Ende Attribute
  
  public Diagramme(JFrame owner, boolean modal) { 
    // Dialog-Initialisierung
    super(owner, modal);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 1040; 
    int frameHeight = 699;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Diagramme");
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    canvas.setBounds(16, 16, 1000, 600);
    cp.add(canvas);
    bAbbrechen1.setBounds(8, 624, 75, 25);
    bAbbrechen1.setText("abbrechen");
    bAbbrechen1.setMargin(new Insets(2, 2, 2, 2));
    bAbbrechen1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bAbbrechen1_ActionPerformed(evt);
      }
    });
    cp.add(bAbbrechen1);
    bDrucken.setBounds(936, 624, 75, 25);
    bDrucken.setText("drucken");
    bDrucken.setMargin(new Insets(2, 2, 2, 2));
    bDrucken.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bDrucken_ActionPerformed(evt);
      }
    });
    cp.add(bDrucken);
    // Ende Komponenten
    
    setResizable(false);
    setVisible(true);
    
  } // end of public Diagramme
  
  // Anfang Methoden
  public void bAbbrechen1_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    
  } // end of bAbbrechen1_ActionPerformed

  public void bDrucken_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    DB db = new DB();
    ladeJahresuebersicht(db.ladeRechnungen(""));
  } // end of bDrucken_ActionPerformed
  
  public void ladeJahresuebersicht(ArrayList<Rechnung> r){
    String[] jahr = {"Januar", "Februar", "Maerz", "April", "Mai", "Juni", "Juli", "August","September","Oktober","November","Dezember"};
    int[] ranzahl = new int[12];
    int[] manzahl = new int[12];
    int manzahlg = 0;
    int maxr = 0;
    int maxm = 0;
    
    for (int i = 0; i < r.size(); i++) {
      ranzahl[r.get(i).getRechnungsdatum().getMonthValue()]++;
      if (maxr<ranzahl[r.get(i).getRechnungsdatum().getMonthValue()]) {
        maxr = ranzahl[r.get(i).getRechnungsdatum().getMonthValue()];
      } // end of if
      for (int j = 0; j < r.get(i).getMietvertraege().size(); j++) {
        manzahl[r.get(i).getMietvertraege().get(j).getRueckgabe().getMonthValue()]++;
        manzahlg++;
        if (maxm<manzahl[r.get(i).getMietvertraege().get(j).getRueckgabe().getMonthValue()]) {
          maxm = manzahl[r.get(i).getMietvertraege().get(j).getRueckgabe().getMonthValue()];
        } // end of if
      }
    }
    int multir = 390 / maxr;
    int multim = 390 / maxm;
    
    
    
    Graphics g = canvas.getGraphics();
    Font font = new Font("Verdana", Font.PLAIN, 10);
    g.setFont(font);
    for (int i = 0; i < jahr.length; i++) {
      g.setColor(Color.black);
      g.drawString(jahr[i],i*70+80,500);
      g.setColor(Color.blue);
      g.fillRect(i*70+80,490,10, -ranzahl[i]*multir);
      g.drawString(ranzahl[i]+"",i*70+80,490-ranzahl[i]*multir-10);
      g.setColor(Color.green);
      g.fillRect(i*70+90,490,10,  -manzahl[i]*multim);
      g.drawString(manzahl[i]+"",i*70+90,490-manzahl[i]*multim-10);     
    }
    
    /*
    g.setColor(Color.red);   g.drawLine(0,10,w1,10);
    g.setColor(Color.green); g.drawLine(0,20,w2,20);
    g.setColor(Color.blue);  g.drawLine(0,30,w3,30);
    g.setColor(Color.black); 
    
    // Canvas-Rectangle
    g.setColor(Color.red);   g.fillRect(0,60,w1,10);
    g.setColor(Color.green); g.fillRect(0,80,w2,10);
    g.setColor(Color.blue);  g.fillRect(0,100,w3,10);  
    */
    }

  // Ende Methoden
  
} // end of class Diagramme


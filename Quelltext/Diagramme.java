import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.Image;
import java.awt.print.*;
import java.awt.print.PageFormat;
import java.awt.PrintJob;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 11.03.2022
 * @author Moritz Kockert
 */

public class Diagramme extends JDialog {
  // Anfang Attribute
  private JButton bZurueck = new JButton();
  private JButton bSpeichern = new JButton();
  private Graphics2D g;
  private JComboBox<String> cbJahre = new JComboBox<String>();
    private DefaultComboBoxModel<String> cbJahreModel = new DefaultComboBoxModel<String>();
  private JLabel lJahresuebersicht1 = new JLabel();
  private DB db = new DB();
  private JButton bLaden = new JButton();
  
  private JFileChooser jFileChooser2 = new JFileChooser();
  private JFrame frame = null;
  private JPanel jPanel1 = new JPanel(null, true);
  // Ende Attribute
  
  public Diagramme(JFrame owner, boolean modal) { 
    // Dialog-Initialisierung
    super(owner, modal);
    frame = owner;
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 945; 
    int frameHeight = 642;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Diagramme");
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    bZurueck.setBounds(16, 568, 99, 25);
    bZurueck.setText("zurueck");
    bZurueck.setMargin(new Insets(2, 2, 2, 2));
    bZurueck.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bZurueck_ActionPerformed(evt);
      }
    });
    cp.add(bZurueck);
    bSpeichern.setBounds(816, 568, 99, 25);
    bSpeichern.setText("speichern");
    bSpeichern.setMargin(new Insets(2, 2, 2, 2));
    bSpeichern.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bSpeichern_ActionPerformed(evt);
      }
    });
    cp.add(bSpeichern);
    cbJahre.setModel(cbJahreModel);
    cbJahre.setBounds(134, 16, 150, 20);
    cp.add(cbJahre);
    lJahresuebersicht1.setBounds(16, 16, 110, 20);
    lJahresuebersicht1.setText("Jahresübersicht:");
    cp.add(lJahresuebersicht1);
    bLaden.setBounds(296, 16, 75, 25);
    bLaden.setText("laden");
    bLaden.setMargin(new Insets(2, 2, 2, 2));
    bLaden.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bLaden_ActionPerformed(evt);
      }
    });
    cp.add(bLaden);
    jPanel1.setBounds(3, 46, 900, 500);
    jPanel1.setOpaque(true);
    jPanel1.setBackground(Color.WHITE);
    cp.add(jPanel1);
    // Ende Komponenten
    
    int[] jahre = db.ladeRechnungsjahre(); 
    for (int i = 0; i < jahre.length; i++) {
      cbJahreModel.addElement(jahre[i]+"");
    }
   
    
    setResizable(false);
    setVisible(true); 
  } // end of public Diagramme
  
  // Anfang Methoden
  public void bZurueck_ActionPerformed(ActionEvent evt) {
    dispose();
  } // end of bZurueck_ActionPerformed

  public void bSpeichern_ActionPerformed(ActionEvent evt) { 
    FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Datei", "jpg");
    jFileChooser2.setFileFilter(filter);

    BufferedImage image = new BufferedImage(jPanel1.getWidth(), jPanel1.getHeight(), BufferedImage.TYPE_INT_RGB);
    Graphics2D g2 = image.createGraphics();
    jPanel1.paintAll(g2);
    ladeJahresuebersicht(g2, db.ladeRechnungen("WHERE YEAR(rechnungsdatum) = "+ cbJahreModel.getSelectedItem().toString()));

    try {                                         
      ImageIO.write(image, "jpg", new File(jFileChooser2_saveFile().getAbsolutePath()+".jpg"));
        } catch (Exception e) {
      System.out.println(e.getMessage()); 
        }
  } // end of bSpeichern_ActionPerformed
  
  public void ladeJahresuebersicht(Graphics2D g, ArrayList<Rechnung> r){
    g.clearRect(0,0,900,500);
    g.setColor(new Color(255, 255, 255, 255));
    g.fillRect(0,0,900,500);
    g.setBackground(new Color(255, 255, 255, 255));
    String[] jahr = {"Januar", "Februar", "Maerz", "April", "Mai", "Juni", "Juli", "August","September","Oktober","November","Dezember"};
    int[] ranzahl = new int[12];
    int[] manzahl = new int[12];
    int manzahlg = 0;
    int maxr = 0;
    int maxm = 0;
    
    for (int i = 0; i < r.size(); i++) {
      ranzahl[r.get(i).getRechnungsdatum().getMonthValue()-1]++;
      if (maxr<ranzahl[r.get(i).getRechnungsdatum().getMonthValue()-1]) {
        maxr = ranzahl[r.get(i).getRechnungsdatum().getMonthValue()-1];
      } // end of if
      for (int j = 0; j < r.get(i).getMietvertraege().size(); j++) {
        manzahl[r.get(i).getRechnungsdatum().getMonthValue()-1]++;
        manzahlg++;
        if (maxm<manzahl[r.get(i).getRechnungsdatum().getMonthValue()-1]) {
          maxm = manzahl[r.get(i).getRechnungsdatum().getMonthValue()-1];
        } // end of if
      }
    }
    int multir = 390 / maxr;
    int multim = 390 / maxm;
    
    Font font = new Font("Verdana", Font.PLAIN, 10);
    g.setFont(font);
    for (int i = 0; i < jahr.length; i++) {
      g.setColor(Color.black);
      g.drawString(jahr[i],i*70+30,450);
      g.setColor(Color.blue);
      g.fillRect(i*70+30,440-ranzahl[i]*multir,20, 440-(440-ranzahl[i]*multir));
      g.drawString(ranzahl[i]+"",i*70+30,440-ranzahl[i]*multir-10);
      g.setColor(Color.red);
      g.fillRect(i*70+50,440-manzahl[i]*multim,20,  440-(440-manzahl[i]*multim));
      g.drawString(manzahl[i]+"",i*70+50,440-manzahl[i]*multim-10);    
    }
    
    g.setColor(Color.black);
    font = new Font("Verdana", Font.PLAIN, 14);
    g.setFont(font);
    g.drawString("Anzahl der Rechnungen",200,485);
    g.drawString("Anzahl der vermieteten Geraete",400,485);
    
    g.drawLine(30,440,870,440);
    g.drawLine(30,440,30,40);
    
    g.setColor(Color.blue);
    g.fillRect(190,485,10,-10);
    g.setColor(Color.red);
    g.fillRect(390,485,10,-10);
    }
  

  public void bLaden_ActionPerformed(ActionEvent evt) {
    g = (Graphics2D) jPanel1.getGraphics();
    ladeJahresuebersicht(g, db.ladeRechnungen("WHERE YEAR(rechnungsdatum) = "+ cbJahreModel.getSelectedItem().toString()));
    
  } // end of bLaden_ActionPerformed

  public File jFileChooser2_saveFile() {
    if (jFileChooser2.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
      return jFileChooser2.getSelectedFile();
    } else {
      return null;
    }
  }
  
  // Ende Methoden
} // end of class Diagramme


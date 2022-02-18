import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JComponent;
import org.jdatepicker.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 17.02.2022
 * @author 
 */

public class Datepickertest extends JFrame {
  // Anfang Attribute
  private JDatePanel datepanel = new JDatePanel();
  private JPanel jPanel1 = new JPanel();
  private JPanel jPanel2 = new JPanel();
  private JDatePicker picker = new JDatePicker();
  private JButton jButton1 = new JButton();
  // Ende Attribute
  
  public Datepickertest() { 
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 386; 
    int frameHeight = 415;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Datepickertest");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    jButton1.setBounds(192, 272, 75, 25);
    jButton1.setText("jButton1");
    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButton1_ActionPerformed(evt);
      }
    });
    cp.add(jButton1);
    // Ende Komponenten
    
        picker.setTextEditable(true);
        picker.setShowYearButtons(true);
        jPanel2.add((JComponent) picker);
  
    
    
        jPanel2.setBounds(1, 52, 364, 100);
        jPanel2.setOpaque(false);
        cp.add(jPanel2);
    
        
    
    setVisible(true);
  } // end of public Datepickertest
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new Datepickertest();
  } // end of main
  
  public void jButton1_ActionPerformed(ActionEvent evt) {
    System.out.println(jdpgetLocalDate(picker));
    System.out.println(picker.getModel().getMonth());
    
  } // end of jButton1_ActionPerformed
  
  public LocalDate jdpgetLocalDate(JDatePicker p){
    if (p != null) {
      LocalDate ld = LocalDate.parse(
      p.getModel().getDay()+"."+
      (p.getModel().getMonth()+1)+"."+
      p.getModel().getYear()
      , DateTimeFormatter.ofPattern("d.M.yyyy"));
      return ld;
    } // end of i
  return null;
  }
  // Ende Methoden
} // end of class Datepickertest

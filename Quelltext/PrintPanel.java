import java.awt.*;
import javax.swing.*;
import java.awt.print.*;


public class PrintPanel extends JPanel implements Printable {
  
  public PrintPanel() {
    setBackground(Color.white);
    setPreferredSize(new Dimension(300, 200));
  }
  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.black);
    g.drawRect(20,20,100,50);
    g.fillOval(80,80,60,30);
    g.drawString("Drucken aus Swing ist einfach",100,150);
    g.setColor(Color.red);
    g.drawRect(0,0,299,199);
  }
  
  public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
    if (pageIndex > 0) {
      return(NO_SUCH_PAGE);
  } else {
      int x = (int)pageFormat.getImageableX() + 1;
      int y = (int)pageFormat.getImageableY() + 1;
      g.translate(x,y);
      RepaintManager currentManager = RepaintManager.currentManager(this);
      currentManager.setDoubleBufferingEnabled(false);
      this.paint(g);
      currentManager.setDoubleBufferingEnabled(true);
      return(PAGE_EXISTS);
    }
  }
}


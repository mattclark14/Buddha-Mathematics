import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FrameTest extends JPanel{
  
  private Player p1;
  
  public static void main(String[] args) throws InterruptedException{
    JFrame frame = new JFrame("Test");
    FrameTest ft = new FrameTest();
    
    frame.add(ft);
    frame.setSize(700,700);
    frame.setVisible(true);
    
    while(true) {
      ft.repaint();
      Thread.sleep(5);
    }
  }
  
  public FrameTest() {
    KeyListener listen = new KeyListener() {
      @Override
      public void keyReleased(KeyEvent e) {}
      @Override
      public void keyTyped(KeyEvent e) {}
      @Override
      public void keyPressed(KeyEvent e) {
        p1.keyPressed(e);
      }
    };
    
    addKeyListener(listen);
    setFocusable(true);
    
    p1 = new Player(250,250);
  }
  
  public void paint(Graphics g) {
    super.paint(g);
    
    p1.paint(g);
  }
}

import javax.swing.*;
import javax.swing.Timer;
import java.util.*;
import java.awt.* ; 
import java.awt.event.*; 
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouser implements ActionListener, MouseListener, MouseMotionListener
{
  private boolean start, enteredStart, enteredInst = false;
  
  public Mouser(boolean start)
  {
    this.start = start;
  }
  
  public boolean getStart()
  {
    return start;
  }
  
  public void actionPerformed(ActionEvent a)
  {
  
  }
  
  public void mouseClicked(MouseEvent m) 
  {
    System.out.println(m.getX() + " " + m.getY());
    if((m.getX() >= 275 && m.getX() <= 425) && (m.getY() >= 120 && m.getY() <= 170))
    {
      start = true;
    }
    System.out.println(start);
  }

  public void mouseExited(MouseEvent m) 
  {

  }

  public void mouseEntered(MouseEvent m) 
  {

  }
  
  public void mousePressed(MouseEvent m) 
  {
    
  }

  public void mouseReleased(MouseEvent m) 
  {
    
    
  }
  
  public void mouseMoved(MouseEvent m) 
  {
    if((m.getX() >= 275 && m.getX() <= 425) && (m.getY() >= 120 && m.getY() <= 170))
    {
      enteredStart = true;      
    }
    else if((m.getX() >= 275 && m.getX() <= 545) && (m.getY() >= 200 && m.getY() <= 250))
    {
      enteredInst = true;
    }
    else
    {
      enteredStart = false;
      enteredInst = false;
    }
  }

  public void mouseDragged(MouseEvent m) 
  {
       
  }
  
  public void paint(Graphics g)
  {
    Graphics2D g2d = (Graphics2D) g;

    if(enteredStart == true)
    {
      g.setColor(Color.YELLOW);
      g.fillRect(275, 120, 150, 5);
      g.fillRect(275, 165, 150, 5);
      g.fillRect(275, 120, 5, 50);
      g.fillRect(420, 120, 5, 50);
    }
    if(enteredInst == true)
    {
      g.setColor(Color.YELLOW);
      g.fillRect(275, 200, 270, 5);
      g.fillRect(275, 245, 270, 5);
      g.fillRect(275, 200, 5, 50);
      g.fillRect(540, 200, 5, 50);    
    }
  }
}

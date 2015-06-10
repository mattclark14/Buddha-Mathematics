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
  private boolean start, enteredStart, enteredInst, enteredStart2, instructions = false;
  private boolean canStart = true;
  
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
    if((m.getX() >= 275 && m.getX() <= 425) && (m.getY() >= 120 && m.getY() <= 170) && canStart == true)
    {
      start = true;
      canStart = false;
    }
    if((m.getX() >= 275 && m.getX() <= 545) && (m.getY() >= 200 && m.getY() <= 250))
    {
      instructions = true;
      canStart = false;
    }
    else if(((m.getX() >= 275 && m.getX() <= 425) && (m.getY() >= 540 && m.getY() <= 590)) && instructions == true)
    {
      start = true;
    }
    System.out.println(instructions);
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
    else if((m.getX() >= 230 && m.getX() <= 500) && (m.getY() >= 200 && m.getY() <= 250))
    {
      enteredInst = true;
    }
    else if(((m.getX() >= 275 && m.getX() <= 425) && (m.getY() >= 540 && m.getY() <= 590)) && instructions == true)
    {
      enteredStart2 = true;
    }
    else
    {
      enteredStart = false;
      enteredInst = false;
      enteredStart2 = false;
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
      g.fillRect(230, 200, 270, 5);
      g.fillRect(230, 245, 270, 5);
      g.fillRect(230, 200, 5, 50);
      g.fillRect(495, 200, 5, 50);    
    }
    if(instructions == true)
    {
      g.setColor(Color.darkGray);
      g.fillRect(0, 0, 700, 700);
      g.setColor(Color.yellow);
      g.setFont(new Font("TimesRoman", Font.BOLD, 40)); 
      g.drawString("shoot the other person to get points and", 0, 50);
      g.drawString("win", 0, 100);
      g2d.setColor(Color.BLACK);
      g2d.fillRect(275, 540, 150, 50);
      g.setColor(Color.RED);
      g.drawString("Start", 305, 575);
    }
    if(enteredStart2 == true)
    {
      g.setColor(Color.YELLOW);
      g.fillRect(275, 540, 150, 5);
      g.fillRect(275, 585, 150, 5);
      g.fillRect(275, 540, 5, 50);
      g.fillRect(420, 540, 5, 50); 
    }
  }
}

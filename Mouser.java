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

/*
 * This class defines the mouse listener that is used in the main menu. It is 
 * a child of the map class.
*/

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
  
  // determines if the mouse is clicked on a button
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
  
  // determines if the mouse cursor has moved within the area of a button
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
      g.fillRect(0, 0, 900, 900);
      g.setColor(Color.yellow);
      g.setFont(new Font("TimesRoman", Font.BOLD, 40)); 
      g.drawString("Shoot the other person to get points and win. Player", 0, 50);
      g.drawString("1 uses 'wasd' keys to move and J to shoot. Player 2", 0, 100);
      g.drawString("uses the arrow keys to move and 0 on the num pad", 0, 150);
      g.drawString("to shoot. Collect weapon spawns to get a new weapon.", 0, 200);
      g.drawString("When your health goes to zero or below, the other", 0, 250);
      g.drawString("player scores a point.", 0, 300);
      g.drawString("Have fun!", 300, 400);
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

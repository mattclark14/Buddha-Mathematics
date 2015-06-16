import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

/* 
 * This class maps the buttons that the players use to control their characters
 * and allows the compiler to process multiple inputs at once. It is a child of
 * the map class.
*/

public class Keyer implements KeyListener, ActionListener
{
  private Map m;
  public HashSet<Integer> pressedKeys = new HashSet<Integer>();
  
  public Keyer(Map m)
  {
    this.m = m;
  }

  @Override
  public void keyPressed(KeyEvent e)
  {
    //Add key to hashSet when pressed
    int keyCode = e.getKeyCode();
    pressedKeys.add(keyCode);
    if(pressedKeys.contains(96))
    {
      m.p1.shoot();
    }
    if(pressedKeys.contains(74))
      m.p2.shoot();
    
  }
  @Override
  public void keyReleased(KeyEvent e)
  {
    //Remove key from hashset when released
    int keyCode = e.getKeyCode();
    pressedKeys.remove(keyCode);
  }
  @Override
  public void keyTyped(KeyEvent e){}
  
  //performs the actions when keys are pressed
  public void actionPerformed(ActionEvent a)
  {
    if(pressedKeys.contains(37))
      m.p1.turn(-1);
    if(pressedKeys.contains(39))
      m.p1.turn(1);
    if(pressedKeys.contains(38))
      m.p1.move(1);
    if(pressedKeys.contains(65))
      m.p2.turn(-1);
    if(pressedKeys.contains(68))
      m.p2.turn(1);
    if(pressedKeys.contains(87))
      m.p2.move(1);
    
    m.p1.gun.move();
    m.p2.gun.move();
  }
}
  

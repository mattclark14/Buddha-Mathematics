import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class Keyer implements KeyListener, ActionListener
{
  private Map m;
  //private JLabel output = new JLabel();
  HashSet<Integer> pressedKeys = new HashSet<Integer>();
  
  public Keyer(Map m)
  {
    this.m = m;
    //this.output = output;
    new Timer(100, new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent a)
      {
        String keysString = "";
        if(!pressedKeys.isEmpty())
        {
          Iterator<Integer> i = pressedKeys.iterator();
          while(i.hasNext())
          {
            keysString += i.next() + ",";
          }
        }
        //output.setText(keysString);
      }
    }).start();
  }

  @Override
  public void keyPressed(KeyEvent e)
  {
    //Add key to hashSet when pressed
    int keyCode = e.getKeyCode();
    pressedKeys.add(keyCode);
    System.out.println(pressedKeys);
    //m.p1.keyPressed(e, pressedKeys);
    //m.p2.keyPressed(e, pressedKeys);
    
  }
  @Override
  public void keyReleased(KeyEvent e)
  {
    //Remove key from hashset when released
    int keyCode = e.getKeyCode();
    pressedKeys.remove(keyCode);
    //m.p1.keyReleased(e);
    //m.p2.keyReleased(e);
  }
  @Override
  public void keyTyped(KeyEvent e){}
  
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
    
  }
}
  

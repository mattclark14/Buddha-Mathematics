import javax.swing.*;
import java.awt.* ; 
import java.awt.event.*; 
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;

/*
 * This class defines the projectiles as they are created and determines how and where
 * they should move, and when they should be deleted. It is a child of the weapon class
*/

public class Projectile implements ActionListener
{
  private Map m;
  private int damage;
  public int x;
  public int y;
  private boolean first;
  private int angle, type;
  private Weapon gun;
  private int map[][];
  
  public Projectile(Weapon gun, int[][] map, boolean first, int type) 
  {
    this.gun = gun;
    this.map = map;
    this.first = first;
    this.type = type;
    
    x = gun.getX()+7;
    y = gun.getY()+7;
    angle = gun.getAngle();
    if(type == 3)
    {
      angle = gun.getAngle() + (int)(Math.random() * (12 - (-10)) -10);
    }
    damage = gun.getDamage();
  }
  
  public int getAngle()
  {
    return gun.getAngle();
  }
 
  // determines how the projectile moves
  public void move()
  {
    if((x < 710 && x > -10) && (y < 710 && y > -10))
    {
      if(type == 1 || type == 2 || type == 4)
      {
        x += 8 * Math.cos(Math.toRadians(angle));
        y += 8 * Math.sin(Math.toRadians(angle));
      }
      else if(type == 3)
      {
        x += (Math.random() * (8 - 6) + 6) * Math.cos(Math.toRadians(angle));
        y += (Math.random() * (8 - 6) + 6) * Math.sin(Math.toRadians(angle));
      }
      else if(type == 5)
      {
        x += 16 * Math.cos(Math.toRadians(angle));
        y += 16 * Math.sin(Math.toRadians(angle));
      }
    }
    if(map[y / 28][x / 28] == 1)
    {
      x = -20;
      y = -20;
    }
  }
  
  //gets the other players x cooridnate
  public double getOtherX()
  {
    if(first == true)
    {
      return m.p2.x;
    }
    else
    {
      return m.p1.x;
    }
  }
  
  public void actionPerformed(ActionEvent a)
  {
    x = gun.getX();
    y = gun.getY();
  }
  
  // get other players y coordinate
  public double getOtherY()
  {
    if(first == true)
    {
      return m.p2.y;
    }
    else
    {
      return m.p1.y;
    }
  }
  
  public void paint(Graphics2D g)
  {
    g.setColor(Color.yellow);
    if(type == 4 || type == 7)
    {
      g.fillOval(x, y, 20, 20);    
    }
    else
    {
    g.fillOval(x, y, 6, 6);
    }
  }
}


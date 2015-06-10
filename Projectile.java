import javax.swing.*;
import java.awt.* ; 
import java.awt.event.*; 
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;

public class Projectile implements ActionListener
{
  private Map m;
  private int damage, decrease;
  private int sixsize = 40;
  private int swordSize = 36;
  private int swing = 1;
  public int x;
  public int y;
  private boolean first;
  private int angle, type;
  private Weapon gun;
  private int map[][];
  Timer shrink;
  
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
    shrink = new Timer(100, this);
  }
  
  public int getAngle()
  {
    return gun.getAngle();
  }
  
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
      else if(type == 6)
      {
        x += 2 * Math.cos(Math.toRadians(angle));
        y += 2 * Math.sin(Math.toRadians(angle));
      }
      else if(type == 7)
      {
        x += (Math.random() * (20 + 10) - 10) * Math.cos(Math.toRadians(angle));
        y += (Math.random() * (20 + 10) - 10) * Math.sin(Math.toRadians(angle));
      }
      else if(type ==8)
      {
        //x = gun.getX();
        //y = gun.getY();
      }
    }
    if(map[y / 28][x / 28] == 1)
    {
      x = -20;
      y = -20;
    }
  }
  
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
    swing = swing + 36;
    sixsize = sixsize - 4;
    if(sixsize <= 0)
    {
      shrink.stop();
    }
    else if(swing >= 5)
    {
      shrink.stop();
    }
  }
  
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
    else if(type == 6)
    {
      g.fillOval(x, y, sixsize, sixsize);
      shrink.start();
    }
    else if(type == 8)
    {
      g.fillArc(x - 15, y - 15, 50, 50, (360 - getAngle()) + ((swing -70) * -1), swordSize);
      if(swing >=180)
      {
        swordSize = 0;
        x = -5;
        y = -5;
        swing = 1;
      }
      shrink.start();
    }
    else
    {
    g.fillOval(x, y, 6, 6);
    }
  }
}

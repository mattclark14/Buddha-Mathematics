import javax.swing.*;
import javax.swing.Timer;
import java.util.*;
import java.awt.* ; 
import java.awt.event.*; 
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;

public class Player implements ActionListener
{
  private int health, angle, spawnCount, spawnCorner, deaths;
  public double x, y;
  private double speed;
  private int[][] map;
  private boolean first = true;
  private boolean canHit = true;
  Weapon gun;
  Map m;
  Timer iF;
  
  public Player(int x, int y, int[][] map, boolean first, int spawnCount) 
  {
    this.x = x;
    this.y = y;
    this.map = map;
    this.spawnCount = spawnCount;
    speed = 1;
    gun = new Weapon(this, 5, map, first);
    angle = 0;
    health = 10;
    this.first = first;
    iF = new Timer(50, this);
  }
  
  public int getDeaths()
  {
    return deaths;
  }
  
  public int getHealth()
  {
    return health;
  }
  
  public int getX()
  {
    return (int)x;
  }
  
  public int getY()
  {
    return (int)y;
  }

  public int getAngle()
  {
    return angle;
  }
  
  public void shoot()
  {
    gun.fire();
  }
  
  public void move(int vel) 
  { 
    y += (Math.sin(Math.toRadians(angle)));
    x += (Math.cos(Math.toRadians(angle)));
    if(map[(int)(y / 28)][(int)(x / 28)] == 1 || map[(int)(y + 26) / 28][(int)(x + 26) / 28] == 1)
    {
      y -= Math.sin(Math.toRadians(angle));
      x -= Math.cos(Math.toRadians(angle));
    }
    if(map[(int)(y / 28)][(int)(x / 28)] == 4)
    {
      map[(int)y / 28][(int)x / 28] = 0;
      gun = new Weapon(this, (int)((Math.random() * (6 - 1)) + 1), map, first);
      spawnCount--;
    }
    if(map[(int)(y + 26) / 28][(int)(x + 26) / 28] == 4)
    {
      map[(int)(y + 26) / 28][(int)(x + 26) / 28] = 0;
      gun = new Weapon(this, (int)((Math.random() * (9 - 7)) + 7), map, first);
      spawnCount--;
    }
  }
  
  public void hit(int damage)
  {
    if(canHit == true)
    {
      health = health - damage;
      canHit = false;
      iF.start();
    }
    if(health <= 0)
    {
      deaths++;
      spawnCorner = (int)((Math.random() * (5 - 1)) + 1);
      if(spawnCorner == 1)
      {
        x = 100;
        y = 100;
      }
      else if(spawnCorner == 2)
      {
        x = 600;
        y = 100;
      }
      else if(spawnCorner == 3)
      {
        x = 100;
        y = 600;
      }
      else if(spawnCorner == 4)
      {
        x = 600;
        y = 600;
      }
      health = 10;
    }
  }
  
  public void actionPerformed(ActionEvent a)
  {
    canHit = true;
    iF.stop();
  }
  
  public boolean changeHealth(boolean down) {
    health += down ? -1 : 1;
    if(health <= 0)
      return true;
    else
      return false;
  }
  
  public void changeWeapon(int type) 
  {
    gun = new Weapon(this, type, map, first);
  }
  
  public void turn(int d)
  {
      angle += d == 1 ? 4 : -4;   
  }
  
  public void paint(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.darkGray);
    
    g2d.rotate(Math.toRadians(angle), x + 10, y + 10);
    g2d.fillOval((int)x,(int)y,20,20);
    g2d.fillRect((int)x+5,(int)y+5,20,10);    
    g2d.rotate(Math.toRadians(-angle), x + 10, y + 10);
    if(first == true)
    {
      g2d.setColor(Color.RED);
      g2d.fillArc((int)x,(int)y,20,20,0,36*health);
    }
    else
    {
      g2d.setColor(Color.BLUE);
      g2d.fillArc((int)x,(int)y,20,20,0,36*health);
    }
    gun.paint(g2d);
  }
}


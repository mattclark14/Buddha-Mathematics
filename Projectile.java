import javax.swing.*;
import java.awt.* ; 
import java.awt.event.*; 
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;

public class Projectile
{
  private int damage;
  private int x;
  private int xv;
  private int y;
  private int yv;
  private Weapon weapon;
  
  public Projectile(int damage, int x, int xv, int y, int yv)
  {
    this.damage = damage;
    this.x = x;
    this.xv = xv;
    this.y = y;
    this.yv = yv;
    this.weapon = weapon;
  }
  
  public void collision()
  {
  
  }
  
  public int getAngle()
  {
    return weapon.getAngle();
  }
  
  public void move()
  {
    xv = (int)(2 * Math.sin(Math.toRadians(getAngle())));
    yv = (int)(2 * Math.cos(Math.toRadians(getAngle())));
    
    x = x + xv;
    y = y + yv;
  }
  
  public void paint(Graphics2D g)
  {
    g.fillOval(x, y, 20, 20);
  }
}

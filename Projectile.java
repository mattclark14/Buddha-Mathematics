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
  private int y;
  private int angle;
  private Weapon weapon;
  
  public Projectile(int damage, int x, int y, int angle)
  {
    this.damage = damage;
    this.x = x;
    this.y = y;
    this.weapon = weapon;
    this.angle = angle;
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
    x += 2 * (int) Math.sin(Math.toRadians(angle));
    y += 2 * (int) Math.cos(Math.toRadians(angle));
  }
  
  public void paint(Graphics2D g)
  {
    g.setColor(Color.yellow);
    g.fillRect(x, y, 20, 40);
  }
}

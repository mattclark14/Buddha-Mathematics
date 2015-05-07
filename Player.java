import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class Player {
  private int health, angle;
  private double x, y;
  private double speed;
  private Weapon gun;
  private int[][] map;
  private boolean first = true;
  
  public Player(int x, int y, int[][] map, boolean first) {
    this.x = x;
    this.y = y;
    speed = 1;
    gun = new Weapon(this, 1);
    angle = 0;
    health = 10;
    this.map = map;
    this.first = first;
  }
  

  public int getAngle(){
    return angle;
  }
  
  public void shoot() {
    gun.fire();
  }
  
  public void move(int vel) 
  { 
    y += Math.sin(Math.toRadians(angle));
    x += Math.cos(Math.toRadians(angle));
    if(map[(int)(y / 28)][(int)(x / 28)] == 1 || map[(int)(y + 26) / 28][(int)(x + 26) / 28] == 1)
    {
      y -= vel * Math.sin(Math.toRadians(angle));
      x -= vel * Math.cos(Math.toRadians(angle));
    }
  }
  
  public boolean changeHealth(boolean down) {
    health += down ? -1 : 1;
    if(health <= 0)
      return true;
    else
      return false;
  }
  
  public void changeWeapon() 
  {
    
  }
  
  public void turn(int d)  //, HashSet pressedKeys) 
  {
    //if(pressedKeys.contains(37) || pressedKeys.contains(39))
      angle += d == 1 ? 3 : -3;   
  }
  
  public void paint(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.darkGray);
    
    g2d.rotate(Math.toRadians(angle), x + 10, y + 10);
    g2d.fillOval((int)x,(int)y,20,20);
    g2d.fillRect((int)x+5,(int)y+5,20,10);    
    g2d.rotate(Math.toRadians(-angle), x + 10, y + 10);
    g2d.setColor(Color.green);
    g2d.fillArc((int)x,(int)y,20,20,0,36*health);
  }
}


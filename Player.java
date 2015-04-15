import java.awt.*;
import java.awt.event.*;

public class Player {
  private int x, y, health;
  private double angle, speed;
  private Weapon gun;
  
  public Player(int x, int y) {
    this.x = x;
    this.y = y;
    speed = 1;
    gun = new Weapon();
    angle = 0.0;
  }
  
  public void keyPressed(KeyEvent e) {
    if(e.getKeyCode() == KeyEvent.VK_RIGHT)
      turn();
    if(e.getKeyCode() == KeyEvent.VK_UP)
      move();
  }
  
  public void shoot() {
    
  }
  
  public void move() {
    y += 4 * Math.sin(Math.toRadians(angle));
    x += 4 * Math.cos(Math.toRadians(angle));
  }
  
  public void changeHealth() {
    
  }
  
  public void changeWeapon() {
    
  }
  
  public void turn() {
    angle += 10;
  }
  
  public void paint(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.black);
    
    g2d.rotate(Math.toRadians(angle), x + 10, y + 10);
    g2d.fillOval(x,y,20,20);
    g2d.fillRect(x+5,y+5,20,10);
    
    g2d.rotate(Math.toRadians(-angle), x + 10, y + 10);
  }
}

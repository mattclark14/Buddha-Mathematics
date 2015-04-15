import java.awt.*;
import java.awt.event.*;

public class Player {
  private int x, y, health, angle;
  private double speed;
  private Weapon gun;
  
  public Player(int x, int y) {
    this.x = x;
    this.y = y;
    speed = 1;
    gun = new Weapon(this);
    angle = 0;
    health = 10;
  }
  
  public void keyPressed(KeyEvent e) {
    if(e.getKeyCode() == KeyEvent.VK_RIGHT)
      turn(1);
    if(e.getKeyCode() == KeyEvent.VK_LEFT)
      turn(-1);
    if(e.getKeyCode() == KeyEvent.VK_UP)
      move();
    if(e.getKeyCode() == KeyEvent.VK_E)
      shoot();
  }
  
  public int getAngle(){
    return angle;
  }
  
  public void shoot() {
    gun.fire();
  }
  
  public void move() {
    y += 4 * Math.sin(Math.toRadians(angle));
    x += 4 * Math.cos(Math.toRadians(angle));
  }
  
  public boolean changeHealth(boolean down) {
    health += down ? -1 : 1;
    if(health <= 0)
      return true;
    else
      return false;
  }
  
  public void changeWeapon() {
    
  }
  
  public void turn(int d) {
    angle += d == 1 ? 15 : -15;
  }
  
  public void paint(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.darkGray);
    
    g2d.rotate(Math.toRadians(angle), x + 10, y + 10);
    g2d.fillOval(x,y,20,20);
    g2d.fillRect(x+5,y+5,20,10);    
    g2d.rotate(Math.toRadians(-angle), x + 10, y + 10);
    g2d.setColor(Color.green);
    g2d.fillArc(x,y,20,20,0,36*health);
  }
}

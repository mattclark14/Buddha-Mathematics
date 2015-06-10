import javax.swing.*;
import javax.swing.Timer;
import java.util.*;
import java.awt.* ; 
import java.awt.event.*; 
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;

public class Weapon implements ActionListener
{
  public int ammo, reload, damage, shots, type, shotMax, x, y;
  private int fireDelay;
  public boolean canFire = true;
  private boolean first;
  private Player p;
  private int map[][];
  Timer reloadRate;
  Timer fireRate;
  
  ArrayList<Projectile> list = new ArrayList<Projectile>();
  ArrayList<Projectile> toRemove = new ArrayList<Projectile>();
  Iterator<Projectile> i = list.iterator();
    
  public Weapon(Player p, int type, int[][] map, boolean first)
  {
    this.p = p;
    this.map = map;
    this.first = first;
    this.type = type;
    
    if(type == 1) // pistol
    {
      damage = 2;
      reload = 1000;
      ammo = -1;
      shots = 8;
      shotMax = 8;
      fireDelay = 1000;
    }
    else if(type == 2) // light machine gun
    {
      damage = 1;
      reload = 4000;
      ammo = -1;
      shots = 150;
      shotMax = 150;
      fireDelay = 1000;
    }
    else if(type == 3) // shotgun
    {
      damage = 4;
      reload = 2000;
      ammo = -1;
      shots = 2;
      shotMax = 2;
      fireDelay = 2000;
    }
    else if(type == 4) // rocket
    {
      damage = 5;
      reload = 5000;
      ammo = -1;
      shots = 2;
      shotMax = 2;
      fireDelay = 3000;       
    }
    else if(type == 5) // sniper
    {
      damage = 10;
      reload = 2000;
      ammo = -1;
      shots = 1;
      shotMax = 1;
      fireDelay = 2000;
    }
    else if(type == 6) // bubble gun
    {
      damage = 10;
      reload = 500;
      ammo = -1;
      shots = 1;
      shotMax = 1;
      fireDelay = 2000;
    }
    else if(type == 7)// cloud gun
    {
      damage = 20;
      reload = 500;
      ammo = -1;
      shots = 20;
      shotMax = 20;
      fireDelay = 2000;
    }
    else // sword
    {
      damage = 4;
      reload = 500;
      ammo = -1;
      shots = 1;
      shotMax = 1;
      fireDelay = 2000;
    }
    reloadRate = new Timer(reload, this);
    fireRate = new Timer(fireDelay, new ActionListener()
    {
      public void actionPerformed(ActionEvent a)
      {

      }
    });
    System.out.println(type);    
  }
  
  public int getX()
  {
    return p.getX();
  }
  
  public int getY()
  {
    return p.getY();
  }
  
  public int getDamage(){
    return damage;
  }

  public int getAngle()
  {
    return p.getAngle(); 
  }
  
  public void fire()
  {
    if(canFire == true)
    {
      shots--;
      list.add(new Projectile(this, map, first, type));
      if(type == 3)
      {
        for(int i = 0; i < 4; i++)
        {
          list.add(new Projectile(this, map, first, type));
        }
      }
      move();
      if(shots <= 0)
      {
        canFire = false;
        reload();
      }
    }
    fireRate.start();
    fireRate.stop();
  }
  
  public void reload()
  {
    System.out.println("reloading");
    reloadRate.start();
  }
  
  
  public void actionPerformed(ActionEvent a)
  {
    shots = shotMax;
    canFire = true;
    reloadRate.stop();
  }  
  
  public void move() 
  {
    for(Projectile p:list)
    {
      p.move();
      if(p.x > 700 || p.x < 0 || p.y > 700 || p.y < 0)
      {
        //i.remove();
      }
      x = p.x;
      y = p.y;
      //if(map[p.y / 28][p.x / 28] == 1)
      //{
      //  toRemove.add(p);
      //  i.remove();
      //}
    }
  }
  
  public int getPX() // projectiles x
  {
    return x;
  }
  
  public int getPY() // projectiles y
  {
    return y;
  }
  
  public void paint(Graphics2D g) 
  {
    Graphics g2d = (Graphics2D) g;
    for(Projectile p:list)
      p.paint(g);
    
    g.setColor(Color.YELLOW);
    
    if(canFire == false)
    {
      g.fillArc(getX(), getY(), 20, 20, 40, 360);
    }
  }
}

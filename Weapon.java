import java.util.*;

public class Weapon
{
  private int fireRate, ammo, reload, damage;
  private boolean canFire = true;
  private Player p;
  
  List<Projectile> mylist = new ArrayList<Projectile>();
    
  public Weapon(Player p, int type)
  {
    this.p = p;
    
    damage = 2;
    fireRate = 200;
    reload = 1000;
    ammo = -1;
  }
  
  public void fire()
  {
    
  }
  
  public void reload()
  {
    
  }
  public int getAngle()
  {
    return p.getAngle(); 
  }
}

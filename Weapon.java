public class Weapon
{
  private int fireRate, ammo, ammoLeft, reload;
  private boolean canFire = true;
  Projectile[] b = new Projectile[ammo];
  Player p = new Player();
    
  public Weapon(Player p, Projectile[] b, int fireRate, int ammo, int reload)
  {
    this.p = p;
    this.b = b;
    this.fireRate = fireRate;
    this.ammo = ammo;
  }
  
  public void fire()
  {
    b[ammo - ammoLeft].move();
    ammoLeft--;
  }
  
  public void reload()
  {
    canFire = false;
    ammoLeft = ammo;
    canFire = true;
  }
  public int getAngle()
  {
    return p.getAngle(); 
  }
  
}

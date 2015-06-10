import javax.swing.*;
import javax.swing.Timer;
import java.util.*;
import java.awt.* ; 
import java.awt.event.*; 
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Map extends JPanel implements ActionListener
{
  JLabel output = new JLabel();
  private Keyer keyer = new Keyer(this);
  Queue myQueue = new Queue();
  Timer spawn = new Timer(2000, this);
  private BufferedImage grass, wall, weapon, water, bridge;
  private boolean start = false;
  private Mouser mouser = new Mouser(start);
  private int xSpawn;
  private int ySpawn;
  private int p1Score, p2Score;
  private int mapType = 1;
  public int spawnCount;
  private String background = "C:/Users/Hunter/Desktop/cstp/bound2.mp3";
  private int type;
  
  // view of the map
  int[][] map = new int[][] {
    { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
    { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
    { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
    { 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
    { 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
    { 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1 },
    { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
    { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
    { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
    { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
    { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
    { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
    { 1, 0, 0, 3, 3, 2, 2, 3, 3, 0, 0, 0, 0, 0, 0, 0, 3, 3, 2, 2, 3, 3, 0, 0, 1 },
    { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
    { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
    { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
    { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
    { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
    { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
    { 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1 },
    { 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
    { 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
    { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
    { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
    { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
  };
  boolean obstacle[][] = new boolean[25][25];
  
  Player p1 = new Player(100, 100, map, true, spawnCount);
  Player p2 = new Player(600, 600, map, false, spawnCount);
  
  public Map() 
  { 
    Timer timer = new Timer(1000/120, keyer);
    timer.start();
    spawn.start();
    addKeyListener(keyer);
    addMouseListener(mouser);
    addMouseMotionListener(mouser);
    add(output, BorderLayout.CENTER);
    setFocusable(true);
    setImages();
  }
  
  public void actionPerformed(ActionEvent a)
  {
    p1Score = p2.getDeaths();
    p2Score = p1.getDeaths();
    if(start == true)
    {
      xSpawn = (int)(Math.random() * 25);
      ySpawn = (int)(Math.random() * 25);
      
      while(map[xSpawn][ySpawn] != 0)
      {
        xSpawn = (int)(Math.random() * 25);
        ySpawn = (int)(Math.random() * 25);
      }
      
      map[xSpawn][ySpawn] = 4;
      spawnCount++;
      myQueue.insertNext(xSpawn, ySpawn);
      System.out.println(myQueue.rear.info);
      System.out.println(myQueue.rear.info2);
    }
    
    if(spawnCount >= 6)
    {
      myQueue.delete();
      map[myQueue.front.info][myQueue.front.info2] = 0;
      spawnCount--;
    }
  }
  
  public static void main(String []args) throws InterruptedException
  {
    //Creates the window
    JFrame frame = new JFrame("Map");
    Map m = new Map();
    
    frame.add(m);
    
    //Window settings
    frame.setSize(870, 725);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    frame.setResizable(false);
    
    while(true) 
    {
      m.repaint();
      m.bulletCollision();
      Thread.sleep(5);
    }
  }
  
  public void bulletCollision()
  {
    if(start == true)
    {
      if((p1.gun.x + 6 >= p2.x && p1.gun.x <= (p2.x + 28)) && (p1.gun.type < 4 || p1.gun.type == 5))
      {
        if(p1.gun.y + 6 >= p2.y && p1.gun.y <= (p2.y + 28))
        {
          p2.hit(p1.gun.getDamage());
        }
      }
      else if((p1.gun.type == 4 || p1.gun.type == 7) && p1.gun.x + 18 >= p2.x && p1.gun.x <= (p2.x + 28))
      {
        if(p1.gun.y + 18 >= p2.y && p1.gun.y <= (p2.y + 28))
        {
          p2.hit(p1.gun.getDamage());
        }
      }
      else if(p1.gun.type == 8 && p1.gun.getAngle() + 30 >=  p2.x && p1.gun.getAngle() <= (p2.x + 58))
      {
        if(p1.gun.getAngle() + 30 >=  p2.y && p1.gun.getAngle() <= (p2.y + 58))
        {
          p2.hit(p1.gun.getDamage());
        }
      }
      if((p2.gun.x + 6 >= p1.x && p2.gun.x <= p1.x + 28) && (p2.gun.type < 4 || p2.gun.type == 5))
      {
        if((p2.gun.y + 6 >= p1.y && p2.gun.y <= p1.y + 28))
        {
          p1.hit(p2.gun.getDamage());
        }
      }
      else if((p2.gun.type == 4 || p2.gun.type == 7) && p2.gun.x + 18 >= p1.x && p2.gun.x <= (p1.x + 28))
      {
        if(p2.gun.y + 18 >= p1.y && p2.gun.y <= (p1.y + 28))
        {
          p1.hit(p2.gun.getDamage());
        }
      }
    }
  }
  
  public void setImages()
  {
    try
    {
      grass = ImageIO.read(new File("U:/Profile/Desktop/ISU/grass.jpg"));
    }catch(IOException e) {
    }
    try
    {
      wall = ImageIO.read(new File("U:/Profile/Desktop/ISU/wall.jpg"));
    }catch(IOException e) {
    }
    try
    {
      weapon = ImageIO.read(new File("U:/Profile/Desktop/ISU/weapon.jpg"));
    }catch(IOException e) {
    }
    try
    {
      water = ImageIO.read(new File("U:/Profile/Desktop/ISU/water.jpg"));
    }catch(IOException e) {
    }
    try
    {
      bridge = ImageIO.read(new File("U:/Profile/Desktop/ISU/bridge.jpg"));
    }catch(IOException e) {
    }
  }
  
  @Override
  public void paint(Graphics g)
  {
    start = mouser.getStart();
    //draws map
    super.paint(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    if(start == false)
    {
      g2d.setColor(Color.darkGray);
      g2d.fillRect(700, 0, 150, 800);
      g2d.fillRect(0, 0, 700, 700);
      g2d.setColor(Color.BLACK);
      g2d.fillRect(275, 120, 150, 50);
      g2d.fillRect(230, 200, 270, 50);
      g2d.setColor(Color.RED);
      g2d.setFont(new Font("TimesRoman", Font.PLAIN, 40)); 
      g2d.drawString("COMPUTER SCIENCE GAME", 90, 40);
      g2d.drawString("the game", 280, 80);
      g2d.setFont(new Font("TimesRoman", Font.BOLD, 40)); 
      g2d.drawString("Start", 305, 155);
      g2d.drawString("Instructions", 265, 235);
      mouser.paint(g2d);
    }
    
    g2d.setColor(Color.darkGray);
    g2d.fillRect(700, 0, 170, 800);
    
    if(start == true)
    {
      for(int i = 0; i < 25; i++)
      {
        for(int j = 0; j < 25; j++)
        {
          Color paint;
          if(map[j][i] == 0)
          {       
            g2d.drawImage(grass, i* 28, j * 28, 28, 28, null);
          }        
          else if(map[j][i] == 1)
          {
            //g2d.setColor(Color.BLACK);
            g2d.drawImage(wall, i* 28, j * 28, 28, 28, null);
          }       
          else if(map[j][i] == 2)
          {
            //g2d.setColor(new Color(165,42,42));
            g2d.drawImage(bridge, i* 28, j * 28, 28, 28, null);
          }
          else if(map[j][i] == 3)
          {
            //g2d.setColor(Color.BLUE);
            g2d.drawImage(water, i* 28, j * 28, 28, 28, null);
          }
          else
          {
            //g2d.setColor(Color.YELLOW); //Obvious color to show errors
            g2d.drawImage(weapon, i* 28, j * 28, 28, 28, null);
          }
        }
      }
      g2d.setColor(Color.RED);
      g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
      g2d.drawString("P1 Score:", 710, 40);
      g2d.drawString(String.valueOf(p1Score), 720, 80);
      g2d.drawString("P1 Health:", 710, 120);
      g2d.drawString(String.valueOf(p1.getHealth()), 720, 160);
      g2d.drawString("P1 Weapon:", 710, 200);
      //g2d.drawString(String.valueOf(p1.getHealth()), 720, 240);
      g2d.setColor(Color.RED);
      g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
      g2d.drawString("P2 Score:", 710, 380);
      g2d.drawString(String.valueOf(p2Score), 720, 420);
      g2d.drawString("P2 Health:", 710, 460);
      g2d.drawString(String.valueOf(p2.getHealth()), 720, 500);
      g2d.drawString("P2 Weapon:", 710, 540);
      //g2d.drawString(String.valueOf(p2.getHealth()), 720, 480);
    
      p1.paint(g2d);
      p2.paint(g2d);
    }
  }
}

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
  Timer spawn = new Timer(10000, this);
  private BufferedImage grass, wall, weapon, water, bridge;
  private boolean start = false;
  private Mouser mouser = new Mouser(start);
  private int xSpawn;
  private int ySpawn;
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
    Timer timer = new Timer(1000/60, keyer);
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
      if(spawnCount > 5)
      {
        for(int i = 0; i < 25; i++)
        {
          for(int j = 0; j < 25; j++)
          {
            if(map[j][i] == 4)
            {
              //map[j][i] = 0;
              spawnCount--;
              break;
            }
          }
        }
      }
    }
  }
  
  public static void main(String []args) throws InterruptedException
  {
    //Creates the window
    JFrame frame = new JFrame("Map");
    Map m = new Map();
    
    frame.add(m);
    
    //Window settings
    frame.setSize(700, 725);
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
      if((p1.gun.x + 6 >= p2.x && p1.gun.x <= (p2.x + 28)))
      {
        if(p1.gun.y + 6 >= p2.y && p1.gun.y <= (p2.y + 28))
        {
          p2.hit(p1.gun.getDamage());
        }
      }
      if((p2.gun.x + 6 >= p1.x && p2.gun.x <= p1.x + 28) && (p2.gun.y + 6 >= p1.y && p2.gun.y <= p1.y + 28))
      {
        p1.hit(p2.gun.getDamage());
      }
    }
  }
  
  public void setImages()
  {
    try
    {
      grass = ImageIO.read(new File("C:/Users/Hunter/Desktop/cstp/grass.jpg"));
    }catch(IOException e) {
    }
    try
    {
      wall = ImageIO.read(new File("C:/Users/Hunter/Desktop/cstp/wall.jpg"));
    }catch(IOException e) {
    }
    try
    {
      weapon = ImageIO.read(new File("C:/Users/Hunter/Desktop/cstp/weapon.jpg"));
    }catch(IOException e) {
    }
    try
    {
      water = ImageIO.read(new File("C:/Users/Hunter/Desktop/cstp/water.jpg"));
    }catch(IOException e) {
    }
    try
    {
      bridge = ImageIO.read(new File("C:/Users/Hunter/Desktop/cstp/bridge.jpg"));
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
      g2d.fillRect(0, 0, 700, 700);
      g2d.setColor(Color.BLACK);
      g2d.fillRect(275, 120, 150, 50);
      g2d.fillRect(275, 200, 270, 50);
      g2d.setColor(Color.RED);
      g2d.setFont(new Font("TimesRoman", Font.PLAIN, 40)); 
      g2d.drawString("COMPUTER SCIENCE GAME", 90, 40);
      g2d.drawString("the game", 280, 80);
      g2d.setFont(new Font("TimesRoman", Font.BOLD, 40)); 
      g2d.drawString("Start", 305, 155);
      g2d.drawString("Instructions", 305, 235);
      mouser.paint(g2d);
    }
    
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
      p1.paint(g2d);
      p2.paint(g2d);
    }
  }
}

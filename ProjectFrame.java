import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.*;
import java.text.*;
import java.io.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.Timer;
public class ProjectFrame extends JFrame
{
   public ProjectFrame()
   {
      super("");
      Container contents = getContentPane();
      ProjectPanel s = new ProjectPanel("Project.txt");
      s.setBackground(Color.BLACK);
      contents.add(s);
      setSize(815,638); 
      setVisible(true);
   }
   public static void main(String[] args)
   {
      ProjectFrame theFrame = new ProjectFrame();
      theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }


   public class ProjectPanel extends JPanel 
   {
      //objects being made to be used throughout the class
      Scanner scan;
      Player playerOne;
      ArrayList<ArrayList<GameObject>> levelList = new ArrayList<ArrayList<GameObject>>();
   
      
      //Variables for the project Panel
      int rows;
      int columns;
      int xposition; 
      int yposition;
      int xPositionOV;
      int yPositionOV;
      boolean drawPlayer = true;
      
      //Constructer that sets up the Timer for smooth movement and scans in the file for the nested arraylist      
      public ProjectPanel(String file)
      {
         super ();
      //the block is offset by 12 for the boundaries of a 25 by 25 block
      
         try
         {
            scan = new Scanner(new File(file));
         }
         catch(FileNotFoundException fnfe)
         {
            System.out.println("File Not Found");
         }
         xposition = scan.nextInt();
         yposition = scan.nextInt();
         rows = scan.nextInt();
         columns = scan.nextInt();
         for(int i=0; i<rows ;i++)//creates the arraylist to hold the blocks for the level
         {
            ArrayList<GameObject> blockList = new ArrayList<GameObject>();
            levelList.add(blockList);
         }
         for(int k=0;k<rows;k++)//This loop adds two types of Gameobjects to the arrayList to the 
         {
            for(int j=0;j<columns;j++)
            {
               int choice=scan.nextInt();
               if (choice == 1)
               {
                  levelList.get(k).add(new Block(j*25+12, k*25+12, Color.PINK));
               }
               else if (choice == 2)
               {
                  levelList.get(k).add(new VictoryBlock(j*25+12, k*25+12, Color.GREEN));
               }
               else //if there isnt an 1 or 0 then sets that arraylist location as null
               {
                  levelList.get(k).add(null);
               }
            }
         }
         playerOne = new Player (xposition+12, yposition+12, Color.RED);
         addKeyListener(new KeyEventDemo());
         setPreferredSize(new Dimension(450,450));
         setFocusable(true);
         
         
         Timer timer1 = new Timer (10, new ButtonListener());
         Timer timer2 = new Timer(10, new TimeListener());
         timer1.start();
         timer2.start();
      }
      
      //This paint component draws the level and the player in the Frame
      public void paintComponent(Graphics g)
      {
         super.paintComponent(g);
         for(int k=0;k<rows;k++) //if there is a block in the arrayList then it will draw whatever object is there
         {
            for(int j=0;j<columns;j++)
            {
               if (levelList.get(k).get(j) instanceof Block)
               {
                  g.setColor(levelList.get(k).get(j).getColor());
                  levelList.get(k).get(j).draw(g);
               }
               else if (levelList.get(k).get(j) instanceof VictoryBlock)
               {
                  g.setColor(levelList.get(k).get(j).getColor());
                  levelList.get(k).get(j).draw(g);
               }
            }
            if (drawPlayer)
            {
               playerOne.draw(g);
            }
            else
            {
               g.setColor(playerOne.getColor());
               g.fillRect(xPositionOV-12,yPositionOV-12,25,25);
            
            }
         }
      }
    
      //This class has a connected time listener so wasd (and space) can have smooth movement    
      public class KeyEventDemo  implements KeyListener 
      {
         public void keyTyped(KeyEvent e) {}
         public void keyReleased(KeyEvent e) 
         {
            if (e.getKeyCode() == KeyEvent.VK_A)
            {
               left = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_D)
            {
               right = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_W)
            {
               up = false;
            }
         }
      
         public void keyPressed(KeyEvent e) 
         {
            //if space or W is pressed then it subtracts from its Y value then applies gravity which adds to the Y Value until ground is touched 
            if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_SPACE)
            {
               if(playerOne.jump(levelList))
               {
                  up = true;
                  jump = 7;
                  jumpCheck = true;
                  gravity = 0;
               }
            }
            if(e.getKeyCode() == KeyEvent.VK_A)
            {
               left = true;
            }
            if(e.getKeyCode() == KeyEvent.VK_D)
            {
               right = true;
            }
            repaint();
         }
      }
   
      
      int jump;
      int timedJump;
      int gravity;
      boolean right;
      boolean up;
      boolean left;
      boolean jumpCheck;
      public class ButtonListener implements ActionListener
      {
         public void actionPerformed (ActionEvent e)
         {
            
            //When an action is preformed it first checks if the user has collided with the win block
            if (playerOne.win(levelList))
            {
               if (true)
               {
                  drawPlayer=false;
                  xPositionOV=playerOne.getx();
                  yPositionOV=playerOne.gety();
                  int outcome = JOptionPane.showConfirmDialog(null,"WINNER","Confirm Quit", JOptionPane.DEFAULT_OPTION);
                  if (outcome == 0)
                     System.exit(0);
               }
            }
            if (left)
            {
               if (playerOne.winl(levelList,1,0))//if they collided with win block then it runs
               {
                  if (true)
                  {
                     drawPlayer=false;
                     xPositionOV=playerOne.getx();
                     yPositionOV=playerOne.gety();
                     int outcome = JOptionPane.showConfirmDialog(null,"WINNER","Confirm Quit", JOptionPane.DEFAULT_OPTION);
                     if (outcome == 0)
                        System.exit(0);
                  }
               
               }
               
               if (playerOne.collides(levelList))
               {
                  if(!playerOne.leftSide(levelList))
                  {
                     xposition--;
                     playerOne.setx(xposition);
                  }
               }
               else
               {
                  xposition--;
                  playerOne.setx(xposition);
               }
            }
            //repeates for every direction
            if (right)
            {
               if (playerOne.winl(levelList,-1,0))
               {
                  if (true)
                  {
                     drawPlayer=false;
                     xPositionOV=playerOne.getx();
                     yPositionOV=playerOne.gety();
                     int outcome = JOptionPane.showConfirmDialog(null,"WINNER","Confirm Quit", JOptionPane.DEFAULT_OPTION);
                     if (outcome == 0)
                        System.exit(0);
                  }
               
               }
            
               if (playerOne.collides(levelList))
               {
                  if(!playerOne.rightSide(levelList))
                  {
                     xposition++;
                     playerOne.setx(xposition);
                  }
               }
               else
               {
                  xposition++;
                  playerOne.setx(xposition);
               }
            }
            if (up)
            {
               if (playerOne.winl(levelList,0,1))
               {
                  if (true)
                  {
                     drawPlayer=false;
                     xPositionOV=playerOne.getx();
                     yPositionOV=playerOne.gety();
                     int outcome = JOptionPane.showConfirmDialog(null,"WINNER","Confirm Quit", JOptionPane.DEFAULT_OPTION);
                     if (outcome == 0)
                        System.exit(0);
                  }
               
               }
            }
         
            repaint();
         }
      }
   
      int counter=1;
      int counterTwo=1;
      //This class checks if player is on ground and if its able to jump
      public class TimeListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            counter++;
            if (counter%20==0)
            {
               if (counterTwo<7)
               {
                  counterTwo++;
               }
            }
            for (int i=0; i<counterTwo; i++)
            {
               playerOne.sety(playerOne.gety()+1);
               if (playerOne.isOnground(levelList))
               {
                  playerOne.sety(playerOne.gety()-1);
                  counter=1;
                  counterTwo=1;
               }
               repaint();
            }
          
            if(jumpCheck)
            {
               timedJump++;
               if(timedJump % 10 == 0)
               {
                  if(gravity<7)
                  {
                     gravity++;
                  }
               }
               for(int i=0; i<jump; i++)
               { 
                  playerOne.sety(playerOne.gety()-1);
                  if (playerOne.top(levelList))
                  {
                     playerOne.sety(playerOne.gety()+1);
                     jump=0;
                     break;
                  }
                  jump = 7-gravity;
               }
               if(jump ==0)
               {
                  jumpCheck = false;
               }
               up=false;
            }
         }
      }
   }
         

}
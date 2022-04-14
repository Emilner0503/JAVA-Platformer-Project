//This code is by Ethan Milner
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.*;
import java.text.*;
import java.io.*;
import java.awt.event.*;
import java.util.Random;
public class Player extends GameObject
{
   //Constructer to initialize Variables
   public Player (int x, int y, Color color1)
   {
      super(x, y, color1);
   }
   //method for drawing player   
   public void draw (Graphics g)
   {
      g.setColor(getColor());
      g.fillRect(getx()-12,gety()-12,25,25);
   }
   
   //Checks if Player collided with any blocks in the arrayList 
   public boolean collides (ArrayList<ArrayList<GameObject>> list)
   {
      for (int i=0; i<list.size(); i++)
      {
         for (int k=0; k<list.get(0).size(); k++)
         {
            if (list.get(i).get(k) instanceof GameObject)
            {
               if (Math.abs((this.gety())-list.get(i).get(k).gety())<25 && Math.abs((this.getx())-list.get(i).get(k).getx())<26)
               {
                  return true;
               }
            }
         }
      }
      return false;
   }
   //This method checks if the player is on the ground and returns a boolean if the bottom is touching a gameobject
   public boolean isOnground (ArrayList<ArrayList<GameObject>> list)
   {
      for (int i=0; i<list.size(); i++)
      {
         for (int k=0; k<list.get(0).size(); k++)
         {
            if (list.get(i).get(k) instanceof GameObject)
            {
               if (list.get(i).get(k).gety()-(this.gety()+1)<24 && Math.abs((this.getx())-list.get(i).get(k).getx())<25 && list.get(i).get(k).gety()-(this.gety()+1)>0)
               {
                  return true;
               }
            }
         }
      }
      return false;
   }
   
   //This method checks if the player is able to jump if it is then true is returned
   public boolean jump(ArrayList<ArrayList<GameObject>> list)
   {
      for (int i=0; i<list.size(); i++)
      {
         for (int k=0; k<list.get(0).size(); k++)
         {
            if (list.get(i).get(k) instanceof GameObject)
            {
               if ( list.get(i).get(k).gety()-(this.gety()+1)<26 && Math.abs((this.getx())-list.get(i).get(k).getx())<25 && list.get(i).get(k).gety()-(this.gety()+1)>-2)
               {
                  return true;
               }
            }
         }
      }
      return false;
   }
   //This method checks the playes top to see if it has hit the roof
   public boolean top (ArrayList<ArrayList<GameObject>> list)
   {
      for (int i=0; i<list.size(); i++)
      {
         for (int k=0; k<list.get(0).size(); k++)
         {
            if (list.get(i).get(k) instanceof GameObject)
            {
               if ((this.gety()-1)-list.get(i).get(k).gety()<24 && Math.abs((this.getx())-list.get(i).get(k).getx())<25 && (this.gety()-1)-list.get(i).get(k).gety()>0)
               {
                  return true;
               }
            }
         }
      }
      return false;
   }


   
   //This method takes in movement from main and checks if its able to move by boolean
   public boolean move (int x, int y, ArrayList<ArrayList<GameObject>> list )
   {
      for (int i=0; i<list.size(); i++)
      {
         for (int k=0; k<list.get(0).size(); k++)
         {
            if (Math.abs((this.gety()+y)-list.get(i).get(k).gety())==26 && Math.abs((this.getx()+x)-list.get(i).get(k).getx())<26 )
            {
               return false;
            }
         }
      }
      return true;
   }
      
   
      
   

   //This method checks if the player is an instance of a victory block and if so the returns true 
   public boolean win (ArrayList<ArrayList<GameObject>> list)
   {
      for (int i=0; i<list.size(); i++)
      {
         for (int k=0; k<list.get(0).size(); k++)
         {
            if (list.get(i).get(k) instanceof VictoryBlock)
            {
               if (list.get(i).get(k).gety()-this.gety()==25 && Math.abs((this.getx())-list.get(i).get(k).getx())<25)
               {
                  return true;
               }
            }
         }
      }
      return false;
   }
   //This method checks if the left side of player is open   

   public boolean leftSide (ArrayList<ArrayList<GameObject>> list)
   {
      for (int i=0; i<list.size(); i++)
      {
         for (int k=0; k<list.get(0).size(); k++)
         {
            if (list.get(i).get(k) instanceof GameObject)
            {
               if (Math.abs((this.gety())-list.get(i).get(k).gety())<25 && Math.abs((this.getx()-1)-list.get(i).get(k).getx())<26)
               {
                  return true;
               }
            }
         }
      }
      return false;
   }
   
   //This method checks if the right side of player is open   
   public boolean rightSide (ArrayList<ArrayList<GameObject>> list)
   {
      for (int i=0; i<list.size(); i++)
      {
         for (int k=0; k<list.get(0).size(); k++)
         {
            if (list.get(i).get(k) instanceof GameObject)
            {
               if (Math.abs((this.gety())-list.get(i).get(k).gety())<25 && Math.abs((this.getx()+1)-list.get(i).get(k).getx())<26)
               {
                  return true;
               }
            }
         }
      }
      return false;
   }
      
      
   
   //This method is the second condition for winning
   public boolean winl (ArrayList<ArrayList<GameObject>> list, int p, int p2)
   {
      for (int i=0; i<list.size(); i++)
      {
         for (int k=0; k<list.get(0).size(); k++)
         {
            if (list.get(i).get(k) instanceof VictoryBlock)
            {
               if (Math.abs((this.getx()-p)-list.get(i).get(k).getx())<25 && Math.abs(list.get(i).get(k).gety()-this.gety()+p2)<25)
               {
                  return true;
               }
            }
         }
      }
      return false;
   }

}

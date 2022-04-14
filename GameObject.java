import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.*;
import java.text.*;
import java.io.*;
import java.awt.event.*;
import java.util.Random;
public class GameObject
{
   //Variables
   private int xPosition;
   private int yPosition;
   private Color color;
   //Sets variables in constructer
   public GameObject(int x, int y, Color color1)
   {
      xPosition = x;
      yPosition = y;
      color = color1;
   }
    //method if another Gameobject x and y minus the other x and y are greater than 26 return true 
   public boolean collides (GameObject other)
   {
      if (this == other)
      {
         return false;
      }
      else
      {
         if (Math.abs(this.gety()-other.gety())<26&&Math.abs(this.getx()-other.getx())<26)
         {
            return true;
         }
         return false;
      }
   }
   //When called it draws the game object   
   public void draw (Graphics g)
   {
      g.setColor(color);
      g.fillRect(getx()-12,gety()-12,25,25);
   }
   
   //Acs and Muts
   public int getx()
   {
      return xPosition;
   }
   public int gety()
   {
      return yPosition;
   }
   
   public Color getColor()
   {
      return color;
   }
   public void setx (int x)
   {
      xPosition = x;
   }
   public void sety (int y)
   {
      yPosition = y;
   }
}


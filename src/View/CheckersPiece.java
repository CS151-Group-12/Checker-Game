import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
   An icon that has the shape of a car.
*/
public class CheckersPiece {
   /**
      Constructs a car of a given width.
      @param width the width of the car
   */
	Point p;
	boolean king;
	Color outer;
	Color inner;
	
	enum Status{
		BLACKPIECE,BLACKKING,REDPIECE,REDKING
	}
	
   public CheckersPiece(Point p, Boolean red, Boolean king)
   {
      this.p = p;
      this.king=king;
      if(red) {
    	   outer = Color.RED; 
    	   inner = new Color(196, 46, 20);
      }
      else {
    	  outer = Color.BLACK;
    	  inner = new Color(74, 72, 72);
      }

      
   }
   
   public void draw(Graphics2D g2) {
//		g2.setColor(outer);
//		g2.drawOval(p.x, p.y, width, 20);
		
//		g2.setColor(Color.GREEN);
	   Ellipse2D.Double Circle = new Ellipse2D.Double(p.x+2, p.y+2, 71 , 71);
	   //Ellipse2D.Double Circle = new Ellipse2D.Double(p.x , p.y , width/2 , width);
	   //Ellipse2D.Double Circle2= new Ellipse2D.Double(p.x+10 , p.y+10 ,width-20 , width-20);
	   Ellipse2D.Double Circle2= new Ellipse2D.Double(p.x+7 , p.y+7 ,61 , 61);
	   
	      
	   	g2.setColor(outer);
	      g2.fill(Circle);
	      g2.setColor(Color.BLACK);
	      g2.draw(Circle);
	      g2.setColor(inner);
	      
	      g2.fill(Circle2);
	      g2.setColor(Color.BLACK);
	      g2.draw(Circle2);
	      
	      if(king) {
	    	  Image i= drawCrown();
	    	  g2.drawImage(i, p.x+9, p.y+9, Circle2.getBounds().width-5, Circle2.getBounds().height-5, null);
	      }
	      
	}
   
   private Image drawCrown() {
	   Image image = null;
	   BufferedImage Bimage = null;
		 
       try {
         Bimage = ImageIO.read(new File("resources/crown.png"));
        // image = ImageIO.read(new File("resources/Placeholder icon.png"));

       } catch (IOException e) {
           e.printStackTrace();
       }

      image = Bimage;
      
      image =image.getScaledInstance(50, 50,image.SCALE_SMOOTH);
	   return image;
   }

}



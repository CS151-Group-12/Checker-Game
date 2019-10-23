import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class Tile  {
	private Point coords;
	private Color tileColor;
	private int size;
	private boolean highlight;
	private CheckersPiece cp;
	private Status s2;

	
	
	enum Status{
		BLACKPIECE,BLACKKING,REDPIECE,REDKING,NONE
	}

	public Tile(Point p,Color c,int Size,boolean Highlight,String s) {
		coords=p;
		tileColor=c;
		size = Size;	
		highlight = Highlight;
		setStatus("NONE");
	}
	public void setStatus(String s) {
		 s2 = Status.valueOf(s);
	}
	public void setHighlight(Boolean b) {
		 highlight = b;
	}
	public void draw(Graphics2D g2) {
		
		g2.setColor(tileColor);
		g2.fillRect(coords.x, coords.y, size, size);
		
		if (highlight) {
			g2.setColor(Color.YELLOW);
			g2.fillRect(coords.x+5, coords.y+5, size-10, size-10);
		}
		
		switch(s2) {  	  
	      case BLACKPIECE:
	    	  cp= new CheckersPiece(coords, false,false);
	    	  cp.draw(g2);
	    	  break;
	      case BLACKKING:
	    	  cp= new CheckersPiece(coords,false,true);
	    	  cp.draw(g2);
	    	  break;
	      case REDPIECE:
	    	 // cp= new CheckersPiece(coords,false,false);
	    	 new CheckersPiece(coords,true,false).draw(g2);
	    	 // cp.draw(g2);
	    	  break;
	      case REDKING:
	    	  cp= new CheckersPiece(coords,true,true);
	    	  cp.draw(g2);
	    	  break;	    	  
	      case NONE:
	    	  break;
	    	   
		}
 
	}
//	   public boolean contains(Point2D p)
//	   {
//	      return x <= p.getX() && p.getX() <= x + width 
//	         && y <= p.getY() && p.getY() <= y + width / 2;
//	   }
	//need coordinates
	
	//need highlight/black/black king/red king/none/
	
	//need 
	//
	
	
	
}

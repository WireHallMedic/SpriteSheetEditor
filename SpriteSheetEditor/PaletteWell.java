package SpriteSheetEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaletteWell extends JPanel implements MouseListener
{
	private Color color;


	public Color getColor(){return color;}



   public PaletteWell(Color c)
   {
      super();
      setColor(c);
      addMouseListener(this);
   }
   
   public PaletteWell(){this(Color.BLACK);}
   
	public void setColor(Color c)
   {
      color = c;
      setBackground(color);
   }
   
   public void mouseEntered(MouseEvent me){}
   public void mouseExited(MouseEvent me){}
   public void mousePressed(MouseEvent me){}
   public void mouseReleased(MouseEvent me){}
   
   public void mouseClicked(MouseEvent me)
   {
      if(me.getButton() == me.BUTTON1)
         setColor(SSEEngine.getCurColor());
      if(me.getButton() == me.BUTTON3)
         SSEEngine.setCurColor(color);
   }
}
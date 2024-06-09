package SpriteSheetEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class CanvasPanel extends JPanel implements MouseMotionListener
{
   private SSEMain parent;
   private int sizeMultiplier = 1;
   private static int[] mouseLoc = {-1, -1};
   private static Color locColor = Color.BLACK;
   private static final int BUFFER_SIZE_PX = 10;
   
   public static int[] getMouseLoc(){return mouseLoc;}
   
   public CanvasPanel(SSEMain p)
   {
      super();
      parent = p;
      addMouseMotionListener(this);
   }
   
   @Override
   public void paint(Graphics g)
   {
      super.paint(g);
      Graphics2D g2d = (Graphics2D)g;
      BufferedImage curTile = SSEEngine.getCurTile();
      
      if(curTile != null)
      {
         calcSizeMultiplier(curTile);
         g2d.drawImage(curTile, BUFFER_SIZE_PX, BUFFER_SIZE_PX, curTile.getWidth() * sizeMultiplier, curTile.getHeight() * sizeMultiplier, null);
      }
   }
   
   public void calcSizeMultiplier(BufferedImage img)
   {
      int maxMultWide = (this.getWidth() - (BUFFER_SIZE_PX * 2)) / img.getWidth();
      int maxMultHigh = (this.getHeight() - (BUFFER_SIZE_PX * 2)) / img.getHeight();
      sizeMultiplier = Math.min(maxMultWide, maxMultHigh);
   }
   
   
   
   // keep track of mouse location
   public void mouseDragged(MouseEvent me){}
   public void mouseMoved(MouseEvent me)
   {
      BufferedImage curTile = SSEEngine.getCurTile();
      int xLoc = (me.getX() - BUFFER_SIZE_PX) / sizeMultiplier;
      int yLoc = (me.getY() - BUFFER_SIZE_PX) / sizeMultiplier;
      if(curTile == null || xLoc < 0 || xLoc > curTile.getWidth() ||
         yLoc < 0 || yLoc > curTile.getHeight())
      {
         mouseLoc[0] = -1;
         mouseLoc[1] = -1;
      }
      else
      {
         mouseLoc[0] = xLoc;
         mouseLoc[1] = yLoc;
      }
   }
}
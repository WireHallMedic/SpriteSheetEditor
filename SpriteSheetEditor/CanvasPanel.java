package SpriteSheetEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class CanvasPanel extends JPanel implements MouseMotionListener
{
   private SSEMain parent;
   private int sizeMultiplier = 1;
   private BufferedImage curTile;
   private static int[] mouseLoc = {-1, -1};
   private static Color locColor = Color.BLACK;
   private static final int BUFFER_SIZE_PX = 10;
   
   public static int[] getMouseLoc(){return mouseLoc;}
   
   public CanvasPanel(SSEMain p)
   {
      super();
      parent = p;
      addMouseMotionListener(this);
      curTile = null;
   }
   
   @Override
   public void paint(Graphics g)
   {
      super.paint(g);
      Graphics2D g2d = (Graphics2D)g;
      curTile = SSEEngine.getCurTile();
      
      if(curTile != null)
      {
         calcSizeMultiplier();
         updateColorLoc();
         g2d.drawImage(curTile, BUFFER_SIZE_PX, BUFFER_SIZE_PX, curTile.getWidth() * sizeMultiplier, curTile.getHeight() * sizeMultiplier, null);
         Color gridColor = ControlPanel.getGridColor();
         g2d.setColor(gridColor);
         if(gridColor != null)
         {
            for(int x = 0; x <= curTile.getWidth(); x++)
               g2d.drawLine(BUFFER_SIZE_PX + (x * sizeMultiplier), BUFFER_SIZE_PX, 
                            BUFFER_SIZE_PX + (x * sizeMultiplier), BUFFER_SIZE_PX + (curTile.getHeight() * sizeMultiplier));
            for(int y = 0; y <= curTile.getHeight(); y++)
               g2d.drawLine(BUFFER_SIZE_PX, BUFFER_SIZE_PX + (y * sizeMultiplier), 
                            BUFFER_SIZE_PX + (curTile.getWidth() * sizeMultiplier), BUFFER_SIZE_PX + (y * sizeMultiplier));
         }
      }
   }
   
   public void calcSizeMultiplier()
   {
      if(curTile == null)
         return;
      int maxMultWide = (this.getWidth() - (BUFFER_SIZE_PX * 2)) / curTile.getWidth();
      int maxMultHigh = (this.getHeight() - (BUFFER_SIZE_PX * 2)) / curTile.getHeight();
      sizeMultiplier = Math.min(maxMultWide, maxMultHigh);
   }
   
   
   
   // keep track of mouse location and update current color under mouse
   public void mouseDragged(MouseEvent me){}
   public void mouseMoved(MouseEvent me)
   {
      int xLoc = (me.getX() - BUFFER_SIZE_PX);
      int yLoc = (me.getY() - BUFFER_SIZE_PX);
      if(curTile == null || xLoc < 0 || xLoc / sizeMultiplier >= curTile.getWidth() ||
         yLoc < 0 || yLoc / sizeMultiplier >= curTile.getHeight())
      {
         mouseLoc[0] = -1;
         mouseLoc[1] = -1;
      }
      else
      {
         mouseLoc[0] = xLoc / sizeMultiplier;
         mouseLoc[1] = yLoc / sizeMultiplier;
      }
   }
   
   public void updateColorLoc()
   {
      if(curTile == null || mouseLoc[0] < 0 || mouseLoc[1] < 0)
         locColor = null;
      else
         locColor = new Color(curTile.getRGB(mouseLoc[0], mouseLoc[1]));
   }
   
   public static String getColorString()
   {
      if(locColor == null)
         return "";
      return String.format("%d, %d, %d, %d", locColor.getRed(), locColor.getGreen(), locColor.getBlue(), locColor.getAlpha());
   }
}
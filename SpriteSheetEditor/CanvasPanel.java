package SpriteSheetEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public class CanvasPanel extends JPanel
{
   private SSEMain parent;
   private int sizeMultiplier = 1;
   private static int[] mouseLoc = {-1, -1};
   private static Color locColor = Color.BLACK;
   
   public CanvasPanel(SSEMain p)
   {
      super();
      parent = p;
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
         g2d.drawImage(curTile, 10, 10, curTile.getWidth() * sizeMultiplier, curTile.getHeight() * sizeMultiplier, null);
      }
   }
   
   public void calcSizeMultiplier(BufferedImage img)
   {
      int maxMultWide = (this.getWidth() - 20) / img.getWidth();
      int maxMultHigh = (this.getHeight() - 20) / img.getHeight();
      sizeMultiplier = Math.min(maxMultWide, maxMultHigh);
   }
}
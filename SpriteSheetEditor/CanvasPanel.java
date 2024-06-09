package SpriteSheetEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public class CanvasPanel extends JPanel
{
   private SSEMain parent;
   private int sizeMultiplier = 1;
   
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
         g2d.drawImage(curTile, 0, 0, curTile.getWidth() * sizeMultiplier, curTile.getHeight() * sizeMultiplier, null);
      }
   }
   
   public void calcSizeMultiplier(BufferedImage img)
   {
      int maxMultWide = this.getWidth() / img.getWidth();
      int maxMultHigh = this.getHeight() / img.getHeight();
      sizeMultiplier = Math.min(maxMultWide, maxMultHigh);
   }
}
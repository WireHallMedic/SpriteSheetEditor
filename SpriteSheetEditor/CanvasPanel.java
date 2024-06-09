package SpriteSheetEditor;

import javax.swing.*;
import java.awt.*;

public class CanvasPanel extends JPanel
{
   private SSEMain parent;
   
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
      
      if(SSEEngine.getCurTile() != null)
      {
         g2d.drawImage(SSEEngine.getCurTile(), 0, 0, null);
      }
   }
}
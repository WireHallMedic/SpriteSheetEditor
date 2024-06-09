package SpriteSheetEditor;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel
{
   public MapPanel()
   {
      super();
   }
   
   @Override
   public void paint(Graphics g)
   {
      super.paint(g);
      if(SSEEngine.getMapImage() != null)
      {
         Graphics2D g2d = (Graphics2D)g;
         g2d.drawImage(SSEEngine.getMapImage(), 0, 0, null);
      }
   }
}
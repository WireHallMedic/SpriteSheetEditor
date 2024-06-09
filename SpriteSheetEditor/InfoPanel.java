package SpriteSheetEditor;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel
{
   private SSEMain parent;
   
   public InfoPanel(SSEMain p)
   {
      super();
      parent = p;
      setBackground(Color.BLUE);
   }
}
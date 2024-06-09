package SpriteSheetEditor;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel
{
   private SSEMain parent;
   
   public ControlPanel(SSEMain p)
   {
      super();
      parent = p;
      setBackground(Color.YELLOW);
   }
}
package SpriteSheetEditor;

import javax.swing.*;
import java.awt.*;

public class PalettePanel extends JPanel
{
   private SSEMain parent;
   
   public PalettePanel(SSEMain p)
   {
      super();
      parent = p;
      setBackground(Color.GREEN);
   }
}
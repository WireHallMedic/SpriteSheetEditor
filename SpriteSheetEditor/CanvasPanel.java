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
      setBackground(Color.RED);
   }
}
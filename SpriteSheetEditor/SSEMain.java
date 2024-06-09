package SpriteSheetEditor;

import javax.swing.*;

public class SSEMain extends JFrame
{
   public static final int WIDTH = 1600;
   public static final int HEIGHT = 1000;
   
   public SSEMain()
   {
      super();
      setSize(WIDTH, HEIGHT);
      setTitle("SpriteSheetEditor");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);
   }
   
   public static void main(String[] args)
   {
      SSEMain frame = new SSEMain();
   }

}
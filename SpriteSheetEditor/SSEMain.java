package SpriteSheetEditor;

import javax.swing.*;
import java.awt.*;

public class SSEMain extends JFrame
{
   public static final int WIDTH = 1600;
   public static final int HEIGHT = 1000;
   private JPanel canvasPanel;
   private JPanel palettePanel;
   private JPanel controlPanel;
   private JPanel infoPanel;
   
   public SSEMain()
   {
      super();
      setSize(WIDTH, HEIGHT);
      setTitle("SpriteSheetEditor");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      populate();
      setVisible(true);
   }
   
   private void populate()
   {
      canvasPanel = new JPanel();
      canvasPanel.add(new JLabel("Canvas"));
      palettePanel = new JPanel();
      palettePanel.add(new JLabel("Palette"));
      controlPanel = new JPanel();
      controlPanel.add(new JLabel("Control"));
      infoPanel = new JPanel();
      infoPanel.add(new JLabel("Info"));
      
      setLayout(new GridLayout(1, 2));
      add(canvasPanel);
      
      JPanel anonPanel = new JPanel();
      anonPanel.setLayout(new GridLayout(3, 1));
      anonPanel.add(infoPanel);
      anonPanel.add(palettePanel);
      anonPanel.add(controlPanel);
      add(anonPanel);
   }
   
   public static void main(String[] args)
   {
      SSEMain frame = new SSEMain();
   }

}
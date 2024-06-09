package SpriteSheetEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SSEMain extends JFrame implements ActionListener
{
   public static final int WIDTH = 1600;
   public static final int HEIGHT = 1000;
   private CanvasPanel canvasPanel;
   private PalettePanel palettePanel;
   private ControlPanel controlPanel;
   private InfoPanel infoPanel;
   
   public SSEMain()
   {
      super();
      setSize(WIDTH, HEIGHT);
      setTitle("SpriteSheetEditor");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      populate();
      setVisible(true);
      javax.swing.Timer timer = new javax.swing.Timer(1000 / 30, this);
      timer.start();
   }
   
   private void populate()
   {
      canvasPanel = new CanvasPanel(this);
      palettePanel = new PalettePanel(this);
      controlPanel = new ControlPanel(this);
      infoPanel = new InfoPanel(this);
      
      setLayout(new GridLayout(1, 2));
      add(canvasPanel);
      
      JPanel anonPanel = new JPanel();
      anonPanel.setLayout(new GridLayout(3, 1));
      anonPanel.add(infoPanel);
      anonPanel.add(palettePanel);
      anonPanel.add(controlPanel);
      add(anonPanel);
   }
   
   public void actionPerformed(ActionEvent ae)
   {
      canvasPanel.repaint();
   }
   
   public static void main(String[] args)
   {
      SSEMain frame = new SSEMain();
   }

}
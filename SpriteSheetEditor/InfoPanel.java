package SpriteSheetEditor;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel
{
   private SSEMain parent;
   private MapPanel mapPanel;
   private JTextField tileLocationTF;
   private JTextField spriteLocationTF;
   private JTextField colorTF;
   
   
   public InfoPanel(SSEMain p)
   {
      super();
      parent = p;
      setLayout(new GridLayout(1, 2));
      
      mapPanel = new MapPanel();
      add(mapPanel);
      
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(5, 1));
      
      tileLocationTF = new JTextField("Tile Location: ");
      tileLocationTF.setEditable(false);
      tileLocationTF.setFocusable(false);
      panel.add(tileLocationTF);
      spriteLocationTF = new JTextField("Sprite Location: ");
      spriteLocationTF.setEditable(false);
      spriteLocationTF.setFocusable(false);
      panel.add(spriteLocationTF);
      colorTF = new JTextField("Color at Location: ");
      colorTF.setEditable(false);
      colorTF.setFocusable(false);
      panel.add(colorTF);
      panel.add(new JLabel("Arrow keys to change tile"));
      panel.add(new JLabel("Left-click to write, right-click to read"));
      add(panel);
   }
   
   public void update()
   {
      mapPanel.repaint();
      tileLocationTF.setText("Tile Location: " + SSEEngine.getXLoc() + ", " + SSEEngine.getYLoc());
      spriteLocationTF.setText("Sprite Location: " + CanvasPanel.getMouseLoc()[0] + ", " + CanvasPanel.getMouseLoc()[1]);
      colorTF.setText("Color at Location: " + CanvasPanel.getColorString());
   }
}
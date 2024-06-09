package SpriteSheetEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PalettePanel extends JPanel implements ActionListener
{
   private SSEMain parent;
   private JPanel curColorPanel;
   private JButton setColorB;
   
   public PalettePanel(SSEMain p)
   {
      super();
      parent = p;
      setLayout(new GridLayout(6, 3));
      add(new JLabel("Current Color:"));
      curColorPanel = new JPanel();
      add(curColorPanel);
      setColorB = new JButton("Custom Color");
      setColorB.setFocusable(false);
      setColorB.addActionListener(this);
      add(setColorB);
      
      add(new PaletteWell(Color.WHITE));
      add(new PaletteWell(Color.RED));
      add(new PaletteWell(new Color(160, 32, 240))); // purple
      
      add(new PaletteWell(Color.LIGHT_GRAY));
      add(new PaletteWell(Color.ORANGE));
      add(new PaletteWell(Color.CYAN));
      
      add(new PaletteWell(Color.GRAY));
      add(new PaletteWell(Color.YELLOW));
      add(new PaletteWell(Color.PINK));
      
      add(new PaletteWell(Color.DARK_GRAY));
      add(new PaletteWell(Color.GREEN));
      add(new PaletteWell(Color.MAGENTA));
      
      add(new PaletteWell(Color.BLACK));
      add(new PaletteWell(Color.BLUE));
      add(new PaletteWell(new Color(150, 75, 0))); // brown
      
      update();
   }
   
   public void update()
   {
      curColorPanel.setBackground(SSEEngine.getCurColor());
   }
   
   public void actionPerformed(ActionEvent ae)
   {
      new ColorChooser(SSEEngine.getCurColor());
   }
}
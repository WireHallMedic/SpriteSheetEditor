package SpriteSheetEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PalettePanel extends JPanel implements ActionListener
{
   private SSEMain parent;
   private JPanel curColorPanel;
   private JButton setColorB;
   private PaletteWell[] wellArr;
   private static final Color[] defaultList = {
      Color.WHITE,
      Color.RED,
      new Color(160, 32, 240), // purple
      Color.LIGHT_GRAY,
      Color.ORANGE,
      Color.CYAN,
      Color.GRAY,
      Color.YELLOW,
      Color.PINK,
      Color.DARK_GRAY,
      Color.GREEN,
      Color.MAGENTA,
      Color.BLACK,
      Color.BLUE,
      new Color(150, 75, 0)}; // brown
   
   public PalettePanel(SSEMain p)
   {
      super();
      parent = p;
      setLayout(new GridLayout(6, 3));
      add(new JLabel("Current Color:", SwingConstants.CENTER));
      curColorPanel = new JPanel();
      add(curColorPanel);
      setColorB = new JButton("Custom Color");
      setColorB.setFocusable(false);
      setColorB.addActionListener(this);
      add(setColorB);
      
      wellArr = new PaletteWell[defaultList.length];
      for(int i = 0; i < defaultList.length; i++)
      {
         wellArr[i] = new PaletteWell(defaultList[i]);
         add(wellArr[i]);
      }
      
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
   
   public void setPalette(String indexStr, String colorStr)
   {
      try
      {
         int index = Integer.parseInt(indexStr);
         int r = Integer.parseInt(colorStr.split(",")[0]);
         int g = Integer.parseInt(colorStr.split(",")[1]);
         int b = Integer.parseInt(colorStr.split(",")[2]);
         Color c = new Color(r, g, b);
         wellArr[index].setColor(c);
      }
      catch(Exception ex)
      {
         System.out.println("Unable to set color #" + indexStr + ": " + colorStr);
      }
   }
}
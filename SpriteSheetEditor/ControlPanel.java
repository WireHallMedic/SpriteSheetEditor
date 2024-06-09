package SpriteSheetEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ControlPanel extends JPanel implements ActionListener
{
   private SSEMain parent;
   private JButton loadB;
   private JButton saveB;
   private JTextField widthF;
   private JTextField heightF;
   private JButton widthPB;
   private JButton widthMB;
   private JButton heightPB;
   private JButton heightMB;
   
   public ControlPanel(SSEMain p)
   {
      super();
      parent = p;
      setLayout(new GridLayout(4, 1));
      
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(1, 6));
      widthF = new JTextField("Tile Width:");
      widthF.setEditable(false);
      widthF.setFocusable(false);
      panel.add(widthF);
      widthPB = new JButton("+");
      widthPB.addActionListener(this);
      widthPB.setFocusable(false);
      panel.add(widthPB);
      widthMB = new JButton("-");
      widthMB.addActionListener(this);
      widthMB.setFocusable(false);
      panel.add(widthMB);
      heightF = new JTextField("Tile Height:");
      heightF.setEditable(false);
      heightF.setFocusable(false);
      panel.add(heightF);
      heightPB = new JButton("+");
      heightPB.addActionListener(this);
      heightPB.setFocusable(false);
      panel.add(heightPB);
      heightMB = new JButton("-");
      heightMB.addActionListener(this);
      heightMB.setFocusable(false);
      panel.add(heightMB);
      add(panel);
      
      loadB = new JButton("Load");
      loadB.addActionListener(this);
      loadB.setFocusable(false);
      add(loadB);
      
      saveB = new JButton("Save");
      saveB.addActionListener(this);
      saveB.setFocusable(false);
      add(saveB);
      
      update();
   }
   
   public void update()
   {
      widthF.setText("Tile Width: " + SSEEngine.getTileWidth());
      heightF.setText("Tile Height: " + SSEEngine.getTileHeight());
   }
   
   public void actionPerformed(ActionEvent ae)
   {
      int widthMod = 0;
      int heightMod = 0;
      if(ae.getSource() == loadB)
         SSEEngine.load(this);
      if(ae.getSource() == widthPB)
         widthMod = 1;
      if(ae.getSource() == widthMB)
         widthMod = -1;
      if(ae.getSource() == heightPB)
         heightMod = 1;
      if(ae.getSource() == heightMB)
         heightMod = -1;
      
      if(widthMod != 0 || heightMod != 0)
      {
         int newWidth = Math.max(1, SSEEngine.getTileWidth() + widthMod);
         int newHeight = Math.max(1, SSEEngine.getTileHeight() + heightMod);
         SSEEngine.setTileSize(newWidth, newHeight);
         update();
      }
   }
}
package SpriteSheetEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

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
   private JRadioButton noGridB;
   private JRadioButton whiteGridB;
   private JRadioButton blackGridB;
   private JRadioButton greyGridB;
   private JRadioButton cyanGridB;
   private JRadioButton drawB;
   private JRadioButton fillB;
   private JButton copyB;
   private JButton pasteB;
   private static BufferedImage copiedImage = null;
   private static Color gridColor = null;
   
   public static Color getGridColor(){return gridColor;}
   
   public ControlPanel(SSEMain p)
   {
      super();
      parent = p;
      setLayout(new GridLayout(5, 1));
      
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(1, 6));
      panel.add(new JLabel("Cursor Action:", SwingConstants.CENTER));
      drawB = new JRadioButton("Draw");
      drawB.setFocusable(false);
      drawB.setSelected(true);
      drawB.addActionListener(this);
      panel.add(drawB);
      fillB = new JRadioButton("Fill");
      fillB.setFocusable(false);
      fillB.addActionListener(this);
      panel.add(fillB);
      ButtonGroup drawTypeGroup = new ButtonGroup();
      drawTypeGroup.add(drawB);
      drawTypeGroup.add(fillB);
      panel.add(new JLabel(""));
      copyB = new JButton("Copy Tile");
      copyB.addActionListener(this);
      copyB.setFocusable(false);
      panel.add(copyB);
      pasteB = new JButton("Paste Tile");
      pasteB.addActionListener(this);
      pasteB.setEnabled(false);
      pasteB.setFocusable(false);
      
      panel.add(pasteB);
      add(panel);
      
      panel = new JPanel();
      panel.setLayout(new GridLayout(1, 7));
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
      panel.add(new JLabel(""));
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
      
      panel = new JPanel();
      panel.setLayout(new GridLayout(1, 6));
      noGridB = new JRadioButton("No Grid");
      noGridB.setFocusable(false);
      noGridB.setSelected(true);
      noGridB.addActionListener(this);
      panel.add(noGridB);
      whiteGridB = new JRadioButton("White Grid");
      whiteGridB.setFocusable(false);
      whiteGridB.addActionListener(this);
      panel.add(whiteGridB);
      blackGridB = new JRadioButton("Black Grid");
      blackGridB.setFocusable(false);
      blackGridB.addActionListener(this);
      panel.add(blackGridB);
      greyGridB = new JRadioButton("Grey Grid");
      greyGridB.setFocusable(false);
      greyGridB.addActionListener(this);
      panel.add(greyGridB);
      cyanGridB = new JRadioButton("Cyan Grid");
      cyanGridB.setFocusable(false);
      cyanGridB.addActionListener(this);
      panel.add(cyanGridB);
      ButtonGroup gridGroup = new ButtonGroup();
      gridGroup.add(noGridB);
      gridGroup.add(whiteGridB);
      gridGroup.add(blackGridB);
      gridGroup.add(greyGridB);
      gridGroup.add(cyanGridB);
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
      if(ae.getSource() == saveB)
         SSEEngine.save(this);
      if(ae.getSource() == widthPB)
         widthMod = 1;
      if(ae.getSource() == widthMB)
         widthMod = -1;
      if(ae.getSource() == heightPB)
         heightMod = 1;
      if(ae.getSource() == heightMB)
         heightMod = -1;
      if(ae.getSource() == noGridB)
         gridColor = null;
      if(ae.getSource() == whiteGridB)
         gridColor = Color.WHITE;
      if(ae.getSource() == blackGridB)
         gridColor = Color.BLACK;
      if(ae.getSource() == greyGridB)
         gridColor = Color.GRAY;
      if(ae.getSource() == cyanGridB)
         gridColor = Color.CYAN;
      
      if(widthMod != 0 || heightMod != 0)
      {
         int newWidth = Math.max(1, SSEEngine.getTileWidth() + widthMod);
         int newHeight = Math.max(1, SSEEngine.getTileHeight() + heightMod);
         SSEEngine.setTileSize(newWidth, newHeight);
         update();
      }
   }
}
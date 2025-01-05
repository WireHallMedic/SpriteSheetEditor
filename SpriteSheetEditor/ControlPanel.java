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
   private JButton rotateB;
   private JButton mirrorVertB;
   private JButton mirrorHorizB;
   private static boolean doDrawOnClick = true;
   private static Color gridColor = null;
   
   public static Color getGridColor(){return gridColor;}
   public static boolean drawOnClick(){return doDrawOnClick;}
   
   public ControlPanel(SSEMain p)
   {
      super();
      parent = p;
      setLayout(new GridLayout(5, 1));
      
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(1, 3));
      panel.add(new JLabel("Cursor Action:", SwingConstants.CENTER));
      drawB = new JRadioButton("Draw");
      setJButton(drawB, panel);
      drawB.setSelected(true);
      fillB = new JRadioButton("Fill");
      setJButton(fillB, panel);
      ButtonGroup drawTypeGroup = new ButtonGroup();
      drawTypeGroup.add(drawB);
      drawTypeGroup.add(fillB);
      add(panel);
      
      panel = new JPanel();
      panel.setLayout(new GridLayout(1, 6));
      panel.add(new JLabel("Tile Action:", SwingConstants.CENTER));
      copyB = new JButton("Copy Tile");
      setJButton(copyB, panel);
      pasteB = new JButton("Paste Tile");
      setJButton(pasteB, panel);
      pasteB.setEnabled(false);
      rotateB = new JButton("Rotate Tile");
      setJButton(rotateB, panel);
      mirrorVertB = new JButton("Mirror Vert");
      setJButton(mirrorVertB, panel);
      mirrorHorizB = new JButton("Mirror Horiz");
      setJButton(mirrorHorizB, panel);
      add(panel);
      
      panel = new JPanel();
      panel.setLayout(new GridLayout(1, 7));
      widthF = new JTextField("Tile Width:");
      widthF.setEditable(false);
      widthF.setFocusable(false);
      panel.add(widthF);
      widthPB = new JButton("+");
      setJButton(widthPB, panel);
      widthMB = new JButton("-");
      setJButton(widthMB, panel);
      panel.add(new JLabel(""));
      heightF = new JTextField("Tile Height:");
      heightF.setEditable(false);
      heightF.setFocusable(false);
      panel.add(heightF);
      heightPB = new JButton("+");
      setJButton(heightPB, panel);
      heightMB = new JButton("-");
      setJButton(heightMB, panel);
      add(panel);
      
      panel = new JPanel();
      panel.setLayout(new GridLayout(1, 6));
      noGridB = new JRadioButton("No Grid");
      setJButton(noGridB, panel);
      noGridB.setSelected(true);
      whiteGridB = new JRadioButton("White Grid");
      setJButton(whiteGridB, panel);
      blackGridB = new JRadioButton("Black Grid");
      setJButton(blackGridB, panel);
      greyGridB = new JRadioButton("Grey Grid");
      setJButton(greyGridB, panel);
      cyanGridB = new JRadioButton("Cyan Grid");
      setJButton(cyanGridB, panel);
      ButtonGroup gridGroup = new ButtonGroup();
      gridGroup.add(noGridB);
      gridGroup.add(whiteGridB);
      gridGroup.add(blackGridB);
      gridGroup.add(greyGridB);
      gridGroup.add(cyanGridB);
      add(panel);
      
      panel = new JPanel();
      panel.setLayout(new GridLayout(1, 2));
      loadB = new JButton("Load");
      setJButton(loadB, panel);
      saveB = new JButton("Save");
      setJButton(saveB, panel);
      add(panel);
      
      update();
   }
   
   private void setJButton(AbstractButton button, JPanel panel)
   {
      button.addActionListener(this);
      button.setFocusable(false);
      panel.add(button);
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
      if(ae.getSource() == drawB)
         doDrawOnClick = true;
      if(ae.getSource() == fillB)
         doDrawOnClick = false;
      if(ae.getSource() == copyB)
      {
         SSEEngine.copyTile();
         pasteB.setEnabled(true);
      }
      if(ae.getSource() == pasteB)
         SSEEngine.pasteTile();
      if(ae.getSource() == rotateB)
         SSEEngine.pasteTile();
      if(ae.getSource() == mirrorHorizB)
         SSEEngine.pasteTile();
      if(ae.getSource() == mirrorVertB)
         SSEEngine.pasteTile();
      
      if(widthMod != 0 || heightMod != 0)
      {
         int newWidth = Math.max(1, SSEEngine.getTileWidth() + widthMod);
         int newHeight = Math.max(1, SSEEngine.getTileHeight() + heightMod);
         SSEEngine.setTileSize(newWidth, newHeight);
         update();
      }
   }
}
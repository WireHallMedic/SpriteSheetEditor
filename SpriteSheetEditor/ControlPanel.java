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
   
   public ControlPanel(SSEMain p)
   {
      super();
      parent = p;
      setLayout(new GridLayout(4, 1));
      
      loadB = new JButton("Load");
      loadB.addActionListener(this);
      add(loadB);
      
      saveB = new JButton("Save");
      saveB.addActionListener(this);
      add(saveB);
      
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(1, 2));
      panel.add(new JLabel("Tile Width"));
      widthF = new JTextField("16");
      panel.add(widthF);
      add(panel);
      
      panel = new JPanel();
      panel.setLayout(new GridLayout(1, 2));
      panel.add(new JLabel("Tile Height"));
      heightF = new JTextField("8");
      panel.add(heightF);
      add(panel);
      
   }
   
   public void actionPerformed(ActionEvent ae)
   {
      if(ae.getSource() == loadB)
         SSEEngine.load(this);
   }
}
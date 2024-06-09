package SpriteSheetEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class ColorChooser extends JFrame implements DocumentListener, ActionListener
{
   private JTextArea redTA;
   private JTextArea greenTA;
   private JTextArea blueTA;
   private JTextArea alphaTA;
   private JPanel colorP;
   private JButton doneB;
   private JButton cancelB;
   
   public ColorChooser(Color oldColor)
   {
      super();
      setSize(300, 300);
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(6, 2));
      add(panel);
      
      panel.add(new JLabel("Red"));
      redTA = new JTextArea("" + oldColor.getRed());
      redTA.getDocument().addDocumentListener(this);
      panel.add(redTA);
      
      panel.add(new JLabel("Green"));
      greenTA = new JTextArea("" + oldColor.getGreen());
      greenTA.getDocument().addDocumentListener(this);
      panel.add(greenTA);
      
      panel.add(new JLabel("Blue"));
      blueTA = new JTextArea("" + oldColor.getBlue());
      blueTA.getDocument().addDocumentListener(this);
      panel.add(blueTA);
      
      panel.add(new JLabel("Alpha"));
      alphaTA = new JTextArea("" + oldColor.getAlpha());
      alphaTA.getDocument().addDocumentListener(this);
      panel.add(alphaTA);
      
      panel.add(new JPanel());
      colorP = new JPanel();
      panel.add(colorP);
      
      cancelB = new JButton("Cancel");
      cancelB.addActionListener(this);
      panel.add(cancelB);
      doneB = new JButton("Done");
      doneB.addActionListener(this);
      panel.add(doneB);
      
      setVisible(true);
      update();
   }
   
   public int readField(JTextArea textArea)
   {
      int val = 0;
      try
      {
         val = Integer.parseInt(textArea.getText());
      }
      catch(Exception ex)
      {
         val = 0;
      }
      val = Math.max(0, val);
      val = Math.min(255, val);
      return val;
   }
   
   public void changedUpdate(DocumentEvent de)
   {
      update();
   }
   
   public void removeUpdate(DocumentEvent de)
   {
      update();
   }
   
   public void insertUpdate(DocumentEvent de)
   {
      update();
   }
   
   public void update()
   {
      colorP.setBackground(new Color(readField(redTA), readField(greenTA), readField(blueTA), readField(alphaTA)));
   }
   
   public void actionPerformed(ActionEvent ae)
   {
      if(ae.getSource() == cancelB)
         this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
      if(ae.getSource() == doneB)
      {
         SSEEngine.setCurColor(new Color(readField(redTA), readField(greenTA), readField(blueTA), readField(alphaTA)));
         this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
      }
   }
}
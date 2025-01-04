package SpriteSheetEditor;

import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;
import java.awt.*;

public class SSEEngine
{
   private static BufferedImage fullImage = null;
   private static BufferedImage curTile = null;
   private static int[][] clipboardArr = null;
   private static BufferedImage mapImage = null;
   private static int xLoc = 0;
   private static int yLoc = 0;
   private static Color curColor = Color.WHITE;
   
   private static int fullImageWidth = 0;
   private static int fullImageHeight = 0;
   private static int tileWidth = 16;
   private static int tileHeight = 16;
   
   public static BufferedImage getCurTile(){return curTile;}
   public static BufferedImage getFullImage(){return fullImage;}
   public static BufferedImage getMapImage(){return mapImage;}
   public static int getXLoc(){return xLoc;}
   public static int getYLoc(){return yLoc;}
   public static int getTileWidth(){return tileWidth;}
   public static int getTileHeight(){return tileHeight;}
   
   public static void setTileSize(int w, int h){tileWidth = w; tileHeight = h; setDependentImages();}
   public static void setFullImage(BufferedImage img){fullImage = img; setDependentImages();}
   public static void setLoc(int x, int y){xLoc = x; yLoc = y; setDependentImages();}
   
   public static void load(Component component)
   {
      JFileChooser fc = new JFileChooser("./SpriteSheetEditor");
      File workingDirectory = new File(System.getProperty("user.dir"));
      fc.setCurrentDirectory(workingDirectory);
      fc.setFileFilter(new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));
      int returnVal = fc.showOpenDialog(component);
      
      if(returnVal == JFileChooser.APPROVE_OPTION)
      {
         try
         {
            fullImage = ImageIO.read(fc.getSelectedFile());
            fullImageWidth = fullImage.getWidth();
            fullImageHeight = fullImage.getHeight();
         }
         catch(Exception ex)
         {
            System.out.println(ex.toString());
            fullImage = null;
            fullImageWidth = 0;
            fullImageHeight = 0;
         }
         setDependentImages();
      }
   }
   
   public static void save(Component component)
   {
      JFileChooser fc = new JFileChooser("./SpriteSheetEditor");
      File workingDirectory = new File(System.getProperty("user.dir"));
      fc.setCurrentDirectory(workingDirectory);
      fc.setFileFilter(new FileNameExtensionFilter("PNG file","png"));
      int returnVal = fc.showSaveDialog(component);
      if(returnVal == JFileChooser.APPROVE_OPTION)
      {
         try
         {
            File outfile = fc.getSelectedFile();
            if(!outfile.getName().endsWith(".png"))
               outfile = new File(outfile.getName() + ".png");
            ImageIO.write(fullImage, "png", outfile);
         }
         catch(Exception ex)
         {
            System.out.println(ex.toString());
         }
      }
   }
   
   private static void setDependentImages()
   {
      if(fullImage == null)
      {
         curTile = null;
         mapImage = null;
      }
      else
      {
         setCurTile(xLoc, yLoc);
         setMapImage(xLoc, yLoc);
      }
   }
   
   private static void setCurTile(int tileLocX, int tileLocY)
   {
      BufferedImage newImage = new BufferedImage(tileWidth, tileHeight, BufferedImage.TYPE_INT_ARGB);
      int xOffset = tileLocX * tileWidth;
      int yOffset = tileLocY * tileHeight;
      int curRGB = 0;
      
      for(int x = 0; x < tileWidth; x++)
      for(int y = 0; y < tileHeight; y++)
      {
         curRGB = getPixelRGB(x + xOffset, y + yOffset);
         newImage.setRGB(x, y, curRGB);
      }
      curTile = newImage;
   }
   
   private static void setMapImage(int tileLocX, int tileLocY)
   {
      BufferedImage newImage = new BufferedImage(tileWidth * 5, tileHeight * 5, BufferedImage.TYPE_INT_ARGB);
      int xOffset = (tileLocX - 2) * tileWidth;
      int yOffset = (tileLocY - 2) * tileHeight;
      for(int x = 0; x < tileWidth * 5; x++)
      for(int y = 0; y < tileHeight * 5; y++)
      {
         newImage.setRGB(x, y, getPixelRGB(x + xOffset, y + yOffset));
      }
      mapImage = newImage;
   }
   
   private static int getPixelRGB(int x, int y)
   {
      if(x < 0 || x >= fullImageWidth ||
         y < 0 || y >= fullImageHeight)
         return 0;
      return fullImage.getRGB(x, y);
   }
   
   private static void setPixelColor(int x, int y, Color c)
   {
      if(x < 0 || x >= fullImageWidth ||
         y < 0 || y >= fullImageHeight)
         return;
      fullImage.setRGB(x, y, c.getRGB());
   }

   public static void setCurColor(Color c)
   {
      curColor = c;
   }
   
   public static Color getCurColor()
   {
      return curColor;
   }
   
   public static void readColor(int x, int y)
   {
      int xIndex = x + (xLoc * tileWidth);
      int yIndex = y + (yLoc * tileHeight);
      setCurColor(new Color(getPixelRGB(xIndex, yIndex)));
   }
   
   public static void writeColor(int x, int y, Color c)
   {
      int xIndex = x + (xLoc * tileWidth);
      int yIndex = y + (yLoc * tileHeight);
      setPixelColor(xIndex, yIndex, c);
      setDependentImages();
   }
   
   public static void writeColor(int x, int y)
   {
      writeColor(x, y, curColor);
   }
   
   public static void copyTile()
   {
      clipboardArr = new int[tileWidth][tileHeight];
      for(int x = 0; x < tileWidth; x++)
      for(int y = 0; y < tileHeight; y++)
      {
         clipboardArr[x][y] = getPixelRGB(x + (xLoc * tileWidth), y + (yLoc * tileHeight));
      }
   }
   
   public static void pasteTile()
   {
      if(clipboardArr != null)
      {
         for(int x = 0; x < tileWidth; x++)
         for(int y = 0; y < tileHeight; y++)
            writeColor(x, y, new Color(clipboardArr[x][y]));
      }
   }
}
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
   public static BufferedImage fullImage = null;
   public static BufferedImage curTile = null;
   public static BufferedImage mapImage = null;
   
   private static int fullImageWidth = 0;
   private static int fullImageHeight = 0;
   private static int tileWidth = 24;
   private static int tileHeight = 24;
   
   public static int getTileWidth(){return tileWidth;}
   public static int getTileHeight(){return tileHeight;}
   public static BufferedImage getCurTile(){return curTile;}
   
   public static void setTileSize(int w, int h){tileWidth = w; tileHeight = h;}
   
   public static void load(Component component)
   {
      JFileChooser fc = new JFileChooser("./SpriteSheetInspector");
      File workingDirectory = new File(System.getProperty("user.dir"));
      fc.setCurrentDirectory(workingDirectory);
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
  //       setDependentImages(0, 0, magnification);
      }
   }
   /*
   public static void update(int tileX, int tileY, int magnification)
   {
      tileWidth = SSIControlPanel.getTileWidth();
      tileHeight = SSIControlPanel.getTileHeight();
      setDependentImages(tileX, tileY, magnification);
   }
   
   private static void setDependentImages(int tileX, int tileY, int magnification)
   {
      if(fullImage == null)
      {
         curTile = null;
         mapImage = null;
      }
      else
      {
         setCurTile(tileX, tileY, magnification);
         setMapImage(tileX, tileY);
      }
   }
   
   private static void setCurTile(int tileLocX, int tileLocY, int magnification)
   {
      BufferedImage newImage = new BufferedImage(tileWidth * magnification, tileHeight * magnification, BufferedImage.TYPE_INT_ARGB);
      int xOffset = tileLocX * tileWidth;
      int yOffset = tileLocY * tileHeight;
      int curRGB = 0;
      
      for(int x = 0; x < tileWidth; x++)
      for(int y = 0; y < tileHeight; y++)
      {
         curRGB = getPixelRGB(x + xOffset, y + yOffset);
         for(int xx = 0; xx < magnification; xx++)
         for(int yy = 0; yy < magnification; yy++)
         {
            newImage.setRGB((x * magnification) + xx, (y * magnification) + yy, curRGB);
         }
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
   
   public static BufferedImage getSplit()
   {
      // new image a little bigger than the original
      int newWidth = 1 + fullImageWidth + (fullImageWidth / tileWidth);
      int newHeight = 1 + fullImageHeight + (fullImageHeight / tileWidth);
      int greyVal = Color.GRAY.getRGB();
      BufferedImage splitCopy = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
      // initialize it grey
      for(int x = 0; x < newWidth; x++)
      for(int y = 0; y < newHeight; y++)
      {
         splitCopy.setRGB(x, y, greyVal);
      }
      for(int x = 0; x < fullImageWidth; x++)
      for(int y = 0; y < fullImageHeight; y++)
      {
         splitCopy.setRGB(x + 1 + (x / tileWidth), y + 1 + (y / tileHeight), getPixelRGB(x, y));
      }
      return splitCopy;
   }*/
}
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
   private static BufferedImage mapImage = null;
   private static int xLoc = 0;
   private static int yLoc = 0;
   
   private static int fullImageWidth = 0;
   private static int fullImageHeight = 0;
   private static int tileWidth = 24;
   private static int tileHeight = 24;
   
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
   /*
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
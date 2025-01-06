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
   private static File lastLoaded = null;
   private static final String CONFIG_FILE = "./SSE Config.txt";
   
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
         lastLoaded = fc.getSelectedFile();
      }
   }
   
   public static void save(Component component)
   {
      JFileChooser fc = new JFileChooser("./SpriteSheetEditor");
      File workingDirectory = new File(System.getProperty("user.dir"));
      fc.setCurrentDirectory(workingDirectory);
      fc.setFileFilter(new FileNameExtensionFilter("PNG file","png"));
      if(lastLoaded != null)
         fc.setSelectedFile(lastLoaded);
      int returnVal = fc.showSaveDialog(component);
      if(returnVal == JFileChooser.APPROVE_OPTION)
      {
         try
         {
            File outfile = fc.getSelectedFile();
            if(!outfile.getName().endsWith(".png"))
               outfile = new File(outfile.getName() + ".png");
            ImageIO.write(fullImage, "png", outfile);
         lastLoaded = outfile;
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
      setPixelColor(x, y, c.getRGB());
   }
   
   private static void setPixelColor(int x, int y, int c)
   {
      if(x < 0 || x >= fullImageWidth ||
         y < 0 || y >= fullImageHeight)
         return;
      fullImage.setRGB(x, y, c);
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
   
   public static void rotate()
   {
      int[][] curArr = new int[tileWidth][tileHeight];
      for(int x = 0; x < tileWidth; x++)
      for(int y = 0; y < tileHeight; y++)
         curArr[x][y] = getPixelRGB(x + (xLoc * tileWidth), y + (yLoc * tileHeight));
      for(int x = 0; x < tileWidth; x++)
      for(int y = 0; y < tileHeight; y++)
         writeColor((tileWidth - 1) - y, x, new Color(curArr[x][y]));
   }
   
   public static void mirrorHoriz()
   {
      int[][] curArr = new int[tileWidth][tileHeight];
      for(int x = 0; x < tileWidth; x++)
      for(int y = 0; y < tileHeight; y++)
         curArr[x][y] = getPixelRGB(x + (xLoc * tileWidth), y + (yLoc * tileHeight));
      for(int x = 0; x < tileWidth; x++)
      for(int y = 0; y < tileHeight; y++)
         writeColor((tileWidth - 1) - x, y, new Color(curArr[x][y]));
   }
   
   public static void mirrorVert()
   {
      int[][] curArr = new int[tileWidth][tileHeight];
      for(int x = 0; x < tileWidth; x++)
      for(int y = 0; y < tileHeight; y++)
         curArr[x][y] = getPixelRGB(x + (xLoc * tileWidth), y + (yLoc * tileHeight));
      for(int x = 0; x < tileWidth; x++)
      for(int y = 0; y < tileHeight; y++)
         writeColor(x, (tileHeight - 1) - y, new Color(curArr[x][y]));
   }
   
   public static void loadConfig(ControlPanel controlPanel, PalettePanel palettePanel)
   {
      try
      {
         File file = new File(CONFIG_FILE);  
         Scanner scn = new Scanner(file);  
         while (scn.hasNextLine())  
            processConfig(scn.nextLine(), controlPanel, palettePanel);  
      }
      catch(FileNotFoundException fnfEx)
      {
         createConfigFile();
      }
      catch(Exception ex)
      {
         System.out.println("Unable to load file. " + ex.toString());
      }
      controlPanel.update();
   }
   
   private static void processConfig(String str, ControlPanel controlPanel, PalettePanel palettePanel)
   {
      // skip comments and lines that do not conform to formatting
      if(str.startsWith("//"))
         return;
      String val;
      String comm;
      try
      {
         comm = str.split(" ")[0];
         val = str.split(" ")[1];
      }
      catch(Exception ex)
      {
         return;
      }
      
      if(str.contains("TILE_WIDTH_PX"))
         setTileSize(Integer.parseInt(val), tileHeight);
      if(str.contains("TILE_HEIGHT_PX"))
         setTileSize(tileWidth, Integer.parseInt(val));
      if(str.contains("GRID_TYPE"))
         controlPanel.setGridByName(val);
      if(str.contains("PALETTE_COLOR_"))
         palettePanel.setPalette(comm.replace("PALETTE_COLOR_", ""), val);
   }
   
   private static void createConfigFile()
   {/*
		PrintWriter outFile = null;
		try
		{
			outFile = new PrintWriter(FILE_NAME);
		}
		catch(Exception ex)
		{
			String errorMessage = "Error: Cannot write to " + FILE_NAME;
			JOptionPane.showMessageDialog(null, errorMessage, "Exception Occured", JOptionPane.ERROR_MESSAGE);
		}
		
		for(String line : output)
			outFile.println(line);
			
		outFile.close();
	}
      
      // Grid options: NONE, BLACK, WHITE, GREY, CYAN
// Set palette colors (0-15) with 'PALETTE_COLOR_# #,#,#' no spaces in the rgb values.

TILE_WIDTH_PX 24
TILE_HEIGHT_PX 24
GRID_TYPE WHITE*/

   }
}
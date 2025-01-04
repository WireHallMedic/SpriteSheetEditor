package SpriteSheetEditor;

import java.awt.image.*;
import java.util.*;
import java.awt.*;

public class SSEFloodFill
{
   private int startX;
   private int startY;
   private int targetColor;
   private BufferedImage img;
   private boolean[][] closedMap;
   private Vector<int[]> locList;
   
   
   public SSEFloodFill(BufferedImage image, int x, int y)
   {
      startX = x;
      startY = y;
      img = image;
      closedMap = new boolean[image.getWidth()][image.getHeight()];
      locList = new Vector<int[]>();
      start();
   }
   
   private void start()
   {
      targetColor = img.getRGB(startX, startY);
      int[] origin = {startX, startY};
      locList.add(origin);
      process(startX, startY);
   }
   
   private void process(int x, int y)
   {
      if(isInBounds(x, y))
      {
         closedMap[x][y] = true;
         // match, add to list and process neighbors
         if(img.getRGB(x, y) == targetColor)
         {
            int[] listItem = {x, y};
            locList.add(listItem);
            if(isValidNeighbor(x + 1, y)) process(x + 1, y);
            if(isValidNeighbor(x - 1, y)) process(x - 1, y);
            if(isValidNeighbor(x, y + 1)) process(x, y + 1);
            if(isValidNeighbor(x, y - 1)) process(x, y - 1);
         }
      }
   }
   
   private boolean isInBounds(int x, int y)
   {
      return x >= 0 &&
             y >= 0 &&
             x < img.getWidth() &&
             y < img.getHeight();
   }
   
   private boolean isValidNeighbor(int x, int y)
   {
      return isInBounds(x, y) && !closedMap[x][y];
   }
   
   public void applyFill(Color newColor)
   {
      System.out.println("Applying fill: " + locList.size());
      for(int i = 0; i < locList.size(); i++)
      {
         SSEEngine.writeColor(locList.elementAt(i)[0], locList.elementAt(i)[1], newColor);
      }
   }
}
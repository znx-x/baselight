/*    */ package net.sf.image4j.util;
/*    */ 
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Image;
/*    */ import java.awt.image.BufferedImage;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ImageUtil
/*    */ {
/*    */   public static BufferedImage scaleImage(BufferedImage src, int width, int height) {
/* 34 */     Image scaled = src.getScaledInstance(width, height, 0);
/* 35 */     BufferedImage ret = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 56 */     ret = new BufferedImage(width, height, 2);
/* 57 */     Graphics2D g = ret.createGraphics();
/*    */     
/* 59 */     g.drawImage(scaled, 0, 0, null);
/* 60 */     return ret;
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\net\sf\image4\\util\ImageUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
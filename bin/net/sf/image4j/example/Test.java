/*     */ package net.sf.image4j.example;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.imageio.ImageIO;
/*     */ import net.sf.image4j.codec.bmp.BMPDecoder;
/*     */ import net.sf.image4j.codec.bmp.BMPEncoder;
/*     */ import net.sf.image4j.codec.ico.ICODecoder;
/*     */ import net.sf.image4j.codec.ico.ICOEncoder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Test
/*     */ {
/*     */   public static void main(String[] args) {
/*  24 */     String strInFile = "input.ico";
/*  25 */     String strOutFile = "output.ico";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  31 */       System.out.println("Decoding ICO file '" + strInFile + "'.");
/*     */       
/*  33 */       File inFile = new File(strInFile);
/*     */ 
/*     */ 
/*     */       
/*  37 */       List<BufferedImage> images = ICODecoder.read(inFile);
/*     */       
/*  39 */       System.out.println("ICO decoding...OK");
/*     */ 
/*     */ 
/*     */       
/*  43 */       System.out.println("  image count = " + images.size());
/*  44 */       System.out.println("  image summary:");
/*  45 */       for (int i = 0; i < images.size(); i++) {
/*  46 */         BufferedImage img = images.get(i);
/*  47 */         int bpp = img.getColorModel().getPixelSize();
/*  48 */         int width = img.getWidth();
/*  49 */         int height = img.getHeight();
/*  50 */         System.out.println("    # " + i + ": size=" + width + "x" + height + "; colour depth=" + bpp + " bpp");
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  55 */       System.out.println("  saving separate images:");
/*     */       
/*  57 */       String format = "png";
/*     */       
/*  59 */       for (int j = 0; j < images.size(); j++) {
/*  60 */         BufferedImage img = images.get(j);
/*  61 */         String name = strInFile + "-" + j;
/*  62 */         File bmpFile = new File(name + ".bmp");
/*  63 */         File pngFile = new File(name + ".png");
/*     */ 
/*     */         
/*  66 */         System.out.println("    writing '" + name + ".bmp'");
/*  67 */         BMPEncoder.write(img, bmpFile);
/*     */ 
/*     */         
/*  70 */         System.out.println("    writing '" + name + ".png'");
/*  71 */         ImageIO.write(img, format, pngFile);
/*     */       } 
/*     */       
/*  74 */       System.out.println("BMP encoding...OK");
/*     */ 
/*     */ 
/*     */       
/*  78 */       System.out.println("  reloading BMP files:");
/*     */ 
/*     */       
/*  81 */       List<BufferedImage> images2 = new ArrayList<>(images.size());
/*     */       
/*  83 */       for (int k = 0; k < images.size(); k++) {
/*  84 */         String name = strInFile + "-" + k + ".bmp";
/*  85 */         File file = new File(name);
/*     */ 
/*     */         
/*  88 */         System.out.println("    reading '" + name + "'");
/*  89 */         BufferedImage image = BMPDecoder.read(file);
/*  90 */         images2.add(image);
/*     */       } 
/*     */       
/*  93 */       System.out.println("BMP decoding...OK");
/*     */ 
/*     */ 
/*     */       
/*  97 */       System.out.println("Encoding ICO file '" + strOutFile + "'.");
/*     */       
/*  99 */       File outFile = new File(strOutFile);
/*     */       
/* 101 */       ICOEncoder.write(images, outFile);
/*     */       
/* 103 */       System.out.println("ICO encoding...OK");
/*     */     }
/* 105 */     catch (IOException ex) {
/* 106 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void usage() {}
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\net\sf\image4j\example\Test.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
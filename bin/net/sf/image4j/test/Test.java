/*     */ package net.sf.image4j.test;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
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
/*     */ 
/*     */ public class Test
/*     */ {
/*     */   public static void main(String[] args) {
/*  28 */     if (args.length < 2) {
/*  29 */       System.out.println("Usage:\n\tTest <inputfile> <outputfile>");
/*  30 */       System.exit(1);
/*     */     } 
/*     */     
/*  33 */     String strInFile = args[0];
/*  34 */     String strOutFile = args[1];
/*     */     
/*  36 */     InputStream in = null;
/*     */ 
/*     */     
/*     */     try {
/*     */       List<BufferedImage> images;
/*     */ 
/*     */       
/*  43 */       if (strInFile.startsWith("http:")) {
/*  44 */         in = (new URL(strInFile)).openStream();
/*     */       } else {
/*  46 */         in = new FileInputStream(strInFile);
/*     */       } 
/*     */       
/*  49 */       if (!strInFile.endsWith(".ico")) {
/*     */         
/*  51 */         images = new ArrayList<>(1);
/*  52 */         images.add(ImageIO.read(in));
/*     */         
/*  54 */         System.out.println("Read image " + strInFile + "...OK");
/*     */       }
/*     */       else {
/*     */         
/*  58 */         System.out.println("Decoding ICO file '" + strInFile + "'.");
/*     */ 
/*     */ 
/*     */         
/*  62 */         images = ICODecoder.read(in);
/*  63 */         System.out.println("ICO decoding...OK");
/*     */ 
/*     */ 
/*     */         
/*  67 */         System.out.println("  image count = " + images.size());
/*  68 */         System.out.println("  image summary:");
/*  69 */         for (int i = 0; i < images.size(); i++) {
/*  70 */           BufferedImage img = images.get(i);
/*  71 */           int bpp = img.getColorModel().getPixelSize();
/*  72 */           int width = img.getWidth();
/*  73 */           int height = img.getHeight();
/*  74 */           System.out.println("    # " + i + ": size=" + width + "x" + height + "; colour depth=" + bpp + " bpp");
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  80 */         System.out.println("  saving separate images:");
/*     */         
/*  82 */         String format = "png";
/*     */         
/*  84 */         for (int j = 0; j < images.size(); j++) {
/*  85 */           BufferedImage img = images.get(j);
/*  86 */           String name = strOutFile + "-" + j;
/*  87 */           File bmpFile = new File(name + ".bmp");
/*  88 */           File pngFile = new File(name + ".png");
/*     */ 
/*     */           
/*  91 */           System.out.println("    writing '" + name + ".bmp'");
/*  92 */           BMPEncoder.write(img, bmpFile);
/*     */ 
/*     */           
/*  95 */           System.out.println("    writing '" + name + ".png'");
/*  96 */           ImageIO.write(img, format, pngFile);
/*     */         } 
/*     */         
/*  99 */         System.out.println("BMP encoding...OK");
/*     */ 
/*     */ 
/*     */         
/* 103 */         System.out.println("  reloading BMP files:");
/*     */ 
/*     */         
/* 106 */         List<BufferedImage> images2 = new ArrayList<>(images.size());
/*     */         
/* 108 */         for (int k = 0; k < images.size(); k++) {
/* 109 */           String name = strOutFile + "-" + k + ".bmp";
/* 110 */           File file = new File(name);
/*     */ 
/*     */           
/* 113 */           System.out.println("    reading '" + name + "'");
/*     */           
/* 115 */           BufferedImage image = BMPDecoder.read(file);
/* 116 */           images2.add(image);
/*     */         } 
/*     */         
/* 119 */         System.out.println("BMP decoding...OK");
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 125 */       System.out.println("Encoding ICO file '" + strOutFile + "'.");
/*     */       
/* 127 */       File outFile = new File(strOutFile);
/*     */       
/* 129 */       ICOEncoder.write(images, outFile);
/*     */       
/* 131 */       System.out.println("ICO encoding...OK");
/*     */     }
/* 133 */     catch (IOException ex) {
/* 134 */       ex.printStackTrace();
/*     */     } finally {
/*     */       try {
/* 137 */         in.close();
/* 138 */       } catch (IOException iOException) {}
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void usage() {}
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\net\sf\image4j\test\Test.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
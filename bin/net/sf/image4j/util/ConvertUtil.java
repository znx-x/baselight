/*     */ package net.sf.image4j.util;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorConvertOp;
/*     */ import java.awt.image.IndexColorModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConvertUtil
/*     */ {
/*     */   public static BufferedImage convert1(BufferedImage src) {
/*  31 */     IndexColorModel icm = new IndexColorModel(1, 2, new byte[] { 0, -1 }, new byte[] { 0, -1 }, new byte[] { 0, -1 });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  38 */     BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), 12, icm);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  45 */     ColorConvertOp cco = new ColorConvertOp(src.getColorModel().getColorSpace(), dest.getColorModel().getColorSpace(), null);
/*     */ 
/*     */ 
/*     */     
/*  49 */     cco.filter(src, dest);
/*     */     
/*  51 */     return dest;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage convert4(BufferedImage src) {
/*  69 */     int[] cmap = { 0, 8388608, 32768, 8421376, 128, 8388736, 32896, 8421504, 12632256, 16711680, 65280, 16776960, 255, 16711935, 65535, 16777215 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  75 */     return convert4(src, cmap);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage convert4(BufferedImage src, int[] cmap) {
/*  87 */     IndexColorModel icm = new IndexColorModel(4, cmap.length, cmap, 0, false, 1, 0);
/*     */ 
/*     */ 
/*     */     
/*  91 */     BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), 12, icm);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  97 */     ColorConvertOp cco = new ColorConvertOp(src.getColorModel().getColorSpace(), dest.getColorModel().getColorSpace(), null);
/*     */ 
/*     */     
/* 100 */     cco.filter(src, dest);
/*     */     
/* 102 */     return dest;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage convert8(BufferedImage src) {
/* 113 */     BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), 13);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 118 */     ColorConvertOp cco = new ColorConvertOp(src.getColorModel().getColorSpace(), dest.getColorModel().getColorSpace(), null);
/*     */ 
/*     */     
/* 121 */     cco.filter(src, dest);
/* 122 */     return dest;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage convert24(BufferedImage src) {
/* 132 */     BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), 1);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 137 */     ColorConvertOp cco = new ColorConvertOp(src.getColorModel().getColorSpace(), dest.getColorModel().getColorSpace(), null);
/*     */ 
/*     */     
/* 140 */     cco.filter(src, dest);
/* 141 */     return dest;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage convert32(BufferedImage src) {
/* 151 */     BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), 2);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 156 */     ColorConvertOp cco = new ColorConvertOp(src.getColorModel().getColorSpace(), dest.getColorModel().getColorSpace(), null);
/*     */ 
/*     */     
/* 159 */     cco.filter(src, dest);
/* 160 */     return dest;
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\net\sf\image4\\util\ConvertUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
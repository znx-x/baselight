/*     */ package com.pnuema.java.barcode;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.BufferedImage;
/*     */ 
/*     */ class Labels {
/*     */   public enum LabelPositions {
/*  10 */     TOP, BOTTOM;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Image Label_ITF14(Barcode barcode, BufferedImage img) {
/*     */     try {
/*  21 */       Font font = barcode.getLabelFont();
/*     */       
/*  23 */       Graphics2D g = img.createGraphics();
/*  24 */       g.drawImage(img, 0, 0, (ImageObserver)null);
/*     */       
/*  26 */       RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
/*     */ 
/*     */       
/*  29 */       g.setRenderingHints(rh);
/*     */       
/*  31 */       g.setColor(barcode.getBackColor());
/*     */       
/*  33 */       Rectangle rect = new Rectangle(0, img.getHeight() - font.getSize() - 2, img.getWidth(), font.getSize());
/*  34 */       g.drawRect((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight());
/*     */ 
/*     */       
/*  37 */       g.setColor(barcode.getForeColor());
/*  38 */       String text = (barcode.getAlternateLabel() == null) ? barcode.getRawData() : barcode.getAlternateLabel();
/*  39 */       drawCenteredString(g, text, rect, font);
/*     */       
/*  41 */       float lineThickness = img.getHeight() / 16.0F;
/*  42 */       g.drawRect(0, img.getHeight() - font.getSize() - 2 - (int)lineThickness / 2, img.getWidth(), img.getHeight() - font.getSize() - 2 + (int)lineThickness / 2);
/*     */       
/*  44 */       g.dispose();
/*     */       
/*  46 */       return img;
/*     */     }
/*  48 */     catch (Exception ex) {
/*  49 */       throw new RuntimeException("ELABEL_ITF14-1: " + ex.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Image labelGeneric(Barcode barcode, BufferedImage img) {
/*     */     try {
/*  61 */       Font font = barcode.getLabelFont();
/*     */       
/*  63 */       Graphics2D g = img.createGraphics();
/*  64 */       g.drawImage(img, 0, 0, (ImageObserver)null);
/*     */       
/*  66 */       RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
/*     */ 
/*     */       
/*  69 */       g.setRenderingHints(rh);
/*     */       
/*  71 */       int LabelY = 0;
/*     */ 
/*     */       
/*  74 */       switch (barcode.getLabelPosition()) {
/*     */         case LEFT:
/*  76 */           LabelY = img.getHeight() - font.getSize();
/*     */           break;
/*     */       } 
/*     */ 
/*     */       
/*  81 */       g.setColor(barcode.getBackColor());
/*  82 */       g.drawRect(0, LabelY, img.getWidth(), font.getSize());
/*     */ 
/*     */       
/*  85 */       g.setColor(barcode.getForeColor());
/*  86 */       Rectangle rect = new Rectangle(0, LabelY, img.getWidth(), font.getSize());
/*  87 */       drawCenteredString(g, (barcode.getAlternateLabel() == null) ? barcode.getRawData() : barcode.getAlternateLabel(), rect, font);
/*     */       
/*  89 */       g.dispose();
/*  90 */       return img;
/*  91 */     } catch (Exception ex) {
/*  92 */       throw new RuntimeException("ELABEL_GENERIC-1: " + ex.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Image Label_EAN13(Barcode barcode, BufferedImage img) {
/*     */     try {
/* 104 */       int iBarWidth = barcode.getWidth() / barcode.getEncodedValue().length();
/* 105 */       String defTxt = barcode.getRawData();
/*     */       
/* 107 */       int fontSize = getFontsize(barcode.getWidth() - barcode.getWidth() % barcode.getEncodedValue().length(), img.getHeight(), defTxt);
/* 108 */       Font labFont = new Font("Serif", 0, fontSize);
/* 109 */       Font smallFont = new Font(labFont.getFamily(), labFont.getStyle(), (int)(fontSize * 0.5F));
/*     */       
/* 111 */       int shiftAdjustment = getShiftAdjustment(barcode);
/*     */       
/* 113 */       Graphics2D g = img.createGraphics();
/* 114 */       g.drawImage(img, 0, 0, (ImageObserver)null);
/*     */       
/* 116 */       RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
/*     */ 
/*     */       
/* 119 */       g.setRenderingHints(rh);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 124 */       int LabelY = img.getHeight() - labFont.getSize();
/*     */       
/* 126 */       float w1 = (iBarWidth * 4);
/* 127 */       float w2 = (iBarWidth * 42);
/* 128 */       float w3 = (iBarWidth * 42);
/*     */       
/* 130 */       float s1 = (shiftAdjustment - iBarWidth);
/* 131 */       float s2 = s1 + (iBarWidth * 4);
/* 132 */       float s3 = s2 + w2 + (iBarWidth * 5);
/*     */ 
/*     */       
/* 135 */       g.setColor(barcode.getBackColor());
/* 136 */       g.drawRect((int)s2, LabelY, (int)w2, labFont.getSize());
/* 137 */       g.drawRect((int)s3, LabelY, (int)w3, labFont.getSize());
/*     */ 
/*     */       
/* 140 */       g.setColor(barcode.getForeColor());
/* 141 */       g.drawString(defTxt.substring(0, 1), s1, img.getHeight() - (float)(smallFont.getSize() * 0.9D));
/* 142 */       g.drawString(defTxt.substring(1, 6), s2, LabelY);
/* 143 */       g.drawString(defTxt.substring(7), s3 - iBarWidth, LabelY);
/*     */       
/* 145 */       g.dispose();
/* 146 */       return img;
/* 147 */     } catch (Exception ex) {
/* 148 */       throw new IllegalArgumentException("ELABEL_EAN13-1: " + ex.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Image Label_UPCA(Barcode barcode, BufferedImage img) {
/*     */     try {
/* 160 */       int iBarWidth = barcode.getWidth() / barcode.getEncodedValue().length();
/* 161 */       int halfBarWidth = (int)(iBarWidth * 0.5D);
/* 162 */       String defTxt = barcode.getRawData();
/*     */       
/* 164 */       int fontSize = getFontsize((int)((barcode.getWidth() - barcode.getWidth() % barcode.getEncodedValue().length()) * 0.9F), img.getHeight(), defTxt);
/* 165 */       Font labFont = new Font("Serif", 0, fontSize);
/* 166 */       Font smallFont = new Font(labFont.getFamily(), labFont.getStyle(), (int)(fontSize * 0.5F));
/*     */       
/* 168 */       int shiftAdjustment = getShiftAdjustment(barcode);
/*     */       
/* 170 */       Graphics2D g = img.createGraphics();
/* 171 */       g.drawImage(img, 0, 0, (ImageObserver)null);
/*     */       
/* 173 */       RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
/*     */ 
/*     */       
/* 176 */       g.setRenderingHints(rh);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 181 */       int LabelY = img.getHeight() - labFont.getSize();
/*     */       
/* 183 */       float w1 = (iBarWidth * 4);
/* 184 */       float w2 = (iBarWidth * 34);
/* 185 */       float w3 = (iBarWidth * 34);
/*     */       
/* 187 */       float s1 = (shiftAdjustment - iBarWidth);
/* 188 */       float s2 = s1 + (iBarWidth * 12);
/* 189 */       float s3 = s2 + w2 + (iBarWidth * 5);
/* 190 */       float s4 = s3 + w3 + (iBarWidth * 8) - halfBarWidth;
/*     */ 
/*     */       
/* 193 */       g.setColor(barcode.getBackColor());
/* 194 */       g.drawRect((int)s2, LabelY, (int)w2, labFont.getSize());
/* 195 */       g.drawRect((int)s3, LabelY, (int)w3, labFont.getSize());
/*     */ 
/*     */       
/* 198 */       g.setColor(barcode.getForeColor());
/* 199 */       g.drawString(defTxt.substring(0, 1), s1, img.getHeight() - smallFont.getSize());
/* 200 */       g.drawString(defTxt.substring(1, 5), s2 - iBarWidth, LabelY);
/* 201 */       g.drawString(defTxt.substring(6, 11), s3 - iBarWidth, LabelY);
/* 202 */       g.drawString(defTxt.substring(11), s4, (img.getHeight() - smallFont.getSize()));
/*     */       
/* 204 */       g.dispose();
/* 205 */       return img;
/* 206 */     } catch (Exception ex) {
/* 207 */       throw new RuntimeException("ELABEL_UPCA-1: " + ex.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getFontsize(int wid, int hgt, String lbl) {
/* 213 */     int fontSize = 10;
/*     */     
/* 215 */     if (lbl.length() > 0) {
/* 216 */       BufferedImage fakeImage = new BufferedImage(1, 1, 2);
/*     */       
/* 218 */       Graphics g = fakeImage.createGraphics();
/*     */       
/* 220 */       for (int i = 1; i <= 100; i++) {
/*     */ 
/*     */ 
/*     */         
/* 224 */         int text_size = g.getFontMetrics().stringWidth(lbl);
/* 225 */         if (text_size > wid || text_size > hgt) {
/* 226 */           fontSize = i - 1;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 231 */       g.dispose();
/*     */     } 
/*     */     
/* 234 */     return fontSize;
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
/*     */   public static void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
/* 246 */     FontMetrics metrics = g.getFontMetrics(font);
/*     */     
/* 248 */     int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
/*     */     
/* 250 */     int y = rect.y + (rect.height - metrics.getHeight()) / 2 + metrics.getAscent();
/*     */     
/* 252 */     g.setFont(font);
/*     */     
/* 254 */     g.drawString(text, x, y);
/*     */   }
/*     */   
/*     */   private static int getShiftAdjustment(Barcode barcode) {
/* 258 */     switch (barcode.getAlignmentPosition()) {
/*     */       case LEFT:
/* 260 */         return 0;
/*     */       case RIGHT:
/* 262 */         return barcode.getWidth() % barcode.getEncodedValue().length();
/*     */     } 
/*     */     
/* 265 */     return barcode.getWidth() % barcode.getEncodedValue().length() / 2;
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\com\pnuema\java\barcode\Labels.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
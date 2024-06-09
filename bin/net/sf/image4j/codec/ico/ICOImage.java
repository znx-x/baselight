/*     */ package net.sf.image4j.codec.ico;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import net.sf.image4j.codec.bmp.BMPImage;
/*     */ import net.sf.image4j.codec.bmp.InfoHeader;
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
/*     */ public class ICOImage
/*     */   extends BMPImage
/*     */ {
/*     */   protected IconEntry iconEntry;
/*     */   protected boolean pngCompressed = false;
/*  23 */   protected int iconIndex = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ICOImage(BufferedImage image, InfoHeader infoHeader, IconEntry iconEntry) {
/*  33 */     super(image, infoHeader);
/*  34 */     this.iconEntry = iconEntry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IconEntry getIconEntry() {
/*  43 */     return this.iconEntry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIconEntry(IconEntry iconEntry) {
/*  51 */     this.iconEntry = iconEntry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPngCompressed() {
/*  59 */     return this.pngCompressed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPngCompressed(boolean pngCompressed) {
/*  67 */     this.pngCompressed = pngCompressed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InfoHeader getInfoHeader() {
/*  75 */     return super.getInfoHeader();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIconIndex() {
/*  83 */     return this.iconIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIconIndex(int iconIndex) {
/*  91 */     this.iconIndex = iconIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 100 */     return (this.iconEntry == null) ? -1 : ((this.iconEntry.bWidth == 0) ? 256 : this.iconEntry.bWidth);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 109 */     return (this.iconEntry == null) ? -1 : ((this.iconEntry.bHeight == 0) ? 256 : this.iconEntry.bHeight);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getColourDepth() {
/* 118 */     return (this.iconEntry == null) ? -1 : this.iconEntry.sBitCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getColourCount() {
/* 127 */     int bpp = (this.iconEntry.sBitCount == 32) ? 24 : this.iconEntry.sBitCount;
/* 128 */     return (bpp == -1) ? -1 : (1 << bpp);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIndexed() {
/* 138 */     return (this.iconEntry == null) ? false : ((this.iconEntry.sBitCount <= 8));
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\net\sf\image4j\codec\ico\ICOImage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
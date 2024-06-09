/*     */ package net.sf.image4j.codec.bmp;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
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
/*     */ public class BMPImage
/*     */ {
/*     */   protected InfoHeader infoHeader;
/*     */   protected BufferedImage image;
/*     */   
/*     */   public BMPImage(BufferedImage image, InfoHeader infoHeader) {
/*  28 */     this.image = image;
/*  29 */     this.infoHeader = infoHeader;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InfoHeader getInfoHeader() {
/*  36 */     return this.infoHeader;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInfoHeader(InfoHeader infoHeader) {
/*  43 */     this.infoHeader = infoHeader;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getImage() {
/*  50 */     return this.image;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setImage(BufferedImage image) {
/*  57 */     this.image = image;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWidth() {
/*  66 */     return (this.infoHeader == null) ? -1 : this.infoHeader.iWidth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeight() {
/*  75 */     return (this.infoHeader == null) ? -1 : this.infoHeader.iHeight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getColourDepth() {
/*  84 */     return (this.infoHeader == null) ? -1 : this.infoHeader.sBitCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getColourCount() {
/*  93 */     int bpp = (this.infoHeader.sBitCount == 32) ? 24 : this.infoHeader.sBitCount;
/*  94 */     return (bpp == -1) ? -1 : (1 << bpp);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIndexed() {
/* 104 */     return (this.infoHeader == null) ? false : ((this.infoHeader.sBitCount <= 8));
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\net\sf\image4j\codec\bmp\BMPImage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
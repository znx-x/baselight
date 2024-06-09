/*    */ package examples;
/*    */ 
/*    */ import java.awt.Canvas;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Image;
/*    */ 
/*    */ 
/*    */ public abstract class DoubleBuffer
/*    */   extends Canvas
/*    */ {
/*    */   private int bufferWidth;
/*    */   private int bufferHeight;
/*    */   private Image bufferImage;
/*    */   private Graphics bufferGraphics;
/*    */   
/*    */   public void paint(Graphics g) {
/* 17 */     if (this.bufferWidth != (getSize()).width || this.bufferHeight != 
/* 18 */       (getSize()).height || this.bufferImage == null || this.bufferGraphics == null)
/*    */     {
/* 20 */       resetBuffer();
/*    */     }
/* 22 */     if (this.bufferGraphics != null) {
/* 23 */       this.bufferGraphics.clearRect(0, 0, this.bufferWidth, this.bufferHeight);
/*    */       
/* 25 */       paintBuffer(this.bufferGraphics);
/*    */       
/* 27 */       g.drawImage(this.bufferImage, 0, 0, this);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public abstract void paintBuffer(Graphics paramGraphics);
/*    */   
/*    */   private void resetBuffer() {
/* 35 */     this.bufferWidth = (getSize()).width;
/* 36 */     this.bufferHeight = (getSize()).height;
/*    */ 
/*    */     
/* 39 */     if (this.bufferGraphics != null) {
/* 40 */       this.bufferGraphics.dispose();
/* 41 */       this.bufferGraphics = null;
/*    */     } 
/* 43 */     if (this.bufferImage != null) {
/* 44 */       this.bufferImage.flush();
/* 45 */       this.bufferImage = null;
/*    */     } 
/* 47 */     System.gc();
/*    */ 
/*    */     
/* 50 */     this.bufferImage = createImage(this.bufferWidth, this.bufferHeight);
/* 51 */     this.bufferGraphics = this.bufferImage.getGraphics();
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\examples\DoubleBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
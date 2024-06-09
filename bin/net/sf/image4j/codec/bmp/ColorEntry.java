/*    */ package net.sf.image4j.codec.bmp;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.sf.image4j.io.LittleEndianInputStream;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ColorEntry
/*    */ {
/*    */   public int bRed;
/*    */   public int bGreen;
/*    */   public int bBlue;
/*    */   public int bReserved;
/*    */   
/*    */   public ColorEntry(LittleEndianInputStream in) throws IOException {
/* 43 */     this.bBlue = in.readUnsignedByte();
/* 44 */     this.bGreen = in.readUnsignedByte();
/* 45 */     this.bRed = in.readUnsignedByte();
/* 46 */     this.bReserved = in.readUnsignedByte();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ColorEntry() {
/* 53 */     this.bBlue = 0;
/* 54 */     this.bGreen = 0;
/* 55 */     this.bRed = 0;
/* 56 */     this.bReserved = 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ColorEntry(int r, int g, int b, int a) {
/* 67 */     this.bBlue = b;
/* 68 */     this.bGreen = g;
/* 69 */     this.bRed = r;
/* 70 */     this.bReserved = a;
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\net\sf\image4j\codec\bmp\ColorEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
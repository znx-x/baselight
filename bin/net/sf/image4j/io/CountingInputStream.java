/*    */ package net.sf.image4j.io;
/*    */ 
/*    */ import java.io.FilterInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ public class CountingInputStream
/*    */   extends FilterInputStream {
/*    */   public CountingInputStream(InputStream src) {
/* 10 */     super(src);
/*    */   }
/*    */   private int count;
/*    */   public int getCount() {
/* 14 */     return this.count;
/*    */   }
/*    */ 
/*    */   
/*    */   public int read() throws IOException {
/* 19 */     int b = super.read();
/* 20 */     if (b != -1) {
/* 21 */       this.count++;
/*    */     }
/* 23 */     return b;
/*    */   }
/*    */ 
/*    */   
/*    */   public int read(byte[] b, int off, int len) throws IOException {
/* 28 */     int r = super.read(b, off, len);
/* 29 */     if (r > 0) {
/* 30 */       this.count += r;
/*    */     }
/* 32 */     return r;
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\net\sf\image4j\io\CountingInputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
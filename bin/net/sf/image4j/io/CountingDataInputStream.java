/*    */ package net.sf.image4j.io;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ public class CountingDataInputStream extends DataInputStream implements CountingDataInput {
/*    */   public CountingDataInputStream(InputStream in) {
/*  8 */     super(new CountingInputStream(in));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCount() {
/* 13 */     return ((CountingInputStream)this.in).getCount();
/*    */   }
/*    */   
/*    */   public int skip(int count, boolean strict) throws IOException {
/* 17 */     return IOUtils.skip(this, count, strict);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 22 */     return getClass().getSimpleName() + "(" + this.in + ") [" + getCount() + "]";
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\net\sf\image4j\io\CountingDataInputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
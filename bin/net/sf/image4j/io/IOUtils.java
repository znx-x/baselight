/*    */ package net.sf.image4j.io;
/*    */ import java.io.EOFException;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ public class IOUtils {
/*    */   public static int skip(InputStream in, int count, boolean strict) throws IOException {
/*  8 */     int skipped = 0;
/*  9 */     while (skipped < count) {
/* 10 */       int b = in.read();
/* 11 */       if (b == -1) {
/*    */         break;
/*    */       }
/* 14 */       skipped++;
/*    */     } 
/* 16 */     if (skipped < count && strict) {
/* 17 */       throw new EOFException("Failed to skip " + count + " bytes in input");
/*    */     }
/*    */     
/* 20 */     return skipped;
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\net\sf\image4j\io\IOUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
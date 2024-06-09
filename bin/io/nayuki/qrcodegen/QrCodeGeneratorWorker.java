/*    */ package io.nayuki.qrcodegen;
/*    */ 
/*    */ import java.nio.charset.StandardCharsets;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.Scanner;
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
/*    */ 
/*    */ public final class QrCodeGeneratorWorker
/*    */ {
/*    */   public static void main(String[] args) {
/* 41 */     Scanner input = new Scanner(System.in, "US-ASCII"); 
/* 42 */     try { input.useDelimiter("\r\n|\n|\r");
/* 43 */       while (processCase(input));
/* 44 */       input.close(); }
/*    */     catch (Throwable throwable) { try {
/*    */         input.close();
/*    */       } catch (Throwable throwable1) {
/*    */         throwable.addSuppressed(throwable1);
/*    */       }  throw throwable; }
/* 50 */      } private static boolean processCase(Scanner input) { int j; List<QrSegment> segs; int length = input.nextInt();
/* 51 */     if (length == -1)
/* 52 */       return false; 
/* 53 */     if (length > 32767) {
/* 54 */       throw new RuntimeException();
/*    */     }
/*    */     
/* 57 */     boolean isAscii = true;
/* 58 */     byte[] data = new byte[length];
/* 59 */     for (int i = 0; i < data.length; i++) {
/* 60 */       int b = input.nextInt();
/* 61 */       if (b < 0 || b > 255)
/* 62 */         throw new RuntimeException(); 
/* 63 */       data[i] = (byte)b;
/* 64 */       j = isAscii & ((b < 128) ? 1 : 0);
/*    */     } 
/*    */ 
/*    */     
/* 68 */     int errCorLvl = input.nextInt();
/* 69 */     int minVersion = input.nextInt();
/* 70 */     int maxVersion = input.nextInt();
/* 71 */     int mask = input.nextInt();
/* 72 */     int boostEcl = input.nextInt();
/* 73 */     if (0 > errCorLvl || errCorLvl > 3 || -1 > mask || mask > 7 || boostEcl >>> 1 != 0 || 1 > minVersion || minVersion > maxVersion || maxVersion > 40)
/*    */     {
/* 75 */       throw new RuntimeException();
/*    */     }
/*    */ 
/*    */     
/* 79 */     if (j != 0) {
/* 80 */       segs = QrSegment.makeSegments(new String(data, StandardCharsets.US_ASCII));
/*    */     } else {
/* 82 */       segs = Arrays.asList(new QrSegment[] { QrSegment.makeBytes(data) });
/*    */     } 
/*    */     try {
/* 85 */       QrCode qr = QrCode.encodeSegments(segs, QrCode.Ecc.values()[errCorLvl], minVersion, maxVersion, mask, (boostEcl != 0));
/*    */       
/* 87 */       System.out.println(qr.version);
/* 88 */       for (int y = 0; y < qr.size; y++) {
/* 89 */         for (int x = 0; x < qr.size; x++) {
/* 90 */           System.out.println(qr.getModule(x, y) ? 1 : 0);
/*    */         }
/*    */       } 
/* 93 */     } catch (DataTooLongException e) {
/* 94 */       System.out.println(-1);
/*    */     } 
/* 96 */     System.out.flush();
/* 97 */     return true; }
/*    */ 
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\io\nayuki\qrcodegen\QrCodeGeneratorWorker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
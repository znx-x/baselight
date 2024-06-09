/*    */ package com.pnuema.java.barcode.utils;
/*    */ 
/*    */ public final class Utils2of5 {
/*    */   public static int CalculateMod10CheckDigit(String data) {
/*  5 */     int sum = 0;
/*  6 */     boolean even = true;
/*  7 */     for (int i = data.length() - 1; i >= 0; i--) {
/*    */       
/*  9 */       sum += data.charAt(i) * (even ? 3 : 1);
/* 10 */       even = !even;
/*    */     } 
/*    */     
/* 13 */     return (10 - sum % 10) % 10;
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\com\pnuema\java\barcod\\utils\Utils2of5.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
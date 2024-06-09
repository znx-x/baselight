/*    */ package com.pnuema.java.barcode.symbologies;
/*    */ 
/*    */ import com.pnuema.java.barcode.BarcodeCommon;
/*    */ import com.pnuema.java.barcode.IBarcode;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Pharmacode
/*    */   extends BarcodeCommon
/*    */   implements IBarcode
/*    */ {
/*    */   public Pharmacode(String input) {
/* 16 */     setRawData(input);
/*    */     
/* 18 */     if (getRawData().length() > 6) {
/* 19 */       error("EPHARM-2: Data too long (invalid data input length).");
/*    */     }
/*    */ 
/*    */     
/* 23 */     if (!checkNumericOnly(getRawData())) {
/* 24 */       error("EPHARM-1: Numeric Data Only");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private String encodePharmacode() {
/* 34 */     if (!getErrors().isEmpty()) {
/* 35 */       return "";
/*    */     }
/*    */     
/* 38 */     int num = Integer.parseInt(getRawData());
/*    */     
/* 40 */     if (num < 3 || num > 131070) {
/* 41 */       error("EPHARM-4: Data contains invalid  characters (invalid numeric range).");
/*    */     }
/*    */     
/* 44 */     String thickBar = "111";
/* 45 */     String thinBar = "1";
/* 46 */     String gap = "00";
/* 47 */     StringBuilder result = new StringBuilder();
/*    */ 
/*    */     
/*    */     do {
/* 51 */       if ((num & 0x1) == 0) {
/*    */         
/* 53 */         result.insert(0, thickBar);
/* 54 */         num = (num - 2) / 2;
/*    */       }
/*    */       else {
/*    */         
/* 58 */         result.insert(0, thinBar);
/* 59 */         num = (num - 1) / 2;
/*    */       } 
/*    */       
/* 62 */       if (num == 0)
/*    */         continue; 
/* 64 */       result.insert(0, gap);
/*    */     }
/* 66 */     while (num != 0);
/*    */     
/* 68 */     return result.toString();
/*    */   }
/*    */   
/*    */   public String getEncodedValue() {
/* 72 */     return encodePharmacode();
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\com\pnuema\java\barcode\symbologies\Pharmacode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
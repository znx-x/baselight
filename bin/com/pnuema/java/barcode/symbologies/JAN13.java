/*    */ package com.pnuema.java.barcode.symbologies;
/*    */ 
/*    */ import com.pnuema.java.barcode.BarcodeCommon;
/*    */ import com.pnuema.java.barcode.IBarcode;
/*    */ 
/*    */ public class JAN13
/*    */   extends BarcodeCommon
/*    */   implements IBarcode
/*    */ {
/*    */   public JAN13(String input) {
/* 11 */     setRawData(input);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private String encodeJAN13() {
/* 20 */     if (!getRawData().startsWith("49")) {
/* 21 */       error("EJAN13-1: Invalid Country Code for JAN13 (49 required)");
/*    */     }
/*    */ 
/*    */     
/* 25 */     if (!checkNumericOnly(getRawData())) {
/* 26 */       error("EJAN13-2: Numeric Data Only");
/*    */     }
/*    */     
/* 29 */     return (new EAN13(getRawData())).getEncodedValue();
/*    */   }
/*    */   
/*    */   public String getEncodedValue() {
/* 33 */     return encodeJAN13();
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\com\pnuema\java\barcode\symbologies\JAN13.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
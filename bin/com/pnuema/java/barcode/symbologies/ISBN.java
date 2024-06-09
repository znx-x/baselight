/*    */ package com.pnuema.java.barcode.symbologies;
/*    */ 
/*    */ import com.pnuema.java.barcode.BarcodeCommon;
/*    */ import com.pnuema.java.barcode.IBarcode;
/*    */ 
/*    */ public class ISBN
/*    */   extends BarcodeCommon
/*    */   implements IBarcode
/*    */ {
/*    */   public ISBN(String input) {
/* 11 */     setRawData(input);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private String encodeISBNBookland() {
/* 20 */     if (!checkNumericOnly(getRawData())) {
/* 21 */       error("EBOOKLANDISBN-1: Numeric Data Only");
/*    */     }
/*    */     
/* 24 */     String type = "UNKNOWN";
/* 25 */     if (getRawData().length() == 10 || getRawData().length() == 9) {
/* 26 */       if (getRawData().length() == 10) {
/* 27 */         setRawData(getRawData().substring(0, 9));
/*    */       }
/* 29 */       setRawData("978" + getRawData());
/* 30 */       type = "ISBN";
/* 31 */     } else if (getRawData().length() == 12 && getRawData().startsWith("978")) {
/* 32 */       type = "BOOKLAND-NOCHECKDIGIT";
/* 33 */     } else if (getRawData().length() == 13 && getRawData().startsWith("978")) {
/* 34 */       type = "BOOKLAND-CHECKDIGIT";
/* 35 */       setRawData(getRawData().substring(0, 12));
/*    */     } 
/*    */ 
/*    */     
/* 39 */     if ("UNKNOWN".equals(type)) {
/* 40 */       error("EBOOKLANDISBN-2: Invalid input.  Must start with 978 and be length must be 9, 10, 12, 13 characters.");
/*    */     }
/*    */     
/* 43 */     return (new EAN13(getRawData())).getEncodedValue();
/*    */   }
/*    */   
/*    */   public String getEncodedValue() {
/* 47 */     return encodeISBNBookland();
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\com\pnuema\java\barcode\symbologies\ISBN.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
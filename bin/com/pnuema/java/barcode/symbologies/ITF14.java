/*    */ package com.pnuema.java.barcode.symbologies;
/*    */ 
/*    */ import com.pnuema.java.barcode.BarcodeCommon;
/*    */ import com.pnuema.java.barcode.IBarcode;
/*    */ 
/*    */ public class ITF14
/*    */   extends BarcodeCommon
/*    */   implements IBarcode
/*    */ {
/* 10 */   private final String[] ITF14_Code = new String[] { "NNWWN", "WNNNW", "NWNNW", "WWNNN", "NNWNW", "WNWNN", "NWWNN", "NNNWW", "WNNWN", "NWNWN" };
/*    */   
/*    */   public ITF14(String input) {
/* 13 */     setRawData(input);
/* 14 */     checkDigit();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private String encodeITF14() {
/* 24 */     if (getRawData().length() > 14 || getRawData().length() < 13) {
/* 25 */       error("EITF14-1: Data length invalid. (Length must be 13 or 14)");
/*    */     }
/*    */ 
/*    */     
/* 29 */     if (!checkNumericOnly(getRawData())) {
/* 30 */       error("EITF14-2: Numeric Data Only");
/*    */     }
/*    */     
/* 33 */     StringBuilder result = new StringBuilder("1010");
/*    */     
/* 35 */     for (int i = 0; i < getRawData().length(); i += 2) {
/* 36 */       boolean bars = true;
/* 37 */       String patternbars = this.ITF14_Code[Integer.parseInt(String.valueOf(getRawData().toCharArray()[i]))];
/* 38 */       String patternspaces = this.ITF14_Code[Integer.parseInt(String.valueOf(getRawData().toCharArray()[i + 1]))];
/* 39 */       StringBuilder patternmixed = new StringBuilder();
/*    */ 
/*    */       
/* 42 */       while (patternbars.length() > 0) {
/* 43 */         patternmixed.append(String.valueOf(patternbars.toCharArray()[0])).append(String.valueOf(patternspaces.toCharArray()[0]));
/* 44 */         patternbars = patternbars.substring(1);
/* 45 */         patternspaces = patternspaces.substring(1);
/*    */       } 
/*    */       
/* 48 */       for (char c1 : patternmixed.toString().toCharArray()) {
/* 49 */         if (bars) {
/* 50 */           if (c1 == 'N') {
/* 51 */             result.append("1");
/*    */           } else {
/* 53 */             result.append("11");
/*    */           }
/*    */         
/* 56 */         } else if (c1 == 'N') {
/* 57 */           result.append("0");
/*    */         } else {
/* 59 */           result.append("00");
/*    */         } 
/*    */         
/* 62 */         bars = !bars;
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 67 */     result.append("1101");
/*    */     
/* 69 */     return result.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   private void checkDigit() {
/* 74 */     if (getRawData().length() == 13) {
/* 75 */       int total = 0;
/*    */       
/* 77 */       for (int i = 0; i <= getRawData().length() - 1; i++) {
/* 78 */         int temp = Integer.parseInt(getRawData().substring(i, i + 1));
/* 79 */         total += temp * ((i == 0 || i % 2 == 0) ? 3 : 1);
/*    */       } 
/*    */       
/* 82 */       int cs = total % 10;
/* 83 */       cs = 10 - cs;
/* 84 */       if (cs == 10) {
/* 85 */         cs = 0;
/*    */       }
/*    */       
/* 88 */       setRawData(getRawData() + cs);
/*    */     } 
/*    */   }
/*    */   
/*    */   public String getEncodedValue() {
/* 93 */     return encodeITF14();
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\com\pnuema\java\barcode\symbologies\ITF14.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
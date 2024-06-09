/*    */ package com.pnuema.java.barcode.symbologies;
/*    */ 
/*    */ import com.pnuema.java.barcode.BarcodeCommon;
/*    */ import com.pnuema.java.barcode.IBarcode;
/*    */ 
/*    */ public class UPCSupplement2
/*    */   extends BarcodeCommon
/*    */   implements IBarcode
/*    */ {
/* 10 */   private final String[] EAN_CodeA = new String[] { "0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011", "0110111", "0001011" };
/* 11 */   private final String[] EAN_CodeB = new String[] { "0100111", "0110011", "0011011", "0100001", "0011101", "0111001", "0000101", "0010001", "0001001", "0010111" };
/* 12 */   private final String[] UPC_SUPP_2 = new String[] { "aa", "ab", "ba", "bb" };
/*    */   
/*    */   public UPCSupplement2(String input) {
/* 15 */     setRawData(input);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private String encodeUPCSupplemental2() {
/* 23 */     if (getRawData().length() != 2) {
/* 24 */       error("EUPC-SUP2-1: Invalid data length. (Length = 2 required)");
/*    */     }
/*    */ 
/*    */     
/* 28 */     if (!checkNumericOnly(getRawData())) {
/* 29 */       error("EUPC-SUP2-2: Numeric Data Only");
/*    */     }
/*    */     
/* 32 */     String pattern = "";
/*    */     
/*    */     try {
/* 35 */       pattern = this.UPC_SUPP_2[Integer.parseInt(getRawData().trim()) % 4];
/* 36 */     } catch (Exception ex) {
/* 37 */       error("EUPC-SUP2-3: Invalid Data. (Numeric only)");
/*    */     } 
/*    */     
/* 40 */     StringBuilder result = new StringBuilder("1011");
/*    */     
/* 42 */     int pos = 0;
/* 43 */     for (char c : pattern.toCharArray()) {
/* 44 */       if (c == 'a') {
/*    */         
/* 46 */         result.append(this.EAN_CodeA[Integer.parseInt(String.valueOf(getRawData().toCharArray()[pos]))]);
/* 47 */       } else if (c == 'b') {
/*    */         
/* 49 */         result.append(this.EAN_CodeB[Integer.parseInt(String.valueOf(getRawData().toCharArray()[pos]))]);
/*    */       } 
/*    */       
/* 52 */       if (pos++ == 0) {
/* 53 */         result.append("01");
/*    */       }
/*    */     } 
/* 56 */     return result.toString();
/*    */   }
/*    */   
/*    */   public String getEncodedValue() {
/* 60 */     return encodeUPCSupplemental2();
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\com\pnuema\java\barcode\symbologies\UPCSupplement2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
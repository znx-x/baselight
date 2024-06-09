/*    */ package com.pnuema.java.barcode.symbologies;
/*    */ 
/*    */ import com.pnuema.java.barcode.BarcodeCommon;
/*    */ import com.pnuema.java.barcode.IBarcode;
/*    */ 
/*    */ public class UPCSupplement5
/*    */   extends BarcodeCommon
/*    */   implements IBarcode
/*    */ {
/* 10 */   private final String[] EAN_CodeA = new String[] { "0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011", "0110111", "0001011" };
/* 11 */   private final String[] EAN_CodeB = new String[] { "0100111", "0110011", "0011011", "0100001", "0011101", "0111001", "0000101", "0010001", "0001001", "0010111" };
/* 12 */   private final String[] UPC_SUPP_5 = new String[] { "bbaaa", "babaa", "baaba", "baaab", "abbaa", "aabba", "aaabb", "ababa", "abaab", "aabab" };
/*    */   
/*    */   public UPCSupplement5(String input) {
/* 15 */     setRawData(input);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private String encodeUPCSupplemental5() {
/* 24 */     if (getRawData().length() != 5) {
/* 25 */       error("EUPC-SUP5-1: Invalid data length. (Length = 5 required)");
/*    */     }
/*    */ 
/*    */     
/* 29 */     if (!checkNumericOnly(getRawData())) {
/* 30 */       error("EUPC-SUP5-2: Numeric Data Only");
/*    */     }
/*    */ 
/*    */     
/* 34 */     int even = 0;
/* 35 */     int odd = 0;
/*    */     
/*    */     int i;
/* 38 */     for (i = 0; i <= 4; i += 2) {
/* 39 */       odd += Integer.parseInt(getRawData().substring(i, i + 1)) * 3;
/*    */     }
/*    */ 
/*    */     
/* 43 */     for (i = 1; i < 4; i += 2) {
/* 44 */       even += Integer.parseInt(getRawData().substring(i, i + 1)) * 9;
/*    */     }
/*    */     
/* 47 */     int total = even + odd;
/* 48 */     int cs = total % 10;
/*    */     
/* 50 */     String pattern = this.UPC_SUPP_5[cs];
/*    */     
/* 52 */     StringBuilder result = new StringBuilder();
/*    */     
/* 54 */     int pos = 0;
/* 55 */     for (char c : pattern.toCharArray()) {
/*    */       
/* 57 */       if (pos == 0) {
/* 58 */         result.append("1011");
/*    */       } else {
/* 60 */         result.append("01");
/*    */       } 
/*    */       
/* 63 */       if (c == 'a') {
/*    */         
/* 65 */         result.append(this.EAN_CodeA[Integer.parseInt(String.valueOf(getRawData().toCharArray()[pos]))]);
/* 66 */       } else if (c == 'b') {
/*    */         
/* 68 */         result.append(this.EAN_CodeB[Integer.parseInt(String.valueOf(getRawData().toCharArray()[pos]))]);
/*    */       } 
/* 70 */       pos++;
/*    */     } 
/* 72 */     return result.toString();
/*    */   }
/*    */   
/*    */   public String getEncodedValue() {
/* 76 */     return encodeUPCSupplemental5();
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\com\pnuema\java\barcode\symbologies\UPCSupplement5.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
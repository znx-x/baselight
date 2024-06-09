/*    */ package com.pnuema.java.barcode.symbologies;
/*    */ 
/*    */ import com.pnuema.java.barcode.BarcodeCommon;
/*    */ import com.pnuema.java.barcode.IBarcode;
/*    */ 
/*    */ public class EAN8
/*    */   extends BarcodeCommon
/*    */   implements IBarcode
/*    */ {
/* 10 */   private final String[] EAN_CodeA = new String[] { "0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011", "0110111", "0001011" };
/* 11 */   private final String[] EAN_CodeC = new String[] { "1110010", "1100110", "1101100", "1000010", "1011100", "1001110", "1010000", "1000100", "1001000", "1110100" };
/*    */   
/*    */   public EAN8(String input) {
/* 14 */     setRawData(input);
/* 15 */     calculateCheckDigit();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private String encodeEAN8() {
/* 24 */     if (getRawData().length() != 8 && getRawData().length() != 7) {
/* 25 */       error("EEAN8-1: Invalid data length. (7 or 8 numbers only)");
/*    */     }
/*    */ 
/*    */     
/* 29 */     if (!checkNumericOnly(getRawData())) {
/* 30 */       error("EEAN8-2: Numeric Data Only");
/*    */     }
/*    */ 
/*    */     
/* 34 */     StringBuilder result = new StringBuilder("101");
/*    */     
/*    */     int i;
/* 37 */     for (i = 0; i < getRawData().length() / 2; i++) {
/* 38 */       result.append(this.EAN_CodeA[Integer.parseInt(String.valueOf(getRawData().toCharArray()[i]))]);
/*    */     }
/*    */ 
/*    */     
/* 42 */     result.append("01010");
/*    */ 
/*    */     
/* 45 */     for (i = getRawData().length() / 2; i < getRawData().length(); i++) {
/* 46 */       result.append(this.EAN_CodeC[Integer.parseInt(String.valueOf(getRawData().toCharArray()[i]))]);
/*    */     }
/*    */     
/* 49 */     result.append("101");
/*    */     
/* 51 */     return result.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   private void calculateCheckDigit() {
/* 56 */     if (getRawData().length() == 7) {
/*    */       
/* 58 */       int even = 0;
/* 59 */       int odd = 0;
/*    */       
/*    */       int i;
/* 62 */       for (i = 0; i <= 6; i += 2) {
/* 63 */         odd += Integer.parseInt(getRawData().substring(i, i + 1)) * 3;
/*    */       }
/*    */ 
/*    */       
/* 67 */       for (i = 1; i <= 5; i += 2) {
/* 68 */         even += Integer.parseInt(getRawData().substring(i, i + 1));
/*    */       }
/*    */       
/* 71 */       int total = even + odd;
/* 72 */       int checksum = total % 10;
/* 73 */       checksum = 10 - checksum;
/* 74 */       if (checksum == 10) {
/* 75 */         checksum = 0;
/*    */       }
/*    */ 
/*    */       
/* 79 */       setRawData(getRawData() + checksum);
/*    */     } 
/*    */   }
/*    */   
/*    */   public String getEncodedValue() {
/* 84 */     return encodeEAN8();
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\com\pnuema\java\barcode\symbologies\EAN8.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
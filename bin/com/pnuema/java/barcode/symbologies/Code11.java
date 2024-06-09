/*    */ package com.pnuema.java.barcode.symbologies;
/*    */ 
/*    */ import com.pnuema.java.barcode.BarcodeCommon;
/*    */ import com.pnuema.java.barcode.IBarcode;
/*    */ 
/*    */ public class Code11
/*    */   extends BarcodeCommon
/*    */   implements IBarcode
/*    */ {
/* 10 */   private final String[] C11_Code = new String[] { "101011", "1101011", "1001011", "1100101", "1011011", "1101101", "1001101", "1010011", "1101001", "110101", "101101", "1011001" };
/*    */   
/*    */   public Code11(String input) {
/* 13 */     setRawData(input);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private String encodeCode11() {
/* 21 */     if (!checkNumericOnly(getRawData().replace("-", ""))) {
/* 22 */       error("EC11-1: Numeric data and '-' Only");
/*    */     }
/*    */ 
/*    */     
/* 26 */     int weight = 1;
/* 27 */     int CTotal = 0;
/* 28 */     String dataToEncodeWithChecksums = getRawData();
/*    */ 
/*    */     
/* 31 */     for (int i = getRawData().length() - 1; i >= 0; i--) {
/*    */       
/* 33 */       if (weight == 10) {
/* 34 */         weight = 1;
/*    */       }
/*    */       
/* 37 */       if (getRawData().charAt(i) != '-') {
/* 38 */         CTotal += Integer.parseInt(String.valueOf(getRawData().charAt(i))) * weight++;
/*    */       } else {
/* 40 */         CTotal += 10 * weight++;
/*    */       } 
/*    */     } 
/*    */     
/* 44 */     int checksumC = CTotal % 11;
/*    */     
/* 46 */     dataToEncodeWithChecksums = dataToEncodeWithChecksums + String.valueOf(checksumC);
/*    */ 
/*    */     
/* 49 */     if (getRawData().length() >= 10) {
/* 50 */       weight = 1;
/* 51 */       int KTotal = 0;
/*    */ 
/*    */       
/* 54 */       for (int j = dataToEncodeWithChecksums.length() - 1; j >= 0; j--) {
/*    */         
/* 56 */         if (weight == 9) {
/* 57 */           weight = 1;
/*    */         }
/*    */         
/* 60 */         if (dataToEncodeWithChecksums.charAt(j) != '-') {
/* 61 */           KTotal += Integer.parseInt(String.valueOf(dataToEncodeWithChecksums.charAt(j))) * weight++;
/*    */         } else {
/* 63 */           KTotal += 10 * weight++;
/*    */         } 
/*    */       } 
/*    */       
/* 67 */       int checksumK = KTotal % 9;
/* 68 */       dataToEncodeWithChecksums = dataToEncodeWithChecksums + checksumK;
/*    */     } 
/*    */ 
/*    */     
/* 72 */     String space = "0";
/* 73 */     StringBuilder builder = new StringBuilder();
/* 74 */     builder.append(this.C11_Code[11]);
/* 75 */     builder.append(space);
/*    */     
/* 77 */     for (char c : dataToEncodeWithChecksums.toCharArray()) {
/* 78 */       int index = (c == '-') ? 10 : Integer.parseInt(String.valueOf(c));
/* 79 */       builder.append(this.C11_Code[index]);
/*    */ 
/*    */       
/* 82 */       builder.append(space);
/*    */     } 
/*    */ 
/*    */     
/* 86 */     builder.append(this.C11_Code[11]);
/*    */     
/* 88 */     return builder.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getEncodedValue() {
/* 93 */     return encodeCode11();
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\com\pnuema\java\barcode\symbologies\Code11.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
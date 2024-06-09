/*     */ package com.pnuema.java.barcode.symbologies;
/*     */ 
/*     */ import com.pnuema.java.barcode.BarcodeCommon;
/*     */ import com.pnuema.java.barcode.IBarcode;
/*     */ 
/*     */ public class UPCE
/*     */   extends BarcodeCommon
/*     */   implements IBarcode
/*     */ {
/*  10 */   private final String[] EAN_CodeA = new String[] { "0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011", "0110111", "0001011" };
/*  11 */   private final String[] EAN_CodeB = new String[] { "0100111", "0110011", "0011011", "0100001", "0011101", "0111001", "0000101", "0010001", "0001001", "0010111" };
/*  12 */   private final String[] UPCE_Code_0 = new String[] { "bbbaaa", "bbabaa", "bbaaba", "bbaaab", "babbaa", "baabba", "baaabb", "bababa", "babaab", "baabab" };
/*  13 */   private final String[] UPCE_Code_1 = new String[] { "aaabbb", "aababb", "aabbab", "aabbba", "abaabb", "abbaab", "abbbaa", "ababab", "ababba", "abbaba" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UPCE(String input) {
/*  21 */     setRawData(input);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String encodeUPCE() {
/*     */     String pattern;
/*  30 */     if (getRawData().length() != 6 && getRawData().length() != 8 && getRawData().length() != 12) {
/*  31 */       error("EUPCE-1: Invalid data length. (8 or 12 numbers only)");
/*     */     }
/*     */ 
/*     */     
/*  35 */     if (!checkNumericOnly(getRawData())) {
/*  36 */       error("EUPCE-2: Numeric Data Only");
/*     */     }
/*     */ 
/*     */     
/*  40 */     int numberSystem = Integer.parseInt(String.valueOf(getRawData().toCharArray()[0]));
/*  41 */     if (numberSystem != 0 && numberSystem != 1) {
/*  42 */       error("EUPCE-3: Invalid Number System (only 0 & 1 are valid)");
/*     */     }
/*     */     
/*  45 */     int checkDigit = Integer.parseInt(String.valueOf(getRawData().toCharArray()[getRawData().length() - 1]));
/*     */ 
/*     */     
/*  48 */     if (getRawData().length() == 12) {
/*  49 */       String UPCECode = "";
/*     */ 
/*     */       
/*  52 */       String manufacturer = getRawData().substring(1, 6);
/*  53 */       String productCode = getRawData().substring(6, 11);
/*     */       
/*  55 */       if (manufacturer.endsWith("000") || manufacturer.endsWith("100") || (manufacturer.endsWith("200") && Integer.parseInt(productCode) <= 999)) {
/*     */         
/*  57 */         UPCECode = UPCECode + manufacturer.substring(0, 2);
/*  58 */         UPCECode = UPCECode + productCode.substring(2, 5);
/*  59 */         UPCECode = UPCECode + String.valueOf(manufacturer.toCharArray()[2]);
/*  60 */       } else if (manufacturer.endsWith("00") && Integer.parseInt(productCode) <= 99) {
/*     */         
/*  62 */         UPCECode = UPCECode + manufacturer.substring(0, 3);
/*  63 */         UPCECode = UPCECode + productCode.substring(3, 5);
/*  64 */         UPCECode = UPCECode + "3";
/*  65 */       } else if (manufacturer.endsWith("0") && Integer.parseInt(productCode) <= 9) {
/*     */         
/*  67 */         UPCECode = UPCECode + manufacturer.substring(0, 4);
/*  68 */         UPCECode = UPCECode + productCode.toCharArray()[4];
/*  69 */         UPCECode = UPCECode + "4";
/*  70 */       } else if (!manufacturer.endsWith("0") && Integer.parseInt(productCode) <= 9 && Integer.parseInt(productCode) >= 5) {
/*     */         
/*  72 */         UPCECode = UPCECode + manufacturer;
/*  73 */         UPCECode = UPCECode + productCode.toCharArray()[4];
/*     */       } else {
/*  75 */         error("EUPCE-4: Illegal UPC-A entered for conversion.  Unable to convert.");
/*     */       } 
/*     */       
/*  78 */       setRawData(UPCECode);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  84 */     if (numberSystem == 0) {
/*  85 */       pattern = this.UPCE_Code_0[checkDigit];
/*     */     } else {
/*  87 */       pattern = this.UPCE_Code_1[checkDigit];
/*     */     } 
/*     */ 
/*     */     
/*  91 */     StringBuilder result = new StringBuilder("101");
/*     */     
/*  93 */     int pos = 0;
/*  94 */     for (char c : pattern.toCharArray()) {
/*  95 */       int i = Integer.parseInt(String.valueOf(getRawData().toCharArray()[pos++]));
/*  96 */       if (c == 'a') {
/*  97 */         result.append(this.EAN_CodeA[i]);
/*  98 */       } else if (c == 'b') {
/*  99 */         result.append(this.EAN_CodeB[i]);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 104 */     result.append("01010");
/*     */ 
/*     */     
/* 107 */     result.append("1");
/*     */     
/* 109 */     return result.toString();
/*     */   }
/*     */   
/*     */   public String getEncodedValue() {
/* 113 */     return encodeUPCE();
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\com\pnuema\java\barcode\symbologies\UPCE.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
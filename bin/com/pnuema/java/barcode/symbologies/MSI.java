/*     */ package com.pnuema.java.barcode.symbologies;
/*     */ 
/*     */ import com.pnuema.java.barcode.Barcode;
/*     */ import com.pnuema.java.barcode.BarcodeCommon;
/*     */ import com.pnuema.java.barcode.IBarcode;
/*     */ 
/*     */ 
/*     */ public class MSI
/*     */   extends BarcodeCommon
/*     */   implements IBarcode
/*     */ {
/*  12 */   private final String[] MSI_Code = new String[] { "100100100100", "100100100110", "100100110100", "100100110110", "100110100100", "100110100110", "100110110100", "100110110110", "110100100100", "110100100110" };
/*     */   private final Barcode.TYPE encodedType;
/*     */   
/*     */   public MSI(String input, Barcode.TYPE EncodedType) {
/*  16 */     this.encodedType = EncodedType;
/*  17 */     setRawData(input);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String encodeMSI() {
/*  27 */     if (!checkNumericOnly(getRawData())) {
/*  28 */       error("EMSI-1: Numeric Data Only");
/*     */     }
/*     */     
/*  31 */     String PreEncoded = getRawData();
/*     */ 
/*     */     
/*  34 */     if (this.encodedType == Barcode.TYPE.MSI_Mod10 || this.encodedType == Barcode.TYPE.MSI_2Mod10) {
/*  35 */       String odds = "";
/*  36 */       String evens = "";
/*  37 */       for (int j = PreEncoded.length() - 1; j >= 0; j -= 2) {
/*  38 */         odds = String.valueOf(PreEncoded.toCharArray()[j]) + odds;
/*  39 */         if (j - 1 >= 0) {
/*  40 */           evens = String.valueOf(PreEncoded.toCharArray()[j - 1]) + evens;
/*     */         }
/*     */       } 
/*     */       
/*  44 */       odds = String.valueOf(Integer.parseInt(odds) * 2);
/*     */       
/*  46 */       int evensum = 0;
/*  47 */       int oddsum = 0; char[] arrayOfChar1; int k; byte b1;
/*  48 */       for (arrayOfChar1 = evens.toCharArray(), k = arrayOfChar1.length, b1 = 0; b1 < k; ) { Character c = Character.valueOf(arrayOfChar1[b1]);
/*  49 */         evensum += Integer.parseInt(c.toString()); b1++; }
/*     */       
/*  51 */       for (arrayOfChar1 = odds.toCharArray(), k = arrayOfChar1.length, b1 = 0; b1 < k; ) { Character c = Character.valueOf(arrayOfChar1[b1]);
/*  52 */         oddsum += Integer.parseInt(c.toString()); b1++; }
/*     */       
/*  54 */       int mod = (oddsum + evensum) % 10;
/*  55 */       int checksum = (mod == 0) ? 0 : (10 - mod);
/*  56 */       PreEncoded = PreEncoded + String.valueOf(checksum);
/*     */     } 
/*     */     
/*  59 */     if (this.encodedType == Barcode.TYPE.MSI_Mod11 || this.encodedType == Barcode.TYPE.MSI_Mod11_Mod10) {
/*  60 */       int sum = 0;
/*  61 */       int weight = 2;
/*  62 */       for (int j = PreEncoded.length() - 1; j >= 0; j--) {
/*  63 */         if (weight > 7) {
/*  64 */           weight = 2;
/*     */         }
/*  66 */         sum += Integer.parseInt(String.valueOf(PreEncoded.toCharArray()[j])) * weight++;
/*     */       } 
/*  68 */       int mod = sum % 11;
/*  69 */       int checksum = (mod == 0) ? 0 : (11 - mod);
/*     */       
/*  71 */       PreEncoded = PreEncoded + String.valueOf(checksum);
/*     */     } 
/*     */     
/*  74 */     if (this.encodedType == Barcode.TYPE.MSI_2Mod10 || this.encodedType == Barcode.TYPE.MSI_Mod11_Mod10) {
/*     */       
/*  76 */       String odds = "";
/*  77 */       String evens = "";
/*  78 */       for (int j = PreEncoded.length() - 1; j >= 0; j -= 2) {
/*  79 */         odds = String.valueOf(PreEncoded.toCharArray()[j]) + odds;
/*  80 */         if (j - 1 >= 0) {
/*  81 */           evens = String.valueOf(PreEncoded.toCharArray()[j - 1]) + evens;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  86 */       odds = String.valueOf(Integer.parseInt(odds) * 2);
/*     */       
/*  88 */       int evensum = 0;
/*  89 */       int oddsum = 0; char[] arrayOfChar1; int k; byte b1;
/*  90 */       for (arrayOfChar1 = evens.toCharArray(), k = arrayOfChar1.length, b1 = 0; b1 < k; ) { Character c = Character.valueOf(arrayOfChar1[b1]);
/*  91 */         evensum += Integer.parseInt(c.toString()); b1++; }
/*     */       
/*  93 */       for (arrayOfChar1 = odds.toCharArray(), k = arrayOfChar1.length, b1 = 0; b1 < k; ) { Character c = Character.valueOf(arrayOfChar1[b1]);
/*  94 */         oddsum += Integer.parseInt(c.toString()); b1++; }
/*     */       
/*  96 */       int checksum = 10 - (oddsum + evensum) % 10;
/*  97 */       PreEncoded = PreEncoded + String.valueOf(checksum);
/*     */     } 
/*     */     
/* 100 */     String result = "110"; char[] arrayOfChar; int i; byte b;
/* 101 */     for (arrayOfChar = PreEncoded.toCharArray(), i = arrayOfChar.length, b = 0; b < i; ) { Character c = Character.valueOf(arrayOfChar[b]);
/* 102 */       result = result + this.MSI_Code[Integer.parseInt(c.toString())];
/*     */       
/*     */       b++; }
/*     */     
/* 106 */     result = result + "1001";
/*     */     
/* 108 */     return result;
/*     */   }
/*     */   
/*     */   public String getEncodedValue() {
/* 112 */     return encodeMSI();
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\com\pnuema\java\barcode\symbologies\MSI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.pnuema.java.barcode.symbologies;
/*     */ 
/*     */ import com.pnuema.java.barcode.BarcodeCommon;
/*     */ import com.pnuema.java.barcode.IBarcode;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class Codabar
/*     */   extends BarcodeCommon
/*     */   implements IBarcode
/*     */ {
/*  12 */   private final HashMap<Character, String> codabarCode = new HashMap<>();
/*     */   
/*     */   public Codabar(String input) {
/*  15 */     setRawData(input);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String encodeCodabar() {
/*  23 */     if (getRawData().length() < 2) error("ECODABAR-1: Data format invalid. (Invalid length)");
/*     */ 
/*     */     
/*  26 */     switch (String.valueOf(getRawData().charAt(0)).toUpperCase().trim()) { case "A":
/*     */       case "B":
/*     */       case "C":
/*     */       case "D":
/*     */         break;
/*     */       default:
/*  32 */         error("ECODABAR-2: Data format invalid. (Invalid START character)");
/*     */         break; }
/*     */ 
/*     */ 
/*     */     
/*  37 */     switch (String.valueOf(getRawData().charAt(getRawData().trim().length() - 1)).trim().toUpperCase()) { case "A":
/*     */       case "B":
/*     */       case "C":
/*     */       case "D":
/*     */         break;
/*     */       default:
/*  43 */         error("ECODABAR-3: Data format invalid. (Invalid STOP character)");
/*     */         break; }
/*     */ 
/*     */ 
/*     */     
/*  48 */     initCodabar();
/*     */ 
/*     */     
/*  51 */     String temp = getRawData();
/*     */     
/*  53 */     for (Iterator<Character> iterator = this.codabarCode.keySet().iterator(); iterator.hasNext(); ) { char c = ((Character)iterator.next()).charValue();
/*  54 */       if (!checkNumericOnly(String.valueOf(c))) {
/*  55 */         temp = temp.replace(c, '1');
/*     */       } }
/*     */ 
/*     */ 
/*     */     
/*  60 */     if (!checkNumericOnly(temp)) {
/*  61 */       error("ECODABAR-4: Data contains invalid  characters.");
/*     */     }
/*     */     
/*  64 */     StringBuilder result = new StringBuilder();
/*  65 */     for (char c : getRawData().toCharArray()) {
/*  66 */       result.append(this.codabarCode.get(Character.valueOf(c)));
/*  67 */       result.append("0");
/*     */     } 
/*     */ 
/*     */     
/*  71 */     result.deleteCharAt(result.length() - 1);
/*     */ 
/*     */     
/*  74 */     this.codabarCode.clear();
/*     */ 
/*     */     
/*  77 */     setRawData(getRawData().trim().substring(1, getRawData().trim().length() - 2));
/*     */     
/*  79 */     return result.toString();
/*     */   }
/*     */   
/*     */   private void initCodabar() {
/*  83 */     this.codabarCode.clear();
/*  84 */     this.codabarCode.put(Character.valueOf('0'), "101010011");
/*  85 */     this.codabarCode.put(Character.valueOf('1'), "101011001");
/*  86 */     this.codabarCode.put(Character.valueOf('2'), "101001011");
/*  87 */     this.codabarCode.put(Character.valueOf('3'), "110010101");
/*  88 */     this.codabarCode.put(Character.valueOf('4'), "101101001");
/*  89 */     this.codabarCode.put(Character.valueOf('5'), "110101001");
/*  90 */     this.codabarCode.put(Character.valueOf('6'), "100101011");
/*  91 */     this.codabarCode.put(Character.valueOf('7'), "100101101");
/*  92 */     this.codabarCode.put(Character.valueOf('8'), "100110101");
/*  93 */     this.codabarCode.put(Character.valueOf('9'), "110100101");
/*  94 */     this.codabarCode.put(Character.valueOf('-'), "101001101");
/*  95 */     this.codabarCode.put(Character.valueOf('$'), "101100101");
/*  96 */     this.codabarCode.put(Character.valueOf(':'), "1101011011");
/*  97 */     this.codabarCode.put(Character.valueOf('/'), "1101101011");
/*  98 */     this.codabarCode.put(Character.valueOf('.'), "1101101101");
/*  99 */     this.codabarCode.put(Character.valueOf('+'), "101100110011");
/* 100 */     this.codabarCode.put(Character.valueOf('A'), "1011001001");
/* 101 */     this.codabarCode.put(Character.valueOf('B'), "1010010011");
/* 102 */     this.codabarCode.put(Character.valueOf('C'), "1001001011");
/* 103 */     this.codabarCode.put(Character.valueOf('D'), "1010011001");
/* 104 */     this.codabarCode.put(Character.valueOf('a'), "1011001001");
/* 105 */     this.codabarCode.put(Character.valueOf('b'), "1010010011");
/* 106 */     this.codabarCode.put(Character.valueOf('c'), "1001001011");
/* 107 */     this.codabarCode.put(Character.valueOf('d'), "1010011001");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEncodedValue() {
/* 112 */     return encodeCodabar();
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\com\pnuema\java\barcode\symbologies\Codabar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
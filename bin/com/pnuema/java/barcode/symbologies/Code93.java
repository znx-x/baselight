/*     */ package com.pnuema.java.barcode.symbologies;
/*     */ 
/*     */ import com.pnuema.java.barcode.BarcodeCommon;
/*     */ import com.pnuema.java.barcode.IBarcode;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ public class Code93
/*     */   extends BarcodeCommon
/*     */   implements IBarcode
/*     */ {
/*  14 */   private final List<Entry> C93_Code = new ArrayList<>();
/*     */   
/*     */   private static class Entry {
/*     */     private final String value;
/*     */     private final String character;
/*     */     private final String encoding;
/*     */     
/*     */     Entry(String value, String character, String encoding) {
/*  22 */       this.value = value;
/*  23 */       this.character = character;
/*  24 */       this.encoding = encoding;
/*     */     }
/*     */     
/*     */     String getValue() {
/*  28 */       return this.value;
/*     */     }
/*     */     
/*     */     String getCharacter() {
/*  32 */       return this.character;
/*     */     }
/*     */     
/*     */     String getEncoding() {
/*  36 */       return this.encoding;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Code93(String input) {
/*  45 */     setRawData(input);
/*     */   }
/*     */   
/*     */   private Entry findRowByValue(String match) {
/*  49 */     Entry returnValue = null;
/*  50 */     for (Entry entry : this.C93_Code) {
/*  51 */       if (entry.getValue().equals(match)) {
/*  52 */         returnValue = entry;
/*     */       }
/*     */     } 
/*     */     
/*  56 */     return Objects.<Entry>requireNonNull(returnValue);
/*     */   }
/*     */   
/*     */   private Entry findRowByCharacter(String match) {
/*  60 */     Entry returnValue = null;
/*  61 */     for (Entry entry : this.C93_Code) {
/*  62 */       if (entry.getCharacter().equals(match)) {
/*  63 */         returnValue = entry;
/*     */       }
/*     */     } 
/*     */     
/*  67 */     return Objects.<Entry>requireNonNull(returnValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String encodeCode93() {
/*  74 */     initCode93();
/*     */     
/*  76 */     String FormattedData = addCheckDigits(getRawData());
/*  77 */     StringBuilder result = new StringBuilder(findRowByCharacter("*").getEncoding());
/*  78 */     for (char c : FormattedData.toCharArray()) {
/*     */       try {
/*  80 */         result.append(findRowByCharacter(String.valueOf(c)).getEncoding());
/*  81 */       } catch (Exception ex) {
/*  82 */         error("EC93-1: Invalid data.");
/*     */       } 
/*     */     } 
/*     */     
/*  86 */     result.append(findRowByCharacter("*").getEncoding());
/*     */ 
/*     */     
/*  89 */     result.append("1");
/*     */ 
/*     */     
/*  92 */     this.C93_Code.clear();
/*     */     
/*  94 */     return result.toString();
/*     */   }
/*     */   private void initCode93() {
/*  97 */     this.C93_Code.clear();
/*  98 */     this.C93_Code.add(new Entry("0", "0", "100010100"));
/*  99 */     this.C93_Code.add(new Entry("1", "1", "101001000"));
/* 100 */     this.C93_Code.add(new Entry("2", "2", "101000100"));
/* 101 */     this.C93_Code.add(new Entry("3", "3", "101000010"));
/* 102 */     this.C93_Code.add(new Entry("4", "4", "100101000"));
/* 103 */     this.C93_Code.add(new Entry("5", "5", "100100100"));
/* 104 */     this.C93_Code.add(new Entry("6", "6", "100100010"));
/* 105 */     this.C93_Code.add(new Entry("7", "7", "101010000"));
/* 106 */     this.C93_Code.add(new Entry("8", "8", "100010010"));
/* 107 */     this.C93_Code.add(new Entry("9", "9", "100001010"));
/* 108 */     this.C93_Code.add(new Entry("10", "A", "110101000"));
/* 109 */     this.C93_Code.add(new Entry("11", "B", "110100100"));
/* 110 */     this.C93_Code.add(new Entry("12", "C", "110100010"));
/* 111 */     this.C93_Code.add(new Entry("13", "D", "110010100"));
/* 112 */     this.C93_Code.add(new Entry("14", "E", "110010010"));
/* 113 */     this.C93_Code.add(new Entry("15", "F", "110001010"));
/* 114 */     this.C93_Code.add(new Entry("16", "G", "101101000"));
/* 115 */     this.C93_Code.add(new Entry("17", "H", "101100100"));
/* 116 */     this.C93_Code.add(new Entry("18", "I", "101100010"));
/* 117 */     this.C93_Code.add(new Entry("19", "J", "100110100"));
/* 118 */     this.C93_Code.add(new Entry("20", "K", "100011010"));
/* 119 */     this.C93_Code.add(new Entry("21", "L", "101011000"));
/* 120 */     this.C93_Code.add(new Entry("22", "M", "101001100"));
/* 121 */     this.C93_Code.add(new Entry("23", "N", "101000110"));
/* 122 */     this.C93_Code.add(new Entry("24", "O", "100101100"));
/* 123 */     this.C93_Code.add(new Entry("25", "P", "100010110"));
/* 124 */     this.C93_Code.add(new Entry("26", "Q", "110110100"));
/* 125 */     this.C93_Code.add(new Entry("27", "R", "110110010"));
/* 126 */     this.C93_Code.add(new Entry("28", "S", "110101100"));
/* 127 */     this.C93_Code.add(new Entry("29", "T", "110100110"));
/* 128 */     this.C93_Code.add(new Entry("30", "U", "110010110"));
/* 129 */     this.C93_Code.add(new Entry("31", "V", "110011010"));
/* 130 */     this.C93_Code.add(new Entry("32", "W", "101101100"));
/* 131 */     this.C93_Code.add(new Entry("33", "X", "101100110"));
/* 132 */     this.C93_Code.add(new Entry("34", "Y", "100110110"));
/* 133 */     this.C93_Code.add(new Entry("35", "Z", "100111010"));
/* 134 */     this.C93_Code.add(new Entry("36", "-", "100101110"));
/* 135 */     this.C93_Code.add(new Entry("37", ".", "111010100"));
/* 136 */     this.C93_Code.add(new Entry("38", " ", "111010010"));
/* 137 */     this.C93_Code.add(new Entry("39", "$", "111001010"));
/* 138 */     this.C93_Code.add(new Entry("40", "/", "101101110"));
/* 139 */     this.C93_Code.add(new Entry("41", "+", "101110110"));
/* 140 */     this.C93_Code.add(new Entry("42", "%", "110101110"));
/* 141 */     this.C93_Code.add(new Entry("43", "(", "100100110"));
/* 142 */     this.C93_Code.add(new Entry("44", ")", "111011010"));
/* 143 */     this.C93_Code.add(new Entry("45", "#", "111010110"));
/* 144 */     this.C93_Code.add(new Entry("46", "@", "100110010"));
/* 145 */     this.C93_Code.add(new Entry("-", "*", "101011110"));
/*     */   }
/*     */ 
/*     */   
/*     */   private String addCheckDigits(String input) {
/* 150 */     int[] aryCWeights = new int[input.length()];
/* 151 */     int curweight = 1;
/* 152 */     for (int i = input.length() - 1; i >= 0; i--) {
/* 153 */       if (curweight > 20) {
/* 154 */         curweight = 1;
/*     */       }
/* 156 */       aryCWeights[i] = curweight;
/* 157 */       curweight++;
/*     */     } 
/*     */ 
/*     */     
/* 161 */     int[] aryKWeights = new int[input.length() + 1];
/* 162 */     curweight = 1;
/* 163 */     for (int j = input.length(); j >= 0; j--) {
/* 164 */       if (curweight > 15) {
/* 165 */         curweight = 1;
/*     */       }
/* 167 */       aryKWeights[j] = curweight;
/* 168 */       curweight++;
/*     */     } 
/*     */ 
/*     */     
/* 172 */     int sum = 0;
/* 173 */     for (int k = 0; k < input.length(); k++) {
/* 174 */       sum += aryCWeights[k] * Integer.parseInt(findRowByCharacter(String.valueOf(input.toCharArray()[k])).getValue());
/*     */     }
/*     */     
/* 177 */     int checksumValue = sum % 47;
/*     */     
/* 179 */     input = input + findRowByValue(String.valueOf(checksumValue)).getCharacter();
/*     */ 
/*     */     
/* 182 */     sum = 0;
/* 183 */     for (int m = 0; m < input.length(); m++) {
/* 184 */       sum += aryKWeights[m] * Integer.parseInt(findRowByCharacter(String.valueOf(input.toCharArray()[m])).getValue());
/*     */     }
/* 186 */     checksumValue = sum % 47;
/*     */     
/* 188 */     input = input + findRowByValue(String.valueOf(checksumValue)).getCharacter();
/*     */     
/* 190 */     return input;
/*     */   }
/*     */   
/*     */   public String getEncodedValue() {
/* 194 */     return encodeCode93();
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\com\pnuema\java\barcode\symbologies\Code93.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
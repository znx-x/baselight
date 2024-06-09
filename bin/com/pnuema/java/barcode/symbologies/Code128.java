/*     */ package com.pnuema.java.barcode.symbologies;
/*     */ 
/*     */ import com.pnuema.java.barcode.BarcodeCommon;
/*     */ import com.pnuema.java.barcode.IBarcode;
/*     */ import com.pnuema.java.barcode.utils.CharUtils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class Code128
/*     */   extends BarcodeCommon
/*     */   implements IBarcode
/*     */ {
/*     */   public enum TYPES
/*     */   {
/*  15 */     DYNAMIC, A, B, C; }
/*  16 */   private final List<Entry> C128_Code = new ArrayList<>();
/*  17 */   private final List<String> formattedData = new ArrayList<>();
/*  18 */   private final List<String> encodedData = new ArrayList<>();
/*  19 */   private Entry startCharacter = null;
/*  20 */   private TYPES type = TYPES.DYNAMIC;
/*     */   private static class CodeCharacter { public List<Code128.Entry> rows;
/*     */     private CodeCharacter() {
/*  23 */       this.rows = new ArrayList<>();
/*     */     }
/*     */     
/*     */     public int col; }
/*     */   
/*     */   private static class Entry { private final String id;
/*     */     private final String A;
/*     */     private final String B;
/*     */     private final String C;
/*     */     private final String encoding;
/*     */     
/*     */     Entry(String id, String a, String b, String c, String encoding) {
/*  35 */       this.id = id;
/*  36 */       this.A = a;
/*  37 */       this.B = b;
/*  38 */       this.C = c;
/*  39 */       this.encoding = encoding;
/*     */     }
/*     */     
/*     */     String getId() {
/*  43 */       return this.id;
/*     */     }
/*     */     
/*     */     String getA() {
/*  47 */       return this.A;
/*     */     }
/*     */     
/*     */     String getB() {
/*  51 */       return this.B;
/*     */     }
/*     */     
/*     */     String getC() {
/*  55 */       return this.C;
/*     */     }
/*     */     
/*     */     public String getEncoding() {
/*  59 */       return this.encoding;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Code128(String input) {
/*  68 */     setRawData(input);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Code128(String input, TYPES type) {
/*  77 */     this.type = type;
/*  78 */     setRawData(input);
/*     */   }
/*     */ 
/*     */   
/*     */   private String encodeCode128() {
/*  83 */     initCode128();
/*     */     
/*  85 */     return getEncoding();
/*     */   }
/*     */ 
/*     */   
/*     */   private void initCode128() {
/*  90 */     this.C128_Code.add(new Entry("0", " ", " ", "00", "11011001100"));
/*  91 */     this.C128_Code.add(new Entry("1", "!", "!", "01", "11001101100"));
/*  92 */     this.C128_Code.add(new Entry("2", "\"", "\"", "02", "11001100110"));
/*  93 */     this.C128_Code.add(new Entry("3", "#", "#", "03", "10010011000"));
/*  94 */     this.C128_Code.add(new Entry("4", "$", "$", "04", "10010001100"));
/*  95 */     this.C128_Code.add(new Entry("5", "%", "%", "05", "10001001100"));
/*  96 */     this.C128_Code.add(new Entry("6", "&", "&", "06", "10011001000"));
/*  97 */     this.C128_Code.add(new Entry("7", "'", "'", "07", "10011000100"));
/*  98 */     this.C128_Code.add(new Entry("8", "(", "(", "08", "10001100100"));
/*  99 */     this.C128_Code.add(new Entry("9", ")", ")", "09", "11001001000"));
/* 100 */     this.C128_Code.add(new Entry("10", "*", "*", "10", "11001000100"));
/* 101 */     this.C128_Code.add(new Entry("11", "+", "+", "11", "11000100100"));
/* 102 */     this.C128_Code.add(new Entry("12", ",", ",", "12", "10110011100"));
/* 103 */     this.C128_Code.add(new Entry("13", "-", "-", "13", "10011011100"));
/* 104 */     this.C128_Code.add(new Entry("14", ".", ".", "14", "10011001110"));
/* 105 */     this.C128_Code.add(new Entry("15", "/", "/", "15", "10111001100"));
/* 106 */     this.C128_Code.add(new Entry("16", "0", "0", "16", "10011101100"));
/* 107 */     this.C128_Code.add(new Entry("17", "1", "1", "17", "10011100110"));
/* 108 */     this.C128_Code.add(new Entry("18", "2", "2", "18", "11001110010"));
/* 109 */     this.C128_Code.add(new Entry("19", "3", "3", "19", "11001011100"));
/* 110 */     this.C128_Code.add(new Entry("20", "4", "4", "20", "11001001110"));
/* 111 */     this.C128_Code.add(new Entry("21", "5", "5", "21", "11011100100"));
/* 112 */     this.C128_Code.add(new Entry("22", "6", "6", "22", "11001110100"));
/* 113 */     this.C128_Code.add(new Entry("23", "7", "7", "23", "11101101110"));
/* 114 */     this.C128_Code.add(new Entry("24", "8", "8", "24", "11101001100"));
/* 115 */     this.C128_Code.add(new Entry("25", "9", "9", "25", "11100101100"));
/* 116 */     this.C128_Code.add(new Entry("26", ":", ":", "26", "11100100110"));
/* 117 */     this.C128_Code.add(new Entry("27", ";", ";", "27", "11101100100"));
/* 118 */     this.C128_Code.add(new Entry("28", "<", "<", "28", "11100110100"));
/* 119 */     this.C128_Code.add(new Entry("29", "=", "=", "29", "11100110010"));
/* 120 */     this.C128_Code.add(new Entry("30", ">", ">", "30", "11011011000"));
/* 121 */     this.C128_Code.add(new Entry("31", "?", "?", "31", "11011000110"));
/* 122 */     this.C128_Code.add(new Entry("32", "@", "@", "32", "11000110110"));
/* 123 */     this.C128_Code.add(new Entry("33", "A", "A", "33", "10100011000"));
/* 124 */     this.C128_Code.add(new Entry("34", "B", "B", "34", "10001011000"));
/* 125 */     this.C128_Code.add(new Entry("35", "C", "C", "35", "10001000110"));
/* 126 */     this.C128_Code.add(new Entry("36", "D", "D", "36", "10110001000"));
/* 127 */     this.C128_Code.add(new Entry("37", "E", "E", "37", "10001101000"));
/* 128 */     this.C128_Code.add(new Entry("38", "F", "F", "38", "10001100010"));
/* 129 */     this.C128_Code.add(new Entry("39", "G", "G", "39", "11010001000"));
/* 130 */     this.C128_Code.add(new Entry("40", "H", "H", "40", "11000101000"));
/* 131 */     this.C128_Code.add(new Entry("41", "I", "I", "41", "11000100010"));
/* 132 */     this.C128_Code.add(new Entry("42", "J", "J", "42", "10110111000"));
/* 133 */     this.C128_Code.add(new Entry("43", "K", "K", "43", "10110001110"));
/* 134 */     this.C128_Code.add(new Entry("44", "L", "L", "44", "10001101110"));
/* 135 */     this.C128_Code.add(new Entry("45", "M", "M", "45", "10111011000"));
/* 136 */     this.C128_Code.add(new Entry("46", "N", "N", "46", "10111000110"));
/* 137 */     this.C128_Code.add(new Entry("47", "O", "O", "47", "10001110110"));
/* 138 */     this.C128_Code.add(new Entry("48", "P", "P", "48", "11101110110"));
/* 139 */     this.C128_Code.add(new Entry("49", "Q", "Q", "49", "11010001110"));
/* 140 */     this.C128_Code.add(new Entry("50", "R", "R", "50", "11000101110"));
/* 141 */     this.C128_Code.add(new Entry("51", "S", "S", "51", "11011101000"));
/* 142 */     this.C128_Code.add(new Entry("52", "T", "T", "52", "11011100010"));
/* 143 */     this.C128_Code.add(new Entry("53", "U", "U", "53", "11011101110"));
/* 144 */     this.C128_Code.add(new Entry("54", "V", "V", "54", "11101011000"));
/* 145 */     this.C128_Code.add(new Entry("55", "W", "W", "55", "11101000110"));
/* 146 */     this.C128_Code.add(new Entry("56", "X", "X", "56", "11100010110"));
/* 147 */     this.C128_Code.add(new Entry("57", "Y", "Y", "57", "11101101000"));
/* 148 */     this.C128_Code.add(new Entry("58", "Z", "Z", "58", "11101100010"));
/* 149 */     this.C128_Code.add(new Entry("59", "[", "[", "59", "11100011010"));
/* 150 */     this.C128_Code.add(new Entry("60", "\\", "\\", "60", "11101111010"));
/* 151 */     this.C128_Code.add(new Entry("61", "]", "]", "61", "11001000010"));
/* 152 */     this.C128_Code.add(new Entry("62", "^", "^", "62", "11110001010"));
/* 153 */     this.C128_Code.add(new Entry("63", "_", "_", "63", "10100110000"));
/* 154 */     this.C128_Code.add(new Entry("64", "\000", "`", "64", "10100001100"));
/* 155 */     this.C128_Code.add(new Entry("65", CharUtils.getChar(1), "a", "65", "10010110000"));
/* 156 */     this.C128_Code.add(new Entry("66", CharUtils.getChar(2), "b", "66", "10010000110"));
/* 157 */     this.C128_Code.add(new Entry("67", CharUtils.getChar(3), "c", "67", "10000101100"));
/* 158 */     this.C128_Code.add(new Entry("68", CharUtils.getChar(4), "d", "68", "10000100110"));
/* 159 */     this.C128_Code.add(new Entry("69", CharUtils.getChar(5), "e", "69", "10110010000"));
/* 160 */     this.C128_Code.add(new Entry("70", CharUtils.getChar(6), "f", "70", "10110000100"));
/* 161 */     this.C128_Code.add(new Entry("71", CharUtils.getChar(7), "g", "71", "10011010000"));
/* 162 */     this.C128_Code.add(new Entry("72", CharUtils.getChar(8), "h", "72", "10011000010"));
/* 163 */     this.C128_Code.add(new Entry("73", CharUtils.getChar(9), "i", "73", "10000110100"));
/* 164 */     this.C128_Code.add(new Entry("74", CharUtils.getChar(10), "j", "74", "10000110010"));
/* 165 */     this.C128_Code.add(new Entry("75", CharUtils.getChar(11), "k", "75", "11000010010"));
/* 166 */     this.C128_Code.add(new Entry("76", CharUtils.getChar(12), "l", "76", "11001010000"));
/* 167 */     this.C128_Code.add(new Entry("77", CharUtils.getChar(13), "m", "77", "11110111010"));
/* 168 */     this.C128_Code.add(new Entry("78", CharUtils.getChar(14), "n", "78", "11000010100"));
/* 169 */     this.C128_Code.add(new Entry("79", CharUtils.getChar(15), "o", "79", "10001111010"));
/* 170 */     this.C128_Code.add(new Entry("80", CharUtils.getChar(16), "p", "80", "10100111100"));
/* 171 */     this.C128_Code.add(new Entry("81", CharUtils.getChar(17), "q", "81", "10010111100"));
/* 172 */     this.C128_Code.add(new Entry("82", CharUtils.getChar(18), "r", "82", "10010011110"));
/* 173 */     this.C128_Code.add(new Entry("83", CharUtils.getChar(19), "s", "83", "10111100100"));
/* 174 */     this.C128_Code.add(new Entry("84", CharUtils.getChar(20), "t", "84", "10011110100"));
/* 175 */     this.C128_Code.add(new Entry("85", CharUtils.getChar(21), "u", "85", "10011110010"));
/* 176 */     this.C128_Code.add(new Entry("86", CharUtils.getChar(22), "v", "86", "11110100100"));
/* 177 */     this.C128_Code.add(new Entry("87", CharUtils.getChar(23), "w", "87", "11110010100"));
/* 178 */     this.C128_Code.add(new Entry("88", CharUtils.getChar(24), "x", "88", "11110010010"));
/* 179 */     this.C128_Code.add(new Entry("89", CharUtils.getChar(25), "y", "89", "11011011110"));
/* 180 */     this.C128_Code.add(new Entry("90", CharUtils.getChar(26), "z", "90", "11011110110"));
/* 181 */     this.C128_Code.add(new Entry("91", CharUtils.getChar(27), "{", "91", "11110110110"));
/* 182 */     this.C128_Code.add(new Entry("92", CharUtils.getChar(28), "|", "92", "10101111000"));
/* 183 */     this.C128_Code.add(new Entry("93", CharUtils.getChar(29), "}", "93", "10100011110"));
/* 184 */     this.C128_Code.add(new Entry("94", CharUtils.getChar(30), "~", "94", "10001011110"));
/*     */     
/* 186 */     this.C128_Code.add(new Entry("95", CharUtils.getChar(31), CharUtils.getChar(127), "95", "10111101000"));
/* 187 */     this.C128_Code.add(new Entry("96", CharUtils.getChar(202), CharUtils.getChar(202), "96", "10111100010"));
/* 188 */     this.C128_Code.add(new Entry("97", CharUtils.getChar(201), CharUtils.getChar(201), "97", "11110101000"));
/* 189 */     this.C128_Code.add(new Entry("98", "SHIFT", "SHIFT", "98", "11110100010"));
/* 190 */     this.C128_Code.add(new Entry("99", "CODE_C", "CODE_C", "99", "10111011110"));
/* 191 */     this.C128_Code.add(new Entry("100", "CODE_B", CharUtils.getChar(203), "CODE_B", "10111101110"));
/* 192 */     this.C128_Code.add(new Entry("101", CharUtils.getChar(203), "CODE_A", "CODE_A", "11101011110"));
/* 193 */     this.C128_Code.add(new Entry("102", CharUtils.getChar(200), CharUtils.getChar(200), CharUtils.getChar(200), "11110101110"));
/* 194 */     this.C128_Code.add(new Entry("103", "START_A", "START_A", "START_A", "11010000100"));
/* 195 */     this.C128_Code.add(new Entry("104", "START_B", "START_B", "START_B", "11010010000"));
/* 196 */     this.C128_Code.add(new Entry("105", "START_C", "START_C", "START_C", "11010011100"));
/* 197 */     this.C128_Code.add(new Entry("", "STOP", "STOP", "STOP", "11000111010"));
/*     */   }
/*     */   
/*     */   private Entry findRow(String column, String match) {
/* 201 */     for (Entry entry : this.C128_Code) {
/* 202 */       if (column.equalsIgnoreCase("A") && entry.getA().equals(match))
/* 203 */         return entry; 
/* 204 */       if (column.equalsIgnoreCase("B") && entry.getB().equals(match))
/* 205 */         return entry; 
/* 206 */       if (column.equalsIgnoreCase("C") && entry.getC().equals(match))
/* 207 */         return entry; 
/* 208 */       if (column.equalsIgnoreCase("encoding") && entry.encoding.equals(match))
/* 209 */         return entry; 
/* 210 */       if (column.equalsIgnoreCase("id") && entry.getId().equals(match)) {
/* 211 */         return entry;
/*     */       }
/*     */     } 
/*     */     
/* 215 */     return null;
/*     */   }
/*     */   private CodeCharacter findStartorCodeCharacter(String s) {
/* 218 */     CodeCharacter returnValue = new CodeCharacter();
/*     */ 
/*     */     
/* 221 */     if (s.length() > 1 && (Character.isDigit(s.toCharArray()[0]) || (String.valueOf(s.toCharArray()[0]).equals(CharUtils.getChar(200)) && (Character.isDigit(s.toCharArray()[1]) || String.valueOf(s.toCharArray()[1]).equals(CharUtils.getChar(200)))))) {
/* 222 */       if (this.startCharacter == null) {
/* 223 */         this.startCharacter = findRow("A", "START_C");
/* 224 */         returnValue.rows.add(this.startCharacter);
/*     */       } else {
/* 226 */         returnValue.rows.add(findRow("A", "CODE_C"));
/*     */       } 
/*     */       
/* 229 */       returnValue.col = 1;
/*     */     } else {
/* 231 */       boolean AFound = false;
/* 232 */       boolean BFound = false;
/* 233 */       for (Entry row : this.C128_Code) {
/*     */         try {
/* 235 */           if (!AFound && s.equals(row.A)) {
/* 236 */             AFound = true;
/* 237 */             returnValue.col = 2;
/*     */             
/* 239 */             if (this.startCharacter == null) {
/* 240 */               this.startCharacter = findRow("A", "START_A");
/* 241 */               returnValue.rows.add(this.startCharacter); continue;
/*     */             } 
/* 243 */             returnValue.rows.add(findRow("B", "CODE_A")); continue;
/*     */           } 
/* 245 */           if (!BFound && s.equals(row.B)) {
/* 246 */             BFound = true;
/* 247 */             returnValue.col = 1;
/*     */             
/* 249 */             if (this.startCharacter == null) {
/* 250 */               this.startCharacter = findRow("A", "START_B");
/* 251 */               returnValue.rows.add(this.startCharacter); continue;
/*     */             } 
/* 253 */             returnValue.rows.add(findRow("A", "CODE_B")); continue;
/*     */           } 
/* 255 */           if (AFound && BFound) {
/*     */             break;
/*     */           }
/* 258 */         } catch (Exception ex) {
/* 259 */           error("EC128-1: " + ex.getMessage());
/*     */         } 
/*     */       } 
/*     */       
/* 263 */       if (returnValue.rows.isEmpty()) {
/* 264 */         error("EC128-2: Could not determine start character.");
/*     */       }
/*     */     } 
/*     */     
/* 268 */     return returnValue;
/*     */   }
/*     */   
/*     */   private String CalculateCheckDigit() {
/* 272 */     int checkSum = 0;
/*     */     
/* 274 */     for (int i = 0; i < this.formattedData.size(); i++) {
/*     */       
/* 276 */       String s = ((String)this.formattedData.get(i)).replace("'", "''");
/*     */ 
/*     */       
/* 279 */       Entry row = findRow("A", s);
/*     */ 
/*     */       
/* 282 */       if (row == null) {
/* 283 */         row = findRow("B", s);
/*     */       }
/*     */ 
/*     */       
/* 287 */       if (row == null) {
/* 288 */         row = findRow("C", s);
/*     */       }
/*     */       
/* 291 */       if (row == null) {
/* 292 */         error("EC128-3: No value found in encoding table");
/* 293 */         return null;
/*     */       } 
/*     */       
/* 296 */       int value = Integer.parseInt(row.getId());
/* 297 */       int addition = value * ((i == 0) ? 1 : i);
/* 298 */       checkSum += addition;
/*     */     } 
/*     */     
/* 301 */     int Remainder = checkSum % 103;
/* 302 */     Entry retRows = findRow("id", String.valueOf(Remainder));
/* 303 */     if (retRows != null) {
/* 304 */       return retRows.encoding;
/*     */     }
/* 306 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   private void breakUpDataForEncoding() {
/* 311 */     StringBuilder temp = new StringBuilder();
/* 312 */     String tempRawData = getRawData();
/*     */ 
/*     */     
/* 315 */     if (this.type == TYPES.A || this.type == TYPES.B) {
/* 316 */       for (char c : getRawData().toCharArray())
/* 317 */         this.formattedData.add(String.valueOf(c)); 
/*     */       return;
/*     */     } 
/* 320 */     if (this.type == TYPES.C) {
/* 321 */       if (!checkNumericOnly(getRawData())) {
/* 322 */         error("EC128-6: Only numeric values can be encoded with C128-C.");
/*     */       }
/*     */ 
/*     */       
/* 326 */       if (getRawData().length() % 2 > 0) {
/* 327 */         tempRawData = "0" + getRawData();
/*     */       }
/*     */     } 
/*     */     
/* 331 */     for (char c : tempRawData.toCharArray()) {
/* 332 */       if (checkNumericOnly(String.valueOf(c))) {
/* 333 */         if (temp.length() == 0) {
/* 334 */           temp.append(c);
/*     */         } else {
/* 336 */           temp.append(c);
/* 337 */           this.formattedData.add(temp.toString());
/* 338 */           temp = new StringBuilder();
/*     */         } 
/*     */       } else {
/* 341 */         if (temp.length() > 0) {
/* 342 */           this.formattedData.add(temp.toString());
/* 343 */           temp = new StringBuilder();
/*     */         } 
/* 345 */         this.formattedData.add(String.valueOf(c));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 350 */     if (temp.length() > 0) {
/* 351 */       this.formattedData.add(temp.toString());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void insertStartandCodeCharacters() {
/* 359 */     if (this.type != TYPES.DYNAMIC) {
/* 360 */       switch (this.type) {
/*     */         case A:
/* 362 */           this.formattedData.add(0, "START_A");
/*     */           return;
/*     */         case B:
/* 365 */           this.formattedData.add(0, "START_B");
/*     */           return;
/*     */         case C:
/* 368 */           this.formattedData.add(0, "START_C");
/*     */           return;
/*     */       } 
/* 371 */       error("EC128-4: Unknown start type in fixed type encoding.");
/*     */     } else {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/* 377 */         Entry currentCodeSet = null;
/* 378 */         String currentCodeString = "";
/*     */         
/* 380 */         for (int i = 0; i < this.formattedData.size(); i++) {
/* 381 */           CodeCharacter codeCharacter = findStartorCodeCharacter(this.formattedData.get(i));
/* 382 */           List<Entry> tempStartChars = codeCharacter.rows;
/*     */ 
/*     */           
/* 385 */           boolean sameCodeSet = false;
/* 386 */           for (Entry row : tempStartChars) {
/* 387 */             if (row.getA().endsWith(currentCodeString) || row.getB().endsWith(currentCodeString) || row.getC().endsWith(currentCodeString)) {
/* 388 */               sameCodeSet = true;
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */           
/* 394 */           if (currentCodeSet == null || !sameCodeSet) {
/* 395 */             currentCodeSet = tempStartChars.get(0);
/*     */             
/* 397 */             switch (codeCharacter.col) {
/*     */               case 0:
/* 399 */                 currentCodeString = currentCodeSet.getA().substring(currentCodeSet.getA().length() - 1);
/* 400 */                 this.formattedData.add(i++, currentCodeSet.getA());
/*     */                 break;
/*     */               case 1:
/* 403 */                 currentCodeString = currentCodeSet.getB().substring(currentCodeSet.getB().length() - 1);
/* 404 */                 this.formattedData.add(i++, currentCodeSet.getB());
/*     */                 break;
/*     */               case 2:
/* 407 */                 currentCodeString = currentCodeSet.getC().substring(currentCodeSet.getC().length() - 1);
/* 408 */                 this.formattedData.add(i++, currentCodeSet.getC());
/*     */                 break;
/*     */             } 
/*     */           } 
/*     */         } 
/* 413 */       } catch (Exception ex) {
/* 414 */         error("EC128-3: Could not insert start and code characters.\n Message: " + ex.getMessage());
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private String getEncoding() {
/* 420 */     breakUpDataForEncoding();
/*     */ 
/*     */     
/* 423 */     insertStartandCodeCharacters();
/*     */     
/* 425 */     StringBuilder encodedData = new StringBuilder();
/* 426 */     for (String s : this.formattedData) {
/*     */       Entry E_Row;
/* 428 */       String s1 = s.replace("'", "''");
/*     */ 
/*     */ 
/*     */       
/* 432 */       switch (this.type) {
/*     */         case A:
/* 434 */           E_Row = findRow("A", s1);
/*     */           break;
/*     */         case B:
/* 437 */           E_Row = findRow("B", s1);
/*     */           break;
/*     */         case C:
/* 440 */           E_Row = findRow("C", s1);
/*     */           break;
/*     */         case DYNAMIC:
/* 443 */           E_Row = findRow("A", s1);
/* 444 */           if (E_Row == null) {
/* 445 */             E_Row = findRow("B", s1);
/*     */             
/* 447 */             if (E_Row == null) {
/* 448 */               E_Row = findRow("C", s1);
/*     */             }
/*     */           } 
/*     */           break;
/*     */         default:
/* 453 */           E_Row = null;
/*     */           break;
/*     */       } 
/*     */       
/* 457 */       if (E_Row == null) {
/* 458 */         error("EC128-5: Could not find encoding of a value( " + s1 + " ) in C128 type " + this.type);
/* 459 */         return null;
/*     */       } 
/*     */       
/* 462 */       encodedData.append(E_Row.encoding);
/* 463 */       this.encodedData.add(E_Row.encoding);
/*     */     } 
/*     */ 
/*     */     
/* 467 */     encodedData.append(CalculateCheckDigit());
/* 468 */     this.encodedData.add(CalculateCheckDigit());
/*     */ 
/*     */     
/* 471 */     Entry stopChar = findRow("A", "STOP");
/* 472 */     if (stopChar != null) {
/* 473 */       encodedData.append(stopChar.encoding);
/* 474 */       this.encodedData.add(stopChar.encoding);
/*     */     } 
/*     */ 
/*     */     
/* 478 */     encodedData.append("11");
/* 479 */     this.encodedData.add("11");
/*     */     
/* 481 */     return encodedData.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEncodedValue() {
/* 486 */     return encodeCode128();
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\com\pnuema\java\barcode\symbologies\Code128.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
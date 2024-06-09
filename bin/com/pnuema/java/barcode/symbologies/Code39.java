/*     */ package com.pnuema.java.barcode.symbologies;
/*     */ 
/*     */ import com.pnuema.java.barcode.BarcodeCommon;
/*     */ import com.pnuema.java.barcode.IBarcode;
/*     */ import com.pnuema.java.barcode.utils.CharUtils;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Code39
/*     */   extends BarcodeCommon
/*     */   implements IBarcode
/*     */ {
/*  14 */   private final HashMap<Character, String> C39_Code = new HashMap<>();
/*  15 */   private final HashMap<String, String> ExtC39_Translation = new HashMap<>();
/*     */ 
/*     */   
/*     */   private boolean _AllowExtended = false;
/*     */   
/*     */   private boolean _EnableChecksum = false;
/*     */ 
/*     */   
/*     */   public Code39(String input) {
/*  24 */     setRawData(input);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Code39(String input, boolean AllowExtended) {
/*  33 */     setRawData(input);
/*  34 */     this._AllowExtended = AllowExtended;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Code39(String input, boolean AllowExtended, boolean EnableChecksum) {
/*  44 */     setRawData(input);
/*  45 */     this._AllowExtended = AllowExtended;
/*  46 */     this._EnableChecksum = EnableChecksum;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String encodeCode39() {
/*  54 */     init_Code39();
/*  55 */     init_ExtendedCode39();
/*     */     
/*  57 */     String strNoAstr = getRawData().replace("*", "");
/*  58 */     String strFormattedData = "*" + strNoAstr + (this._EnableChecksum ? (String)Character.valueOf(getChecksumChar(strNoAstr)) : "") + "*";
/*     */     
/*  60 */     if (this._AllowExtended) {
/*  61 */       InsertExtendedCharsIfNeeded(strFormattedData);
/*     */     }
/*     */     
/*  64 */     StringBuilder result = new StringBuilder();
/*     */     
/*  66 */     for (char c : strFormattedData.toCharArray()) {
/*     */       try {
/*  68 */         result.append(this.C39_Code.get(Character.valueOf(c)));
/*  69 */         result.append("0");
/*  70 */       } catch (Exception ex) {
/*  71 */         if (this._AllowExtended) {
/*  72 */           error("EC39-1: Invalid data.");
/*     */         } else {
/*  74 */           error("EC39-1: Invalid data. (Try using Extended Code39)");
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  79 */     result = new StringBuilder(result.substring(0, result.length() - 1));
/*     */ 
/*     */     
/*  82 */     this.C39_Code.clear();
/*     */     
/*  84 */     return result.toString();
/*     */   }
/*     */   
/*     */   private void init_Code39() {
/*  88 */     this.C39_Code.clear();
/*  89 */     this.C39_Code.put(Character.valueOf('0'), "101001101101");
/*  90 */     this.C39_Code.put(Character.valueOf('1'), "110100101011");
/*  91 */     this.C39_Code.put(Character.valueOf('2'), "101100101011");
/*  92 */     this.C39_Code.put(Character.valueOf('3'), "110110010101");
/*  93 */     this.C39_Code.put(Character.valueOf('4'), "101001101011");
/*  94 */     this.C39_Code.put(Character.valueOf('5'), "110100110101");
/*  95 */     this.C39_Code.put(Character.valueOf('6'), "101100110101");
/*  96 */     this.C39_Code.put(Character.valueOf('7'), "101001011011");
/*  97 */     this.C39_Code.put(Character.valueOf('8'), "110100101101");
/*  98 */     this.C39_Code.put(Character.valueOf('9'), "101100101101");
/*  99 */     this.C39_Code.put(Character.valueOf('A'), "110101001011");
/* 100 */     this.C39_Code.put(Character.valueOf('B'), "101101001011");
/* 101 */     this.C39_Code.put(Character.valueOf('C'), "110110100101");
/* 102 */     this.C39_Code.put(Character.valueOf('D'), "101011001011");
/* 103 */     this.C39_Code.put(Character.valueOf('E'), "110101100101");
/* 104 */     this.C39_Code.put(Character.valueOf('F'), "101101100101");
/* 105 */     this.C39_Code.put(Character.valueOf('G'), "101010011011");
/* 106 */     this.C39_Code.put(Character.valueOf('H'), "110101001101");
/* 107 */     this.C39_Code.put(Character.valueOf('I'), "101101001101");
/* 108 */     this.C39_Code.put(Character.valueOf('J'), "101011001101");
/* 109 */     this.C39_Code.put(Character.valueOf('K'), "110101010011");
/* 110 */     this.C39_Code.put(Character.valueOf('L'), "101101010011");
/* 111 */     this.C39_Code.put(Character.valueOf('M'), "110110101001");
/* 112 */     this.C39_Code.put(Character.valueOf('N'), "101011010011");
/* 113 */     this.C39_Code.put(Character.valueOf('O'), "110101101001");
/* 114 */     this.C39_Code.put(Character.valueOf('P'), "101101101001");
/* 115 */     this.C39_Code.put(Character.valueOf('Q'), "101010110011");
/* 116 */     this.C39_Code.put(Character.valueOf('R'), "110101011001");
/* 117 */     this.C39_Code.put(Character.valueOf('S'), "101101011001");
/* 118 */     this.C39_Code.put(Character.valueOf('T'), "101011011001");
/* 119 */     this.C39_Code.put(Character.valueOf('U'), "110010101011");
/* 120 */     this.C39_Code.put(Character.valueOf('V'), "100110101011");
/* 121 */     this.C39_Code.put(Character.valueOf('W'), "110011010101");
/* 122 */     this.C39_Code.put(Character.valueOf('X'), "100101101011");
/* 123 */     this.C39_Code.put(Character.valueOf('Y'), "110010110101");
/* 124 */     this.C39_Code.put(Character.valueOf('Z'), "100110110101");
/* 125 */     this.C39_Code.put(Character.valueOf('-'), "100101011011");
/* 126 */     this.C39_Code.put(Character.valueOf('.'), "110010101101");
/* 127 */     this.C39_Code.put(Character.valueOf(' '), "100110101101");
/* 128 */     this.C39_Code.put(Character.valueOf('$'), "100100100101");
/* 129 */     this.C39_Code.put(Character.valueOf('/'), "100100101001");
/* 130 */     this.C39_Code.put(Character.valueOf('+'), "100101001001");
/* 131 */     this.C39_Code.put(Character.valueOf('%'), "101001001001");
/* 132 */     this.C39_Code.put(Character.valueOf('*'), "100101101101");
/*     */   }
/*     */   
/*     */   private void init_ExtendedCode39() {
/* 136 */     this.ExtC39_Translation.clear();
/* 137 */     this.ExtC39_Translation.put(CharUtils.getChar(0), "%U");
/* 138 */     this.ExtC39_Translation.put(CharUtils.getChar(1), "$A");
/* 139 */     this.ExtC39_Translation.put(CharUtils.getChar(2), "$B");
/* 140 */     this.ExtC39_Translation.put(CharUtils.getChar(3), "$C");
/* 141 */     this.ExtC39_Translation.put(CharUtils.getChar(4), "$D");
/* 142 */     this.ExtC39_Translation.put(CharUtils.getChar(5), "$E");
/* 143 */     this.ExtC39_Translation.put(CharUtils.getChar(6), "$F");
/* 144 */     this.ExtC39_Translation.put(CharUtils.getChar(7), "$G");
/* 145 */     this.ExtC39_Translation.put(CharUtils.getChar(8), "$H");
/* 146 */     this.ExtC39_Translation.put(CharUtils.getChar(9), "$I");
/* 147 */     this.ExtC39_Translation.put(CharUtils.getChar(10), "$J");
/* 148 */     this.ExtC39_Translation.put(CharUtils.getChar(11), "$K");
/* 149 */     this.ExtC39_Translation.put(CharUtils.getChar(12), "$L");
/* 150 */     this.ExtC39_Translation.put(CharUtils.getChar(13), "$M");
/* 151 */     this.ExtC39_Translation.put(CharUtils.getChar(14), "$N");
/* 152 */     this.ExtC39_Translation.put(CharUtils.getChar(15), "$O");
/* 153 */     this.ExtC39_Translation.put(CharUtils.getChar(16), "$P");
/* 154 */     this.ExtC39_Translation.put(CharUtils.getChar(17), "$Q");
/* 155 */     this.ExtC39_Translation.put(CharUtils.getChar(18), "$R");
/* 156 */     this.ExtC39_Translation.put(CharUtils.getChar(19), "$S");
/* 157 */     this.ExtC39_Translation.put(CharUtils.getChar(20), "$T");
/* 158 */     this.ExtC39_Translation.put(CharUtils.getChar(21), "$U");
/* 159 */     this.ExtC39_Translation.put(CharUtils.getChar(22), "$V");
/* 160 */     this.ExtC39_Translation.put(CharUtils.getChar(23), "$W");
/* 161 */     this.ExtC39_Translation.put(CharUtils.getChar(24), "$X");
/* 162 */     this.ExtC39_Translation.put(CharUtils.getChar(25), "$Y");
/* 163 */     this.ExtC39_Translation.put(CharUtils.getChar(26), "$Z");
/* 164 */     this.ExtC39_Translation.put(CharUtils.getChar(27), "%A");
/* 165 */     this.ExtC39_Translation.put(CharUtils.getChar(28), "%B");
/* 166 */     this.ExtC39_Translation.put(CharUtils.getChar(29), "%C");
/* 167 */     this.ExtC39_Translation.put(CharUtils.getChar(30), "%D");
/* 168 */     this.ExtC39_Translation.put(CharUtils.getChar(31), "%E");
/* 169 */     this.ExtC39_Translation.put("!", "/A");
/* 170 */     this.ExtC39_Translation.put("\"", "/B");
/* 171 */     this.ExtC39_Translation.put("#", "/C");
/* 172 */     this.ExtC39_Translation.put("$", "/D");
/* 173 */     this.ExtC39_Translation.put("%", "/E");
/* 174 */     this.ExtC39_Translation.put("&", "/F");
/* 175 */     this.ExtC39_Translation.put("'", "/G");
/* 176 */     this.ExtC39_Translation.put("(", "/H");
/* 177 */     this.ExtC39_Translation.put(")", "/I");
/* 178 */     this.ExtC39_Translation.put("*", "/J");
/* 179 */     this.ExtC39_Translation.put("+", "/K");
/* 180 */     this.ExtC39_Translation.put(",", "/L");
/* 181 */     this.ExtC39_Translation.put("/", "/O");
/* 182 */     this.ExtC39_Translation.put(":", "/Z");
/* 183 */     this.ExtC39_Translation.put(";", "%F");
/* 184 */     this.ExtC39_Translation.put("<", "%G");
/* 185 */     this.ExtC39_Translation.put("=", "%H");
/* 186 */     this.ExtC39_Translation.put(">", "%I");
/* 187 */     this.ExtC39_Translation.put("?", "%J");
/* 188 */     this.ExtC39_Translation.put("[", "%K");
/* 189 */     this.ExtC39_Translation.put("\\", "%L");
/* 190 */     this.ExtC39_Translation.put("]", "%M");
/* 191 */     this.ExtC39_Translation.put("^", "%N");
/* 192 */     this.ExtC39_Translation.put("_", "%O");
/* 193 */     this.ExtC39_Translation.put("{", "%P");
/* 194 */     this.ExtC39_Translation.put("|", "%Q");
/* 195 */     this.ExtC39_Translation.put("}", "%R");
/* 196 */     this.ExtC39_Translation.put("~", "%S");
/* 197 */     this.ExtC39_Translation.put("`", "%W");
/* 198 */     this.ExtC39_Translation.put("@", "%V");
/* 199 */     this.ExtC39_Translation.put("a", "+A");
/* 200 */     this.ExtC39_Translation.put("b", "+B");
/* 201 */     this.ExtC39_Translation.put("c", "+C");
/* 202 */     this.ExtC39_Translation.put("d", "+D");
/* 203 */     this.ExtC39_Translation.put("e", "+E");
/* 204 */     this.ExtC39_Translation.put("f", "+F");
/* 205 */     this.ExtC39_Translation.put("g", "+G");
/* 206 */     this.ExtC39_Translation.put("h", "+H");
/* 207 */     this.ExtC39_Translation.put("i", "+I");
/* 208 */     this.ExtC39_Translation.put("j", "+J");
/* 209 */     this.ExtC39_Translation.put("k", "+K");
/* 210 */     this.ExtC39_Translation.put("l", "+L");
/* 211 */     this.ExtC39_Translation.put("m", "+M");
/* 212 */     this.ExtC39_Translation.put("n", "+N");
/* 213 */     this.ExtC39_Translation.put("o", "+O");
/* 214 */     this.ExtC39_Translation.put("p", "+P");
/* 215 */     this.ExtC39_Translation.put("q", "+Q");
/* 216 */     this.ExtC39_Translation.put("r", "+R");
/* 217 */     this.ExtC39_Translation.put("s", "+S");
/* 218 */     this.ExtC39_Translation.put("t", "+T");
/* 219 */     this.ExtC39_Translation.put("u", "+U");
/* 220 */     this.ExtC39_Translation.put("v", "+V");
/* 221 */     this.ExtC39_Translation.put("w", "+W");
/* 222 */     this.ExtC39_Translation.put("x", "+X");
/* 223 */     this.ExtC39_Translation.put("y", "+Y");
/* 224 */     this.ExtC39_Translation.put("z", "+Z");
/* 225 */     this.ExtC39_Translation.put(CharUtils.getChar(127), "%T");
/*     */   }
/*     */   private void InsertExtendedCharsIfNeeded(String FormattedData) {
/* 228 */     StringBuilder output = new StringBuilder();
/* 229 */     for (char c : FormattedData.toCharArray()) {
/*     */       try {
/* 231 */         String s = this.C39_Code.get(Character.valueOf(c));
/* 232 */         output.append(c);
/* 233 */       } catch (Exception ex) {
/*     */         
/* 235 */         Object oTrans = this.ExtC39_Translation.get(String.valueOf(c));
/* 236 */         output.append(oTrans.toString());
/*     */       } 
/*     */     } 
/*     */     
/* 240 */     FormattedData = output.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   private char getChecksumChar(String strNoAstr) {
/* 245 */     String Code39_Charset = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%";
/* 246 */     InsertExtendedCharsIfNeeded(strNoAstr);
/* 247 */     int sum = 0;
/*     */ 
/*     */     
/* 250 */     for (int i = 0; i < strNoAstr.length(); i++) {
/* 251 */       sum += Code39_Charset.indexOf(strNoAstr.toCharArray()[i]);
/*     */     }
/*     */ 
/*     */     
/* 255 */     return Code39_Charset.toCharArray()[sum % 43];
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEncodedValue() {
/* 260 */     return encodeCode39();
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\com\pnuema\java\barcode\symbologies\Code39.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
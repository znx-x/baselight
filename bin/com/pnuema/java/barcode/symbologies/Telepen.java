/*     */ package com.pnuema.java.barcode.symbologies;
/*     */ 
/*     */ import com.pnuema.java.barcode.BarcodeCommon;
/*     */ import com.pnuema.java.barcode.IBarcode;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ public class Telepen
/*     */   extends BarcodeCommon
/*     */   implements IBarcode
/*     */ {
/*  12 */   private static final HashMap<Character, String> Telepen_Code = new HashMap<>();
/*     */   
/*     */   public enum StartStopCode {
/*  15 */     START1(Character.forDigit(0, 10)),
/*  16 */     STOP1(Character.forDigit(1, 10)),
/*  17 */     START2(Character.forDigit(2, 10)),
/*  18 */     STOP2(Character.forDigit(3, 10)),
/*  19 */     START3(Character.forDigit(4, 10)),
/*  20 */     STOP3(Character.forDigit(5, 10)); private final char asChar;
/*     */     
/*     */     private char asChar() {
/*  23 */       return this.asChar;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     StartStopCode(char asChar) {
/*  29 */       this.asChar = asChar;
/*     */     }
/*     */   }
/*     */   
/*  33 */   private StartStopCode startCode = StartStopCode.START1;
/*  34 */   private StartStopCode stopCode = StartStopCode.STOP1;
/*  35 */   private int switchModeIndex = 0;
/*  36 */   private int iCheckSum = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Telepen(String input) {
/*  44 */     setRawData(input);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String encodeTelepen() {
/*  53 */     if (Telepen_Code.isEmpty()) {
/*  54 */       initTelepen();
/*     */     }
/*     */     
/*  57 */     this.iCheckSum = 0;
/*     */ 
/*     */     
/*  60 */     setEncodingSequence();
/*     */ 
/*     */     
/*  63 */     String result = Telepen_Code.get(Character.valueOf(this.startCode.asChar()));
/*     */     
/*  65 */     switch (this.startCode)
/*     */     
/*     */     { case START2:
/*  68 */         encodeNumeric(getRawData().substring(0, this.switchModeIndex), result);
/*     */         
/*  70 */         if (this.switchModeIndex < getRawData().length()) {
/*  71 */           encodeSwitchMode(result);
/*  72 */           encodeASCII(getRawData().substring(this.switchModeIndex), result);
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  88 */         result = result + (String)Telepen_Code.get(Character.valueOf(Calculate_Checksum(this.iCheckSum)));
/*     */ 
/*     */         
/*  91 */         result = result + (String)Telepen_Code.get(Character.valueOf(this.stopCode.asChar()));
/*     */         
/*  93 */         return result;case START3: encodeASCII(getRawData().substring(0, this.switchModeIndex), result); encodeSwitchMode(result); encodeNumeric(getRawData().substring(this.switchModeIndex), result); result = result + (String)Telepen_Code.get(Character.valueOf(Calculate_Checksum(this.iCheckSum))); result = result + (String)Telepen_Code.get(Character.valueOf(this.stopCode.asChar())); return result; }  encodeASCII(getRawData(), result); result = result + (String)Telepen_Code.get(Character.valueOf(Calculate_Checksum(this.iCheckSum))); result = result + (String)Telepen_Code.get(Character.valueOf(this.stopCode.asChar())); return result;
/*     */   }
/*     */ 
/*     */   
/*     */   private void encodeASCII(String input, String output) {
/*     */     try {
/*  99 */       for (char c : input.toCharArray()) {
/* 100 */         output = output + (String)Telepen_Code.get(Character.valueOf(c));
/* 101 */         this.iCheckSum += c;
/*     */       } 
/* 103 */     } catch (Exception ex) {
/* 104 */       error("ETELEPEN-1: Invalid data when encoding ASCII");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void encodeNumeric(String input, String output) {
/*     */     try {
/* 111 */       if (input.length() % 2 > 0) {
/* 112 */         error("ETELEPEN-3: Numeric encoding attempted on odd number of characters");
/*     */       }
/* 114 */       for (int i = 0; i < input.length(); i += 2) {
/* 115 */         output = output + (String)Telepen_Code.get(Character.valueOf(Character.forDigit(Integer.parseInt(input.substring(i, i + 2)) + 27, 10)));
/* 116 */         this.iCheckSum += Integer.parseInt(input.substring(i, i + 2)) + 27;
/*     */       } 
/* 118 */     } catch (Exception ex) {
/* 119 */       error("ETELEPEN-2: Numeric encoding failed");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void encodeSwitchMode(String output) {
/* 125 */     this.iCheckSum += 16;
/*     */     
/* 127 */     output = output + (String)Telepen_Code.get(Character.valueOf(Character.forDigit(16, 10)));
/*     */   }
/*     */   
/*     */   private char Calculate_Checksum(int iCheckSum) {
/* 131 */     return Character.forDigit(127 - iCheckSum % 127, 10);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setEncodingSequence() {
/* 136 */     this.startCode = StartStopCode.START1;
/* 137 */     this.stopCode = StartStopCode.STOP1;
/* 138 */     this.switchModeIndex = getRawData().length();
/*     */ 
/*     */     
/* 141 */     int StartNumerics = 0; char[] arrayOfChar; int i; byte b;
/* 142 */     for (arrayOfChar = getRawData().toCharArray(), i = arrayOfChar.length, b = 0; b < i; ) { char c = arrayOfChar[b];
/* 143 */       if (Character.isDigit(c)) {
/* 144 */         StartNumerics++;
/*     */         
/*     */         b++;
/*     */       }  }
/*     */ 
/*     */     
/* 150 */     if (StartNumerics == getRawData().length()) {
/*     */       
/* 152 */       this.startCode = StartStopCode.START2;
/* 153 */       this.stopCode = StartStopCode.STOP2;
/*     */       
/* 155 */       if (getRawData().length() % 2 > 0) {
/* 156 */         this.switchModeIndex = getRawData().length() - 1;
/*     */       }
/*     */     } else {
/*     */       
/* 160 */       int EndNumerics = 0;
/* 161 */       for (int j = getRawData().length() - 1; j >= 0 && 
/* 162 */         Character.isDigit(getRawData().toCharArray()[j]); j--) {
/* 163 */         EndNumerics++;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 169 */       if (StartNumerics >= 4 || EndNumerics >= 4)
/*     */       {
/* 171 */         if (StartNumerics > EndNumerics) {
/*     */           
/* 173 */           this.startCode = StartStopCode.START2;
/* 174 */           this.stopCode = StartStopCode.STOP2;
/* 175 */           this.switchModeIndex = (StartNumerics % 2 == 1) ? (StartNumerics - 1) : StartNumerics;
/*     */         } else {
/*     */           
/* 178 */           this.startCode = StartStopCode.START3;
/* 179 */           this.stopCode = StartStopCode.STOP3;
/* 180 */           this.switchModeIndex = (EndNumerics % 2 == 1) ? (getRawData().length() - EndNumerics + 1) : (getRawData().length() - EndNumerics);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void initTelepen() {
/* 187 */     Telepen_Code.put(Character.valueOf(Character.forDigit(0, 10)), "1110111011101110");
/* 188 */     Telepen_Code.put(Character.valueOf(Character.forDigit(1, 10)), "1011101110111010");
/* 189 */     Telepen_Code.put(Character.valueOf(Character.forDigit(2, 10)), "1110001110111010");
/* 190 */     Telepen_Code.put(Character.valueOf(Character.forDigit(3, 10)), "1010111011101110");
/* 191 */     Telepen_Code.put(Character.valueOf(Character.forDigit(4, 10)), "1110101110111010");
/* 192 */     Telepen_Code.put(Character.valueOf(Character.forDigit(5, 10)), "1011100011101110");
/* 193 */     Telepen_Code.put(Character.valueOf(Character.forDigit(6, 10)), "1000100011101110");
/* 194 */     Telepen_Code.put(Character.valueOf(Character.forDigit(7, 10)), "1010101110111010");
/* 195 */     Telepen_Code.put(Character.valueOf(Character.forDigit(8, 10)), "1110111000111010");
/* 196 */     Telepen_Code.put(Character.valueOf(Character.forDigit(9, 10)), "1011101011101110");
/* 197 */     Telepen_Code.put(Character.valueOf(Character.forDigit(10, 10)), "1110001011101110");
/* 198 */     Telepen_Code.put(Character.valueOf(Character.forDigit(11, 10)), "1010111000111010");
/* 199 */     Telepen_Code.put(Character.valueOf(Character.forDigit(12, 10)), "1110101011101110");
/* 200 */     Telepen_Code.put(Character.valueOf(Character.forDigit(13, 10)), "1010001000111010");
/* 201 */     Telepen_Code.put(Character.valueOf(Character.forDigit(14, 10)), "1000101000111010");
/* 202 */     Telepen_Code.put(Character.valueOf(Character.forDigit(15, 10)), "1010101011101110");
/* 203 */     Telepen_Code.put(Character.valueOf(Character.forDigit(16, 10)), "1110111010111010");
/* 204 */     Telepen_Code.put(Character.valueOf(Character.forDigit(17, 10)), "1011101110001110");
/* 205 */     Telepen_Code.put(Character.valueOf(Character.forDigit(18, 10)), "1110001110001110");
/* 206 */     Telepen_Code.put(Character.valueOf(Character.forDigit(19, 10)), "1010111010111010");
/* 207 */     Telepen_Code.put(Character.valueOf(Character.forDigit(20, 10)), "1110101110001110");
/* 208 */     Telepen_Code.put(Character.valueOf(Character.forDigit(21, 10)), "1011100010111010");
/* 209 */     Telepen_Code.put(Character.valueOf(Character.forDigit(22, 10)), "1000100010111010");
/* 210 */     Telepen_Code.put(Character.valueOf(Character.forDigit(23, 10)), "1010101110001110");
/* 211 */     Telepen_Code.put(Character.valueOf(Character.forDigit(24, 10)), "1110100010001110");
/* 212 */     Telepen_Code.put(Character.valueOf(Character.forDigit(25, 10)), "1011101010111010");
/* 213 */     Telepen_Code.put(Character.valueOf(Character.forDigit(26, 10)), "1110001010111010");
/* 214 */     Telepen_Code.put(Character.valueOf(Character.forDigit(27, 10)), "1010100010001110");
/* 215 */     Telepen_Code.put(Character.valueOf(Character.forDigit(28, 10)), "1110101010111010");
/* 216 */     Telepen_Code.put(Character.valueOf(Character.forDigit(29, 10)), "1010001010001110");
/* 217 */     Telepen_Code.put(Character.valueOf(Character.forDigit(30, 10)), "1000101010001110");
/* 218 */     Telepen_Code.put(Character.valueOf(Character.forDigit(31, 10)), "1010101010111010");
/* 219 */     Telepen_Code.put(Character.valueOf(' '), "1110111011100010");
/* 220 */     Telepen_Code.put(Character.valueOf('!'), "1011101110101110");
/* 221 */     Telepen_Code.put(Character.valueOf('"'), "1110001110101110");
/* 222 */     Telepen_Code.put(Character.valueOf('#'), "1010111011100010");
/* 223 */     Telepen_Code.put(Character.valueOf('$'), "1110101110101110");
/* 224 */     Telepen_Code.put(Character.valueOf('%'), "1011100011100010");
/* 225 */     Telepen_Code.put(Character.valueOf('&'), "1000100011100010");
/* 226 */     Telepen_Code.put(Character.valueOf('\''), "1010101110101110");
/* 227 */     Telepen_Code.put(Character.valueOf('('), "1110111000101110");
/* 228 */     Telepen_Code.put(Character.valueOf(')'), "1011101011100010");
/* 229 */     Telepen_Code.put(Character.valueOf('*'), "1110001011100010");
/* 230 */     Telepen_Code.put(Character.valueOf('+'), "1010111000101110");
/* 231 */     Telepen_Code.put(Character.valueOf(','), "1110101011100010");
/* 232 */     Telepen_Code.put(Character.valueOf('-'), "1010001000101110");
/* 233 */     Telepen_Code.put(Character.valueOf('.'), "1000101000101110");
/* 234 */     Telepen_Code.put(Character.valueOf('/'), "1010101011100010");
/* 235 */     Telepen_Code.put(Character.valueOf('0'), "1110111010101110");
/* 236 */     Telepen_Code.put(Character.valueOf('1'), "1011101000100010");
/* 237 */     Telepen_Code.put(Character.valueOf('2'), "1110001000100010");
/* 238 */     Telepen_Code.put(Character.valueOf('3'), "1010111010101110");
/* 239 */     Telepen_Code.put(Character.valueOf('4'), "1110101000100010");
/* 240 */     Telepen_Code.put(Character.valueOf('5'), "1011100010101110");
/* 241 */     Telepen_Code.put(Character.valueOf('6'), "1000100010101110");
/* 242 */     Telepen_Code.put(Character.valueOf('7'), "1010101000100010");
/* 243 */     Telepen_Code.put(Character.valueOf('8'), "1110100010100010");
/* 244 */     Telepen_Code.put(Character.valueOf('9'), "1011101010101110");
/* 245 */     Telepen_Code.put(Character.valueOf(':'), "1110001010101110");
/* 246 */     Telepen_Code.put(Character.valueOf(';'), "1010100010100010");
/* 247 */     Telepen_Code.put(Character.valueOf('<'), "1110101010101110");
/* 248 */     Telepen_Code.put(Character.valueOf('='), "1010001010100010");
/* 249 */     Telepen_Code.put(Character.valueOf('>'), "1000101010100010");
/* 250 */     Telepen_Code.put(Character.valueOf('?'), "1010101010101110");
/* 251 */     Telepen_Code.put(Character.valueOf('@'), "1110111011101010");
/* 252 */     Telepen_Code.put(Character.valueOf('A'), "1011101110111000");
/* 253 */     Telepen_Code.put(Character.valueOf('B'), "1110001110111000");
/* 254 */     Telepen_Code.put(Character.valueOf('C'), "1010111011101010");
/* 255 */     Telepen_Code.put(Character.valueOf('D'), "1110101110111000");
/* 256 */     Telepen_Code.put(Character.valueOf('E'), "1011100011101010");
/* 257 */     Telepen_Code.put(Character.valueOf('F'), "1000100011101010");
/* 258 */     Telepen_Code.put(Character.valueOf('G'), "1010101110111000");
/* 259 */     Telepen_Code.put(Character.valueOf('H'), "1110111000111000");
/* 260 */     Telepen_Code.put(Character.valueOf('I'), "1011101011101010");
/* 261 */     Telepen_Code.put(Character.valueOf('J'), "1110001011101010");
/* 262 */     Telepen_Code.put(Character.valueOf('K'), "1010111000111000");
/* 263 */     Telepen_Code.put(Character.valueOf('L'), "1110101011101010");
/* 264 */     Telepen_Code.put(Character.valueOf('M'), "1010001000111000");
/* 265 */     Telepen_Code.put(Character.valueOf('N'), "1000101000111000");
/* 266 */     Telepen_Code.put(Character.valueOf('O'), "1010101011101010");
/* 267 */     Telepen_Code.put(Character.valueOf('P'), "1110111010111000");
/* 268 */     Telepen_Code.put(Character.valueOf('Q'), "1011101110001010");
/* 269 */     Telepen_Code.put(Character.valueOf('R'), "1110001110001010");
/* 270 */     Telepen_Code.put(Character.valueOf('S'), "1010111010111000");
/* 271 */     Telepen_Code.put(Character.valueOf('T'), "1110101110001010");
/* 272 */     Telepen_Code.put(Character.valueOf('U'), "1011100010111000");
/* 273 */     Telepen_Code.put(Character.valueOf('V'), "1000100010111000");
/* 274 */     Telepen_Code.put(Character.valueOf('W'), "1010101110001010");
/* 275 */     Telepen_Code.put(Character.valueOf('X'), "1110100010001010");
/* 276 */     Telepen_Code.put(Character.valueOf('Y'), "1011101010111000");
/* 277 */     Telepen_Code.put(Character.valueOf('Z'), "1110001010111000");
/* 278 */     Telepen_Code.put(Character.valueOf('['), "1010100010001010");
/* 279 */     Telepen_Code.put(Character.valueOf('\\'), "1110101010111000");
/* 280 */     Telepen_Code.put(Character.valueOf(']'), "1010001010001010");
/* 281 */     Telepen_Code.put(Character.valueOf('^'), "1000101010001010");
/* 282 */     Telepen_Code.put(Character.valueOf('_'), "1010101010111000");
/* 283 */     Telepen_Code.put(Character.valueOf('`'), "1110111010001000");
/* 284 */     Telepen_Code.put(Character.valueOf('a'), "1011101110101010");
/* 285 */     Telepen_Code.put(Character.valueOf('b'), "1110001110101010");
/* 286 */     Telepen_Code.put(Character.valueOf('c'), "1010111010001000");
/* 287 */     Telepen_Code.put(Character.valueOf('d'), "1110101110101010");
/* 288 */     Telepen_Code.put(Character.valueOf('e'), "1011100010001000");
/* 289 */     Telepen_Code.put(Character.valueOf('f'), "1000100010001000");
/* 290 */     Telepen_Code.put(Character.valueOf('g'), "1010101110101010");
/* 291 */     Telepen_Code.put(Character.valueOf('h'), "1110111000101010");
/* 292 */     Telepen_Code.put(Character.valueOf('i'), "1011101010001000");
/* 293 */     Telepen_Code.put(Character.valueOf('j'), "1110001010001000");
/* 294 */     Telepen_Code.put(Character.valueOf('k'), "1010111000101010");
/* 295 */     Telepen_Code.put(Character.valueOf('l'), "1110101010001000");
/* 296 */     Telepen_Code.put(Character.valueOf('m'), "1010001000101010");
/* 297 */     Telepen_Code.put(Character.valueOf('n'), "1000101000101010");
/* 298 */     Telepen_Code.put(Character.valueOf('o'), "1010101010001000");
/* 299 */     Telepen_Code.put(Character.valueOf('p'), "1110111010101010");
/* 300 */     Telepen_Code.put(Character.valueOf('q'), "1011101000101000");
/* 301 */     Telepen_Code.put(Character.valueOf('r'), "1110001000101000");
/* 302 */     Telepen_Code.put(Character.valueOf('s'), "1010111010101010");
/* 303 */     Telepen_Code.put(Character.valueOf('t'), "1110101000101000");
/* 304 */     Telepen_Code.put(Character.valueOf('u'), "1011100010101010");
/* 305 */     Telepen_Code.put(Character.valueOf('v'), "1000100010101010");
/* 306 */     Telepen_Code.put(Character.valueOf('w'), "1010101000101000");
/* 307 */     Telepen_Code.put(Character.valueOf('x'), "1110100010101000");
/* 308 */     Telepen_Code.put(Character.valueOf('y'), "1011101010101010");
/* 309 */     Telepen_Code.put(Character.valueOf('z'), "1110001010101010");
/* 310 */     Telepen_Code.put(Character.valueOf('{'), "1010100010101000");
/* 311 */     Telepen_Code.put(Character.valueOf('|'), "1110101010101010");
/* 312 */     Telepen_Code.put(Character.valueOf('}'), "1010001010101000");
/* 313 */     Telepen_Code.put(Character.valueOf('~'), "1000101010101000");
/* 314 */     Telepen_Code.put(Character.valueOf(Character.forDigit(127, 10)), "1010101010101010");
/* 315 */     Telepen_Code.put(Character.valueOf(StartStopCode.START1.asChar()), "1010101010111000");
/* 316 */     Telepen_Code.put(Character.valueOf(StartStopCode.STOP1.asChar()), "1110001010101010");
/* 317 */     Telepen_Code.put(Character.valueOf(StartStopCode.START2.asChar()), "1010101011101000");
/* 318 */     Telepen_Code.put(Character.valueOf(StartStopCode.STOP2.asChar()), "1110100010101010");
/* 319 */     Telepen_Code.put(Character.valueOf(StartStopCode.START3.asChar()), "1010101110101000");
/* 320 */     Telepen_Code.put(Character.valueOf(StartStopCode.STOP3.asChar()), "1110101000101010");
/*     */   }
/*     */   
/*     */   public String getEncodedValue() {
/* 324 */     return encodeTelepen();
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\com\pnuema\java\barcode\symbologies\Telepen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
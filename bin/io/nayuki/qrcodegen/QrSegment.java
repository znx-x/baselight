/*     */ package io.nayuki.qrcodegen;
/*     */ 
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.regex.Pattern;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class QrSegment
/*     */ {
/*     */   public final Mode mode;
/*     */   public final int numChars;
/*     */   final BitBuffer data;
/*     */   
/*     */   public static QrSegment makeBytes(byte[] data) {
/*  60 */     Objects.requireNonNull(data);
/*  61 */     BitBuffer bb = new BitBuffer();
/*  62 */     for (byte b : data)
/*  63 */       bb.appendBits(b & 0xFF, 8); 
/*  64 */     return new QrSegment(Mode.BYTE, data.length, bb);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static QrSegment makeNumeric(String digits) {
/*  76 */     Objects.requireNonNull(digits);
/*  77 */     if (!NUMERIC_REGEX.matcher(digits).matches()) {
/*  78 */       throw new IllegalArgumentException("String contains non-numeric characters");
/*     */     }
/*  80 */     BitBuffer bb = new BitBuffer();
/*  81 */     for (int i = 0; i < digits.length(); ) {
/*  82 */       int n = Math.min(digits.length() - i, 3);
/*  83 */       bb.appendBits(Integer.parseInt(digits.substring(i, i + n)), n * 3 + 1);
/*  84 */       i += n;
/*     */     } 
/*  86 */     return new QrSegment(Mode.NUMERIC, digits.length(), bb);
/*     */   }
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
/*     */   public static QrSegment makeAlphanumeric(String text) {
/* 100 */     Objects.requireNonNull(text);
/* 101 */     if (!ALPHANUMERIC_REGEX.matcher(text).matches()) {
/* 102 */       throw new IllegalArgumentException("String contains unencodable characters in alphanumeric mode");
/*     */     }
/* 104 */     BitBuffer bb = new BitBuffer();
/*     */     int i;
/* 106 */     for (i = 0; i <= text.length() - 2; i += 2) {
/* 107 */       int temp = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:".indexOf(text.charAt(i)) * 45;
/* 108 */       temp += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:".indexOf(text.charAt(i + 1));
/* 109 */       bb.appendBits(temp, 11);
/*     */     } 
/* 111 */     if (i < text.length())
/* 112 */       bb.appendBits("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:".indexOf(text.charAt(i)), 6); 
/* 113 */     return new QrSegment(Mode.ALPHANUMERIC, text.length(), bb);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<QrSegment> makeSegments(String text) {
/* 125 */     Objects.requireNonNull(text);
/*     */ 
/*     */     
/* 128 */     List<QrSegment> result = new ArrayList<>();
/* 129 */     if (!text.equals(""))
/* 130 */       if (NUMERIC_REGEX.matcher(text).matches()) {
/* 131 */         result.add(makeNumeric(text));
/* 132 */       } else if (ALPHANUMERIC_REGEX.matcher(text).matches()) {
/* 133 */         result.add(makeAlphanumeric(text));
/*     */       } else {
/* 135 */         result.add(makeBytes(text.getBytes(StandardCharsets.UTF_8)));
/* 136 */       }   return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static QrSegment makeEci(int assignVal) {
/* 148 */     BitBuffer bb = new BitBuffer();
/* 149 */     if (assignVal < 0)
/* 150 */       throw new IllegalArgumentException("ECI assignment value out of range"); 
/* 151 */     if (assignVal < 128) {
/* 152 */       bb.appendBits(assignVal, 8);
/* 153 */     } else if (assignVal < 16384) {
/* 154 */       bb.appendBits(2, 2);
/* 155 */       bb.appendBits(assignVal, 14);
/* 156 */     } else if (assignVal < 1000000) {
/* 157 */       bb.appendBits(6, 3);
/* 158 */       bb.appendBits(assignVal, 21);
/*     */     } else {
/* 160 */       throw new IllegalArgumentException("ECI assignment value out of range");
/* 161 */     }  return new QrSegment(Mode.ECI, 0, bb);
/*     */   }
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
/*     */   
/*     */   public QrSegment(Mode md, int numCh, BitBuffer data) {
/* 193 */     this.mode = Objects.<Mode>requireNonNull(md);
/* 194 */     Objects.requireNonNull(data);
/* 195 */     if (numCh < 0)
/* 196 */       throw new IllegalArgumentException("Invalid value"); 
/* 197 */     this.numChars = numCh;
/* 198 */     this.data = data.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BitBuffer getData() {
/* 209 */     return this.data.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static int getTotalBits(List<QrSegment> segs, int version) {
/* 217 */     Objects.requireNonNull(segs);
/* 218 */     long result = 0L;
/* 219 */     for (QrSegment seg : segs) {
/* 220 */       Objects.requireNonNull(seg);
/* 221 */       int ccbits = seg.mode.numCharCountBits(version);
/* 222 */       if (seg.numChars >= 1 << ccbits)
/* 223 */         return -1; 
/* 224 */       result += 4L + ccbits + seg.data.bitLength();
/* 225 */       if (result > 2147483647L)
/* 226 */         return -1; 
/*     */     } 
/* 228 */     return (int)result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 238 */   public static final Pattern NUMERIC_REGEX = Pattern.compile("[0-9]*");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 245 */   public static final Pattern ALPHANUMERIC_REGEX = Pattern.compile("[A-Z0-9 $%*+./:-]*");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final String ALPHANUMERIC_CHARSET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum Mode
/*     */   {
/* 262 */     NUMERIC(1, new int[] { 10, 12, 14 }),
/* 263 */     ALPHANUMERIC(2, new int[] { 9, 11, 13 }),
/* 264 */     BYTE(4, new int[] { 8, 16, 16 }),
/* 265 */     KANJI(8, new int[] { 8, 10, 12 }),
/* 266 */     ECI(7, new int[] { 0, 0, 0 });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     final int modeBits;
/*     */ 
/*     */ 
/*     */     
/*     */     private final int[] numBitsCharCount;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Mode(int mode, int... ccbits) {
/* 281 */       this.modeBits = mode;
/* 282 */       this.numBitsCharCount = ccbits;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     int numCharCountBits(int ver) {
/* 291 */       assert 1 <= ver && ver <= 40;
/* 292 */       return this.numBitsCharCount[(ver + 7) / 17];
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\io\nayuki\qrcodegen\QrSegment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
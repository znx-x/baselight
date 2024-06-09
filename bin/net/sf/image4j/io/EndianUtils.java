/*     */ package net.sf.image4j.io;
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
/*     */ public class EndianUtils
/*     */ {
/*     */   public static short swapShort(short value) {
/*  19 */     return (short)((value & 0xFF00) >> 8 | (value & 0xFF) << 8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int swapInteger(int value) {
/*  30 */     return (value & 0xFF000000) >> 24 | (value & 0xFF0000) >> 8 | (value & 0xFF00) << 8 | (value & 0xFF) << 24;
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
/*     */   public static long swapLong(long value) {
/*  42 */     return (value & 0xFF00000000000000L) >> 56L | (value & 0xFF000000000000L) >> 40L | (value & 0xFF0000000000L) >> 24L | (value & 0xFF00000000L) >> 8L | (value & 0xFF000000L) << 8L | (value & 0xFF0000L) << 24L | (value & 0xFF00L) << 40L | (value & 0xFFL) << 56L;
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
/*     */   public static float swapFloat(float value) {
/*  60 */     int i = Float.floatToIntBits(value);
/*  61 */     i = swapInteger(i);
/*  62 */     return Float.intBitsToFloat(i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double swapDouble(double value) {
/*  73 */     long l = Double.doubleToLongBits(value);
/*  74 */     l = swapLong(l);
/*  75 */     return Double.longBitsToDouble(l);
/*     */   }
/*     */   
/*     */   public static String toHexString(int i, boolean littleEndian, int bytes) {
/*  79 */     if (littleEndian) {
/*  80 */       i = swapInteger(i);
/*     */     }
/*  82 */     StringBuilder sb = new StringBuilder();
/*  83 */     sb.append(Integer.toHexString(i));
/*  84 */     if (sb.length() % 2 != 0) {
/*  85 */       sb.insert(0, '0');
/*     */     }
/*  87 */     while (sb.length() < bytes * 2) {
/*  88 */       sb.insert(0, "00");
/*     */     }
/*  90 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public static StringBuilder toCharString(StringBuilder sb, int i, int bytes, char def) {
/*  94 */     int shift = 24;
/*  95 */     for (int j = 0; j < bytes; j++) {
/*  96 */       int b = i >> shift & 0xFF;
/*  97 */       char c = (b < 32) ? def : (char)b;
/*  98 */       sb.append(c);
/*  99 */       shift -= 8;
/*     */     } 
/* 101 */     return sb;
/*     */   }
/*     */   
/*     */   public static String toInfoString(int info) {
/* 105 */     StringBuilder sb = new StringBuilder();
/* 106 */     sb.append("Decimal: ").append(info);
/* 107 */     sb.append(", Hex BE: ").append(toHexString(info, false, 4));
/* 108 */     sb.append(", Hex LE: ").append(toHexString(info, true, 4));
/* 109 */     sb.append(", String BE: [");
/* 110 */     sb = toCharString(sb, info, 4, '.');
/* 111 */     sb.append(']');
/* 112 */     sb.append(", String LE: [");
/* 113 */     sb = toCharString(sb, swapInteger(info), 4, '.');
/* 114 */     sb.append(']');
/* 115 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\net\sf\image4j\io\EndianUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
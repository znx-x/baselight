/*     */ package net.sf.image4j.io;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
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
/*     */ public class LittleEndianInputStream
/*     */   extends DataInputStream
/*     */   implements CountingDataInput
/*     */ {
/*     */   public LittleEndianInputStream(CountingInputStream in) {
/*  26 */     super(in);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCount() {
/*  31 */     return ((CountingInputStream)this.in).getCount();
/*     */   }
/*     */   
/*     */   public int skip(int count, boolean strict) throws IOException {
/*  35 */     return IOUtils.skip(this, count, strict);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short readShortLE() throws IOException {
/*  45 */     int b1 = read();
/*  46 */     int b2 = read();
/*     */     
/*  48 */     if (b1 < 0 || b2 < 0) {
/*  49 */       throw new EOFException();
/*     */     }
/*     */     
/*  52 */     short ret = (short)((b2 << 8) + (b1 << 0));
/*     */     
/*  54 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readIntLE() throws IOException {
/*  63 */     int b1 = read();
/*  64 */     int b2 = read();
/*  65 */     int b3 = read();
/*  66 */     int b4 = read();
/*     */     
/*  68 */     if (b1 < -1 || b2 < -1 || b3 < -1 || b4 < -1) {
/*  69 */       throw new EOFException();
/*     */     }
/*     */     
/*  72 */     int ret = (b4 << 24) + (b3 << 16) + (b2 << 8) + (b1 << 0);
/*     */     
/*  74 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float readFloatLE() throws IOException {
/*  83 */     int i = readIntLE();
/*     */     
/*  85 */     float ret = Float.intBitsToFloat(i);
/*     */     
/*  87 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long readLongLE() throws IOException {
/*  97 */     int i1 = readIntLE();
/*  98 */     int i2 = readIntLE();
/*     */     
/* 100 */     long ret = (i1 << 32L) + (i2 & 0xFFFFFFFFL);
/*     */     
/* 102 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double readDoubleLE() throws IOException {
/* 112 */     long l = readLongLE();
/*     */     
/* 114 */     double ret = Double.longBitsToDouble(l);
/*     */     
/* 116 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long readUnsignedInt() throws IOException {
/* 123 */     long i1 = readUnsignedByte();
/* 124 */     long i2 = readUnsignedByte();
/* 125 */     long i3 = readUnsignedByte();
/* 126 */     long i4 = readUnsignedByte();
/*     */     
/* 128 */     long ret = i1 << 24L | i2 << 16L | i3 << 8L | i4;
/*     */     
/* 130 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long readUnsignedIntLE() throws IOException {
/* 137 */     long i1 = readUnsignedByte();
/* 138 */     long i2 = readUnsignedByte();
/* 139 */     long i3 = readUnsignedByte();
/* 140 */     long i4 = readUnsignedByte();
/*     */     
/* 142 */     long ret = i4 << 24L | i3 << 16L | i2 << 8L | i1;
/*     */     
/* 144 */     return ret;
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\net\sf\image4j\io\LittleEndianInputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
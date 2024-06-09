/*     */ package net.sf.image4j.io;
/*     */ 
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
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
/*     */ public class LittleEndianOutputStream
/*     */   extends DataOutputStream
/*     */ {
/*     */   public LittleEndianOutputStream(OutputStream out) {
/*  26 */     super(out);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeShortLE(short value) throws IOException {
/*  35 */     value = EndianUtils.swapShort(value);
/*  36 */     writeShort(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeIntLE(int value) throws IOException {
/*  45 */     value = EndianUtils.swapInteger(value);
/*  46 */     writeInt(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeFloatLE(float value) throws IOException {
/*  55 */     value = EndianUtils.swapFloat(value);
/*  56 */     writeFloat(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeLongLE(long value) throws IOException {
/*  65 */     value = EndianUtils.swapLong(value);
/*  66 */     writeLong(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeDoubleLE(double value) throws IOException {
/*  75 */     value = EndianUtils.swapDouble(value);
/*  76 */     writeDouble(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeUnsignedInt(long value) throws IOException {
/*  83 */     int i1 = (int)(value >> 24L);
/*  84 */     int i2 = (int)(value >> 16L & 0xFFL);
/*  85 */     int i3 = (int)(value >> 8L & 0xFFL);
/*  86 */     int i4 = (int)(value & 0xFFL);
/*     */     
/*  88 */     write(i1);
/*  89 */     write(i2);
/*  90 */     write(i3);
/*  91 */     write(i4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeUnsignedIntLE(long value) throws IOException {
/*  98 */     int i1 = (int)(value >> 24L);
/*  99 */     int i2 = (int)(value >> 16L & 0xFFL);
/* 100 */     int i3 = (int)(value >> 8L & 0xFFL);
/* 101 */     int i4 = (int)(value & 0xFFL);
/*     */     
/* 103 */     write(i4);
/* 104 */     write(i3);
/* 105 */     write(i2);
/* 106 */     write(i1);
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\net\sf\image4j\io\LittleEndianOutputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
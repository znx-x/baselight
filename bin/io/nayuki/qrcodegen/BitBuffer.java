/*     */ package io.nayuki.qrcodegen;
/*     */ 
/*     */ import java.util.BitSet;
/*     */ import java.util.Objects;
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
/*     */ public final class BitBuffer
/*     */   implements Cloneable
/*     */ {
/*  49 */   private BitSet data = new BitSet();
/*  50 */   private int bitLength = 0;
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
/*     */   public int bitLength() {
/*  62 */     assert this.bitLength >= 0;
/*  63 */     return this.bitLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBit(int index) {
/*  74 */     if (index < 0 || index >= this.bitLength)
/*  75 */       throw new IndexOutOfBoundsException(); 
/*  76 */     return this.data.get(index) ? 1 : 0;
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
/*     */   public void appendBits(int val, int len) {
/*  90 */     if (len < 0 || len > 31 || val >>> len != 0)
/*  91 */       throw new IllegalArgumentException("Value out of range"); 
/*  92 */     if (Integer.MAX_VALUE - this.bitLength < len)
/*  93 */       throw new IllegalStateException("Maximum length reached"); 
/*  94 */     for (int i = len - 1; i >= 0; i--, this.bitLength++) {
/*  95 */       this.data.set(this.bitLength, QrCode.getBit(val, i));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendData(BitBuffer bb) {
/* 107 */     Objects.requireNonNull(bb);
/* 108 */     if (Integer.MAX_VALUE - this.bitLength < bb.bitLength)
/* 109 */       throw new IllegalStateException("Maximum length reached"); 
/* 110 */     for (int i = 0; i < bb.bitLength; i++, this.bitLength++) {
/* 111 */       this.data.set(this.bitLength, bb.data.get(i));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BitBuffer clone() {
/*     */     try {
/* 121 */       BitBuffer result = (BitBuffer)super.clone();
/* 122 */       result.data = (BitSet)result.data.clone();
/* 123 */       return result;
/* 124 */     } catch (CloneNotSupportedException e) {
/* 125 */       throw new AssertionError(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\io\nayuki\qrcodegen\BitBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
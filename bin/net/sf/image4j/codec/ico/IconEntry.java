/*     */ package net.sf.image4j.codec.ico;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.sf.image4j.io.LittleEndianInputStream;
/*     */ import net.sf.image4j.io.LittleEndianOutputStream;
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
/*     */ public class IconEntry
/*     */ {
/*     */   public int bWidth;
/*     */   public int bHeight;
/*     */   public int bColorCount;
/*     */   public byte bReserved;
/*     */   public short sPlanes;
/*     */   public short sBitCount;
/*     */   public int iSizeInBytes;
/*     */   public int iFileOffset;
/*     */   
/*     */   public IconEntry(LittleEndianInputStream in) throws IOException {
/*  54 */     this.bWidth = in.readUnsignedByte();
/*     */     
/*  56 */     this.bHeight = in.readUnsignedByte();
/*     */     
/*  58 */     this.bColorCount = in.readUnsignedByte();
/*     */     
/*  60 */     this.bReserved = in.readByte();
/*     */     
/*  62 */     this.sPlanes = in.readShortLE();
/*     */     
/*  64 */     this.sBitCount = in.readShortLE();
/*     */     
/*  66 */     this.iSizeInBytes = in.readIntLE();
/*     */     
/*  68 */     this.iFileOffset = in.readIntLE();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IconEntry() {
/*  75 */     this.bWidth = 0;
/*  76 */     this.bHeight = 0;
/*  77 */     this.bColorCount = 0;
/*  78 */     this.sPlanes = 1;
/*  79 */     this.bReserved = 0;
/*  80 */     this.sBitCount = 0;
/*  81 */     this.iSizeInBytes = 0;
/*  82 */     this.iFileOffset = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  89 */     StringBuffer sb = new StringBuffer();
/*  90 */     sb.append("width=");
/*  91 */     sb.append(this.bWidth);
/*  92 */     sb.append(",height=");
/*  93 */     sb.append(this.bHeight);
/*  94 */     sb.append(",bitCount=");
/*  95 */     sb.append(this.sBitCount);
/*  96 */     sb.append(",colorCount=" + this.bColorCount);
/*  97 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(LittleEndianOutputStream out) throws IOException {
/* 107 */     out.writeByte(this.bWidth);
/*     */     
/* 109 */     out.writeByte(this.bHeight);
/*     */     
/* 111 */     out.writeByte(this.bColorCount);
/*     */     
/* 113 */     out.writeByte(this.bReserved);
/*     */     
/* 115 */     out.writeShortLE(this.sPlanes);
/*     */     
/* 117 */     out.writeShortLE(this.sBitCount);
/*     */     
/* 119 */     out.writeIntLE(this.iSizeInBytes);
/*     */     
/* 121 */     out.writeIntLE(this.iFileOffset);
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\net\sf\image4j\codec\ico\IconEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
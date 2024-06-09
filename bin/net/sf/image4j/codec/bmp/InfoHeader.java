/*     */ package net.sf.image4j.codec.bmp;
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
/*     */ public class InfoHeader
/*     */ {
/*     */   public int iSize;
/*     */   public int iWidth;
/*     */   public int iHeight;
/*     */   public short sPlanes;
/*     */   public short sBitCount;
/*     */   public int iCompression;
/*     */   public int iImageSize;
/*     */   public int iXpixelsPerM;
/*     */   public int iYpixelsPerM;
/*     */   public int iColorsUsed;
/*     */   public int iColorsImportant;
/*     */   public int iNumColors;
/*     */   
/*     */   public InfoHeader(LittleEndianInputStream in) throws IOException {
/*  84 */     this.iSize = in.readIntLE();
/*     */     
/*  86 */     init(in, this.iSize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InfoHeader(LittleEndianInputStream in, int infoSize) throws IOException {
/*  93 */     init(in, infoSize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void init(LittleEndianInputStream in, int infoSize) throws IOException {
/* 100 */     this.iSize = infoSize;
/*     */ 
/*     */     
/* 103 */     this.iWidth = in.readIntLE();
/*     */     
/* 105 */     this.iHeight = in.readIntLE();
/*     */     
/* 107 */     this.sPlanes = in.readShortLE();
/*     */     
/* 109 */     this.sBitCount = in.readShortLE();
/*     */ 
/*     */     
/* 112 */     this.iNumColors = (int)Math.pow(2.0D, this.sBitCount);
/*     */ 
/*     */     
/* 115 */     this.iCompression = in.readIntLE();
/*     */     
/* 117 */     this.iImageSize = in.readIntLE();
/*     */     
/* 119 */     this.iXpixelsPerM = in.readIntLE();
/*     */     
/* 121 */     this.iYpixelsPerM = in.readIntLE();
/*     */     
/* 123 */     this.iColorsUsed = in.readIntLE();
/*     */     
/* 125 */     this.iColorsImportant = in.readIntLE();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InfoHeader() {
/* 133 */     this.iSize = 40;
/*     */     
/* 135 */     this.iWidth = 0;
/*     */     
/* 137 */     this.iHeight = 0;
/*     */     
/* 139 */     this.sPlanes = 1;
/*     */     
/* 141 */     this.sBitCount = 0;
/*     */ 
/*     */     
/* 144 */     this.iNumColors = 0;
/*     */ 
/*     */     
/* 147 */     this.iCompression = 0;
/*     */     
/* 149 */     this.iImageSize = 0;
/*     */     
/* 151 */     this.iXpixelsPerM = 0;
/*     */     
/* 153 */     this.iYpixelsPerM = 0;
/*     */     
/* 155 */     this.iColorsUsed = 0;
/*     */     
/* 157 */     this.iColorsImportant = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InfoHeader(InfoHeader source) {
/* 165 */     this.iColorsImportant = source.iColorsImportant;
/* 166 */     this.iColorsUsed = source.iColorsUsed;
/* 167 */     this.iCompression = source.iCompression;
/* 168 */     this.iHeight = source.iHeight;
/* 169 */     this.iWidth = source.iWidth;
/* 170 */     this.iImageSize = source.iImageSize;
/* 171 */     this.iNumColors = source.iNumColors;
/* 172 */     this.iSize = source.iSize;
/* 173 */     this.iXpixelsPerM = source.iXpixelsPerM;
/* 174 */     this.iYpixelsPerM = source.iYpixelsPerM;
/* 175 */     this.sBitCount = source.sBitCount;
/* 176 */     this.sPlanes = source.sPlanes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(LittleEndianOutputStream out) throws IOException {
/* 187 */     out.writeIntLE(this.iSize);
/*     */     
/* 189 */     out.writeIntLE(this.iWidth);
/*     */     
/* 191 */     out.writeIntLE(this.iHeight);
/*     */     
/* 193 */     out.writeShortLE(this.sPlanes);
/*     */     
/* 195 */     out.writeShortLE(this.sBitCount);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 201 */     out.writeIntLE(this.iCompression);
/*     */     
/* 203 */     out.writeIntLE(this.iImageSize);
/*     */     
/* 205 */     out.writeIntLE(this.iXpixelsPerM);
/*     */     
/* 207 */     out.writeIntLE(this.iYpixelsPerM);
/*     */     
/* 209 */     out.writeIntLE(this.iColorsUsed);
/*     */     
/* 211 */     out.writeIntLE(this.iColorsImportant);
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\net\sf\image4j\codec\bmp\InfoHeader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
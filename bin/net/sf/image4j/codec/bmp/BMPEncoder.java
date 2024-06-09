/*     */ package net.sf.image4j.codec.bmp;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.IndexColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
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
/*     */ public class BMPEncoder
/*     */ {
/*     */   public static void write(BufferedImage img, File file) throws IOException {
/*  36 */     FileOutputStream fout = new FileOutputStream(file);
/*     */     try {
/*  38 */       BufferedOutputStream out = new BufferedOutputStream(fout);
/*  39 */       write(img, out);
/*  40 */       out.flush();
/*     */     } finally {
/*     */       try {
/*  43 */         fout.close();
/*  44 */       } catch (IOException iOException) {}
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
/*     */ 
/*     */   
/*     */   public static void write(BufferedImage img, OutputStream os) throws IOException {
/*  58 */     InfoHeader ih = createInfoHeader(img);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  63 */     int mapSize = 0;
/*  64 */     IndexColorModel icm = null;
/*     */     
/*  66 */     if (ih.sBitCount <= 8) {
/*  67 */       icm = (IndexColorModel)img.getColorModel();
/*  68 */       mapSize = icm.getMapSize();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  73 */     int headerSize = 14 + ih.iSize;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  79 */     int mapBytes = 4 * mapSize;
/*     */ 
/*     */ 
/*     */     
/*  83 */     int dataOffset = headerSize + mapBytes;
/*     */ 
/*     */ 
/*     */     
/*  87 */     int bytesPerLine = 0;
/*     */     
/*  89 */     switch (ih.sBitCount) {
/*     */       case 1:
/*  91 */         bytesPerLine = getBytesPerLine1(ih.iWidth);
/*     */         break;
/*     */       case 4:
/*  94 */         bytesPerLine = getBytesPerLine4(ih.iWidth);
/*     */         break;
/*     */       case 8:
/*  97 */         bytesPerLine = getBytesPerLine8(ih.iWidth);
/*     */         break;
/*     */       case 24:
/* 100 */         bytesPerLine = getBytesPerLine24(ih.iWidth);
/*     */         break;
/*     */       case 32:
/* 103 */         bytesPerLine = ih.iWidth * 4;
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 109 */     int fileSize = dataOffset + bytesPerLine * ih.iHeight;
/*     */ 
/*     */ 
/*     */     
/* 113 */     LittleEndianOutputStream out = new LittleEndianOutputStream(os);
/*     */ 
/*     */     
/* 116 */     writeFileHeader(fileSize, dataOffset, out);
/*     */ 
/*     */     
/* 119 */     ih.write(out);
/*     */ 
/*     */     
/* 122 */     if (ih.sBitCount <= 8) {
/* 123 */       writeColorMap(icm, out);
/*     */     }
/*     */ 
/*     */     
/* 127 */     switch (ih.sBitCount) {
/*     */       case 1:
/* 129 */         write1(img.getRaster(), out);
/*     */         break;
/*     */       case 4:
/* 132 */         write4(img.getRaster(), out);
/*     */         break;
/*     */       case 8:
/* 135 */         write8(img.getRaster(), out);
/*     */         break;
/*     */       case 24:
/* 138 */         write24(img.getRaster(), out);
/*     */         break;
/*     */       case 32:
/* 141 */         write32(img.getRaster(), img.getAlphaRaster(), out);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static InfoHeader createInfoHeader(BufferedImage img) {
/* 152 */     InfoHeader ret = new InfoHeader();
/* 153 */     ret.iColorsImportant = 0;
/* 154 */     ret.iColorsUsed = 0;
/* 155 */     ret.iCompression = 0;
/* 156 */     ret.iHeight = img.getHeight();
/* 157 */     ret.iWidth = img.getWidth();
/* 158 */     ret.sBitCount = (short)img.getColorModel().getPixelSize();
/* 159 */     ret.iNumColors = 1 << ((ret.sBitCount == 32) ? 24 : ret.sBitCount);
/* 160 */     ret.iImageSize = 0;
/* 161 */     return ret;
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
/*     */   public static void writeFileHeader(int fileSize, int dataOffset, LittleEndianOutputStream out) throws IOException {
/* 174 */     byte[] signature = "BM".getBytes("UTF-8");
/* 175 */     out.write(signature);
/*     */     
/* 177 */     out.writeIntLE(fileSize);
/*     */     
/* 179 */     out.writeIntLE(0);
/*     */     
/* 181 */     out.writeIntLE(dataOffset);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeColorMap(IndexColorModel icm, LittleEndianOutputStream out) throws IOException {
/* 191 */     int mapSize = icm.getMapSize();
/* 192 */     for (int i = 0; i < mapSize; i++) {
/* 193 */       int rgb = icm.getRGB(i);
/* 194 */       int r = rgb >> 16 & 0xFF;
/* 195 */       int g = rgb >> 8 & 0xFF;
/* 196 */       int b = rgb & 0xFF;
/* 197 */       out.writeByte(b);
/* 198 */       out.writeByte(g);
/* 199 */       out.writeByte(r);
/* 200 */       out.writeByte(0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getBytesPerLine1(int width) {
/* 211 */     int ret = width / 8;
/* 212 */     if (ret * 8 < width) {
/* 213 */       ret++;
/*     */     }
/* 215 */     if (ret % 4 != 0) {
/* 216 */       ret = (ret / 4 + 1) * 4;
/*     */     }
/* 218 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getBytesPerLine4(int width) {
/* 228 */     int ret = width / 2;
/* 229 */     if (ret % 4 != 0) {
/* 230 */       ret = (ret / 4 + 1) * 4;
/*     */     }
/* 232 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getBytesPerLine8(int width) {
/* 242 */     int ret = width;
/* 243 */     if (ret % 4 != 0) {
/* 244 */       ret = (ret / 4 + 1) * 4;
/*     */     }
/* 246 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getBytesPerLine24(int width) {
/* 256 */     int ret = width * 3;
/* 257 */     if (ret % 4 != 0) {
/* 258 */       ret = (ret / 4 + 1) * 4;
/*     */     }
/* 260 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getBitmapSize(int w, int h, int bpp) {
/* 271 */     int bytesPerLine = 0;
/* 272 */     switch (bpp) {
/*     */       case 1:
/* 274 */         bytesPerLine = getBytesPerLine1(w);
/*     */         break;
/*     */       case 4:
/* 277 */         bytesPerLine = getBytesPerLine4(w);
/*     */         break;
/*     */       case 8:
/* 280 */         bytesPerLine = getBytesPerLine8(w);
/*     */         break;
/*     */       case 24:
/* 283 */         bytesPerLine = getBytesPerLine24(w);
/*     */         break;
/*     */       case 32:
/* 286 */         bytesPerLine = w * 4;
/*     */         break;
/*     */     } 
/* 289 */     int ret = bytesPerLine * h;
/* 290 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void write1(Raster raster, LittleEndianOutputStream out) throws IOException {
/* 300 */     int bytesPerLine = getBytesPerLine1(raster.getWidth());
/*     */     
/* 302 */     byte[] line = new byte[bytesPerLine];
/*     */     
/* 304 */     for (int y = raster.getHeight() - 1; y >= 0; y--) {
/* 305 */       for (int i = 0; i < bytesPerLine; i++) {
/* 306 */         line[i] = 0;
/*     */       }
/*     */       
/* 309 */       for (int x = 0; x < raster.getWidth(); x++) {
/* 310 */         int bi = x / 8;
/* 311 */         int j = x % 8;
/* 312 */         int index = raster.getSample(x, y, 0);
/* 313 */         line[bi] = setBit(line[bi], j, index);
/*     */       } 
/*     */       
/* 316 */       out.write(line);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void write4(Raster raster, LittleEndianOutputStream out) throws IOException {
/* 336 */     int width = raster.getWidth();
/* 337 */     int height = raster.getHeight();
/*     */ 
/*     */     
/* 340 */     int bytesPerLine = getBytesPerLine4(width);
/*     */ 
/*     */     
/* 343 */     byte[] line = new byte[bytesPerLine];
/*     */ 
/*     */     
/* 346 */     for (int y = height - 1; y >= 0; y--) {
/*     */ 
/*     */       
/* 349 */       for (int i = 0; i < bytesPerLine; i++) {
/* 350 */         line[i] = 0;
/*     */       }
/*     */ 
/*     */       
/* 354 */       for (int x = 0; x < width; x++) {
/*     */ 
/*     */         
/* 357 */         int bi = x / 2;
/*     */ 
/*     */         
/* 360 */         int j = x % 2;
/*     */ 
/*     */         
/* 363 */         int index = raster.getSample(x, y, 0);
/*     */         
/* 365 */         line[bi] = setNibble(line[bi], j, index);
/*     */       } 
/*     */ 
/*     */       
/* 369 */       out.write(line);
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
/*     */   public static void write8(Raster raster, LittleEndianOutputStream out) throws IOException {
/* 381 */     int width = raster.getWidth();
/* 382 */     int height = raster.getHeight();
/*     */ 
/*     */     
/* 385 */     int bytesPerLine = getBytesPerLine8(width);
/*     */ 
/*     */     
/* 388 */     for (int y = height - 1; y >= 0; y--) {
/*     */ 
/*     */       
/* 391 */       for (int x = 0; x < width; x++) {
/*     */ 
/*     */         
/* 394 */         int index = raster.getSample(x, y, 0);
/*     */ 
/*     */         
/* 397 */         out.writeByte(index);
/*     */       } 
/*     */ 
/*     */       
/* 401 */       for (int i = width; i < bytesPerLine; i++) {
/* 402 */         out.writeByte(0);
/*     */       }
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
/*     */   
/*     */   public static void write24(Raster raster, LittleEndianOutputStream out) throws IOException {
/* 416 */     int width = raster.getWidth();
/* 417 */     int height = raster.getHeight();
/*     */ 
/*     */     
/* 420 */     int bytesPerLine = getBytesPerLine24(width);
/*     */ 
/*     */     
/* 423 */     for (int y = height - 1; y >= 0; y--) {
/*     */ 
/*     */       
/* 426 */       for (int x = 0; x < width; x++) {
/*     */ 
/*     */         
/* 429 */         int r = raster.getSample(x, y, 0);
/* 430 */         int g = raster.getSample(x, y, 1);
/* 431 */         int b = raster.getSample(x, y, 2);
/*     */ 
/*     */         
/* 434 */         out.writeByte(b);
/* 435 */         out.writeByte(g);
/* 436 */         out.writeByte(r);
/*     */       } 
/*     */ 
/*     */       
/* 440 */       for (int i = width * 3; i < bytesPerLine; i++) {
/* 441 */         out.writeByte(0);
/*     */       }
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
/*     */   
/*     */   public static void write32(Raster raster, Raster alpha, LittleEndianOutputStream out) throws IOException {
/* 455 */     int width = raster.getWidth();
/* 456 */     int height = raster.getHeight();
/*     */ 
/*     */     
/* 459 */     for (int y = height - 1; y >= 0; y--) {
/*     */ 
/*     */       
/* 462 */       for (int x = 0; x < width; x++) {
/*     */ 
/*     */         
/* 465 */         int r = raster.getSample(x, y, 0);
/* 466 */         int g = raster.getSample(x, y, 1);
/* 467 */         int b = raster.getSample(x, y, 2);
/* 468 */         int a = alpha.getSample(x, y, 0);
/*     */ 
/*     */         
/* 471 */         out.writeByte(b);
/* 472 */         out.writeByte(g);
/* 473 */         out.writeByte(r);
/* 474 */         out.writeByte(a);
/*     */       } 
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
/*     */   private static byte setBit(byte bits, int index, int bit) {
/* 487 */     if (bit == 0) {
/* 488 */       bits = (byte)(bits & (1 << 7 - index ^ 0xFFFFFFFF));
/*     */     } else {
/* 490 */       bits = (byte)(bits | 1 << 7 - index);
/*     */     } 
/* 492 */     return bits;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static byte setNibble(byte nibbles, int index, int nibble) {
/* 502 */     nibbles = (byte)(nibbles | nibble << (1 - index) * 4);
/*     */     
/* 504 */     return nibbles;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getColorMapSize(short sBitCount) {
/* 514 */     int ret = 0;
/* 515 */     if (sBitCount <= 8) {
/* 516 */       ret = (1 << sBitCount) * 4;
/*     */     }
/* 518 */     return ret;
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\net\sf\image4j\codec\bmp\BMPEncoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
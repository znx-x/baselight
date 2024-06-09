/*     */ package net.sf.image4j.codec.bmp;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.IndexColorModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import net.sf.image4j.io.CountingInputStream;
/*     */ import net.sf.image4j.io.LittleEndianInputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BMPDecoder
/*     */ {
/*     */   private BufferedImage img;
/*     */   private InfoHeader infoHeader;
/*     */   
/*     */   public BMPDecoder(InputStream in) throws IOException {
/*  28 */     LittleEndianInputStream lis = new LittleEndianInputStream(new CountingInputStream(in));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  33 */     byte[] bsignature = new byte[2];
/*  34 */     lis.read(bsignature);
/*  35 */     String signature = new String(bsignature, "UTF-8");
/*     */     
/*  37 */     if (!signature.equals("BM")) {
/*  38 */       throw new IOException("Invalid signature '" + signature + "' for BMP format");
/*     */     }
/*     */ 
/*     */     
/*  42 */     int fileSize = lis.readIntLE();
/*     */ 
/*     */     
/*  45 */     int reserved = lis.readIntLE();
/*     */ 
/*     */     
/*  48 */     int dataOffset = lis.readIntLE();
/*     */ 
/*     */ 
/*     */     
/*  52 */     this.infoHeader = readInfoHeader(lis);
/*     */ 
/*     */ 
/*     */     
/*  56 */     this.img = read(this.infoHeader, lis);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getBit(int bits, int index) {
/*  66 */     return bits >> 7 - index & 0x1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getNibble(int nibbles, int index) {
/*  76 */     return nibbles >> 4 * (1 - index) & 0xF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InfoHeader getInfoHeader() {
/*  85 */     return this.infoHeader;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getBufferedImage() {
/*  93 */     return this.img;
/*     */   }
/*     */   
/*     */   private static void getColorTable(ColorEntry[] colorTable, byte[] ar, byte[] ag, byte[] ab) {
/*  97 */     for (int i = 0; i < colorTable.length; i++) {
/*  98 */       ar[i] = (byte)(colorTable[i]).bRed;
/*  99 */       ag[i] = (byte)(colorTable[i]).bGreen;
/* 100 */       ab[i] = (byte)(colorTable[i]).bBlue;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static InfoHeader readInfoHeader(LittleEndianInputStream lis) throws IOException {
/* 111 */     InfoHeader infoHeader = new InfoHeader(lis);
/* 112 */     return infoHeader;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static InfoHeader readInfoHeader(LittleEndianInputStream lis, int infoSize) throws IOException {
/* 119 */     InfoHeader infoHeader = new InfoHeader(lis, infoSize);
/* 120 */     return infoHeader;
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
/*     */   public static BufferedImage read(InfoHeader infoHeader, LittleEndianInputStream lis) throws IOException {
/* 133 */     BufferedImage img = null;
/*     */ 
/*     */ 
/*     */     
/* 137 */     ColorEntry[] colorTable = null;
/*     */ 
/*     */     
/* 140 */     if (infoHeader.sBitCount <= 8) {
/* 141 */       colorTable = readColorTable(infoHeader, lis);
/*     */     }
/*     */     
/* 144 */     img = read(infoHeader, lis, colorTable);
/*     */     
/* 146 */     return img;
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
/*     */   public static BufferedImage read(InfoHeader infoHeader, LittleEndianInputStream lis, ColorEntry[] colorTable) throws IOException {
/* 162 */     BufferedImage img = null;
/*     */ 
/*     */     
/* 165 */     if (infoHeader.sBitCount == 1 && infoHeader.iCompression == 0) {
/*     */       
/* 167 */       img = read1(infoHeader, lis, colorTable);
/*     */ 
/*     */     
/*     */     }
/* 171 */     else if (infoHeader.sBitCount == 4 && infoHeader.iCompression == 0) {
/*     */       
/* 173 */       img = read4(infoHeader, lis, colorTable);
/*     */ 
/*     */     
/*     */     }
/* 177 */     else if (infoHeader.sBitCount == 8 && infoHeader.iCompression == 0) {
/*     */       
/* 179 */       img = read8(infoHeader, lis, colorTable);
/*     */ 
/*     */     
/*     */     }
/* 183 */     else if (infoHeader.sBitCount == 24 && infoHeader.iCompression == 0) {
/*     */       
/* 185 */       img = read24(infoHeader, lis);
/*     */ 
/*     */     
/*     */     }
/* 189 */     else if (infoHeader.sBitCount == 32 && infoHeader.iCompression == 0) {
/*     */       
/* 191 */       img = read32(infoHeader, lis);
/*     */     } else {
/*     */       
/* 194 */       throw new IOException("Unrecognized bitmap format: bit count=" + infoHeader.sBitCount + ", compression=" + infoHeader.iCompression);
/*     */     } 
/*     */ 
/*     */     
/* 198 */     return img;
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
/*     */   public static ColorEntry[] readColorTable(InfoHeader infoHeader, LittleEndianInputStream lis) throws IOException {
/* 211 */     ColorEntry[] colorTable = new ColorEntry[infoHeader.iNumColors];
/* 212 */     for (int i = 0; i < infoHeader.iNumColors; i++) {
/* 213 */       ColorEntry ce = new ColorEntry(lis);
/* 214 */       colorTable[i] = ce;
/*     */     } 
/* 216 */     return colorTable;
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
/*     */   public static BufferedImage read1(InfoHeader infoHeader, LittleEndianInputStream lis, ColorEntry[] colorTable) throws IOException {
/* 236 */     byte[] ar = new byte[colorTable.length];
/* 237 */     byte[] ag = new byte[colorTable.length];
/* 238 */     byte[] ab = new byte[colorTable.length];
/*     */     
/* 240 */     getColorTable(colorTable, ar, ag, ab);
/*     */     
/* 242 */     IndexColorModel icm = new IndexColorModel(1, 2, ar, ag, ab);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 247 */     BufferedImage img = new BufferedImage(infoHeader.iWidth, infoHeader.iHeight, 12, icm);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 255 */     WritableRaster raster = img.getRaster();
/*     */ 
/*     */ 
/*     */     
/* 259 */     int dataBitsPerLine = infoHeader.iWidth;
/* 260 */     int bitsPerLine = dataBitsPerLine;
/* 261 */     if (bitsPerLine % 32 != 0) {
/* 262 */       bitsPerLine = (bitsPerLine / 32 + 1) * 32;
/*     */     }
/* 264 */     int padBits = bitsPerLine - dataBitsPerLine;
/* 265 */     int padBytes = padBits / 8;
/*     */     
/* 267 */     int bytesPerLine = bitsPerLine / 8;
/* 268 */     int[] line = new int[bytesPerLine];
/*     */     
/* 270 */     for (int y = infoHeader.iHeight - 1; y >= 0; y--) {
/* 271 */       for (int i = 0; i < bytesPerLine; i++) {
/* 272 */         line[i] = lis.readUnsignedByte();
/*     */       }
/*     */       
/* 275 */       for (int x = 0; x < infoHeader.iWidth; x++) {
/* 276 */         int j = x / 8;
/* 277 */         int v = line[j];
/* 278 */         int b = x % 8;
/* 279 */         int index = getBit(v, b);
/*     */ 
/*     */ 
/*     */         
/* 283 */         raster.setSample(x, y, 0, index);
/*     */       } 
/*     */     } 
/*     */     
/* 287 */     return img;
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
/*     */   public static BufferedImage read4(InfoHeader infoHeader, LittleEndianInputStream lis, ColorEntry[] colorTable) throws IOException {
/* 308 */     byte[] ar = new byte[colorTable.length];
/* 309 */     byte[] ag = new byte[colorTable.length];
/* 310 */     byte[] ab = new byte[colorTable.length];
/*     */     
/* 312 */     getColorTable(colorTable, ar, ag, ab);
/*     */     
/* 314 */     IndexColorModel icm = new IndexColorModel(4, infoHeader.iNumColors, ar, ag, ab);
/*     */ 
/*     */ 
/*     */     
/* 318 */     BufferedImage img = new BufferedImage(infoHeader.iWidth, infoHeader.iHeight, 12, icm);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 324 */     WritableRaster raster = img.getRaster();
/*     */ 
/*     */     
/* 327 */     int bitsPerLine = infoHeader.iWidth * 4;
/* 328 */     if (bitsPerLine % 32 != 0) {
/* 329 */       bitsPerLine = (bitsPerLine / 32 + 1) * 32;
/*     */     }
/* 331 */     int bytesPerLine = bitsPerLine / 8;
/*     */     
/* 333 */     int[] line = new int[bytesPerLine];
/*     */     
/* 335 */     for (int y = infoHeader.iHeight - 1; y >= 0; y--) {
/*     */       
/* 337 */       for (int i = 0; i < bytesPerLine; i++) {
/* 338 */         int b = lis.readUnsignedByte();
/* 339 */         line[i] = b;
/*     */       } 
/*     */ 
/*     */       
/* 343 */       for (int x = 0; x < infoHeader.iWidth; x++) {
/*     */         
/* 345 */         int b = x / 2;
/* 346 */         int j = x % 2;
/* 347 */         int n = line[b];
/* 348 */         int index = getNibble(n, j);
/* 349 */         raster.setSample(x, y, 0, index);
/*     */       } 
/*     */     } 
/*     */     
/* 353 */     return img;
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
/*     */   public static BufferedImage read8(InfoHeader infoHeader, LittleEndianInputStream lis, ColorEntry[] colorTable) throws IOException {
/* 375 */     byte[] ar = new byte[colorTable.length];
/* 376 */     byte[] ag = new byte[colorTable.length];
/* 377 */     byte[] ab = new byte[colorTable.length];
/*     */     
/* 379 */     getColorTable(colorTable, ar, ag, ab);
/*     */     
/* 381 */     IndexColorModel icm = new IndexColorModel(8, infoHeader.iNumColors, ar, ag, ab);
/*     */ 
/*     */ 
/*     */     
/* 385 */     BufferedImage img = new BufferedImage(infoHeader.iWidth, infoHeader.iHeight, 13, icm);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 391 */     WritableRaster raster = img.getRaster();
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
/* 405 */     int dataPerLine = infoHeader.iWidth;
/* 406 */     int bytesPerLine = dataPerLine;
/* 407 */     if (bytesPerLine % 4 != 0) {
/* 408 */       bytesPerLine = (bytesPerLine / 4 + 1) * 4;
/*     */     }
/* 410 */     int padBytesPerLine = bytesPerLine - dataPerLine;
/*     */     
/* 412 */     for (int y = infoHeader.iHeight - 1; y >= 0; y--) {
/* 413 */       for (int x = 0; x < infoHeader.iWidth; x++) {
/* 414 */         int b = lis.readUnsignedByte();
/*     */ 
/*     */ 
/*     */         
/* 418 */         raster.setSample(x, y, 0, b);
/*     */       } 
/*     */       
/* 421 */       lis.skip(padBytesPerLine);
/*     */     } 
/*     */     
/* 424 */     return img;
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
/*     */   public static BufferedImage read24(InfoHeader infoHeader, LittleEndianInputStream lis) throws IOException {
/* 444 */     BufferedImage img = new BufferedImage(infoHeader.iWidth, infoHeader.iHeight, 1);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 449 */     WritableRaster raster = img.getRaster();
/*     */ 
/*     */     
/* 452 */     int dataPerLine = infoHeader.iWidth * 3;
/* 453 */     int bytesPerLine = dataPerLine;
/* 454 */     if (bytesPerLine % 4 != 0) {
/* 455 */       bytesPerLine = (bytesPerLine / 4 + 1) * 4;
/*     */     }
/* 457 */     int padBytesPerLine = bytesPerLine - dataPerLine;
/*     */     
/* 459 */     for (int y = infoHeader.iHeight - 1; y >= 0; y--) {
/* 460 */       for (int x = 0; x < infoHeader.iWidth; x++) {
/* 461 */         int b = lis.readUnsignedByte();
/* 462 */         int g = lis.readUnsignedByte();
/* 463 */         int r = lis.readUnsignedByte();
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 468 */         raster.setSample(x, y, 0, r);
/* 469 */         raster.setSample(x, y, 1, g);
/* 470 */         raster.setSample(x, y, 2, b);
/*     */       } 
/* 472 */       lis.skip(padBytesPerLine);
/*     */     } 
/*     */     
/* 475 */     return img;
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
/*     */   public static BufferedImage read32(InfoHeader infoHeader, LittleEndianInputStream lis) throws IOException {
/* 496 */     BufferedImage img = new BufferedImage(infoHeader.iWidth, infoHeader.iHeight, 2);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 501 */     WritableRaster rgb = img.getRaster();
/* 502 */     WritableRaster alpha = img.getAlphaRaster();
/*     */     
/* 504 */     for (int y = infoHeader.iHeight - 1; y >= 0; y--) {
/* 505 */       for (int x = 0; x < infoHeader.iWidth; x++) {
/* 506 */         int b = lis.readUnsignedByte();
/* 507 */         int g = lis.readUnsignedByte();
/* 508 */         int r = lis.readUnsignedByte();
/* 509 */         int a = lis.readUnsignedByte();
/* 510 */         rgb.setSample(x, y, 0, r);
/* 511 */         rgb.setSample(x, y, 1, g);
/* 512 */         rgb.setSample(x, y, 2, b);
/* 513 */         alpha.setSample(x, y, 0, a);
/*     */       } 
/*     */     } 
/*     */     
/* 517 */     return img;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage read(File file) throws IOException {
/* 527 */     FileInputStream fin = new FileInputStream(file);
/*     */     try {
/* 529 */       return read(new BufferedInputStream(fin));
/*     */     } finally {
/*     */       try {
/* 532 */         fin.close();
/* 533 */       } catch (IOException iOException) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage read(InputStream in) throws IOException {
/* 544 */     BMPDecoder d = new BMPDecoder(in);
/* 545 */     return d.getBufferedImage();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BMPImage readExt(File file) throws IOException {
/* 556 */     FileInputStream fin = new FileInputStream(file);
/*     */     try {
/* 558 */       return readExt(new BufferedInputStream(fin));
/*     */     } finally {
/*     */       try {
/* 561 */         fin.close();
/* 562 */       } catch (IOException iOException) {}
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
/*     */   public static BMPImage readExt(InputStream in) throws IOException {
/* 574 */     BMPDecoder d = new BMPDecoder(in);
/* 575 */     BMPImage ret = new BMPImage(d.getBufferedImage(), d.getInfoHeader());
/* 576 */     return ret;
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\net\sf\image4j\codec\bmp\BMPDecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
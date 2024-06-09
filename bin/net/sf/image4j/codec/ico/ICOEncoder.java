/*     */ package net.sf.image4j.codec.ico;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.IndexColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.imageio.ImageWriter;
/*     */ import javax.imageio.stream.ImageOutputStream;
/*     */ import net.sf.image4j.codec.bmp.BMPEncoder;
/*     */ import net.sf.image4j.codec.bmp.InfoHeader;
/*     */ import net.sf.image4j.io.LittleEndianOutputStream;
/*     */ import net.sf.image4j.util.ConvertUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ICOEncoder
/*     */ {
/*     */   public static void write(BufferedImage image, File file) throws IOException {
/*  43 */     write(image, -1, file);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void write(BufferedImage image, OutputStream os) throws IOException {
/*  53 */     write(image, -1, os);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void write(List<BufferedImage> images, OutputStream os) throws IOException {
/*  63 */     write(images, (int[])null, (boolean[])null, os);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void write(List<BufferedImage> images, File file) throws IOException {
/*  73 */     write(images, (int[])null, file);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void write(List<BufferedImage> images, int[] bpp, File file) throws IOException {
/*  84 */     write(images, bpp, new FileOutputStream(file));
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
/*     */   public static void write(List<BufferedImage> images, int[] bpp, boolean[] compress, File file) throws IOException {
/*  97 */     write(images, bpp, compress, new FileOutputStream(file));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void write(BufferedImage image, int bpp, File file) throws IOException {
/* 108 */     FileOutputStream fout = new FileOutputStream(file);
/*     */     try {
/* 110 */       BufferedOutputStream out = new BufferedOutputStream(fout);
/* 111 */       write(image, bpp, out);
/* 112 */       out.flush();
/*     */     } finally {
/*     */       try {
/* 115 */         fout.close();
/* 116 */       } catch (IOException iOException) {}
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
/*     */   public static void write(BufferedImage image, int bpp, OutputStream os) throws IOException {
/* 129 */     List<BufferedImage> list = new ArrayList<>(1);
/* 130 */     list.add(image);
/* 131 */     write(list, new int[] { bpp }, new boolean[] { false }, os);
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
/*     */   public static void write(List<BufferedImage> images, int[] bpp, OutputStream os) throws IOException {
/* 143 */     write(images, bpp, (boolean[])null, os);
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
/*     */   public static void write(List<BufferedImage> images, int[] bpp, boolean[] compress, OutputStream os) throws IOException {
/* 156 */     LittleEndianOutputStream out = new LittleEndianOutputStream(os);
/*     */     
/* 158 */     int count = images.size();
/*     */ 
/*     */     
/* 161 */     writeFileHeader(count, 1, out);
/*     */ 
/*     */     
/* 164 */     int fileOffset = 6 + count * 16;
/*     */     
/* 166 */     List<InfoHeader> infoHeaders = new ArrayList<>(count);
/*     */     
/* 168 */     List<BufferedImage> converted = new ArrayList<>(count);
/*     */     
/* 170 */     List<byte[]> compressedImages = null;
/* 171 */     if (compress != null) {
/* 172 */       compressedImages = (List)new ArrayList<>(count);
/*     */     }
/*     */     
/* 175 */     ImageWriter pngWriter = null;
/*     */     
/*     */     int i;
/* 178 */     for (i = 0; i < count; i++) {
/* 179 */       BufferedImage img = images.get(i);
/* 180 */       int b = (bpp == null) ? -1 : bpp[i];
/*     */       
/* 182 */       BufferedImage imgc = (b == -1) ? img : convert(img, b);
/* 183 */       converted.add(imgc);
/*     */       
/* 185 */       InfoHeader ih = BMPEncoder.createInfoHeader(imgc);
/*     */       
/* 187 */       IconEntry e = createIconEntry(ih);
/*     */       
/* 189 */       if (compress != null) {
/* 190 */         if (compress[i]) {
/* 191 */           if (pngWriter == null) {
/* 192 */             pngWriter = getPNGImageWriter();
/*     */           }
/* 194 */           byte[] compressedImage = encodePNG(pngWriter, imgc);
/* 195 */           compressedImages.add(compressedImage);
/* 196 */           e.iSizeInBytes = compressedImage.length;
/*     */         } else {
/* 198 */           compressedImages.add(null);
/*     */         } 
/*     */       }
/*     */       
/* 202 */       ih.iHeight *= 2;
/*     */       
/* 204 */       e.iFileOffset = fileOffset;
/* 205 */       fileOffset += e.iSizeInBytes;
/*     */       
/* 207 */       e.write(out);
/*     */       
/* 209 */       infoHeaders.add(ih);
/*     */     } 
/*     */ 
/*     */     
/* 213 */     for (i = 0; i < count; i++) {
/* 214 */       BufferedImage img = images.get(i);
/* 215 */       BufferedImage imgc = converted.get(i);
/*     */       
/* 217 */       if (compress == null || !compress[i]) {
/*     */ 
/*     */         
/* 220 */         InfoHeader ih = infoHeaders.get(i);
/* 221 */         ih.write(out);
/*     */         
/* 223 */         if (ih.sBitCount <= 8) {
/* 224 */           IndexColorModel icm = (IndexColorModel)imgc.getColorModel();
/* 225 */           BMPEncoder.writeColorMap(icm, out);
/*     */         } 
/*     */         
/* 228 */         writeXorBitmap(imgc, ih, out);
/*     */         
/* 230 */         writeAndBitmap(img, out);
/*     */       }
/*     */       else {
/*     */         
/* 234 */         byte[] compressedImage = compressedImages.get(i);
/* 235 */         out.write(compressedImage);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeFileHeader(int count, int type, LittleEndianOutputStream out) throws IOException {
/* 253 */     out.writeShortLE((short)0);
/*     */     
/* 255 */     out.writeShortLE((short)type);
/*     */     
/* 257 */     out.writeShortLE((short)count);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IconEntry createIconEntry(InfoHeader ih) {
/* 267 */     IconEntry ret = new IconEntry();
/*     */     
/* 269 */     ret.bWidth = (ih.iWidth == 256) ? 0 : ih.iWidth;
/*     */     
/* 271 */     ret.bHeight = (ih.iHeight == 256) ? 0 : ih.iHeight;
/*     */     
/* 273 */     ret.bColorCount = (ih.iNumColors >= 256) ? 0 : ih.iNumColors;
/*     */     
/* 275 */     ret.bReserved = 0;
/*     */     
/* 277 */     ret.sPlanes = 1;
/*     */     
/* 279 */     ret.sBitCount = ih.sBitCount;
/*     */     
/* 281 */     int cmapSize = BMPEncoder.getColorMapSize(ih.sBitCount);
/* 282 */     int xorSize = BMPEncoder.getBitmapSize(ih.iWidth, ih.iHeight, ih.sBitCount);
/* 283 */     int andSize = BMPEncoder.getBitmapSize(ih.iWidth, ih.iHeight, 1);
/* 284 */     int size = ih.iSize + cmapSize + xorSize + andSize;
/* 285 */     ret.iSizeInBytes = size;
/*     */     
/* 287 */     ret.iFileOffset = 0;
/* 288 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeAndBitmap(BufferedImage img, LittleEndianOutputStream out) throws IOException {
/* 298 */     WritableRaster alpha = img.getAlphaRaster();
/*     */ 
/*     */     
/* 301 */     if (img.getColorModel() instanceof IndexColorModel && img.getColorModel().hasAlpha()) {
/* 302 */       int w = img.getWidth();
/* 303 */       int h = img.getHeight();
/*     */       
/* 305 */       int bytesPerLine = BMPEncoder.getBytesPerLine1(w);
/*     */       
/* 307 */       byte[] line = new byte[bytesPerLine];
/*     */       
/* 309 */       IndexColorModel icm = (IndexColorModel)img.getColorModel();
/* 310 */       Raster raster = img.getRaster();
/*     */       
/* 312 */       for (int y = h - 1; y >= 0; y--)
/*     */       {
/* 314 */         for (int x = 0; x < w; x++) {
/* 315 */           int bi = x / 8;
/* 316 */           int i = x % 8;
/*     */           
/* 318 */           int p = raster.getSample(x, y, 0);
/* 319 */           int a = icm.getAlpha(p);
/*     */           
/* 321 */           int b = (a ^ 0xFFFFFFFF) & 0x1;
/* 322 */           line[bi] = setBit(line[bi], i, b);
/*     */         } 
/*     */         
/* 325 */         out.write(line);
/*     */       }
/*     */     
/*     */     }
/* 329 */     else if (alpha == null) {
/* 330 */       int h = img.getHeight();
/* 331 */       int w = img.getWidth();
/*     */       
/* 333 */       int bytesPerLine = BMPEncoder.getBytesPerLine1(w);
/*     */       
/* 335 */       byte[] line = new byte[bytesPerLine];
/* 336 */       for (int i = 0; i < bytesPerLine; i++) {
/* 337 */         line[i] = 0;
/*     */       }
/*     */       
/* 340 */       for (int y = h - 1; y >= 0; y--) {
/* 341 */         out.write(line);
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 348 */       int w = img.getWidth();
/* 349 */       int h = img.getHeight();
/*     */       
/* 351 */       int bytesPerLine = BMPEncoder.getBytesPerLine1(w);
/*     */       
/* 353 */       byte[] line = new byte[bytesPerLine];
/*     */       
/* 355 */       for (int y = h - 1; y >= 0; y--) {
/*     */         
/* 357 */         for (int x = 0; x < w; x++) {
/* 358 */           int bi = x / 8;
/* 359 */           int i = x % 8;
/* 360 */           int a = alpha.getSample(x, y, 0);
/*     */           
/* 362 */           int b = (a ^ 0xFFFFFFFF) & 0x1;
/* 363 */           line[bi] = setBit(line[bi], i, b);
/*     */         } 
/*     */         
/* 366 */         out.write(line);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static byte setBit(byte bits, int index, int bit) {
/* 373 */     int mask = 1 << 7 - index;
/* 374 */     bits = (byte)(bits & (mask ^ 0xFFFFFFFF));
/* 375 */     bits = (byte)(bits | bit << 7 - index);
/* 376 */     return bits;
/*     */   }
/*     */   
/*     */   private static void writeXorBitmap(BufferedImage img, InfoHeader ih, LittleEndianOutputStream out) throws IOException {
/* 380 */     Raster alpha, raster = img.getRaster();
/* 381 */     switch (ih.sBitCount) {
/*     */       case 1:
/* 383 */         BMPEncoder.write1(raster, out);
/*     */         break;
/*     */       case 4:
/* 386 */         BMPEncoder.write4(raster, out);
/*     */         break;
/*     */       case 8:
/* 389 */         BMPEncoder.write8(raster, out);
/*     */         break;
/*     */       case 24:
/* 392 */         BMPEncoder.write24(raster, out);
/*     */         break;
/*     */       case 32:
/* 395 */         alpha = img.getAlphaRaster();
/* 396 */         BMPEncoder.write32(raster, alpha, out);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage convert(BufferedImage img, int bpp) {
/*     */     int b, b2;
/* 408 */     BufferedImage ret = null;
/* 409 */     switch (bpp) {
/*     */       case 1:
/* 411 */         ret = ConvertUtil.convert1(img);
/*     */         break;
/*     */       case 4:
/* 414 */         ret = ConvertUtil.convert4(img);
/*     */         break;
/*     */       case 8:
/* 417 */         ret = ConvertUtil.convert8(img);
/*     */         break;
/*     */       case 24:
/* 420 */         b = img.getColorModel().getPixelSize();
/* 421 */         if (b == 24 || b == 32) {
/* 422 */           ret = img; break;
/*     */         } 
/* 424 */         ret = ConvertUtil.convert24(img);
/*     */         break;
/*     */       
/*     */       case 32:
/* 428 */         b2 = img.getColorModel().getPixelSize();
/* 429 */         if (b2 == 24 || b2 == 32) {
/* 430 */           ret = img; break;
/*     */         } 
/* 432 */         ret = ConvertUtil.convert32(img);
/*     */         break;
/*     */     } 
/*     */     
/* 436 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static ImageWriter getPNGImageWriter() {
/* 443 */     ImageWriter ret = null;
/* 444 */     Iterator<ImageWriter> itr = ImageIO.getImageWritersByFormatName("png");
/* 445 */     if (itr.hasNext()) {
/* 446 */       ret = itr.next();
/*     */     }
/* 448 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static byte[] encodePNG(ImageWriter pngWriter, BufferedImage img) throws IOException {
/* 455 */     ByteArrayOutputStream bout = new ByteArrayOutputStream();
/* 456 */     ImageOutputStream output = ImageIO.createImageOutputStream(bout);
/* 457 */     pngWriter.setOutput(output);
/* 458 */     pngWriter.write(img);
/* 459 */     bout.flush();
/* 460 */     byte[] ret = bout.toByteArray();
/* 461 */     return ret;
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\net\sf\image4j\codec\ico\ICOEncoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
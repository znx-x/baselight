/*     */ package net.sf.image4j.codec.ico;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.EOFException;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.imageio.ImageReader;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import net.sf.image4j.codec.bmp.BMPDecoder;
/*     */ import net.sf.image4j.codec.bmp.ColorEntry;
/*     */ import net.sf.image4j.codec.bmp.InfoHeader;
/*     */ import net.sf.image4j.io.LittleEndianInputStream;
/*     */ 
/*     */ public class ICODecoder {
/*  29 */   private static Logger log = Logger.getLogger(ICODecoder.class.getName());
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int PNG_MAGIC = -1991225785;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int PNG_MAGIC_LE = 1196314761;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int PNG_MAGIC2 = 218765834;
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int PNG_MAGIC2_LE = 169478669;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<BufferedImage> read(File file) throws IOException {
/*  54 */     FileInputStream fin = new FileInputStream(file);
/*     */     try {
/*  56 */       return read(new BufferedInputStream(fin));
/*     */     } finally {
/*     */       try {
/*  59 */         fin.close();
/*  60 */       } catch (IOException ex) {
/*  61 */         log.log(Level.FINE, "Failed to close file input for file " + file);
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
/*     */ 
/*     */   
/*     */   public static List<ICOImage> readExt(File file) throws IOException {
/*  81 */     FileInputStream fin = new FileInputStream(file);
/*     */     try {
/*  83 */       return readExt(new BufferedInputStream(fin));
/*     */     } finally {
/*     */       try {
/*  86 */         fin.close();
/*  87 */       } catch (IOException ex) {
/*  88 */         log.log(Level.WARNING, "Failed to close file input for file " + file, ex);
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
/*     */   public static List<BufferedImage> read(InputStream is) throws IOException {
/* 106 */     List<ICOImage> list = readExt(is);
/*     */     
/* 108 */     List<BufferedImage> ret = new ArrayList<>(list.size());
/* 109 */     for (int i = 0; i < list.size(); i++) {
/* 110 */       ICOImage icoImage = list.get(i);
/* 111 */       BufferedImage image = icoImage.getImage();
/* 112 */       ret.add(image);
/*     */     } 
/* 114 */     return ret;
/*     */   }
/*     */   
/*     */   private static IconEntry[] sortByFileOffset(IconEntry[] entries) {
/* 118 */     List<IconEntry> list = Arrays.asList(entries);
/* 119 */     Collections.sort(list, new Comparator<IconEntry>()
/*     */         {
/*     */           public int compare(IconEntry o1, IconEntry o2)
/*     */           {
/* 123 */             return o1.iFileOffset - o2.iFileOffset;
/*     */           }
/*     */         });
/* 126 */     return list.<IconEntry>toArray(new IconEntry[list.size()]);
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
/*     */   public static List<ICOImage> readExt(InputStream is) throws IOException {
/* 145 */     LittleEndianInputStream in = new LittleEndianInputStream(new CountingInputStream(is));
/*     */ 
/*     */ 
/*     */     
/* 149 */     short sReserved = in.readShortLE();
/*     */     
/* 151 */     short sType = in.readShortLE();
/*     */     
/* 153 */     short sCount = in.readShortLE();
/*     */ 
/*     */     
/* 156 */     IconEntry[] entries = new IconEntry[sCount]; short s;
/* 157 */     for (s = 0; s < sCount; s = (short)(s + 1)) {
/* 158 */       entries[s] = new IconEntry(in);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 163 */     int i = 0;
/*     */     
/* 165 */     List<ICOImage> ret = new ArrayList<>(sCount);
/*     */     
/*     */     try {
/* 168 */       for (i = 0; i < sCount; i++)
/*     */       {
/* 170 */         int fileOffset = in.getCount();
/* 171 */         if (fileOffset != (entries[i]).iFileOffset) {
/* 172 */           throw new IOException("Cannot read image #" + i + " starting at unexpected file offset.");
/*     */         }
/*     */         
/* 175 */         int info = in.readIntLE();
/* 176 */         log.log(Level.FINE, "Image #" + i + " @ " + in.getCount() + " info = " + 
/* 177 */             EndianUtils.toInfoString(info));
/* 178 */         if (info == 40) {
/*     */ 
/*     */ 
/*     */           
/* 182 */           InfoHeader infoHeader = BMPDecoder.readInfoHeader(in, info);
/* 183 */           InfoHeader andHeader = new InfoHeader(infoHeader);
/* 184 */           infoHeader.iHeight /= 2;
/* 185 */           InfoHeader xorHeader = new InfoHeader(infoHeader);
/* 186 */           xorHeader.iHeight = andHeader.iHeight;
/*     */           
/* 188 */           andHeader.sBitCount = 1;
/* 189 */           andHeader.iNumColors = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 194 */           BufferedImage xor = BMPDecoder.read(xorHeader, in);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 208 */           BufferedImage img = new BufferedImage(xorHeader.iWidth, xorHeader.iHeight, 2);
/*     */ 
/*     */           
/* 211 */           ColorEntry[] andColorTable = { new ColorEntry(255, 255, 255, 255), new ColorEntry(0, 0, 0, 0) };
/*     */ 
/*     */ 
/*     */           
/* 215 */           if (infoHeader.sBitCount == 32) {
/*     */ 
/*     */             
/* 218 */             int size = (entries[i]).iSizeInBytes;
/* 219 */             int infoHeaderSize = infoHeader.iSize;
/*     */             
/* 221 */             int dataSize = xorHeader.iWidth * xorHeader.iHeight * 4;
/* 222 */             int skip = size - infoHeaderSize - dataSize;
/*     */             
/* 224 */             int skip2 = (entries[i]).iFileOffset + size - in.getCount();
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 229 */             if (in.skip(skip, false) < skip && i < sCount - 1) {
/* 230 */               throw new EOFException("Unexpected end of input");
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 242 */             WritableRaster srgb = xor.getRaster();
/* 243 */             WritableRaster salpha = xor.getAlphaRaster();
/* 244 */             WritableRaster rgb = img.getRaster();
/* 245 */             WritableRaster alpha = img.getAlphaRaster();
/*     */             
/* 247 */             for (int y = xorHeader.iHeight - 1; y >= 0; y--) {
/* 248 */               for (int x = 0; x < xorHeader.iWidth; x++) {
/* 249 */                 int r = srgb.getSample(x, y, 0);
/* 250 */                 int g = srgb.getSample(x, y, 1);
/* 251 */                 int b = srgb.getSample(x, y, 2);
/* 252 */                 int a = salpha.getSample(x, y, 0);
/* 253 */                 rgb.setSample(x, y, 0, r);
/* 254 */                 rgb.setSample(x, y, 1, g);
/* 255 */                 rgb.setSample(x, y, 2, b);
/* 256 */                 alpha.setSample(x, y, 0, a);
/*     */               } 
/*     */             } 
/*     */           } else {
/*     */             
/* 261 */             BufferedImage and = BMPDecoder.read(andHeader, in, andColorTable);
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 266 */             WritableRaster srgb = xor.getRaster();
/* 267 */             WritableRaster rgb = img.getRaster();
/*     */             
/* 269 */             WritableRaster alpha = img.getAlphaRaster();
/* 270 */             WritableRaster salpha = and.getRaster();
/*     */             
/* 272 */             for (int y = 0; y < xorHeader.iHeight; y++) {
/* 273 */               for (int x = 0; x < xorHeader.iWidth; x++) {
/*     */                 
/* 275 */                 int c = xor.getRGB(x, y);
/* 276 */                 int r = c >> 16 & 0xFF;
/* 277 */                 int g = c >> 8 & 0xFF;
/* 278 */                 int b = c & 0xFF;
/*     */                 
/* 280 */                 rgb.setSample(x, y, 0, r);
/*     */                 
/* 282 */                 rgb.setSample(x, y, 1, g);
/*     */                 
/* 284 */                 rgb.setSample(x, y, 2, b);
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 289 */                 int a = and.getRGB(x, y);
/* 290 */                 alpha.setSample(x, y, 0, a);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 295 */           IconEntry iconEntry = entries[i];
/* 296 */           ICOImage icoImage = new ICOImage(img, infoHeader, iconEntry);
/* 297 */           icoImage.setPngCompressed(false);
/* 298 */           icoImage.setIconIndex(i);
/* 299 */           ret.add(icoImage);
/*     */ 
/*     */         
/*     */         }
/* 303 */         else if (info == 1196314761) {
/*     */           
/* 305 */           int info2 = in.readIntLE();
/*     */           
/* 307 */           if (info2 != 169478669) {
/* 308 */             throw new IOException("Unrecognized icon format for image #" + i);
/*     */           }
/*     */ 
/*     */           
/* 312 */           IconEntry e = entries[i];
/* 313 */           int size = e.iSizeInBytes - 8;
/* 314 */           byte[] pngData = new byte[size];
/* 315 */           in.readFully(pngData);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 320 */           ByteArrayOutputStream bout = new ByteArrayOutputStream();
/* 321 */           DataOutputStream dout = new DataOutputStream(bout);
/*     */           
/* 323 */           dout.writeInt(-1991225785);
/* 324 */           dout.writeInt(218765834);
/* 325 */           dout.write(pngData);
/* 326 */           byte[] pngData2 = bout.toByteArray();
/* 327 */           ByteArrayInputStream bin = new ByteArrayInputStream(pngData2);
/*     */ 
/*     */           
/* 330 */           ImageInputStream input = ImageIO.createImageInputStream(bin);
/* 331 */           ImageReader reader = getPNGImageReader();
/* 332 */           reader.setInput(input);
/* 333 */           BufferedImage img = reader.read(0);
/*     */ 
/*     */           
/* 336 */           IconEntry iconEntry = entries[i];
/* 337 */           ICOImage icoImage = new ICOImage(img, null, iconEntry);
/* 338 */           icoImage.setPngCompressed(true);
/* 339 */           icoImage.setIconIndex(i);
/* 340 */           ret.add(icoImage);
/*     */         } else {
/* 342 */           throw new IOException("Unrecognized icon format for image #" + i);
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 354 */     catch (IOException ex) {
/* 355 */       throw new IOException("Failed to read image # " + i, ex);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 361 */     return ret;
/*     */   }
/*     */   
/*     */   private static ImageReader getPNGImageReader() {
/* 365 */     ImageReader ret = null;
/*     */     
/* 367 */     Iterator<ImageReader> itr = ImageIO.getImageReadersByFormatName("png");
/* 368 */     if (itr.hasNext()) {
/* 369 */       ret = itr.next();
/*     */     }
/* 371 */     return ret;
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\net\sf\image4j\codec\ico\ICODecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
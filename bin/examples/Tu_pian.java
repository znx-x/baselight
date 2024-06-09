/*     */ package examples;
/*     */ 
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorConvertOp;
/*     */ import java.util.Arrays;
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
/*     */ public class Tu_pian
/*     */ {
/*     */   public static BufferedImage zoomImage(BufferedImage im, double resizeTimes) {
/*  23 */     BufferedImage result = null;
/*     */ 
/*     */     
/*     */     try {
/*  27 */       int width = im.getWidth();
/*  28 */       int height = im.getHeight();
/*     */       
/*  30 */       int toWidth = (int)(width * resizeTimes);
/*  31 */       int toHeight = (int)(height * resizeTimes);
/*     */       
/*  33 */       result = new BufferedImage(toWidth, toHeight, 2);
/*  34 */       result.getGraphics().drawImage(im.getScaledInstance(toWidth, toHeight, 4), 0, 0, null);
/*     */     }
/*  36 */     catch (Exception e) {
/*  37 */       System.out.println("创建缩略图发生异常" + e.getMessage());
/*     */     } 
/*  39 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int colorToRGB(int alpha, int red, int green, int blue) {
/*  44 */     int newPixel = 0;
/*  45 */     newPixel += alpha;
/*  46 */     newPixel <<= 8;
/*  47 */     newPixel += red;
/*  48 */     newPixel <<= 8;
/*  49 */     newPixel += green;
/*  50 */     newPixel <<= 8;
/*  51 */     newPixel += blue;
/*     */     
/*  53 */     return newPixel;
/*     */   }
/*     */   
/*     */   public static BufferedImage hui_du(BufferedImage image) {
/*  57 */     int width = image.getWidth();
/*  58 */     int height = image.getHeight();
/*  59 */     BufferedImage grayImage = new BufferedImage(width, height, image.getType());
/*     */     
/*  61 */     for (int i = 0; i < width; i++) {
/*  62 */       for (int j = 0; j < height; j++) {
/*  63 */         int color = image.getRGB(i, j);
/*  64 */         int a = color >> 24 & 0xFF;
/*  65 */         int r = color >> 16 & 0xFF;
/*  66 */         int g = color >> 8 & 0xFF;
/*  67 */         int b = color & 0xFF;
/*  68 */         if (a < 128) {
/*     */           
/*  70 */           a = 255; r = 255; g = 255; b = 255;
/*     */         } else {
/*     */           
/*  73 */           a = 255;
/*     */         } 
/*  75 */         int gray = 0;
/*     */         
/*  77 */         gray = (int)((r + g + b) / 3.0D);
/*  78 */         grayImage.setRGB(i, j, colorToRGB(a, gray, gray, gray));
/*     */       } 
/*     */     } 
/*  81 */     image = null;
/*  82 */     return grayImage;
/*     */   }
/*     */   public static BufferedImage hui_du2(BufferedImage image) {
/*  85 */     int width = image.getWidth();
/*  86 */     int height = image.getHeight();
/*  87 */     int[] pixels = new int[width * height];
/*  88 */     int[] pixels2 = new int[width * height];
/*  89 */     image.getRGB(0, 0, width, height, pixels, 0, width);
/*  90 */     image = null;
/*  91 */     for (int i = 0; i < pixels.length; i++) {
/*     */       
/*  93 */       int a = pixels[i] >> 24 & 0xFF;
/*  94 */       int r = pixels[i] >> 16 & 0xFF;
/*  95 */       int g = pixels[i] >> 8 & 0xFF;
/*  96 */       int b = pixels[i] & 0xFF;
/*  97 */       if (a < 128) {
/*     */         
/*  99 */         a = 255; r = 255; g = 255; b = 255;
/*     */       } else {
/*     */         
/* 102 */         a = 255;
/*     */       } 
/* 104 */       int gray = 0;
/* 105 */       gray = (int)((r + g + b) / 3.0D);
/* 106 */       pixels2[i] = colorToRGB(a, gray, gray, gray);
/*     */     } 
/* 108 */     BufferedImage mBitmap = new BufferedImage(width, height, 2);
/* 109 */     mBitmap.setRGB(0, 0, width, height, pixels2, 0, width);
/* 110 */     pixels = null;
/* 111 */     pixels2 = null;
/* 112 */     return mBitmap;
/*     */   }
/*     */   public static BufferedImage qu_you_xiao(BufferedImage image) {
/* 115 */     int width = image.getWidth();
/* 116 */     int height = image.getHeight();
/* 117 */     int[] pixels = new int[width * height];
/* 118 */     image.getRGB(0, 0, width, height, pixels, 0, width);
/* 119 */     int shang = 0, xia = 0, zuo = 0, you = 0; int i;
/* 120 */     for (i = 0; i < height; i++) {
/* 121 */       for (int j = 0; j < width; j++) {
/* 122 */         int grey = pixels[width * i + j];
/* 123 */         if ((grey & 0xFFFFFF) != 16777215) {
/*     */           
/* 125 */           shang = i;
/*     */           break;
/*     */         } 
/*     */       } 
/* 129 */       if (shang > 0)
/*     */         break; 
/* 131 */     }  for (i = height; i > 0; i--) {
/* 132 */       for (int j = 0; j < width; j++) {
/* 133 */         int grey = pixels[width * (i - 1) + j];
/* 134 */         if ((grey & 0xFFFFFF) != 16777215) {
/*     */           
/* 136 */           xia = i;
/*     */           break;
/*     */         } 
/*     */       } 
/* 140 */       if (xia > 0)
/*     */         break; 
/* 142 */     }  for (i = 0; i < width; i++) {
/* 143 */       for (int j = 0; j < height; j++) {
/* 144 */         int grey = pixels[width * j + i];
/* 145 */         if ((grey & 0xFFFFFF) != 16777215) {
/*     */           
/* 147 */           zuo = i; break;
/*     */         } 
/*     */       } 
/* 150 */       if (zuo > 0)
/*     */         break; 
/* 152 */     }  for (i = width; i > 0; i--) {
/* 153 */       for (int j = 0; j < height; j++) {
/* 154 */         int grey = pixels[width * j + i - 1];
/* 155 */         if ((grey & 0xFFFFFF) != 16777215) {
/*     */           
/* 157 */           you = i; break;
/*     */         } 
/*     */       } 
/* 160 */       if (you > 0)
/*     */         break; 
/* 162 */     }  if (zuo - 3 > 0) zuo -= 3; 
/* 163 */     if (shang - 3 > 0) shang -= 3; 
/* 164 */     if (you + 3 < width) you += 3; 
/* 165 */     if (xia + 3 < height) xia += 3; 
/* 166 */     return image.getSubimage(zuo, shang, you - zuo, xia - shang);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage convertGreyImgByFloyd(BufferedImage img, int zhi) {
/* 174 */     int width = img.getWidth();
/* 175 */     int height = img.getHeight();
/* 176 */     int[] pixels = new int[width * height];
/* 177 */     img.getRGB(0, 0, width, height, pixels, 0, width);
/*     */     
/* 179 */     int[] gray = new int[height * width];
/* 180 */     for (int i = 0; i < height; i++) {
/* 181 */       for (int k = 0; k < width; k++) {
/* 182 */         int grey = pixels[width * i + k];
/* 183 */         int red = (grey & 0xFF0000) >> 16;
/* 184 */         red += 128 - zhi;
/* 185 */         if (red > 255) red = 255; 
/* 186 */         if (red < 0) red = 0; 
/* 187 */         gray[width * i + k] = red;
/*     */       } 
/*     */     } 
/* 190 */     int e = 0;
/* 191 */     for (int j = 0; j < height; j++) {
/* 192 */       for (int k = 0; k < width; k++) {
/* 193 */         int g = gray[width * j + k];
/* 194 */         if (g >= 128) {
/* 195 */           pixels[width * j + k] = 33554431;
/* 196 */           e = g - 255;
/*     */         } else {
/* 198 */           pixels[width * j + k] = -16777216;
/* 199 */           e = g - 0;
/*     */         } 
/* 201 */         if (k < width - 1 && j < height - 1) {
/*     */           
/* 203 */           gray[width * j + k + 1] = gray[width * j + k + 1] + 3 * e / 8;
/*     */           
/* 205 */           gray[width * (j + 1) + k] = gray[width * (j + 1) + k] + 3 * e / 8;
/*     */           
/* 207 */           gray[width * (j + 1) + k + 1] = gray[width * (j + 1) + k + 1] + e / 4;
/* 208 */         } else if (k == width - 1 && j < height - 1) {
/*     */           
/* 210 */           gray[width * (j + 1) + k] = gray[width * (j + 1) + k] + 3 * e / 8;
/* 211 */         } else if (k < width - 1 && j == height - 1) {
/*     */           
/* 213 */           gray[width * j + k + 1] = gray[width * j + k + 1] + e / 4;
/*     */         } 
/*     */       } 
/*     */     } 
/* 217 */     img = null;
/*     */     
/* 219 */     BufferedImage mBitmap = new BufferedImage(width, height, 2);
/*     */     
/* 221 */     mBitmap.setRGB(0, 0, width, height, pixels, 0, width);
/* 222 */     return mBitmap;
/*     */   }
/*     */   public static BufferedImage heibai(BufferedImage bb, int zhi) {
/* 225 */     BufferedImage nb = bb.getSubimage(0, 0, bb.getWidth(), bb.getHeight());
/* 226 */     int[] pixels = new int[bb.getWidth() * bb.getHeight()];
/* 227 */     nb.getRGB(0, 0, nb.getWidth(), nb.getHeight(), pixels, 0, nb.getWidth());
/*     */     
/* 229 */     for (int i = 0; i < pixels.length; i++) {
/* 230 */       int clr = pixels[i];
/* 231 */       int a = clr >> 24 & 0xFF;
/* 232 */       int r = clr >> 16 & 0xFF;
/* 233 */       int g = clr >> 8 & 0xFF;
/* 234 */       int b = clr & 0xFF;
/* 235 */       int gray = 0;
/* 236 */       gray = (int)((r + g + b) / 3.0D);
/* 237 */       if (gray < zhi) {
/*     */ 
/*     */         
/* 240 */         pixels[i] = -16777216;
/*     */       }
/*     */       else {
/*     */         
/* 244 */         pixels[i] = 33554431;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 250 */     BufferedImage mBitmap = new BufferedImage(nb.getWidth(), nb.getHeight(), 2);
/*     */     
/* 252 */     mBitmap.setRGB(0, 0, nb.getWidth(), nb.getHeight(), pixels, 0, nb.getWidth());
/* 253 */     bb = null;
/* 254 */     nb = null;
/* 255 */     return mBitmap;
/*     */   }
/*     */   
/*     */   public static BufferedImage fanse(BufferedImage bb) {
/* 259 */     BufferedImage nb = bb.getSubimage(0, 0, bb.getWidth(), bb.getHeight());
/* 260 */     int[] pixels = new int[bb.getWidth() * bb.getHeight()];
/* 261 */     nb.getRGB(0, 0, nb.getWidth(), nb.getHeight(), pixels, 0, nb.getWidth());
/*     */     
/* 263 */     for (int i = 0; i < pixels.length; i++) {
/* 264 */       int clr = pixels[i];
/* 265 */       int red = (clr & 0xFF0000) >> 16;
/* 266 */       if (red == 255) {
/*     */ 
/*     */         
/* 269 */         pixels[i] = -16777216;
/*     */       }
/*     */       else {
/*     */         
/* 273 */         pixels[i] = 33554431;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 279 */     BufferedImage mBitmap = new BufferedImage(nb.getWidth(), nb.getHeight(), 2);
/*     */     
/* 281 */     mBitmap.setRGB(0, 0, nb.getWidth(), nb.getHeight(), pixels, 0, nb.getWidth());
/* 282 */     bb = null;
/* 283 */     nb = null;
/* 284 */     return mBitmap;
/*     */   }
/*     */   
/*     */   public static BufferedImage jing_xiang_x(BufferedImage bb) {
/* 288 */     BufferedImage nb = bb.getSubimage(0, 0, bb.getWidth(), bb.getHeight());
/* 289 */     int[] pixels = new int[bb.getWidth() * bb.getHeight()];
/* 290 */     int[] pixels2 = new int[bb.getWidth() * bb.getHeight()];
/* 291 */     nb.getRGB(0, 0, nb.getWidth(), nb.getHeight(), pixels, 0, nb.getWidth());
/*     */     
/* 293 */     int k = nb.getWidth();
/* 294 */     int g = nb.getHeight();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 299 */     for (int i = 0; i < g; i++) {
/*     */       
/* 301 */       for (int j = 0; j < k; j++)
/*     */       {
/* 303 */         pixels2[i * k + j] = pixels[i * k + k - j - 1];
/*     */       }
/*     */     } 
/* 306 */     BufferedImage mBitmap = new BufferedImage(nb.getWidth(), nb.getHeight(), 2);
/*     */     
/* 308 */     mBitmap.setRGB(0, 0, nb.getWidth(), nb.getHeight(), pixels2, 0, nb.getWidth());
/* 309 */     bb = null;
/* 310 */     nb = null;
/* 311 */     return mBitmap;
/*     */   }
/*     */   
/*     */   public static BufferedImage jing_xiang_y(BufferedImage bb) {
/* 315 */     BufferedImage nb = bb.getSubimage(0, 0, bb.getWidth(), bb.getHeight());
/* 316 */     int[] pixels = new int[bb.getWidth() * bb.getHeight()];
/* 317 */     int[] pixels2 = new int[bb.getWidth() * bb.getHeight()];
/* 318 */     nb.getRGB(0, 0, nb.getWidth(), nb.getHeight(), pixels, 0, nb.getWidth());
/*     */     
/* 320 */     int k = nb.getWidth();
/* 321 */     int g = nb.getHeight();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 326 */     for (int i = 0; i < k; i++) {
/*     */       
/* 328 */       for (int j = 0; j < g; j++)
/*     */       {
/* 330 */         pixels2[j * k + i] = pixels[(g - j - 1) * k + i];
/*     */       }
/*     */     } 
/* 333 */     BufferedImage mBitmap = new BufferedImage(nb.getWidth(), nb.getHeight(), 2);
/*     */     
/* 335 */     mBitmap.setRGB(0, 0, nb.getWidth(), nb.getHeight(), pixels2, 0, nb.getWidth());
/* 336 */     bb = null;
/* 337 */     nb = null;
/* 338 */     return mBitmap;
/*     */   }
/*     */   
/*     */   public static BufferedImage qu_lunkuo(BufferedImage img, int zhi) {
/* 342 */     int width = img.getWidth();
/* 343 */     int height = img.getHeight();
/* 344 */     int[] pixels = new int[width * height];
/* 345 */     int[] pixels2 = new int[(width + 2) * (height + 2)];
/* 346 */     Arrays.fill(pixels2, 16777215);
/*     */     
/* 348 */     img.getRGB(0, 0, width, height, pixels, 0, width);
/* 349 */     int[] gray = new int[(height + 2) * (width + 2)]; int i;
/* 350 */     for (i = 0; i < height + 2; i++) {
/* 351 */       for (int j = 0; j < width + 2; j++) {
/* 352 */         if (i == 0 || j == 0 || j == width + 1 || i == height + 1) {
/* 353 */           gray[(width + 2) * i + j] = 16777215;
/*     */         } else {
/* 355 */           int grey = pixels[width * (i - 1) + j - 1];
/* 356 */           int red = (grey & 0xFF0000) >> 16;
/*     */           
/* 358 */           if (red > zhi) {
/* 359 */             gray[(width + 2) * i + j] = 16777215;
/*     */           } else {
/* 361 */             gray[(width + 2) * i + j] = -16777216;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 366 */     height += 2;
/* 367 */     width += 2;
/*     */     
/* 369 */     for (i = 1; i < height; i++) {
/* 370 */       for (int j = 1; j < width; j++) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 375 */         if (gray[width * i + j] != gray[width * i + j - 1])
/*     */         {
/* 377 */           if (gray[width * i + j] == -16777216) {
/* 378 */             pixels2[width * i + j] = -16777216;
/*     */           } else {
/* 380 */             pixels2[width * i + j - 1] = -16777216;
/*     */           }  } 
/* 382 */         if (gray[width * i + j] != gray[width * (i - 1) + j])
/*     */         {
/* 384 */           if (gray[width * i + j] == -16777216) {
/* 385 */             pixels2[width * i + j] = -16777216;
/*     */           } else {
/* 387 */             pixels2[width * (i - 1) + j] = -16777216;
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 394 */     BufferedImage mBitmap = new BufferedImage(width, height, 2);
/*     */     
/* 396 */     mBitmap.setRGB(0, 0, width, height, pixels2, 0, width);
/* 397 */     img = null;
/* 398 */     return mBitmap;
/*     */   }
/*     */   static int[] getGray(int[] pixels, int width, int height) {
/* 401 */     int[] gray = new int[width * height];
/* 402 */     for (int i = 0; i < width - 1; i++) {
/* 403 */       for (int j = 0; j < height - 1; j++) {
/* 404 */         int index = width * j + i;
/* 405 */         int rgba = pixels[index];
/* 406 */         int g = ((rgba & 0xFF0000) >> 16) * 3 + ((rgba & 0xFF00) >> 8) * 6 + (rgba & 0xFF) * 1;
/* 407 */         gray[index] = g / 10;
/*     */       } 
/*     */     } 
/* 410 */     return gray;
/*     */   }
/*     */   static int[] getInverse(int[] gray) {
/* 413 */     int[] inverse = new int[gray.length];
/* 414 */     for (int i = 0, size = gray.length; i < size; i++) {
/* 415 */       inverse[i] = 255 - gray[i];
/*     */     }
/* 417 */     return inverse;
/*     */   }
/*     */   static int[] guassBlur(int[] inverse, int width, int height) {
/* 420 */     int[] guassBlur = new int[inverse.length];
/*     */     
/* 422 */     for (int i = 0; i < width; i++) {
/* 423 */       for (int j = 0; j < height; j++) {
/* 424 */         int temp = width * j + i;
/* 425 */         if (i == 0 || i == width - 1 || j == 0 || j == height - 1) {
/* 426 */           guassBlur[temp] = 0;
/*     */         } else {
/* 428 */           int i0 = width * (j - 1) + i - 1;
/* 429 */           int i1 = width * (j - 1) + i;
/* 430 */           int i2 = width * (j - 1) + i + 1;
/* 431 */           int i3 = width * j + i - 1;
/* 432 */           int i4 = width * j + i;
/* 433 */           int i5 = width * j + i + 1;
/* 434 */           int i6 = width * (j + 1) + i - 1;
/* 435 */           int i7 = width * (j + 1) + i;
/* 436 */           int i8 = width * (j + 1) + i + 1;
/* 437 */           int sum = inverse[i0] + 2 * inverse[i1] + inverse[i2] + 2 * inverse[i3] + 4 * inverse[i4] + 2 * inverse[i5] + inverse[i6] + 2 * inverse[i7] + inverse[i8];
/* 438 */           sum /= 16;
/* 439 */           guassBlur[temp] = sum;
/*     */         } 
/*     */       } 
/*     */     } 
/* 443 */     return guassBlur;
/*     */   }
/*     */ 
/*     */   
/*     */   static int[] deceasecolorCompound(int[] guassBlur, int[] gray, int width, int height) {
/* 448 */     int[] output = new int[guassBlur.length];
/* 449 */     for (int i = 0; i < width; i++) {
/* 450 */       for (int j = 0; j < height; j++) {
/* 451 */         int index = j * width + i;
/* 452 */         int b = guassBlur[index];
/* 453 */         int a = gray[index];
/*     */         
/* 455 */         int temp = a + a * b / (256 - b);
/* 456 */         float ex = (temp * temp) * 1.0F / 255.0F / 255.0F;
/* 457 */         temp = (int)(temp * ex);
/* 458 */         a = Math.min(temp, 255);
/* 459 */         output[index] = a;
/*     */       } 
/*     */     } 
/* 462 */     return output;
/*     */   }
/*     */   static BufferedImage create(int[] pixels, int[] output, int width, int height) {
/* 465 */     for (int i = 0, size = pixels.length; i < size; i++) {
/* 466 */       int gray = output[i];
/* 467 */       int pixel = pixels[i] & 0xFF000000 | gray << 16 | gray << 8 | gray;
/* 468 */       output[i] = pixel;
/*     */     } 
/* 470 */     BufferedImage mBitmap = new BufferedImage(width, height, 2);
/* 471 */     mBitmap.setRGB(0, 0, width, height, output, 0, width);
/* 472 */     return mBitmap;
/*     */   }
/*     */   public static BufferedImage su_miao(BufferedImage bitmap) {
/* 475 */     int width = bitmap.getWidth();
/* 476 */     int height = bitmap.getHeight();
/* 477 */     int[] pixels = new int[width * height];
/*     */     
/* 479 */     bitmap.getRGB(0, 0, width, height, pixels, 0, width);
/* 480 */     int[] gray = getGray(pixels, width, height);
/* 481 */     int[] inverse = getInverse(gray);
/* 482 */     int[] guassBlur = guassBlur(inverse, width, height);
/* 483 */     int[] output = deceasecolorCompound(guassBlur, gray, width, height);
/* 484 */     bitmap = null;
/* 485 */     return create(pixels, output, width, height);
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
/*     */   public static BufferedImage su_miao2(BufferedImage old) {
/* 501 */     BufferedImage b1 = hui_du2(old);
/*     */ 
/*     */     
/* 504 */     b1 = invert2(b1);
/*     */ 
/*     */     
/* 507 */     float[][] matric = gaussian2DKernel(3, 3.0F);
/* 508 */     b1 = convolution(b1, matric);
/*     */ 
/*     */     
/* 511 */     b1 = deceaseColorCompound(old, b1);
/*     */ 
/*     */     
/* 514 */     ColorSpace cs1 = ColorSpace.getInstance(1003);
/* 515 */     ColorConvertOp op1 = new ColorConvertOp(cs1, null);
/* 516 */     BufferedImage b2 = new BufferedImage(old.getWidth(), old.getHeight(), 1);
/* 517 */     op1.filter(b1, b2);
/*     */ 
/*     */     
/* 520 */     old = null;
/* 521 */     b1 = null;
/* 522 */     return b2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage shi_liang(BufferedImage old) {
/* 530 */     BufferedImage b1 = discolor(old);
/*     */ 
/*     */     
/* 533 */     b1 = invert(b1);
/*     */ 
/*     */     
/* 536 */     float[][] matric = gaussian2DKernel(3, 3.0F);
/* 537 */     b1 = convolution(b1, matric);
/*     */ 
/*     */     
/* 540 */     b1 = deceaseColorCompound(old, b1);
/*     */ 
/*     */     
/* 543 */     ColorSpace cs1 = ColorSpace.getInstance(1003);
/* 544 */     ColorConvertOp op1 = new ColorConvertOp(cs1, null);
/* 545 */     BufferedImage b2 = new BufferedImage(old.getWidth(), old.getHeight(), 1);
/* 546 */     op1.filter(b1, b2);
/*     */ 
/*     */     
/* 549 */     return b2;
/*     */   }
/*     */ 
/*     */   
/*     */   public static BufferedImage discolor(BufferedImage sourceImage) {
/* 554 */     int width = sourceImage.getWidth();
/* 555 */     int height = sourceImage.getHeight();
/* 556 */     BufferedImage retImage = new BufferedImage(width, height, 2);
/*     */     
/* 558 */     for (int i = 0; i < width; i++) {
/* 559 */       for (int j = 0; j < height; j++) {
/*     */         
/* 561 */         int color1 = sourceImage.getRGB(i, j);
/*     */         
/* 563 */         int a1 = color1 >> 24 & 0xFF;
/* 564 */         int r1 = color1 >> 16 & 0xFF;
/* 565 */         int g1 = color1 >> 8 & 0xFF;
/* 566 */         int b1 = color1 & 0xFF;
/*     */         
/* 568 */         double sumA = a1;
/* 569 */         double sumR = 0.0D;
/* 570 */         double sumG = 0.0D;
/* 571 */         double sumB = 0.0D;
/* 572 */         sumR = sumG = sumB = r1 * 0.299D + g1 * 0.587D + b1 * 0.114D;
/*     */         
/* 574 */         int result = (int)sumA << 24 | (int)sumR << 16 | (int)sumG << 8 | (int)sumB;
/*     */ 
/*     */         
/* 577 */         retImage.setRGB(i, j, result);
/*     */       } 
/*     */     } 
/* 580 */     return retImage;
/*     */   }
/*     */ 
/*     */   
/*     */   public static BufferedImage invert(BufferedImage sourceImage) {
/* 585 */     int width = sourceImage.getWidth();
/* 586 */     int height = sourceImage.getHeight();
/* 587 */     BufferedImage retImage = new BufferedImage(width, height, 2);
/*     */     
/* 589 */     for (int i = 0; i < width; i++) {
/* 590 */       for (int j = 0; j < height; j++) {
/*     */         
/* 592 */         int color1 = sourceImage.getRGB(i, j);
/*     */         
/* 594 */         int a1 = color1 >> 24 & 0xFF;
/* 595 */         int r1 = color1 >> 16 & 0xFF;
/* 596 */         int g1 = color1 >> 8 & 0xFF;
/* 597 */         int b1 = color1 & 0xFF;
/*     */         
/* 599 */         int a = a1;
/* 600 */         int r = 255 - r1;
/* 601 */         int g = 255 - g1;
/* 602 */         int b = 255 - b1;
/*     */         
/* 604 */         int result = a << 24 | r << 16 | g << 8 | b;
/* 605 */         if (result > 255) result = 255; 
/* 606 */         retImage.setRGB(i, j, result);
/*     */       } 
/*     */     } 
/* 609 */     return retImage;
/*     */   }
/*     */   
/*     */   public static BufferedImage invert2(BufferedImage sourceImage) {
/* 613 */     int width = sourceImage.getWidth();
/* 614 */     int height = sourceImage.getHeight();
/* 615 */     int[] pixels = new int[width * height];
/* 616 */     int[] pixels2 = new int[width * height];
/* 617 */     sourceImage.getRGB(0, 0, width, height, pixels, 0, width);
/* 618 */     for (int i = 0; i < pixels.length; i++) {
/*     */       
/* 620 */       int a1 = pixels[i] >> 24 & 0xFF;
/* 621 */       int r1 = pixels[i] >> 16 & 0xFF;
/* 622 */       int g1 = pixels[i] >> 8 & 0xFF;
/* 623 */       int b1 = pixels[i] & 0xFF;
/*     */       
/* 625 */       int a = a1;
/* 626 */       int r = 255 - r1;
/* 627 */       int g = 255 - g1;
/* 628 */       int b = 255 - b1;
/* 629 */       pixels2[i] = a << 24 | r << 16 | g << 8 | b;
/*     */     } 
/* 631 */     BufferedImage mBitmap = new BufferedImage(width, height, 2);
/* 632 */     mBitmap.setRGB(0, 0, width, height, pixels2, 0, width);
/* 633 */     pixels = null;
/* 634 */     pixels2 = null;
/* 635 */     return mBitmap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage deceaseColorCompound(BufferedImage sourceImage, BufferedImage targetImage) {
/* 643 */     int width = (sourceImage.getWidth() > targetImage.getWidth()) ? sourceImage.getWidth() : targetImage.getWidth();
/*     */     
/* 645 */     int height = (sourceImage.getHeight() > targetImage.getHeight()) ? sourceImage.getHeight() : targetImage.getHeight();
/* 646 */     BufferedImage retImage = new BufferedImage(width, height, 2);
/*     */     
/* 648 */     for (int i = 0; i < width; i++) {
/* 649 */       for (int j = 0; j < height; j++) {
/* 650 */         if (i >= sourceImage.getWidth() || j >= sourceImage.getHeight()) {
/* 651 */           if (i >= targetImage.getWidth() || j >= targetImage.getHeight()) {
/* 652 */             retImage.setRGB(i, j, 0);
/*     */           } else {
/*     */             
/* 655 */             retImage.setRGB(i, j, targetImage.getRGB(i, j));
/*     */           }
/*     */         
/* 658 */         } else if (i >= targetImage.getWidth() || j >= targetImage.getHeight()) {
/* 659 */           retImage.setRGB(i, j, sourceImage.getRGB(i, j));
/*     */         }
/*     */         else {
/*     */           
/* 663 */           int color1 = sourceImage.getRGB(i, j);
/* 664 */           int color2 = targetImage.getRGB(i, j);
/*     */           
/* 666 */           int a1 = color1 >> 24 & 0xFF;
/* 667 */           int r1 = color1 >> 16 & 0xFF;
/* 668 */           int g1 = color1 >> 8 & 0xFF;
/* 669 */           int b1 = color1 & 0xFF;
/*     */           
/* 671 */           int a2 = color2 >> 24 & 0xFF;
/* 672 */           int r2 = color2 >> 16 & 0xFF;
/* 673 */           int g2 = color2 >> 8 & 0xFF;
/* 674 */           int b2 = color2 & 0xFF;
/*     */           
/* 676 */           int a = deceaseColorChannel(a1, a2);
/* 677 */           int r = deceaseColorChannel(r1, r2);
/* 678 */           int g = deceaseColorChannel(g1, g2);
/* 679 */           int b = deceaseColorChannel(b1, b2);
/*     */           
/* 681 */           int result = a << 24 | r << 16 | g << 8 | b;
/* 682 */           retImage.setRGB(i, j, result);
/*     */         } 
/*     */       } 
/* 685 */     }  return retImage;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int deceaseColorChannel(int source, int target) {
/* 690 */     int result = source + source * target / (256 - target);
/* 691 */     return (result > 255) ? 255 : result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage convolution(BufferedImage image, float[][] kernel) {
/* 697 */     int width = image.getWidth();
/* 698 */     int height = image.getHeight();
/* 699 */     int radius = kernel.length / 2;
/* 700 */     BufferedImage retImage = new BufferedImage(width, height, 2);
/*     */     
/* 702 */     for (int i = 0; i < width; i++) {
/* 703 */       for (int j = 0; j < height; j++) {
/* 704 */         double sumA = 0.0D;
/* 705 */         double sumR = 0.0D;
/* 706 */         double sumG = 0.0D;
/* 707 */         double sumB = 0.0D;
/* 708 */         for (int x = i - radius; x <= i + radius; x++) {
/* 709 */           for (int y = j - radius; y <= j + radius; y++) {
/* 710 */             int posX = (x < 0) ? 0 : ((x >= width) ? (width - 1) : x);
/*     */             
/* 712 */             int posY = (y < 0) ? 0 : ((y >= height) ? (height - 1) : y);
/* 713 */             int color = image.getRGB(posX, posY);
/* 714 */             int a = color >> 24 & 0xFF;
/* 715 */             int r = color >> 16 & 0xFF;
/* 716 */             int g = color >> 8 & 0xFF;
/* 717 */             int b = color & 0xFF;
/*     */             
/* 719 */             int kelX = x - i + radius;
/* 720 */             int kelY = y - j + radius;
/* 721 */             sumA += (kernel[kelX][kelY] * a);
/* 722 */             sumR += (kernel[kelX][kelY] * r);
/* 723 */             sumG += (kernel[kelX][kelY] * g);
/* 724 */             sumB += (kernel[kelX][kelY] * b);
/*     */           } 
/*     */         } 
/* 727 */         int blurColor = (int)sumA << 24 | (int)sumR << 16 | (int)sumG << 8 | (int)sumB;
/*     */         
/* 729 */         retImage.setRGB(i, j, blurColor);
/*     */       } 
/*     */     } 
/* 732 */     return retImage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float[][] gaussian2DKernel(int radius, float sigma) {
/* 739 */     int length = 2 * radius;
/* 740 */     float[][] matric = new float[length + 1][length + 1];
/* 741 */     float sigmaSquare2 = 2.0F * sigma * sigma;
/* 742 */     float sum = 0.0F; int x;
/* 743 */     for (x = -radius; x <= radius; x++) {
/* 744 */       for (int y = -radius; y <= radius; y++) {
/* 745 */         matric[radius + x][radius + y] = (float)(Math.pow(Math.E, (-(x * x + y * y) / sigmaSquare2)) / Math.PI * sigmaSquare2);
/*     */ 
/*     */         
/* 748 */         sum += matric[radius + x][radius + y];
/*     */       } 
/*     */     } 
/* 751 */     for (x = 0; x < length; x++) {
/* 752 */       for (int y = 0; y < length; y++) {
/* 753 */         matric[x][y] = matric[x][y] / sum;
/*     */       }
/*     */     } 
/* 756 */     return matric;
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\examples\Tu_pian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
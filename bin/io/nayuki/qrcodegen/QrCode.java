/*     */ package io.nayuki.qrcodegen;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class QrCode
/*     */ {
/*     */   public final int version;
/*     */   public final int size;
/*     */   public final Ecc errorCorrectionLevel;
/*     */   public final int mask;
/*     */   private boolean[][] modules;
/*     */   private boolean[][] isFunction;
/*     */   public static final int MIN_VERSION = 1;
/*     */   public static final int MAX_VERSION = 40;
/*     */   private static final int PENALTY_N1 = 3;
/*     */   private static final int PENALTY_N2 = 3;
/*     */   private static final int PENALTY_N3 = 40;
/*     */   private static final int PENALTY_N4 = 10;
/*     */   
/*     */   public static QrCode encodeText(String text, Ecc ecl) {
/*  71 */     Objects.requireNonNull(text);
/*  72 */     Objects.requireNonNull(ecl);
/*  73 */     List<QrSegment> segs = QrSegment.makeSegments(text);
/*  74 */     return encodeSegments(segs, ecl);
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
/*     */   public static QrCode encodeBinary(byte[] data, Ecc ecl) {
/*  91 */     Objects.requireNonNull(data);
/*  92 */     Objects.requireNonNull(ecl);
/*  93 */     QrSegment seg = QrSegment.makeBytes(data);
/*  94 */     return encodeSegments(Arrays.asList(new QrSegment[] { seg }, ), ecl);
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
/*     */   public static QrCode encodeSegments(List<QrSegment> segs, Ecc ecl) {
/* 116 */     return encodeSegments(segs, ecl, 1, 40, -1, true);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static QrCode encodeSegments(List<QrSegment> segs, Ecc ecl, int minVersion, int maxVersion, int mask, boolean boostEcl) {
/*     */     int dataUsedBits;
/* 145 */     Objects.requireNonNull(segs);
/* 146 */     Objects.requireNonNull(ecl);
/* 147 */     if (1 > minVersion || minVersion > maxVersion || maxVersion > 40 || mask < -1 || mask > 7) {
/* 148 */       throw new IllegalArgumentException("Invalid value");
/*     */     }
/*     */     
/*     */     int version;
/* 152 */     for (version = minVersion;; version++) {
/* 153 */       int j = getNumDataCodewords(version, ecl) * 8;
/* 154 */       dataUsedBits = QrSegment.getTotalBits(segs, version);
/* 155 */       if (dataUsedBits != -1 && dataUsedBits <= j)
/*     */         break; 
/* 157 */       if (version >= maxVersion) {
/* 158 */         String msg = "Segment too long";
/* 159 */         if (dataUsedBits != -1)
/* 160 */           msg = String.format("Data length = %d bits, Max capacity = %d bits", new Object[] { Integer.valueOf(dataUsedBits), Integer.valueOf(j) }); 
/* 161 */         throw new DataTooLongException(msg);
/*     */       } 
/*     */     } 
/* 164 */     assert dataUsedBits != -1;
/*     */ 
/*     */     
/* 167 */     for (Ecc newEcl : Ecc.values()) {
/* 168 */       if (boostEcl && dataUsedBits <= getNumDataCodewords(version, newEcl) * 8) {
/* 169 */         ecl = newEcl;
/*     */       }
/*     */     } 
/*     */     
/* 173 */     BitBuffer bb = new BitBuffer();
/* 174 */     for (QrSegment seg : segs) {
/* 175 */       bb.appendBits(seg.mode.modeBits, 4);
/* 176 */       bb.appendBits(seg.numChars, seg.mode.numCharCountBits(version));
/* 177 */       bb.appendData(seg.data);
/*     */     } 
/* 179 */     assert bb.bitLength() == dataUsedBits;
/*     */ 
/*     */     
/* 182 */     int dataCapacityBits = getNumDataCodewords(version, ecl) * 8;
/* 183 */     assert bb.bitLength() <= dataCapacityBits;
/* 184 */     bb.appendBits(0, Math.min(4, dataCapacityBits - bb.bitLength()));
/* 185 */     bb.appendBits(0, (8 - bb.bitLength() % 8) % 8);
/* 186 */     assert bb.bitLength() % 8 == 0;
/*     */     
/*     */     int padByte;
/* 189 */     for (padByte = 236; bb.bitLength() < dataCapacityBits; padByte ^= 0xFD) {
/* 190 */       bb.appendBits(padByte, 8);
/*     */     }
/*     */     
/* 193 */     byte[] dataCodewords = new byte[bb.bitLength() / 8];
/* 194 */     for (int i = 0; i < bb.bitLength(); i++) {
/* 195 */       dataCodewords[i >>> 3] = (byte)(dataCodewords[i >>> 3] | bb.getBit(i) << 7 - (i & 0x7));
/*     */     }
/*     */     
/* 198 */     return new QrCode(version, ecl, dataCodewords, mask);
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
/*     */   public QrCode(int ver, Ecc ecl, byte[] dataCodewords, int msk) {
/* 251 */     if (ver < 1 || ver > 40)
/* 252 */       throw new IllegalArgumentException("Version value out of range"); 
/* 253 */     if (msk < -1 || msk > 7)
/* 254 */       throw new IllegalArgumentException("Mask value out of range"); 
/* 255 */     this.version = ver;
/* 256 */     this.size = ver * 4 + 17;
/* 257 */     this.errorCorrectionLevel = Objects.<Ecc>requireNonNull(ecl);
/* 258 */     Objects.requireNonNull(dataCodewords);
/* 259 */     this.modules = new boolean[this.size][this.size];
/* 260 */     this.isFunction = new boolean[this.size][this.size];
/*     */ 
/*     */     
/* 263 */     drawFunctionPatterns();
/* 264 */     byte[] allCodewords = addEccAndInterleave(dataCodewords);
/* 265 */     drawCodewords(allCodewords);
/* 266 */     this.mask = handleConstructorMasking(msk);
/* 267 */     this.isFunction = null;
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
/*     */   public boolean getModule(int x, int y) {
/* 284 */     return (0 <= x && x < this.size && 0 <= y && y < this.size && this.modules[y][x]);
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
/*     */   public BufferedImage toImage(int scale, int border) {
/* 300 */     if (scale <= 0 || border < 0)
/* 301 */       throw new IllegalArgumentException("Value out of range"); 
/* 302 */     if (border > 1073741823 || this.size + border * 2L > (Integer.MAX_VALUE / scale)) {
/* 303 */       throw new IllegalArgumentException("Scale or border too large");
/*     */     }
/* 305 */     BufferedImage result = new BufferedImage((this.size + border * 2) * scale, (this.size + border * 2) * scale, 1);
/* 306 */     for (int y = 0; y < result.getHeight(); y++) {
/* 307 */       for (int x = 0; x < result.getWidth(); x++) {
/* 308 */         boolean color = getModule(x / scale - border, y / scale - border);
/* 309 */         result.setRGB(x, y, color ? 0 : 16777215);
/*     */       } 
/*     */     } 
/* 312 */     return result;
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
/*     */   public String toSvgString(int border) {
/* 324 */     if (border < 0)
/* 325 */       throw new IllegalArgumentException("Border must be non-negative"); 
/* 326 */     long brd = border;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 333 */     StringBuilder sb = (new StringBuilder()).append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n").append("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n").append(String.format("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" viewBox=\"0 0 %1$d %1$d\" stroke=\"none\">\n", new Object[] { Long.valueOf(this.size + brd * 2L) })).append("\t<rect width=\"100%\" height=\"100%\" fill=\"#FFFFFF\"/>\n").append("\t<path d=\"");
/* 334 */     for (int y = 0; y < this.size; y++) {
/* 335 */       for (int x = 0; x < this.size; x++) {
/* 336 */         if (getModule(x, y)) {
/* 337 */           if (x != 0 || y != 0)
/* 338 */             sb.append(" "); 
/* 339 */           sb.append(String.format("M%d,%dh1v1h-1z", new Object[] { Long.valueOf(x + brd), Long.valueOf(y + brd) }));
/*     */         } 
/*     */       } 
/*     */     } 
/* 343 */     return sb
/* 344 */       .append("\" fill=\"#000000\"/>\n")
/* 345 */       .append("</svg>\n")
/* 346 */       .toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawFunctionPatterns() {
/* 356 */     for (int i = 0; i < this.size; i++) {
/* 357 */       setFunctionModule(6, i, (i % 2 == 0));
/* 358 */       setFunctionModule(i, 6, (i % 2 == 0));
/*     */     } 
/*     */ 
/*     */     
/* 362 */     drawFinderPattern(3, 3);
/* 363 */     drawFinderPattern(this.size - 4, 3);
/* 364 */     drawFinderPattern(3, this.size - 4);
/*     */ 
/*     */     
/* 367 */     int[] alignPatPos = getAlignmentPatternPositions();
/* 368 */     int numAlign = alignPatPos.length;
/* 369 */     for (int j = 0; j < numAlign; j++) {
/* 370 */       for (int k = 0; k < numAlign; k++) {
/*     */         
/* 372 */         if ((j != 0 || k != 0) && (j != 0 || k != numAlign - 1) && (j != numAlign - 1 || k != 0)) {
/* 373 */           drawAlignmentPattern(alignPatPos[j], alignPatPos[k]);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 378 */     drawFormatBits(0);
/* 379 */     drawVersion();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawFormatBits(int msk) {
/* 387 */     int data = this.errorCorrectionLevel.formatBits << 3 | msk;
/* 388 */     int rem = data;
/* 389 */     for (int i = 0; i < 10; i++)
/* 390 */       rem = rem << 1 ^ (rem >>> 9) * 1335; 
/* 391 */     int bits = (data << 10 | rem) ^ 0x5412;
/* 392 */     assert bits >>> 15 == 0;
/*     */     
/*     */     int j;
/* 395 */     for (j = 0; j <= 5; j++)
/* 396 */       setFunctionModule(8, j, getBit(bits, j)); 
/* 397 */     setFunctionModule(8, 7, getBit(bits, 6));
/* 398 */     setFunctionModule(8, 8, getBit(bits, 7));
/* 399 */     setFunctionModule(7, 8, getBit(bits, 8));
/* 400 */     for (j = 9; j < 15; j++) {
/* 401 */       setFunctionModule(14 - j, 8, getBit(bits, j));
/*     */     }
/*     */     
/* 404 */     for (j = 0; j < 8; j++)
/* 405 */       setFunctionModule(this.size - 1 - j, 8, getBit(bits, j)); 
/* 406 */     for (j = 8; j < 15; j++)
/* 407 */       setFunctionModule(8, this.size - 15 + j, getBit(bits, j)); 
/* 408 */     setFunctionModule(8, this.size - 8, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawVersion() {
/* 415 */     if (this.version < 7) {
/*     */       return;
/*     */     }
/*     */     
/* 419 */     int rem = this.version;
/* 420 */     for (int i = 0; i < 12; i++)
/* 421 */       rem = rem << 1 ^ (rem >>> 11) * 7973; 
/* 422 */     int bits = this.version << 12 | rem;
/* 423 */     assert bits >>> 18 == 0;
/*     */ 
/*     */     
/* 426 */     for (int j = 0; j < 18; j++) {
/* 427 */       boolean bit = getBit(bits, j);
/* 428 */       int a = this.size - 11 + j % 3;
/* 429 */       int b = j / 3;
/* 430 */       setFunctionModule(a, b, bit);
/* 431 */       setFunctionModule(b, a, bit);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawFinderPattern(int x, int y) {
/* 439 */     for (int dy = -4; dy <= 4; dy++) {
/* 440 */       for (int dx = -4; dx <= 4; dx++) {
/* 441 */         int dist = Math.max(Math.abs(dx), Math.abs(dy));
/* 442 */         int xx = x + dx, yy = y + dy;
/* 443 */         if (0 <= xx && xx < this.size && 0 <= yy && yy < this.size) {
/* 444 */           setFunctionModule(xx, yy, (dist != 2 && dist != 4));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawAlignmentPattern(int x, int y) {
/* 453 */     for (int dy = -2; dy <= 2; dy++) {
/* 454 */       for (int dx = -2; dx <= 2; dx++) {
/* 455 */         setFunctionModule(x + dx, y + dy, (Math.max(Math.abs(dx), Math.abs(dy)) != 1));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setFunctionModule(int x, int y, boolean isBlack) {
/* 463 */     this.modules[y][x] = isBlack;
/* 464 */     this.isFunction[y][x] = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[] addEccAndInterleave(byte[] data) {
/* 473 */     Objects.requireNonNull(data);
/* 474 */     if (data.length != getNumDataCodewords(this.version, this.errorCorrectionLevel)) {
/* 475 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 478 */     int numBlocks = NUM_ERROR_CORRECTION_BLOCKS[this.errorCorrectionLevel.ordinal()][this.version];
/* 479 */     int blockEccLen = ECC_CODEWORDS_PER_BLOCK[this.errorCorrectionLevel.ordinal()][this.version];
/* 480 */     int rawCodewords = getNumRawDataModules(this.version) / 8;
/* 481 */     int numShortBlocks = numBlocks - rawCodewords % numBlocks;
/* 482 */     int shortBlockLen = rawCodewords / numBlocks;
/*     */ 
/*     */     
/* 485 */     byte[][] blocks = new byte[numBlocks][];
/* 486 */     byte[] rsDiv = reedSolomonComputeDivisor(blockEccLen);
/* 487 */     for (int i = 0, k = 0; i < numBlocks; i++) {
/* 488 */       byte[] dat = Arrays.copyOfRange(data, k, k + shortBlockLen - blockEccLen + ((i < numShortBlocks) ? 0 : 1));
/* 489 */       k += dat.length;
/* 490 */       byte[] block = Arrays.copyOf(dat, shortBlockLen + 1);
/* 491 */       byte[] ecc = reedSolomonComputeRemainder(dat, rsDiv);
/* 492 */       System.arraycopy(ecc, 0, block, block.length - blockEccLen, ecc.length);
/* 493 */       blocks[i] = block;
/*     */     } 
/*     */ 
/*     */     
/* 497 */     byte[] result = new byte[rawCodewords];
/* 498 */     for (int j = 0, m = 0; j < (blocks[0]).length; j++) {
/* 499 */       for (int n = 0; n < blocks.length; n++) {
/*     */         
/* 501 */         if (j != shortBlockLen - blockEccLen || n >= numShortBlocks) {
/* 502 */           result[m] = blocks[n][j];
/* 503 */           m++;
/*     */         } 
/*     */       } 
/*     */     } 
/* 507 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawCodewords(byte[] data) {
/* 514 */     Objects.requireNonNull(data);
/* 515 */     if (data.length != getNumRawDataModules(this.version) / 8) {
/* 516 */       throw new IllegalArgumentException();
/*     */     }
/* 518 */     int i = 0;
/*     */     
/* 520 */     for (int right = this.size - 1; right >= 1; right -= 2) {
/* 521 */       if (right == 6)
/* 522 */         right = 5; 
/* 523 */       for (int vert = 0; vert < this.size; vert++) {
/* 524 */         for (int j = 0; j < 2; j++) {
/* 525 */           int x = right - j;
/* 526 */           boolean upward = ((right + 1 & 0x2) == 0);
/* 527 */           int y = upward ? (this.size - 1 - vert) : vert;
/* 528 */           if (!this.isFunction[y][x] && i < data.length * 8) {
/* 529 */             this.modules[y][x] = getBit(data[i >>> 3], 7 - (i & 0x7));
/* 530 */             i++;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 537 */     assert i == data.length * 8;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void applyMask(int msk) {
/* 547 */     if (msk < 0 || msk > 7)
/* 548 */       throw new IllegalArgumentException("Mask value out of range"); 
/* 549 */     for (int y = 0; y < this.size; y++) {
/* 550 */       for (int x = 0; x < this.size; x++) {
/*     */         boolean invert;
/* 552 */         switch (msk) { case 0:
/* 553 */             invert = ((x + y) % 2 == 0); break;
/* 554 */           case 1: invert = (y % 2 == 0); break;
/* 555 */           case 2: invert = (x % 3 == 0); break;
/* 556 */           case 3: invert = ((x + y) % 3 == 0); break;
/* 557 */           case 4: invert = ((x / 3 + y / 2) % 2 == 0); break;
/* 558 */           case 5: invert = (x * y % 2 + x * y % 3 == 0); break;
/* 559 */           case 6: invert = ((x * y % 2 + x * y % 3) % 2 == 0); break;
/* 560 */           case 7: invert = (((x + y) % 2 + x * y % 3) % 2 == 0); break;
/* 561 */           default: throw new AssertionError(); }
/*     */         
/* 563 */         this.modules[y][x] = this.modules[y][x] ^ invert & (!this.isFunction[y][x]);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int handleConstructorMasking(int msk) {
/* 573 */     if (msk == -1) {
/* 574 */       int minPenalty = Integer.MAX_VALUE;
/* 575 */       for (int i = 0; i < 8; i++) {
/* 576 */         applyMask(i);
/* 577 */         drawFormatBits(i);
/* 578 */         int penalty = getPenaltyScore();
/* 579 */         if (penalty < minPenalty) {
/* 580 */           msk = i;
/* 581 */           minPenalty = penalty;
/*     */         } 
/* 583 */         applyMask(i);
/*     */       } 
/*     */     } 
/* 586 */     assert 0 <= msk && msk <= 7;
/* 587 */     applyMask(msk);
/* 588 */     drawFormatBits(msk);
/* 589 */     return msk;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getPenaltyScore() {
/* 596 */     int result = 0;
/*     */ 
/*     */     
/* 599 */     for (int i = 0; i < this.size; i++) {
/* 600 */       boolean runColor = false;
/* 601 */       int runX = 0;
/* 602 */       int[] runHistory = new int[7];
/* 603 */       for (int j = 0; j < this.size; j++) {
/* 604 */         if (this.modules[i][j] == runColor)
/* 605 */         { runX++;
/* 606 */           if (runX == 5) {
/* 607 */             result += 3;
/* 608 */           } else if (runX > 5) {
/* 609 */             result++;
/*     */           }  }
/* 611 */         else { finderPenaltyAddHistory(runX, runHistory);
/* 612 */           if (!runColor)
/* 613 */             result += finderPenaltyCountPatterns(runHistory) * 40; 
/* 614 */           runColor = this.modules[i][j];
/* 615 */           runX = 1; }
/*     */       
/*     */       } 
/* 618 */       result += finderPenaltyTerminateAndCount(runColor, runX, runHistory) * 40;
/*     */     } 
/*     */     
/* 621 */     for (int x = 0; x < this.size; x++) {
/* 622 */       boolean runColor = false;
/* 623 */       int runY = 0;
/* 624 */       int[] runHistory = new int[7];
/* 625 */       for (int j = 0; j < this.size; j++) {
/* 626 */         if (this.modules[j][x] == runColor)
/* 627 */         { runY++;
/* 628 */           if (runY == 5) {
/* 629 */             result += 3;
/* 630 */           } else if (runY > 5) {
/* 631 */             result++;
/*     */           }  }
/* 633 */         else { finderPenaltyAddHistory(runY, runHistory);
/* 634 */           if (!runColor)
/* 635 */             result += finderPenaltyCountPatterns(runHistory) * 40; 
/* 636 */           runColor = this.modules[j][x];
/* 637 */           runY = 1; }
/*     */       
/*     */       } 
/* 640 */       result += finderPenaltyTerminateAndCount(runColor, runY, runHistory) * 40;
/*     */     } 
/*     */ 
/*     */     
/* 644 */     for (int y = 0; y < this.size - 1; y++) {
/* 645 */       for (int j = 0; j < this.size - 1; j++) {
/* 646 */         boolean color = this.modules[y][j];
/* 647 */         if (color == this.modules[y][j + 1] && color == this.modules[y + 1][j] && color == this.modules[y + 1][j + 1])
/*     */         {
/*     */           
/* 650 */           result += 3;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 655 */     int black = 0;
/* 656 */     for (boolean[] row : this.modules) {
/* 657 */       for (boolean color : row) {
/* 658 */         if (color)
/* 659 */           black++; 
/*     */       } 
/*     */     } 
/* 662 */     int total = this.size * this.size;
/*     */     
/* 664 */     int k = (Math.abs(black * 20 - total * 10) + total - 1) / total - 1;
/* 665 */     result += k * 10;
/* 666 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int[] getAlignmentPatternPositions() {
/*     */     int step;
/* 677 */     if (this.version == 1) {
/* 678 */       return new int[0];
/*     */     }
/* 680 */     int numAlign = this.version / 7 + 2;
/*     */     
/* 682 */     if (this.version == 32) {
/* 683 */       step = 26;
/*     */     } else {
/* 685 */       step = (this.version * 4 + numAlign * 2 + 1) / (numAlign * 2 - 2) * 2;
/* 686 */     }  int[] result = new int[numAlign];
/* 687 */     result[0] = 6; int pos;
/* 688 */     for (int i = result.length - 1; i >= 1; i--, pos -= step)
/* 689 */       result[i] = pos; 
/* 690 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getNumRawDataModules(int ver) {
/* 699 */     if (ver < 1 || ver > 40) {
/* 700 */       throw new IllegalArgumentException("Version number out of range");
/*     */     }
/* 702 */     int size = ver * 4 + 17;
/* 703 */     int result = size * size;
/* 704 */     result -= 192;
/* 705 */     result -= 31;
/* 706 */     result -= (size - 16) * 2;
/*     */     
/* 708 */     if (ver >= 2) {
/* 709 */       int numAlign = ver / 7 + 2;
/* 710 */       result -= (numAlign - 1) * (numAlign - 1) * 25;
/* 711 */       result -= (numAlign - 2) * 2 * 20;
/*     */       
/* 713 */       if (ver >= 7)
/* 714 */         result -= 36; 
/*     */     } 
/* 716 */     assert 208 <= result && result <= 29648;
/* 717 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static byte[] reedSolomonComputeDivisor(int degree) {
/* 724 */     if (degree < 1 || degree > 255) {
/* 725 */       throw new IllegalArgumentException("Degree out of range");
/*     */     }
/*     */     
/* 728 */     byte[] result = new byte[degree];
/* 729 */     result[degree - 1] = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 734 */     int root = 1;
/* 735 */     for (int i = 0; i < degree; i++) {
/*     */       
/* 737 */       for (int j = 0; j < result.length; j++) {
/* 738 */         result[j] = (byte)reedSolomonMultiply(result[j] & 0xFF, root);
/* 739 */         if (j + 1 < result.length)
/* 740 */           result[j] = (byte)(result[j] ^ result[j + 1]); 
/*     */       } 
/* 742 */       root = reedSolomonMultiply(root, 2);
/*     */     } 
/* 744 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static byte[] reedSolomonComputeRemainder(byte[] data, byte[] divisor) {
/* 750 */     Objects.requireNonNull(data);
/* 751 */     Objects.requireNonNull(divisor);
/* 752 */     byte[] result = new byte[divisor.length];
/* 753 */     for (byte b : data) {
/* 754 */       int factor = (b ^ result[0]) & 0xFF;
/* 755 */       System.arraycopy(result, 1, result, 0, result.length - 1);
/* 756 */       result[result.length - 1] = 0;
/* 757 */       for (int i = 0; i < result.length; i++)
/* 758 */         result[i] = (byte)(result[i] ^ reedSolomonMultiply(divisor[i] & 0xFF, factor)); 
/*     */     } 
/* 760 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int reedSolomonMultiply(int x, int y) {
/* 767 */     assert x >> 8 == 0 && y >> 8 == 0;
/*     */     
/* 769 */     int z = 0;
/* 770 */     for (int i = 7; i >= 0; i--) {
/* 771 */       z = z << 1 ^ (z >>> 7) * 285;
/* 772 */       z ^= (y >>> i & 0x1) * x;
/*     */     } 
/* 774 */     assert z >>> 8 == 0;
/* 775 */     return z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static int getNumDataCodewords(int ver, Ecc ecl) {
/* 783 */     return getNumRawDataModules(ver) / 8 - ECC_CODEWORDS_PER_BLOCK[ecl
/* 784 */         .ordinal()][ver] * NUM_ERROR_CORRECTION_BLOCKS[ecl
/* 785 */         .ordinal()][ver];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int finderPenaltyCountPatterns(int[] runHistory) {
/* 792 */     int n = runHistory[1];
/* 793 */     assert n <= this.size * 3;
/* 794 */     boolean core = (n > 0 && runHistory[2] == n && runHistory[3] == n * 3 && runHistory[4] == n && runHistory[5] == n);
/* 795 */     return ((core && runHistory[0] >= n * 4 && runHistory[6] >= n) ? 1 : 0) + (
/* 796 */       (core && runHistory[6] >= n * 4 && runHistory[0] >= n) ? 1 : 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int finderPenaltyTerminateAndCount(boolean currentRunColor, int currentRunLength, int[] runHistory) {
/* 802 */     if (currentRunColor) {
/* 803 */       finderPenaltyAddHistory(currentRunLength, runHistory);
/* 804 */       currentRunLength = 0;
/*     */     } 
/* 806 */     currentRunLength += this.size;
/* 807 */     finderPenaltyAddHistory(currentRunLength, runHistory);
/* 808 */     return finderPenaltyCountPatterns(runHistory);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void finderPenaltyAddHistory(int currentRunLength, int[] runHistory) {
/* 814 */     if (runHistory[0] == 0)
/* 815 */       currentRunLength += this.size; 
/* 816 */     System.arraycopy(runHistory, 0, runHistory, 1, runHistory.length - 1);
/* 817 */     runHistory[0] = currentRunLength;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean getBit(int x, int i) {
/* 823 */     return ((x >>> i & 0x1) != 0);
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
/* 843 */   private static final byte[][] ECC_CODEWORDS_PER_BLOCK = new byte[][] { { -1, 7, 10, 15, 20, 26, 18, 20, 24, 30, 18, 20, 24, 26, 30, 22, 24, 28, 30, 28, 28, 28, 28, 30, 30, 26, 28, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30 }, { -1, 10, 16, 26, 18, 24, 16, 18, 22, 22, 26, 30, 22, 22, 24, 24, 28, 28, 26, 26, 26, 26, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28 }, { -1, 13, 22, 18, 26, 18, 24, 18, 22, 20, 24, 28, 26, 24, 20, 30, 24, 28, 28, 26, 30, 28, 30, 30, 30, 30, 28, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30 }, { -1, 17, 28, 22, 16, 22, 28, 26, 26, 24, 28, 24, 28, 22, 24, 24, 30, 28, 28, 26, 28, 30, 24, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30 } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 852 */   private static final byte[][] NUM_ERROR_CORRECTION_BLOCKS = new byte[][] { { -1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 4, 4, 4, 4, 4, 6, 6, 6, 6, 7, 8, 8, 9, 9, 10, 12, 12, 12, 13, 14, 15, 16, 17, 18, 19, 19, 20, 21, 22, 24, 25 }, { -1, 1, 1, 1, 2, 2, 4, 4, 4, 5, 5, 5, 8, 9, 9, 10, 10, 11, 13, 14, 16, 17, 17, 18, 20, 21, 23, 25, 26, 28, 29, 31, 33, 35, 37, 38, 40, 43, 45, 47, 49 }, { -1, 1, 1, 2, 2, 4, 4, 6, 6, 8, 8, 8, 10, 12, 16, 12, 17, 16, 18, 21, 20, 23, 23, 25, 27, 29, 34, 34, 35, 38, 40, 43, 45, 48, 51, 53, 56, 59, 62, 65, 68 }, { -1, 1, 1, 2, 4, 4, 4, 5, 6, 8, 8, 11, 11, 16, 16, 18, 16, 19, 21, 25, 25, 25, 34, 30, 32, 35, 37, 40, 42, 45, 48, 51, 54, 57, 60, 63, 66, 70, 74, 77, 81 } };
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
/*     */   public enum Ecc
/*     */   {
/* 871 */     LOW(1),
/* 872 */     MEDIUM(0),
/* 873 */     QUARTILE(3),
/* 874 */     HIGH(2);
/*     */ 
/*     */     
/*     */     final int formatBits;
/*     */ 
/*     */     
/*     */     Ecc(int fb) {
/* 881 */       this.formatBits = fb;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\io\nayuki\qrcodegen\QrCode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
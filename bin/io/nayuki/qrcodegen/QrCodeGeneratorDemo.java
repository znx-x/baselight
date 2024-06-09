/*     */ package io.nayuki.qrcodegen;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.nio.file.Files;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.imageio.ImageIO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class QrCodeGeneratorDemo
/*     */ {
/*     */   public static void main(String[] args) throws IOException {
/*  43 */     doBasicDemo();
/*  44 */     doVarietyDemo();
/*  45 */     doSegmentDemo();
/*  46 */     doMaskDemo();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void doBasicDemo() throws IOException {
/*  55 */     String text = "Hello, world!";
/*  56 */     QrCode.Ecc errCorLvl = QrCode.Ecc.LOW;
/*     */     
/*  58 */     QrCode qr = QrCode.encodeText(text, errCorLvl);
/*     */     
/*  60 */     BufferedImage img = qr.toImage(10, 4);
/*  61 */     File imgFile = new File("hello-world-QR.png");
/*  62 */     ImageIO.write(img, "png", imgFile);
/*     */     
/*  64 */     String svg = qr.toSvgString(4);
/*  65 */     File svgFile = new File("hello-world-QR.svg");
/*  66 */     Files.write(svgFile.toPath(), svg
/*  67 */         .getBytes(StandardCharsets.UTF_8), new java.nio.file.OpenOption[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void doVarietyDemo() throws IOException {
/*  76 */     QrCode qr = QrCode.encodeText("314159265358979323846264338327950288419716939937510", QrCode.Ecc.MEDIUM);
/*  77 */     writePng(qr.toImage(13, 1), "pi-digits-QR.png");
/*     */ 
/*     */     
/*  80 */     qr = QrCode.encodeText("DOLLAR-AMOUNT:$39.87 PERCENTAGE:100.00% OPERATIONS:+-*/", QrCode.Ecc.HIGH);
/*  81 */     writePng(qr.toImage(10, 2), "alphanumeric-QR.png");
/*     */ 
/*     */     
/*  84 */     qr = QrCode.encodeText("こんにちwa、世界！ αβγδ", QrCode.Ecc.QUARTILE);
/*  85 */     writePng(qr.toImage(10, 3), "unicode-QR.png");
/*     */ 
/*     */     
/*  88 */     qr = QrCode.encodeText("Alice was beginning to get very tired of sitting by her sister on the bank, and of having nothing to do: once or twice she had peeped into the book her sister was reading, but it had no pictures or conversations in it, 'and what is the use of a book,' thought Alice 'without pictures or conversations?' So she was considering in her own mind (as well as she could, for the hot day made her feel very sleepy and stupid), whether the pleasure of making a daisy-chain would be worth the trouble of getting up and picking the daisies, when suddenly a White Rabbit with pink eyes ran close by her.", QrCode.Ecc.HIGH);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  96 */     writePng(qr.toImage(6, 10), "alice-wonderland-QR.png");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void doSegmentDemo() throws IOException {
/* 106 */     String silver0 = "THE SQUARE ROOT OF 2 IS 1.";
/* 107 */     String silver1 = "41421356237309504880168872420969807856967187537694807317667973799";
/* 108 */     QrCode qr = QrCode.encodeText(silver0 + silver1, QrCode.Ecc.LOW);
/* 109 */     writePng(qr.toImage(10, 3), "sqrt2-monolithic-QR.png");
/*     */     
/* 111 */     List<QrSegment> segs = Arrays.asList(new QrSegment[] {
/* 112 */           QrSegment.makeAlphanumeric(silver0), 
/* 113 */           QrSegment.makeNumeric(silver1) });
/* 114 */     qr = QrCode.encodeSegments(segs, QrCode.Ecc.LOW);
/* 115 */     writePng(qr.toImage(10, 3), "sqrt2-segmented-QR.png");
/*     */ 
/*     */     
/* 118 */     String golden0 = "Golden ratio φ = 1.";
/* 119 */     String golden1 = "6180339887498948482045868343656381177203091798057628621354486227052604628189024497072072041893911374";
/* 120 */     String golden2 = "......";
/* 121 */     qr = QrCode.encodeText(golden0 + golden1 + golden2, QrCode.Ecc.LOW);
/* 122 */     writePng(qr.toImage(8, 5), "phi-monolithic-QR.png");
/*     */     
/* 124 */     segs = Arrays.asList(new QrSegment[] {
/* 125 */           QrSegment.makeBytes(golden0.getBytes(StandardCharsets.UTF_8)), 
/* 126 */           QrSegment.makeNumeric(golden1), 
/* 127 */           QrSegment.makeAlphanumeric(golden2) });
/* 128 */     qr = QrCode.encodeSegments(segs, QrCode.Ecc.LOW);
/* 129 */     writePng(qr.toImage(8, 5), "phi-segmented-QR.png");
/*     */ 
/*     */     
/* 132 */     String madoka = "「魔法少女まどか☆マギカ」って、　ИАИ　ｄｅｓｕ　κα？";
/* 133 */     qr = QrCode.encodeText(madoka, QrCode.Ecc.LOW);
/* 134 */     writePng(qr.toImage(9, 4), "madoka-utf8-QR.png");
/*     */     
/* 136 */     segs = Arrays.asList(new QrSegment[] { QrSegmentAdvanced.makeKanji(madoka) });
/* 137 */     qr = QrCode.encodeSegments(segs, QrCode.Ecc.LOW);
/* 138 */     writePng(qr.toImage(9, 4), "madoka-kanji-QR.png");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void doMaskDemo() throws IOException {
/* 148 */     List<QrSegment> segs = QrSegment.makeSegments("https://www.nayuki.io/");
/* 149 */     QrCode qr = QrCode.encodeSegments(segs, QrCode.Ecc.HIGH, 1, 40, -1, true);
/* 150 */     writePng(qr.toImage(8, 6), "project-nayuki-automask-QR.png");
/* 151 */     qr = QrCode.encodeSegments(segs, QrCode.Ecc.HIGH, 1, 40, 3, true);
/* 152 */     writePng(qr.toImage(8, 6), "project-nayuki-mask3-QR.png");
/*     */ 
/*     */     
/* 155 */     segs = QrSegment.makeSegments("維基百科（Wikipedia，聆聽i/ˌwɪkᵻˈpiːdi.ə/）是一個自由內容、公開編輯且多語言的網路百科全書協作計畫");
/* 156 */     qr = QrCode.encodeSegments(segs, QrCode.Ecc.MEDIUM, 1, 40, 0, true);
/* 157 */     writePng(qr.toImage(10, 3), "unicode-mask0-QR.png");
/* 158 */     qr = QrCode.encodeSegments(segs, QrCode.Ecc.MEDIUM, 1, 40, 1, true);
/* 159 */     writePng(qr.toImage(10, 3), "unicode-mask1-QR.png");
/* 160 */     qr = QrCode.encodeSegments(segs, QrCode.Ecc.MEDIUM, 1, 40, 5, true);
/* 161 */     writePng(qr.toImage(10, 3), "unicode-mask5-QR.png");
/* 162 */     qr = QrCode.encodeSegments(segs, QrCode.Ecc.MEDIUM, 1, 40, 7, true);
/* 163 */     writePng(qr.toImage(10, 3), "unicode-mask7-QR.png");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void writePng(BufferedImage img, String filepath) throws IOException {
/* 172 */     ImageIO.write(img, "png", new File(filepath));
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\io\nayuki\qrcodegen\QrCodeGeneratorDemo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*      */ package com.pnuema.java.barcode;
/*      */ 
/*      */ import com.pnuema.java.barcode.symbologies.Code128;
/*      */ import com.pnuema.java.barcode.symbologies.Code39;
/*      */ import com.pnuema.java.barcode.symbologies.ITF14;
/*      */ import com.pnuema.java.barcode.symbologies.JAN13;
/*      */ import com.pnuema.java.barcode.symbologies.Pharmacode;
/*      */ import com.pnuema.java.barcode.symbologies.Postnet;
/*      */ import java.awt.Color;
/*      */ import java.awt.Font;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.Image;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.OutputStream;
/*      */ import javax.imageio.ImageIO;
/*      */ 
/*      */ public class Barcode {
/*      */   private IBarcode ibarcode;
/*      */   
/*   23 */   public enum TYPE { UNSPECIFIED, UPCA, UPCE, UPC_SUPPLEMENTAL_2DIGIT, UPC_SUPPLEMENTAL_5DIGIT, EAN13, EAN8, Interleaved2of5, Interleaved2of5_Mod10, Standard2of5, Standard2of5_Mod10, Industrial2of5, Industrial2of5_Mod10, CODE39, CODE39Extended, CODE39_Mod43, Codabar, PostNet, BOOKLAND, ISBN, JAN13, MSI_Mod10, MSI_2Mod10, MSI_Mod11, MSI_Mod11_Mod10, Modified_Plessey, CODE11, USD8, UCC12, UCC13, LOGMARS, CODE128, CODE128A, CODE128B, CODE128C, ITF14, CODE93, TELEPEN, FIM, PHARMACODE; }
/*      */   
/*   25 */   public enum SaveTypes { JPG, BMP, PNG, GIF, TIFF, UNSPECIFIED; }
/*      */   
/*   27 */   public enum AlignmentPositions { CENTER, LEFT, RIGHT; }
/*      */ 
/*      */   
/*   30 */   private String rawData = "";
/*   31 */   private String encodedValue = "";
/*   32 */   private String countryAssigningManufacturerCode = "N/A";
/*   33 */   private TYPE encodedType = TYPE.UNSPECIFIED;
/*   34 */   private Image encodedImage = null;
/*   35 */   private Color foreColor = Color.BLACK;
/*   36 */   private Color backColor = Color.WHITE;
/*   37 */   private int width = 300;
/*   38 */   private int height = 150;
/*   39 */   private Font labelFont = new Font("Serif", 1, 10);
/*   40 */   private Labels.LabelPositions labelPositions = Labels.LabelPositions.BOTTOM;
/*      */ 
/*      */   
/*   43 */   private AlignmentPositions alignmentPosition = AlignmentPositions.CENTER;
/*      */ 
/*      */   
/*      */   private String alternateLabel;
/*      */ 
/*      */   
/*      */   private boolean includeLabel;
/*      */   
/*      */   private boolean standardizeLabel = true;
/*      */   
/*      */   private long encodingTime;
/*      */   
/*      */   private long drawTime;
/*      */   
/*      */   private Integer barWidth;
/*      */   
/*      */   private Double aspectRatio;
/*      */ 
/*      */   
/*      */   public Barcode() {}
/*      */ 
/*      */   
/*      */   public Barcode(String data) {
/*   66 */     setRawData(data);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Barcode(String data, TYPE iType) {
/*   76 */     this(data);
/*   77 */     this.encodedType = iType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTitle() {
/*   85 */     return getClass().getPackage().getImplementationTitle();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getVersion() {
/*   93 */     return getClass().getPackage().getImplementationVersion();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getRawData() {
/*  102 */     return this.rawData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRawData(String rawData) {
/*  111 */     this.rawData = rawData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getEncodedValue() {
/*  120 */     return this.encodedValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getCountryAssigningManufacturerCode() {
/*  129 */     return this.countryAssigningManufacturerCode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TYPE getEncodedType() {
/*  138 */     return this.encodedType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEncodedType(TYPE encoded_Type) {
/*  147 */     this.encodedType = encoded_Type;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Image getEncodedImage() {
/*  156 */     return this.encodedImage;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Color getForeColor() {
/*  165 */     return this.foreColor;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setForeColor(Color _ForeColor) {
/*  174 */     this.foreColor = _ForeColor;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Color getBackColor() {
/*  183 */     return this.backColor;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBackColor(Color _BackColor) {
/*  192 */     this.backColor = _BackColor;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Font getLabelFont() {
/*  201 */     return this.labelFont;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLabelFont(Font _LabelFont) {
/*  210 */     this.labelFont = _LabelFont;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Labels.LabelPositions getLabelPosition() {
/*  219 */     return this.labelPositions;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLabelPosition(Labels.LabelPositions _LabelPosition) {
/*  228 */     this.labelPositions = _LabelPosition;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getWidth() {
/*  237 */     return this.width;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setWidth(int width) {
/*  246 */     this.width = width;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getHeight() {
/*  255 */     return this.height;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHeight(int height) {
/*  264 */     this.height = height;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getAlternateLabel() {
/*  273 */     return this.alternateLabel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAlternateLabel(String alternateLabel) {
/*  282 */     this.alternateLabel = alternateLabel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isIncludeLabel() {
/*  291 */     return this.includeLabel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIncludeLabel(boolean includeLabel) {
/*  300 */     this.includeLabel = includeLabel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isStandardizeLabel() {
/*  309 */     return this.standardizeLabel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setStandardizeLabel(boolean standardizeLabel) {
/*  318 */     this.standardizeLabel = standardizeLabel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getEncodingTime() {
/*  327 */     return this.encodingTime;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setEncodingTime(long encodingTime) {
/*  336 */     this.encodingTime = encodingTime;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getDrawTime() {
/*  345 */     return this.drawTime;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setDrawTime(long drawTime) {
/*  354 */     this.drawTime = drawTime;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Integer getBarWidth() {
/*  362 */     return this.barWidth;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBarWidth(Integer barWidth) {
/*  370 */     this.barWidth = barWidth;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Double getAspectRatio() {
/*  378 */     return this.aspectRatio;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAspectRatio(Double aspectRatio) {
/*  394 */     this.aspectRatio = aspectRatio;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<String> getErrors() {
/*  403 */     return this.ibarcode.getErrors();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AlignmentPositions getAlignmentPosition() {
/*  412 */     return this.alignmentPosition;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAlignmentPosition(AlignmentPositions alignment) {
/*  421 */     this.alignmentPosition = alignment;
/*      */   }
/*      */   
/*      */   public class ImageSize { private double width;
/*      */     private double height;
/*      */     private boolean metric;
/*      */     
/*      */     public ImageSize(double width, double height, boolean metric) {
/*  429 */       setWidth(width);
/*  430 */       setHeight(height);
/*  431 */       setMetric(metric);
/*      */     }
/*      */     
/*      */     public double getWidth() {
/*  435 */       return this.width;
/*      */     }
/*      */     
/*      */     public void setWidth(double width) {
/*  439 */       this.width = width;
/*      */     }
/*      */     
/*      */     public double getHeight() {
/*  443 */       return this.height;
/*      */     }
/*      */     
/*      */     public void setHeight(double height) {
/*  447 */       this.height = height;
/*      */     }
/*      */     
/*      */     public boolean isMetric() {
/*  451 */       return this.metric;
/*      */     }
/*      */     
/*      */     public void setMetric(boolean metric) {
/*  455 */       this.metric = metric;
/*      */     } }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Image encode(TYPE iType, String stringToEncode, int width, int height) {
/*  473 */     setWidth(width);
/*  474 */     setHeight(height);
/*  475 */     return encode(iType, stringToEncode);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Image encode(TYPE iType, String stringToEncode, Color foreColor, Color backColor, int width, int height) {
/*  490 */     setWidth(width);
/*  491 */     setHeight(height);
/*  492 */     return encode(iType, stringToEncode, foreColor, backColor);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Image encode(TYPE iType, String stringToEncode, Color foreColor, Color backColor) {
/*  505 */     setBackColor(backColor);
/*  506 */     setForeColor(foreColor);
/*  507 */     return encode(iType, stringToEncode);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Image encode(TYPE iType, String stringToEncode) {
/*  518 */     this.rawData = stringToEncode;
/*  519 */     return encode(iType);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Image encode(TYPE iType) {
/*  529 */     this.encodedType = iType;
/*  530 */     return encode();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Image encode() {
/*  539 */     long dtStartTime = System.currentTimeMillis();
/*      */ 
/*      */     
/*  542 */     if (this.rawData.trim().isEmpty()) {
/*  543 */       throw new IllegalArgumentException("EENCODE-1: Input data not allowed to be blank.");
/*      */     }
/*      */     
/*  546 */     if (getEncodedType() == TYPE.UNSPECIFIED) {
/*  547 */       throw new IllegalArgumentException("EENCODE-2: Symbology type not allowed to be unspecified.");
/*      */     }
/*      */     
/*  550 */     this.encodedValue = "";
/*  551 */     this.countryAssigningManufacturerCode = "N/A";
/*      */     
/*  553 */     switch (this.encodedType) {
/*      */       case LEFT:
/*      */       case RIGHT:
/*  556 */         this.ibarcode = (IBarcode)new UPCA(this.rawData);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  650 */         this.ibarcode.clearErrors();
/*      */         
/*  652 */         this.encodedValue = this.ibarcode.getEncodedValue();
/*  653 */         this.rawData = this.ibarcode.getRawData();
/*      */         
/*  655 */         this.encodedImage = generateImage();
/*      */         
/*  657 */         setEncodingTime(System.currentTimeMillis() - dtStartTime);
/*      */         
/*  659 */         return this.encodedImage;case CENTER: case null: case null: case null: this.ibarcode = (IBarcode)new Standard2of5(this.rawData, getEncodedType()); this.ibarcode.clearErrors(); this.encodedValue = this.ibarcode.getEncodedValue(); this.rawData = this.ibarcode.getRawData(); this.encodedImage = generateImage(); setEncodingTime(System.currentTimeMillis() - dtStartTime); return this.encodedImage;case null: case null: this.ibarcode = (IBarcode)new Interleaved2of5(this.rawData, getEncodedType()); this.ibarcode.clearErrors(); this.encodedValue = this.ibarcode.getEncodedValue(); this.rawData = this.ibarcode.getRawData(); this.encodedImage = generateImage(); setEncodingTime(System.currentTimeMillis() - dtStartTime); return this.encodedImage;case null: case null: this.ibarcode = (IBarcode)new EAN13(this.rawData); this.ibarcode.clearErrors(); this.encodedValue = this.ibarcode.getEncodedValue(); this.rawData = this.ibarcode.getRawData(); this.encodedImage = generateImage(); setEncodingTime(System.currentTimeMillis() - dtStartTime); return this.encodedImage;case null: case null: this.ibarcode = (IBarcode)new Code39(this.rawData); this.ibarcode.clearErrors(); this.encodedValue = this.ibarcode.getEncodedValue(); this.rawData = this.ibarcode.getRawData(); this.encodedImage = generateImage(); setEncodingTime(System.currentTimeMillis() - dtStartTime); return this.encodedImage;case null: this.ibarcode = (IBarcode)new Code39(this.rawData, true); this.ibarcode.clearErrors(); this.encodedValue = this.ibarcode.getEncodedValue(); this.rawData = this.ibarcode.getRawData(); this.encodedImage = generateImage(); setEncodingTime(System.currentTimeMillis() - dtStartTime); return this.encodedImage;case null: this.ibarcode = (IBarcode)new Code39(this.rawData, false, true); this.ibarcode.clearErrors(); this.encodedValue = this.ibarcode.getEncodedValue(); this.rawData = this.ibarcode.getRawData(); this.encodedImage = generateImage(); setEncodingTime(System.currentTimeMillis() - dtStartTime); return this.encodedImage;case null: this.ibarcode = (IBarcode)new Codabar(this.rawData); this.ibarcode.clearErrors(); this.encodedValue = this.ibarcode.getEncodedValue(); this.rawData = this.ibarcode.getRawData(); this.encodedImage = generateImage(); setEncodingTime(System.currentTimeMillis() - dtStartTime); return this.encodedImage;case null: case null: this.ibarcode = (IBarcode)new ISBN(this.rawData); this.ibarcode.clearErrors(); this.encodedValue = this.ibarcode.getEncodedValue(); this.rawData = this.ibarcode.getRawData(); this.encodedImage = generateImage(); setEncodingTime(System.currentTimeMillis() - dtStartTime); return this.encodedImage;case null: this.ibarcode = (IBarcode)new JAN13(this.rawData); this.ibarcode.clearErrors(); this.encodedValue = this.ibarcode.getEncodedValue(); this.rawData = this.ibarcode.getRawData(); this.encodedImage = generateImage(); setEncodingTime(System.currentTimeMillis() - dtStartTime); return this.encodedImage;case null: case null: case null: case null: case null: this.ibarcode = (IBarcode)new MSI(this.rawData, this.encodedType); this.ibarcode.clearErrors(); this.encodedValue = this.ibarcode.getEncodedValue(); this.rawData = this.ibarcode.getRawData(); this.encodedImage = generateImage(); setEncodingTime(System.currentTimeMillis() - dtStartTime); return this.encodedImage;case null: this.ibarcode = (IBarcode)new UPCSupplement2(this.rawData); this.ibarcode.clearErrors(); this.encodedValue = this.ibarcode.getEncodedValue(); this.rawData = this.ibarcode.getRawData(); this.encodedImage = generateImage(); setEncodingTime(System.currentTimeMillis() - dtStartTime); return this.encodedImage;case null: this.ibarcode = (IBarcode)new UPCSupplement5(this.rawData); this.ibarcode.clearErrors(); this.encodedValue = this.ibarcode.getEncodedValue(); this.rawData = this.ibarcode.getRawData(); this.encodedImage = generateImage(); setEncodingTime(System.currentTimeMillis() - dtStartTime); return this.encodedImage;case null: this.ibarcode = (IBarcode)new UPCE(this.rawData); this.ibarcode.clearErrors(); this.encodedValue = this.ibarcode.getEncodedValue(); this.rawData = this.ibarcode.getRawData(); this.encodedImage = generateImage(); setEncodingTime(System.currentTimeMillis() - dtStartTime); return this.encodedImage;case null: this.ibarcode = (IBarcode)new Postnet(this.rawData); this.ibarcode.clearErrors(); this.encodedValue = this.ibarcode.getEncodedValue(); this.rawData = this.ibarcode.getRawData(); this.encodedImage = generateImage(); setEncodingTime(System.currentTimeMillis() - dtStartTime); return this.encodedImage;case null: this.ibarcode = (IBarcode)new EAN8(this.rawData); this.ibarcode.clearErrors(); this.encodedValue = this.ibarcode.getEncodedValue(); this.rawData = this.ibarcode.getRawData(); this.encodedImage = generateImage(); setEncodingTime(System.currentTimeMillis() - dtStartTime); return this.encodedImage;case null: case null: this.ibarcode = (IBarcode)new Code11(this.rawData); this.ibarcode.clearErrors(); this.encodedValue = this.ibarcode.getEncodedValue(); this.rawData = this.ibarcode.getRawData(); this.encodedImage = generateImage(); setEncodingTime(System.currentTimeMillis() - dtStartTime); return this.encodedImage;case null: this.ibarcode = (IBarcode)new Code128(this.rawData); this.ibarcode.clearErrors(); this.encodedValue = this.ibarcode.getEncodedValue(); this.rawData = this.ibarcode.getRawData(); this.encodedImage = generateImage(); setEncodingTime(System.currentTimeMillis() - dtStartTime); return this.encodedImage;case null: this.ibarcode = (IBarcode)new Code128(this.rawData, Code128.TYPES.A); this.ibarcode.clearErrors(); this.encodedValue = this.ibarcode.getEncodedValue(); this.rawData = this.ibarcode.getRawData(); this.encodedImage = generateImage(); setEncodingTime(System.currentTimeMillis() - dtStartTime); return this.encodedImage;case null: this.ibarcode = (IBarcode)new Code128(this.rawData, Code128.TYPES.B); this.ibarcode.clearErrors(); this.encodedValue = this.ibarcode.getEncodedValue(); this.rawData = this.ibarcode.getRawData(); this.encodedImage = generateImage(); setEncodingTime(System.currentTimeMillis() - dtStartTime); return this.encodedImage;case null: this.ibarcode = (IBarcode)new Code128(this.rawData, Code128.TYPES.C); this.ibarcode.clearErrors(); this.encodedValue = this.ibarcode.getEncodedValue(); this.rawData = this.ibarcode.getRawData(); this.encodedImage = generateImage(); setEncodingTime(System.currentTimeMillis() - dtStartTime); return this.encodedImage;case null: this.ibarcode = (IBarcode)new Code93(this.rawData); this.ibarcode.clearErrors(); this.encodedValue = this.ibarcode.getEncodedValue(); this.rawData = this.ibarcode.getRawData(); this.encodedImage = generateImage(); setEncodingTime(System.currentTimeMillis() - dtStartTime); return this.encodedImage;case null: this.ibarcode = (IBarcode)new FIM(this.rawData); this.ibarcode.clearErrors(); this.encodedValue = this.ibarcode.getEncodedValue(); this.rawData = this.ibarcode.getRawData(); this.encodedImage = generateImage(); setEncodingTime(System.currentTimeMillis() - dtStartTime); return this.encodedImage;case null: this.ibarcode = (IBarcode)new ITF14(this.rawData); this.ibarcode.clearErrors(); this.encodedValue = this.ibarcode.getEncodedValue(); this.rawData = this.ibarcode.getRawData(); this.encodedImage = generateImage(); setEncodingTime(System.currentTimeMillis() - dtStartTime); return this.encodedImage;case null: this.ibarcode = (IBarcode)new Telepen(this.rawData); this.ibarcode.clearErrors(); this.encodedValue = this.ibarcode.getEncodedValue(); this.rawData = this.ibarcode.getRawData(); this.encodedImage = generateImage(); setEncodingTime(System.currentTimeMillis() - dtStartTime); return this.encodedImage;case null: this.ibarcode = (IBarcode)new Pharmacode(this.rawData); this.ibarcode.clearErrors(); this.encodedValue = this.ibarcode.getEncodedValue(); this.rawData = this.ibarcode.getRawData(); this.encodedImage = generateImage(); setEncodingTime(System.currentTimeMillis() - dtStartTime); return this.encodedImage;
/*      */     } 
/*      */     throw new IllegalArgumentException("EENCODE-2: Unsupported encoding type specified.");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private Image generateImage() {
/*      */     int bearerwidth, iquietzone, shiftAdjustment, j, k, m;
/*      */     Graphics graphics1;
/*  669 */     if (this.encodedValue.isEmpty()) {
/*  670 */       throw new IllegalArgumentException("EGENERATE_IMAGE-1: Must be encoded first.");
/*      */     }
/*      */ 
/*      */     
/*  674 */     long dtStartTime = System.currentTimeMillis();
/*      */     
/*  676 */     switch (this.encodedType)
/*      */     
/*      */     { 
/*      */       
/*      */       case null:
/*  681 */         if (getBarWidth() != null) {
/*  682 */           setWidth((int)(1.362351611079706D * this.encodedValue.length() * getBarWidth().intValue() + 1.0D));
/*      */         }
/*      */         
/*  685 */         if (getAspectRatio() != null) {
/*  686 */           setHeight((int)(getWidth() / getAspectRatio().doubleValue()));
/*      */         }
/*      */         
/*  689 */         ILHeight = getHeight();
/*  690 */         if (isIncludeLabel()) {
/*  691 */           ILHeight -= getLabelFont().getSize();
/*      */         }
/*      */         
/*  694 */         bitmap = new BufferedImage(getWidth(), getHeight(), 2);
/*      */         
/*  696 */         bearerwidth = (int)(getWidth() / 12.05D);
/*  697 */         iquietzone = (int)Math.round(getWidth() * 0.05D);
/*  698 */         j = (getWidth() - bearerwidth * 2 - iquietzone * 2) / this.encodedValue.length();
/*  699 */         k = (getWidth() - bearerwidth * 2 - iquietzone * 2) % this.encodedValue.length() / 2;
/*      */         
/*  701 */         if (j <= 0 || iquietzone <= 0) {
/*  702 */           throw new IllegalArgumentException("EGENERATE_IMAGE-3: Image size specified not large enough to draw image. (Bar size determined to be less than 1 pixel or quiet zone determined to be less than 1 pixel)");
/*      */         }
/*      */ 
/*      */         
/*  706 */         m = 0;
/*      */         
/*  708 */         graphics1 = bitmap.createGraphics();
/*      */         
/*      */         try {
/*  711 */           graphics1.setColor(getBackColor());
/*  712 */           graphics1.fillRect(0, 0, bitmap.getWidth(), bitmap.getHeight());
/*      */ 
/*      */           
/*  715 */           graphics1.setColor(getForeColor());
/*      */           
/*  717 */           while (m < getEncodedValue().length()) {
/*      */             
/*  719 */             if (getEncodedValue().charAt(m) == '1') {
/*  720 */               graphics1.fillRect(m * j + k + bearerwidth + iquietzone, 0, j, getHeight());
/*      */             }
/*      */             
/*  723 */             m++;
/*      */           } 
/*      */ 
/*      */           
/*  727 */           int bearerBarWidth = ILHeight / 8;
/*      */           
/*  729 */           graphics1.fillRect(0, 0, getWidth(), bearerBarWidth);
/*  730 */           graphics1.fillRect(0, ILHeight - bearerBarWidth, getWidth(), bearerBarWidth);
/*  731 */           graphics1.fillRect(0, 0, bearerBarWidth, ILHeight);
/*  732 */           graphics1.fillRect(getWidth() - bearerBarWidth, bearerBarWidth, getWidth(), ILHeight);
/*      */         } finally {
/*  734 */           graphics1.dispose();
/*      */         } 
/*      */         
/*  737 */         if (isIncludeLabel()) {
/*  738 */           Labels.Label_ITF14(this, bitmap);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  971 */         this.encodedImage = bitmap;
/*      */         
/*  973 */         setDrawTime(System.currentTimeMillis() - dtStartTime);
/*      */         
/*  975 */         return bitmap;case RIGHT: if (getBarWidth() != null && !getEncodedValue().isEmpty()) setWidth(getBarWidth().intValue() * this.encodedValue.length());  if (getAspectRatio() != null) setHeight((int)(getWidth() / getAspectRatio().doubleValue()));  ILHeight = getHeight(); topLabelAdjustment = 0; shiftAdjustment = 0; j = getWidth() / this.encodedValue.length(); shiftAdjustment = getShiftAdjustment(); if (isIncludeLabel()) if ((getAlternateLabel() == null || getRawData().startsWith(getAlternateLabel())) && isStandardizeLabel()) { String defTxt = getRawData(); String labTxt = defTxt.charAt(0) + "--" + defTxt.substring(1, 6) + "--" + defTxt.substring(7); Font font = getLabelFont(); Font labFont = new Font((font != null) ? font.getFamily() : "Serif", 0, Labels.getFontsize(getWidth(), getHeight(), labTxt)); setLabelFont(labFont); ILHeight -= labFont.getSize() / 2; j = getWidth() / this.encodedValue.length(); } else { if ((getLabelPosition().ordinal() & Labels.LabelPositions.TOP.ordinal()) > 0) topLabelAdjustment = getLabelFont().getSize();  ILHeight -= getLabelFont().getSize(); }   bitmap = new BufferedImage(getWidth(), getHeight(), 2); if (j <= 0) throw new IllegalArgumentException("EGENERATE_IMAGE-2: Image size specified not large enough to draw image. (Bar size determined to be less than 1 pixel)");  pos = 0; g = bitmap.createGraphics(); try { g.setColor(getBackColor()); g.fillRect(0, 0, bitmap.getWidth(), bitmap.getHeight()); g.setColor(getForeColor()); while (pos < getEncodedValue().length()) { if (getEncodedValue().charAt(pos) == '1') g.fillRect(pos * j + shiftAdjustment, topLabelAdjustment, j, ILHeight + topLabelAdjustment);  pos++; }  } finally { g.dispose(); }  if (isIncludeLabel()) if ((getAlternateLabel() == null || getRawData().startsWith(getAlternateLabel())) && isStandardizeLabel()) { Labels.Label_UPCA(this, bitmap); } else { Labels.labelGeneric(this, bitmap); }   this.encodedImage = bitmap; setDrawTime(System.currentTimeMillis() - dtStartTime); return bitmap;case null: if (getBarWidth() != null) setWidth(getBarWidth().intValue() * getEncodedValue().length());  if (getAspectRatio() != null) setHeight((int)(getWidth() / getAspectRatio().doubleValue()));  ILHeight = getHeight(); topLabelAdjustment = 0; shiftAdjustment = getShiftAdjustment(); if (isIncludeLabel()) if ((getAlternateLabel() == null || getRawData().startsWith(getAlternateLabel())) && isStandardizeLabel()) { String defTxt = getRawData(); String labTxt = defTxt.charAt(0) + "--" + defTxt.substring(1, 6) + "--" + defTxt.substring(7); Font font = getLabelFont(); Font labFont = new Font((font != null) ? font.getFamily() : "Serif", 0, Labels.getFontsize(getWidth(), getHeight(), labTxt)); setLabelFont(labFont); ILHeight -= labFont.getSize() / 2; } else { if ((getLabelPosition().ordinal() & Labels.LabelPositions.TOP.ordinal()) > 0) topLabelAdjustment = getLabelFont().getSize();  ILHeight -= getLabelFont().getSize(); }   bitmap = new BufferedImage(getWidth(), getHeight(), 2); j = getWidth() / getEncodedValue().length(); if (j <= 0) throw new IllegalArgumentException("EGENERATE_IMAGE-2: Image size specified not large enough to draw image. (Bar size determined to be less than 1 pixel)");  pos = 0; g = bitmap.createGraphics(); try { g.setColor(getBackColor()); g.fillRect(0, 0, getWidth(), getHeight()); g.setColor(getForeColor()); while (pos < getEncodedValue().length()) { if (getEncodedValue().charAt(pos) == '1') g.fillRect(pos * j + shiftAdjustment, topLabelAdjustment, j, ILHeight + topLabelAdjustment);  pos++; }  } finally { g.dispose(); }  if (isIncludeLabel()) if ((getAlternateLabel() == null || getRawData().startsWith(getAlternateLabel())) && isStandardizeLabel()) { Labels.Label_EAN13(this, bitmap); } else { Labels.labelGeneric(this, bitmap); }   this.encodedImage = bitmap; setDrawTime(System.currentTimeMillis() - dtStartTime); return bitmap; }  if (getBarWidth() != null) setWidth(getBarWidth().intValue() * getEncodedValue().length());  if (getAspectRatio() != null) setHeight((int)(getWidth() / getAspectRatio().doubleValue()));  int ILHeight = getHeight(); int topLabelAdjustment = 0; if (isIncludeLabel()) { if ((getLabelPosition().ordinal() & Labels.LabelPositions.TOP.ordinal()) > 0) topLabelAdjustment = getLabelFont().getSize();  ILHeight -= getLabelFont().getSize(); }  BufferedImage bitmap = new BufferedImage(getWidth(), getHeight(), 2); int iBarWidth = getWidth() / getEncodedValue().length(); int i = getShiftAdjustment(); if (iBarWidth <= 0) throw new IllegalArgumentException("EGENERATE_IMAGE-2: Image size specified not large enough to draw image. (Bar size determined to be less than 1 pixel)");  int pos = 0; Graphics g = bitmap.createGraphics(); try { g.setColor(getBackColor()); g.fillRect(0, 0, getWidth(), getHeight()); g.setColor(getForeColor()); while (pos < getEncodedValue().length()) { if (getEncodedType() == TYPE.PostNet) { if (getEncodedValue().charAt(pos) == '0') { g.fillRect(pos * iBarWidth + i, ILHeight / 2 + topLabelAdjustment, iBarWidth / 2, ILHeight / 2 + topLabelAdjustment); } else { g.fillRect(pos * iBarWidth + i, topLabelAdjustment, iBarWidth / 2, ILHeight + topLabelAdjustment); }  } else if (getEncodedValue().charAt(pos) == '1') { g.fillRect(pos * iBarWidth + i, topLabelAdjustment, iBarWidth, ILHeight + topLabelAdjustment); }  pos++; }  } finally { g.dispose(); }  if (isIncludeLabel()) Labels.labelGeneric(this, bitmap);  this.encodedImage = bitmap; setDrawTime(System.currentTimeMillis() - dtStartTime); return bitmap;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getImageData(SaveTypes savetype) {
/*  984 */     byte[] imageData = null;
/*      */     
/*      */     try {
/*  987 */       if (this.encodedImage != null) {
/*      */         
/*  989 */         ByteArrayOutputStream stream = new ByteArrayOutputStream();
/*  990 */         saveImage(stream, savetype);
/*  991 */         imageData = stream.toByteArray();
/*  992 */         stream.flush();
/*  993 */         stream.close();
/*      */       } 
/*  995 */     } catch (Exception ex) {
/*  996 */       throw new IllegalStateException("EGETIMAGEDATA-1: Could not retrieve image data. " + ex.getMessage());
/*      */     } 
/*  998 */     return imageData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void saveImage(String filename, SaveTypes fileType) throws IOException {
/*      */     try {
/* 1009 */       if (getEncodedImage() != null) {
/* 1010 */         String imageformat = getImageFormatFromFileType(fileType);
/* 1011 */         ImageIO.write((RenderedImage)getEncodedImage(), imageformat, new File(filename));
/*      */       } 
/* 1013 */     } catch (IOException ex) {
/* 1014 */       throw new IOException("ESAVEIMAGE-1: Could not save image.\n\n=======================\n\n" + ex.getMessage());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void saveImage(OutputStream stream, SaveTypes fileType) throws IOException {
/*      */     try {
/* 1027 */       if (getEncodedImage() != null) {
/* 1028 */         String imageformat = getImageFormatFromFileType(fileType);
/* 1029 */         ImageIO.write((RenderedImage)getEncodedImage(), imageformat, stream);
/*      */       } 
/* 1031 */     } catch (Exception ex) {
/* 1032 */       throw new IOException("ESAVEIMAGE-2: Could not save image.\n\n=======================\n\n" + ex.getMessage());
/*      */     } 
/*      */   }
/*      */   
/*      */   private String getImageFormatFromFileType(SaveTypes saveType) {
/* 1037 */     switch (saveType) {
/*      */       case LEFT:
/* 1039 */         return "bmp";
/*      */       case RIGHT:
/* 1041 */         return "gif";
/*      */       case CENTER:
/* 1043 */         return "png";
/*      */       case null:
/* 1045 */         return "tif";
/*      */     } 
/*      */     
/* 1048 */     return "jpg";
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private int getShiftAdjustment() {
/* 1054 */     switch (getAlignmentPosition())
/*      */     { case LEFT:
/* 1056 */         shiftAdjustment = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1067 */         return shiftAdjustment;case RIGHT: shiftAdjustment = getWidth() % getEncodedValue().length(); return shiftAdjustment; }  int shiftAdjustment = getWidth() % getEncodedValue().length() / 2; return shiftAdjustment;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Image DoEncode(TYPE iType, String data) {
/* 1078 */     return (new Barcode()).encode(iType, data);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Image DoEncode(TYPE iType, String data, boolean includeLabel) {
/* 1090 */     Barcode b = new Barcode();
/* 1091 */     b.setIncludeLabel(includeLabel);
/* 1092 */     return b.encode(iType, data);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Image DoEncode(TYPE iType, String data, boolean includeLabel, int width, int height) {
/* 1106 */     Barcode b = new Barcode();
/* 1107 */     b.setIncludeLabel(includeLabel);
/* 1108 */     return b.encode(iType, data, width, height);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Image DoEncode(TYPE iType, String data, boolean includeLabel, Color drawColor, Color backColor) {
/* 1122 */     Barcode b = new Barcode();
/* 1123 */     b.setIncludeLabel(includeLabel);
/* 1124 */     return b.encode(iType, data, drawColor, backColor);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Image DoEncode(TYPE iType, String data, boolean includeLabel, Color drawColor, Color backColor, int width, int height) {
/* 1140 */     Barcode b = new Barcode();
/* 1141 */     b.setIncludeLabel(includeLabel);
/* 1142 */     return b.encode(iType, data, drawColor, backColor, width, height);
/*      */   }
/*      */ }


/* Location:              C:\diao\bin\diao.jar!\com\pnuema\java\barcode\Barcode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package examples;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class ImagePreviewPanel
/*     */   extends JPanel
/*     */   implements PropertyChangeListener {
/*     */   private int width;
/*     */   private int height;
/*     */   private ImageIcon icon;
/*     */   private BufferedImage image;
/*     */   private static final int ACCSIZE = 155;
/*     */   private Color bg;
/*     */   
/*     */   public ImagePreviewPanel() {
/*  30 */     setPreferredSize(new Dimension(155, -1));
/*  31 */     this.bg = getBackground();
/*     */   }
/*     */   
/*     */   public void propertyChange(PropertyChangeEvent e) {
/*  35 */     String propertyName = e.getPropertyName();
/*     */ 
/*     */     
/*  38 */     if (propertyName.equals("SelectedFileChangedProperty")) {
/*  39 */       File selection = (File)e.getNewValue();
/*     */ 
/*     */       
/*  42 */       if (selection == null) {
/*     */         return;
/*     */       }
/*  45 */       String name = selection.getAbsolutePath();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  51 */       if ((name != null && name.toLowerCase().endsWith(".jpg")) || name
/*  52 */         .toLowerCase().endsWith(".jpeg") || name
/*  53 */         .toLowerCase().endsWith(".gif") || name
/*  54 */         .toLowerCase().endsWith(".bmp") || name
/*  55 */         .toLowerCase().endsWith(".png")) {
/*     */         try {
/*  57 */           this.image = ImageIO.read(selection);
/*     */ 
/*     */           
/*  60 */           scaleImage();
/*  61 */           repaint();
/*     */         }
/*  63 */         catch (IOException ex) {
/*  64 */           Logger.getLogger(ImagePreviewPanel.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void scaleImage() {
/*  72 */     this.width = this.image.getWidth(this);
/*  73 */     this.height = this.image.getHeight(this);
/*  74 */     double ratio = 1.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  81 */     if (this.width >= this.height) {
/*  82 */       ratio = 150.0D / this.width;
/*  83 */       this.width = 150;
/*  84 */       this.height = (int)(this.height * ratio);
/*     */     }
/*  86 */     else if (getHeight() > 150) {
/*  87 */       ratio = 150.0D / this.height;
/*  88 */       this.height = 150;
/*  89 */       this.width = (int)(this.width * ratio);
/*     */     } else {
/*  91 */       ratio = getHeight() / this.height;
/*  92 */       this.height = getHeight();
/*  93 */       this.width = (int)(this.width * ratio);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paintComponent(Graphics g) {
/* 101 */     g.setColor(this.bg);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 111 */     g.fillRect(0, 0, 155, getHeight());
/* 112 */     g.drawImage(this.image, getWidth() / 2 - this.width / 2 + 5, getHeight() / 2 - this.height / 2, this.width, this.height, this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 120 */     JFileChooser chooser = new JFileChooser();
/* 121 */     ImagePreviewPanel preview = new ImagePreviewPanel();
/* 122 */     chooser.setAccessory(preview);
/* 123 */     chooser.addPropertyChangeListener(preview);
/* 124 */     chooser.showOpenDialog((Component)null);
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\examples\ImagePreviewPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
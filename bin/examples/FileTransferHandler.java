/*     */ package examples;
/*     */ 
/*     */ import com.kitfox.svg.SVGDiagram;
/*     */ import com.kitfox.svg.SVGException;
/*     */ import com.kitfox.svg.SVGUniverse;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.datatransfer.DataFlavor;
/*     */ import java.awt.datatransfer.Transferable;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URI;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.TransferHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class FileTransferHandler
/*     */   extends TransferHandler
/*     */ {
/*  35 */   public static Hua_ban hb = null;
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean importData(JComponent comp, Transferable t) {
/*     */     try {
/*  41 */       List<File> list = (List<File>)t.getTransferData(DataFlavor.javaFileListFlavor);
/*  42 */       for (File f : list) {
/*  43 */         System.out.println(f.getAbsolutePath());
/*  44 */         String fileName = f.getAbsolutePath();
/*  45 */         String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
/*  46 */         suffix = suffix.toUpperCase();
/*  47 */         if (suffix.equals("BMP") || suffix.equals("JPG") || suffix.equals("PNG") || suffix.equals("JPEG") || suffix.equals("GIF")) {
/*     */           
/*     */           try {
/*  50 */             BufferedImage bmp = ImageIO.read(new File(fileName));
/*  51 */             if (bmp.getWidth() > 2400 || bmp.getHeight() > 2400) {
/*     */               double bi;
/*     */               
/*  54 */               if (bmp.getWidth() > bmp.getHeight()) {
/*     */                 
/*  56 */                 bi = 2400.0D / bmp.getWidth();
/*     */               } else {
/*     */                 
/*  59 */                 bi = 2400.0D / bmp.getHeight();
/*     */               } 
/*  61 */               bmp = Tu_pian.zoomImage(bmp, bi);
/*     */             } 
/*  63 */             Hua_ban.ty_shuzu.add(Tu_yuan.chuang_jian(1, bmp));
/*  64 */             for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++)
/*     */             {
/*  66 */               ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong = false;
/*     */             }
/*  68 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(Hua_ban.ty_shuzu.size() - 1)).xuan_zhong = true;
/*     */             
/*  70 */             Tu_yuan.zhong_xin(Hua_ban.ty_shuzu);
/*  71 */             if (hb != null)
/*  72 */               hb.repaint(); 
/*  73 */           } catch (IOException iOException) {}
/*     */           continue;
/*     */         } 
/*  76 */         if (suffix.equals("XJ")) {
/*     */ 
/*     */           
/*  79 */           try { ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName)); 
/*  80 */             try { Hua_ban.ty_shuzu = (List<Tu_yuan>)ois.readObject();
/*  81 */               ois.close(); } catch (Throwable throwable) { try { ois.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (Exception e)
/*  82 */           { e.printStackTrace(); }
/*     */           
/*  84 */           for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*     */             
/*  86 */             if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).lei_xing == 1) {
/*     */               
/*  88 */               ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu = new BufferedImage(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wt_w, ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wt_g, 2);
/*  89 */               ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu_yuan = new BufferedImage(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wty_w, ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wty_g, 2);
/*     */               
/*  91 */               ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu.setRGB(0, 0, ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wt_w, ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wt_g, ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu_, 0, ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wt_w);
/*  92 */               ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu_yuan.setRGB(0, 0, ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wty_w, ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wty_g, ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu_yuan_, 0, ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wty_w);
/*     */             } 
/*     */           } 
/*     */ 
/*     */           
/*  97 */           if (hb != null)
/*  98 */             hb.repaint();  continue;
/*  99 */         }  if (suffix.equals("PLT")) {
/*     */           
/* 101 */           jie_xi_PLT plt = new jie_xi_PLT();
/* 102 */           plt.jie_xi_PLT(f);
/* 103 */           plt = null;
/* 104 */           hb.updateUI(); continue;
/* 105 */         }  if (suffix.equals("HPGL")) {
/*     */           
/* 107 */           jie_xi_PLT plt = new jie_xi_PLT();
/* 108 */           plt.jie_xi_PLT(f);
/* 109 */           plt = null;
/* 110 */           hb.updateUI(); continue;
/* 111 */         }  if (suffix.equals("SVG")) {
/*     */           
/*     */           try {
/* 114 */             URI uri = f.toURI();
/* 115 */             SVGUniverse svg = new SVGUniverse();
/* 116 */             svg.loadSVG(uri.toURL());
/* 117 */             SVGDiagram ff = svg.getDiagram(uri);
/* 118 */             Rectangle2D rr = ff.getViewRect();
/* 119 */             BufferedImage bb = new BufferedImage((int)rr.getWidth(), (int)rr.getHeight(), 1);
/* 120 */             Graphics2D gg = bb.createGraphics();
/* 121 */             gg.setColor(Color.WHITE);
/* 122 */             gg.fillRect(0, 0, bb.getWidth(), bb.getHeight());
/*     */             
/* 124 */             ff.render(gg);
/* 125 */             Hua_ban.ty_shuzu.add(Tu_yuan.chuang_jian(1, bb));
/* 126 */             Tu_yuan.zhong_xin(Hua_ban.ty_shuzu);
/* 127 */             hb.updateUI();
/* 128 */             Che_xiao.tian_jia();
/* 129 */           } catch (MalformedURLException ex) {
/* 130 */             Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 131 */           } catch (SVGException ex) {
/* 132 */             Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, (Throwable)ex);
/*     */           }  continue;
/* 134 */         }  if (suffix.equals("DXF")) {
/*     */           
/* 136 */           jie_xi_dxf.jie_xi(f);
/* 137 */           Tu_yuan.zhong_xin(Hua_ban.ty_shuzu);
/* 138 */           hb.updateUI();
/* 139 */           Che_xiao.tian_jia();
/*     */         } 
/*     */       } 
/* 142 */     } catch (Exception e) {
/* 143 */       e.printStackTrace();
/*     */     } 
/* 145 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canImport(TransferHandler.TransferSupport support) {
/* 152 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\examples\FileTransferHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
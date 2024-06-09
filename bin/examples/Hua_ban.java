/*     */ package examples;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.TexturePaint;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ImageObserver;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Hua_ban
/*     */   extends JPanel
/*     */ {
/*  44 */   public static int quan_x = 0; public static int quan_y = 0;
/*  45 */   public static double quan_beishu = 1.0D;
/*  46 */   public static int quan_x2 = 0; public static int quan_y2 = 0;
/*  47 */   public static double quan_beishu2 = 1.0D;
/*     */ 
/*     */   
/*  50 */   public static List<Tu_yuan> ty_shuzu = new ArrayList<>();
/*  51 */   public static int dang_qian2 = -1;
/*  52 */   public AffineTransform Tx = new AffineTransform();
/*     */   
/*     */   public static boolean suo = true;
/*     */   
/*     */   public JPanel win;
/*     */   public JPanel jp;
/*     */   public JPanel jp2;
/*     */   public JLabel wb;
/*     */   public JTextField wb1;
/*     */   public JTextField wb2;
/*     */   public JTextField wb3;
/*     */   public JTextField wb4;
/*  64 */   public mainJFrame win2 = null;
/*  65 */   public int x = 0, y = 0;
/*  66 */   public int ww = 0; public int hh = 0;
/*     */   
/*     */   public boolean LeiXing_sl = false;
/*     */   public boolean LeiXing_tp = false;
/*     */   
/*     */   public void paint(Graphics g) {
/*  72 */     super.paint(g);
/*  73 */     Graphics2D g2D = (Graphics2D)g;
/*  74 */     boolean xuan_zhong = false;
/*     */     
/*  76 */     for (int i = 0; i < ty_shuzu.size(); i++) {
/*     */       
/*  78 */       GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)ty_shuzu.get(i)).lu_jing);
/*  79 */       lu_jing2.transform(((Tu_yuan)ty_shuzu.get(i)).Tx);
/*  80 */       Rectangle rect = lu_jing2.getBounds();
/*  81 */       double mm = Quan_ju.diao_ke_ji.kuan_du / 10.0D / rect.width;
/*  82 */       int w = (int)(mm / 0.02D);
/*  83 */       if (w < 1) w = 1; 
/*  84 */       if (i == 0) {
/*     */         
/*  86 */         g2D.setColor(Color.LIGHT_GRAY);
/*  87 */         g2D.draw(lu_jing2); int j;
/*  88 */         for (j = 0; j < Quan_ju.diao_ke_ji.kuan_du / 10 + 1; j++) {
/*     */           
/*  90 */           if (j % w == 0.0D)
/*  91 */             g2D.drawString(String.valueOf(j), (int)(rect.x + j * rect.width / Quan_ju.diao_ke_ji.kuan_du / 10.0D), rect.y); 
/*     */         } 
/*  93 */         for (j = 0; j < Quan_ju.diao_ke_ji.gao_du / 10 + 1; j++) {
/*     */           
/*  95 */           if (j % w == 0.0D)
/*  96 */             g2D.drawString(String.valueOf(j), rect.x - 16, (int)(rect.y + j * rect.height / Quan_ju.diao_ke_ji.gao_du / 10.0D + 10.0D)); 
/*     */         } 
/*  98 */         g2D.setColor(Color.BLACK);
/*     */       } else {
/*     */         
/* 101 */         g2D.setPaint(Color.BLACK);
/* 102 */         lu_jing2.setWindingRule(0);
/* 103 */         if (((Tu_yuan)ty_shuzu.get(i)).tian_chong && !((Tu_yuan)ty_shuzu.get(i)).wen_zi) {
/*     */           
/* 105 */           Image image2 = (new ImageIcon(getClass().getResource("/tu/" + (10 - Tu_yuan.tian_chong_md) + ".png"))).getImage();
/* 106 */           int kk = image2.getWidth(null), gg = image2.getHeight(null);
/* 107 */           BufferedImage image = new BufferedImage(kk, gg, 1);
/* 108 */           Graphics2D g2 = (Graphics2D)image.getGraphics();
/* 109 */           g2.drawImage(image2, 0, 0, kk, gg, null);
/* 110 */           Rectangle2D.Float rect2 = new Rectangle2D.Float(0.0F, 0.0F, kk, gg);
/* 111 */           TexturePaint textPaint = new TexturePaint(image, rect2);
/* 112 */           g2D.setPaint(textPaint);
/* 113 */           g2D.fill(lu_jing2);
/*     */         } 
/* 115 */         if (((Tu_yuan)ty_shuzu.get(i)).wen_zi) {
/*     */           
/* 117 */           g2D.setPaint(Color.BLACK);
/* 118 */           g2D.fill(lu_jing2);
/*     */         } 
/* 120 */         g2D.setColor(Color.BLUE);
/* 121 */         g2D.draw(lu_jing2);
/*     */         
/* 123 */         g2D.setColor(Color.BLUE);
/* 124 */         if (Tu_yuan.tuo)
/* 125 */           g2D.draw(Tu_yuan.shu_biao); 
/* 126 */         g2D.setColor(Color.RED);
/*     */ 
/*     */         
/* 129 */         g2D.setColor(Color.BLACK);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 137 */       if (((Tu_yuan)ty_shuzu.get(i)).lei_xing == 1)
/*     */       {
/* 139 */         g2D.drawImage(((Tu_yuan)ty_shuzu.get(i)).wei_tu, ((Tu_yuan)ty_shuzu.get(i)).Tx, (ImageObserver)null);
/*     */       }
/* 141 */       if (((Tu_yuan)ty_shuzu.get(i)).xuan_zhong)
/*     */       {
/* 143 */         xuan_zhong = true;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 148 */     if (xuan_zhong && !Tu_yuan.tuo) {
/*     */       
/* 150 */       Rectangle rect = Tu_yuan.qu_jv_xing(ty_shuzu);
/* 151 */       g2D.setColor(Color.GREEN);
/* 152 */       g2D.draw(rect);
/*     */       
/* 154 */       g2D.drawImage((new ImageIcon(getClass().getResource("/tu/ic_icon_delete.png"))).getImage(), rect.x - 15, rect.y - 20, 40, 40, null);
/* 155 */       g2D.drawImage((new ImageIcon(getClass().getResource("/tu/ic_icon_rotate.png"))).getImage(), rect.x + rect.width - 20, rect.y - 20, 40, 40, null);
/* 156 */       g2D.drawImage((new ImageIcon(getClass().getResource("/tu/ic_icon_scale.png"))).getImage(), rect.x + rect.width - 20, rect.y + rect.height - 20, 40, 40, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 200 */       g2D.setColor(Color.BLUE);
/* 201 */       g2D.setFont(new Font(g2D.getFont().getName(), g2D.getFont().getStyle(), 16));
/*     */       
/* 203 */       Point2D d = zhuan_huan(rect.getLocation());
/* 204 */       this.x = (int)Math.round(d.getX() * Quan_ju.diao_ke_ji.fen_bian_lv);
/* 205 */       this.y = (int)Math.round(d.getY() * Quan_ju.diao_ke_ji.fen_bian_lv);
/* 206 */       d = zhuan_huan(new Point(rect.x + rect.width, rect.y + rect.height));
/* 207 */       this.ww = (int)Math.round(d.getX() * Quan_ju.diao_ke_ji.fen_bian_lv) - this.x;
/* 208 */       this.hh = (int)Math.round(d.getY() * Quan_ju.diao_ke_ji.fen_bian_lv) - this.y;
/* 209 */       this.jp.setLocation(rect.x - 18, rect.y - 60);
/* 210 */       this.jp.setSize(130, 35);
/* 211 */       this.wb1.setText(String.valueOf(this.x));
/* 212 */       this.wb2.setText(String.valueOf(this.y));
/* 213 */       this.wb3.setText(String.valueOf(this.ww));
/* 214 */       this.wb4.setText(String.valueOf(this.hh));
/*     */ 
/*     */ 
/*     */       
/* 218 */       g2D.setColor(Color.BLACK);
/*     */     } 
/*     */     
/* 221 */     if (this.jp != null && 
/* 222 */       !xuan_zhong)
/*     */     {
/* 224 */       this.jp.setLocation(2, -this.jp.getHeight());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void xuan_zhong_lei_xing() {
/* 230 */     this.LeiXing_sl = false;
/* 231 */     this.LeiXing_tp = false;
/* 232 */     for (int i = 1; i < ty_shuzu.size(); i++) {
/*     */       
/* 234 */       if (((Tu_yuan)ty_shuzu.get(i)).xuan_zhong)
/*     */       {
/* 236 */         if (((Tu_yuan)ty_shuzu.get(i)).lei_xing == 0) {
/*     */           
/* 238 */           this.LeiXing_sl = true;
/*     */         } else {
/*     */           
/* 241 */           this.LeiXing_tp = true;
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   Point2D zhuan_huan(Point2D m) {
/* 248 */     GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)ty_shuzu.get(0)).lu_jing);
/* 249 */     lu_jing2.transform(((Tu_yuan)ty_shuzu.get(0)).Tx);
/* 250 */     Rectangle rect = lu_jing2.getBounds();
/* 251 */     AffineTransform sf = AffineTransform.getTranslateInstance((0 - rect.x), (0 - rect.y));
/* 252 */     sf.transform(m, m);
/* 253 */     sf = AffineTransform.getScaleInstance(1.0D / quan_beishu, 1.0D / quan_beishu);
/* 254 */     sf.transform(m, m);
/* 255 */     return m;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void di_tu() {
/* 262 */     Tu_yuan.hui_fu();
/* 263 */     Tu_yuan ty = new Tu_yuan();
/* 264 */     ty.lei_xing = 0;
/* 265 */     quan_beishu = 1.0D;
/* 266 */     quan_x = 0;
/* 267 */     quan_y = 0; int j;
/* 268 */     for (j = 0; j < Quan_ju.diao_ke_ji.gao_du + 1; j++) {
/*     */       
/* 270 */       if (j == 0 || j % 5 == 0 || j == Quan_ju.diao_ke_ji.gao_du) {
/*     */         
/* 272 */         ty.lu_jing.moveTo(0.0D, j / Quan_ju.diao_ke_ji.fen_bian_lv);
/* 273 */         ty.lu_jing.lineTo(Quan_ju.diao_ke_ji.kuan_du / Quan_ju.diao_ke_ji.fen_bian_lv, j / Quan_ju.diao_ke_ji.fen_bian_lv);
/*     */       } 
/*     */     } 
/* 276 */     for (j = 0; j < Quan_ju.diao_ke_ji.kuan_du + 1; j++) {
/*     */       
/* 278 */       if (j == 0 || j % 5 == 0 || j == Quan_ju.diao_ke_ji.kuan_du) {
/*     */         
/* 280 */         ty.lu_jing.moveTo(j / Quan_ju.diao_ke_ji.fen_bian_lv, 0.0D);
/* 281 */         ty.lu_jing.lineTo(j / Quan_ju.diao_ke_ji.fen_bian_lv, Quan_ju.diao_ke_ji.gao_du / Quan_ju.diao_ke_ji.fen_bian_lv);
/*     */       } 
/*     */     } 
/* 284 */     ty_shuzu.set(0, ty);
/* 285 */     repaint();
/* 286 */     Tu_yuan.hui_fu_xian_chang();
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\examples\Hua_ban.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
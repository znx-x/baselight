/*      */ package examples;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.font.LineMetrics;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.Ellipse2D;
/*      */ import java.awt.geom.GeneralPath;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.awt.image.ImageObserver;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import javax.imageio.ImageIO;
/*      */ import javax.swing.JOptionPane;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Tu_yuan
/*      */   implements Serializable
/*      */ {
/*      */   public static final int lei_xing_tupian = 1;
/*      */   public static final int lei_xing_lujing = 0;
/*   46 */   public int lei_xing = 0;
/*   47 */   public transient BufferedImage wei_tu = null;
/*   48 */   public transient BufferedImage wei_tu_yuan = null;
/*   49 */   public int[] wei_tu_ = null;
/*   50 */   public int[] wei_tu_yuan_ = null;
/*   51 */   public int wt_w = 0;
/*   52 */   public int wt_g = 0;
/*   53 */   public int wty_w = 0;
/*   54 */   public int wty_g = 0;
/*   55 */   public int chuli_fs = 1;
/*      */   public boolean chuli_fan = false;
/*      */   public boolean chuli_jxx = false;
/*      */   public boolean chuli_jxy = false;
/*      */   public boolean tian_chong = false;
/*   60 */   public static int tian_chong_md = 5;
/*   61 */   public int yu_zhi = 50;
/*   62 */   public GeneralPath lu_jing = new GeneralPath();
/*      */   public boolean xuan_zhong = false;
/*   64 */   public AffineTransform Tx = new AffineTransform();
/*   65 */   public static Rectangle zui_zhong_wjx = new Rectangle();
/*   66 */   public static Rectangle wei_tu_wjx = new Rectangle();
/*   67 */   public static Rectangle shi_liang_wjx = new Rectangle();
/*   68 */   public static Rectangle shu_biao = new Rectangle();
/*      */   public static boolean tuo = false;
/*   70 */   public double jiao_du = 0.0D;
/*   71 */   public static int dk_gonglv = 100;
/*   72 */   public static int dk_shendu = 10;
/*   73 */   public static int dk_gonglv_sl = 100;
/*   74 */   public static int dk_shendu_sl = 10;
/*   75 */   public static int dk_cishu = 1;
/*   76 */   public static int dk_ruo_guang = 10;
/*   77 */   public static int dk_jing_du = 2;
/*   78 */   public static List<Dian> dian = null;
/*   79 */   public liu_shui_hao lsh = null;
/*      */   public boolean wen_zi = false;
/*      */   
/*      */   public static List<Tu_yuan> fu_zhi(List<Tu_yuan> ty) {
/*   83 */     List<Tu_yuan> fh = new ArrayList<>();
/*   84 */     for (int i = 0; i < ty.size(); i++)
/*      */     {
/*   86 */       fh.add(fu_zhi(ty.get(i)));
/*      */     }
/*   88 */     return fh;
/*      */   }
/*      */   
/*      */   public static Tu_yuan fu_zhi(Tu_yuan ty) {
/*   92 */     Tu_yuan fh = chuang_jian(ty.lei_xing, ty.wei_tu);
/*   93 */     fh.Tx = new AffineTransform(ty.Tx);
/*   94 */     fh.xuan_zhong = ty.xuan_zhong;
/*   95 */     fh.lu_jing = new GeneralPath(ty.lu_jing);
/*   96 */     fh.chuli_fan = ty.chuli_fan;
/*   97 */     fh.chuli_fs = ty.chuli_fs;
/*   98 */     fh.chuli_jxx = ty.chuli_jxx;
/*   99 */     fh.chuli_jxy = ty.chuli_jxy;
/*  100 */     fh.tian_chong = ty.tian_chong;
/*  101 */     fh.yu_zhi = ty.yu_zhi;
/*  102 */     return fh;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void ping_yi(int x, int y) {
/*  108 */     AffineTransform Tx2 = AffineTransform.getTranslateInstance(x, y);
/*  109 */     Tx2.concatenate(this.Tx);
/*  110 */     this.Tx = Tx2;
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
/*      */   
/*      */   void xuan_zhuan(double jiao, double zx, double zy) {
/*  127 */     this.jiao_du += jiao;
/*  128 */     jiao = jiao * 3.14D / 180.0D;
/*      */     
/*  130 */     AffineTransform Tx2 = AffineTransform.getRotateInstance(jiao, zx, zy);
/*  131 */     Tx2.concatenate(this.Tx);
/*  132 */     this.Tx = Tx2;
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
/*      */   void suo_fang(double beishu_x, double beishu_y) {
/*  146 */     AffineTransform Tx2 = AffineTransform.getScaleInstance(beishu_x, beishu_y);
/*  147 */     Tx2.concatenate(this.Tx);
/*  148 */     this.Tx = Tx2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static void zhong_xin(List<Tu_yuan> sz) {
/*  157 */     Rectangle rect = qu_jv_xing(Hua_ban.ty_shuzu);
/*  158 */     GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)Hua_ban.ty_shuzu.get(0)).lu_jing);
/*  159 */     lu_jing2.transform(((Tu_yuan)Hua_ban.ty_shuzu.get(0)).Tx);
/*  160 */     Rectangle rect2 = lu_jing2.getBounds();
/*  161 */     double x = (rect2.x + rect2.width / 2);
/*  162 */     double y = (rect2.y + rect2.height / 2);
/*  163 */     for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */       
/*  165 */       if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong)
/*      */       {
/*  167 */         ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).ping_yi((int)(x - (rect.x + rect.width / 2)), (int)(y - (rect.y + rect.height / 2)));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Rectangle qu_jv_xing(Tu_yuan ty) {
/*  178 */     GeneralPath lu_jing2 = new GeneralPath(ty.lu_jing);
/*  179 */     lu_jing2.transform(ty.Tx);
/*  180 */     Rectangle rect = lu_jing2.getBounds();
/*  181 */     return rect;
/*      */   }
/*      */   
/*      */   public static Rectangle qu_jv_xing(List<Tu_yuan> sz) {
/*  185 */     GeneralPath lu_jing2 = new GeneralPath();
/*  186 */     for (int i = 0; i < sz.size(); i++) {
/*      */       
/*  188 */       if (((Tu_yuan)sz.get(i)).xuan_zhong) {
/*      */         
/*  190 */         GeneralPath lu_jing3 = new GeneralPath(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).lu_jing);
/*  191 */         lu_jing3.transform(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).Tx);
/*  192 */         lu_jing2.append(lu_jing3, true);
/*      */       } 
/*      */     } 
/*  195 */     Rectangle rect = lu_jing2.getBounds();
/*  196 */     return rect;
/*      */   }
/*      */   
/*      */   public static void kuang_xuan(List<Tu_yuan> sz, Rectangle rect) {
/*  200 */     for (int i = 1; i < sz.size(); i++) {
/*      */       
/*  202 */       GeneralPath lu_jing3 = new GeneralPath(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).lu_jing);
/*  203 */       lu_jing3.transform(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).Tx);
/*  204 */       if (rect.contains(lu_jing3.getBounds())) {
/*      */         
/*  206 */         ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong = true;
/*      */       } else {
/*      */         
/*  209 */         ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong = false;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static Tu_yuan chuang_jian_wen_zi(String ss, Font zt, boolean sl) {
/*  215 */     Tu_yuan fh = chuang_jian(0, null);
/*  216 */     fh.lu_jing = Wen_zi.getGeneralPath(ss, zt, false);
/*  217 */     fh.wen_zi = !sl;
/*  218 */     return fh;
/*      */   }
/*      */   
/*      */   public static Tu_yuan chuang_jian_wen_zi_shu(String ss, Font zt, int gao, boolean sl) {
/*  222 */     Tu_yuan fh = chuang_jian(0, null);
/*  223 */     fh.lu_jing = Wen_zi.getGeneralPath(ss, zt, true);
/*  224 */     fh.wen_zi = !sl;
/*  225 */     return fh;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Tu_yuan chuang_jian_wen_zi2(String ss, Font zt, boolean sl) {
/*  230 */     BufferedImage image = new BufferedImage(1, 1, 2);
/*  231 */     Graphics2D graphics = image.createGraphics();
/*  232 */     FontMetrics fm = graphics.getFontMetrics(zt);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  237 */     int g = fm.getHeight();
/*  238 */     g = (int)(g + g * 0.3D);
/*  239 */     String[] sz = ss.split("\n");
/*  240 */     int da = 0;
/*  241 */     for (int i = 0; i < sz.length; i++) {
/*      */       
/*  243 */       int dq = fm.stringWidth(sz[i]);
/*  244 */       if (dq > da)
/*  245 */         da = dq; 
/*      */     } 
/*  247 */     da = (int)(da + da * 0.3D);
/*  248 */     graphics.dispose();
/*  249 */     BufferedImage bimg2 = new BufferedImage(da, sz.length * g, 2);
/*      */     
/*  251 */     Graphics2D g2d2 = (Graphics2D)bimg2.getGraphics();
/*  252 */     g2d2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
/*  253 */     g2d2.setBackground(Color.WHITE);
/*  254 */     g2d2.setFont(zt);
/*  255 */     g2d2.clearRect(0, 0, bimg2.getWidth(), bimg2.getHeight());
/*  256 */     g2d2.setColor(Color.BLACK);
/*      */     
/*  258 */     for (int j = 0; j < sz.length; j++)
/*      */     {
/*  260 */       g2d2.drawString(sz[j], 0, (int)((g * (j + 1)) - g * 0.3D));
/*      */     }
/*  262 */     if (!sl) {
/*      */       
/*  264 */       Tu_yuan tu_yuan = chuang_jian(1, bimg2);
/*  265 */       return tu_yuan;
/*      */     } 
/*      */     
/*  268 */     GeneralPath lj = to_ls(bimg2);
/*  269 */     Tu_yuan fh = chuang_jian(0, null);
/*      */     
/*  271 */     fh.lu_jing = new GeneralPath(lj);
/*  272 */     return fh;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Tu_yuan chuang_jian_wen_zi_shu2(String ss, Font zt, int gao, boolean sl) {
/*  278 */     BufferedImage bimg = new BufferedImage(10, 10, 2);
/*      */     
/*  280 */     Graphics2D g2d = (Graphics2D)bimg.getGraphics();
/*      */     
/*  282 */     Font font = new Font(zt.getName(), zt.getStyle(), zt.getSize());
/*      */     
/*  284 */     g2d.setFont(font);
/*  285 */     int k = g2d.getFontMetrics().stringWidth("信");
/*  286 */     int g = g2d.getFontMetrics().getHeight();
/*  287 */     FontMetrics fm = g2d.getFontMetrics(font);
/*  288 */     LineMetrics line = fm.getLineMetrics(ss, g2d);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  297 */     BufferedImage bimg2 = new BufferedImage(k, ss.length() * (g + gao), 2);
/*      */     
/*  299 */     Graphics2D g2d2 = (Graphics2D)bimg2.getGraphics();
/*  300 */     g2d2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
/*  301 */     g2d2.setBackground(Color.WHITE);
/*  302 */     g2d2.setFont(font);
/*  303 */     g2d2.clearRect(0, 0, bimg2.getWidth(), bimg2.getHeight());
/*  304 */     g2d2.setColor(Color.BLACK);
/*  305 */     String tempStr = new String();
/*  306 */     for (int i = 0; i < ss.length(); i++) {
/*      */       
/*  308 */       tempStr = ss.substring(i, i + 1);
/*  309 */       g2d2.drawString(tempStr, 0.0F, line.getAscent() + ((g + gao) * i));
/*      */     } 
/*  311 */     if (!sl) {
/*      */       
/*  313 */       Tu_yuan tu_yuan = chuang_jian(1, bimg2);
/*  314 */       return tu_yuan;
/*      */     } 
/*      */     
/*  317 */     GeneralPath lj = to_ls(bimg2);
/*  318 */     Tu_yuan fh = chuang_jian(0, null);
/*      */     
/*  320 */     fh.lu_jing = new GeneralPath(lj);
/*  321 */     return fh;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Tu_yuan chuang_jian(int leixing, BufferedImage wt) {
/*      */     double bi;
/*      */     Ellipse2D.Float d;
/*  328 */     Tu_yuan ty = new Tu_yuan();
/*  329 */     GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)Hua_ban.ty_shuzu.get(0)).lu_jing);
/*  330 */     lu_jing2.transform(((Tu_yuan)Hua_ban.ty_shuzu.get(0)).Tx);
/*  331 */     Rectangle rect = lu_jing2.getBounds();
/*  332 */     ty.Tx.translate(rect.x, rect.y);
/*  333 */     AffineTransform sf = AffineTransform.getScaleInstance(Hua_ban.quan_beishu, Hua_ban.quan_beishu);
/*  334 */     ty.Tx.concatenate(sf);
/*  335 */     switch (leixing) {
/*      */       
/*      */       case 0:
/*  338 */         ty.lei_xing = 0;
/*  339 */         ty.lu_jing.moveTo(0.0F, 0.0F);
/*  340 */         ty.lu_jing.lineTo(400.0F, 0.0F);
/*  341 */         ty.lu_jing.lineTo(400.0F, 400.0F);
/*  342 */         ty.lu_jing.lineTo(0.0F, 400.0F);
/*  343 */         ty.lu_jing.closePath();
/*      */         break;
/*      */       case 1:
/*  346 */         bi = 0.0D;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  363 */         ty.wei_tu_yuan = wt;
/*  364 */         ty.wei_tu_yuan = Tu_pian.qu_you_xiao(ty.wei_tu_yuan);
/*  365 */         ty.lei_xing = 1;
/*  366 */         ty.chuli_fs = 1;
/*  367 */         ty.wei_tu = Tu_pian.hui_du(ty.wei_tu_yuan);
/*  368 */         ty.wei_tu = Tu_pian.heibai(ty.wei_tu, 128);
/*  369 */         ty.lu_jing.moveTo(0.0F, 0.0F);
/*  370 */         ty.lu_jing.lineTo(ty.wei_tu.getWidth(), 0.0F);
/*  371 */         ty.lu_jing.lineTo(ty.wei_tu.getWidth(), ty.wei_tu.getHeight());
/*  372 */         ty.lu_jing.lineTo(0.0F, ty.wei_tu.getHeight());
/*  373 */         ty.lu_jing.closePath();
/*      */         break;
/*      */       case 2:
/*  376 */         ty.lei_xing = 0;
/*  377 */         d = new Ellipse2D.Float(1.0F, 1.0F, 400.0F, 400.0F);
/*  378 */         ty.lu_jing.append(d, false);
/*      */         break;
/*      */       case 3:
/*  381 */         ty.lei_xing = 0;
/*  382 */         ty.lu_jing.moveTo(197.0F, 102.0F);
/*  383 */         ty.lu_jing.lineTo(212.0F, 69.0F);
/*  384 */         ty.lu_jing.lineTo(224.0F, 48.0F);
/*  385 */         ty.lu_jing.lineTo(242.0F, 27.0F);
/*  386 */         ty.lu_jing.lineTo(266.0F, 10.0F);
/*  387 */         ty.lu_jing.lineTo(304.0F, 0.0F);
/*  388 */         ty.lu_jing.lineTo(343.0F, 10.0F);
/*  389 */         ty.lu_jing.lineTo(363.0F, 27.0F);
/*  390 */         ty.lu_jing.lineTo(378.0F, 48.0F);
/*  391 */         ty.lu_jing.lineTo(387.0F, 69.0F);
/*  392 */         ty.lu_jing.lineTo(393.0F, 102.0F);
/*  393 */         ty.lu_jing.lineTo(390.0F, 150.0F);
/*  394 */         ty.lu_jing.lineTo(372.0F, 208.0F);
/*  395 */         ty.lu_jing.lineTo(343.0F, 264.0F);
/*  396 */         ty.lu_jing.lineTo(295.0F, 322.0F);
/*  397 */         ty.lu_jing.lineTo(197.0F, 394.0F);
/*  398 */         ty.lu_jing.lineTo(98.0F, 322.0F);
/*  399 */         ty.lu_jing.lineTo(50.0F, 264.0F);
/*  400 */         ty.lu_jing.lineTo(20.0F, 208.0F);
/*  401 */         ty.lu_jing.lineTo(3.0F, 150.0F);
/*  402 */         ty.lu_jing.lineTo(0.0F, 102.0F);
/*  403 */         ty.lu_jing.lineTo(6.0F, 69.0F);
/*  404 */         ty.lu_jing.lineTo(15.0F, 48.0F);
/*  405 */         ty.lu_jing.lineTo(29.0F, 27.0F);
/*  406 */         ty.lu_jing.lineTo(50.0F, 10.0F);
/*  407 */         ty.lu_jing.lineTo(88.0F, 0.0F);
/*  408 */         ty.lu_jing.lineTo(128.0F, 10.0F);
/*  409 */         ty.lu_jing.lineTo(151.0F, 27.0F);
/*  410 */         ty.lu_jing.lineTo(170.0F, 48.0F);
/*  411 */         ty.lu_jing.lineTo(183.0F, 69.0F);
/*  412 */         ty.lu_jing.closePath();
/*      */         break;
/*      */       case 4:
/*  415 */         ty.lei_xing = 0;
/*  416 */         ty.lu_jing.moveTo(121.0F, 0.0F);
/*  417 */         ty.lu_jing.lineTo(149.0F, 93.0F);
/*  418 */         ty.lu_jing.lineTo(241.0F, 94.0F);
/*  419 */         ty.lu_jing.lineTo(169.0F, 149.0F);
/*  420 */         ty.lu_jing.lineTo(196.0F, 241.0F);
/*  421 */         ty.lu_jing.lineTo(122.0F, 188.0F);
/*  422 */         ty.lu_jing.lineTo(46.0F, 241.0F);
/*  423 */         ty.lu_jing.lineTo(72.0F, 149.0F);
/*  424 */         ty.lu_jing.lineTo(0.0F, 94.0F);
/*  425 */         ty.lu_jing.lineTo(92.0F, 93.0F);
/*  426 */         ty.lu_jing.closePath();
/*      */         break;
/*      */     } 
/*  429 */     return ty;
/*      */   }
/*      */ 
/*      */   
/*      */   public BufferedImage qu_tu() {
/*  434 */     GeneralPath lu_jing2 = new GeneralPath(this.lu_jing);
/*  435 */     lu_jing2.transform(this.Tx);
/*  436 */     Rectangle jx = lu_jing2.getBounds();
/*  437 */     System.out.println(jx);
/*  438 */     System.out.println(this.Tx);
/*  439 */     AffineTransform tx2 = new AffineTransform(this.Tx.getScaleX(), this.Tx.getShearY(), this.Tx.getShearX(), this.Tx.getScaleY(), this.Tx.getTranslateX() - jx.x, this.Tx.getTranslateY() - jx.y);
/*  440 */     System.out.println(tx2);
/*      */ 
/*      */ 
/*      */     
/*  444 */     BufferedImage fh = new BufferedImage(jx.width, jx.height, 2);
/*  445 */     Graphics2D g2D = fh.createGraphics();
/*  446 */     g2D.setBackground(Color.WHITE);
/*  447 */     g2D.clearRect(0, 0, jx.width, jx.height);
/*  448 */     g2D.drawImage(this.wei_tu_yuan, tx2, (ImageObserver)null);
/*  449 */     if (this.lei_xing == 0) {
/*      */       
/*  451 */       if (this.chuli_jxx) {
/*      */         
/*  453 */         this.chuli_jxx = false;
/*  454 */         Rectangle rect = qu_jv_xing(Hua_ban.ty_shuzu);
/*  455 */         for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */           
/*  457 */           if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong) {
/*      */             
/*  459 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).ping_yi(-rect.x - rect.width / 2, 0);
/*  460 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).suo_fang(-1.0D, 1.0D);
/*  461 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).ping_yi(rect.x + rect.width / 2, 0);
/*      */           } 
/*      */         } 
/*      */       } 
/*  465 */       if (this.chuli_jxy) {
/*      */         
/*  467 */         this.chuli_jxy = false;
/*  468 */         Rectangle rect = qu_jv_xing(Hua_ban.ty_shuzu);
/*  469 */         for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */           
/*  471 */           if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong) {
/*      */             
/*  473 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).ping_yi(0, -rect.y - rect.height / 2);
/*  474 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).suo_fang(1.0D, -1.0D);
/*  475 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).ping_yi(0, rect.y + rect.height / 2);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/*  481 */       switch (this.chuli_fs) {
/*      */         
/*      */         case 1:
/*  484 */           fh = Tu_pian.hui_du(fh);
/*  485 */           fh = Tu_pian.heibai(fh, (int)(this.yu_zhi * 2.56D));
/*  486 */           if (this.chuli_fan)
/*      */           {
/*  488 */             fh = Tu_pian.fanse(fh);
/*      */           }
/*  490 */           if (this.chuli_jxx)
/*      */           {
/*  492 */             fh = Tu_pian.jing_xiang_x(fh);
/*      */           }
/*  494 */           if (this.chuli_jxy)
/*      */           {
/*  496 */             fh = Tu_pian.jing_xiang_y(fh);
/*      */           }
/*      */           break;
/*      */         case 2:
/*  500 */           fh = Tu_pian.hui_du(fh);
/*  501 */           fh = Tu_pian.convertGreyImgByFloyd(fh, (int)(this.yu_zhi * 2.56D));
/*  502 */           if (this.chuli_fan)
/*      */           {
/*  504 */             fh = Tu_pian.fanse(fh);
/*      */           }
/*  506 */           if (this.chuli_jxx)
/*      */           {
/*  508 */             fh = Tu_pian.jing_xiang_x(fh);
/*      */           }
/*  510 */           if (this.chuli_jxy)
/*      */           {
/*  512 */             fh = Tu_pian.jing_xiang_y(fh);
/*      */           }
/*      */           break;
/*      */         case 3:
/*  516 */           fh = Tu_pian.hui_du(fh);
/*  517 */           fh = Tu_pian.heibai(fh, 128);
/*  518 */           fh = Tu_pian.qu_lunkuo(fh, (int)(this.yu_zhi * 2.56D));
/*  519 */           if (this.chuli_jxx)
/*      */           {
/*  521 */             fh = Tu_pian.jing_xiang_x(fh);
/*      */           }
/*  523 */           if (this.chuli_jxy)
/*      */           {
/*  525 */             fh = Tu_pian.jing_xiang_y(fh);
/*      */           }
/*      */           break;
/*      */         case 4:
/*  529 */           fh = Tu_pian.su_miao(fh);
/*      */           
/*  531 */           fh = Tu_pian.heibai(fh, 50 + (int)(this.yu_zhi * 2.56D));
/*  532 */           if (this.chuli_fan)
/*      */           {
/*  534 */             fh = Tu_pian.fanse(fh);
/*      */           }
/*  536 */           if (this.chuli_jxx)
/*      */           {
/*  538 */             fh = Tu_pian.jing_xiang_x(fh);
/*      */           }
/*  540 */           if (this.chuli_jxy)
/*      */           {
/*  542 */             fh = Tu_pian.jing_xiang_y(fh);
/*      */           }
/*      */           break;
/*      */       } 
/*      */     } 
/*  547 */     fh = fh.getSubimage(3, 3, fh.getWidth() - 6, fh.getHeight() - 6);
/*  548 */     return fh;
/*      */   }
/*      */   
/*      */   public void chu_li() {
/*  552 */     if (this.lei_xing == 0) {
/*      */       
/*  554 */       if (this.chuli_jxx) {
/*      */         
/*  556 */         this.chuli_jxx = false;
/*  557 */         Rectangle rect = qu_jv_xing(Hua_ban.ty_shuzu);
/*  558 */         for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */           
/*  560 */           if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong) {
/*      */             
/*  562 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).ping_yi(-rect.x - rect.width / 2, 0);
/*  563 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).suo_fang(-1.0D, 1.0D);
/*  564 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).ping_yi(rect.x + rect.width / 2, 0);
/*      */           } 
/*      */         } 
/*      */       } 
/*  568 */       if (this.chuli_jxy) {
/*      */         
/*  570 */         this.chuli_jxy = false;
/*  571 */         Rectangle rect = qu_jv_xing(Hua_ban.ty_shuzu);
/*  572 */         for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */           
/*  574 */           if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong) {
/*      */             
/*  576 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).ping_yi(0, -rect.y - rect.height / 2);
/*  577 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).suo_fang(1.0D, -1.0D);
/*  578 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).ping_yi(0, rect.y + rect.height / 2);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/*  584 */       switch (this.chuli_fs) {
/*      */         
/*      */         case 1:
/*  587 */           this.wei_tu = Tu_pian.hui_du(this.wei_tu_yuan);
/*  588 */           this.wei_tu = Tu_pian.heibai(this.wei_tu, (int)(this.yu_zhi * 2.56D));
/*  589 */           if (this.chuli_fan)
/*      */           {
/*  591 */             this.wei_tu = Tu_pian.fanse(this.wei_tu);
/*      */           }
/*  593 */           if (this.chuli_jxx)
/*      */           {
/*  595 */             this.wei_tu = Tu_pian.jing_xiang_x(this.wei_tu);
/*      */           }
/*  597 */           if (this.chuli_jxy)
/*      */           {
/*  599 */             this.wei_tu = Tu_pian.jing_xiang_y(this.wei_tu);
/*      */           }
/*      */           break;
/*      */         case 2:
/*  603 */           this.wei_tu = Tu_pian.hui_du(this.wei_tu_yuan);
/*  604 */           this.wei_tu = Tu_pian.convertGreyImgByFloyd(this.wei_tu, (int)(this.yu_zhi * 2.56D));
/*  605 */           if (this.chuli_fan)
/*      */           {
/*  607 */             this.wei_tu = Tu_pian.fanse(this.wei_tu);
/*      */           }
/*  609 */           if (this.chuli_jxx)
/*      */           {
/*  611 */             this.wei_tu = Tu_pian.jing_xiang_x(this.wei_tu);
/*      */           }
/*  613 */           if (this.chuli_jxy)
/*      */           {
/*  615 */             this.wei_tu = Tu_pian.jing_xiang_y(this.wei_tu);
/*      */           }
/*      */           break;
/*      */ 
/*      */         
/*      */         case 3:
/*  621 */           this.wei_tu = Tu_pian.qu_lunkuo(this.wei_tu_yuan, (int)(this.yu_zhi * 2.56D));
/*  622 */           if (this.chuli_jxx)
/*      */           {
/*  624 */             this.wei_tu = Tu_pian.jing_xiang_x(this.wei_tu);
/*      */           }
/*  626 */           if (this.chuli_jxy)
/*      */           {
/*  628 */             this.wei_tu = Tu_pian.jing_xiang_y(this.wei_tu);
/*      */           }
/*      */           break;
/*      */         case 4:
/*  632 */           this.wei_tu = Tu_pian.su_miao2(this.wei_tu_yuan);
/*      */           
/*  634 */           this.wei_tu = Tu_pian.heibai(this.wei_tu, 50 + (int)(this.yu_zhi * 2.56D));
/*  635 */           if (this.chuli_fan)
/*      */           {
/*  637 */             this.wei_tu = Tu_pian.fanse(this.wei_tu);
/*      */           }
/*  639 */           if (this.chuli_jxx)
/*      */           {
/*  641 */             this.wei_tu = Tu_pian.jing_xiang_x(this.wei_tu);
/*      */           }
/*  643 */           if (this.chuli_jxy)
/*      */           {
/*  645 */             this.wei_tu = Tu_pian.jing_xiang_y(this.wei_tu);
/*      */           }
/*      */           break;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void qu_jvxing(List<Tu_yuan> sz) {
/*  654 */     int z = 0, d = 0, y = 0, x = 0;
/*  655 */     for (int i = 1; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */       
/*  657 */       GeneralPath generalPath = new GeneralPath(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).lu_jing);
/*  658 */       generalPath.transform(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).Tx);
/*  659 */       Rectangle jx = generalPath.getBounds();
/*  660 */       if (i == 1) {
/*      */         
/*  662 */         z = jx.x;
/*  663 */         d = jx.y;
/*  664 */         y = jx.x + jx.width;
/*  665 */         x = jx.y + jx.height;
/*      */       } else {
/*      */         
/*  668 */         if (jx.x < z)
/*  669 */           z = jx.x; 
/*  670 */         if (jx.y < d)
/*  671 */           d = jx.y; 
/*  672 */         if (jx.x + jx.width > y)
/*  673 */           y = jx.x + jx.width; 
/*  674 */         if (jx.y + jx.height > x)
/*  675 */           x = jx.y + jx.height; 
/*      */       } 
/*      */     } 
/*  678 */     GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)Hua_ban.ty_shuzu.get(0)).lu_jing);
/*  679 */     lu_jing2.transform(((Tu_yuan)Hua_ban.ty_shuzu.get(0)).Tx);
/*  680 */     Rectangle jx2 = lu_jing2.getBounds();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  690 */     jx2.createIntersection(new Rectangle(z, d, y - z, x - d));
/*  691 */     zui_zhong_wjx = (new Rectangle(z, d, y - z, x - d)).createIntersection(jx2).getBounds();
/*  692 */     if (zui_zhong_wjx.width > 0 && zui_zhong_wjx.height > 0) {
/*      */       
/*  694 */       zui_zhong_wjx = (new Rectangle(z, d, y - z, x - d)).createIntersection(jx2).getBounds();
/*      */     } else {
/*  696 */       zui_zhong_wjx = new Rectangle();
/*      */     }  int j;
/*  698 */     for (j = 1; j < Hua_ban.ty_shuzu.size(); j++) {
/*      */       
/*  700 */       if (((Tu_yuan)Hua_ban.ty_shuzu.get(j)).lei_xing == 0) {
/*      */         
/*  702 */         GeneralPath lu_jing3 = new GeneralPath(((Tu_yuan)Hua_ban.ty_shuzu.get(j)).lu_jing);
/*  703 */         lu_jing3.transform(((Tu_yuan)Hua_ban.ty_shuzu.get(j)).Tx);
/*  704 */         Rectangle jx = lu_jing3.getBounds();
/*  705 */         if (j == 1) {
/*      */           
/*  707 */           z = jx.x;
/*  708 */           d = jx.y;
/*  709 */           y = jx.x + jx.width;
/*  710 */           x = jx.y + jx.height;
/*      */         } else {
/*      */           
/*  713 */           if (jx.x < z)
/*  714 */             z = jx.x; 
/*  715 */           if (jx.y < d)
/*  716 */             d = jx.y; 
/*  717 */           if (jx.x + jx.width > y)
/*  718 */             y = jx.x + jx.width; 
/*  719 */           if (jx.y + jx.height > x)
/*  720 */             x = jx.y + jx.height; 
/*      */         } 
/*      */       } 
/*      */     } 
/*  724 */     shi_liang_wjx = new Rectangle(z, d, y - z, x - d);
/*  725 */     for (j = 1; j < Hua_ban.ty_shuzu.size(); j++) {
/*      */       
/*  727 */       if (((Tu_yuan)Hua_ban.ty_shuzu.get(j)).lei_xing == 1) {
/*      */         
/*  729 */         GeneralPath lu_jing3 = new GeneralPath(((Tu_yuan)Hua_ban.ty_shuzu.get(j)).lu_jing);
/*  730 */         lu_jing3.transform(((Tu_yuan)Hua_ban.ty_shuzu.get(j)).Tx);
/*  731 */         Rectangle jx = lu_jing3.getBounds();
/*  732 */         if (j == 1) {
/*      */           
/*  734 */           z = jx.x;
/*  735 */           d = jx.y;
/*  736 */           y = jx.x + jx.width;
/*  737 */           x = jx.y + jx.height;
/*      */         } else {
/*      */           
/*  740 */           if (jx.x < z)
/*  741 */             z = jx.x; 
/*  742 */           if (jx.y < d)
/*  743 */             d = jx.y; 
/*  744 */           if (jx.x + jx.width > y)
/*  745 */             y = jx.x + jx.width; 
/*  746 */           if (jx.y + jx.height > x)
/*  747 */             x = jx.y + jx.height; 
/*      */         } 
/*      */       } 
/*      */     } 
/*  751 */     wei_tu_wjx = new Rectangle(z, d, y - z, x - d);
/*      */   }
/*      */   
/*      */   public static void hui_fu() {
/*  755 */     GeneralPath lu_jing3 = new GeneralPath(((Tu_yuan)Hua_ban.ty_shuzu.get(0)).lu_jing);
/*  756 */     lu_jing3.transform(((Tu_yuan)Hua_ban.ty_shuzu.get(0)).Tx);
/*  757 */     Rectangle rect3 = lu_jing3.getBounds();
/*  758 */     Hua_ban.quan_x2 = rect3.x;
/*  759 */     Hua_ban.quan_y2 = rect3.y;
/*  760 */     Hua_ban.quan_beishu2 = Hua_ban.quan_beishu;
/*  761 */     for (int j = 0; j < 2; j++) {
/*      */       
/*  763 */       GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)Hua_ban.ty_shuzu.get(0)).lu_jing);
/*  764 */       lu_jing2.transform(((Tu_yuan)Hua_ban.ty_shuzu.get(0)).Tx);
/*  765 */       Rectangle rect = lu_jing2.getBounds();
/*  766 */       AffineTransform sf = AffineTransform.getTranslateInstance((0 - rect.x), (0 - rect.y));
/*  767 */       Hua_ban.quan_x = 0; Hua_ban.quan_y = 0; int i;
/*  768 */       for (i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */         
/*  770 */         AffineTransform fb = new AffineTransform(sf);
/*  771 */         fb.concatenate(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).Tx);
/*  772 */         ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).Tx = fb;
/*      */       } 
/*  774 */       sf = AffineTransform.getScaleInstance(1.0D / Hua_ban.quan_beishu, 1.0D / Hua_ban.quan_beishu);
/*  775 */       Hua_ban.quan_beishu = 1.0D;
/*  776 */       for (i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */         
/*  778 */         AffineTransform fb = new AffineTransform(sf);
/*  779 */         fb.concatenate(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).Tx);
/*  780 */         ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).Tx = fb;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void hui_fu_xian_chang() {
/*  786 */     Hua_ban.quan_x = Hua_ban.quan_x2;
/*  787 */     Hua_ban.quan_y = Hua_ban.quan_y2;
/*  788 */     Hua_ban.quan_beishu = Hua_ban.quan_beishu2;
/*  789 */     AffineTransform sf = AffineTransform.getScaleInstance(Hua_ban.quan_beishu, Hua_ban.quan_beishu); int i;
/*  790 */     for (i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */       
/*  792 */       AffineTransform fb = new AffineTransform(sf);
/*  793 */       fb.concatenate(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).Tx);
/*  794 */       ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).Tx = fb;
/*      */     } 
/*      */     
/*  797 */     sf = AffineTransform.getTranslateInstance(Hua_ban.quan_x, Hua_ban.quan_y);
/*  798 */     for (i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */       
/*  800 */       AffineTransform fb = new AffineTransform(sf);
/*  801 */       fb.concatenate(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).Tx);
/*  802 */       ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).Tx = fb;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void shu_chu(BufferedImage bb, String ss) {
/*  807 */     File outputfile = new File(ss);
/*      */     
/*      */     try {
/*  810 */       ImageIO.write(bb, "png", outputfile);
/*      */     }
/*  812 */     catch (IOException ex) {
/*  813 */       Logger.getLogger(Tu_yuan.class.getName()).log(Level.SEVERE, (String)null, ex);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static BufferedImage qu_tu(List<Tu_yuan> sz) {
/*  820 */     BufferedImage fh = new BufferedImage((int)(Quan_ju.diao_ke_ji.kuan_du / Quan_ju.diao_ke_ji.fen_bian_lv) - 2, (int)(Quan_ju.diao_ke_ji.gao_du / Quan_ju.diao_ke_ji.fen_bian_lv) - 2, 2);
/*      */     
/*  822 */     Graphics2D g2D = fh.createGraphics();
/*  823 */     g2D.setBackground(Color.WHITE);
/*  824 */     g2D.clearRect(0, 0, fh.getWidth(), fh.getHeight());
/*  825 */     boolean you = false;
/*  826 */     for (int i = 1; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */ 
/*      */       
/*  829 */       if ((((Tu_yuan)Hua_ban.ty_shuzu.get(i)).lei_xing == 1 && ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).chuli_fs != 3) || ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wen_zi) {
/*      */         
/*  831 */         if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wen_zi) {
/*      */           
/*  833 */           GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).lu_jing);
/*  834 */           lu_jing2.transform(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).Tx);
/*  835 */           g2D.setPaint(Color.BLACK);
/*  836 */           g2D.fill(lu_jing2);
/*      */         } else {
/*      */           
/*  839 */           GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).lu_jing);
/*  840 */           lu_jing2.transform(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).Tx);
/*  841 */           Rectangle jx = lu_jing2.getBounds();
/*      */           
/*  843 */           g2D.drawImage(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).qu_tu(), jx.x, jx.y, (ImageObserver)null);
/*      */         } 
/*  845 */         you = true;
/*      */       } 
/*      */     } 
/*      */     
/*  849 */     if (you) {
/*      */       
/*  851 */       BufferedImage fh2 = new BufferedImage((int)(Quan_ju.diao_ke_ji.kuan_du / Quan_ju.diao_ke_ji.fen_bian_lv), (int)(Quan_ju.diao_ke_ji.gao_du / Quan_ju.diao_ke_ji.fen_bian_lv), 2);
/*  852 */       Graphics2D g2D2 = fh2.createGraphics();
/*  853 */       g2D2.setBackground(Color.WHITE);
/*  854 */       int w = fh2.getWidth(), h = fh2.getHeight();
/*  855 */       g2D2.clearRect(0, 0, w, h);
/*  856 */       g2D2.drawImage(fh, 1, 1, (ImageObserver)null);
/*  857 */       qu_jvxing(Hua_ban.ty_shuzu);
/*  858 */       int xx = 0, yy = 0, ww = 0, hh = 0;
/*  859 */       Rectangle jx = new Rectangle(0, 0, w, h);
/*  860 */       Rectangle jx2 = new Rectangle();
/*  861 */       if (zui_zhong_wjx.x == 0 && zui_zhong_wjx.y == 0 && zui_zhong_wjx.width == 0 && zui_zhong_wjx.height == 0) return null; 
/*  862 */       if (jx.contains(zui_zhong_wjx.x, zui_zhong_wjx.y) || jx.contains(zui_zhong_wjx.x + zui_zhong_wjx.width, zui_zhong_wjx.y + zui_zhong_wjx.height)) {
/*      */         
/*  864 */         jx2 = jx.createIntersection(zui_zhong_wjx).getBounds();
/*      */       } else {
/*      */         
/*  867 */         return null;
/*      */       } 
/*      */ 
/*      */       
/*  871 */       if (jx2.x + jx2.width + 5 >= fh2.getWidth()) { w = jx2.width; } else { w = jx2.width + 5; }
/*  872 */        if (jx2.y + jx2.height + 5 >= fh2.getHeight()) { h = jx2.height; } else { h = jx2.height + 5; }
/*      */       
/*  874 */       return fh2.getSubimage(jx2.x, jx2.y, w, h);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  879 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static BufferedImage qu_tu_sl(List<Tu_yuan> sz) {
/*  885 */     BufferedImage fh = new BufferedImage((int)(Quan_ju.diao_ke_ji.kuan_du / Quan_ju.diao_ke_ji.fen_bian_lv), (int)(Quan_ju.diao_ke_ji.gao_du / Quan_ju.diao_ke_ji.fen_bian_lv), 2);
/*  886 */     Graphics2D g2D = fh.createGraphics();
/*  887 */     g2D.setBackground(Color.WHITE);
/*  888 */     g2D.clearRect(0, 0, fh.getWidth(), fh.getHeight());
/*  889 */     boolean you = false;
/*  890 */     for (int i = 1; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */       
/*  892 */       GeneralPath lu_jing4 = new GeneralPath(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).lu_jing);
/*  893 */       lu_jing4.transform(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).Tx);
/*  894 */       if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).lei_xing == 0 && !((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wen_zi) {
/*      */         
/*  896 */         g2D.setColor(Color.BLACK);
/*  897 */         g2D.draw(lu_jing4);
/*      */         
/*  899 */         you = true;
/*      */       } 
/*  901 */       if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).lei_xing == 1 && ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).chuli_fs == 3) {
/*      */         
/*  903 */         GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).lu_jing);
/*  904 */         lu_jing2.transform(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).Tx);
/*  905 */         Rectangle jx = lu_jing2.getBounds();
/*  906 */         g2D.setColor(Color.BLACK);
/*      */         
/*  908 */         g2D.drawImage(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).qu_tu(), jx.x, jx.y, (ImageObserver)null);
/*  909 */         you = true;
/*      */       } 
/*      */     } 
/*  912 */     if (you) {
/*      */       
/*      */       try {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  934 */         BufferedImage fh2 = new BufferedImage((int)(Quan_ju.diao_ke_ji.kuan_du / Quan_ju.diao_ke_ji.fen_bian_lv) + 4, (int)(Quan_ju.diao_ke_ji.gao_du / Quan_ju.diao_ke_ji.fen_bian_lv) + 4, 2);
/*  935 */         Graphics2D g2D2 = fh2.createGraphics();
/*  936 */         g2D2.setBackground(Color.WHITE);
/*  937 */         int w = fh2.getWidth(), h = fh2.getHeight();
/*  938 */         g2D2.clearRect(0, 0, w, h);
/*  939 */         g2D2.drawImage(fh, 2, 2, (ImageObserver)null);
/*      */         
/*  941 */         qu_jvxing(Hua_ban.ty_shuzu);
/*      */         
/*  943 */         int xx = 0, yy = 0, ww = 0, hh = 0;
/*  944 */         Rectangle jx = new Rectangle(0, 0, w, h);
/*  945 */         Rectangle jx2 = new Rectangle();
/*  946 */         if (zui_zhong_wjx.x == 0 && zui_zhong_wjx.y == 0 && zui_zhong_wjx.width == 0 && zui_zhong_wjx.height == 0) return null; 
/*  947 */         if (jx.contains(zui_zhong_wjx.x, zui_zhong_wjx.y) || jx.contains(zui_zhong_wjx.x + zui_zhong_wjx.width, zui_zhong_wjx.y + zui_zhong_wjx.height)) {
/*      */           
/*  949 */           jx2 = jx.createIntersection(zui_zhong_wjx).getBounds();
/*      */         } else {
/*      */           
/*  952 */           return null;
/*      */         } 
/*      */ 
/*      */         
/*  956 */         if (jx2.x + jx2.width + 5 >= fh2.getWidth()) { w = jx2.width; } else { w = jx2.width + 5; }
/*  957 */          if (jx2.y + jx2.height + 5 >= fh2.getHeight()) { h = jx2.height; } else { h = jx2.height + 5; }
/*      */ 
/*      */         
/*      */         try {
/*  961 */           return fh2.getSubimage(jx2.x, jx2.y, w, h);
/*      */         }
/*  963 */         catch (Exception ex) {
/*  964 */           JOptionPane.showMessageDialog(null, ex);
/*  965 */           return null;
/*      */         }
/*      */       
/*      */       }
/*  969 */       catch (Exception ex) {
/*  970 */         JOptionPane.showMessageDialog(null, ex);
/*  971 */         return null;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  982 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static BufferedImage qu_tu_sl_tc(List<Tu_yuan> sz) {
/*  989 */     BufferedImage fh = new BufferedImage((int)(Quan_ju.diao_ke_ji.kuan_du / Quan_ju.diao_ke_ji.fen_bian_lv), (int)(Quan_ju.diao_ke_ji.gao_du / Quan_ju.diao_ke_ji.fen_bian_lv), 2);
/*  990 */     Graphics2D g2D = fh.createGraphics();
/*  991 */     g2D.setBackground(Color.WHITE);
/*  992 */     g2D.clearRect(0, 0, fh.getWidth(), fh.getHeight());
/*  993 */     boolean you = false;
/*  994 */     for (int i = 1; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */       
/*  996 */       GeneralPath lu_jing4 = new GeneralPath(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).lu_jing);
/*  997 */       lu_jing4.transform(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).Tx);
/*  998 */       if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).lei_xing == 0 && ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).tian_chong) {
/*      */         
/* 1000 */         g2D.setColor(Color.BLACK);
/* 1001 */         lu_jing4.setWindingRule(0);
/* 1002 */         g2D.fill(lu_jing4);
/* 1003 */         you = true;
/*      */       } 
/*      */     } 
/* 1006 */     if (you) {
/*      */       
/* 1008 */       BufferedImage fh2 = new BufferedImage((int)(Quan_ju.diao_ke_ji.kuan_du / Quan_ju.diao_ke_ji.fen_bian_lv) + 4, (int)(Quan_ju.diao_ke_ji.gao_du / Quan_ju.diao_ke_ji.fen_bian_lv) + 4, 2);
/* 1009 */       Graphics2D g2D2 = fh2.createGraphics();
/* 1010 */       g2D2.setBackground(Color.WHITE);
/* 1011 */       int w = fh2.getWidth(), h = fh2.getHeight();
/* 1012 */       g2D2.clearRect(0, 0, w, h);
/* 1013 */       g2D2.drawImage(fh, 2, 2, (ImageObserver)null);
/*      */       
/* 1015 */       qu_jvxing(Hua_ban.ty_shuzu);
/* 1016 */       int xx = 0, yy = 0, ww = 0, hh = 0;
/* 1017 */       Rectangle jx = new Rectangle(0, 0, w, h);
/* 1018 */       Rectangle jx2 = new Rectangle();
/* 1019 */       if (zui_zhong_wjx.x == 0 && zui_zhong_wjx.y == 0 && zui_zhong_wjx.width == 0 && zui_zhong_wjx.height == 0) return null; 
/* 1020 */       if (jx.contains(zui_zhong_wjx.x, zui_zhong_wjx.y) || jx.contains(zui_zhong_wjx.x + zui_zhong_wjx.width, zui_zhong_wjx.y + zui_zhong_wjx.height)) {
/*      */         
/* 1022 */         jx2 = jx.createIntersection(zui_zhong_wjx).getBounds();
/*      */       } else {
/*      */         
/* 1025 */         return null;
/*      */       } 
/*      */ 
/*      */       
/* 1029 */       if (jx2.x + jx2.width + 5 >= fh2.getWidth()) { w = jx2.width; } else { w = jx2.width + 5; }
/* 1030 */        if (jx2.y + jx2.height + 5 >= fh2.getHeight()) { h = jx2.height; } else { h = jx2.height + 5; }
/*      */       
/* 1032 */       return fh2.getSubimage(jx2.x, jx2.y, w, h);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1038 */     return null;
/*      */   }
/*      */   
/*      */   static Dian qu_xiao(Dian d1, Dian d2, Dian d3) {
/* 1042 */     if (d1.x == 242 && d1.y == 85) {
/* 1043 */       d1.x = 242;
/*      */     }
/* 1045 */     int lxiao = (Math.abs(d1.x - d2.x) > Math.abs(d1.y - d2.y)) ? Math.abs(d1.y - d2.y) : Math.abs(d1.x - d2.x);
/* 1046 */     int lxiao2 = (Math.abs(d1.x - d3.x) > Math.abs(d1.y - d3.y)) ? Math.abs(d1.y - d3.y) : Math.abs(d1.x - d3.x);
/* 1047 */     if (lxiao < lxiao2) {
/* 1048 */       return d2;
/*      */     }
/* 1050 */     return d3;
/*      */   }
/* 1052 */   static int tu_kuan = 0, tu_gao = 0; static int da;
/*      */   
/*      */   static Dian qu_jindian(Dian d, BufferedImage bb) {
/* 1055 */     Dian fh = new Dian(50000, 0);
/*      */     
/* 1057 */     List<Dian> zd = new ArrayList<>();
/* 1058 */     for (int c = 1; c < da; c++) {
/*      */ 
/*      */       
/* 1061 */       int ls = d.y - c; int b;
/* 1062 */       for (b = d.x - c; b < d.x + c; b++) {
/*      */         
/* 1064 */         if (b > 0 && b < tu_kuan && ls > 0 && ls < tu_gao)
/*      */         {
/*      */ 
/*      */           
/* 1068 */           if ((new Color(bb.getRGB(b, ls))).getRed() == 0)
/*      */           {
/* 1070 */             zd.add(new Dian(b, ls));
/*      */           }
/*      */         }
/*      */       } 
/* 1074 */       ls = d.x + c;
/* 1075 */       for (b = d.y - c; b < d.y + c; b++) {
/*      */         
/* 1077 */         if (b > 0 && b < tu_gao && ls > 0 && ls < tu_kuan)
/*      */         {
/*      */ 
/*      */           
/* 1081 */           if ((new Color(bb.getRGB(ls, b))).getRed() == 0)
/*      */           {
/* 1083 */             zd.add(new Dian(ls, b));
/*      */           }
/*      */         }
/*      */       } 
/* 1087 */       ls = d.y + c;
/* 1088 */       for (b = d.x + c; b > d.x - c; b--) {
/*      */         
/* 1090 */         if (b > 0 && b < tu_kuan && ls > 0 && ls < tu_gao)
/*      */         {
/* 1092 */           if ((new Color(bb.getRGB(b, ls))).getRed() == 0)
/*      */           {
/* 1094 */             zd.add(new Dian(b, ls));
/*      */           }
/*      */         }
/*      */       } 
/* 1098 */       ls = d.x - c;
/* 1099 */       for (b = d.y + c; b > d.y - c; b--) {
/*      */         
/* 1101 */         if (b > 0 && b < tu_gao && ls > 0 && ls < tu_kuan)
/*      */         {
/* 1103 */           if ((new Color(bb.getRGB(ls, b))).getRed() == 0)
/*      */           {
/* 1105 */             zd.add(new Dian(ls, b));
/*      */           }
/*      */         }
/*      */       } 
/*      */       
/* 1110 */       Dian fh2 = new Dian(0, 0);
/* 1111 */       if (zd.size() > 0) {
/* 1112 */         for (int i = 0; i < zd.size(); i++) {
/* 1113 */           if (i == 0) {
/* 1114 */             fh2 = zd.get(i);
/*      */           } else {
/* 1116 */             fh2 = qu_xiao(d, fh2, zd.get(i));
/*      */           } 
/*      */         } 
/* 1119 */         return fh2;
/*      */       } 
/*      */     } 
/*      */     
/* 1123 */     return fh;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static List<Dian> pai_xu(BufferedImage tu_diaoke2) {
/* 1129 */     BufferedImage bb = tu_diaoke2.getSubimage(0, 0, tu_diaoke2.getWidth(), tu_diaoke2.getHeight());
/* 1130 */     List<Dian> fh = new ArrayList<>();
/* 1131 */     tu_kuan = bb.getWidth();
/* 1132 */     tu_gao = bb.getHeight();
/* 1133 */     da = (bb.getWidth() > bb.getHeight()) ? bb.getWidth() : bb.getHeight();
/* 1134 */     for (int i = 0; i < dian.size(); i++) {
/* 1135 */       if (i == 0) {
/* 1136 */         fh.add(dian.get(i));
/* 1137 */         fh.add(new Dian(30000, 30000));
/*      */         
/* 1139 */         bb.setRGB(((Dian)dian.get(i)).x, ((Dian)dian.get(i)).y, Color.WHITE.getRGB());
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1144 */         Dian d2 = new Dian(0, 0);
/* 1145 */         if (((Dian)fh.get(fh.size() - 1)).x == 30000 || ((Dian)fh.get(fh.size() - 1)).x == 50000) {
/*      */           
/* 1147 */           d2 = fh.get(fh.size() - 2);
/*      */         } else {
/*      */           
/* 1150 */           d2 = fh.get(fh.size() - 1);
/*      */         } 
/* 1152 */         Dian d = qu_jindian(d2, bb);
/* 1153 */         if (d.x != 50000) {
/*      */           
/* 1155 */           if (!xiang_lian(d, d2)) {
/*      */             
/* 1157 */             fh.add(new Dian(50000, 50000));
/* 1158 */             fh.add(d);
/* 1159 */             fh.add(new Dian(30000, 30000));
/*      */           } else {
/*      */             
/* 1162 */             fh.add(d);
/*      */           } 
/*      */           
/* 1165 */           bb.setRGB(d.x, d.y, Color.WHITE.getRGB());
/*      */         } else {
/*      */           break;
/*      */         } 
/*      */       } 
/* 1170 */     }  fh.add(new Dian(50000, 50000));
/* 1171 */     return fh;
/*      */   }
/*      */ 
/*      */   
/*      */   static List<Dian> pai_xu2(BufferedImage tu_diaoke2) {
/* 1176 */     BufferedImage bb = tu_diaoke2.getSubimage(0, 0, tu_diaoke2.getWidth(), tu_diaoke2.getHeight());
/* 1177 */     List<Dian> fh = new ArrayList<>();
/* 1178 */     tu_kuan = bb.getWidth();
/* 1179 */     tu_gao = bb.getHeight();
/* 1180 */     da = (bb.getWidth() > bb.getHeight()) ? bb.getWidth() : bb.getHeight();
/* 1181 */     for (int i = 0; i < dian.size(); i++) {
/* 1182 */       if (i == 0) {
/* 1183 */         fh.add(dian.get(i));
/*      */         
/* 1185 */         bb.setRGB(((Dian)dian.get(i)).x, ((Dian)dian.get(i)).y, Color.WHITE.getRGB());
/*      */       } else {
/* 1187 */         if (((Dian)fh.get(fh.size() - 1)).x == 242 && ((Dian)fh.get(fh.size() - 1)).y == 87) {
/* 1188 */           ((Dian)fh.get(fh.size() - 1)).x = 242;
/*      */         }
/* 1190 */         Dian d = qu_jindian(fh.get(fh.size() - 1), bb);
/* 1191 */         if (d.x != 50000) {
/*      */           
/* 1193 */           fh.add(d);
/*      */           
/* 1195 */           bb.setRGB(d.x, d.y, Color.WHITE.getRGB());
/*      */         } else {
/*      */           break;
/*      */         } 
/*      */       } 
/* 1200 */     }  return fh;
/*      */   }
/*      */   static List<Dian> qu_tian_chong(BufferedImage img, int jian_ge) {
/* 1203 */     List<Dian> fh = new ArrayList<>();
/* 1204 */     int width = img.getWidth();
/* 1205 */     int height = img.getHeight();
/* 1206 */     int[] pixels = new int[width * height];
/*      */     
/* 1208 */     img.getRGB(0, 0, width, height, pixels, 0, width); int i;
/* 1209 */     for (i = 1; i < height; i += jian_ge) {
/* 1210 */       for (int j = 1; j < width; j++) {
/* 1211 */         if ((new Color(pixels[width * i + j])).getRed() == 0)
/*      */         {
/* 1213 */           fh.add(new Dian(j, i));
/*      */         }
/*      */       } 
/*      */     } 
/* 1217 */     return fh;
/*      */   }
/*      */   static List<Dian> qudian(BufferedImage img) {
/* 1220 */     List<Dian> fh = new ArrayList<>();
/* 1221 */     int width = img.getWidth();
/* 1222 */     int height = img.getHeight();
/* 1223 */     int[] pixels = new int[width * height];
/*      */     
/* 1225 */     img.getRGB(0, 0, width, height, pixels, 0, width);
/* 1226 */     for (int i = 1; i < height; i++) {
/* 1227 */       for (int j = 1; j < width; j++) {
/* 1228 */         if ((new Color(pixels[width * i + j])).getRed() == 0)
/*      */         {
/* 1230 */           fh.add(new Dian(j, i));
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1237 */     return fh;
/*      */   }
/*      */   
/*      */   static int qu_fang_xiang(Dian d1, Dian d2) {
/* 1241 */     if (d2.x - d1.x == 1 && d2.y - d1.y == 1)
/*      */     {
/* 1243 */       return 4; } 
/* 1244 */     if (d2.x - d1.x == -1 && d2.y - d1.y == -1)
/*      */     {
/* 1246 */       return 8; } 
/* 1247 */     if (d2.x - d1.x == 1 && d2.y - d1.y == -1)
/*      */     {
/* 1249 */       return 2; } 
/* 1250 */     if (d2.x - d1.x == -1 && d2.y - d1.y == 1)
/*      */     {
/* 1252 */       return 6; } 
/* 1253 */     if (d2.x - d1.x == -1 && d2.y - d1.y == 0)
/*      */     {
/* 1255 */       return 7; } 
/* 1256 */     if (d2.x - d1.x == 1 && d2.y - d1.y == 0)
/*      */     {
/* 1258 */       return 3; } 
/* 1259 */     if (d2.x - d1.x == 0 && d2.y - d1.y == -1)
/*      */     {
/* 1261 */       return 1; } 
/* 1262 */     if (d2.x - d1.x == 0 && d2.y - d1.y == 1)
/*      */     {
/* 1264 */       return 5; } 
/* 1265 */     if (Math.abs(d2.x - d1.x) > 1 && Math.abs(d2.y - d1.y) > 1)
/*      */     {
/* 1267 */       return 9;
/*      */     }
/* 1269 */     return 9;
/*      */   }
/*      */   
/*      */   public static List<Dian> you_hua(List<Dian> dd) {
/* 1273 */     List<Dian> fh = new ArrayList<>();
/* 1274 */     int fx = 0;
/* 1275 */     for (int i = 0; i < dd.size(); i++) {
/*      */       
/* 1277 */       if (i == 0) {
/*      */         
/* 1279 */         fh.add(dd.get(i));
/* 1280 */       } else if (i == 1) {
/*      */         
/* 1282 */         fh.add(dd.get(i));
/* 1283 */         fx = qu_fang_xiang(fh.get(fh.size() - 2), fh.get(fh.size() - 2));
/*      */       }
/*      */       else {
/*      */         
/* 1287 */         int fx2 = qu_fang_xiang(fh.get(fh.size() - 1), dd.get(i));
/* 1288 */         if (fx == fx2 && fx != 9) {
/*      */           
/* 1290 */           fh.remove(fh.size() - 1);
/* 1291 */           fh.add(dd.get(i));
/*      */         } else {
/*      */           
/* 1294 */           fh.add(dd.get(i));
/* 1295 */           fx = fx2;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1299 */     return fh;
/*      */   }
/*      */   
/*      */   static boolean xiang_lian(Dian a, Dian b) {
/* 1303 */     if (Math.abs(a.x - b.x) <= 2 && Math.abs(a.y - b.y) <= 2) {
/* 1304 */       return true;
/*      */     }
/* 1306 */     return false;
/*      */   }
/*      */   
/*      */   public static double getDistance(int x1, int x2, int y1, int y2) {
/* 1310 */     return Math.sqrt(((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)));
/*      */   }
/*      */ 
/*      */   
/*      */   public static double qu_gao(double a, double b, double c) {
/* 1315 */     double p = (a + b + c) / 2.0D;
/* 1316 */     double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));
/* 1317 */     return 2.0D * s / b;
/*      */   }
/*      */   
/*      */   public static double qu_gao_da(List<Dian> dd, int d1, int d2) {
/* 1321 */     double da = 0.0D;
/* 1322 */     for (int i = 0; i < d2 - d1 - 1; i++) {
/*      */       
/* 1324 */       if (((Dian)dd.get(i + d1)).x != 30000 && ((Dian)dd.get(i + d1)).x != 50000) {
/*      */         
/* 1326 */         double a = getDistance(((Dian)dd.get(d1)).x, ((Dian)dd.get(i + d1)).x, ((Dian)dd.get(d1)).y, ((Dian)dd.get(i + d1)).y);
/* 1327 */         double b = getDistance(((Dian)dd.get(d1)).x, ((Dian)dd.get(d2)).x, ((Dian)dd.get(d1)).y, ((Dian)dd.get(d2)).y);
/* 1328 */         double c = getDistance(((Dian)dd.get(i + d1)).x, ((Dian)dd.get(d2)).x, ((Dian)dd.get(i + d1)).y, ((Dian)dd.get(d2)).y);
/* 1329 */         double d = qu_gao(a, b, c);
/* 1330 */         if (d > da)
/*      */         {
/* 1332 */           da = d;
/*      */         }
/*      */       } 
/*      */     } 
/* 1336 */     return da;
/*      */   }
/*      */   
/*      */   public static List<Dian> you_hua2(List<Dian> dd) {
/* 1340 */     List<Dian> fh = new ArrayList<>();
/* 1341 */     int yi = 0;
/* 1342 */     for (int i = 0; i < dd.size(); i++) {
/*      */       
/* 1344 */       if (((Dian)dd.get(i)).x == 30000) {
/*      */         
/* 1346 */         fh.add(dd.get(i - 1));
/* 1347 */         fh.add(dd.get(i));
/* 1348 */         yi = i - 1;
/* 1349 */       } else if (((Dian)dd.get(i)).x == 50000) {
/*      */         
/* 1351 */         fh.add(dd.get(i - 1));
/* 1352 */         fh.add(dd.get(i));
/* 1353 */         yi = i + 1;
/*      */       
/*      */       }
/* 1356 */       else if (i != 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1362 */         int ge_shu = i - yi;
/* 1363 */         if (ge_shu > 1) {
/*      */           
/* 1365 */           double da = qu_gao_da(dd, yi, i);
/* 1366 */           if (da > 0.7D) {
/*      */             
/* 1368 */             fh.add(dd.get(i - 1));
/* 1369 */             yi = i - 1;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1377 */     return fh;
/*      */   }
/*      */ 
/*      */   
/*      */   public static GeneralPath to_ls(BufferedImage bb) {
/* 1382 */     List<Dian> fh = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1391 */     GeneralPath lj = new GeneralPath();
/* 1392 */     bb = Tu_pian.qu_lunkuo(bb, 128);
/* 1393 */     if (bb != null) {
/*      */       
/* 1395 */       dian = qudian(bb);
/* 1396 */       fh = pai_xu(bb);
/* 1397 */       fh = you_hua2(fh);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1429 */     Dian qian = new Dian(0, 0);
/* 1430 */     Dian qi = new Dian(0, 0);
/* 1431 */     boolean kai_jg = false;
/* 1432 */     for (int i = 0; i < fh.size(); i++) {
/*      */       
/* 1434 */       Dian ls = fh.get(i);
/* 1435 */       if (i == 0) {
/*      */         
/* 1437 */         lj.moveTo(ls.x, ls.y);
/* 1438 */         qian = new Dian(ls.x, ls.y);
/*      */       
/*      */       }
/* 1441 */       else if (((Dian)fh.get(i)).x == 30000) {
/*      */         
/* 1443 */         kai_jg = true;
/* 1444 */         qi = new Dian(((Dian)fh.get(i - 1)).x, ((Dian)fh.get(i - 1)).y);
/* 1445 */       } else if (((Dian)fh.get(i)).x == 50000) {
/*      */         
/* 1447 */         kai_jg = false;
/* 1448 */         if (xiang_lian(qi, fh.get(i - 1)))
/*      */         {
/* 1450 */           lj.closePath();
/*      */         
/*      */         }
/*      */       }
/* 1454 */       else if (kai_jg) {
/*      */         
/* 1456 */         lj.lineTo(ls.x, ls.y);
/* 1457 */         qian = new Dian(ls.x, ls.y);
/*      */       } else {
/*      */         
/* 1460 */         lj.moveTo(ls.x, ls.y);
/* 1461 */         qian = new Dian(ls.x, ls.y);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1466 */     if (xiang_lian(qi, fh.get(fh.size() - 1)))
/* 1467 */       lj.closePath(); 
/* 1468 */     return lj;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<Dian> qu_dian(List<Tu_yuan> sz) {
/* 1494 */     List<Dian> fh = null;
/* 1495 */     BufferedImage bb = qu_tu_sl(Hua_ban.ty_shuzu);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1505 */     if (bb != null) {
/*      */       
/* 1507 */       dian = qudian(bb);
/* 1508 */       fh = pai_xu2(bb);
/*      */     } 
/* 1510 */     BufferedImage bb2 = qu_tu_sl_tc(Hua_ban.ty_shuzu);
/* 1511 */     if (bb2 != null)
/*      */     {
/* 1513 */       fh.addAll(0, qu_tian_chong(bb2, 11 - tian_chong_md));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1518 */     return fh;
/*      */   }
/*      */ }


/* Location:              C:\diao\bin\diao.jar!\examples\Tu_yuan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
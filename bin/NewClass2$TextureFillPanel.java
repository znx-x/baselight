/*    */ import examples.Tu_yuan;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Image;
/*    */ import java.awt.TexturePaint;
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import java.awt.image.BufferedImage;
/*    */ import javax.swing.ImageIcon;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class TextureFillPanel
/*    */   extends JPanel
/*    */ {
/*    */   public void paint(Graphics g) {
/* 45 */     Image image2 = (new ImageIcon(getClass().getResource("/tu/3.png"))).getImage();
/* 46 */     int kk = image2.getWidth(null), gg = image2.getHeight(null);
/*    */ 
/*    */ 
/*    */     
/* 50 */     BufferedImage image = new BufferedImage(kk, gg, 1);
/* 51 */     Graphics2D g2 = (Graphics2D)image.getGraphics();
/* 52 */     g2.drawImage(image2, 0, 0, kk, gg, null);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 59 */     Rectangle2D.Float rect = new Rectangle2D.Float(0.0F, 0.0F, kk, gg);
/* 60 */     TexturePaint textPaint = new TexturePaint(image, rect);
/* 61 */     Graphics2D graphics2 = (Graphics2D)g;
/* 62 */     graphics2.setPaint(textPaint);
/* 63 */     Rectangle2D.Float ellipse2 = new Rectangle2D.Float(45.0F, 25.0F, 200.0F, 200.0F);
/* 64 */     Tu_yuan ty = new Tu_yuan();
/*    */     
/* 66 */     ty.lu_jing.moveTo(121.0F, 0.0F);
/* 67 */     ty.lu_jing.lineTo(149.0F, 93.0F);
/* 68 */     ty.lu_jing.lineTo(241.0F, 94.0F);
/* 69 */     ty.lu_jing.lineTo(169.0F, 149.0F);
/* 70 */     ty.lu_jing.lineTo(196.0F, 241.0F);
/* 71 */     ty.lu_jing.lineTo(122.0F, 188.0F);
/* 72 */     ty.lu_jing.lineTo(46.0F, 241.0F);
/* 73 */     ty.lu_jing.lineTo(72.0F, 149.0F);
/* 74 */     ty.lu_jing.lineTo(0.0F, 94.0F);
/* 75 */     ty.lu_jing.lineTo(92.0F, 93.0F);
/* 76 */     ty.lu_jing.closePath();
/* 77 */     graphics2.fill(ty.lu_jing);
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\NewClass2$TextureFillPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
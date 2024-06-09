/*    */ package examples;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Font;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.File;
/*    */ import javax.imageio.ImageIO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NewClass
/*    */ {
/*    */   public static void main(String[] args) throws Exception {
/* 17 */     int width = 150;
/* 18 */     int height = 30;
/* 19 */     String out = new String("今夕是何年？");
/* 20 */     double rate = 1.9D;
/*    */     
/* 22 */     BufferedImage image = new BufferedImage(width, height, 4);
/* 23 */     Graphics g = image.getGraphics();
/* 24 */     g.setColor(new Color(200, 192, 184));
/* 25 */     g.fill3DRect(0, 0, width, height, true);
/* 26 */     g.setColor(Color.BLACK);
/* 27 */     g.setFont(new Font("宋体", 1, 20));
/*    */ 
/*    */     
/* 30 */     int x = (int)((width / 2) - rate * g.getFontMetrics().stringWidth(out) / 2.0D);
/* 31 */     int y = height / 2 + g.getFontMetrics().getHeight() / 3;
/*    */     
/* 33 */     MyDrawString(out, x, y, rate, g);
/*    */     
/* 35 */     ImageIO.write(image, "png", new File("d:/2.png"));
/*    */   }
/*    */   
/*    */   public static void MyDrawString(String str, int x, int y, double rate, Graphics g) {
/* 39 */     String tempStr = new String();
/* 40 */     int orgStringWight = g.getFontMetrics().stringWidth(str);
/* 41 */     int orgStringLength = str.length();
/* 42 */     int tempx = x;
/* 43 */     int tempy = y;
/* 44 */     while (str.length() > 0) {
/*    */       
/* 46 */       tempStr = str.substring(0, 1);
/* 47 */       str = str.substring(1, str.length());
/* 48 */       g.drawString(tempStr, tempx, tempy);
/* 49 */       tempx = (int)(tempx + orgStringWight / orgStringLength * rate);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\examples\NewClass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package examples;
/*    */ 
/*    */ import java.awt.Font;
/*    */ import java.awt.font.FontRenderContext;
/*    */ import java.awt.font.TextLayout;
/*    */ import java.awt.geom.AffineTransform;
/*    */ import java.awt.geom.GeneralPath;
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
/*    */ public class Wen_zi
/*    */ {
/*    */   public static GeneralPath getGeneralPath(String text, Font font, boolean shu) {
/* 21 */     FontRenderContext context = new FontRenderContext(null, false, false);
/* 22 */     GeneralPath shape = new GeneralPath();
/*    */     
/* 24 */     TextLayout layout2 = new TextLayout("信", font, context);
/* 25 */     GeneralPath outline2 = (GeneralPath)layout2.getOutline(null);
/* 26 */     double jian_ge_y = (outline2.getBounds()).height + outline2.getBounds2D().getHeight() * 0.15D;
/* 27 */     double jian_ge_x = (outline2.getBounds()).width + outline2.getBounds2D().getWidth() * 0.15D;
/*    */     
/* 29 */     if (shu) {
/*    */       
/* 31 */       int j = 0, k = 0;
/* 32 */       for (int i1 = 0; i1 < text.length(); i1++) {
/*    */         
/* 34 */         k++;
/* 35 */         String tempStr = text.substring(i1, i1 + 1);
/* 36 */         if (tempStr.equals("\n")) {
/*    */           
/* 38 */           j++;
/* 39 */           k = 0;
/*    */         } 
/* 41 */         TextLayout layout = new TextLayout(tempStr, font, context);
/* 42 */         GeneralPath outline = (GeneralPath)layout.getOutline(null);
/* 43 */         double yy = k * jian_ge_y;
/* 44 */         double xx = j * jian_ge_x;
/* 45 */         outline.transform(AffineTransform.getTranslateInstance(xx, yy));
/* 46 */         shape.append(outline, false);
/*    */       } 
/* 48 */       return shape;
/*    */     } 
/*    */     
/* 51 */     int m = 0, n = 0;
/* 52 */     for (int i = 0; i < text.length(); i++) {
/*    */       
/* 54 */       n++;
/* 55 */       String tempStr = text.substring(i, i + 1);
/* 56 */       if (tempStr.equals("\n")) {
/*    */         
/* 58 */         m++;
/* 59 */         n = 0;
/*    */       } 
/* 61 */       TextLayout layout = new TextLayout(tempStr, font, context);
/* 62 */       GeneralPath outline = (GeneralPath)layout.getOutline(null);
/* 63 */       double yy = n * jian_ge_y;
/* 64 */       double xx = m * jian_ge_x;
/* 65 */       outline.transform(AffineTransform.getTranslateInstance(yy, xx));
/* 66 */       shape.append(outline, false);
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 71 */     return shape;
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\examples\Wen_zi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
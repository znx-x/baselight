/*    */ package examples;
/*    */ 
/*    */ import java.awt.Point;
/*    */ 
/*    */ public class MathUtils
/*    */ {
/*    */   public static double getAngle(Point p1, Point p2) {
/*  8 */     double xDiff = (p2.x - p1.x);
/*  9 */     double yDiff = (p2.y - p1.y);
/* 10 */     return 180.0D - Math.toDegrees(Math.atan2(yDiff, xDiff)) - 90.0D;
/*    */   }
/*    */   
/*    */   public static Point movePoint(Point p, double angle, int distance) {
/* 14 */     p = new Point(p);
/* 15 */     p.x = (int)(p.x + distance * Math.sin(angle));
/* 16 */     p.y = (int)(p.y + distance * Math.cos(angle));
/*    */     
/* 18 */     return p;
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\examples\MathUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
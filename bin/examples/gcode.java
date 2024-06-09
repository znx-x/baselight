/*    */ package examples;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class gcode
/*    */ {
/*    */   class Point
/*    */   {
/* 12 */     int x = 0; int y = 0;
/*    */   }
/* 14 */   static double fen_bian_lv = 0.05D;
/* 15 */   static double x_double = 0.0D;
/* 16 */   static double y_double = 0.0D;
/* 17 */   static int bu = 8;
/* 18 */   static Point[] zhixian = new Point[0];
/*    */   
/*    */   static double[] qu_dian_double(String str) {
/* 21 */     double[] dou = { 0.0D, 0.0D };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 27 */     int xw = str.indexOf("X");
/* 28 */     int kw = str.indexOf(" ");
/* 29 */     double ddd = Double.valueOf(str.substring(xw + 1, kw - xw - 1)).doubleValue();
/* 30 */     dou[0] = ddd;
/* 31 */     xw = str.indexOf("Y", 0);
/* 32 */     kw = str.indexOf(" ", xw);
/* 33 */     if (kw <= 0) kw = str.length(); 
/* 34 */     String ss = str.substring(xw + 1, kw - xw - 1);
/* 35 */     int f = ss.indexOf("F", 0);
/* 36 */     if (f >= 1)
/*    */     {
/* 38 */       ss = str.substring(xw + 1, f - 1);
/*    */     }
/* 40 */     dou[1] = Double.valueOf(ss).doubleValue();
/* 41 */     return dou;
/*    */   }
/*    */   
/*    */   static Point[] jiaru(Point[] sz, Point cy) {
/* 45 */     Point[] fanhui = new Point[sz.length + 1];
/* 46 */     for (int i = 0; i < sz.length; i++)
/*    */     {
/* 48 */       fanhui[i] = sz[i];
/*    */     }
/* 50 */     fanhui[sz.length] = cy;
/* 51 */     return fanhui;
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\examples\gcode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
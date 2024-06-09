/*     */ package examples;
/*     */ import java.awt.Point;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class BasicSpline {
/*     */   private List<Point> points;
/*     */   private List<Cubic> xCubics;
/*     */   private List<Cubic> yCubics;
/*     */   
/*     */   public class Cubic {
/*     */     private double a;
/*     */     private double b;
/*     */     
/*     */     public Cubic(double p0, double d2, double e, double f) {
/*  17 */       this.d = p0;
/*  18 */       this.c = d2;
/*  19 */       this.b = e;
/*  20 */       this.a = f;
/*     */     }
/*     */     private double c; private double d;
/*     */     
/*     */     public double eval(double u) {
/*  25 */       return ((this.a * u + this.b) * u + this.c) * u + this.d;
/*     */     }
/*     */   }
/*     */   
/*     */   public BasicSpline() {
/*  30 */     this.points = new ArrayList<>();
/*  31 */     this.xCubics = new ArrayList<>();
/*  32 */     this.yCubics = new ArrayList<>();
/*     */   }
/*     */   
/*     */   public void addPoint(Point point) {
/*  36 */     this.points.add(point);
/*     */   }
/*     */   
/*     */   private enum PosField {
/*  40 */     X, Y;
/*     */   }
/*     */   
/*     */   private List<Integer> extractValues(List<Point> points, PosField field) {
/*  44 */     List<Integer> ints = new ArrayList<>();
/*  45 */     for (Point p : points) {
/*  46 */       switch (field) {
/*     */         case X:
/*  48 */           ints.add(Integer.valueOf(p.x));
/*     */         
/*     */         case Y:
/*  51 */           ints.add(Integer.valueOf(p.y));
/*     */       } 
/*     */ 
/*     */     
/*     */     } 
/*  56 */     return ints;
/*     */   }
/*     */   
/*     */   public void calcSpline() {
/*  60 */     calcNaturalCubic(extractValues(this.points, PosField.X), this.xCubics);
/*  61 */     calcNaturalCubic(extractValues(this.points, PosField.Y), this.yCubics);
/*     */   }
/*     */   
/*     */   public Point getPoint(float position) {
/*  65 */     position *= this.xCubics.size();
/*  66 */     int cubicNum = (int)Math.min((this.xCubics.size() - 1), position);
/*  67 */     float cubicPos = position - cubicNum;
/*     */     
/*  69 */     return new Point((int)((Cubic)this.xCubics.get(cubicNum)).eval(cubicPos), 
/*  70 */         (int)((Cubic)this.yCubics.get(cubicNum)).eval(cubicPos));
/*     */   }
/*     */   
/*     */   public void calcNaturalCubic(List<Integer> values, Collection<Cubic> cubics) {
/*  74 */     int num = values.size() - 1;
/*     */     
/*  76 */     double[] gamma = new double[num + 1];
/*  77 */     double[] delta = new double[num + 1];
/*  78 */     double[] D = new double[num + 1];
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
/*  93 */     gamma[0] = 0.5D; int i;
/*  94 */     for (i = 1; i < num; i++) {
/*  95 */       gamma[i] = 1.0D / (4.0D - gamma[i - 1]);
/*     */     }
/*  97 */     gamma[num] = 1.0D / (2.0D - gamma[num - 1]);
/*     */     
/*  99 */     int p0 = ((Integer)values.get(0)).intValue();
/* 100 */     int p1 = ((Integer)values.get(1)).intValue();
/*     */     
/* 102 */     delta[0] = (3.0F * (p1 - p0)) * gamma[0];
/* 103 */     for (i = 1; i < num; i++) {
/* 104 */       p0 = ((Integer)values.get(i - 1)).intValue();
/* 105 */       p1 = ((Integer)values.get(i + 1)).intValue();
/* 106 */       delta[i] = ((3.0F * (p1 - p0)) - delta[i - 1]) * gamma[i];
/*     */     } 
/*     */     
/* 109 */     p0 = ((Integer)values.get(num - 1)).intValue();
/* 110 */     p1 = ((Integer)values.get(num)).intValue();
/*     */     
/* 112 */     delta[num] = ((3.0F * (p1 - p0)) - delta[num - 1]) * gamma[num];
/*     */     
/* 114 */     D[num] = delta[num];
/* 115 */     for (i = num - 1; i >= 0; i--) {
/* 116 */       D[i] = delta[i] - gamma[i] * D[i + 1];
/*     */     }
/*     */ 
/*     */     
/* 120 */     cubics.clear();
/*     */     
/* 122 */     for (i = 0; i < num; i++) {
/* 123 */       p0 = ((Integer)values.get(i)).intValue();
/* 124 */       p1 = ((Integer)values.get(i + 1)).intValue();
/*     */       
/* 126 */       cubics.add(new Cubic(p0, D[i], (3 * (p1 - p0)) - 2.0D * D[i] - D[i + 1], (2 * (p0 - p1)) + D[i] + D[i + 1]));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\examples\BasicSpline.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
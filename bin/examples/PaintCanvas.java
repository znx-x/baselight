/*     */ package examples;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PaintCanvas
/*     */   extends DoubleBuffer
/*     */ {
/*     */   private List<Point> points;
/*  22 */   private BasicSpline spline = new BasicSpline();
/*     */   
/*  24 */   private Point grabbedPoint = null;
/*     */   
/*  26 */   private Point grabClick = null; private static final int POINT_RADIUS = 15;
/*     */   
/*     */   private Point getNearestPoint(int x, int y, int radius) {
/*  29 */     for (Point pos : this.points) {
/*  30 */       double dist = Math.sqrt(Math.pow((x - pos.x), 2.0D) + Math.pow((y - pos.y), 2.0D));
/*  31 */       if (dist <= radius) {
/*  32 */         return pos;
/*     */       }
/*     */     } 
/*  35 */     return null;
/*     */   }
/*     */   private static final int BORDER = 3; private static final int LINE_RADIUS = 10;
/*     */   public PaintCanvas() {
/*  39 */     this.points = new ArrayList<>();
/*     */     
/*  41 */     addMouseListener(new MouseAdapter() {
/*     */           public void mousePressed(MouseEvent e) {
/*     */             Point toRemove;
/*  44 */             switch (e.getButton()) {
/*     */               case 1:
/*  46 */                 PaintCanvas.this.grabbedPoint = PaintCanvas.this.getNearestPoint(e.getX(), e.getY(), 18);
/*     */                 
/*  48 */                 if (PaintCanvas.this.grabbedPoint != null) {
/*  49 */                   PaintCanvas.this.grabClick = new Point(e.getX() - PaintCanvas.this.grabbedPoint.x, e.getY() - PaintCanvas.this.grabbedPoint.y);
/*     */                   break;
/*     */                 } 
/*  52 */                 PaintCanvas.this.points.add(new Point(e.getX(), e.getY()));
/*  53 */                 PaintCanvas.this.recalcSpline();
/*  54 */                 PaintCanvas.this.paint(PaintCanvas.this.getGraphics());
/*     */                 break;
/*     */ 
/*     */               
/*     */               case 3:
/*  59 */                 toRemove = PaintCanvas.this.getNearestPoint(e.getX(), e.getY(), 18);
/*  60 */                 if (toRemove != null) {
/*  61 */                   PaintCanvas.this.points.remove(toRemove);
/*  62 */                   PaintCanvas.this.recalcSpline();
/*  63 */                   PaintCanvas.this.paint(PaintCanvas.this.getGraphics());
/*     */                 } 
/*     */                 break;
/*     */             } 
/*     */           }
/*     */ 
/*     */           
/*     */           public void mouseReleased(MouseEvent e) {
/*  71 */             if (e.getButton() == 1 && 
/*  72 */               PaintCanvas.this.grabbedPoint != null) {
/*  73 */               PaintCanvas.this.grabbedPoint = null;
/*     */               
/*  75 */               PaintCanvas.this.recalcSpline();
/*  76 */               PaintCanvas.this.paint(PaintCanvas.this.getGraphics());
/*     */             } 
/*     */           }
/*     */         });
/*     */ 
/*     */     
/*  82 */     addMouseMotionListener(new MouseMotionListener()
/*     */         {
/*     */           public void mouseMoved(MouseEvent e) {}
/*     */ 
/*     */           
/*     */           public void mouseDragged(MouseEvent e) {
/*  88 */             if (PaintCanvas.this.grabbedPoint != null) {
/*  89 */               PaintCanvas.this.grabbedPoint.x = e.getX() - PaintCanvas.this.grabClick.x;
/*  90 */               PaintCanvas.this.grabbedPoint.y = e.getY() - PaintCanvas.this.grabClick.y;
/*     */               
/*  92 */               PaintCanvas.this.recalcSpline();
/*  93 */               PaintCanvas.this.paint(PaintCanvas.this.getGraphics());
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/*  98 */     addKeyListener(new KeyAdapter()
/*     */         {
/*     */           public void keyTyped(KeyEvent e)
/*     */           {
/* 102 */             if (e.getKeyChar() == 'c') {
/* 103 */               PaintCanvas.this.points = new ArrayList();
/* 104 */               PaintCanvas.this.recalcSpline();
/* 105 */               PaintCanvas.this.paint(PaintCanvas.this.getGraphics());
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void recalcSpline() {
/* 113 */     this.spline = new BasicSpline();
/* 114 */     if (this.points.size() > 2) {
/* 115 */       for (Point p : this.points) {
/* 116 */         this.spline.addPoint(p);
/*     */       }
/* 118 */       this.spline.calcSpline();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paintBuffer(Graphics g) {
/* 128 */     Graphics2D g2D = (Graphics2D)g;
/* 129 */     g2D.setColor(Color.LIGHT_GRAY);
/* 130 */     g2D.fillRect(0, 0, getWidth(), getHeight());
/*     */     
/* 132 */     Point pointBefore = null;
/* 133 */     if (this.points.size() > 2) {
/* 134 */       float f; for (f = 0.0F; f <= 1.0F; f = (float)(f + 0.001D)) {
/* 135 */         Point p = this.spline.getPoint(f);
/* 136 */         Point pnt = new Point(p.x, p.y);
/* 137 */         drawConnectionPoint(pnt, g2D);
/*     */         
/* 139 */         if (pointBefore != null) drawConnection(pnt, pointBefore, g2D);
/*     */         
/* 141 */         pointBefore = pnt;
/*     */       } 
/*     */     } 
/*     */     
/* 145 */     for (Point p : this.points) {
/* 146 */       drawPoint(p, g2D);
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawConnection(Point a, Point b, Graphics2D g2D) {
/* 151 */     g2D.setColor(Color.RED);
/* 152 */     g2D.setStroke(new BasicStroke(20.0F));
/* 153 */     g2D.drawLine(a.x, a.y, b.x, b.y);
/*     */   }
/*     */   
/*     */   private void drawConnectionPoint(Point p, Graphics2D g2D) {
/* 157 */     g2D.setColor(Color.RED);
/* 158 */     g2D.fillOval(p.x - 10, p.y - 10, 20, 20);
/*     */   }
/*     */   
/*     */   private void drawPoint(Point p, Graphics2D g2D) {
/* 162 */     g2D.setColor(Color.BLACK);
/* 163 */     g2D.fillOval(p.x - 18, p.y - 18, 36, 36);
/*     */     
/* 165 */     g2D.setColor(Color.GRAY);
/* 166 */     g2D.fillOval(p.x - 15, p.y - 15, 30, 30);
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\examples\PaintCanvas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
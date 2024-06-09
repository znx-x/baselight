/*     */ package examples;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseMotionAdapter;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Point2D;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JPanel;
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
/*     */ public class TestBezier
/*     */ {
/*     */   private static Point2D[] keyPointP;
/*     */   private static Ellipse2D.Double[] keyPointE;
/*     */   private static int keyPointNum;
/*  44 */   private static double t = 0.001D;
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean flagShow = true;
/*     */ 
/*     */ 
/*     */   
/*     */   static class BezierPanel
/*     */     extends JPanel
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */ 
/*     */     
/*  58 */     private int keyPointID = -1;
/*     */     
/*     */     BezierPanel() {
/*  61 */       addMouseListener(new MouseAction());
/*  62 */       addMouseMotionListener(new MouseMotion());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void paintComponent(Graphics gs) {
/*  68 */       super.paintComponent(gs);
/*  69 */       Graphics2D g = (Graphics2D)gs;
/*  70 */       g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*  71 */       g.setColor(Color.BLUE);
/*  72 */       if (TestBezier.flagShow) {
/*  73 */         for (int i = 0; i < TestBezier.keyPointNum; i++) {
/*  74 */           if (i > 0 && i < TestBezier.keyPointNum - 1) {
/*  75 */             g.fill(TestBezier.keyPointE[i]);
/*     */           } else {
/*  77 */             g.draw(TestBezier.keyPointE[i]);
/*     */           } 
/*  79 */           if (TestBezier.keyPointNum > 1 && i < TestBezier.keyPointNum - 1) {
/*  80 */             g.drawLine((int)TestBezier.keyPointP[i].getX(), (int)TestBezier.keyPointP[i].getY(), (int)TestBezier.keyPointP[i + 1].getX(), 
/*  81 */                 (int)TestBezier.keyPointP[i + 1].getY());
/*     */           }
/*  83 */           if (i == 0) {
/*  84 */             g.drawString("A", (int)(TestBezier.keyPointE[i]).x, (int)(TestBezier.keyPointE[i]).y);
/*  85 */           } else if (i == 1) {
/*  86 */             g.drawString("B", (int)(TestBezier.keyPointE[i]).x, (int)(TestBezier.keyPointE[i]).y);
/*  87 */           } else if (i == 2) {
/*  88 */             g.drawString("C", (int)(TestBezier.keyPointE[i]).x, (int)(TestBezier.keyPointE[i]).y);
/*  89 */           } else if (i == 3) {
/*  90 */             g.drawString("D", (int)(TestBezier.keyPointE[i]).x, (int)(TestBezier.keyPointE[i]).y);
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/*  95 */       if (TestBezier.keyPointNum == 3) {
/*     */         
/*  97 */         g.setColor(Color.RED); double k;
/*  98 */         for (k = TestBezier.t; k <= 1.0D + TestBezier.t; k += TestBezier.t) {
/*  99 */           double r = 1.0D - k;
/* 100 */           double x = Math.pow(r, 2.0D) * TestBezier.keyPointP[0].getX() + 2.0D * k * r * TestBezier.keyPointP[1].getX() + Math.pow(k, 2.0D) * TestBezier.keyPointP[2].getX();
/* 101 */           double y = Math.pow(r, 2.0D) * TestBezier.keyPointP[0].getY() + 2.0D * k * r * TestBezier.keyPointP[1].getY() + Math.pow(k, 2.0D) * TestBezier.keyPointP[2].getY();
/*     */           
/* 103 */           g.drawLine((int)x, (int)y, (int)x, (int)y);
/*     */         } 
/*     */       } 
/*     */       
/* 107 */       if (TestBezier.keyPointNum == 4) {
/*     */         
/* 109 */         g.setColor(Color.RED); double k;
/* 110 */         for (k = TestBezier.t; k <= 1.0D + TestBezier.t; k += TestBezier.t) {
/* 111 */           double r = 1.0D - k;
/*     */           
/* 113 */           double x = Math.pow(r, 3.0D) * TestBezier.keyPointP[0].getX() + 3.0D * k * Math.pow(r, 2.0D) * TestBezier.keyPointP[1].getX() + 3.0D * Math.pow(k, 2.0D) * (1.0D - k) * TestBezier.keyPointP[2].getX() + Math.pow(k, 3.0D) * TestBezier.keyPointP[3].getX();
/*     */           
/* 115 */           double y = Math.pow(r, 3.0D) * TestBezier.keyPointP[0].getY() + 3.0D * k * Math.pow(r, 2.0D) * TestBezier.keyPointP[1].getY() + 3.0D * Math.pow(k, 2.0D) * (1.0D - k) * TestBezier.keyPointP[2].getY() + Math.pow(k, 3.0D) * TestBezier.keyPointP[3].getY();
/*     */           
/* 117 */           g.drawLine((int)x, (int)y, (int)x, (int)y);
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     class MouseAction
/*     */       extends MouseAdapter
/*     */     {
/*     */       public void mouseClicked(MouseEvent e) {
/* 126 */         if (e.getButton() == 1) {
/* 127 */           if (TestBezier.keyPointNum < 4) {
/* 128 */             double x = e.getX();
/* 129 */             double y = e.getY();
/* 130 */             TestBezier.keyPointP[TestBezier.keyPointNum] = new Point2D.Double(x, y);
/* 131 */             TestBezier.keyPointE[TestBezier.keyPointNum] = new Ellipse2D.Double(x - 4.0D, y - 4.0D, 8.0D, 8.0D);
/*     */             TestBezier.keyPointNum++;
/* 133 */             TestBezier.BezierPanel.this.repaint();
/*     */           }
/*     */         
/* 136 */         } else if (e.getButton() == 3) {
/* 137 */           TestBezier.flagShow = false;
/* 138 */           TestBezier.BezierPanel.this.repaint();
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public void mousePressed(MouseEvent e) {
/* 145 */         for (int i = 0; i < TestBezier.keyPointNum; i++) {
/* 146 */           if (TestBezier.keyPointE[i].contains(e.getPoint())) {
/* 147 */             TestBezier.BezierPanel.this.keyPointID = i;
/*     */             break;
/*     */           } 
/* 150 */           TestBezier.BezierPanel.this.keyPointID = -1;
/*     */         } 
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     class MouseMotion
/*     */       extends MouseMotionAdapter
/*     */     {
/*     */       public void mouseDragged(MouseEvent e) {
/* 160 */         if (TestBezier.BezierPanel.this.keyPointID != -1) {
/* 161 */           double x = e.getX();
/* 162 */           double y = e.getY();
/* 163 */           TestBezier.keyPointP[TestBezier.BezierPanel.this.keyPointID] = new Point2D.Double(x, y);
/* 164 */           TestBezier.keyPointE[TestBezier.BezierPanel.this.keyPointID] = new Ellipse2D.Double(x - 4.0D, y - 4.0D, 8.0D, 8.0D);
/* 165 */           TestBezier.BezierPanel.this.repaint();
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public TestBezier() {
/* 172 */     JFrame f = new JFrame();
/* 173 */     f.setTitle("Bezier Test");
/* 174 */     f.setDefaultCloseOperation(3);
/* 175 */     f.setSize(800, 600);
/* 176 */     f.setLocationRelativeTo(null);
/*     */     
/* 178 */     keyPointNum = 0;
/* 179 */     keyPointP = new Point2D[4];
/* 180 */     keyPointE = new Ellipse2D.Double[4];
/* 181 */     BezierPanel bezierPanel = new BezierPanel();
/* 182 */     bezierPanel.setPreferredSize(new Dimension(800, 600));
/* 183 */     bezierPanel.setBackground(Color.WHITE);
/*     */     
/* 185 */     f.setContentPane(bezierPanel);
/* 186 */     f.setVisible(true);
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 190 */     new TestBezier();
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\examples\TestBezier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
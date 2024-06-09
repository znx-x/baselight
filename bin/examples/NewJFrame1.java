/*     */ package examples;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.EventQueue;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.LayoutStyle;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.UnsupportedLookAndFeelException;
/*     */ 
/*     */ public class NewJFrame1 extends JFrame {
/*     */   public NewJFrame1() {
/*  18 */     initComponents();
/*     */   }
/*     */ 
/*     */   
/*     */   private Box.Filler filler1;
/*     */   
/*     */   private JButton jButton1;
/*     */   
/*     */   private JButton jButton2;
/*     */ 
/*     */   
/*     */   private void initComponents() {
/*  30 */     this.filler1 = new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(32767, 32767));
/*  31 */     this.jButton1 = new JButton();
/*  32 */     this.jButton2 = new JButton();
/*     */     
/*  34 */     setDefaultCloseOperation(3);
/*     */     
/*  36 */     this.filler1.setBackground(new Color(255, 0, 0));
/*     */     
/*  38 */     this.jButton1.setText("jButton1");
/*     */     
/*  40 */     this.jButton2.setText("jButton1");
/*     */     
/*  42 */     GroupLayout layout = new GroupLayout(getContentPane());
/*  43 */     getContentPane().setLayout(layout);
/*  44 */     layout.setHorizontalGroup(layout
/*  45 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  46 */         .addGroup(layout.createSequentialGroup()
/*  47 */           .addGap(41, 41, 41)
/*  48 */           .addComponent(this.jButton2)
/*  49 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  50 */           .addComponent(this.filler1, -1, 389, 32767)
/*  51 */           .addGap(29, 29, 29)
/*  52 */           .addComponent(this.jButton1)
/*  53 */           .addGap(136, 136, 136)));
/*     */     
/*  55 */     layout.setVerticalGroup(layout
/*  56 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  57 */         .addGroup(layout.createSequentialGroup()
/*  58 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  59 */             .addGroup(layout.createSequentialGroup()
/*  60 */               .addGap(119, 119, 119)
/*  61 */               .addComponent(this.filler1, -2, 231, -2))
/*  62 */             .addGroup(layout.createSequentialGroup()
/*  63 */               .addGap(246, 246, 246)
/*  64 */               .addComponent(this.jButton1))
/*  65 */             .addGroup(layout.createSequentialGroup()
/*  66 */               .addGap(241, 241, 241)
/*  67 */               .addComponent(this.jButton2)))
/*  68 */           .addContainerGap(194, 32767)));
/*     */ 
/*     */     
/*  71 */     pack();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*     */     try {
/*  84 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
/*  85 */         if ("Nimbus".equals(info.getName())) {
/*  86 */           UIManager.setLookAndFeel(info.getClassName());
/*     */           break;
/*     */         } 
/*     */       } 
/*  90 */     } catch (ClassNotFoundException ex) {
/*  91 */       Logger.getLogger(NewJFrame1.class.getName()).log(Level.SEVERE, (String)null, ex);
/*  92 */     } catch (InstantiationException ex) {
/*  93 */       Logger.getLogger(NewJFrame1.class.getName()).log(Level.SEVERE, (String)null, ex);
/*  94 */     } catch (IllegalAccessException ex) {
/*  95 */       Logger.getLogger(NewJFrame1.class.getName()).log(Level.SEVERE, (String)null, ex);
/*  96 */     } catch (UnsupportedLookAndFeelException ex) {
/*  97 */       Logger.getLogger(NewJFrame1.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 102 */     EventQueue.invokeLater(new Runnable() {
/*     */           public void run() {
/* 104 */             (new NewJFrame1()).setVisible(true);
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\examples\NewJFrame1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
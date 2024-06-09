/*     */ package examples;
/*     */ 
/*     */ import java.awt.EventQueue;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JSlider;
/*     */ import javax.swing.LayoutStyle;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.UnsupportedLookAndFeelException;
/*     */ 
/*     */ public class she_zhi
/*     */   extends JFrame {
/*     */   public she_zhi() {
/*  18 */     initComponents();
/*     */   }
/*     */ 
/*     */   
/*     */   private JButton jButton1;
/*     */   
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JSlider jSlider1;
/*     */   private JSlider jSlider2;
/*     */   
/*     */   private void initComponents() {
/*  30 */     this.jLabel1 = new JLabel();
/*  31 */     this.jLabel2 = new JLabel();
/*  32 */     this.jSlider1 = new JSlider();
/*  33 */     this.jSlider2 = new JSlider();
/*  34 */     this.jButton1 = new JButton();
/*     */     
/*  36 */     setDefaultCloseOperation(3);
/*     */     
/*  38 */     this.jLabel1.setText("弱光功率：");
/*     */     
/*  40 */     this.jLabel2.setText("雕刻次数：");
/*  41 */     this.jLabel2.setToolTipText("");
/*     */     
/*  43 */     this.jButton1.setText("确定");
/*     */     
/*  45 */     GroupLayout layout = new GroupLayout(getContentPane());
/*  46 */     getContentPane().setLayout(layout);
/*  47 */     layout.setHorizontalGroup(layout
/*  48 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  49 */         .addGroup(layout.createSequentialGroup()
/*  50 */           .addGap(30, 30, 30)
/*  51 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/*  52 */             .addComponent(this.jButton1, -2, 98, -2)
/*  53 */             .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  54 */               .addGroup(layout.createSequentialGroup()
/*  55 */                 .addComponent(this.jLabel2)
/*  56 */                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  57 */                 .addComponent(this.jSlider2, -2, -1, -2))
/*  58 */               .addGroup(layout.createSequentialGroup()
/*  59 */                 .addComponent(this.jLabel1)
/*  60 */                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  61 */                 .addComponent(this.jSlider1, -2, -1, -2))))
/*  62 */           .addContainerGap(30, 32767)));
/*     */     
/*  64 */     layout.setVerticalGroup(layout
/*  65 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  66 */         .addGroup(layout.createSequentialGroup()
/*  67 */           .addGap(32, 32, 32)
/*  68 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
/*  69 */             .addComponent(this.jSlider1, -2, -1, -2)
/*  70 */             .addComponent(this.jLabel1))
/*  71 */           .addGap(39, 39, 39)
/*  72 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
/*  73 */             .addComponent(this.jSlider2, -2, -1, -2)
/*  74 */             .addComponent(this.jLabel2))
/*  75 */           .addGap(18, 18, 18)
/*  76 */           .addComponent(this.jButton1, -2, 38, -2)
/*  77 */           .addContainerGap(27, 32767)));
/*     */ 
/*     */     
/*  80 */     pack();
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
/*  93 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
/*  94 */         if ("Nimbus".equals(info.getName())) {
/*  95 */           UIManager.setLookAndFeel(info.getClassName());
/*     */           break;
/*     */         } 
/*     */       } 
/*  99 */     } catch (ClassNotFoundException ex) {
/* 100 */       Logger.getLogger(she_zhi.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 101 */     } catch (InstantiationException ex) {
/* 102 */       Logger.getLogger(she_zhi.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 103 */     } catch (IllegalAccessException ex) {
/* 104 */       Logger.getLogger(she_zhi.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 105 */     } catch (UnsupportedLookAndFeelException ex) {
/* 106 */       Logger.getLogger(she_zhi.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 111 */     EventQueue.invokeLater(new Runnable() {
/*     */           public void run() {
/* 113 */             (new she_zhi()).setVisible(true);
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\examples\she_zhi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
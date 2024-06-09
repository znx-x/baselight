/*     */ package examples;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.LayoutStyle;
/*     */ import javax.swing.UIManager;
/*     */ 
/*     */ public class Can_shu extends JDialog {
/*     */   private Canvas canvas1;
/*     */   private JButton jButton1;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   
/*     */   public Can_shu(Frame parent, boolean modal) {
/*  20 */     super(parent, modal);
/*  21 */     initComponents();
/*     */   } private JLabel jLabel4; private JTextField jTextField1; private JTextField jTextField2; private JTextField jTextField3; private JTextField jTextField4;
/*     */   public Can_shu() {
/*  24 */     setUndecorated(true);
/*  25 */     initComponents();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initComponents() {
/*  36 */     this.jLabel1 = new JLabel();
/*  37 */     this.jTextField1 = new JTextField();
/*  38 */     this.jLabel2 = new JLabel();
/*  39 */     this.jTextField2 = new JTextField();
/*  40 */     this.jLabel3 = new JLabel();
/*  41 */     this.jTextField3 = new JTextField();
/*  42 */     this.jLabel4 = new JLabel();
/*  43 */     this.jTextField4 = new JTextField();
/*  44 */     this.jButton1 = new JButton();
/*  45 */     this.canvas1 = new Canvas();
/*     */     
/*  47 */     setDefaultCloseOperation(2);
/*  48 */     setAlwaysOnTop(true);
/*  49 */     setBackground(new Color(255, 255, 255));
/*  50 */     addWindowListener(new WindowAdapter() {
/*     */           public void windowOpened(WindowEvent evt) {
/*  52 */             Can_shu.this.formWindowOpened(evt);
/*     */           }
/*     */         });
/*     */     
/*  56 */     this.jLabel1.setText("X:");
/*     */     
/*  58 */     this.jTextField1.setText("0");
/*     */     
/*  60 */     this.jLabel2.setText("Y:");
/*     */     
/*  62 */     this.jTextField2.setText("0");
/*     */     
/*  64 */     this.jLabel3.setText("H:");
/*     */     
/*  66 */     this.jTextField3.setText("100");
/*     */     
/*  68 */     this.jLabel4.setText("W:");
/*     */     
/*  70 */     this.jTextField4.setText("100");
/*     */     
/*  72 */     this.jButton1.setText("jButton1");
/*     */     
/*  74 */     GroupLayout layout = new GroupLayout(getContentPane());
/*  75 */     getContentPane().setLayout(layout);
/*  76 */     layout.setHorizontalGroup(layout
/*  77 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  78 */         .addGroup(layout.createSequentialGroup()
/*  79 */           .addContainerGap()
/*  80 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  81 */             .addGroup(layout.createSequentialGroup()
/*  82 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  83 */                 .addGroup(layout.createSequentialGroup()
/*  84 */                   .addComponent(this.jTextField1, -2, 42, -2)
/*  85 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  86 */                   .addComponent(this.jTextField2, -2, 42, -2)
/*  87 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  88 */                   .addComponent(this.jTextField3, -2, 42, -2))
/*  89 */                 .addGroup(layout.createSequentialGroup()
/*  90 */                   .addComponent(this.jLabel1, -2, 23, -2)
/*  91 */                   .addGap(29, 29, 29)
/*  92 */                   .addComponent(this.jLabel2)
/*  93 */                   .addGap(32, 32, 32)
/*  94 */                   .addComponent(this.jLabel4)))
/*  95 */               .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/*  96 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  97 */                 .addComponent(this.jLabel3)
/*  98 */                 .addComponent(this.jTextField4, -2, 42, -2)))
/*  99 */             .addGroup(layout.createSequentialGroup()
/* 100 */               .addComponent(this.jButton1)
/* 101 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 102 */               .addComponent(this.canvas1, -2, 419, -2)))
/* 103 */           .addContainerGap(119, 32767)));
/*     */     
/* 105 */     layout.setVerticalGroup(layout
/* 106 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 107 */         .addGroup(layout.createSequentialGroup()
/* 108 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 109 */             .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 110 */               .addComponent(this.jLabel4)
/* 111 */               .addComponent(this.jLabel3))
/* 112 */             .addComponent(this.jLabel2)
/* 113 */             .addComponent(this.jLabel1))
/* 114 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 115 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 116 */             .addComponent(this.jTextField1, -2, -1, -2)
/* 117 */             .addComponent(this.jTextField2, -2, -1, -2)
/* 118 */             .addComponent(this.jTextField3, -2, -1, -2)
/* 119 */             .addComponent(this.jTextField4, -2, -1, -2))
/* 120 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 121 */             .addGroup(layout.createSequentialGroup()
/* 122 */               .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 123 */               .addComponent(this.jButton1, -1, 292, 32767)
/* 124 */               .addContainerGap())
/* 125 */             .addGroup(layout.createSequentialGroup()
/* 126 */               .addGap(24, 24, 24)
/* 127 */               .addComponent(this.canvas1, -2, 236, -2)
/* 128 */               .addContainerGap(-1, 32767)))));
/*     */ 
/*     */     
/* 131 */     pack();
/*     */   }
/*     */ 
/*     */   
/*     */   private void formWindowOpened(WindowEvent evt) {
/* 136 */     setBackground(Color.WHITE);
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
/* 149 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
/* 150 */         if ("Nimbus".equals(info.getName())) {
/* 151 */           UIManager.setLookAndFeel(info.getClassName());
/*     */           break;
/*     */         } 
/*     */       } 
/* 155 */     } catch (ClassNotFoundException ex) {
/* 156 */       Logger.getLogger(Can_shu.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 157 */     } catch (InstantiationException ex) {
/* 158 */       Logger.getLogger(Can_shu.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 159 */     } catch (IllegalAccessException ex) {
/* 160 */       Logger.getLogger(Can_shu.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 161 */     } catch (UnsupportedLookAndFeelException ex) {
/* 162 */       Logger.getLogger(Can_shu.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 167 */     EventQueue.invokeLater(new Runnable() {
/*     */           public void run() {
/* 169 */             Can_shu dialog = new Can_shu(new JFrame(), true);
/* 170 */             dialog.addWindowListener(new WindowAdapter()
/*     */                 {
/*     */                   public void windowClosing(WindowEvent e) {
/* 173 */                     System.exit(0);
/*     */                   }
/*     */                 });
/* 176 */             dialog.setVisible(true);
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\examples\Can_shu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
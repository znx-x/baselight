/*    */ package examples;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.EventQueue;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import javax.swing.BorderFactory;
/*    */ import javax.swing.GroupLayout;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.UIManager;
/*    */ import javax.swing.UnsupportedLookAndFeelException;
/*    */ 
/*    */ public class NewJFrame extends JFrame {
/*    */   private JPanel jPanel1;
/*    */   
/*    */   public NewJFrame() {
/* 18 */     initComponents();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void initComponents() {
/* 30 */     this.jPanel1 = new JPanel();
/*    */     
/* 32 */     setDefaultCloseOperation(3);
/*    */     
/* 34 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
/* 35 */     this.jPanel1.setForeground(new Color(255, 255, 0));
/*    */     
/* 37 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 38 */     this.jPanel1.setLayout(jPanel1Layout);
/* 39 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/* 40 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 41 */         .addGap(0, 177, 32767));
/*    */     
/* 43 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 44 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 45 */         .addGap(0, 266, 32767));
/*    */ 
/*    */     
/* 48 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 49 */     getContentPane().setLayout(layout);
/* 50 */     layout.setHorizontalGroup(layout
/* 51 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 52 */         .addGroup(layout.createSequentialGroup()
/* 53 */           .addContainerGap()
/* 54 */           .addComponent(this.jPanel1, -1, -1, 32767)
/* 55 */           .addContainerGap(211, 32767)));
/*    */     
/* 57 */     layout.setVerticalGroup(layout
/* 58 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 59 */         .addGroup(layout.createSequentialGroup()
/* 60 */           .addContainerGap()
/* 61 */           .addComponent(this.jPanel1, -1, -1, 32767)
/* 62 */           .addContainerGap(22, 32767)));
/*    */ 
/*    */     
/* 65 */     pack();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/*    */     try {
/* 78 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
/* 79 */         if ("Nimbus".equals(info.getName())) {
/* 80 */           UIManager.setLookAndFeel(info.getClassName());
/*    */           break;
/*    */         } 
/*    */       } 
/* 84 */     } catch (ClassNotFoundException ex) {
/* 85 */       Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 86 */     } catch (InstantiationException ex) {
/* 87 */       Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 88 */     } catch (IllegalAccessException ex) {
/* 89 */       Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 90 */     } catch (UnsupportedLookAndFeelException ex) {
/* 91 */       Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 96 */     EventQueue.invokeLater(new Runnable() {
/*    */           public void run() {
/* 98 */             (new NewJFrame()).setVisible(true);
/*    */           }
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\examples\NewJFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package examples;
/*    */ 
/*    */ import java.awt.EventQueue;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import javax.swing.GroupLayout;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.UIManager;
/*    */ import javax.swing.UnsupportedLookAndFeelException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Bang_zhu
/*    */   extends JFrame
/*    */ {
/*    */   public Bang_zhu() {
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
/* 30 */     setDefaultCloseOperation(3);
/*    */     
/* 32 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 33 */     getContentPane().setLayout(layout);
/* 34 */     layout.setHorizontalGroup(layout
/* 35 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 36 */         .addGap(0, 836, 32767));
/*    */     
/* 38 */     layout.setVerticalGroup(layout
/* 39 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 40 */         .addGap(0, 643, 32767));
/*    */ 
/*    */     
/* 43 */     pack();
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
/* 56 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
/* 57 */         if ("Nimbus".equals(info.getName())) {
/* 58 */           UIManager.setLookAndFeel(info.getClassName());
/*    */           break;
/*    */         } 
/*    */       } 
/* 62 */     } catch (ClassNotFoundException ex) {
/* 63 */       Logger.getLogger(Bang_zhu.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 64 */     } catch (InstantiationException ex) {
/* 65 */       Logger.getLogger(Bang_zhu.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 66 */     } catch (IllegalAccessException ex) {
/* 67 */       Logger.getLogger(Bang_zhu.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 68 */     } catch (UnsupportedLookAndFeelException ex) {
/* 69 */       Logger.getLogger(Bang_zhu.class.getName()).log(Level.SEVERE, (String)null, ex);
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 74 */     EventQueue.invokeLater(new Runnable() {
/*    */           public void run() {
/* 76 */             (new Bang_zhu()).setVisible(true);
/*    */           }
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\examples\Bang_zhu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
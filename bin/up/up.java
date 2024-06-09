/*    */ package up;
/*    */ 
/*    */ import java.awt.EventQueue;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import javax.swing.GroupLayout;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.UIManager;
/*    */ import javax.swing.UnsupportedLookAndFeelException;
/*    */ 
/*    */ public class up
/*    */   extends JFrame
/*    */ {
/*    */   private JButton jButton1;
/*    */   
/*    */   public up() {
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
/* 30 */     this.jButton1 = new JButton();
/*    */     
/* 32 */     setDefaultCloseOperation(3);
/*    */     
/* 34 */     this.jButton1.setText("jButton1");
/*    */     
/* 36 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 37 */     getContentPane().setLayout(layout);
/* 38 */     layout.setHorizontalGroup(layout
/* 39 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 40 */         .addGroup(layout.createSequentialGroup()
/* 41 */           .addGap(123, 123, 123)
/* 42 */           .addComponent(this.jButton1)
/* 43 */           .addContainerGap(140, 32767)));
/*    */     
/* 45 */     layout.setVerticalGroup(layout
/* 46 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 47 */         .addGroup(layout.createSequentialGroup()
/* 48 */           .addGap(70, 70, 70)
/* 49 */           .addComponent(this.jButton1)
/* 50 */           .addContainerGap(96, 32767)));
/*    */ 
/*    */     
/* 53 */     pack();
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
/* 66 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
/* 67 */         if ("Nimbus".equals(info.getName())) {
/* 68 */           UIManager.setLookAndFeel(info.getClassName());
/*    */           break;
/*    */         } 
/*    */       } 
/* 72 */     } catch (ClassNotFoundException ex) {
/* 73 */       Logger.getLogger(up.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 74 */     } catch (InstantiationException ex) {
/* 75 */       Logger.getLogger(up.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 76 */     } catch (IllegalAccessException ex) {
/* 77 */       Logger.getLogger(up.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 78 */     } catch (UnsupportedLookAndFeelException ex) {
/* 79 */       Logger.getLogger(up.class.getName()).log(Level.SEVERE, (String)null, ex);
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 84 */     EventQueue.invokeLater(new Runnable() {
/*    */           public void run() {
/* 86 */             (new up()).setVisible(true);
/*    */           }
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar\\u\\up.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
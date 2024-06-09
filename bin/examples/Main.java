/*    */ package examples;
/*    */ 
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JOptionPane;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Main
/*    */ {
/*    */   public static void main(String[] args) {
/* 14 */     JLabel label = new JLabel("레이저 조각 기\\u000d\\u000aUnicode!");
/* 15 */     label.setToolTipText("This is Russian");
/*    */ 
/*    */     
/* 18 */     JOptionPane.showMessageDialog(null, label);
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\examples\Main.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
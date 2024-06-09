/*    */ package examples;
/*    */ 
/*    */ import java.awt.Frame;
/*    */ import java.awt.Menu;
/*    */ import java.awt.MenuBar;
/*    */ import java.awt.MenuItem;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NewClass1
/*    */ {
/* 16 */   Frame fr = new Frame("MenuDemo");
/* 17 */   MenuBar mb = new MenuBar();
/* 18 */   Menu m1 = new Menu("文件");
/* 19 */   MenuItem open = new MenuItem("打开");
/* 20 */   MenuItem close = new MenuItem("关闭");
/* 21 */   MenuItem exit = new MenuItem("退出");
/*    */ 
/*    */   
/*    */   NewClass1() {
/* 25 */     this.fr.setSize(350, 200);
/* 26 */     this.m1.add(this.open);
/* 27 */     this.m1.add(this.close);
/* 28 */     this.m1.addSeparator();
/* 29 */     this.m1.add(this.exit);
/* 30 */     this.mb.add(this.m1);
/*    */     
/* 32 */     this.fr.setMenuBar(this.mb);
/* 33 */     this.fr.setVisible(true);
/*    */   }
/*    */   public static void main(String[] args) {
/* 36 */     new NewClass1();
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\examples\NewClass1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package examples;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Che_xiao
/*    */ {
/* 17 */   static List<List<Tu_yuan>> ty_shuzu_cx = new ArrayList<>();
/* 18 */   static int dang_qian = 0;
/* 19 */   static int bu_shu = 20;
/*    */   
/*    */   public static void tian_jia() {
/* 22 */     if (dang_qian < bu_shu) {
/*    */       
/* 24 */       if (ty_shuzu_cx.size() > dang_qian)
/*    */       {
/* 26 */         for (int i = 0; i < ty_shuzu_cx.size() - dang_qian + 1; i++)
/*    */         {
/* 28 */           ty_shuzu_cx.remove(ty_shuzu_cx.size() - 1);
/*    */         }
/*    */       }
/* 31 */       ty_shuzu_cx.add(Tu_yuan.fu_zhi(Hua_ban.ty_shuzu));
/* 32 */       dang_qian = ty_shuzu_cx.size();
/*    */     } else {
/*    */       
/* 35 */       ty_shuzu_cx.remove(0);
/* 36 */       ty_shuzu_cx.add(Tu_yuan.fu_zhi(Hua_ban.ty_shuzu));
/* 37 */       dang_qian = ty_shuzu_cx.size();
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void che_xiao() {
/* 42 */     if (dang_qian > 1) {
/*    */       
/* 44 */       Hua_ban.ty_shuzu = Tu_yuan.fu_zhi(ty_shuzu_cx.get(dang_qian - 1 - 1));
/* 45 */       dang_qian--;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void chong_zuo() {
/* 50 */     if (dang_qian < bu_shu) {
/*    */       
/* 52 */       Hua_ban.ty_shuzu = Tu_yuan.fu_zhi(ty_shuzu_cx.get(dang_qian + 1 - 1));
/* 53 */       dang_qian++;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\examples\Che_xiao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
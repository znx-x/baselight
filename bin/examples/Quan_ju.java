/*    */ package examples;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Quan_ju
/*    */ {
/* 17 */   public int bo_te_lv = 115200;
/*    */ 
/*    */   
/* 20 */   public static She_bei she_bei = new She_bei();
/* 21 */   public static DKJ diao_ke_ji = new DKJ();
/* 22 */   public static int gong_lv_wt = 100;
/* 23 */   public static int shen_du_wt = 10;
/*    */ 
/*    */   
/* 26 */   public static int diao_ke_ci_shu = 1;
/*    */   public static int jin_du;
/*    */   public static boolean ting_zhi = false;
/*    */   
/*    */   public static int qu_zhuang_tai() {
/* 31 */     return diao_ke_ji.zhuang_tai2 & 0x7;
/*    */   }
/*    */   
/*    */   public static Boolean qu_diao_ke() {
/* 35 */     return Boolean.valueOf(((diao_ke_ji.zhuang_tai1 & 0x10) > 0));
/*    */   }
/*    */   
/*    */   public static Boolean qu_zan_ting() {
/* 39 */     return Boolean.valueOf(((diao_ke_ji.zhuang_tai1 & 0x2) > 0));
/*    */   }
/*    */   
/*    */   public static Boolean qu_yu_lan() {
/* 43 */     return Boolean.valueOf(((diao_ke_ji.zhuang_tai1 & 0x40) > 0));
/*    */   }
/*    */   
/*    */   public static Boolean is_IAP() {
/* 47 */     return Boolean.valueOf((diao_ke_ji.zhuang_tai1 == 1));
/*    */   }
/*    */   
/*    */   public static int qu_jing_du() {
/* 51 */     return diao_ke_ji.zhuang_tai2 >> 3 & 0x3;
/*    */   }
/*    */   
/*    */   public static Boolean qu_gun_zhou() {
/* 55 */     return Boolean.valueOf(((diao_ke_ji.zhuang_tai1 & 0x80) > 0));
/*    */   }
/*    */   
/*    */   public static Boolean qu_1064() {
/* 59 */     return Boolean.valueOf(((diao_ke_ji.zhuang_tai2 & 0x40) > 0));
/*    */   }
/*    */   
/*    */   public static class DKJ {
/*    */     public int ying_jian;
/*    */     public int ruan_jian;
/*    */     public int kuan_du;
/*    */     public int gao_du;
/*    */     public double x;
/*    */     public double y;
/*    */     public int jin_du;
/*    */     public int shen_du;
/*    */     public int gong_lv;
/*    */     public int dai_ji;
/*    */     public int bao_xu_hao;
/*    */     public int zhuang_tai1;
/*    */     public int zhuang_tai2;
/*    */     public int ji_xing;
/*    */     public double fen_bian_lv;
/*    */     
/*    */     public int qu_zhuang_tai() {
/* 80 */       return this.zhuang_tai2 & 0x7;
/*    */     }
/*    */   }
/*    */   
/*    */   public static byte[] jiao_yan(byte[] sz) {
/* 85 */     int jiao = 0;
/* 86 */     for (int i = 0; i < sz.length - 2; i++) {
/*    */       
/* 88 */       if (sz[i] < 0) {
/* 89 */         jiao = jiao + sz[i] + 256;
/*    */       } else {
/* 91 */         jiao += sz[i];
/*    */       } 
/* 93 */     }  sz[sz.length - 2] = (byte)(jiao >> 8);
/* 94 */     sz[sz.length - 1] = (byte)jiao;
/* 95 */     return sz;
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\examples\Quan_ju.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
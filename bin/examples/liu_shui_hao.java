/*    */ package examples;
/*    */ 
/*    */ import java.awt.Font;
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
/*    */ public class liu_shui_hao
/*    */ {
/* 16 */   public int kai_shi = 0; public int jie_shu = 0; public int dang_qian = 0;
/* 17 */   public String qian_zhui = ""; public String hou_zhui = "";
/* 18 */   public Font zi_ti = null;
/*    */   public boolean shu_xiang = false, shi_liang = false;
/*    */   
/*    */   public liu_shui_hao(int ks, int js, int dq, String qz, String hz, Font zt, boolean sx, boolean sl) {
/* 22 */     this.kai_shi = ks;
/* 23 */     this.jie_shu = js;
/* 24 */     this.dang_qian = dq;
/* 25 */     this.qian_zhui = qz;
/* 26 */     this.hou_zhui = hz;
/* 27 */     this.zi_ti = zt;
/* 28 */     this.shu_xiang = sx;
/* 29 */     this.shi_liang = sl;
/*    */   }
/*    */   
/*    */   public Tu_yuan sheng_cheng() {
/* 33 */     Tu_yuan fh = null;
/* 34 */     if (this.shu_xiang) {
/* 35 */       fh = Tu_yuan.chuang_jian_wen_zi_shu(this.qian_zhui + this.dang_qian + this.hou_zhui, Zi_ti2.ziti, 0, this.shi_liang);
/*    */     } else {
/* 37 */       fh = Tu_yuan.chuang_jian_wen_zi(this.qian_zhui + this.dang_qian + this.hou_zhui, Zi_ti2.ziti, this.shi_liang);
/* 38 */     }  this.dang_qian++;
/* 39 */     fh.lsh = this;
/* 40 */     return fh;
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\examples\liu_shui_hao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
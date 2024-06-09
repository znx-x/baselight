/*     */ package examples;
/*     */ 
/*     */ import com.fazecast.jSerialComm.SerialPort;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class She_bei
/*     */ {
/*     */   SerialPort com;
/*  22 */   Boolean kai_shi_jei_shou = Boolean.valueOf(false);
/*  23 */   public Boolean kai_shi_du_qu = Boolean.valueOf(true);
/*  24 */   public Boolean yi_lian_jie = Boolean.valueOf(false);
/*  25 */   int[] shu_ju = new int[28];
/*  26 */   int ge_shu = 0;
/*  27 */   private Object Lock = new Object();
/*  28 */   private Object Lock_fa_song = new Object();
/*  29 */   String osName = "";
/*  30 */   OutputStream out = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void guan_bi() {
/*  61 */     this.kai_shi_jei_shou = Boolean.valueOf(false);
/*  62 */     this.yi_lian_jie = Boolean.valueOf(false);
/*  63 */     if (this.com != null)
/*  64 */       this.com.closePort(); 
/*     */   }
/*     */   
/*     */   void jie_shou() {
/*  68 */     while (this.kai_shi_jei_shou.booleanValue()) {
/*     */       
/*  70 */       if (this.com.isOpen()) {
/*     */         
/*  72 */         int shu = this.com.bytesAvailable();
/*  73 */         if (shu >= this.shu_ju.length)
/*     */         {
/*     */           
/*  76 */           for (int i = 0; i < shu; i++) {
/*     */             
/*  78 */             byte[] shu_m = new byte[1];
/*  79 */             this.com.readBytes(shu_m, 1L);
/*  80 */             int m = shu_m[0];
/*  81 */             if (m < 0)
/*  82 */               m += 256; 
/*  83 */             if (this.ge_shu == 0) {
/*     */               
/*  85 */               if (m == 186)
/*     */               {
/*  87 */                 this.shu_ju[this.ge_shu++] = m;
/*     */               }
/*     */               else
/*     */               {
/*  91 */                 System.out.println("第一个字节不是0xba!" + m + "\r\n");
/*     */               }
/*     */             
/*     */             } else {
/*     */               
/*  96 */               this.shu_ju[this.ge_shu++] = m;
/*  97 */               if (this.ge_shu == this.shu_ju.length) {
/*     */                 
/*  99 */                 jie_xi_shu_ju();
/* 100 */                 this.ge_shu = 0;
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void chu_shi_hua(SerialPort c) {
/* 112 */     this.com = c;
/* 113 */     if (this.com.isOpen()) {
/*     */       
/* 115 */       this.kai_shi_jei_shou = Boolean.valueOf(true);
/* 116 */       Runnable runnable2 = new Runnable() {
/*     */           public void run() {
/* 118 */             She_bei.this.jie_shou();
/*     */           }
/*     */         };
/* 121 */       Thread thread2 = new Thread(runnable2);
/*     */       
/* 123 */       thread2.start();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void ming_ling_gun_zhou() {
/* 130 */     byte[] m = { -85, 2, 0, 6, 0, 0 };
/* 131 */     m = Quan_ju.jiao_yan(m);
/* 132 */     fa_song(m);
/*     */   }
/*     */   
/*     */   public void ming_ling_guan_gun_zhou() {
/* 136 */     byte[] m = { -85, 3, 0, 6, 0, 0 };
/* 137 */     m = Quan_ju.jiao_yan(m);
/* 138 */     fa_song(m);
/*     */   }
/*     */   
/*     */   public void ming_ling_1064() {
/* 142 */     byte[] m = { -85, 22, 0, 6, 0, 0 };
/* 143 */     m = Quan_ju.jiao_yan(m);
/* 144 */     fa_song(m);
/*     */   }
/*     */   
/*     */   public void ming_ling_guan_1064() {
/* 148 */     byte[] m = { -85, 23, 0, 6, 0, 0 };
/* 149 */     m = Quan_ju.jiao_yan(m);
/* 150 */     fa_song(m);
/*     */   }
/*     */   
/*     */   public void ming_ling_zan_ting() {
/* 154 */     byte[] m = { -85, 8, 0, 6, 0, 0 };
/* 155 */     m = Quan_ju.jiao_yan(m);
/* 156 */     fa_song(m);
/*     */   }
/*     */   
/*     */   public void ming_ling_ji_xu() {
/* 160 */     byte[] m = { -85, 9, 0, 6, 0, 0 };
/* 161 */     m = Quan_ju.jiao_yan(m);
/* 162 */     fa_song(m);
/*     */   }
/*     */   
/*     */   public void ming_ling_cha_xun() {
/* 166 */     byte[] m = { -85, 1, 0, 6, 0, 0 };
/* 167 */     m = Quan_ju.jiao_yan(m);
/* 168 */     fa_song(m);
/*     */   }
/*     */ 
/*     */   
/*     */   public void ming_ling_chu_shi_hua() {
/* 173 */     byte[] m = { -85, 13, 0, 6, 0, 0 };
/* 174 */     m = Quan_ju.jiao_yan(m);
/* 175 */     fa_song(m);
/*     */   }
/*     */   
/*     */   public void ming_ling_qing_flash() {
/* 179 */     byte[] m = { -85, 103, 0, 8, 0, 115, 0, 0 };
/* 180 */     m = Quan_ju.jiao_yan(m);
/* 181 */     fa_song(m);
/*     */   }
/*     */   
/*     */   public void ming_ling_ting_zhi() {
/* 185 */     byte[] m = { -85, 7, 0, 6, 0, 0 };
/* 186 */     m = Quan_ju.jiao_yan(m);
/* 187 */     fa_song(m);
/*     */   }
/*     */   
/*     */   public void ming_ling_IAP() {
/* 191 */     byte[] m = { -85, 10, 0, 6, 0, 0 };
/* 192 */     m = Quan_ju.jiao_yan(m);
/* 193 */     fa_song(m);
/*     */   }
/*     */   
/*     */   public void ming_ling_she_zhi(int rg, int jd) {
/* 197 */     byte[] m = { -85, 12, 0, 8, (byte)rg, (byte)jd, 0, 0 };
/* 198 */     m = Quan_ju.jiao_yan(m);
/* 199 */     fa_song(m);
/*     */   }
/*     */   
/*     */   public void ming_ling_fu_wei() {
/* 203 */     byte[] m = { -85, 105, 0, 6, 0, 0 };
/* 204 */     m = Quan_ju.jiao_yan(m);
/* 205 */     fa_song(m);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ming_ling_yu_lan(int zx, int zy, int k, int g) {
/* 281 */     byte[] m = { -85, 4, 0, 14, (byte)(zx >> 8), (byte)zx, (byte)(zy >> 8), (byte)zy, (byte)(k >> 8), (byte)k, (byte)(g >> 8), (byte)g, 0, 0 };
/* 282 */     m = Quan_ju.jiao_yan(m);
/* 283 */     fa_song(m);
/*     */   }
/*     */   
/*     */   public void ming_ling_ting_zhi_yu_lan() {
/* 287 */     byte[] m = { -85, 6, 0, 6, 0, 0 };
/* 288 */     m = Quan_ju.jiao_yan(m);
/* 289 */     fa_song(m);
/*     */   }
/*     */   
/*     */   public void ming_ling_gl_sd(int gl, int sd) {
/* 293 */     byte[] m = { -85, 11, 0, 8, (byte)gl, (byte)sd, 0, 0 };
/* 294 */     m = Quan_ju.jiao_yan(m);
/* 295 */     fa_song(m);
/*     */   }
/*     */   
/*     */   public void ming_ling_qing_flash(int shan_qu, int ban_ben, int z_wt, int s_wt, int kuan_wt, int gao_wt, int weizhi_wt, int gonglv_wt, int shendu_wt, int z_sl, int s_sl, int kuan_sl, int gao_sl, int weizhi_sl, int gonglv_sl, int shendu_sl, int dianshu_sl, int ci_shu) {
/* 299 */     while (Quan_ju.qu_zhuang_tai() != 0) {
/*     */       
/* 301 */       if (Quan_ju.ting_zhi)
/* 302 */         return;  ming_ling_chu_shi_hua();
/*     */       try {
/* 304 */         Thread.sleep(200L);
/* 305 */       } catch (InterruptedException ex) {
/* 306 */         Logger.getLogger(She_bei.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */       } 
/*     */     } 
/* 309 */     byte[] m = { -85, 100, 0, 40, (byte)(shan_qu >> 8), (byte)shan_qu, (byte)ban_ben, (byte)(z_wt >> 8), (byte)z_wt, (byte)(s_wt >> 8), (byte)s_wt, (byte)(kuan_wt >> 8), (byte)kuan_wt, (byte)(gao_wt >> 8), (byte)gao_wt, (byte)(weizhi_wt >> 8), (byte)weizhi_wt, (byte)gonglv_wt, (byte)shendu_wt, (byte)(z_sl >> 8), (byte)z_sl, (byte)(s_sl >> 8), (byte)s_sl, (byte)(kuan_sl >> 8), (byte)kuan_sl, (byte)(gao_sl >> 8), (byte)gao_sl, (byte)(weizhi_sl >> 24), (byte)(weizhi_sl >> 16), (byte)(weizhi_sl >> 8), (byte)weizhi_sl, (byte)gonglv_sl, (byte)shendu_sl, (byte)(dianshu_sl >> 24), (byte)(dianshu_sl >> 16), (byte)(dianshu_sl >> 8), (byte)dianshu_sl, (byte)ci_shu, 0, 0 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 345 */     m = Quan_ju.jiao_yan(m);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 353 */     while (Quan_ju.qu_zhuang_tai() == 0) {
/*     */       
/* 355 */       if (Quan_ju.ting_zhi)
/* 356 */         return;  fa_song(m);
/*     */       try {
/* 358 */         Thread.sleep(100L);
/* 359 */       } catch (InterruptedException ex) {
/* 360 */         Logger.getLogger(She_bei.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */       } 
/*     */     } 
/* 363 */     while (Quan_ju.qu_zhuang_tai() == 1) {
/*     */       
/* 365 */       if (Quan_ju.ting_zhi)
/*     */         return;  try {
/* 367 */         Thread.sleep(100L);
/* 368 */       } catch (InterruptedException ex) {
/* 369 */         Logger.getLogger(She_bei.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void ming_ling_fa_shu_ju(byte[] m) {
/* 375 */     m = Quan_ju.jiao_yan(m);
/* 376 */     fa_song(m);
/*     */   }
/*     */   
/*     */   public void ming_ling_diao_ke() {
/* 380 */     byte[] m = { -85, 16, 0, 6, 0, 0 };
/* 381 */     m = Quan_ju.jiao_yan(m);
/* 382 */     fa_song(m);
/*     */   }
/*     */   
/*     */   void fa_song(byte[] m) {
/* 386 */     synchronized (this.Lock_fa_song) {
/*     */       
/* 388 */       if (this.com.isOpen()) {
/*     */         
/*     */         try {
/* 391 */           if (m.length > 1000) {
/*     */             
/* 393 */             if (this.osName.contains("Win")) {
/* 394 */               this.out.write(m);
/*     */             } else {
/*     */               
/* 397 */               for (int i = 0; i < m.length / 1000 * 1000; i += 1000) {
/*     */                 
/* 399 */                 this.out.write(m, i, 1000);
/* 400 */                 Thread.sleep(80L);
/* 401 */                 this.out.flush();
/*     */               } 
/* 403 */               this.out.write(m, m.length / 1000 * 1000, m.length % 1000);
/* 404 */               this.out.flush();
/*     */             } 
/*     */           } else {
/*     */             
/* 408 */             this.com.writeBytes(m, m.length);
/*     */           }
/*     */         
/* 411 */         } catch (IOException ex) {
/* 412 */           Logger.getLogger(Com.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 413 */         } catch (InterruptedException ex) {
/* 414 */           Logger.getLogger(Com.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void jie_xi_shu_ju() {
/* 423 */     synchronized (this.Lock) {
/*     */       
/* 425 */       if (this.shu_ju[0] == 186) {
/*     */         
/* 427 */         int jiao = this.shu_ju[this.shu_ju.length - 2] << 8 | this.shu_ju[this.shu_ju.length - 1];
/* 428 */         int jiao2 = 0;
/* 429 */         for (int i = 0; i < this.shu_ju.length - 2; i++)
/*     */         {
/* 431 */           jiao2 += this.shu_ju[i];
/*     */         }
/* 433 */         if (jiao == jiao2) {
/*     */           
/* 435 */           this.yi_lian_jie = Boolean.valueOf(true);
/* 436 */           Quan_ju.diao_ke_ji.ying_jian = this.shu_ju[4];
/* 437 */           Quan_ju.diao_ke_ji.ruan_jian = this.shu_ju[5] << 8 | this.shu_ju[6];
/* 438 */           Quan_ju.diao_ke_ji.kuan_du = this.shu_ju[7] << 8 | this.shu_ju[8];
/* 439 */           Quan_ju.diao_ke_ji.gao_du = this.shu_ju[9] << 8 | this.shu_ju[10];
/* 440 */           Quan_ju.diao_ke_ji.x = (this.shu_ju[11] << 8 | this.shu_ju[12]) * Quan_ju.diao_ke_ji.fen_bian_lv;
/* 441 */           Quan_ju.diao_ke_ji.y = (this.shu_ju[13] << 8 | this.shu_ju[14]) * Quan_ju.diao_ke_ji.fen_bian_lv;
/* 442 */           Quan_ju.diao_ke_ji.jin_du = this.shu_ju[15];
/* 443 */           Quan_ju.diao_ke_ji.shen_du = this.shu_ju[16];
/* 444 */           Quan_ju.diao_ke_ji.gong_lv = this.shu_ju[17];
/* 445 */           Quan_ju.diao_ke_ji.dai_ji = this.shu_ju[18];
/* 446 */           Quan_ju.diao_ke_ji.bao_xu_hao = this.shu_ju[19] << 8 | this.shu_ju[20];
/* 447 */           Quan_ju.diao_ke_ji.zhuang_tai1 = this.shu_ju[21];
/* 448 */           Quan_ju.diao_ke_ji.zhuang_tai2 = this.shu_ju[22];
/* 449 */           Quan_ju.diao_ke_ji.ji_xing = this.shu_ju[23];
/* 450 */           Quan_ju.diao_ke_ji.fen_bian_lv = 10.0D / this.shu_ju[24];
/*     */           
/* 452 */           String ss = "";
/* 453 */           for (int j = 0; j < this.shu_ju.length; j++)
/*     */           {
/* 455 */             ss = ss + String.format("%X", new Object[] { Integer.valueOf(this.shu_ju[j]) }) + " ";
/*     */           
/*     */           }
/*     */ 
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 463 */           System.out.println("接收数据出错！\r\n");
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\examples\She_bei.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
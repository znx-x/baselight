/*     */ package examples;
/*     */ 
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
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
/*     */ public class jie_xi_PLT
/*     */ {
/*     */   void add_lj(GeneralPath lj) {
/*  22 */     lj.setWindingRule(0);
/*  23 */     Tu_yuan ty = new Tu_yuan();
/*  24 */     ty = Tu_yuan.chuang_jian(0, null);
/*  25 */     ty.lu_jing = new GeneralPath(lj);
/*  26 */     Hua_ban.ty_shuzu.add(ty);
/*     */     
/*  28 */     ((Tu_yuan)Hua_ban.ty_shuzu.get(Hua_ban.ty_shuzu.size() - 1)).xuan_zhong = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void jie_xi_PLT(File file) {
/*  34 */     BufferedReader reader = null;
/*  35 */     StringBuffer sbf = new StringBuffer();
/*     */     try {
/*  37 */       reader = new BufferedReader(new FileReader(file));
/*     */       String tempStr;
/*  39 */       while ((tempStr = reader.readLine()) != null) {
/*  40 */         sbf.append(tempStr);
/*     */       }
/*  42 */       reader.close();
/*  43 */     } catch (IOException e) {
/*  44 */       e.printStackTrace();
/*     */     } finally {
/*  46 */       if (reader != null) {
/*     */         try {
/*  48 */           reader.close();
/*  49 */         } catch (IOException e1) {
/*  50 */           e1.printStackTrace();
/*     */         } 
/*     */       }
/*     */     } 
/*  54 */     String plt = sbf.toString();
/*  55 */     plt.replaceAll("\r|\n", "");
/*  56 */     String[] strArr = plt.split(";");
/*  57 */     GeneralPath lj = new GeneralPath();
/*  58 */     boolean yi = true;
/*  59 */     boolean jue_dui = true;
/*  60 */     double d_x = 0.0D, d_y = 0.0D;
/*  61 */     double q_x = 0.0D, q_y = 0.0D; int i;
/*  62 */     for (i = 0; i < Hua_ban.ty_shuzu.size(); i++)
/*     */     {
/*  64 */       ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong = false;
/*     */     }
/*  66 */     for (i = 0; i < strArr.length; i++) {
/*     */       
/*  68 */       String ml = strArr[i].toUpperCase();
/*  69 */       String zb = "";
/*  70 */       if (ml.length() > 1) {
/*  71 */         ml = ml.substring(0, 2);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  78 */       if (ml.equals("PU")) {
/*     */         String[] zb2;
/*  80 */         zb = strArr[i].substring(2, strArr[i].length());
/*     */ 
/*     */ 
/*     */         
/*  84 */         int a = zb.indexOf(",");
/*  85 */         if (a != -1) {
/*     */           
/*  87 */           zb2 = zb.split(",");
/*     */         } else {
/*     */           
/*  90 */           zb2 = zb.split(" ");
/*     */         } 
/*  92 */         if (zb2.length == 2) {
/*     */           
/*  94 */           double x = Double.valueOf(zb2[0]).doubleValue() / 40.0D / Quan_ju.diao_ke_ji.fen_bian_lv;
/*  95 */           double y = Double.valueOf(zb2[1]).doubleValue() / 40.0D / Quan_ju.diao_ke_ji.fen_bian_lv;
/*     */           
/*  97 */           y = 0.0D - y;
/*  98 */           if (jue_dui) {
/*     */             
/* 100 */             d_x = x; d_y = y;
/*     */           } else {
/*     */             
/* 103 */             d_x += x; d_y += y;
/*     */           } 
/* 105 */           if (yi) {
/*     */             
/* 107 */             lj.moveTo(d_x, d_y);
/* 108 */             yi = false;
/*     */           } else {
/*     */             
/* 111 */             if (q_x != d_x || q_y == d_y);
/*     */ 
/*     */ 
/*     */             
/* 115 */             add_lj(lj);
/* 116 */             lj = new GeneralPath();
/* 117 */             lj.moveTo(d_x, d_y);
/*     */           } 
/* 119 */           q_x = d_x; q_y = d_y;
/*     */         } 
/* 121 */       } else if (ml.equals("PD")) {
/*     */         String[] zb2;
/* 123 */         zb = strArr[i].substring(2, strArr[i].length());
/*     */         
/* 125 */         int a = zb.indexOf(",");
/* 126 */         if (a != -1) {
/*     */           
/* 128 */           zb2 = zb.split(",");
/*     */         } else {
/*     */           
/* 131 */           zb2 = zb.split(" ");
/*     */         }  int j;
/* 133 */         for (j = 0; j < zb2.length; j += 2)
/*     */         {
/* 135 */           double x = Double.valueOf(zb2[j]).doubleValue() / 40.0D / Quan_ju.diao_ke_ji.fen_bian_lv;
/* 136 */           double y = Double.valueOf(zb2[j + 1]).doubleValue() / 40.0D / Quan_ju.diao_ke_ji.fen_bian_lv;
/* 137 */           y = 0.0D - y;
/* 138 */           if (jue_dui) {
/*     */             
/* 140 */             d_x = x; d_y = y;
/*     */           } else {
/*     */             
/* 143 */             d_x += x; d_y += y;
/*     */           } 
/* 145 */           lj.lineTo(d_x, d_y);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 161 */       else if (ml.equals("PA")) {
/*     */         
/* 163 */         zb = strArr[i].substring(2, strArr[i].length());
/* 164 */         String[] zb2 = zb.split(" ");
/* 165 */         if (zb2.length == 2) {
/*     */           
/* 167 */           double x = Double.valueOf(zb2[0]).doubleValue() / 40.0D / Quan_ju.diao_ke_ji.fen_bian_lv;
/* 168 */           double y = Double.valueOf(zb2[1]).doubleValue() / 40.0D / Quan_ju.diao_ke_ji.fen_bian_lv;
/* 169 */           y = 0.0D - y;
/* 170 */           jue_dui = true;
/* 171 */           if (jue_dui) {
/*     */             
/* 173 */             d_x = x; d_y = y;
/*     */           } else {
/*     */             
/* 176 */             d_x += x; d_y += y;
/*     */           } 
/* 178 */           lj.lineTo(d_x, d_y);
/*     */         } 
/* 180 */       } else if (ml.equals("PR")) {
/*     */         
/* 182 */         zb = strArr[i].substring(2, strArr[i].length());
/* 183 */         String[] zb2 = zb.split(" ");
/* 184 */         if (zb2.length == 2) {
/*     */           
/* 186 */           double x = Double.valueOf(zb2[0]).doubleValue() / 40.0D / Quan_ju.diao_ke_ji.fen_bian_lv;
/* 187 */           double y = Double.valueOf(zb2[1]).doubleValue() / 40.0D / Quan_ju.diao_ke_ji.fen_bian_lv;
/* 188 */           y = 0.0D - y;
/* 189 */           jue_dui = false;
/* 190 */           if (jue_dui) {
/*     */             
/* 192 */             d_x = x; d_y = y;
/*     */           } else {
/*     */             
/* 195 */             d_x += x; d_y += y;
/*     */           } 
/* 197 */           lj.lineTo(d_x, d_y);
/*     */         } 
/*     */       } 
/*     */     } 
/* 201 */     add_lj(lj);
/* 202 */     Tu_yuan.zhong_xin(Hua_ban.ty_shuzu);
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\examples\jie_xi_PLT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
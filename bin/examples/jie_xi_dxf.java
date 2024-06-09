/*     */ package examples;
/*     */ 
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.kabeja.dxf.DXFArc;
/*     */ import org.kabeja.dxf.DXFCircle;
/*     */ import org.kabeja.dxf.DXFDocument;
/*     */ import org.kabeja.dxf.DXFEllipse;
/*     */ import org.kabeja.dxf.DXFEntity;
/*     */ import org.kabeja.dxf.DXFLayer;
/*     */ import org.kabeja.dxf.DXFLine;
/*     */ import org.kabeja.dxf.DXFMLine;
/*     */ import org.kabeja.dxf.DXFPolyline;
/*     */ import org.kabeja.dxf.DXFSpline;
/*     */ import org.kabeja.dxf.DXFVertex;
/*     */ import org.kabeja.dxf.helpers.DXFSplineConverter;
/*     */ import org.kabeja.dxf.helpers.MLineConverter;
/*     */ import org.kabeja.dxf.helpers.Point;
/*     */ import org.kabeja.parser.ParseException;
/*     */ import org.kabeja.parser.Parser;
/*     */ import org.kabeja.parser.ParserBuilder;
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
/*     */ public class jie_xi_dxf
/*     */ {
/*     */   static void polyline(DXFPolyline polyline) {
/*  43 */     Tu_yuan ty = Tu_yuan.chuang_jian(0, null);
/*  44 */     ty.lu_jing = new GeneralPath();
/*  45 */     GeneralPath lj = new GeneralPath();
/*  46 */     Iterator<DXFVertex> vertex = polyline.getVertexIterator();
/*  47 */     boolean yi = false;
/*  48 */     while (vertex.hasNext()) {
/*  49 */       DXFVertex vertex_ = vertex.next();
/*  50 */       if (!yi) {
/*     */         
/*  52 */         lj.moveTo(vertex_.getX(), vertex_.getY());
/*  53 */         yi = true;
/*     */         continue;
/*     */       } 
/*  56 */       lj.lineTo(vertex_.getX(), vertex_.getY());
/*     */     } 
/*     */ 
/*     */     
/*  60 */     if (polyline.isClosed()) {
/*  61 */       lj.closePath();
/*     */     }
/*  63 */     ty.lu_jing = lj;
/*  64 */     ty.xuan_zhong = true;
/*  65 */     Hua_ban.ty_shuzu.add(ty);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void jie_xi(File f) {
/*     */     try {
/*  71 */       Parser dxfParser = ParserBuilder.createDefaultParser();
/*  72 */       dxfParser.parse(new FileInputStream(f.getAbsolutePath()), "UTF-8");
/*     */ 
/*     */       
/*  75 */       DXFDocument doc = dxfParser.getDocument();
/*  76 */       Iterator<DXFLayer> ceng_list = doc.getDXFLayerIterator();
/*  77 */       while (ceng_list.hasNext()) {
/*  78 */         DXFLayer ceng = ceng_list.next();
/*  79 */         Iterator<String> shi_ti_list = ceng.getDXFEntityTypeIterator();
/*  80 */         while (shi_ti_list.hasNext()) {
/*  81 */           String shi_ti2 = shi_ti_list.next();
/*  82 */           System.out.println(shi_ti2);
/*  83 */           List<DXFEntity> st = ceng.getDXFEntities(shi_ti2);
/*  84 */           for (int i = 0; i < st.size(); i++) {
/*     */             
/*  86 */             if (((DXFEntity)st.get(i)).getType() == "CIRCLE") {
/*     */               
/*  88 */               Tu_yuan ty = Tu_yuan.chuang_jian(0, null);
/*  89 */               ty.lu_jing = new GeneralPath();
/*  90 */               DXFCircle cir = (DXFCircle)st.get(i);
/*  91 */               List<Point> dian = new ArrayList<>(); double j;
/*  92 */               for (j = 0.0D; j < 360.0D; j += 0.1D)
/*     */               {
/*  94 */                 dian.add(cir.getPointAt(j));
/*     */               }
/*  96 */               ty.lu_jing.moveTo(((Point)dian.get(0)).getX(), ((Point)dian.get(0)).getY());
/*  97 */               for (Point dian1 : dian) {
/*  98 */                 ty.lu_jing.lineTo(dian1.getX(), dian1.getY());
/*     */               }
/*     */ 
/*     */               
/* 102 */               ty.xuan_zhong = true;
/* 103 */               Hua_ban.ty_shuzu.add(ty);
/* 104 */             } else if (((DXFEntity)st.get(i)).getType() == "LINE") {
/*     */               
/* 106 */               Tu_yuan ty = Tu_yuan.chuang_jian(0, null);
/* 107 */               ty.lu_jing = new GeneralPath();
/* 108 */               DXFLine l = (DXFLine)st.get(i);
/* 109 */               ty.lu_jing.moveTo(l.getStartPoint().getX(), l.getStartPoint().getY());
/* 110 */               ty.lu_jing.lineTo(l.getEndPoint().getX(), l.getEndPoint().getY());
/* 111 */               ty.xuan_zhong = true;
/* 112 */               Hua_ban.ty_shuzu.add(ty);
/* 113 */             } else if (((DXFEntity)st.get(i)).getType() == "LWPOLYLINE" || ((DXFEntity)st.get(i)).getType() == "POLYLINE") {
/*     */               
/* 115 */               Tu_yuan ty = Tu_yuan.chuang_jian(0, null);
/* 116 */               ty.lu_jing = new GeneralPath();
/* 117 */               DXFPolyline poly = (DXFPolyline)st.get(i);
/* 118 */               polyline(poly);
/*     */             
/*     */             }
/* 121 */             else if (((DXFEntity)st.get(i)).getType() == "ELLIPSE") {
/*     */               
/* 123 */               Tu_yuan ty = Tu_yuan.chuang_jian(0, null);
/* 124 */               ty.lu_jing = new GeneralPath();
/* 125 */               DXFEllipse sp = (DXFEllipse)st.get(i);
/* 126 */               List<Point> dian = new ArrayList<>();
/* 127 */               if (sp.getStartParameter() < sp.getEndParameter()) {
/* 128 */                 double j; for (j = sp.getStartParameter(); j < sp.getEndParameter(); j += 0.001D)
/*     */                 {
/* 130 */                   dian.add(sp.getPointAt(j)); } 
/*     */               } else {
/*     */                 double j;
/* 133 */                 for (j = sp.getEndParameter(); j < sp.getStartParameter(); j += 0.1D)
/*     */                 {
/* 135 */                   dian.add(sp.getPointAt(j)); } 
/*     */               } 
/* 137 */               ty.lu_jing.moveTo(((Point)dian.get(0)).getX(), ((Point)dian.get(0)).getY());
/* 138 */               for (Point dian1 : dian) {
/* 139 */                 ty.lu_jing.lineTo(dian1.getX(), dian1.getY());
/*     */               }
/* 141 */               ty.xuan_zhong = true;
/* 142 */               Hua_ban.ty_shuzu.add(ty);
/* 143 */             } else if (((DXFEntity)st.get(i)).getType() == "SPLINE") {
/*     */               
/* 145 */               GeneralPath lj = new GeneralPath();
/* 146 */               DXFSpline sp = (DXFSpline)st.get(i);
/* 147 */               polyline(DXFSplineConverter.toDXFPolyline(sp));
/* 148 */             } else if (((DXFEntity)st.get(i)).getType() == "MLINE") {
/*     */               
/* 150 */               GeneralPath lj = new GeneralPath();
/* 151 */               DXFMLine sp = (DXFMLine)st.get(i);
/* 152 */               DXFPolyline[] po = MLineConverter.toDXFPolyline(sp);
/* 153 */               for (DXFPolyline jk1 : po) {
/* 154 */                 polyline(jk1);
/*     */               }
/* 156 */             } else if (((DXFEntity)st.get(i)).getType() == "ARC") {
/*     */               
/* 158 */               Tu_yuan ty = Tu_yuan.chuang_jian(0, null);
/* 159 */               ty.lu_jing = new GeneralPath();
/* 160 */               DXFArc sp = (DXFArc)st.get(i);
/* 161 */               List<Point> dian = new ArrayList<>();
/* 162 */               if (!sp.isCounterClockwise()) {
/*     */                 
/* 164 */                 if (sp.getStartAngle() < sp.getEndAngle()) {
/*     */                   double j;
/* 166 */                   for (j = sp.getStartAngle(); j < sp.getEndAngle(); j += 0.1D)
/*     */                   {
/* 168 */                     dian.add(sp.getPointAt(j));
/*     */                   }
/*     */                 } else {
/*     */                   double j;
/* 172 */                   for (j = sp.getStartAngle(); j < 360.0D; j += 0.1D)
/*     */                   {
/* 174 */                     dian.add(sp.getPointAt(j));
/*     */                   }
/* 176 */                   for (j = 0.0D; j < sp.getEndAngle(); j += 0.1D)
/*     */                   {
/* 178 */                     dian.add(sp.getPointAt(j));
/*     */                   
/*     */                   }
/*     */                 }
/*     */               
/*     */               }
/* 184 */               else if (sp.getStartAngle() > sp.getEndAngle()) {
/*     */                 double j;
/* 186 */                 for (j = sp.getStartAngle(); j > sp.getEndAngle(); j -= 0.1D)
/*     */                 {
/* 188 */                   dian.add(sp.getPointAt(j));
/*     */                 }
/*     */               } else {
/*     */                 double j;
/* 192 */                 for (j = sp.getStartAngle(); j > 0.0D; j -= 0.1D)
/*     */                 {
/* 194 */                   dian.add(sp.getPointAt(j));
/*     */                 }
/* 196 */                 for (j = 360.0D; j > sp.getEndAngle(); j -= 0.1D)
/*     */                 {
/* 198 */                   dian.add(sp.getPointAt(j));
/*     */                 }
/*     */               } 
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
/* 213 */               ty.lu_jing.moveTo(((Point)dian.get(0)).getX(), ((Point)dian.get(0)).getY());
/* 214 */               for (Point dian1 : dian) {
/* 215 */                 ty.lu_jing.lineTo(dian1.getX(), dian1.getY());
/*     */               }
/* 217 */               ty.xuan_zhong = true;
/* 218 */               Hua_ban.ty_shuzu.add(ty);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 223 */     } catch (FileNotFoundException ex) {
/* 224 */       Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 225 */     } catch (ParseException ex) {
/* 226 */       Logger.getLogger(jie_xi_dxf.class.getName()).log(Level.SEVERE, (String)null, (Throwable)ex);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\examples\jie_xi_dxf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
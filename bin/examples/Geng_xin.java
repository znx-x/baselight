/*     */ package examples;
/*     */ 
/*     */ import java.awt.Desktop;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.util.Properties;
/*     */ import java.util.TooManyListenersException;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.JOptionPane;
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
/*     */ public class Geng_xin
/*     */ {
/*     */   private static String openFile(String filePath) {
/*  31 */     String ee = new String();
/*     */     
/*     */     try {
/*  34 */       URL url = new URL(filePath);
/*  35 */       URLConnection urlconn = url.openConnection();
/*  36 */       urlconn.connect();
/*  37 */       HttpURLConnection httpconn = (HttpURLConnection)urlconn;
/*  38 */       int HttpResult = httpconn.getResponseCode();
/*  39 */       if (HttpResult != 200) {
/*  40 */         System.out.print("无法连接到");
/*     */       } else {
/*  42 */         int filesize = urlconn.getContentLength();
/*  43 */         InputStreamReader isReader = new InputStreamReader(urlconn.getInputStream(), "UTF-8");
/*  44 */         BufferedReader reader = new BufferedReader(isReader);
/*  45 */         StringBuffer buffer = new StringBuffer();
/*     */         
/*  47 */         String line = reader.readLine();
/*  48 */         while (line != null) {
/*  49 */           buffer.append(line);
/*  50 */           buffer.append("\r\n");
/*  51 */           line = reader.readLine();
/*     */         } 
/*  53 */         System.out.print(buffer.toString());
/*  54 */         ee = buffer.toString();
/*     */       }
/*     */     
/*  57 */     } catch (FileNotFoundException e) {
/*  58 */       e.printStackTrace();
/*     */     }
/*  60 */     catch (IOException e) {
/*  61 */       e.printStackTrace();
/*     */     } 
/*  63 */     return ee;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void browse2(String url) throws Exception {
/*  70 */     Desktop desktop = Desktop.getDesktop();
/*  71 */     if (Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
/*  72 */       URI uri = new URI(url);
/*  73 */       desktop.browse(uri);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int compareVersion(String version1, String version2) throws Exception {
/*  83 */     if (version1 == null || version2 == null) {
/*  84 */       throw new Exception("compareVersion error:illegal params.");
/*     */     }
/*  86 */     String[] versionArray1 = version1.split("\\.");
/*  87 */     String[] versionArray2 = version2.split("\\.");
/*  88 */     int idx = 0;
/*  89 */     int minLength = Math.min(versionArray1.length, versionArray2.length);
/*  90 */     int diff = 0;
/*  91 */     while (idx < minLength && (
/*  92 */       diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0 && (
/*  93 */       diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {
/*  94 */       idx++;
/*     */     }
/*     */     
/*  97 */     diff = (diff != 0) ? diff : (versionArray1.length - versionArray2.length);
/*  98 */     return diff;
/*     */   }
/*     */   
/*     */   public static void geng_xin() {
/* 102 */     Runnable runnable2 = new Runnable() {
/*     */         public void run() {
/* 104 */           String gx = Geng_xin.openFile("http://www.jiakuo25.com/geng_xin.txt");
/* 105 */           String[] strArr = gx.split("\r\n");
/* 106 */           if (strArr.length > 1)
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 113 */             if (strArr[0].equals("1")) {
/*     */               
/*     */               try {
/* 116 */                 if (Geng_xin.compareVersion(strArr[1].toUpperCase(), mainJFrame.ban_ben.toUpperCase()) > 0) {
/*     */                   
/* 118 */                   int n = JOptionPane.showConfirmDialog(null, mainJFrame.str_geng_xin, "", 0);
/*     */                   
/* 120 */                   if (n == 0) {
/*     */                     
/*     */                     try {
/* 123 */                       Properties props = System.getProperties();
/* 124 */                       String osName = props.getProperty("os.name");
/* 125 */                       if (osName.contains("Win"))
/* 126 */                       { Geng_xin.browse2("http://www.jiakuo25.com/Laser_java_win.zip"); }
/*     */                       else
/* 128 */                       { Geng_xin.browse2("http://www.jiakuo25.com/Laser_java_mac.zip"); } 
/* 129 */                     } catch (TooManyListenersException ex) {
/* 130 */                       Logger.getLogger(Com.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */                     }
/*     */                   
/*     */                   }
/*     */                 } 
/* 135 */               } catch (Exception ex) {
/* 136 */                 Logger.getLogger(Geng_xin.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */               } 
/*     */             }
/*     */           }
/*     */         }
/*     */       };
/* 142 */     Thread thread2 = new Thread(runnable2);
/*     */     
/* 144 */     thread2.start();
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\examples\Geng_xin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
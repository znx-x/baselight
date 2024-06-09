/*     */ package examples;
/*     */ 
/*     */ import com.fazecast.jSerialComm.SerialPort;
/*     */ import com.fazecast.jSerialComm.SerialPortDataListener;
/*     */ import com.fazecast.jSerialComm.SerialPortEvent;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.JProgressBar;
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
/*     */ public class Com
/*     */ {
/*  28 */   SerialPort com = null;
/*  29 */   OutputStream out = null;
/*     */   boolean fan_hui_ma = false;
/*  31 */   public byte[] fan_hui_shu = new byte[] { 0, 0, 0, 0 };
/*     */   public JProgressBar jdt;
/*  33 */   int fh_shu = 0;
/*  34 */   public int jie_shou_lei_xing = 0;
/*  35 */   public int jie_shou_ji_shu = 0;
/*  36 */   public List<Byte> jie_shou_huan_cun = new ArrayList<>();
/*  37 */   String osName = "";
/*     */   
/*     */   public static void shu_chu(String ss) {
/*  40 */     mainJFrame.ce_shi += ss + "\n";
/*     */   }
/*     */   
/*     */   public Com(SerialPort c) {
/*  44 */     this.jie_shou_huan_cun.add(Byte.valueOf((byte)1));
/*  45 */     synchronized (this) {
/*     */       
/*  47 */       this.com = c;
/*  48 */       this.out = this.com.getOutputStream();
/*  49 */       Properties props = System.getProperties();
/*  50 */       this.osName = props.getProperty("os.name");
/*  51 */       this.com.addDataListener(new SerialPortDataListener() {
/*     */             public int getListeningEvents() {
/*  53 */               return 1;
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             public void serialEvent(SerialPortEvent event) {
/*  64 */               synchronized (this) {
/*     */ 
/*     */                 
/*     */                 try {
/*  68 */                   mainJFrame.chaoshi = 0;
/*  69 */                   SerialPort comPort = event.getSerialPort();
/*  70 */                   byte[] fh = new byte[comPort.bytesAvailable()];
/*  71 */                   int numRead = comPort.readBytes(fh, fh.length);
/*  72 */                   Com.shu_chu("Read " + numRead + " bytes."); int i;
/*  73 */                   for (i = 0; i < numRead; i++)
/*     */                   {
/*  75 */                     Com.shu_chu(String.valueOf(fh[i]));
/*     */                   }
/*     */                   
/*  78 */                   switch (Com.this.jie_shou_lei_xing) {
/*     */                     
/*     */                     case 0:
/*  81 */                       Com.this.fan_hui_ma = true;
/*  82 */                       Com.this.fan_hui_shu = fh;
/*     */                       break;
/*     */ 
/*     */                     
/*     */                     case 2:
/*  87 */                       Com.this.jie_shou_ji_shu += fh.length;
/*  88 */                       for (i = 0; i < fh.length; i++)
/*     */                       {
/*  90 */                         Com.this.jie_shou_huan_cun.add(Byte.valueOf(fh[i]));
/*     */                       }
/*  92 */                       if (Com.this.jie_shou_ji_shu >= 3) {
/*     */                         
/*  94 */                         Com.this.fan_hui_shu = new byte[3];
/*  95 */                         for (i = 0; i < 3; i++)
/*     */                         {
/*  97 */                           Com.this.fan_hui_shu[i] = ((Byte)Com.this.jie_shou_huan_cun.get(i)).byteValue();
/*     */                         }
/*  99 */                         Com.this.fan_hui_ma = true;
/*     */                       } 
/*     */                       break;
/*     */                     case 3:
/* 103 */                       for (i = 0; i < fh.length; i++)
/*     */                       {
/* 105 */                         Com.this.jie_shou_huan_cun.add(Byte.valueOf(fh[i]));
/*     */                       }
/* 107 */                       if (Com.this.jie_shou_huan_cun.size() > 3)
/*     */                       {
/* 109 */                         if (((Byte)Com.this.jie_shou_huan_cun.get(Com.this.jie_shou_huan_cun.size() - 4)).byteValue() == -1 && ((Byte)Com.this.jie_shou_huan_cun.get(Com.this.jie_shou_huan_cun.size() - 3)).byteValue() == -1 && ((Byte)Com.this.jie_shou_huan_cun.get(Com.this.jie_shou_huan_cun.size() - 2)).byteValue() == 0) {
/*     */                           
/* 111 */                           Com.this.jdt.setValue(((Byte)Com.this.jie_shou_huan_cun.get(Com.this.jie_shou_huan_cun.size() - 1)).byteValue());
/* 112 */                           Com.this.jdt.setVisible(true);
/*     */                           
/* 114 */                           mainJFrame.kai_shi2 = true;
/* 115 */                           Com.this.jie_shou_huan_cun.clear();
/*     */                         } 
/*     */                       }
/* 118 */                       System.out.println(fh.length);
/*     */                       break;
/*     */                   } 
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
/* 131 */                 } catch (Exception e1) {
/*     */ 
/*     */                   
/* 134 */                   Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, e1);
/*     */                 } 
/*     */               } 
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */   
/*     */   public void guan_bi() {
/* 143 */     if (!this.com.equals(null))
/*     */     {
/* 145 */       this.com.closePort();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean du_banben() {
/* 150 */     final Object suo_ = new Object();
/* 151 */     this.fh_shu = 3;
/* 152 */     this.jie_shou_ji_shu = 0;
/* 153 */     this.jie_shou_lei_xing = 2;
/* 154 */     this.jie_shou_huan_cun.clear();
/* 155 */     synchronized (this) {
/*     */     
/* 157 */     }  this.fan_hui_ma = false;
/* 158 */     this.com.writeBytes(new byte[] { -1, 0, 4, 0 }, 4L);
/* 159 */     Runnable runnable2 = new Runnable() {
/*     */         public void run() {
/* 161 */           synchronized (suo_) {
/*     */             
/* 163 */             for (int i = 200; i > 0; i--) {
/*     */               
/* 165 */               if (Com.this.fan_hui_ma) {
/*     */                 
/* 167 */                 if (Com.this.fan_hui_shu.length == 3) {
/*     */                   break;
/*     */                 }
/*     */ 
/*     */                 
/* 172 */                 Com.this.fan_hui_ma = false;
/*     */                 
/*     */                 break;
/*     */               } 
/*     */               try {
/* 177 */                 Thread.sleep(10L);
/* 178 */               } catch (InterruptedException ex) {
/* 179 */                 Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*     */       };
/* 185 */     Thread thread2 = new Thread(runnable2);
/*     */     
/* 187 */     thread2.start();
/*     */     try {
/* 189 */       Thread.sleep(100L);
/* 190 */     } catch (InterruptedException ex) {
/* 191 */       Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/* 193 */     synchronized (suo_) {
/*     */       
/* 195 */       return this.fan_hui_ma;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean fa_song2(byte[] shu, final int chao_shi) {
/* 202 */     final Object suo_ = new Object();
/* 203 */     this.fh_shu = 1;
/* 204 */     this.jie_shou_ji_shu = 0;
/* 205 */     this.jie_shou_lei_xing = 0;
/* 206 */     this.jie_shou_huan_cun.clear();
/* 207 */     synchronized (this) {
/*     */     
/* 209 */     }  this.fan_hui_ma = false;
/* 210 */     int fb = 0;
/*     */     
/* 212 */     if (shu.length == 1904) {
/*     */       
/* 214 */       byte[] mb = new byte[1904];
/* 215 */       int fh = 0, zong = 0;
/*     */       
/*     */       do {
/* 218 */         System.arraycopy(shu, zong, mb, 0, shu.length - zong);
/*     */         try {
/* 220 */           Thread.sleep(10L);
/* 221 */         } catch (InterruptedException ex) {
/* 222 */           Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */         } 
/* 224 */         if (!this.com.isOpen()) this.com.openPort(); 
/* 225 */         fh = this.com.writeBytes(mb, (shu.length - zong));
/*     */         
/* 227 */         zong += fh;
/* 228 */         shu_chu("fh:" + String.valueOf(fh) + "zong:" + String.valueOf(zong));
/* 229 */       } while (zong < shu.length);
/*     */     }
/*     */     else {
/*     */       
/* 233 */       shu_chu(String.valueOf(this.com.writeBytes(shu, shu.length)));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 238 */     Runnable runnable2 = new Runnable() {
/*     */         public void run() {
/* 240 */           synchronized (suo_) {
/*     */             
/* 242 */             for (int i = chao_shi * 100; i > 0; i--) {
/*     */               
/* 244 */               if (Com.this.fan_hui_ma) {
/*     */                 
/* 246 */                 if (Com.this.fan_hui_shu[0] == 9) {
/*     */                   break;
/*     */                 }
/* 249 */                 if (Com.this.fan_hui_shu[0] == 8) {
/*     */                   
/* 251 */                   Com.this.fan_hui_ma = false;
/*     */                   break;
/*     */                 } 
/*     */               } 
/*     */               try {
/* 256 */                 Thread.sleep(10L);
/* 257 */               } catch (InterruptedException ex) {
/* 258 */                 Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*     */       };
/* 264 */     Thread thread2 = new Thread(runnable2);
/*     */     
/* 266 */     thread2.start();
/*     */     try {
/* 268 */       Thread.sleep(100L);
/* 269 */     } catch (InterruptedException ex) {
/* 270 */       Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/* 272 */     synchronized (suo_) {
/*     */       
/* 274 */       return this.fan_hui_ma;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean fa_song(byte[] shu, final int chao_shi) {
/* 280 */     final Object suo_ = new Object();
/* 281 */     this.fh_shu = 1;
/* 282 */     this.jie_shou_ji_shu = 0;
/* 283 */     this.jie_shou_lei_xing = 0;
/* 284 */     this.jie_shou_huan_cun.clear();
/* 285 */     synchronized (this) {
/*     */     
/* 287 */     }  this.fan_hui_ma = false;
/* 288 */     int fb = 0;
/*     */     
/* 290 */     if (shu.length == 1904) {
/*     */       
/*     */       try {
/* 293 */         if (this.osName.contains("Win")) {
/* 294 */           this.out.write(shu);
/*     */         } else {
/*     */           int i;
/* 297 */           for (i = 0; i < 1000; i += 1000) {
/*     */             
/* 299 */             this.out.write(shu, i, 1000);
/* 300 */             Thread.sleep(100L);
/*     */           } 
/* 302 */           this.out.write(shu, 1000, 904);
/*     */         } 
/* 304 */       } catch (IOException ex) {
/* 305 */         Logger.getLogger(Com.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 306 */       } catch (InterruptedException ex) {
/* 307 */         Logger.getLogger(Com.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */       } 
/*     */     } else {
/*     */       
/* 311 */       this.com.writeBytes(shu, shu.length);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 317 */     Runnable runnable2 = new Runnable() {
/*     */         public void run() {
/* 319 */           synchronized (suo_) {
/*     */             
/* 321 */             for (int i = chao_shi * 100; i > 0; i--) {
/*     */               
/* 323 */               if (Com.this.fan_hui_ma) {
/*     */                 
/* 325 */                 if (Com.this.fan_hui_shu[0] == 9) {
/*     */                   break;
/*     */                 }
/* 328 */                 if (Com.this.fan_hui_shu[0] == 8) {
/*     */                   
/* 330 */                   Com.this.fan_hui_ma = false;
/*     */                   break;
/*     */                 } 
/*     */               } 
/*     */               try {
/* 335 */                 Thread.sleep(10L);
/* 336 */               } catch (InterruptedException ex) {
/* 337 */                 Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*     */       };
/* 343 */     Thread thread2 = new Thread(runnable2);
/*     */     
/* 345 */     thread2.start();
/*     */     try {
/* 347 */       Thread.sleep(100L);
/* 348 */     } catch (InterruptedException ex) {
/* 349 */       Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/* 351 */     synchronized (suo_) {
/*     */       
/* 353 */       return this.fan_hui_ma;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean fa_song_mo_ni(byte[] shu, final int chao_shi) {
/* 359 */     final Object suo_ = new Object();
/* 360 */     this.fh_shu = 1;
/* 361 */     this.jie_shou_ji_shu = 0;
/* 362 */     this.jie_shou_lei_xing = 0;
/* 363 */     this.jie_shou_huan_cun.clear();
/* 364 */     synchronized (this) {
/*     */     
/* 366 */     }  this.fan_hui_ma = false;
/* 367 */     int fb = 0;
/*     */     
/* 369 */     byte[] mb = new byte[shu.length];
/* 370 */     int fh = 0, zong = 0;
/*     */     try {
/* 372 */       if (this.osName.contains("Win")) {
/* 373 */         this.out.write(shu);
/*     */       } else {
/*     */         
/* 376 */         int j = shu.length / 1000;
/* 377 */         if (j > 0) {
/*     */           
/* 379 */           for (int i = 0; i < j; i++) {
/*     */             
/* 381 */             this.out.write(shu, i * 1000, 1000);
/* 382 */             Thread.sleep(100L);
/*     */           } 
/* 384 */           this.out.write(shu, j * 1000, shu.length - j * 1000);
/*     */         } else {
/*     */           
/* 387 */           this.out.write(shu, 0, shu.length);
/*     */         } 
/*     */       } 
/* 390 */     } catch (IOException ex) {
/* 391 */       Logger.getLogger(Com.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 392 */     } catch (InterruptedException ex) {
/* 393 */       Logger.getLogger(Com.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
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
/* 414 */     Runnable runnable2 = new Runnable() {
/*     */         public void run() {
/* 416 */           synchronized (suo_) {
/*     */             
/* 418 */             for (int i = chao_shi * 100; i > 0; i--) {
/*     */               
/* 420 */               if (Com.this.fan_hui_ma) {
/*     */                 
/* 422 */                 if (Com.this.fan_hui_shu[0] == 9) {
/*     */                   break;
/*     */                 }
/* 425 */                 if (Com.this.fan_hui_shu[0] == 8) {
/*     */                   
/* 427 */                   Com.this.fan_hui_ma = false;
/*     */                   break;
/*     */                 } 
/*     */               } 
/*     */               try {
/* 432 */                 Thread.sleep(10L);
/* 433 */               } catch (InterruptedException ex) {
/* 434 */                 Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*     */       };
/* 440 */     Thread thread2 = new Thread(runnable2);
/*     */     
/* 442 */     thread2.start();
/*     */     try {
/* 444 */       Thread.sleep(100L);
/* 445 */     } catch (InterruptedException ex) {
/* 446 */       Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/* 448 */     synchronized (suo_) {
/*     */       
/* 450 */       return this.fan_hui_ma;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean fa_song_mo_ni2(byte[] shu, final int chao_shi) {
/* 456 */     final Object suo_ = new Object();
/* 457 */     this.fh_shu = 1;
/* 458 */     this.jie_shou_ji_shu = 0;
/* 459 */     this.jie_shou_lei_xing = 0;
/* 460 */     this.jie_shou_huan_cun.clear();
/* 461 */     synchronized (this) {
/*     */     
/* 463 */     }  this.fan_hui_ma = false;
/* 464 */     int fb = 0;
/*     */     
/* 466 */     byte[] mb = new byte[shu.length];
/* 467 */     int fh = 0, zong = 0;
/*     */     
/*     */     do {
/* 470 */       System.arraycopy(shu, zong, mb, 0, shu.length - zong);
/*     */       try {
/* 472 */         Thread.sleep(10L);
/* 473 */       } catch (InterruptedException ex) {
/* 474 */         Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */       } 
/* 476 */       if (!this.com.isOpen()) this.com.openPort(); 
/* 477 */       fh = this.com.writeBytes(mb, (shu.length - zong));
/*     */       
/* 479 */       zong += fh;
/* 480 */       shu_chu("fh:" + String.valueOf(fh) + "zong:" + String.valueOf(zong));
/* 481 */     } while (zong < shu.length);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 486 */     Runnable runnable2 = new Runnable() {
/*     */         public void run() {
/* 488 */           synchronized (suo_) {
/*     */             
/* 490 */             for (int i = chao_shi * 100; i > 0; i--) {
/*     */               
/* 492 */               if (Com.this.fan_hui_ma) {
/*     */                 
/* 494 */                 if (Com.this.fan_hui_shu[0] == 9) {
/*     */                   break;
/*     */                 }
/* 497 */                 if (Com.this.fan_hui_shu[0] == 8) {
/*     */                   
/* 499 */                   Com.this.fan_hui_ma = false;
/*     */                   break;
/*     */                 } 
/*     */               } 
/*     */               try {
/* 504 */                 Thread.sleep(10L);
/* 505 */               } catch (InterruptedException ex) {
/* 506 */                 Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*     */       };
/* 512 */     Thread thread2 = new Thread(runnable2);
/*     */     
/* 514 */     thread2.start();
/*     */     try {
/* 516 */       Thread.sleep(100L);
/* 517 */     } catch (InterruptedException ex) {
/* 518 */       Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/* 520 */     synchronized (suo_) {
/*     */       
/* 522 */       return this.fan_hui_ma;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean fa_song_fe(byte[] shu, int chao_shi) {
/* 528 */     this.fh_shu = 4;
/* 529 */     this.jie_shou_ji_shu = 0;
/* 530 */     this.jie_shou_lei_xing = 3;
/* 531 */     this.jie_shou_huan_cun.clear();
/* 532 */     this.fan_hui_ma = false;
/* 533 */     this.com.writeBytes(shu, shu.length);
/* 534 */     return true;
/*     */   }
/*     */   
/*     */   public boolean fa_song_she_zhi(byte[] shu, int chao_shi) {
/* 538 */     this.fh_shu = 4;
/* 539 */     this.jie_shou_ji_shu = 0;
/* 540 */     this.jie_shou_lei_xing = 0;
/* 541 */     this.jie_shou_huan_cun.clear();
/* 542 */     this.fan_hui_ma = false;
/* 543 */     this.com.writeBytes(shu, shu.length);
/* 544 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\examples\Com.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
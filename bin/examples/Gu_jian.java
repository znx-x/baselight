/*     */ package examples;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JProgressBar;
/*     */ import javax.swing.LayoutStyle;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.UnsupportedLookAndFeelException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Gu_jian
/*     */   extends JFrame
/*     */ {
/*  43 */   int jin_du = 0; private JButton jButton1; private JComboBox<String> jComboBox1;
/*     */   private JLabel jLabel1;
/*     */   private JProgressBar jProgressBar1;
/*     */   
/*     */   public Gu_jian() {
/*  48 */     initComponents();
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
/*     */   private void initComponents() {
/*  60 */     this.jButton1 = new JButton();
/*  61 */     this.jComboBox1 = new JComboBox<>();
/*  62 */     this.jLabel1 = new JLabel();
/*  63 */     this.jProgressBar1 = new JProgressBar();
/*     */     
/*  65 */     setDefaultCloseOperation(3);
/*  66 */     setTitle("UP");
/*  67 */     addWindowListener(new WindowAdapter() {
/*     */           public void windowOpened(WindowEvent evt) {
/*  69 */             Gu_jian.this.formWindowOpened(evt);
/*     */           }
/*     */         });
/*     */     
/*  73 */     this.jButton1.setText("更新");
/*  74 */     this.jButton1.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/*  76 */             Gu_jian.this.jButton1ActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/*  80 */     this.jComboBox1.setModel(new DefaultComboBoxModel<>(new String[] { "K6", "JL1", "JL1_S", "JL2", "JL3", "JL3_S", "JL4", "JL4_S", "L1", "L1_S", "L3", "Z2", "Z3", "Z4", "" }));
/*     */     
/*  82 */     this.jLabel1.setText("设备型号");
/*     */     
/*  84 */     GroupLayout layout = new GroupLayout(getContentPane());
/*  85 */     getContentPane().setLayout(layout);
/*  86 */     layout.setHorizontalGroup(layout
/*  87 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  88 */         .addGroup(layout.createSequentialGroup()
/*  89 */           .addGap(66, 66, 66)
/*  90 */           .addComponent(this.jLabel1)
/*  91 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 72, 32767)
/*  92 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/*  93 */             .addComponent(this.jButton1, -1, -1, 32767)
/*  94 */             .addComponent(this.jComboBox1, -2, 164, -2))
/*  95 */           .addGap(50, 50, 50))
/*  96 */         .addComponent(this.jProgressBar1, -1, -1, 32767));
/*     */     
/*  98 */     layout.setVerticalGroup(layout
/*  99 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 100 */         .addGroup(layout.createSequentialGroup()
/* 101 */           .addGap(42, 42, 42)
/* 102 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 103 */             .addComponent(this.jComboBox1, -2, -1, -2)
/* 104 */             .addComponent(this.jLabel1))
/* 105 */           .addGap(33, 33, 33)
/* 106 */           .addComponent(this.jButton1, -2, 37, -2)
/* 107 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 11, 32767)
/* 108 */           .addComponent(this.jProgressBar1, -2, -1, -2)));
/*     */ 
/*     */     
/* 111 */     pack();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void formWindowOpened(WindowEvent evt) {
/* 119 */     this.jLabel1.setText(mainJFrame.str_xing_hao);
/* 120 */     this.jButton1.setText(mainJFrame.str_kai_shi_geng_xin);
/* 121 */     setBounds(500, 300, getWidth(), getHeight());
/* 122 */     setIconImage((new ImageIcon(getClass().getResource("/tu/tu_biao.png"))).getImage());
/*     */   }
/*     */   
/*     */   public boolean downloadNet(String di_zhi) throws MalformedURLException {
/* 126 */     int bytesum = 0;
/* 127 */     int byteread = 0;
/*     */     
/* 129 */     URL url = new URL(di_zhi);
/*     */     
/*     */     try {
/* 132 */       URLConnection conn = url.openConnection();
/* 133 */       InputStream inStream = conn.getInputStream();
/* 134 */       String filepath = System.getProperty("user.dir");
/* 135 */       FileOutputStream fs = new FileOutputStream(filepath + "/bin.bin");
/*     */       
/* 137 */       byte[] buffer = new byte[1204];
/*     */       
/* 139 */       while ((byteread = inStream.read(buffer)) != -1) {
/* 140 */         bytesum += byteread;
/* 141 */         System.out.println(bytesum);
/* 142 */         fs.write(buffer, 0, byteread);
/*     */       } 
/* 144 */       inStream.close();
/* 145 */       fs.close();
/* 146 */       return true;
/* 147 */     } catch (FileNotFoundException e) {
/* 148 */       e.printStackTrace();
/* 149 */     } catch (IOException e) {
/* 150 */       e.printStackTrace();
/*     */     } 
/* 152 */     return false;
/*     */   }
/*     */   byte jiao_yan(byte[] bao) {
/* 155 */     int sum = 0;
/* 156 */     for (int i = 0; i < bao.length - 1; i++) {
/* 157 */       int a = 0xFF & bao[i];
/* 158 */       sum += a;
/*     */     } 
/* 160 */     if (sum > 255) {
/* 161 */       sum ^= 0xFFFFFFFF;
/* 162 */       sum++;
/*     */     } 
/* 164 */     sum &= 0xFF;
/* 165 */     return (byte)sum;
/*     */   }
/*     */   
/*     */   void fu_wei() {
/* 169 */     byte[] bao = { -2, 0, 5, 0, 0 };
/* 170 */     bao[4] = jiao_yan(bao);
/* 171 */     mainJFrame.com2.fa_song(bao, 1);
/*     */     try {
/* 173 */       Thread.sleep(600L);
/* 174 */     } catch (InterruptedException ex) {
/* 175 */       Logger.getLogger(Gu_jian.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */   }
/*     */   public byte[] getContent(String filePath) throws IOException {
/* 179 */     File file = new File(filePath);
/* 180 */     long fileSize = file.length();
/* 181 */     if (fileSize > 2147483647L) {
/* 182 */       System.out.println("file too big...");
/* 183 */       return null;
/*     */     } 
/* 185 */     FileInputStream fi = new FileInputStream(file);
/* 186 */     byte[] buffer = new byte[(int)fileSize];
/* 187 */     int offset = 0;
/* 188 */     int numRead = 0;
/* 189 */     while (offset < buffer.length && (
/* 190 */       numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
/* 191 */       offset += numRead;
/*     */     }
/*     */     
/* 194 */     if (offset != buffer.length) {
/* 195 */       throw new IOException("Could not completely read file " + file
/* 196 */           .getName());
/*     */     }
/* 198 */     fi.close();
/* 199 */     return buffer;
/*     */   }
/*     */ 
/*     */   
/*     */   int jiao_yan(List<Byte> m) {
/* 204 */     int jiao = 0;
/* 205 */     for (int i = 0; i < m.size(); i++)
/*     */     {
/* 207 */       jiao += ((Byte)m.get(i)).byteValue();
/*     */     }
/* 209 */     if (jiao > 255) {
/*     */       
/* 211 */       jiao ^= 0xFFFFFFFF;
/* 212 */       jiao++;
/*     */     } 
/* 214 */     jiao &= 0xFF;
/* 215 */     return jiao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static byte[] listTobyte1(List<Byte> list) {
/* 225 */     if (list == null || list.size() < 0)
/* 226 */       return null; 
/* 227 */     byte[] bytes = new byte[list.size()];
/* 228 */     int i = 0;
/* 229 */     Iterator<Byte> iterator = list.iterator();
/* 230 */     while (iterator.hasNext()) {
/* 231 */       bytes[i] = ((Byte)iterator.next()).byteValue();
/* 232 */       i++;
/*     */     } 
/* 234 */     return bytes;
/*     */   }
/*     */   
/*     */   void sheng() {
/* 238 */     byte[] byData = null;
/* 239 */     mainJFrame.com2.fa_song(new byte[] { 2, 0, 5, 0, 115 }, 1);
/*     */     try {
/* 241 */       Thread.sleep(6000L);
/* 242 */     } catch (InterruptedException ex) {
/* 243 */       Logger.getLogger(Gu_jian.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/* 245 */     String filepath = System.getProperty("user.dir");
/*     */     try {
/* 247 */       byData = getContent(filepath + "/bin.bin");
/* 248 */     } catch (IOException ex) {
/* 249 */       Logger.getLogger(Gu_jian.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/* 251 */     if (byData.equals(null)) {
/*     */       return;
/*     */     }
/* 254 */     List<Byte> bao = new ArrayList<>();
/* 255 */     int l = 0;
/* 256 */     if (byData.length % 1024 > 0) {
/* 257 */       l = byData.length / 1024 + 1;
/*     */     } else {
/* 259 */       l = byData.length / 1024;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 264 */     for (int j = 0; j < l; j++) {
/*     */       
/* 266 */       for (int i = 0; i < 1024; i++) {
/*     */         
/* 268 */         if (j * 1024 + i < byData.length) {
/*     */ 
/*     */           
/* 271 */           bao.add(Byte.valueOf(byData[j * 1024 + i]));
/*     */         } else {
/*     */           
/* 274 */           bao.add(Byte.valueOf((byte)-1));
/*     */         } 
/* 276 */       }  bao.add(0, Byte.valueOf((byte)4));
/* 277 */       bao.add(0, Byte.valueOf((byte)4));
/* 278 */       bao.add(0, Byte.valueOf((byte)3));
/*     */       
/* 280 */       bao.add(Byte.valueOf((byte)jiao_yan(bao)));
/*     */       
/*     */       while (true) {
/*     */         try {
/* 284 */           Thread.sleep(10L);
/* 285 */         } catch (InterruptedException ex) {
/* 286 */           Logger.getLogger(Gu_jian.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */         } 
/* 288 */         if (mainJFrame.com2.fa_song(listTobyte1(bao), 1)) {
/* 289 */           bao.clear();
/* 290 */           Dimension d = this.jProgressBar1.getSize();
/*     */           
/* 292 */           this.jin_du = (int)(j / l * 100.0D);
/*     */ 
/*     */           
/* 295 */           SwingUtilities.invokeLater(new Runnable()
/*     */               {
/* 297 */                 public void run() { Gu_jian.this.jProgressBar1.setValue(Gu_jian.this.jin_du); } }); break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 301 */     SwingUtilities.invokeLater(new Runnable() {
/*     */           public void run() {
/* 303 */             Gu_jian.this.jProgressBar1.setValue(0);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   void geng_xin(final String di_zhi) {
/* 309 */     Runnable runnable2 = new Runnable() {
/*     */         public void run() {
/*     */           try {
/* 312 */             if (Gu_jian.this.downloadNet(di_zhi)) {
/*     */               
/* 314 */               Gu_jian.this.fu_wei();
/* 315 */               Gu_jian.this.sheng();
/* 316 */               mainJFrame.com2.fa_song(new byte[] { 4, 0, 4, 0 }, 1);
/* 317 */               Gu_jian.this.jButton1.setEnabled(true);
/* 318 */               Gu_jian.this.jButton1.setText(mainJFrame.bundle.getString("str_kai_shi_geng_xin"));
/*     */             } else {
/*     */               
/* 321 */               JOptionPane.showMessageDialog(null, mainJFrame.str_xia_zai_shi_bai);
/* 322 */               Gu_jian.this.jButton1.setEnabled(true);
/* 323 */               Gu_jian.this.jButton1.setText(mainJFrame.bundle.getString("str_kai_shi_geng_xin"));
/*     */             } 
/* 325 */           } catch (MalformedURLException ex) {
/* 326 */             Logger.getLogger(Gu_jian.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */           } 
/*     */         }
/*     */       };
/* 330 */     Thread thread2 = new Thread(runnable2);
/*     */     
/* 332 */     thread2.start();
/*     */   }
/*     */   
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 336 */     this.jButton1.setEnabled(false);
/* 337 */     this.jButton1.setText(mainJFrame.bundle.getString("str_qing_shao_hou"));
/* 338 */     switch (this.jComboBox1.getSelectedIndex()) {
/*     */       
/*     */       case 0:
/* 341 */         geng_xin("http://jiakuo25.0594.bftii.com/K6.bin");
/*     */         break;
/*     */       case 1:
/* 344 */         geng_xin("http://jiakuo25.0594.bftii.com/JL1.bin");
/*     */         break;
/*     */       case 2:
/* 347 */         geng_xin("http://jiakuo25.0594.bftii.com/JL1_S.bin");
/*     */         break;
/*     */       case 3:
/* 350 */         geng_xin("http://jiakuo25.0594.bftii.com/JL2.bin");
/*     */         break;
/*     */       case 4:
/* 353 */         geng_xin("http://jiakuo25.0594.bftii.com/JL3.bin");
/*     */         break;
/*     */       case 5:
/* 356 */         geng_xin("http://jiakuo25.0594.bftii.com/JL3_S.bin");
/*     */         break;
/*     */       case 6:
/* 359 */         geng_xin("http://jiakuo25.0594.bftii.com/JL4.bin");
/*     */         break;
/*     */       case 7:
/* 362 */         geng_xin("http://jiakuo25.0594.bftii.com/JL4_S.bin");
/*     */         break;
/*     */       case 8:
/* 365 */         geng_xin("http://jiakuo25.0594.bftii.com/L1.bin");
/*     */         break;
/*     */       case 9:
/* 368 */         geng_xin("http://jiakuo25.0594.bftii.com/L1_S.bin");
/*     */         break;
/*     */       case 10:
/* 371 */         geng_xin("http://jiakuo25.0594.bftii.com/L3.bin");
/*     */         break;
/*     */       case 11:
/* 374 */         geng_xin("http://jiakuo25.0594.bftii.com/Z2.bin");
/*     */         break;
/*     */       case 12:
/* 377 */         geng_xin("http://jiakuo25.0594.bftii.com/Z3.bin");
/*     */         break;
/*     */       case 13:
/* 380 */         geng_xin("http://jiakuo25.0594.bftii.com/Z4.bin");
/*     */         break;
/*     */     } 
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
/*     */   public static void main(String[] args) {
/*     */     try {
/* 395 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
/* 396 */         if ("Nimbus".equals(info.getName())) {
/* 397 */           UIManager.setLookAndFeel(info.getClassName());
/*     */           break;
/*     */         } 
/*     */       } 
/* 401 */     } catch (ClassNotFoundException ex) {
/* 402 */       Logger.getLogger(Gu_jian.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 403 */     } catch (InstantiationException ex) {
/* 404 */       Logger.getLogger(Gu_jian.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 405 */     } catch (IllegalAccessException ex) {
/* 406 */       Logger.getLogger(Gu_jian.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 407 */     } catch (UnsupportedLookAndFeelException ex) {
/* 408 */       Logger.getLogger(Gu_jian.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 413 */     EventQueue.invokeLater(new Runnable() {
/*     */           public void run() {
/* 415 */             (new Gu_jian()).setVisible(true);
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\examples\Gu_jian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
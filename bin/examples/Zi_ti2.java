/*     */ package examples;
/*     */ import java.awt.Font;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JSlider;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.LayoutStyle;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.UnsupportedLookAndFeelException;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
/*     */ 
/*     */ public class Zi_ti2 extends JDialog {
/*  33 */   public static Font ziti = null;
/*  34 */   static int box1 = 0; static int box2 = 0; static int daxiao = 10; Hua_ban fu; private JSlider da_xiao; private JButton jButton1; private JCheckBox jCheckBox1; private JCheckBox jCheckBox2; private JCheckBox jCheckBox3; private JLabel jLabel1; private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JLabel jLabel4;
/*     */   private JLabel jLabel5;
/*     */   
/*     */   public Zi_ti2(Frame parent, boolean modal) {
/*  40 */     super(parent, modal);
/*  41 */     initComponents();
/*     */   } private JLabel jLabel6; private JLabel jLabel7; private JPanel jPanel1; private JScrollPane jScrollPane1; private JTextField jTextField1; private JTextField jTextField2; private JTextField jTextField3; private JTextField jTextField4; private JTextArea wen_zi; private JComboBox<String> zi_ti_Box; private JComboBox<String> zi_xing_Box;
/*     */   public Zi_ti2(Hua_ban parent, boolean modal) {
/*  44 */     setTitle("输入文字");
/*  45 */     setLocation(new Point(200, 100));
/*  46 */     this.fu = parent;
/*  47 */     initComponents();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initComponents() {
/*  58 */     this.jLabel2 = new JLabel();
/*  59 */     this.jLabel3 = new JLabel();
/*  60 */     this.zi_ti_Box = new JComboBox<>();
/*  61 */     this.zi_xing_Box = new JComboBox<>();
/*  62 */     this.da_xiao = new JSlider();
/*  63 */     this.jScrollPane1 = new JScrollPane();
/*  64 */     this.wen_zi = new JTextArea();
/*  65 */     this.jButton1 = new JButton();
/*  66 */     this.jLabel1 = new JLabel();
/*  67 */     this.jCheckBox1 = new JCheckBox();
/*  68 */     this.jCheckBox2 = new JCheckBox();
/*  69 */     this.jCheckBox3 = new JCheckBox();
/*  70 */     this.jPanel1 = new JPanel();
/*  71 */     this.jLabel4 = new JLabel();
/*  72 */     this.jTextField1 = new JTextField();
/*  73 */     this.jLabel5 = new JLabel();
/*  74 */     this.jTextField2 = new JTextField();
/*  75 */     this.jLabel6 = new JLabel();
/*  76 */     this.jTextField3 = new JTextField();
/*  77 */     this.jLabel7 = new JLabel();
/*  78 */     this.jTextField4 = new JTextField();
/*     */     
/*  80 */     setDefaultCloseOperation(2);
/*  81 */     addWindowListener(new WindowAdapter() {
/*     */           public void windowOpened(WindowEvent evt) {
/*  83 */             Zi_ti2.this.formWindowOpened(evt);
/*     */           }
/*     */         });
/*     */     
/*  87 */     this.jLabel2.setText("字型：");
/*     */     
/*  89 */     this.jLabel3.setText("尺寸:10");
/*     */     
/*  91 */     this.zi_ti_Box.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
/*  92 */     this.zi_ti_Box.addItemListener(new ItemListener() {
/*     */           public void itemStateChanged(ItemEvent evt) {
/*  94 */             Zi_ti2.this.zi_ti_BoxItemStateChanged(evt);
/*     */           }
/*     */         });
/*     */     
/*  98 */     this.zi_xing_Box.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
/*  99 */     this.zi_xing_Box.addItemListener(new ItemListener() {
/*     */           public void itemStateChanged(ItemEvent evt) {
/* 101 */             Zi_ti2.this.zi_xing_BoxItemStateChanged(evt);
/*     */           }
/*     */         });
/*     */     
/* 105 */     this.da_xiao.setMaximum(200);
/* 106 */     this.da_xiao.setValue(10);
/* 107 */     this.da_xiao.addChangeListener(new ChangeListener() {
/*     */           public void stateChanged(ChangeEvent evt) {
/* 109 */             Zi_ti2.this.da_xiaoStateChanged(evt);
/*     */           }
/*     */         });
/*     */     
/* 113 */     this.wen_zi.setColumns(20);
/* 114 */     this.wen_zi.setRows(5);
/* 115 */     this.wen_zi.setText("ABCD");
/* 116 */     this.jScrollPane1.setViewportView(this.wen_zi);
/*     */     
/* 118 */     this.jButton1.setText("OK");
/* 119 */     this.jButton1.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 121 */             Zi_ti2.this.jButton1ActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 125 */     this.jLabel1.setText("字体：");
/*     */     
/* 127 */     this.jCheckBox1.setText("竖向");
/*     */     
/* 129 */     this.jCheckBox2.setText("矢量图5");
/*     */     
/* 131 */     this.jCheckBox3.setText("序列号");
/* 132 */     this.jCheckBox3.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 134 */             Zi_ti2.this.jCheckBox3ActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 138 */     this.jLabel4.setText("前缀：");
/*     */     
/* 140 */     this.jTextField1.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 142 */             Zi_ti2.this.jTextField1ActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 146 */     this.jLabel5.setText("起始：");
/*     */     
/* 148 */     this.jTextField2.setText("0");
/* 149 */     this.jTextField2.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 151 */             Zi_ti2.this.jTextField2ActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 155 */     this.jLabel6.setText("结束：");
/*     */     
/* 157 */     this.jTextField3.setText("1000");
/* 158 */     this.jTextField3.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 160 */             Zi_ti2.this.jTextField3ActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 164 */     this.jLabel7.setText("后缀：");
/*     */     
/* 166 */     this.jTextField4.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 168 */             Zi_ti2.this.jTextField4ActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 172 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 173 */     this.jPanel1.setLayout(jPanel1Layout);
/* 174 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/* 175 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 176 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 177 */           .addContainerGap()
/* 178 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 179 */             .addComponent(this.jLabel4)
/* 180 */             .addComponent(this.jLabel5)
/* 181 */             .addComponent(this.jLabel6)
/* 182 */             .addComponent(this.jLabel7))
/* 183 */           .addGap(86, 86, 86)
/* 184 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 185 */             .addComponent(this.jTextField1, -2, 126, -2)
/* 186 */             .addComponent(this.jTextField2, -2, 126, -2)
/* 187 */             .addComponent(this.jTextField3, -2, 126, -2)
/* 188 */             .addComponent(this.jTextField4, -2, 126, -2))
/* 189 */           .addContainerGap()));
/*     */     
/* 191 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 192 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 193 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 194 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 195 */             .addComponent(this.jTextField1, -2, -1, -2)
/* 196 */             .addComponent(this.jLabel4))
/* 197 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 198 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 199 */             .addComponent(this.jTextField2, -2, -1, -2)
/* 200 */             .addComponent(this.jLabel5))
/* 201 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 202 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 203 */             .addComponent(this.jTextField3, -2, -1, -2)
/* 204 */             .addComponent(this.jLabel6))
/* 205 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 206 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 207 */             .addComponent(this.jTextField4, -2, -1, -2)
/* 208 */             .addComponent(this.jLabel7))
/* 209 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 212 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 213 */     getContentPane().setLayout(layout);
/* 214 */     layout.setHorizontalGroup(layout
/* 215 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 216 */         .addGroup(layout.createSequentialGroup()
/* 217 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 218 */             .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
/* 219 */               .addGap(0, 0, 32767)
/* 220 */               .addComponent(this.jButton1, -2, 92, -2))
/* 221 */             .addGroup(layout.createSequentialGroup()
/* 222 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 223 */                 .addGroup(layout.createSequentialGroup()
/* 224 */                   .addGap(14, 14, 14)
/* 225 */                   .addComponent(this.jLabel1))
/* 226 */                 .addGroup(layout.createSequentialGroup()
/* 227 */                   .addContainerGap()
/* 228 */                   .addComponent(this.zi_ti_Box, -2, 165, -2)))
/* 229 */               .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 230 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 231 */                 .addComponent(this.jLabel2)
/* 232 */                 .addComponent(this.zi_xing_Box, -2, 149, -2))
/* 233 */               .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 234 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 235 */                 .addComponent(this.da_xiao, -2, -1, -2)
/* 236 */                 .addComponent(this.jLabel3))
/* 237 */               .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 238 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 239 */                 .addComponent(this.jCheckBox2)
/* 240 */                 .addComponent(this.jCheckBox1))
/* 241 */               .addGap(0, 198, 32767)))
/* 242 */           .addContainerGap())
/* 243 */         .addGroup(layout.createSequentialGroup()
/* 244 */           .addContainerGap()
/* 245 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 246 */             .addComponent(this.jScrollPane1)
/* 247 */             .addGroup(layout.createSequentialGroup()
/* 248 */               .addComponent(this.jCheckBox3)
/* 249 */               .addGap(10, 10, 10)
/* 250 */               .addComponent(this.jPanel1, -2, -1, -2)
/* 251 */               .addGap(0, 0, 32767)))));
/*     */     
/* 253 */     layout.setVerticalGroup(layout
/* 254 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 255 */         .addGroup(layout.createSequentialGroup()
/* 256 */           .addContainerGap()
/* 257 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 258 */             .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 259 */               .addGroup(layout.createSequentialGroup()
/* 260 */                 .addComponent(this.jLabel1)
/* 261 */                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 262 */                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 263 */                   .addComponent(this.zi_ti_Box, -2, -1, -2)
/* 264 */                   .addComponent(this.da_xiao, -2, 0, 32767)))
/* 265 */               .addGroup(layout.createSequentialGroup()
/* 266 */                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 267 */                   .addComponent(this.jLabel2)
/* 268 */                   .addComponent(this.jLabel3)
/* 269 */                   .addComponent(this.jCheckBox2))
/* 270 */                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 271 */                 .addComponent(this.zi_xing_Box, -2, -1, -2)))
/* 272 */             .addComponent(this.jCheckBox1))
/* 273 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 274 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 275 */             .addComponent(this.jCheckBox3)
/* 276 */             .addComponent(this.jPanel1, -2, -1, -2))
/* 277 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 278 */           .addComponent(this.jScrollPane1, -1, 416, 32767)
/* 279 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 280 */           .addComponent(this.jButton1, -2, 34, -2)
/* 281 */           .addContainerGap()));
/*     */ 
/*     */     
/* 284 */     pack();
/*     */   }
/*     */ 
/*     */   
/*     */   private void zi_ti_BoxItemStateChanged(ItemEvent evt) {
/* 289 */     String fontName = (String)evt.getItem();
/* 290 */     System.out.println(fontName);
/*     */ 
/*     */     
/* 293 */     ziti = new Font(fontName, ziti.getStyle(), ziti.getSize());
/* 294 */     this.wen_zi.setFont(ziti);
/* 295 */     box1 = this.zi_ti_Box.getSelectedIndex();
/*     */   }
/*     */   public static int getFontStyleByCnName(String fontStyle) {
/* 298 */     if (fontStyle.equals(mainJFrame.str_chang_gui)) {
/* 299 */       return 0;
/*     */     }
/* 301 */     if (fontStyle.equals(mainJFrame.str_xie_ti)) {
/* 302 */       return 2;
/*     */     }
/* 304 */     if (fontStyle.equals(mainJFrame.str_cu_ti)) {
/* 305 */       return 1;
/*     */     }
/* 307 */     if (fontStyle.equals(mainJFrame.str_cu_xie)) {
/* 308 */       return 3;
/*     */     }
/* 310 */     return -1;
/*     */   }
/*     */   
/*     */   private void zi_xing_BoxItemStateChanged(ItemEvent evt) {
/* 314 */     String fontStyle = (String)evt.getItem();
/* 315 */     System.out.println(fontStyle);
/* 316 */     ziti = new Font(ziti.getName(), getFontStyleByCnName(fontStyle), ziti.getSize());
/* 317 */     this.wen_zi.setFont(ziti);
/* 318 */     box2 = this.zi_xing_Box.getSelectedIndex();
/*     */   }
/*     */ 
/*     */   
/*     */   private void da_xiaoStateChanged(ChangeEvent evt) {
/* 323 */     this.jLabel3.setText(mainJFrame.bundle.getString("str_chi_cun") + this.da_xiao.getValue());
/* 324 */     daxiao = 100 + this.da_xiao.getValue() * 5;
/* 325 */     ziti = new Font(ziti.getName(), ziti.getStyle(), daxiao);
/* 326 */     this.wen_zi.setFont(ziti);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 332 */     if (this.jCheckBox3.isSelected()) {
/*     */       
/* 334 */       liu_shui_hao lsm = new liu_shui_hao(Integer.parseInt(this.jTextField2.getText()), Integer.parseInt(this.jTextField3.getText()), Integer.parseInt(this.jTextField2.getText()), this.jTextField1.getText(), this.jTextField4.getText(), ziti, this.jCheckBox1.isSelected(), this.jCheckBox2.isSelected());
/* 335 */       Hua_ban.ty_shuzu.add(lsm.sheng_cheng());
/*     */     
/*     */     }
/* 338 */     else if (this.jCheckBox1.isSelected()) {
/* 339 */       Hua_ban.ty_shuzu.add(Tu_yuan.chuang_jian_wen_zi_shu(this.wen_zi.getText(), ziti, 0, this.jCheckBox2.isSelected()));
/*     */     } else {
/* 341 */       Hua_ban.ty_shuzu.add(Tu_yuan.chuang_jian_wen_zi(this.wen_zi.getText(), ziti, this.jCheckBox2.isSelected()));
/*     */     } 
/* 343 */     for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++)
/*     */     {
/* 345 */       ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong = false;
/*     */     }
/* 347 */     ((Tu_yuan)Hua_ban.ty_shuzu.get(Hua_ban.ty_shuzu.size() - 1)).xuan_zhong = true;
/*     */     
/* 349 */     Tu_yuan.zhong_xin(Hua_ban.ty_shuzu);
/* 350 */     Che_xiao.tian_jia();
/* 351 */     this.fu.repaint();
/* 352 */     setVisible(false);
/*     */   }
/*     */ 
/*     */   
/*     */   private void formWindowOpened(WindowEvent evt) {
/* 357 */     this.jPanel1.setVisible(false);
/* 358 */     this.wen_zi.setBounds((this.wen_zi.getBounds()).x, 75, (this.wen_zi.getBounds()).width, 49);
/* 359 */     this.jLabel1.setText(mainJFrame.str_zi_ti);
/* 360 */     this.jLabel2.setText(mainJFrame.str_zi_xing);
/* 361 */     this.jLabel3.setText(mainJFrame.str_chi_cun + "10");
/* 362 */     this.jCheckBox1.setText(mainJFrame.str_shu);
/* 363 */     this.jCheckBox2.setText(mainJFrame.str_shi_liang);
/* 364 */     this.jCheckBox3.setText(mainJFrame.str_liu_shui_hao);
/* 365 */     this.jCheckBox3.setVisible(false);
/* 366 */     this.jLabel4.setText(mainJFrame.str_qian_zhui);
/* 367 */     this.jLabel5.setText(mainJFrame.str_qi_shi);
/* 368 */     this.jLabel6.setText(mainJFrame.str_jie_shu);
/* 369 */     this.jLabel7.setText(mainJFrame.str_hou_zhui);
/*     */     
/* 371 */     setIconImage((new ImageIcon(getClass().getResource("/tu/tu_biao.png"))).getImage());
/*     */     
/* 373 */     GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
/* 374 */     String[] fontNames = ge.getAvailableFontFamilyNames();
/* 375 */     String[] fontNames2 = new String[fontNames.length];
/* 376 */     for (int i = 0; i < fontNames.length; i++)
/*     */     {
/* 378 */       fontNames2[i] = fontNames[fontNames.length - i - 1];
/*     */     }
/* 380 */     this.zi_ti_Box.setModel(new DefaultComboBoxModel<>(fontNames2));
/*     */     
/* 382 */     String[] fontStyles = { mainJFrame.str_chang_gui, mainJFrame.str_xie_ti, mainJFrame.str_cu_ti, mainJFrame.str_cu_xie };
/* 383 */     this.zi_xing_Box.setModel(new DefaultComboBoxModel<>(fontStyles));
/* 384 */     if (ziti == null) {
/*     */       
/* 386 */       daxiao = 150;
/* 387 */       ziti = new Font(this.zi_ti_Box.getItemAt(this.zi_ti_Box.getSelectedIndex()), 0, daxiao);
/* 388 */       this.wen_zi.setFont(ziti);
/* 389 */       box1 = this.zi_ti_Box.getSelectedIndex();
/* 390 */       box2 = this.zi_xing_Box.getSelectedIndex();
/*     */     } else {
/*     */       
/* 393 */       this.zi_ti_Box.setSelectedIndex(box1);
/* 394 */       this.zi_xing_Box.setSelectedIndex(box2);
/* 395 */       this.da_xiao.setValue((daxiao - 100) / 5);
/* 396 */       ziti = new Font(ziti.getName(), getFontStyleByCnName(this.zi_xing_Box.getItemAt(box2)), ziti.getSize());
/* 397 */       this.wen_zi.setFont(ziti);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void jCheckBox3ActionPerformed(ActionEvent evt) {
/* 403 */     if (this.jCheckBox3.isSelected()) {
/*     */       
/* 405 */       this.wen_zi.setBounds((this.wen_zi.getBounds()).x, 155, (this.wen_zi.getBounds()).width, 45);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 410 */       this.wen_zi.setBounds((this.wen_zi.getBounds()).x, 75, (this.wen_zi.getBounds()).width, 45);
/*     */     } 
/* 412 */     this.jPanel1.setVisible(this.jCheckBox3.isSelected());
/* 413 */     liu_shui_hao();
/*     */   }
/*     */   
/*     */   void liu_shui_hao() {
/* 417 */     this.wen_zi.setText(this.jTextField1.getText() + this.jTextField2.getText() + this.jTextField4.getText());
/*     */   }
/*     */   
/*     */   private void jTextField1ActionPerformed(ActionEvent evt) {
/* 421 */     liu_shui_hao();
/*     */   }
/*     */ 
/*     */   
/*     */   private void jTextField2ActionPerformed(ActionEvent evt) {
/* 426 */     liu_shui_hao();
/*     */   }
/*     */ 
/*     */   
/*     */   private void jTextField3ActionPerformed(ActionEvent evt) {
/* 431 */     liu_shui_hao();
/*     */   }
/*     */ 
/*     */   
/*     */   private void jTextField4ActionPerformed(ActionEvent evt) {
/* 436 */     liu_shui_hao();
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
/* 449 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
/* 450 */         if ("Nimbus".equals(info.getName())) {
/* 451 */           UIManager.setLookAndFeel(info.getClassName());
/*     */           break;
/*     */         } 
/*     */       } 
/* 455 */     } catch (ClassNotFoundException ex) {
/* 456 */       Logger.getLogger(Zi_ti2.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 457 */     } catch (InstantiationException ex) {
/* 458 */       Logger.getLogger(Zi_ti2.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 459 */     } catch (IllegalAccessException ex) {
/* 460 */       Logger.getLogger(Zi_ti2.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 461 */     } catch (UnsupportedLookAndFeelException ex) {
/* 462 */       Logger.getLogger(Zi_ti2.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 467 */     EventQueue.invokeLater(new Runnable() {
/*     */           public void run() {
/* 469 */             Zi_ti2 dialog = new Zi_ti2(new JFrame(), true);
/* 470 */             dialog.addWindowListener(new WindowAdapter()
/*     */                 {
/*     */                   public void windowClosing(WindowEvent e) {
/* 473 */                     System.exit(0);
/*     */                   }
/*     */                 });
/* 476 */             dialog.setVisible(true);
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\examples\Zi_ti2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
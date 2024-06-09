/*     */ package examples;
/*     */ import java.awt.Font;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.LayoutStyle;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ 
/*     */ public class Zi_ti extends JFrame {
/*  18 */   public static Font ziti = null;
/*  19 */   static int box1 = 0; static int box2 = 0; static int daxiao = 14; private JSlider da_xiao; private JButton jButton1; private JLabel jLabel1; private JLabel jLabel2; private JLabel jLabel3; private JScrollPane jScrollPane1; private JTextArea wen_zi;
/*     */   private JComboBox<String> zi_ti_Box;
/*     */   private JComboBox<String> zi_xing_Box;
/*     */   
/*     */   public Zi_ti() {
/*  24 */     initComponents();
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
/*  36 */     this.jLabel1 = new JLabel();
/*  37 */     this.jLabel2 = new JLabel();
/*  38 */     this.jLabel3 = new JLabel();
/*  39 */     this.zi_ti_Box = new JComboBox<>();
/*  40 */     this.zi_xing_Box = new JComboBox<>();
/*  41 */     this.da_xiao = new JSlider();
/*  42 */     this.jScrollPane1 = new JScrollPane();
/*  43 */     this.wen_zi = new JTextArea();
/*  44 */     this.jButton1 = new JButton();
/*     */     
/*  46 */     setDefaultCloseOperation(3);
/*  47 */     setTitle("字体设置");
/*  48 */     setLocation(new Point(300, 300));
/*  49 */     addWindowListener(new WindowAdapter() {
/*     */           public void windowOpened(WindowEvent evt) {
/*  51 */             Zi_ti.this.formWindowOpened(evt);
/*     */           }
/*     */         });
/*     */     
/*  55 */     this.jLabel1.setText("字体：");
/*     */     
/*  57 */     this.jLabel2.setText("字型：");
/*     */     
/*  59 */     this.jLabel3.setText("大小：");
/*     */     
/*  61 */     this.zi_ti_Box.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
/*  62 */     this.zi_ti_Box.addItemListener(new ItemListener() {
/*     */           public void itemStateChanged(ItemEvent evt) {
/*  64 */             Zi_ti.this.zi_ti_BoxItemStateChanged(evt);
/*     */           }
/*     */         });
/*     */     
/*  68 */     this.zi_xing_Box.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
/*  69 */     this.zi_xing_Box.addItemListener(new ItemListener() {
/*     */           public void itemStateChanged(ItemEvent evt) {
/*  71 */             Zi_ti.this.zi_xing_BoxItemStateChanged(evt);
/*     */           }
/*     */         });
/*     */     
/*  75 */     this.da_xiao.setMaximum(200);
/*  76 */     this.da_xiao.setValue(14);
/*  77 */     this.da_xiao.addChangeListener(new ChangeListener() {
/*     */           public void stateChanged(ChangeEvent evt) {
/*  79 */             Zi_ti.this.da_xiaoStateChanged(evt);
/*     */           }
/*     */         });
/*     */     
/*  83 */     this.wen_zi.setColumns(20);
/*  84 */     this.wen_zi.setRows(5);
/*  85 */     this.wen_zi.setText("ABCD");
/*  86 */     this.jScrollPane1.setViewportView(this.wen_zi);
/*     */     
/*  88 */     this.jButton1.setText("OK");
/*  89 */     this.jButton1.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/*  91 */             Zi_ti.this.jButton1ActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/*  95 */     GroupLayout layout = new GroupLayout(getContentPane());
/*  96 */     getContentPane().setLayout(layout);
/*  97 */     layout.setHorizontalGroup(layout
/*  98 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  99 */         .addGroup(layout.createSequentialGroup()
/* 100 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 101 */             .addGroup(layout.createSequentialGroup()
/* 102 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 103 */                 .addGroup(layout.createSequentialGroup()
/* 104 */                   .addGap(14, 14, 14)
/* 105 */                   .addComponent(this.jLabel1))
/* 106 */                 .addGroup(layout.createSequentialGroup()
/* 107 */                   .addContainerGap()
/* 108 */                   .addComponent(this.zi_ti_Box, -2, 165, -2)))
/* 109 */               .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 110 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 111 */                 .addComponent(this.jLabel2)
/* 112 */                 .addComponent(this.zi_xing_Box, -2, 149, -2))
/* 113 */               .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 114 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 115 */                 .addComponent(this.da_xiao, -2, -1, -2)
/* 116 */                 .addComponent(this.jLabel3))
/* 117 */               .addGap(0, 142, 32767))
/* 118 */             .addGroup(layout.createSequentialGroup()
/* 119 */               .addContainerGap()
/* 120 */               .addComponent(this.jScrollPane1))
/* 121 */             .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
/* 122 */               .addGap(0, 0, 32767)
/* 123 */               .addComponent(this.jButton1, -2, 92, -2)))
/* 124 */           .addContainerGap()));
/*     */     
/* 126 */     layout.setVerticalGroup(layout
/* 127 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 128 */         .addGroup(layout.createSequentialGroup()
/* 129 */           .addContainerGap()
/* 130 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 131 */             .addGroup(layout.createSequentialGroup()
/* 132 */               .addComponent(this.jLabel1)
/* 133 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 134 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 135 */                 .addComponent(this.zi_ti_Box, -2, -1, -2)
/* 136 */                 .addComponent(this.da_xiao, -2, 0, 32767)))
/* 137 */             .addGroup(layout.createSequentialGroup()
/* 138 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 139 */                 .addComponent(this.jLabel2)
/* 140 */                 .addComponent(this.jLabel3))
/* 141 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 142 */               .addComponent(this.zi_xing_Box, -2, -1, -2)))
/* 143 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 144 */           .addComponent(this.jScrollPane1, -1, 387, 32767)
/* 145 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 146 */           .addComponent(this.jButton1, -2, 34, -2)
/* 147 */           .addContainerGap()));
/*     */ 
/*     */     
/* 150 */     pack();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void formWindowOpened(WindowEvent evt) {
/* 156 */     GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
/* 157 */     String[] fontNames = ge.getAvailableFontFamilyNames();
/* 158 */     this.zi_ti_Box.setModel(new DefaultComboBoxModel<>(fontNames));
/*     */     
/* 160 */     String[] fontStyles = { "常规", "斜体", "粗体", "粗斜体" };
/* 161 */     this.zi_xing_Box.setModel(new DefaultComboBoxModel<>(fontStyles));
/* 162 */     if (ziti == null) {
/*     */       
/* 164 */       ziti = new Font(this.zi_ti_Box.getItemAt(this.zi_ti_Box.getSelectedIndex()), 0, 14);
/* 165 */       this.wen_zi.setFont(ziti);
/*     */     } else {
/*     */       
/* 168 */       this.zi_ti_Box.setSelectedIndex(box1);
/* 169 */       this.zi_xing_Box.setSelectedIndex(box2);
/* 170 */       this.da_xiao.setValue(daxiao);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int getFontStyleByCnName(String fontStyle) {
/* 175 */     if (fontStyle.equals("常规")) {
/* 176 */       return 0;
/*     */     }
/* 178 */     if (fontStyle.equals("斜体")) {
/* 179 */       return 2;
/*     */     }
/* 181 */     if (fontStyle.equals("粗体")) {
/* 182 */       return 1;
/*     */     }
/* 184 */     if (fontStyle.equals("粗斜体")) {
/* 185 */       return 3;
/*     */     }
/* 187 */     return -1;
/*     */   }
/*     */   
/*     */   private void zi_ti_BoxItemStateChanged(ItemEvent evt) {
/* 191 */     String fontName = (String)evt.getItem();
/* 192 */     System.out.println(fontName);
/*     */ 
/*     */     
/* 195 */     ziti = new Font(fontName, ziti.getStyle(), ziti.getSize());
/* 196 */     this.wen_zi.setFont(ziti);
/* 197 */     box1 = this.zi_ti_Box.getSelectedIndex();
/*     */   }
/*     */ 
/*     */   
/*     */   private void zi_xing_BoxItemStateChanged(ItemEvent evt) {
/* 202 */     String fontStyle = (String)evt.getItem();
/* 203 */     System.out.println(fontStyle);
/* 204 */     ziti = new Font(ziti.getName(), getFontStyleByCnName(fontStyle), ziti.getSize());
/* 205 */     this.wen_zi.setFont(ziti);
/* 206 */     box2 = this.zi_xing_Box.getSelectedIndex();
/*     */   }
/*     */ 
/*     */   
/*     */   private void da_xiaoStateChanged(ChangeEvent evt) {
/* 211 */     ziti = new Font(ziti.getName(), ziti.getStyle(), this.da_xiao.getValue());
/* 212 */     this.wen_zi.setFont(ziti);
/* 213 */     daxiao = this.da_xiao.getValue();
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
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 226 */     Tu_yuan.chuang_jian_wen_zi(this.wen_zi.getText(), ziti, false);
/* 227 */     setVisible(false);
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
/* 240 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
/* 241 */         if ("Nimbus".equals(info.getName())) {
/* 242 */           UIManager.setLookAndFeel(info.getClassName());
/*     */           break;
/*     */         } 
/*     */       } 
/* 246 */     } catch (ClassNotFoundException ex) {
/* 247 */       Logger.getLogger(Zi_ti.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 248 */     } catch (InstantiationException ex) {
/* 249 */       Logger.getLogger(Zi_ti.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 250 */     } catch (IllegalAccessException ex) {
/* 251 */       Logger.getLogger(Zi_ti.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 252 */     } catch (UnsupportedLookAndFeelException ex) {
/* 253 */       Logger.getLogger(Zi_ti.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 258 */     EventQueue.invokeLater(new Runnable() {
/*     */           public void run() {
/* 260 */             (new Zi_ti()).setVisible(true);
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\examples\Zi_ti.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
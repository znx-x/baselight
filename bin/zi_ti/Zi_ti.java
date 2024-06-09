/*     */ package zi_ti;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Font;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.Panel;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.border.EmptyBorder;
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
/*     */ public class Zi_ti
/*     */   extends JDialog
/*     */ {
/*  40 */   private final JPanel contentPanel = new JPanel();
/*  41 */   private JComboBox fontNameBox = null;
/*  42 */   private JComboBox fontStyleBox = null;
/*  43 */   private JComboBox fontSizeBox = null;
/*  44 */   private JTextArea txtrHereIs = null;
/*     */   
/*     */   private String fontName;
/*     */   
/*     */   private String fontStyle;
/*     */   
/*     */   private String fontSize;
/*     */   
/*     */   private int fontSty;
/*     */   private int fontSiz;
/*     */   
/*     */   public static void main(String[] args) {
/*     */     try {
/*  57 */       Zi_ti dialog = new Zi_ti();
/*  58 */       dialog.setDefaultCloseOperation(2);
/*  59 */       dialog.setVisible(true);
/*  60 */     } catch (Exception e) {
/*  61 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Zi_ti() {
/*  69 */     setTitle("你好我来了");
/*  70 */     setBounds(100, 100, 483, 234);
/*  71 */     getContentPane().setLayout(new BorderLayout());
/*  72 */     this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  73 */     getContentPane().add(this.contentPanel, "Center");
/*  74 */     this.contentPanel.setLayout((LayoutManager)null);
/*     */     
/*  76 */     JLabel lblf = new JLabel("字体(F):");
/*  77 */     lblf.setBounds(0, 10, 54, 15);
/*  78 */     this.contentPanel.add(lblf);
/*     */ 
/*     */     
/*  81 */     JLabel lbly = new JLabel("字形(Y):");
/*  82 */     lbly.setBounds(182, 10, 54, 15);
/*  83 */     this.contentPanel.add(lbly);
/*     */ 
/*     */     
/*  86 */     JLabel lbls = new JLabel("大小(S):");
/*  87 */     lbls.setBounds(315, 10, 54, 15);
/*  88 */     this.contentPanel.add(lbls);
/*     */ 
/*     */     
/*  91 */     JLabel label = new JLabel("显示效果:");
/*  92 */     label.setBounds(126, 82, 64, 15);
/*  93 */     this.contentPanel.add(label);
/*     */ 
/*     */     
/*  96 */     Panel panel = new Panel();
/*  97 */     panel.setBounds(196, 40, 228, 113);
/*  98 */     this.contentPanel.add(panel);
/*  99 */     panel.setLayout((LayoutManager)null);
/*     */     
/* 101 */     this.txtrHereIs = new JTextArea();
/* 102 */     this.txtrHereIs.setBounds(39, 38, 177, 44);
/* 103 */     this.txtrHereIs
/* 104 */       .setText("这里显示预览\r\nHere is the preview");
/* 105 */     panel.add(this.txtrHereIs);
/*     */ 
/*     */     
/* 108 */     this.fontNameBox = new JComboBox();
/* 109 */     this.fontNameBox.setBounds(49, 7, 123, 21);
/* 110 */     this.contentPanel.add(this.fontNameBox);
/* 111 */     this.fontNameBox.addItemListener(new ItemListener() {
/*     */           public void itemStateChanged(ItemEvent itemevent) {
/* 113 */             Zi_ti.this.fontName = (String)itemevent.getItem();
/* 114 */             System.out.println(Zi_ti.this.fontName);
/*     */ 
/*     */             
/* 117 */             Font f = new Font(Zi_ti.this.fontName, Zi_ti.this.fontSty, Zi_ti.this.fontSiz);
/* 118 */             Zi_ti.this.txtrHereIs.setFont(f);
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 123 */     this.fontStyleBox = new JComboBox();
/* 124 */     this.fontStyleBox.setBounds(232, 7, 73, 21);
/* 125 */     this.contentPanel.add(this.fontStyleBox);
/* 126 */     this.fontStyleBox.addItemListener(new ItemListener() {
/*     */           public void itemStateChanged(ItemEvent itemevent) {
/* 128 */             Zi_ti.this.fontStyle = (String)itemevent.getItem();
/* 129 */             Zi_ti.this.fontSty = Zi_ti.getFontStyleByCnName(Zi_ti.this.fontStyle);
/*     */             
/* 131 */             Font f = new Font(Zi_ti.this.fontName, Zi_ti.this.fontSty, Zi_ti.this.fontSiz);
/* 132 */             Zi_ti.this.txtrHereIs.setFont(f);
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 137 */     this.fontSizeBox = new JComboBox();
/* 138 */     this.fontSizeBox.setBounds(379, 7, 78, 21);
/* 139 */     this.contentPanel.add(this.fontSizeBox);
/* 140 */     this.fontSizeBox.addItemListener(new ItemListener() {
/*     */           public void itemStateChanged(ItemEvent itemevent) {
/* 142 */             Zi_ti.this.fontSize = (String)itemevent.getItem();
/* 143 */             Zi_ti.this.fontSiz = Integer.parseInt(Zi_ti.this.fontSize);
/*     */ 
/*     */             
/* 146 */             Font f = new Font(Zi_ti.this.fontName, Zi_ti.this.fontSty, Zi_ti.this.fontSiz);
/* 147 */             Zi_ti.this.txtrHereIs.setFont(f);
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 152 */     JPanel buttonPane = new JPanel();
/* 153 */     buttonPane.setLayout(new FlowLayout(2));
/* 154 */     getContentPane().add(buttonPane, "South");
/*     */     
/* 156 */     JButton okButton = new JButton("确定");
/* 157 */     okButton.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent actionevent) {
/* 159 */             int fontSty = Zi_ti.getFontStyleByCnName(Zi_ti.this.fontStyle);
/* 160 */             int fontSiz = Integer.parseInt(Zi_ti.this.fontSize);
/* 161 */             JOptionPane.showMessageDialog(Zi_ti.this, "你选择的字体名称：" + Zi_ti.this
/* 162 */                 .fontName + ",字体样式：" + Zi_ti.this.fontStyle + ",字体大小：" + fontSiz, "提示", -1);
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 168 */     okButton.setActionCommand("OK");
/* 169 */     buttonPane.add(okButton);
/* 170 */     getRootPane().setDefaultButton(okButton);
/*     */ 
/*     */     
/* 173 */     JButton cancelButton = new JButton("取消");
/* 174 */     cancelButton.setActionCommand("Cancel");
/* 175 */     buttonPane.add(cancelButton);
/* 176 */     cancelButton.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent actionevent) {
/* 178 */             Zi_ti.this.dispose();
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 186 */     GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
/* 187 */     String[] fontNames = ge.getAvailableFontFamilyNames();
/* 188 */     this.fontNameBox.setModel(new DefaultComboBoxModel<>(fontNames));
/*     */     
/* 190 */     String[] fontStyles = { "常规", "斜体", "粗体", "粗斜体" };
/* 191 */     this.fontStyleBox.setModel(new DefaultComboBoxModel<>(fontStyles));
/*     */     
/* 193 */     String[] fontSizes = { "8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36", "48", "72" };
/*     */     
/* 195 */     this.fontSizeBox.setModel(new DefaultComboBoxModel<>(fontSizes));
/* 196 */     System.out.println("finish.");
/*     */     
/* 198 */     this.fontSizeBox.setSelectedIndex(4);
/* 199 */     this.fontStyle = (String)this.fontStyleBox.getSelectedItem();
/* 200 */     this.fontSize = (String)this.fontSizeBox.getSelectedItem();
/* 201 */     this.fontSty = getFontStyleByCnName(this.fontStyle);
/* 202 */     this.fontSiz = Integer.parseInt(this.fontSize);
/*     */   }
/*     */   
/*     */   public static int getFontStyleByCnName(String fontStyle) {
/* 206 */     if (fontStyle.equals("常规")) {
/* 207 */       return 0;
/*     */     }
/* 209 */     if (fontStyle.equals("斜体")) {
/* 210 */       return 2;
/*     */     }
/* 212 */     if (fontStyle.equals("粗体")) {
/* 213 */       return 1;
/*     */     }
/* 215 */     if (fontStyle.equals("粗斜体")) {
/* 216 */       return 3;
/*     */     }
/* 218 */     return -1;
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\zi_ti\Zi_ti.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
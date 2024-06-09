/*      */ package examples;
/*      */ 
/*      */ import com.fazecast.jSerialComm.SerialPort;
/*      */ import com.kitfox.svg.SVGDiagram;
/*      */ import com.kitfox.svg.SVGException;
/*      */ import com.kitfox.svg.SVGUniverse;
/*      */ import com.pnuema.java.barcode.Barcode;
/*      */ import io.nayuki.qrcodegen.QrCode;
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.Container;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.EventQueue;
/*      */ import java.awt.Font;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Image;
/*      */ import java.awt.LayoutManager;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.TextArea;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.ComponentAdapter;
/*      */ import java.awt.event.ComponentEvent;
/*      */ import java.awt.event.ItemEvent;
/*      */ import java.awt.event.ItemListener;
/*      */ import java.awt.event.KeyEvent;
/*      */ import java.awt.event.KeyListener;
/*      */ import java.awt.event.MouseAdapter;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.awt.event.MouseListener;
/*      */ import java.awt.event.MouseMotionAdapter;
/*      */ import java.awt.event.MouseWheelEvent;
/*      */ import java.awt.event.MouseWheelListener;
/*      */ import java.awt.event.WindowAdapter;
/*      */ import java.awt.event.WindowEvent;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.GeneralPath;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.awt.image.ImageObserver;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URI;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Properties;
/*      */ import java.util.ResourceBundle;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import javax.imageio.ImageIO;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.DefaultComboBoxModel;
/*      */ import javax.swing.GroupLayout;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JCheckBox;
/*      */ import javax.swing.JComboBox;
/*      */ import javax.swing.JDialog;
/*      */ import javax.swing.JFileChooser;
/*      */ import javax.swing.JFrame;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JMenu;
/*      */ import javax.swing.JMenuBar;
/*      */ import javax.swing.JMenuItem;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JPopupMenu;
/*      */ import javax.swing.JProgressBar;
/*      */ import javax.swing.JSlider;
/*      */ import javax.swing.JTextField;
/*      */ import javax.swing.JToggleButton;
/*      */ import javax.swing.LayoutStyle;
/*      */ import javax.swing.SwingUtilities;
/*      */ import javax.swing.UIManager;
/*      */ import javax.swing.UnsupportedLookAndFeelException;
/*      */ import javax.swing.event.ChangeEvent;
/*      */ import javax.swing.event.ChangeListener;
/*      */ import javax.swing.filechooser.FileNameExtensionFilter;
/*      */ import net.sf.image4j.codec.bmp.BMPEncoder;
/*      */ import org.kabeja.dxf.DXFDocument;
/*      */ import org.kabeja.parser.ParseException;
/*      */ import org.kabeja.parser.Parser;
/*      */ import org.kabeja.parser.ParserBuilder;
/*      */ import org.netbeans.lib.awtextra.AbsoluteConstraints;
/*      */ import org.netbeans.lib.awtextra.AbsoluteLayout;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class mainJFrame
/*      */   extends JFrame
/*      */   implements KeyListener
/*      */ {
/*      */   JMenuBar jmb;
/*  137 */   JPopupMenu popMenu = new JPopupMenu();
/*      */   JMenu menu1;
/*      */   JMenuItem item1;
/*  140 */   public byte[] banben = new byte[] { 0, 0, 0, 0 };
/*  141 */   public static String ce_shi = "";
/*  142 */   public static String ban_ben = "v2.0.6";
/*      */   boolean an_xia = false;
/*  144 */   int anxia_x = 0, anxia_y = 0;
/*  145 */   int anxia_x_1 = 0, anxia_y_1 = 0;
/*  146 */   int an = 0;
/*  147 */   int an_niu = 0;
/*      */   
/*  149 */   public static Com com2 = null;
/*      */   boolean com_dakai = false;
/*  151 */   private Object suo_fhm = new Object();
/*      */   
/*      */   private boolean kuang = false;
/*      */   
/*      */   boolean fan_hui_ma = false;
/*      */   
/*      */   boolean shi_zi = false;
/*  158 */   mainJFrame win = null;
/*  159 */   public static int chaoshi = 0;
/*      */   public static boolean kai_shi2 = false;
/*  161 */   public static ResourceBundle bundle = null;
/*      */   boolean fu_zhi = false;
/*  163 */   List<Tu_yuan> ty_fu_zhi = new ArrayList<>();
/*      */   boolean tuo_dong = false;
/*      */   boolean mo_ni = false;
/*      */   boolean jing_du = false;
/*  167 */   int ji_xing = 0;
/*  168 */   int shi_jian = 0;
/*  169 */   String str_da_kai = "";
/*  170 */   String str_wen_zi = "";
/*  171 */   String str_yuan = "";
/*  172 */   String str_fang = "";
/*  173 */   String str_xin = "";
/*  174 */   String str_xing = "";
/*  175 */   String str_bao_cun = "";
/*  176 */   String str_shi_zi = "";
/*  177 */   String str_yu_lan = "";
/*  178 */   String str_kai_shi = "";
/*  179 */   String str_ting_zhi = "";
/*  180 */   String str_lian_jie = "";
/*  181 */   String str_ruo_guang = "";
/*  182 */   String str_gong_lv = "";
/*  183 */   String str_shen_du = "";
/*  184 */   String str_gong_lv_sl = "";
/*  185 */   String str_shen_du_sl = "";
/*  186 */   String str_ci_shu = "";
/*  187 */   String str_jing_du = "";
/*  188 */   String str_gao = "";
/*  189 */   String str_zhong = "";
/*  190 */   String str_di = "";
/*      */   
/*  192 */   public static String str_zi_ti = "";
/*  193 */   public static String str_zi_xing = "";
/*  194 */   public static String str_chi_cun = "";
/*  195 */   public static String str_chang_gui = "";
/*  196 */   public static String str_xie_ti = "";
/*  197 */   public static String str_cu_ti = "";
/*  198 */   public static String str_cu_xie = "";
/*  199 */   public static String str_shu = "";
/*  200 */   public static String str_shi_liang = "";
/*  201 */   public static String str_geng_xin = "";
/*  202 */   public static String str_shi_zhi = "";
/*  203 */   public static String str_gu_jian = "";
/*  204 */   public static String str_xing_hao = "";
/*  205 */   public static String str_kai_shi_geng_xin = "";
/*  206 */   public static String str_xia_zai_shi_bai = "";
/*  207 */   public static String str_liu_shui_hao = "";
/*  208 */   public static String str_qi_shi = "";
/*  209 */   public static String str_jie_shu = "";
/*  210 */   public static String str_qian_zhui = "";
/*  211 */   public static String str_hou_zhui = ""; List<Byte> bao; Boolean fa_wan; private JLabel bq_bao_cun; private JLabel bq_bao_cun1; private JLabel bq_da_kai; private JLabel bq_er_wei_ma; private JLabel bq_lian_jie; private JLabel bq_lian_jie1; private JLabel bq_tiao_ma; private JLabel bq_tu_xing; private JLabel bq_wen_zi; private JToggleButton but_hei_bai; private JToggleButton but_hui_du; private JToggleButton but_lun_kuo; private JToggleButton but_su_miao; private Hua_ban hua_ban1; private JButton jButton1;
/*      */   private JButton jButton12;
/*      */   private JButton jButton13;
/*      */   private JButton jButton14;
/*      */   private JButton jButton15;
/*      */   private JButton jButton16;
/*      */   private JButton jButton17;
/*      */   private JButton jButton18;
/*      */   private JButton jButton19;
/*      */   private JButton jButton2;
/*      */   private JButton jButton20;
/*      */   private JButton jButton21;
/*      */   private JButton jButton22;
/*      */   private JButton jButton4;
/*      */   private JButton jButton5;
/*      */   
/*      */   private void initComponents() {
/*  228 */     this.jDialog1 = new JDialog();
/*  229 */     this.jButton2 = new JButton();
/*  230 */     this.jButton1 = new JButton();
/*  231 */     this.jButton7 = new JButton();
/*  232 */     this.hua_ban1 = new Hua_ban();
/*  233 */     this.jPanel1 = new JPanel();
/*  234 */     this.jLabel1 = new JLabel();
/*  235 */     this.jTextField1 = new JTextField();
/*  236 */     this.jLabel2 = new JLabel();
/*  237 */     this.jTextField2 = new JTextField();
/*  238 */     this.jLabel3 = new JLabel();
/*  239 */     this.jLabel4 = new JLabel();
/*  240 */     this.jButton18 = new JButton();
/*  241 */     this.jPanel2 = new JPanel();
/*  242 */     this.jSlider1 = new JSlider();
/*  243 */     this.jSlider2 = new JSlider();
/*  244 */     this.jLabel5 = new JLabel();
/*  245 */     this.jLabel7 = new JLabel();
/*  246 */     this.jComboBox1 = new JComboBox<>();
/*  247 */     this.jLabel14 = new JLabel();
/*  248 */     this.jSlider6 = new JSlider();
/*  249 */     this.jLabel13 = new JLabel();
/*  250 */     this.jLabel16 = new JLabel();
/*  251 */     this.jSlider7 = new JSlider();
/*  252 */     this.but_hei_bai = new JToggleButton();
/*  253 */     this.but_hui_du = new JToggleButton();
/*  254 */     this.but_lun_kuo = new JToggleButton();
/*  255 */     this.but_su_miao = new JToggleButton();
/*  256 */     this.jButton5 = new JButton();
/*  257 */     this.jButton6 = new JButton();
/*  258 */     this.jButton8 = new JButton();
/*  259 */     this.jLabel10 = new JLabel();
/*  260 */     this.jButton14 = new JButton();
/*  261 */     this.jButton21 = new JButton();
/*  262 */     this.jButton15 = new JButton();
/*  263 */     this.jButton17 = new JButton();
/*  264 */     this.jCheckBox2 = new JCheckBox();
/*  265 */     this.jButton4 = new JButton();
/*  266 */     this.jLabel11 = new JLabel();
/*  267 */     this.jCheckBox1 = new JCheckBox();
/*  268 */     this.jTextField3 = new JTextField();
/*  269 */     this.jTextField4 = new JTextField();
/*  270 */     this.jLabel8 = new JLabel();
/*  271 */     this.jLabel9 = new JLabel();
/*  272 */     this.jdt = new JProgressBar();
/*  273 */     this.jButton12 = new JButton();
/*  274 */     this.jLabel6 = new JLabel();
/*  275 */     this.bq_wen_zi = new JLabel();
/*  276 */     this.bq_bao_cun = new JLabel();
/*  277 */     this.bq_lian_jie = new JLabel();
/*  278 */     this.bq_da_kai = new JLabel();
/*  279 */     this.jButton13 = new JButton();
/*  280 */     this.bq_er_wei_ma = new JLabel();
/*  281 */     this.jButton16 = new JButton();
/*  282 */     this.bq_tiao_ma = new JLabel();
/*  283 */     this.jButton20 = new JButton();
/*  284 */     this.bq_tu_xing = new JLabel();
/*  285 */     this.jButton22 = new JButton();
/*  286 */     this.bq_bao_cun1 = new JLabel();
/*  287 */     this.jButton19 = new JButton();
/*  288 */     this.bq_lian_jie1 = new JLabel();
/*      */     
/*  290 */     GroupLayout jDialog1Layout = new GroupLayout(this.jDialog1.getContentPane());
/*  291 */     this.jDialog1.getContentPane().setLayout(jDialog1Layout);
/*  292 */     jDialog1Layout.setHorizontalGroup(jDialog1Layout
/*  293 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  294 */         .addGap(0, 400, 32767));
/*      */     
/*  296 */     jDialog1Layout.setVerticalGroup(jDialog1Layout
/*  297 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  298 */         .addGap(0, 300, 32767));
/*      */ 
/*      */     
/*  301 */     this.jButton2.setText("jButton2");
/*      */     
/*  303 */     setDefaultCloseOperation(3);
/*  304 */     setTitle("激光雕刻机");
/*  305 */     setBackground(new Color(204, 204, 204));
/*  306 */     setLocation(new Point(400, 200));
/*  307 */     addComponentListener(new ComponentAdapter() {
/*      */           public void componentResized(ComponentEvent evt) {
/*  309 */             mainJFrame.this.formComponentResized(evt);
/*      */           }
/*      */         });
/*  312 */     addWindowListener(new WindowAdapter() {
/*      */           public void windowOpened(WindowEvent evt) {
/*  314 */             mainJFrame.this.formWindowOpened(evt);
/*      */           }
/*      */         });
/*      */     
/*  318 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/tu/tu_pian.png")));
/*  319 */     this.jButton1.setToolTipText("打开图片");
/*  320 */     this.jButton1.addMouseListener(new MouseAdapter() {
/*      */           public void mouseClicked(MouseEvent evt) {
/*  322 */             mainJFrame.this.jButton1MouseClicked(evt);
/*      */           }
/*      */         });
/*  325 */     this.jButton1.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  327 */             mainJFrame.this.jButton1ActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  331 */     this.jButton7.setIcon(new ImageIcon(getClass().getResource("/tu/wen_zi.png")));
/*  332 */     this.jButton7.setToolTipText("输入文字");
/*  333 */     this.jButton7.addMouseListener(new MouseAdapter() {
/*      */           public void mouseClicked(MouseEvent evt) {
/*  335 */             mainJFrame.this.jButton7MouseClicked(evt);
/*      */           }
/*      */         });
/*  338 */     this.jButton7.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  340 */             mainJFrame.this.jButton7ActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  344 */     this.hua_ban1.setBackground(new Color(255, 255, 255));
/*  345 */     this.hua_ban1.setBorder(BorderFactory.createLineBorder(new Color(153, 153, 255)));
/*  346 */     this.hua_ban1.addMouseMotionListener(new MouseMotionAdapter() {
/*      */           public void mouseDragged(MouseEvent evt) {
/*  348 */             mainJFrame.this.hua_ban1MouseDragged(evt);
/*      */           }
/*      */         });
/*  351 */     this.hua_ban1.addMouseWheelListener(new MouseWheelListener() {
/*      */           public void mouseWheelMoved(MouseWheelEvent evt) {
/*  353 */             mainJFrame.this.hua_ban1MouseWheelMoved(evt);
/*      */           }
/*      */         });
/*  356 */     this.hua_ban1.addMouseListener(new MouseAdapter() {
/*      */           public void mousePressed(MouseEvent evt) {
/*  358 */             mainJFrame.this.hua_ban1MousePressed(evt);
/*      */           }
/*      */           public void mouseReleased(MouseEvent evt) {
/*  361 */             mainJFrame.this.hua_ban1MouseReleased(evt);
/*      */           }
/*      */         });
/*  364 */     this.hua_ban1.setLayout((LayoutManager)new AbsoluteLayout());
/*      */     
/*  366 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/*  367 */     this.jPanel1.setPreferredSize(new Dimension(3, 3));
/*  368 */     this.jPanel1.setLayout((LayoutManager)new AbsoluteLayout());
/*      */     
/*  370 */     this.jLabel1.setFont(new Font("宋体", 0, 14));
/*  371 */     this.jLabel1.setText(" X:");
/*  372 */     this.jPanel1.add(this.jLabel1, new AbsoluteConstraints(10, 10, 24, 23));
/*      */     
/*  374 */     this.jTextField1.setText("0");
/*  375 */     this.jTextField1.setMinimumSize(new Dimension(6, 20));
/*  376 */     this.jTextField1.setPreferredSize(new Dimension(12, 20));
/*  377 */     this.jPanel1.add(this.jTextField1, new AbsoluteConstraints(38, 10, 30, 25));
/*      */     
/*  379 */     this.jLabel2.setFont(new Font("宋体", 0, 14));
/*  380 */     this.jLabel2.setText("Y:");
/*  381 */     this.jPanel1.add(this.jLabel2, new AbsoluteConstraints(75, 10, -1, 23));
/*      */     
/*  383 */     this.jTextField2.setText("0");
/*  384 */     this.jTextField2.setMinimumSize(new Dimension(6, 20));
/*  385 */     this.jTextField2.setPreferredSize(new Dimension(12, 20));
/*  386 */     this.jPanel1.add(this.jTextField2, new AbsoluteConstraints(93, 10, 30, 25));
/*      */     
/*  388 */     this.jLabel3.setFont(new Font("宋体", 0, 14));
/*  389 */     this.jLabel3.setText("W:");
/*  390 */     this.jPanel1.add(this.jLabel3, new AbsoluteConstraints(130, 10, -1, 23));
/*      */     
/*  392 */     this.jLabel4.setFont(new Font("宋体", 0, 14));
/*  393 */     this.jLabel4.setText("H:");
/*  394 */     this.jPanel1.add(this.jLabel4, new AbsoluteConstraints(185, 10, -1, 20));
/*      */     
/*  396 */     this.hua_ban1.add(this.jPanel1, new AbsoluteConstraints(30, 20, 10, 50));
/*      */     
/*  398 */     this.jButton18.setIcon(new ImageIcon(getClass().getResource("/tu/usb_1.png")));
/*  399 */     this.jButton18.setToolTipText("连接设备");
/*  400 */     this.jButton18.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  402 */             mainJFrame.this.jButton18ActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  406 */     this.jPanel2.setBackground(new Color(204, 204, 204));
/*  407 */     this.jPanel2.setLayout((LayoutManager)new AbsoluteLayout());
/*      */     
/*  409 */     this.jSlider1.setValue(100);
/*  410 */     this.jSlider1.addChangeListener(new ChangeListener() {
/*      */           public void stateChanged(ChangeEvent evt) {
/*  412 */             mainJFrame.this.jSlider1StateChanged(evt);
/*      */           }
/*      */         });
/*  415 */     this.jSlider1.addMouseListener(new MouseAdapter() {
/*      */           public void mouseReleased(MouseEvent evt) {
/*  417 */             mainJFrame.this.jSlider1MouseReleased(evt);
/*      */           }
/*      */         });
/*  420 */     this.jPanel2.add(this.jSlider1, new AbsoluteConstraints(10, 30, 210, -1));
/*      */     
/*  422 */     this.jSlider2.setValue(90);
/*  423 */     this.jSlider2.addChangeListener(new ChangeListener() {
/*      */           public void stateChanged(ChangeEvent evt) {
/*  425 */             mainJFrame.this.jSlider2StateChanged(evt);
/*      */           }
/*      */         });
/*  428 */     this.jSlider2.addMouseListener(new MouseAdapter() {
/*      */           public void mouseReleased(MouseEvent evt) {
/*  430 */             mainJFrame.this.jSlider2MouseReleased(evt);
/*      */           }
/*      */         });
/*  433 */     this.jPanel2.add(this.jSlider2, new AbsoluteConstraints(10, 80, 210, -1));
/*      */     
/*  435 */     this.jLabel5.setFont(new Font("宋体", 0, 14));
/*  436 */     this.jLabel5.setText("功率：100%");
/*  437 */     this.jPanel2.add(this.jLabel5, new AbsoluteConstraints(10, 10, 200, -1));
/*      */     
/*  439 */     this.jLabel7.setFont(new Font("宋体", 0, 14));
/*  440 */     this.jLabel7.setText("次数：");
/*  441 */     this.jPanel2.add(this.jLabel7, new AbsoluteConstraints(10, 232, 100, -1));
/*      */     
/*  443 */     this.jComboBox1.setModel(new DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
/*  444 */     this.jComboBox1.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  446 */             mainJFrame.this.jComboBox1ActionPerformed(evt);
/*      */           }
/*      */         });
/*  449 */     this.jPanel2.add(this.jComboBox1, new AbsoluteConstraints(110, 230, 110, -1));
/*      */     
/*  451 */     this.jLabel14.setFont(new Font("宋体", 0, 14));
/*  452 */     this.jLabel14.setText("对比度：50%");
/*  453 */     this.jPanel2.add(this.jLabel14, new AbsoluteConstraints(12, 110, 200, -1));
/*      */     
/*  455 */     this.jSlider6.addChangeListener(new ChangeListener() {
/*      */           public void stateChanged(ChangeEvent evt) {
/*  457 */             mainJFrame.this.jSlider6StateChanged(evt);
/*      */           }
/*      */         });
/*  460 */     this.jPanel2.add(this.jSlider6, new AbsoluteConstraints(10, 130, 210, -1));
/*      */     
/*  462 */     this.jLabel13.setFont(new Font("宋体", 0, 14));
/*  463 */     this.jLabel13.setText("速度：90%");
/*  464 */     this.jPanel2.add(this.jLabel13, new AbsoluteConstraints(12, 60, 190, -1));
/*  465 */     this.jLabel13.getAccessibleContext().setAccessibleName("速度：%10");
/*      */     
/*  467 */     this.jLabel16.setFont(new Font("宋体", 0, 14));
/*  468 */     this.jLabel16.setText("填充密度：5");
/*  469 */     this.jPanel2.add(this.jLabel16, new AbsoluteConstraints(12, 170, 130, -1));
/*      */     
/*  471 */     this.jSlider7.setMaximum(10);
/*  472 */     this.jSlider7.setMinimum(1);
/*  473 */     this.jSlider7.setToolTipText("");
/*  474 */     this.jSlider7.setValue(5);
/*  475 */     this.jSlider7.addChangeListener(new ChangeListener() {
/*      */           public void stateChanged(ChangeEvent evt) {
/*  477 */             mainJFrame.this.jSlider7StateChanged(evt);
/*      */           }
/*      */         });
/*  480 */     this.jPanel2.add(this.jSlider7, new AbsoluteConstraints(10, 190, 210, -1));
/*      */     
/*  482 */     this.but_hei_bai.setText("黑白");
/*  483 */     this.but_hei_bai.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  485 */             mainJFrame.this.but_hei_baiActionPerformed(evt);
/*      */           }
/*      */         });
/*  488 */     this.jPanel2.add(this.but_hei_bai, new AbsoluteConstraints(10, 370, 100, -1));
/*      */     
/*  490 */     this.but_hui_du.setText("灰度");
/*  491 */     this.but_hui_du.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  493 */             mainJFrame.this.but_hui_duActionPerformed(evt);
/*      */           }
/*      */         });
/*  496 */     this.jPanel2.add(this.but_hui_du, new AbsoluteConstraints(120, 370, 100, -1));
/*      */     
/*  498 */     this.but_lun_kuo.setText("轮廓");
/*  499 */     this.but_lun_kuo.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  501 */             mainJFrame.this.but_lun_kuoActionPerformed(evt);
/*      */           }
/*      */         });
/*  504 */     this.jPanel2.add(this.but_lun_kuo, new AbsoluteConstraints(10, 400, 100, -1));
/*      */     
/*  506 */     this.but_su_miao.setText("素描");
/*  507 */     this.but_su_miao.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  509 */             mainJFrame.this.but_su_miaoActionPerformed(evt);
/*      */           }
/*      */         });
/*  512 */     this.jPanel2.add(this.but_su_miao, new AbsoluteConstraints(120, 400, 100, -1));
/*      */     
/*  514 */     this.jButton5.setText("X镜像");
/*  515 */     this.jButton5.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  517 */             mainJFrame.this.jButton5ActionPerformed(evt);
/*      */           }
/*      */         });
/*  520 */     this.jPanel2.add(this.jButton5, new AbsoluteConstraints(10, 430, 100, -1));
/*      */     
/*  522 */     this.jButton6.setText("Y镜像");
/*  523 */     this.jButton6.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  525 */             mainJFrame.this.jButton6ActionPerformed(evt);
/*      */           }
/*      */         });
/*  528 */     this.jPanel2.add(this.jButton6, new AbsoluteConstraints(120, 430, 100, -1));
/*      */     
/*  530 */     this.jButton8.setText("到中心");
/*  531 */     this.jButton8.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  533 */             mainJFrame.this.jButton8ActionPerformed(evt);
/*      */           }
/*      */         });
/*  536 */     this.jPanel2.add(this.jButton8, new AbsoluteConstraints(120, 460, 100, -1));
/*      */     
/*  538 */     this.jLabel10.setFont(new Font("宋体", 0, 14));
/*  539 */     this.jLabel10.setText("宽度：");
/*  540 */     this.jPanel2.add(this.jLabel10, new AbsoluteConstraints(10, 270, 100, -1));
/*      */     
/*  542 */     this.jButton14.setIcon(new ImageIcon(getClass().getResource("/tu/ding_wei_1.png")));
/*  543 */     this.jButton14.setText("预览位置");
/*  544 */     this.jButton14.setToolTipText("预览位置");
/*  545 */     this.jButton14.setHorizontalTextPosition(4);
/*  546 */     this.jButton14.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  548 */             mainJFrame.this.jButton14ActionPerformed(evt);
/*      */           }
/*      */         });
/*  551 */     this.jPanel2.add(this.jButton14, new AbsoluteConstraints(10, 530, 210, 30));
/*      */     
/*  553 */     this.jButton21.setIcon(new ImageIcon(getClass().getResource("/tu/mo_ni.png")));
/*  554 */     this.jButton21.setText("模拟雕刻");
/*  555 */     this.jButton21.setToolTipText("模拟雕刻");
/*  556 */     this.jButton21.setHorizontalTextPosition(4);
/*  557 */     this.jButton21.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  559 */             mainJFrame.this.jButton21ActionPerformed(evt);
/*      */           }
/*      */         });
/*  562 */     this.jPanel2.add(this.jButton21, new AbsoluteConstraints(10, 500, 210, 30));
/*      */     
/*  564 */     this.jButton15.setIcon(new ImageIcon(getClass().getResource("/tu/bo_fang.png")));
/*  565 */     this.jButton15.setText("开始");
/*  566 */     this.jButton15.setToolTipText("开始");
/*  567 */     this.jButton15.setHorizontalTextPosition(4);
/*  568 */     this.jButton15.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  570 */             mainJFrame.this.jButton15ActionPerformed(evt);
/*      */           }
/*      */         });
/*  573 */     this.jPanel2.add(this.jButton15, new AbsoluteConstraints(10, 560, 100, 30));
/*      */     
/*  575 */     this.jButton17.setIcon(new ImageIcon(getClass().getResource("/tu/ting_zhi.png")));
/*  576 */     this.jButton17.setText("停止");
/*  577 */     this.jButton17.setToolTipText("停止");
/*  578 */     this.jButton17.setHorizontalTextPosition(4);
/*  579 */     this.jButton17.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  581 */             mainJFrame.this.jButton17ActionPerformed(evt);
/*      */           }
/*      */         });
/*  584 */     this.jPanel2.add(this.jButton17, new AbsoluteConstraints(110, 560, 110, 30));
/*      */     
/*  586 */     this.jCheckBox2.setLabel("填充");
/*  587 */     this.jCheckBox2.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  589 */             mainJFrame.this.jCheckBox2ActionPerformed(evt);
/*      */           }
/*      */         });
/*  592 */     this.jPanel2.add(this.jCheckBox2, new AbsoluteConstraints(140, 165, -1, -1));
/*      */     
/*  594 */     this.jButton4.setText("反色");
/*  595 */     this.jButton4.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  597 */             mainJFrame.this.jButton4ActionPerformed(evt);
/*      */           }
/*      */         });
/*  600 */     this.jPanel2.add(this.jButton4, new AbsoluteConstraints(10, 460, 100, -1));
/*      */     
/*  602 */     this.jLabel11.setFont(new Font("宋体", 0, 14));
/*  603 */     this.jLabel11.setText("高度：");
/*  604 */     this.jPanel2.add(this.jLabel11, new AbsoluteConstraints(10, 300, 100, -1));
/*      */     
/*  606 */     this.jCheckBox1.setText("等比缩放");
/*  607 */     this.jCheckBox1.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  609 */             mainJFrame.this.jCheckBox1ActionPerformed(evt);
/*      */           }
/*      */         });
/*  612 */     this.jPanel2.add(this.jCheckBox1, new AbsoluteConstraints(7, 320, -1, -1));
/*      */     
/*  614 */     this.jTextField3.setText("0");
/*  615 */     this.jTextField3.setMinimumSize(new Dimension(6, 20));
/*  616 */     this.jTextField3.setPreferredSize(new Dimension(12, 20));
/*  617 */     this.jPanel2.add(this.jTextField3, new AbsoluteConstraints(120, 270, 80, 20));
/*      */     
/*  619 */     this.jTextField4.setText("0");
/*  620 */     this.jTextField4.setMinimumSize(new Dimension(6, 20));
/*  621 */     this.jTextField4.setPreferredSize(new Dimension(12, 20));
/*  622 */     this.jPanel2.add(this.jTextField4, new AbsoluteConstraints(120, 300, 80, 20));
/*      */     
/*  624 */     this.jLabel8.setIcon(new ImageIcon(getClass().getResource("/tu/shang_xia.png")));
/*  625 */     this.jLabel8.addMouseListener(new MouseAdapter() {
/*      */           public void mouseClicked(MouseEvent evt) {
/*  627 */             mainJFrame.this.jLabel8MouseClicked(evt);
/*      */           }
/*      */         });
/*  630 */     this.jPanel2.add(this.jLabel8, new AbsoluteConstraints(200, 270, 20, 20));
/*      */     
/*  632 */     this.jLabel9.setIcon(new ImageIcon(getClass().getResource("/tu/shang_xia.png")));
/*  633 */     this.jLabel9.addMouseListener(new MouseAdapter() {
/*      */           public void mouseClicked(MouseEvent evt) {
/*  635 */             mainJFrame.this.jLabel9MouseClicked(evt);
/*      */           }
/*      */         });
/*  638 */     this.jPanel2.add(this.jLabel9, new AbsoluteConstraints(200, 300, 20, 20));
/*      */     
/*  640 */     this.jdt.setRequestFocusEnabled(false);
/*  641 */     this.jdt.setStringPainted(true);
/*      */     
/*  643 */     this.jButton12.setIcon(new ImageIcon(getClass().getResource("/tu/bao_cun.png")));
/*  644 */     this.jButton12.setToolTipText("五角星");
/*  645 */     this.jButton12.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  647 */             mainJFrame.this.jButton12ActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  651 */     this.jLabel6.setFont(new Font("宋体", 0, 14));
/*  652 */     this.jLabel6.setHorizontalAlignment(4);
/*  653 */     this.jLabel6.setText("0.0");
/*      */     
/*  655 */     this.bq_wen_zi.setHorizontalAlignment(0);
/*  656 */     this.bq_wen_zi.setText("导入图片");
/*      */     
/*  658 */     this.bq_bao_cun.setHorizontalAlignment(0);
/*  659 */     this.bq_bao_cun.setText("导入图片");
/*      */     
/*  661 */     this.bq_lian_jie.setHorizontalAlignment(0);
/*  662 */     this.bq_lian_jie.setText("导入图片");
/*      */     
/*  664 */     this.bq_da_kai.setHorizontalAlignment(0);
/*  665 */     this.bq_da_kai.setText("导入图片");
/*      */     
/*  667 */     this.jButton13.setIcon(new ImageIcon(getClass().getResource("/tu/er_wei_ma.png")));
/*  668 */     this.jButton13.setToolTipText("输入文字");
/*  669 */     this.jButton13.addMouseListener(new MouseAdapter() {
/*      */           public void mouseClicked(MouseEvent evt) {
/*  671 */             mainJFrame.this.jButton13MouseClicked(evt);
/*      */           }
/*      */         });
/*  674 */     this.jButton13.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  676 */             mainJFrame.this.jButton13ActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  680 */     this.bq_er_wei_ma.setHorizontalAlignment(0);
/*  681 */     this.bq_er_wei_ma.setText("导入图片");
/*      */     
/*  683 */     this.jButton16.setIcon(new ImageIcon(getClass().getResource("/tu/tiao_ma.png")));
/*  684 */     this.jButton16.setToolTipText("输入文字");
/*  685 */     this.jButton16.addMouseListener(new MouseAdapter() {
/*      */           public void mouseClicked(MouseEvent evt) {
/*  687 */             mainJFrame.this.jButton16MouseClicked(evt);
/*      */           }
/*      */         });
/*  690 */     this.jButton16.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  692 */             mainJFrame.this.jButton16ActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  696 */     this.bq_tiao_ma.setHorizontalAlignment(0);
/*  697 */     this.bq_tiao_ma.setText("导入图片");
/*      */     
/*  699 */     this.jButton20.setIcon(new ImageIcon(getClass().getResource("/tu/tu_xing.png")));
/*  700 */     this.jButton20.setToolTipText("输入文字");
/*  701 */     this.jButton20.addMouseListener(new MouseAdapter() {
/*      */           public void mouseClicked(MouseEvent evt) {
/*  703 */             mainJFrame.this.jButton20MouseClicked(evt);
/*      */           }
/*      */         });
/*  706 */     this.jButton20.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  708 */             mainJFrame.this.jButton20ActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  712 */     this.bq_tu_xing.setHorizontalAlignment(0);
/*  713 */     this.bq_tu_xing.setText("导入图片");
/*      */     
/*  715 */     this.jButton22.setIcon(new ImageIcon(getClass().getResource("/tu/she_zhi.png")));
/*  716 */     this.jButton22.setToolTipText("五角星");
/*  717 */     this.jButton22.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  719 */             mainJFrame.this.jButton22ActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  723 */     this.bq_bao_cun1.setHorizontalAlignment(0);
/*  724 */     this.bq_bao_cun1.setText("导入图片");
/*      */     
/*  726 */     this.jButton19.setIcon(new ImageIcon(getClass().getResource("/tu/bang_zhu.png")));
/*  727 */     this.jButton19.setToolTipText("连接设备");
/*  728 */     this.jButton19.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  730 */             mainJFrame.this.jButton19ActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  734 */     this.bq_lian_jie1.setHorizontalAlignment(0);
/*  735 */     this.bq_lian_jie1.setText("导入图片");
/*      */     
/*  737 */     GroupLayout layout = new GroupLayout(getContentPane());
/*  738 */     getContentPane().setLayout(layout);
/*  739 */     layout.setHorizontalGroup(layout
/*  740 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  741 */         .addGroup(layout.createSequentialGroup()
/*  742 */           .addContainerGap()
/*  743 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  744 */             .addComponent(this.jdt, -1, -1, 32767)
/*  745 */             .addGroup(layout.createSequentialGroup()
/*  746 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  747 */                 .addComponent(this.jButton1, -2, 60, -2)
/*  748 */                 .addComponent(this.bq_da_kai, -2, 60, -2))
/*  749 */               .addGap(47, 47, 47)
/*  750 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  751 */                 .addComponent(this.bq_bao_cun, -2, 60, -2)
/*  752 */                 .addComponent(this.jButton12, -2, 60, -2))
/*  753 */               .addGap(51, 51, 51)
/*  754 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/*  755 */                 .addComponent(this.jButton7, -2, 60, -2)
/*  756 */                 .addComponent(this.bq_wen_zi, -2, 60, -2))
/*  757 */               .addGap(51, 51, 51)
/*  758 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/*  759 */                 .addComponent(this.jButton13, -2, 60, -2)
/*  760 */                 .addComponent(this.bq_er_wei_ma, -2, 60, -2))
/*  761 */               .addGap(46, 46, 46)
/*  762 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/*  763 */                 .addComponent(this.jButton16, -2, 60, -2)
/*  764 */                 .addComponent(this.bq_tiao_ma, -2, 60, -2))
/*  765 */               .addGap(52, 52, 52)
/*  766 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/*  767 */                 .addComponent(this.jButton20, -2, 60, -2)
/*  768 */                 .addComponent(this.bq_tu_xing, -2, 60, -2))
/*  769 */               .addGap(54, 54, 54)
/*  770 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  771 */                 .addComponent(this.bq_bao_cun1, -2, 60, -2)
/*  772 */                 .addComponent(this.jButton22, -2, 60, -2))
/*  773 */               .addGap(0, 0, 32767))
/*  774 */             .addComponent(this.hua_ban1, -1, -1, 32767))
/*  775 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  776 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  777 */             .addComponent(this.jPanel2, GroupLayout.Alignment.TRAILING, -2, 236, -2)
/*  778 */             .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
/*  779 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  780 */                 .addComponent(this.bq_lian_jie, -2, 60, -2)
/*  781 */                 .addComponent(this.jButton18, -2, 60, -2))
/*  782 */               .addGap(53, 53, 53)
/*  783 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  784 */                 .addComponent(this.bq_lian_jie1, -2, 60, -2)
/*  785 */                 .addComponent(this.jButton19, -2, 60, -2)))
/*  786 */             .addComponent(this.jLabel6, GroupLayout.Alignment.TRAILING, -2, 110, -2))
/*  787 */           .addContainerGap()));
/*      */     
/*  789 */     layout.setVerticalGroup(layout
/*  790 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  791 */         .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
/*  792 */           .addContainerGap()
/*  793 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/*  794 */             .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  795 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/*  796 */                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  797 */                   .addGroup(layout.createSequentialGroup()
/*  798 */                     .addComponent(this.jButton1, -2, 60, -2)
/*  799 */                     .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  800 */                     .addComponent(this.bq_da_kai))
/*  801 */                   .addGroup(layout.createSequentialGroup()
/*  802 */                     .addComponent(this.jButton22, -2, 60, -2)
/*  803 */                     .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  804 */                     .addComponent(this.bq_bao_cun1)))
/*  805 */                 .addGroup(layout.createSequentialGroup()
/*  806 */                   .addComponent(this.jButton7, -2, 60, -2)
/*  807 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  808 */                   .addComponent(this.bq_wen_zi)))
/*  809 */               .addGroup(layout.createSequentialGroup()
/*  810 */                 .addComponent(this.jButton13, -2, 60, -2)
/*  811 */                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  812 */                 .addComponent(this.bq_er_wei_ma))
/*  813 */               .addGroup(layout.createSequentialGroup()
/*  814 */                 .addComponent(this.jButton16, -2, 60, -2)
/*  815 */                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  816 */                 .addComponent(this.bq_tiao_ma))
/*  817 */               .addGroup(layout.createSequentialGroup()
/*  818 */                 .addComponent(this.jButton20, -2, 60, -2)
/*  819 */                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  820 */                 .addComponent(this.bq_tu_xing))
/*  821 */               .addGroup(layout.createSequentialGroup()
/*  822 */                 .addComponent(this.jButton12, -2, 60, -2)
/*  823 */                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  824 */                 .addComponent(this.bq_bao_cun)))
/*  825 */             .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  826 */               .addGroup(layout.createSequentialGroup()
/*  827 */                 .addComponent(this.jButton18, -2, 60, -2)
/*  828 */                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  829 */                 .addComponent(this.bq_lian_jie))
/*  830 */               .addGroup(layout.createSequentialGroup()
/*  831 */                 .addComponent(this.jButton19, -2, 60, -2)
/*  832 */                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  833 */                 .addComponent(this.bq_lian_jie1))))
/*  834 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  835 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  836 */             .addComponent(this.hua_ban1, -1, -1, 32767)
/*  837 */             .addComponent(this.jPanel2, -1, 597, 32767))
/*  838 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  839 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  840 */             .addComponent(this.jdt, -2, -1, -2)
/*  841 */             .addComponent(this.jLabel6))));
/*      */ 
/*      */     
/*  844 */     pack();
/*      */   }
/*      */   private JButton jButton6; private JButton jButton7; private JButton jButton8; private JCheckBox jCheckBox1; private JCheckBox jCheckBox2; private JComboBox<String> jComboBox1; private JDialog jDialog1; private JLabel jLabel1; private JLabel jLabel10; private JLabel jLabel11; private JLabel jLabel13; private JLabel jLabel14; private JLabel jLabel16; private JLabel jLabel2; private JLabel jLabel3; private JLabel jLabel4; private JLabel jLabel5; private JLabel jLabel6; private JLabel jLabel7; private JLabel jLabel8; private JLabel jLabel9; private JPanel jPanel1; private JPanel jPanel2; private JSlider jSlider1; private JSlider jSlider2; private JSlider jSlider6; private JSlider jSlider7; private JTextField jTextField1; private JTextField jTextField2; private JTextField jTextField3; private JTextField jTextField4; private JProgressBar jdt;
/*      */   public void up() {
/*  848 */     this.hua_ban1.repaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void yu_yan() {
/*  856 */     Locale lo = Locale.getDefault();
/*      */ 
/*      */ 
/*      */     
/*  860 */     if (lo.getLanguage() == Locale.CHINA.getLanguage()) {
/*      */       
/*  862 */       if (lo == Locale.TRADITIONAL_CHINESE)
/*  863 */       { bundle = ResourceBundle.getBundle("examples.diao_CN_T"); }
/*      */       else
/*  865 */       { bundle = ResourceBundle.getBundle("examples.diao_zh_CN"); } 
/*  866 */     } else if (lo.getLanguage() == Locale.US.getLanguage()) {
/*      */       
/*  868 */       bundle = ResourceBundle.getBundle("examples.diao_en_US");
/*  869 */     } else if (lo.getLanguage() == Locale.JAPAN.getLanguage() || lo.getLanguage() == Locale.JAPANESE.getLanguage()) {
/*      */       
/*  871 */       bundle = ResourceBundle.getBundle("examples.diao_JP");
/*      */     } else {
/*      */       
/*  874 */       bundle = ResourceBundle.getBundle("examples.diao_");
/*      */     } 
/*  876 */     this.str_da_kai = bundle.getString("str_da_kai");
/*  877 */     this.str_wen_zi = bundle.getString("str_wen_zi");
/*  878 */     this.str_yuan = bundle.getString("str_yuan");
/*  879 */     this.str_fang = bundle.getString("str_fang");
/*  880 */     this.str_xin = bundle.getString("str_xin");
/*  881 */     this.str_xing = bundle.getString("str_xing");
/*  882 */     this.str_bao_cun = bundle.getString("str_bao_cun");
/*  883 */     this.str_shi_zi = bundle.getString("str_shi_zi");
/*  884 */     this.str_yu_lan = bundle.getString("str_yu_lan");
/*  885 */     this.str_kai_shi = bundle.getString("str_kai_shi");
/*  886 */     this.str_ting_zhi = bundle.getString("str_ting_zhi");
/*  887 */     this.str_lian_jie = bundle.getString("str_lian_jie");
/*  888 */     this.str_ruo_guang = bundle.getString("str_ruo_guang");
/*  889 */     this.str_gong_lv = bundle.getString("str_gong_lv");
/*  890 */     this.str_shen_du = bundle.getString("str_shen_du");
/*  891 */     this.str_gong_lv_sl = bundle.getString("str_gong_lv_sl");
/*  892 */     this.str_shen_du_sl = bundle.getString("str_shen_du_sl");
/*  893 */     this.str_ci_shu = bundle.getString("str_ci_shu");
/*  894 */     this.str_jing_du = bundle.getString("str_jing_du");
/*  895 */     this.str_gao = bundle.getString("str_gao");
/*  896 */     this.str_zhong = bundle.getString("str_zhong");
/*  897 */     this.str_di = bundle.getString("str_di");
/*      */     
/*  899 */     str_zi_ti = bundle.getString("str_zi_ti");
/*  900 */     str_zi_xing = bundle.getString("str_zi_xing");
/*  901 */     str_chi_cun = bundle.getString("str_chi_cun");
/*  902 */     str_chang_gui = bundle.getString("str_chang_gui");
/*  903 */     str_xie_ti = bundle.getString("str_xie_ti");
/*  904 */     str_cu_ti = bundle.getString("str_cu_ti");
/*  905 */     str_cu_xie = bundle.getString("str_cu_xie");
/*  906 */     str_shu = bundle.getString("str_shu");
/*  907 */     str_shi_liang = bundle.getString("str_shi_liang");
/*  908 */     str_geng_xin = bundle.getString("str_geng_xin");
/*  909 */     str_shi_zhi = bundle.getString("str_shi_zhi");
/*  910 */     str_gu_jian = bundle.getString("str_gu_jian");
/*  911 */     str_xing_hao = bundle.getString("str_xing_hao");
/*  912 */     str_kai_shi_geng_xin = bundle.getString("str_kai_shi_geng_xin");
/*  913 */     str_xia_zai_shi_bai = bundle.getString("str_xia_zai_shi_bai");
/*  914 */     str_liu_shui_hao = bundle.getString("str_liu_shui_hao");
/*  915 */     str_qian_zhui = bundle.getString("str_qian_zhui");
/*  916 */     str_hou_zhui = bundle.getString("str_hou_zhui");
/*  917 */     str_qi_shi = bundle.getString("str_qi_shi");
/*  918 */     str_jie_shu = bundle.getString("str_jie_shu");
/*  919 */     this.jButton1.setToolTipText(this.str_da_kai);
/*  920 */     this.bq_da_kai.setText(this.str_da_kai);
/*  921 */     this.jButton7.setToolTipText(this.str_wen_zi);
/*  922 */     this.bq_wen_zi.setText(this.str_wen_zi);
/*      */     
/*  924 */     this.jButton13.setToolTipText(bundle.getString("str_er_wei_ma"));
/*  925 */     this.bq_er_wei_ma.setText(bundle.getString("str_er_wei_ma"));
/*  926 */     this.jButton16.setToolTipText(bundle.getString("str_tiao_xing_ma"));
/*  927 */     this.bq_tiao_ma.setText(bundle.getString("str_tiao_xing_ma"));
/*  928 */     this.jButton20.setToolTipText(bundle.getString("str_tu_xing"));
/*  929 */     this.bq_tu_xing.setText(bundle.getString("str_tu_xing"));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  939 */     this.jButton12.setToolTipText(this.str_bao_cun);
/*  940 */     this.bq_bao_cun.setText(this.str_bao_cun);
/*  941 */     this.jButton14.setToolTipText(this.str_yu_lan);
/*      */ 
/*      */     
/*  944 */     this.jButton15.setToolTipText(this.str_kai_shi);
/*      */     
/*  946 */     this.jButton17.setToolTipText(this.str_ting_zhi);
/*      */     
/*  948 */     this.jButton18.setToolTipText(this.str_lian_jie);
/*  949 */     this.bq_lian_jie.setText(this.str_lian_jie);
/*      */ 
/*      */ 
/*      */     
/*  953 */     this.jButton22.setToolTipText(bundle.getString("str_shi_zhi"));
/*  954 */     this.bq_bao_cun1.setText(bundle.getString("str_shi_zhi"));
/*      */     
/*  956 */     this.but_hei_bai.setText(bundle.getString("str_hei_bai"));
/*  957 */     this.but_hei_bai.setToolTipText(bundle.getString("str_hei_bai"));
/*  958 */     this.but_hui_du.setText(bundle.getString("str_hui_du"));
/*  959 */     this.but_hui_du.setToolTipText(bundle.getString("str_hui_du"));
/*  960 */     this.but_lun_kuo.setText(bundle.getString("str_lun_kuo"));
/*  961 */     this.but_lun_kuo.setToolTipText(bundle.getString("str_lun_kuo"));
/*  962 */     this.but_su_miao.setText(bundle.getString("str_su_miao"));
/*  963 */     this.but_su_miao.setToolTipText(bundle.getString("str_su_miao"));
/*  964 */     this.jButton4.setText(bundle.getString("str_fan_se"));
/*  965 */     this.jButton4.setToolTipText(bundle.getString("str_fan_se"));
/*  966 */     this.jButton5.setText(bundle.getString("str_x_jing_xiang"));
/*  967 */     this.jButton5.setToolTipText(bundle.getString("str_x_jing_xiang"));
/*  968 */     this.jButton6.setText(bundle.getString("str_y_jing_xiang"));
/*  969 */     this.jButton6.setToolTipText(bundle.getString("str_y_jing_xiang"));
/*  970 */     this.jButton8.setText(bundle.getString("str_dao_zhong_xin"));
/*  971 */     this.jButton8.setToolTipText(bundle.getString("str_dao_zhong_xin"));
/*  972 */     this.jCheckBox2.setText(bundle.getString("str_tian_chong_xing_zhuang"));
/*  973 */     this.jCheckBox2.setToolTipText(bundle.getString("str_tian_chong_xing_zhuang"));
/*      */     
/*  975 */     this.jButton21.setText(bundle.getString("str_mo_ni"));
/*  976 */     this.jButton14.setText(bundle.getString("str_yu_lan"));
/*  977 */     this.jButton15.setText(bundle.getString("str_kai_shi"));
/*  978 */     this.jButton17.setText(bundle.getString("str_ting_zhi"));
/*      */ 
/*      */     
/*  981 */     this.jLabel5.setText(this.str_gong_lv + "100%");
/*  982 */     this.jLabel13.setText(this.str_shen_du + "90%");
/*      */     
/*  984 */     this.jLabel7.setText(this.str_ci_shu);
/*  985 */     this.jLabel7.setToolTipText(this.str_ci_shu);
/*  986 */     this.jComboBox1.setToolTipText(this.str_ci_shu);
/*  987 */     this.jLabel14.setText(bundle.getString("str_dui_bi") + "50%");
/*  988 */     this.jLabel16.setText(bundle.getString("str_tian_chong") + "5");
/*  989 */     setTitle(bundle.getString("str_ji_guang") + " " + ban_ben);
/*  990 */     this.jLabel10.setText(bundle.getString("str_kuan_du"));
/*  991 */     this.jLabel11.setText(bundle.getString("str_gao_du"));
/*  992 */     this.jCheckBox1.setText(bundle.getString("str_suo_ding"));
/*  993 */     this.hua_ban1.setToolTipText(bundle.getString("str_hua_ban"));
/*  994 */     this.bq_lian_jie1.setText(bundle.getString("str_bang_zhu"));
/*  995 */     this.jButton19.setToolTipText(bundle.getString("str_bang_zhu"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void cai_dan() {
/* 1022 */     JMenuItem menuItem1 = new JMenuItem(bundle.getString("str_yuan"), new ImageIcon(getClass().getResource("/tu/yuan.png")));
/* 1023 */     JMenuItem menuItem2 = new JMenuItem(bundle.getString("str_fang"), new ImageIcon(getClass().getResource("/tu/fang.png")));
/* 1024 */     JMenuItem menuItem3 = new JMenuItem(bundle.getString("str_xin"), new ImageIcon(getClass().getResource("/tu/xin.png")));
/* 1025 */     JMenuItem menuItem4 = new JMenuItem(bundle.getString("str_xing"), new ImageIcon(getClass().getResource("/tu/5xing.png")));
/* 1026 */     menuItem1.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/* 1029 */             Hua_ban.ty_shuzu.add(Tu_yuan.chuang_jian(2, null));
/* 1030 */             for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++)
/*      */             {
/* 1032 */               ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong = false;
/*      */             }
/* 1034 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(Hua_ban.ty_shuzu.size() - 1)).xuan_zhong = true;
/*      */             
/* 1036 */             Tu_yuan.zhong_xin(Hua_ban.ty_shuzu);
/* 1037 */             Che_xiao.tian_jia();
/* 1038 */             mainJFrame.this.up();
/*      */           }
/*      */         });
/* 1041 */     menuItem2.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/* 1044 */             Hua_ban.ty_shuzu.add(Tu_yuan.chuang_jian(0, null));
/* 1045 */             for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++)
/*      */             {
/* 1047 */               ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong = false;
/*      */             }
/* 1049 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(Hua_ban.ty_shuzu.size() - 1)).xuan_zhong = true;
/*      */             
/* 1051 */             Tu_yuan.zhong_xin(Hua_ban.ty_shuzu);
/* 1052 */             Che_xiao.tian_jia();
/* 1053 */             mainJFrame.this.up();
/*      */           }
/*      */         });
/* 1056 */     menuItem3.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/* 1059 */             Hua_ban.ty_shuzu.add(Tu_yuan.chuang_jian(3, null));
/* 1060 */             for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++)
/*      */             {
/* 1062 */               ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong = false;
/*      */             }
/* 1064 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(Hua_ban.ty_shuzu.size() - 1)).xuan_zhong = true;
/*      */             
/* 1066 */             Tu_yuan.zhong_xin(Hua_ban.ty_shuzu);
/* 1067 */             Che_xiao.tian_jia();
/* 1068 */             mainJFrame.this.up();
/*      */           }
/*      */         });
/* 1071 */     menuItem4.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/* 1074 */             Hua_ban.ty_shuzu.add(Tu_yuan.chuang_jian(4, null));
/* 1075 */             for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++)
/*      */             {
/* 1077 */               ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong = false;
/*      */             }
/* 1079 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(Hua_ban.ty_shuzu.size() - 1)).xuan_zhong = true;
/*      */             
/* 1081 */             Tu_yuan.zhong_xin(Hua_ban.ty_shuzu);
/* 1082 */             Che_xiao.tian_jia();
/* 1083 */             mainJFrame.this.up();
/*      */           }
/*      */         });
/*      */     
/* 1087 */     this.popMenu.add(menuItem1);
/* 1088 */     this.popMenu.add(menuItem2);
/* 1089 */     this.popMenu.add(menuItem3);
/* 1090 */     this.popMenu.add(menuItem4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void formWindowOpened(WindowEvent evt) {
/*      */     try {
/* 1145 */       Geng_xin.geng_xin();
/* 1146 */       FileTransferHandler ft = new FileTransferHandler();
/* 1147 */       FileTransferHandler.hb = this.hua_ban1;
/* 1148 */       this.hua_ban1.setTransferHandler(ft);
/* 1149 */       yu_yan();
/* 1150 */       cai_dan();
/* 1151 */       Tu_yuan ty = new Tu_yuan();
/* 1152 */       ty.lei_xing = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1163 */       Hua_ban.ty_shuzu.add(ty);
/* 1164 */       Quan_ju.diao_ke_ji.kuan_du = 80;
/* 1165 */       Quan_ju.diao_ke_ji.gao_du = 75;
/* 1166 */       Quan_ju.diao_ke_ji.fen_bian_lv = 0.05D;
/* 1167 */       Quan_ju.diao_ke_ji.ji_xing = 0;
/* 1168 */       this.hua_ban1.di_tu();
/* 1169 */       UI_();
/* 1170 */       up();
/*      */       
/* 1172 */       setIconImage((new ImageIcon(getClass().getResource("/tu/tu_biao.png"))).getImage());
/* 1173 */       this.jButton21.setVisible(false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1179 */       this.hua_ban1.win2 = this;
/* 1180 */       this.hua_ban1.jp = this.jPanel1;
/* 1181 */       this.hua_ban1.win = this.jPanel2;
/*      */       
/* 1183 */       this.hua_ban1.wb1 = this.jTextField1;
/* 1184 */       this.hua_ban1.wb2 = this.jTextField2;
/* 1185 */       this.hua_ban1.wb3 = this.jTextField3;
/* 1186 */       this.hua_ban1.wb4 = this.jTextField4;
/*      */       
/* 1188 */       this.jdt.setVisible(false);
/* 1189 */       this.jTextField1.addKeyListener(this);
/* 1190 */       this.jTextField2.addKeyListener(this);
/* 1191 */       this.jTextField3.addKeyListener(this);
/* 1192 */       this.jTextField4.addKeyListener(this);
/* 1193 */       this.jTextField1.requestFocus();
/*      */       
/* 1195 */       getContentPane().setBackground(new Color(240, 240, 240));
/* 1196 */       this.jPanel2.setBackground(new Color(240, 240, 240));
/* 1197 */       Hua_ban.suo = true;
/* 1198 */       this.jCheckBox1.setSelected(Hua_ban.suo);
/* 1199 */       this.win = this;
/* 1200 */       qu_yu();
/* 1201 */     } catch (Exception ex) {
/* 1202 */       JOptionPane.showMessageDialog(null, ex);
/* 1203 */       Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*      */     } 
/* 1205 */     Runnable runnable2 = new Runnable()
/*      */       {
/*      */         public void run() {
/*      */           while (true) {
/* 1209 */             SwingUtilities.invokeLater(new Runnable()
/*      */                 {
/*      */                   public void run()
/*      */                   {
/* 1213 */                     if (mainJFrame.this.ji_xing != Quan_ju.diao_ke_ji.ji_xing) {
/*      */                       
/* 1215 */                       mainJFrame.this.ji_xing = Quan_ju.diao_ke_ji.ji_xing;
/* 1216 */                       mainJFrame.this.hua_ban1.di_tu();
/* 1217 */                       mainJFrame.this.UI_();
/* 1218 */                       mainJFrame.this.qu_yu();
/*      */                     } 
/* 1220 */                     if (Quan_ju.jin_du > 0) {
/*      */                       
/* 1222 */                       mainJFrame.this.jdt.setValue(Quan_ju.jin_du);
/* 1223 */                       mainJFrame.this.jdt.setVisible(true);
/*      */                     } else {
/*      */                       
/* 1226 */                       mainJFrame.this.jdt.setValue(0);
/* 1227 */                       mainJFrame.this.jdt.setVisible(false);
/*      */                     } 
/* 1229 */                     if (!Quan_ju.qu_diao_ke().booleanValue()) {
/*      */                       
/* 1231 */                       Quan_ju.jin_du = 0;
/*      */                     }
/*      */                     else {
/*      */                       
/* 1235 */                       Quan_ju.jin_du = Quan_ju.diao_ke_ji.jin_du;
/*      */                     } 
/* 1237 */                     if (Quan_ju.she_bei.yi_lian_jie.booleanValue()) {
/*      */                       
/* 1239 */                       mainJFrame.this.jButton18.setIcon(new ImageIcon(getClass().getResource("/tu/usb.png")));
/*      */                     } else {
/*      */                       
/* 1242 */                       mainJFrame.this.jButton18.setIcon(new ImageIcon(getClass().getResource("/tu/usb_1.png")));
/*      */                     } 
/*      */                   }
/*      */                 });
/*      */             
/*      */             try {
/* 1248 */               Thread.sleep(200L);
/* 1249 */             } catch (InterruptedException ex) {
/* 1250 */               Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*      */             } 
/*      */           } 
/*      */         }
/*      */       };
/* 1255 */     Thread lj = new Thread(runnable2);
/* 1256 */     lj.start();
/* 1257 */     Runnable runnable3 = new Runnable()
/*      */       {
/*      */         public void run() {
/*      */           while (true) {
/* 1261 */             if (Quan_ju.qu_diao_ke().booleanValue()) {
/*      */               
/* 1263 */               if (!Quan_ju.qu_zan_ting().booleanValue())
/* 1264 */                 mainJFrame.this.shi_jian++; 
/* 1265 */               int fen = mainJFrame.this.shi_jian / 60;
/* 1266 */               int miao = mainJFrame.this.shi_jian % 60;
/* 1267 */               mainJFrame.this.jLabel6.setText(String.valueOf(fen) + "." + String.valueOf(miao));
/*      */             } 
/*      */             try {
/* 1270 */               Thread.sleep(1000L);
/* 1271 */             } catch (InterruptedException ex) {
/* 1272 */               Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*      */             } 
/*      */           } 
/*      */         }
/*      */       };
/* 1277 */     Thread lj2 = new Thread(runnable3);
/* 1278 */     lj2.start();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void ni() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void jButton1MouseClicked(MouseEvent evt) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void jButton7MouseClicked(MouseEvent evt) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int qu_anniu(int x, int y) {
/* 1370 */     Rectangle rect = Tu_yuan.qu_jv_xing(Hua_ban.ty_shuzu);
/* 1371 */     if (x > rect.x - 15 && x < rect.x + 15 && y > rect.y - 15 && y < rect.y + 15)
/*      */     {
/* 1373 */       return 1; } 
/* 1374 */     if (x > rect.x + rect.width - 15 && x < rect.x + rect.width + 15 && y > rect.y - 15 && y < rect.y + 15)
/*      */     {
/* 1376 */       return 2; } 
/* 1377 */     if (x > rect.x + rect.width - 15 && x < rect.x + rect.width + 15 && y > rect.y + rect.height - 15 && y < rect.y + rect.height + 15)
/*      */     {
/* 1379 */       return 3; } 
/* 1380 */     if (x > rect.x - 15 && x < rect.x + 15 && y > rect.y + rect.height - 15 && y < rect.y + rect.height + 15)
/*      */     {
/* 1382 */       return 4; } 
/* 1383 */     if (x > rect.x + rect.width - 15 && x < rect.x + rect.width + 15 && y > rect.y + rect.height + 20 && y < rect.y + rect.height + 50)
/*      */     {
/* 1385 */       return 5; } 
/* 1386 */     if (x > rect.x + rect.width + 25 && x < rect.x + rect.width + 25 + 60 && y > rect.y - 20 && y < rect.y - 20 + 65) {
/*      */       
/* 1388 */       System.out.println(6);
/* 1389 */       return 6;
/* 1390 */     }  if (x > rect.x + rect.width + 25 && x < rect.x + rect.width + 25 + 60 && y > rect.y + 45 && y < rect.y + 45 + 65) {
/*      */       
/* 1392 */       System.out.println(7);
/* 1393 */       return 7;
/* 1394 */     }  if (x > rect.x + rect.width + 25 && x < rect.x + rect.width + 25 + 60 && y > rect.y + 110 && y < rect.y + 110 + 65) {
/*      */       
/* 1396 */       System.out.println(8);
/* 1397 */       return 8;
/* 1398 */     }  if (x > rect.x + rect.width + 25 && x < rect.x + rect.width + 25 + 60 && y > rect.y + 175 && y < rect.y + 175 + 65) {
/*      */       
/* 1400 */       System.out.println(9);
/* 1401 */       return 9;
/* 1402 */     }  if (x > rect.x + rect.width - 50 && x < rect.x + rect.width + 50 && y > rect.y + rect.height + 20 && y < rect.y + rect.height + 50)
/*      */     {
/* 1404 */       return 10; } 
/* 1405 */     if (x > rect.x + rect.width - 85 && x < rect.x + rect.width + 85 && y > rect.y + rect.height + 20 && y < rect.y + rect.height + 50)
/*      */     {
/* 1407 */       return 11; } 
/* 1408 */     if (x > rect.x + rect.width - 120 && x < rect.x + rect.width + 120 && y > rect.y + rect.height + 20 && y < rect.y + rect.height + 50)
/*      */     {
/* 1410 */       return 12; } 
/* 1411 */     if (x > rect.x + rect.width - 155 && x < rect.x + rect.width + 155 && y > rect.y + rect.height + 20 && y < rect.y + rect.height + 50)
/*      */     {
/* 1413 */       return 13;
/*      */     }
/*      */     
/* 1416 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void mo_shi(int m) {
/* 1565 */     switch (m) {
/*      */       
/*      */       case 1:
/* 1568 */         this.but_hei_bai.setSelected(true);
/* 1569 */         this.but_hui_du.setSelected(false);
/* 1570 */         this.but_lun_kuo.setSelected(false);
/* 1571 */         this.but_su_miao.setSelected(false);
/*      */         break;
/*      */       case 2:
/* 1574 */         this.but_hei_bai.setSelected(false);
/* 1575 */         this.but_hui_du.setSelected(true);
/* 1576 */         this.but_lun_kuo.setSelected(false);
/* 1577 */         this.but_su_miao.setSelected(false);
/*      */         break;
/*      */       case 3:
/* 1580 */         this.but_hei_bai.setSelected(false);
/* 1581 */         this.but_hui_du.setSelected(false);
/* 1582 */         this.but_lun_kuo.setSelected(true);
/* 1583 */         this.but_su_miao.setSelected(false);
/*      */         break;
/*      */       case 4:
/* 1586 */         this.but_hei_bai.setSelected(false);
/* 1587 */         this.but_hui_du.setSelected(false);
/* 1588 */         this.but_lun_kuo.setSelected(false);
/* 1589 */         this.but_su_miao.setSelected(true);
/*      */         break;
/*      */     } 
/* 1592 */     for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */       
/* 1594 */       if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong)
/*      */       {
/* 1596 */         if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).lei_xing == 1) {
/*      */           
/* 1598 */           ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).chuli_fs = m;
/* 1599 */           ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).chu_li();
/* 1600 */           up();
/* 1601 */           Che_xiao.tian_jia();
/*      */           return;
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   void jing_xiang_xy(boolean xx) {
/* 1609 */     for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */       
/* 1611 */       if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong) {
/*      */ 
/*      */ 
/*      */         
/* 1615 */         if (xx) {
/* 1616 */           ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).chuli_jxx = !((Tu_yuan)Hua_ban.ty_shuzu.get(i)).chuli_jxx;
/*      */         } else {
/* 1618 */           ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).chuli_jxy = !((Tu_yuan)Hua_ban.ty_shuzu.get(i)).chuli_jxy;
/* 1619 */         }  ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).chu_li();
/* 1620 */         up();
/* 1621 */         Che_xiao.tian_jia();
/*      */         return;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void fan_se() {
/* 1629 */     for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */       
/* 1631 */       if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong)
/*      */       {
/* 1633 */         if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).lei_xing == 1) {
/*      */           
/* 1635 */           ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).chuli_fan = !((Tu_yuan)Hua_ban.ty_shuzu.get(i)).chuli_fan;
/* 1636 */           ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).chu_li();
/* 1637 */           up();
/* 1638 */           Che_xiao.tian_jia();
/*      */           return;
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   void tian_chong() {
/* 1646 */     Rectangle rect_q = Tu_yuan.qu_jv_xing(Hua_ban.ty_shuzu);
/* 1647 */     Tu_yuan ty = Tu_yuan.chuang_jian(0, null);
/* 1648 */     ty.lu_jing = new GeneralPath();
/* 1649 */     int ge_shu = 0, wei_zhi = 0;
/* 1650 */     boolean tc = false;
/* 1651 */     for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */       
/* 1653 */       if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong)
/*      */       {
/* 1655 */         if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).lei_xing == 0 && !((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wen_zi) {
/*      */           
/* 1657 */           GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).lu_jing);
/* 1658 */           lu_jing2.transform(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).Tx);
/* 1659 */           ty.lu_jing.append(lu_jing2, false);
/* 1660 */           ge_shu++;
/* 1661 */           wei_zhi = i;
/* 1662 */           tc = true;
/*      */         } 
/*      */       }
/*      */     } 
/* 1666 */     if (ge_shu == 1) {
/* 1667 */       ty.tian_chong = !((Tu_yuan)Hua_ban.ty_shuzu.get(wei_zhi)).tian_chong;
/*      */     } else {
/* 1669 */       ty.tian_chong = true;
/* 1670 */     }  this.jCheckBox2.setSelected(ty.tian_chong);
/* 1671 */     List<Tu_yuan> sz = new ArrayList<>();
/* 1672 */     for (int j = 0; j < Hua_ban.ty_shuzu.size(); j++) {
/*      */       
/* 1674 */       if (!((Tu_yuan)Hua_ban.ty_shuzu.get(j)).xuan_zhong || ((Tu_yuan)Hua_ban.ty_shuzu.get(j)).lei_xing == 1 || ((Tu_yuan)Hua_ban.ty_shuzu.get(j)).wen_zi)
/*      */       {
/* 1676 */         sz.add(Hua_ban.ty_shuzu.get(j));
/*      */       }
/*      */     } 
/* 1679 */     Hua_ban.ty_shuzu = sz;
/* 1680 */     ty.xuan_zhong = true;
/*      */     
/* 1682 */     Rectangle r = Tu_yuan.qu_jv_xing(ty);
/*      */ 
/*      */     
/* 1685 */     AffineTransform sf1 = AffineTransform.getTranslateInstance(-r.x, -r.y);
/* 1686 */     AffineTransform fb = new AffineTransform(sf1);
/* 1687 */     fb.concatenate(ty.Tx);
/* 1688 */     ty.Tx = fb;
/*      */     
/* 1690 */     AffineTransform sf3 = AffineTransform.getScaleInstance(rect_q.width / r.width, rect_q.height / r.height);
/* 1691 */     AffineTransform fb3 = new AffineTransform(sf3);
/* 1692 */     fb3.concatenate(ty.Tx);
/* 1693 */     ty.Tx = fb3;
/*      */     
/* 1695 */     AffineTransform sf2 = AffineTransform.getTranslateInstance(rect_q.x, rect_q.y);
/* 1696 */     AffineTransform fb2 = new AffineTransform(sf2);
/* 1697 */     fb2.concatenate(ty.Tx);
/* 1698 */     ty.Tx = fb2;
/* 1699 */     if (tc)
/* 1700 */       Hua_ban.ty_shuzu.add(ty); 
/* 1701 */     Che_xiao.tian_jia();
/* 1702 */     up();
/*      */   }
/*      */   
/*      */   void shu_an_xia2(MouseEvent evt) {
/* 1706 */     this.an_xia = true;
/* 1707 */     this.anxia_x = evt.getX();
/* 1708 */     this.anxia_y = evt.getY();
/* 1709 */     this.anxia_x_1 = this.anxia_x;
/* 1710 */     this.anxia_y_1 = this.anxia_y;
/* 1711 */     this.an_niu = evt.getButton();
/*      */     
/* 1713 */     if (this.an_niu == 1) {
/*      */       
/* 1715 */       this.an = qu_anniu(this.anxia_x, this.anxia_y);
/* 1716 */       if (this.an == 1) {
/*      */         
/* 1718 */         List<Tu_yuan> sz = new ArrayList<>();
/* 1719 */         for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */           
/* 1721 */           if (!((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong)
/*      */           {
/* 1723 */             sz.add(Hua_ban.ty_shuzu.get(i));
/*      */           }
/*      */         } 
/* 1726 */         Hua_ban.ty_shuzu = sz;
/* 1727 */         up();
/* 1728 */         Che_xiao.tian_jia(); return;
/*      */       } 
/* 1730 */       if (this.an != 4)
/*      */       {
/*      */ 
/*      */ 
/*      */         
/* 1735 */         if (this.an != 5)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1741 */           if (this.an != 6)
/*      */           {
/*      */ 
/*      */             
/* 1745 */             if (this.an != 7)
/*      */             {
/*      */ 
/*      */               
/* 1749 */               if (this.an != 8)
/*      */               {
/*      */ 
/*      */                 
/* 1753 */                 if (this.an != 9) {
/*      */ 
/*      */ 
/*      */                   
/* 1757 */                   if (this.an == 2) {
/*      */                     return;
/*      */                   }
/* 1760 */                   if (this.an == 3) {
/*      */                     return;
/*      */                   }
/* 1763 */                   if (this.an != 10)
/*      */                   {
/*      */ 
/*      */                     
/* 1767 */                     if (this.an != 11)
/*      */                     {
/*      */ 
/*      */                       
/* 1771 */                       if (this.an != 12)
/*      */                       {
/*      */ 
/*      */                         
/* 1775 */                         if (this.an == 13); }  }  } 
/*      */                 }  }  } 
/*      */           }
/*      */         }
/*      */       }
/* 1780 */       Rectangle rect_q = Tu_yuan.qu_jv_xing(Hua_ban.ty_shuzu);
/* 1781 */       if (this.anxia_x > rect_q.x && this.anxia_x < rect_q.x + rect_q.width && this.anxia_y > rect_q.y && this.anxia_y < rect_q.y + rect_q.height) {
/*      */         
/* 1783 */         this.kuang = false;
/* 1784 */         up();
/*      */       } else {
/*      */         
/* 1787 */         for (int i = 1; i < Hua_ban.ty_shuzu.size(); i++)
/*      */         {
/* 1789 */           if (!((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong) {
/*      */             
/* 1791 */             GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).lu_jing);
/* 1792 */             lu_jing2.transform(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).Tx);
/* 1793 */             Rectangle rect = lu_jing2.getBounds();
/* 1794 */             if (this.anxia_x > rect.x && this.anxia_x < rect.x + rect.width && this.anxia_y > rect.y && this.anxia_y < rect.y + rect.height) {
/*      */               
/* 1796 */               for (int j = 0; j < Hua_ban.ty_shuzu.size(); j++)
/*      */               {
/* 1798 */                 ((Tu_yuan)Hua_ban.ty_shuzu.get(j)).xuan_zhong = false;
/*      */               }
/* 1800 */               ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong = true;
/* 1801 */               Tu_yuan ty = Hua_ban.ty_shuzu.get(i);
/* 1802 */               Hua_ban.ty_shuzu.remove(i);
/*      */               
/* 1804 */               Hua_ban.ty_shuzu.add(1, ty);
/* 1805 */               this.jSlider6.setValue(ty.yu_zhi);
/* 1806 */               up();
/* 1807 */               this.kuang = false;
/*      */               return;
/*      */             } 
/*      */           } 
/* 1811 */           for (int ii = 0; ii < Hua_ban.ty_shuzu.size(); ii++)
/*      */           {
/* 1813 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(ii)).xuan_zhong = false;
/*      */           }
/* 1815 */           this.kuang = true;
/* 1816 */           up();
/*      */         }
/*      */       
/*      */       }
/*      */     
/* 1821 */     } else if (this.an_niu == 3) {
/*      */     
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void hua_ban1MousePressed(MouseEvent evt) {
/* 1830 */     shu_an_xia2(evt);
/* 1831 */     this.jTextField1.requestFocus(true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void hua_ban1MouseReleased(MouseEvent evt) {
/* 1839 */     this.an_xia = false;
/* 1840 */     Tu_yuan.tuo = false;
/* 1841 */     if (Quan_ju.qu_yu_lan().booleanValue()) {
/*      */       
/* 1843 */       Runnable runnable2 = new Runnable() {
/*      */           public void run() {
/* 1845 */             Tu_yuan.qu_jvxing(Hua_ban.ty_shuzu);
/* 1846 */             GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)Hua_ban.ty_shuzu.get(0)).lu_jing);
/* 1847 */             lu_jing2.transform(((Tu_yuan)Hua_ban.ty_shuzu.get(0)).Tx);
/* 1848 */             Rectangle rect = lu_jing2.getBounds();
/* 1849 */             Rectangle zui_zhong_wjx2 = new Rectangle(Tu_yuan.zui_zhong_wjx);
/* 1850 */             AffineTransform sf = AffineTransform.getTranslateInstance((0 - rect.x), (0 - rect.y));
/* 1851 */             zui_zhong_wjx2 = sf.createTransformedShape(zui_zhong_wjx2).getBounds();
/* 1852 */             sf = AffineTransform.getScaleInstance(1.0D / Hua_ban.quan_beishu, 1.0D / Hua_ban.quan_beishu);
/* 1853 */             zui_zhong_wjx2 = sf.createTransformedShape(zui_zhong_wjx2).getBounds();
/* 1854 */             if (zui_zhong_wjx2.width < 2 && zui_zhong_wjx2.height < 2) {
/*      */               return;
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1867 */             Quan_ju.she_bei.ming_ling_yu_lan(zui_zhong_wjx2.x + zui_zhong_wjx2.width / 2, zui_zhong_wjx2.y + zui_zhong_wjx2.height / 2, zui_zhong_wjx2.width, zui_zhong_wjx2.height);
/* 1868 */             mainJFrame.this.up();
/*      */           }
/*      */         };
/* 1871 */       Thread thread2 = new Thread(runnable2);
/* 1872 */       thread2.start();
/*      */     } 
/* 1874 */     if (this.tuo_dong) {
/*      */       
/* 1876 */       this.tuo_dong = false;
/* 1877 */       Che_xiao.tian_jia();
/*      */     } 
/* 1879 */     this.hua_ban1.xuan_zhong_lei_xing();
/* 1880 */     if (this.hua_ban1.LeiXing_sl && this.hua_ban1.LeiXing_tp) {
/*      */       
/* 1882 */       this.but_hei_bai.setEnabled(true);
/* 1883 */       this.but_hui_du.setEnabled(true);
/* 1884 */       this.but_lun_kuo.setEnabled(true);
/* 1885 */       this.but_su_miao.setEnabled(true);
/* 1886 */       this.jCheckBox1.setEnabled(true);
/* 1887 */       this.jButton4.setEnabled(true);
/* 1888 */       this.jButton5.setEnabled(true);
/* 1889 */       this.jButton6.setEnabled(true);
/* 1890 */       this.jButton8.setEnabled(true);
/* 1891 */       this.jCheckBox2.setEnabled(true);
/* 1892 */       this.jSlider7.setEnabled(true);
/* 1893 */     } else if (this.hua_ban1.LeiXing_sl && !this.hua_ban1.LeiXing_tp) {
/*      */       
/* 1895 */       this.but_hei_bai.setEnabled(false);
/* 1896 */       this.but_hui_du.setEnabled(false);
/* 1897 */       this.but_lun_kuo.setEnabled(false);
/* 1898 */       this.but_su_miao.setEnabled(false);
/* 1899 */       this.jCheckBox1.setEnabled(true);
/* 1900 */       this.jButton4.setEnabled(false);
/* 1901 */       this.jButton5.setEnabled(true);
/* 1902 */       this.jButton6.setEnabled(true);
/* 1903 */       this.jButton8.setEnabled(true);
/* 1904 */       this.jCheckBox2.setEnabled(true);
/* 1905 */       this.jSlider7.setEnabled(true);
/* 1906 */     } else if (!this.hua_ban1.LeiXing_sl && this.hua_ban1.LeiXing_tp) {
/*      */       
/* 1908 */       this.but_hei_bai.setEnabled(true);
/* 1909 */       this.but_hui_du.setEnabled(true);
/* 1910 */       this.but_lun_kuo.setEnabled(true);
/* 1911 */       this.but_su_miao.setEnabled(true);
/* 1912 */       this.jCheckBox1.setEnabled(true);
/* 1913 */       this.jButton4.setEnabled(true);
/* 1914 */       this.jButton5.setEnabled(true);
/* 1915 */       this.jButton6.setEnabled(true);
/* 1916 */       this.jButton8.setEnabled(true);
/* 1917 */       this.jCheckBox2.setEnabled(false);
/* 1918 */       this.jSlider7.setEnabled(false);
/* 1919 */     } else if (!this.hua_ban1.LeiXing_sl && !this.hua_ban1.LeiXing_tp) {
/*      */       
/* 1921 */       this.but_hei_bai.setEnabled(false);
/* 1922 */       this.but_hui_du.setEnabled(false);
/* 1923 */       this.but_lun_kuo.setEnabled(false);
/* 1924 */       this.but_su_miao.setEnabled(false);
/* 1925 */       this.jCheckBox1.setEnabled(false);
/* 1926 */       this.jButton4.setEnabled(false);
/* 1927 */       this.jButton5.setEnabled(true);
/* 1928 */       this.jButton6.setEnabled(true);
/* 1929 */       this.jButton8.setEnabled(false);
/* 1930 */       this.jCheckBox2.setEnabled(false);
/* 1931 */       this.jSlider7.setEnabled(false);
/*      */     } 
/* 1933 */     up();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void shu_yi_dong2(MouseEvent evt) {
/* 2044 */     if (this.an_xia) {
/*      */       
/* 2046 */       int dx = evt.getX(), dy = evt.getY();
/* 2047 */       if (this.an_niu == 1) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2052 */         Rectangle rect = Tu_yuan.qu_jv_xing(Hua_ban.ty_shuzu);
/* 2053 */         if (this.an == 0) {
/*      */           
/* 2055 */           if (this.kuang) {
/*      */             int x, y, w, g;
/*      */             
/* 2058 */             if (dx < this.anxia_x_1) {
/*      */               
/* 2060 */               x = dx; w = this.anxia_x_1 - dx;
/*      */             } else {
/*      */               
/* 2063 */               x = this.anxia_x_1; w = dx - this.anxia_x_1;
/*      */             } 
/* 2065 */             if (dy < this.anxia_y_1) {
/*      */               
/* 2067 */               y = dy; g = this.anxia_y_1 - dy;
/*      */             } else {
/*      */               
/* 2070 */               y = this.anxia_y_1; g = dy - this.anxia_y_1;
/*      */             } 
/* 2072 */             Tu_yuan.tuo = true;
/* 2073 */             Tu_yuan.shu_biao = new Rectangle(x, y, w, g);
/* 2074 */             Tu_yuan.kuang_xuan(Hua_ban.ty_shuzu, Tu_yuan.shu_biao);
/*      */           } else {
/*      */             
/* 2077 */             for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */               
/* 2079 */               if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong)
/*      */               {
/* 2081 */                 ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).ping_yi(dx - this.anxia_x, dy - this.anxia_y);
/*      */               }
/*      */             } 
/*      */           } 
/*      */           
/* 2086 */           this.anxia_x = dx;
/* 2087 */           this.anxia_y = dy;
/* 2088 */           up();
/* 2089 */         } else if (this.an == 2) {
/*      */           
/* 2091 */           float zhong_xin_x = 0.0F, zhong_xin_y = 0.0F;
/* 2092 */           float jiao1 = 0.0F, jiao2 = 0.0F;
/* 2093 */           zhong_xin_x = (rect.x + rect.width / 2);
/* 2094 */           zhong_xin_y = (rect.y + rect.height / 2);
/* 2095 */           if (this.anxia_x > zhong_xin_x && this.anxia_y < zhong_xin_y) {
/*      */             
/* 2097 */             jiao1 = 360.0F - (float)Math.toDegrees(Math.atan(((zhong_xin_y - this.anxia_y) / (this.anxia_x - zhong_xin_x))));
/* 2098 */           } else if (this.anxia_x < zhong_xin_x && this.anxia_y < zhong_xin_y) {
/*      */             
/* 2100 */             jiao1 = 270.0F - (float)Math.toDegrees(Math.atan(((zhong_xin_x - this.anxia_x) / (zhong_xin_y - this.anxia_y))));
/* 2101 */           } else if (this.anxia_x < zhong_xin_x && this.anxia_y > zhong_xin_y) {
/*      */             
/* 2103 */             jiao1 = 90.0F + (float)Math.toDegrees(Math.atan(((zhong_xin_x - this.anxia_x) / (this.anxia_y - zhong_xin_y))));
/* 2104 */           } else if (this.anxia_x > zhong_xin_x && this.anxia_y > zhong_xin_y) {
/*      */             
/* 2106 */             jiao1 = (float)Math.toDegrees(Math.atan(((this.anxia_y - zhong_xin_y) / (this.anxia_x - zhong_xin_x))));
/*      */           } 
/*      */           
/* 2109 */           if (dx > zhong_xin_x && dy < zhong_xin_y) {
/*      */             
/* 2111 */             jiao2 = 360.0F - (float)Math.toDegrees(Math.atan(((zhong_xin_y - dy) / (dx - zhong_xin_x))));
/* 2112 */             for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */               
/* 2114 */               if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong)
/*      */               {
/* 2116 */                 ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhuan((jiao2 - jiao1), zhong_xin_x, zhong_xin_y);
/*      */               }
/*      */             } 
/* 2119 */             this.anxia_x = dx; this.anxia_y = dy;
/* 2120 */           } else if (dx < zhong_xin_x && dy < zhong_xin_y) {
/*      */             
/* 2122 */             jiao2 = 270.0F - (float)Math.toDegrees(Math.atan(((zhong_xin_x - dx) / (zhong_xin_y - dy))));
/* 2123 */             for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */               
/* 2125 */               if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong)
/*      */               {
/* 2127 */                 ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhuan((jiao2 - jiao1), zhong_xin_x, zhong_xin_y);
/*      */               }
/*      */             } 
/* 2130 */             this.anxia_x = dx; this.anxia_y = dy;
/* 2131 */           } else if (dx < zhong_xin_x && dy > zhong_xin_y) {
/*      */             
/* 2133 */             jiao2 = 90.0F + (float)Math.toDegrees(Math.atan(((zhong_xin_x - dx) / (dy - zhong_xin_y))));
/* 2134 */             for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */               
/* 2136 */               if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong)
/*      */               {
/* 2138 */                 ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhuan((jiao2 - jiao1), zhong_xin_x, zhong_xin_y);
/*      */               }
/*      */             } 
/* 2141 */             this.anxia_x = dx; this.anxia_y = dy;
/* 2142 */           } else if (dx > zhong_xin_x && dy > zhong_xin_y) {
/*      */             
/* 2144 */             jiao2 = (float)Math.toDegrees(Math.atan(((dy - zhong_xin_y) / (dx - zhong_xin_x))));
/* 2145 */             for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */               
/* 2147 */               if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong)
/*      */               {
/* 2149 */                 ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhuan((jiao2 - jiao1), zhong_xin_x, zhong_xin_y);
/*      */               }
/*      */             } 
/* 2152 */             this.anxia_x = dx; this.anxia_y = dy;
/*      */           } 
/* 2154 */           up();
/* 2155 */         }  if (this.an == 3) {
/*      */           
/* 2157 */           if (Hua_ban.suo) {
/*      */             
/* 2159 */             double sf = (this.anxia_x - rect.x) / rect.width;
/* 2160 */             if (sf > 0.0D)
/*      */             {
/* 2162 */               for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */                 
/* 2164 */                 if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong) {
/*      */                   
/* 2166 */                   ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).ping_yi(-rect.x, -rect.y);
/* 2167 */                   ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).suo_fang(sf, sf);
/* 2168 */                   ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).ping_yi(rect.x, rect.y);
/*      */                 } 
/*      */               } 
/*      */             }
/*      */             
/* 2173 */             this.anxia_x = dx;
/*      */           } else {
/*      */             
/* 2176 */             double sf_x = (this.anxia_x - rect.x) / rect.width;
/* 2177 */             double sf_y = (this.anxia_y - rect.y) / rect.height;
/* 2178 */             if (sf_x > 0.0D && sf_y > 0.0D)
/*      */             {
/* 2180 */               for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */                 
/* 2182 */                 if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong) {
/*      */                   
/* 2184 */                   ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).ping_yi(-rect.x, -rect.y);
/* 2185 */                   ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).suo_fang(sf_x, sf_y);
/* 2186 */                   ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).ping_yi(rect.x, rect.y);
/*      */                 } 
/*      */               } 
/*      */             }
/*      */             
/* 2191 */             this.anxia_x = dx;
/* 2192 */             this.anxia_y = dy;
/*      */           } 
/* 2194 */           up();
/*      */         } 
/* 2196 */         this.tuo_dong = true;
/* 2197 */       } else if (this.an_niu == 3) {
/*      */         
/* 2199 */         AffineTransform py = AffineTransform.getTranslateInstance((dx - this.anxia_x), (dy - this.anxia_y));
/* 2200 */         Hua_ban.quan_x = (int)((Hua_ban.quan_x + dx - this.anxia_x) / Hua_ban.quan_beishu);
/* 2201 */         Hua_ban.quan_y = (int)((Hua_ban.quan_y + dy - this.anxia_y) / Hua_ban.quan_beishu);
/*      */         
/* 2203 */         for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */           
/* 2205 */           AffineTransform fb = new AffineTransform(py);
/* 2206 */           fb.concatenate(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).Tx);
/* 2207 */           ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).Tx = fb;
/*      */         } 
/* 2209 */         this.anxia_x = dx;
/* 2210 */         this.anxia_y = dy;
/* 2211 */         up();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void hua_ban1MouseDragged(MouseEvent evt) {
/* 2219 */     shu_yi_dong2(evt);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static BufferedImage convertToBufferedImage(Image image) {
/* 2225 */     BufferedImage newImage = new BufferedImage(image.getWidth(null), image.getHeight(null), 2);
/* 2226 */     Graphics2D g = newImage.createGraphics();
/* 2227 */     g.drawImage(image, 0, 0, (ImageObserver)null);
/* 2228 */     g.dispose();
/* 2229 */     return newImage;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 2236 */     JFileChooser fc = new JFileChooser();
/* 2237 */     ImagePreviewPanel preview = new ImagePreviewPanel();
/* 2238 */     fc.setAccessory(preview);
/* 2239 */     fc.addPropertyChangeListener(preview);
/*      */     
/* 2241 */     FileNameExtensionFilter filter = new FileNameExtensionFilter("Picture Files (.bmp,.jpg,.png,.jpeg,.gif,.xj,.plt,.hpgl,.svg,.dxf)", new String[] { "bmp", "jpg", "png", "jpeg", "gif", "xj", "plt", "hpgl", "svg", "dxf" });
/* 2242 */     fc.setFileFilter(filter);
/* 2243 */     int returnVal = fc.showOpenDialog(this);
/* 2244 */     if (fc.getSelectedFile() != null && returnVal == 0) {
/*      */       
/* 2246 */       File gcodeFile = fc.getSelectedFile();
/* 2247 */       String gcodeFilePath = fc.getSelectedFile().getPath();
/* 2248 */       String fileName = gcodeFile.getName();
/* 2249 */       String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
/* 2250 */       suffix = suffix.toUpperCase();
/* 2251 */       if (suffix.equals("BMP") || suffix.equals("JPG") || suffix.equals("PNG") || suffix.equals("JPEG") || suffix.equals("GIF")) {
/*      */         
/*      */         try {
/* 2254 */           BufferedImage bmp = ImageIO.read(gcodeFile);
/* 2255 */           if (bmp.getWidth() > 2400 || bmp.getHeight() > 2400) {
/*      */             double bi;
/*      */             
/* 2258 */             if (bmp.getWidth() > bmp.getHeight()) {
/*      */               
/* 2260 */               bi = 2400.0D / bmp.getWidth();
/*      */             } else {
/*      */               
/* 2263 */               bi = 2400.0D / bmp.getHeight();
/*      */             } 
/* 2265 */             bmp = Tu_pian.zoomImage(bmp, bi);
/*      */           } 
/* 2267 */           Hua_ban.ty_shuzu.add(Tu_yuan.chuang_jian(1, bmp));
/* 2268 */           for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++)
/*      */           {
/* 2270 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong = false;
/*      */           }
/* 2272 */           ((Tu_yuan)Hua_ban.ty_shuzu.get(Hua_ban.ty_shuzu.size() - 1)).xuan_zhong = true;
/*      */           
/* 2274 */           Tu_yuan.zhong_xin(Hua_ban.ty_shuzu);
/* 2275 */           Che_xiao.tian_jia();
/* 2276 */           up();
/* 2277 */         } catch (IOException iOException) {}
/*      */       
/*      */       }
/* 2280 */       else if (suffix.equals("XJ")) {
/*      */ 
/*      */         
/* 2283 */         try { ObjectInputStream ois = new ObjectInputStream(new FileInputStream(gcodeFilePath)); 
/* 2284 */           try { Hua_ban.ty_shuzu = (List<Tu_yuan>)ois.readObject();
/* 2285 */             ois.close(); } catch (Throwable throwable) { try { ois.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (Exception e)
/* 2286 */         { e.printStackTrace(); }
/*      */         
/* 2288 */         for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */           
/* 2290 */           if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).lei_xing == 1) {
/*      */             
/* 2292 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu = new BufferedImage(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wt_w, ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wt_g, 2);
/* 2293 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu_yuan = new BufferedImage(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wty_w, ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wty_g, 2);
/*      */             
/* 2295 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu.setRGB(0, 0, ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wt_w, ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wt_g, ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu_, 0, ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wt_w);
/* 2296 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu_yuan.setRGB(0, 0, ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wty_w, ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wty_g, ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu_yuan_, 0, ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wty_w);
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/* 2301 */         Che_xiao.tian_jia();
/* 2302 */         up();
/* 2303 */       } else if (suffix.equals("PLT")) {
/*      */         
/* 2305 */         jie_xi_PLT plt = new jie_xi_PLT();
/* 2306 */         plt.jie_xi_PLT(gcodeFile);
/* 2307 */         plt = null;
/* 2308 */         Che_xiao.tian_jia();
/* 2309 */         up();
/* 2310 */       } else if (suffix.equals("HPGL")) {
/*      */         
/* 2312 */         jie_xi_PLT plt = new jie_xi_PLT();
/* 2313 */         plt.jie_xi_PLT(gcodeFile);
/* 2314 */         plt = null;
/* 2315 */         Che_xiao.tian_jia();
/* 2316 */         up();
/* 2317 */       } else if (suffix.equals("SVG")) {
/*      */         
/*      */         try {
/* 2320 */           URI uri = gcodeFile.toURI();
/* 2321 */           SVGUniverse svg = new SVGUniverse();
/* 2322 */           svg.loadSVG(uri.toURL());
/* 2323 */           SVGDiagram ff = svg.getDiagram(uri);
/* 2324 */           Rectangle2D rr = ff.getViewRect();
/* 2325 */           BufferedImage bb = new BufferedImage((int)rr.getWidth(), (int)rr.getHeight(), 1);
/* 2326 */           Graphics2D gg = bb.createGraphics();
/* 2327 */           gg.setColor(Color.WHITE);
/* 2328 */           gg.fillRect(0, 0, bb.getWidth(), bb.getHeight());
/*      */           
/* 2330 */           ff.render(gg);
/* 2331 */           Hua_ban.ty_shuzu.add(Tu_yuan.chuang_jian(1, bb));
/* 2332 */           Che_xiao.tian_jia();
/* 2333 */           up();
/* 2334 */         } catch (MalformedURLException ex) {
/* 2335 */           Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 2336 */         } catch (SVGException ex) {
/* 2337 */           Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, (Throwable)ex);
/*      */         } 
/* 2339 */       } else if (suffix.equals("DXF")) {
/*      */         
/* 2341 */         jie_xi_dxf.jie_xi(gcodeFile);
/* 2342 */         Tu_yuan.zhong_xin(Hua_ban.ty_shuzu);
/* 2343 */         Che_xiao.tian_jia();
/* 2344 */         up();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void hua_ban1MouseWheelMoved(MouseWheelEvent evt) {
/* 2351 */     int dx = evt.getX(), dy = evt.getY();
/* 2352 */     if (evt.getPreciseWheelRotation() < 0.0D) {
/*      */       
/* 2354 */       AffineTransform sf1 = AffineTransform.getTranslateInstance(-dx, -dy);
/* 2355 */       for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */         
/* 2357 */         AffineTransform fb = new AffineTransform(sf1);
/* 2358 */         fb.concatenate(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).Tx);
/* 2359 */         ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).Tx = fb;
/*      */       } 
/*      */       
/* 2362 */       Hua_ban.quan_beishu *= 1.1D;
/* 2363 */       AffineTransform sf = AffineTransform.getScaleInstance(1.1D, 1.1D);
/* 2364 */       for (int j = 0; j < Hua_ban.ty_shuzu.size(); j++) {
/*      */         
/* 2366 */         AffineTransform fb = new AffineTransform(sf);
/* 2367 */         fb.concatenate(((Tu_yuan)Hua_ban.ty_shuzu.get(j)).Tx);
/* 2368 */         ((Tu_yuan)Hua_ban.ty_shuzu.get(j)).Tx = fb;
/*      */       } 
/*      */       
/* 2371 */       AffineTransform sf2 = AffineTransform.getTranslateInstance(dx, dy);
/* 2372 */       for (int k = 0; k < Hua_ban.ty_shuzu.size(); k++) {
/*      */         
/* 2374 */         AffineTransform fb = new AffineTransform(sf2);
/* 2375 */         fb.concatenate(((Tu_yuan)Hua_ban.ty_shuzu.get(k)).Tx);
/* 2376 */         ((Tu_yuan)Hua_ban.ty_shuzu.get(k)).Tx = fb;
/*      */       } 
/*      */     } else {
/*      */       
/* 2380 */       GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)Hua_ban.ty_shuzu.get(0)).lu_jing);
/* 2381 */       lu_jing2.transform(((Tu_yuan)Hua_ban.ty_shuzu.get(0)).Tx);
/* 2382 */       Rectangle rect = lu_jing2.getBounds();
/* 2383 */       if (rect.width > 200) {
/*      */         
/* 2385 */         AffineTransform sf1 = AffineTransform.getTranslateInstance(-dx, -dy);
/* 2386 */         for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */           
/* 2388 */           AffineTransform fb = new AffineTransform(sf1);
/* 2389 */           fb.concatenate(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).Tx);
/* 2390 */           ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).Tx = fb;
/*      */         } 
/*      */         
/* 2393 */         Hua_ban.quan_beishu *= 0.9D;
/* 2394 */         AffineTransform sf = AffineTransform.getScaleInstance(0.9D, 0.9D);
/* 2395 */         for (int j = 0; j < Hua_ban.ty_shuzu.size(); j++) {
/*      */           
/* 2397 */           AffineTransform fb = new AffineTransform(sf);
/* 2398 */           fb.concatenate(((Tu_yuan)Hua_ban.ty_shuzu.get(j)).Tx);
/* 2399 */           ((Tu_yuan)Hua_ban.ty_shuzu.get(j)).Tx = fb;
/*      */         } 
/*      */         
/* 2402 */         AffineTransform sf2 = AffineTransform.getTranslateInstance(dx, dy);
/* 2403 */         for (int k = 0; k < Hua_ban.ty_shuzu.size(); k++) {
/*      */           
/* 2405 */           AffineTransform fb = new AffineTransform(sf2);
/* 2406 */           fb.concatenate(((Tu_yuan)Hua_ban.ty_shuzu.get(k)).Tx);
/* 2407 */           ((Tu_yuan)Hua_ban.ty_shuzu.get(k)).Tx = fb;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2412 */     up();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void jButton17ActionPerformed(ActionEvent evt) {
/* 2418 */     Runnable runnable2 = new Runnable() {
/*      */         public void run() {
/* 2420 */           if (Quan_ju.she_bei.yi_lian_jie.booleanValue()) {
/* 2421 */             Quan_ju.she_bei.ming_ling_ting_zhi();
/*      */           }
/* 2423 */           Quan_ju.ting_zhi = true;
/*      */         }
/*      */       };
/* 2426 */     Thread thread2 = new Thread(runnable2);
/*      */     
/* 2428 */     thread2.start();
/* 2429 */     Runnable runnable = new Runnable() {
/*      */         public void run() {
/* 2431 */           Quan_ju.she_bei.ming_ling_ting_zhi_yu_lan();
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2436 */           mainJFrame.this.up();
/*      */         }
/*      */       };
/* 2439 */     Thread thread = new Thread(runnable);
/*      */     
/* 2441 */     thread.start();
/*      */   }
/*      */ 
/*      */   
/*      */   void lian_jie() {
/* 2446 */     SerialPort[] ports = SerialPort.getCommPorts();
/* 2447 */     She_bei sb = new She_bei();
/* 2448 */     SerialPort com = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2455 */     for (int i = 0; i < ports.length; i++) {
/*      */       
/* 2457 */       if (-1 == ports[i].getSystemPortName().indexOf("Bluetooth")) {
/*      */         
/* 2459 */         com = ports[i];
/* 2460 */         com.setBaudRate(921600);
/* 2461 */         com.openPort();
/* 2462 */         if (com.isOpen()) {
/*      */           
/* 2464 */           sb.chu_shi_hua(com);
/* 2465 */           sb.ming_ling_cha_xun();
/* 2466 */           for (int j = 0; j < 300; j++) {
/*      */             
/*      */             try {
/* 2469 */               Thread.sleep(10L);
/* 2470 */             } catch (InterruptedException ex) {
/* 2471 */               Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*      */             } 
/* 2473 */             if (sb.yi_lian_jie.booleanValue()) {
/*      */               
/* 2475 */               Quan_ju.she_bei = sb;
/* 2476 */               Quan_ju.she_bei.out = Quan_ju.she_bei.com.getOutputStream();
/* 2477 */               Properties props = System.getProperties();
/* 2478 */               Quan_ju.she_bei.osName = props.getProperty("os.name");
/*      */               
/*      */               return;
/*      */             } 
/*      */           } 
/*      */         } 
/* 2484 */         sb.guan_bi();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void jButton18ActionPerformed(ActionEvent evt) {
/* 2499 */     if (Quan_ju.she_bei.yi_lian_jie.booleanValue()) {
/*      */       
/* 2501 */       Quan_ju.she_bei.guan_bi();
/*      */     }
/*      */     else {
/*      */       
/* 2505 */       Runnable runnable2 = new Runnable() {
/*      */           public void run() {
/* 2507 */             mainJFrame.this.lian_jie();
/*      */           }
/*      */         };
/* 2510 */       Thread lj = new Thread(runnable2);
/* 2511 */       lj.start();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void jie_xi_dxf2() {
/* 2541 */     Parser dxfParser = ParserBuilder.createDefaultParser();
/*      */     try {
/* 2543 */       dxfParser.parse(new FileInputStream("C:\\Users\\Administrator\\Desktop\\dxf2.dxf"), "UTF-8");
/* 2544 */       DXFDocument dXFDocument = dxfParser.getDocument();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 2572 */     catch (FileNotFoundException ex) {
/* 2573 */       Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 2574 */     } catch (ParseException ex) {
/* 2575 */       Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, (Throwable)ex);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   URI StringtoURI(String ss) {
/* 2584 */     File file = new File(ss);
/* 2585 */     URI uri = null;
/* 2586 */     uri = file.toURI();
/* 2587 */     return uri;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void jie_xi_svg() {
/*      */     try {
/* 2595 */       URI uri = StringtoURI("C:\\Users\\Administrator\\Desktop\\svg.svg");
/* 2596 */       SVGUniverse svg = new SVGUniverse();
/* 2597 */       svg.loadSVG(uri.toURL());
/*      */       
/* 2599 */       SVGDiagram ff = svg.getDiagram(uri);
/* 2600 */       Rectangle2D rr = ff.getViewRect();
/* 2601 */       BufferedImage bb = new BufferedImage((int)rr.getWidth(), (int)rr.getHeight(), 1);
/* 2602 */       Graphics2D gg = bb.createGraphics();
/* 2603 */       gg.setColor(Color.WHITE);
/* 2604 */       gg.fillRect(0, 0, bb.getWidth(), bb.getHeight());
/*      */       
/* 2606 */       ff.render(gg);
/* 2607 */       Hua_ban.ty_shuzu.add(Tu_yuan.chuang_jian(1, bb));
/*      */ 
/*      */     
/*      */     }
/* 2611 */     catch (MalformedURLException ex) {
/* 2612 */       Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 2613 */     } catch (SVGException ex) {
/* 2614 */       Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, (Throwable)ex);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void jButton15ActionPerformed(ActionEvent evt) {
/* 2620 */     if (!Quan_ju.she_bei.yi_lian_jie.booleanValue())
/* 2621 */       return;  Quan_ju.ting_zhi = false;
/*      */     
/* 2623 */     if (!Quan_ju.qu_diao_ke().booleanValue()) {
/*      */       
/* 2625 */       if (Quan_ju.qu_yu_lan().booleanValue())
/*      */       {
/* 2627 */         Quan_ju.she_bei.ming_ling_ting_zhi_yu_lan();
/*      */       }
/*      */       
/* 2630 */       Runnable runnable2 = new Runnable() {
/*      */           public void run() {
/*      */             try {
/* 2633 */               mainJFrame.this.jButton15.setEnabled(false);
/* 2634 */               mainJFrame.this.tuo_ji();
/* 2635 */               mainJFrame.this.jButton15.setEnabled(true);
/* 2636 */               Quan_ju.jin_du = 0;
/* 2637 */             } catch (Exception ex) {
/* 2638 */               JOptionPane.showMessageDialog(null, ex);
/* 2639 */               Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*      */             } 
/*      */           }
/*      */         };
/*      */       
/* 2644 */       Thread thread2 = new Thread(runnable2);
/*      */       
/* 2646 */       thread2.start();
/* 2647 */       this.shi_jian = 0; chaoshi = 0;
/*      */     
/*      */     }
/* 2650 */     else if (Quan_ju.qu_zan_ting().booleanValue()) {
/*      */       
/* 2652 */       Runnable runnable2 = new Runnable() {
/*      */           public void run() {
/* 2654 */             if (Quan_ju.she_bei.yi_lian_jie.booleanValue())
/*      */             {
/* 2656 */               Quan_ju.she_bei.ming_ling_ji_xu();
/*      */             }
/*      */           }
/*      */         };
/* 2660 */       Thread thread2 = new Thread(runnable2);
/*      */       
/* 2662 */       thread2.start();
/*      */     } else {
/*      */       
/* 2665 */       Runnable runnable2 = new Runnable() {
/*      */           public void run() {
/* 2667 */             if (Quan_ju.she_bei.yi_lian_jie.booleanValue())
/*      */             {
/* 2669 */               Quan_ju.she_bei.ming_ling_zan_ting();
/*      */             }
/*      */           }
/*      */         };
/* 2673 */       Thread thread2 = new Thread(runnable2);
/*      */       
/* 2675 */       thread2.start();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void jButton14ActionPerformed(ActionEvent evt) {
/* 2683 */     if (Quan_ju.qu_diao_ke().booleanValue())
/* 2684 */       return;  if (Quan_ju.she_bei.yi_lian_jie.booleanValue())
/*      */     {
/* 2686 */       if (Quan_ju.qu_yu_lan().booleanValue()) {
/*      */         
/* 2688 */         Runnable runnable2 = new Runnable() {
/*      */             public void run() {
/* 2690 */               Quan_ju.she_bei.ming_ling_ting_zhi_yu_lan();
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 2695 */               mainJFrame.this.up();
/*      */             }
/*      */           };
/* 2698 */         Thread thread2 = new Thread(runnable2);
/*      */         
/* 2700 */         thread2.start();
/*      */       }
/*      */       else {
/*      */         
/* 2704 */         Runnable runnable2 = new Runnable() {
/*      */             public void run() {
/* 2706 */               Tu_yuan.qu_jvxing(Hua_ban.ty_shuzu);
/* 2707 */               GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)Hua_ban.ty_shuzu.get(0)).lu_jing);
/* 2708 */               lu_jing2.transform(((Tu_yuan)Hua_ban.ty_shuzu.get(0)).Tx);
/* 2709 */               Rectangle rect = lu_jing2.getBounds();
/* 2710 */               Rectangle zui_zhong_wjx2 = new Rectangle(Tu_yuan.zui_zhong_wjx);
/* 2711 */               AffineTransform sf = AffineTransform.getTranslateInstance((0 - rect.x), (0 - rect.y));
/* 2712 */               zui_zhong_wjx2 = sf.createTransformedShape(zui_zhong_wjx2).getBounds();
/* 2713 */               sf = AffineTransform.getScaleInstance(1.0D / Hua_ban.quan_beishu, 1.0D / Hua_ban.quan_beishu);
/* 2714 */               zui_zhong_wjx2 = sf.createTransformedShape(zui_zhong_wjx2).getBounds();
/* 2715 */               if (zui_zhong_wjx2.width < 2 && zui_zhong_wjx2.height < 2) {
/*      */                 return;
/*      */               }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 2728 */               Quan_ju.she_bei.ming_ling_yu_lan(zui_zhong_wjx2.x + zui_zhong_wjx2.width / 2, zui_zhong_wjx2.y + zui_zhong_wjx2.height / 2, zui_zhong_wjx2.width, zui_zhong_wjx2.height);
/* 2729 */               mainJFrame.this.up();
/*      */             }
/*      */           };
/* 2732 */         Thread thread2 = new Thread(runnable2);
/* 2733 */         thread2.start();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void jButton7ActionPerformed(ActionEvent evt) {
/* 2741 */     Zi_ti2 dialog = new Zi_ti2(this.hua_ban1, true);
/* 2742 */     dialog.setDefaultCloseOperation(2);
/* 2743 */     dialog.setVisible(true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void formComponentResized(ComponentEvent evt) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void jSlider1StateChanged(ChangeEvent evt) {
/* 2756 */     this.jLabel5.setText(this.str_gong_lv + this.jSlider1.getValue() + "%");
/* 2757 */     Quan_ju.gong_lv_wt = this.jSlider1.getValue();
/*      */   }
/*      */   
/*      */   void she_zhi() {
/* 2761 */     Runnable runnable2 = new Runnable() {
/*      */         public void run() {
/* 2763 */           Quan_ju.shen_du_wt = 100 - mainJFrame.this.jSlider2.getValue();
/* 2764 */           Quan_ju.gong_lv_wt = mainJFrame.this.jSlider1.getValue();
/* 2765 */           if (Quan_ju.she_bei.yi_lian_jie.booleanValue()) {
/* 2766 */             Quan_ju.she_bei.ming_ling_gl_sd(Quan_ju.gong_lv_wt, Quan_ju.shen_du_wt);
/*      */           }
/*      */         }
/*      */       };
/* 2770 */     Thread thread2 = new Thread(runnable2);
/*      */     
/* 2772 */     thread2.start();
/*      */   }
/*      */ 
/*      */   
/*      */   private void jSlider1MouseReleased(MouseEvent evt) {
/* 2777 */     if (!Quan_ju.qu_diao_ke().booleanValue())
/* 2778 */       return;  she_zhi();
/*      */   }
/*      */ 
/*      */   
/*      */   private void jSlider2StateChanged(ChangeEvent evt) {
/* 2783 */     this.jLabel13.setText(this.str_shen_du + this.jSlider2.getValue() + "%");
/* 2784 */     Quan_ju.shen_du_wt = 100 - this.jSlider2.getValue();
/*      */   }
/*      */ 
/*      */   
/*      */   private void jSlider2MouseReleased(MouseEvent evt) {
/* 2789 */     if (!Quan_ju.qu_diao_ke().booleanValue())
/* 2790 */       return;  she_zhi();
/*      */   }
/*      */   
/*      */   void she_zhi_can_shu() {
/* 2794 */     Runnable runnable2 = new Runnable() {
/*      */         public void run() {
/* 2796 */           int rg = Tu_yuan.dk_ruo_guang * 2;
/* 2797 */           int jd = Tu_yuan.dk_jing_du;
/* 2798 */           if (mainJFrame.this.com_dakai)
/* 2799 */             mainJFrame.com2.fa_song(new byte[] { 40, 0, 11, (byte)rg, (byte)jd, 0, 0, 0, 0, 0, 0 }, 2); 
/*      */         }
/*      */       };
/* 2802 */     Thread thread2 = new Thread(runnable2);
/*      */     
/* 2804 */     thread2.start();
/*      */   }
/*      */   
/*      */   private void jButton12ActionPerformed(ActionEvent evt) {
/* 2808 */     JFileChooser chooser = new JFileChooser();
/* 2809 */     FileNameExtensionFilter filter = new FileNameExtensionFilter("Picture Files (.xj)", new String[] { "xj" });
/* 2810 */     chooser.setFileFilter(filter);
/*      */     
/* 2812 */     int option = chooser.showSaveDialog(this);
/* 2813 */     if (option == 0) {
/*      */       
/* 2815 */       for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */         
/* 2817 */         if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).lei_xing == 1) {
/*      */           
/* 2819 */           ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wt_w = ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu.getWidth();
/* 2820 */           ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wt_g = ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu.getHeight();
/* 2821 */           ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wty_w = ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu_yuan.getWidth();
/* 2822 */           ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wty_g = ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu_yuan.getHeight();
/* 2823 */           ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu_ = new int[((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu.getWidth() * ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu.getHeight()];
/* 2824 */           ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu.getRGB(0, 0, ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu.getWidth(), ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu.getHeight(), ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu_, 0, ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu.getWidth());
/* 2825 */           ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu_yuan_ = new int[((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu_yuan.getWidth() * ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu_yuan.getHeight()];
/* 2826 */           ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu_yuan.getRGB(0, 0, ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu_yuan.getWidth(), ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu_yuan.getHeight(), ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu_yuan_, 0, ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).wei_tu_yuan.getWidth());
/*      */         } 
/*      */       } 
/* 2829 */       Tu_yuan.hui_fu();
/* 2830 */       BufferedImage tu_diaoke2 = Tu_yuan.qu_tu(Hua_ban.ty_shuzu);
/*      */       
/* 2832 */       Tu_yuan.hui_fu_xian_chang();
/*      */       
/* 2834 */       String ss = chooser.getSelectedFile().getPath();
/* 2835 */       ss = ss.toLowerCase();
/* 2836 */       if (!ss.substring(ss.length() - 3).equals(".xj"))
/*      */       {
/* 2838 */         ss = ss + ".xj";
/*      */       }
/* 2840 */       if (tu_diaoke2 != null) {
/*      */         
/* 2842 */         File outputfile = new File(ss + ".bmp");
/*      */ 
/*      */         
/*      */         try {
/* 2846 */           BMPEncoder.write(tu_diaoke2, outputfile);
/*      */         }
/* 2848 */         catch (IOException ex) {
/* 2849 */           Logger.getLogger(Tu_yuan.class.getName()).log(Level.SEVERE, (String)null, ex);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 2854 */       File f = new File(ss);
/*      */       
/* 2856 */       try { ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
/*      */         
/* 2858 */         try { oos.writeObject(Hua_ban.ty_shuzu);
/* 2859 */           oos.close(); } catch (Throwable throwable) { try { oos.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (Exception e)
/* 2860 */       { e.printStackTrace(); }
/*      */     
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void jSlider6StateChanged(ChangeEvent evt) {
/* 2869 */     this.jLabel14.setText(bundle.getString("str_dui_bi") + this.jSlider6.getValue() + "%");
/* 2870 */     if (Hua_ban.ty_shuzu.size() < 2)
/* 2871 */       return;  for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */       
/* 2873 */       if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong && ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).lei_xing == 1) {
/*      */         
/* 2875 */         ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).yu_zhi = this.jSlider6.getValue();
/* 2876 */         ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).chu_li();
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2882 */     up();
/*      */   }
/*      */ 
/*      */   
/*      */   private void jSlider7StateChanged(ChangeEvent evt) {
/* 2887 */     this.jLabel16.setText(bundle.getString("str_tian_chong") + this.jSlider7.getValue());
/* 2888 */     Tu_yuan.tian_chong_md = this.jSlider7.getValue();
/* 2889 */     up();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void jButton13MouseClicked(MouseEvent evt) {}
/*      */ 
/*      */   
/*      */   private void jButton13ActionPerformed(ActionEvent evt) {
/* 2898 */     final JFrame win = new JFrame("QR");
/* 2899 */     Container con = win.getContentPane();
/* 2900 */     con.setLayout((LayoutManager)null);
/* 2901 */     JLabel label1 = new JLabel(bundle.getString("str_qing_shu_ru"));
/* 2902 */     JButton button = new JButton("OK");
/*      */ 
/*      */     
/* 2905 */     final TextArea textArea = new TextArea("", 20, 43, 0);
/* 2906 */     textArea.setBounds(10, 36, 311, 113);
/* 2907 */     label1.setBounds(10, 10, 274, 16);
/* 2908 */     button.setBounds(248, 156, 74, 38);
/* 2909 */     con.add(label1);
/* 2910 */     con.add(textArea);
/* 2911 */     con.add(button);
/* 2912 */     win.setBackground(Color.LIGHT_GRAY);
/* 2913 */     win.setIconImage((new ImageIcon(getClass().getResource("/tu/she_zhi.png"))).getImage());
/* 2914 */     win.setVisible(true);
/* 2915 */     win.setSize(345, 238);
/* 2916 */     win.setLocationRelativeTo((Component)null);
/* 2917 */     win.setDefaultCloseOperation(2);
/* 2918 */     win.setResizable(false);
/* 2919 */     button.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent ae) {
/* 2922 */             String text = textArea.getText();
/* 2923 */             win.setTitle(text);
/* 2924 */             QrCode.Ecc errCorLvl = QrCode.Ecc.LOW;
/* 2925 */             QrCode qr = QrCode.encodeText(text, errCorLvl);
/* 2926 */             BufferedImage img = qr.toImage(10, 4);
/*      */             
/* 2928 */             Hua_ban.ty_shuzu.add(Tu_yuan.chuang_jian(1, img));
/* 2929 */             for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++)
/*      */             {
/* 2931 */               ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong = false;
/*      */             }
/* 2933 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(Hua_ban.ty_shuzu.size() - 1)).xuan_zhong = true;
/*      */             
/* 2935 */             Tu_yuan.zhong_xin(Hua_ban.ty_shuzu);
/* 2936 */             Che_xiao.tian_jia();
/* 2937 */             mainJFrame.this.up();
/* 2938 */             win.dispose();
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void jButton16MouseClicked(MouseEvent evt) {}
/*      */ 
/*      */   
/*      */   private void jButton16ActionPerformed(ActionEvent evt) {
/* 2949 */     final JFrame win = new JFrame("Barcode");
/* 2950 */     Container con = win.getContentPane();
/* 2951 */     con.setLayout((LayoutManager)null);
/* 2952 */     JLabel label1 = new JLabel(bundle.getString("str_qing_shu_ru2"));
/* 2953 */     JButton button = new JButton("OK");
/*      */ 
/*      */     
/* 2956 */     final TextArea textArea = new TextArea("", 20, 43, 0);
/* 2957 */     textArea.setBounds(10, 36, 311, 113);
/* 2958 */     label1.setBounds(10, 10, 274, 16);
/* 2959 */     button.setBounds(248, 156, 74, 38);
/* 2960 */     con.add(label1);
/* 2961 */     con.add(textArea);
/* 2962 */     con.add(button);
/* 2963 */     win.setBackground(Color.LIGHT_GRAY);
/* 2964 */     win.setIconImage((new ImageIcon(getClass().getResource("/tu/she_zhi.png"))).getImage());
/* 2965 */     win.setVisible(true);
/* 2966 */     win.setSize(345, 238);
/* 2967 */     win.setLocationRelativeTo((Component)null);
/* 2968 */     win.setDefaultCloseOperation(2);
/* 2969 */     win.setResizable(false);
/* 2970 */     button.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent ae) {
/* 2973 */             String text = textArea.getText();
/* 2974 */             win.setTitle(text);
/*      */             
/* 2976 */             BufferedImage img = (BufferedImage)Barcode.DoEncode(Barcode.TYPE.CODE128, text);
/*      */             
/* 2978 */             Hua_ban.ty_shuzu.add(Tu_yuan.chuang_jian(1, img));
/* 2979 */             for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++)
/*      */             {
/* 2981 */               ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong = false;
/*      */             }
/* 2983 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(Hua_ban.ty_shuzu.size() - 1)).suo_fang(4.0D, 1.0D);
/* 2984 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(Hua_ban.ty_shuzu.size() - 1)).xuan_zhong = true;
/*      */             
/* 2986 */             Tu_yuan.zhong_xin(Hua_ban.ty_shuzu);
/* 2987 */             Che_xiao.tian_jia();
/* 2988 */             mainJFrame.this.up();
/* 2989 */             win.dispose();
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void jButton20MouseClicked(MouseEvent evt) {}
/*      */ 
/*      */   
/*      */   private void jButton20ActionPerformed(ActionEvent evt) {
/* 3000 */     this.popMenu.show(getComponent(getComponentCount() - 1), this.jButton20.getX(), this.jButton20.getY() + this.jButton20.getHeight());
/*      */   }
/*      */ 
/*      */   
/*      */   private void jButton21ActionPerformed(ActionEvent evt) {
/* 3005 */     if (!this.mo_ni) {
/*      */       
/* 3007 */       this.mo_ni = true;
/* 3008 */       Runnable runnable2 = new Runnable() {
/*      */           public void run() {
/* 3010 */             Tu_yuan.hui_fu();
/* 3011 */             BufferedImage tu_diaoke2 = Tu_yuan.qu_tu(Hua_ban.ty_shuzu);
/* 3012 */             List<Dian> dian = Tu_yuan.qu_dian(Hua_ban.ty_shuzu);
/* 3013 */             Tu_yuan.hui_fu_xian_chang();
/* 3014 */             if (tu_diaoke2 == null || dian != null)
/*      */             {
/*      */ 
/*      */               
/* 3018 */               if (tu_diaoke2 == null && dian != null) {
/*      */                 
/* 3020 */                 dian = mainJFrame.this.dian_ya_suo(dian);
/* 3021 */                 byte[] bao = new byte[dian.size() * 4 + 6];
/* 3022 */                 int jishu = 4;
/* 3023 */                 for (int j = 0; j < dian.size(); j++) {
/*      */                   
/* 3025 */                   bao[jishu++] = (byte)(((Dian)dian.get(j)).x >> 8);
/* 3026 */                   bao[jishu++] = (byte)((Dian)dian.get(j)).x;
/* 3027 */                   bao[jishu++] = (byte)(((Dian)dian.get(j)).y >> 8);
/* 3028 */                   bao[jishu++] = (byte)((Dian)dian.get(j)).y;
/*      */                 } 
/* 3030 */                 bao[0] = -85;
/* 3031 */                 bao[1] = 17;
/* 3032 */                 bao[2] = (byte)(dian.size() * 4 + 6 >> 8);
/* 3033 */                 bao[3] = (byte)(dian.size() * 4 + 6);
/* 3034 */                 Quan_ju.she_bei.ming_ling_fa_shu_ju(bao);
/*      */               
/*      */               }
/* 3037 */               else if (tu_diaoke2 != null && dian != null) {
/*      */                 
/* 3039 */                 dian = mainJFrame.this.dian_ya_suo(dian);
/* 3040 */                 byte[] bao = new byte[dian.size() * 4 + 6];
/* 3041 */                 int jishu = 4;
/* 3042 */                 for (int j = 0; j < dian.size(); j++) {
/*      */                   
/* 3044 */                   bao[jishu++] = (byte)(((Dian)dian.get(j)).x >> 8);
/* 3045 */                   bao[jishu++] = (byte)((Dian)dian.get(j)).x;
/* 3046 */                   bao[jishu++] = (byte)(((Dian)dian.get(j)).y >> 8);
/* 3047 */                   bao[jishu++] = (byte)((Dian)dian.get(j)).y;
/*      */                 } 
/* 3049 */                 bao[0] = -85;
/* 3050 */                 bao[1] = 17;
/* 3051 */                 bao[2] = (byte)(dian.size() * 4 + 6 >> 8);
/* 3052 */                 bao[3] = (byte)(dian.size() * 4 + 6);
/* 3053 */                 Quan_ju.she_bei.ming_ling_fa_shu_ju(bao);
/*      */               }  } 
/*      */           }
/*      */         };
/* 3057 */       Thread thread2 = new Thread(runnable2);
/* 3058 */       thread2.start();
/*      */     }
/*      */     else {
/*      */       
/* 3062 */       Runnable runnable2 = new Runnable() {
/*      */           public void run() {
/* 3064 */             Quan_ju.she_bei.ming_ling_ting_zhi_yu_lan();
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 3069 */             mainJFrame.this.mo_ni = false;
/* 3070 */             mainJFrame.this.up();
/*      */           }
/*      */         };
/* 3073 */       Thread thread2 = new Thread(runnable2);
/*      */       
/* 3075 */       thread2.start();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void but_hei_baiActionPerformed(ActionEvent evt) {
/* 3083 */     mo_shi(1);
/*      */   }
/*      */ 
/*      */   
/*      */   private void but_hui_duActionPerformed(ActionEvent evt) {
/* 3088 */     mo_shi(2);
/*      */   }
/*      */ 
/*      */   
/*      */   private void but_lun_kuoActionPerformed(ActionEvent evt) {
/* 3093 */     mo_shi(3);
/*      */   }
/*      */ 
/*      */   
/*      */   private void but_su_miaoActionPerformed(ActionEvent evt) {
/* 3098 */     mo_shi(4);
/*      */   }
/*      */ 
/*      */   
/*      */   private void jButton8ActionPerformed(ActionEvent evt) {
/* 3103 */     Tu_yuan.zhong_xin(Hua_ban.ty_shuzu);
/* 3104 */     up();
/* 3105 */     Che_xiao.tian_jia();
/*      */   }
/*      */ 
/*      */   
/*      */   private void jButton5ActionPerformed(ActionEvent evt) {
/* 3110 */     jing_xiang_xy(true);
/*      */   }
/*      */ 
/*      */   
/*      */   private void jButton6ActionPerformed(ActionEvent evt) {
/* 3115 */     jing_xiang_xy(false);
/*      */   }
/*      */ 
/*      */   
/*      */   private void jButton22ActionPerformed(ActionEvent evt) {
/* 3120 */     final JFrame win = new JFrame("Set");
/* 3121 */     Container con = win.getContentPane();
/* 3122 */     con.setLayout((LayoutManager)null);
/* 3123 */     final JLabel label1 = new JLabel(bundle.getString("str_ruo_guang") + (Quan_ju.diao_ke_ji.dai_ji / 2) + "%");
/* 3124 */     JLabel label2 = new JLabel(bundle.getString("str_jing_du"));
/* 3125 */     final JCheckBox gz = new JCheckBox(bundle.getString("str_gun_zhou"));
/* 3126 */     gz.setSelected(Quan_ju.qu_gun_zhou().booleanValue());
/* 3127 */     final JCheckBox laser1064 = new JCheckBox(bundle.getString("str_laser1064"));
/* 3128 */     laser1064.setSelected(Quan_ju.qu_1064().booleanValue());
/* 3129 */     JButton but = new JButton("OK");
/* 3130 */     final JSlider slider = new JSlider(0, 100, Quan_ju.diao_ke_ji.dai_ji / 2);
/*      */     
/* 3132 */     String[] listData = { bundle.getString("str_di"), bundle.getString("str_zhong"), bundle.getString("str_gao") };
/*      */     
/* 3134 */     final JComboBox<String> comboBox = new JComboBox<>(listData);
/*      */     
/* 3136 */     comboBox.addItemListener(new ItemListener()
/*      */         {
/*      */           public void itemStateChanged(ItemEvent e)
/*      */           {
/* 3140 */             if (e.getStateChange() == 1) {
/* 3141 */               Tu_yuan.dk_jing_du = slider.getValue();
/* 3142 */               Runnable runnable = new Runnable() {
/*      */                   public void run() {
/* 3144 */                     int rg = slider.getValue() * 2;
/* 3145 */                     int jd = comboBox.getSelectedIndex();
/* 3146 */                     if (Quan_ju.she_bei.yi_lian_jie.booleanValue())
/* 3147 */                       Quan_ju.she_bei.ming_ling_she_zhi(rg, jd); 
/*      */                   }
/*      */                 };
/* 3150 */               Thread thread = new Thread(runnable);
/*      */               
/* 3152 */               thread.start();
/*      */             } 
/*      */           }
/*      */         });
/*      */     
/* 3157 */     comboBox.setSelectedIndex(Quan_ju.qu_jing_du());
/*      */     
/* 3159 */     label1.setBounds(10, 10, 274, 16);
/* 3160 */     slider.setBounds(10, 35, 300, 20);
/* 3161 */     label2.setBounds(10, 60, 274, 16);
/* 3162 */     comboBox.setBounds(10, 85, 300, 20);
/* 3163 */     gz.setBounds(10, 110, 300, 20);
/* 3164 */     laser1064.setBounds(10, 135, 300, 20);
/* 3165 */     but.setBounds(230, 170, 70, 35);
/* 3166 */     if ((Quan_ju.diao_ke_ji.ji_xing & 0x1) > 0) {
/*      */       
/* 3168 */       con.add(label1);
/* 3169 */       con.add(slider);
/*      */     } 
/* 3171 */     if ((Quan_ju.diao_ke_ji.ji_xing & 0x2) > 0) {
/*      */       
/* 3173 */       con.add(label2);
/* 3174 */       con.add(comboBox);
/*      */     } 
/* 3176 */     if ((Quan_ju.diao_ke_ji.ji_xing & 0x10) > 0)
/*      */     {
/* 3178 */       con.add(gz);
/*      */     }
/* 3180 */     if ((Quan_ju.diao_ke_ji.ji_xing & 0x20) > 0)
/*      */     {
/* 3182 */       con.add(laser1064);
/*      */     }
/* 3184 */     con.add(but);
/*      */     
/* 3186 */     win.setBackground(Color.LIGHT_GRAY);
/* 3187 */     win.setIconImage((new ImageIcon(getClass().getResource("/tu/she_zhi.png"))).getImage());
/* 3188 */     win.setVisible(true);
/* 3189 */     win.setSize(345, 230);
/* 3190 */     win.setLocationRelativeTo((Component)null);
/* 3191 */     win.setDefaultCloseOperation(2);
/* 3192 */     win.setResizable(false);
/* 3193 */     gz.addChangeListener(new ChangeListener()
/*      */         {
/*      */           public void stateChanged(ChangeEvent e) {
/* 3196 */             if (gz.isSelected()) {
/*      */               
/* 3198 */               Quan_ju.she_bei.ming_ling_gun_zhou();
/*      */             } else {
/*      */               
/* 3201 */               Quan_ju.she_bei.ming_ling_guan_gun_zhou();
/*      */             } 
/*      */           }
/*      */         });
/* 3205 */     laser1064.addChangeListener(new ChangeListener()
/*      */         {
/*      */           public void stateChanged(ChangeEvent e) {
/* 3208 */             if (laser1064.isSelected()) {
/*      */               
/* 3210 */               Quan_ju.she_bei.ming_ling_1064();
/* 3211 */               Quan_ju.diao_ke_ci_shu |= 0x80;
/*      */             } else {
/*      */               
/* 3214 */               Quan_ju.she_bei.ming_ling_guan_1064();
/* 3215 */               Quan_ju.diao_ke_ci_shu &= 0x7F;
/*      */             } 
/*      */           }
/*      */         });
/*      */     
/* 3220 */     slider.addChangeListener(new ChangeListener()
/*      */         {
/*      */           public void stateChanged(ChangeEvent e) {
/* 3223 */             label1.setText(mainJFrame.bundle.getString("str_ruo_guang") + slider.getValue() + "%");
/*      */           }
/*      */         });
/* 3226 */     slider.addMouseListener(new MouseListener()
/*      */         {
/*      */           public void mouseClicked(MouseEvent e) {}
/*      */ 
/*      */ 
/*      */           
/*      */           public void mousePressed(MouseEvent e) {}
/*      */ 
/*      */ 
/*      */           
/*      */           public void mouseReleased(MouseEvent e) {
/* 3237 */             Tu_yuan.dk_ruo_guang = slider.getValue();
/* 3238 */             Runnable runnable = new Runnable() {
/*      */                 public void run() {
/* 3240 */                   int rg = slider.getValue() * 2;
/* 3241 */                   int jd = comboBox.getSelectedIndex();
/* 3242 */                   if (Quan_ju.she_bei.yi_lian_jie.booleanValue()) {
/* 3243 */                     Quan_ju.she_bei.ming_ling_she_zhi(rg, jd);
/*      */                   }
/*      */                 }
/*      */               };
/* 3247 */             Thread thread = new Thread(runnable);
/*      */             
/* 3249 */             thread.start();
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public void mouseEntered(MouseEvent e) {}
/*      */ 
/*      */ 
/*      */           
/*      */           public void mouseExited(MouseEvent e) {}
/*      */         });
/* 3261 */     but.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent ae) {
/* 3264 */             Runnable runnable = new Runnable() {
/*      */                 public void run() {
/* 3266 */                   int rg = slider.getValue() * 2;
/* 3267 */                   int jd = comboBox.getSelectedIndex();
/* 3268 */                   if (Quan_ju.she_bei.yi_lian_jie.booleanValue())
/* 3269 */                     Quan_ju.she_bei.ming_ling_she_zhi(rg, jd); 
/*      */                 }
/*      */               };
/* 3272 */             Thread thread = new Thread(runnable);
/*      */             
/* 3274 */             thread.start();
/* 3275 */             win.dispose();
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */   
/*      */   private void jButton4ActionPerformed(ActionEvent evt) {
/* 3282 */     fan_se();
/*      */   }
/*      */ 
/*      */   
/*      */   private void jButton19ActionPerformed(ActionEvent evt) {
/*      */     try {
/* 3288 */       Geng_xin.browse2("http://www.dkjxz.com");
/* 3289 */     } catch (Exception ex) {
/* 3290 */       Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void jCheckBox1ActionPerformed(ActionEvent evt) {
/* 3296 */     Hua_ban.suo = this.jCheckBox1.isSelected();
/* 3297 */     up();
/*      */   }
/*      */ 
/*      */   
/*      */   private void jCheckBox2ActionPerformed(ActionEvent evt) {
/* 3302 */     tian_chong();
/*      */   }
/*      */ 
/*      */   
/*      */   private void jLabel9MouseClicked(MouseEvent evt) {
/* 3307 */     if (evt.getY() > this.jLabel9.getHeight() / 2) {
/*      */       
/* 3309 */       this.jTextField4.setText(String.valueOf(Integer.parseInt(this.jTextField4.getText()) - 1));
/*      */     } else {
/*      */       
/* 3312 */       this.jTextField4.setText(String.valueOf(Integer.parseInt(this.jTextField4.getText()) + 1));
/*      */     } 
/* 3314 */     set();
/*      */   }
/*      */ 
/*      */   
/*      */   private void jLabel8MouseClicked(MouseEvent evt) {
/* 3319 */     if (evt.getY() > this.jLabel8.getHeight() / 2) {
/*      */       
/* 3321 */       this.jTextField3.setText(String.valueOf(Integer.parseInt(this.jTextField3.getText()) - 1));
/*      */     } else {
/*      */       
/* 3324 */       this.jTextField3.setText(String.valueOf(Integer.parseInt(this.jTextField3.getText()) + 1));
/*      */     } 
/* 3326 */     set();
/*      */   }
/*      */ 
/*      */   
/*      */   private void jComboBox1ActionPerformed(ActionEvent evt) {
/* 3331 */     Quan_ju.diao_ke_ci_shu = this.jComboBox1.getSelectedIndex() + 1;
/* 3332 */     if (Quan_ju.qu_1064().booleanValue()) {
/* 3333 */       Quan_ju.diao_ke_ci_shu |= 0x80;
/*      */     } else {
/* 3335 */       Quan_ju.diao_ke_ci_shu &= 0x7F;
/*      */     } 
/*      */   }
/*      */   List<Dian> dian_ya_suo(List<Dian> d) {
/* 3339 */     List<Dian> e = new ArrayList<>();
/* 3340 */     Dian f = new Dian(0, 0);
/* 3341 */     for (int i = 0; i < d.size(); i++) {
/*      */       
/* 3343 */       f = new Dian(((Dian)d.get(i)).x + Tu_yuan.zui_zhong_wjx.x, ((Dian)d.get(i)).y + Tu_yuan.zui_zhong_wjx.y);
/* 3344 */       e.add(f);
/*      */     } 
/* 3346 */     d = e;
/* 3347 */     if (d.size() < 1975)
/*      */     {
/* 3349 */       return d;
/*      */     }
/*      */     
/* 3352 */     double bi = d.size() / 1975.0D;
/* 3353 */     List<Dian> fh = new ArrayList<>();
/* 3354 */     for (int j = 0; j < 1975; j++)
/*      */     {
/* 3356 */       fh.add(d.get((int)(j * bi)));
/*      */     }
/* 3358 */     return fh;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void qu_yu() {
/* 3364 */     GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)Hua_ban.ty_shuzu.get(0)).lu_jing);
/* 3365 */     lu_jing2.transform(((Tu_yuan)Hua_ban.ty_shuzu.get(0)).Tx);
/* 3366 */     Rectangle rect = lu_jing2.getBounds();
/* 3367 */     System.out.print(rect);
/* 3368 */     if (rect.width > this.hua_ban1.getWidth() || rect.height > this.hua_ban1.getHeight()) {
/*      */       double b;
/*      */       
/* 3371 */       if (rect.width - this.hua_ban1.getWidth() > rect.height - this.hua_ban1.getHeight()) {
/*      */         
/* 3373 */         b = this.hua_ban1.getWidth() / rect.width;
/*      */       } else {
/*      */         
/* 3376 */         b = this.hua_ban1.getHeight() / rect.height;
/*      */       } 
/* 3378 */       Hua_ban.quan_beishu *= b;
/* 3379 */       AffineTransform sf = AffineTransform.getScaleInstance(b, b);
/* 3380 */       for (int j = 0; j < Hua_ban.ty_shuzu.size(); j++) {
/*      */         
/* 3382 */         AffineTransform fb = new AffineTransform(sf);
/* 3383 */         fb.concatenate(((Tu_yuan)Hua_ban.ty_shuzu.get(j)).Tx);
/* 3384 */         ((Tu_yuan)Hua_ban.ty_shuzu.get(j)).Tx = fb;
/*      */       } 
/*      */     } 
/* 3387 */     lu_jing2 = new GeneralPath(((Tu_yuan)Hua_ban.ty_shuzu.get(0)).lu_jing);
/* 3388 */     lu_jing2.transform(((Tu_yuan)Hua_ban.ty_shuzu.get(0)).Tx);
/* 3389 */     rect = lu_jing2.getBounds();
/* 3390 */     int x1 = rect.x + rect.width / 2, y1 = rect.y + rect.height / 2, x2 = this.hua_ban1.getWidth() / 2, y2 = this.hua_ban1.getHeight() / 2;
/*      */     
/* 3392 */     AffineTransform sf2 = AffineTransform.getTranslateInstance((x2 - x1), (y2 - y1));
/* 3393 */     for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */       
/* 3395 */       AffineTransform fb = new AffineTransform(sf2);
/* 3396 */       fb.concatenate(((Tu_yuan)Hua_ban.ty_shuzu.get(i)).Tx);
/* 3397 */       ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).Tx = fb;
/*      */     } 
/*      */     
/* 3400 */     up();
/*      */   }
/*      */   
/*      */   boolean kai_shi_tuo_ji(int shan_qu, int ban_ben, int kuan_wt, int gao_wt, int weizhi_wt, int gonglv_wt, int shendu_wt, int kuan_sl, int gao_sl, int weizhi_sl, int gonglv_sl, int shendu_sl, int dianshu_sl, int z, int s, int ci_shu, int z_sl, int s_sl) {
/* 3404 */     byte[] sz = { 35, 0, 38, (byte)(shan_qu >> 8), (byte)shan_qu, (byte)ban_ben, (byte)(kuan_wt >> 8), (byte)kuan_wt, (byte)(gao_wt >> 8), (byte)gao_wt, (byte)(weizhi_wt >> 8), (byte)weizhi_wt, (byte)(gonglv_wt >> 8), (byte)gonglv_wt, (byte)(shendu_wt >> 8), (byte)shendu_wt, (byte)(kuan_sl >> 8), (byte)kuan_sl, (byte)(gao_sl >> 8), (byte)gao_sl, (byte)(weizhi_sl >> 24), (byte)(weizhi_sl >> 16), (byte)(weizhi_sl >> 8), (byte)weizhi_sl, (byte)(gonglv_sl >> 8), (byte)gonglv_sl, (byte)(shendu_sl >> 8), (byte)shendu_sl, (byte)(dianshu_sl >> 24), (byte)(dianshu_sl >> 16), (byte)(dianshu_sl >> 8), (byte)dianshu_sl, (byte)(z >> 8), (byte)z, (byte)(s >> 8), (byte)s, (byte)ci_shu, 0 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3440 */     if (this.com_dakai)
/* 3441 */       return com2.fa_song_fe(sz, 2); 
/* 3442 */     return false;
/*      */   }
/*      */   
/*      */   boolean xie_ru(byte[] m, int chao_shi) {
/* 3446 */     if (this.com_dakai)
/*      */     {
/* 3448 */       return com2.fa_song(m, chao_shi);
/*      */     }
/* 3450 */     return false;
/*      */   }
/*      */   byte jiao_yan(byte[] bao) {
/* 3453 */     int sum = 0;
/* 3454 */     for (int i = 0; i < bao.length - 1; i++) {
/* 3455 */       int a = 0xFF & bao[i];
/* 3456 */       sum += a;
/*      */     } 
/* 3458 */     if (sum > 255) {
/* 3459 */       sum ^= 0xFFFFFFFF;
/* 3460 */       sum++;
/*      */     } 
/* 3462 */     sum &= 0xFF;
/* 3463 */     return (byte)sum;
/*      */   }
/*      */ 
/*      */   
/*      */   int jiao_yan2(byte[] m) {
/* 3468 */     int jiao = 0;
/* 3469 */     for (int i = 0; i < m.length; i++)
/*      */     {
/* 3471 */       jiao += m[i];
/*      */     }
/* 3473 */     if (jiao > 255) {
/*      */       
/* 3475 */       jiao ^= 0xFFFFFFFF;
/* 3476 */       jiao++;
/*      */     } 
/* 3478 */     jiao &= 0xFF;
/* 3479 */     return jiao;
/*      */   } public mainJFrame() {
/* 3481 */     this.bao = new ArrayList<>();
/* 3482 */     this.fa_wan = Boolean.valueOf(false);
/*      */     initComponents();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void diao_ke(BufferedImage tu, int tu_x, int tu_y, List<Dian> shi_liang, int shi_liang_x, int shi_liang_y, int shi_liang_kuan, int shi_liang_gao) {
/* 3498 */     int dian_shu, len_tu = 0, kuan_b = 0;
/* 3499 */     int len_shi_liang = 0;
/* 3500 */     int k = 0, g = 0;
/* 3501 */     this.bao.clear();
/* 3502 */     this.fa_wan = Boolean.valueOf(false);
/* 3503 */     if (tu != null) {
/*      */       
/* 3505 */       k = tu.getWidth(); g = tu.getHeight();
/* 3506 */       if (tu.getWidth() % 8 > 0) {
/*      */         
/* 3508 */         kuan_b = tu.getWidth() / 8 + 1;
/*      */       }
/*      */       else {
/*      */         
/* 3512 */         kuan_b = tu.getWidth() / 8;
/*      */       } 
/* 3514 */       len_tu = kuan_b * tu.getHeight();
/*      */     } 
/*      */     
/* 3517 */     if (shi_liang == null) {
/* 3518 */       dian_shu = 0;
/*      */     } else {
/* 3520 */       dian_shu = shi_liang.size();
/* 3521 */     }  len_shi_liang = dian_shu * 4;
/* 3522 */     int shan_qu = (34 + len_tu + len_shi_liang) / 4094 + 1;
/* 3523 */     int wei_zhi_sl = 34 + len_tu;
/*      */     
/* 3525 */     Quan_ju.she_bei.ming_ling_qing_flash(shan_qu, 1, tu_x, tu_y, k, g, 34, Quan_ju.gong_lv_wt, Quan_ju.shen_du_wt, shi_liang_x, shi_liang_y, shi_liang_kuan, shi_liang_gao, wei_zhi_sl, Quan_ju.gong_lv_wt, Quan_ju.shen_du_wt, dian_shu, Quan_ju.diao_ke_ci_shu);
/* 3526 */     if (tu != null) {
/*      */       
/* 3528 */       int[] pixels = new int[k * g];
/* 3529 */       tu.getRGB(0, 0, k, g, pixels, 0, k);
/* 3530 */       byte[] yi = { Byte.MIN_VALUE, 64, 32, 16, 8, 4, 2, 1 };
/* 3531 */       for (int j = 0; j < g; j++) {
/*      */         
/* 3533 */         if (Quan_ju.ting_zhi)
/* 3534 */           return;  for (int m = 0; m < kuan_b; m++) {
/*      */           
/* 3536 */           if (Quan_ju.ting_zhi)
/* 3537 */             return;  int ba = 0;
/* 3538 */           byte bl = 0;
/* 3539 */           for (ba = 0; ba < 8; ba++) {
/*      */             
/* 3541 */             if (m * 8 + ba < k) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 3547 */               int clr = pixels[j * k + m * 8 + ba];
/* 3548 */               clr = (clr & 0xFF0000) >> 16;
/* 3549 */               if (clr < 10)
/*      */               {
/*      */                 
/* 3552 */                 bl = (byte)(bl | yi[ba]);
/*      */               }
/*      */             } 
/*      */           } 
/* 3556 */           this.bao.add(Byte.valueOf(bl));
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 3561 */     for (int i = 0; i < dian_shu; i++) {
/*      */       
/* 3563 */       if (Quan_ju.ting_zhi)
/* 3564 */         return;  Dian d = shi_liang.get(i);
/* 3565 */       this.bao.add(Byte.valueOf((byte)(d.x >> 8)));
/* 3566 */       this.bao.add(Byte.valueOf((byte)d.x));
/* 3567 */       this.bao.add(Byte.valueOf((byte)(d.y >> 8)));
/* 3568 */       this.bao.add(Byte.valueOf((byte)d.y));
/*      */     } 
/* 3570 */     int bao_hao = 0;
/* 3571 */     int xu_hao = 0;
/* 3572 */     int chao_shi = 0;
/* 3573 */     long shi_jian = 0L, shi_jian2 = 0L;
/*      */     
/* 3575 */     fa_song(0);
/*      */     try {
/* 3577 */       Thread.sleep(1L);
/* 3578 */     } catch (InterruptedException ex) {
/* 3579 */       Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*      */     } 
/*      */     do {
/* 3582 */       if (Quan_ju.ting_zhi)
/* 3583 */         return;  if (Quan_ju.diao_ke_ji.qu_zhuang_tai() == 2) {
/*      */         
/* 3585 */         if (xu_hao != Quan_ju.diao_ke_ji.bao_xu_hao) {
/*      */           
/* 3587 */           fa_song(Quan_ju.diao_ke_ji.bao_xu_hao);
/*      */           
/* 3589 */           System.out.println(Quan_ju.diao_ke_ji.bao_xu_hao + " ===");
/* 3590 */           xu_hao = Quan_ju.diao_ke_ji.bao_xu_hao;
/*      */ 
/*      */         
/*      */         }
/* 3594 */         else if (chao_shi++ > 250) {
/*      */           
/* 3596 */           fa_song(Quan_ju.diao_ke_ji.bao_xu_hao);
/* 3597 */           chao_shi = 0;
/*      */           
/* 3599 */           System.out.println("超时重发！->" + Quan_ju.diao_ke_ji.bao_xu_hao);
/*      */         } 
/*      */         
/*      */         try {
/* 3603 */           Thread.sleep(10L);
/* 3604 */         } catch (InterruptedException ex) {
/* 3605 */           Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*      */         }
/*      */       
/* 3608 */       } else if (Quan_ju.diao_ke_ji.qu_zhuang_tai() == 3) {
/*      */         
/*      */         try {
/* 3611 */           Thread.sleep(1L);
/* 3612 */         } catch (InterruptedException ex) {
/* 3613 */           Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*      */         } 
/*      */       } 
/* 3616 */       Quan_ju.jin_du = (int)(Quan_ju.diao_ke_ji.bao_xu_hao * 4000.0D / this.bao.size() * 100.0D);
/* 3617 */     } while (!this.fa_wan.booleanValue());
/*      */     
/* 3619 */     Quan_ju.she_bei.ming_ling_diao_ke();
/*      */     
/* 3621 */     shi_jian = shi_jian2 - shi_jian;
/* 3622 */     shi_jian = shi_jian / 10000L / 1000L;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void fa_song2(int bao_xu_hao) {
/* 3630 */     List<Byte> bao_m = new ArrayList<>();
/* 3631 */     if (bao_xu_hao * 2000 + 2000 < this.bao.size()) {
/*      */       
/* 3633 */       bao_m.addAll(this.bao.subList(bao_xu_hao * 2000, bao_xu_hao * 2000 + 2000));
/* 3634 */       bao_m.add(0, Byte.valueOf((byte)-85));
/* 3635 */       bao_m.add(1, Byte.valueOf((byte)101));
/* 3636 */       bao_m.add(2, Byte.valueOf((byte)15));
/* 3637 */       bao_m.add(3, Byte.valueOf((byte)-88));
/* 3638 */       bao_m.add(4, Byte.valueOf((byte)(bao_xu_hao >> 8)));
/* 3639 */       bao_m.add(5, Byte.valueOf((byte)bao_xu_hao));
/* 3640 */       bao_m.add(Byte.valueOf((byte)0));
/* 3641 */       bao_m.add(Byte.valueOf((byte)0));
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3651 */       int len = this.bao.size() - bao_xu_hao * 2000;
/* 3652 */       if (len > 0) {
/*      */ 
/*      */         
/* 3655 */         bao_m = this.bao.subList(bao_xu_hao * 2000, bao_xu_hao * 2000 + len);
/* 3656 */         len += 8;
/* 3657 */         bao_m.add(0, Byte.valueOf((byte)-85));
/* 3658 */         bao_m.add(1, Byte.valueOf((byte)101));
/* 3659 */         bao_m.add(2, Byte.valueOf((byte)(len >> 8)));
/* 3660 */         bao_m.add(3, Byte.valueOf((byte)len));
/* 3661 */         bao_m.add(4, Byte.valueOf((byte)(bao_xu_hao >> 8)));
/* 3662 */         bao_m.add(5, Byte.valueOf((byte)bao_xu_hao));
/* 3663 */         bao_m.add(Byte.valueOf((byte)0));
/* 3664 */         bao_m.add(Byte.valueOf((byte)0));
/*      */       }
/*      */       else {
/*      */         
/* 3668 */         this.fa_wan = Boolean.valueOf(true);
/*      */         return;
/*      */       } 
/*      */     } 
/* 3672 */     byte[] sz = new byte[bao_m.size()];
/* 3673 */     for (int i = 0; i < bao_m.size(); i++)
/*      */     {
/* 3675 */       sz[i] = ((Byte)bao_m.get(i)).byteValue();
/*      */     }
/* 3677 */     Quan_ju.she_bei.ming_ling_fa_shu_ju(sz);
/*      */   }
/*      */   
/*      */   void fa_song(int bao_xu_hao) {
/* 3681 */     List<Byte> bao_m = new ArrayList<>();
/* 3682 */     if (bao_xu_hao * 4000 + 4000 < this.bao.size()) {
/*      */       
/* 3684 */       bao_m.addAll(this.bao.subList(bao_xu_hao * 4000, bao_xu_hao * 4000 + 4000));
/* 3685 */       bao_m.add(0, Byte.valueOf((byte)-85));
/* 3686 */       bao_m.add(1, Byte.valueOf((byte)101));
/* 3687 */       bao_m.add(2, Byte.valueOf((byte)15));
/* 3688 */       bao_m.add(3, Byte.valueOf((byte)-88));
/* 3689 */       bao_m.add(4, Byte.valueOf((byte)(bao_xu_hao >> 8)));
/* 3690 */       bao_m.add(5, Byte.valueOf((byte)bao_xu_hao));
/* 3691 */       bao_m.add(Byte.valueOf((byte)0));
/* 3692 */       bao_m.add(Byte.valueOf((byte)0));
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3702 */       int len = this.bao.size() - bao_xu_hao * 4000;
/* 3703 */       if (len > 0) {
/*      */ 
/*      */         
/* 3706 */         bao_m = this.bao.subList(bao_xu_hao * 4000, bao_xu_hao * 4000 + len);
/* 3707 */         len += 8;
/* 3708 */         bao_m.add(0, Byte.valueOf((byte)-85));
/* 3709 */         bao_m.add(1, Byte.valueOf((byte)101));
/* 3710 */         bao_m.add(2, Byte.valueOf((byte)(len >> 8)));
/* 3711 */         bao_m.add(3, Byte.valueOf((byte)len));
/* 3712 */         bao_m.add(4, Byte.valueOf((byte)(bao_xu_hao >> 8)));
/* 3713 */         bao_m.add(5, Byte.valueOf((byte)bao_xu_hao));
/* 3714 */         bao_m.add(Byte.valueOf((byte)0));
/* 3715 */         bao_m.add(Byte.valueOf((byte)0));
/*      */       }
/*      */       else {
/*      */         
/* 3719 */         this.fa_wan = Boolean.valueOf(true);
/*      */         return;
/*      */       } 
/*      */     } 
/* 3723 */     byte[] sz = new byte[bao_m.size()];
/* 3724 */     for (int i = 0; i < bao_m.size(); i++)
/*      */     {
/* 3726 */       sz[i] = ((Byte)bao_m.get(i)).byteValue();
/*      */     }
/* 3728 */     Quan_ju.she_bei.ming_ling_fa_shu_ju(sz);
/*      */   }
/*      */   
/*      */   void tuo_ji() {
/* 3732 */     int i = 0, j = 0, jishu = 0, k = 0, g = 0, k_sl = 0, g_sl = 0, wz_sl = 33, k_len = 0;
/* 3733 */     boolean wt_ = false, sl_ = false, cuo = true;
/* 3734 */     byte bl = 0;
/* 3735 */     byte[] bao = null;
/* 3736 */     int len = 0;
/* 3737 */     Tu_yuan.hui_fu();
/* 3738 */     BufferedImage tu_diaoke = Tu_yuan.qu_tu(Hua_ban.ty_shuzu);
/* 3739 */     List<Dian> dian = Tu_yuan.qu_dian(Hua_ban.ty_shuzu);
/* 3740 */     Tu_yuan.hui_fu_xian_chang();
/* 3741 */     if (tu_diaoke == null && dian == null)
/* 3742 */       return;  diao_ke(tu_diaoke, Tu_yuan.zui_zhong_wjx.x + Tu_yuan.zui_zhong_wjx.width / 2, Tu_yuan.zui_zhong_wjx.y + Tu_yuan.zui_zhong_wjx.height / 2, dian, Tu_yuan.zui_zhong_wjx.x + Tu_yuan.zui_zhong_wjx.width / 2, Tu_yuan.zui_zhong_wjx.y + Tu_yuan.zui_zhong_wjx.height / 2, Tu_yuan.zui_zhong_wjx.width, Tu_yuan.zui_zhong_wjx.height);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void tuo_ji2() {
/* 3748 */     int i = 0, j = 0, jishu = 0, k = 0, g = 0, k_sl = 0, g_sl = 0, wz_sl = 33, k_len = 0;
/*      */     
/* 3750 */     boolean wt_ = false, sl_ = false, cuo = true;
/* 3751 */     byte bl = 0;
/* 3752 */     byte[] bao = null;
/* 3753 */     int len = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3761 */     Tu_yuan.hui_fu();
/* 3762 */     BufferedImage tu_diaoke2 = Tu_yuan.qu_tu(Hua_ban.ty_shuzu);
/* 3763 */     List<Dian> dian = Tu_yuan.qu_dian(Hua_ban.ty_shuzu);
/* 3764 */     Tu_yuan.hui_fu_xian_chang();
/*      */ 
/*      */ 
/*      */     
/* 3768 */     if (tu_diaoke2 == null && dian == null) {
/*      */       
/* 3770 */       this.jButton15.setEnabled(true);
/*      */       return;
/*      */     } 
/* 3773 */     jishu = 0;
/* 3774 */     if (tu_diaoke2 != null) {
/*      */       
/* 3776 */       g = tu_diaoke2.getHeight();
/* 3777 */       k = tu_diaoke2.getWidth();
/* 3778 */       if (tu_diaoke2.getWidth() % 8 > 0) {
/*      */         
/* 3780 */         bao = new byte[tu_diaoke2.getWidth() / 8 + 1];
/* 3781 */         k_len = tu_diaoke2.getWidth() / 8 + 1;
/*      */       }
/*      */       else {
/*      */         
/* 3785 */         bao = new byte[tu_diaoke2.getWidth() / 8];
/* 3786 */         k_len = tu_diaoke2.getWidth() / 8;
/*      */       } 
/* 3788 */       wz_sl = 33 + g * bao.length;
/* 3789 */       wt_ = true;
/* 3790 */       len = bao.length;
/*      */     } else {
/* 3792 */       wz_sl = 33;
/*      */     } 
/* 3794 */     if (dian != null) {
/*      */       
/* 3796 */       k_sl = Tu_yuan.zui_zhong_wjx.width;
/* 3797 */       g_sl = Tu_yuan.zui_zhong_wjx.height;
/* 3798 */       sl_ = true;
/*      */     } else {
/* 3800 */       dian = new ArrayList<>();
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 3805 */     int z = Tu_yuan.zui_zhong_wjx.x + Tu_yuan.zui_zhong_wjx.width / 2;
/* 3806 */     int s = Tu_yuan.zui_zhong_wjx.y + Tu_yuan.zui_zhong_wjx.height / 2;
/* 3807 */     int z_sl = Tu_yuan.shi_liang_wjx.x + Tu_yuan.shi_liang_wjx.width / 2;
/* 3808 */     int s_sl = Tu_yuan.shi_liang_wjx.y + Tu_yuan.shi_liang_wjx.height / 2;
/*      */     
/* 3810 */     bao = new byte[wz_sl - 33 + dian.size() * 4];
/* 3811 */     kai_shi_tuo_ji((33 + len * g + dian.size() * 4) / 4094 + 1, 1, k, g, 33, this.jSlider1.getValue() * 10, 100 - this.jSlider2.getValue(), k_sl, g_sl, wz_sl, this.jSlider1.getValue() * 10, 100 - this.jSlider2.getValue(), dian.size(), z, s, this.jComboBox1.getSelectedIndex() + 1, z_sl, s_sl);
/*      */     
/*      */     try {
/* 3814 */       Thread.sleep((70 * ((33 + len * g + dian.size() * 4) / 4094 + 1)));
/* 3815 */     } catch (InterruptedException ex) {
/* 3816 */       Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*      */     } 
/* 3818 */     xie_ru(new byte[] { 10, 0, 4, 0 }, 1);
/*      */     try {
/* 3820 */       Thread.sleep(500L);
/* 3821 */     } catch (InterruptedException ex) {
/* 3822 */       Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*      */     } 
/* 3824 */     xie_ru(new byte[] { 10, 0, 4, 0 }, 1);
/*      */     try {
/* 3826 */       Thread.sleep(500L);
/* 3827 */     } catch (InterruptedException ex) {
/* 3828 */       Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3844 */     if (wt_) {
/*      */       
/* 3846 */       int[] pixels = new int[k * g];
/*      */       
/* 3848 */       tu_diaoke2.getRGB(0, 0, k, g, pixels, 0, k);
/* 3849 */       byte[] yi = { Byte.MIN_VALUE, 64, 32, 16, 8, 4, 2, 1 };
/* 3850 */       for (i = 0; i < g; i++) {
/*      */         
/* 3852 */         for (j = 0; j < k_len; j++) {
/*      */           
/* 3854 */           int ba = 0;
/* 3855 */           bl = 0;
/* 3856 */           for (ba = 0; ba < 8; ba++) {
/*      */             
/* 3858 */             if (i * k + j * 8 + ba < pixels.length && j * 8 + ba <= k) {
/*      */ 
/*      */               
/* 3861 */               int clr = pixels[i * k + j * 8 + ba];
/* 3862 */               clr = (clr & 0xFF0000) >> 16;
/* 3863 */               if (clr < 10)
/*      */               {
/*      */                 
/* 3866 */                 bl = (byte)(bl | yi[ba]);
/*      */               }
/*      */             } 
/*      */           } 
/*      */           
/* 3871 */           bao[jishu] = bl;
/* 3872 */           jishu++;
/*      */         } 
/*      */       } 
/*      */     } 
/* 3876 */     if (sl_)
/*      */     {
/* 3878 */       for (j = 0; j < dian.size(); j++) {
/*      */         
/* 3880 */         bao[jishu++] = (byte)((Dian)dian.get(j)).x;
/*      */         
/* 3882 */         bao[jishu++] = (byte)(((Dian)dian.get(j)).x >> 8);
/*      */         
/* 3884 */         bao[jishu++] = (byte)((Dian)dian.get(j)).y;
/*      */         
/* 3886 */         bao[jishu++] = (byte)(((Dian)dian.get(j)).y >> 8);
/*      */       } 
/*      */     }
/* 3889 */     int bao_chicuo = 1900;
/* 3890 */     byte[] bao2 = new byte[bao_chicuo + 4];
/* 3891 */     for (i = 0; i < bao.length / bao_chicuo; i++) {
/*      */       
/* 3893 */       for (j = 0; j < bao_chicuo; j++)
/*      */       {
/* 3895 */         bao2[j + 3] = bao[i * bao_chicuo + j];
/*      */       }
/*      */       
/* 3898 */       boolean chong_fa = false;
/*      */       
/*      */       do {
/* 3901 */         bao2[0] = 34;
/* 3902 */         bao2[1] = (byte)(bao2.length >> 8);
/* 3903 */         bao2[2] = (byte)bao2.length;
/* 3904 */         bao2[bao2.length - 1] = jiao_yan(bao2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3919 */         chong_fa = !xie_ru(bao2, 2);
/*      */       }
/* 3921 */       while (chong_fa);
/*      */       
/* 3923 */       this.jdt.setVisible(true);
/* 3924 */       this.jdt.setValue((int)(i / (bao.length / bao_chicuo) * 100.0F));
/*      */     } 
/* 3926 */     bao2 = new byte[1904];
/* 3927 */     int wei_bao_ge_shu = bao.length % bao_chicuo;
/* 3928 */     if (wei_bao_ge_shu > 0) {
/*      */       
/* 3930 */       for (i = 0; i < wei_bao_ge_shu; i++)
/*      */       {
/* 3932 */         bao2[i + 3] = bao[bao.length / bao_chicuo * bao_chicuo + i];
/*      */       }
/* 3934 */       for (i = 0; i < 1900 - bao.length % bao_chicuo; i++)
/*      */       {
/* 3936 */         bao2[bao.length % bao_chicuo + 3 + i] = -1;
/*      */       }
/* 3938 */       boolean chong_fa = false;
/*      */       
/*      */       do {
/* 3941 */         bao2[0] = 34;
/* 3942 */         bao2[1] = (byte)(bao2.length >> 8);
/* 3943 */         bao2[2] = (byte)bao2.length;
/* 3944 */         bao2[bao2.length - 1] = jiao_yan(bao2);
/*      */         
/* 3946 */         chong_fa = !xie_ru(bao2, 2);
/*      */       }
/* 3948 */       while (chong_fa);
/*      */     } 
/*      */     try {
/* 3951 */       Thread.sleep(200L);
/* 3952 */     } catch (InterruptedException ex) {
/* 3953 */       Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*      */     } 
/* 3955 */     xie_ru(new byte[] { 10, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0 }, 1);
/*      */     try {
/* 3957 */       Thread.sleep(200L);
/* 3958 */     } catch (InterruptedException ex) {
/* 3959 */       Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*      */     } 
/* 3961 */     xie_ru(new byte[] { 36, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0 }, 1);
/*      */     try {
/* 3963 */       Thread.sleep(500L);
/* 3964 */     } catch (InterruptedException ex) {
/* 3965 */       Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*      */     } 
/* 3967 */     kai_shi2 = true;
/* 3968 */     if (com2 != null) {
/*      */       
/* 3970 */       com2.jie_shou_ji_shu = 0;
/* 3971 */       com2.jie_shou_lei_xing = 3;
/* 3972 */       com2.jie_shou_huan_cun.clear();
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 3977 */     this.jdt.setVisible(false);
/* 3978 */     this.jButton15.setEnabled(true);
/*      */   }
/*      */ 
/*      */   
/*      */   void UI_() {
/* 3983 */     if ((Quan_ju.diao_ke_ji.ji_xing & 0x33) == 0) {
/*      */       
/* 3985 */       this.jButton22.setVisible(false);
/* 3986 */       this.bq_bao_cun1.setVisible(false);
/*      */     } else {
/*      */       
/* 3989 */       this.jButton22.setVisible(true);
/* 3990 */       this.bq_bao_cun1.setVisible(true);
/*      */     } 
/* 3992 */     if ((Quan_ju.diao_ke_ji.ji_xing & 0x4) > 0) {
/*      */       
/* 3994 */       this.jButton21.setVisible(true);
/*      */     } else {
/*      */       
/* 3997 */       this.jButton21.setVisible(false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean du_banben() {
/* 4113 */     final Object suo_ = new Object();
/* 4114 */     synchronized (this.suo_fhm) {
/*      */       
/* 4116 */       this.fan_hui_ma = false;
/*      */     } 
/*      */ 
/*      */     
/* 4120 */     Runnable runnable2 = new Runnable() {
/*      */         public void run() {
/* 4122 */           synchronized (suo_) {
/*      */             
/* 4124 */             for (int i = 200; i > 0; i--) {
/*      */               
/* 4126 */               synchronized (mainJFrame.this.suo_fhm) {
/*      */                 
/* 4128 */                 if (mainJFrame.this.fan_hui_ma) {
/*      */                   
/* 4130 */                   if (mainJFrame.com2.fan_hui_shu.length == 3) {
/*      */                     break;
/*      */                   }
/*      */ 
/*      */                   
/* 4135 */                   mainJFrame.this.fan_hui_ma = false;
/*      */                   
/*      */                   break;
/*      */                 } 
/*      */               } 
/*      */               try {
/* 4141 */                 Thread.sleep(10L);
/* 4142 */               } catch (InterruptedException ex) {
/* 4143 */                 Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         }
/*      */       };
/* 4149 */     Thread thread2 = new Thread(runnable2);
/*      */     
/* 4151 */     thread2.start();
/*      */     try {
/* 4153 */       Thread.sleep(100L);
/* 4154 */     } catch (InterruptedException ex) {
/* 4155 */       Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*      */     } 
/* 4157 */     synchronized (suo_) {
/*      */       
/* 4159 */       return this.fan_hui_ma;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void main(String[] args) {
/*      */     try {
/* 4174 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
/* 4175 */         if ("Nimbus".equals(info.getName())) {
/* 4176 */           UIManager.setLookAndFeel(info.getClassName());
/*      */           break;
/*      */         } 
/*      */       } 
/* 4180 */     } catch (ClassNotFoundException ex) {
/* 4181 */       Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 4182 */     } catch (InstantiationException ex) {
/* 4183 */       Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 4184 */     } catch (IllegalAccessException ex) {
/* 4185 */       Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 4186 */     } catch (UnsupportedLookAndFeelException ex) {
/* 4187 */       Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 4195 */       UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 4203 */     catch (Exception e) {
/* 4204 */       e.printStackTrace();
/*      */     } 
/*      */     
/* 4207 */     EventQueue.invokeLater(new Runnable() {
/*      */           public void run() {
/* 4209 */             (new mainJFrame()).setVisible(true);
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void set() {
/* 4279 */     if (this.jTextField1.getText() != "") {
/*      */       
/* 4281 */       double x = Integer.valueOf(this.jTextField1.getText()).intValue();
/* 4282 */       x -= this.hua_ban1.x;
/* 4283 */       x /= Quan_ju.diao_ke_ji.fen_bian_lv;
/* 4284 */       x *= Hua_ban.quan_beishu;
/* 4285 */       for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */         
/* 4287 */         if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong)
/*      */         {
/* 4289 */           ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).ping_yi((int)x, (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).lu_jing.getBounds()).y);
/*      */         }
/*      */       } 
/* 4292 */       up();
/*      */     } 
/* 4294 */     if (this.jTextField2.getText() != "") {
/*      */       
/* 4296 */       double x = Integer.valueOf(this.jTextField2.getText()).intValue();
/* 4297 */       x -= this.hua_ban1.y;
/* 4298 */       x /= Quan_ju.diao_ke_ji.fen_bian_lv;
/* 4299 */       x *= Hua_ban.quan_beishu;
/* 4300 */       for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */         
/* 4302 */         if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong)
/*      */         {
/* 4304 */           ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).ping_yi((((Tu_yuan)Hua_ban.ty_shuzu.get(i)).lu_jing.getBounds()).x, (int)x);
/*      */         }
/*      */       } 
/* 4307 */       up();
/*      */     } 
/* 4309 */     if (this.jTextField3.getText() != "") {
/*      */ 
/*      */       
/* 4312 */       double x = Integer.valueOf(this.jTextField3.getText()).intValue();
/* 4313 */       x /= this.hua_ban1.ww;
/* 4314 */       Rectangle rect = Tu_yuan.qu_jv_xing(Hua_ban.ty_shuzu);
/* 4315 */       for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */         
/* 4317 */         if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong) {
/*      */           
/* 4319 */           ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).ping_yi(-rect.x, -rect.y);
/* 4320 */           if (Hua_ban.suo) {
/* 4321 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).suo_fang(x, x);
/*      */           } else {
/* 4323 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).suo_fang(x, 1.0D);
/* 4324 */           }  ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).ping_yi(rect.x, rect.y);
/*      */         } 
/*      */       } 
/* 4327 */       up();
/*      */     } 
/* 4329 */     if (this.jTextField4.getText() != "") {
/*      */ 
/*      */       
/* 4332 */       double x = Integer.valueOf(this.jTextField4.getText()).intValue();
/* 4333 */       x /= this.hua_ban1.hh;
/* 4334 */       Rectangle rect = Tu_yuan.qu_jv_xing(Hua_ban.ty_shuzu);
/* 4335 */       for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */         
/* 4337 */         if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong) {
/*      */           
/* 4339 */           ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).ping_yi(-rect.x, -rect.y);
/* 4340 */           if (Hua_ban.suo) {
/* 4341 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).suo_fang(x, x);
/*      */           } else {
/* 4343 */             ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).suo_fang(1.0D, x);
/* 4344 */           }  ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).ping_yi(rect.x, rect.y);
/*      */         } 
/*      */       } 
/* 4347 */       up();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void keyPressed(KeyEvent e) {
/* 4353 */     int c = e.getKeyCode();
/* 4354 */     if (e.isControlDown() && e.getKeyCode() == 67) {
/* 4355 */       this.ty_fu_zhi.clear();
/* 4356 */       for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */         
/* 4358 */         if (((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong)
/*      */         {
/* 4360 */           this.ty_fu_zhi.add(Tu_yuan.fu_zhi(Hua_ban.ty_shuzu.get(i)));
/*      */         }
/*      */       } 
/* 4363 */       this.fu_zhi = true;
/* 4364 */     } else if (e.isControlDown() && e.getKeyCode() == 86) {
/* 4365 */       if (this.fu_zhi) {
/*      */         
/* 4367 */         for (int i = 0; i < this.ty_fu_zhi.size(); i++)
/*      */         {
/* 4369 */           Hua_ban.ty_shuzu.add(Tu_yuan.fu_zhi(this.ty_fu_zhi.get(i)));
/*      */         }
/* 4371 */         up();
/* 4372 */         Che_xiao.tian_jia();
/*      */       } 
/* 4374 */     } else if (e.isControlDown() && e.getKeyCode() == 65) {
/* 4375 */       for (int i = 1; i < Hua_ban.ty_shuzu.size(); i++)
/*      */       {
/* 4377 */         ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong = true;
/*      */       }
/* 4379 */       up();
/* 4380 */     } else if (e.isControlDown() && e.getKeyCode() == 90) {
/* 4381 */       Che_xiao.che_xiao();
/* 4382 */       up();
/* 4383 */     } else if (e.isControlDown() && e.getKeyCode() == 88) {
/* 4384 */       for (int i = 1; i < Hua_ban.ty_shuzu.size(); i++)
/*      */       {
/* 4386 */         ((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong = true;
/*      */       }
/* 4388 */       Che_xiao.chong_zuo();
/* 4389 */       up();
/*      */     } 
/*      */     
/* 4392 */     if (c == 10) {
/*      */       
/* 4394 */       set();
/* 4395 */     } else if (c == 127) {
/*      */       
/* 4397 */       List<Tu_yuan> sz = new ArrayList<>();
/* 4398 */       for (int i = 0; i < Hua_ban.ty_shuzu.size(); i++) {
/*      */         
/* 4400 */         if (!((Tu_yuan)Hua_ban.ty_shuzu.get(i)).xuan_zhong)
/*      */         {
/* 4402 */           sz.add(Hua_ban.ty_shuzu.get(i));
/*      */         }
/*      */       } 
/* 4405 */       Hua_ban.ty_shuzu = sz;
/* 4406 */       up();
/* 4407 */       Che_xiao.tian_jia();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void keyReleased(KeyEvent e) {}
/*      */   
/*      */   public void keyTyped(KeyEvent e) {}
/*      */ }


/* Location:              C:\diao\bin\diao.jar!\examples\mainJFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
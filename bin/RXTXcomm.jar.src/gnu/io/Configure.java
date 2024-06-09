/*     */ package gnu.io;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Button;
/*     */ import java.awt.Checkbox;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.Label;
/*     */ import java.awt.Panel;
/*     */ import java.awt.TextArea;
/*     */ import java.awt.TextField;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.io.FileOutputStream;
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
/*     */ class Configure
/*     */   extends Frame
/*     */ {
/*     */   Checkbox[] cb;
/*     */   Panel p1;
/*     */   static final int PORT_SERIAL = 1;
/*     */   static final int PORT_PARALLEL = 2;
/*     */   int PortType;
/*     */   String EnumMessage;
/*     */   
/*     */   private void saveSpecifiedPorts() {
/*  75 */     String str1, str2 = System.getProperty("java.home");
/*  76 */     String str3 = System.getProperty("path.separator", ":");
/*  77 */     String str4 = System.getProperty("file.separator", "/");
/*  78 */     String str5 = System.getProperty("line.separator");
/*     */ 
/*     */     
/*  81 */     if (this.PortType == 1) {
/*  82 */       str1 = str2 + str4 + "lib" + str4 + "gnu.io.rxtx.SerialPorts";
/*     */     
/*     */     }
/*  85 */     else if (this.PortType == 2) {
/*  86 */       str1 = str2 + "gnu.io.rxtx.ParallelPorts";
/*     */     }
/*     */     else {
/*     */       
/*  90 */       System.out.println("Bad Port Type!");
/*     */       return;
/*     */     } 
/*  93 */     System.out.println(str1);
/*     */     
/*     */     try {
/*  96 */       FileOutputStream fileOutputStream = new FileOutputStream(str1);
/*     */       
/*  98 */       for (byte b = 0; b < ''; b++) {
/*     */         
/* 100 */         if (this.cb[b].getState()) {
/*     */           
/* 102 */           String str = this.cb[b].getLabel() + str3;
/*     */           
/* 104 */           fileOutputStream.write(str.getBytes());
/*     */         } 
/*     */       } 
/* 107 */       fileOutputStream.write(str5.getBytes());
/* 108 */       fileOutputStream.close();
/*     */     }
/* 110 */     catch (IOException iOException) {
/*     */       
/* 112 */       System.out.println("IOException!");
/*     */     } 
/*     */   }
/*     */   
/*     */   void addCheckBoxes(String paramString) {
/*     */     byte b;
/* 118 */     for (b = 0; b < ''; b++) {
/* 119 */       if (this.cb[b] != null)
/* 120 */         this.p1.remove(this.cb[b]); 
/* 121 */     }  for (b = 1; b < ''; b++) {
/*     */       
/* 123 */       this.cb[b - 1] = new Checkbox(paramString + b);
/* 124 */       this.p1.add("NORTH", this.cb[b - 1]);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Configure() {
/*     */     String str2;
/*     */     this.PortType = 1;
/* 217 */     this.EnumMessage = "gnu.io.rxtx.properties has not been detected.\n\nThere is no consistant means of detecting ports on this operating System.  It is necessary to indicate which ports are valid on this system before proper port enumeration can happen.  Please check the ports that are valid on this system and select Save";
/*     */     char c1 = 'ʀ';
/*     */     char c2 = 'Ǡ';
/*     */     this.cb = new Checkbox[128];
/*     */     Frame frame = new Frame("Configure gnu.io.rxtx.properties");
/*     */     String str1 = System.getProperty("file.separator", "/");
/*     */     if (str1.compareTo("/") != 0) {
/*     */       str2 = "COM";
/*     */     } else {
/*     */       str2 = "/dev/";
/*     */     } 
/*     */     frame.setBounds(100, 50, c1, c2);
/*     */     frame.setLayout(new BorderLayout());
/*     */     this.p1 = new Panel();
/*     */     this.p1.setLayout(new GridLayout(16, 4));
/*     */     ActionListener actionListener = new ActionListener(this) {
/*     */         private final Configure this$0;
/*     */         
/*     */         public void actionPerformed(ActionEvent param1ActionEvent) {
/*     */           String str = param1ActionEvent.getActionCommand();
/*     */           if (str.equals("Save"))
/*     */             this.this$0.saveSpecifiedPorts(); 
/*     */         }
/*     */       };
/*     */     addCheckBoxes(str2);
/*     */     TextArea textArea = new TextArea(this.EnumMessage, 5, 50, 3);
/*     */     textArea.setSize(50, c1);
/*     */     textArea.setEditable(false);
/*     */     Panel panel = new Panel();
/*     */     panel.add(new Label("Port Name:"));
/*     */     TextField textField = new TextField(str2, 8);
/*     */     textField.addActionListener(new ActionListener(this, frame) {
/*     */           private final Frame val$f;
/*     */           private final Configure this$0;
/*     */           
/*     */           public void actionPerformed(ActionEvent param1ActionEvent) {
/*     */             this.this$0.addCheckBoxes(param1ActionEvent.getActionCommand());
/*     */             this.val$f.setVisible(true);
/*     */           }
/*     */         });
/*     */     panel.add(textField);
/*     */     Checkbox checkbox = new Checkbox("Keep Ports");
/*     */     panel.add(checkbox);
/*     */     Button[] arrayOfButton = new Button[6];
/*     */     byte b;
/*     */     int i;
/*     */     for (b = 0, i = 4; i < 129; i *= 2, b++) {
/*     */       arrayOfButton[b] = new Button("1-" + i);
/*     */       arrayOfButton[b].addActionListener(new ActionListener(this, frame) {
/*     */             private final Frame val$f;
/*     */             private final Configure this$0;
/*     */             
/*     */             public void actionPerformed(ActionEvent param1ActionEvent) {
/*     */               int i = Integer.parseInt(param1ActionEvent.getActionCommand().substring(2));
/*     */               for (byte b = 0; b < i; b++) {
/*     */                 this.this$0.cb[b].setState(!this.this$0.cb[b].getState());
/*     */                 this.val$f.setVisible(true);
/*     */               } 
/*     */             }
/*     */           });
/*     */       panel.add(arrayOfButton[b]);
/*     */     } 
/*     */     Button button1 = new Button("More");
/*     */     Button button2 = new Button("Save");
/*     */     button1.addActionListener(actionListener);
/*     */     button2.addActionListener(actionListener);
/*     */     panel.add(button1);
/*     */     panel.add(button2);
/*     */     frame.add("South", panel);
/*     */     frame.add("Center", this.p1);
/*     */     frame.add("North", textArea);
/*     */     frame.addWindowListener(new WindowAdapter(this) {
/*     */           private final Configure this$0;
/*     */           
/*     */           public void windowClosing(WindowEvent param1WindowEvent) {
/*     */             System.exit(0);
/*     */           }
/*     */         });
/*     */     frame.setVisible(true);
/*     */   }
/*     */   
/*     */   public static void main(String[] paramArrayOfString) {
/*     */     new Configure();
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\RXTXcomm.jar!\gnu\io\Configure.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package gnu.io;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.util.Properties;
/*     */ import java.util.StringTokenizer;
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
/*     */ public class RXTXCommDriver
/*     */   implements CommDriver
/*     */ {
/*     */   private static final boolean debug = false;
/*     */   private static final boolean devel = false;
/*  79 */   private static final boolean noVersionOutput = "true".equals(System.getProperty("gnu.io.rxtx.NoVersionOutput"));
/*     */   
/*     */   private String deviceDirectory;
/*     */   
/*     */   static {
/*  84 */     System.loadLibrary("rxtxSerial");
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
/*  96 */     String str1 = RXTXVersion.getVersion();
/*     */     
/*     */     try {
/*  99 */       str2 = RXTXVersion.nativeGetVersion();
/* 100 */     } catch (Error error) {
/*     */ 
/*     */       
/* 103 */       str2 = nativeGetVersion();
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
/* 116 */     if (!str1.equals(str2))
/*     */     {
/* 118 */       System.out.println("WARNING:  RXTX Version mismatch\n\tJar version = " + str1 + "\n\tnative lib Version = " + str2);
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
/*     */   private String osName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*     */     String str2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final String[] getValidPortPrefixes(String[] paramArrayOfString) {
/* 150 */     String[] arrayOfString1 = new String[256];
/*     */ 
/*     */     
/* 153 */     if (paramArrayOfString == null);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 158 */     byte b1 = 0;
/* 159 */     for (byte b2 = 0; b2 < paramArrayOfString.length; b2++) {
/* 160 */       if (isPortPrefixValid(paramArrayOfString[b2])) {
/* 161 */         arrayOfString1[b1++] = paramArrayOfString[b2];
/*     */       }
/*     */     } 
/*     */     
/* 165 */     String[] arrayOfString2 = new String[b1];
/* 166 */     System.arraycopy(arrayOfString1, 0, arrayOfString2, 0, b1);
/* 167 */     if (arrayOfString1[0] == null);
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
/* 191 */     return arrayOfString2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkSolaris(String paramString, int paramInt) {
/* 197 */     char[] arrayOfChar = { '[' };
/* 198 */     for (arrayOfChar[0] = 'a'; arrayOfChar[0] < '{'; arrayOfChar[0] = (char)(arrayOfChar[0] + 1)) {
/*     */       
/* 200 */       if (testRead(paramString.concat(new String(arrayOfChar)), paramInt))
/*     */       {
/* 202 */         CommPortIdentifier.addPortName(paramString.concat(new String(arrayOfChar)), paramInt, this);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 210 */     for (arrayOfChar[0] = '0'; arrayOfChar[0] <= '9'; arrayOfChar[0] = (char)(arrayOfChar[0] + 1)) {
/*     */       
/* 212 */       if (testRead(paramString.concat(new String(arrayOfChar)), paramInt))
/*     */       {
/* 214 */         CommPortIdentifier.addPortName(paramString.concat(new String(arrayOfChar)), paramInt, this);
/*     */       }
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
/*     */   private void registerValidPorts(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt) {
/* 227 */     byte b1 = 0;
/* 228 */     byte b2 = 0;
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
/* 255 */     if (paramArrayOfString1 != null && paramArrayOfString2 != null)
/*     */     {
/* 257 */       for (b1 = 0; b1 < paramArrayOfString1.length; b1++) {
/* 258 */         for (b2 = 0; b2 < paramArrayOfString2.length; b2++) {
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
/* 274 */           String str1 = paramArrayOfString2[b2];
/* 275 */           int i = str1.length();
/* 276 */           String str2 = paramArrayOfString1[b1];
/* 277 */           if (str2.length() >= i) {
/* 278 */             String str3 = str2.substring(i).toUpperCase();
/*     */             
/* 280 */             String str4 = str2.substring(i).toLowerCase();
/*     */             
/* 282 */             if (str2.regionMatches(0, str1, 0, i) && str3.equals(str4)) {
/*     */               String str;
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 288 */               if (this.osName.toLowerCase().indexOf("windows") == -1) {
/*     */                 
/* 290 */                 str = this.deviceDirectory + str2;
/*     */               }
/*     */               else {
/*     */                 
/* 294 */                 str = str2;
/*     */               } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 303 */               if (this.osName.equals("Solaris") || this.osName.equals("SunOS")) {
/*     */                 
/* 305 */                 checkSolaris(str, paramInt);
/* 306 */               } else if (testRead(str, paramInt)) {
/*     */                 
/* 308 */                 CommPortIdentifier.addPortName(str, paramInt, this);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
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
/*     */   public void initialize() {
/* 352 */     this.osName = System.getProperty("os.name");
/* 353 */     this.deviceDirectory = getDeviceDirectory();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 359 */     for (byte b = 1; b <= 2; b++) {
/* 360 */       if (!registerSpecifiedPorts(b) && 
/* 361 */         !registerKnownPorts(b)) {
/* 362 */         registerScannedPorts(b);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void addSpecifiedPorts(String paramString, int paramInt) {
/* 370 */     String str = System.getProperty("path.separator", ":");
/* 371 */     StringTokenizer stringTokenizer = new StringTokenizer(paramString, str);
/*     */ 
/*     */ 
/*     */     
/* 375 */     while (stringTokenizer.hasMoreElements()) {
/*     */       
/* 377 */       String str1 = stringTokenizer.nextToken();
/*     */       
/* 379 */       if (testRead(str1, paramInt)) {
/* 380 */         CommPortIdentifier.addPortName(str1, paramInt, this);
/*     */       }
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
/*     */   private boolean registerSpecifiedPorts(int paramInt) {
/* 404 */     String str = null;
/* 405 */     Properties properties = System.getProperties();
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 410 */       String str1 = System.getProperty("java.ext.dirs") + System.getProperty("file.separator");
/* 411 */       FileInputStream fileInputStream = new FileInputStream(str1 + "gnu.io.rxtx.properties");
/* 412 */       Properties properties1 = new Properties();
/* 413 */       properties1.load(fileInputStream);
/* 414 */       System.setProperties(properties1);
/* 415 */       for (String str2 : properties1.keySet())
/*     */       {
/* 417 */         System.setProperty(str2, properties1.getProperty(str2));
/*     */       }
/* 419 */     } catch (Exception exception) {}
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
/* 431 */     switch (paramInt) {
/*     */       case 1:
/* 433 */         if ((str = System.getProperty("gnu.io.rxtx.SerialPorts")) == null) {
/* 434 */           str = System.getProperty("gnu.io.SerialPorts");
/*     */         }
/*     */         break;
/*     */       case 2:
/* 438 */         if ((str = System.getProperty("gnu.io.rxtx.ParallelPorts")) == null) {
/* 439 */           str = System.getProperty("gnu.io.ParallelPorts");
/*     */         }
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 446 */     System.setProperties(properties);
/* 447 */     if (str != null) {
/* 448 */       addSpecifiedPorts(str, paramInt);
/* 449 */       return true;
/*     */     } 
/* 451 */     return false;
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
/*     */   private void registerScannedPorts(int paramInt) {
/*     */     String[] arrayOfString1, arrayOfString3;
/* 466 */     if (this.osName.equals("Windows CE")) {
/*     */       
/* 468 */       String[] arrayOfString = { "COM1:", "COM2:", "COM3:", "COM4:", "COM5:", "COM6:", "COM7:", "COM8:" };
/*     */ 
/*     */       
/* 471 */       arrayOfString1 = arrayOfString;
/*     */     }
/* 473 */     else if (this.osName.toLowerCase().indexOf("windows") != -1) {
/*     */       
/* 475 */       String[] arrayOfString = new String[259]; byte b;
/* 476 */       for (b = 1; b <= 'Ā'; b++)
/*     */       {
/* 478 */         arrayOfString[b - 1] = "COM" + b;
/*     */       }
/* 480 */       for (b = 1; b <= 3; b++)
/*     */       {
/* 482 */         arrayOfString[b + 255] = "LPT" + b;
/*     */       }
/* 484 */       arrayOfString1 = arrayOfString;
/*     */     }
/* 486 */     else if (this.osName.equals("Solaris") || this.osName.equals("SunOS")) {
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
/* 523 */       String[] arrayOfString4 = new String[2];
/* 524 */       byte b = 0;
/* 525 */       File file = null;
/*     */       
/* 527 */       file = new File("/dev/term");
/* 528 */       if ((file.list()).length > 0) {
/* 529 */         arrayOfString4[b++] = "term/";
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 535 */       String[] arrayOfString5 = new String[b];
/* 536 */       for (; --b >= 0; b--)
/* 537 */         arrayOfString5[b] = arrayOfString4[b]; 
/* 538 */       arrayOfString1 = arrayOfString5;
/*     */     }
/*     */     else {
/*     */       
/* 542 */       File file = new File(this.deviceDirectory);
/* 543 */       String[] arrayOfString = file.list();
/* 544 */       arrayOfString1 = arrayOfString;
/*     */     } 
/* 546 */     if (arrayOfString1 == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 553 */     String[] arrayOfString2 = new String[0];
/* 554 */     switch (paramInt) {
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
/*     */       case 1:
/* 574 */         if (this.osName.equals("Linux")) {
/*     */           
/* 576 */           String[] arrayOfString = { "ttyS", "ttySA", "ttyUSB", "rfcomm", "ttyircomm" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 583 */           arrayOfString2 = arrayOfString; break;
/*     */         } 
/* 585 */         if (this.osName.equals("Linux-all-ports")) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 590 */           String[] arrayOfString = { "comx", "holter", "modem", "rfcomm", "ttyircomm", "ttycosa0c", "ttycosa1c", "ttyACM", "ttyC", "ttyCH", "ttyD", "ttyE", "ttyF", "ttyH", "ttyI", "ttyL", "ttyM", "ttyMX", "ttyP", "ttyR", "ttyS", "ttySI", "ttySR", "ttyT", "ttyUSB", "ttyV", "ttyW", "ttyX" };
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
/* 622 */           arrayOfString2 = arrayOfString; break;
/*     */         } 
/* 624 */         if (this.osName.toLowerCase().indexOf("qnx") != -1) {
/*     */           
/* 626 */           String[] arrayOfString = { "ser" };
/*     */ 
/*     */           
/* 629 */           arrayOfString2 = arrayOfString; break;
/*     */         } 
/* 631 */         if (this.osName.equals("Irix")) {
/*     */           
/* 633 */           String[] arrayOfString = { "ttyc", "ttyd", "ttyf", "ttym", "ttyq", "tty4d", "tty4f", "midi", "us" };
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
/* 644 */           arrayOfString2 = arrayOfString; break;
/*     */         } 
/* 646 */         if (this.osName.equals("FreeBSD")) {
/*     */           
/* 648 */           String[] arrayOfString = { "ttyd", "cuaa", "ttyA", "cuaA", "ttyD", "cuaD", "ttyE", "cuaE", "ttyF", "cuaF", "ttyR", "cuaR", "stl" };
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
/* 663 */           arrayOfString2 = arrayOfString; break;
/*     */         } 
/* 665 */         if (this.osName.equals("NetBSD")) {
/*     */           
/* 667 */           String[] arrayOfString = { "tty0" };
/*     */ 
/*     */           
/* 670 */           arrayOfString2 = arrayOfString; break;
/*     */         } 
/* 672 */         if (this.osName.equals("Solaris") || this.osName.equals("SunOS")) {
/*     */ 
/*     */           
/* 675 */           String[] arrayOfString = { "term/", "cua/" };
/*     */ 
/*     */ 
/*     */           
/* 679 */           arrayOfString2 = arrayOfString; break;
/*     */         } 
/* 681 */         if (this.osName.equals("HP-UX")) {
/*     */           
/* 683 */           String[] arrayOfString = { "tty0p", "tty1p" };
/*     */ 
/*     */ 
/*     */           
/* 687 */           arrayOfString2 = arrayOfString;
/*     */           break;
/*     */         } 
/* 690 */         if (this.osName.equals("UnixWare") || this.osName.equals("OpenUNIX")) {
/*     */ 
/*     */           
/* 693 */           String[] arrayOfString = { "tty00s", "tty01s", "tty02s", "tty03s" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 699 */           arrayOfString2 = arrayOfString;
/*     */           break;
/*     */         } 
/* 702 */         if (this.osName.equals("OpenServer")) {
/*     */           
/* 704 */           String[] arrayOfString = { "tty1A", "tty2A", "tty3A", "tty4A", "tty5A", "tty6A", "tty7A", "tty8A", "tty9A", "tty10A", "tty11A", "tty12A", "tty13A", "tty14A", "tty15A", "tty16A", "ttyu1A", "ttyu2A", "ttyu3A", "ttyu4A", "ttyu5A", "ttyu6A", "ttyu7A", "ttyu8A", "ttyu9A", "ttyu10A", "ttyu11A", "ttyu12A", "ttyu13A", "ttyu14A", "ttyu15A", "ttyu16A" };
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
/* 738 */           arrayOfString2 = arrayOfString; break;
/*     */         } 
/* 740 */         if (this.osName.equals("Compaq's Digital UNIX") || this.osName.equals("OSF1")) {
/*     */           
/* 742 */           String[] arrayOfString = { "tty0" };
/*     */ 
/*     */           
/* 745 */           arrayOfString2 = arrayOfString;
/*     */           break;
/*     */         } 
/* 748 */         if (this.osName.equals("BeOS")) {
/*     */           
/* 750 */           String[] arrayOfString = { "serial" };
/*     */ 
/*     */           
/* 753 */           arrayOfString2 = arrayOfString; break;
/*     */         } 
/* 755 */         if (this.osName.equals("Mac OS X")) {
/*     */           
/* 757 */           String[] arrayOfString = { "cu.KeyUSA28X191.", "tty.KeyUSA28X191.", "cu.KeyUSA28X181.", "tty.KeyUSA28X181.", "cu.KeyUSA19181.", "tty.KeyUSA19181." };
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
/* 771 */           arrayOfString2 = arrayOfString; break;
/*     */         } 
/* 773 */         if (this.osName.toLowerCase().indexOf("windows") != -1) {
/*     */           
/* 775 */           String[] arrayOfString = { "COM" };
/*     */ 
/*     */ 
/*     */           
/* 779 */           arrayOfString2 = arrayOfString;
/*     */         } 
/*     */         break;
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
/*     */       case 2:
/* 796 */         if (this.osName.equals("Linux")) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 806 */           String[] arrayOfString = { "lp" };
/*     */ 
/*     */           
/* 809 */           arrayOfString2 = arrayOfString; break;
/*     */         } 
/* 811 */         if (this.osName.equals("FreeBSD")) {
/*     */           
/* 813 */           String[] arrayOfString = { "lpt" };
/*     */ 
/*     */           
/* 816 */           arrayOfString2 = arrayOfString; break;
/*     */         } 
/* 818 */         if (this.osName.toLowerCase().indexOf("windows") != -1) {
/*     */           
/* 820 */           String[] arrayOfString = { "LPT" };
/*     */ 
/*     */           
/* 823 */           arrayOfString2 = arrayOfString;
/*     */           
/*     */           break;
/*     */         } 
/* 827 */         arrayOfString3 = new String[0];
/* 828 */         arrayOfString2 = arrayOfString3;
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 835 */     registerValidPorts(arrayOfString1, arrayOfString2, paramInt);
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
/*     */   public CommPort getCommPort(String paramString, int paramInt) {
/*     */     try {
/* 856 */       switch (paramInt) {
/*     */         case 1:
/* 858 */           if (this.osName.toLowerCase().indexOf("windows") == -1)
/*     */           {
/*     */             
/* 861 */             return new RXTXPort(paramString);
/*     */           }
/*     */ 
/*     */           
/* 865 */           return new RXTXPort(this.deviceDirectory + paramString);
/*     */         
/*     */         case 2:
/* 868 */           return new LPRPort(paramString);
/*     */       } 
/*     */ 
/*     */ 
/*     */     
/* 873 */     } catch (PortInUseException portInUseException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 878 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void Report(String paramString) {
/* 884 */     System.out.println(paramString);
/*     */   }
/*     */   
/*     */   private native boolean registerKnownPorts(int paramInt);
/*     */   
/*     */   private native boolean isPortPrefixValid(String paramString);
/*     */   
/*     */   private native boolean testRead(String paramString, int paramInt);
/*     */   
/*     */   private native String getDeviceDirectory();
/*     */   
/*     */   public static native String nativeGetVersion();
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\RXTXcomm.jar!\gnu\io\RXTXCommDriver.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
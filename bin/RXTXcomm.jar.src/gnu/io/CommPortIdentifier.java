/*     */ package gnu.io;
/*     */ 
/*     */ import java.io.FileDescriptor;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommPortIdentifier
/*     */ {
/*     */   public static final int PORT_SERIAL = 1;
/*     */   public static final int PORT_PARALLEL = 2;
/*     */   public static final int PORT_I2C = 3;
/*     */   public static final int PORT_RS485 = 4;
/*     */   public static final int PORT_RAW = 5;
/*     */   private String PortName;
/*     */   private boolean Available = true;
/*     */   private String Owner;
/*     */   private CommPort commport;
/*     */   private CommDriver RXTXDriver;
/*     */   static CommPortIdentifier CommPortIndex;
/*     */   CommPortIdentifier next;
/*     */   private int PortType;
/*     */   private static final boolean debug = false;
/* 104 */   static Object Sync = new Object(); Vector ownershipListener;
/*     */   static {
/*     */     try {
/* 107 */       CommDriver commDriver = (CommDriver)Class.forName("gnu.io.RXTXCommDriver").newInstance();
/* 108 */       commDriver.initialize();
/*     */     }
/* 110 */     catch (Throwable throwable) {
/*     */       
/* 112 */       System.err.println(throwable + " thrown while loading " + "gnu.io.RXTXCommDriver");
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 117 */     String str = System.getProperty("os.name");
/* 118 */     if (str.toLowerCase().indexOf("linux") == -1);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 123 */     System.loadLibrary("rxtxSerial");
/*     */   }
/*     */   private boolean HideOwnerEvents;
/*     */   CommPortIdentifier(String paramString, CommPort paramCommPort, int paramInt, CommDriver paramCommDriver) {
/* 127 */     this.PortName = paramString;
/* 128 */     this.commport = paramCommPort;
/* 129 */     this.PortType = paramInt;
/* 130 */     this.next = null;
/* 131 */     this.RXTXDriver = paramCommDriver;
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
/*     */   public static void addPortName(String paramString, int paramInt, CommDriver paramCommDriver) {
/* 148 */     AddIdentifierToList(new CommPortIdentifier(paramString, null, paramInt, paramCommDriver));
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
/*     */   private static void AddIdentifierToList(CommPortIdentifier paramCommPortIdentifier) {
/* 161 */     synchronized (Sync) {
/*     */       
/* 163 */       if (CommPortIndex == null) {
/*     */         
/* 165 */         CommPortIndex = paramCommPortIdentifier;
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 170 */         CommPortIdentifier commPortIdentifier = CommPortIndex;
/* 171 */         while (commPortIdentifier.next != null)
/*     */         {
/* 173 */           commPortIdentifier = commPortIdentifier.next;
/*     */         }
/*     */         
/* 176 */         commPortIdentifier.next = paramCommPortIdentifier;
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
/*     */   public void addPortOwnershipListener(CommPortOwnershipListener paramCommPortOwnershipListener) {
/* 194 */     if (this.ownershipListener == null)
/*     */     {
/* 196 */       this.ownershipListener = new Vector();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 201 */     if (!this.ownershipListener.contains(paramCommPortOwnershipListener))
/*     */     {
/* 203 */       this.ownershipListener.addElement(paramCommPortOwnershipListener);
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
/*     */   public String getCurrentOwner() {
/* 217 */     return this.Owner;
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
/*     */   public String getName() {
/* 230 */     return this.PortName;
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
/*     */   public static CommPortIdentifier getPortIdentifier(String paramString) throws NoSuchPortException {
/*     */     CommPortIdentifier commPortIdentifier;
/* 245 */     synchronized (Sync) {
/*     */       
/* 247 */       commPortIdentifier = CommPortIndex;
/* 248 */       while (commPortIdentifier != null && !commPortIdentifier.PortName.equals(paramString)) {
/* 249 */         commPortIdentifier = commPortIdentifier.next;
/*     */       }
/* 251 */       if (commPortIdentifier == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 257 */         getPortIdentifiers();
/* 258 */         commPortIdentifier = CommPortIndex;
/* 259 */         while (commPortIdentifier != null && !commPortIdentifier.PortName.equals(paramString)) {
/* 260 */           commPortIdentifier = commPortIdentifier.next;
/*     */         }
/*     */       } 
/*     */     } 
/* 264 */     if (commPortIdentifier != null) return commPortIdentifier;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 269 */     throw new NoSuchPortException();
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
/*     */   public static CommPortIdentifier getPortIdentifier(CommPort paramCommPort) throws NoSuchPortException {
/*     */     CommPortIdentifier commPortIdentifier;
/* 285 */     synchronized (Sync) {
/*     */       
/* 287 */       commPortIdentifier = CommPortIndex;
/* 288 */       while (commPortIdentifier != null && commPortIdentifier.commport != paramCommPort)
/* 289 */         commPortIdentifier = commPortIdentifier.next; 
/*     */     } 
/* 291 */     if (commPortIdentifier != null) {
/* 292 */       return commPortIdentifier;
/*     */     }
/*     */ 
/*     */     
/* 296 */     throw new NoSuchPortException();
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
/*     */   public static Enumeration getPortIdentifiers() {
/* 311 */     synchronized (Sync) {
/*     */       
/* 313 */       HashMap hashMap = new HashMap();
/* 314 */       CommPortIdentifier commPortIdentifier = CommPortIndex;
/* 315 */       while (commPortIdentifier != null) {
/* 316 */         hashMap.put(commPortIdentifier.PortName, commPortIdentifier);
/* 317 */         commPortIdentifier = commPortIdentifier.next;
/*     */       } 
/* 319 */       CommPortIndex = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 326 */         CommDriver commDriver = (CommDriver)Class.forName("gnu.io.RXTXCommDriver").newInstance();
/* 327 */         commDriver.initialize();
/*     */ 
/*     */ 
/*     */         
/* 331 */         CommPortIdentifier commPortIdentifier1 = CommPortIndex;
/* 332 */         CommPortIdentifier commPortIdentifier2 = null;
/* 333 */         while (commPortIdentifier1 != null) {
/* 334 */           CommPortIdentifier commPortIdentifier3 = (CommPortIdentifier)hashMap.get(commPortIdentifier1.PortName);
/* 335 */           if (commPortIdentifier3 != null && commPortIdentifier3.PortType == commPortIdentifier1.PortType) {
/*     */             
/* 337 */             commPortIdentifier3.RXTXDriver = commPortIdentifier1.RXTXDriver;
/* 338 */             commPortIdentifier3.next = commPortIdentifier1.next;
/* 339 */             if (commPortIdentifier2 == null) {
/* 340 */               CommPortIndex = commPortIdentifier3;
/*     */             } else {
/* 342 */               commPortIdentifier2.next = commPortIdentifier3;
/*     */             } 
/* 344 */             commPortIdentifier2 = commPortIdentifier3;
/*     */           } else {
/* 346 */             commPortIdentifier2 = commPortIdentifier1;
/*     */           } 
/* 348 */           commPortIdentifier1 = commPortIdentifier1.next;
/*     */         }
/*     */       
/* 351 */       } catch (Throwable throwable) {
/*     */         
/* 353 */         System.err.println(throwable + " thrown while loading " + "gnu.io.RXTXCommDriver");
/* 354 */         System.err.flush();
/*     */       } 
/*     */     } 
/* 357 */     return new CommPortEnumerator();
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
/*     */   public int getPortType() {
/* 370 */     return this.PortType;
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
/*     */   public synchronized boolean isCurrentlyOwned() {
/* 383 */     return !this.Available;
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
/*     */   public synchronized CommPort open(FileDescriptor paramFileDescriptor) throws UnsupportedCommOperationException {
/* 396 */     throw new UnsupportedCommOperationException();
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
/*     */   public CommPort open(String paramString, int paramInt) throws PortInUseException {
/*     */     boolean bool;
/* 416 */     synchronized (this) {
/* 417 */       bool = this.Available;
/* 418 */       if (bool) {
/*     */         
/* 420 */         this.Available = false;
/* 421 */         this.Owner = paramString;
/*     */       } 
/*     */     } 
/* 424 */     if (!bool) {
/*     */       
/* 426 */       long l = System.currentTimeMillis() + paramInt;
/*     */       
/* 428 */       fireOwnershipEvent(3);
/*     */       
/* 430 */       synchronized (this) {
/* 431 */         long l1; while (!this.Available && (l1 = System.currentTimeMillis()) < l) {
/*     */           
/*     */           try {
/* 434 */             wait(l - l1);
/*     */           }
/* 436 */           catch (InterruptedException interruptedException) {
/*     */             
/* 438 */             Thread.currentThread().interrupt();
/*     */             break;
/*     */           } 
/*     */         } 
/* 442 */         bool = this.Available;
/* 443 */         if (bool) {
/*     */           
/* 445 */           this.Available = false;
/* 446 */           this.Owner = paramString;
/*     */         } 
/*     */       } 
/*     */     } 
/* 450 */     if (!bool)
/*     */     {
/* 452 */       throw new PortInUseException(getCurrentOwner());
/*     */     }
/*     */     
/*     */     try {
/* 456 */       if (this.commport == null)
/*     */       {
/* 458 */         this.commport = this.RXTXDriver.getCommPort(this.PortName, this.PortType);
/*     */       }
/* 460 */       if (this.commport != null) {
/*     */         
/* 462 */         fireOwnershipEvent(1);
/* 463 */         return this.commport;
/*     */       } 
/*     */ 
/*     */       
/* 467 */       throw new PortInUseException(native_psmisc_report_owner(this.PortName));
/*     */     }
/*     */     finally {
/*     */       
/* 471 */       if (this.commport == null)
/*     */       {
/* 473 */         synchronized (this) {
/* 474 */           this.Available = true;
/* 475 */           this.Owner = null;
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
/*     */   public void removePortOwnershipListener(CommPortOwnershipListener paramCommPortOwnershipListener) {
/* 492 */     if (this.ownershipListener != null) {
/* 493 */       this.ownershipListener.removeElement(paramCommPortOwnershipListener);
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
/*     */   void internalClosePort() {
/* 506 */     synchronized (this) {
/*     */       
/* 508 */       this.Owner = null;
/* 509 */       this.Available = true;
/* 510 */       this.commport = null;
/*     */       
/* 512 */       notifyAll();
/*     */     } 
/* 514 */     fireOwnershipEvent(2);
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
/*     */   void fireOwnershipEvent(int paramInt) {
/* 527 */     if (this.ownershipListener != null) {
/*     */ 
/*     */       
/* 530 */       Enumeration enumeration = this.ownershipListener.elements();
/* 531 */       for (; enumeration.hasMoreElements(); 
/* 532 */         commPortOwnershipListener.ownershipChange(paramInt))
/* 533 */         CommPortOwnershipListener commPortOwnershipListener = enumeration.nextElement(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private native String native_psmisc_report_owner(String paramString);
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\RXTXcomm.jar!\gnu\io\CommPortIdentifier.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
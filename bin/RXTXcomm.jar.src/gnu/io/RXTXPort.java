/*      */ package gnu.io;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.util.TooManyListenersException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class RXTXPort
/*      */   extends SerialPort
/*      */ {
/*      */   protected static final boolean debug = false;
/*      */   protected static final boolean debug_read = false;
/*      */   protected static final boolean debug_read_results = false;
/*      */   protected static final boolean debug_write = false;
/*      */   protected static final boolean debug_events = false;
/*      */   protected static final boolean debug_verbose = false;
/*      */   private static Zystem z;
/*      */   
/*      */   static {
/*      */     try {
/*   88 */       z = new Zystem();
/*   89 */     } catch (Exception exception) {}
/*      */ 
/*      */ 
/*      */     
/*   93 */     System.loadLibrary("rxtxSerial");
/*   94 */     Initialize();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean MonitorThreadAlive = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int IOLocked;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Object IOLockedMutex;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int fd;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   long eis;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int pid;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RXTXPort(String paramString) throws PortInUseException {
/*  142 */     this.IOLocked = 0;
/*  143 */     this.IOLockedMutex = new Object();
/*      */ 
/*      */     
/*  146 */     this.fd = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  154 */     this.eis = 0L;
/*      */     
/*  156 */     this.pid = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  162 */     this.out = new SerialOutputStream(this);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  175 */     this.in = new SerialInputStream(this);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  233 */     this.speed = 9600;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  246 */     this.dataBits = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  258 */     this.stopBits = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  270 */     this.parity = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  283 */     this.flowmode = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  438 */     this.threshold = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  497 */     this.InputBuffer = 0;
/*  498 */     this.OutputBuffer = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  625 */     this.monThreadisInterrupted = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  794 */     this.MonitorThreadLock = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1049 */     this.closeLock = false; this.fd = open(paramString); this.name = paramString; this.MonitorThreadLock = true; this.monThread = new MonitorThread(this); this.monThread.start(); waitForTheNativeCodeSilly(); this.MonitorThreadAlive = true; this.timeout = -1;
/*      */   } static boolean dsrFlag = false; private final SerialOutputStream out; private final SerialInputStream in; private int speed; private int dataBits; private int stopBits; private int parity; private int flowmode; private int timeout; private int threshold; private int InputBuffer; private int OutputBuffer; private SerialPortEventListener SPEventListener; private MonitorThread monThread; boolean monThreadisInterrupted; boolean MonitorThreadLock; boolean closeLock; public OutputStream getOutputStream() { return this.out; } public InputStream getInputStream() { return this.in; } public synchronized void setSerialPortParams(int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws UnsupportedCommOperationException { if (nativeSetSerialPortParams(paramInt1, paramInt2, paramInt3, paramInt4)) throw new UnsupportedCommOperationException("Invalid Parameter");  this.speed = paramInt1; if (paramInt3 == 3) { this.dataBits = 5; } else { this.dataBits = paramInt2; }  this.stopBits = paramInt3; this.parity = paramInt4; z.reportln("RXTXPort:setSerialPortParams(" + paramInt1 + " " + paramInt2 + " " + paramInt3 + " " + paramInt4 + ") returning"); } public int getBaudRate() { return this.speed; } public int getDataBits() { return this.dataBits; } public int getStopBits() { return this.stopBits; } public int getParity() { return this.parity; } public void setFlowControlMode(int paramInt) { if (this.monThreadisInterrupted) return;  try { setflowcontrol(paramInt); } catch (IOException iOException) { iOException.printStackTrace(); return; }  this.flowmode = paramInt; } public int getFlowControlMode() { return this.flowmode; } public void enableReceiveFraming(int paramInt) throws UnsupportedCommOperationException { throw new UnsupportedCommOperationException("Not supported"); }
/*      */   public void disableReceiveFraming() {}
/* 1052 */   public void close() { synchronized (this) {
/*      */ 
/*      */ 
/*      */       
/* 1056 */       while (this.IOLocked > 0) {
/*      */ 
/*      */         
/*      */         try {
/*      */           
/* 1061 */           wait(500L);
/* 1062 */         } catch (InterruptedException interruptedException) {
/*      */ 
/*      */           
/* 1065 */           Thread.currentThread().interrupt();
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */       } 
/*      */       
/* 1072 */       if (this.closeLock)
/* 1073 */         return;  this.closeLock = true;
/*      */     } 
/*      */     
/* 1076 */     if (this.fd <= 0) {
/*      */       
/* 1078 */       z.reportln("RXTXPort:close detected bad File Descriptor");
/*      */       return;
/*      */     } 
/* 1081 */     setDTR(false);
/* 1082 */     setDSR(false);
/*      */ 
/*      */     
/* 1085 */     if (!this.monThreadisInterrupted)
/*      */     {
/* 1087 */       removeEventListener();
/*      */     }
/*      */ 
/*      */     
/* 1091 */     nativeClose(this.name);
/*      */ 
/*      */     
/* 1094 */     super.close();
/* 1095 */     this.fd = 0;
/* 1096 */     this.closeLock = false; }
/*      */   public boolean isReceiveFramingEnabled() { return false; }
/*      */   public int getReceiveFramingByte() { return 0; }
/*      */   public void disableReceiveTimeout() { this.timeout = -1; NativeEnableReceiveTimeoutThreshold(this.timeout, this.threshold, this.InputBuffer); }
/*      */   public void enableReceiveTimeout(int paramInt) { if (paramInt >= 0) { this.timeout = paramInt; NativeEnableReceiveTimeoutThreshold(paramInt, this.threshold, this.InputBuffer); } else { throw new IllegalArgumentException("Unexpected negative timeout value"); }  }
/*      */   public boolean isReceiveTimeoutEnabled() { return NativeisReceiveTimeoutEnabled(); }
/*      */   public int getReceiveTimeout() { return NativegetReceiveTimeout(); }
/*      */   public void enableReceiveThreshold(int paramInt) { if (paramInt >= 0) { this.threshold = paramInt; NativeEnableReceiveTimeoutThreshold(this.timeout, this.threshold, this.InputBuffer); } else { throw new IllegalArgumentException("Unexpected negative threshold value"); }  }
/*      */   public void disableReceiveThreshold() { enableReceiveThreshold(0); }
/*      */   public int getReceiveThreshold() { return this.threshold; }
/*      */   public boolean isReceiveThresholdEnabled() { return (this.threshold > 0); }
/* 1107 */   public void setInputBufferSize(int paramInt) { if (paramInt < 0) throw new IllegalArgumentException("Unexpected negative buffer size value");  this.InputBuffer = paramInt; } public int getInputBufferSize() { return this.InputBuffer; } public void setOutputBufferSize(int paramInt) { if (paramInt < 0) throw new IllegalArgumentException("Unexpected negative buffer size value");  this.OutputBuffer = paramInt; } public int getOutputBufferSize() { return this.OutputBuffer; } protected void finalize() { if (this.fd > 0)
/*      */     {
/*      */ 
/*      */       
/* 1111 */       close();
/*      */     }
/* 1113 */     z.finalize(); }
/*      */   public boolean checkMonitorThread() { if (this.monThread != null) return this.monThreadisInterrupted;  return true; }
/*      */   public boolean sendEvent(int paramInt, boolean paramBoolean) { if (this.fd == 0 || this.SPEventListener == null || this.monThread == null) return true;  switch (paramInt) {  }  switch (paramInt) { case 1: if (this.monThread.Data) break;  return false;case 2: if (this.monThread.Output) break;  return false;case 3: if (this.monThread.CTS) break;  return false;case 4: if (this.monThread.DSR) break;  return false;case 5: if (this.monThread.RI) break;  return false;case 6: if (this.monThread.CD) break;  return false;case 7: if (this.monThread.OE) break;  return false;case 8: if (this.monThread.PE) break;  return false;case 9: if (this.monThread.FE) break;  return false;case 10: if (this.monThread.BI) break;  return false;default: System.err.println("unknown event: " + paramInt); return false; }  SerialPortEvent serialPortEvent = new SerialPortEvent(this, paramInt, !paramBoolean, paramBoolean); if (this.monThreadisInterrupted) return true;  if (this.SPEventListener != null) this.SPEventListener.serialEvent(serialPortEvent);  if (this.fd == 0 || this.SPEventListener == null || this.monThread == null) return true;  return false; } public void addEventListener(SerialPortEventListener paramSerialPortEventListener) throws TooManyListenersException { if (this.SPEventListener != null) throw new TooManyListenersException();  this.SPEventListener = paramSerialPortEventListener; if (!this.MonitorThreadAlive) { this.MonitorThreadLock = true; this.monThread = new MonitorThread(this); this.monThread.start(); waitForTheNativeCodeSilly(); this.MonitorThreadAlive = true; }  } public void removeEventListener() { waitForTheNativeCodeSilly(); if (this.monThreadisInterrupted == true) { z.reportln("\tRXTXPort:removeEventListener() already interrupted"); this.monThread = null; this.SPEventListener = null; return; }  if (this.monThread != null && this.monThread.isAlive()) { this.monThreadisInterrupted = true; interruptEventLoop(); try { this.monThread.join(3000L); } catch (InterruptedException interruptedException) { Thread.currentThread().interrupt(); return; }  }  this.monThread = null; this.SPEventListener = null; this.MonitorThreadLock = false; this.MonitorThreadAlive = false; this.monThreadisInterrupted = true; z.reportln("RXTXPort:removeEventListener() returning"); } protected void waitForTheNativeCodeSilly() { while (this.MonitorThreadLock) { try { Thread.sleep(5L); } catch (Exception exception) {} }  } public void notifyOnDataAvailable(boolean paramBoolean) { waitForTheNativeCodeSilly(); this.MonitorThreadLock = true; nativeSetEventFlag(this.fd, 1, paramBoolean); this.monThread.Data = paramBoolean; this.MonitorThreadLock = false; } public void notifyOnOutputEmpty(boolean paramBoolean) { waitForTheNativeCodeSilly(); this.MonitorThreadLock = true; nativeSetEventFlag(this.fd, 2, paramBoolean); this.monThread.Output = paramBoolean; this.MonitorThreadLock = false; } public void notifyOnCTS(boolean paramBoolean) { waitForTheNativeCodeSilly(); this.MonitorThreadLock = true; nativeSetEventFlag(this.fd, 3, paramBoolean); this.monThread.CTS = paramBoolean; this.MonitorThreadLock = false; } public void notifyOnDSR(boolean paramBoolean) { waitForTheNativeCodeSilly(); this.MonitorThreadLock = true; nativeSetEventFlag(this.fd, 4, paramBoolean); this.monThread.DSR = paramBoolean; this.MonitorThreadLock = false; } public void notifyOnRingIndicator(boolean paramBoolean) { waitForTheNativeCodeSilly(); this.MonitorThreadLock = true; nativeSetEventFlag(this.fd, 5, paramBoolean); this.monThread.RI = paramBoolean; this.MonitorThreadLock = false; } public void notifyOnCarrierDetect(boolean paramBoolean) { waitForTheNativeCodeSilly(); this.MonitorThreadLock = true; nativeSetEventFlag(this.fd, 6, paramBoolean); this.monThread.CD = paramBoolean; this.MonitorThreadLock = false; } public void notifyOnOverrunError(boolean paramBoolean) { waitForTheNativeCodeSilly(); this.MonitorThreadLock = true; nativeSetEventFlag(this.fd, 7, paramBoolean); this.monThread.OE = paramBoolean; this.MonitorThreadLock = false; } public void notifyOnParityError(boolean paramBoolean) { waitForTheNativeCodeSilly(); this.MonitorThreadLock = true; nativeSetEventFlag(this.fd, 8, paramBoolean); this.monThread.PE = paramBoolean; this.MonitorThreadLock = false; } public void notifyOnFramingError(boolean paramBoolean) { waitForTheNativeCodeSilly(); this.MonitorThreadLock = true; nativeSetEventFlag(this.fd, 9, paramBoolean); this.monThread.FE = paramBoolean; this.MonitorThreadLock = false; } public void notifyOnBreakInterrupt(boolean paramBoolean) { waitForTheNativeCodeSilly(); this.MonitorThreadLock = true; nativeSetEventFlag(this.fd, 10, paramBoolean); this.monThread.BI = paramBoolean; this.MonitorThreadLock = false; } class SerialOutputStream extends OutputStream
/*      */   {
/* 1117 */     private final RXTXPort this$0; SerialOutputStream(RXTXPort this$0) { this.this$0 = this$0; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void write(int param1Int) throws IOException {
/* 1127 */       if (this.this$0.speed == 0)
/* 1128 */         return;  if (this.this$0.monThreadisInterrupted == true) {
/*      */         return;
/*      */       }
/*      */       
/* 1132 */       synchronized (this.this$0.IOLockedMutex) {
/* 1133 */         this.this$0.IOLocked++;
/*      */       } 
/*      */       try {
/* 1136 */         this.this$0.waitForTheNativeCodeSilly();
/* 1137 */         if (this.this$0.fd == 0)
/*      */         {
/* 1139 */           throw new IOException();
/*      */         }
/* 1141 */         this.this$0.writeByte(param1Int, this.this$0.monThreadisInterrupted);
/*      */       }
/*      */       finally {
/*      */         
/* 1145 */         synchronized (this.this$0.IOLockedMutex) {
/* 1146 */           this.this$0.IOLocked--;
/*      */         } 
/*      */       } 
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
/*      */     public void write(byte[] param1ArrayOfbyte) throws IOException {
/* 1160 */       if (this.this$0.speed == 0)
/* 1161 */         return;  if (this.this$0.monThreadisInterrupted == true) {
/*      */         return;
/*      */       }
/*      */       
/* 1165 */       if (this.this$0.fd == 0) throw new IOException(); 
/* 1166 */       synchronized (this.this$0.IOLockedMutex) {
/* 1167 */         this.this$0.IOLocked++;
/*      */       } 
/*      */       try {
/* 1170 */         this.this$0.waitForTheNativeCodeSilly();
/* 1171 */         this.this$0.writeArray(param1ArrayOfbyte, 0, param1ArrayOfbyte.length, this.this$0.monThreadisInterrupted);
/*      */       }
/*      */       finally {
/*      */         
/* 1175 */         synchronized (this.this$0.IOLockedMutex) {
/* 1176 */           this.this$0.IOLocked--;
/*      */         } 
/*      */       } 
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
/*      */     public void write(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
/* 1190 */       if (this.this$0.speed == 0)
/* 1191 */         return;  if (param1Int1 + param1Int2 > param1ArrayOfbyte.length)
/*      */       {
/* 1193 */         throw new IndexOutOfBoundsException("Invalid offset/length passed to read");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1198 */       byte[] arrayOfByte = new byte[param1Int2];
/* 1199 */       System.arraycopy(param1ArrayOfbyte, param1Int1, arrayOfByte, 0, param1Int2);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1204 */       if (this.this$0.fd == 0) throw new IOException(); 
/* 1205 */       if (this.this$0.monThreadisInterrupted == true) {
/*      */         return;
/*      */       }
/*      */       
/* 1209 */       synchronized (this.this$0.IOLockedMutex) {
/* 1210 */         this.this$0.IOLocked++;
/*      */       } 
/*      */       
/*      */       try {
/* 1214 */         this.this$0.waitForTheNativeCodeSilly();
/* 1215 */         this.this$0.writeArray(arrayOfByte, 0, param1Int2, this.this$0.monThreadisInterrupted);
/*      */       }
/*      */       finally {
/*      */         
/* 1219 */         synchronized (this.this$0.IOLockedMutex) {
/* 1220 */           this.this$0.IOLocked--;
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void flush() throws IOException {
/* 1230 */       if (this.this$0.speed == 0)
/* 1231 */         return;  if (this.this$0.fd == 0) throw new IOException(); 
/* 1232 */       if (this.this$0.monThreadisInterrupted == true) {
/*      */         return;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1238 */       synchronized (this.this$0.IOLockedMutex) {
/* 1239 */         this.this$0.IOLocked++;
/*      */       } 
/*      */       
/*      */       try {
/* 1243 */         this.this$0.waitForTheNativeCodeSilly();
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1248 */         if (this.this$0.nativeDrain(this.this$0.monThreadisInterrupted)) {
/* 1249 */           this.this$0.sendEvent(2, true);
/*      */         
/*      */         }
/*      */       }
/*      */       finally {
/*      */         
/* 1255 */         synchronized (this.this$0.IOLockedMutex) {
/* 1256 */           this.this$0.IOLocked--;
/*      */         } 
/*      */       } 
/*      */     } }
/*      */   class SerialInputStream extends InputStream { private final RXTXPort this$0;
/*      */     
/*      */     SerialInputStream(RXTXPort this$0) {
/* 1263 */       this.this$0 = this$0;
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
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public synchronized int read() throws IOException {
/* 1284 */       if (this.this$0.fd == 0) throw new IOException(); 
/* 1285 */       if (this.this$0.monThreadisInterrupted)
/*      */       {
/* 1287 */         RXTXPort.z.reportln("+++++++++ read() monThreadisInterrupted");
/*      */       }
/* 1289 */       synchronized (this.this$0.IOLockedMutex) {
/* 1290 */         this.this$0.IOLocked++;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/* 1295 */         this.this$0.waitForTheNativeCodeSilly();
/*      */ 
/*      */         
/* 1298 */         int i = this.this$0.readByte();
/*      */ 
/*      */ 
/*      */         
/* 1302 */         return i;
/*      */       }
/*      */       finally {
/*      */         
/* 1306 */         synchronized (this.this$0.IOLockedMutex) {
/* 1307 */           this.this$0.IOLocked--;
/*      */         } 
/*      */       } 
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
/*      */ 
/*      */ 
/*      */     
/*      */     public synchronized int read(byte[] param1ArrayOfbyte) throws IOException {
/* 1329 */       if (this.this$0.monThreadisInterrupted == true)
/*      */       {
/* 1331 */         return 0;
/*      */       }
/* 1333 */       synchronized (this.this$0.IOLockedMutex) {
/* 1334 */         this.this$0.IOLocked++;
/*      */       } 
/*      */       
/*      */       try {
/* 1338 */         this.this$0.waitForTheNativeCodeSilly();
/* 1339 */         int i = read(param1ArrayOfbyte, 0, param1ArrayOfbyte.length);
/*      */ 
/*      */         
/* 1342 */         return i;
/*      */       }
/*      */       finally {
/*      */         
/* 1346 */         synchronized (this.this$0.IOLockedMutex) {
/* 1347 */           this.this$0.IOLocked--;
/*      */         } 
/*      */       } 
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public synchronized int read(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
/* 1380 */       if (this.this$0.fd == 0) {
/*      */ 
/*      */ 
/*      */         
/* 1384 */         RXTXPort.z.reportln("+++++++ IOException()\n");
/* 1385 */         throw new IOException();
/*      */       } 
/*      */       
/* 1388 */       if (param1ArrayOfbyte == null) {
/*      */         
/* 1390 */         RXTXPort.z.reportln("+++++++ NullPointerException()\n");
/*      */ 
/*      */         
/* 1393 */         throw new NullPointerException();
/*      */       } 
/*      */       
/* 1396 */       if (param1Int1 < 0 || param1Int2 < 0 || param1Int1 + param1Int2 > param1ArrayOfbyte.length) {
/*      */         
/* 1398 */         RXTXPort.z.reportln("+++++++ IndexOutOfBoundsException()\n");
/*      */ 
/*      */         
/* 1401 */         throw new IndexOutOfBoundsException();
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1407 */       if (param1Int2 == 0)
/*      */       {
/*      */ 
/*      */         
/* 1411 */         return 0;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1416 */       int i = param1Int2;
/*      */       
/* 1418 */       if (this.this$0.threshold == 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1427 */         int j = this.this$0.nativeavailable();
/* 1428 */         if (j == 0) {
/* 1429 */           i = 1;
/*      */         } else {
/* 1431 */           i = Math.min(i, j);
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 1440 */         i = Math.min(i, this.this$0.threshold);
/*      */       } 
/* 1442 */       if (this.this$0.monThreadisInterrupted == true)
/*      */       {
/*      */ 
/*      */         
/* 1446 */         return 0;
/*      */       }
/* 1448 */       synchronized (this.this$0.IOLockedMutex) {
/* 1449 */         this.this$0.IOLocked++;
/*      */       } 
/*      */       
/*      */       try {
/* 1453 */         this.this$0.waitForTheNativeCodeSilly();
/* 1454 */         int j = this.this$0.readArray(param1ArrayOfbyte, param1Int1, i);
/*      */ 
/*      */         
/* 1457 */         return j;
/*      */       }
/*      */       finally {
/*      */         
/* 1461 */         synchronized (this.this$0.IOLockedMutex) {
/* 1462 */           this.this$0.IOLocked--;
/*      */         } 
/*      */       } 
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public synchronized int read(byte[] param1ArrayOfbyte1, int param1Int1, int param1Int2, byte[] param1ArrayOfbyte2) throws IOException {
/* 1493 */       if (this.this$0.fd == 0) {
/*      */ 
/*      */ 
/*      */         
/* 1497 */         RXTXPort.z.reportln("+++++++ IOException()\n");
/* 1498 */         throw new IOException();
/*      */       } 
/*      */       
/* 1501 */       if (param1ArrayOfbyte1 == null) {
/*      */         
/* 1503 */         RXTXPort.z.reportln("+++++++ NullPointerException()\n");
/*      */ 
/*      */         
/* 1506 */         throw new NullPointerException();
/*      */       } 
/*      */       
/* 1509 */       if (param1Int1 < 0 || param1Int2 < 0 || param1Int1 + param1Int2 > param1ArrayOfbyte1.length) {
/*      */         
/* 1511 */         RXTXPort.z.reportln("+++++++ IndexOutOfBoundsException()\n");
/*      */ 
/*      */         
/* 1514 */         throw new IndexOutOfBoundsException();
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1520 */       if (param1Int2 == 0)
/*      */       {
/*      */ 
/*      */         
/* 1524 */         return 0;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1529 */       int i = param1Int2;
/*      */       
/* 1531 */       if (this.this$0.threshold == 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1540 */         int j = this.this$0.nativeavailable();
/* 1541 */         if (j == 0) {
/* 1542 */           i = 1;
/*      */         } else {
/* 1544 */           i = Math.min(i, j);
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 1553 */         i = Math.min(i, this.this$0.threshold);
/*      */       } 
/* 1555 */       if (this.this$0.monThreadisInterrupted == true)
/*      */       {
/*      */ 
/*      */         
/* 1559 */         return 0;
/*      */       }
/* 1561 */       synchronized (this.this$0.IOLockedMutex) {
/* 1562 */         this.this$0.IOLocked++;
/*      */       } 
/*      */       
/*      */       try {
/* 1566 */         this.this$0.waitForTheNativeCodeSilly();
/* 1567 */         int j = this.this$0.readTerminatedArray(param1ArrayOfbyte1, param1Int1, i, param1ArrayOfbyte2);
/*      */ 
/*      */         
/* 1570 */         return j;
/*      */       }
/*      */       finally {
/*      */         
/* 1574 */         synchronized (this.this$0.IOLockedMutex) {
/* 1575 */           this.this$0.IOLocked--;
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public synchronized int available() throws IOException {
/* 1585 */       if (this.this$0.monThreadisInterrupted == true)
/*      */       {
/* 1587 */         return 0;
/*      */       }
/*      */ 
/*      */       
/* 1591 */       synchronized (this.this$0.IOLockedMutex) {
/* 1592 */         this.this$0.IOLocked++;
/*      */       } 
/*      */       
/*      */       try {
/* 1596 */         int i = this.this$0.nativeavailable();
/*      */ 
/*      */ 
/*      */         
/* 1600 */         return i;
/*      */       }
/*      */       finally {
/*      */         
/* 1604 */         synchronized (this.this$0.IOLockedMutex) {
/* 1605 */           this.this$0.IOLocked--;
/*      */         } 
/*      */       } 
/*      */     } }
/*      */ 
/*      */ 
/*      */   
/*      */   class MonitorThread
/*      */     extends Thread
/*      */   {
/*      */     private volatile boolean CTS;
/*      */     private volatile boolean DSR;
/*      */     private volatile boolean RI;
/*      */     private volatile boolean CD;
/*      */     private volatile boolean OE;
/*      */     private volatile boolean PE;
/*      */     private volatile boolean FE;
/*      */     private volatile boolean BI;
/*      */     private volatile boolean Data;
/*      */     private volatile boolean Output;
/*      */     private final RXTXPort this$0;
/*      */     
/*      */     MonitorThread(RXTXPort this$0)
/*      */     {
/* 1629 */       this.this$0 = this$0;
/*      */       this.CTS = false;
/*      */       this.DSR = false;
/*      */       this.RI = false;
/*      */       this.CD = false;
/*      */       this.OE = false;
/*      */       this.PE = false;
/*      */       this.FE = false;
/*      */       this.BI = false;
/*      */       this.Data = false;
/*      */       this.Output = false; } public void run() {
/* 1640 */       this.this$0.monThreadisInterrupted = false;
/* 1641 */       this.this$0.eventLoop();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void finalize() throws Throwable {}
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
/*      */   public void setRcvFifoTrigger(int paramInt) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int staticGetBaudRate(String paramString) throws UnsupportedCommOperationException {
/* 1745 */     return nativeStaticGetBaudRate(paramString);
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
/*      */   public static int staticGetDataBits(String paramString) throws UnsupportedCommOperationException {
/* 1763 */     return nativeStaticGetDataBits(paramString);
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
/*      */   public static int staticGetParity(String paramString) throws UnsupportedCommOperationException {
/* 1782 */     return nativeStaticGetParity(paramString);
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
/*      */   public static int staticGetStopBits(String paramString) throws UnsupportedCommOperationException {
/* 1801 */     return nativeStaticGetStopBits(paramString);
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
/*      */   public static void staticSetSerialPortParams(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws UnsupportedCommOperationException {
/* 1829 */     nativeStaticSetSerialPortParams(paramString, paramInt1, paramInt2, paramInt3, paramInt4);
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
/*      */   public static boolean staticSetDSR(String paramString, boolean paramBoolean) throws UnsupportedCommOperationException {
/* 1852 */     return nativeStaticSetDSR(paramString, paramBoolean);
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
/*      */   public static boolean staticSetDTR(String paramString, boolean paramBoolean) throws UnsupportedCommOperationException {
/* 1875 */     return nativeStaticSetDTR(paramString, paramBoolean);
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
/*      */   public static boolean staticSetRTS(String paramString, boolean paramBoolean) throws UnsupportedCommOperationException {
/* 1898 */     return nativeStaticSetRTS(paramString, paramBoolean);
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
/*      */   public static boolean staticIsRTS(String paramString) throws UnsupportedCommOperationException {
/* 1919 */     return nativeStaticIsRTS(paramString);
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
/*      */   public static boolean staticIsCD(String paramString) throws UnsupportedCommOperationException {
/* 1939 */     return nativeStaticIsCD(paramString);
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
/*      */   public static boolean staticIsCTS(String paramString) throws UnsupportedCommOperationException {
/* 1959 */     return nativeStaticIsCTS(paramString);
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
/*      */   public static boolean staticIsDSR(String paramString) throws UnsupportedCommOperationException {
/* 1979 */     return nativeStaticIsDSR(paramString);
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
/*      */   public static boolean staticIsDTR(String paramString) throws UnsupportedCommOperationException {
/* 1999 */     return nativeStaticIsDTR(paramString);
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
/*      */   public static boolean staticIsRI(String paramString) throws UnsupportedCommOperationException {
/* 2019 */     return nativeStaticIsRI(paramString);
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
/*      */   public byte getParityErrorChar() throws UnsupportedCommOperationException {
/* 2039 */     return nativeGetParityErrorChar();
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
/*      */   public boolean setParityErrorChar(byte paramByte) throws UnsupportedCommOperationException {
/* 2062 */     return nativeSetParityErrorChar(paramByte);
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
/*      */   public byte getEndOfInputChar() throws UnsupportedCommOperationException {
/* 2081 */     return nativeGetEndOfInputChar();
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
/*      */   public boolean setEndOfInputChar(byte paramByte) throws UnsupportedCommOperationException {
/* 2102 */     return nativeSetEndOfInputChar(paramByte);
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
/*      */   public boolean setUARTType(String paramString, boolean paramBoolean) throws UnsupportedCommOperationException {
/* 2121 */     return nativeSetUartType(paramString, paramBoolean);
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
/*      */   public String getUARTType() throws UnsupportedCommOperationException {
/* 2134 */     return nativeGetUartType();
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
/*      */   public boolean setBaudBase(int paramInt) throws UnsupportedCommOperationException, IOException {
/* 2152 */     return nativeSetBaudBase(paramInt);
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
/*      */   public int getBaudBase() throws UnsupportedCommOperationException, IOException {
/* 2166 */     return nativeGetBaudBase();
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
/*      */   public boolean setDivisor(int paramInt) throws UnsupportedCommOperationException, IOException {
/* 2181 */     return nativeSetDivisor(paramInt);
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
/*      */   public int getDivisor() throws UnsupportedCommOperationException, IOException {
/* 2195 */     return nativeGetDivisor();
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
/*      */   public boolean setLowLatency() throws UnsupportedCommOperationException {
/* 2208 */     return nativeSetLowLatency();
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
/*      */   public boolean getLowLatency() throws UnsupportedCommOperationException {
/* 2221 */     return nativeGetLowLatency();
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
/*      */   public boolean setCallOutHangup(boolean paramBoolean) throws UnsupportedCommOperationException {
/* 2235 */     return nativeSetCallOutHangup(paramBoolean);
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
/*      */   public boolean getCallOutHangup() throws UnsupportedCommOperationException {
/* 2249 */     return nativeGetCallOutHangup();
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
/*      */   public boolean clearCommInput() throws UnsupportedCommOperationException {
/* 2263 */     return nativeClearCommInput();
/*      */   }
/*      */   
/*      */   private static native void Initialize();
/*      */   
/*      */   private synchronized native int open(String paramString) throws PortInUseException;
/*      */   
/*      */   private native int nativeGetParity(int paramInt);
/*      */   
/*      */   private native int nativeGetFlowControlMode(int paramInt);
/*      */   
/*      */   private native boolean nativeSetSerialPortParams(int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws UnsupportedCommOperationException;
/*      */   
/*      */   native void setflowcontrol(int paramInt) throws IOException;
/*      */   
/*      */   public native int NativegetReceiveTimeout();
/*      */   
/*      */   private native boolean NativeisReceiveTimeoutEnabled();
/*      */   
/*      */   private native void NativeEnableReceiveTimeoutThreshold(int paramInt1, int paramInt2, int paramInt3);
/*      */   
/*      */   public native boolean isDTR();
/*      */   
/*      */   public native void setDTR(boolean paramBoolean);
/*      */   
/*      */   public native void setRTS(boolean paramBoolean);
/*      */   
/*      */   private native void setDSR(boolean paramBoolean);
/*      */   
/*      */   public native boolean isCTS();
/*      */   
/*      */   public native boolean isDSR();
/*      */   
/*      */   public native boolean isCD();
/*      */   
/*      */   public native boolean isRI();
/*      */   
/*      */   public native boolean isRTS();
/*      */   
/*      */   public native void sendBreak(int paramInt);
/*      */   
/*      */   protected native void writeByte(int paramInt, boolean paramBoolean) throws IOException;
/*      */   
/*      */   protected native void writeArray(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, boolean paramBoolean) throws IOException;
/*      */   
/*      */   protected native boolean nativeDrain(boolean paramBoolean) throws IOException;
/*      */   
/*      */   protected native int nativeavailable() throws IOException;
/*      */   
/*      */   protected native int readByte() throws IOException;
/*      */   
/*      */   protected native int readArray(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
/*      */   
/*      */   protected native int readTerminatedArray(byte[] paramArrayOfbyte1, int paramInt1, int paramInt2, byte[] paramArrayOfbyte2) throws IOException;
/*      */   
/*      */   native void eventLoop();
/*      */   
/*      */   private native void interruptEventLoop();
/*      */   
/*      */   private native void nativeSetEventFlag(int paramInt1, int paramInt2, boolean paramBoolean);
/*      */   
/*      */   private native void nativeClose(String paramString);
/*      */   
/*      */   private static native void nativeStaticSetSerialPortParams(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws UnsupportedCommOperationException;
/*      */   
/*      */   private static native boolean nativeStaticSetDSR(String paramString, boolean paramBoolean) throws UnsupportedCommOperationException;
/*      */   
/*      */   private static native boolean nativeStaticSetDTR(String paramString, boolean paramBoolean) throws UnsupportedCommOperationException;
/*      */   
/*      */   private static native boolean nativeStaticSetRTS(String paramString, boolean paramBoolean) throws UnsupportedCommOperationException;
/*      */   
/*      */   private static native boolean nativeStaticIsDSR(String paramString) throws UnsupportedCommOperationException;
/*      */   
/*      */   private static native boolean nativeStaticIsDTR(String paramString) throws UnsupportedCommOperationException;
/*      */   
/*      */   private static native boolean nativeStaticIsRTS(String paramString) throws UnsupportedCommOperationException;
/*      */   
/*      */   private static native boolean nativeStaticIsCTS(String paramString) throws UnsupportedCommOperationException;
/*      */   
/*      */   private static native boolean nativeStaticIsCD(String paramString) throws UnsupportedCommOperationException;
/*      */   
/*      */   private static native boolean nativeStaticIsRI(String paramString) throws UnsupportedCommOperationException;
/*      */   
/*      */   private static native int nativeStaticGetBaudRate(String paramString) throws UnsupportedCommOperationException;
/*      */   
/*      */   private static native int nativeStaticGetDataBits(String paramString) throws UnsupportedCommOperationException;
/*      */   
/*      */   private static native int nativeStaticGetParity(String paramString) throws UnsupportedCommOperationException;
/*      */   
/*      */   private static native int nativeStaticGetStopBits(String paramString) throws UnsupportedCommOperationException;
/*      */   
/*      */   private native byte nativeGetParityErrorChar() throws UnsupportedCommOperationException;
/*      */   
/*      */   private native boolean nativeSetParityErrorChar(byte paramByte) throws UnsupportedCommOperationException;
/*      */   
/*      */   private native byte nativeGetEndOfInputChar() throws UnsupportedCommOperationException;
/*      */   
/*      */   private native boolean nativeSetEndOfInputChar(byte paramByte) throws UnsupportedCommOperationException;
/*      */   
/*      */   private native boolean nativeSetUartType(String paramString, boolean paramBoolean) throws UnsupportedCommOperationException;
/*      */   
/*      */   native String nativeGetUartType() throws UnsupportedCommOperationException;
/*      */   
/*      */   private native boolean nativeSetBaudBase(int paramInt) throws UnsupportedCommOperationException;
/*      */   
/*      */   private native int nativeGetBaudBase() throws UnsupportedCommOperationException;
/*      */   
/*      */   private native boolean nativeSetDivisor(int paramInt) throws UnsupportedCommOperationException;
/*      */   
/*      */   private native int nativeGetDivisor() throws UnsupportedCommOperationException;
/*      */   
/*      */   private native boolean nativeSetLowLatency() throws UnsupportedCommOperationException;
/*      */   
/*      */   private native boolean nativeGetLowLatency() throws UnsupportedCommOperationException;
/*      */   
/*      */   private native boolean nativeSetCallOutHangup(boolean paramBoolean) throws UnsupportedCommOperationException;
/*      */   
/*      */   private native boolean nativeGetCallOutHangup() throws UnsupportedCommOperationException;
/*      */   
/*      */   private native boolean nativeClearCommInput() throws UnsupportedCommOperationException;
/*      */ }


/* Location:              C:\diao\bin\diao.jar!\RXTXcomm.jar!\gnu\io\RXTXPort.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
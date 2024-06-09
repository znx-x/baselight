/*     */ package gnu.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.TooManyListenersException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class LPRPort
/*     */   extends ParallelPort
/*     */ {
/*     */   private static final boolean debug = false;
/*     */   private int fd;
/*     */   private final ParallelOutputStream out;
/*     */   private final ParallelInputStream in;
/*     */   private int lprmode;
/*     */   private int timeout;
/*     */   private int threshold;
/*     */   private ParallelPortEventListener PPEventListener;
/*     */   private MonitorThread monThread;
/*     */   
/*     */   static {
/*  74 */     System.loadLibrary("rxtxParallel");
/*  75 */     Initialize();
/*     */   } public OutputStream getOutputStream() {
/*     */     return this.out;
/*     */   } public InputStream getInputStream() {
/*     */     return this.in;
/*     */   }
/*     */   public int getMode() {
/*     */     return this.lprmode;
/*     */   }
/*     */   public int setMode(int paramInt) throws UnsupportedCommOperationException {
/*     */     try {
/*     */       setLPRMode(paramInt);
/*     */     } catch (UnsupportedCommOperationException unsupportedCommOperationException) {
/*     */       unsupportedCommOperationException.printStackTrace();
/*     */       return -1;
/*     */     } 
/*     */     this.lprmode = paramInt;
/*     */     return 0;
/*     */   }
/*     */   public void restart() {
/*     */     System.out.println("restart() is not implemented");
/*     */   }
/*     */   public void suspend() {
/*     */     System.out.println("suspend() is not implemented");
/*     */   }
/*     */   public synchronized void close() {
/*     */     if (this.fd < 0)
/*     */       return; 
/*     */     nativeClose();
/*     */     super.close();
/*     */     removeEventListener();
/*     */     this.fd = 0;
/*     */     Runtime.getRuntime().gc();
/*     */   }
/*     */   public void enableReceiveFraming(int paramInt) throws UnsupportedCommOperationException {
/*     */     throw new UnsupportedCommOperationException("Not supported");
/*     */   }
/* 112 */   public LPRPort(String paramString) throws PortInUseException { this.out = new ParallelOutputStream(this);
/*     */ 
/*     */ 
/*     */     
/* 116 */     this.in = new ParallelInputStream(this);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 121 */     this.lprmode = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 174 */     this.timeout = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 185 */     this.threshold = 1; this.fd = open(paramString); this.name = paramString; } public void disableReceiveFraming() {} public boolean isReceiveFramingEnabled() { return false; } public int getReceiveFramingByte() { return 0; } public void enableReceiveTimeout(int paramInt) { if (paramInt > 0) { this.timeout = paramInt; } else { this.timeout = 0; }  } public void disableReceiveTimeout() { this.timeout = 0; }
/*     */   public boolean isReceiveTimeoutEnabled() { return (this.timeout > 0); }
/*     */   public int getReceiveTimeout() { return this.timeout; }
/* 188 */   public void enableReceiveThreshold(int paramInt) { if (paramInt > 1) { this.threshold = paramInt; }
/* 189 */     else { this.threshold = 1; }
/*     */      }
/* 191 */   public void disableReceiveThreshold() { this.threshold = 1; }
/* 192 */   public int getReceiveThreshold() { return this.threshold; } public boolean isReceiveThresholdEnabled() {
/* 193 */     return (this.threshold > 1);
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
/*     */   public boolean checkMonitorThread() {
/* 229 */     if (this.monThread != null)
/* 230 */       return this.monThread.isInterrupted(); 
/* 231 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized boolean sendEvent(int paramInt, boolean paramBoolean) {
/* 238 */     if (this.fd == 0 || this.PPEventListener == null || this.monThread == null)
/*     */     {
/* 240 */       return true;
/*     */     }
/*     */     
/* 243 */     switch (paramInt) {
/*     */       
/*     */       case 2:
/* 246 */         if (this.monThread.monBuffer)
/* 247 */           break;  return false;
/*     */       case 1:
/* 249 */         if (this.monThread.monError)
/* 250 */           break;  return false;
/*     */       default:
/* 252 */         System.err.println("unknown event:" + paramInt);
/* 253 */         return false;
/*     */     } 
/* 255 */     ParallelPortEvent parallelPortEvent = new ParallelPortEvent(this, paramInt, !paramBoolean, paramBoolean);
/*     */     
/* 257 */     if (this.PPEventListener != null)
/* 258 */       this.PPEventListener.parallelEvent(parallelPortEvent); 
/* 259 */     if (this.fd == 0 || this.PPEventListener == null || this.monThread == null)
/*     */     {
/* 261 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 265 */     try { Thread.sleep(50L); } catch (Exception exception) {}
/* 266 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void addEventListener(ParallelPortEventListener paramParallelPortEventListener) throws TooManyListenersException {
/* 275 */     if (this.PPEventListener != null)
/* 276 */       throw new TooManyListenersException(); 
/* 277 */     this.PPEventListener = paramParallelPortEventListener;
/* 278 */     this.monThread = new MonitorThread(this);
/* 279 */     this.monThread.start();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void removeEventListener() {
/* 285 */     this.PPEventListener = null;
/* 286 */     if (this.monThread != null) {
/*     */       
/* 288 */       this.monThread.interrupt();
/* 289 */       this.monThread = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void notifyOnError(boolean paramBoolean) {
/* 298 */     System.out.println("notifyOnError is not implemented yet");
/* 299 */     this.monThread.monError = paramBoolean;
/*     */   }
/*     */   
/*     */   public synchronized void notifyOnBuffer(boolean paramBoolean) {
/* 303 */     System.out.println("notifyOnBuffer is not implemented yet");
/* 304 */     this.monThread.monBuffer = paramBoolean;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void finalize() {
/* 311 */     if (this.fd > 0) close(); 
/*     */   } private static native void Initialize(); private synchronized native int open(String paramString) throws PortInUseException; public native boolean setLPRMode(int paramInt) throws UnsupportedCommOperationException; public native boolean isPaperOut(); public native boolean isPrinterBusy(); public native boolean isPrinterError(); public native boolean isPrinterSelected(); public native boolean isPrinterTimedOut(); private native void nativeClose(); public native void setInputBufferSize(int paramInt); public native int getInputBufferSize(); public native void setOutputBufferSize(int paramInt); public native int getOutputBufferSize(); public native int getOutputBufferFree(); protected native void writeByte(int paramInt) throws IOException; protected native void writeArray(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException; protected native void drain() throws IOException; protected native int nativeavailable() throws IOException; protected native int readByte() throws IOException; protected native int readArray(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
/*     */   native void eventLoop();
/*     */   class ParallelOutputStream extends OutputStream { ParallelOutputStream(LPRPort this$0) {
/* 315 */       this.this$0 = this$0;
/*     */     }
/*     */     private final LPRPort this$0;
/*     */     public synchronized void write(int param1Int) throws IOException {
/* 319 */       if (this.this$0.fd == 0) throw new IOException(); 
/* 320 */       this.this$0.writeByte(param1Int);
/*     */     }
/*     */     
/*     */     public synchronized void write(byte[] param1ArrayOfbyte) throws IOException {
/* 324 */       if (this.this$0.fd == 0) throw new IOException(); 
/* 325 */       this.this$0.writeArray(param1ArrayOfbyte, 0, param1ArrayOfbyte.length);
/*     */     }
/*     */ 
/*     */     
/*     */     public synchronized void write(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
/* 330 */       if (this.this$0.fd == 0) throw new IOException(); 
/* 331 */       this.this$0.writeArray(param1ArrayOfbyte, param1Int1, param1Int2);
/*     */     }
/*     */     
/*     */     public synchronized void flush() throws IOException {
/* 335 */       if (this.this$0.fd == 0) throw new IOException(); 
/*     */     } }
/*     */   
/*     */   class ParallelInputStream extends InputStream { private final LPRPort this$0;
/*     */     
/*     */     ParallelInputStream(LPRPort this$0) {
/* 341 */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public int read() throws IOException {
/* 345 */       if (this.this$0.fd == 0) throw new IOException(); 
/* 346 */       return this.this$0.readByte();
/*     */     }
/*     */     
/*     */     public int read(byte[] param1ArrayOfbyte) throws IOException {
/* 350 */       if (this.this$0.fd == 0) throw new IOException(); 
/* 351 */       return this.this$0.readArray(param1ArrayOfbyte, 0, param1ArrayOfbyte.length);
/*     */     }
/*     */ 
/*     */     
/*     */     public int read(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
/* 356 */       if (this.this$0.fd == 0) throw new IOException(); 
/* 357 */       return this.this$0.readArray(param1ArrayOfbyte, param1Int1, param1Int2);
/*     */     }
/*     */     
/*     */     public int available() throws IOException {
/* 361 */       if (this.this$0.fd == 0) throw new IOException(); 
/* 362 */       return this.this$0.nativeavailable();
/*     */     } }
/*     */   
/*     */   class MonitorThread extends Thread { private boolean monError;
/*     */     private boolean monBuffer;
/*     */     private final LPRPort this$0;
/*     */     
/* 369 */     MonitorThread(LPRPort this$0) { this.this$0 = this$0;
/*     */       this.monError = false;
/*     */       this.monBuffer = false; } public void run() {
/* 372 */       this.this$0.eventLoop();
/* 373 */       yield();
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\RXTXcomm.jar!\gnu\io\LPRPort.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
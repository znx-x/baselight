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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class I2C
/*     */   extends I2CPort
/*     */ {
/*     */   private int fd;
/*     */   
/*     */   static {
/*  77 */     System.loadLibrary("rxtxI2C");
/*  78 */     Initialize();
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
/*     */   public I2C(String paramString) throws PortInUseException {
/* 103 */     this.out = new I2COutputStream(this);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 108 */     this.in = new I2CInputStream(this);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     this.speed = 9600;
/*     */ 
/*     */ 
/*     */     
/* 134 */     this.dataBits = 8;
/*     */ 
/*     */ 
/*     */     
/* 138 */     this.stopBits = 1;
/*     */ 
/*     */ 
/*     */     
/* 142 */     this.parity = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 147 */     this.flowmode = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 177 */     this.timeout = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 203 */     this.threshold = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 233 */     this.InputBuffer = 0;
/* 234 */     this.OutputBuffer = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 287 */     this.dataAvailable = 0; this.fd = open(paramString);
/*     */   } static boolean dsrFlag = false; private final I2COutputStream out; private final I2CInputStream in; private int speed; private int dataBits; private int stopBits; private int parity; private int flowmode; private int timeout; private int threshold; private int InputBuffer; private int OutputBuffer; private I2CPortEventListener SPEventListener; private MonitorThread monThread; private int dataAvailable; public OutputStream getOutputStream() { return this.out; } public InputStream getInputStream() { return this.in; } public void setI2CPortParams(int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws UnsupportedCommOperationException { nativeSetI2CPortParams(paramInt1, paramInt2, paramInt3, paramInt4); this.speed = paramInt1; this.dataBits = paramInt2; this.stopBits = paramInt3; this.parity = paramInt4; } public int getBaudRate() { return this.speed; } public int getDataBits() { return this.dataBits; }
/* 289 */   public void sendEvent(int paramInt, boolean paramBoolean) { switch (paramInt) {
/*     */       case 1:
/* 291 */         this.dataAvailable = 1;
/* 292 */         if (this.monThread.Data)
/*     */           break;  return;
/*     */       case 2:
/* 295 */         if (this.monThread.Output) {
/*     */           break;
/*     */         }
/*     */         return;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/* 315 */         if (this.monThread.CTS)
/*     */           break;  return;
/*     */       case 4:
/* 318 */         if (this.monThread.DSR)
/*     */           break;  return;
/*     */       case 5:
/* 321 */         if (this.monThread.RI)
/*     */           break;  return;
/*     */       case 6:
/* 324 */         if (this.monThread.CD)
/*     */           break;  return;
/*     */       case 7:
/* 327 */         if (this.monThread.OE)
/*     */           break;  return;
/*     */       case 8:
/* 330 */         if (this.monThread.PE)
/*     */           break;  return;
/*     */       case 9:
/* 333 */         if (this.monThread.FE)
/*     */           break;  return;
/*     */       case 10:
/* 336 */         if (this.monThread.BI)
/*     */           break;  return;
/*     */       default:
/* 339 */         System.err.println("unknown event:" + paramInt);
/*     */         return;
/*     */     } 
/* 342 */     I2CPortEvent i2CPortEvent = new I2CPortEvent(this, paramInt, !paramBoolean, paramBoolean);
/* 343 */     if (this.SPEventListener != null) this.SPEventListener.I2CEvent(i2CPortEvent);  }
/*     */   public int getStopBits() { return this.stopBits; }
/*     */   public int getParity() { return this.parity; }
/*     */   public void setFlowControlMode(int paramInt) { try { setflowcontrol(paramInt); } catch (IOException iOException) { iOException.printStackTrace(); return; }  this.flowmode = paramInt; }
/*     */   public int getFlowControlMode() { return this.flowmode; }
/*     */   public void enableReceiveFraming(int paramInt) throws UnsupportedCommOperationException { throw new UnsupportedCommOperationException("Not supported"); }
/*     */   public void disableReceiveFraming() {}
/* 350 */   public boolean isReceiveFramingEnabled() { return false; } public int getReceiveFramingByte() { return 0; } public void disableReceiveTimeout() { enableReceiveTimeout(0); } public void enableReceiveTimeout(int paramInt) { if (paramInt >= 0) { this.timeout = paramInt; NativeEnableReceiveTimeoutThreshold(paramInt, this.threshold, this.InputBuffer); } else { System.out.println("Invalid timeout"); }  } public void addEventListener(I2CPortEventListener paramI2CPortEventListener) throws TooManyListenersException { if (this.SPEventListener != null) throw new TooManyListenersException(); 
/* 351 */     this.SPEventListener = paramI2CPortEventListener;
/* 352 */     this.monThread = new MonitorThread(this);
/* 353 */     this.monThread.start(); }
/*     */   public boolean isReceiveTimeoutEnabled() { return NativeisReceiveTimeoutEnabled(); }
/*     */   public int getReceiveTimeout() { return NativegetReceiveTimeout(); }
/*     */   public void enableReceiveThreshold(int paramInt) { if (paramInt >= 0) { this.threshold = paramInt; NativeEnableReceiveTimeoutThreshold(this.timeout, this.threshold, this.InputBuffer); } else { System.out.println("Invalid Threshold"); }  }
/* 357 */   public void disableReceiveThreshold() { enableReceiveThreshold(0); } public int getReceiveThreshold() { return this.threshold; } public boolean isReceiveThresholdEnabled() { return (this.threshold > 0); } public void setInputBufferSize(int paramInt) { this.InputBuffer = paramInt; } public int getInputBufferSize() { return this.InputBuffer; } public void setOutputBufferSize(int paramInt) { this.OutputBuffer = paramInt; } public int getOutputBufferSize() { return this.OutputBuffer; } public void removeEventListener() { this.SPEventListener = null;
/* 358 */     if (this.monThread != null) {
/* 359 */       this.monThread.interrupt();
/* 360 */       this.monThread = null;
/*     */     }  }
/*     */   
/*     */   public void notifyOnDataAvailable(boolean paramBoolean) {
/* 364 */     this.monThread.Data = paramBoolean;
/*     */   } public void notifyOnOutputEmpty(boolean paramBoolean) {
/* 366 */     this.monThread.Output = paramBoolean;
/*     */   }
/* 368 */   public void notifyOnCTS(boolean paramBoolean) { this.monThread.CTS = paramBoolean; }
/* 369 */   public void notifyOnDSR(boolean paramBoolean) { this.monThread.DSR = paramBoolean; }
/* 370 */   public void notifyOnRingIndicator(boolean paramBoolean) { this.monThread.RI = paramBoolean; }
/* 371 */   public void notifyOnCarrierDetect(boolean paramBoolean) { this.monThread.CD = paramBoolean; }
/* 372 */   public void notifyOnOverrunError(boolean paramBoolean) { this.monThread.OE = paramBoolean; }
/* 373 */   public void notifyOnParityError(boolean paramBoolean) { this.monThread.PE = paramBoolean; }
/* 374 */   public void notifyOnFramingError(boolean paramBoolean) { this.monThread.FE = paramBoolean; } public void notifyOnBreakInterrupt(boolean paramBoolean) {
/* 375 */     this.monThread.BI = paramBoolean;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/* 381 */     setDTR(false);
/* 382 */     setDSR(false);
/* 383 */     nativeClose();
/* 384 */     super.close();
/* 385 */     this.fd = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void finalize() {
/* 391 */     if (this.fd > 0) close(); 
/*     */   } private static native void Initialize(); private native int open(String paramString) throws PortInUseException; private native void nativeSetI2CPortParams(int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws UnsupportedCommOperationException; native void setflowcontrol(int paramInt) throws IOException; public native int NativegetReceiveTimeout(); public native boolean NativeisReceiveTimeoutEnabled(); public native void NativeEnableReceiveTimeoutThreshold(int paramInt1, int paramInt2, int paramInt3); public native boolean isDTR(); public native void setDTR(boolean paramBoolean); public native void setRTS(boolean paramBoolean); private native void setDSR(boolean paramBoolean); public native boolean isCTS(); public native boolean isDSR(); public native boolean isCD(); public native boolean isRI(); public native boolean isRTS(); public native void sendBreak(int paramInt); private native void writeByte(int paramInt) throws IOException; private native void writeArray(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException; private native void drain() throws IOException; private native int nativeavailable() throws IOException; private native int readByte() throws IOException; private native int readArray(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException; native void eventLoop();
/*     */   private native void nativeClose();
/*     */   class I2COutputStream extends OutputStream { private final I2C this$0;
/*     */     I2COutputStream(I2C this$0) {
/* 396 */       this.this$0 = this$0;
/*     */     } public void write(int param1Int) throws IOException {
/* 398 */       this.this$0.writeByte(param1Int);
/*     */     }
/*     */     public void write(byte[] param1ArrayOfbyte) throws IOException {
/* 401 */       this.this$0.writeArray(param1ArrayOfbyte, 0, param1ArrayOfbyte.length);
/*     */     }
/*     */     public void write(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
/* 404 */       this.this$0.writeArray(param1ArrayOfbyte, param1Int1, param1Int2);
/*     */     }
/*     */     public void flush() throws IOException {
/* 407 */       this.this$0.drain();
/*     */     } }
/*     */   class I2CInputStream extends InputStream { private final I2C this$0;
/*     */     
/*     */     I2CInputStream(I2C this$0) {
/* 412 */       this.this$0 = this$0;
/*     */     } public int read() throws IOException {
/* 414 */       this.this$0.dataAvailable = 0;
/* 415 */       return this.this$0.readByte();
/*     */     }
/*     */     
/*     */     public int read(byte[] param1ArrayOfbyte) throws IOException {
/* 419 */       return read(param1ArrayOfbyte, 0, param1ArrayOfbyte.length);
/*     */     }
/*     */     
/*     */     public int read(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
/* 423 */       this.this$0.dataAvailable = 0;
/* 424 */       byte b = 0; int i = 0;
/* 425 */       int[] arrayOfInt = { param1ArrayOfbyte.length, this.this$0.InputBuffer, param1Int2 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 437 */       for (; arrayOfInt[b] == 0 && b < arrayOfInt.length; b++);
/* 438 */       i = arrayOfInt[b];
/* 439 */       while (b < arrayOfInt.length) {
/*     */         
/* 441 */         if (arrayOfInt[b] > 0)
/*     */         {
/* 443 */           i = Math.min(i, arrayOfInt[b]);
/*     */         }
/* 445 */         b++;
/*     */       } 
/* 447 */       i = Math.min(i, this.this$0.threshold);
/* 448 */       if (i == 0) i = 1; 
/* 449 */       int j = available();
/* 450 */       return this.this$0.readArray(param1ArrayOfbyte, param1Int1, i);
/*     */     }
/*     */     
/*     */     public int available() throws IOException {
/* 454 */       return this.this$0.nativeavailable();
/*     */     } }
/*     */ 
/*     */   
/*     */   class MonitorThread extends Thread {
/*     */     private boolean CTS;
/*     */     private boolean DSR;
/*     */     private boolean RI;
/*     */     private boolean CD;
/*     */     private boolean OE;
/*     */     private boolean PE;
/*     */     private boolean FE;
/*     */     private boolean BI;
/*     */     private boolean Data;
/*     */     private boolean Output;
/*     */     private final I2C this$0;
/*     */     
/* 471 */     MonitorThread(I2C this$0) { this.this$0 = this$0; this.CTS = false; this.DSR = false; this.RI = false; this.CD = false; this.OE = false; this.PE = false; this.FE = false; this.BI = false;
/*     */       this.Data = false;
/* 473 */       this.Output = false; } public void run() { this.this$0.eventLoop(); }
/*     */   
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\RXTXcomm.jar!\gnu\io\I2C.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
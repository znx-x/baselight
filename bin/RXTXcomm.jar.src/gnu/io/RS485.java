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
/*     */ final class RS485
/*     */   extends RS485Port
/*     */ {
/*     */   private int fd;
/*     */   
/*     */   static {
/*  74 */     System.loadLibrary("rxtxRS485");
/*  75 */     Initialize();
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
/*     */   public RS485(String paramString) throws PortInUseException {
/* 100 */     this.out = new RS485OutputStream(this);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 105 */     this.in = new RS485InputStream(this);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 127 */     this.speed = 9600;
/*     */ 
/*     */ 
/*     */     
/* 131 */     this.dataBits = 8;
/*     */ 
/*     */ 
/*     */     
/* 135 */     this.stopBits = 1;
/*     */ 
/*     */ 
/*     */     
/* 139 */     this.parity = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 144 */     this.flowmode = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 200 */     this.threshold = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 230 */     this.InputBuffer = 0;
/* 231 */     this.OutputBuffer = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 284 */     this.dataAvailable = 0; this.fd = open(paramString);
/*     */   } static boolean dsrFlag = false; private final RS485OutputStream out; private final RS485InputStream in; private int speed; private int dataBits; private int stopBits; private int parity; private int flowmode; private int timeout; private int threshold; private int InputBuffer; private int OutputBuffer; private RS485PortEventListener SPEventListener; private MonitorThread monThread; private int dataAvailable; public OutputStream getOutputStream() { return this.out; } public InputStream getInputStream() { return this.in; } public void setRS485PortParams(int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws UnsupportedCommOperationException { nativeSetRS485PortParams(paramInt1, paramInt2, paramInt3, paramInt4); this.speed = paramInt1; this.dataBits = paramInt2; this.stopBits = paramInt3; this.parity = paramInt4; } public int getBaudRate() { return this.speed; } public int getDataBits() { return this.dataBits; }
/* 286 */   public void sendEvent(int paramInt, boolean paramBoolean) { switch (paramInt) {
/*     */       case 1:
/* 288 */         this.dataAvailable = 1;
/* 289 */         if (this.monThread.Data)
/*     */           break;  return;
/*     */       case 2:
/* 292 */         if (this.monThread.Output) {
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
/* 312 */         if (this.monThread.CTS)
/*     */           break;  return;
/*     */       case 4:
/* 315 */         if (this.monThread.DSR)
/*     */           break;  return;
/*     */       case 5:
/* 318 */         if (this.monThread.RI)
/*     */           break;  return;
/*     */       case 6:
/* 321 */         if (this.monThread.CD)
/*     */           break;  return;
/*     */       case 7:
/* 324 */         if (this.monThread.OE)
/*     */           break;  return;
/*     */       case 8:
/* 327 */         if (this.monThread.PE)
/*     */           break;  return;
/*     */       case 9:
/* 330 */         if (this.monThread.FE)
/*     */           break;  return;
/*     */       case 10:
/* 333 */         if (this.monThread.BI)
/*     */           break;  return;
/*     */       default:
/* 336 */         System.err.println("unknown event:" + paramInt);
/*     */         return;
/*     */     } 
/* 339 */     RS485PortEvent rS485PortEvent = new RS485PortEvent(this, paramInt, !paramBoolean, paramBoolean);
/* 340 */     if (this.SPEventListener != null) this.SPEventListener.RS485Event(rS485PortEvent);  }
/*     */   public int getStopBits() { return this.stopBits; }
/*     */   public int getParity() { return this.parity; }
/*     */   public void setFlowControlMode(int paramInt) { try { setflowcontrol(paramInt); } catch (IOException iOException) { iOException.printStackTrace(); return; }  this.flowmode = paramInt; }
/*     */   public int getFlowControlMode() { return this.flowmode; }
/*     */   public void enableReceiveFraming(int paramInt) throws UnsupportedCommOperationException { throw new UnsupportedCommOperationException("Not supported"); }
/*     */   public void disableReceiveFraming() {}
/* 347 */   public boolean isReceiveFramingEnabled() { return false; } public int getReceiveFramingByte() { return 0; } public void disableReceiveTimeout() { enableReceiveTimeout(0); } public void enableReceiveTimeout(int paramInt) { if (paramInt >= 0) { this.timeout = paramInt; NativeEnableReceiveTimeoutThreshold(paramInt, this.threshold, this.InputBuffer); } else { System.out.println("Invalid timeout"); }  } public void addEventListener(RS485PortEventListener paramRS485PortEventListener) throws TooManyListenersException { if (this.SPEventListener != null) throw new TooManyListenersException(); 
/* 348 */     this.SPEventListener = paramRS485PortEventListener;
/* 349 */     this.monThread = new MonitorThread(this);
/* 350 */     this.monThread.start(); }
/*     */   public boolean isReceiveTimeoutEnabled() { return NativeisReceiveTimeoutEnabled(); }
/*     */   public int getReceiveTimeout() { return NativegetReceiveTimeout(); }
/*     */   public void enableReceiveThreshold(int paramInt) { if (paramInt >= 0) { this.threshold = paramInt; NativeEnableReceiveTimeoutThreshold(this.timeout, this.threshold, this.InputBuffer); } else { System.out.println("Invalid Threshold"); }  }
/* 354 */   public void disableReceiveThreshold() { enableReceiveThreshold(0); } public int getReceiveThreshold() { return this.threshold; } public boolean isReceiveThresholdEnabled() { return (this.threshold > 0); } public void setInputBufferSize(int paramInt) { this.InputBuffer = paramInt; } public int getInputBufferSize() { return this.InputBuffer; } public void setOutputBufferSize(int paramInt) { this.OutputBuffer = paramInt; } public int getOutputBufferSize() { return this.OutputBuffer; } public void removeEventListener() { this.SPEventListener = null;
/* 355 */     if (this.monThread != null) {
/* 356 */       this.monThread.interrupt();
/* 357 */       this.monThread = null;
/*     */     }  }
/*     */   
/*     */   public void notifyOnDataAvailable(boolean paramBoolean) {
/* 361 */     this.monThread.Data = paramBoolean;
/*     */   } public void notifyOnOutputEmpty(boolean paramBoolean) {
/* 363 */     this.monThread.Output = paramBoolean;
/*     */   }
/* 365 */   public void notifyOnCTS(boolean paramBoolean) { this.monThread.CTS = paramBoolean; }
/* 366 */   public void notifyOnDSR(boolean paramBoolean) { this.monThread.DSR = paramBoolean; }
/* 367 */   public void notifyOnRingIndicator(boolean paramBoolean) { this.monThread.RI = paramBoolean; }
/* 368 */   public void notifyOnCarrierDetect(boolean paramBoolean) { this.monThread.CD = paramBoolean; }
/* 369 */   public void notifyOnOverrunError(boolean paramBoolean) { this.monThread.OE = paramBoolean; }
/* 370 */   public void notifyOnParityError(boolean paramBoolean) { this.monThread.PE = paramBoolean; }
/* 371 */   public void notifyOnFramingError(boolean paramBoolean) { this.monThread.FE = paramBoolean; } public void notifyOnBreakInterrupt(boolean paramBoolean) {
/* 372 */     this.monThread.BI = paramBoolean;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/* 378 */     setDTR(false);
/* 379 */     setDSR(false);
/* 380 */     nativeClose();
/* 381 */     super.close();
/* 382 */     this.fd = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void finalize() {
/* 388 */     if (this.fd > 0) close(); 
/*     */   } private static native void Initialize(); private native int open(String paramString) throws PortInUseException; private native void nativeSetRS485PortParams(int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws UnsupportedCommOperationException; native void setflowcontrol(int paramInt) throws IOException; public native int NativegetReceiveTimeout(); public native boolean NativeisReceiveTimeoutEnabled(); public native void NativeEnableReceiveTimeoutThreshold(int paramInt1, int paramInt2, int paramInt3); public native boolean isDTR(); public native void setDTR(boolean paramBoolean); public native void setRTS(boolean paramBoolean); private native void setDSR(boolean paramBoolean); public native boolean isCTS(); public native boolean isDSR(); public native boolean isCD(); public native boolean isRI(); public native boolean isRTS(); public native void sendBreak(int paramInt); private native void writeByte(int paramInt) throws IOException; private native void writeArray(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException; private native void drain() throws IOException; private native int nativeavailable() throws IOException; private native int readByte() throws IOException; private native int readArray(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException; native void eventLoop();
/*     */   private native void nativeClose();
/*     */   class RS485OutputStream extends OutputStream { private final RS485 this$0;
/*     */     RS485OutputStream(RS485 this$0) {
/* 393 */       this.this$0 = this$0;
/*     */     } public void write(int param1Int) throws IOException {
/* 395 */       this.this$0.writeByte(param1Int);
/*     */     }
/*     */     public void write(byte[] param1ArrayOfbyte) throws IOException {
/* 398 */       this.this$0.writeArray(param1ArrayOfbyte, 0, param1ArrayOfbyte.length);
/*     */     }
/*     */     public void write(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
/* 401 */       this.this$0.writeArray(param1ArrayOfbyte, param1Int1, param1Int2);
/*     */     }
/*     */     public void flush() throws IOException {
/* 404 */       this.this$0.drain();
/*     */     } }
/*     */   class RS485InputStream extends InputStream { private final RS485 this$0;
/*     */     
/*     */     RS485InputStream(RS485 this$0) {
/* 409 */       this.this$0 = this$0;
/*     */     } public int read() throws IOException {
/* 411 */       this.this$0.dataAvailable = 0;
/* 412 */       return this.this$0.readByte();
/*     */     }
/*     */     
/*     */     public int read(byte[] param1ArrayOfbyte) throws IOException {
/* 416 */       return read(param1ArrayOfbyte, 0, param1ArrayOfbyte.length);
/*     */     }
/*     */     
/*     */     public int read(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
/* 420 */       this.this$0.dataAvailable = 0;
/* 421 */       byte b = 0; int i = 0;
/* 422 */       int[] arrayOfInt = { param1ArrayOfbyte.length, this.this$0.InputBuffer, param1Int2 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 434 */       for (; arrayOfInt[b] == 0 && b < arrayOfInt.length; b++);
/* 435 */       i = arrayOfInt[b];
/* 436 */       while (b < arrayOfInt.length) {
/*     */         
/* 438 */         if (arrayOfInt[b] > 0)
/*     */         {
/* 440 */           i = Math.min(i, arrayOfInt[b]);
/*     */         }
/* 442 */         b++;
/*     */       } 
/* 444 */       i = Math.min(i, this.this$0.threshold);
/* 445 */       if (i == 0) i = 1; 
/* 446 */       int j = available();
/* 447 */       return this.this$0.readArray(param1ArrayOfbyte, param1Int1, i);
/*     */     }
/*     */     
/*     */     public int available() throws IOException {
/* 451 */       return this.this$0.nativeavailable();
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
/*     */     private final RS485 this$0;
/*     */     
/* 468 */     MonitorThread(RS485 this$0) { this.this$0 = this$0; this.CTS = false; this.DSR = false; this.RI = false; this.CD = false; this.OE = false; this.PE = false; this.FE = false; this.BI = false;
/*     */       this.Data = false;
/* 470 */       this.Output = false; } public void run() { this.this$0.eventLoop(); }
/*     */   
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\RXTXcomm.jar!\gnu\io\RS485.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
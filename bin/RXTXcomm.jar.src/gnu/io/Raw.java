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
/*     */ final class Raw
/*     */   extends RawPort
/*     */ {
/*     */   private int ciAddress;
/*     */   
/*     */   static {
/*  74 */     System.loadLibrary("rxtxRaw");
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
/*     */   
/*     */   public Raw(String paramString) throws PortInUseException {
/* 101 */     this.out = new RawOutputStream(this);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 106 */     this.in = new RawInputStream(this);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 128 */     this.speed = 9600;
/*     */ 
/*     */ 
/*     */     
/* 132 */     this.dataBits = 8;
/*     */ 
/*     */ 
/*     */     
/* 136 */     this.stopBits = 1;
/*     */ 
/*     */ 
/*     */     
/* 140 */     this.parity = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 145 */     this.flowmode = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 175 */     this.timeout = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 201 */     this.threshold = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 231 */     this.InputBuffer = 0;
/* 232 */     this.OutputBuffer = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 285 */     this.dataAvailable = 0; this.ciAddress = Integer.parseInt(paramString); open(this.ciAddress);
/*     */   } static boolean dsrFlag = false; private final RawOutputStream out; private final RawInputStream in; private int speed; private int dataBits; private int stopBits; private int parity; private int flowmode; private int timeout; private int threshold; private int InputBuffer; private int OutputBuffer; private RawPortEventListener SPEventListener; private MonitorThread monThread; private int dataAvailable; public OutputStream getOutputStream() { return this.out; } public InputStream getInputStream() { return this.in; } public void setRawPortParams(int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws UnsupportedCommOperationException { nativeSetRawPortParams(paramInt1, paramInt2, paramInt3, paramInt4); this.speed = paramInt1; this.dataBits = paramInt2; this.stopBits = paramInt3; this.parity = paramInt4; } public int getBaudRate() { return this.speed; } public int getDataBits() { return this.dataBits; }
/* 287 */   public void sendEvent(int paramInt, boolean paramBoolean) { switch (paramInt) {
/*     */       case 1:
/* 289 */         this.dataAvailable = 1;
/* 290 */         if (this.monThread.Data)
/*     */           break;  return;
/*     */       case 2:
/* 293 */         if (this.monThread.Output) {
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
/* 313 */         if (this.monThread.CTS)
/*     */           break;  return;
/*     */       case 4:
/* 316 */         if (this.monThread.DSR)
/*     */           break;  return;
/*     */       case 5:
/* 319 */         if (this.monThread.RI)
/*     */           break;  return;
/*     */       case 6:
/* 322 */         if (this.monThread.CD)
/*     */           break;  return;
/*     */       case 7:
/* 325 */         if (this.monThread.OE)
/*     */           break;  return;
/*     */       case 8:
/* 328 */         if (this.monThread.PE)
/*     */           break;  return;
/*     */       case 9:
/* 331 */         if (this.monThread.FE)
/*     */           break;  return;
/*     */       case 10:
/* 334 */         if (this.monThread.BI)
/*     */           break;  return;
/*     */       default:
/* 337 */         System.err.println("unknown event:" + paramInt);
/*     */         return;
/*     */     } 
/* 340 */     RawPortEvent rawPortEvent = new RawPortEvent(this, paramInt, !paramBoolean, paramBoolean);
/* 341 */     if (this.SPEventListener != null) this.SPEventListener.RawEvent(rawPortEvent);  }
/*     */   public int getStopBits() { return this.stopBits; }
/*     */   public int getParity() { return this.parity; }
/*     */   public void setFlowControlMode(int paramInt) { try { setflowcontrol(paramInt); } catch (IOException iOException) { iOException.printStackTrace(); return; }  this.flowmode = paramInt; }
/*     */   public int getFlowControlMode() { return this.flowmode; }
/*     */   public void enableReceiveFraming(int paramInt) throws UnsupportedCommOperationException { throw new UnsupportedCommOperationException("Not supported"); }
/*     */   public void disableReceiveFraming() {}
/* 348 */   public boolean isReceiveFramingEnabled() { return false; } public int getReceiveFramingByte() { return 0; } public void disableReceiveTimeout() { enableReceiveTimeout(0); } public void enableReceiveTimeout(int paramInt) { if (paramInt >= 0) { this.timeout = paramInt; NativeEnableReceiveTimeoutThreshold(paramInt, this.threshold, this.InputBuffer); } else { System.out.println("Invalid timeout"); }  } public void addEventListener(RawPortEventListener paramRawPortEventListener) throws TooManyListenersException { if (this.SPEventListener != null) throw new TooManyListenersException(); 
/* 349 */     this.SPEventListener = paramRawPortEventListener;
/* 350 */     this.monThread = new MonitorThread(this);
/* 351 */     this.monThread.start(); }
/*     */   public boolean isReceiveTimeoutEnabled() { return NativeisReceiveTimeoutEnabled(); }
/*     */   public int getReceiveTimeout() { return NativegetReceiveTimeout(); }
/*     */   public void enableReceiveThreshold(int paramInt) { if (paramInt >= 0) { this.threshold = paramInt; NativeEnableReceiveTimeoutThreshold(this.timeout, this.threshold, this.InputBuffer); } else { System.out.println("Invalid Threshold"); }  }
/* 355 */   public void disableReceiveThreshold() { enableReceiveThreshold(0); } public int getReceiveThreshold() { return this.threshold; } public boolean isReceiveThresholdEnabled() { return (this.threshold > 0); } public void setInputBufferSize(int paramInt) { this.InputBuffer = paramInt; } public int getInputBufferSize() { return this.InputBuffer; } public void setOutputBufferSize(int paramInt) { this.OutputBuffer = paramInt; } public int getOutputBufferSize() { return this.OutputBuffer; } public void removeEventListener() { this.SPEventListener = null;
/* 356 */     if (this.monThread != null) {
/* 357 */       this.monThread.interrupt();
/* 358 */       this.monThread = null;
/*     */     }  }
/*     */   
/*     */   public void notifyOnDataAvailable(boolean paramBoolean) {
/* 362 */     this.monThread.Data = paramBoolean;
/*     */   } public void notifyOnOutputEmpty(boolean paramBoolean) {
/* 364 */     this.monThread.Output = paramBoolean;
/*     */   }
/* 366 */   public void notifyOnCTS(boolean paramBoolean) { this.monThread.CTS = paramBoolean; }
/* 367 */   public void notifyOnDSR(boolean paramBoolean) { this.monThread.DSR = paramBoolean; }
/* 368 */   public void notifyOnRingIndicator(boolean paramBoolean) { this.monThread.RI = paramBoolean; }
/* 369 */   public void notifyOnCarrierDetect(boolean paramBoolean) { this.monThread.CD = paramBoolean; }
/* 370 */   public void notifyOnOverrunError(boolean paramBoolean) { this.monThread.OE = paramBoolean; }
/* 371 */   public void notifyOnParityError(boolean paramBoolean) { this.monThread.PE = paramBoolean; }
/* 372 */   public void notifyOnFramingError(boolean paramBoolean) { this.monThread.FE = paramBoolean; } public void notifyOnBreakInterrupt(boolean paramBoolean) {
/* 373 */     this.monThread.BI = paramBoolean;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/* 379 */     setDTR(false);
/* 380 */     setDSR(false);
/* 381 */     nativeClose();
/* 382 */     super.close();
/* 383 */     this.ciAddress = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void finalize() {
/* 389 */     close();
/*     */   }
/*     */   class RawOutputStream extends OutputStream { private final Raw this$0;
/*     */     
/*     */     RawOutputStream(Raw this$0) {
/* 394 */       this.this$0 = this$0;
/*     */     } public void write(int param1Int) throws IOException {
/* 396 */       this.this$0.writeByte(param1Int);
/*     */     }
/*     */     public void write(byte[] param1ArrayOfbyte) throws IOException {
/* 399 */       this.this$0.writeArray(param1ArrayOfbyte, 0, param1ArrayOfbyte.length);
/*     */     }
/*     */     public void write(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
/* 402 */       this.this$0.writeArray(param1ArrayOfbyte, param1Int1, param1Int2);
/*     */     }
/*     */     public void flush() throws IOException {
/* 405 */       this.this$0.drain();
/*     */     } }
/*     */   class RawInputStream extends InputStream { private final Raw this$0;
/*     */     
/*     */     RawInputStream(Raw this$0) {
/* 410 */       this.this$0 = this$0;
/*     */     } public int read() throws IOException {
/* 412 */       this.this$0.dataAvailable = 0;
/* 413 */       return this.this$0.readByte();
/*     */     }
/*     */     
/*     */     public int read(byte[] param1ArrayOfbyte) throws IOException {
/* 417 */       return read(param1ArrayOfbyte, 0, param1ArrayOfbyte.length);
/*     */     }
/*     */     
/*     */     public int read(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
/* 421 */       this.this$0.dataAvailable = 0;
/* 422 */       byte b = 0; int i = 0;
/* 423 */       int[] arrayOfInt = { param1ArrayOfbyte.length, this.this$0.InputBuffer, param1Int2 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 435 */       for (; arrayOfInt[b] == 0 && b < arrayOfInt.length; b++);
/* 436 */       i = arrayOfInt[b];
/* 437 */       while (b < arrayOfInt.length) {
/*     */         
/* 439 */         if (arrayOfInt[b] > 0)
/*     */         {
/* 441 */           i = Math.min(i, arrayOfInt[b]);
/*     */         }
/* 443 */         b++;
/*     */       } 
/* 445 */       i = Math.min(i, this.this$0.threshold);
/* 446 */       if (i == 0) i = 1; 
/* 447 */       int j = available();
/* 448 */       return this.this$0.readArray(param1ArrayOfbyte, param1Int1, i);
/*     */     }
/*     */     
/*     */     public int available() throws IOException {
/* 452 */       return this.this$0.nativeavailable();
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
/*     */     private final Raw this$0;
/*     */     
/* 469 */     MonitorThread(Raw this$0) { this.this$0 = this$0; this.CTS = false; this.DSR = false; this.RI = false; this.CD = false; this.OE = false; this.PE = false; this.FE = false; this.BI = false;
/*     */       this.Data = false;
/* 471 */       this.Output = false; } public void run() { this.this$0.eventLoop(); }
/*     */   
/*     */   }
/*     */   
/*     */   public String getVersion() {
/* 476 */     return "$Id: Raw.java,v 1.1.2.17 2008-09-14 22:29:30 jarvi Exp $";
/*     */   }
/*     */   
/*     */   private static native void Initialize();
/*     */   
/*     */   private native int open(int paramInt) throws PortInUseException;
/*     */   
/*     */   private native void nativeSetRawPortParams(int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws UnsupportedCommOperationException;
/*     */   
/*     */   native void setflowcontrol(int paramInt) throws IOException;
/*     */   
/*     */   public native int NativegetReceiveTimeout();
/*     */   
/*     */   public native boolean NativeisReceiveTimeoutEnabled();
/*     */   
/*     */   public native void NativeEnableReceiveTimeoutThreshold(int paramInt1, int paramInt2, int paramInt3);
/*     */   
/*     */   public native boolean isDTR();
/*     */   
/*     */   public native void setDTR(boolean paramBoolean);
/*     */   
/*     */   public native void setRTS(boolean paramBoolean);
/*     */   
/*     */   private native void setDSR(boolean paramBoolean);
/*     */   
/*     */   public native boolean isCTS();
/*     */   
/*     */   public native boolean isDSR();
/*     */   
/*     */   public native boolean isCD();
/*     */   
/*     */   public native boolean isRI();
/*     */   
/*     */   public native boolean isRTS();
/*     */   
/*     */   public native void sendBreak(int paramInt);
/*     */   
/*     */   private native void writeByte(int paramInt) throws IOException;
/*     */   
/*     */   private native void writeArray(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
/*     */   
/*     */   private native void drain() throws IOException;
/*     */   
/*     */   private native int nativeavailable() throws IOException;
/*     */   
/*     */   private native int readByte() throws IOException;
/*     */   
/*     */   private native int readArray(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
/*     */   
/*     */   native void eventLoop();
/*     */   
/*     */   private native int nativeClose();
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\RXTXcomm.jar!\gnu\io\Raw.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
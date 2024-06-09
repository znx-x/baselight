/*     */ package gnu.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class CommPort
/*     */ {
/*     */   protected String name;
/*     */   private static final boolean debug = false;
/*     */   
/*     */   public abstract void enableReceiveFraming(int paramInt) throws UnsupportedCommOperationException;
/*     */   
/*     */   public abstract void disableReceiveFraming();
/*     */   
/*     */   public abstract boolean isReceiveFramingEnabled();
/*     */   
/*     */   public abstract int getReceiveFramingByte();
/*     */   
/*     */   public abstract void disableReceiveTimeout();
/*     */   
/*     */   public abstract void enableReceiveTimeout(int paramInt) throws UnsupportedCommOperationException;
/*     */   
/*     */   public abstract boolean isReceiveTimeoutEnabled();
/*     */   
/*     */   public abstract int getReceiveTimeout();
/*     */   
/*     */   public abstract void enableReceiveThreshold(int paramInt) throws UnsupportedCommOperationException;
/*     */   
/*     */   public abstract void disableReceiveThreshold();
/*     */   
/*     */   public abstract int getReceiveThreshold();
/*     */   
/*     */   public abstract boolean isReceiveThresholdEnabled();
/*     */   
/*     */   public abstract void setInputBufferSize(int paramInt);
/*     */   
/*     */   public abstract int getInputBufferSize();
/*     */   
/*     */   public abstract void setOutputBufferSize(int paramInt);
/*     */   
/*     */   public abstract int getOutputBufferSize();
/*     */   
/*     */   public void close() {
/*     */     try {
/* 103 */       CommPortIdentifier commPortIdentifier = CommPortIdentifier.getPortIdentifier(this);
/*     */       
/* 105 */       if (commPortIdentifier != null) {
/* 106 */         CommPortIdentifier.getPortIdentifier(this).internalClosePort();
/*     */       }
/* 108 */     } catch (NoSuchPortException noSuchPortException) {}
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract InputStream getInputStream() throws IOException;
/*     */ 
/*     */   
/*     */   public abstract OutputStream getOutputStream() throws IOException;
/*     */ 
/*     */   
/*     */   public String getName() {
/* 119 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 124 */     return this.name;
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\RXTXcomm.jar!\gnu\io\CommPort.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
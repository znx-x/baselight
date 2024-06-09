/*     */ package gnu.io;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class CommPortEnumerator
/*     */   implements Enumeration
/*     */ {
/*     */   private CommPortIdentifier index;
/*     */   private static final boolean debug = false;
/*     */   
/*     */   public Object nextElement() {
/*  93 */     synchronized (CommPortIdentifier.Sync) {
/*     */       
/*  95 */       if (this.index != null) { this.index = this.index.next; }
/*  96 */       else { this.index = CommPortIdentifier.CommPortIndex; }
/*  97 */        return this.index;
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
/*     */   public boolean hasMoreElements() {
/* 111 */     synchronized (CommPortIdentifier.Sync) {
/*     */       
/* 113 */       if (this.index != null) return !(this.index.next == null); 
/* 114 */       return !(CommPortIdentifier.CommPortIndex == null);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\diao\bin\diao.jar!\RXTXcomm.jar!\gnu\io\CommPortEnumerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package gnu.io;
/*    */ 
/*    */ import java.util.EventObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ParallelPortEvent
/*    */   extends EventObject
/*    */ {
/*    */   public static final int PAR_EV_ERROR = 1;
/*    */   public static final int PAR_EV_BUFFER = 2;
/*    */   private boolean OldValue;
/*    */   private boolean NewValue;
/*    */   private int eventType;
/*    */   
/*    */   public ParallelPortEvent(ParallelPort paramParallelPort, int paramInt, boolean paramBoolean1, boolean paramBoolean2) {
/* 81 */     super(paramParallelPort);
/* 82 */     this.OldValue = paramBoolean1;
/* 83 */     this.NewValue = paramBoolean2;
/* 84 */     this.eventType = paramInt;
/*    */   }
/*    */   
/*    */   public int getEventType() {
/* 88 */     return this.eventType;
/*    */   }
/*    */   
/*    */   public boolean getNewValue() {
/* 92 */     return this.NewValue;
/*    */   }
/*    */   
/*    */   public boolean getOldValue() {
/* 96 */     return this.OldValue;
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\RXTXcomm.jar!\gnu\io\ParallelPortEvent.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
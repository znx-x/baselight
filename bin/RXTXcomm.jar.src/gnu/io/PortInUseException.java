/*    */ package gnu.io;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PortInUseException
/*    */   extends Exception
/*    */ {
/*    */   public String currentOwner;
/*    */   
/*    */   PortInUseException(String paramString) {
/* 82 */     super(paramString);
/* 83 */     this.currentOwner = paramString;
/*    */   }
/*    */   
/*    */   public PortInUseException() {}
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\RXTXcomm.jar!\gnu\io\PortInUseException.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
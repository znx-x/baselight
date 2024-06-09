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
/*    */ public class RXTXVersion
/*    */ {
/*    */   static {
/* 78 */     System.loadLibrary("rxtxSerial");
/* 79 */   } private static String Version = "RXTX-2.2-20081207 Cloudhopper Build rxtx.cloudhopper.net";
/*    */ 
/*    */ 
/*    */   
/*    */   public static native String nativeGetVersion();
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getVersion() {
/* 88 */     return Version;
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\RXTXcomm.jar!\gnu\io\RXTXVersion.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
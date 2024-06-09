/*    */ package com.pnuema.java.barcode.symbologies;
/*    */ 
/*    */ import com.pnuema.java.barcode.BarcodeCommon;
/*    */ import com.pnuema.java.barcode.IBarcode;
/*    */ 
/*    */ public class FIM
/*    */   extends BarcodeCommon
/*    */   implements IBarcode {
/*    */   public enum FIMTypes {
/* 10 */     FIM_A, FIM_B, FIM_C, FIM_D, FIM_E; }
/*    */   
/*    */   public FIM(String input) {
/* 13 */     input = input.trim();
/*    */     
/* 15 */     String[] FIM_Codes = { "110010011", "101101101", "110101011", "111010111", "101000101" };
/* 16 */     switch (input) {
/*    */       case "A":
/*    */       case "a":
/* 19 */         setRawData(FIM_Codes[FIMTypes.FIM_A.ordinal()]);
/*    */         return;
/*    */       case "B":
/*    */       case "b":
/* 23 */         setRawData(FIM_Codes[FIMTypes.FIM_B.ordinal()]);
/*    */         return;
/*    */       case "C":
/*    */       case "c":
/* 27 */         setRawData(FIM_Codes[FIMTypes.FIM_C.ordinal()]);
/*    */         return;
/*    */       case "D":
/*    */       case "d":
/* 31 */         setRawData(FIM_Codes[FIMTypes.FIM_D.ordinal()]);
/*    */         return;
/*    */       case "E":
/*    */       case "e":
/* 35 */         setRawData(FIM_Codes[FIMTypes.FIM_E.ordinal()]);
/*    */         return;
/*    */     } 
/* 38 */     error("EFIM-1: Could not determine encoding type. (Only pass in A, B, C, D, or E)");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private String encodeFIM() {
/* 44 */     StringBuilder encoded = new StringBuilder();
/* 45 */     for (char c : getRawData().toCharArray()) {
/* 46 */       encoded.append(c).append("0");
/*    */     }
/*    */     
/* 49 */     encoded = new StringBuilder(encoded.substring(0, encoded.length() - 1));
/*    */     
/* 51 */     return encoded.toString();
/*    */   }
/*    */   
/*    */   public String getEncodedValue() {
/* 55 */     return encodeFIM();
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\com\pnuema\java\barcode\symbologies\FIM.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package com.pnuema.java.barcode.symbologies;
/*    */ 
/*    */ import com.pnuema.java.barcode.Barcode;
/*    */ import com.pnuema.java.barcode.BarcodeCommon;
/*    */ import com.pnuema.java.barcode.IBarcode;
/*    */ import com.pnuema.java.barcode.utils.Utils2of5;
/*    */ 
/*    */ public class Standard2of5
/*    */   extends BarcodeCommon
/*    */   implements IBarcode
/*    */ {
/* 12 */   private final String[] S25_Code = new String[] { "10101110111010", "11101010101110", "10111010101110", "11101110101010", "10101110101110", "11101011101010", "10111011101010", "10101011101110", "11101010111010", "10111010111010" };
/*    */   private final Barcode.TYPE type;
/*    */   
/*    */   public Standard2of5(String input, Barcode.TYPE encodingType) {
/* 16 */     setRawData(input);
/* 17 */     this.type = encodingType;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private String encodeStandard2Of5() {
/* 27 */     if (!checkNumericOnly(getRawData())) {
/* 28 */       error("ES25-1: Numeric Data Only");
/*    */     }
/*    */     
/* 31 */     StringBuilder result = new StringBuilder("11011010");
/* 32 */     String data = getRawData() + ((this.type == Barcode.TYPE.Standard2of5_Mod10) ? (String)Integer.valueOf(Utils2of5.CalculateMod10CheckDigit(getRawData())) : "");
/*    */     
/* 34 */     for (char c : data.toCharArray()) {
/* 35 */       result.append(this.S25_Code[Integer.parseInt(String.valueOf(c))]);
/*    */     }
/*    */     
/* 38 */     result.append((this.type == Barcode.TYPE.Standard2of5_Mod10) ? this.S25_Code[Utils2of5.CalculateMod10CheckDigit(getRawData())] : "");
/*    */ 
/*    */     
/* 41 */     result.append("1101011");
/* 42 */     return result.toString();
/*    */   }
/*    */   
/*    */   public String getEncodedValue() {
/* 46 */     return encodeStandard2Of5();
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\com\pnuema\java\barcode\symbologies\Standard2of5.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
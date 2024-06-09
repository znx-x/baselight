/*    */ package com.pnuema.java.barcode.symbologies;
/*    */ 
/*    */ import com.pnuema.java.barcode.Barcode;
/*    */ import com.pnuema.java.barcode.BarcodeCommon;
/*    */ import com.pnuema.java.barcode.IBarcode;
/*    */ import com.pnuema.java.barcode.utils.Utils2of5;
/*    */ 
/*    */ public class Interleaved2of5
/*    */   extends BarcodeCommon
/*    */   implements IBarcode
/*    */ {
/* 12 */   private final String[] I25_Code = new String[] { "NNWWN", "WNNNW", "NWNNW", "WWNNN", "NNWNW", "WNWNN", "NWWNN", "NNNWW", "WNNWN", "NWNWN" };
/*    */   private final Barcode.TYPE type;
/*    */   
/*    */   public Interleaved2of5(String input, Barcode.TYPE encodingType) {
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
/*    */   private String encodeInterleaved2Of5() {
/* 27 */     if (getRawData().length() % 2 != ((this.type == Barcode.TYPE.Interleaved2of5_Mod10) ? 1 : 0)) {
/* 28 */       error("EI25-1: Data length invalid.");
/*    */     }
/*    */     
/* 31 */     if (!checkNumericOnly(getRawData())) {
/* 32 */       error("EI25-2: Numeric Data Only");
/*    */     }
/*    */     
/* 35 */     StringBuilder result = new StringBuilder("1010");
/* 36 */     String data = getRawData() + ((this.type == Barcode.TYPE.Interleaved2of5_Mod10) ? (String)Integer.valueOf(Utils2of5.CalculateMod10CheckDigit(getRawData())) : "");
/*    */     
/* 38 */     for (int i = 0; i < data.length(); i += 2) {
/* 39 */       boolean bars = true;
/* 40 */       String patternbars = this.I25_Code[Integer.parseInt(String.valueOf(data.charAt(i)))];
/* 41 */       String patternspaces = this.I25_Code[Integer.parseInt(String.valueOf(data.charAt(i + 1)))];
/* 42 */       StringBuilder patternmixed = new StringBuilder();
/*    */ 
/*    */       
/* 45 */       while (!patternbars.isEmpty()) {
/* 46 */         patternmixed.append(patternbars.charAt(0)).append(patternspaces.toCharArray()[0]);
/* 47 */         patternbars = patternbars.substring(1);
/* 48 */         patternspaces = patternspaces.substring(1);
/*    */       } 
/*    */       
/* 51 */       for (char c1 : patternmixed.toString().toCharArray()) {
/* 52 */         if (bars) {
/* 53 */           result.append((c1 == 'N') ? "1" : "11");
/*    */         } else {
/* 55 */           result.append((c1 == 'N') ? "0" : "00");
/*    */         } 
/* 57 */         bars = !bars;
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 62 */     result.append("1101");
/* 63 */     return result.toString();
/*    */   }
/*    */   
/*    */   public String getEncodedValue() {
/* 67 */     return encodeInterleaved2Of5();
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\com\pnuema\java\barcode\symbologies\Interleaved2of5.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
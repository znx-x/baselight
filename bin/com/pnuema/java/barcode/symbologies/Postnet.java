/*    */ package com.pnuema.java.barcode.symbologies;
/*    */ 
/*    */ import com.pnuema.java.barcode.BarcodeCommon;
/*    */ import com.pnuema.java.barcode.IBarcode;
/*    */ 
/*    */ public class Postnet
/*    */   extends BarcodeCommon
/*    */   implements IBarcode
/*    */ {
/* 10 */   private final String[] POSTNET_Code = new String[] { "11000", "00011", "00101", "00110", "01001", "01010", "01100", "10001", "10010", "10100" };
/*    */   
/*    */   public Postnet(String input) {
/* 13 */     setRawData(input);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private String encodePostnet() {
/* 22 */     setRawData(getRawData().replace("-", ""));
/*    */ 
/*    */     
/* 25 */     if (!checkNumericOnly(getRawData())) {
/* 26 */       error("EPOSTNET-1: Numeric Data Only");
/* 27 */       return "";
/*    */     } 
/*    */     
/* 30 */     switch (getRawData().length()) {
/*    */       case 5:
/*    */       case 6:
/*    */       case 9:
/*    */       case 11:
/*    */         break;
/*    */       default:
/* 37 */         error("EPOSTNET-2: Invalid data length. (5, 6, 9, or 11 digits only)");
/*    */         break;
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 43 */     StringBuilder result = new StringBuilder("1");
/* 44 */     int checkdigitsum = 0;
/*    */     
/* 46 */     for (char c : getRawData().toCharArray()) {
/* 47 */       int index = Integer.parseInt(String.valueOf(c));
/* 48 */       result.append(this.POSTNET_Code[index]);
/* 49 */       checkdigitsum += index;
/*    */     } 
/*    */ 
/*    */     
/* 53 */     int temp = checkdigitsum % 10;
/* 54 */     int checkdigit = 10 - ((temp == 0) ? 10 : temp);
/*    */     
/* 56 */     result.append(this.POSTNET_Code[checkdigit]);
/*    */ 
/*    */     
/* 59 */     result.append("1");
/*    */     
/* 61 */     return result.toString();
/*    */   }
/*    */   
/*    */   public String getEncodedValue() {
/* 65 */     return encodePostnet();
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\com\pnuema\java\barcode\symbologies\Postnet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
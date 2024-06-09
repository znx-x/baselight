/*    */ package com.pnuema.java.barcode;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public abstract class BarcodeCommon {
/*  7 */   private String rawData = "";
/*  8 */   private List<String> errors = new ArrayList<>();
/*    */   
/*    */   protected void setRawData(String rawData) {
/* 11 */     this.rawData = rawData;
/*    */   }
/*    */   
/*    */   public String getRawData() {
/* 15 */     return this.rawData;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> getErrors() {
/* 20 */     return this.errors;
/*    */   }
/*    */   
/*    */   public void clearErrors() {
/* 24 */     this.errors.clear();
/*    */   }
/*    */   
/*    */   protected void error(String ErrorMessage) {
/* 28 */     this.errors.add(ErrorMessage);
/* 29 */     throw new RuntimeException(ErrorMessage);
/*    */   }
/*    */   
/*    */   protected static boolean checkNumericOnly(String data) {
/* 33 */     return data.matches("^\\d+$");
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\com\pnuema\java\barcode\BarcodeCommon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
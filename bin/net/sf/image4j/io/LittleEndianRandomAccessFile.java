/*    */ package net.sf.image4j.io;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.IOException;
/*    */ import java.io.RandomAccessFile;
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
/*    */ public class LittleEndianRandomAccessFile
/*    */   extends RandomAccessFile
/*    */ {
/*    */   public LittleEndianRandomAccessFile(File file, String mode) throws FileNotFoundException {
/* 26 */     super(file, mode);
/*    */   }
/*    */   
/*    */   public LittleEndianRandomAccessFile(String name, String mode) throws FileNotFoundException {
/* 30 */     super(name, mode);
/*    */   }
/*    */   
/*    */   public short readShortLE() throws IOException {
/* 34 */     short ret = readShort();
/* 35 */     ret = EndianUtils.swapShort(ret);
/* 36 */     return ret;
/*    */   }
/*    */   
/*    */   public int readIntLE() throws IOException {
/* 40 */     int ret = readInt();
/* 41 */     ret = EndianUtils.swapInteger(ret);
/* 42 */     return ret;
/*    */   }
/*    */   
/*    */   public float readFloatLE() throws IOException {
/* 46 */     float ret = readFloat();
/* 47 */     ret = EndianUtils.swapFloat(ret);
/* 48 */     return ret;
/*    */   }
/*    */   
/*    */   public long readLongLE() throws IOException {
/* 52 */     long ret = readLong();
/* 53 */     ret = EndianUtils.swapLong(ret);
/* 54 */     return ret;
/*    */   }
/*    */   
/*    */   public double readDoubleLE() throws IOException {
/* 58 */     double ret = readDouble();
/* 59 */     ret = EndianUtils.swapDouble(ret);
/* 60 */     return ret;
/*    */   }
/*    */   
/*    */   public void writeShortLE(short value) throws IOException {
/* 64 */     value = EndianUtils.swapShort(value);
/* 65 */     writeShort(value);
/*    */   }
/*    */   
/*    */   public void writeIntLE(int value) throws IOException {
/* 69 */     value = EndianUtils.swapInteger(value);
/* 70 */     writeInt(value);
/*    */   }
/*    */   
/*    */   public void writeFloatLE(float value) throws IOException {
/* 74 */     value = EndianUtils.swapFloat(value);
/* 75 */     writeFloat(value);
/*    */   }
/*    */   
/*    */   public void writeLongLE(long value) throws IOException {
/* 79 */     value = EndianUtils.swapLong(value);
/* 80 */     writeLong(value);
/*    */   }
/*    */   
/*    */   public void writeDoubleLE(double value) throws IOException {
/* 84 */     value = EndianUtils.swapDouble(value);
/* 85 */     writeDouble(value);
/*    */   }
/*    */ }


/* Location:              C:\diao\bin\diao.jar!\net\sf\image4j\io\LittleEndianRandomAccessFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
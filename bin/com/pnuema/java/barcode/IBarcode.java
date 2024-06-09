package com.pnuema.java.barcode;

import java.util.List;

public interface IBarcode {
  String getEncodedValue();
  
  String getRawData();
  
  List<String> getErrors();
  
  void clearErrors();
}


/* Location:              C:\diao\bin\diao.jar!\com\pnuema\java\barcode\IBarcode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
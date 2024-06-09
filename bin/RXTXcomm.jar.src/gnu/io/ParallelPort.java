package gnu.io;

import java.util.TooManyListenersException;

public abstract class ParallelPort extends CommPort {
  public static final int LPT_MODE_ANY = 0;
  
  public static final int LPT_MODE_SPP = 1;
  
  public static final int LPT_MODE_PS2 = 2;
  
  public static final int LPT_MODE_EPP = 3;
  
  public static final int LPT_MODE_ECP = 4;
  
  public static final int LPT_MODE_NIBBLE = 5;
  
  public abstract int getMode();
  
  public abstract int setMode(int paramInt) throws UnsupportedCommOperationException;
  
  public abstract void restart();
  
  public abstract void suspend();
  
  public abstract boolean isPaperOut();
  
  public abstract boolean isPrinterBusy();
  
  public abstract boolean isPrinterError();
  
  public abstract boolean isPrinterSelected();
  
  public abstract boolean isPrinterTimedOut();
  
  public abstract int getOutputBufferFree();
  
  public abstract void addEventListener(ParallelPortEventListener paramParallelPortEventListener) throws TooManyListenersException;
  
  public abstract void removeEventListener();
  
  public abstract void notifyOnError(boolean paramBoolean);
  
  public abstract void notifyOnBuffer(boolean paramBoolean);
}


/* Location:              C:\diao\bin\diao.jar!\RXTXcomm.jar!\gnu\io\ParallelPort.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
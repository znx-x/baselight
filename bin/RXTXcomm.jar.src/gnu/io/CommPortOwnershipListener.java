package gnu.io;

import java.util.EventListener;

public interface CommPortOwnershipListener extends EventListener {
  public static final int PORT_OWNED = 1;
  
  public static final int PORT_UNOWNED = 2;
  
  public static final int PORT_OWNERSHIP_REQUESTED = 3;
  
  void ownershipChange(int paramInt);
}


/* Location:              C:\diao\bin\diao.jar!\RXTXcomm.jar!\gnu\io\CommPortOwnershipListener.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
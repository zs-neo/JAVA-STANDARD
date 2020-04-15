package standard.clone.serial;

import java.io.Serializable;

public class Thing3 implements Cloneable {
  
  @Override
  public Object clone() {
    Object o = null;
    try {
      o = super.clone();
    } catch (CloneNotSupportedException e) {
      System.out.println("Thing3 can't clone");
    }
    return o;
  }
  
}

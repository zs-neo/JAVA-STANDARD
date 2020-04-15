package standard.clone;

public class DepthReading implements Cloneable {
  
  private double depth;
  
  public DepthReading(double depth) {
    this.depth = depth;
  }
  
  @Override
  public Object clone() {
    Object o = null;
    try {
      o = super.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return o;
  }
  
}

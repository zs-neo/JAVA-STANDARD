package standard;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class SafePoint {
  
  private int x,y;
  
  public SafePoint(int[] a) {
    this.x = a[0];
    this.y = a[1];
  }
  
  public SafePoint(SafePoint p){
    this(p.get());
  }
  
  public synchronized int[] get(){
    return new int[]{x,y};
  }
  
  public synchronized void set(int x,int y){
    this.x = x;
    this.y = y;
  }
  
}

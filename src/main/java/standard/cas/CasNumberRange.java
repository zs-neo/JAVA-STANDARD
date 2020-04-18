package standard.cas;

import java.util.concurrent.atomic.AtomicReference;

public class CasNumberRange {
  
  private static class InPair {
    final int lower;
    final int upper;
    
    public InPair(int lower, int upper) {
      this.lower = lower;
      this.upper = upper;
    }
  }
  
  private final AtomicReference<InPair> values = new AtomicReference<InPair>(new InPair(0, 0));
  
  public int getLower() {
    return values.get().lower;
  }
  
  public int getUpper() {
    return values.get().upper;
  }
  
  public void setLower(int i) {
    while (true) {
      InPair oldv = values.get();
      if (i > oldv.upper) {
        throw new IllegalArgumentException("xx");
      }
      InPair newv = new InPair(i, oldv.upper);
      if (values.compareAndSet(oldv, newv)) return;
    }
  }
}

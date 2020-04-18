package standard.cas;

public class SimulatedCAS {
  
  /**
   * 首先从内存V中读取原值A,由A生成新值B,然后使用CAS原子化地把V的值从A改成B
   */
  private int value;
  
  public synchronized int get() {
    return value;
  }
  
  /**
   * 比较并交换,首先判断是否旧值和预期值是否相等
   *
   * @param expectValue
   * @param newValue
   * @return
   */
  public synchronized int compareAndSwap(int expectValue, int newValue) {
    int oldValue = value;
    if (oldValue == expectValue) {
      value = expectValue;
    }
    return oldValue;
  }
  
  public synchronized boolean compareAndSet(int expectValue, int newValue) {
    return expectValue == compareAndSwap(expectValue, newValue);
  }
  
}

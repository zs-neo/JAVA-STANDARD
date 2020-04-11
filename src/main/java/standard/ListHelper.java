package standard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListHelper<E> {
  
  public List<E> list = Collections.synchronizedList(new ArrayList<>());
  
  /**
   * 非线程安全，锁并未加到list上
   *
   * @param x
   * @return
   */
  public synchronized boolean putIfAbsent(E x) {
    boolean absent = !list.contains(x);
    if (absent) {
      list.add(x);
    }
    return absent;
  }
  
  /**
   * 线程安全
   *
   * @param x
   * @return
   */
  public boolean safePutIfAbsent(E x) {
    synchronized (list) {
      boolean absent = !list.contains(x);
      if (absent) {
        list.add(x);
      }
      return absent;
    }
  }
  
}

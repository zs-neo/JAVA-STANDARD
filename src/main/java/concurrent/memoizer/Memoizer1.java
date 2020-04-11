package concurrent.memoizer;

import java.util.HashMap;
import java.util.Map;

/**
 * 效率较低，同步了compute方法。而且hashMap也不是线程安全的。
 *
 * @param <A>
 * @param <V>
 */
public class Memoizer1<A, V> implements Computable<A, V> {
  
  private final Map<A, V> cache = new HashMap<>();
  private final Computable<A, V> c;
  
  public Memoizer1(Computable<A, V> c) {
    this.c = c;
  }
  
  @Override
  public synchronized V compute(A arg) throws InterruptedException {
    V result = cache.get(arg);
    if (result == null) {
      result = c.compute(arg);
      cache.put(arg, result);
    }
    return result;
  }
}

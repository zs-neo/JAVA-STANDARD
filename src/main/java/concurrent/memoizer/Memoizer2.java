package concurrent.memoizer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 选用concurrentHashMap替换HashMap，不需要做外部的同步了。
 * 但是当两个线程同时调用compute的时候存在一个漏洞，会导致它们计算相同的值。
 *
 * @param <A>
 * @param <V>
 */
public class Memoizer2<A, V> implements Computable<A, V> {
  
  private final Map<A, V> cache = new ConcurrentHashMap<>();
  private final Computable<A, V> c;
  
  public Memoizer2(Computable<A, V> c) {
    this.c = c;
  }
  
  @Override
  public V compute(A arg) throws InterruptedException {
    V result = cache.get(arg);
    if (result == null) {
      result = c.compute(arg);
      cache.put(arg, result);
    }
    return result;
  }
}

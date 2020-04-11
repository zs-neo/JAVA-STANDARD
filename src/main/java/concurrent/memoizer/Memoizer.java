package concurrent.memoizer;

import java.util.Map;
import java.util.concurrent.*;

public class Memoizer<A,V> implements Computable<A,V> {
  
  private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
  private final Computable<A, V> c;
  
  public Memoizer(Computable<A, V> c) {
    this.c = c;
  }
  
  @Override
  public V compute(A arg) throws InterruptedException {
    while(true){
      Future<V> result = cache.get(arg);
      if (result == null) {
        Callable<V> eval = new Callable<V>() {
          @Override
          public V call() throws InterruptedException {
            return c.compute(arg);
          }
        };
        FutureTask<V> ft = new FutureTask<>(eval);
        result = cache.putIfAbsent(arg,ft);
        if(result == null){
          result = ft;
          ft.run();
        }
      }
      try {
        return result.get();
      }catch (CancellationException e){
        cache.remove(arg,result);
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    }
  }
  
}

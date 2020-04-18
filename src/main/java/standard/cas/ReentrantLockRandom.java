package standard.cas;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockRandom {
  
  private final Lock lock = new ReentrantLock();
  private AtomicInteger atomic;
  private int seed;
  
  public ReentrantLockRandom(int seed) {
    this.seed = seed;
  }
  
  public int calculate(int i){
    return i+1;
  }
  
  public int nextInt(int n) {
    lock.lock();
    try{
      int s = seed;
      seed = calculate(seed);
      return seed;
    }finally {
      lock.unlock();
    }
  }
  
  public int nextInteger(int n){
    while(true){
      int s = atomic.get();
      int next = calculate(s);
      if(atomic.compareAndSet(s,next)){
        return seed;
      }
    }
  }
}

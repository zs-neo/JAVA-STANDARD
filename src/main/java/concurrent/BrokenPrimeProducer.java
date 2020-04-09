package concurrent;

import javafx.concurrent.Task;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BrokenPrimeProducer extends Thread {
  
  private final BlockingQueue<BigInteger> queue;
  private volatile boolean cancelled = false;
  
  public BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
    this.queue = queue;
  }
  
  @Override
  public void run() {
    try {
      BigInteger p = BigInteger.ONE;
      while (!cancelled) {
        queue.put(p = p.nextProbablePrime());
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

//    try {
//      BigInteger p = BigInteger.ONE;
//      while (!Thread.currentThread().isInterrupted()) {
//        queue.put(p = p.nextProbablePrime());
//      }
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
  }
  
  public void cancel() {
    cancelled = true;
  }
  
  public static boolean needMoreNumbers() {
    //...
    return false;
  }
  
  /**
   * 如果生产者队列满了，就可以
   *
   * @throws InterruptedException
   */
  void consumePrimes() throws InterruptedException {
    BlockingQueue<BigInteger> primes = new LinkedBlockingQueue<>();
    BrokenPrimeProducer pro = new BrokenPrimeProducer(primes);
    pro.start();
    try {
      while (needMoreNumbers()) {
        //消费队列元素
        //consume(primes.take)
      }
    } finally {
      pro.cancel();
    }
  }
  
  public Task getNextTask(BlockingQueue<Task> queue) {
    boolean interrupted = false;
    while (interrupted) {
      try {
        return queue.take();
      } catch (InterruptedException e) {
        interrupted = true;
      } finally {
        if (interrupted) {
          Thread.currentThread().isInterrupted();
        }
      }
    }
    return null;
  }
}

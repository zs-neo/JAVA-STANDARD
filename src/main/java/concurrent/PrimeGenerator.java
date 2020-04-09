package concurrent;

import net.jcip.annotations.ThreadSafe;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

@ThreadSafe
public class PrimeGenerator implements Runnable {
  
  private final List<BigInteger> primes = new ArrayList<BigInteger>();
  private volatile boolean cancelled;
  
  public void run() {
    BigInteger p = BigInteger.ONE;
    while(!cancelled){
      p = p.nextProbablePrime();
      synchronized (this){
        primes.add(p);
      }
    }
  }
  
  public void cancel(){
    this.cancelled = true;
  }
  
  public synchronized List<BigInteger> getPrimes() {
    return new ArrayList<BigInteger>(primes);
  }
  
  List<BigInteger> runOneSecond() throws InterruptedException{
    PrimeGenerator primeGenerator = new PrimeGenerator();
    new Thread(primeGenerator).start();
    try{
      SECONDS.sleep(1);
    }finally {
      primeGenerator.cancel();
    }
    return primeGenerator.getPrimes();
  }
  
  public static void main(String[] args)throws InterruptedException{
    PrimeGenerator primeGenerator = new PrimeGenerator();
    primeGenerator.runOneSecond().forEach(i->{
      System.out.println(i);
    });
  }
  
  
  
  public static void timedRun(final Runnable r, long timeout, TimeUnit unit) throws InterruptedException{
    class RethrowableTask implements Runnable{
      private volatile Throwable t;
      @Override
      public void run(){
        try{
          r.run();
        }catch (Throwable t){
          this.t = t;
        }
      }
    }
    RethrowableTask rethrowableTask = new RethrowableTask();
    final Thread thread = new Thread(rethrowableTask);
    thread.start();
//    cancelExec.schedule(new Runnable(){
//      public void run(){
//        thread.interrupt();
//      }
//    },timeout,unit);
//    thread.join(unit.toMillis(timeout));
//    rethrowableTask.reThrow();
  }
}

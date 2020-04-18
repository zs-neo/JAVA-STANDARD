package test.consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueueTest {
  
  public static void main(String[] args) {
    ExecutorService exec = Executors.newFixedThreadPool(10);
    
    ArrayBlockingQueue<CostomerTask> queue = new ArrayBlockingQueue<CostomerTask>(10);
    
    new Thread(new Runnable() {
      @Override
      public void run() {
        while (!Thread.currentThread().isInterrupted()) {
          try {
            queue.put(new CostomerTask());
            TimeUnit.SECONDS.sleep(1);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }).start();
    
    new Thread(new Runnable() {
      @Override
      public void run() {
        while (!Thread.currentThread().isInterrupted()) {
          CostomerTask task;
          try {
            while ((task = queue.take()) != null && (!Thread.currentThread().isInterrupted())) {
              exec.submit(task);
            }
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }).start();
  }
  
  static class CostomerTask implements Runnable {
    @Override
    public void run() {
      System.out.println(System.currentTimeMillis());
    }
  }
  
}

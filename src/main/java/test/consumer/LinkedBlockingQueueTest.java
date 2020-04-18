package test.consumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class LinkedBlockingQueueTest {
  
  public static void main(String[] args) {
    ExecutorService exec = Executors.newFixedThreadPool(50);
    LinkedBlockingQueue<MyTask> queue = new LinkedBlockingQueue<MyTask>();
    
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          while (!Thread.currentThread().isInterrupted()) {
            queue.put(new MyTask());
            TimeUnit.SECONDS.sleep(1);
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
    
    new Thread(new Runnable() {
      @Override
      public void run() {
        MyTask task;
        try {
          while ((task = queue.take()) != null && (!Thread.currentThread().isInterrupted())) {
            exec.submit(task);
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
    
  }
  
  static class MyTask implements Runnable {
    @Override
    public void run() {
      System.out.println(System.currentTimeMillis());
    }
  }
  
}

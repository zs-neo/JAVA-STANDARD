package concurrent;

import java.util.concurrent.CountDownLatch;

public class TestHarness {
  
  public long timeTask(int nThread, final Runnable task) throws InterruptedException {
    final CountDownLatch start = new CountDownLatch(1);
    final CountDownLatch end = new CountDownLatch(nThread);
    for (int i = 0; i < nThread; i++) {
      Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
          try{
            start.await();
            try{
              task.run();
            }finally {
              end.countDown();
            }
          }catch (InterruptedException e){
          
          }
        }
      });
    }
    long startTime = System.nanoTime();
    start.countDown();
    end.await();
    long endTime = System.nanoTime();
    return endTime-startTime;
  }
  
}

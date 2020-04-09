package concurrent;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PoisonExample {
  
  private final static File POISON = new File("");
  private final ConsumerThread consumerThread = new ConsumerThread();
  private final ProducerThread producerThread = new ProducerThread();
  private final BlockingQueue<File> blockingQueue = new LinkedBlockingQueue<>();
  
  class ProducerThread extends Thread {
    @Override
    public void run() {
      try {
        crawl();
      } catch (InterruptedException e) {
      
      } finally {
        while (true) {
          try {
            blockingQueue.put(POISON);
            break;
          } catch (InterruptedException e) {
            //retry
          }
        }
      }
    }
    private void crawl() throws InterruptedException {
    
    }
  }
  
  class ConsumerThread extends Thread {
    public void run(){
      try {
//        crawl();
//      } catch (InterruptedException e) {
    
      } finally {
        while (true) {
          try {
            File f = blockingQueue.take();
            if(f == POISON){
              break;
            }else{
              //...
            }
          } catch (InterruptedException e) {

          }
        }
      }
    }
  }
  
  public void start() {
    producerThread.start();
    consumerThread.start();
  }
  
  public void stop() {
    producerThread.interrupt();
  }
  
  public void awaitTermination() throws InterruptedException {
    consumerThread.join();
  }
  
}

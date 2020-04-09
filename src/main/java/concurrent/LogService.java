package concurrent;

import org.omg.CORBA.TIMEOUT;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogService {
  
  private final BlockingQueue<String> queue;
  private final ExecutorService exec = Executors.newSingleThreadExecutor();
  private final LoggerThread logger;
  private final PrintWriter writer;
  private boolean shutDown;
  private int reservations;
  
  public LogService(BlockingQueue<String> queue, LoggerThread logger, PrintWriter writer, boolean shutDown, int reservations) {
    this.queue = queue;
    this.logger = logger;
    this.writer = writer;
    this.shutDown = shutDown;
    this.reservations = reservations;
  }
  
  public void start(){
    logger.start();
  }
  
  public void stop() throws InterruptedException{
//    try{
//      exec.shutdown();
//      exec.awaitTermination(TIMEOUT,UNIT);
//    }finally {
//      writer.close();
//    }
    synchronized (this){
      shutDown = true;
    }
    logger.interrupt();
  }
  
  public void log(String msg) throws InterruptedException{
    synchronized (this){
      if(shutDown){
        throw new IllegalStateException();
      }
      ++reservations;
    }
    queue.put(msg);
  }
  
  /**
   * 创建子任务必须是原子的。但是我们不希望在消息加入队列时加锁。
   * 我们可以原子化的检查并关闭，并有条件地递增记录获得提交消息权利的计数器。
   */
  private class LoggerThread extends Thread {
    @Override
    public void run() {
      try {
        while (true) {
          try {
            synchronized (LogService.this) {
              if (shutDown && reservations == 0) {
                break;
              }
              String msg = queue.take();
              synchronized (LogService.this) {
                --reservations;
              }
              writer.println(msg);
            }
          } catch (InterruptedException e) {
            e.printStackTrace();
            //重试
          }
        }
      } finally {
        writer.close();
      }
    }
  }
  
}

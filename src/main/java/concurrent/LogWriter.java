package concurrent;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

public class LogWriter {
  
  private final BlockingQueue<String> queue;
  private final LoggerThread logger;
  private final PrintWriter writer;
  private boolean shutDown;
  
  public LogWriter(BlockingQueue<String> queue, LoggerThread logger, PrintWriter writer) {
    this.queue = queue;
    this.logger = logger;
    this.writer = writer;
  }
  
  /**
   * 在收到关闭请求后消费者会离开队列写出所有等待中的消息，并将log中所有阻塞的生产者解除阻塞。
   *
   * @param msg
   * @throws InterruptedException
   */
  public void log(String msg) throws InterruptedException {
    if (!this.shutDown) {
      queue.put(msg);
    } else {
      throw new IllegalStateException("log is shutDown!");
    }
  }
  
  private class LoggerThread extends Thread {
    private final PrintWriter printWriter;
    
    public LoggerThread(PrintWriter printWriter) {
      this.printWriter = printWriter;
    }
    
    @Override
    public void run() {
      try {
        while (true) {
          //take方法响应中断，但是只会取消消费者，生产者有很多其他线程执行无法正常取消
          printWriter.println(queue.take());
        }
      } catch (InterruptedException e) {
      } finally {
        printWriter.close();
      }
    }
    
  }
}

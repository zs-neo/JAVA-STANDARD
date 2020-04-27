package reactor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Acceptor implements Runnable {
  
  private int port;
  private Selector selector;
  
  private BlockingQueue<InputSource> queue = new LinkedBlockingQueue<InputSource>();
  
  public Acceptor(int port, Selector selector) {
    this.port = port;
    this.selector = selector;
  }
  
  public int getPort() {
    return this.port;
  }
  
  public void addNewConnection(InputSource source) {
    System.out.println("acceptor add new source " + source.getId());
    queue.offer(source);
  }
  
  @Override
  public void run() {
    System.out.println("acceptor run");
    InputSource source;
    while (true) {
      source = null;
      try {
        source = queue.take();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      if (source != null) {
        Event acceptEvent = new Event();
        acceptEvent.setSource(source);
        acceptEvent.setType(EventType.ACCEPT);
        selector.addEvent(acceptEvent);
      }
    }
  }
  
}

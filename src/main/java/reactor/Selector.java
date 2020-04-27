package reactor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Selector {
  
  private BlockingQueue queue = new LinkedBlockingQueue(10);
  
  private Object lock = new Object();
  
  List<Event> select() {
    return select(0);
  }
  
  List<Event> select(long timeout) {
    if (timeout > 0) {
      if (queue.isEmpty()) {
        synchronized (lock) {
          if (queue.isEmpty()) {
            try {
              lock.wait(timeout);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      }
    }
    List<Event> events = new ArrayList<Event>();
    //choose availale(by some rules) event
    queue.drainTo(events);
    return events;
  }
  
  public void addEvent(Event event){
    System.out.println("selector add event "+event.getSource().getId());
    boolean offer = queue.offer(event);
    if(offer){
      synchronized (lock){
        lock.notify();
      }
    }
  }
  
}

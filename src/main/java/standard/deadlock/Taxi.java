package standard.deadlock;

import standard.entity.Point;

public class Taxi {
  
  private Point location, destination;
  private final Dispatcher dispatcher;
  
  public Taxi(Dispatcher dispatcher) {
    this.dispatcher = dispatcher;
  }
  
  public synchronized Point getLocation() {
    return location;
  }
  
  public synchronized void setLocation(Point location) {
    //线程不安全
//    this.location = location;
//    if(location.equals(destination)){
//      dispatcher.notifyAvailable(this);
//    }
    
    //线程安全
    boolean reachedDestination;
    synchronized (this) {
      this.location = location;
      reachedDestination = location.equals(destination);
    }
    if (reachedDestination) {
      dispatcher.notifyAvailable(this);
    }
  }
}

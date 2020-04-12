package standard.deadlock;

import java.util.HashSet;
import java.util.Set;

public class Dispatcher {
  
  private final Set<Taxi> taxis;
  private final Set<Taxi> availableTaxis;
  
  public Dispatcher() {
    this.taxis = new HashSet<>();
    this.availableTaxis = new HashSet<>();
  }
  
  public synchronized void notifyAvailable(Taxi taxi){
    availableTaxis.add(taxi);
  }
  
  public void getSomething(){
    //线程不安全
//    Image image = new Image();
//    for(Taxi t : taxis){
//      image.dosomething();
//    }
    //使用开放调用，去除synchorized，线程安全
    Set<Taxi> copy;
    synchronized (this){
      copy = new HashSet<Taxi>(taxis);
    }
//    Image image = new Image();
//    for (Taxi t : copy) {
//      image.dosomething();
//    }
  }
}

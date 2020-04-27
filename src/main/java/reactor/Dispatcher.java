package reactor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Dispatcher {
  
  Map<EventType, EventHandler> map = new ConcurrentHashMap<EventType, EventHandler>();
  
  Selector selector;
  
  public Dispatcher(Selector selector) {
    this.selector = selector;
  }
  
  public void registerEventHandler(EventType eventType, EventHandler eventHandler) {
    System.out.println("add accept handler");
    map.put(eventType, eventHandler);
  }
  
  public void handleEvents() {
    dispatch();
  }
  
  public void dispatch() {
    //dispatch event to handler
    while (true) {
      List<Event> eventList = selector.select();
      
      for (Event event : eventList) {
        EventHandler handler = map.get(event.getType());
        handler.handle(event);
      }
    }
  }
  
}

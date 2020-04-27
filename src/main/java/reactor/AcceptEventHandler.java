package reactor;

public class AcceptEventHandler extends EventHandler {
  
  private Selector selector;
  
  public AcceptEventHandler(Selector selector) {
    this.selector = selector;
  }
  
  @Override
  public void handle(Event event) {
    if (event.getType().equals(EventType.ACCEPT)) {
      
      //execute accept event
      System.out.println("-execute accept event " + event.getSource().getId() +" "+event.getType());
      
      //change to read
      Event readEvent = new Event();
      readEvent.setSource(event.getSource());
      readEvent.setType(EventType.READ);
      
      selector.addEvent(event);
    }
  }
}

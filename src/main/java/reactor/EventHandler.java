package reactor;

public abstract class EventHandler {
  
  private InputSource source;
  
  public abstract void handle(Event event);
  
  public void setSource(InputSource source) {
    this.source = source;
  }
}

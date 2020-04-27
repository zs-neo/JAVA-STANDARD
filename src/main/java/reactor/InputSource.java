package reactor;

public class InputSource {
  
  private Object data;
  private long id;
  
  public InputSource(Object data, long id) {
    this.data = data;
    this.id = id;
  }
  
  public Object getData() {
    return data;
  }
  
  public long getId() {
    return id;
  }
  
  @Override
  public String toString() {
    return "InputSource{" +
      "data=" + data +
      ", id=" + id +
      '}';
  }
}

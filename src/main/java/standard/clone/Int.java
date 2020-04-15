package standard.clone;

class Int {
  private int i;
  
  public Int(int ii) {
    i = ii;
  }
  
  public void increment() {
    i++;
  }
  
  @Override
  public String toString() {
    return Integer.toString(i);
  }
}

package standard.entity;

public class Chess extends BoardGame {
  
  private final int i;
  
  public Chess() {
    super(11);
    i =2;
    System.out.println("Chess");
  }
  
  public static void main(String[] args) {
    Chess x = new Chess();
  }
  
}

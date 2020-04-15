package standard.polymorphism;

public class Brass extends Wind {
  
  @Override
  public void adjust() {
    System.out.println("Brass adjust");
  }
  
  @Override
  public void play() {
    System.out.println("Brass play");
  }
  
}

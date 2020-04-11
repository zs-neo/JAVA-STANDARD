package standard.Polymorphism;

public class Stringed extends Instrument {
  
  @Override
  public void play() {
    System.out.println("Stringed play");
  }
  
  @Override
  public String what() {
    return "Stringed";
  }
  
  @Override
  public void adjust() {
  
  }
}

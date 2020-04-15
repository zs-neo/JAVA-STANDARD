package standard.polymorphism;

public class WoodWind extends Wind {
  
  @Override
  public void play() {
    System.out.println("WoodWind play");
  }
  
  @Override
  public String what() {
    return "WoodWind";
  }
  
}

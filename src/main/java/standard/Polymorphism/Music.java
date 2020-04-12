package standard.Polymorphism;

public class Music {
  
  static void tune(Instrument i) {
    i.play();
  }
  
  static void tuneAll(Instrument[] e) {
    for (int i = 0; i < e.length; i++) {
      tune(e[i]);
    }
  }
  
  public static void main(String[] args) {
    Instrument[] orchestra = new Instrument[5];
    int i = 0;
// Upcasting during addition to the array:
    orchestra[i++] = new Wind();
    orchestra[i++] = new Percession();
    orchestra[i++] = new Stringed();
    orchestra[i++] = new Brass();
    orchestra[i++] = new WoodWind();
    tuneAll(orchestra);
  }
  
}
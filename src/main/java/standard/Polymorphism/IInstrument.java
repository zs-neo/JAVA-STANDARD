package standard.Polymorphism;

public interface IInstrument {
  
  int i = (int)(Math.random() * 10);
  int j = 0;
  
  void play();
  
  String what();
  
  void adjust();
  
}

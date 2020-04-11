package standard.enumeration;

import java.util.BitSet;
import java.util.Enumeration;
import java.util.Stack;
import java.util.Vector;

public class CatsAndDogs {
  
  public static void main(String[] args) {
    Vector cats = new Vector();
    for (int i = 0; i < 7; i++)
      cats.addElement(new Cat(i));
// Not a problem to add a dog to cats:
    cats.addElement(new Dog(7));
    Enumeration e = cats.elements();
    while (e.hasMoreElements()) {
      ((Cat) e.nextElement()).print();
    }
// Dog is detected only at run-time
  }
  
}

package standard;

import standard.entity.Number;

public class Assignment {
  
  static void func(Number number) {
    number.i = 10;
  }
  
  public static void main(String[] args) {
    Number n1 = new Number();
    Number n2 = new Number();
    n1.i = 9;
    n2.i = 47;
    System.out.println(n1.i + " " + n2.i);
    n1 = n2;
    System.out.println(n1.i + " " + n2.i);
    n1.i = 27;
    System.out.println(n1.i + " " + n2.i);
    
    Number n3 = new Number();
    n3.i = 1;
    System.out.println(n3.i);
    func(n3);
    System.out.println(n3.i);
    
    Integer i1 = new Integer(47);
    Integer i2 = new Integer(47);
    System.out.println(i1 == i2);
    System.out.println(i1.equals(i2));
    
    Number n4 = new Number();
    Number n5 = new Number();
    n4.i = n5.i = 100;
    System.out.println(n5 == n4);
    System.out.println(n5.equals(n4));
  }
  
}

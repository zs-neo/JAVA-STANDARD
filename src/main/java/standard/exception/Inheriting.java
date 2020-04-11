package standard.exception;

public class Inheriting {
  public static void f() throws MyException {
    System.out.println(
      "Throwing MyException from f()");
    throw new MyException();
  }
  
  public static void g() throws MyException {
    System.out.println(
      "Throwing MyException from g()");
    throw new MyException("Originated in g()");
  }
  
  public static void h() throws MyException {
    System.out.println(
      "Throwing MyException from h()");
    throw new MyException("Originated in h()", 47);
  }
  
  public static void main(String[] args) {
    try {
      f();
    } catch (MyException e) {
      e.printStackTrace();
    }
    try {
      g();
    } catch (MyException e) {
      e.printStackTrace();
    }
    try {
      h();
    } catch (MyException e) {
      e.printStackTrace();
      System.out.println("e.val() = " + e.val());
    }
  }
} ///:~

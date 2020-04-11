package standard.exception;

public class MyException extends Exception {
  
  private int i;
  
  public MyException() {
  }
  
  public MyException(String message) {
    super(message);
  }
  
  public MyException(String msg,int i){
    this.i = i;
  }
  
  public int val(){
    return i;
  }
  
}

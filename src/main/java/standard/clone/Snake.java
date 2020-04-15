package standard.clone;

public class Snake implements Cloneable {
  
  private Snake next;
  private char c;
  
  public Snake(int i, char c) {
    this.c = c;
    if(--i>0){
      next = new Snake(i,(char)(c+1));
    }
  }
  
  void increment(){
    c++;
    if(next!=null){
      next.increment();
    }
  }
  
  @Override
  public String toString(){
    String s = c + ":";
    if(next!=null){
      s+=next.toString();
    }
    return s;
  }
  
  @Override
  public Object clone(){
    Object obj = null;
    try{
      obj = super.clone();
    }catch (CloneNotSupportedException e){
      e.printStackTrace();
    }
    return obj;
  }
  
  public static void main(String[] args) {
    Snake s = new Snake(5,'a');
    System.out.println(s.toString());
    Snake s2 = (Snake)s.clone();
    System.out.println(s2.toString());
    s.increment();
    System.out.println(s2.toString());
  }
}

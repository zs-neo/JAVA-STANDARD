package standard.clone.serial;

import java.io.*;

public class Compete {
  
  static final int SIZE = 5000;
  
  public static void main(String[] args) {
    Thing2[] a = new Thing2[SIZE];
    for (int i = 0; i < a.length; i++) a[i] = new Thing2();
    Thing4[] b = new Thing4[SIZE];
    for (int i = 0; i < b.length; i++) b[i] = new Thing4();
    
    try {
      long t1 = System.currentTimeMillis();
      ByteArrayOutputStream buf = new ByteArrayOutputStream();
      ObjectOutputStream oos = new ObjectOutputStream(buf);
      for (int i = 0; i < a.length; i++) {
        oos.writeObject(a[i]);
      }
      
      ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buf.toByteArray()));
      Thing2[] c = new Thing2[SIZE];
      for (int i = 0; i < c.length; i++) {
        c[i] = (Thing2) ois.readObject();
      }
      
      long t2 = System.currentTimeMillis();
      t1 = System.currentTimeMillis();
      Thing4[] d = new Thing4[SIZE];
      for (int i = 0; i < d.length; i++) d[i] = (Thing4) b[i].clone();
      
      t2 = System.currentTimeMillis();
      System.out.println("Duplication via cloning: " + (t2 - t1) + " Milliseconds");
    } catch (IOException e) {
    
    } catch (ClassNotFoundException e) {
    
    }
  }
  
}

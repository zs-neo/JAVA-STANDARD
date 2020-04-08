package standard;

import java.util.Date;
import java.util.Properties;

/**
 * The first Thinking in Java example program.
 * Lists system information on current machine.
 *
 * @author Bruce Eckel
 * @author http://www.BruceEckel.com
 * @version 1.0
 */
public class Property {
  
  /**
   * 测试方法及参数
   *
   * @param a 测试参数
   */
  public void test(String a) {
  
  }
  
  /**
   * Sole entry point to class & application
   *
   * @param args array of string arguments
   * @return No return value
   * @throws exceptions No exceptions thrown
   */
  public static void main(String[] args) {
    System.out.println(new Date());
    Property property = new Property();
    property.test("");
    Properties properties = System.getProperties();
    properties.list(System.out);
    System.out.println("--- Memory Usage:");
    Runtime rt = Runtime.getRuntime();
    System.out.println("Total Memory = "
      + rt.totalMemory()
      + " Free Memory = "
      + rt.freeMemory());
  }
  
}

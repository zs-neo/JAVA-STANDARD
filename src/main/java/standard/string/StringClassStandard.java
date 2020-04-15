package standard.string;

public class StringClassStandard {
  
  private String s = new String();
  
  /**
   * StringBuffer 是线程安全的可变字符集合，适用于多线程场景。
   * 将类定义成 final形式，为了保证变量初始化后的引用对象不可以重新赋值，主要是为了“效率”和“安全性”的考虑
   */
  private StringBuffer stringBuffer;
  
  /**
   *
   */
  private StringBuilder stringBuilder;
  
}
